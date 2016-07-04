/*     */ package org.joda.time;
/*     */ 
/*     */ import org.joda.time.base.BaseSingleFieldPeriod;
/*     */ import org.joda.time.field.FieldUtils;
/*     */ import org.joda.time.format.ISOPeriodFormat;
/*     */ import org.joda.time.format.PeriodFormatter;
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
/*     */ public final class Hours
/*     */   extends BaseSingleFieldPeriod
/*     */ {
/*  43 */   public static final Hours ZERO = new Hours(0);
/*     */   
/*  45 */   public static final Hours ONE = new Hours(1);
/*     */   
/*  47 */   public static final Hours TWO = new Hours(2);
/*     */   
/*  49 */   public static final Hours THREE = new Hours(3);
/*     */   
/*  51 */   public static final Hours FOUR = new Hours(4);
/*     */   
/*  53 */   public static final Hours FIVE = new Hours(5);
/*     */   
/*  55 */   public static final Hours SIX = new Hours(6);
/*     */   
/*  57 */   public static final Hours SEVEN = new Hours(7);
/*     */   
/*  59 */   public static final Hours EIGHT = new Hours(8);
/*     */   
/*  61 */   public static final Hours MAX_VALUE = new Hours(Integer.MAX_VALUE);
/*     */   
/*  63 */   public static final Hours MIN_VALUE = new Hours(Integer.MIN_VALUE);
/*     */   
/*     */ 
/*  66 */   private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.hours());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final long serialVersionUID = 87525275727380864L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Hours hours(int paramInt)
/*     */   {
/*  80 */     switch (paramInt) {
/*     */     case 0: 
/*  82 */       return ZERO;
/*     */     case 1: 
/*  84 */       return ONE;
/*     */     case 2: 
/*  86 */       return TWO;
/*     */     case 3: 
/*  88 */       return THREE;
/*     */     case 4: 
/*  90 */       return FOUR;
/*     */     case 5: 
/*  92 */       return FIVE;
/*     */     case 6: 
/*  94 */       return SIX;
/*     */     case 7: 
/*  96 */       return SEVEN;
/*     */     case 8: 
/*  98 */       return EIGHT;
/*     */     case 2147483647: 
/* 100 */       return MAX_VALUE;
/*     */     case -2147483648: 
/* 102 */       return MIN_VALUE;
/*     */     }
/* 104 */     return new Hours(paramInt);
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
/*     */   public static Hours hoursBetween(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
/*     */   {
/* 119 */     int i = BaseSingleFieldPeriod.between(paramReadableInstant1, paramReadableInstant2, DurationFieldType.hours());
/* 120 */     return hours(i);
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
/*     */ 
/*     */   public static Hours hoursBetween(ReadablePartial paramReadablePartial1, ReadablePartial paramReadablePartial2)
/*     */   {
/* 136 */     if (((paramReadablePartial1 instanceof LocalTime)) && ((paramReadablePartial2 instanceof LocalTime))) {
/* 137 */       Chronology localChronology = DateTimeUtils.getChronology(paramReadablePartial1.getChronology());
/* 138 */       int j = localChronology.hours().getDifference(((LocalTime)paramReadablePartial2).getLocalMillis(), ((LocalTime)paramReadablePartial1).getLocalMillis());
/*     */       
/* 140 */       return hours(j);
/*     */     }
/* 142 */     int i = BaseSingleFieldPeriod.between(paramReadablePartial1, paramReadablePartial2, ZERO);
/* 143 */     return hours(i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Hours hoursIn(ReadableInterval paramReadableInterval)
/*     */   {
/* 155 */     if (paramReadableInterval == null) {
/* 156 */       return ZERO;
/*     */     }
/* 158 */     int i = BaseSingleFieldPeriod.between(paramReadableInterval.getStart(), paramReadableInterval.getEnd(), DurationFieldType.hours());
/* 159 */     return hours(i);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Hours standardHoursIn(ReadablePeriod paramReadablePeriod)
/*     */   {
/* 183 */     int i = BaseSingleFieldPeriod.standardPeriodIn(paramReadablePeriod, 3600000L);
/* 184 */     return hours(i);
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
/*     */   public static Hours parseHours(String paramString)
/*     */   {
/* 199 */     if (paramString == null) {
/* 200 */       return ZERO;
/*     */     }
/* 202 */     Period localPeriod = PARSER.parsePeriod(paramString);
/* 203 */     return hours(localPeriod.getHours());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Hours(int paramInt)
/*     */   {
/* 215 */     super(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 224 */     return hours(getValue());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DurationFieldType getFieldType()
/*     */   {
/* 234 */     return DurationFieldType.hours();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PeriodType getPeriodType()
/*     */   {
/* 243 */     return PeriodType.hours();
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public Weeks toStandardWeeks()
/*     */   {
/* 261 */     return Weeks.weeks(getValue() / 168);
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
/*     */ 
/*     */   public Days toStandardDays()
/*     */   {
/* 277 */     return Days.days(getValue() / 24);
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
/*     */ 
/*     */   public Minutes toStandardMinutes()
/*     */   {
/* 293 */     return Minutes.minutes(FieldUtils.safeMultiply(getValue(), 60));
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
/*     */ 
/*     */ 
/*     */   public Seconds toStandardSeconds()
/*     */   {
/* 310 */     return Seconds.seconds(FieldUtils.safeMultiply(getValue(), 3600));
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public Duration toStandardDuration()
/*     */   {
/* 328 */     long l = getValue();
/* 329 */     return new Duration(l * 3600000L);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getHours()
/*     */   {
/* 339 */     return getValue();
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
/*     */   public Hours plus(int paramInt)
/*     */   {
/* 353 */     if (paramInt == 0) {
/* 354 */       return this;
/*     */     }
/* 356 */     return hours(FieldUtils.safeAdd(getValue(), paramInt));
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
/*     */   public Hours plus(Hours paramHours)
/*     */   {
/* 369 */     if (paramHours == null) {
/* 370 */       return this;
/*     */     }
/* 372 */     return plus(paramHours.getValue());
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
/*     */   public Hours minus(int paramInt)
/*     */   {
/* 386 */     return plus(FieldUtils.safeNegate(paramInt));
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
/*     */   public Hours minus(Hours paramHours)
/*     */   {
/* 399 */     if (paramHours == null) {
/* 400 */       return this;
/*     */     }
/* 402 */     return minus(paramHours.getValue());
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
/*     */   public Hours multipliedBy(int paramInt)
/*     */   {
/* 416 */     return hours(FieldUtils.safeMultiply(getValue(), paramInt));
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
/*     */   public Hours dividedBy(int paramInt)
/*     */   {
/* 430 */     if (paramInt == 1) {
/* 431 */       return this;
/*     */     }
/* 433 */     return hours(getValue() / paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Hours negated()
/*     */   {
/* 444 */     return hours(FieldUtils.safeNegate(getValue()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isGreaterThan(Hours paramHours)
/*     */   {
/* 455 */     if (paramHours == null) {
/* 456 */       return getValue() > 0;
/*     */     }
/* 458 */     return getValue() > paramHours.getValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLessThan(Hours paramHours)
/*     */   {
/* 468 */     if (paramHours == null) {
/* 469 */       return getValue() < 0;
/*     */     }
/* 471 */     return getValue() < paramHours.getValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 483 */     return "PT" + String.valueOf(getValue()) + "H";
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\Hours.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */