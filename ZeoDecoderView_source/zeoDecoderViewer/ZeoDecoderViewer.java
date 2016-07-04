/*    */ package zeoDecoderViewer;
/*    */ 
/*    */ import java.awt.Window;
/*    */ import org.jdesktop.application.Application;
/*    */ import org.jdesktop.application.SingleFrameApplication;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ZeoDecoderViewer
/*    */   extends SingleFrameApplication
/*    */ {
/*    */   protected void startup()
/*    */   {
/* 19 */     show(new ZeoDecodeView(this));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void configureWindow(Window root) {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static ZeoDecoderViewer getApplication()
/*    */   {
/* 35 */     return (ZeoDecoderViewer)Application.getInstance(ZeoDecoderViewer.class);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 42 */     launch(ZeoDecoderViewer.class, args);
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\zeoDecoderViewer\ZeoDecoderViewer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */