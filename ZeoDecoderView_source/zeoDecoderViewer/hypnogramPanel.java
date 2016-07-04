/*    */ package zeoDecoderViewer;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.awt.image.RenderedImage;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class hypnogramPanel
/*    */   extends JPanel
/*    */ {
/*    */   private static final long serialVersionUID = -3577421868368524385L;
/*    */   private RenderedImage hypImage;
/*    */   
/*    */   public hypnogramPanel()
/*    */   {
/* 23 */     setBackground(Color.WHITE);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setHypnogramImage(RenderedImage hyp)
/*    */   {
/* 31 */     this.hypImage = hyp;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void clear()
/*    */   {
/* 38 */     this.hypImage = null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void paintComponent(Graphics g)
/*    */   {
/* 45 */     super.paintComponent(g);
/*    */     
/* 47 */     Graphics2D g2 = (Graphics2D)g;
/* 48 */     int width = getWidth();
/* 49 */     int height = getHeight();
/* 50 */     if (this.hypImage != null)
/*    */     {
/* 52 */       AffineTransform scaleXform = AffineTransform.getScaleInstance(width / 576.0F, height / 140.0F);
/* 53 */       g2.clearRect(0, 0, width, height);
/* 54 */       g2.drawRenderedImage(this.hypImage, scaleXform);
/*    */     }
/*    */     else {
/* 57 */       g2.setColor(Color.WHITE);
/* 58 */       g2.fillRect(0, 0, width, height);
/* 59 */       g2.setColor(Color.BLACK);
/* 60 */       g2.drawString("Insufficient Data", width / 2 - 50, height / 2 - 10);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\zeoDecoderViewer\hypnogramPanel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */