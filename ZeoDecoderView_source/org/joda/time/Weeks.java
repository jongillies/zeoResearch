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
/*     */ public final class Weeks
/*     */   extends BaseSingleFieldPeriod
/*     */ {
/*  43 */   public static final Weeks ZERO = new Weeks(0);
/*     */   
/*  45 */   public static final Weeks ONE = new Weeks(1);
/*     */   
/*  47 */   public static final Weeks TWO = new Weeks(2);
/*     */   
/*  49 */   public static final Weeks THREE = new Weeks(3);
/*     */   
/*  51 */   public static final Weeks MAX_VALUE = new Weeks(Integer.MAX_VALUE);
/*     */   
/*  53 */   public static final Weeks MIN_VALUE = new Weeks(Integer.MIN_VALUE);
/*     */   
/*     */ 
/*  56 */   private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.weeks());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final long serialVersionUID = 87525275727380866L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Weeks weeks(int paramInt)
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
/*  84 */     return new Weeks(paramInt);
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
/*     */   public static Weeks weeksBetween(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
/*     */   {
/*  99 */     int i = BaseSingleFieldPeriod.between(paramReadableInstant1, paramReadableInstant2, DurationFieldType.weeks());
/* 100 */     return weeks(i);
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
/*     */   public static Weeks weeksBetween(ReadablePartial paramReadablePartial1, ReadablePartial paramReadablePartial2)
/*     */   {
/* 116 */     if (((paramReadablePartial1 instanceof LocalDate)) && ((paramReadablePartial2 instanceof LocalDate))) {
/* 117 */       Chronology localChronology = DateTimeUtils.getChronology(paramReadablePartial1.getChronology());
/* 118 */       int j = localChronology.weeks().getDifference(((LocalDate)paramReadablePartial2).getLocalMillis(), ((LocalDate)paramReadablePartial1).getLocalMillis());
/*     */       
/* 120 */       return weeks(j);
/*     */     }
/* 122 */     int i = BaseSingleFieldPeriod.between(paramReadablePartial1, paramReadablePartial2, ZERO);
/* 123 */     return weeks(i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Weeks weeksIn(ReadableInterval paramReadableInterval)
/*     */   {
/* 135 */     if (paramReadableInterval == null) {
/* 136 */       return ZERO;
/*     */     }
/* 138 */     int i = BaseSingleFieldPeriod.between(paramReadableInterval.getStart(), paramReadableInterval.getEnd(), DurationFieldType.weeks());
/* 139 */     return weeks(i);
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
/*     */   public static Weeks standardWeeksIn(ReadablePeriod paramReadablePeriod)
/*     */   {
/* 163 */     int i = BaseSingleFieldPeriod.standardPeriodIn(paramReadablePeriod, 604800000L);
/* 164 */     return weeks(i);
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
/*     */   public static Weeks parseWeeks(String paramString)
/*     */   {
/* 179 */     if (paramString == null) {
/* 180 */       return ZERO;
/*     */     }
/* 182 */     Period localPeriod = PARSER.parsePeriod(paramString);
/* 183 */     return weeks(localPeriod.getWeeks());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Weeks(int paramInt)
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
/* 204 */     return weeks(getValue());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DurationFieldType getFieldType()
/*     */   {
/* 214 */     return DurationFieldType.weeks();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PeriodType getPeriodType()
/*     */   {
/* 223 */     return PeriodType.weeks();
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
/*     */   public Days toStandardDays()
/*     */   {
/* 241 */     return Days.days(FieldUtils.safeMultiply(getValue(), 7));
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
/*     */   public Hours toStandardHours()
/*     */   {
/* 259 */     return Hours.hours(FieldUtils.safeMultiply(getValue(), 168));
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
/* 277 */     return Minutes.minutes(FieldUtils.safeMultiply(getValue(), 10080));
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
/*     */   public Seconds toStandardSeconds()
/*     */   {
/* 296 */     return Seconds.seconds(FieldUtils.safeMultiply(getValue(), 604800));
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
/*     */   public Duration toStandardDuration()
/*     */   {
/* 315 */     long l = getValue();
/* 316 */     return new Duration(l * 604800000L);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getWeeks()
/*     */   {
/* 326 */     return getValue();
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
/*     */   public Weeks plus(int paramInt)
/*     */   {
/* 340 */     if (paramInt == 0) {
/* 341 */       return this;
/*     */     }
/* 343 */     return weeks(FieldUtils.safeAdd(getValue(), paramInt));
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
/*     */   public Weeks plus(Weeks paramWeeks)
/*     */   {
/* 356 */     if (paramWeeks == null) {
/* 357 */       return this;
/*     */     }
/* 359 */     return plus(paramWeeks.getValue());
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
/*     */   public Weeks minus(int paramInt)
/*     */   {
/* 373 */     return plus(FieldUtils.safeNegate(paramInt));
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
/*     */   public Weeks minus(Weeks paramWeeks)
/*     */   {
/* 386 */     if (paramWeeks == null) {
/* 387 */       return this;
/*     */     }
/* 389 */     return minus(paramWeeks.getValue());
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
/*     */   public Weeks multipliedBy(int paramInt)
/*     */   {
/* 403 */     return weeks(FieldUtils.safeMultiply(getValue(), paramInt));
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
/*     */   public Weeks dividedBy(int paramInt)
/*     */   {
/* 417 */     if (paramInt == 1) {
/* 418 */       return this;
/*     */     }
/* 420 */     return weeks(getValue() / paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Weeks negated()
/*     */   {
/* 431 */     return weeks(FieldUtils.safeNegate(getValue()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isGreaterThan(Weeks paramWeeks)
/*     */   {
/* 442 */     if (paramWeeks == null) {
/* 443 */       return getValue() > 0;
/*     */     }
/* 445 */     return getValue() > paramWeeks.getValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLessThan(Weeks paramWeeks)
/*     */   {
/* 455 */     if (paramWeeks == null) {
/* 456 */       return getValue() < 0;
/*     */     }
/* 458 */     return getValue() < paramWeeks.getValue();
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
/* 470 */     return "P" + String.valueOf(getValue()) + "W";
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\Weeks.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */