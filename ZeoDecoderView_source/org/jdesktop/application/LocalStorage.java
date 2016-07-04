/*     */ package org.jdesktop.application;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.beans.DefaultPersistenceDelegate;
/*     */ import java.beans.Encoder;
/*     */ import java.beans.ExceptionListener;
/*     */ import java.beans.Expression;
/*     */ import java.beans.XMLDecoder;
/*     */ import java.beans.XMLEncoder;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.lang.reflect.Method;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LocalStorage
/*     */   extends AbstractBean
/*     */ {
/*  44 */   private static Logger logger = Logger.getLogger(LocalStorage.class.getName());
/*     */   private final ApplicationContext context;
/*  46 */   private long storageLimit = -1L;
/*  47 */   private LocalIO localIO = null;
/*  48 */   private final File unspecifiedFile = new File("unspecified");
/*  49 */   private File directory = this.unspecifiedFile;
/*     */   
/*     */   protected LocalStorage(ApplicationContext context) {
/*  52 */     if (context == null) {
/*  53 */       throw new IllegalArgumentException("null context");
/*     */     }
/*  55 */     this.context = context;
/*     */   }
/*     */   
/*     */   protected final ApplicationContext getContext()
/*     */   {
/*  60 */     return this.context;
/*     */   }
/*     */   
/*     */   private void checkFileName(String fileName) {
/*  64 */     if (fileName == null) {
/*  65 */       throw new IllegalArgumentException("null fileName");
/*     */     }
/*     */   }
/*     */   
/*     */   public InputStream openInputFile(String fileName) throws IOException {
/*  70 */     checkFileName(fileName);
/*  71 */     return getLocalIO().openInputFile(fileName);
/*     */   }
/*     */   
/*     */   public OutputStream openOutputFile(String fileName) throws IOException {
/*  75 */     checkFileName(fileName);
/*  76 */     return getLocalIO().openOutputFile(fileName);
/*     */   }
/*     */   
/*     */   public boolean deleteFile(String fileName) throws IOException {
/*  80 */     checkFileName(fileName);
/*  81 */     return getLocalIO().deleteFile(fileName);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static class AbortExceptionListener
/*     */     implements ExceptionListener
/*     */   {
/*  90 */     public Exception exception = null;
/*     */     
/*  92 */     public void exceptionThrown(Exception e) { if (this.exception == null) this.exception = e;
/*     */     }
/*     */   }
/*     */   
/*  96 */   private static boolean persistenceDelegatesInitialized = false;
/*     */   
/*     */   public void save(Object bean, String fileName) throws IOException {
/*  99 */     AbortExceptionListener el = new AbortExceptionListener(null);
/* 100 */     XMLEncoder e = null;
/*     */     
/*     */ 
/*     */ 
/* 104 */     ByteArrayOutputStream bst = new ByteArrayOutputStream();
/*     */     try {
/* 106 */       e = new XMLEncoder(bst);
/* 107 */       if (!persistenceDelegatesInitialized) {
/* 108 */         e.setPersistenceDelegate(Rectangle.class, new RectanglePD());
/* 109 */         persistenceDelegatesInitialized = true;
/*     */       }
/* 111 */       e.setExceptionListener(el);
/* 112 */       e.writeObject(bean);
/*     */     }
/*     */     finally {
/* 115 */       if (e != null) e.close();
/*     */     }
/* 117 */     if (el.exception != null) {
/* 118 */       throw new LSException("save failed \"" + fileName + "\"", el.exception);
/*     */     }
/* 120 */     OutputStream ost = null;
/*     */     try {
/* 122 */       ost = openOutputFile(fileName);
/* 123 */       ost.write(bst.toByteArray());
/*     */     }
/*     */     finally {
/* 126 */       if (ost != null) ost.close();
/*     */     }
/*     */   }
/*     */   
/*     */   public Object load(String fileName) throws IOException {
/* 131 */     InputStream ist = null;
/*     */     try {
/* 133 */       ist = openInputFile(fileName);
/*     */     }
/*     */     catch (IOException e) {
/* 136 */       return null;
/*     */     }
/* 138 */     AbortExceptionListener el = new AbortExceptionListener(null);
/* 139 */     XMLDecoder d = null;
/*     */     try {
/* 141 */       d = new XMLDecoder(ist);
/* 142 */       d.setExceptionListener(el);
/* 143 */       Object bean = d.readObject();
/* 144 */       if (el.exception != null) {
/* 145 */         throw new LSException("load failed \"" + fileName + "\"", el.exception);
/*     */       }
/* 147 */       return bean;
/*     */     }
/*     */     finally {
/* 150 */       if (d != null) d.close();
/*     */     }
/*     */   }
/*     */   
/*     */   private void closeStream(Closeable st, String fileName) throws IOException {
/* 155 */     if (st != null) {
/* 156 */       try { st.close();
/*     */       } catch (IOException e) {
/* 158 */         throw new LSException("close failed \"" + fileName + "\"", e);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public long getStorageLimit() {
/* 164 */     return this.storageLimit;
/*     */   }
/*     */   
/*     */   public void setStorageLimit(long storageLimit) {
/* 168 */     if (storageLimit < -1L) {
/* 169 */       throw new IllegalArgumentException("invalid storageLimit");
/*     */     }
/* 171 */     long oldValue = this.storageLimit;
/* 172 */     this.storageLimit = storageLimit;
/* 173 */     firePropertyChange("storageLimit", Long.valueOf(oldValue), Long.valueOf(this.storageLimit));
/*     */   }
/*     */   
/*     */   private String getId(String key, String def) {
/* 177 */     ResourceMap appResourceMap = getContext().getResourceMap();
/* 178 */     String id = appResourceMap.getString(key, new Object[0]);
/* 179 */     if (id == null) {
/* 180 */       logger.log(Level.WARNING, "unspecified resource " + key + " using " + def);
/* 181 */       id = def;
/*     */     }
/* 183 */     else if (id.trim().length() == 0) {
/* 184 */       logger.log(Level.WARNING, "empty resource " + key + " using " + def);
/* 185 */       id = def;
/*     */     }
/* 187 */     return id;
/*     */   }
/*     */   
/* 190 */   private String getApplicationId() { return getId("Application.id", getContext().getApplicationClass().getSimpleName()); }
/*     */   
/*     */   private String getVendorId() {
/* 193 */     return getId("Application.vendorId", "UnknownApplicationVendor");
/*     */   }
/*     */   
/*     */ 
/*     */   private static enum OSId
/*     */   {
/* 199 */     WINDOWS,  OSX,  UNIX;
/*     */     private OSId() {} }
/* 201 */   private OSId getOSId() { PrivilegedAction<String> doGetOSName = new PrivilegedAction() {
/*     */       public String run() {
/* 203 */         return System.getProperty("os.name");
/*     */       }
/* 205 */     };
/* 206 */     OSId id = OSId.UNIX;
/* 207 */     String osName = (String)AccessController.doPrivileged(doGetOSName);
/* 208 */     if (osName != null) {
/* 209 */       if (osName.toLowerCase().startsWith("mac os x")) {
/* 210 */         id = OSId.OSX;
/*     */       }
/* 212 */       else if (osName.contains("Windows")) {
/* 213 */         id = OSId.WINDOWS;
/*     */       }
/*     */     }
/* 216 */     return id;
/*     */   }
/*     */   
/*     */   public File getDirectory() {
/* 220 */     if (this.directory == this.unspecifiedFile) {
/* 221 */       this.directory = null;
/* 222 */       String userHome = null;
/*     */       try {
/* 224 */         userHome = System.getProperty("user.home");
/*     */       }
/*     */       catch (SecurityException ignore) {}
/*     */       
/* 228 */       if (userHome != null) {
/* 229 */         String applicationId = getApplicationId();
/* 230 */         OSId osId = getOSId();
/* 231 */         if (osId == OSId.WINDOWS) {
/* 232 */           File appDataDir = null;
/*     */           try {
/* 234 */             String appDataEV = System.getenv("APPDATA");
/* 235 */             if ((appDataEV != null) && (appDataEV.length() > 0)) {
/* 236 */               appDataDir = new File(appDataEV);
/*     */             }
/*     */           }
/*     */           catch (SecurityException ignore) {}
/*     */           
/* 241 */           String vendorId = getVendorId();
/* 242 */           if ((appDataDir != null) && (appDataDir.isDirectory()))
/*     */           {
/* 244 */             String path = vendorId + "\\" + applicationId + "\\";
/* 245 */             this.directory = new File(appDataDir, path);
/*     */           }
/*     */           else
/*     */           {
/* 249 */             String path = "Application Data\\" + vendorId + "\\" + applicationId + "\\";
/* 250 */             this.directory = new File(userHome, path);
/*     */           }
/*     */         }
/* 253 */         else if (osId == OSId.OSX)
/*     */         {
/* 255 */           String path = "Library/Application Support/" + applicationId + "/";
/* 256 */           this.directory = new File(userHome, path);
/*     */         }
/*     */         else
/*     */         {
/* 260 */           String path = "." + applicationId + "/";
/* 261 */           this.directory = new File(userHome, path);
/*     */         }
/*     */       }
/*     */     }
/* 265 */     return this.directory;
/*     */   }
/*     */   
/*     */   public void setDirectory(File directory) {
/* 269 */     File oldValue = this.directory;
/* 270 */     this.directory = directory;
/* 271 */     firePropertyChange("directory", oldValue, this.directory);
/*     */   }
/*     */   
/*     */   private static class LSException
/*     */     extends IOException
/*     */   {
/*     */     public LSException(String s, Throwable e)
/*     */     {
/* 279 */       super();
/* 280 */       initCause(e);
/*     */     }
/*     */     
/* 283 */     public LSException(String s) { super(); }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static class RectanglePD
/*     */     extends DefaultPersistenceDelegate
/*     */   {
/* 296 */     public RectanglePD() { super(); }
/*     */     
/*     */     protected Expression instantiate(Object oldInstance, Encoder out) {
/* 299 */       Rectangle oldR = (Rectangle)oldInstance;
/* 300 */       Object[] constructorArgs = { Integer.valueOf(oldR.x), Integer.valueOf(oldR.y), Integer.valueOf(oldR.width), Integer.valueOf(oldR.height) };
/*     */       
/*     */ 
/* 303 */       return new Expression(oldInstance, oldInstance.getClass(), "new", constructorArgs); } }
/*     */   private abstract class LocalIO { private LocalIO() {}
/*     */     public abstract InputStream openInputFile(String paramString) throws IOException;
/*     */     public abstract OutputStream openOutputFile(String paramString) throws IOException;
/*     */     public abstract boolean deleteFile(String paramString) throws IOException; }
/* 308 */   private synchronized LocalIO getLocalIO() { if (this.localIO == null) {
/* 309 */       this.localIO = getPersistenceServiceIO();
/* 310 */       if (this.localIO == null) {
/* 311 */         this.localIO = new LocalFileIO(null);
/*     */       }
/*     */     }
/* 314 */     return this.localIO;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private class LocalFileIO
/*     */     extends LocalStorage.LocalIO
/*     */   {
/* 323 */     private LocalFileIO() { super(null); }
/*     */     
/* 325 */     public InputStream openInputFile(String fileName) throws IOException { File path = new File(LocalStorage.this.getDirectory(), fileName);
/*     */       try {
/* 327 */         return new BufferedInputStream(new FileInputStream(path));
/*     */       }
/*     */       catch (IOException e) {
/* 330 */         throw new LocalStorage.LSException("couldn't open input file \"" + fileName + "\"", e);
/*     */       }
/*     */     }
/*     */     
/* 334 */     public OutputStream openOutputFile(String fileName) throws IOException { File dir = LocalStorage.this.getDirectory();
/* 335 */       if ((!dir.isDirectory()) && 
/* 336 */         (!dir.mkdirs())) {
/* 337 */         throw new LocalStorage.LSException("couldn't create directory " + dir);
/*     */       }
/*     */       
/* 340 */       File path = new File(dir, fileName);
/*     */       try {
/* 342 */         return new BufferedOutputStream(new FileOutputStream(path));
/*     */       }
/*     */       catch (IOException e) {
/* 345 */         throw new LocalStorage.LSException("couldn't open output file \"" + fileName + "\"", e);
/*     */       }
/*     */     }
/*     */     
/* 349 */     public boolean deleteFile(String fileName) throws IOException { File path = new File(LocalStorage.this.getDirectory(), fileName);
/* 350 */       return path.delete();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private LocalIO getPersistenceServiceIO()
/*     */   {
/*     */     try
/*     */     {
/* 361 */       Class smClass = Class.forName("javax.jnlp.ServiceManager");
/* 362 */       Method getServiceNamesMethod = smClass.getMethod("getServiceNames", new Class[0]);
/* 363 */       String[] serviceNames = (String[])getServiceNamesMethod.invoke(null, new Object[0]);
/* 364 */       boolean psFound = false;
/* 365 */       boolean bsFound = false;
/* 366 */       for (String serviceName : serviceNames) {
/* 367 */         if (serviceName.equals("javax.jnlp.BasicService")) {
/* 368 */           bsFound = true;
/*     */         }
/* 370 */         else if (serviceName.equals("javax.jnlp.PersistenceService")) {
/* 371 */           psFound = true;
/*     */         }
/*     */       }
/* 374 */       if ((bsFound) && (psFound)) {}
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 381 */       return null;
/*     */     }
/*     */     catch (Exception ignore) {}
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\jdesktop\application\LocalStorage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */