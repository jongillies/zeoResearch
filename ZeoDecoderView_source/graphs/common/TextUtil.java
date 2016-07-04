/*    */ package graphs.common;
/*    */ 
/*    */ import java.awt.FontMetrics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.font.LineMetrics;
/*    */ import java.awt.geom.Point2D;
/*    */ import java.awt.geom.Point2D.Float;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TextUtil
/*    */ {
/*    */   public static Point2D getPointForCenteredText(Graphics2D g, String text, Rectangle2D container)
/*    */   {
/* 19 */     FontMetrics fm = g.getFontMetrics();
/* 20 */     Rectangle2D bounds = fm.getStringBounds(text, g);
/* 21 */     LineMetrics metrics = fm.getLineMetrics(text, g);
/*    */     
/*    */ 
/* 24 */     float width = (float)bounds.getWidth();
/*    */     
/*    */ 
/* 27 */     float lineheight = metrics.getHeight();
/*    */     
/*    */ 
/* 30 */     float ascent = metrics.getAscent();
/*    */     
/* 32 */     float x = (float)(container.getX() + (container.getWidth() - width) / 2.0D);
/* 33 */     float y = (float)(container.getY() + (container.getHeight() - lineheight) / 2.0D + ascent);
/*    */     
/* 35 */     return new Point2D.Float(x, y);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void drawTextCenteredAt(Graphics2D g, String text, Point2D center)
/*    */   {
/* 43 */     FontMetrics fm = g.getFontMetrics();
/* 44 */     Rectangle2D bounds = fm.getStringBounds(text, g);
/*    */     
/*    */ 
/* 47 */     float width = (float)bounds.getWidth();
/*    */     
/* 49 */     g.drawString(text, (float)center.getX() - width / 2.0F, (float)center.getY());
/*    */   }
/*    */   
/*    */   public static Rectangle2D getStringBounds(Graphics2D g, String text)
/*    */   {
/* 54 */     FontMetrics fm = g.getFontMetrics();
/* 55 */     return fm.getStringBounds(text, g);
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\graphs\common\TextUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */