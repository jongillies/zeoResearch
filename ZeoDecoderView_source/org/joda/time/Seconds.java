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
/*     */ public final class Seconds
/*     */   extends BaseSingleFieldPeriod
/*     */ {
/*  43 */   public static final Seconds ZERO = new Seconds(0);
/*     */   
/*  45 */   public static final Seconds ONE = new Seconds(1);
/*     */   
/*  47 */   public static final Seconds TWO = new Seconds(2);
/*     */   
/*  49 */   public static final Seconds THREE = new Seconds(3);
/*     */   
/*  51 */   public static final Seconds MAX_VALUE = new Seconds(Integer.MAX_VALUE);
/*     */   
/*  53 */   public static final Seconds MIN_VALUE = new Seconds(Integer.MIN_VALUE);
/*     */   
/*     */ 
/*  56 */   private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.seconds());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final long serialVersionUID = 87525275727380862L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Seconds seconds(int paramInt)
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
/*  84 */     return new Seconds(paramInt);
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
/*     */   public static Seconds secondsBetween(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
/*     */   {
/*  99 */     int i = BaseSingleFieldPeriod.between(paramReadableInstant1, paramReadableInstant2, DurationFieldType.seconds());
/* 100 */     return seconds(i);
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
/*     */   public static Seconds secondsBetween(ReadablePartial paramReadablePartial1, ReadablePartial paramReadablePartial2)
/*     */   {
/* 116 */     if (((paramReadablePartial1 instanceof LocalTime)) && ((paramReadablePartial2 instanceof LocalTime))) {
/* 117 */       Chronology localChronology = DateTimeUtils.getChronology(paramReadablePartial1.getChronology());
/* 118 */       int j = localChronology.seconds().getDifference(((LocalTime)paramReadablePartial2).getLocalMillis(), ((LocalTime)paramReadablePartial1).getLocalMillis());
/*     */       
/* 120 */       return seconds(j);
/*     */     }
/* 122 */     int i = BaseSingleFieldPeriod.between(paramReadablePartial1, paramReadablePartial2, ZERO);
/* 123 */     return seconds(i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Seconds secondsIn(ReadableInterval paramReadableInterval)
/*     */   {
/* 135 */     if (paramReadableInterval == null) {
/* 136 */       return ZERO;
/*     */     }
/* 138 */     int i = BaseSingleFieldPeriod.between(paramReadableInterval.getStart(), paramReadableInterval.getEnd(), DurationFieldType.seconds());
/* 139 */     return seconds(i);
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
/*     */   public static Seconds standardSecondsIn(ReadablePeriod paramReadablePeriod)
/*     */   {
/* 163 */     int i = BaseSingleFieldPeriod.standardPeriodIn(paramReadablePeriod, 1000L);
/* 164 */     return seconds(i);
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
/*     */   public static Seconds parseSeconds(String paramString)
/*     */   {
/* 179 */     if (paramString == null) {
/* 180 */       return ZERO;
/*     */     }
/* 182 */     Period localPeriod = PARSER.parsePeriod(paramString);
/* 183 */     return seconds(localPeriod.getSeconds());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Seconds(int paramInt)
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
/* 204 */     return seconds(getValue());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DurationFieldType getFieldType()
/*     */   {
/* 214 */     return DurationFieldType.seconds();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PeriodType getPeriodType()
/*     */   {
/* 223 */     return PeriodType.seconds();
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
/*     */   public Weeks toStandardWeeks()
/*     */   {
/* 242 */     return Weeks.weeks(getValue() / 604800);
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
/* 259 */     return Days.days(getValue() / 86400);
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
/* 275 */     return Hours.hours(getValue() / 3600);
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
/* 291 */     return Minutes.minutes(getValue() / 60);
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
/* 310 */     return new Duration(l * 1000L);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeconds()
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
/*     */   public Seconds plus(int paramInt)
/*     */   {
/* 334 */     if (paramInt == 0) {
/* 335 */       return this;
/*     */     }
/* 337 */     return seconds(FieldUtils.safeAdd(getValue(), paramInt));
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
/*     */   public Seconds plus(Seconds paramSeconds)
/*     */   {
/* 350 */     if (paramSeconds == null) {
/* 351 */       return this;
/*     */     }
/* 353 */     return plus(paramSeconds.getValue());
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
/*     */   public Seconds minus(int paramInt)
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
/*     */   public Seconds minus(Seconds paramSeconds)
/*     */   {
/* 380 */     if (paramSeconds == null) {
/* 381 */       return this;
/*     */     }
/* 383 */     return minus(paramSeconds.getValue());
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
/*     */   public Seconds multipliedBy(int paramInt)
/*     */   {
/* 397 */     return seconds(FieldUtils.safeMultiply(getValue(), paramInt));
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
/*     */   public Seconds dividedBy(int paramInt)
/*     */   {
/* 411 */     if (paramInt == 1) {
/* 412 */       return this;
/*     */     }
/* 414 */     return seconds(getValue() / paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Seconds negated()
/*     */   {
/* 425 */     return seconds(FieldUtils.safeNegate(getValue()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isGreaterThan(Seconds paramSeconds)
/*     */   {
/* 436 */     if (paramSeconds == null) {
/* 437 */       return getValue() > 0;
/*     */     }
/* 439 */     return getValue() > paramSeconds.getValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLessThan(Seconds paramSeconds)
/*     */   {
/* 449 */     if (paramSeconds == null) {
/* 450 */       return getValue() < 0;
/*     */     }
/* 452 */     return getValue() < paramSeconds.getValue();
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
/* 464 */     return "PT" + String.valueOf(getValue()) + "S";
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\Seconds.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */