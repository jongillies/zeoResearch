/*    */ package graphs;
/*    */ 
/*    */ import graphs.common.Layer;
/*    */ import java.awt.Color;
/*    */ import java.awt.Font;
/*    */ import java.awt.FontMetrics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Toolkit;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import java.awt.geom.Rectangle2D.Float;
/*    */ import org.joda.time.LocalDateTime;
/*    */ import org.joda.time.Minutes;
/*    */ import org.joda.time.format.DateTimeFormat;
/*    */ import org.joda.time.format.DateTimeFormatter;
/*    */ 
/*    */ public class AxisLayer implements Layer
/*    */ {
/* 18 */   private static DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("ha");
/*    */   
/*    */ 
/*    */ 
/*    */   private LocalDateTime startTime;
/*    */   
/*    */ 
/*    */   private float imageWidth;
/*    */   
/*    */ 
/*    */   private float barWidth;
/*    */   
/*    */ 
/*    */   private float barSpacing;
/*    */   
/*    */ 
/*    */   private Font font;
/*    */   
/*    */ 
/*    */   private Color color;
/*    */   
/*    */ 
/*    */   private FontMetrics fontMetrics;
/*    */   
/*    */ 
/*    */ 
/*    */   public AxisLayer(LocalDateTime startTime, LocalDateTime endTime, float imageWidth, float barWidth, float barSpacing, Font font, Color color)
/*    */   {
/* 46 */     this.startTime = startTime;
/*    */     
/* 48 */     this.imageWidth = imageWidth;
/* 49 */     this.barWidth = barWidth;
/* 50 */     this.barSpacing = barSpacing;
/* 51 */     this.font = font;
/* 52 */     this.color = color;
/*    */     
/* 54 */     this.fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public Rectangle2D getBoundingBox()
/*    */   {
/* 62 */     float w = this.imageWidth;
/* 63 */     float h = this.fontMetrics.getHeight() + 2 * this.fontMetrics.getLeading();
/*    */     
/* 65 */     return new Rectangle2D.Float(0.0F, 0.0F, w, h);
/*    */   }
/*    */   
/*    */   public void render(Graphics2D g)
/*    */   {
/* 70 */     g.setFont(this.font);
/* 71 */     g.setPaint(this.color);
/*    */     
/* 73 */     float y = this.fontMetrics.getLeading() + this.fontMetrics.getAscent();
/*    */     
/* 75 */     LocalDateTime currentTime = this.startTime;
/*    */     
/* 77 */     float x = 0.0F;
/* 78 */     int barNumber = 0;
/* 79 */     while (x < this.imageWidth) {
/* 80 */       x = barNumber * (this.barWidth + this.barSpacing);
/*    */       
/* 82 */       if (currentTime.getMinuteOfHour() == 0)
/*    */       {
/* 84 */         String label = TIME_FORMATTER.print(currentTime);
/*    */         
/*    */ 
/* 87 */         Rectangle2D labelBounds = g.getFontMetrics().getStringBounds(label, g);
/*    */         
/*    */ 
/* 90 */         if (x + labelBounds.getWidth() < this.imageWidth) {
/* 91 */           g.drawString(label, x, y);
/*    */         }
/*    */       }
/*    */       
/* 95 */       currentTime = currentTime.plus(Minutes.minutes(5));
/* 96 */       barNumber++;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\graphs\AxisLayer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */