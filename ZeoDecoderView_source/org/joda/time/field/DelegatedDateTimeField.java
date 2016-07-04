/*     */ package org.joda.time.field;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Locale;
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
/*     */ 
/*     */ 
/*     */ public class DelegatedDateTimeField
/*     */   extends DateTimeField
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4730164440214502503L;
/*     */   private final DateTimeField iField;
/*     */   private final DateTimeFieldType iType;
/*     */   
/*     */   public DelegatedDateTimeField(DateTimeField paramDateTimeField)
/*     */   {
/*  52 */     this(paramDateTimeField, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DelegatedDateTimeField(DateTimeField paramDateTimeField, DateTimeFieldType paramDateTimeFieldType)
/*     */   {
/*  63 */     if (paramDateTimeField == null) {
/*  64 */       throw new IllegalArgumentException("The field must not be null");
/*     */     }
/*  66 */     this.iField = paramDateTimeField;
/*  67 */     this.iType = (paramDateTimeFieldType == null ? paramDateTimeField.getType() : paramDateTimeFieldType);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final DateTimeField getWrappedField()
/*     */   {
/*  76 */     return this.iField;
/*     */   }
/*     */   
/*     */   public DateTimeFieldType getType() {
/*  80 */     return this.iType;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  84 */     return this.iType.getName();
/*     */   }
/*     */   
/*     */   public boolean isSupported() {
/*  88 */     return this.iField.isSupported();
/*     */   }
/*     */   
/*     */   public boolean isLenient() {
/*  92 */     return this.iField.isLenient();
/*     */   }
/*     */   
/*     */   public int get(long paramLong) {
/*  96 */     return this.iField.get(paramLong);
/*     */   }
/*     */   
/*     */   public String getAsText(long paramLong, Locale paramLocale) {
/* 100 */     return this.iField.getAsText(paramLong, paramLocale);
/*     */   }
/*     */   
/*     */   public String getAsText(long paramLong) {
/* 104 */     return this.iField.getAsText(paramLong);
/*     */   }
/*     */   
/*     */   public String getAsText(ReadablePartial paramReadablePartial, int paramInt, Locale paramLocale) {
/* 108 */     return this.iField.getAsText(paramReadablePartial, paramInt, paramLocale);
/*     */   }
/*     */   
/*     */   public String getAsText(ReadablePartial paramReadablePartial, Locale paramLocale) {
/* 112 */     return this.iField.getAsText(paramReadablePartial, paramLocale);
/*     */   }
/*     */   
/*     */   public String getAsText(int paramInt, Locale paramLocale) {
/* 116 */     return this.iField.getAsText(paramInt, paramLocale);
/*     */   }
/*     */   
/*     */   public String getAsShortText(long paramLong, Locale paramLocale) {
/* 120 */     return this.iField.getAsShortText(paramLong, paramLocale);
/*     */   }
/*     */   
/*     */   public String getAsShortText(long paramLong) {
/* 124 */     return this.iField.getAsShortText(paramLong);
/*     */   }
/*     */   
/*     */   public String getAsShortText(ReadablePartial paramReadablePartial, int paramInt, Locale paramLocale) {
/* 128 */     return this.iField.getAsShortText(paramReadablePartial, paramInt, paramLocale);
/*     */   }
/*     */   
/*     */   public String getAsShortText(ReadablePartial paramReadablePartial, Locale paramLocale) {
/* 132 */     return this.iField.getAsShortText(paramReadablePartial, paramLocale);
/*     */   }
/*     */   
/*     */   public String getAsShortText(int paramInt, Locale paramLocale) {
/* 136 */     return this.iField.getAsShortText(paramInt, paramLocale);
/*     */   }
/*     */   
/*     */   public long add(long paramLong, int paramInt) {
/* 140 */     return this.iField.add(paramLong, paramInt);
/*     */   }
/*     */   
/*     */   public long add(long paramLong1, long paramLong2) {
/* 144 */     return this.iField.add(paramLong1, paramLong2);
/*     */   }
/*     */   
/*     */   public int[] add(ReadablePartial paramReadablePartial, int paramInt1, int[] paramArrayOfInt, int paramInt2) {
/* 148 */     return this.iField.add(paramReadablePartial, paramInt1, paramArrayOfInt, paramInt2);
/*     */   }
/*     */   
/*     */   public int[] addWrapPartial(ReadablePartial paramReadablePartial, int paramInt1, int[] paramArrayOfInt, int paramInt2) {
/* 152 */     return this.iField.addWrapPartial(paramReadablePartial, paramInt1, paramArrayOfInt, paramInt2);
/*     */   }
/*     */   
/*     */   public long addWrapField(long paramLong, int paramInt) {
/* 156 */     return this.iField.addWrapField(paramLong, paramInt);
/*     */   }
/*     */   
/*     */   public int[] addWrapField(ReadablePartial paramReadablePartial, int paramInt1, int[] paramArrayOfInt, int paramInt2) {
/* 160 */     return this.iField.addWrapField(paramReadablePartial, paramInt1, paramArrayOfInt, paramInt2);
/*     */   }
/*     */   
/*     */   public int getDifference(long paramLong1, long paramLong2) {
/* 164 */     return this.iField.getDifference(paramLong1, paramLong2);
/*     */   }
/*     */   
/*     */   public long getDifferenceAsLong(long paramLong1, long paramLong2) {
/* 168 */     return this.iField.getDifferenceAsLong(paramLong1, paramLong2);
/*     */   }
/*     */   
/*     */   public long set(long paramLong, int paramInt) {
/* 172 */     return this.iField.set(paramLong, paramInt);
/*     */   }
/*     */   
/*     */   public long set(long paramLong, String paramString, Locale paramLocale) {
/* 176 */     return this.iField.set(paramLong, paramString, paramLocale);
/*     */   }
/*     */   
/*     */   public long set(long paramLong, String paramString) {
/* 180 */     return this.iField.set(paramLong, paramString);
/*     */   }
/*     */   
/*     */   public int[] set(ReadablePartial paramReadablePartial, int paramInt1, int[] paramArrayOfInt, int paramInt2) {
/* 184 */     return this.iField.set(paramReadablePartial, paramInt1, paramArrayOfInt, paramInt2);
/*     */   }
/*     */   
/*     */   public int[] set(ReadablePartial paramReadablePartial, int paramInt, int[] paramArrayOfInt, String paramString, Locale paramLocale) {
/* 188 */     return this.iField.set(paramReadablePartial, paramInt, paramArrayOfInt, paramString, paramLocale);
/*     */   }
/*     */   
/*     */   public DurationField getDurationField() {
/* 192 */     return this.iField.getDurationField();
/*     */   }
/*     */   
/*     */   public DurationField getRangeDurationField() {
/* 196 */     return this.iField.getRangeDurationField();
/*     */   }
/*     */   
/*     */   public boolean isLeap(long paramLong) {
/* 200 */     return this.iField.isLeap(paramLong);
/*     */   }
/*     */   
/*     */   public int getLeapAmount(long paramLong) {
/* 204 */     return this.iField.getLeapAmount(paramLong);
/*     */   }
/*     */   
/*     */   public DurationField getLeapDurationField() {
/* 208 */     return this.iField.getLeapDurationField();
/*     */   }
/*     */   
/*     */   public int getMinimumValue() {
/* 212 */     return this.iField.getMinimumValue();
/*     */   }
/*     */   
/*     */   public int getMinimumValue(long paramLong) {
/* 216 */     return this.iField.getMinimumValue(paramLong);
/*     */   }
/*     */   
/*     */   public int getMinimumValue(ReadablePartial paramReadablePartial) {
/* 220 */     return this.iField.getMinimumValue(paramReadablePartial);
/*     */   }
/*     */   
/*     */   public int getMinimumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt) {
/* 224 */     return this.iField.getMinimumValue(paramReadablePartial, paramArrayOfInt);
/*     */   }
/*     */   
/*     */   public int getMaximumValue() {
/* 228 */     return this.iField.getMaximumValue();
/*     */   }
/*     */   
/*     */   public int getMaximumValue(long paramLong) {
/* 232 */     return this.iField.getMaximumValue(paramLong);
/*     */   }
/*     */   
/*     */   public int getMaximumValue(ReadablePartial paramReadablePartial) {
/* 236 */     return this.iField.getMaximumValue(paramReadablePartial);
/*     */   }
/*     */   
/*     */   public int getMaximumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt) {
/* 240 */     return this.iField.getMaximumValue(paramReadablePartial, paramArrayOfInt);
/*     */   }
/*     */   
/*     */   public int getMaximumTextLength(Locale paramLocale) {
/* 244 */     return this.iField.getMaximumTextLength(paramLocale);
/*     */   }
/*     */   
/*     */   public int getMaximumShortTextLength(Locale paramLocale) {
/* 248 */     return this.iField.getMaximumShortTextLength(paramLocale);
/*     */   }
/*     */   
/*     */   public long roundFloor(long paramLong) {
/* 252 */     return this.iField.roundFloor(paramLong);
/*     */   }
/*     */   
/*     */   public long roundCeiling(long paramLong) {
/* 256 */     return this.iField.roundCeiling(paramLong);
/*     */   }
/*     */   
/*     */   public long roundHalfFloor(long paramLong) {
/* 260 */     return this.iField.roundHalfFloor(paramLong);
/*     */   }
/*     */   
/*     */   public long roundHalfCeiling(long paramLong) {
/* 264 */     return this.iField.roundHalfCeiling(paramLong);
/*     */   }
/*     */   
/*     */   public long roundHalfEven(long paramLong) {
/* 268 */     return this.iField.roundHalfEven(paramLong);
/*     */   }
/*     */   
/*     */   public long remainder(long paramLong) {
/* 272 */     return this.iField.remainder(paramLong);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 276 */     return "DateTimeField[" + getName() + ']';
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\field\DelegatedDateTimeField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */