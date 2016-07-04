/*     */ package org.joda.time.tz;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import java.util.TreeSet;
/*     */ import org.joda.time.DateTimeZone;
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
/*     */ public class ZoneInfoProvider
/*     */   implements Provider
/*     */ {
/*     */   private final File iFileDir;
/*     */   private final String iResourcePath;
/*     */   private final ClassLoader iLoader;
/*     */   private final Map iZoneInfoMap;
/*     */   
/*     */   public ZoneInfoProvider(File paramFile)
/*     */     throws IOException
/*     */   {
/*  57 */     if (paramFile == null) {
/*  58 */       throw new IllegalArgumentException("No file directory provided");
/*     */     }
/*  60 */     if (!paramFile.exists()) {
/*  61 */       throw new IOException("File directory doesn't exist: " + paramFile);
/*     */     }
/*  63 */     if (!paramFile.isDirectory()) {
/*  64 */       throw new IOException("File doesn't refer to a directory: " + paramFile);
/*     */     }
/*     */     
/*  67 */     this.iFileDir = paramFile;
/*  68 */     this.iResourcePath = null;
/*  69 */     this.iLoader = null;
/*     */     
/*  71 */     this.iZoneInfoMap = loadZoneInfoMap(openResource("ZoneInfoMap"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ZoneInfoProvider(String paramString)
/*     */     throws IOException
/*     */   {
/*  82 */     this(paramString, null, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ZoneInfoProvider(String paramString, ClassLoader paramClassLoader)
/*     */     throws IOException
/*     */   {
/*  96 */     this(paramString, paramClassLoader, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private ZoneInfoProvider(String paramString, ClassLoader paramClassLoader, boolean paramBoolean)
/*     */     throws IOException
/*     */   {
/* 107 */     if (paramString == null) {
/* 108 */       throw new IllegalArgumentException("No resource path provided");
/*     */     }
/* 110 */     if (!paramString.endsWith("/")) {
/* 111 */       paramString = paramString + '/';
/*     */     }
/*     */     
/* 114 */     this.iFileDir = null;
/* 115 */     this.iResourcePath = paramString;
/*     */     
/* 117 */     if ((paramClassLoader == null) && (!paramBoolean)) {
/* 118 */       paramClassLoader = getClass().getClassLoader();
/*     */     }
/*     */     
/* 121 */     this.iLoader = paramClassLoader;
/*     */     
/* 123 */     this.iZoneInfoMap = loadZoneInfoMap(openResource("ZoneInfoMap"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized DateTimeZone getZone(String paramString)
/*     */   {
/* 136 */     if (paramString == null) {
/* 137 */       return null;
/*     */     }
/*     */     
/* 140 */     Object localObject = this.iZoneInfoMap.get(paramString);
/* 141 */     if (localObject == null) {
/* 142 */       return null;
/*     */     }
/*     */     
/* 145 */     if (paramString.equals(localObject))
/*     */     {
/* 147 */       return loadZoneData(paramString);
/*     */     }
/*     */     
/* 150 */     if ((localObject instanceof SoftReference)) {
/* 151 */       DateTimeZone localDateTimeZone = (DateTimeZone)((SoftReference)localObject).get();
/* 152 */       if (localDateTimeZone != null) {
/* 153 */         return localDateTimeZone;
/*     */       }
/*     */       
/* 156 */       return loadZoneData(paramString);
/*     */     }
/*     */     
/*     */ 
/* 160 */     return getZone((String)localObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized Set getAvailableIDs()
/*     */   {
/* 172 */     return new TreeSet(this.iZoneInfoMap.keySet());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void uncaughtException(Exception paramException)
/*     */   {
/* 181 */     Thread localThread = Thread.currentThread();
/* 182 */     localThread.getThreadGroup().uncaughtException(localThread, paramException);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private InputStream openResource(String paramString)
/*     */     throws IOException
/*     */   {
/*     */     Object localObject;
/*     */     
/*     */ 
/* 194 */     if (this.iFileDir != null) {
/* 195 */       localObject = new FileInputStream(new File(this.iFileDir, paramString));
/*     */     } else {
/* 197 */       String str = this.iResourcePath.concat(paramString);
/* 198 */       if (this.iLoader != null) {
/* 199 */         localObject = this.iLoader.getResourceAsStream(str);
/*     */       } else {
/* 201 */         localObject = ClassLoader.getSystemResourceAsStream(str);
/*     */       }
/* 203 */       if (localObject == null) {
/* 204 */         StringBuffer localStringBuffer = new StringBuffer(40).append("Resource not found: \"").append(str).append("\" ClassLoader: ").append(this.iLoader != null ? this.iLoader.toString() : "system");
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 209 */         throw new IOException(localStringBuffer.toString());
/*     */       }
/*     */     }
/* 212 */     return (InputStream)localObject;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private DateTimeZone loadZoneData(String paramString)
/*     */   {
/* 222 */     InputStream localInputStream = null;
/*     */     try {
/* 224 */       localInputStream = openResource(paramString);
/* 225 */       DateTimeZone localDateTimeZone1 = DateTimeZoneBuilder.readFrom(localInputStream, paramString);
/* 226 */       this.iZoneInfoMap.put(paramString, new SoftReference(localDateTimeZone1));
/* 227 */       return localDateTimeZone1;
/*     */     } catch (IOException localIOException1) { DateTimeZone localDateTimeZone2;
/* 229 */       uncaughtException(localIOException1);
/* 230 */       this.iZoneInfoMap.remove(paramString);
/* 231 */       return null;
/*     */     } finally {
/*     */       try {
/* 234 */         if (localInputStream != null) {
/* 235 */           localInputStream.close();
/*     */         }
/*     */       }
/*     */       catch (IOException localIOException2) {}
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static Map loadZoneInfoMap(InputStream paramInputStream)
/*     */     throws IOException
/*     */   {
/* 250 */     TreeMap localTreeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
/* 251 */     DataInputStream localDataInputStream = new DataInputStream(paramInputStream);
/*     */     try {
/* 253 */       readZoneInfoMap(localDataInputStream, localTreeMap);
/*     */       try
/*     */       {
/* 256 */         localDataInputStream.close();
/*     */       }
/*     */       catch (IOException localIOException1) {}
/*     */       
/* 260 */       localTreeMap.put("UTC", new SoftReference(DateTimeZone.UTC));
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/* 256 */         localDataInputStream.close();
/*     */       }
/*     */       catch (IOException localIOException2) {}
/*     */     }
/*     */     
/* 261 */     return localTreeMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void readZoneInfoMap(DataInputStream paramDataInputStream, Map paramMap)
/*     */     throws IOException
/*     */   {
/* 272 */     int i = paramDataInputStream.readUnsignedShort();
/* 273 */     String[] arrayOfString = new String[i];
/* 274 */     for (int j = 0; j < i; j++) {
/* 275 */       arrayOfString[j] = paramDataInputStream.readUTF().intern();
/*     */     }
/*     */     
/*     */ 
/* 279 */     i = paramDataInputStream.readUnsignedShort();
/* 280 */     for (j = 0; j < i; j++) {
/*     */       try {
/* 282 */         paramMap.put(arrayOfString[paramDataInputStream.readUnsignedShort()], arrayOfString[paramDataInputStream.readUnsignedShort()]);
/*     */       } catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {
/* 284 */         throw new IOException("Corrupt zone info map");
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\tz\ZoneInfoProvider.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */