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
/*     */ public final class Minutes
/*     */   extends BaseSingleFieldPeriod
/*     */ {
/*  43 */   public static final Minutes ZERO = new Minutes(0);
/*     */   
/*  45 */   public static final Minutes ONE = new Minutes(1);
/*     */   
/*  47 */   public static final Minutes TWO = new Minutes(2);
/*     */   
/*  49 */   public static final Minutes THREE = new Minutes(3);
/*     */   
/*  51 */   public static final Minutes MAX_VALUE = new Minutes(Integer.MAX_VALUE);
/*     */   
/*  53 */   public static final Minutes MIN_VALUE = new Minutes(Integer.MIN_VALUE);
/*     */   
/*     */ 
/*  56 */   private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.minutes());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final long serialVersionUID = 87525275727380863L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Minutes minutes(int paramInt)
/*     */   {
/*  70 */     switch (paramInt) {
/*     */     case 0: 
/*  72 */       return ZERO;
/*     */     case 1: 
/*  74 */       return ONE;
/*     */     case 2: 
/*  76 */       return TWO;
/*     */     case 3: 
/*  78 */       return THREE;
/*     */     case 2147483647: 
/*  80 */       return MAX_VALUE;
/*     */     case -2147483648: 
/*  82 */       return MIN_VALUE;
/*     */     }
/*  84 */     return new Minutes(paramInt);
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
/*     */   public static Minutes minutesBetween(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
/*     */   {
/*  99 */     int i = BaseSingleFieldPeriod.between(paramReadableInstant1, paramReadableInstant2, DurationFieldType.minutes());
/* 100 */     return minutes(i);
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
/*     */   public static Minutes minutesBetween(ReadablePartial paramReadablePartial1, ReadablePartial paramReadablePartial2)
/*     */   {
/* 116 */     if (((paramReadablePartial1 instanceof LocalTime)) && ((paramReadablePartial2 instanceof LocalTime))) {
/* 117 */       Chronology localChronology = DateTimeUtils.getChronology(paramReadablePartial1.getChronology());
/* 118 */       int j = localChronology.minutes().getDifference(((LocalTime)paramReadablePartial2).getLocalMillis(), ((LocalTime)paramReadablePartial1).getLocalMillis());
/*     */       
/* 120 */       return minutes(j);
/*     */     }
/* 122 */     int i = BaseSingleFieldPeriod.between(paramReadablePartial1, paramReadablePartial2, ZERO);
/* 123 */     return minutes(i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Minutes minutesIn(ReadableInterval paramReadableInterval)
/*     */   {
/* 135 */     if (paramReadableInterval == null) {
/* 136 */       return ZERO;
/*     */     }
/* 138 */     int i = BaseSingleFieldPeriod.between(paramReadableInterval.getStart(), paramReadableInterval.getEnd(), DurationFieldType.minutes());
/* 139 */     return minutes(i);
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
/*     */   public static Minutes standardMinutesIn(ReadablePeriod paramReadablePeriod)
/*     */   {
/* 163 */     int i = BaseSingleFieldPeriod.standardPeriodIn(paramReadablePeriod, 60000L);
/* 164 */     return minutes(i);
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
/*     */   public static Minutes parseMinutes(String paramString)
/*     */   {
/* 179 */     if (paramString == null) {
/* 180 */       return ZERO;
/*     */     }
/* 182 */     Period localPeriod = PARSER.parsePeriod(paramString);
/* 183 */     return minutes(localPeriod.getMinutes());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Minutes(int paramInt)
/*     */   {
/* 195 */     super(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 204 */     return minutes(getValue());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DurationFieldType getFieldType()
/*     */   {
/* 214 */     return DurationFieldType.minutes();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PeriodType getPeriodType()
/*     */   {
/* 223 */     return PeriodType.minutes();
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
/* 241 */     return Weeks.weeks(getValue() / 10080);
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
/*     */   public Days toStandardDays()
/*     */   {
/* 258 */     return Days.days(getValue() / 1440);
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
/*     */   public Hours toStandardHours()
/*     */   {
/* 274 */     return Hours.hours(getValue() / 60);
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
/* 291 */     return Seconds.seconds(FieldUtils.safeMultiply(getValue(), 60));
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
/* 309 */     long l = getValue();
/* 310 */     return new Duration(l * 60000L);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMinutes()
/*     */   {
/* 320 */     return getValue();
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
/*     */   public Minutes plus(int paramInt)
/*     */   {
/* 334 */     if (paramInt == 0) {
/* 335 */       return this;
/*     */     }
/* 337 */     return minutes(FieldUtils.safeAdd(getValue(), paramInt));
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
/*     */   public Minutes plus(Minutes paramMinutes)
/*     */   {
/* 350 */     if (paramMinutes == null) {
/* 351 */       return this;
/*     */     }
/* 353 */     return plus(paramMinutes.getValue());
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
/*     */   public Minutes minus(int paramInt)
/*     */   {
/* 367 */     return plus(FieldUtils.safeNegate(paramInt));
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
/*     */   public Minutes minus(Minutes paramMinutes)
/*     */   {
/* 380 */     if (paramMinutes == null) {
/* 381 */       return this;
/*     */     }
/* 383 */     return minus(paramMinutes.getValue());
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
/*     */   public Minutes multipliedBy(int paramInt)
/*     */   {
/* 397 */     return minutes(FieldUtils.safeMultiply(getValue(), paramInt));
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
/*     */   public Minutes dividedBy(int paramInt)
/*     */   {
/* 411 */     if (paramInt == 1) {
/* 412 */       return this;
/*     */     }
/* 414 */     return minutes(getValue() / paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Minutes negated()
/*     */   {
/* 425 */     return minutes(FieldUtils.safeNegate(getValue()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isGreaterThan(Minutes paramMinutes)
/*     */   {
/* 436 */     if (paramMinutes == null) {
/* 437 */       return getValue() > 0;
/*     */     }
/* 439 */     return getValue() > paramMinutes.getValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLessThan(Minutes paramMinutes)
/*     */   {
/* 449 */     if (paramMinutes == null) {
/* 450 */       return getValue() < 0;
/*     */     }
/* 452 */     return getValue() < paramMinutes.getValue();
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
/* 464 */     return "PT" + String.valueOf(getValue()) + "M";
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\Minutes.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */