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
/*     */ public class OffsetDateTimeField
/*     */   extends DecoratedDateTimeField
/*     */ {
/*     */   private static final long serialVersionUID = 3145790132623583142L;
/*     */   private final int iOffset;
/*     */   private final int iMin;
/*     */   private final int iMax;
/*     */   
/*     */   public OffsetDateTimeField(DateTimeField paramDateTimeField, int paramInt)
/*     */   {
/*  46 */     this(paramDateTimeField, paramDateTimeField == null ? null : paramDateTimeField.getType(), paramInt, Integer.MIN_VALUE, Integer.MAX_VALUE);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public OffsetDateTimeField(DateTimeField paramDateTimeField, DateTimeFieldType paramDateTimeFieldType, int paramInt)
/*     */   {
/*  58 */     this(paramDateTimeField, paramDateTimeFieldType, paramInt, Integer.MIN_VALUE, Integer.MAX_VALUE);
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
/*     */   public OffsetDateTimeField(DateTimeField paramDateTimeField, DateTimeFieldType paramDateTimeFieldType, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  73 */     super(paramDateTimeField, paramDateTimeFieldType);
/*     */     
/*  75 */     if (paramInt1 == 0) {
/*  76 */       throw new IllegalArgumentException("The offset cannot be zero");
/*     */     }
/*     */     
/*  79 */     this.iOffset = paramInt1;
/*     */     
/*  81 */     if (paramInt2 < paramDateTimeField.getMinimumValue() + paramInt1) {
/*  82 */       this.iMin = (paramDateTimeField.getMinimumValue() + paramInt1);
/*     */     } else {
/*  84 */       this.iMin = paramInt2;
/*     */     }
/*  86 */     if (paramInt3 > paramDateTimeField.getMaximumValue() + paramInt1) {
/*  87 */       this.iMax = (paramDateTimeField.getMaximumValue() + paramInt1);
/*     */     } else {
/*  89 */       this.iMax = paramInt3;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int get(long paramLong)
/*     */   {
/* 100 */     return super.get(paramLong) + this.iOffset;
/*     */   }
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
/* 112 */     paramLong = super.add(paramLong, paramInt);
/* 113 */     FieldUtils.verifyValueBounds(this, get(paramLong), this.iMin, this.iMax);
/* 114 */     return paramLong;
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
/* 126 */     paramLong1 = super.add(paramLong1, paramLong2);
/* 127 */     FieldUtils.verifyValueBounds(this, get(paramLong1), this.iMin, this.iMax);
/* 128 */     return paramLong1;
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
/* 140 */     return set(paramLong, FieldUtils.getWrappedValue(get(paramLong), paramInt, this.iMin, this.iMax));
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
/* 152 */     FieldUtils.verifyValueBounds(this, paramInt, this.iMin, this.iMax);
/* 153 */     return super.set(paramLong, paramInt - this.iOffset);
/*     */   }
/*     */   
/*     */   public boolean isLeap(long paramLong) {
/* 157 */     return getWrappedField().isLeap(paramLong);
/*     */   }
/*     */   
/*     */   public int getLeapAmount(long paramLong) {
/* 161 */     return getWrappedField().getLeapAmount(paramLong);
/*     */   }
/*     */   
/*     */   public DurationField getLeapDurationField() {
/* 165 */     return getWrappedField().getLeapDurationField();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMinimumValue()
/*     */   {
/* 174 */     return this.iMin;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMaximumValue()
/*     */   {
/* 183 */     return this.iMax;
/*     */   }
/*     */   
/*     */   public long roundFloor(long paramLong) {
/* 187 */     return getWrappedField().roundFloor(paramLong);
/*     */   }
/*     */   
/*     */   public long roundCeiling(long paramLong) {
/* 191 */     return getWrappedField().roundCeiling(paramLong);
/*     */   }
/*     */   
/*     */   public long roundHalfFloor(long paramLong) {
/* 195 */     return getWrappedField().roundHalfFloor(paramLong);
/*     */   }
/*     */   
/*     */   public long roundHalfCeiling(long paramLong) {
/* 199 */     return getWrappedField().roundHalfCeiling(paramLong);
/*     */   }
/*     */   
/*     */   public long roundHalfEven(long paramLong) {
/* 203 */     return getWrappedField().roundHalfEven(paramLong);
/*     */   }
/*     */   
/*     */   public long remainder(long paramLong) {
/* 207 */     return getWrappedField().remainder(paramLong);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getOffset()
/*     */   {
/* 216 */     return this.iOffset;
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\field\OffsetDateTimeField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */