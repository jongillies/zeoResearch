/*     */ package org.joda.time.field;
/*     */ 
/*     */ import org.joda.time.DateTimeFieldType;
/*     */ import org.joda.time.DurationField;
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
/*     */ public class PreciseDateTimeField
/*     */   extends PreciseDurationDateTimeField
/*     */ {
/*     */   private static final long serialVersionUID = -5586801265774496376L;
/*     */   private final int iRange;
/*     */   private final DurationField iRangeField;
/*     */   
/*     */   public PreciseDateTimeField(DateTimeFieldType paramDateTimeFieldType, DurationField paramDurationField1, DurationField paramDurationField2)
/*     */   {
/*  57 */     super(paramDateTimeFieldType, paramDurationField1);
/*     */     
/*  59 */     if (!paramDurationField2.isPrecise()) {
/*  60 */       throw new IllegalArgumentException("Range duration field must be precise");
/*     */     }
/*     */     
/*  63 */     long l = paramDurationField2.getUnitMillis();
/*  64 */     this.iRange = ((int)(l / getUnitMillis()));
/*  65 */     if (this.iRange < 2) {
/*  66 */       throw new IllegalArgumentException("The effective range must be at least 2");
/*     */     }
/*     */     
/*  69 */     this.iRangeField = paramDurationField2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int get(long paramLong)
/*     */   {
/*  79 */     if (paramLong >= 0L) {
/*  80 */       return (int)(paramLong / getUnitMillis() % this.iRange);
/*     */     }
/*  82 */     return this.iRange - 1 + (int)((paramLong + 1L) / getUnitMillis() % this.iRange);
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
/*     */   public long addWrapField(long paramLong, int paramInt)
/*     */   {
/*  95 */     int i = get(paramLong);
/*  96 */     int j = FieldUtils.getWrappedValue(i, paramInt, getMinimumValue(), getMaximumValue());
/*     */     
/*     */ 
/*  99 */     return paramLong + (j - i) * getUnitMillis();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long set(long paramLong, int paramInt)
/*     */   {
/* 111 */     FieldUtils.verifyValueBounds(this, paramInt, getMinimumValue(), getMaximumValue());
/* 112 */     return paramLong + (paramInt - get(paramLong)) * this.iUnitMillis;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DurationField getRangeDurationField()
/*     */   {
/* 122 */     return this.iRangeField;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMaximumValue()
/*     */   {
/* 131 */     return this.iRange - 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRange()
/*     */   {
/* 143 */     return this.iRange;
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\field\PreciseDateTimeField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */