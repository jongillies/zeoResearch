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
/*      */ 
/*      */ 
/*      */ 
/*      */ /**
/*      */  * @deprecated
/*      */  */
/*      */ public final class TimeOfDay
/*      */   extends BasePartial
/*      */   implements ReadablePartial, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 3633353405803318660L;
/*   74 */   private static final DateTimeFieldType[] FIELD_TYPES = { DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(), DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond() };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   82 */   public static final TimeOfDay MIDNIGHT = new TimeOfDay(0, 0, 0, 0);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final int HOUR_OF_DAY = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final int MINUTE_OF_HOUR = 1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final int SECOND_OF_MINUTE = 2;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final int MILLIS_OF_SECOND = 3;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static TimeOfDay fromCalendarFields(Calendar paramCalendar)
/*      */   {
/*  114 */     if (paramCalendar == null) {
/*  115 */       throw new IllegalArgumentException("The calendar must not be null");
/*      */     }
/*  117 */     return new TimeOfDay(paramCalendar.get(11), paramCalendar.get(12), paramCalendar.get(13), paramCalendar.get(14));
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
/*      */   public static TimeOfDay fromDateFields(Date paramDate)
/*      */   {
/*  144 */     if (paramDate == null) {
/*  145 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  147 */     return new TimeOfDay(paramDate.getHours(), paramDate.getMinutes(), paramDate.getSeconds(), ((int)(paramDate.getTime() % 1000L) + 1000) % 1000);
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
/*      */   public static TimeOfDay fromMillisOfDay(long paramLong)
/*      */   {
/*  167 */     return fromMillisOfDay(paramLong, null);
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
/*      */   public static TimeOfDay fromMillisOfDay(long paramLong, Chronology paramChronology)
/*      */   {
/*  182 */     paramChronology = DateTimeUtils.getChronology(paramChronology);
/*  183 */     paramChronology = paramChronology.withUTC();
/*  184 */     return new TimeOfDay(paramLong, paramChronology);
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
/*      */   public TimeOfDay() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TimeOfDay(DateTimeZone paramDateTimeZone)
/*      */   {
/*  213 */     super(ISOChronology.getInstance(paramDateTimeZone));
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
/*      */   public TimeOfDay(Chronology paramChronology)
/*      */   {
/*  227 */     super(paramChronology);
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
/*      */   public TimeOfDay(long paramLong)
/*      */   {
/*  241 */     super(paramLong);
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
/*      */   public TimeOfDay(long paramLong, Chronology paramChronology)
/*      */   {
/*  256 */     super(paramLong, paramChronology);
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
/*      */   public TimeOfDay(Object paramObject)
/*      */   {
/*  276 */     super(paramObject, null, ISODateTimeFormat.timeParser());
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
/*      */   public TimeOfDay(Object paramObject, Chronology paramChronology)
/*      */   {
/*  301 */     super(paramObject, DateTimeUtils.getChronology(paramChronology), ISODateTimeFormat.timeParser());
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
/*      */   public TimeOfDay(int paramInt1, int paramInt2)
/*      */   {
/*  316 */     this(paramInt1, paramInt2, 0, 0, null);
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
/*      */   public TimeOfDay(int paramInt1, int paramInt2, Chronology paramChronology)
/*      */   {
/*  331 */     this(paramInt1, paramInt2, 0, 0, paramChronology);
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
/*      */   public TimeOfDay(int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/*  347 */     this(paramInt1, paramInt2, paramInt3, 0, null);
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
/*      */   public TimeOfDay(int paramInt1, int paramInt2, int paramInt3, Chronology paramChronology)
/*      */   {
/*  363 */     this(paramInt1, paramInt2, paramInt3, 0, paramChronology);
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
/*      */   public TimeOfDay(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/*  380 */     this(paramInt1, paramInt2, paramInt3, paramInt4, null);
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
/*      */   public TimeOfDay(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Chronology paramChronology)
/*      */   {
/*  398 */     super(new int[] { paramInt1, paramInt2, paramInt3, paramInt4 }, paramChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   TimeOfDay(TimeOfDay paramTimeOfDay, int[] paramArrayOfInt)
/*      */   {
/*  408 */     super(paramTimeOfDay, paramArrayOfInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   TimeOfDay(TimeOfDay paramTimeOfDay, Chronology paramChronology)
/*      */   {
/*  418 */     super(paramTimeOfDay, paramChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int size()
/*      */   {
/*  428 */     return 4;
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
/*  441 */     switch (paramInt) {
/*      */     case 0: 
/*  443 */       return paramChronology.hourOfDay();
/*      */     case 1: 
/*  445 */       return paramChronology.minuteOfHour();
/*      */     case 2: 
/*  447 */       return paramChronology.secondOfMinute();
/*      */     case 3: 
/*  449 */       return paramChronology.millisOfSecond();
/*      */     }
/*  451 */     throw new IndexOutOfBoundsException("Invalid index: " + paramInt);
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
/*  463 */     return FIELD_TYPES[paramInt];
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
/*  474 */     return (DateTimeFieldType[])FIELD_TYPES.clone();
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
/*      */   public TimeOfDay withChronologyRetainFields(Chronology paramChronology)
/*      */   {
/*  493 */     paramChronology = DateTimeUtils.getChronology(paramChronology);
/*  494 */     paramChronology = paramChronology.withUTC();
/*  495 */     if (paramChronology == getChronology()) {
/*  496 */       return this;
/*      */     }
/*  498 */     TimeOfDay localTimeOfDay = new TimeOfDay(this, paramChronology);
/*  499 */     paramChronology.validate(localTimeOfDay, getValues());
/*  500 */     return localTimeOfDay;
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
/*      */   public TimeOfDay withField(DateTimeFieldType paramDateTimeFieldType, int paramInt)
/*      */   {
/*  523 */     int i = indexOfSupported(paramDateTimeFieldType);
/*  524 */     if (paramInt == getValue(i)) {
/*  525 */       return this;
/*      */     }
/*  527 */     int[] arrayOfInt = getValues();
/*  528 */     arrayOfInt = getField(i).set(this, i, arrayOfInt, paramInt);
/*  529 */     return new TimeOfDay(this, arrayOfInt);
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
/*      */   public TimeOfDay withFieldAdded(DurationFieldType paramDurationFieldType, int paramInt)
/*      */   {
/*  552 */     int i = indexOfSupported(paramDurationFieldType);
/*  553 */     if (paramInt == 0) {
/*  554 */       return this;
/*      */     }
/*  556 */     int[] arrayOfInt = getValues();
/*  557 */     arrayOfInt = getField(i).addWrapPartial(this, i, arrayOfInt, paramInt);
/*  558 */     return new TimeOfDay(this, arrayOfInt);
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
/*      */   public TimeOfDay withPeriodAdded(ReadablePeriod paramReadablePeriod, int paramInt)
/*      */   {
/*  579 */     if ((paramReadablePeriod == null) || (paramInt == 0)) {
/*  580 */       return this;
/*      */     }
/*  582 */     int[] arrayOfInt = getValues();
/*  583 */     for (int i = 0; i < paramReadablePeriod.size(); i++) {
/*  584 */       DurationFieldType localDurationFieldType = paramReadablePeriod.getFieldType(i);
/*  585 */       int j = indexOf(localDurationFieldType);
/*  586 */       if (j >= 0) {
/*  587 */         arrayOfInt = getField(j).addWrapPartial(this, j, arrayOfInt, FieldUtils.safeMultiply(paramReadablePeriod.getValue(i), paramInt));
/*      */       }
/*      */     }
/*      */     
/*  591 */     return new TimeOfDay(this, arrayOfInt);
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
/*      */   public TimeOfDay plus(ReadablePeriod paramReadablePeriod)
/*      */   {
/*  610 */     return withPeriodAdded(paramReadablePeriod, 1);
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
/*      */   public TimeOfDay plusHours(int paramInt)
/*      */   {
/*  631 */     return withFieldAdded(DurationFieldType.hours(), paramInt);
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
/*      */   public TimeOfDay plusMinutes(int paramInt)
/*      */   {
/*  651 */     return withFieldAdded(DurationFieldType.minutes(), paramInt);
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
/*      */   public TimeOfDay plusSeconds(int paramInt)
/*      */   {
/*  671 */     return withFieldAdded(DurationFieldType.seconds(), paramInt);
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
/*      */   public TimeOfDay plusMillis(int paramInt)
/*      */   {
/*  691 */     return withFieldAdded(DurationFieldType.millis(), paramInt);
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
/*      */   public TimeOfDay minus(ReadablePeriod paramReadablePeriod)
/*      */   {
/*  710 */     return withPeriodAdded(paramReadablePeriod, -1);
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
/*      */   public TimeOfDay minusHours(int paramInt)
/*      */   {
/*  731 */     return withFieldAdded(DurationFieldType.hours(), FieldUtils.safeNegate(paramInt));
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
/*      */   public TimeOfDay minusMinutes(int paramInt)
/*      */   {
/*  751 */     return withFieldAdded(DurationFieldType.minutes(), FieldUtils.safeNegate(paramInt));
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
/*      */   public TimeOfDay minusSeconds(int paramInt)
/*      */   {
/*  771 */     return withFieldAdded(DurationFieldType.seconds(), FieldUtils.safeNegate(paramInt));
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
/*      */   public TimeOfDay minusMillis(int paramInt)
/*      */   {
/*  791 */     return withFieldAdded(DurationFieldType.millis(), FieldUtils.safeNegate(paramInt));
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
/*  804 */     return new Property(this, indexOfSupported(paramDateTimeFieldType));
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
/*  815 */     return new LocalTime(getHourOfDay(), getMinuteOfHour(), getSecondOfMinute(), getMillisOfSecond(), getChronology());
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
/*      */   public DateTime toDateTimeToday()
/*      */   {
/*  828 */     return toDateTimeToday(null);
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
/*      */   public DateTime toDateTimeToday(DateTimeZone paramDateTimeZone)
/*      */   {
/*  843 */     Chronology localChronology = getChronology().withZone(paramDateTimeZone);
/*  844 */     long l1 = DateTimeUtils.currentTimeMillis();
/*  845 */     long l2 = localChronology.set(this, l1);
/*  846 */     return new DateTime(l2, localChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getHourOfDay()
/*      */   {
/*  856 */     return getValue(0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMinuteOfHour()
/*      */   {
/*  865 */     return getValue(1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getSecondOfMinute()
/*      */   {
/*  874 */     return getValue(2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMillisOfSecond()
/*      */   {
/*  883 */     return getValue(3);
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
/*      */   public TimeOfDay withHourOfDay(int paramInt)
/*      */   {
/*  900 */     int[] arrayOfInt = getValues();
/*  901 */     arrayOfInt = getChronology().hourOfDay().set(this, 0, arrayOfInt, paramInt);
/*  902 */     return new TimeOfDay(this, arrayOfInt);
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
/*      */   public TimeOfDay withMinuteOfHour(int paramInt)
/*      */   {
/*  918 */     int[] arrayOfInt = getValues();
/*  919 */     arrayOfInt = getChronology().minuteOfHour().set(this, 1, arrayOfInt, paramInt);
/*  920 */     return new TimeOfDay(this, arrayOfInt);
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
/*      */   public TimeOfDay withSecondOfMinute(int paramInt)
/*      */   {
/*  936 */     int[] arrayOfInt = getValues();
/*  937 */     arrayOfInt = getChronology().secondOfMinute().set(this, 2, arrayOfInt, paramInt);
/*  938 */     return new TimeOfDay(this, arrayOfInt);
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
/*      */   public TimeOfDay withMillisOfSecond(int paramInt)
/*      */   {
/*  954 */     int[] arrayOfInt = getValues();
/*  955 */     arrayOfInt = getChronology().millisOfSecond().set(this, 3, arrayOfInt, paramInt);
/*  956 */     return new TimeOfDay(this, arrayOfInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property hourOfDay()
/*      */   {
/*  966 */     return new Property(this, 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property minuteOfHour()
/*      */   {
/*  975 */     return new Property(this, 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property secondOfMinute()
/*      */   {
/*  984 */     return new Property(this, 2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property millisOfSecond()
/*      */   {
/*  993 */     return new Property(this, 3);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/* 1003 */     return ISODateTimeFormat.tTime().print(this);
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
/*      */     private static final long serialVersionUID = 5598459141741063833L;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     private final TimeOfDay iTimeOfDay;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     private final int iFieldIndex;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     Property(TimeOfDay paramTimeOfDay, int paramInt)
/*      */     {
/* 1034 */       this.iTimeOfDay = paramTimeOfDay;
/* 1035 */       this.iFieldIndex = paramInt;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateTimeField getField()
/*      */     {
/* 1044 */       return this.iTimeOfDay.getField(this.iFieldIndex);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected ReadablePartial getReadablePartial()
/*      */     {
/* 1053 */       return this.iTimeOfDay;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public TimeOfDay getTimeOfDay()
/*      */     {
/* 1062 */       return this.iTimeOfDay;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public int get()
/*      */     {
/* 1071 */       return this.iTimeOfDay.getValue(this.iFieldIndex);
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
/*      */ 
/*      */ 
/*      */     public TimeOfDay addToCopy(int paramInt)
/*      */     {
/* 1095 */       int[] arrayOfInt = this.iTimeOfDay.getValues();
/* 1096 */       arrayOfInt = getField().addWrapPartial(this.iTimeOfDay, this.iFieldIndex, arrayOfInt, paramInt);
/* 1097 */       return new TimeOfDay(this.iTimeOfDay, arrayOfInt);
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
/*      */ 
/*      */ 
/*      */     public TimeOfDay addNoWrapToCopy(int paramInt)
/*      */     {
/* 1121 */       int[] arrayOfInt = this.iTimeOfDay.getValues();
/* 1122 */       arrayOfInt = getField().add(this.iTimeOfDay, this.iFieldIndex, arrayOfInt, paramInt);
/* 1123 */       return new TimeOfDay(this.iTimeOfDay, arrayOfInt);
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
/*      */     public TimeOfDay addWrapFieldToCopy(int paramInt)
/*      */     {
/* 1145 */       int[] arrayOfInt = this.iTimeOfDay.getValues();
/* 1146 */       arrayOfInt = getField().addWrapField(this.iTimeOfDay, this.iFieldIndex, arrayOfInt, paramInt);
/* 1147 */       return new TimeOfDay(this.iTimeOfDay, arrayOfInt);
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
/*      */     public TimeOfDay setCopy(int paramInt)
/*      */     {
/* 1162 */       int[] arrayOfInt = this.iTimeOfDay.getValues();
/* 1163 */       arrayOfInt = getField().set(this.iTimeOfDay, this.iFieldIndex, arrayOfInt, paramInt);
/* 1164 */       return new TimeOfDay(this.iTimeOfDay, arrayOfInt);
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
/*      */     public TimeOfDay setCopy(String paramString, Locale paramLocale)
/*      */     {
/* 1179 */       int[] arrayOfInt = this.iTimeOfDay.getValues();
/* 1180 */       arrayOfInt = getField().set(this.iTimeOfDay, this.iFieldIndex, arrayOfInt, paramString, paramLocale);
/* 1181 */       return new TimeOfDay(this.iTimeOfDay, arrayOfInt);
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
/*      */     public TimeOfDay setCopy(String paramString)
/*      */     {
/* 1195 */       return setCopy(paramString, null);
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
/*      */     public TimeOfDay withMaximumValue()
/*      */     {
/* 1209 */       return setCopy(getMaximumValue());
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
/*      */     public TimeOfDay withMinimumValue()
/*      */     {
/* 1222 */       return setCopy(getMinimumValue());
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\TimeOfDay.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */