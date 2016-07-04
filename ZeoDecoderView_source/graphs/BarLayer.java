/*    */ package graphs;
/*    */ 
/*    */ import graphs.common.Layer;
/*    */ import graphs.common.ZeoColors;
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import java.awt.geom.Rectangle2D.Float;
/*    */ import java.util.List;
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
/*    */ public class BarLayer
/*    */   implements Layer
/*    */ {
/*    */   private List<Integer> barValues;
/*    */   private float barWidth;
/*    */   private float maxHeight;
/*    */   private float barSpacing;
/*    */   
/*    */   public BarLayer(List<Integer> barValues, float barWidth, float maxHeight, float barSpacing)
/*    */   {
/* 35 */     this.barValues = barValues;
/* 36 */     this.barWidth = barWidth;
/* 37 */     this.maxHeight = maxHeight;
/* 38 */     this.barSpacing = barSpacing;
/*    */   }
/*    */   
/*    */   public Rectangle2D getBoundingBox()
/*    */   {
/* 43 */     return new Rectangle2D.Float(0.0F, 0.0F, 
/* 44 */       (this.barWidth + this.barSpacing) * this.barValues.size(), 
/* 45 */       this.maxHeight);
/*    */   }
/*    */   
/*    */ 
/*    */   public void render(Graphics2D g)
/*    */   {
/* 51 */     float qh = Math.round(this.maxHeight / 4.0F);
/* 52 */     float deepHeight = qh;
/* 53 */     float lightHeight = qh * 2.0F;
/* 54 */     float remHeight = qh * 3.0F;
/* 55 */     float wakeHeight = this.maxHeight;
/*    */     
/* 57 */     for (int i = 0; i < this.barValues.size(); i++) {
/* 58 */       float h = 0.0F;
/* 59 */       Color c = null;
/*    */       
/* 61 */       switch (((Integer)this.barValues.get(i)).intValue()) {
/*    */       case 1: 
/* 63 */         h = wakeHeight;
/* 64 */         c = ZeoColors.WAKE;
/* 65 */         break;
/*    */       
/*    */       case 2: 
/* 68 */         h = remHeight;
/* 69 */         c = ZeoColors.REM_ZQ;
/* 70 */         break;
/*    */       
/*    */       case 3: 
/* 73 */         h = lightHeight;
/* 74 */         c = ZeoColors.LIGHT_SLEEP;
/* 75 */         break;
/*    */       
/*    */       case 4: 
/* 78 */         h = deepHeight;
/* 79 */         c = ZeoColors.DEEP_SLEEP;
/* 80 */         break;
/*    */       }
/*    */       
/*    */       
/*    */ 
/*    */ 
/* 86 */       Rectangle2D bar = 
/* 87 */         new Rectangle2D.Float(i * (this.barWidth + this.barSpacing), 
/* 88 */         this.maxHeight - h, 
/* 89 */         this.barWidth, 
/* 90 */         h);
/*    */       
/* 92 */       g.setPaint(c);
/* 93 */       g.fill(bar);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\graphs\BarLayer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */