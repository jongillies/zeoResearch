/*     */ package org.joda.time.chrono;
/*     */ 
/*     */ import java.util.Locale;
/*     */ import org.joda.time.DateTimeFieldType;
/*     */ import org.joda.time.DurationField;
/*     */ import org.joda.time.DurationFieldType;
/*     */ import org.joda.time.field.BaseDateTimeField;
/*     */ import org.joda.time.field.FieldUtils;
/*     */ import org.joda.time.field.UnsupportedDurationField;
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
/*     */ final class GJEraDateTimeField
/*     */   extends BaseDateTimeField
/*     */ {
/*     */   private static final long serialVersionUID = 4240986525305515528L;
/*     */   private final BasicChronology iChronology;
/*     */   
/*     */   GJEraDateTimeField(BasicChronology paramBasicChronology)
/*     */   {
/*  46 */     super(DateTimeFieldType.era());
/*  47 */     this.iChronology = paramBasicChronology;
/*     */   }
/*     */   
/*     */   public boolean isLenient() {
/*  51 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int get(long paramLong)
/*     */   {
/*  60 */     if (this.iChronology.getYear(paramLong) <= 0) {
/*  61 */       return 0;
/*     */     }
/*  63 */     return 1;
/*     */   }
/*     */   
/*     */   public String getAsText(int paramInt, Locale paramLocale)
/*     */   {
/*  68 */     return GJLocaleSymbols.forLocale(paramLocale).eraValueToText(paramInt);
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
/*  80 */     FieldUtils.verifyValueBounds(this, paramInt, 0, 1);
/*     */     
/*  82 */     int i = get(paramLong);
/*  83 */     if (i != paramInt) {
/*  84 */       int j = this.iChronology.getYear(paramLong);
/*  85 */       return this.iChronology.setYear(paramLong, -j);
/*     */     }
/*  87 */     return paramLong;
/*     */   }
/*     */   
/*     */   public long set(long paramLong, String paramString, Locale paramLocale)
/*     */   {
/*  92 */     return set(paramLong, GJLocaleSymbols.forLocale(paramLocale).eraTextToValue(paramString));
/*     */   }
/*     */   
/*     */   public long roundFloor(long paramLong) {
/*  96 */     if (get(paramLong) == 1) {
/*  97 */       return this.iChronology.setYear(0L, 1);
/*     */     }
/*  99 */     return Long.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public long roundCeiling(long paramLong)
/*     */   {
/* 104 */     if (get(paramLong) == 0) {
/* 105 */       return this.iChronology.setYear(0L, 1);
/*     */     }
/* 107 */     return Long.MAX_VALUE;
/*     */   }
/*     */   
/*     */ 
/*     */   public long roundHalfFloor(long paramLong)
/*     */   {
/* 113 */     return roundFloor(paramLong);
/*     */   }
/*     */   
/*     */   public long roundHalfCeiling(long paramLong)
/*     */   {
/* 118 */     return roundFloor(paramLong);
/*     */   }
/*     */   
/*     */   public long roundHalfEven(long paramLong)
/*     */   {
/* 123 */     return roundFloor(paramLong);
/*     */   }
/*     */   
/*     */   public DurationField getDurationField() {
/* 127 */     return UnsupportedDurationField.getInstance(DurationFieldType.eras());
/*     */   }
/*     */   
/*     */   public DurationField getRangeDurationField() {
/* 131 */     return null;
/*     */   }
/*     */   
/*     */   public int getMinimumValue() {
/* 135 */     return 0;
/*     */   }
/*     */   
/*     */   public int getMaximumValue() {
/* 139 */     return 1;
/*     */   }
/*     */   
/*     */   public int getMaximumTextLength(Locale paramLocale) {
/* 143 */     return GJLocaleSymbols.forLocale(paramLocale).getEraMaxTextLength();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 150 */     return this.iChronology.era();
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\GJEraDateTimeField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */