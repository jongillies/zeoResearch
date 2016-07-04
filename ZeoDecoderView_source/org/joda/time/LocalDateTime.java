/*      */ package org.joda.time;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.Locale;
/*      */ import org.joda.time.base.BaseLocal;
/*      */ import org.joda.time.chrono.ISOChronology;
/*      */ import org.joda.time.convert.ConverterManager;
/*      */ import org.joda.time.convert.PartialConverter;
/*      */ import org.joda.time.field.AbstractReadableInstantFieldProperty;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class LocalDateTime
/*      */   extends BaseLocal
/*      */   implements ReadablePartial, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = -268716875315837168L;
/*      */   private static final int YEAR = 0;
/*      */   private static final int MONTH_OF_YEAR = 1;
/*      */   private static final int DAY_OF_MONTH = 2;
/*      */   private static final int MILLIS_OF_DAY = 3;
/*      */   private long iLocalMillis;
/*      */   private Chronology iChronology;
/*      */   
/*      */   public static LocalDateTime fromCalendarFields(Calendar paramCalendar)
/*      */   {
/*  121 */     if (paramCalendar == null) {
/*  122 */       throw new IllegalArgumentException("The calendar must not be null");
/*      */     }
/*  124 */     return new LocalDateTime(paramCalendar.get(1), paramCalendar.get(2) + 1, paramCalendar.get(5), paramCalendar.get(11), paramCalendar.get(12), paramCalendar.get(13), paramCalendar.get(14));
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
/*      */ 
/*      */   public static LocalDateTime fromDateFields(Date paramDate)
/*      */   {
/*  156 */     if (paramDate == null) {
/*  157 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  159 */     return new LocalDateTime(paramDate.getYear() + 1900, paramDate.getMonth() + 1, paramDate.getDate(), paramDate.getHours(), paramDate.getMinutes(), paramDate.getSeconds(), (int)(paramDate.getTime() % 1000L));
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
/*      */   public LocalDateTime()
/*      */   {
/*  178 */     this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance());
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
/*      */   public LocalDateTime(DateTimeZone paramDateTimeZone)
/*      */   {
/*  191 */     this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance(paramDateTimeZone));
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
/*      */   public LocalDateTime(Chronology paramChronology)
/*      */   {
/*  204 */     this(DateTimeUtils.currentTimeMillis(), paramChronology);
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
/*      */   public LocalDateTime(long paramLong)
/*      */   {
/*  217 */     this(paramLong, ISOChronology.getInstance());
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
/*      */   public LocalDateTime(long paramLong, DateTimeZone paramDateTimeZone)
/*      */   {
/*  231 */     this(paramLong, ISOChronology.getInstance(paramDateTimeZone));
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
/*      */   public LocalDateTime(long paramLong, Chronology paramChronology)
/*      */   {
/*  245 */     paramChronology = DateTimeUtils.getChronology(paramChronology);
/*      */     
/*  247 */     long l = paramChronology.getZone().getMillisKeepLocal(DateTimeZone.UTC, paramLong);
/*  248 */     this.iLocalMillis = l;
/*  249 */     this.iChronology = paramChronology.withUTC();
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
/*      */   public LocalDateTime(Object paramObject)
/*      */   {
/*  270 */     this(paramObject, (Chronology)null);
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
/*      */   public LocalDateTime(Object paramObject, DateTimeZone paramDateTimeZone)
/*      */   {
/*  292 */     PartialConverter localPartialConverter = ConverterManager.getInstance().getPartialConverter(paramObject);
/*  293 */     Chronology localChronology = localPartialConverter.getChronology(paramObject, paramDateTimeZone);
/*  294 */     localChronology = DateTimeUtils.getChronology(localChronology);
/*  295 */     this.iChronology = localChronology.withUTC();
/*  296 */     int[] arrayOfInt = localPartialConverter.getPartialValues(this, paramObject, localChronology, ISODateTimeFormat.localDateOptionalTimeParser());
/*  297 */     this.iLocalMillis = this.iChronology.getDateTimeMillis(arrayOfInt[0], arrayOfInt[1], arrayOfInt[2], arrayOfInt[3]);
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
/*      */   public LocalDateTime(Object paramObject, Chronology paramChronology)
/*      */   {
/*  318 */     PartialConverter localPartialConverter = ConverterManager.getInstance().getPartialConverter(paramObject);
/*  319 */     paramChronology = localPartialConverter.getChronology(paramObject, paramChronology);
/*  320 */     paramChronology = DateTimeUtils.getChronology(paramChronology);
/*  321 */     this.iChronology = paramChronology.withUTC();
/*  322 */     int[] arrayOfInt = localPartialConverter.getPartialValues(this, paramObject, paramChronology, ISODateTimeFormat.localDateOptionalTimeParser());
/*  323 */     this.iLocalMillis = this.iChronology.getDateTimeMillis(arrayOfInt[0], arrayOfInt[1], arrayOfInt[2], arrayOfInt[3]);
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
/*      */   public LocalDateTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*      */   {
/*  343 */     this(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, 0, 0, ISOChronology.getInstanceUTC());
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
/*      */   public LocalDateTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*      */   {
/*  365 */     this(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, 0, ISOChronology.getInstanceUTC());
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
/*      */   public LocalDateTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
/*      */   {
/*  389 */     this(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, ISOChronology.getInstanceUTC());
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
/*      */   public LocalDateTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, Chronology paramChronology)
/*      */   {
/*  418 */     paramChronology = DateTimeUtils.getChronology(paramChronology).withUTC();
/*  419 */     long l = paramChronology.getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
/*      */     
/*  421 */     this.iChronology = paramChronology;
/*  422 */     this.iLocalMillis = l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int size()
/*      */   {
/*  433 */     return 4;
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
/*  446 */     switch (paramInt) {
/*      */     case 0: 
/*  448 */       return paramChronology.year();
/*      */     case 1: 
/*  450 */       return paramChronology.monthOfYear();
/*      */     case 2: 
/*  452 */       return paramChronology.dayOfMonth();
/*      */     case 3: 
/*  454 */       return paramChronology.millisOfDay();
/*      */     }
/*  456 */     throw new IndexOutOfBoundsException("Invalid index: " + paramInt);
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
/*      */   public int getValue(int paramInt)
/*      */   {
/*  471 */     switch (paramInt) {
/*      */     case 0: 
/*  473 */       return getChronology().year().get(getLocalMillis());
/*      */     case 1: 
/*  475 */       return getChronology().monthOfYear().get(getLocalMillis());
/*      */     case 2: 
/*  477 */       return getChronology().dayOfMonth().get(getLocalMillis());
/*      */     case 3: 
/*  479 */       return getChronology().millisOfDay().get(getLocalMillis());
/*      */     }
/*  481 */     throw new IndexOutOfBoundsException("Invalid index: " + paramInt);
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
/*  501 */     if (paramDateTimeFieldType == null) {
/*  502 */       throw new IllegalArgumentException("The DateTimeFieldType must not be null");
/*      */     }
/*  504 */     return paramDateTimeFieldType.getField(getChronology()).get(getLocalMillis());
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
/*  516 */     if (paramDateTimeFieldType == null) {
/*  517 */       return false;
/*      */     }
/*  519 */     return paramDateTimeFieldType.getField(getChronology()).isSupported();
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
/*  530 */     if (paramDurationFieldType == null) {
/*  531 */       return false;
/*      */     }
/*  533 */     return paramDurationFieldType.getField(getChronology()).isSupported();
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
/*  545 */     return this.iLocalMillis;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Chronology getChronology()
/*      */   {
/*  554 */     return this.iChronology;
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
/*  567 */     if (this == paramObject) {
/*  568 */       return true;
/*      */     }
/*  570 */     if ((paramObject instanceof LocalDateTime)) {
/*  571 */       LocalDateTime localLocalDateTime = (LocalDateTime)paramObject;
/*  572 */       if (this.iChronology.equals(localLocalDateTime.iChronology)) {
/*  573 */         return this.iLocalMillis == localLocalDateTime.iLocalMillis;
/*      */       }
/*      */     }
/*  576 */     return super.equals(paramObject);
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
/*  605 */     if (this == paramObject) {
/*  606 */       return 0;
/*      */     }
/*  608 */     if ((paramObject instanceof LocalDateTime)) {
/*  609 */       LocalDateTime localLocalDateTime = (LocalDateTime)paramObject;
/*  610 */       if (this.iChronology.equals(localLocalDateTime.iChronology)) {
/*  611 */         return this.iLocalMillis == localLocalDateTime.iLocalMillis ? 0 : this.iLocalMillis < localLocalDateTime.iLocalMillis ? -1 : 1;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  616 */     return super.compareTo(paramObject);
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
/*      */   public DateTime toDateTime()
/*      */   {
/*  629 */     return toDateTime((DateTimeZone)null);
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
/*      */   public DateTime toDateTime(DateTimeZone paramDateTimeZone)
/*      */   {
/*  642 */     paramDateTimeZone = DateTimeUtils.getZone(paramDateTimeZone);
/*  643 */     Chronology localChronology = this.iChronology.withZone(paramDateTimeZone);
/*  644 */     return new DateTime(getYear(), getMonthOfYear(), getDayOfMonth(), getHourOfDay(), getMinuteOfHour(), getSecondOfMinute(), getMillisOfSecond(), localChronology);
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
/*      */   public LocalDate toLocalDate()
/*      */   {
/*  657 */     return new LocalDate(getLocalMillis(), getChronology());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime toLocalTime()
/*      */   {
/*  666 */     return new LocalTime(getLocalMillis(), getChronology());
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
/*      */   LocalDateTime withLocalMillis(long paramLong)
/*      */   {
/*  681 */     return paramLong == getLocalMillis() ? this : new LocalDateTime(paramLong, getChronology());
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
/*      */   public LocalDateTime withDate(int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/*  703 */     Chronology localChronology = getChronology();
/*  704 */     long l = getLocalMillis();
/*  705 */     l = localChronology.year().set(l, paramInt1);
/*  706 */     l = localChronology.monthOfYear().set(l, paramInt2);
/*  707 */     l = localChronology.dayOfMonth().set(l, paramInt3);
/*  708 */     return withLocalMillis(l);
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
/*      */   public LocalDateTime withTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/*  730 */     Chronology localChronology = getChronology();
/*  731 */     long l = getLocalMillis();
/*  732 */     l = localChronology.hourOfDay().set(l, paramInt1);
/*  733 */     l = localChronology.minuteOfHour().set(l, paramInt2);
/*  734 */     l = localChronology.secondOfMinute().set(l, paramInt3);
/*  735 */     l = localChronology.millisOfSecond().set(l, paramInt4);
/*  736 */     return withLocalMillis(l);
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
/*      */   public LocalDateTime withFields(ReadablePartial paramReadablePartial)
/*      */   {
/*  753 */     if (paramReadablePartial == null) {
/*  754 */       return this;
/*      */     }
/*  756 */     return withLocalMillis(getChronology().set(paramReadablePartial, getLocalMillis()));
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
/*      */   public LocalDateTime withField(DateTimeFieldType paramDateTimeFieldType, int paramInt)
/*      */   {
/*  779 */     if (paramDateTimeFieldType == null) {
/*  780 */       throw new IllegalArgumentException("Field must not be null");
/*      */     }
/*  782 */     long l = paramDateTimeFieldType.getField(getChronology()).set(getLocalMillis(), paramInt);
/*  783 */     return withLocalMillis(l);
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
/*      */   public LocalDateTime withFieldAdded(DurationFieldType paramDurationFieldType, int paramInt)
/*      */   {
/*  806 */     if (paramDurationFieldType == null) {
/*  807 */       throw new IllegalArgumentException("Field must not be null");
/*      */     }
/*  809 */     if (paramInt == 0) {
/*  810 */       return this;
/*      */     }
/*  812 */     long l = paramDurationFieldType.getField(getChronology()).add(getLocalMillis(), paramInt);
/*  813 */     return withLocalMillis(l);
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
/*      */   public LocalDateTime withDurationAdded(ReadableDuration paramReadableDuration, int paramInt)
/*      */   {
/*  828 */     if ((paramReadableDuration == null) || (paramInt == 0)) {
/*  829 */       return this;
/*      */     }
/*  831 */     long l = getChronology().add(getLocalMillis(), paramReadableDuration.getMillis(), paramInt);
/*  832 */     return withLocalMillis(l);
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
/*      */   public LocalDateTime withPeriodAdded(ReadablePeriod paramReadablePeriod, int paramInt)
/*      */   {
/*  851 */     if ((paramReadablePeriod == null) || (paramInt == 0)) {
/*  852 */       return this;
/*      */     }
/*  854 */     long l = getChronology().add(paramReadablePeriod, getLocalMillis(), paramInt);
/*  855 */     return withLocalMillis(l);
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
/*      */   public LocalDateTime plus(ReadableDuration paramReadableDuration)
/*      */   {
/*  869 */     return withDurationAdded(paramReadableDuration, 1);
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
/*      */   public LocalDateTime plus(ReadablePeriod paramReadablePeriod)
/*      */   {
/*  886 */     return withPeriodAdded(paramReadablePeriod, 1);
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
/*      */   public LocalDateTime plusYears(int paramInt)
/*      */   {
/*  906 */     if (paramInt == 0) {
/*  907 */       return this;
/*      */     }
/*  909 */     long l = getChronology().years().add(getLocalMillis(), paramInt);
/*  910 */     return withLocalMillis(l);
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
/*      */   public LocalDateTime plusMonths(int paramInt)
/*      */   {
/*  929 */     if (paramInt == 0) {
/*  930 */       return this;
/*      */     }
/*  932 */     long l = getChronology().months().add(getLocalMillis(), paramInt);
/*  933 */     return withLocalMillis(l);
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
/*      */   public LocalDateTime plusWeeks(int paramInt)
/*      */   {
/*  952 */     if (paramInt == 0) {
/*  953 */       return this;
/*      */     }
/*  955 */     long l = getChronology().weeks().add(getLocalMillis(), paramInt);
/*  956 */     return withLocalMillis(l);
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
/*      */   public LocalDateTime plusDays(int paramInt)
/*      */   {
/*  975 */     if (paramInt == 0) {
/*  976 */       return this;
/*      */     }
/*  978 */     long l = getChronology().days().add(getLocalMillis(), paramInt);
/*  979 */     return withLocalMillis(l);
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
/*      */   public LocalDateTime plusHours(int paramInt)
/*      */   {
/*  999 */     if (paramInt == 0) {
/* 1000 */       return this;
/*      */     }
/* 1002 */     long l = getChronology().hours().add(getLocalMillis(), paramInt);
/* 1003 */     return withLocalMillis(l);
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
/*      */   public LocalDateTime plusMinutes(int paramInt)
/*      */   {
/* 1022 */     if (paramInt == 0) {
/* 1023 */       return this;
/*      */     }
/* 1025 */     long l = getChronology().minutes().add(getLocalMillis(), paramInt);
/* 1026 */     return withLocalMillis(l);
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
/*      */   public LocalDateTime plusSeconds(int paramInt)
/*      */   {
/* 1045 */     if (paramInt == 0) {
/* 1046 */       return this;
/*      */     }
/* 1048 */     long l = getChronology().seconds().add(getLocalMillis(), paramInt);
/* 1049 */     return withLocalMillis(l);
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
/*      */   public LocalDateTime plusMillis(int paramInt)
/*      */   {
/* 1068 */     if (paramInt == 0) {
/* 1069 */       return this;
/*      */     }
/* 1071 */     long l = getChronology().millis().add(getLocalMillis(), paramInt);
/* 1072 */     return withLocalMillis(l);
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
/*      */   public LocalDateTime minus(ReadableDuration paramReadableDuration)
/*      */   {
/* 1086 */     return withDurationAdded(paramReadableDuration, -1);
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
/*      */   public LocalDateTime minus(ReadablePeriod paramReadablePeriod)
/*      */   {
/* 1103 */     return withPeriodAdded(paramReadablePeriod, -1);
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
/*      */   public LocalDateTime minusYears(int paramInt)
/*      */   {
/* 1123 */     if (paramInt == 0) {
/* 1124 */       return this;
/*      */     }
/* 1126 */     long l = getChronology().years().subtract(getLocalMillis(), paramInt);
/* 1127 */     return withLocalMillis(l);
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
/*      */   public LocalDateTime minusMonths(int paramInt)
/*      */   {
/* 1146 */     if (paramInt == 0) {
/* 1147 */       return this;
/*      */     }
/* 1149 */     long l = getChronology().months().subtract(getLocalMillis(), paramInt);
/* 1150 */     return withLocalMillis(l);
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
/*      */   public LocalDateTime minusWeeks(int paramInt)
/*      */   {
/* 1169 */     if (paramInt == 0) {
/* 1170 */       return this;
/*      */     }
/* 1172 */     long l = getChronology().weeks().subtract(getLocalMillis(), paramInt);
/* 1173 */     return withLocalMillis(l);
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
/*      */   public LocalDateTime minusDays(int paramInt)
/*      */   {
/* 1192 */     if (paramInt == 0) {
/* 1193 */       return this;
/*      */     }
/* 1195 */     long l = getChronology().days().subtract(getLocalMillis(), paramInt);
/* 1196 */     return withLocalMillis(l);
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
/*      */   public LocalDateTime minusHours(int paramInt)
/*      */   {
/* 1216 */     if (paramInt == 0) {
/* 1217 */       return this;
/*      */     }
/* 1219 */     long l = getChronology().hours().subtract(getLocalMillis(), paramInt);
/* 1220 */     return withLocalMillis(l);
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
/*      */   public LocalDateTime minusMinutes(int paramInt)
/*      */   {
/* 1239 */     if (paramInt == 0) {
/* 1240 */       return this;
/*      */     }
/* 1242 */     long l = getChronology().minutes().subtract(getLocalMillis(), paramInt);
/* 1243 */     return withLocalMillis(l);
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
/*      */   public LocalDateTime minusSeconds(int paramInt)
/*      */   {
/* 1262 */     if (paramInt == 0) {
/* 1263 */       return this;
/*      */     }
/* 1265 */     long l = getChronology().seconds().subtract(getLocalMillis(), paramInt);
/* 1266 */     return withLocalMillis(l);
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
/*      */   public LocalDateTime minusMillis(int paramInt)
/*      */   {
/* 1285 */     if (paramInt == 0) {
/* 1286 */       return this;
/*      */     }
/* 1288 */     long l = getChronology().millis().subtract(getLocalMillis(), paramInt);
/* 1289 */     return withLocalMillis(l);
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
/* 1302 */     if (paramDateTimeFieldType == null) {
/* 1303 */       throw new IllegalArgumentException("The DateTimeFieldType must not be null");
/*      */     }
/* 1305 */     if (!isSupported(paramDateTimeFieldType)) {
/* 1306 */       throw new IllegalArgumentException("Field '" + paramDateTimeFieldType + "' is not supported");
/*      */     }
/* 1308 */     return new Property(this, paramDateTimeFieldType.getField(getChronology()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getEra()
/*      */   {
/* 1318 */     return getChronology().era().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getCenturyOfEra()
/*      */   {
/* 1327 */     return getChronology().centuryOfEra().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getYearOfEra()
/*      */   {
/* 1336 */     return getChronology().yearOfEra().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getYearOfCentury()
/*      */   {
/* 1345 */     return getChronology().yearOfCentury().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getYear()
/*      */   {
/* 1354 */     return getChronology().year().get(getLocalMillis());
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
/* 1369 */     return getChronology().weekyear().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMonthOfYear()
/*      */   {
/* 1378 */     return getChronology().monthOfYear().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getWeekOfWeekyear()
/*      */   {
/* 1387 */     return getChronology().weekOfWeekyear().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getDayOfYear()
/*      */   {
/* 1396 */     return getChronology().dayOfYear().get(getLocalMillis());
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
/* 1407 */     return getChronology().dayOfMonth().get(getLocalMillis());
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
/* 1418 */     return getChronology().dayOfWeek().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getHourOfDay()
/*      */   {
/* 1428 */     return getChronology().hourOfDay().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMinuteOfHour()
/*      */   {
/* 1437 */     return getChronology().minuteOfHour().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getSecondOfMinute()
/*      */   {
/* 1446 */     return getChronology().secondOfMinute().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMillisOfSecond()
/*      */   {
/* 1455 */     return getChronology().millisOfSecond().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMillisOfDay()
/*      */   {
/* 1464 */     return getChronology().millisOfDay().get(getLocalMillis());
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
/*      */   public LocalDateTime withEra(int paramInt)
/*      */   {
/* 1480 */     return withLocalMillis(getChronology().era().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDateTime withCenturyOfEra(int paramInt)
/*      */   {
/* 1495 */     return withLocalMillis(getChronology().centuryOfEra().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDateTime withYearOfEra(int paramInt)
/*      */   {
/* 1510 */     return withLocalMillis(getChronology().yearOfEra().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDateTime withYearOfCentury(int paramInt)
/*      */   {
/* 1525 */     return withLocalMillis(getChronology().yearOfCentury().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDateTime withYear(int paramInt)
/*      */   {
/* 1540 */     return withLocalMillis(getChronology().year().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDateTime withWeekyear(int paramInt)
/*      */   {
/* 1555 */     return withLocalMillis(getChronology().weekyear().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDateTime withMonthOfYear(int paramInt)
/*      */   {
/* 1570 */     return withLocalMillis(getChronology().monthOfYear().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDateTime withWeekOfWeekyear(int paramInt)
/*      */   {
/* 1585 */     return withLocalMillis(getChronology().weekOfWeekyear().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDateTime withDayOfYear(int paramInt)
/*      */   {
/* 1600 */     return withLocalMillis(getChronology().dayOfYear().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDateTime withDayOfMonth(int paramInt)
/*      */   {
/* 1615 */     return withLocalMillis(getChronology().dayOfMonth().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDateTime withDayOfWeek(int paramInt)
/*      */   {
/* 1630 */     return withLocalMillis(getChronology().dayOfWeek().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDateTime withHourOfDay(int paramInt)
/*      */   {
/* 1646 */     return withLocalMillis(getChronology().hourOfDay().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDateTime withMinuteOfHour(int paramInt)
/*      */   {
/* 1661 */     return withLocalMillis(getChronology().minuteOfHour().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDateTime withSecondOfMinute(int paramInt)
/*      */   {
/* 1676 */     return withLocalMillis(getChronology().secondOfMinute().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDateTime withMillisOfSecond(int paramInt)
/*      */   {
/* 1691 */     return withLocalMillis(getChronology().millisOfSecond().set(getLocalMillis(), paramInt));
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
/*      */   public LocalDateTime withMillisOfDay(int paramInt)
/*      */   {
/* 1706 */     return withLocalMillis(getChronology().millisOfDay().set(getLocalMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property era()
/*      */   {
/* 1716 */     return new Property(this, getChronology().era());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property centuryOfEra()
/*      */   {
/* 1725 */     return new Property(this, getChronology().centuryOfEra());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property yearOfCentury()
/*      */   {
/* 1734 */     return new Property(this, getChronology().yearOfCentury());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property yearOfEra()
/*      */   {
/* 1743 */     return new Property(this, getChronology().yearOfEra());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property year()
/*      */   {
/* 1752 */     return new Property(this, getChronology().year());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property weekyear()
/*      */   {
/* 1761 */     return new Property(this, getChronology().weekyear());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property monthOfYear()
/*      */   {
/* 1770 */     return new Property(this, getChronology().monthOfYear());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property weekOfWeekyear()
/*      */   {
/* 1779 */     return new Property(this, getChronology().weekOfWeekyear());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property dayOfYear()
/*      */   {
/* 1788 */     return new Property(this, getChronology().dayOfYear());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property dayOfMonth()
/*      */   {
/* 1797 */     return new Property(this, getChronology().dayOfMonth());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property dayOfWeek()
/*      */   {
/* 1806 */     return new Property(this, getChronology().dayOfWeek());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property hourOfDay()
/*      */   {
/* 1816 */     return new Property(this, getChronology().hourOfDay());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property minuteOfHour()
/*      */   {
/* 1825 */     return new Property(this, getChronology().minuteOfHour());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property secondOfMinute()
/*      */   {
/* 1834 */     return new Property(this, getChronology().secondOfMinute());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property millisOfSecond()
/*      */   {
/* 1843 */     return new Property(this, getChronology().millisOfSecond());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property millisOfDay()
/*      */   {
/* 1852 */     return new Property(this, getChronology().millisOfDay());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/* 1862 */     return ISODateTimeFormat.dateTime().print(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString(String paramString)
/*      */   {
/* 1872 */     if (paramString == null) {
/* 1873 */       return toString();
/*      */     }
/* 1875 */     return DateTimeFormat.forPattern(paramString).print(this);
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
/* 1886 */     if (paramString == null) {
/* 1887 */       return toString();
/*      */     }
/* 1889 */     return DateTimeFormat.forPattern(paramString).withLocale(paramLocale).print(this);
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
/*      */     private static final long serialVersionUID = -358138762846288L;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private transient LocalDateTime iInstant;
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
/*      */     Property(LocalDateTime paramLocalDateTime, DateTimeField paramDateTimeField)
/*      */     {
/* 1939 */       this.iInstant = paramLocalDateTime;
/* 1940 */       this.iField = paramDateTimeField;
/*      */     }
/*      */     
/*      */ 
/*      */     private void writeObject(ObjectOutputStream paramObjectOutputStream)
/*      */       throws IOException
/*      */     {
/* 1947 */       paramObjectOutputStream.writeObject(this.iInstant);
/* 1948 */       paramObjectOutputStream.writeObject(this.iField.getType());
/*      */     }
/*      */     
/*      */ 
/*      */     private void readObject(ObjectInputStream paramObjectInputStream)
/*      */       throws IOException, ClassNotFoundException
/*      */     {
/* 1955 */       this.iInstant = ((LocalDateTime)paramObjectInputStream.readObject());
/* 1956 */       DateTimeFieldType localDateTimeFieldType = (DateTimeFieldType)paramObjectInputStream.readObject();
/* 1957 */       this.iField = localDateTimeFieldType.getField(this.iInstant.getChronology());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateTimeField getField()
/*      */     {
/* 1967 */       return this.iField;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected long getMillis()
/*      */     {
/* 1976 */       return this.iInstant.getLocalMillis();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected Chronology getChronology()
/*      */     {
/* 1986 */       return this.iInstant.getChronology();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public LocalDateTime getLocalDateTime()
/*      */     {
/* 1995 */       return this.iInstant;
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
/*      */     public LocalDateTime addToCopy(int paramInt)
/*      */     {
/* 2009 */       return this.iInstant.withLocalMillis(this.iField.add(this.iInstant.getLocalMillis(), paramInt));
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
/*      */     public LocalDateTime addToCopy(long paramLong)
/*      */     {
/* 2022 */       return this.iInstant.withLocalMillis(this.iField.add(this.iInstant.getLocalMillis(), paramLong));
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
/*      */     public LocalDateTime addWrapFieldToCopy(int paramInt)
/*      */     {
/* 2037 */       return this.iInstant.withLocalMillis(this.iField.addWrapField(this.iInstant.getLocalMillis(), paramInt));
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
/*      */     public LocalDateTime setCopy(int paramInt)
/*      */     {
/* 2051 */       return this.iInstant.withLocalMillis(this.iField.set(this.iInstant.getLocalMillis(), paramInt));
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
/*      */     public LocalDateTime setCopy(String paramString, Locale paramLocale)
/*      */     {
/* 2065 */       return this.iInstant.withLocalMillis(this.iField.set(this.iInstant.getLocalMillis(), paramString, paramLocale));
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
/*      */     public LocalDateTime setCopy(String paramString)
/*      */     {
/* 2078 */       return setCopy(paramString, null);
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
/*      */     public LocalDateTime withMaximumValue()
/*      */     {
/* 2097 */       return setCopy(getMaximumValue());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public LocalDateTime withMinimumValue()
/*      */     {
/* 2109 */       return setCopy(getMinimumValue());
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
/*      */     public LocalDateTime roundFloorCopy()
/*      */     {
/* 2124 */       return this.iInstant.withLocalMillis(this.iField.roundFloor(this.iInstant.getLocalMillis()));
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
/*      */     public LocalDateTime roundCeilingCopy()
/*      */     {
/* 2138 */       return this.iInstant.withLocalMillis(this.iField.roundCeiling(this.iInstant.getLocalMillis()));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public LocalDateTime roundHalfFloorCopy()
/*      */     {
/* 2148 */       return this.iInstant.withLocalMillis(this.iField.roundHalfFloor(this.iInstant.getLocalMillis()));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public LocalDateTime roundHalfCeilingCopy()
/*      */     {
/* 2158 */       return this.iInstant.withLocalMillis(this.iField.roundHalfCeiling(this.iInstant.getLocalMillis()));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public LocalDateTime roundHalfEvenCopy()
/*      */     {
/* 2169 */       return this.iInstant.withLocalMillis(this.iField.roundHalfEven(this.iInstant.getLocalMillis()));
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\LocalDateTime.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */