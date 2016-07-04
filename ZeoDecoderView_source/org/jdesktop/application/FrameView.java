/*    */ package org.jdesktop.application;
/*    */ 
/*    */ import java.util.logging.Logger;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JRootPane;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FrameView
/*    */   extends View
/*    */ {
/* 19 */   private static final Logger logger = Logger.getLogger(FrameView.class.getName());
/* 20 */   private JFrame frame = null;
/*    */   
/*    */   public FrameView(Application application) {
/* 23 */     super(application);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public JFrame getFrame()
/*    */   {
/* 42 */     if (this.frame == null) {
/* 43 */       String title = getContext().getResourceMap().getString("Application.title", new Object[0]);
/* 44 */       this.frame = new JFrame(title);
/* 45 */       this.frame.setName("mainFrame");
/*    */     }
/* 47 */     return this.frame;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setFrame(JFrame frame)
/*    */   {
/* 73 */     if (frame == null) {
/* 74 */       throw new IllegalArgumentException("null JFrame");
/*    */     }
/* 76 */     if (this.frame != null) {
/* 77 */       throw new IllegalStateException("frame already set");
/*    */     }
/* 79 */     this.frame = frame;
/* 80 */     firePropertyChange("frame", null, this.frame);
/*    */   }
/*    */   
/*    */   public JRootPane getRootPane() {
/* 84 */     return getFrame().getRootPane();
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\jdesktop\application\FrameView.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */