/*     */ package org.joda.time.base;
/*     */ 
/*     */ import org.joda.time.DateTime;
/*     */ import org.joda.time.DateTimeUtils;
/*     */ import org.joda.time.Duration;
/*     */ import org.joda.time.Interval;
/*     */ import org.joda.time.MutableInterval;
/*     */ import org.joda.time.Period;
/*     */ import org.joda.time.PeriodType;
/*     */ import org.joda.time.ReadableInstant;
/*     */ import org.joda.time.ReadableInterval;
/*     */ import org.joda.time.field.FieldUtils;
/*     */ import org.joda.time.format.DateTimeFormatter;
/*     */ import org.joda.time.format.ISODateTimeFormat;
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
/*     */ public abstract class AbstractInterval
/*     */   implements ReadableInterval
/*     */ {
/*     */   protected void checkInterval(long paramLong1, long paramLong2)
/*     */   {
/*  62 */     if (paramLong2 < paramLong1) {
/*  63 */       throw new IllegalArgumentException("The end instant must be greater or equal to the start");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTime getStart()
/*     */   {
/*  74 */     return new DateTime(getStartMillis(), getChronology());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTime getEnd()
/*     */   {
/*  83 */     return new DateTime(getEndMillis(), getChronology());
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
/*     */   public boolean contains(long paramLong)
/*     */   {
/*  98 */     long l1 = getStartMillis();
/*  99 */     long l2 = getEndMillis();
/* 100 */     return (paramLong >= l1) && (paramLong < l2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsNow()
/*     */   {
/* 112 */     return contains(DateTimeUtils.currentTimeMillis());
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean contains(ReadableInstant paramReadableInstant)
/*     */   {
/* 138 */     if (paramReadableInstant == null) {
/* 139 */       return containsNow();
/*     */     }
/* 141 */     return contains(paramReadableInstant.getMillis());
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
/*     */   public boolean contains(ReadableInterval paramReadableInterval)
/*     */   {
/* 179 */     if (paramReadableInterval == null) {
/* 180 */       return containsNow();
/*     */     }
/* 182 */     long l1 = paramReadableInterval.getStartMillis();
/* 183 */     long l2 = paramReadableInterval.getEndMillis();
/* 184 */     long l3 = getStartMillis();
/* 185 */     long l4 = getEndMillis();
/* 186 */     return (l3 <= l1) && (l1 < l4) && (l2 <= l4);
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
/*     */   public boolean overlaps(ReadableInterval paramReadableInterval)
/*     */   {
/* 231 */     long l1 = getStartMillis();
/* 232 */     long l2 = getEndMillis();
/* 233 */     if (paramReadableInterval == null) {
/* 234 */       l3 = DateTimeUtils.currentTimeMillis();
/* 235 */       return (l1 < l3) && (l3 < l2);
/*     */     }
/* 237 */     long l3 = paramReadableInterval.getStartMillis();
/* 238 */     long l4 = paramReadableInterval.getEndMillis();
/* 239 */     return (l1 < l4) && (l3 < l2);
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
/*     */   public boolean isBefore(long paramLong)
/*     */   {
/* 254 */     return getEndMillis() <= paramLong;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isBeforeNow()
/*     */   {
/* 265 */     return isBefore(DateTimeUtils.currentTimeMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isBefore(ReadableInstant paramReadableInstant)
/*     */   {
/* 277 */     if (paramReadableInstant == null) {
/* 278 */       return isBeforeNow();
/*     */     }
/* 280 */     return isBefore(paramReadableInstant.getMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isBefore(ReadableInterval paramReadableInterval)
/*     */   {
/* 292 */     if (paramReadableInterval == null) {
/* 293 */       return isBeforeNow();
/*     */     }
/* 295 */     return isBefore(paramReadableInterval.getStartMillis());
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
/*     */   public boolean isAfter(long paramLong)
/*     */   {
/* 309 */     return getStartMillis() > paramLong;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAfterNow()
/*     */   {
/* 320 */     return isAfter(DateTimeUtils.currentTimeMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAfter(ReadableInstant paramReadableInstant)
/*     */   {
/* 332 */     if (paramReadableInstant == null) {
/* 333 */       return isAfterNow();
/*     */     }
/* 335 */     return isAfter(paramReadableInstant.getMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAfter(ReadableInterval paramReadableInterval)
/*     */   {
/*     */     long l;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 349 */     if (paramReadableInterval == null) {
/* 350 */       l = DateTimeUtils.currentTimeMillis();
/*     */     } else {
/* 352 */       l = paramReadableInterval.getEndMillis();
/*     */     }
/* 354 */     return getStartMillis() >= l;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Interval toInterval()
/*     */   {
/* 364 */     return new Interval(getStartMillis(), getEndMillis(), getChronology());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutableInterval toMutableInterval()
/*     */   {
/* 375 */     return new MutableInterval(getStartMillis(), getEndMillis(), getChronology());
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
/*     */   public long toDurationMillis()
/*     */   {
/* 388 */     return FieldUtils.safeAdd(getEndMillis(), -getStartMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Duration toDuration()
/*     */   {
/* 400 */     long l = toDurationMillis();
/* 401 */     if (l == 0L) {
/* 402 */       return Duration.ZERO;
/*     */     }
/* 404 */     return new Duration(l);
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
/*     */   public Period toPeriod()
/*     */   {
/* 419 */     return new Period(getStartMillis(), getEndMillis(), getChronology());
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
/*     */   public Period toPeriod(PeriodType paramPeriodType)
/*     */   {
/* 433 */     return new Period(getStartMillis(), getEndMillis(), paramPeriodType, getChronology());
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
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 449 */     if (this == paramObject) {
/* 450 */       return true;
/*     */     }
/* 452 */     if (!(paramObject instanceof ReadableInterval)) {
/* 453 */       return false;
/*     */     }
/* 455 */     ReadableInterval localReadableInterval = (ReadableInterval)paramObject;
/* 456 */     return (getStartMillis() == localReadableInterval.getStartMillis()) && (getEndMillis() == localReadableInterval.getEndMillis()) && (FieldUtils.equals(getChronology(), localReadableInterval.getChronology()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 468 */     long l1 = getStartMillis();
/* 469 */     long l2 = getEndMillis();
/* 470 */     int i = 97;
/* 471 */     i = 31 * i + (int)(l1 ^ l1 >>> 32);
/* 472 */     i = 31 * i + (int)(l2 ^ l2 >>> 32);
/* 473 */     i = 31 * i + getChronology().hashCode();
/* 474 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 483 */     DateTimeFormatter localDateTimeFormatter = ISODateTimeFormat.dateHourMinuteSecondFraction();
/* 484 */     localDateTimeFormatter = localDateTimeFormatter.withChronology(getChronology());
/* 485 */     StringBuffer localStringBuffer = new StringBuffer(48);
/* 486 */     localDateTimeFormatter.printTo(localStringBuffer, getStartMillis());
/* 487 */     localStringBuffer.append('/');
/* 488 */     localDateTimeFormatter.printTo(localStringBuffer, getEndMillis());
/* 489 */     return localStringBuffer.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\base\AbstractInterval.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */