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
/*     */ final class BasicDayOfMonthDateTimeField
/*     */   extends PreciseDurationDateTimeField
/*     */ {
/*     */   private static final long serialVersionUID = -4677223814028011723L;
/*     */   private final BasicChronology iChronology;
/*     */   
/*     */   BasicDayOfMonthDateTimeField(BasicChronology paramBasicChronology, DurationField paramDurationField)
/*     */   {
/*  41 */     super(DateTimeFieldType.dayOfMonth(), paramDurationField);
/*  42 */     this.iChronology = paramBasicChronology;
/*     */   }
/*     */   
/*     */   public int get(long paramLong)
/*     */   {
/*  47 */     return this.iChronology.getDayOfMonth(paramLong);
/*     */   }
/*     */   
/*     */   public DurationField getRangeDurationField() {
/*  51 */     return this.iChronology.months();
/*     */   }
/*     */   
/*     */   public int getMinimumValue() {
/*  55 */     return 1;
/*     */   }
/*     */   
/*     */   public int getMaximumValue() {
/*  59 */     return this.iChronology.getDaysInMonthMax();
/*     */   }
/*     */   
/*     */   public int getMaximumValue(long paramLong) {
/*  63 */     return this.iChronology.getDaysInMonthMax(paramLong);
/*     */   }
/*     */   
/*     */   public int getMaximumValue(ReadablePartial paramReadablePartial) {
/*  67 */     if (paramReadablePartial.isSupported(DateTimeFieldType.monthOfYear())) {
/*  68 */       int i = paramReadablePartial.get(DateTimeFieldType.monthOfYear());
/*  69 */       if (paramReadablePartial.isSupported(DateTimeFieldType.year())) {
/*  70 */         int j = paramReadablePartial.get(DateTimeFieldType.year());
/*  71 */         return this.iChronology.getDaysInYearMonth(j, i);
/*     */       }
/*  73 */       return this.iChronology.getDaysInMonthMax(i);
/*     */     }
/*  75 */     return getMaximumValue();
/*     */   }
/*     */   
/*     */   public int getMaximumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt) {
/*  79 */     int i = paramReadablePartial.size();
/*  80 */     for (int j = 0; j < i; j++) {
/*  81 */       if (paramReadablePartial.getFieldType(j) == DateTimeFieldType.monthOfYear()) {
/*  82 */         int k = paramArrayOfInt[j];
/*  83 */         for (int m = 0; m < i; m++) {
/*  84 */           if (paramReadablePartial.getFieldType(m) == DateTimeFieldType.year()) {
/*  85 */             int n = paramArrayOfInt[m];
/*  86 */             return this.iChronology.getDaysInYearMonth(n, k);
/*     */           }
/*     */         }
/*  89 */         return this.iChronology.getDaysInMonthMax(k);
/*     */       }
/*     */     }
/*  92 */     return getMaximumValue();
/*     */   }
/*     */   
/*     */   protected int getMaximumValueForSet(long paramLong, int paramInt) {
/*  96 */     return this.iChronology.getDaysInMonthMaxForSet(paramLong, paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 103 */     return this.iChronology.dayOfMonth();
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\BasicDayOfMonthDateTimeField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */