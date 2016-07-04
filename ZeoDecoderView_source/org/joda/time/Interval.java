/*     */ package org.joda.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.joda.time.base.BaseInterval;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Interval
/*     */   extends BaseInterval
/*     */   implements ReadableInterval, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 4922451897541386752L;
/*     */   
/*     */   public Interval(long paramLong1, long paramLong2)
/*     */   {
/*  67 */     super(paramLong1, paramLong2, null);
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
/*     */   public Interval(long paramLong1, long paramLong2, DateTimeZone paramDateTimeZone)
/*     */   {
/*  81 */     super(paramLong1, paramLong2, ISOChronology.getInstance(paramDateTimeZone));
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
/*     */   public Interval(long paramLong1, long paramLong2, Chronology paramChronology)
/*     */   {
/*  94 */     super(paramLong1, paramLong2, paramChronology);
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
/*     */   public Interval(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
/*     */   {
/* 107 */     super(paramReadableInstant1, paramReadableInstant2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Interval(ReadableInstant paramReadableInstant, ReadableDuration paramReadableDuration)
/*     */   {
/* 119 */     super(paramReadableInstant, paramReadableDuration);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Interval(ReadableDuration paramReadableDuration, ReadableInstant paramReadableInstant)
/*     */   {
/* 131 */     super(paramReadableDuration, paramReadableInstant);
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
/*     */   public Interval(ReadableInstant paramReadableInstant, ReadablePeriod paramReadablePeriod)
/*     */   {
/* 146 */     super(paramReadableInstant, paramReadablePeriod);
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
/*     */   public Interval(ReadablePeriod paramReadablePeriod, ReadableInstant paramReadableInstant)
/*     */   {
/* 161 */     super(paramReadablePeriod, paramReadableInstant);
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
/*     */   public Interval(Object paramObject)
/*     */   {
/* 178 */     super(paramObject, null);
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
/*     */   public Interval(Object paramObject, Chronology paramChronology)
/*     */   {
/* 197 */     super(paramObject, paramChronology);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Interval toInterval()
/*     */   {
/* 208 */     return this;
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
/*     */   public Interval overlap(ReadableInterval paramReadableInterval)
/*     */   {
/* 236 */     paramReadableInterval = DateTimeUtils.getReadableInterval(paramReadableInterval);
/* 237 */     if (!overlaps(paramReadableInterval)) {
/* 238 */       return null;
/*     */     }
/* 240 */     long l1 = Math.max(getStartMillis(), paramReadableInterval.getStartMillis());
/* 241 */     long l2 = Math.min(getEndMillis(), paramReadableInterval.getEndMillis());
/* 242 */     return new Interval(l1, l2, getChronology());
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
/*     */   public Interval gap(ReadableInterval paramReadableInterval)
/*     */   {
/* 271 */     paramReadableInterval = DateTimeUtils.getReadableInterval(paramReadableInterval);
/* 272 */     long l1 = paramReadableInterval.getStartMillis();
/* 273 */     long l2 = paramReadableInterval.getEndMillis();
/* 274 */     long l3 = getStartMillis();
/* 275 */     long l4 = getEndMillis();
/* 276 */     if (l3 > l2)
/* 277 */       return new Interval(l2, l3, getChronology());
/* 278 */     if (l1 > l4) {
/* 279 */       return new Interval(l4, l1, getChronology());
/*     */     }
/* 281 */     return null;
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
/*     */   public boolean abuts(ReadableInterval paramReadableInterval)
/*     */   {
/* 323 */     if (paramReadableInterval == null) {
/* 324 */       long l = DateTimeUtils.currentTimeMillis();
/* 325 */       return (getStartMillis() == l) || (getEndMillis() == l);
/*     */     }
/* 327 */     return (paramReadableInterval.getEndMillis() == getStartMillis()) || (getEndMillis() == paramReadableInterval.getStartMillis());
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
/*     */   public Interval withChronology(Chronology paramChronology)
/*     */   {
/* 340 */     if (getChronology() == paramChronology) {
/* 341 */       return this;
/*     */     }
/* 343 */     return new Interval(getStartMillis(), getEndMillis(), paramChronology);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Interval withStartMillis(long paramLong)
/*     */   {
/* 354 */     if (paramLong == getStartMillis()) {
/* 355 */       return this;
/*     */     }
/* 357 */     return new Interval(paramLong, getEndMillis(), getChronology());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Interval withStart(ReadableInstant paramReadableInstant)
/*     */   {
/* 368 */     long l = DateTimeUtils.getInstantMillis(paramReadableInstant);
/* 369 */     return withStartMillis(l);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Interval withEndMillis(long paramLong)
/*     */   {
/* 380 */     if (paramLong == getEndMillis()) {
/* 381 */       return this;
/*     */     }
/* 383 */     return new Interval(getStartMillis(), paramLong, getChronology());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Interval withEnd(ReadableInstant paramReadableInstant)
/*     */   {
/* 394 */     long l = DateTimeUtils.getInstantMillis(paramReadableInstant);
/* 395 */     return withEndMillis(l);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Interval withDurationAfterStart(ReadableDuration paramReadableDuration)
/*     */   {
/* 407 */     long l1 = DateTimeUtils.getDurationMillis(paramReadableDuration);
/* 408 */     if (l1 == toDurationMillis()) {
/* 409 */       return this;
/*     */     }
/* 411 */     Chronology localChronology = getChronology();
/* 412 */     long l2 = getStartMillis();
/* 413 */     long l3 = localChronology.add(l2, l1, 1);
/* 414 */     return new Interval(l2, l3, localChronology);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Interval withDurationBeforeEnd(ReadableDuration paramReadableDuration)
/*     */   {
/* 425 */     long l1 = DateTimeUtils.getDurationMillis(paramReadableDuration);
/* 426 */     if (l1 == toDurationMillis()) {
/* 427 */       return this;
/*     */     }
/* 429 */     Chronology localChronology = getChronology();
/* 430 */     long l2 = getEndMillis();
/* 431 */     long l3 = localChronology.add(l2, l1, -1);
/* 432 */     return new Interval(l3, l2, localChronology);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Interval withPeriodAfterStart(ReadablePeriod paramReadablePeriod)
/*     */   {
/* 444 */     if (paramReadablePeriod == null) {
/* 445 */       return withDurationAfterStart(null);
/*     */     }
/* 447 */     Chronology localChronology = getChronology();
/* 448 */     long l1 = getStartMillis();
/* 449 */     long l2 = localChronology.add(paramReadablePeriod, l1, 1);
/* 450 */     return new Interval(l1, l2, localChronology);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Interval withPeriodBeforeEnd(ReadablePeriod paramReadablePeriod)
/*     */   {
/* 461 */     if (paramReadablePeriod == null) {
/* 462 */       return withDurationBeforeEnd(null);
/*     */     }
/* 464 */     Chronology localChronology = getChronology();
/* 465 */     long l1 = getEndMillis();
/* 466 */     long l2 = localChronology.add(paramReadablePeriod, l1, -1);
/* 467 */     return new Interval(l2, l1, localChronology);
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\Interval.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */