/*     */ package org.joda.time.chrono;
/*     */ 
/*     */ import org.joda.time.DateTimeField;
/*     */ import org.joda.time.DateTimeFieldType;
/*     */ import org.joda.time.ReadablePartial;
/*     */ import org.joda.time.field.DecoratedDateTimeField;
/*     */ import org.joda.time.field.FieldUtils;
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
/*     */ class ISOYearOfEraDateTimeField
/*     */   extends DecoratedDateTimeField
/*     */ {
/*     */   private static final long serialVersionUID = 7037524068969447317L;
/*  40 */   static final DateTimeField INSTANCE = new ISOYearOfEraDateTimeField();
/*     */   
/*     */ 
/*     */ 
/*     */   private ISOYearOfEraDateTimeField()
/*     */   {
/*  46 */     super(GregorianChronology.getInstanceUTC().year(), DateTimeFieldType.yearOfEra());
/*     */   }
/*     */   
/*     */   public int get(long paramLong) {
/*  50 */     int i = getWrappedField().get(paramLong);
/*  51 */     return i < 0 ? -i : i;
/*     */   }
/*     */   
/*     */   public long add(long paramLong, int paramInt) {
/*  55 */     return getWrappedField().add(paramLong, paramInt);
/*     */   }
/*     */   
/*     */   public long add(long paramLong1, long paramLong2) {
/*  59 */     return getWrappedField().add(paramLong1, paramLong2);
/*     */   }
/*     */   
/*     */   public long addWrapField(long paramLong, int paramInt) {
/*  63 */     return getWrappedField().addWrapField(paramLong, paramInt);
/*     */   }
/*     */   
/*     */   public int[] addWrapField(ReadablePartial paramReadablePartial, int paramInt1, int[] paramArrayOfInt, int paramInt2) {
/*  67 */     return getWrappedField().addWrapField(paramReadablePartial, paramInt1, paramArrayOfInt, paramInt2);
/*     */   }
/*     */   
/*     */   public int getDifference(long paramLong1, long paramLong2) {
/*  71 */     return getWrappedField().getDifference(paramLong1, paramLong2);
/*     */   }
/*     */   
/*     */   public long getDifferenceAsLong(long paramLong1, long paramLong2) {
/*  75 */     return getWrappedField().getDifferenceAsLong(paramLong1, paramLong2);
/*     */   }
/*     */   
/*     */   public long set(long paramLong, int paramInt) {
/*  79 */     FieldUtils.verifyValueBounds(this, paramInt, 0, getMaximumValue());
/*  80 */     if (getWrappedField().get(paramLong) < 0) {
/*  81 */       paramInt = -paramInt;
/*     */     }
/*  83 */     return super.set(paramLong, paramInt);
/*     */   }
/*     */   
/*     */   public int getMinimumValue() {
/*  87 */     return 0;
/*     */   }
/*     */   
/*     */   public int getMaximumValue() {
/*  91 */     return getWrappedField().getMaximumValue();
/*     */   }
/*     */   
/*     */   public long roundFloor(long paramLong) {
/*  95 */     return getWrappedField().roundFloor(paramLong);
/*     */   }
/*     */   
/*     */   public long roundCeiling(long paramLong) {
/*  99 */     return getWrappedField().roundCeiling(paramLong);
/*     */   }
/*     */   
/*     */   public long remainder(long paramLong) {
/* 103 */     return getWrappedField().remainder(paramLong);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 110 */     return INSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\ISOYearOfEraDateTimeField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */