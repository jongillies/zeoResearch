/*     */ package org.joda.time.convert;
/*     */ 
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTimeUtils;
/*     */ import org.joda.time.DateTimeZone;
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
/*     */ class ReadablePartialConverter
/*     */   extends AbstractConverter
/*     */   implements PartialConverter
/*     */ {
/*  35 */   static final ReadablePartialConverter INSTANCE = new ReadablePartialConverter();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Chronology getChronology(Object paramObject, DateTimeZone paramDateTimeZone)
/*     */   {
/*  53 */     return getChronology(paramObject, (Chronology)null).withZone(paramDateTimeZone);
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
/*     */   public Chronology getChronology(Object paramObject, Chronology paramChronology)
/*     */   {
/*  67 */     if (paramChronology == null) {
/*  68 */       paramChronology = ((ReadablePartial)paramObject).getChronology();
/*  69 */       paramChronology = DateTimeUtils.getChronology(paramChronology);
/*     */     }
/*  71 */     return paramChronology;
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
/*     */   public int[] getPartialValues(ReadablePartial paramReadablePartial, Object paramObject, Chronology paramChronology)
/*     */   {
/*  87 */     ReadablePartial localReadablePartial = (ReadablePartial)paramObject;
/*  88 */     int i = paramReadablePartial.size();
/*  89 */     int[] arrayOfInt = new int[i];
/*  90 */     for (int j = 0; j < i; j++) {
/*  91 */       arrayOfInt[j] = localReadablePartial.get(paramReadablePartial.getFieldType(j));
/*     */     }
/*  93 */     paramChronology.validate(paramReadablePartial, arrayOfInt);
/*  94 */     return arrayOfInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Class getSupportedType()
/*     */   {
/* 104 */     return ReadablePartial.class;
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\convert\ReadablePartialConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */