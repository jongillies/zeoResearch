/*    */ package graphs;
/*    */ 
/*    */ import graphs.common.Renderable;
/*    */ import java.awt.Color;
/*    */ import java.awt.Font;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import java.awt.geom.Rectangle2D.Float;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.util.List;
/*    */ import org.joda.time.LocalDateTime;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NightlyReportHypnogram
/*    */   implements Renderable
/*    */ {
/*    */   private static final float IMAGE_WIDTH = 576.0F;
/*    */   private static final float IMAGE_HEIGHT = 140.0F;
/*    */   private static final float DEFAULT_BAR_WIDTH = 3.0F;
/*    */   private static final float DEFAULT_BAR_SPACING = 1.0F;
/*    */   private static final float MIN_BAR_WIDTH = 2.5F;
/*    */   private static final float MIN_BAR_SPACING = 0.5F;
/* 31 */   private static final Font X_AXIS_FONT = new Font("Arial", 0, 11);
/*    */   
/* 33 */   private static final Color X_AXIS_COLOR = new Color(10066329);
/*    */   
/*    */ 
/*    */   private AxisLayer axisLayer;
/*    */   
/*    */   private BarLayer barLayer;
/*    */   
/*    */ 
/*    */   public NightlyReportHypnogram(List<Integer> barValues, LocalDateTime startTime, LocalDateTime endTime)
/*    */   {
/* 43 */     float barWidth = 3.0F;
/* 44 */     float barSpacing = 1.0F;
/*    */     
/* 46 */     if ((barWidth + barSpacing) * barValues.size() > 576.0F) {
/* 47 */       barWidth = 2.5F;
/* 48 */       barSpacing = 0.5F;
/*    */     }
/*    */     
/* 51 */     this.axisLayer = new AxisLayer(startTime, 
/* 52 */       endTime, 
/* 53 */       576.0F, 
/* 54 */       barWidth, 
/* 55 */       barSpacing, 
/* 56 */       X_AXIS_FONT, 
/* 57 */       X_AXIS_COLOR);
/*    */     
/* 59 */     float maxBarHeight = 140.0F - (float)this.axisLayer.getBoundingBox().getHeight();
/*    */     
/* 61 */     this.barLayer = new BarLayer(barValues, barWidth, maxBarHeight, barSpacing);
/*    */   }
/*    */   
/*    */   public RenderedImage render()
/*    */   {
/* 66 */     BufferedImage img = new BufferedImage(576, 
/* 67 */       140, 
/* 68 */       1);
/*    */     
/* 70 */     Graphics2D g = (Graphics2D)img.getGraphics();
/* 71 */     g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 72 */     g.setColor(Color.WHITE);
/* 73 */     g.fill(new Rectangle2D.Float(0.0F, 0.0F, 576.0F, 140.0F));
/*    */     
/* 75 */     this.barLayer.render(g);
/*    */     
/* 77 */     g.translate(0.0D, this.barLayer.getBoundingBox().getHeight());
/* 78 */     this.axisLayer.render(g);
/*    */     
/* 80 */     return img;
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\graphs\NightlyReportHypnogram.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */