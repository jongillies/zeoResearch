/*     */ package org.joda.time.chrono;
/*     */ 
/*     */ import java.util.Locale;
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTimeField;
/*     */ import org.joda.time.DateTimeFieldType;
/*     */ import org.joda.time.DateTimeZone;
/*     */ import org.joda.time.DurationField;
/*     */ import org.joda.time.DurationFieldType;
/*     */ import org.joda.time.field.DividedDateTimeField;
/*     */ import org.joda.time.field.FieldUtils;
/*     */ import org.joda.time.field.MillisDurationField;
/*     */ import org.joda.time.field.OffsetDateTimeField;
/*     */ import org.joda.time.field.PreciseDateTimeField;
/*     */ import org.joda.time.field.PreciseDurationField;
/*     */ import org.joda.time.field.RemainderDateTimeField;
/*     */ import org.joda.time.field.ZeroIsMaxDateTimeField;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class BasicChronology
/*     */   extends AssembledChronology
/*     */ {
/*     */   private static final long serialVersionUID = 8283225332206808863L;
/*  76 */   private static final DurationField cMillisField = MillisDurationField.INSTANCE;
/*  77 */   private static final DurationField cSecondsField = new PreciseDurationField(DurationFieldType.seconds(), 1000L);
/*     */   
/*  79 */   private static final DurationField cMinutesField = new PreciseDurationField(DurationFieldType.minutes(), 60000L);
/*     */   
/*  81 */   private static final DurationField cHoursField = new PreciseDurationField(DurationFieldType.hours(), 3600000L);
/*     */   
/*  83 */   private static final DurationField cHalfdaysField = new PreciseDurationField(DurationFieldType.halfdays(), 43200000L);
/*     */   
/*  85 */   private static final DurationField cDaysField = new PreciseDurationField(DurationFieldType.days(), 86400000L);
/*     */   
/*  87 */   private static final DurationField cWeeksField = new PreciseDurationField(DurationFieldType.weeks(), 604800000L);
/*     */   
/*     */ 
/*  90 */   private static final DateTimeField cMillisOfSecondField = new PreciseDateTimeField(DateTimeFieldType.millisOfSecond(), cMillisField, cSecondsField);
/*     */   
/*     */ 
/*  93 */   private static final DateTimeField cMillisOfDayField = new PreciseDateTimeField(DateTimeFieldType.millisOfDay(), cMillisField, cDaysField);
/*     */   
/*     */ 
/*  96 */   private static final DateTimeField cSecondOfMinuteField = new PreciseDateTimeField(DateTimeFieldType.secondOfMinute(), cSecondsField, cMinutesField);
/*     */   
/*     */ 
/*  99 */   private static final DateTimeField cSecondOfDayField = new PreciseDateTimeField(DateTimeFieldType.secondOfDay(), cSecondsField, cDaysField);
/*     */   
/*     */ 
/* 102 */   private static final DateTimeField cMinuteOfHourField = new PreciseDateTimeField(DateTimeFieldType.minuteOfHour(), cMinutesField, cHoursField);
/*     */   
/*     */ 
/* 105 */   private static final DateTimeField cMinuteOfDayField = new PreciseDateTimeField(DateTimeFieldType.minuteOfDay(), cMinutesField, cDaysField);
/*     */   
/*     */ 
/* 108 */   private static final DateTimeField cHourOfDayField = new PreciseDateTimeField(DateTimeFieldType.hourOfDay(), cHoursField, cDaysField);
/*     */   
/*     */ 
/* 111 */   private static final DateTimeField cHourOfHalfdayField = new PreciseDateTimeField(DateTimeFieldType.hourOfHalfday(), cHoursField, cHalfdaysField);
/*     */   
/*     */ 
/* 114 */   private static final DateTimeField cClockhourOfDayField = new ZeroIsMaxDateTimeField(cHourOfDayField, DateTimeFieldType.clockhourOfDay());
/*     */   
/*     */ 
/* 117 */   private static final DateTimeField cClockhourOfHalfdayField = new ZeroIsMaxDateTimeField(cHourOfHalfdayField, DateTimeFieldType.clockhourOfHalfday());
/*     */   
/*     */ 
/* 120 */   private static final DateTimeField cHalfdayOfDayField = new HalfdayField();
/*     */   
/*     */   private static final int CACHE_SIZE = 1024;
/*     */   
/*     */   private static final int CACHE_MASK = 1023;
/*     */   
/* 126 */   private final transient YearInfo[] iYearInfoCache = new YearInfo['Ѐ'];
/*     */   private final int iMinDaysInFirstWeek;
/*     */   
/*     */   BasicChronology(Chronology paramChronology, Object paramObject, int paramInt)
/*     */   {
/* 131 */     super(paramChronology, paramObject);
/*     */     
/* 133 */     if ((paramInt < 1) || (paramInt > 7)) {
/* 134 */       throw new IllegalArgumentException("Invalid min days in first week: " + paramInt);
/*     */     }
/*     */     
/*     */ 
/* 138 */     this.iMinDaysInFirstWeek = paramInt;
/*     */   }
/*     */   
/*     */   public DateTimeZone getZone() {
/*     */     Chronology localChronology;
/* 143 */     if ((localChronology = getBase()) != null) {
/* 144 */       return localChronology.getZone();
/*     */     }
/* 146 */     return DateTimeZone.UTC;
/*     */   }
/*     */   
/*     */   public long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */     throws IllegalArgumentException
/*     */   {
/*     */     Chronology localChronology;
/* 153 */     if ((localChronology = getBase()) != null) {
/* 154 */       return localChronology.getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4);
/*     */     }
/*     */     
/* 157 */     FieldUtils.verifyValueBounds(DateTimeFieldType.millisOfDay(), paramInt4, 0, 86400000);
/*     */     
/* 159 */     return getDateMidnightMillis(paramInt1, paramInt2, paramInt3) + paramInt4;
/*     */   }
/*     */   
/*     */ 
/*     */   public long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
/*     */     throws IllegalArgumentException
/*     */   {
/*     */     Chronology localChronology;
/* 167 */     if ((localChronology = getBase()) != null) {
/* 168 */       return localChronology.getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
/*     */     }
/*     */     
/*     */ 
/* 172 */     FieldUtils.verifyValueBounds(DateTimeFieldType.hourOfDay(), paramInt4, 0, 23);
/* 173 */     FieldUtils.verifyValueBounds(DateTimeFieldType.minuteOfHour(), paramInt5, 0, 59);
/* 174 */     FieldUtils.verifyValueBounds(DateTimeFieldType.secondOfMinute(), paramInt6, 0, 59);
/* 175 */     FieldUtils.verifyValueBounds(DateTimeFieldType.millisOfSecond(), paramInt7, 0, 999);
/*     */     
/* 177 */     return getDateMidnightMillis(paramInt1, paramInt2, paramInt3) + paramInt4 * 3600000 + paramInt5 * 60000 + paramInt6 * 1000 + paramInt7;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMinimumDaysInFirstWeek()
/*     */   {
/* 185 */     return this.iMinDaysInFirstWeek;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 196 */     return super.equals(paramObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 206 */     return getClass().getName().hashCode() * 11 + getZone().hashCode() + getMinimumDaysInFirstWeek();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 217 */     StringBuffer localStringBuffer = new StringBuffer(60);
/* 218 */     String str = getClass().getName();
/* 219 */     int i = str.lastIndexOf('.');
/* 220 */     if (i >= 0) {
/* 221 */       str = str.substring(i + 1);
/*     */     }
/* 223 */     localStringBuffer.append(str);
/* 224 */     localStringBuffer.append('[');
/* 225 */     DateTimeZone localDateTimeZone = getZone();
/* 226 */     if (localDateTimeZone != null) {
/* 227 */       localStringBuffer.append(localDateTimeZone.getID());
/*     */     }
/* 229 */     if (getMinimumDaysInFirstWeek() != 4) {
/* 230 */       localStringBuffer.append(",mdfw=");
/* 231 */       localStringBuffer.append(getMinimumDaysInFirstWeek());
/*     */     }
/* 233 */     localStringBuffer.append(']');
/* 234 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void assemble(AssembledChronology.Fields paramFields)
/*     */   {
/* 241 */     paramFields.millis = cMillisField;
/* 242 */     paramFields.seconds = cSecondsField;
/* 243 */     paramFields.minutes = cMinutesField;
/* 244 */     paramFields.hours = cHoursField;
/* 245 */     paramFields.halfdays = cHalfdaysField;
/* 246 */     paramFields.days = cDaysField;
/* 247 */     paramFields.weeks = cWeeksField;
/*     */     
/* 249 */     paramFields.millisOfSecond = cMillisOfSecondField;
/* 250 */     paramFields.millisOfDay = cMillisOfDayField;
/* 251 */     paramFields.secondOfMinute = cSecondOfMinuteField;
/* 252 */     paramFields.secondOfDay = cSecondOfDayField;
/* 253 */     paramFields.minuteOfHour = cMinuteOfHourField;
/* 254 */     paramFields.minuteOfDay = cMinuteOfDayField;
/* 255 */     paramFields.hourOfDay = cHourOfDayField;
/* 256 */     paramFields.hourOfHalfday = cHourOfHalfdayField;
/* 257 */     paramFields.clockhourOfDay = cClockhourOfDayField;
/* 258 */     paramFields.clockhourOfHalfday = cClockhourOfHalfdayField;
/* 259 */     paramFields.halfdayOfDay = cHalfdayOfDayField;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 264 */     paramFields.year = new BasicYearDateTimeField(this);
/* 265 */     paramFields.yearOfEra = new GJYearOfEraDateTimeField(paramFields.year, this);
/*     */     
/*     */ 
/* 268 */     Object localObject = new OffsetDateTimeField(paramFields.yearOfEra, 99);
/*     */     
/* 270 */     paramFields.centuryOfEra = new DividedDateTimeField((DateTimeField)localObject, DateTimeFieldType.centuryOfEra(), 100);
/*     */     
/*     */ 
/* 273 */     localObject = new RemainderDateTimeField((DividedDateTimeField)paramFields.centuryOfEra);
/*     */     
/* 275 */     paramFields.yearOfCentury = new OffsetDateTimeField((DateTimeField)localObject, DateTimeFieldType.yearOfCentury(), 1);
/*     */     
/*     */ 
/* 278 */     paramFields.era = new GJEraDateTimeField(this);
/* 279 */     paramFields.dayOfWeek = new GJDayOfWeekDateTimeField(this, paramFields.days);
/* 280 */     paramFields.dayOfMonth = new BasicDayOfMonthDateTimeField(this, paramFields.days);
/* 281 */     paramFields.dayOfYear = new BasicDayOfYearDateTimeField(this, paramFields.days);
/* 282 */     paramFields.monthOfYear = new GJMonthOfYearDateTimeField(this);
/* 283 */     paramFields.weekyear = new BasicWeekyearDateTimeField(this);
/* 284 */     paramFields.weekOfWeekyear = new BasicWeekOfWeekyearDateTimeField(this, paramFields.weeks);
/*     */     
/* 286 */     localObject = new RemainderDateTimeField(paramFields.weekyear, DateTimeFieldType.weekyearOfCentury(), 100);
/*     */     
/* 288 */     paramFields.weekyearOfCentury = new OffsetDateTimeField((DateTimeField)localObject, DateTimeFieldType.weekyearOfCentury(), 1);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 294 */     paramFields.years = paramFields.year.getDurationField();
/* 295 */     paramFields.centuries = paramFields.centuryOfEra.getDurationField();
/* 296 */     paramFields.months = paramFields.monthOfYear.getDurationField();
/* 297 */     paramFields.weekyears = paramFields.weekyear.getDurationField();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   int getDaysInYearMax()
/*     */   {
/* 307 */     return 366;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   int getDaysInYear(int paramInt)
/*     */   {
/* 317 */     return isLeapYear(paramInt) ? 366 : 365;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   int getWeeksInYear(int paramInt)
/*     */   {
/* 327 */     long l1 = getFirstWeekOfYearMillis(paramInt);
/* 328 */     long l2 = getFirstWeekOfYearMillis(paramInt + 1);
/* 329 */     return (int)((l2 - l1) / 604800000L);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   long getFirstWeekOfYearMillis(int paramInt)
/*     */   {
/* 339 */     long l = getYearMillis(paramInt);
/* 340 */     int i = getDayOfWeek(l);
/*     */     
/* 342 */     if (i > 8 - this.iMinDaysInFirstWeek)
/*     */     {
/* 344 */       return l + (8 - i) * 86400000L;
/*     */     }
/*     */     
/*     */ 
/* 348 */     return l - (i - 1) * 86400000L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   long getYearMillis(int paramInt)
/*     */   {
/* 360 */     return getYearInfo(paramInt).iFirstDayMillis;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   long getYearMonthMillis(int paramInt1, int paramInt2)
/*     */   {
/* 371 */     long l = getYearMillis(paramInt1);
/* 372 */     l += getTotalMillisByYearMonth(paramInt1, paramInt2);
/* 373 */     return l;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   long getYearMonthDayMillis(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 385 */     long l = getYearMillis(paramInt1);
/* 386 */     l += getTotalMillisByYearMonth(paramInt1, paramInt2);
/* 387 */     return l + (paramInt3 - 1) * 86400000L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   int getYear(long paramLong)
/*     */   {
/* 399 */     long l1 = getAverageMillisPerYearDividedByTwo();
/* 400 */     long l2 = (paramLong >> 1) + getApproxMillisAtEpochDividedByTwo();
/* 401 */     if (l2 < 0L) {
/* 402 */       l2 = l2 - l1 + 1L;
/*     */     }
/* 404 */     int i = (int)(l2 / l1);
/*     */     
/* 406 */     long l3 = getYearMillis(i);
/* 407 */     long l4 = paramLong - l3;
/*     */     
/* 409 */     if (l4 < 0L) {
/* 410 */       i--;
/* 411 */     } else if (l4 >= 31536000000L)
/*     */     {
/*     */       long l5;
/* 414 */       if (isLeapYear(i)) {
/* 415 */         l5 = 31622400000L;
/*     */       } else {
/* 417 */         l5 = 31536000000L;
/*     */       }
/*     */       
/* 420 */       l3 += l5;
/*     */       
/* 422 */       if (l3 <= paramLong)
/*     */       {
/* 424 */         i++;
/*     */       }
/*     */     }
/*     */     
/* 428 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   int getMonthOfYear(long paramLong)
/*     */   {
/* 435 */     return getMonthOfYear(paramLong, getYear(paramLong));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   abstract int getMonthOfYear(long paramLong, int paramInt);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   int getDayOfMonth(long paramLong)
/*     */   {
/* 448 */     int i = getYear(paramLong);
/* 449 */     int j = getMonthOfYear(paramLong, i);
/* 450 */     return getDayOfMonth(paramLong, i, j);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   int getDayOfMonth(long paramLong, int paramInt)
/*     */   {
/* 458 */     int i = getMonthOfYear(paramLong, paramInt);
/* 459 */     return getDayOfMonth(paramLong, paramInt, i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   int getDayOfMonth(long paramLong, int paramInt1, int paramInt2)
/*     */   {
/* 468 */     long l = getYearMillis(paramInt1);
/* 469 */     l += getTotalMillisByYearMonth(paramInt1, paramInt2);
/* 470 */     return (int)((paramLong - l) / 86400000L) + 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   int getDayOfYear(long paramLong)
/*     */   {
/* 477 */     return getDayOfYear(paramLong, getYear(paramLong));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   int getDayOfYear(long paramLong, int paramInt)
/*     */   {
/* 485 */     long l = getYearMillis(paramInt);
/* 486 */     return (int)((paramLong - l) / 86400000L) + 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   int getWeekyear(long paramLong)
/*     */   {
/* 493 */     int i = getYear(paramLong);
/* 494 */     int j = getWeekOfWeekyear(paramLong, i);
/* 495 */     if (j == 1)
/* 496 */       return getYear(paramLong + 604800000L);
/* 497 */     if (j > 51) {
/* 498 */       return getYear(paramLong - 1209600000L);
/*     */     }
/* 500 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   int getWeekOfWeekyear(long paramLong)
/*     */   {
/* 508 */     return getWeekOfWeekyear(paramLong, getYear(paramLong));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   int getWeekOfWeekyear(long paramLong, int paramInt)
/*     */   {
/* 516 */     long l1 = getFirstWeekOfYearMillis(paramInt);
/* 517 */     if (paramLong < l1) {
/* 518 */       return getWeeksInYear(paramInt - 1);
/*     */     }
/* 520 */     long l2 = getFirstWeekOfYearMillis(paramInt + 1);
/* 521 */     if (paramLong >= l2) {
/* 522 */       return 1;
/*     */     }
/* 524 */     return (int)((paramLong - l1) / 604800000L) + 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   int getDayOfWeek(long paramLong)
/*     */   {
/*     */     long l;
/*     */     
/*     */ 
/* 534 */     if (paramLong >= 0L) {
/* 535 */       l = paramLong / 86400000L;
/*     */     } else {
/* 537 */       l = (paramLong - 86399999L) / 86400000L;
/*     */       
/* 539 */       if (l < -3L) {
/* 540 */         return 7 + (int)((l + 4L) % 7L);
/*     */       }
/*     */     }
/*     */     
/* 544 */     return 1 + (int)((l + 3L) % 7L);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   int getMillisOfDay(long paramLong)
/*     */   {
/* 551 */     if (paramLong >= 0L) {
/* 552 */       return (int)(paramLong % 86400000L);
/*     */     }
/* 554 */     return 86399999 + (int)((paramLong + 1L) % 86400000L);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   int getDaysInMonthMax()
/*     */   {
/* 565 */     return 31;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   int getDaysInMonthMax(long paramLong)
/*     */   {
/* 575 */     int i = getYear(paramLong);
/* 576 */     int j = getMonthOfYear(paramLong, i);
/* 577 */     return getDaysInYearMonth(i, j);
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
/*     */   int getDaysInMonthMaxForSet(long paramLong, int paramInt)
/*     */   {
/* 590 */     return getDaysInMonthMax(paramLong);
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
/*     */   long getDateMidnightMillis(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 603 */     FieldUtils.verifyValueBounds(DateTimeFieldType.year(), paramInt1, getMinYear(), getMaxYear());
/* 604 */     FieldUtils.verifyValueBounds(DateTimeFieldType.monthOfYear(), paramInt2, 1, getMaxMonth(paramInt1));
/* 605 */     FieldUtils.verifyValueBounds(DateTimeFieldType.dayOfMonth(), paramInt3, 1, getDaysInYearMonth(paramInt1, paramInt2));
/* 606 */     return getYearMonthDayMillis(paramInt1, paramInt2, paramInt3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   abstract long getYearDifference(long paramLong1, long paramLong2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   abstract boolean isLeapYear(int paramInt);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   abstract int getDaysInYearMonth(int paramInt1, int paramInt2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   abstract int getDaysInMonthMax(int paramInt);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   abstract long getTotalMillisByYearMonth(int paramInt1, int paramInt2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   abstract long calculateFirstDayOfYearMillis(int paramInt);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   abstract int getMinYear();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   abstract int getMaxYear();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   int getMaxMonth(int paramInt)
/*     */   {
/* 682 */     return getMaxMonth();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   int getMaxMonth()
/*     */   {
/* 691 */     return 12;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   abstract long getAverageMillisPerYear();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   abstract long getAverageMillisPerYearDividedByTwo();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   abstract long getAverageMillisPerMonth();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   abstract long getApproxMillisAtEpochDividedByTwo();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   abstract long setYear(long paramLong, int paramInt);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private YearInfo getYearInfo(int paramInt)
/*     */   {
/* 738 */     YearInfo localYearInfo = this.iYearInfoCache[(paramInt & 0x3FF)];
/* 739 */     if ((localYearInfo == null) || (localYearInfo.iYear != paramInt)) {
/* 740 */       localYearInfo = new YearInfo(paramInt, calculateFirstDayOfYearMillis(paramInt));
/* 741 */       this.iYearInfoCache[(paramInt & 0x3FF)] = localYearInfo;
/*     */     }
/* 743 */     return localYearInfo;
/*     */   }
/*     */   
/*     */   private static class HalfdayField extends PreciseDateTimeField {
/*     */     private static final long serialVersionUID = 581601443656929254L;
/*     */     
/*     */     HalfdayField() {
/* 750 */       super(BasicChronology.cHalfdaysField, BasicChronology.cDaysField);
/*     */     }
/*     */     
/*     */     public String getAsText(int paramInt, Locale paramLocale) {
/* 754 */       return GJLocaleSymbols.forLocale(paramLocale).halfdayValueToText(paramInt);
/*     */     }
/*     */     
/*     */     public long set(long paramLong, String paramString, Locale paramLocale) {
/* 758 */       return set(paramLong, GJLocaleSymbols.forLocale(paramLocale).halfdayTextToValue(paramString));
/*     */     }
/*     */     
/*     */     public int getMaximumTextLength(Locale paramLocale) {
/* 762 */       return GJLocaleSymbols.forLocale(paramLocale).getHalfdayMaxTextLength();
/*     */     }
/*     */   }
/*     */   
/*     */   private static class YearInfo {
/*     */     public final int iYear;
/*     */     public final long iFirstDayMillis;
/*     */     
/*     */     YearInfo(int paramInt, long paramLong) {
/* 771 */       this.iYear = paramInt;
/* 772 */       this.iFirstDayMillis = paramLong;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\BasicChronology.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */