/*     */ package org.joda.time.chrono;
/*     */ 
/*     */ import org.joda.time.DateTimeFieldType;
/*     */ import org.joda.time.DurationField;
/*     */ import org.joda.time.ReadablePartial;
/*     */ import org.joda.time.field.PreciseDurationDateTimeField;
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
/*     */ final class BasicWeekOfWeekyearDateTimeField
/*     */   extends PreciseDurationDateTimeField
/*     */ {
/*     */   private static final long serialVersionUID = -1587436826395135328L;
/*     */   private final BasicChronology iChronology;
/*     */   
/*     */   BasicWeekOfWeekyearDateTimeField(BasicChronology paramBasicChronology, DurationField paramDurationField)
/*     */   {
/*  42 */     super(DateTimeFieldType.weekOfWeekyear(), paramDurationField);
/*  43 */     this.iChronology = paramBasicChronology;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int get(long paramLong)
/*     */   {
/*  54 */     return this.iChronology.getWeekOfWeekyear(paramLong);
/*     */   }
/*     */   
/*     */   public DurationField getRangeDurationField() {
/*  58 */     return this.iChronology.weekyears();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public long roundFloor(long paramLong)
/*     */   {
/*  65 */     return super.roundFloor(paramLong + 259200000L) - 259200000L;
/*     */   }
/*     */   
/*     */   public long roundCeiling(long paramLong)
/*     */   {
/*  70 */     return super.roundCeiling(paramLong + 259200000L) - 259200000L;
/*     */   }
/*     */   
/*     */   public long remainder(long paramLong)
/*     */   {
/*  75 */     return super.remainder(paramLong + 259200000L);
/*     */   }
/*     */   
/*     */   public int getMinimumValue() {
/*  79 */     return 1;
/*     */   }
/*     */   
/*     */   public int getMaximumValue() {
/*  83 */     return 53;
/*     */   }
/*     */   
/*     */   public int getMaximumValue(long paramLong) {
/*  87 */     int i = this.iChronology.getWeekyear(paramLong);
/*  88 */     return this.iChronology.getWeeksInYear(i);
/*     */   }
/*     */   
/*     */   public int getMaximumValue(ReadablePartial paramReadablePartial) {
/*  92 */     if (paramReadablePartial.isSupported(DateTimeFieldType.weekyear())) {
/*  93 */       int i = paramReadablePartial.get(DateTimeFieldType.weekyear());
/*  94 */       return this.iChronology.getWeeksInYear(i);
/*     */     }
/*  96 */     return 53;
/*     */   }
/*     */   
/*     */   public int getMaximumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt) {
/* 100 */     int i = paramReadablePartial.size();
/* 101 */     for (int j = 0; j < i; j++) {
/* 102 */       if (paramReadablePartial.getFieldType(j) == DateTimeFieldType.weekyear()) {
/* 103 */         int k = paramArrayOfInt[j];
/* 104 */         return this.iChronology.getWeeksInYear(k);
/*     */       }
/*     */     }
/* 107 */     return 53;
/*     */   }
/*     */   
/*     */   protected int getMaximumValueForSet(long paramLong, int paramInt) {
/* 111 */     return paramInt > 52 ? getMaximumValue(paramLong) : 52;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 118 */     return this.iChronology.weekOfWeekyear();
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\BasicWeekOfWeekyearDateTimeField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */