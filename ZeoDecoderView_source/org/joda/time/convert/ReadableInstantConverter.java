/*     */ package org.joda.time.convert;
/*     */ 
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTimeUtils;
/*     */ import org.joda.time.DateTimeZone;
/*     */ import org.joda.time.ReadableInstant;
/*     */ import org.joda.time.chrono.ISOChronology;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ReadableInstantConverter
/*     */   extends AbstractConverter
/*     */   implements InstantConverter, PartialConverter
/*     */ {
/*  36 */   static final ReadableInstantConverter INSTANCE = new ReadableInstantConverter();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  58 */     Chronology localChronology = ((ReadableInstant)paramObject).getChronology();
/*  59 */     if (localChronology == null) {
/*  60 */       return ISOChronology.getInstance(paramDateTimeZone);
/*     */     }
/*  62 */     DateTimeZone localDateTimeZone = localChronology.getZone();
/*  63 */     if (localDateTimeZone != paramDateTimeZone) {
/*  64 */       localChronology = localChronology.withZone(paramDateTimeZone);
/*  65 */       if (localChronology == null) {
/*  66 */         return ISOChronology.getInstance(paramDateTimeZone);
/*     */       }
/*     */     }
/*  69 */     return localChronology;
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
/*  83 */     if (paramChronology == null) {
/*  84 */       paramChronology = ((ReadableInstant)paramObject).getChronology();
/*  85 */       paramChronology = DateTimeUtils.getChronology(paramChronology);
/*     */     }
/*  87 */     return paramChronology;
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
/*     */   public long getInstantMillis(Object paramObject, Chronology paramChronology)
/*     */   {
/* 100 */     return ((ReadableInstant)paramObject).getMillis();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Class getSupportedType()
/*     */   {
/* 110 */     return ReadableInstant.class;
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\convert\ReadableInstantConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */