/*     */ package org.joda.time.field;
/*     */ 
/*     */ import org.joda.time.DateTimeField;
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
/*     */ public class RemainderDateTimeField
/*     */   extends DecoratedDateTimeField
/*     */ {
/*     */   private static final long serialVersionUID = 5708241235177666790L;
/*     */   final int iDivisor;
/*     */   final DurationField iRangeField;
/*     */   
/*     */   public RemainderDateTimeField(DateTimeField paramDateTimeField, DateTimeFieldType paramDateTimeFieldType, int paramInt)
/*     */   {
/*  52 */     super(paramDateTimeField, paramDateTimeFieldType);
/*     */     
/*  54 */     if (paramInt < 2) {
/*  55 */       throw new IllegalArgumentException("The divisor must be at least 2");
/*     */     }
/*     */     
/*  58 */     DurationField localDurationField = paramDateTimeField.getDurationField();
/*  59 */     if (localDurationField == null) {
/*  60 */       this.iRangeField = null;
/*     */     } else {
/*  62 */       this.iRangeField = new ScaledDurationField(localDurationField, paramDateTimeFieldType.getRangeDurationType(), paramInt);
/*     */     }
/*     */     
/*     */ 
/*  66 */     this.iDivisor = paramInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RemainderDateTimeField(DividedDateTimeField paramDividedDateTimeField)
/*     */   {
/*  76 */     this(paramDividedDateTimeField, paramDividedDateTimeField.getType());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RemainderDateTimeField(DividedDateTimeField paramDividedDateTimeField, DateTimeFieldType paramDateTimeFieldType)
/*     */   {
/*  87 */     super(paramDividedDateTimeField.getWrappedField(), paramDateTimeFieldType);
/*  88 */     this.iDivisor = paramDividedDateTimeField.iDivisor;
/*  89 */     this.iRangeField = paramDividedDateTimeField.iDurationField;
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
/* 100 */     int i = getWrappedField().get(paramLong);
/* 101 */     if (i >= 0) {
/* 102 */       return i % this.iDivisor;
/*     */     }
/* 104 */     return this.iDivisor - 1 + (i + 1) % this.iDivisor;
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
/*     */   public long addWrapField(long paramLong, int paramInt)
/*     */   {
/* 118 */     return set(paramLong, FieldUtils.getWrappedValue(get(paramLong), paramInt, 0, this.iDivisor - 1));
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
/* 130 */     FieldUtils.verifyValueBounds(this, paramInt, 0, this.iDivisor - 1);
/* 131 */     int i = getDivided(getWrappedField().get(paramLong));
/* 132 */     return getWrappedField().set(paramLong, i * this.iDivisor + paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DurationField getRangeDurationField()
/*     */   {
/* 139 */     return this.iRangeField;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMinimumValue()
/*     */   {
/* 148 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMaximumValue()
/*     */   {
/* 158 */     return this.iDivisor - 1;
/*     */   }
/*     */   
/*     */   public long roundFloor(long paramLong) {
/* 162 */     return getWrappedField().roundFloor(paramLong);
/*     */   }
/*     */   
/*     */   public long roundCeiling(long paramLong) {
/* 166 */     return getWrappedField().roundCeiling(paramLong);
/*     */   }
/*     */   
/*     */   public long roundHalfFloor(long paramLong) {
/* 170 */     return getWrappedField().roundHalfFloor(paramLong);
/*     */   }
/*     */   
/*     */   public long roundHalfCeiling(long paramLong) {
/* 174 */     return getWrappedField().roundHalfCeiling(paramLong);
/*     */   }
/*     */   
/*     */   public long roundHalfEven(long paramLong) {
/* 178 */     return getWrappedField().roundHalfEven(paramLong);
/*     */   }
/*     */   
/*     */   public long remainder(long paramLong) {
/* 182 */     return getWrappedField().remainder(paramLong);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDivisor()
/*     */   {
/* 191 */     return this.iDivisor;
/*     */   }
/*     */   
/*     */   private int getDivided(int paramInt) {
/* 195 */     if (paramInt >= 0) {
/* 196 */       return paramInt / this.iDivisor;
/*     */     }
/* 198 */     return (paramInt + 1) / this.iDivisor - 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\field\RemainderDateTimeField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */