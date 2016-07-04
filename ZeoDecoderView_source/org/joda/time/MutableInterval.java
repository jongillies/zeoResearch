/*     */ package org.joda.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.joda.time.base.BaseInterval;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MutableInterval
/*     */   extends BaseInterval
/*     */   implements ReadWritableInterval, Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -5982824024992428470L;
/*     */   
/*     */   public MutableInterval()
/*     */   {
/*  64 */     super(0L, 0L, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutableInterval(long paramLong1, long paramLong2)
/*     */   {
/*  75 */     super(paramLong1, paramLong2, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutableInterval(long paramLong1, long paramLong2, Chronology paramChronology)
/*     */   {
/*  87 */     super(paramLong1, paramLong2, paramChronology);
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
/*     */   public MutableInterval(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
/*     */   {
/* 100 */     super(paramReadableInstant1, paramReadableInstant2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutableInterval(ReadableInstant paramReadableInstant, ReadableDuration paramReadableDuration)
/*     */   {
/* 112 */     super(paramReadableInstant, paramReadableDuration);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutableInterval(ReadableDuration paramReadableDuration, ReadableInstant paramReadableInstant)
/*     */   {
/* 124 */     super(paramReadableDuration, paramReadableInstant);
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
/*     */   public MutableInterval(ReadableInstant paramReadableInstant, ReadablePeriod paramReadablePeriod)
/*     */   {
/* 139 */     super(paramReadableInstant, paramReadablePeriod);
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
/*     */   public MutableInterval(ReadablePeriod paramReadablePeriod, ReadableInstant paramReadableInstant)
/*     */   {
/* 154 */     super(paramReadablePeriod, paramReadableInstant);
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
/*     */   public MutableInterval(Object paramObject)
/*     */   {
/* 171 */     super(paramObject, null);
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
/*     */   public MutableInterval(Object paramObject, Chronology paramChronology)
/*     */   {
/* 190 */     super(paramObject, paramChronology);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setInterval(long paramLong1, long paramLong2)
/*     */   {
/* 202 */     super.setInterval(paramLong1, paramLong2, getChronology());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setInterval(ReadableInterval paramReadableInterval)
/*     */   {
/* 212 */     if (paramReadableInterval == null) {
/* 213 */       throw new IllegalArgumentException("Interval must not be null");
/*     */     }
/* 215 */     long l1 = paramReadableInterval.getStartMillis();
/* 216 */     long l2 = paramReadableInterval.getEndMillis();
/* 217 */     Chronology localChronology = paramReadableInterval.getChronology();
/* 218 */     super.setInterval(l1, l2, localChronology);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setInterval(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
/*     */   {
/*     */     long l1;
/*     */     
/*     */ 
/*     */ 
/* 230 */     if ((paramReadableInstant1 == null) && (paramReadableInstant2 == null)) {
/* 231 */       l1 = DateTimeUtils.currentTimeMillis();
/* 232 */       setInterval(l1, l1);
/*     */     } else {
/* 234 */       l1 = DateTimeUtils.getInstantMillis(paramReadableInstant1);
/* 235 */       long l2 = DateTimeUtils.getInstantMillis(paramReadableInstant2);
/* 236 */       Chronology localChronology = DateTimeUtils.getInstantChronology(paramReadableInstant1);
/* 237 */       super.setInterval(l1, l2, localChronology);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setChronology(Chronology paramChronology)
/*     */   {
/* 248 */     super.setInterval(getStartMillis(), getEndMillis(), paramChronology);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStartMillis(long paramLong)
/*     */   {
/* 259 */     super.setInterval(paramLong, getEndMillis(), getChronology());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStart(ReadableInstant paramReadableInstant)
/*     */   {
/* 269 */     long l = DateTimeUtils.getInstantMillis(paramReadableInstant);
/* 270 */     super.setInterval(l, getEndMillis(), getChronology());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEndMillis(long paramLong)
/*     */   {
/* 281 */     super.setInterval(getStartMillis(), paramLong, getChronology());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEnd(ReadableInstant paramReadableInstant)
/*     */   {
/* 291 */     long l = DateTimeUtils.getInstantMillis(paramReadableInstant);
/* 292 */     super.setInterval(getStartMillis(), l, getChronology());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDurationAfterStart(long paramLong)
/*     */   {
/* 304 */     setEndMillis(FieldUtils.safeAdd(getStartMillis(), paramLong));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDurationBeforeEnd(long paramLong)
/*     */   {
/* 315 */     setStartMillis(FieldUtils.safeAdd(getEndMillis(), -paramLong));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDurationAfterStart(ReadableDuration paramReadableDuration)
/*     */   {
/* 327 */     long l = DateTimeUtils.getDurationMillis(paramReadableDuration);
/* 328 */     setEndMillis(FieldUtils.safeAdd(getStartMillis(), l));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDurationBeforeEnd(ReadableDuration paramReadableDuration)
/*     */   {
/* 339 */     long l = DateTimeUtils.getDurationMillis(paramReadableDuration);
/* 340 */     setStartMillis(FieldUtils.safeAdd(getEndMillis(), -l));
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
/*     */   public void setPeriodAfterStart(ReadablePeriod paramReadablePeriod)
/*     */   {
/* 353 */     if (paramReadablePeriod == null) {
/* 354 */       setEndMillis(getStartMillis());
/*     */     } else {
/* 356 */       setEndMillis(getChronology().add(paramReadablePeriod, getStartMillis(), 1));
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
/*     */   public void setPeriodBeforeEnd(ReadablePeriod paramReadablePeriod)
/*     */   {
/* 369 */     if (paramReadablePeriod == null) {
/* 370 */       setStartMillis(getEndMillis());
/*     */     } else {
/* 372 */       setStartMillis(getChronology().add(paramReadablePeriod, getEndMillis(), -1));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutableInterval copy()
/*     */   {
/* 383 */     return (MutableInterval)clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/*     */     try
/*     */     {
/* 393 */       return super.clone();
/*     */     } catch (CloneNotSupportedException localCloneNotSupportedException) {
/* 395 */       throw new InternalError("Clone error");
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\MutableInterval.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */