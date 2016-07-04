/*     */ package org.joda.time.convert;
/*     */ 
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTimeUtils;
/*     */ import org.joda.time.ReadWritableInterval;
/*     */ import org.joda.time.ReadWritablePeriod;
/*     */ import org.joda.time.ReadableInterval;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ReadableIntervalConverter
/*     */   extends AbstractConverter
/*     */   implements IntervalConverter, DurationConverter, PeriodConverter
/*     */ {
/*  36 */   static final ReadableIntervalConverter INSTANCE = new ReadableIntervalConverter();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getDurationMillis(Object paramObject)
/*     */   {
/*  52 */     return ((ReadableInterval)paramObject).toDurationMillis();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setInto(ReadWritablePeriod paramReadWritablePeriod, Object paramObject, Chronology paramChronology)
/*     */   {
/*  64 */     ReadableInterval localReadableInterval = (ReadableInterval)paramObject;
/*  65 */     paramChronology = paramChronology != null ? paramChronology : DateTimeUtils.getIntervalChronology(localReadableInterval);
/*  66 */     long l1 = localReadableInterval.getStartMillis();
/*  67 */     long l2 = localReadableInterval.getEndMillis();
/*  68 */     int[] arrayOfInt = paramChronology.get(paramReadWritablePeriod, l1, l2);
/*  69 */     for (int i = 0; i < arrayOfInt.length; i++) {
/*  70 */       paramReadWritablePeriod.setValue(i, arrayOfInt[i]);
/*     */     }
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
/*     */   public boolean isReadableInterval(Object paramObject, Chronology paramChronology)
/*     */   {
/*  86 */     return true;
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
/*     */   public void setInto(ReadWritableInterval paramReadWritableInterval, Object paramObject, Chronology paramChronology)
/*     */   {
/*  99 */     ReadableInterval localReadableInterval = (ReadableInterval)paramObject;
/* 100 */     paramReadWritableInterval.setInterval(localReadableInterval);
/* 101 */     if (paramChronology != null) {
/* 102 */       paramReadWritableInterval.setChronology(paramChronology);
/*     */     } else {
/* 104 */       paramReadWritableInterval.setChronology(localReadableInterval.getChronology());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Class getSupportedType()
/*     */   {
/* 113 */     return ReadableInterval.class;
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\convert\ReadableIntervalConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */