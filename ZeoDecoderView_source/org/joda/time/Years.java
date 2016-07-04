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
/*     */ public final class Years
/*     */   extends BaseSingleFieldPeriod
/*     */ {
/*  43 */   public static final Years ZERO = new Years(0);
/*     */   
/*  45 */   public static final Years ONE = new Years(1);
/*     */   
/*  47 */   public static final Years TWO = new Years(2);
/*     */   
/*  49 */   public static final Years THREE = new Years(3);
/*     */   
/*  51 */   public static final Years MAX_VALUE = new Years(Integer.MAX_VALUE);
/*     */   
/*  53 */   public static final Years MIN_VALUE = new Years(Integer.MIN_VALUE);
/*     */   
/*     */ 
/*  56 */   private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.years());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final long serialVersionUID = 87525275727380868L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Years years(int paramInt)
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
/*  84 */     return new Years(paramInt);
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
/*     */   public static Years yearsBetween(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
/*     */   {
/* 100 */     int i = BaseSingleFieldPeriod.between(paramReadableInstant1, paramReadableInstant2, DurationFieldType.years());
/* 101 */     return years(i);
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
/*     */   public static Years yearsBetween(ReadablePartial paramReadablePartial1, ReadablePartial paramReadablePartial2)
/*     */   {
/* 117 */     if (((paramReadablePartial1 instanceof LocalDate)) && ((paramReadablePartial2 instanceof LocalDate))) {
/* 118 */       Chronology localChronology = DateTimeUtils.getChronology(paramReadablePartial1.getChronology());
/* 119 */       int j = localChronology.years().getDifference(((LocalDate)paramReadablePartial2).getLocalMillis(), ((LocalDate)paramReadablePartial1).getLocalMillis());
/*     */       
/* 121 */       return years(j);
/*     */     }
/* 123 */     int i = BaseSingleFieldPeriod.between(paramReadablePartial1, paramReadablePartial2, ZERO);
/* 124 */     return years(i);
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
/*     */   public static Years yearsIn(ReadableInterval paramReadableInterval)
/*     */   {
/* 137 */     if (paramReadableInterval == null) {
/* 138 */       return ZERO;
/*     */     }
/* 140 */     int i = BaseSingleFieldPeriod.between(paramReadableInterval.getStart(), paramReadableInterval.getEnd(), DurationFieldType.years());
/* 141 */     return years(i);
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
/*     */   public static Years parseYears(String paramString)
/*     */   {
/* 156 */     if (paramString == null) {
/* 157 */       return ZERO;
/*     */     }
/* 159 */     Period localPeriod = PARSER.parsePeriod(paramString);
/* 160 */     return years(localPeriod.getYears());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Years(int paramInt)
/*     */   {
/* 172 */     super(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 181 */     return years(getValue());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DurationFieldType getFieldType()
/*     */   {
/* 191 */     return DurationFieldType.years();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PeriodType getPeriodType()
/*     */   {
/* 200 */     return PeriodType.years();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getYears()
/*     */   {
/* 210 */     return getValue();
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
/*     */   public Years plus(int paramInt)
/*     */   {
/* 224 */     if (paramInt == 0) {
/* 225 */       return this;
/*     */     }
/* 227 */     return years(FieldUtils.safeAdd(getValue(), paramInt));
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
/*     */   public Years plus(Years paramYears)
/*     */   {
/* 240 */     if (paramYears == null) {
/* 241 */       return this;
/*     */     }
/* 243 */     return plus(paramYears.getValue());
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
/*     */   public Years minus(int paramInt)
/*     */   {
/* 257 */     return plus(FieldUtils.safeNegate(paramInt));
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
/*     */   public Years minus(Years paramYears)
/*     */   {
/* 270 */     if (paramYears == null) {
/* 271 */       return this;
/*     */     }
/* 273 */     return minus(paramYears.getValue());
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
/*     */   public Years multipliedBy(int paramInt)
/*     */   {
/* 287 */     return years(FieldUtils.safeMultiply(getValue(), paramInt));
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
/*     */   public Years dividedBy(int paramInt)
/*     */   {
/* 301 */     if (paramInt == 1) {
/* 302 */       return this;
/*     */     }
/* 304 */     return years(getValue() / paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Years negated()
/*     */   {
/* 315 */     return years(FieldUtils.safeNegate(getValue()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isGreaterThan(Years paramYears)
/*     */   {
/* 326 */     if (paramYears == null) {
/* 327 */       return getValue() > 0;
/*     */     }
/* 329 */     return getValue() > paramYears.getValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLessThan(Years paramYears)
/*     */   {
/* 339 */     if (paramYears == null) {
/* 340 */       return getValue() < 0;
/*     */     }
/* 342 */     return getValue() < paramYears.getValue();
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
/* 354 */     return "P" + String.valueOf(getValue()) + "Y";
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\Years.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */