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
/*     */ 
/*     */ 
/*     */ public class DividedDateTimeField
/*     */   extends DecoratedDateTimeField
/*     */ {
/*     */   private static final long serialVersionUID = 8318475124230605365L;
/*     */   final int iDivisor;
/*     */   final DurationField iDurationField;
/*     */   private final int iMin;
/*     */   private final int iMax;
/*     */   
/*     */   public DividedDateTimeField(DateTimeField paramDateTimeField, DateTimeFieldType paramDateTimeFieldType, int paramInt)
/*     */   {
/*  56 */     super(paramDateTimeField, paramDateTimeFieldType);
/*     */     
/*  58 */     if (paramInt < 2) {
/*  59 */       throw new IllegalArgumentException("The divisor must be at least 2");
/*     */     }
/*     */     
/*  62 */     DurationField localDurationField = paramDateTimeField.getDurationField();
/*  63 */     if (localDurationField == null) {
/*  64 */       this.iDurationField = null;
/*     */     } else {
/*  66 */       this.iDurationField = new ScaledDurationField(localDurationField, paramDateTimeFieldType.getDurationType(), paramInt);
/*     */     }
/*     */     
/*     */ 
/*  70 */     this.iDivisor = paramInt;
/*     */     
/*  72 */     int i = paramDateTimeField.getMinimumValue();
/*  73 */     int j = i >= 0 ? i / paramInt : (i + 1) / paramInt - 1;
/*     */     
/*  75 */     int k = paramDateTimeField.getMaximumValue();
/*  76 */     int m = k >= 0 ? k / paramInt : (k + 1) / paramInt - 1;
/*     */     
/*  78 */     this.iMin = j;
/*  79 */     this.iMax = m;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DividedDateTimeField(RemainderDateTimeField paramRemainderDateTimeField, DateTimeFieldType paramDateTimeFieldType)
/*     */   {
/*  90 */     super(paramRemainderDateTimeField.getWrappedField(), paramDateTimeFieldType);
/*  91 */     int i = this.iDivisor = paramRemainderDateTimeField.iDivisor;
/*  92 */     this.iDurationField = paramRemainderDateTimeField.iRangeField;
/*     */     
/*  94 */     DateTimeField localDateTimeField = getWrappedField();
/*  95 */     int j = localDateTimeField.getMinimumValue();
/*  96 */     int k = j >= 0 ? j / i : (j + 1) / i - 1;
/*     */     
/*  98 */     int m = localDateTimeField.getMaximumValue();
/*  99 */     int n = m >= 0 ? m / i : (m + 1) / i - 1;
/*     */     
/* 101 */     this.iMin = k;
/* 102 */     this.iMax = n;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int get(long paramLong)
/*     */   {
/* 112 */     int i = getWrappedField().get(paramLong);
/* 113 */     if (i >= 0) {
/* 114 */       return i / this.iDivisor;
/*     */     }
/* 116 */     return (i + 1) / this.iDivisor - 1;
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
/*     */   public long add(long paramLong, int paramInt)
/*     */   {
/* 129 */     return getWrappedField().add(paramLong, paramInt * this.iDivisor);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long add(long paramLong1, long paramLong2)
/*     */   {
/* 141 */     return getWrappedField().add(paramLong1, paramLong2 * this.iDivisor);
/*     */   }
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
/* 153 */     return set(paramLong, FieldUtils.getWrappedValue(get(paramLong), paramInt, this.iMin, this.iMax));
/*     */   }
/*     */   
/*     */   public int getDifference(long paramLong1, long paramLong2) {
/* 157 */     return getWrappedField().getDifference(paramLong1, paramLong2) / this.iDivisor;
/*     */   }
/*     */   
/*     */   public long getDifferenceAsLong(long paramLong1, long paramLong2) {
/* 161 */     return getWrappedField().getDifferenceAsLong(paramLong1, paramLong2) / this.iDivisor;
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
/* 173 */     FieldUtils.verifyValueBounds(this, paramInt, this.iMin, this.iMax);
/* 174 */     int i = getRemainder(getWrappedField().get(paramLong));
/* 175 */     return getWrappedField().set(paramLong, paramInt * this.iDivisor + i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DurationField getDurationField()
/*     */   {
/* 182 */     return this.iDurationField;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMinimumValue()
/*     */   {
/* 191 */     return this.iMin;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMaximumValue()
/*     */   {
/* 200 */     return this.iMax;
/*     */   }
/*     */   
/*     */   public long roundFloor(long paramLong) {
/* 204 */     DateTimeField localDateTimeField = getWrappedField();
/* 205 */     return localDateTimeField.roundFloor(localDateTimeField.set(paramLong, get(paramLong) * this.iDivisor));
/*     */   }
/*     */   
/*     */   public long remainder(long paramLong) {
/* 209 */     return set(paramLong, get(getWrappedField().remainder(paramLong)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDivisor()
/*     */   {
/* 218 */     return this.iDivisor;
/*     */   }
/*     */   
/*     */   private int getRemainder(int paramInt) {
/* 222 */     if (paramInt >= 0) {
/* 223 */       return paramInt % this.iDivisor;
/*     */     }
/* 225 */     return this.iDivisor - 1 + (paramInt + 1) % this.iDivisor;
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\field\DividedDateTimeField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */