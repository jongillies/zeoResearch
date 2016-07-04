/*     */ package org.joda.time.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Locale;
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTimeField;
/*     */ import org.joda.time.DateTimeUtils;
/*     */ import org.joda.time.ReadablePartial;
/*     */ import org.joda.time.convert.ConverterManager;
/*     */ import org.joda.time.convert.PartialConverter;
/*     */ import org.joda.time.format.DateTimeFormat;
/*     */ import org.joda.time.format.DateTimeFormatter;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BasePartial
/*     */   extends AbstractPartial
/*     */   implements ReadablePartial, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2353678632973660L;
/*     */   private Chronology iChronology;
/*     */   private int[] iValues;
/*     */   
/*     */   protected BasePartial()
/*     */   {
/*  65 */     this(DateTimeUtils.currentTimeMillis(), null);
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
/*     */   protected BasePartial(Chronology paramChronology)
/*     */   {
/*  79 */     this(DateTimeUtils.currentTimeMillis(), paramChronology);
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
/*     */   protected BasePartial(long paramLong)
/*     */   {
/*  93 */     this(paramLong, null);
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
/*     */ 
/*     */   protected BasePartial(long paramLong, Chronology paramChronology)
/*     */   {
/* 109 */     paramChronology = DateTimeUtils.getChronology(paramChronology);
/* 110 */     this.iChronology = paramChronology.withUTC();
/* 111 */     this.iValues = paramChronology.get(this, paramLong);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected BasePartial(Object paramObject, Chronology paramChronology)
/*     */   {
/* 132 */     PartialConverter localPartialConverter = ConverterManager.getInstance().getPartialConverter(paramObject);
/* 133 */     paramChronology = localPartialConverter.getChronology(paramObject, paramChronology);
/* 134 */     paramChronology = DateTimeUtils.getChronology(paramChronology);
/* 135 */     this.iChronology = paramChronology.withUTC();
/* 136 */     this.iValues = localPartialConverter.getPartialValues(this, paramObject, paramChronology);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected BasePartial(Object paramObject, Chronology paramChronology, DateTimeFormatter paramDateTimeFormatter)
/*     */   {
/* 159 */     PartialConverter localPartialConverter = ConverterManager.getInstance().getPartialConverter(paramObject);
/* 160 */     paramChronology = localPartialConverter.getChronology(paramObject, paramChronology);
/* 161 */     paramChronology = DateTimeUtils.getChronology(paramChronology);
/* 162 */     this.iChronology = paramChronology.withUTC();
/* 163 */     this.iValues = localPartialConverter.getPartialValues(this, paramObject, paramChronology, paramDateTimeFormatter);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   protected BasePartial(int[] paramArrayOfInt, Chronology paramChronology)
/*     */   {
/* 181 */     paramChronology = DateTimeUtils.getChronology(paramChronology);
/* 182 */     this.iChronology = paramChronology.withUTC();
/* 183 */     paramChronology.validate(this, paramArrayOfInt);
/* 184 */     this.iValues = paramArrayOfInt;
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
/*     */   protected BasePartial(BasePartial paramBasePartial, int[] paramArrayOfInt)
/*     */   {
/* 197 */     this.iChronology = paramBasePartial.iChronology;
/* 198 */     this.iValues = paramArrayOfInt;
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
/*     */   protected BasePartial(BasePartial paramBasePartial, Chronology paramChronology)
/*     */   {
/* 212 */     this.iChronology = paramChronology.withUTC();
/* 213 */     this.iValues = paramBasePartial.iValues;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getValue(int paramInt)
/*     */   {
/* 225 */     return this.iValues[paramInt];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int[] getValues()
/*     */   {
/* 237 */     return (int[])this.iValues.clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Chronology getChronology()
/*     */   {
/* 249 */     return this.iChronology;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setValue(int paramInt1, int paramInt2)
/*     */   {
/* 261 */     DateTimeField localDateTimeField = getField(paramInt1);
/* 262 */     this.iValues = localDateTimeField.set(this, paramInt1, this.iValues, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setValues(int[] paramArrayOfInt)
/*     */   {
/* 271 */     getChronology().validate(this, paramArrayOfInt);
/* 272 */     this.iValues = paramArrayOfInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString(String paramString)
/*     */   {
/* 283 */     if (paramString == null) {
/* 284 */       return toString();
/*     */     }
/* 286 */     return DateTimeFormat.forPattern(paramString).print(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString(String paramString, Locale paramLocale)
/*     */     throws IllegalArgumentException
/*     */   {
/* 297 */     if (paramString == null) {
/* 298 */       return toString();
/*     */     }
/* 300 */     return DateTimeFormat.forPattern(paramString).withLocale(paramLocale).print(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\base\BasePartial.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */