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
/*     */ public abstract class PreciseDurationDateTimeField
/*     */   extends BaseDateTimeField
/*     */ {
/*     */   private static final long serialVersionUID = 5004523158306266035L;
/*     */   final long iUnitMillis;
/*     */   private final DurationField iUnitField;
/*     */   
/*     */   public PreciseDurationDateTimeField(DateTimeFieldType paramDateTimeFieldType, DurationField paramDurationField)
/*     */   {
/*  48 */     super(paramDateTimeFieldType);
/*     */     
/*  50 */     if (!paramDurationField.isPrecise()) {
/*  51 */       throw new IllegalArgumentException("Unit duration field must be precise");
/*     */     }
/*     */     
/*  54 */     this.iUnitMillis = paramDurationField.getUnitMillis();
/*  55 */     if (this.iUnitMillis < 1L) {
/*  56 */       throw new IllegalArgumentException("The unit milliseconds must be at least 1");
/*     */     }
/*     */     
/*  59 */     this.iUnitField = paramDurationField;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isLenient()
/*     */   {
/*  66 */     return false;
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
/*  78 */     FieldUtils.verifyValueBounds(this, paramInt, getMinimumValue(), getMaximumValueForSet(paramLong, paramInt));
/*     */     
/*  80 */     return paramLong + (paramInt - get(paramLong)) * this.iUnitMillis;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long roundFloor(long paramLong)
/*     */   {
/*  92 */     if (paramLong >= 0L) {
/*  93 */       return paramLong - paramLong % this.iUnitMillis;
/*     */     }
/*  95 */     paramLong += 1L;
/*  96 */     return paramLong - paramLong % this.iUnitMillis - this.iUnitMillis;
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
/*     */   public long roundCeiling(long paramLong)
/*     */   {
/* 109 */     if (paramLong > 0L) {
/* 110 */       paramLong -= 1L;
/* 111 */       return paramLong - paramLong % this.iUnitMillis + this.iUnitMillis;
/*     */     }
/* 113 */     return paramLong - paramLong % this.iUnitMillis;
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
/*     */   public long remainder(long paramLong)
/*     */   {
/* 126 */     if (paramLong >= 0L) {
/* 127 */       return paramLong % this.iUnitMillis;
/*     */     }
/* 129 */     return (paramLong + 1L) % this.iUnitMillis + this.iUnitMillis - 1L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DurationField getDurationField()
/*     */   {
/* 141 */     return this.iUnitField;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMinimumValue()
/*     */   {
/* 150 */     return 0;
/*     */   }
/*     */   
/*     */   public final long getUnitMillis() {
/* 154 */     return this.iUnitMillis;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int getMaximumValueForSet(long paramLong, int paramInt)
/*     */   {
/* 163 */     return getMaximumValue(paramLong);
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\field\PreciseDurationDateTimeField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */