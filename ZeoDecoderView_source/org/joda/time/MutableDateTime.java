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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MutableDateTime
/*      */   extends BaseDateTime
/*      */   implements ReadWritableDateTime, Cloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 2852608688135209575L;
/*      */   public static final int ROUND_NONE = 0;
/*      */   public static final int ROUND_FLOOR = 1;
/*      */   public static final int ROUND_CEILING = 2;
/*      */   public static final int ROUND_HALF_FLOOR = 3;
/*      */   public static final int ROUND_HALF_CEILING = 4;
/*      */   public static final int ROUND_HALF_EVEN = 5;
/*      */   private DateTimeField iRoundingField;
/*      */   private int iRoundingMode;
/*      */   
/*      */   public MutableDateTime() {}
/*      */   
/*      */   public MutableDateTime(DateTimeZone paramDateTimeZone)
/*      */   {
/*  112 */     super(paramDateTimeZone);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MutableDateTime(Chronology paramChronology)
/*      */   {
/*  125 */     super(paramChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MutableDateTime(long paramLong)
/*      */   {
/*  136 */     super(paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MutableDateTime(long paramLong, DateTimeZone paramDateTimeZone)
/*      */   {
/*  149 */     super(paramLong, paramDateTimeZone);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MutableDateTime(long paramLong, Chronology paramChronology)
/*      */   {
/*  163 */     super(paramLong, paramChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MutableDateTime(Object paramObject)
/*      */   {
/*  183 */     super(paramObject, (Chronology)null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MutableDateTime(Object paramObject, DateTimeZone paramDateTimeZone)
/*      */   {
/*  206 */     super(paramObject, paramDateTimeZone);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MutableDateTime(Object paramObject, Chronology paramChronology)
/*      */   {
/*  226 */     super(paramObject, DateTimeUtils.getChronology(paramChronology));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MutableDateTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
/*      */   {
/*  250 */     super(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MutableDateTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, DateTimeZone paramDateTimeZone)
/*      */   {
/*  277 */     super(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramDateTimeZone);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MutableDateTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, Chronology paramChronology)
/*      */   {
/*  306 */     super(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeField getRoundingField()
/*      */   {
/*  318 */     return this.iRoundingField;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getRoundingMode()
/*      */   {
/*  328 */     return this.iRoundingMode;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRounding(DateTimeField paramDateTimeField)
/*      */   {
/*  344 */     setRounding(paramDateTimeField, 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRounding(DateTimeField paramDateTimeField, int paramInt)
/*      */   {
/*  362 */     if ((paramDateTimeField != null) && ((paramInt < 0) || (paramInt > 5))) {
/*  363 */       throw new IllegalArgumentException("Illegal rounding mode: " + paramInt);
/*      */     }
/*  365 */     this.iRoundingField = (paramInt == 0 ? null : paramDateTimeField);
/*  366 */     this.iRoundingMode = (paramDateTimeField == null ? 0 : paramInt);
/*  367 */     setMillis(getMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMillis(long paramLong)
/*      */   {
/*  380 */     switch (this.iRoundingMode) {
/*      */     case 0: 
/*      */       break;
/*      */     case 1: 
/*  384 */       paramLong = this.iRoundingField.roundFloor(paramLong);
/*  385 */       break;
/*      */     case 2: 
/*  387 */       paramLong = this.iRoundingField.roundCeiling(paramLong);
/*  388 */       break;
/*      */     case 3: 
/*  390 */       paramLong = this.iRoundingField.roundHalfFloor(paramLong);
/*  391 */       break;
/*      */     case 4: 
/*  393 */       paramLong = this.iRoundingField.roundHalfCeiling(paramLong);
/*  394 */       break;
/*      */     case 5: 
/*  396 */       paramLong = this.iRoundingField.roundHalfEven(paramLong);
/*      */     }
/*      */     
/*      */     
/*  400 */     super.setMillis(paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMillis(ReadableInstant paramReadableInstant)
/*      */   {
/*  412 */     long l = DateTimeUtils.getInstantMillis(paramReadableInstant);
/*  413 */     setMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void add(long paramLong)
/*      */   {
/*  424 */     setMillis(FieldUtils.safeAdd(getMillis(), paramLong));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void add(ReadableDuration paramReadableDuration)
/*      */   {
/*  436 */     add(paramReadableDuration, 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void add(ReadableDuration paramReadableDuration, int paramInt)
/*      */   {
/*  449 */     if (paramReadableDuration != null) {
/*  450 */       add(FieldUtils.safeMultiply(paramReadableDuration.getMillis(), paramInt));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void add(ReadablePeriod paramReadablePeriod)
/*      */   {
/*  463 */     add(paramReadablePeriod, 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void add(ReadablePeriod paramReadablePeriod, int paramInt)
/*      */   {
/*  476 */     if (paramReadablePeriod != null) {
/*  477 */       setMillis(getChronology().add(paramReadablePeriod, getMillis(), paramInt));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setChronology(Chronology paramChronology)
/*      */   {
/*  490 */     super.setChronology(paramChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setZone(DateTimeZone paramDateTimeZone)
/*      */   {
/*  509 */     paramDateTimeZone = DateTimeUtils.getZone(paramDateTimeZone);
/*  510 */     Chronology localChronology = getChronology();
/*  511 */     if (localChronology.getZone() != paramDateTimeZone) {
/*  512 */       setChronology(localChronology.withZone(paramDateTimeZone));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setZoneRetainFields(DateTimeZone paramDateTimeZone)
/*      */   {
/*  528 */     paramDateTimeZone = DateTimeUtils.getZone(paramDateTimeZone);
/*  529 */     DateTimeZone localDateTimeZone = DateTimeUtils.getZone(getZone());
/*  530 */     if (paramDateTimeZone == localDateTimeZone) {
/*  531 */       return;
/*      */     }
/*      */     
/*  534 */     long l = localDateTimeZone.getMillisKeepLocal(paramDateTimeZone, getMillis());
/*  535 */     setChronology(getChronology().withZone(paramDateTimeZone));
/*  536 */     setMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void set(DateTimeFieldType paramDateTimeFieldType, int paramInt)
/*      */   {
/*  548 */     if (paramDateTimeFieldType == null) {
/*  549 */       throw new IllegalArgumentException("Field must not be null");
/*      */     }
/*  551 */     setMillis(paramDateTimeFieldType.getField(getChronology()).set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void add(DurationFieldType paramDurationFieldType, int paramInt)
/*      */   {
/*  563 */     if (paramDurationFieldType == null) {
/*  564 */       throw new IllegalArgumentException("Field must not be null");
/*      */     }
/*  566 */     setMillis(paramDurationFieldType.getField(getChronology()).add(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setYear(int paramInt)
/*      */   {
/*  577 */     setMillis(getChronology().year().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addYears(int paramInt)
/*      */   {
/*  587 */     setMillis(getChronology().years().add(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setWeekyear(int paramInt)
/*      */   {
/*  598 */     setMillis(getChronology().weekyear().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addWeekyears(int paramInt)
/*      */   {
/*  608 */     setMillis(getChronology().weekyears().add(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMonthOfYear(int paramInt)
/*      */   {
/*  619 */     setMillis(getChronology().monthOfYear().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addMonths(int paramInt)
/*      */   {
/*  629 */     setMillis(getChronology().months().add(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setWeekOfWeekyear(int paramInt)
/*      */   {
/*  640 */     setMillis(getChronology().weekOfWeekyear().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addWeeks(int paramInt)
/*      */   {
/*  650 */     setMillis(getChronology().weeks().add(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDayOfYear(int paramInt)
/*      */   {
/*  661 */     setMillis(getChronology().dayOfYear().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDayOfMonth(int paramInt)
/*      */   {
/*  671 */     setMillis(getChronology().dayOfMonth().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDayOfWeek(int paramInt)
/*      */   {
/*  681 */     setMillis(getChronology().dayOfWeek().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addDays(int paramInt)
/*      */   {
/*  691 */     setMillis(getChronology().days().add(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setHourOfDay(int paramInt)
/*      */   {
/*  702 */     setMillis(getChronology().hourOfDay().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addHours(int paramInt)
/*      */   {
/*  712 */     setMillis(getChronology().hours().add(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMinuteOfDay(int paramInt)
/*      */   {
/*  723 */     setMillis(getChronology().minuteOfDay().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMinuteOfHour(int paramInt)
/*      */   {
/*  733 */     setMillis(getChronology().minuteOfHour().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addMinutes(int paramInt)
/*      */   {
/*  743 */     setMillis(getChronology().minutes().add(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSecondOfDay(int paramInt)
/*      */   {
/*  754 */     setMillis(getChronology().secondOfDay().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSecondOfMinute(int paramInt)
/*      */   {
/*  764 */     setMillis(getChronology().secondOfMinute().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addSeconds(int paramInt)
/*      */   {
/*  774 */     setMillis(getChronology().seconds().add(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMillisOfDay(int paramInt)
/*      */   {
/*  785 */     setMillis(getChronology().millisOfDay().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMillisOfSecond(int paramInt)
/*      */   {
/*  795 */     setMillis(getChronology().millisOfSecond().set(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addMillis(int paramInt)
/*      */   {
/*  807 */     setMillis(getChronology().millis().add(getMillis(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDate(long paramLong)
/*      */   {
/*  819 */     setMillis(getChronology().millisOfDay().set(paramLong, getMillisOfDay()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDate(ReadableInstant paramReadableInstant)
/*      */   {
/*  830 */     long l = DateTimeUtils.getInstantMillis(paramReadableInstant);
/*  831 */     Chronology localChronology = DateTimeUtils.getInstantChronology(paramReadableInstant);
/*  832 */     DateTimeZone localDateTimeZone = localChronology.getZone();
/*  833 */     if (localDateTimeZone != null) {
/*  834 */       l = localDateTimeZone.getMillisKeepLocal(DateTimeZone.UTC, l);
/*      */     }
/*  836 */     setDate(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDate(int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/*  852 */     Chronology localChronology = getChronology();
/*  853 */     long l = localChronology.getDateTimeMillis(paramInt1, paramInt2, paramInt3, 0);
/*  854 */     setDate(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTime(long paramLong)
/*      */   {
/*  866 */     int i = ISOChronology.getInstanceUTC().millisOfDay().get(paramLong);
/*  867 */     setMillis(getChronology().millisOfDay().set(getMillis(), i));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTime(ReadableInstant paramReadableInstant)
/*      */   {
/*  878 */     long l = DateTimeUtils.getInstantMillis(paramReadableInstant);
/*  879 */     Chronology localChronology = DateTimeUtils.getInstantChronology(paramReadableInstant);
/*  880 */     DateTimeZone localDateTimeZone = localChronology.getZone();
/*  881 */     if (localDateTimeZone != null) {
/*  882 */       l = localDateTimeZone.getMillisKeepLocal(DateTimeZone.UTC, l);
/*      */     }
/*  884 */     setTime(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/*  902 */     long l = getChronology().getDateTimeMillis(getMillis(), paramInt1, paramInt2, paramInt3, paramInt4);
/*      */     
/*  904 */     setMillis(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDateTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
/*      */   {
/*  927 */     long l = getChronology().getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
/*      */     
/*  929 */     setMillis(l);
/*      */   }
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
/*  942 */     if (paramDateTimeFieldType == null) {
/*  943 */       throw new IllegalArgumentException("The DateTimeFieldType must not be null");
/*      */     }
/*  945 */     DateTimeField localDateTimeField = paramDateTimeFieldType.getField(getChronology());
/*  946 */     if (!localDateTimeField.isSupported()) {
/*  947 */       throw new IllegalArgumentException("Field '" + paramDateTimeFieldType + "' is not supported");
/*      */     }
/*  949 */     return new Property(this, localDateTimeField);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property era()
/*      */   {
/*  958 */     return new Property(this, getChronology().era());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property centuryOfEra()
/*      */   {
/*  967 */     return new Property(this, getChronology().centuryOfEra());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property yearOfCentury()
/*      */   {
/*  976 */     return new Property(this, getChronology().yearOfCentury());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property yearOfEra()
/*      */   {
/*  985 */     return new Property(this, getChronology().yearOfEra());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property year()
/*      */   {
/*  994 */     return new Property(this, getChronology().year());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property weekyear()
/*      */   {
/* 1003 */     return new Property(this, getChronology().weekyear());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property monthOfYear()
/*      */   {
/* 1012 */     return new Property(this, getChronology().monthOfYear());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property weekOfWeekyear()
/*      */   {
/* 1021 */     return new Property(this, getChronology().weekOfWeekyear());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property dayOfYear()
/*      */   {
/* 1030 */     return new Property(this, getChronology().dayOfYear());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property dayOfMonth()
/*      */   {
/* 1041 */     return new Property(this, getChronology().dayOfMonth());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property dayOfWeek()
/*      */   {
/* 1052 */     return new Property(this, getChronology().dayOfWeek());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property hourOfDay()
/*      */   {
/* 1062 */     return new Property(this, getChronology().hourOfDay());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property minuteOfDay()
/*      */   {
/* 1071 */     return new Property(this, getChronology().minuteOfDay());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property minuteOfHour()
/*      */   {
/* 1080 */     return new Property(this, getChronology().minuteOfHour());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property secondOfDay()
/*      */   {
/* 1089 */     return new Property(this, getChronology().secondOfDay());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property secondOfMinute()
/*      */   {
/* 1098 */     return new Property(this, getChronology().secondOfMinute());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property millisOfDay()
/*      */   {
/* 1107 */     return new Property(this, getChronology().millisOfDay());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Property millisOfSecond()
/*      */   {
/* 1116 */     return new Property(this, getChronology().millisOfSecond());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MutableDateTime copy()
/*      */   {
/* 1126 */     return (MutableDateTime)clone();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object clone()
/*      */   {
/*      */     try
/*      */     {
/* 1137 */       return super.clone();
/*      */     } catch (CloneNotSupportedException localCloneNotSupportedException) {
/* 1139 */       throw new InternalError("Clone error");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/* 1149 */     return ISODateTimeFormat.dateTime().print(this);
/*      */   }
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
/*      */     private static final long serialVersionUID = -4481126543819298617L;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private MutableDateTime iInstant;
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
/*      */     Property(MutableDateTime paramMutableDateTime, DateTimeField paramDateTimeField)
/*      */     {
/* 1190 */       this.iInstant = paramMutableDateTime;
/* 1191 */       this.iField = paramDateTimeField;
/*      */     }
/*      */     
/*      */ 
/*      */     private void writeObject(ObjectOutputStream paramObjectOutputStream)
/*      */       throws IOException
/*      */     {
/* 1198 */       paramObjectOutputStream.writeObject(this.iInstant);
/* 1199 */       paramObjectOutputStream.writeObject(this.iField.getType());
/*      */     }
/*      */     
/*      */ 
/*      */     private void readObject(ObjectInputStream paramObjectInputStream)
/*      */       throws IOException, ClassNotFoundException
/*      */     {
/* 1206 */       this.iInstant = ((MutableDateTime)paramObjectInputStream.readObject());
/* 1207 */       DateTimeFieldType localDateTimeFieldType = (DateTimeFieldType)paramObjectInputStream.readObject();
/* 1208 */       this.iField = localDateTimeFieldType.getField(this.iInstant.getChronology());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateTimeField getField()
/*      */     {
/* 1218 */       return this.iField;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected long getMillis()
/*      */     {
/* 1227 */       return this.iInstant.getMillis();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected Chronology getChronology()
/*      */     {
/* 1237 */       return this.iInstant.getChronology();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public MutableDateTime getMutableDateTime()
/*      */     {
/* 1246 */       return this.iInstant;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public MutableDateTime add(int paramInt)
/*      */     {
/* 1258 */       this.iInstant.setMillis(getField().add(this.iInstant.getMillis(), paramInt));
/* 1259 */       return this.iInstant;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public MutableDateTime add(long paramLong)
/*      */     {
/* 1270 */       this.iInstant.setMillis(getField().add(this.iInstant.getMillis(), paramLong));
/* 1271 */       return this.iInstant;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public MutableDateTime addWrapField(int paramInt)
/*      */     {
/* 1282 */       this.iInstant.setMillis(getField().addWrapField(this.iInstant.getMillis(), paramInt));
/* 1283 */       return this.iInstant;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public MutableDateTime set(int paramInt)
/*      */     {
/* 1295 */       this.iInstant.setMillis(getField().set(this.iInstant.getMillis(), paramInt));
/* 1296 */       return this.iInstant;
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
/*      */     public MutableDateTime set(String paramString, Locale paramLocale)
/*      */     {
/* 1309 */       this.iInstant.setMillis(getField().set(this.iInstant.getMillis(), paramString, paramLocale));
/* 1310 */       return this.iInstant;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public MutableDateTime set(String paramString)
/*      */     {
/* 1322 */       set(paramString, null);
/* 1323 */       return this.iInstant;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public MutableDateTime roundFloor()
/*      */     {
/* 1334 */       this.iInstant.setMillis(getField().roundFloor(this.iInstant.getMillis()));
/* 1335 */       return this.iInstant;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public MutableDateTime roundCeiling()
/*      */     {
/* 1345 */       this.iInstant.setMillis(getField().roundCeiling(this.iInstant.getMillis()));
/* 1346 */       return this.iInstant;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public MutableDateTime roundHalfFloor()
/*      */     {
/* 1357 */       this.iInstant.setMillis(getField().roundHalfFloor(this.iInstant.getMillis()));
/* 1358 */       return this.iInstant;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public MutableDateTime roundHalfCeiling()
/*      */     {
/* 1369 */       this.iInstant.setMillis(getField().roundHalfCeiling(this.iInstant.getMillis()));
/* 1370 */       return this.iInstant;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public MutableDateTime roundHalfEven()
/*      */     {
/* 1381 */       this.iInstant.setMillis(getField().roundHalfEven(this.iInstant.getMillis()));
/* 1382 */       return this.iInstant;
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\MutableDateTime.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */