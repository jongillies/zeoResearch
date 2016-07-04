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
/*     */ final class BasicDayOfYearDateTimeField
/*     */   extends PreciseDurationDateTimeField
/*     */ {
/*     */   private static final long serialVersionUID = -6821236822336841037L;
/*     */   private final BasicChronology iChronology;
/*     */   
/*     */   BasicDayOfYearDateTimeField(BasicChronology paramBasicChronology, DurationField paramDurationField)
/*     */   {
/*  41 */     super(DateTimeFieldType.dayOfYear(), paramDurationField);
/*  42 */     this.iChronology = paramBasicChronology;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int get(long paramLong)
/*     */   {
/*  52 */     return this.iChronology.getDayOfYear(paramLong);
/*     */   }
/*     */   
/*     */   public DurationField getRangeDurationField() {
/*  56 */     return this.iChronology.years();
/*     */   }
/*     */   
/*     */   public int getMinimumValue() {
/*  60 */     return 1;
/*     */   }
/*     */   
/*     */   public int getMaximumValue() {
/*  64 */     return this.iChronology.getDaysInYearMax();
/*     */   }
/*     */   
/*     */   public int getMaximumValue(long paramLong) {
/*  68 */     int i = this.iChronology.getYear(paramLong);
/*  69 */     return this.iChronology.getDaysInYear(i);
/*     */   }
/*     */   
/*     */   public int getMaximumValue(ReadablePartial paramReadablePartial) {
/*  73 */     if (paramReadablePartial.isSupported(DateTimeFieldType.year())) {
/*  74 */       int i = paramReadablePartial.get(DateTimeFieldType.year());
/*  75 */       return this.iChronology.getDaysInYear(i);
/*     */     }
/*  77 */     return this.iChronology.getDaysInYearMax();
/*     */   }
/*     */   
/*     */   public int getMaximumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt) {
/*  81 */     int i = paramReadablePartial.size();
/*  82 */     for (int j = 0; j < i; j++) {
/*  83 */       if (paramReadablePartial.getFieldType(j) == DateTimeFieldType.year()) {
/*  84 */         int k = paramArrayOfInt[j];
/*  85 */         return this.iChronology.getDaysInYear(k);
/*     */       }
/*     */     }
/*  88 */     return this.iChronology.getDaysInYearMax();
/*     */   }
/*     */   
/*     */   protected int getMaximumValueForSet(long paramLong, int paramInt) {
/*  92 */     int i = this.iChronology.getDaysInYearMax() - 1;
/*  93 */     return (paramInt > i) || (paramInt < 1) ? getMaximumValue(paramLong) : i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 100 */     return this.iChronology.dayOfYear();
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\BasicDayOfYearDateTimeField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */