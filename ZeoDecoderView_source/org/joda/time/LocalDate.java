/*      */ package org.joda.time;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.HashSet;
/*      */ import java.util.Locale;
/*      */ import java.util.Set;
/*      */ import org.joda.time.base.BaseLocal;
/*      */ import org.joda.time.chrono.ISOChronology;
/*      */ import org.joda.time.convert.ConverterManager;
/*      */ import org.joda.time.convert.PartialConverter;
/*      */ import org.joda.time.field.AbstractReadableInstantFieldProperty;
/*      */ import org.joda.time.field.FieldUtils;
/*      */ import org.joda.time.format.DateTimeFormat;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class LocalDate
/*      */   extends BaseLocal
/*      */   implements ReadablePartial, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = -8775358157899L;
/*      */   private static final int YEAR = 0;
/*      */   private static final int MONTH_OF_YEAR = 1;
/*      */   private static final int DAY_OF_MONTH = 2;
/*   91 */   private static final Set DATE_DURATION_TYPES = new HashSet();
/*      */   
/*   93 */   static { DATE_DURATION_TYPES.add(DurationFieldType.days());
/*   94 */     DATE_DURATION_TYPES.add(DurationFieldType.weeks());
/*   95 */     DATE_DURATION_TYPES.add(DurationFieldType.months());
/*   96 */     DATE_DURATION_TYPES.add(DurationFieldType.weekyears());
/*   97 */     DATE_DURATION_TYPES.add(DurationFieldType.years());
/*   98 */     DATE_DURATION_TYPES.add(DurationFieldType.centuries());
/*      */     
/*  100 */     DATE_DURATION_TYPES.add(DurationFieldType.eras());
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
/*      */   private long iLocalMillis;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Chronology iChronology;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static LocalDate fromCalendarFields(Calendar paramCalendar)
/*      */   {
/*  133 */     if (paramCalendar == null) {
/*  134 */       throw new IllegalArgumentException("The calendar must not be null");
/*      */     }
/*  136 */     return new LocalDate(paramCalendar.get(1), paramCalendar.get(2) + 1, paramCalendar.get(5));
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
/*      */   public static LocalDate fromDateFields(Date paramDate)
/*      */   {
/*  164 */     if (paramDate == null) {
/*  165 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  167 */     return new LocalDate(paramDate.getYear() + 1900, paramDate.getMonth() + 1, paramDate.getDate());
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
/*      */   public LocalDate()
/*      */   {
/*  182 */     this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance());
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
/*      */   public LocalDate(DateTimeZone paramDateTimeZone)
/*      */   {
/*  195 */     this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance(paramDateTimeZone));
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
/*      */   public LocalDate(Chronology paramChronology)
/*      */   {
/*  208 */     this(DateTimeUtils.currentTimeMillis(), paramChronology);
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
/*      */   public LocalDate(long paramLong)
/*      */   {
/*  221 */     this(paramLong, ISOChronology.getInstance());
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
/*      */   public LocalDate(long paramLong, DateTimeZone paramDateTimeZone)
/*      */   {
/*  235 */     this(paramLong, ISOChronology.getInstance(paramDateTimeZone));
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
/*      */   public LocalDate(long paramLong, Chronology paramChronology)
/*      */   {
/*  249 */     paramChronology = DateTimeUtils.getChronology(paramChronology);
/*      */     
/*  251 */     long l = paramChronology.getZone().getMillisKeepLocal(DateTimeZone.UTC, paramLong);
/*  252 */     paramChronology = paramChronology.withUTC();
/*  253 */     this.iLocalMillis = paramChronology.dayOfMonth().roundFloor(l);
/*  254 */     this.iChronology = paramChronology;
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
/*      */   public LocalDate(Object paramObject)
/*      */   {
/*  276 */     this(paramObject, (Chronology)null);
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
/*      */   public LocalDate(Object paramObject, DateTimeZone paramDateTimeZone)
/*      */   {
/*  298 */     PartialConverter localPartialConverter = ConverterManager.getInstance().getPartialConverter(paramObject);
/*  299 */     Chronology localChronology = localPartialConverter.getChronology(paramObject, paramDateTimeZone);
/*  300 */     localChronology = DateTimeUtils.getChronology(localChronology);
/*  301 */     this.iChronology = localChronology.withUTC();
/*  302 */     int[] arrayOfInt = localPartialConverter.getPartialValues(this, paramObject, localChronology, ISODateTimeFormat.localDateParser());
/*  303 */     this.iLocalMillis = this.iChronology.getDateTimeMillis(arrayOfInt[0], arrayOfInt[1], arrayOfInt[2], 0);
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
/*      */   public LocalDate(Object paramObject, Chronology paramChronology)
/*      */   {
/*  324 */     PartialConverter localPartialConverter = ConverterManager.getInstance().getPartialConverter(paramObject);
/*  325 */     paramChronology = localPartialConverter.getChronology(paramObject, paramChronology);
/*  326 */     paramChronology = DateTimeUtils.getChronology(paramChronology);
/*  327 */     this.iChronology = paramChronology.withUTC();
/*  328 */     int[] arrayOfInt = localPartialConverter.getPartialValues(this, paramObject, paramChronology, ISODateTimeFormat.localDateParser());
/*  329 */     this.iLocalMillis = this.iChronology.getDateTimeMillis(arrayOfInt[0], arrayOfInt[1], arrayOfInt[2], 0);
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
/*      */   public LocalDate(int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/*  345 */     this(paramInt1, paramInt2, paramInt3, ISOChronology.getInstanceUTC());
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
/*      */   public LocalDate(int paramInt1, int paramInt2, int paramInt3, Chronology paramChronology)
/*      */   {
/*  365 */     paramChronology = DateTimeUtils.getChronology(paramChronology).withUTC();
/*  366 */     long l = paramChronology.getDateTimeMillis(paramInt1, paramInt2, paramInt3, 0);
/*  367 */     this.iChronology = paramChronology;
/*  368 */     this.iLocalMillis = l;
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
/*      */   public int size()
/*      */   {
/*  381 */     return 3;
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
/*  394 */     switch (paramInt) {
/*      */     case 0: 
/*  396 */       return paramChronology.year();
/*      */     case 1: 
/*  398 */       return paramChronology.monthOfYear();
/*      */     case 2: 
/*  400 */       return paramChronology.dayOfMonth();
/*      */     }
/*  402 */     throw new IndexOutOfBoundsException("Invalid index: " + paramInt);
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
/*      */   public int getValue(int paramInt)
/*      */   {
/*  419 */     switch (paramInt) {
/*      */     case 0: 
/*  421 */       return getChronology().year().get(getLocalMillis());
/*      */     case 1: 
/*  423 */       return getChronology().monthOfYear().get(getLocalMillis());
/*      */     case 2: 
/*  425 */       return getChronology().dayOfMonth().get(getLocalMillis());
/*      */     }
/*  427 */     throw new IndexOutOfBoundsException("Invalid index: " + paramInt);
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
/*      */   public int get(DateTimeFieldType paramDateTimeFieldType)
/*      */   {
/*  447 */     if (paramDateTimeFieldType == null) {
/*  448 */       throw new IllegalArgumentException("The DateTimeFieldType must not be null");
/*      */     }
/*  450 */     if (!isSupported(paramDateTimeFieldType)) {
/*  451 */       throw new IllegalArgumentException("Field '" + paramDateTimeFieldType + "' is not supported");
/*      */     }
/*  453 */     return paramDateTimeFieldType.getField(getChronology()).get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isSupported(DateTimeFieldType paramDateTimeFieldType)
/*      */   {
/*  465 */     if (paramDateTimeFieldType == null) {
/*  466 */       return false;
/*      */     }
/*  468 */     DurationFieldType localDurationFieldType = paramDateTimeFieldType.getDurationType();
/*  469 */     if ((DATE_DURATION_TYPES.contains(localDurationFieldType)) || (localDurationFieldType.getField(getChronology()).getUnitMillis() >= getChronology().days().getUnitMillis()))
/*      */     {
/*      */ 
/*  472 */       return paramDateTimeFieldType.getField(getChronology()).isSupported();
/*      */     }
/*  474 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isSupported(DurationFieldType paramDurationFieldType)
/*      */   {
/*  485 */     if (paramDurationFieldType == null) {
/*  486 */       return false;
/*      */     }
/*  488 */     DurationField localDurationField = paramDurationFieldType.getField(getChronology());
/*  489 */     if ((DATE_DURATION_TYPES.contains(paramDurationFieldType)) || (localDurationField.getUnitMillis() >= getChronology().days().getUnitMillis()))
/*      */     {
/*  491 */       return localDurationField.isSupported();
/*      */     }
/*  493 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected long getLocalMillis()
/*      */   {
/*  505 */     return this.iLocalMillis;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Chronology getChronology()
/*      */   {
/*  514 */     return this.iChronology;
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
/*      */   public boolean equals(Object paramObject)
/*      */   {
/*  527 */     if (this == paramObject) {
/*  528 */       return true;
/*      */     }
/*  530 */     if ((paramObject instanceof LocalDate)) {
/*  531 */       LocalDate localLocalDate = (LocalDate)paramObject;
/*  532 */       if (this.iChronology.equals(localLocalDate.iChronology)) {
/*  533 */         return this.iLocalMillis == localLocalDate.iLocalMillis;
/*      */       }
/*      */     }
/*  536 */     return super.equals(paramObject);
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
/*      */   public int compareTo(Object paramObject)
/*      */   {
/*  565 */     if (this == paramObject) {
/*  566 */       return 0;
/*      */     }
/*  568 */     if ((paramObject instanceof LocalDate)) {
/*  569 */       LocalDate localLocalDate = (LocalDate)paramObject;
/*  570 */       if (this.iChronology.equals(localLocalDate.iChronology)) {
/*  571 */         return this.iLocalMillis == localLocalDate.iLocalMillis ? 0 : this.iLocalMillis < localLocalDate.iLocalMillis ? -1 : 1;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  576 */     return super.compareTo(paramObject);
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
/*      */   public DateTime toDateTimeAtStartOfDay()
/*      */   {
/*  595 */     return toDateTimeAtStartOfDay(null);
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
/*      */   public DateTime toDateTimeAtStartOfDay(DateTimeZone paramDateTimeZone)
/*      */   {
/*  617 */     paramDateTimeZone = DateTimeUtils.getZone(paramDateTimeZone);
/*  618 */     Chronology localChronology = getChronology().withZone(paramDateTimeZone);
/*  619 */     long l1 = getLocalMillis() + 21600000L;
/*  620 */     long l2 = paramDateTimeZone.convertLocalToUTC(l1, false);
/*  621 */     l2 = localChronology.dayOfMonth().roundFloor(l2);
/*  622 */     return new DateTime(l2, localChronology);
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public DateTime toDateTimeAtMidnight()
/*      */   {
/*  641 */     return toDateTimeAtMidnight(null);
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public DateTime toDateTimeAtMidnight(DateTimeZone paramDateTimeZone)
/*      */   {
/*  663 */     paramDateTimeZone = DateTimeUtils.getZone(paramDateTimeZone);
/*  664 */     Chronology localChronology = getChronology().withZone(paramDateTimeZone);
/*  665 */     return new DateTime(getYear(), getMonthOfYear(), getDayOfMonth(), 0, 0, 0, 0, localChronology);
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
/*      */   public DateTime toDateTimeAtCurrentTime()
/*      */   {
/*  682 */     return toDateTimeAtCurrentTime(null);
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
/*      */   public DateTime toDateTimeAtCurrentTime(DateTimeZone paramDateTimeZone)
/*      */   {
/*  702 */     paramDateTimeZone = DateTimeUtils.getZone(paramDateTimeZone);
/*  703 */     Chronology localChronology = getChronology().withZone(paramDateTimeZone);
/*  704 */     long l1 = DateTimeUtils.currentTimeMillis();
/*  705 */     long l2 = localChronology.set(this, l1);
/*  706 */     return new DateTime(l2, localChronology);
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
/*      */   public DateMidnight toDateMidnight()
/*      */   {
/*  727 */     return toDateMidnight(null);
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
/*      */   public DateMidnight toDateMidnight(DateTimeZone paramDateTimeZone)
/*      */   {
/*  748 */     paramDateTimeZone = DateTimeUtils.getZone(paramDateTimeZone);
/*  749 */     Chronology localChronology = getChronology().withZone(paramDateTimeZone);
/*  750 */     return new DateMidnight(getYear(), getMonthOfYear(), getDayOfMonth(), localChronology);
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
/*      */   public LocalDateTime toLocalDateTime(LocalTime paramLocalTime)
/*      */   {
/*  771 */     if (paramLocalTime == null) {
/*  772 */       throw new IllegalArgumentException("The time must not be null");
/*      */     }
/*  774 */     if (getChronology() != paramLocalTime.getChronology()) {
/*  775 */       throw new IllegalArgumentException("The chronology of the time does not match");
/*      */     }
/*  777 */     long l = getLocalMillis() + paramLocalTime.getLocalMillis();
/*  778 */     return new LocalDateTime(l, getChronology());
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
/*      */   public DateTime toDateTime(LocalTime paramLocalTime)
/*      */   {
/*  800 */     return toDateTime(paramLocalTime, null);
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
/*      */   public DateTime toDateTime(LocalTime paramLocalTime, DateTimeZone paramDateTimeZone)
/*      */   {
/*  822 */     if ((paramLocalTime != null) && (getChronology() != paramLocalTime.getChronology())) {
/*  823 */       throw new IllegalArgumentException("The chronology of the time does not match");
/*      */     }
/*  825 */     Chronology localChronology = getChronology().withZone(paramDateTimeZone);
/*  826 */     long l = DateTimeUtils.currentTimeMillis();
/*  827 */     l = localChronology.set(this, l);
/*  828 */     if (paramLocalTime != null) {
/*  829 */       l = localChronology.set(paramLocalTime, l);
/*      */     }
/*  831 */     return new DateTime(l, localChronology);
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
/*      */   public Interval toInterval()
/*      */   {
/*  847 */     return toInterval(null);
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
/*      */   public Interval toInterval(DateTimeZone paramDateTimeZone)
/*      */   {
/*  862 */     paramDateTimeZone = DateTimeUtils.getZone(paramDateTimeZone);
/*  863 */     DateTime localDateTime1 = toDateTimeAtStartOfDay(paramDateTimeZone);
/*  864 */     DateTime localDateTime2 = plusDays(1).toDateTimeAtStartOfDay(paramDateTimeZone);
/*  865 */     return new Interval(localDateTime1, localDateTime2);
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
/*      */   LocalDate withLocalMillis(long paramLong)
/*      */   {
/*  880 */     paramLong = this.iChronology.dayOfMonth().roundFloor(paramLong);
/*  881 */     return paramLong == getLocalMillis() ? this : new LocalDate(paramLong, getChronology());
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
/*      */   public LocalDate withFields(ReadablePartial paramReadablePartial)
/*      */   {
/*  899 */     if (paramReadablePartial == null) {
/*  900 */       return this;
/*      */     }
/*  902 */     return withLocalMillis(getChronology().set(paramReadablePartial, getLocalMillis()));
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
/*      */   public LocalDate withField(DateTimeFieldType paramDateTimeFieldType, int paramInt)
/*      */   {
/*  924 */     if (paramDateTimeFieldType == null) {
/*  925 */       throw new IllegalArgumentException("Field must not be null");
/*      */     }
/*  927 */     if (!isSupported(paramDateTimeFieldType)) {
/*  928 */       throw new IllegalArgumentException("Field '" + paramDateTimeFieldType + "' is not supported");
/*      */     }
/*  930 */     long l = paramDateTimeFieldType.getField(getChronology()).set(getLocalMillis(), paramInt);
/*  931 */     return withLocalMillis(l);
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
/*      */   public LocalDate withFieldAdded(DurationFieldType paramDurationFieldType, int paramInt)
/*      */   {
/*  953 */     if (paramDurationFieldType == null) {
/*  954 */       throw new IllegalArgumentException("Field must not be null");
/*      */     }
/*  956 */     if (!isSupported(paramDurationFieldType)) {
/*  957 */       throw new IllegalArgumentException("Field '" + paramDurationFieldType + "' is not supported");
/*      */     }
/*  959 */     if (paramInt == 0) {
/*  960 */       return this;
/*      */     }
/*  962 */     long l = paramDurationFieldType.getField(getChronology()).add(getLocalMillis(), paramInt);
/*  963 */     return withLocalMillis(l);
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
/*      */   public LocalDate withPeriodAdded(ReadablePeriod paramReadablePeriod, int paramInt)
/*      */   {
/*  986 */     if ((paramReadablePeriod == null) || (paramInt == 0)) {
/*  987 */       return this;
/*      */     }
/*  989 */     long l1 = getLocalMillis();
/*  990 */     Chronology localChronology = getChronology();
/*  991 */     for (int i = 0; i < paramReadablePeriod.size(); i++) {
/*  992 */       long l2 = FieldUtils.safeMultiply(paramReadablePeriod.getValue(i), paramInt);
/*  993 */       DurationFieldType localDurationFieldType = paramReadablePeriod.getFieldType(i);
/*  994 */       if (isSupported(localDurationFieldType)) {
/*  995 */         l1 = localDurationFieldType.getField(localChronology).add(l1, l2);
/*      */       }
/*      */     }
/*  998 */     return withLocalMillis(l1);
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
/*      */   public LocalDate plus(ReadablePeriod paramReadablePeriod)
/*      */   {
/* 1019 */     return withPeriodAdded(paramReadablePeriod, 1);
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
/*      */   public LocalDate plusYears(int paramInt)
/*      */   {
/* 1039 */     if (paramInt == 0) {
/* 1040 */       return this;
/*      */     }
/* 1042 */     long l = getChronology().years().add(getLocalMillis(), paramInt);
/* 1043 */     return withLocalMillis(l);
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
/*      */   public LocalDate plusMonths(int paramInt)
/*      */   {
/* 1062 */     if (paramInt == 0) {
/* 1063 */       return this;
/*      */     }
/* 1065 */     long l = getChronology().months().add(getLocalMillis(), paramInt);
/* 1066 */     return withLocalMillis(l);
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
/*      */   public LocalDate plusWeeks(int paramInt)
/*      */   {
/* 1085 */     if (paramInt == 0) {
/* 1086 */       return this;
/*      */     }
/* 1088 */     long l = getChronology().weeks().add(getLocalMillis(), paramInt);
/* 1089 */     return withLocalMillis(l);
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
/*      */   public LocalDate plusDays(int paramInt)
/*      */   {
/* 1108 */     if (paramInt == 0) {
/* 1109 */       return this;
/*      */     }
/* 1111 */     long l = getChronology().days().add(getLocalMillis(), paramInt);
/* 1112 */     return withLocalMillis(l);
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
/*      */   public LocalDate minus(ReadablePeriod paramReadablePeriod)
/*      */   {
/* 1133 */     return withPeriodAdded(paramReadablePeriod, -1);
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
/*      */   public LocalDate minusYears(int paramInt)
/*      */   {
/* 1153 */     if (paramInt == 0) {
/* 1154 */       return this;
/*      */     }
/* 1156 */     long l = getChronology().years().subtract(getLocalMillis(), paramInt);
/* 1157 */     return withLocalMillis(l);
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
/*      */   public LocalDate minusMonths(int paramInt)
/*      */   {
/* 1176 */     if (paramInt == 0) {
/* 1177 */       return this;
/*      */     }
/* 1179 */     long l = getChronology().months().subtract(getLocalMillis(), paramInt);
/* 1180 */     return withLocalMillis(l);
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
/*      */   public LocalDate minusWeeks(int paramInt)
/*      */   {
/* 1199 */     if (paramInt == 0) {
/* 1200 */       return this;
/*      */     }
/* 1202 */     long l = getChronology().weeks().subtract(getLocalMillis(), paramInt);
/* 1203 */     return withLocalMillis(l);
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
/*      */   public LocalDate minusDays(int paramInt)
/*      */   {
/* 1222 */     if (paramInt == 0) {
/* 1223 */       return this;
/*      */     }
/* 1225 */     long l = getChronology().days().subtract(getLocalMillis(), paramInt);
/* 1226 */     return withLocalMillis(l);
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
/* 1239 */     if (paramDateTimeFieldType == null) {
/* 1240 */       throw new IllegalArgumentException("The DateTimeFieldType must not be null");
/*      */     }
/* 1242 */     if (!isSupported(paramDateTimeFieldType)) {
/* 1243 */       throw new IllegalArgumentException("Field '" + paramDateTimeFieldType + "' is not supported");
/*      */     }
/* 1245 */     return new Property(this, paramDateTimeFieldType.getField(getChronology()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getEra()
/*      */   {
/* 1255 */     return getChronology().era().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getCenturyOfEra()
/*      */   {
/* 1264 */     return getChronology().centuryOfEra().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getYearOfEra()
/*      */   {
/* 1273 */     return getChronology().yearOfEra().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getYearOfCentury()
/*      */   {
/* 1282 */     return getChronology().yearOfCentury().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getYear()
/*      */   {
/* 1291 */     return getChronology().year().get(getLocalMillis());
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
/*      */   public int getWeekyear()
/*      */   {
/* 1306 */     return getChronology().weekyear().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMonthOfYear()
/*      */   {
/* 1315 */     return getChronology().monthOfYear().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getWeekOfWeekyear()
/*      */   {
/* 1324 */     return getChronology().weekOfWeekyear().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getDayOfYear()
/*      */   {
/* 1333 */     return getChronology().dayOfYear().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getDayOfMonth()
/*      */   {
/* 1344 */     return getChronology().dayOfMonth().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getDayOfWeek()
/*      */   {
/* 1355 */     return getChronology().dayOfWeek().get(getLocalMillis());
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
/*      */   public LocalDate withEra(int paramInt)
/*      */   {
/* 1371 */     return withLocalMillis(getChronology().era().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDate withCenturyOfEra(int paramInt)
/*      */   {
/* 1386 */     return withLocalMillis(getChronology().centuryOfEra().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDate withYearOfEra(int paramInt)
/*      */   {
/* 1401 */     return withLocalMillis(getChronology().yearOfEra().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDate withYearOfCentury(int paramInt)
/*      */   {
/* 1416 */     return withLocalMillis(getChronology().yearOfCentury().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDate withYear(int paramInt)
/*      */   {
/* 1431 */     return withLocalMillis(getChronology().year().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDate withWeekyear(int paramInt)
/*      */   {
/* 1446 */     return withLocalMillis(getChronology().weekyear().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDate withMonthOfYear(int paramInt)
/*      */   {
/* 1461 */     return withLocalMillis(getChronology().monthOfYear().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDate withWeekOfWeekyear(int paramInt)
/*      */   {
/* 1476 */     return withLocalMillis(getChronology().weekOfWeekyear().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDate withDayOfYear(int paramInt)
/*      */   {
/* 1491 */     return withLocalMillis(getChronology().dayOfYear().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDate withDayOfMonth(int paramInt)
/*      */   {
/* 1506 */     return withLocalMillis(getChronology().dayOfMonth().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDate withDayOfWeek(int paramInt)
/*      */   {
/* 1521 */     return withLocalMillis(getChronology().dayOfWeek().set(getLocalMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property era()
/*      */   {
/* 1531 */     return new Property(this, getChronology().era());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property centuryOfEra()
/*      */   {
/* 1540 */     return new Property(this, getChronology().centuryOfEra());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property yearOfCentury()
/*      */   {
/* 1549 */     return new Property(this, getChronology().yearOfCentury());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property yearOfEra()
/*      */   {
/* 1558 */     return new Property(this, getChronology().yearOfEra());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property year()
/*      */   {
/* 1567 */     return new Property(this, getChronology().year());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property weekyear()
/*      */   {
/* 1576 */     return new Property(this, getChronology().weekyear());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property monthOfYear()
/*      */   {
/* 1585 */     return new Property(this, getChronology().monthOfYear());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property weekOfWeekyear()
/*      */   {
/* 1594 */     return new Property(this, getChronology().weekOfWeekyear());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property dayOfYear()
/*      */   {
/* 1603 */     return new Property(this, getChronology().dayOfYear());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property dayOfMonth()
/*      */   {
/* 1612 */     return new Property(this, getChronology().dayOfMonth());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property dayOfWeek()
/*      */   {
/* 1621 */     return new Property(this, getChronology().dayOfWeek());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/* 1631 */     return ISODateTimeFormat.date().print(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString(String paramString)
/*      */   {
/* 1641 */     if (paramString == null) {
/* 1642 */       return toString();
/*      */     }
/* 1644 */     return DateTimeFormat.forPattern(paramString).print(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString(String paramString, Locale paramLocale)
/*      */     throws IllegalArgumentException
/*      */   {
/* 1655 */     if (paramString == null) {
/* 1656 */       return toString();
/*      */     }
/* 1658 */     return DateTimeFormat.forPattern(paramString).withLocale(paramLocale).print(this);
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
/*      */     private static final long serialVersionUID = -3193829732634L;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private transient LocalDate iInstant;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private transient DateTimeField iField;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     Property(LocalDate paramLocalDate, DateTimeField paramDateTimeField)
/*      */     {
/* 1707 */       this.iInstant = paramLocalDate;
/* 1708 */       this.iField = paramDateTimeField;
/*      */     }
/*      */     
/*      */ 
/*      */     private void writeObject(ObjectOutputStream paramObjectOutputStream)
/*      */       throws IOException
/*      */     {
/* 1715 */       paramObjectOutputStream.writeObject(this.iInstant);
/* 1716 */       paramObjectOutputStream.writeObject(this.iField.getType());
/*      */     }
/*      */     
/*      */ 
/*      */     private void readObject(ObjectInputStream paramObjectInputStream)
/*      */       throws IOException, ClassNotFoundException
/*      */     {
/* 1723 */       this.iInstant = ((LocalDate)paramObjectInputStream.readObject());
/* 1724 */       DateTimeFieldType localDateTimeFieldType = (DateTimeFieldType)paramObjectInputStream.readObject();
/* 1725 */       this.iField = localDateTimeFieldType.getField(this.iInstant.getChronology());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateTimeField getField()
/*      */     {
/* 1735 */       return this.iField;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected long getMillis()
/*      */     {
/* 1744 */       return this.iInstant.getLocalMillis();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected Chronology getChronology()
/*      */     {
/* 1754 */       return this.iInstant.getChronology();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public LocalDate getLocalDate()
/*      */     {
/* 1763 */       return this.iInstant;
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
/*      */     public LocalDate addToCopy(int paramInt)
/*      */     {
/* 1777 */       return this.iInstant.withLocalMillis(this.iField.add(this.iInstant.getLocalMillis(), paramInt));
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
/*      */     public LocalDate addWrapFieldToCopy(int paramInt)
/*      */     {
/* 1792 */       return this.iInstant.withLocalMillis(this.iField.addWrapField(this.iInstant.getLocalMillis(), paramInt));
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
/*      */     public LocalDate setCopy(int paramInt)
/*      */     {
/* 1806 */       return this.iInstant.withLocalMillis(this.iField.set(this.iInstant.getLocalMillis(), paramInt));
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
/*      */     public LocalDate setCopy(String paramString, Locale paramLocale)
/*      */     {
/* 1820 */       return this.iInstant.withLocalMillis(this.iField.set(this.iInstant.getLocalMillis(), paramString, paramLocale));
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
/*      */     public LocalDate setCopy(String paramString)
/*      */     {
/* 1833 */       return setCopy(paramString, null);
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
/*      */     public LocalDate withMaximumValue()
/*      */     {
/* 1852 */       return setCopy(getMaximumValue());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public LocalDate withMinimumValue()
/*      */     {
/* 1864 */       return setCopy(getMinimumValue());
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
/*      */     public LocalDate roundFloorCopy()
/*      */     {
/* 1879 */       return this.iInstant.withLocalMillis(this.iField.roundFloor(this.iInstant.getLocalMillis()));
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
/*      */     public LocalDate roundCeilingCopy()
/*      */     {
/* 1893 */       return this.iInstant.withLocalMillis(this.iField.roundCeiling(this.iInstant.getLocalMillis()));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public LocalDate roundHalfFloorCopy()
/*      */     {
/* 1903 */       return this.iInstant.withLocalMillis(this.iField.roundHalfFloor(this.iInstant.getLocalMillis()));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public LocalDate roundHalfCeilingCopy()
/*      */     {
/* 1913 */       return this.iInstant.withLocalMillis(this.iField.roundHalfCeiling(this.iInstant.getLocalMillis()));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public LocalDate roundHalfEvenCopy()
/*      */     {
/* 1924 */       return this.iInstant.withLocalMillis(this.iField.roundHalfEven(this.iInstant.getLocalMillis()));
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\LocalDate.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */