/*     */ package org.joda.time.chrono;
/*     */ 
/*     */ import org.joda.time.DateTimeFieldType;
/*     */ import org.joda.time.DurationField;
/*     */ import org.joda.time.field.FieldUtils;
/*     */ import org.joda.time.field.ImpreciseDateTimeField;
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
/*     */ class BasicYearDateTimeField
/*     */   extends ImpreciseDateTimeField
/*     */ {
/*     */   private static final long serialVersionUID = -98628754872287L;
/*     */   protected final BasicChronology iChronology;
/*     */   
/*     */   BasicYearDateTimeField(BasicChronology paramBasicChronology)
/*     */   {
/*  45 */     super(DateTimeFieldType.year(), paramBasicChronology.getAverageMillisPerYear());
/*  46 */     this.iChronology = paramBasicChronology;
/*     */   }
/*     */   
/*     */   public boolean isLenient() {
/*  50 */     return false;
/*     */   }
/*     */   
/*     */   public int get(long paramLong) {
/*  54 */     return this.iChronology.getYear(paramLong);
/*     */   }
/*     */   
/*     */   public long add(long paramLong, int paramInt) {
/*  58 */     if (paramInt == 0) {
/*  59 */       return paramLong;
/*     */     }
/*  61 */     int i = get(paramLong);
/*  62 */     int j = FieldUtils.safeAdd(i, paramInt);
/*  63 */     return set(paramLong, j);
/*     */   }
/*     */   
/*     */   public long add(long paramLong1, long paramLong2) {
/*  67 */     return add(paramLong1, FieldUtils.safeToInt(paramLong2));
/*     */   }
/*     */   
/*     */   public long addWrapField(long paramLong, int paramInt) {
/*  71 */     if (paramInt == 0) {
/*  72 */       return paramLong;
/*     */     }
/*     */     
/*  75 */     int i = this.iChronology.getYear(paramLong);
/*  76 */     int j = FieldUtils.getWrappedValue(i, paramInt, this.iChronology.getMinYear(), this.iChronology.getMaxYear());
/*     */     
/*  78 */     return set(paramLong, j);
/*     */   }
/*     */   
/*     */   public long set(long paramLong, int paramInt) {
/*  82 */     FieldUtils.verifyValueBounds(this, paramInt, this.iChronology.getMinYear(), this.iChronology.getMaxYear());
/*     */     
/*  84 */     return this.iChronology.setYear(paramLong, paramInt);
/*     */   }
/*     */   
/*     */   public long getDifferenceAsLong(long paramLong1, long paramLong2) {
/*  88 */     if (paramLong1 < paramLong2) {
/*  89 */       return -this.iChronology.getYearDifference(paramLong2, paramLong1);
/*     */     }
/*  91 */     return this.iChronology.getYearDifference(paramLong1, paramLong2);
/*     */   }
/*     */   
/*     */   public DurationField getRangeDurationField() {
/*  95 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isLeap(long paramLong) {
/*  99 */     return this.iChronology.isLeapYear(get(paramLong));
/*     */   }
/*     */   
/*     */   public int getLeapAmount(long paramLong) {
/* 103 */     if (this.iChronology.isLeapYear(get(paramLong))) {
/* 104 */       return 1;
/*     */     }
/* 106 */     return 0;
/*     */   }
/*     */   
/*     */   public DurationField getLeapDurationField()
/*     */   {
/* 111 */     return this.iChronology.days();
/*     */   }
/*     */   
/*     */   public int getMinimumValue() {
/* 115 */     return this.iChronology.getMinYear();
/*     */   }
/*     */   
/*     */   public int getMaximumValue() {
/* 119 */     return this.iChronology.getMaxYear();
/*     */   }
/*     */   
/*     */   public long roundFloor(long paramLong) {
/* 123 */     return this.iChronology.getYearMillis(get(paramLong));
/*     */   }
/*     */   
/*     */   public long roundCeiling(long paramLong) {
/* 127 */     int i = get(paramLong);
/* 128 */     long l = this.iChronology.getYearMillis(i);
/* 129 */     if (paramLong != l)
/*     */     {
/* 131 */       paramLong = this.iChronology.getYearMillis(i + 1);
/*     */     }
/* 133 */     return paramLong;
/*     */   }
/*     */   
/*     */   public long remainder(long paramLong) {
/* 137 */     return paramLong - roundFloor(paramLong);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 144 */     return this.iChronology.year();
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\BasicYearDateTimeField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */