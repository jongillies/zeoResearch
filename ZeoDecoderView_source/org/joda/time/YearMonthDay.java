/*      */ package org.joda.time;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.Locale;
/*      */ import org.joda.time.base.BasePartial;
/*      */ import org.joda.time.chrono.ISOChronology;
/*      */ import org.joda.time.field.AbstractPartialFieldProperty;
/*      */ import org.joda.time.field.FieldUtils;
/*      */ import org.joda.time.format.DateTimeFormatter;
/*      */ import org.joda.time.format.ISODateTimeFormat;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ /**
/*      */  * @deprecated
/*      */  */
/*      */ public final class YearMonthDay
/*      */   extends BasePartial
/*      */   implements ReadablePartial, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 797544782896179L;
/*   71 */   private static final DateTimeFieldType[] FIELD_TYPES = { DateTimeFieldType.year(), DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth() };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final int YEAR = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final int MONTH_OF_YEAR = 1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final int DAY_OF_MONTH = 2;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static YearMonthDay fromCalendarFields(Calendar paramCalendar)
/*      */   {
/*  105 */     if (paramCalendar == null) {
/*  106 */       throw new IllegalArgumentException("The calendar must not be null");
/*      */     }
/*  108 */     return new YearMonthDay(paramCalendar.get(1), paramCalendar.get(2) + 1, paramCalendar.get(5));
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
/*      */   public static YearMonthDay fromDateFields(Date paramDate)
/*      */   {
/*  132 */     if (paramDate == null) {
/*  133 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  135 */     return new YearMonthDay(paramDate.getYear() + 1900, paramDate.getMonth() + 1, paramDate.getDate());
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
/*      */   public YearMonthDay() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public YearMonthDay(DateTimeZone paramDateTimeZone)
/*      */   {
/*  167 */     super(ISOChronology.getInstance(paramDateTimeZone));
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
/*      */   public YearMonthDay(Chronology paramChronology)
/*      */   {
/*  181 */     super(paramChronology);
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
/*      */   public YearMonthDay(long paramLong)
/*      */   {
/*  195 */     super(paramLong);
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
/*      */   public YearMonthDay(long paramLong, Chronology paramChronology)
/*      */   {
/*  210 */     super(paramLong, paramChronology);
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
/*      */   public YearMonthDay(Object paramObject)
/*      */   {
/*  230 */     super(paramObject, null, ISODateTimeFormat.dateOptionalTimeParser());
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
/*      */   public YearMonthDay(Object paramObject, Chronology paramChronology)
/*      */   {
/*  255 */     super(paramObject, DateTimeUtils.getChronology(paramChronology), ISODateTimeFormat.dateOptionalTimeParser());
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
/*      */   public YearMonthDay(int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/*  271 */     this(paramInt1, paramInt2, paramInt3, null);
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
/*      */   public YearMonthDay(int paramInt1, int paramInt2, int paramInt3, Chronology paramChronology)
/*      */   {
/*  287 */     super(new int[] { paramInt1, paramInt2, paramInt3 }, paramChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   YearMonthDay(YearMonthDay paramYearMonthDay, int[] paramArrayOfInt)
/*      */   {
/*  297 */     super(paramYearMonthDay, paramArrayOfInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   YearMonthDay(YearMonthDay paramYearMonthDay, Chronology paramChronology)
/*      */   {
/*  307 */     super(paramYearMonthDay, paramChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int size()
/*      */   {
/*  317 */     return 3;
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
/*      */   protected DateTimeField getField(int paramInt, Chronology paramChronology)
/*      */   {
/*  330 */     switch (paramInt) {
/*      */     case 0: 
/*  332 */       return paramChronology.year();
/*      */     case 1: 
/*  334 */       return paramChronology.monthOfYear();
/*      */     case 2: 
/*  336 */       return paramChronology.dayOfMonth();
/*      */     }
/*  338 */     throw new IndexOutOfBoundsException("Invalid index: " + paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFieldType getFieldType(int paramInt)
/*      */   {
/*  350 */     return FIELD_TYPES[paramInt];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFieldType[] getFieldTypes()
/*      */   {
/*  361 */     return (DateTimeFieldType[])FIELD_TYPES.clone();
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
/*      */   public YearMonthDay withChronologyRetainFields(Chronology paramChronology)
/*      */   {
/*  380 */     paramChronology = DateTimeUtils.getChronology(paramChronology);
/*  381 */     paramChronology = paramChronology.withUTC();
/*  382 */     if (paramChronology == getChronology()) {
/*  383 */       return this;
/*      */     }
/*  385 */     YearMonthDay localYearMonthDay = new YearMonthDay(this, paramChronology);
/*  386 */     paramChronology.validate(localYearMonthDay, getValues());
/*  387 */     return localYearMonthDay;
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
/*      */   public YearMonthDay withField(DateTimeFieldType paramDateTimeFieldType, int paramInt)
/*      */   {
/*  410 */     int i = indexOfSupported(paramDateTimeFieldType);
/*  411 */     if (paramInt == getValue(i)) {
/*  412 */       return this;
/*      */     }
/*  414 */     int[] arrayOfInt = getValues();
/*  415 */     arrayOfInt = getField(i).set(this, i, arrayOfInt, paramInt);
/*  416 */     return new YearMonthDay(this, arrayOfInt);
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
/*      */   public YearMonthDay withFieldAdded(DurationFieldType paramDurationFieldType, int paramInt)
/*      */   {
/*  438 */     int i = indexOfSupported(paramDurationFieldType);
/*  439 */     if (paramInt == 0) {
/*  440 */       return this;
/*      */     }
/*  442 */     int[] arrayOfInt = getValues();
/*  443 */     arrayOfInt = getField(i).add(this, i, arrayOfInt, paramInt);
/*  444 */     return new YearMonthDay(this, arrayOfInt);
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
/*      */   public YearMonthDay withPeriodAdded(ReadablePeriod paramReadablePeriod, int paramInt)
/*      */   {
/*  464 */     if ((paramReadablePeriod == null) || (paramInt == 0)) {
/*  465 */       return this;
/*      */     }
/*  467 */     int[] arrayOfInt = getValues();
/*  468 */     for (int i = 0; i < paramReadablePeriod.size(); i++) {
/*  469 */       DurationFieldType localDurationFieldType = paramReadablePeriod.getFieldType(i);
/*  470 */       int j = indexOf(localDurationFieldType);
/*  471 */       if (j >= 0) {
/*  472 */         arrayOfInt = getField(j).add(this, j, arrayOfInt, FieldUtils.safeMultiply(paramReadablePeriod.getValue(i), paramInt));
/*      */       }
/*      */     }
/*      */     
/*  476 */     return new YearMonthDay(this, arrayOfInt);
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
/*      */   public YearMonthDay plus(ReadablePeriod paramReadablePeriod)
/*      */   {
/*  494 */     return withPeriodAdded(paramReadablePeriod, 1);
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
/*      */   public YearMonthDay plusYears(int paramInt)
/*      */   {
/*  515 */     return withFieldAdded(DurationFieldType.years(), paramInt);
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
/*      */   public YearMonthDay plusMonths(int paramInt)
/*      */   {
/*  535 */     return withFieldAdded(DurationFieldType.months(), paramInt);
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
/*      */   public YearMonthDay plusDays(int paramInt)
/*      */   {
/*  555 */     return withFieldAdded(DurationFieldType.days(), paramInt);
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
/*      */   public YearMonthDay minus(ReadablePeriod paramReadablePeriod)
/*      */   {
/*  573 */     return withPeriodAdded(paramReadablePeriod, -1);
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
/*      */   public YearMonthDay minusYears(int paramInt)
/*      */   {
/*  594 */     return withFieldAdded(DurationFieldType.years(), FieldUtils.safeNegate(paramInt));
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
/*      */   public YearMonthDay minusMonths(int paramInt)
/*      */   {
/*  614 */     return withFieldAdded(DurationFieldType.months(), FieldUtils.safeNegate(paramInt));
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
/*      */   public YearMonthDay minusDays(int paramInt)
/*      */   {
/*  634 */     return withFieldAdded(DurationFieldType.days(), FieldUtils.safeNegate(paramInt));
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
/*      */   public Property property(DateTimeFieldType paramDateTimeFieldType)
/*      */   {
/*  647 */     return new Property(this, indexOfSupported(paramDateTimeFieldType));
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
/*  658 */     return new LocalDate(getYear(), getMonthOfYear(), getDayOfMonth(), getChronology());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime toDateTimeAtMidnight()
/*      */   {
/*  669 */     return toDateTimeAtMidnight(null);
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
/*      */   public DateTime toDateTimeAtMidnight(DateTimeZone paramDateTimeZone)
/*      */   {
/*  683 */     Chronology localChronology = getChronology().withZone(paramDateTimeZone);
/*  684 */     return new DateTime(getYear(), getMonthOfYear(), getDayOfMonth(), 0, 0, 0, 0, localChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTime toDateTimeAtCurrentTime()
/*      */   {
/*  696 */     return toDateTimeAtCurrentTime(null);
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
/*      */   public DateTime toDateTimeAtCurrentTime(DateTimeZone paramDateTimeZone)
/*      */   {
/*  711 */     Chronology localChronology = getChronology().withZone(paramDateTimeZone);
/*  712 */     long l1 = DateTimeUtils.currentTimeMillis();
/*  713 */     long l2 = localChronology.set(this, l1);
/*  714 */     return new DateTime(l2, localChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateMidnight toDateMidnight()
/*      */   {
/*  724 */     return toDateMidnight(null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateMidnight toDateMidnight(DateTimeZone paramDateTimeZone)
/*      */   {
/*  734 */     Chronology localChronology = getChronology().withZone(paramDateTimeZone);
/*  735 */     return new DateMidnight(getYear(), getMonthOfYear(), getDayOfMonth(), localChronology);
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
/*      */   public DateTime toDateTime(TimeOfDay paramTimeOfDay)
/*      */   {
/*  752 */     return toDateTime(paramTimeOfDay, null);
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
/*      */   public DateTime toDateTime(TimeOfDay paramTimeOfDay, DateTimeZone paramDateTimeZone)
/*      */   {
/*  769 */     Chronology localChronology = getChronology().withZone(paramDateTimeZone);
/*  770 */     long l = DateTimeUtils.currentTimeMillis();
/*  771 */     l = localChronology.set(this, l);
/*  772 */     if (paramTimeOfDay != null) {
/*  773 */       l = localChronology.set(paramTimeOfDay, l);
/*      */     }
/*  775 */     return new DateTime(l, localChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Interval toInterval()
/*      */   {
/*  786 */     return toInterval(null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Interval toInterval(DateTimeZone paramDateTimeZone)
/*      */   {
/*  796 */     paramDateTimeZone = DateTimeUtils.getZone(paramDateTimeZone);
/*  797 */     return toDateMidnight(paramDateTimeZone).toInterval();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getYear()
/*      */   {
/*  807 */     return getValue(0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMonthOfYear()
/*      */   {
/*  816 */     return getValue(1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getDayOfMonth()
/*      */   {
/*  825 */     return getValue(2);
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
/*      */   public YearMonthDay withYear(int paramInt)
/*      */   {
/*  842 */     int[] arrayOfInt = getValues();
/*  843 */     arrayOfInt = getChronology().year().set(this, 0, arrayOfInt, paramInt);
/*  844 */     return new YearMonthDay(this, arrayOfInt);
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
/*      */   public YearMonthDay withMonthOfYear(int paramInt)
/*      */   {
/*  860 */     int[] arrayOfInt = getValues();
/*  861 */     arrayOfInt = getChronology().monthOfYear().set(this, 1, arrayOfInt, paramInt);
/*  862 */     return new YearMonthDay(this, arrayOfInt);
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
/*      */   public YearMonthDay withDayOfMonth(int paramInt)
/*      */   {
/*  878 */     int[] arrayOfInt = getValues();
/*  879 */     arrayOfInt = getChronology().dayOfMonth().set(this, 2, arrayOfInt, paramInt);
/*  880 */     return new YearMonthDay(this, arrayOfInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property year()
/*      */   {
/*  890 */     return new Property(this, 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property monthOfYear()
/*      */   {
/*  899 */     return new Property(this, 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property dayOfMonth()
/*      */   {
/*  908 */     return new Property(this, 2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/*  918 */     return ISODateTimeFormat.yearMonthDay().print(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static class Property
/*      */     extends AbstractPartialFieldProperty
/*      */     implements Serializable
/*      */   {
/*      */     private static final long serialVersionUID = 5727734012190224363L;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     private final YearMonthDay iYearMonthDay;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     private final int iFieldIndex;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     Property(YearMonthDay paramYearMonthDay, int paramInt)
/*      */     {
/*  949 */       this.iYearMonthDay = paramYearMonthDay;
/*  950 */       this.iFieldIndex = paramInt;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateTimeField getField()
/*      */     {
/*  959 */       return this.iYearMonthDay.getField(this.iFieldIndex);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected ReadablePartial getReadablePartial()
/*      */     {
/*  968 */       return this.iYearMonthDay;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public YearMonthDay getYearMonthDay()
/*      */     {
/*  977 */       return this.iYearMonthDay;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public int get()
/*      */     {
/*  986 */       return this.iYearMonthDay.getValue(this.iFieldIndex);
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
/*      */ 
/*      */ 
/*      */     public YearMonthDay addToCopy(int paramInt)
/*      */     {
/* 1008 */       int[] arrayOfInt = this.iYearMonthDay.getValues();
/* 1009 */       arrayOfInt = getField().add(this.iYearMonthDay, this.iFieldIndex, arrayOfInt, paramInt);
/* 1010 */       return new YearMonthDay(this.iYearMonthDay, arrayOfInt);
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
/*      */ 
/*      */ 
/*      */     public YearMonthDay addWrapFieldToCopy(int paramInt)
/*      */     {
/* 1032 */       int[] arrayOfInt = this.iYearMonthDay.getValues();
/* 1033 */       arrayOfInt = getField().addWrapField(this.iYearMonthDay, this.iFieldIndex, arrayOfInt, paramInt);
/* 1034 */       return new YearMonthDay(this.iYearMonthDay, arrayOfInt);
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
/*      */     public YearMonthDay setCopy(int paramInt)
/*      */     {
/* 1049 */       int[] arrayOfInt = this.iYearMonthDay.getValues();
/* 1050 */       arrayOfInt = getField().set(this.iYearMonthDay, this.iFieldIndex, arrayOfInt, paramInt);
/* 1051 */       return new YearMonthDay(this.iYearMonthDay, arrayOfInt);
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
/*      */     public YearMonthDay setCopy(String paramString, Locale paramLocale)
/*      */     {
/* 1066 */       int[] arrayOfInt = this.iYearMonthDay.getValues();
/* 1067 */       arrayOfInt = getField().set(this.iYearMonthDay, this.iFieldIndex, arrayOfInt, paramString, paramLocale);
/* 1068 */       return new YearMonthDay(this.iYearMonthDay, arrayOfInt);
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
/*      */     public YearMonthDay setCopy(String paramString)
/*      */     {
/* 1082 */       return setCopy(paramString, null);
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
/*      */     public YearMonthDay withMaximumValue()
/*      */     {
/* 1102 */       return setCopy(getMaximumValue());
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
/*      */     public YearMonthDay withMinimumValue()
/*      */     {
/* 1115 */       return setCopy(getMinimumValue());
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\YearMonthDay.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */