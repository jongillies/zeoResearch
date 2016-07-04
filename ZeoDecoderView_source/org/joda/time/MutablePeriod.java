/*     */ package org.joda.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.joda.time.base.BasePeriod;
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
/*     */ 
/*     */ public class MutablePeriod
/*     */   extends BasePeriod
/*     */   implements ReadWritablePeriod, Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3436451121567212165L;
/*     */   
/*     */   public MutablePeriod()
/*     */   {
/*  65 */     super(0L, null, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutablePeriod(PeriodType paramPeriodType)
/*     */   {
/*  74 */     super(0L, paramPeriodType, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutablePeriod(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/*  86 */     super(0, 0, 0, 0, paramInt1, paramInt2, paramInt3, paramInt4, PeriodType.standard());
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
/*     */   public MutablePeriod(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
/*     */   {
/* 103 */     super(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, PeriodType.standard());
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
/*     */   public MutablePeriod(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, PeriodType paramPeriodType)
/*     */   {
/* 122 */     super(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramPeriodType);
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
/*     */   public MutablePeriod(long paramLong)
/*     */   {
/* 152 */     super(paramLong, null, null);
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
/*     */   public MutablePeriod(long paramLong, PeriodType paramPeriodType)
/*     */   {
/* 172 */     super(paramLong, paramPeriodType, null);
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
/*     */   public MutablePeriod(long paramLong, Chronology paramChronology)
/*     */   {
/* 193 */     super(paramLong, null, paramChronology);
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
/*     */   public MutablePeriod(long paramLong, PeriodType paramPeriodType, Chronology paramChronology)
/*     */   {
/* 214 */     super(paramLong, paramPeriodType, paramChronology);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutablePeriod(long paramLong1, long paramLong2)
/*     */   {
/* 225 */     super(paramLong1, paramLong2, null, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutablePeriod(long paramLong1, long paramLong2, PeriodType paramPeriodType)
/*     */   {
/* 236 */     super(paramLong1, paramLong2, paramPeriodType, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutablePeriod(long paramLong1, long paramLong2, Chronology paramChronology)
/*     */   {
/* 248 */     super(paramLong1, paramLong2, null, paramChronology);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutablePeriod(long paramLong1, long paramLong2, PeriodType paramPeriodType, Chronology paramChronology)
/*     */   {
/* 260 */     super(paramLong1, paramLong2, paramPeriodType, paramChronology);
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
/*     */   public MutablePeriod(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
/*     */   {
/* 274 */     super(paramReadableInstant1, paramReadableInstant2, null);
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
/*     */   public MutablePeriod(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2, PeriodType paramPeriodType)
/*     */   {
/* 288 */     super(paramReadableInstant1, paramReadableInstant2, paramPeriodType);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutablePeriod(ReadableInstant paramReadableInstant, ReadableDuration paramReadableDuration)
/*     */   {
/* 298 */     super(paramReadableInstant, paramReadableDuration, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutablePeriod(ReadableInstant paramReadableInstant, ReadableDuration paramReadableDuration, PeriodType paramPeriodType)
/*     */   {
/* 309 */     super(paramReadableInstant, paramReadableDuration, paramPeriodType);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutablePeriod(ReadableDuration paramReadableDuration, ReadableInstant paramReadableInstant)
/*     */   {
/* 319 */     super(paramReadableDuration, paramReadableInstant, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutablePeriod(ReadableDuration paramReadableDuration, ReadableInstant paramReadableInstant, PeriodType paramPeriodType)
/*     */   {
/* 330 */     super(paramReadableDuration, paramReadableInstant, paramPeriodType);
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
/*     */   public MutablePeriod(Object paramObject)
/*     */   {
/* 346 */     super(paramObject, null, null);
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
/*     */   public MutablePeriod(Object paramObject, PeriodType paramPeriodType)
/*     */   {
/* 363 */     super(paramObject, paramPeriodType, null);
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
/*     */   public MutablePeriod(Object paramObject, Chronology paramChronology)
/*     */   {
/* 380 */     super(paramObject, null, paramChronology);
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
/*     */   public MutablePeriod(Object paramObject, PeriodType paramPeriodType, Chronology paramChronology)
/*     */   {
/* 398 */     super(paramObject, paramPeriodType, paramChronology);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 406 */     super.setValues(new int[size()]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValue(int paramInt1, int paramInt2)
/*     */   {
/* 417 */     super.setValue(paramInt1, paramInt2);
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
/*     */   public void set(DurationFieldType paramDurationFieldType, int paramInt)
/*     */   {
/* 430 */     super.setField(paramDurationFieldType, paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPeriod(ReadablePeriod paramReadablePeriod)
/*     */   {
/* 440 */     super.setPeriod(paramReadablePeriod);
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
/*     */   public void setPeriod(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
/*     */   {
/* 458 */     super.setPeriod(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPeriod(ReadableInterval paramReadableInterval)
/*     */   {
/* 469 */     if (paramReadableInterval == null) {
/* 470 */       setPeriod(0L);
/*     */     } else {
/* 472 */       Chronology localChronology = DateTimeUtils.getChronology(paramReadableInterval.getChronology());
/* 473 */       setPeriod(paramReadableInterval.getStartMillis(), paramReadableInterval.getEndMillis(), localChronology);
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
/*     */   public void setPeriod(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
/*     */   {
/* 488 */     if (paramReadableInstant1 == paramReadableInstant2) {
/* 489 */       setPeriod(0L);
/*     */     } else {
/* 491 */       long l1 = DateTimeUtils.getInstantMillis(paramReadableInstant1);
/* 492 */       long l2 = DateTimeUtils.getInstantMillis(paramReadableInstant2);
/* 493 */       Chronology localChronology = DateTimeUtils.getIntervalChronology(paramReadableInstant1, paramReadableInstant2);
/* 494 */       setPeriod(l1, l2, localChronology);
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
/*     */   public void setPeriod(long paramLong1, long paramLong2)
/*     */   {
/* 507 */     setPeriod(paramLong1, paramLong2, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPeriod(long paramLong1, long paramLong2, Chronology paramChronology)
/*     */   {
/* 519 */     paramChronology = DateTimeUtils.getChronology(paramChronology);
/* 520 */     setValues(paramChronology.get(this, paramLong1, paramLong2));
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
/*     */   public void setPeriod(ReadableDuration paramReadableDuration)
/*     */   {
/* 535 */     setPeriod(paramReadableDuration, null);
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
/*     */   public void setPeriod(ReadableDuration paramReadableDuration, Chronology paramChronology)
/*     */   {
/* 551 */     long l = DateTimeUtils.getDurationMillis(paramReadableDuration);
/* 552 */     setPeriod(l, paramChronology);
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
/*     */   public void setPeriod(long paramLong)
/*     */   {
/* 567 */     setPeriod(paramLong, null);
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
/*     */   public void setPeriod(long paramLong, Chronology paramChronology)
/*     */   {
/* 582 */     paramChronology = DateTimeUtils.getChronology(paramChronology);
/* 583 */     setValues(paramChronology.get(this, paramLong));
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
/*     */   public void add(DurationFieldType paramDurationFieldType, int paramInt)
/*     */   {
/* 597 */     super.addField(paramDurationFieldType, paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(ReadablePeriod paramReadablePeriod)
/*     */   {
/* 609 */     super.addPeriod(paramReadablePeriod);
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
/*     */   public void add(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
/*     */   {
/* 629 */     setPeriod(FieldUtils.safeAdd(getYears(), paramInt1), FieldUtils.safeAdd(getMonths(), paramInt2), FieldUtils.safeAdd(getWeeks(), paramInt3), FieldUtils.safeAdd(getDays(), paramInt4), FieldUtils.safeAdd(getHours(), paramInt5), FieldUtils.safeAdd(getMinutes(), paramInt6), FieldUtils.safeAdd(getSeconds(), paramInt7), FieldUtils.safeAdd(getMillis(), paramInt8));
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
/*     */   public void add(ReadableInterval paramReadableInterval)
/*     */   {
/* 649 */     if (paramReadableInterval != null) {
/* 650 */       add(paramReadableInterval.toPeriod(getPeriodType()));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(ReadableDuration paramReadableDuration)
/*     */   {
/* 662 */     if (paramReadableDuration != null) {
/* 663 */       add(new Period(paramReadableDuration.getMillis(), getPeriodType()));
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
/*     */   public void add(long paramLong)
/*     */   {
/* 679 */     add(new Period(paramLong, getPeriodType()));
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
/*     */   public void add(long paramLong, Chronology paramChronology)
/*     */   {
/* 695 */     add(new Period(paramLong, getPeriodType(), paramChronology));
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
/*     */   public void mergePeriod(ReadablePeriod paramReadablePeriod)
/*     */   {
/* 708 */     super.mergePeriod(paramReadablePeriod);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getYears()
/*     */   {
/* 718 */     return getPeriodType().getIndexedField(this, PeriodType.YEAR_INDEX);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMonths()
/*     */   {
/* 727 */     return getPeriodType().getIndexedField(this, PeriodType.MONTH_INDEX);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getWeeks()
/*     */   {
/* 736 */     return getPeriodType().getIndexedField(this, PeriodType.WEEK_INDEX);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDays()
/*     */   {
/* 745 */     return getPeriodType().getIndexedField(this, PeriodType.DAY_INDEX);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getHours()
/*     */   {
/* 755 */     return getPeriodType().getIndexedField(this, PeriodType.HOUR_INDEX);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMinutes()
/*     */   {
/* 764 */     return getPeriodType().getIndexedField(this, PeriodType.MINUTE_INDEX);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeconds()
/*     */   {
/* 773 */     return getPeriodType().getIndexedField(this, PeriodType.SECOND_INDEX);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMillis()
/*     */   {
/* 782 */     return getPeriodType().getIndexedField(this, PeriodType.MILLI_INDEX);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setYears(int paramInt)
/*     */   {
/* 793 */     super.setField(DurationFieldType.years(), paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addYears(int paramInt)
/*     */   {
/* 804 */     super.addField(DurationFieldType.years(), paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMonths(int paramInt)
/*     */   {
/* 815 */     super.setField(DurationFieldType.months(), paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addMonths(int paramInt)
/*     */   {
/* 826 */     super.addField(DurationFieldType.months(), paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setWeeks(int paramInt)
/*     */   {
/* 837 */     super.setField(DurationFieldType.weeks(), paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addWeeks(int paramInt)
/*     */   {
/* 848 */     super.addField(DurationFieldType.weeks(), paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDays(int paramInt)
/*     */   {
/* 859 */     super.setField(DurationFieldType.days(), paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addDays(int paramInt)
/*     */   {
/* 870 */     super.addField(DurationFieldType.days(), paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setHours(int paramInt)
/*     */   {
/* 881 */     super.setField(DurationFieldType.hours(), paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addHours(int paramInt)
/*     */   {
/* 892 */     super.addField(DurationFieldType.hours(), paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMinutes(int paramInt)
/*     */   {
/* 903 */     super.setField(DurationFieldType.minutes(), paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addMinutes(int paramInt)
/*     */   {
/* 914 */     super.addField(DurationFieldType.minutes(), paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSeconds(int paramInt)
/*     */   {
/* 925 */     super.setField(DurationFieldType.seconds(), paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addSeconds(int paramInt)
/*     */   {
/* 936 */     super.addField(DurationFieldType.seconds(), paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMillis(int paramInt)
/*     */   {
/* 947 */     super.setField(DurationFieldType.millis(), paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addMillis(int paramInt)
/*     */   {
/* 958 */     super.addField(DurationFieldType.millis(), paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutablePeriod copy()
/*     */   {
/* 969 */     return (MutablePeriod)clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/*     */     try
/*     */     {
/* 979 */       return super.clone();
/*     */     } catch (CloneNotSupportedException localCloneNotSupportedException) {
/* 981 */       throw new InternalError("Clone error");
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\MutablePeriod.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */