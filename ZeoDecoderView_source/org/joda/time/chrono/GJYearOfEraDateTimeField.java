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
/*     */ final class GJYearOfEraDateTimeField
/*     */   extends DecoratedDateTimeField
/*     */ {
/*     */   private static final long serialVersionUID = -5961050944769862059L;
/*     */   private final BasicChronology iChronology;
/*     */   
/*     */   GJYearOfEraDateTimeField(DateTimeField paramDateTimeField, BasicChronology paramBasicChronology)
/*     */   {
/*  40 */     super(paramDateTimeField, DateTimeFieldType.yearOfEra());
/*  41 */     this.iChronology = paramBasicChronology;
/*     */   }
/*     */   
/*     */   public int get(long paramLong) {
/*  45 */     int i = getWrappedField().get(paramLong);
/*  46 */     if (i <= 0) {
/*  47 */       i = 1 - i;
/*     */     }
/*  49 */     return i;
/*     */   }
/*     */   
/*     */   public long add(long paramLong, int paramInt) {
/*  53 */     return getWrappedField().add(paramLong, paramInt);
/*     */   }
/*     */   
/*     */   public long add(long paramLong1, long paramLong2) {
/*  57 */     return getWrappedField().add(paramLong1, paramLong2);
/*     */   }
/*     */   
/*     */   public long addWrapField(long paramLong, int paramInt) {
/*  61 */     return getWrappedField().addWrapField(paramLong, paramInt);
/*     */   }
/*     */   
/*     */   public int[] addWrapField(ReadablePartial paramReadablePartial, int paramInt1, int[] paramArrayOfInt, int paramInt2) {
/*  65 */     return getWrappedField().addWrapField(paramReadablePartial, paramInt1, paramArrayOfInt, paramInt2);
/*     */   }
/*     */   
/*     */   public int getDifference(long paramLong1, long paramLong2) {
/*  69 */     return getWrappedField().getDifference(paramLong1, paramLong2);
/*     */   }
/*     */   
/*     */   public long getDifferenceAsLong(long paramLong1, long paramLong2) {
/*  73 */     return getWrappedField().getDifferenceAsLong(paramLong1, paramLong2);
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
/*  85 */     FieldUtils.verifyValueBounds(this, paramInt, 1, getMaximumValue());
/*  86 */     if (this.iChronology.getYear(paramLong) <= 0) {
/*  87 */       paramInt = 1 - paramInt;
/*     */     }
/*  89 */     return super.set(paramLong, paramInt);
/*     */   }
/*     */   
/*     */   public int getMinimumValue() {
/*  93 */     return 1;
/*     */   }
/*     */   
/*     */   public int getMaximumValue() {
/*  97 */     return getWrappedField().getMaximumValue();
/*     */   }
/*     */   
/*     */   public long roundFloor(long paramLong) {
/* 101 */     return getWrappedField().roundFloor(paramLong);
/*     */   }
/*     */   
/*     */   public long roundCeiling(long paramLong) {
/* 105 */     return getWrappedField().roundCeiling(paramLong);
/*     */   }
/*     */   
/*     */   public long remainder(long paramLong) {
/* 109 */     return getWrappedField().remainder(paramLong);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 116 */     return this.iChronology.yearOfEra();
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\GJYearOfEraDateTimeField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */