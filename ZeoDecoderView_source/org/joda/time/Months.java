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
/*     */ public final class Months
/*     */   extends BaseSingleFieldPeriod
/*     */ {
/*  43 */   public static final Months ZERO = new Months(0);
/*     */   
/*  45 */   public static final Months ONE = new Months(1);
/*     */   
/*  47 */   public static final Months TWO = new Months(2);
/*     */   
/*  49 */   public static final Months THREE = new Months(3);
/*     */   
/*  51 */   public static final Months FOUR = new Months(4);
/*     */   
/*  53 */   public static final Months FIVE = new Months(5);
/*     */   
/*  55 */   public static final Months SIX = new Months(6);
/*     */   
/*  57 */   public static final Months SEVEN = new Months(7);
/*     */   
/*  59 */   public static final Months EIGHT = new Months(8);
/*     */   
/*  61 */   public static final Months NINE = new Months(9);
/*     */   
/*  63 */   public static final Months TEN = new Months(10);
/*     */   
/*  65 */   public static final Months ELEVEN = new Months(11);
/*     */   
/*  67 */   public static final Months TWELVE = new Months(12);
/*     */   
/*  69 */   public static final Months MAX_VALUE = new Months(Integer.MAX_VALUE);
/*     */   
/*  71 */   public static final Months MIN_VALUE = new Months(Integer.MIN_VALUE);
/*     */   
/*     */ 
/*  74 */   private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.months());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final long serialVersionUID = 87525275727380867L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Months months(int paramInt)
/*     */   {
/*  88 */     switch (paramInt) {
/*     */     case 0: 
/*  90 */       return ZERO;
/*     */     case 1: 
/*  92 */       return ONE;
/*     */     case 2: 
/*  94 */       return TWO;
/*     */     case 3: 
/*  96 */       return THREE;
/*     */     case 4: 
/*  98 */       return FOUR;
/*     */     case 5: 
/* 100 */       return FIVE;
/*     */     case 6: 
/* 102 */       return SIX;
/*     */     case 7: 
/* 104 */       return SEVEN;
/*     */     case 8: 
/* 106 */       return EIGHT;
/*     */     case 9: 
/* 108 */       return NINE;
/*     */     case 10: 
/* 110 */       return TEN;
/*     */     case 11: 
/* 112 */       return ELEVEN;
/*     */     case 12: 
/* 114 */       return TWELVE;
/*     */     case 2147483647: 
/* 116 */       return MAX_VALUE;
/*     */     case -2147483648: 
/* 118 */       return MIN_VALUE;
/*     */     }
/* 120 */     return new Months(paramInt);
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
/*     */   public static Months monthsBetween(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
/*     */   {
/* 136 */     int i = BaseSingleFieldPeriod.between(paramReadableInstant1, paramReadableInstant2, DurationFieldType.months());
/* 137 */     return months(i);
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
/*     */   public static Months monthsBetween(ReadablePartial paramReadablePartial1, ReadablePartial paramReadablePartial2)
/*     */   {
/* 153 */     if (((paramReadablePartial1 instanceof LocalDate)) && ((paramReadablePartial2 instanceof LocalDate))) {
/* 154 */       Chronology localChronology = DateTimeUtils.getChronology(paramReadablePartial1.getChronology());
/* 155 */       int j = localChronology.months().getDifference(((LocalDate)paramReadablePartial2).getLocalMillis(), ((LocalDate)paramReadablePartial1).getLocalMillis());
/*     */       
/* 157 */       return months(j);
/*     */     }
/* 159 */     int i = BaseSingleFieldPeriod.between(paramReadablePartial1, paramReadablePartial2, ZERO);
/* 160 */     return months(i);
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
/*     */   public static Months monthsIn(ReadableInterval paramReadableInterval)
/*     */   {
/* 173 */     if (paramReadableInterval == null) {
/* 174 */       return ZERO;
/*     */     }
/* 176 */     int i = BaseSingleFieldPeriod.between(paramReadableInterval.getStart(), paramReadableInterval.getEnd(), DurationFieldType.months());
/* 177 */     return months(i);
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
/*     */   public static Months parseMonths(String paramString)
/*     */   {
/* 192 */     if (paramString == null) {
/* 193 */       return ZERO;
/*     */     }
/* 195 */     Period localPeriod = PARSER.parsePeriod(paramString);
/* 196 */     return months(localPeriod.getMonths());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Months(int paramInt)
/*     */   {
/* 208 */     super(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 217 */     return months(getValue());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DurationFieldType getFieldType()
/*     */   {
/* 227 */     return DurationFieldType.months();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PeriodType getPeriodType()
/*     */   {
/* 236 */     return PeriodType.months();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMonths()
/*     */   {
/* 246 */     return getValue();
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
/*     */   public Months plus(int paramInt)
/*     */   {
/* 260 */     if (paramInt == 0) {
/* 261 */       return this;
/*     */     }
/* 263 */     return months(FieldUtils.safeAdd(getValue(), paramInt));
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
/*     */   public Months plus(Months paramMonths)
/*     */   {
/* 276 */     if (paramMonths == null) {
/* 277 */       return this;
/*     */     }
/* 279 */     return plus(paramMonths.getValue());
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
/*     */   public Months minus(int paramInt)
/*     */   {
/* 293 */     return plus(FieldUtils.safeNegate(paramInt));
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
/*     */   public Months minus(Months paramMonths)
/*     */   {
/* 306 */     if (paramMonths == null) {
/* 307 */       return this;
/*     */     }
/* 309 */     return minus(paramMonths.getValue());
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
/*     */   public Months multipliedBy(int paramInt)
/*     */   {
/* 323 */     return months(FieldUtils.safeMultiply(getValue(), paramInt));
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
/*     */   public Months dividedBy(int paramInt)
/*     */   {
/* 337 */     if (paramInt == 1) {
/* 338 */       return this;
/*     */     }
/* 340 */     return months(getValue() / paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Months negated()
/*     */   {
/* 351 */     return months(FieldUtils.safeNegate(getValue()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isGreaterThan(Months paramMonths)
/*     */   {
/* 362 */     if (paramMonths == null) {
/* 363 */       return getValue() > 0;
/*     */     }
/* 365 */     return getValue() > paramMonths.getValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLessThan(Months paramMonths)
/*     */   {
/* 375 */     if (paramMonths == null) {
/* 376 */       return getValue() < 0;
/*     */     }
/* 378 */     return getValue() < paramMonths.getValue();
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
/* 390 */     return "P" + String.valueOf(getValue()) + "M";
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\Months.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */