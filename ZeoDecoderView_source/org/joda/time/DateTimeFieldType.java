/*     */ package org.joda.time;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public abstract class DateTimeFieldType
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -42615285973990L;
/*     */   static final byte ERA = 1;
/*     */   static final byte YEAR_OF_ERA = 2;
/*     */   static final byte CENTURY_OF_ERA = 3;
/*     */   static final byte YEAR_OF_CENTURY = 4;
/*     */   static final byte YEAR = 5;
/*     */   static final byte DAY_OF_YEAR = 6;
/*     */   static final byte MONTH_OF_YEAR = 7;
/*     */   static final byte DAY_OF_MONTH = 8;
/*     */   static final byte WEEKYEAR_OF_CENTURY = 9;
/*     */   static final byte WEEKYEAR = 10;
/*     */   static final byte WEEK_OF_WEEKYEAR = 11;
/*     */   static final byte DAY_OF_WEEK = 12;
/*     */   static final byte HALFDAY_OF_DAY = 13;
/*     */   static final byte HOUR_OF_HALFDAY = 14;
/*     */   static final byte CLOCKHOUR_OF_HALFDAY = 15;
/*     */   static final byte CLOCKHOUR_OF_DAY = 16;
/*     */   static final byte HOUR_OF_DAY = 17;
/*     */   static final byte MINUTE_OF_DAY = 18;
/*     */   static final byte MINUTE_OF_HOUR = 19;
/*     */   static final byte SECOND_OF_DAY = 20;
/*     */   static final byte SECOND_OF_MINUTE = 21;
/*     */   static final byte MILLIS_OF_DAY = 22;
/*     */   static final byte MILLIS_OF_SECOND = 23;
/*  72 */   private static final DateTimeFieldType ERA_TYPE = new StandardDateTimeFieldType("era", (byte)1, DurationFieldType.eras(), null);
/*     */   
/*     */ 
/*  75 */   private static final DateTimeFieldType YEAR_OF_ERA_TYPE = new StandardDateTimeFieldType("yearOfEra", (byte)2, DurationFieldType.years(), DurationFieldType.eras());
/*     */   
/*     */ 
/*  78 */   private static final DateTimeFieldType CENTURY_OF_ERA_TYPE = new StandardDateTimeFieldType("centuryOfEra", (byte)3, DurationFieldType.centuries(), DurationFieldType.eras());
/*     */   
/*     */ 
/*  81 */   private static final DateTimeFieldType YEAR_OF_CENTURY_TYPE = new StandardDateTimeFieldType("yearOfCentury", (byte)4, DurationFieldType.years(), DurationFieldType.centuries());
/*     */   
/*     */ 
/*  84 */   private static final DateTimeFieldType YEAR_TYPE = new StandardDateTimeFieldType("year", (byte)5, DurationFieldType.years(), null);
/*     */   
/*     */ 
/*  87 */   private static final DateTimeFieldType DAY_OF_YEAR_TYPE = new StandardDateTimeFieldType("dayOfYear", (byte)6, DurationFieldType.days(), DurationFieldType.years());
/*     */   
/*     */ 
/*  90 */   private static final DateTimeFieldType MONTH_OF_YEAR_TYPE = new StandardDateTimeFieldType("monthOfYear", (byte)7, DurationFieldType.months(), DurationFieldType.years());
/*     */   
/*     */ 
/*  93 */   private static final DateTimeFieldType DAY_OF_MONTH_TYPE = new StandardDateTimeFieldType("dayOfMonth", (byte)8, DurationFieldType.days(), DurationFieldType.months());
/*     */   
/*     */ 
/*  96 */   private static final DateTimeFieldType WEEKYEAR_OF_CENTURY_TYPE = new StandardDateTimeFieldType("weekyearOfCentury", (byte)9, DurationFieldType.weekyears(), DurationFieldType.centuries());
/*     */   
/*     */ 
/*  99 */   private static final DateTimeFieldType WEEKYEAR_TYPE = new StandardDateTimeFieldType("weekyear", (byte)10, DurationFieldType.weekyears(), null);
/*     */   
/*     */ 
/* 102 */   private static final DateTimeFieldType WEEK_OF_WEEKYEAR_TYPE = new StandardDateTimeFieldType("weekOfWeekyear", (byte)11, DurationFieldType.weeks(), DurationFieldType.weekyears());
/*     */   
/*     */ 
/* 105 */   private static final DateTimeFieldType DAY_OF_WEEK_TYPE = new StandardDateTimeFieldType("dayOfWeek", (byte)12, DurationFieldType.days(), DurationFieldType.weeks());
/*     */   
/*     */ 
/*     */ 
/* 109 */   private static final DateTimeFieldType HALFDAY_OF_DAY_TYPE = new StandardDateTimeFieldType("halfdayOfDay", (byte)13, DurationFieldType.halfdays(), DurationFieldType.days());
/*     */   
/*     */ 
/* 112 */   private static final DateTimeFieldType HOUR_OF_HALFDAY_TYPE = new StandardDateTimeFieldType("hourOfHalfday", (byte)14, DurationFieldType.hours(), DurationFieldType.halfdays());
/*     */   
/*     */ 
/* 115 */   private static final DateTimeFieldType CLOCKHOUR_OF_HALFDAY_TYPE = new StandardDateTimeFieldType("clockhourOfHalfday", (byte)15, DurationFieldType.hours(), DurationFieldType.halfdays());
/*     */   
/*     */ 
/* 118 */   private static final DateTimeFieldType CLOCKHOUR_OF_DAY_TYPE = new StandardDateTimeFieldType("clockhourOfDay", (byte)16, DurationFieldType.hours(), DurationFieldType.days());
/*     */   
/*     */ 
/* 121 */   private static final DateTimeFieldType HOUR_OF_DAY_TYPE = new StandardDateTimeFieldType("hourOfDay", (byte)17, DurationFieldType.hours(), DurationFieldType.days());
/*     */   
/*     */ 
/* 124 */   private static final DateTimeFieldType MINUTE_OF_DAY_TYPE = new StandardDateTimeFieldType("minuteOfDay", (byte)18, DurationFieldType.minutes(), DurationFieldType.days());
/*     */   
/*     */ 
/* 127 */   private static final DateTimeFieldType MINUTE_OF_HOUR_TYPE = new StandardDateTimeFieldType("minuteOfHour", (byte)19, DurationFieldType.minutes(), DurationFieldType.hours());
/*     */   
/*     */ 
/* 130 */   private static final DateTimeFieldType SECOND_OF_DAY_TYPE = new StandardDateTimeFieldType("secondOfDay", (byte)20, DurationFieldType.seconds(), DurationFieldType.days());
/*     */   
/*     */ 
/* 133 */   private static final DateTimeFieldType SECOND_OF_MINUTE_TYPE = new StandardDateTimeFieldType("secondOfMinute", (byte)21, DurationFieldType.seconds(), DurationFieldType.minutes());
/*     */   
/*     */ 
/* 136 */   private static final DateTimeFieldType MILLIS_OF_DAY_TYPE = new StandardDateTimeFieldType("millisOfDay", (byte)22, DurationFieldType.millis(), DurationFieldType.days());
/*     */   
/*     */ 
/* 139 */   private static final DateTimeFieldType MILLIS_OF_SECOND_TYPE = new StandardDateTimeFieldType("millisOfSecond", (byte)23, DurationFieldType.millis(), DurationFieldType.seconds());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final String iName;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DateTimeFieldType(String paramString)
/*     */   {
/* 153 */     this.iName = paramString;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFieldType millisOfSecond()
/*     */   {
/* 163 */     return MILLIS_OF_SECOND_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFieldType millisOfDay()
/*     */   {
/* 172 */     return MILLIS_OF_DAY_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFieldType secondOfMinute()
/*     */   {
/* 181 */     return SECOND_OF_MINUTE_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFieldType secondOfDay()
/*     */   {
/* 190 */     return SECOND_OF_DAY_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFieldType minuteOfHour()
/*     */   {
/* 199 */     return MINUTE_OF_HOUR_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFieldType minuteOfDay()
/*     */   {
/* 208 */     return MINUTE_OF_DAY_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFieldType hourOfDay()
/*     */   {
/* 217 */     return HOUR_OF_DAY_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFieldType clockhourOfDay()
/*     */   {
/* 226 */     return CLOCKHOUR_OF_DAY_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFieldType hourOfHalfday()
/*     */   {
/* 235 */     return HOUR_OF_HALFDAY_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFieldType clockhourOfHalfday()
/*     */   {
/* 244 */     return CLOCKHOUR_OF_HALFDAY_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFieldType halfdayOfDay()
/*     */   {
/* 253 */     return HALFDAY_OF_DAY_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFieldType dayOfWeek()
/*     */   {
/* 263 */     return DAY_OF_WEEK_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFieldType dayOfMonth()
/*     */   {
/* 272 */     return DAY_OF_MONTH_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFieldType dayOfYear()
/*     */   {
/* 281 */     return DAY_OF_YEAR_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFieldType weekOfWeekyear()
/*     */   {
/* 290 */     return WEEK_OF_WEEKYEAR_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFieldType weekyear()
/*     */   {
/* 299 */     return WEEKYEAR_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFieldType weekyearOfCentury()
/*     */   {
/* 308 */     return WEEKYEAR_OF_CENTURY_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFieldType monthOfYear()
/*     */   {
/* 317 */     return MONTH_OF_YEAR_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFieldType year()
/*     */   {
/* 326 */     return YEAR_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFieldType yearOfEra()
/*     */   {
/* 335 */     return YEAR_OF_ERA_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFieldType yearOfCentury()
/*     */   {
/* 344 */     return YEAR_OF_CENTURY_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFieldType centuryOfEra()
/*     */   {
/* 353 */     return CENTURY_OF_ERA_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFieldType era()
/*     */   {
/* 362 */     return ERA_TYPE;
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
/*     */   public String getName()
/*     */   {
/* 377 */     return this.iName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract DurationFieldType getDurationType();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract DurationFieldType getRangeDurationType();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract DateTimeField getField(Chronology paramChronology);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isSupported(Chronology paramChronology)
/*     */   {
/* 409 */     return getField(paramChronology).isSupported();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 418 */     return getName();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static class StandardDateTimeFieldType
/*     */     extends DateTimeFieldType
/*     */   {
/*     */     private static final long serialVersionUID = -9937958251642L;
/*     */     
/*     */ 
/*     */ 
/*     */     private final byte iOrdinal;
/*     */     
/*     */ 
/*     */     private final transient DurationFieldType iUnitType;
/*     */     
/*     */ 
/*     */     private final transient DurationFieldType iRangeType;
/*     */     
/*     */ 
/*     */ 
/*     */     StandardDateTimeFieldType(String paramString, byte paramByte, DurationFieldType paramDurationFieldType1, DurationFieldType paramDurationFieldType2)
/*     */     {
/* 443 */       super();
/* 444 */       this.iOrdinal = paramByte;
/* 445 */       this.iUnitType = paramDurationFieldType1;
/* 446 */       this.iRangeType = paramDurationFieldType2;
/*     */     }
/*     */     
/*     */     public DurationFieldType getDurationType()
/*     */     {
/* 451 */       return this.iUnitType;
/*     */     }
/*     */     
/*     */     public DurationFieldType getRangeDurationType()
/*     */     {
/* 456 */       return this.iRangeType;
/*     */     }
/*     */     
/*     */     public DateTimeField getField(Chronology paramChronology)
/*     */     {
/* 461 */       paramChronology = DateTimeUtils.getChronology(paramChronology);
/*     */       
/* 463 */       switch (this.iOrdinal) {
/*     */       case 1: 
/* 465 */         return paramChronology.era();
/*     */       case 2: 
/* 467 */         return paramChronology.yearOfEra();
/*     */       case 3: 
/* 469 */         return paramChronology.centuryOfEra();
/*     */       case 4: 
/* 471 */         return paramChronology.yearOfCentury();
/*     */       case 5: 
/* 473 */         return paramChronology.year();
/*     */       case 6: 
/* 475 */         return paramChronology.dayOfYear();
/*     */       case 7: 
/* 477 */         return paramChronology.monthOfYear();
/*     */       case 8: 
/* 479 */         return paramChronology.dayOfMonth();
/*     */       case 9: 
/* 481 */         return paramChronology.weekyearOfCentury();
/*     */       case 10: 
/* 483 */         return paramChronology.weekyear();
/*     */       case 11: 
/* 485 */         return paramChronology.weekOfWeekyear();
/*     */       case 12: 
/* 487 */         return paramChronology.dayOfWeek();
/*     */       case 13: 
/* 489 */         return paramChronology.halfdayOfDay();
/*     */       case 14: 
/* 491 */         return paramChronology.hourOfHalfday();
/*     */       case 15: 
/* 493 */         return paramChronology.clockhourOfHalfday();
/*     */       case 16: 
/* 495 */         return paramChronology.clockhourOfDay();
/*     */       case 17: 
/* 497 */         return paramChronology.hourOfDay();
/*     */       case 18: 
/* 499 */         return paramChronology.minuteOfDay();
/*     */       case 19: 
/* 501 */         return paramChronology.minuteOfHour();
/*     */       case 20: 
/* 503 */         return paramChronology.secondOfDay();
/*     */       case 21: 
/* 505 */         return paramChronology.secondOfMinute();
/*     */       case 22: 
/* 507 */         return paramChronology.millisOfDay();
/*     */       case 23: 
/* 509 */         return paramChronology.millisOfSecond();
/*     */       }
/*     */       
/* 512 */       throw new InternalError();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private Object readResolve()
/*     */     {
/* 522 */       switch (this.iOrdinal) {
/*     */       case 1: 
/* 524 */         return DateTimeFieldType.ERA_TYPE;
/*     */       case 2: 
/* 526 */         return DateTimeFieldType.YEAR_OF_ERA_TYPE;
/*     */       case 3: 
/* 528 */         return DateTimeFieldType.CENTURY_OF_ERA_TYPE;
/*     */       case 4: 
/* 530 */         return DateTimeFieldType.YEAR_OF_CENTURY_TYPE;
/*     */       case 5: 
/* 532 */         return DateTimeFieldType.YEAR_TYPE;
/*     */       case 6: 
/* 534 */         return DateTimeFieldType.DAY_OF_YEAR_TYPE;
/*     */       case 7: 
/* 536 */         return DateTimeFieldType.MONTH_OF_YEAR_TYPE;
/*     */       case 8: 
/* 538 */         return DateTimeFieldType.DAY_OF_MONTH_TYPE;
/*     */       case 9: 
/* 540 */         return DateTimeFieldType.WEEKYEAR_OF_CENTURY_TYPE;
/*     */       case 10: 
/* 542 */         return DateTimeFieldType.WEEKYEAR_TYPE;
/*     */       case 11: 
/* 544 */         return DateTimeFieldType.WEEK_OF_WEEKYEAR_TYPE;
/*     */       case 12: 
/* 546 */         return DateTimeFieldType.DAY_OF_WEEK_TYPE;
/*     */       case 13: 
/* 548 */         return DateTimeFieldType.HALFDAY_OF_DAY_TYPE;
/*     */       case 14: 
/* 550 */         return DateTimeFieldType.HOUR_OF_HALFDAY_TYPE;
/*     */       case 15: 
/* 552 */         return DateTimeFieldType.CLOCKHOUR_OF_HALFDAY_TYPE;
/*     */       case 16: 
/* 554 */         return DateTimeFieldType.CLOCKHOUR_OF_DAY_TYPE;
/*     */       case 17: 
/* 556 */         return DateTimeFieldType.HOUR_OF_DAY_TYPE;
/*     */       case 18: 
/* 558 */         return DateTimeFieldType.MINUTE_OF_DAY_TYPE;
/*     */       case 19: 
/* 560 */         return DateTimeFieldType.MINUTE_OF_HOUR_TYPE;
/*     */       case 20: 
/* 562 */         return DateTimeFieldType.SECOND_OF_DAY_TYPE;
/*     */       case 21: 
/* 564 */         return DateTimeFieldType.SECOND_OF_MINUTE_TYPE;
/*     */       case 22: 
/* 566 */         return DateTimeFieldType.MILLIS_OF_DAY_TYPE;
/*     */       case 23: 
/* 568 */         return DateTimeFieldType.MILLIS_OF_SECOND_TYPE;
/*     */       }
/*     */       
/* 571 */       return this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\DateTimeFieldType.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */