/*     */ package org.jdesktop.application;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Window;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.awt.event.HierarchyEvent;
/*     */ import java.awt.event.HierarchyListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JRootPane;
/*     */ import javax.swing.JWindow;
/*     */ import javax.swing.RootPaneContainer;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SingleFrameApplication
/*     */   extends Application
/*     */ {
/*  96 */   private static final Logger logger = Logger.getLogger(SingleFrameApplication.class.getName());
/*  97 */   private ResourceMap appResources = null;
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
/*     */   public final JFrame getMainFrame()
/*     */   {
/* 125 */     return getMainView().getFrame();
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
/*     */   protected final void setMainFrame(JFrame mainFrame)
/*     */   {
/* 149 */     getMainView().setFrame(mainFrame);
/*     */   }
/*     */   
/*     */   private String sessionFilename(Window window) {
/* 153 */     if (window == null) {
/* 154 */       return null;
/*     */     }
/*     */     
/* 157 */     String name = window.getName();
/* 158 */     return name + ".session.xml";
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
/*     */   protected void configureWindow(Window root)
/*     */   {
/* 182 */     getContext().getResourceMap().injectComponents(root);
/*     */   }
/*     */   
/*     */   private void initRootPaneContainer(RootPaneContainer c) {
/* 186 */     JComponent rootPane = c.getRootPane();
/*     */     
/* 188 */     Object k = "SingleFrameApplication.initRootPaneContainer";
/* 189 */     if (rootPane.getClientProperty(k) != null) {
/* 190 */       return;
/*     */     }
/* 192 */     rootPane.putClientProperty(k, Boolean.TRUE);
/*     */     
/* 194 */     Container root = rootPane.getParent();
/* 195 */     if ((root instanceof Window)) {
/* 196 */       configureWindow((Window)root);
/*     */     }
/*     */     
/* 199 */     JFrame mainFrame = getMainFrame();
/* 200 */     if (c == mainFrame) {
/* 201 */       mainFrame.addWindowListener(new MainFrameListener(null));
/* 202 */       mainFrame.setDefaultCloseOperation(0);
/*     */     }
/* 204 */     else if ((root instanceof Window)) {
/* 205 */       Window window = (Window)root;
/* 206 */       window.addHierarchyListener(new SecondaryWindowListener(null));
/*     */     }
/*     */     
/* 209 */     if ((root instanceof JFrame)) {
/* 210 */       root.addComponentListener(new FrameBoundsListener(null));
/*     */     }
/*     */     
/* 213 */     if ((root instanceof Window)) {
/* 214 */       Window window = (Window)root;
/* 215 */       if ((!root.isValid()) || (root.getWidth() == 0) || (root.getHeight() == 0)) {
/* 216 */         window.pack();
/*     */       }
/* 218 */       if ((!window.isLocationByPlatform()) && (root.getX() == 0) && (root.getY() == 0)) {
/* 219 */         Component owner = window.getOwner();
/* 220 */         if (owner == null) {
/* 221 */           owner = window != mainFrame ? mainFrame : null;
/*     */         }
/* 223 */         window.setLocationRelativeTo(owner);
/*     */       }
/*     */     }
/*     */     
/* 227 */     if ((root instanceof Window)) {
/* 228 */       String filename = sessionFilename((Window)root);
/* 229 */       if (filename != null) {
/*     */         try {
/* 231 */           getContext().getSessionStorage().restore(root, filename);
/*     */         }
/*     */         catch (Exception e) {
/* 234 */           String msg = String.format("couldn't restore sesssion [%s]", new Object[] { filename });
/* 235 */           logger.log(Level.WARNING, msg, e);
/*     */         }
/*     */       }
/*     */     }
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
/*     */   protected void show(JComponent c)
/*     */   {
/* 264 */     if (c == null) {
/* 265 */       throw new IllegalArgumentException("null JComponent");
/*     */     }
/* 267 */     JFrame f = getMainFrame();
/* 268 */     f.getContentPane().add(c, "Center");
/* 269 */     initRootPaneContainer(f);
/* 270 */     f.setVisible(true);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void show(JDialog c)
/*     */   {
/* 292 */     if (c == null) {
/* 293 */       throw new IllegalArgumentException("null JDialog");
/*     */     }
/* 295 */     initRootPaneContainer(c);
/* 296 */     c.setVisible(true);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void show(JFrame c)
/*     */   {
/* 317 */     if (c == null) {
/* 318 */       throw new IllegalArgumentException("null JFrame");
/*     */     }
/* 320 */     initRootPaneContainer(c);
/* 321 */     c.setVisible(true);
/*     */   }
/*     */   
/*     */   private void saveSession(Window window) {
/* 325 */     String filename = sessionFilename(window);
/* 326 */     if (filename != null) {
/*     */       try {
/* 328 */         getContext().getSessionStorage().save(window, filename);
/*     */       }
/*     */       catch (IOException e) {
/* 331 */         logger.log(Level.WARNING, "couldn't save sesssion", e);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isVisibleWindow(Window w) {
/* 337 */     return (w.isVisible()) && (((w instanceof JFrame)) || ((w instanceof JDialog)) || ((w instanceof JWindow)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private List<Window> getVisibleSecondaryWindows()
/*     */   {
/* 347 */     List<Window> rv = new ArrayList();
/* 348 */     Method getWindowsM = null;
/*     */     try {
/* 350 */       getWindowsM = Window.class.getMethod("getWindows", new Class[0]);
/*     */     }
/*     */     catch (Exception ignore) {}
/*     */     
/* 354 */     if (getWindowsM != null) {
/* 355 */       Window[] windows = null;
/*     */       try {
/* 357 */         windows = (Window[])getWindowsM.invoke(null, new Object[0]);
/*     */       }
/*     */       catch (Exception e) {
/* 360 */         throw new Error("HCTB - can't get top level windows list", e);
/*     */       }
/* 362 */       if (windows != null) {
/* 363 */         for (Window window : windows) {
/* 364 */           if (isVisibleWindow(window)) {
/* 365 */             rv.add(window);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 371 */       Frame[] frames = Frame.getFrames();
/* 372 */       if (frames != null) {
/* 373 */         for (Frame frame : frames) {
/* 374 */           if (isVisibleWindow(frame)) {
/* 375 */             rv.add(frame);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 380 */     return rv;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void shutdown()
/*     */   {
/* 389 */     saveSession(getMainFrame());
/* 390 */     for (Window window : getVisibleSecondaryWindows())
/* 391 */       saveSession(window);
/*     */   }
/*     */   
/*     */   private class MainFrameListener extends WindowAdapter {
/*     */     private MainFrameListener() {}
/*     */     
/* 397 */     public void windowClosing(WindowEvent e) { SingleFrameApplication.this.exit(e); }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private class SecondaryWindowListener
/*     */     implements HierarchyListener
/*     */   {
/*     */     private SecondaryWindowListener() {}
/*     */     
/*     */ 
/*     */ 
/*     */     public void hierarchyChanged(HierarchyEvent e)
/*     */     {
/* 411 */       if (((e.getChangeFlags() & 0x4) != 0L) && 
/* 412 */         ((e.getSource() instanceof Window))) {
/* 413 */         Window secondaryWindow = (Window)e.getSource();
/* 414 */         if (!secondaryWindow.isShowing()) {
/* 415 */           SingleFrameApplication.this.saveSession(secondaryWindow);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static class FrameBoundsListener
/*     */     implements ComponentListener
/*     */   {
/*     */     private void maybeSaveFrameSize(ComponentEvent e)
/*     */     {
/* 429 */       if ((e.getComponent() instanceof JFrame)) {
/* 430 */         JFrame f = (JFrame)e.getComponent();
/* 431 */         if ((f.getExtendedState() & 0x6) == 0) {
/* 432 */           String clientPropertyKey = "WindowState.normalBounds";
/* 433 */           f.getRootPane().putClientProperty(clientPropertyKey, f.getBounds());
/*     */         }
/*     */       } }
/*     */     
/* 437 */     public void componentResized(ComponentEvent e) { maybeSaveFrameSize(e); }
/*     */     
/*     */ 
/*     */     public void componentMoved(ComponentEvent e) {}
/*     */     
/*     */ 
/*     */     public void componentHidden(ComponentEvent e) {}
/*     */     
/*     */ 
/*     */     public void componentShown(ComponentEvent e) {}
/*     */   }
/*     */   
/* 449 */   private FrameView mainView = null;
/*     */   
/*     */   public FrameView getMainView() {
/* 452 */     if (this.mainView == null) {
/* 453 */       this.mainView = new FrameView(this);
/*     */     }
/* 455 */     return this.mainView;
/*     */   }
/*     */   
/*     */   public void show(View view) {
/* 459 */     if ((this.mainView == null) && ((view instanceof FrameView))) {
/* 460 */       this.mainView = ((FrameView)view);
/*     */     }
/* 462 */     RootPaneContainer c = (RootPaneContainer)view.getRootPane().getParent();
/* 463 */     initRootPaneContainer(c);
/* 464 */     ((Window)c).setVisible(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\jdesktop\application\SingleFrameApplication.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */