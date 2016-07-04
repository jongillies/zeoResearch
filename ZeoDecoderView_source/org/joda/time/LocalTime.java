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
/*      */ public final class LocalTime
/*      */   extends BaseLocal
/*      */   implements ReadablePartial, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = -12873158713873L;
/*   81 */   public static final LocalTime MIDNIGHT = new LocalTime(0, 0, 0, 0);
/*      */   
/*      */ 
/*      */   private static final int HOUR_OF_DAY = 0;
/*      */   
/*      */   private static final int MINUTE_OF_HOUR = 1;
/*      */   
/*      */   private static final int SECOND_OF_MINUTE = 2;
/*      */   
/*      */   private static final int MILLIS_OF_SECOND = 3;
/*      */   
/*   92 */   private static final Set TIME_DURATION_TYPES = new HashSet();
/*      */   
/*   94 */   static { TIME_DURATION_TYPES.add(DurationFieldType.millis());
/*   95 */     TIME_DURATION_TYPES.add(DurationFieldType.seconds());
/*   96 */     TIME_DURATION_TYPES.add(DurationFieldType.minutes());
/*   97 */     TIME_DURATION_TYPES.add(DurationFieldType.hours());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static LocalTime fromMillisOfDay(long paramLong)
/*      */   {
/*  117 */     return fromMillisOfDay(paramLong, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static LocalTime fromMillisOfDay(long paramLong, Chronology paramChronology)
/*      */   {
/*  132 */     paramChronology = DateTimeUtils.getChronology(paramChronology).withUTC();
/*  133 */     return new LocalTime(paramLong, paramChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
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
/*      */ 
/*      */ 
/*      */   public static LocalTime fromCalendarFields(Calendar paramCalendar)
/*      */   {
/*  161 */     if (paramCalendar == null) {
/*  162 */       throw new IllegalArgumentException("The calendar must not be null");
/*      */     }
/*  164 */     return new LocalTime(paramCalendar.get(11), paramCalendar.get(12), paramCalendar.get(13), paramCalendar.get(14));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
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
/*      */ 
/*      */ 
/*      */ 
/*      */   public static LocalTime fromDateFields(Date paramDate)
/*      */   {
/*  193 */     if (paramDate == null) {
/*  194 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  196 */     return new LocalTime(paramDate.getHours(), paramDate.getMinutes(), paramDate.getSeconds(), ((int)(paramDate.getTime() % 1000L) + 1000) % 1000);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime()
/*      */   {
/*  212 */     this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime(DateTimeZone paramDateTimeZone)
/*      */   {
/*  225 */     this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance(paramDateTimeZone));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime(Chronology paramChronology)
/*      */   {
/*  238 */     this(DateTimeUtils.currentTimeMillis(), paramChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime(long paramLong)
/*      */   {
/*  251 */     this(paramLong, ISOChronology.getInstance());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime(long paramLong, DateTimeZone paramDateTimeZone)
/*      */   {
/*  265 */     this(paramLong, ISOChronology.getInstance(paramDateTimeZone));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime(long paramLong, Chronology paramChronology)
/*      */   {
/*  279 */     paramChronology = DateTimeUtils.getChronology(paramChronology);
/*      */     
/*  281 */     long l = paramChronology.getZone().getMillisKeepLocal(DateTimeZone.UTC, paramLong);
/*  282 */     paramChronology = paramChronology.withUTC();
/*  283 */     this.iLocalMillis = paramChronology.millisOfDay().get(l);
/*  284 */     this.iChronology = paramChronology;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime(Object paramObject)
/*      */   {
/*  305 */     this(paramObject, (Chronology)null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime(Object paramObject, DateTimeZone paramDateTimeZone)
/*      */   {
/*  327 */     PartialConverter localPartialConverter = ConverterManager.getInstance().getPartialConverter(paramObject);
/*  328 */     Chronology localChronology = localPartialConverter.getChronology(paramObject, paramDateTimeZone);
/*  329 */     localChronology = DateTimeUtils.getChronology(localChronology);
/*  330 */     this.iChronology = localChronology.withUTC();
/*  331 */     int[] arrayOfInt = localPartialConverter.getPartialValues(this, paramObject, localChronology, ISODateTimeFormat.localTimeParser());
/*  332 */     this.iLocalMillis = this.iChronology.getDateTimeMillis(0L, arrayOfInt[0], arrayOfInt[1], arrayOfInt[2], arrayOfInt[3]);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime(Object paramObject, Chronology paramChronology)
/*      */   {
/*  353 */     PartialConverter localPartialConverter = ConverterManager.getInstance().getPartialConverter(paramObject);
/*  354 */     paramChronology = localPartialConverter.getChronology(paramObject, paramChronology);
/*  355 */     paramChronology = DateTimeUtils.getChronology(paramChronology);
/*  356 */     this.iChronology = paramChronology.withUTC();
/*  357 */     int[] arrayOfInt = localPartialConverter.getPartialValues(this, paramObject, paramChronology, ISODateTimeFormat.localTimeParser());
/*  358 */     this.iLocalMillis = this.iChronology.getDateTimeMillis(0L, arrayOfInt[0], arrayOfInt[1], arrayOfInt[2], arrayOfInt[3]);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime(int paramInt1, int paramInt2)
/*      */   {
/*  372 */     this(paramInt1, paramInt2, 0, 0, ISOChronology.getInstanceUTC());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime(int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/*  387 */     this(paramInt1, paramInt2, paramInt3, 0, ISOChronology.getInstanceUTC());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/*  404 */     this(paramInt1, paramInt2, paramInt3, paramInt4, ISOChronology.getInstanceUTC());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Chronology paramChronology)
/*      */   {
/*  427 */     paramChronology = DateTimeUtils.getChronology(paramChronology).withUTC();
/*  428 */     long l = paramChronology.getDateTimeMillis(0L, paramInt1, paramInt2, paramInt3, paramInt4);
/*      */     
/*  430 */     this.iChronology = paramChronology;
/*  431 */     this.iLocalMillis = l;
/*      */   }
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
/*  443 */     return 4;
/*      */   }
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
/*  456 */     switch (paramInt) {
/*      */     case 0: 
/*  458 */       return paramChronology.hourOfDay();
/*      */     case 1: 
/*  460 */       return paramChronology.minuteOfHour();
/*      */     case 2: 
/*  462 */       return paramChronology.secondOfMinute();
/*      */     case 3: 
/*  464 */       return paramChronology.millisOfSecond();
/*      */     }
/*  466 */     throw new IndexOutOfBoundsException("Invalid index: " + paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
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
/*  482 */     switch (paramInt) {
/*      */     case 0: 
/*  484 */       return getChronology().hourOfDay().get(getLocalMillis());
/*      */     case 1: 
/*  486 */       return getChronology().minuteOfHour().get(getLocalMillis());
/*      */     case 2: 
/*  488 */       return getChronology().secondOfMinute().get(getLocalMillis());
/*      */     case 3: 
/*  490 */       return getChronology().millisOfSecond().get(getLocalMillis());
/*      */     }
/*  492 */     throw new IndexOutOfBoundsException("Invalid index: " + paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  512 */     if (paramDateTimeFieldType == null) {
/*  513 */       throw new IllegalArgumentException("The DateTimeFieldType must not be null");
/*      */     }
/*  515 */     if (!isSupported(paramDateTimeFieldType)) {
/*  516 */       throw new IllegalArgumentException("Field '" + paramDateTimeFieldType + "' is not supported");
/*      */     }
/*  518 */     return paramDateTimeFieldType.getField(getChronology()).get(getLocalMillis());
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
/*  530 */     if (paramDateTimeFieldType == null) {
/*  531 */       return false;
/*      */     }
/*  533 */     if (!isSupported(paramDateTimeFieldType.getDurationType())) {
/*  534 */       return false;
/*      */     }
/*  536 */     DurationFieldType localDurationFieldType = paramDateTimeFieldType.getRangeDurationType();
/*  537 */     return (isSupported(localDurationFieldType)) || (localDurationFieldType == DurationFieldType.days());
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
/*  548 */     if (paramDurationFieldType == null) {
/*  549 */       return false;
/*      */     }
/*  551 */     DurationField localDurationField = paramDurationFieldType.getField(getChronology());
/*  552 */     if ((TIME_DURATION_TYPES.contains(paramDurationFieldType)) || (localDurationField.getUnitMillis() < getChronology().days().getUnitMillis()))
/*      */     {
/*  554 */       return localDurationField.isSupported();
/*      */     }
/*  556 */     return false;
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
/*  568 */     return this.iLocalMillis;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Chronology getChronology()
/*      */   {
/*  577 */     return this.iChronology;
/*      */   }
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
/*  590 */     if (this == paramObject) {
/*  591 */       return true;
/*      */     }
/*  593 */     if ((paramObject instanceof LocalTime)) {
/*  594 */       LocalTime localLocalTime = (LocalTime)paramObject;
/*  595 */       if (this.iChronology.equals(localLocalTime.iChronology)) {
/*  596 */         return this.iLocalMillis == localLocalTime.iLocalMillis;
/*      */       }
/*      */     }
/*  599 */     return super.equals(paramObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  628 */     if (this == paramObject) {
/*  629 */       return 0;
/*      */     }
/*  631 */     if ((paramObject instanceof LocalTime)) {
/*  632 */       LocalTime localLocalTime = (LocalTime)paramObject;
/*  633 */       if (this.iChronology.equals(localLocalTime.iChronology)) {
/*  634 */         return this.iLocalMillis == localLocalTime.iLocalMillis ? 0 : this.iLocalMillis < localLocalTime.iLocalMillis ? -1 : 1;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  639 */     return super.compareTo(paramObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   LocalTime withLocalMillis(long paramLong)
/*      */   {
/*  654 */     return paramLong == getLocalMillis() ? this : new LocalTime(paramLong, getChronology());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime withFields(ReadablePartial paramReadablePartial)
/*      */   {
/*  672 */     if (paramReadablePartial == null) {
/*  673 */       return this;
/*      */     }
/*  675 */     return withLocalMillis(getChronology().set(paramReadablePartial, getLocalMillis()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime withField(DateTimeFieldType paramDateTimeFieldType, int paramInt)
/*      */   {
/*  698 */     if (paramDateTimeFieldType == null) {
/*  699 */       throw new IllegalArgumentException("Field must not be null");
/*      */     }
/*  701 */     if (!isSupported(paramDateTimeFieldType)) {
/*  702 */       throw new IllegalArgumentException("Field '" + paramDateTimeFieldType + "' is not supported");
/*      */     }
/*  704 */     long l = paramDateTimeFieldType.getField(getChronology()).set(getLocalMillis(), paramInt);
/*  705 */     return withLocalMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime withFieldAdded(DurationFieldType paramDurationFieldType, int paramInt)
/*      */   {
/*  731 */     if (paramDurationFieldType == null) {
/*  732 */       throw new IllegalArgumentException("Field must not be null");
/*      */     }
/*  734 */     if (!isSupported(paramDurationFieldType)) {
/*  735 */       throw new IllegalArgumentException("Field '" + paramDurationFieldType + "' is not supported");
/*      */     }
/*  737 */     if (paramInt == 0) {
/*  738 */       return this;
/*      */     }
/*  740 */     long l = paramDurationFieldType.getField(getChronology()).add(getLocalMillis(), paramInt);
/*  741 */     return withLocalMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime withPeriodAdded(ReadablePeriod paramReadablePeriod, int paramInt)
/*      */   {
/*  761 */     if ((paramReadablePeriod == null) || (paramInt == 0)) {
/*  762 */       return this;
/*      */     }
/*  764 */     long l = getChronology().add(paramReadablePeriod, getLocalMillis(), paramInt);
/*  765 */     return withLocalMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime plus(ReadablePeriod paramReadablePeriod)
/*      */   {
/*  783 */     return withPeriodAdded(paramReadablePeriod, 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime plusHours(int paramInt)
/*      */   {
/*  803 */     if (paramInt == 0) {
/*  804 */       return this;
/*      */     }
/*  806 */     long l = getChronology().hours().add(getLocalMillis(), paramInt);
/*  807 */     return withLocalMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime plusMinutes(int paramInt)
/*      */   {
/*  826 */     if (paramInt == 0) {
/*  827 */       return this;
/*      */     }
/*  829 */     long l = getChronology().minutes().add(getLocalMillis(), paramInt);
/*  830 */     return withLocalMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime plusSeconds(int paramInt)
/*      */   {
/*  849 */     if (paramInt == 0) {
/*  850 */       return this;
/*      */     }
/*  852 */     long l = getChronology().seconds().add(getLocalMillis(), paramInt);
/*  853 */     return withLocalMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime plusMillis(int paramInt)
/*      */   {
/*  872 */     if (paramInt == 0) {
/*  873 */       return this;
/*      */     }
/*  875 */     long l = getChronology().millis().add(getLocalMillis(), paramInt);
/*  876 */     return withLocalMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime minus(ReadablePeriod paramReadablePeriod)
/*      */   {
/*  894 */     return withPeriodAdded(paramReadablePeriod, -1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime minusHours(int paramInt)
/*      */   {
/*  914 */     if (paramInt == 0) {
/*  915 */       return this;
/*      */     }
/*  917 */     long l = getChronology().hours().subtract(getLocalMillis(), paramInt);
/*  918 */     return withLocalMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime minusMinutes(int paramInt)
/*      */   {
/*  937 */     if (paramInt == 0) {
/*  938 */       return this;
/*      */     }
/*  940 */     long l = getChronology().minutes().subtract(getLocalMillis(), paramInt);
/*  941 */     return withLocalMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime minusSeconds(int paramInt)
/*      */   {
/*  960 */     if (paramInt == 0) {
/*  961 */       return this;
/*      */     }
/*  963 */     long l = getChronology().seconds().subtract(getLocalMillis(), paramInt);
/*  964 */     return withLocalMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime minusMillis(int paramInt)
/*      */   {
/*  983 */     if (paramInt == 0) {
/*  984 */       return this;
/*      */     }
/*  986 */     long l = getChronology().millis().subtract(getLocalMillis(), paramInt);
/*  987 */     return withLocalMillis(l);
/*      */   }
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
/* 1000 */     if (paramDateTimeFieldType == null) {
/* 1001 */       throw new IllegalArgumentException("The DateTimeFieldType must not be null");
/*      */     }
/* 1003 */     if (!isSupported(paramDateTimeFieldType)) {
/* 1004 */       throw new IllegalArgumentException("Field '" + paramDateTimeFieldType + "' is not supported");
/*      */     }
/* 1006 */     return new Property(this, paramDateTimeFieldType.getField(getChronology()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getHourOfDay()
/*      */   {
/* 1016 */     return getChronology().hourOfDay().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMinuteOfHour()
/*      */   {
/* 1025 */     return getChronology().minuteOfHour().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getSecondOfMinute()
/*      */   {
/* 1034 */     return getChronology().secondOfMinute().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMillisOfSecond()
/*      */   {
/* 1043 */     return getChronology().millisOfSecond().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMillisOfDay()
/*      */   {
/* 1052 */     return getChronology().millisOfDay().get(getLocalMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime withHourOfDay(int paramInt)
/*      */   {
/* 1068 */     return withLocalMillis(getChronology().hourOfDay().set(getLocalMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime withMinuteOfHour(int paramInt)
/*      */   {
/* 1083 */     return withLocalMillis(getChronology().minuteOfHour().set(getLocalMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime withSecondOfMinute(int paramInt)
/*      */   {
/* 1098 */     return withLocalMillis(getChronology().secondOfMinute().set(getLocalMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime withMillisOfSecond(int paramInt)
/*      */   {
/* 1113 */     return withLocalMillis(getChronology().millisOfSecond().set(getLocalMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LocalTime withMillisOfDay(int paramInt)
/*      */   {
/* 1128 */     return withLocalMillis(getChronology().millisOfDay().set(getLocalMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property hourOfDay()
/*      */   {
/* 1138 */     return new Property(this, getChronology().hourOfDay());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property minuteOfHour()
/*      */   {
/* 1147 */     return new Property(this, getChronology().minuteOfHour());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property secondOfMinute()
/*      */   {
/* 1156 */     return new Property(this, getChronology().secondOfMinute());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property millisOfSecond()
/*      */   {
/* 1165 */     return new Property(this, getChronology().millisOfSecond());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property millisOfDay()
/*      */   {
/* 1174 */     return new Property(this, getChronology().millisOfDay());
/*      */   }
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
/* 1186 */     return toDateTimeToday(null);
/*      */   }
/*      */   
/*      */ 
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
/* 1201 */     Chronology localChronology = getChronology().withZone(paramDateTimeZone);
/* 1202 */     long l1 = DateTimeUtils.currentTimeMillis();
/* 1203 */     long l2 = localChronology.set(this, l1);
/* 1204 */     return new DateTime(l2, localChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/* 1214 */     return ISODateTimeFormat.time().print(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString(String paramString)
/*      */   {
/* 1224 */     if (paramString == null) {
/* 1225 */       return toString();
/*      */     }
/* 1227 */     return DateTimeFormat.forPattern(paramString).print(this);
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
/* 1238 */     if (paramString == null) {
/* 1239 */       return toString();
/*      */     }
/* 1241 */     return DateTimeFormat.forPattern(paramString).withLocale(paramLocale).print(this);
/*      */   }
/*      */   
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
/*      */     private static final long serialVersionUID = -325842547277223L;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private transient LocalTime iInstant;
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
/*      */     Property(LocalTime paramLocalTime, DateTimeField paramDateTimeField)
/*      */     {
/* 1289 */       this.iInstant = paramLocalTime;
/* 1290 */       this.iField = paramDateTimeField;
/*      */     }
/*      */     
/*      */ 
/*      */     private void writeObject(ObjectOutputStream paramObjectOutputStream)
/*      */       throws IOException
/*      */     {
/* 1297 */       paramObjectOutputStream.writeObject(this.iInstant);
/* 1298 */       paramObjectOutputStream.writeObject(this.iField.getType());
/*      */     }
/*      */     
/*      */ 
/*      */     private void readObject(ObjectInputStream paramObjectInputStream)
/*      */       throws IOException, ClassNotFoundException
/*      */     {
/* 1305 */       this.iInstant = ((LocalTime)paramObjectInputStream.readObject());
/* 1306 */       DateTimeFieldType localDateTimeFieldType = (DateTimeFieldType)paramObjectInputStream.readObject();
/* 1307 */       this.iField = localDateTimeFieldType.getField(this.iInstant.getChronology());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateTimeField getField()
/*      */     {
/* 1317 */       return this.iField;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected long getMillis()
/*      */     {
/* 1326 */       return this.iInstant.getLocalMillis();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected Chronology getChronology()
/*      */     {
/* 1336 */       return this.iInstant.getChronology();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public LocalTime getLocalTime()
/*      */     {
/* 1345 */       return this.iInstant;
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
/*      */     public LocalTime addCopy(int paramInt)
/*      */     {
/* 1358 */       return this.iInstant.withLocalMillis(this.iField.add(this.iInstant.getLocalMillis(), paramInt));
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
/*      */     public LocalTime addCopy(long paramLong)
/*      */     {
/* 1372 */       return this.iInstant.withLocalMillis(this.iField.add(this.iInstant.getLocalMillis(), paramLong));
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
/*      */     public LocalTime addNoWrapToCopy(int paramInt)
/*      */     {
/* 1388 */       long l1 = this.iField.add(this.iInstant.getLocalMillis(), paramInt);
/* 1389 */       long l2 = this.iInstant.getChronology().millisOfDay().get(l1);
/* 1390 */       if (l2 != l1) {
/* 1391 */         throw new IllegalArgumentException("The addition exceeded the boundaries of LocalTime");
/*      */       }
/* 1393 */       return this.iInstant.withLocalMillis(l1);
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
/*      */     public LocalTime addWrapFieldToCopy(int paramInt)
/*      */     {
/* 1408 */       return this.iInstant.withLocalMillis(this.iField.addWrapField(this.iInstant.getLocalMillis(), paramInt));
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
/*      */     public LocalTime setCopy(int paramInt)
/*      */     {
/* 1422 */       return this.iInstant.withLocalMillis(this.iField.set(this.iInstant.getLocalMillis(), paramInt));
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
/*      */     public LocalTime setCopy(String paramString, Locale paramLocale)
/*      */     {
/* 1436 */       return this.iInstant.withLocalMillis(this.iField.set(this.iInstant.getLocalMillis(), paramString, paramLocale));
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
/*      */     public LocalTime setCopy(String paramString)
/*      */     {
/* 1449 */       return setCopy(paramString, null);
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
/*      */     public LocalTime withMaximumValue()
/*      */     {
/* 1462 */       return setCopy(getMaximumValue());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public LocalTime withMinimumValue()
/*      */     {
/* 1474 */       return setCopy(getMinimumValue());
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
/*      */     public LocalTime roundFloorCopy()
/*      */     {
/* 1489 */       return this.iInstant.withLocalMillis(this.iField.roundFloor(this.iInstant.getLocalMillis()));
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
/*      */     public LocalTime roundCeilingCopy()
/*      */     {
/* 1503 */       return this.iInstant.withLocalMillis(this.iField.roundCeiling(this.iInstant.getLocalMillis()));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public LocalTime roundHalfFloorCopy()
/*      */     {
/* 1513 */       return this.iInstant.withLocalMillis(this.iField.roundHalfFloor(this.iInstant.getLocalMillis()));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public LocalTime roundHalfCeilingCopy()
/*      */     {
/* 1523 */       return this.iInstant.withLocalMillis(this.iField.roundHalfCeiling(this.iInstant.getLocalMillis()));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public LocalTime roundHalfEvenCopy()
/*      */     {
/* 1534 */       return this.iInstant.withLocalMillis(this.iField.roundHalfEven(this.iInstant.getLocalMillis()));
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\LocalTime.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */