/*     */ package graphs;
/*     */ 
/*     */ import graphs.common.Layer;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Line2D.Float;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Float;
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
/*     */ public class GridLayer
/*     */   implements Layer
/*     */ {
/*     */   private float width;
/*     */   private float height;
/*     */   private float majorXTickMarkBleed;
/*     */   private int xTickMarkCount;
/*     */   private float xTickMarkSpacing;
/*     */   private Color majorXTickMarkColor;
/*     */   private Color minorXTickMarkColor;
/*     */   private Color yTickMarkColor;
/*     */   private int majorXTickMarkEvery;
/*     */   private int firstMajorXTickMarkAt;
/*     */   
/*     */   public GridLayer(float width, float height, float majorXTickMarkBleed)
/*     */   {
/*  44 */     this(width, height, majorXTickMarkBleed, 25, width / 24.0F, new Color(13684944), new Color(15790320), new Color(15790320), 3, 0);
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
/*     */   public GridLayer(float width, float height, float majorXTickMarkBleed, int xTickMarkCount, float xTickMarkSpacing, Color majorXTickMarkColor, Color minorXTickMarkColor, Color yTickMarkColor, int majorXTickMarkEvery, int firstMajorXTickMarkAt)
/*     */   {
/*  59 */     this.width = width;
/*  60 */     this.height = height;
/*  61 */     this.majorXTickMarkBleed = majorXTickMarkBleed;
/*  62 */     this.xTickMarkCount = xTickMarkCount;
/*  63 */     this.xTickMarkSpacing = xTickMarkSpacing;
/*  64 */     this.majorXTickMarkColor = majorXTickMarkColor;
/*  65 */     this.minorXTickMarkColor = minorXTickMarkColor;
/*  66 */     this.yTickMarkColor = yTickMarkColor;
/*  67 */     this.majorXTickMarkEvery = majorXTickMarkEvery;
/*  68 */     this.firstMajorXTickMarkAt = firstMajorXTickMarkAt;
/*     */   }
/*     */   
/*     */   public Rectangle2D getBoundingBox()
/*     */   {
/*  73 */     return new Rectangle2D.Float(0.0F, 0.0F, this.width, this.height + this.majorXTickMarkBleed);
/*     */   }
/*     */   
/*     */   public void render(Graphics2D g)
/*     */   {
/*  78 */     float qh = Math.round(this.height / 4.0F);
/*  79 */     float[] yMarks = {
/*  80 */       this.majorXTickMarkBleed, 
/*  81 */       this.majorXTickMarkBleed + qh, 
/*  82 */       this.majorXTickMarkBleed + 2.0F * qh, 
/*  83 */       this.majorXTickMarkBleed + 3.0F * qh, 
/*  84 */       this.majorXTickMarkBleed + this.height };
/*     */     
/*     */ 
/*  87 */     for (int i = 0; i < yMarks.length; i++) {
/*  88 */       g.setPaint(this.yTickMarkColor);
/*  89 */       g.draw(new Line2D.Float(0.0F, yMarks[i], this.width, yMarks[i]));
/*     */     }
/*     */     
/*  92 */     for (int i = 0; i < this.xTickMarkCount; i++) {
/*  93 */       float x = this.xTickMarkSpacing * i;
/*     */       float h;
/*     */       Color c;
/*     */       float y;
/*     */       float h;
/*  98 */       if ((i - this.firstMajorXTickMarkAt) % this.majorXTickMarkEvery == 0) {
/*  99 */         Color c = this.majorXTickMarkColor;
/* 100 */         float y = 0.0F;
/* 101 */         h = this.height + this.majorXTickMarkBleed;
/*     */       } else {
/* 103 */         c = this.minorXTickMarkColor;
/* 104 */         y = this.majorXTickMarkBleed;
/* 105 */         h = this.height;
/*     */       }
/*     */       
/* 108 */       g.setPaint(c);
/* 109 */       g.draw(new Line2D.Float(x, y, x, y + h));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\graphs\GridLayer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */