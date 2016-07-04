/*      */ package org.joda.time;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Locale;
/*      */ import org.joda.time.base.BaseDateTime;
/*      */ import org.joda.time.chrono.ISOChronology;
/*      */ import org.joda.time.field.AbstractReadableInstantFieldProperty;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class DateTime
/*      */   extends BaseDateTime
/*      */   implements ReadableDateTime, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = -5171125899451703815L;
/*      */   
/*      */   public DateTime() {}
/*      */   
/*      */   public DateTime(DateTimeZone paramDateTimeZone)
/*      */   {
/*   96 */     super(paramDateTimeZone);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime(Chronology paramChronology)
/*      */   {
/*  109 */     super(paramChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime(long paramLong)
/*      */   {
/*  120 */     super(paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime(long paramLong, DateTimeZone paramDateTimeZone)
/*      */   {
/*  133 */     super(paramLong, paramDateTimeZone);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime(long paramLong, Chronology paramChronology)
/*      */   {
/*  147 */     super(paramLong, paramChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime(Object paramObject)
/*      */   {
/*  168 */     super(paramObject, (Chronology)null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime(Object paramObject, DateTimeZone paramDateTimeZone)
/*      */   {
/*  192 */     super(paramObject, paramDateTimeZone);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime(Object paramObject, Chronology paramChronology)
/*      */   {
/*  213 */     super(paramObject, DateTimeUtils.getChronology(paramChronology));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
/*      */   {
/*  237 */     super(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, DateTimeZone paramDateTimeZone)
/*      */   {
/*  264 */     super(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramDateTimeZone);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, Chronology paramChronology)
/*      */   {
/*  293 */     super(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime toDateTime()
/*      */   {
/*  304 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime toDateTimeISO()
/*      */   {
/*  314 */     if (getChronology() == ISOChronology.getInstance()) {
/*  315 */       return this;
/*      */     }
/*  317 */     return super.toDateTimeISO();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime toDateTime(DateTimeZone paramDateTimeZone)
/*      */   {
/*  327 */     paramDateTimeZone = DateTimeUtils.getZone(paramDateTimeZone);
/*  328 */     if (getZone() == paramDateTimeZone) {
/*  329 */       return this;
/*      */     }
/*  331 */     return super.toDateTime(paramDateTimeZone);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime toDateTime(Chronology paramChronology)
/*      */   {
/*  341 */     paramChronology = DateTimeUtils.getChronology(paramChronology);
/*  342 */     if (getChronology() == paramChronology) {
/*  343 */       return this;
/*      */     }
/*  345 */     return super.toDateTime(paramChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withMillis(long paramLong)
/*      */   {
/*  359 */     return paramLong == getMillis() ? this : new DateTime(paramLong, getChronology());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withChronology(Chronology paramChronology)
/*      */   {
/*  372 */     paramChronology = DateTimeUtils.getChronology(paramChronology);
/*  373 */     return paramChronology == getChronology() ? this : new DateTime(getMillis(), paramChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withZone(DateTimeZone paramDateTimeZone)
/*      */   {
/*  395 */     return withChronology(getChronology().withZone(paramDateTimeZone));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withZoneRetainFields(DateTimeZone paramDateTimeZone)
/*      */   {
/*  416 */     paramDateTimeZone = DateTimeUtils.getZone(paramDateTimeZone);
/*  417 */     DateTimeZone localDateTimeZone = DateTimeUtils.getZone(getZone());
/*  418 */     if (paramDateTimeZone == localDateTimeZone) {
/*  419 */       return this;
/*      */     }
/*      */     
/*  422 */     long l = localDateTimeZone.getMillisKeepLocal(paramDateTimeZone, getMillis());
/*  423 */     return new DateTime(l, getChronology().withZone(paramDateTimeZone));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withDate(int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/*  444 */     Chronology localChronology = getChronology();
/*  445 */     long l = getMillis();
/*  446 */     l = localChronology.year().set(l, paramInt1);
/*  447 */     l = localChronology.monthOfYear().set(l, paramInt2);
/*  448 */     l = localChronology.dayOfMonth().set(l, paramInt3);
/*  449 */     return withMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/*  470 */     Chronology localChronology = getChronology();
/*  471 */     long l = getMillis();
/*  472 */     l = localChronology.hourOfDay().set(l, paramInt1);
/*  473 */     l = localChronology.minuteOfHour().set(l, paramInt2);
/*  474 */     l = localChronology.secondOfMinute().set(l, paramInt3);
/*  475 */     l = localChronology.millisOfSecond().set(l, paramInt4);
/*  476 */     return withMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withFields(ReadablePartial paramReadablePartial)
/*      */   {
/*  493 */     if (paramReadablePartial == null) {
/*  494 */       return this;
/*      */     }
/*  496 */     return withMillis(getChronology().set(paramReadablePartial, getMillis()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withField(DateTimeFieldType paramDateTimeFieldType, int paramInt)
/*      */   {
/*  519 */     if (paramDateTimeFieldType == null) {
/*  520 */       throw new IllegalArgumentException("Field must not be null");
/*      */     }
/*  522 */     long l = paramDateTimeFieldType.getField(getChronology()).set(getMillis(), paramInt);
/*  523 */     return withMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withFieldAdded(DurationFieldType paramDurationFieldType, int paramInt)
/*      */   {
/*  545 */     if (paramDurationFieldType == null) {
/*  546 */       throw new IllegalArgumentException("Field must not be null");
/*      */     }
/*  548 */     if (paramInt == 0) {
/*  549 */       return this;
/*      */     }
/*  551 */     long l = paramDurationFieldType.getField(getChronology()).add(getMillis(), paramInt);
/*  552 */     return withMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withDurationAdded(long paramLong, int paramInt)
/*      */   {
/*  567 */     if ((paramLong == 0L) || (paramInt == 0)) {
/*  568 */       return this;
/*      */     }
/*  570 */     long l = getChronology().add(getMillis(), paramLong, paramInt);
/*  571 */     return withMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withDurationAdded(ReadableDuration paramReadableDuration, int paramInt)
/*      */   {
/*  585 */     if ((paramReadableDuration == null) || (paramInt == 0)) {
/*  586 */       return this;
/*      */     }
/*  588 */     return withDurationAdded(paramReadableDuration.getMillis(), paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withPeriodAdded(ReadablePeriod paramReadablePeriod, int paramInt)
/*      */   {
/*  607 */     if ((paramReadablePeriod == null) || (paramInt == 0)) {
/*  608 */       return this;
/*      */     }
/*  610 */     long l = getChronology().add(paramReadablePeriod, getMillis(), paramInt);
/*  611 */     return withMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime plus(long paramLong)
/*      */   {
/*  626 */     return withDurationAdded(paramLong, 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime plus(ReadableDuration paramReadableDuration)
/*      */   {
/*  640 */     return withDurationAdded(paramReadableDuration, 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime plus(ReadablePeriod paramReadablePeriod)
/*      */   {
/*  666 */     return withPeriodAdded(paramReadablePeriod, 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime plusYears(int paramInt)
/*      */   {
/*  693 */     if (paramInt == 0) {
/*  694 */       return this;
/*      */     }
/*  696 */     long l = getChronology().years().add(getMillis(), paramInt);
/*  697 */     return withMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime plusMonths(int paramInt)
/*      */   {
/*  723 */     if (paramInt == 0) {
/*  724 */       return this;
/*      */     }
/*  726 */     long l = getChronology().months().add(getMillis(), paramInt);
/*  727 */     return withMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime plusWeeks(int paramInt)
/*      */   {
/*  749 */     if (paramInt == 0) {
/*  750 */       return this;
/*      */     }
/*  752 */     long l = getChronology().weeks().add(getMillis(), paramInt);
/*  753 */     return withMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime plusDays(int paramInt)
/*      */   {
/*  784 */     if (paramInt == 0) {
/*  785 */       return this;
/*      */     }
/*  787 */     long l = getChronology().days().add(getMillis(), paramInt);
/*  788 */     return withMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime plusHours(int paramInt)
/*      */   {
/*  815 */     if (paramInt == 0) {
/*  816 */       return this;
/*      */     }
/*  818 */     long l = getChronology().hours().add(getMillis(), paramInt);
/*  819 */     return withMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime plusMinutes(int paramInt)
/*      */   {
/*  842 */     if (paramInt == 0) {
/*  843 */       return this;
/*      */     }
/*  845 */     long l = getChronology().minutes().add(getMillis(), paramInt);
/*  846 */     return withMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime plusSeconds(int paramInt)
/*      */   {
/*  869 */     if (paramInt == 0) {
/*  870 */       return this;
/*      */     }
/*  872 */     long l = getChronology().seconds().add(getMillis(), paramInt);
/*  873 */     return withMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime plusMillis(int paramInt)
/*      */   {
/*  895 */     if (paramInt == 0) {
/*  896 */       return this;
/*      */     }
/*  898 */     long l = getChronology().millis().add(getMillis(), paramInt);
/*  899 */     return withMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime minus(long paramLong)
/*      */   {
/*  914 */     return withDurationAdded(paramLong, -1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime minus(ReadableDuration paramReadableDuration)
/*      */   {
/*  928 */     return withDurationAdded(paramReadableDuration, -1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime minus(ReadablePeriod paramReadablePeriod)
/*      */   {
/*  955 */     return withPeriodAdded(paramReadablePeriod, -1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime minusYears(int paramInt)
/*      */   {
/*  982 */     if (paramInt == 0) {
/*  983 */       return this;
/*      */     }
/*  985 */     long l = getChronology().years().subtract(getMillis(), paramInt);
/*  986 */     return withMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime minusMonths(int paramInt)
/*      */   {
/* 1012 */     if (paramInt == 0) {
/* 1013 */       return this;
/*      */     }
/* 1015 */     long l = getChronology().months().subtract(getMillis(), paramInt);
/* 1016 */     return withMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime minusWeeks(int paramInt)
/*      */   {
/* 1038 */     if (paramInt == 0) {
/* 1039 */       return this;
/*      */     }
/* 1041 */     long l = getChronology().weeks().subtract(getMillis(), paramInt);
/* 1042 */     return withMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime minusDays(int paramInt)
/*      */   {
/* 1073 */     if (paramInt == 0) {
/* 1074 */       return this;
/*      */     }
/* 1076 */     long l = getChronology().days().subtract(getMillis(), paramInt);
/* 1077 */     return withMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime minusHours(int paramInt)
/*      */   {
/* 1105 */     if (paramInt == 0) {
/* 1106 */       return this;
/*      */     }
/* 1108 */     long l = getChronology().hours().subtract(getMillis(), paramInt);
/* 1109 */     return withMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime minusMinutes(int paramInt)
/*      */   {
/* 1132 */     if (paramInt == 0) {
/* 1133 */       return this;
/*      */     }
/* 1135 */     long l = getChronology().minutes().subtract(getMillis(), paramInt);
/* 1136 */     return withMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime minusSeconds(int paramInt)
/*      */   {
/* 1159 */     if (paramInt == 0) {
/* 1160 */       return this;
/*      */     }
/* 1162 */     long l = getChronology().seconds().subtract(getMillis(), paramInt);
/* 1163 */     return withMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime minusMillis(int paramInt)
/*      */   {
/* 1186 */     if (paramInt == 0) {
/* 1187 */       return this;
/*      */     }
/* 1189 */     long l = getChronology().millis().subtract(getMillis(), paramInt);
/* 1190 */     return withMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property property(DateTimeFieldType paramDateTimeFieldType)
/*      */   {
/* 1202 */     if (paramDateTimeFieldType == null) {
/* 1203 */       throw new IllegalArgumentException("The DateTimeFieldType must not be null");
/*      */     }
/* 1205 */     DateTimeField localDateTimeField = paramDateTimeFieldType.getField(getChronology());
/* 1206 */     if (!localDateTimeField.isSupported()) {
/* 1207 */       throw new IllegalArgumentException("Field '" + paramDateTimeFieldType + "' is not supported");
/*      */     }
/* 1209 */     return new Property(this, localDateTimeField);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateMidnight toDateMidnight()
/*      */   {
/* 1220 */     return new DateMidnight(getMillis(), getChronology());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public YearMonthDay toYearMonthDay()
/*      */   {
/* 1231 */     return new YearMonthDay(getMillis(), getChronology());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public TimeOfDay toTimeOfDay()
/*      */   {
/* 1242 */     return new TimeOfDay(getMillis(), getChronology());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalDateTime toLocalDateTime()
/*      */   {
/* 1253 */     return new LocalDateTime(getMillis(), getChronology());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalDate toLocalDate()
/*      */   {
/* 1264 */     return new LocalDate(getMillis(), getChronology());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime toLocalTime()
/*      */   {
/* 1275 */     return new LocalTime(getMillis(), getChronology());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withEra(int paramInt)
/*      */   {
/* 1292 */     return withMillis(getChronology().era().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withCenturyOfEra(int paramInt)
/*      */   {
/* 1308 */     return withMillis(getChronology().centuryOfEra().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withYearOfEra(int paramInt)
/*      */   {
/* 1324 */     return withMillis(getChronology().yearOfEra().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withYearOfCentury(int paramInt)
/*      */   {
/* 1340 */     return withMillis(getChronology().yearOfCentury().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withYear(int paramInt)
/*      */   {
/* 1356 */     return withMillis(getChronology().year().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withWeekyear(int paramInt)
/*      */   {
/* 1372 */     return withMillis(getChronology().weekyear().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withMonthOfYear(int paramInt)
/*      */   {
/* 1388 */     return withMillis(getChronology().monthOfYear().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withWeekOfWeekyear(int paramInt)
/*      */   {
/* 1404 */     return withMillis(getChronology().weekOfWeekyear().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withDayOfYear(int paramInt)
/*      */   {
/* 1420 */     return withMillis(getChronology().dayOfYear().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withDayOfMonth(int paramInt)
/*      */   {
/* 1436 */     return withMillis(getChronology().dayOfMonth().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withDayOfWeek(int paramInt)
/*      */   {
/* 1452 */     return withMillis(getChronology().dayOfWeek().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withHourOfDay(int paramInt)
/*      */   {
/* 1469 */     return withMillis(getChronology().hourOfDay().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withMinuteOfHour(int paramInt)
/*      */   {
/* 1485 */     return withMillis(getChronology().minuteOfHour().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withSecondOfMinute(int paramInt)
/*      */   {
/* 1501 */     return withMillis(getChronology().secondOfMinute().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withMillisOfSecond(int paramInt)
/*      */   {
/* 1517 */     return withMillis(getChronology().millisOfSecond().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime withMillisOfDay(int paramInt)
/*      */   {
/* 1533 */     return withMillis(getChronology().millisOfDay().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property era()
/*      */   {
/* 1544 */     return new Property(this, getChronology().era());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property centuryOfEra()
/*      */   {
/* 1553 */     return new Property(this, getChronology().centuryOfEra());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property yearOfCentury()
/*      */   {
/* 1562 */     return new Property(this, getChronology().yearOfCentury());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property yearOfEra()
/*      */   {
/* 1571 */     return new Property(this, getChronology().yearOfEra());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property year()
/*      */   {
/* 1580 */     return new Property(this, getChronology().year());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property weekyear()
/*      */   {
/* 1589 */     return new Property(this, getChronology().weekyear());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property monthOfYear()
/*      */   {
/* 1598 */     return new Property(this, getChronology().monthOfYear());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property weekOfWeekyear()
/*      */   {
/* 1607 */     return new Property(this, getChronology().weekOfWeekyear());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property dayOfYear()
/*      */   {
/* 1616 */     return new Property(this, getChronology().dayOfYear());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property dayOfMonth()
/*      */   {
/* 1625 */     return new Property(this, getChronology().dayOfMonth());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property dayOfWeek()
/*      */   {
/* 1634 */     return new Property(this, getChronology().dayOfWeek());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property hourOfDay()
/*      */   {
/* 1645 */     return new Property(this, getChronology().hourOfDay());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property minuteOfDay()
/*      */   {
/* 1654 */     return new Property(this, getChronology().minuteOfDay());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property minuteOfHour()
/*      */   {
/* 1663 */     return new Property(this, getChronology().minuteOfHour());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property secondOfDay()
/*      */   {
/* 1672 */     return new Property(this, getChronology().secondOfDay());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property secondOfMinute()
/*      */   {
/* 1681 */     return new Property(this, getChronology().secondOfMinute());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property millisOfDay()
/*      */   {
/* 1690 */     return new Property(this, getChronology().millisOfDay());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property millisOfSecond()
/*      */   {
/* 1699 */     return new Property(this, getChronology().millisOfSecond());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final class Property
/*      */     extends AbstractReadableInstantFieldProperty
/*      */   {
/*      */     private static final long serialVersionUID = -6983323811635733510L;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private DateTime iInstant;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private DateTimeField iField;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     Property(DateTime paramDateTime, DateTimeField paramDateTimeField)
/*      */     {
/* 1750 */       this.iInstant = paramDateTime;
/* 1751 */       this.iField = paramDateTimeField;
/*      */     }
/*      */     
/*      */ 
/*      */     private void writeObject(ObjectOutputStream paramObjectOutputStream)
/*      */       throws IOException
/*      */     {
/* 1758 */       paramObjectOutputStream.writeObject(this.iInstant);
/* 1759 */       paramObjectOutputStream.writeObject(this.iField.getType());
/*      */     }
/*      */     
/*      */ 
/*      */     private void readObject(ObjectInputStream paramObjectInputStream)
/*      */       throws IOException, ClassNotFoundException
/*      */     {
/* 1766 */       this.iInstant = ((DateTime)paramObjectInputStream.readObject());
/* 1767 */       DateTimeFieldType localDateTimeFieldType = (DateTimeFieldType)paramObjectInputStream.readObject();
/* 1768 */       this.iField = localDateTimeFieldType.getField(this.iInstant.getChronology());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateTimeField getField()
/*      */     {
/* 1778 */       return this.iField;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected long getMillis()
/*      */     {
/* 1787 */       return this.iInstant.getMillis();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected Chronology getChronology()
/*      */     {
/* 1797 */       return this.iInstant.getChronology();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateTime getDateTime()
/*      */     {
/* 1806 */       return this.iInstant;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateTime addToCopy(int paramInt)
/*      */     {
/* 1823 */       return this.iInstant.withMillis(this.iField.add(this.iInstant.getMillis(), paramInt));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateTime addToCopy(long paramLong)
/*      */     {
/* 1839 */       return this.iInstant.withMillis(this.iField.add(this.iInstant.getMillis(), paramLong));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateTime addWrapFieldToCopy(int paramInt)
/*      */     {
/* 1857 */       return this.iInstant.withMillis(this.iField.addWrapField(this.iInstant.getMillis(), paramInt));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateTime setCopy(int paramInt)
/*      */     {
/* 1874 */       return this.iInstant.withMillis(this.iField.set(this.iInstant.getMillis(), paramInt));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateTime setCopy(String paramString, Locale paramLocale)
/*      */     {
/* 1891 */       return this.iInstant.withMillis(this.iField.set(this.iInstant.getMillis(), paramString, paramLocale));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateTime setCopy(String paramString)
/*      */     {
/* 1907 */       return setCopy(paramString, null);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateTime withMaximumValue()
/*      */     {
/* 1927 */       return setCopy(getMaximumValue());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateTime withMinimumValue()
/*      */     {
/* 1940 */       return setCopy(getMinimumValue());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateTime roundFloorCopy()
/*      */     {
/* 1950 */       return this.iInstant.withMillis(this.iField.roundFloor(this.iInstant.getMillis()));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateTime roundCeilingCopy()
/*      */     {
/* 1959 */       return this.iInstant.withMillis(this.iField.roundCeiling(this.iInstant.getMillis()));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateTime roundHalfFloorCopy()
/*      */     {
/* 1969 */       return this.iInstant.withMillis(this.iField.roundHalfFloor(this.iInstant.getMillis()));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateTime roundHalfCeilingCopy()
/*      */     {
/* 1979 */       return this.iInstant.withMillis(this.iField.roundHalfCeiling(this.iInstant.getMillis()));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateTime roundHalfEvenCopy()
/*      */     {
/* 1990 */       return this.iInstant.withMillis(this.iField.roundHalfEven(this.iInstant.getMillis()));
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\DateTime.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */