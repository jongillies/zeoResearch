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
/*     */ public final class Days
/*     */   extends BaseSingleFieldPeriod
/*     */ {
/*  43 */   public static final Days ZERO = new Days(0);
/*     */   
/*  45 */   public static final Days ONE = new Days(1);
/*     */   
/*  47 */   public static final Days TWO = new Days(2);
/*     */   
/*  49 */   public static final Days THREE = new Days(3);
/*     */   
/*  51 */   public static final Days FOUR = new Days(4);
/*     */   
/*  53 */   public static final Days FIVE = new Days(5);
/*     */   
/*  55 */   public static final Days SIX = new Days(6);
/*     */   
/*  57 */   public static final Days SEVEN = new Days(7);
/*     */   
/*  59 */   public static final Days MAX_VALUE = new Days(Integer.MAX_VALUE);
/*     */   
/*  61 */   public static final Days MIN_VALUE = new Days(Integer.MIN_VALUE);
/*     */   
/*     */ 
/*  64 */   private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.days());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final long serialVersionUID = 87525275727380865L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Days days(int paramInt)
/*     */   {
/*  78 */     switch (paramInt) {
/*     */     case 0: 
/*  80 */       return ZERO;
/*     */     case 1: 
/*  82 */       return ONE;
/*     */     case 2: 
/*  84 */       return TWO;
/*     */     case 3: 
/*  86 */       return THREE;
/*     */     case 4: 
/*  88 */       return FOUR;
/*     */     case 5: 
/*  90 */       return FIVE;
/*     */     case 6: 
/*  92 */       return SIX;
/*     */     case 7: 
/*  94 */       return SEVEN;
/*     */     case 2147483647: 
/*  96 */       return MAX_VALUE;
/*     */     case -2147483648: 
/*  98 */       return MIN_VALUE;
/*     */     }
/* 100 */     return new Days(paramInt);
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
/*     */   public static Days daysBetween(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
/*     */   {
/* 116 */     int i = BaseSingleFieldPeriod.between(paramReadableInstant1, paramReadableInstant2, DurationFieldType.days());
/* 117 */     return days(i);
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
/*     */   public static Days daysBetween(ReadablePartial paramReadablePartial1, ReadablePartial paramReadablePartial2)
/*     */   {
/* 133 */     if (((paramReadablePartial1 instanceof LocalDate)) && ((paramReadablePartial2 instanceof LocalDate))) {
/* 134 */       Chronology localChronology = DateTimeUtils.getChronology(paramReadablePartial1.getChronology());
/* 135 */       int j = localChronology.days().getDifference(((LocalDate)paramReadablePartial2).getLocalMillis(), ((LocalDate)paramReadablePartial1).getLocalMillis());
/*     */       
/* 137 */       return days(j);
/*     */     }
/* 139 */     int i = BaseSingleFieldPeriod.between(paramReadablePartial1, paramReadablePartial2, ZERO);
/* 140 */     return days(i);
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
/*     */   public static Days daysIn(ReadableInterval paramReadableInterval)
/*     */   {
/* 153 */     if (paramReadableInterval == null) {
/* 154 */       return ZERO;
/*     */     }
/* 156 */     int i = BaseSingleFieldPeriod.between(paramReadableInterval.getStart(), paramReadableInterval.getEnd(), DurationFieldType.days());
/* 157 */     return days(i);
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
/*     */   public static Days standardDaysIn(ReadablePeriod paramReadablePeriod)
/*     */   {
/* 181 */     int i = BaseSingleFieldPeriod.standardPeriodIn(paramReadablePeriod, 86400000L);
/* 182 */     return days(i);
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
/*     */   public static Days parseDays(String paramString)
/*     */   {
/* 197 */     if (paramString == null) {
/* 198 */       return ZERO;
/*     */     }
/* 200 */     Period localPeriod = PARSER.parsePeriod(paramString);
/* 201 */     return days(localPeriod.getDays());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Days(int paramInt)
/*     */   {
/* 213 */     super(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 222 */     return days(getValue());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DurationFieldType getFieldType()
/*     */   {
/* 232 */     return DurationFieldType.days();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PeriodType getPeriodType()
/*     */   {
/* 241 */     return PeriodType.days();
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
/*     */   public Weeks toStandardWeeks()
/*     */   {
/* 258 */     return Weeks.weeks(getValue() / 7);
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
/*     */   public Hours toStandardHours()
/*     */   {
/* 275 */     return Hours.hours(FieldUtils.safeMultiply(getValue(), 24));
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
/*     */   public Minutes toStandardMinutes()
/*     */   {
/* 293 */     return Minutes.minutes(FieldUtils.safeMultiply(getValue(), 1440));
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
/*     */   public Seconds toStandardSeconds()
/*     */   {
/* 311 */     return Seconds.seconds(FieldUtils.safeMultiply(getValue(), 86400));
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
/* 329 */     long l = getValue();
/* 330 */     return new Duration(l * 86400000L);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDays()
/*     */   {
/* 340 */     return getValue();
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
/*     */   public Days plus(int paramInt)
/*     */   {
/* 354 */     if (paramInt == 0) {
/* 355 */       return this;
/*     */     }
/* 357 */     return days(FieldUtils.safeAdd(getValue(), paramInt));
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
/*     */   public Days plus(Days paramDays)
/*     */   {
/* 370 */     if (paramDays == null) {
/* 371 */       return this;
/*     */     }
/* 373 */     return plus(paramDays.getValue());
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
/*     */   public Days minus(int paramInt)
/*     */   {
/* 387 */     return plus(FieldUtils.safeNegate(paramInt));
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
/*     */   public Days minus(Days paramDays)
/*     */   {
/* 400 */     if (paramDays == null) {
/* 401 */       return this;
/*     */     }
/* 403 */     return minus(paramDays.getValue());
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
/*     */   public Days multipliedBy(int paramInt)
/*     */   {
/* 417 */     return days(FieldUtils.safeMultiply(getValue(), paramInt));
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
/*     */   public Days dividedBy(int paramInt)
/*     */   {
/* 431 */     if (paramInt == 1) {
/* 432 */       return this;
/*     */     }
/* 434 */     return days(getValue() / paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Days negated()
/*     */   {
/* 445 */     return days(FieldUtils.safeNegate(getValue()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isGreaterThan(Days paramDays)
/*     */   {
/* 456 */     if (paramDays == null) {
/* 457 */       return getValue() > 0;
/*     */     }
/* 459 */     return getValue() > paramDays.getValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLessThan(Days paramDays)
/*     */   {
/* 469 */     if (paramDays == null) {
/* 470 */       return getValue() < 0;
/*     */     }
/* 472 */     return getValue() < paramDays.getValue();
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
/* 484 */     return "P" + String.valueOf(getValue()) + "D";
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\Days.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */