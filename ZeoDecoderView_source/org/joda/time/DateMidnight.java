/*      */ package org.joda.time;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Locale;
/*      */ import org.joda.time.base.BaseDateTime;
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
/*      */ 
/*      */ 
/*      */ public final class DateMidnight
/*      */   extends BaseDateTime
/*      */   implements ReadableDateTime, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 156371964018738L;
/*      */   
/*      */   public DateMidnight() {}
/*      */   
/*      */   public DateMidnight(DateTimeZone paramDateTimeZone)
/*      */   {
/*   97 */     super(paramDateTimeZone);
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
/*      */   public DateMidnight(Chronology paramChronology)
/*      */   {
/*  111 */     super(paramChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateMidnight(long paramLong)
/*      */   {
/*  123 */     super(paramLong);
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
/*      */   public DateMidnight(long paramLong, DateTimeZone paramDateTimeZone)
/*      */   {
/*  137 */     super(paramLong, paramDateTimeZone);
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
/*      */   public DateMidnight(long paramLong, Chronology paramChronology)
/*      */   {
/*  152 */     super(paramLong, paramChronology);
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
/*      */   public DateMidnight(Object paramObject)
/*      */   {
/*  174 */     super(paramObject, (Chronology)null);
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
/*      */   public DateMidnight(Object paramObject, DateTimeZone paramDateTimeZone)
/*      */   {
/*  199 */     super(paramObject, paramDateTimeZone);
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
/*      */   public DateMidnight(Object paramObject, Chronology paramChronology)
/*      */   {
/*  221 */     super(paramObject, DateTimeUtils.getChronology(paramChronology));
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
/*      */   public DateMidnight(int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/*  235 */     super(paramInt1, paramInt2, paramInt3, 0, 0, 0, 0);
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
/*      */   public DateMidnight(int paramInt1, int paramInt2, int paramInt3, DateTimeZone paramDateTimeZone)
/*      */   {
/*  251 */     super(paramInt1, paramInt2, paramInt3, 0, 0, 0, 0, paramDateTimeZone);
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
/*      */   public DateMidnight(int paramInt1, int paramInt2, int paramInt3, Chronology paramChronology)
/*      */   {
/*  268 */     super(paramInt1, paramInt2, paramInt3, 0, 0, 0, 0, paramChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected long checkInstant(long paramLong, Chronology paramChronology)
/*      */   {
/*  279 */     return paramChronology.dayOfMonth().roundFloor(paramLong);
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
/*      */   public DateMidnight withMillis(long paramLong)
/*      */   {
/*  294 */     Chronology localChronology = getChronology();
/*  295 */     paramLong = checkInstant(paramLong, localChronology);
/*  296 */     return paramLong == getMillis() ? this : new DateMidnight(paramLong, localChronology);
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
/*      */   public DateMidnight withChronology(Chronology paramChronology)
/*      */   {
/*  321 */     return paramChronology == getChronology() ? this : new DateMidnight(getMillis(), paramChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateMidnight withZoneRetainFields(DateTimeZone paramDateTimeZone)
/*      */   {
/*  333 */     paramDateTimeZone = DateTimeUtils.getZone(paramDateTimeZone);
/*  334 */     DateTimeZone localDateTimeZone = DateTimeUtils.getZone(getZone());
/*  335 */     if (paramDateTimeZone == localDateTimeZone) {
/*  336 */       return this;
/*      */     }
/*      */     
/*  339 */     long l = localDateTimeZone.getMillisKeepLocal(paramDateTimeZone, getMillis());
/*  340 */     return new DateMidnight(l, getChronology().withZone(paramDateTimeZone));
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
/*      */   public DateMidnight withFields(ReadablePartial paramReadablePartial)
/*      */   {
/*  357 */     if (paramReadablePartial == null) {
/*  358 */       return this;
/*      */     }
/*  360 */     return withMillis(getChronology().set(paramReadablePartial, getMillis()));
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
/*      */   public DateMidnight withField(DateTimeFieldType paramDateTimeFieldType, int paramInt)
/*      */   {
/*  383 */     if (paramDateTimeFieldType == null) {
/*  384 */       throw new IllegalArgumentException("Field must not be null");
/*      */     }
/*  386 */     long l = paramDateTimeFieldType.getField(getChronology()).set(getMillis(), paramInt);
/*  387 */     return withMillis(l);
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
/*      */   public DateMidnight withFieldAdded(DurationFieldType paramDurationFieldType, int paramInt)
/*      */   {
/*  409 */     if (paramDurationFieldType == null) {
/*  410 */       throw new IllegalArgumentException("Field must not be null");
/*      */     }
/*  412 */     if (paramInt == 0) {
/*  413 */       return this;
/*      */     }
/*  415 */     long l = paramDurationFieldType.getField(getChronology()).add(getMillis(), paramInt);
/*  416 */     return withMillis(l);
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
/*      */   public DateMidnight withDurationAdded(long paramLong, int paramInt)
/*      */   {
/*  431 */     if ((paramLong == 0L) || (paramInt == 0)) {
/*  432 */       return this;
/*      */     }
/*  434 */     long l = getChronology().add(getMillis(), paramLong, paramInt);
/*  435 */     return withMillis(l);
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
/*      */   public DateMidnight withDurationAdded(ReadableDuration paramReadableDuration, int paramInt)
/*      */   {
/*  449 */     if ((paramReadableDuration == null) || (paramInt == 0)) {
/*  450 */       return this;
/*      */     }
/*  452 */     return withDurationAdded(paramReadableDuration.getMillis(), paramInt);
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
/*      */   public DateMidnight withPeriodAdded(ReadablePeriod paramReadablePeriod, int paramInt)
/*      */   {
/*  471 */     if ((paramReadablePeriod == null) || (paramInt == 0)) {
/*  472 */       return this;
/*      */     }
/*  474 */     long l = getChronology().add(paramReadablePeriod, getMillis(), paramInt);
/*  475 */     return withMillis(l);
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
/*      */   public DateMidnight plus(long paramLong)
/*      */   {
/*  489 */     return withDurationAdded(paramLong, 1);
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
/*      */   public DateMidnight plus(ReadableDuration paramReadableDuration)
/*      */   {
/*  502 */     return withDurationAdded(paramReadableDuration, 1);
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
/*      */   public DateMidnight plus(ReadablePeriod paramReadablePeriod)
/*      */   {
/*  519 */     return withPeriodAdded(paramReadablePeriod, 1);
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
/*      */   public DateMidnight plusYears(int paramInt)
/*      */   {
/*  540 */     if (paramInt == 0) {
/*  541 */       return this;
/*      */     }
/*  543 */     long l = getChronology().years().add(getMillis(), paramInt);
/*  544 */     return withMillis(l);
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
/*      */   public DateMidnight plusMonths(int paramInt)
/*      */   {
/*  564 */     if (paramInt == 0) {
/*  565 */       return this;
/*      */     }
/*  567 */     long l = getChronology().months().add(getMillis(), paramInt);
/*  568 */     return withMillis(l);
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
/*      */   public DateMidnight plusWeeks(int paramInt)
/*      */   {
/*  588 */     if (paramInt == 0) {
/*  589 */       return this;
/*      */     }
/*  591 */     long l = getChronology().weeks().add(getMillis(), paramInt);
/*  592 */     return withMillis(l);
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
/*      */   public DateMidnight plusDays(int paramInt)
/*      */   {
/*  612 */     if (paramInt == 0) {
/*  613 */       return this;
/*      */     }
/*  615 */     long l = getChronology().days().add(getMillis(), paramInt);
/*  616 */     return withMillis(l);
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
/*      */   public DateMidnight minus(long paramLong)
/*      */   {
/*  630 */     return withDurationAdded(paramLong, -1);
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
/*      */   public DateMidnight minus(ReadableDuration paramReadableDuration)
/*      */   {
/*  643 */     return withDurationAdded(paramReadableDuration, -1);
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
/*      */   public DateMidnight minus(ReadablePeriod paramReadablePeriod)
/*      */   {
/*  660 */     return withPeriodAdded(paramReadablePeriod, -1);
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
/*      */   public DateMidnight minusYears(int paramInt)
/*      */   {
/*  681 */     if (paramInt == 0) {
/*  682 */       return this;
/*      */     }
/*  684 */     long l = getChronology().years().subtract(getMillis(), paramInt);
/*  685 */     return withMillis(l);
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
/*      */   public DateMidnight minusMonths(int paramInt)
/*      */   {
/*  705 */     if (paramInt == 0) {
/*  706 */       return this;
/*      */     }
/*  708 */     long l = getChronology().months().subtract(getMillis(), paramInt);
/*  709 */     return withMillis(l);
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
/*      */   public DateMidnight minusWeeks(int paramInt)
/*      */   {
/*  729 */     if (paramInt == 0) {
/*  730 */       return this;
/*      */     }
/*  732 */     long l = getChronology().weeks().subtract(getMillis(), paramInt);
/*  733 */     return withMillis(l);
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
/*      */   public DateMidnight minusDays(int paramInt)
/*      */   {
/*  753 */     if (paramInt == 0) {
/*  754 */       return this;
/*      */     }
/*  756 */     long l = getChronology().days().subtract(getMillis(), paramInt);
/*  757 */     return withMillis(l);
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
/*  769 */     if (paramDateTimeFieldType == null) {
/*  770 */       throw new IllegalArgumentException("The DateTimeFieldType must not be null");
/*      */     }
/*  772 */     DateTimeField localDateTimeField = paramDateTimeFieldType.getField(getChronology());
/*  773 */     if (!localDateTimeField.isSupported()) {
/*  774 */       throw new IllegalArgumentException("Field '" + paramDateTimeFieldType + "' is not supported");
/*      */     }
/*  776 */     return new Property(this, localDateTimeField);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public YearMonthDay toYearMonthDay()
/*      */   {
/*  788 */     return new YearMonthDay(getMillis(), getChronology());
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
/*  799 */     return new LocalDate(getMillis(), getChronology());
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
/*      */   public Interval toInterval()
/*      */   {
/*  812 */     Chronology localChronology = getChronology();
/*  813 */     long l1 = getMillis();
/*  814 */     long l2 = DurationFieldType.days().getField(localChronology).add(l1, 1);
/*  815 */     return new Interval(l1, l2, localChronology);
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
/*      */   public DateMidnight withEra(int paramInt)
/*      */   {
/*  832 */     return withMillis(getChronology().era().set(getMillis(), paramInt));
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
/*      */   public DateMidnight withCenturyOfEra(int paramInt)
/*      */   {
/*  848 */     return withMillis(getChronology().centuryOfEra().set(getMillis(), paramInt));
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
/*      */   public DateMidnight withYearOfEra(int paramInt)
/*      */   {
/*  864 */     return withMillis(getChronology().yearOfEra().set(getMillis(), paramInt));
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
/*      */   public DateMidnight withYearOfCentury(int paramInt)
/*      */   {
/*  880 */     return withMillis(getChronology().yearOfCentury().set(getMillis(), paramInt));
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
/*      */   public DateMidnight withYear(int paramInt)
/*      */   {
/*  896 */     return withMillis(getChronology().year().set(getMillis(), paramInt));
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
/*      */   public DateMidnight withWeekyear(int paramInt)
/*      */   {
/*  912 */     return withMillis(getChronology().weekyear().set(getMillis(), paramInt));
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
/*      */   public DateMidnight withMonthOfYear(int paramInt)
/*      */   {
/*  928 */     return withMillis(getChronology().monthOfYear().set(getMillis(), paramInt));
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
/*      */   public DateMidnight withWeekOfWeekyear(int paramInt)
/*      */   {
/*  944 */     return withMillis(getChronology().weekOfWeekyear().set(getMillis(), paramInt));
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
/*      */   public DateMidnight withDayOfYear(int paramInt)
/*      */   {
/*  960 */     return withMillis(getChronology().dayOfYear().set(getMillis(), paramInt));
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
/*      */   public DateMidnight withDayOfMonth(int paramInt)
/*      */   {
/*  976 */     return withMillis(getChronology().dayOfMonth().set(getMillis(), paramInt));
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
/*      */   public DateMidnight withDayOfWeek(int paramInt)
/*      */   {
/*  992 */     return withMillis(getChronology().dayOfWeek().set(getMillis(), paramInt));
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
/* 1003 */     return new Property(this, getChronology().era());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property centuryOfEra()
/*      */   {
/* 1012 */     return new Property(this, getChronology().centuryOfEra());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property yearOfCentury()
/*      */   {
/* 1021 */     return new Property(this, getChronology().yearOfCentury());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property yearOfEra()
/*      */   {
/* 1030 */     return new Property(this, getChronology().yearOfEra());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property year()
/*      */   {
/* 1039 */     return new Property(this, getChronology().year());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property weekyear()
/*      */   {
/* 1048 */     return new Property(this, getChronology().weekyear());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property monthOfYear()
/*      */   {
/* 1057 */     return new Property(this, getChronology().monthOfYear());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property weekOfWeekyear()
/*      */   {
/* 1066 */     return new Property(this, getChronology().weekOfWeekyear());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property dayOfYear()
/*      */   {
/* 1075 */     return new Property(this, getChronology().dayOfYear());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property dayOfMonth()
/*      */   {
/* 1084 */     return new Property(this, getChronology().dayOfMonth());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property dayOfWeek()
/*      */   {
/* 1093 */     return new Property(this, getChronology().dayOfWeek());
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
/*      */     private static final long serialVersionUID = 257629620L;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private DateMidnight iInstant;
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
/*      */     Property(DateMidnight paramDateMidnight, DateTimeField paramDateTimeField)
/*      */     {
/* 1143 */       this.iInstant = paramDateMidnight;
/* 1144 */       this.iField = paramDateTimeField;
/*      */     }
/*      */     
/*      */ 
/*      */     private void writeObject(ObjectOutputStream paramObjectOutputStream)
/*      */       throws IOException
/*      */     {
/* 1151 */       paramObjectOutputStream.writeObject(this.iInstant);
/* 1152 */       paramObjectOutputStream.writeObject(this.iField.getType());
/*      */     }
/*      */     
/*      */ 
/*      */     private void readObject(ObjectInputStream paramObjectInputStream)
/*      */       throws IOException, ClassNotFoundException
/*      */     {
/* 1159 */       this.iInstant = ((DateMidnight)paramObjectInputStream.readObject());
/* 1160 */       DateTimeFieldType localDateTimeFieldType = (DateTimeFieldType)paramObjectInputStream.readObject();
/* 1161 */       this.iField = localDateTimeFieldType.getField(this.iInstant.getChronology());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateTimeField getField()
/*      */     {
/* 1171 */       return this.iField;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected long getMillis()
/*      */     {
/* 1180 */       return this.iInstant.getMillis();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected Chronology getChronology()
/*      */     {
/* 1190 */       return this.iInstant.getChronology();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateMidnight getDateMidnight()
/*      */     {
/* 1199 */       return this.iInstant;
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
/*      */     public DateMidnight addToCopy(int paramInt)
/*      */     {
/* 1216 */       return this.iInstant.withMillis(this.iField.add(this.iInstant.getMillis(), paramInt));
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
/*      */     public DateMidnight addToCopy(long paramLong)
/*      */     {
/* 1232 */       return this.iInstant.withMillis(this.iField.add(this.iInstant.getMillis(), paramLong));
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
/*      */     public DateMidnight addWrapFieldToCopy(int paramInt)
/*      */     {
/* 1250 */       return this.iInstant.withMillis(this.iField.addWrapField(this.iInstant.getMillis(), paramInt));
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
/*      */     public DateMidnight setCopy(int paramInt)
/*      */     {
/* 1267 */       return this.iInstant.withMillis(this.iField.set(this.iInstant.getMillis(), paramInt));
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
/*      */     public DateMidnight setCopy(String paramString, Locale paramLocale)
/*      */     {
/* 1284 */       return this.iInstant.withMillis(this.iField.set(this.iInstant.getMillis(), paramString, paramLocale));
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
/*      */     public DateMidnight setCopy(String paramString)
/*      */     {
/* 1300 */       return setCopy(paramString, null);
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
/*      */     public DateMidnight withMaximumValue()
/*      */     {
/* 1320 */       return setCopy(getMaximumValue());
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
/*      */     public DateMidnight withMinimumValue()
/*      */     {
/* 1333 */       return setCopy(getMinimumValue());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateMidnight roundFloorCopy()
/*      */     {
/* 1343 */       return this.iInstant.withMillis(this.iField.roundFloor(this.iInstant.getMillis()));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateMidnight roundCeilingCopy()
/*      */     {
/* 1352 */       return this.iInstant.withMillis(this.iField.roundCeiling(this.iInstant.getMillis()));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateMidnight roundHalfFloorCopy()
/*      */     {
/* 1362 */       return this.iInstant.withMillis(this.iField.roundHalfFloor(this.iInstant.getMillis()));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateMidnight roundHalfCeilingCopy()
/*      */     {
/* 1372 */       return this.iInstant.withMillis(this.iField.roundHalfCeiling(this.iInstant.getMillis()));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateMidnight roundHalfEvenCopy()
/*      */     {
/* 1382 */       return this.iInstant.withMillis(this.iField.roundHalfEven(this.iInstant.getMillis()));
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\DateMidnight.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */