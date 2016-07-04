/*     */ package org.joda.time.field;
/*     */ 
/*     */ import org.joda.time.DateTimeField;
/*     */ import org.joda.time.DateTimeFieldType;
/*     */ import org.joda.time.DurationField;
/*     */ import org.joda.time.ReadablePartial;
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
/*     */ public final class ZeroIsMaxDateTimeField
/*     */   extends DecoratedDateTimeField
/*     */ {
/*     */   private static final long serialVersionUID = 961749798233026866L;
/*     */   
/*     */   public ZeroIsMaxDateTimeField(DateTimeField paramDateTimeField, DateTimeFieldType paramDateTimeFieldType)
/*     */   {
/*  45 */     super(paramDateTimeField, paramDateTimeFieldType);
/*  46 */     if (paramDateTimeField.getMinimumValue() != 0) {
/*  47 */       throw new IllegalArgumentException("Wrapped field's minumum value must be zero");
/*     */     }
/*     */   }
/*     */   
/*     */   public int get(long paramLong) {
/*  52 */     int i = getWrappedField().get(paramLong);
/*  53 */     if (i == 0) {
/*  54 */       i = getMaximumValue();
/*     */     }
/*  56 */     return i;
/*     */   }
/*     */   
/*     */   public long add(long paramLong, int paramInt) {
/*  60 */     return getWrappedField().add(paramLong, paramInt);
/*     */   }
/*     */   
/*     */   public long add(long paramLong1, long paramLong2) {
/*  64 */     return getWrappedField().add(paramLong1, paramLong2);
/*     */   }
/*     */   
/*     */   public long addWrapField(long paramLong, int paramInt) {
/*  68 */     return getWrappedField().addWrapField(paramLong, paramInt);
/*     */   }
/*     */   
/*     */   public int[] addWrapField(ReadablePartial paramReadablePartial, int paramInt1, int[] paramArrayOfInt, int paramInt2) {
/*  72 */     return getWrappedField().addWrapField(paramReadablePartial, paramInt1, paramArrayOfInt, paramInt2);
/*     */   }
/*     */   
/*     */   public int getDifference(long paramLong1, long paramLong2) {
/*  76 */     return getWrappedField().getDifference(paramLong1, paramLong2);
/*     */   }
/*     */   
/*     */   public long getDifferenceAsLong(long paramLong1, long paramLong2) {
/*  80 */     return getWrappedField().getDifferenceAsLong(paramLong1, paramLong2);
/*     */   }
/*     */   
/*     */   public long set(long paramLong, int paramInt) {
/*  84 */     int i = getMaximumValue();
/*  85 */     FieldUtils.verifyValueBounds(this, paramInt, 1, i);
/*  86 */     if (paramInt == i) {
/*  87 */       paramInt = 0;
/*     */     }
/*  89 */     return getWrappedField().set(paramLong, paramInt);
/*     */   }
/*     */   
/*     */   public boolean isLeap(long paramLong) {
/*  93 */     return getWrappedField().isLeap(paramLong);
/*     */   }
/*     */   
/*     */   public int getLeapAmount(long paramLong) {
/*  97 */     return getWrappedField().getLeapAmount(paramLong);
/*     */   }
/*     */   
/*     */   public DurationField getLeapDurationField() {
/* 101 */     return getWrappedField().getLeapDurationField();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMinimumValue()
/*     */   {
/* 110 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMinimumValue(long paramLong)
/*     */   {
/* 119 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMinimumValue(ReadablePartial paramReadablePartial)
/*     */   {
/* 128 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMinimumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt)
/*     */   {
/* 137 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMaximumValue()
/*     */   {
/* 147 */     return getWrappedField().getMaximumValue() + 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMaximumValue(long paramLong)
/*     */   {
/* 157 */     return getWrappedField().getMaximumValue(paramLong) + 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMaximumValue(ReadablePartial paramReadablePartial)
/*     */   {
/* 167 */     return getWrappedField().getMaximumValue(paramReadablePartial) + 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMaximumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt)
/*     */   {
/* 177 */     return getWrappedField().getMaximumValue(paramReadablePartial, paramArrayOfInt) + 1;
/*     */   }
/*     */   
/*     */   public long roundFloor(long paramLong) {
/* 181 */     return getWrappedField().roundFloor(paramLong);
/*     */   }
/*     */   
/*     */   public long roundCeiling(long paramLong) {
/* 185 */     return getWrappedField().roundCeiling(paramLong);
/*     */   }
/*     */   
/*     */   public long roundHalfFloor(long paramLong) {
/* 189 */     return getWrappedField().roundHalfFloor(paramLong);
/*     */   }
/*     */   
/*     */   public long roundHalfCeiling(long paramLong) {
/* 193 */     return getWrappedField().roundHalfCeiling(paramLong);
/*     */   }
/*     */   
/*     */   public long roundHalfEven(long paramLong) {
/* 197 */     return getWrappedField().roundHalfEven(paramLong);
/*     */   }
/*     */   
/*     */   public long remainder(long paramLong) {
/* 201 */     return getWrappedField().remainder(paramLong);
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\field\ZeroIsMaxDateTimeField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */