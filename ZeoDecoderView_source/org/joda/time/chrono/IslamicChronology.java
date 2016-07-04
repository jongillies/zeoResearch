/*     */ package org.joda.time.chrono;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTime;
/*     */ import org.joda.time.DateTimeField;
/*     */ import org.joda.time.DateTimeZone;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class IslamicChronology
/*     */   extends BasicChronology
/*     */ {
/*     */   private static final long serialVersionUID = -3663823829888L;
/*     */   public static final int AH = 1;
/*  78 */   private static final DateTimeField ERA_FIELD = new BasicSingleEraDateTimeField("AH");
/*     */   
/*     */ 
/*  81 */   public static final LeapYearPatternType LEAP_YEAR_15_BASED = new LeapYearPatternType(0, 623158436);
/*     */   
/*  83 */   public static final LeapYearPatternType LEAP_YEAR_16_BASED = new LeapYearPatternType(1, 623191204);
/*     */   
/*  85 */   public static final LeapYearPatternType LEAP_YEAR_INDIAN = new LeapYearPatternType(2, 690562340);
/*     */   
/*  87 */   public static final LeapYearPatternType LEAP_YEAR_HABASH_AL_HASIB = new LeapYearPatternType(3, 153692453);
/*     */   
/*     */ 
/*     */ 
/*     */   private static final int MIN_YEAR = -292269337;
/*     */   
/*     */ 
/*     */ 
/*     */   private static final int MAX_YEAR = 292271022;
/*     */   
/*     */ 
/*     */ 
/*     */   private static final int MONTH_PAIR_LENGTH = 59;
/*     */   
/*     */ 
/*     */ 
/*     */   private static final int LONG_MONTH_LENGTH = 30;
/*     */   
/*     */ 
/*     */ 
/*     */   private static final int SHORT_MONTH_LENGTH = 29;
/*     */   
/*     */ 
/*     */ 
/*     */   private static final long MILLIS_PER_MONTH_PAIR = 5097600000L;
/*     */   
/*     */ 
/*     */ 
/*     */   private static final long MILLIS_PER_MONTH = 2551440384L;
/*     */   
/*     */ 
/*     */ 
/*     */   private static final long MILLIS_PER_LONG_MONTH = 2592000000L;
/*     */   
/*     */ 
/*     */ 
/*     */   private static final long MILLIS_PER_YEAR = 30617280288L;
/*     */   
/*     */ 
/*     */ 
/*     */   private static final long MILLIS_PER_SHORT_YEAR = 30585600000L;
/*     */   
/*     */ 
/*     */   private static final long MILLIS_PER_LONG_YEAR = 30672000000L;
/*     */   
/*     */ 
/*     */   private static final long MILLIS_YEAR_1 = -42521587200000L;
/*     */   
/*     */ 
/*     */   private static final int CYCLE = 30;
/*     */   
/*     */ 
/*     */   private static final long MILLIS_PER_CYCLE = 918518400000L;
/*     */   
/*     */ 
/* 142 */   private static final Map cCache = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 148 */   private static final IslamicChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final LeapYearPatternType iLeapYears;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static IslamicChronology getInstanceUTC()
/*     */   {
/* 162 */     return INSTANCE_UTC;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static IslamicChronology getInstance()
/*     */   {
/* 171 */     return getInstance(DateTimeZone.getDefault(), LEAP_YEAR_16_BASED);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static IslamicChronology getInstance(DateTimeZone paramDateTimeZone)
/*     */   {
/* 181 */     return getInstance(paramDateTimeZone, LEAP_YEAR_16_BASED);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static IslamicChronology getInstance(DateTimeZone paramDateTimeZone, LeapYearPatternType paramLeapYearPatternType)
/*     */   {
/* 192 */     if (paramDateTimeZone == null) {
/* 193 */       paramDateTimeZone = DateTimeZone.getDefault();
/*     */     }
/*     */     IslamicChronology localIslamicChronology;
/* 196 */     synchronized (cCache) {
/* 197 */       IslamicChronology[] arrayOfIslamicChronology = (IslamicChronology[])cCache.get(paramDateTimeZone);
/* 198 */       if (arrayOfIslamicChronology == null) {
/* 199 */         arrayOfIslamicChronology = new IslamicChronology[4];
/* 200 */         cCache.put(paramDateTimeZone, arrayOfIslamicChronology);
/*     */       }
/* 202 */       localIslamicChronology = arrayOfIslamicChronology[paramLeapYearPatternType.index];
/* 203 */       if (localIslamicChronology == null) {
/* 204 */         if (paramDateTimeZone == DateTimeZone.UTC)
/*     */         {
/* 206 */           localIslamicChronology = new IslamicChronology(null, null, paramLeapYearPatternType);
/*     */           
/* 208 */           DateTime localDateTime = new DateTime(1, 1, 1, 0, 0, 0, 0, localIslamicChronology);
/* 209 */           localIslamicChronology = new IslamicChronology(LimitChronology.getInstance(localIslamicChronology, localDateTime, null), null, paramLeapYearPatternType);
/*     */         }
/*     */         else
/*     */         {
/* 213 */           localIslamicChronology = getInstance(DateTimeZone.UTC, paramLeapYearPatternType);
/* 214 */           localIslamicChronology = new IslamicChronology(ZonedChronology.getInstance(localIslamicChronology, paramDateTimeZone), null, paramLeapYearPatternType);
/*     */         }
/*     */         
/* 217 */         arrayOfIslamicChronology[paramLeapYearPatternType.index] = localIslamicChronology;
/*     */       }
/*     */     }
/* 220 */     return localIslamicChronology;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   IslamicChronology(Chronology paramChronology, Object paramObject, LeapYearPatternType paramLeapYearPatternType)
/*     */   {
/* 229 */     super(paramChronology, paramObject, 4);
/* 230 */     this.iLeapYears = paramLeapYearPatternType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 237 */     Chronology localChronology = getBase();
/* 238 */     return localChronology == null ? getInstanceUTC() : getInstance(localChronology.getZone());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LeapYearPatternType getLeapYearPatternType()
/*     */   {
/* 248 */     return this.iLeapYears;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Chronology withUTC()
/*     */   {
/* 259 */     return INSTANCE_UTC;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Chronology withZone(DateTimeZone paramDateTimeZone)
/*     */   {
/* 269 */     if (paramDateTimeZone == null) {
/* 270 */       paramDateTimeZone = DateTimeZone.getDefault();
/*     */     }
/* 272 */     if (paramDateTimeZone == getZone()) {
/* 273 */       return this;
/*     */     }
/* 275 */     return getInstance(paramDateTimeZone);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 285 */     return super.hashCode() * 13 + getLeapYearPatternType().hashCode();
/*     */   }
/*     */   
/*     */   int getYear(long paramLong)
/*     */   {
/* 290 */     long l1 = paramLong - -42521587200000L;
/* 291 */     long l2 = l1 / 918518400000L;
/* 292 */     long l3 = l1 % 918518400000L;
/*     */     
/* 294 */     int i = (int)(l2 * 30L + 1L);
/* 295 */     long l4 = isLeapYear(i) ? 30672000000L : 30585600000L;
/* 296 */     while (l3 >= l4) {
/* 297 */       l3 -= l4;
/* 298 */       l4 = isLeapYear(++i) ? 30672000000L : 30585600000L;
/*     */     }
/* 300 */     return i;
/*     */   }
/*     */   
/*     */   long setYear(long paramLong, int paramInt)
/*     */   {
/* 305 */     int i = getYear(paramLong);
/* 306 */     int j = getDayOfYear(paramLong, i);
/* 307 */     int k = getMillisOfDay(paramLong);
/*     */     
/* 309 */     if (j > 354)
/*     */     {
/* 311 */       if (!isLeapYear(paramInt))
/*     */       {
/* 313 */         j--;
/*     */       }
/*     */     }
/*     */     
/* 317 */     paramLong = getYearMonthDayMillis(paramInt, 1, j);
/* 318 */     paramLong += k;
/* 319 */     return paramLong;
/*     */   }
/*     */   
/*     */ 
/*     */   long getYearDifference(long paramLong1, long paramLong2)
/*     */   {
/* 325 */     int i = getYear(paramLong1);
/* 326 */     int j = getYear(paramLong2);
/*     */     
/*     */ 
/* 329 */     long l1 = paramLong1 - getYearMillis(i);
/* 330 */     long l2 = paramLong2 - getYearMillis(j);
/*     */     
/* 332 */     int k = i - j;
/* 333 */     if (l1 < l2) {
/* 334 */       k--;
/*     */     }
/* 336 */     return k;
/*     */   }
/*     */   
/*     */   long getTotalMillisByYearMonth(int paramInt1, int paramInt2) {
/*     */     
/* 341 */     if (paramInt2 % 2 == 1) {
/* 342 */       paramInt2 /= 2;
/* 343 */       return paramInt2 * 5097600000L + 2592000000L;
/*     */     }
/* 345 */     paramInt2 /= 2;
/* 346 */     return paramInt2 * 5097600000L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   int getDayOfMonth(long paramLong)
/*     */   {
/* 353 */     int i = getDayOfYear(paramLong) - 1;
/* 354 */     if (i == 354) {
/* 355 */       return 30;
/*     */     }
/* 357 */     return i % 59 % 30 + 1;
/*     */   }
/*     */   
/*     */   boolean isLeapYear(int paramInt)
/*     */   {
/* 362 */     return this.iLeapYears.isLeapYear(paramInt);
/*     */   }
/*     */   
/*     */   int getDaysInYearMax()
/*     */   {
/* 367 */     return 355;
/*     */   }
/*     */   
/*     */   int getDaysInYear(int paramInt)
/*     */   {
/* 372 */     return isLeapYear(paramInt) ? 355 : 354;
/*     */   }
/*     */   
/*     */   int getDaysInYearMonth(int paramInt1, int paramInt2)
/*     */   {
/* 377 */     if ((paramInt2 == 12) && (isLeapYear(paramInt1))) {
/* 378 */       return 30;
/*     */     }
/* 380 */     paramInt2--;return paramInt2 % 2 == 0 ? 30 : 29;
/*     */   }
/*     */   
/*     */   int getDaysInMonthMax()
/*     */   {
/* 385 */     return 30;
/*     */   }
/*     */   
/*     */   int getDaysInMonthMax(int paramInt)
/*     */   {
/* 390 */     if (paramInt == 12) {
/* 391 */       return 30;
/*     */     }
/* 393 */     paramInt--;return paramInt % 2 == 0 ? 30 : 29;
/*     */   }
/*     */   
/*     */   int getMonthOfYear(long paramLong, int paramInt)
/*     */   {
/* 398 */     int i = (int)((paramLong - getYearMillis(paramInt)) / 86400000L);
/* 399 */     if (i == 354) {
/* 400 */       return 12;
/*     */     }
/* 402 */     return i * 2 / 59 + 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   long getAverageMillisPerYear()
/*     */   {
/* 412 */     return 30617280288L;
/*     */   }
/*     */   
/*     */   long getAverageMillisPerYearDividedByTwo()
/*     */   {
/* 417 */     return 15308640144L;
/*     */   }
/*     */   
/*     */   long getAverageMillisPerMonth()
/*     */   {
/* 422 */     return 2551440384L;
/*     */   }
/*     */   
/*     */   long calculateFirstDayOfYearMillis(int paramInt)
/*     */   {
/* 427 */     if (paramInt > 292271022) {
/* 428 */       throw new ArithmeticException("Year is too large: " + paramInt + " > " + 292271022);
/*     */     }
/* 430 */     if (paramInt < -292269337) {
/* 431 */       throw new ArithmeticException("Year is too small: " + paramInt + " < " + -292269337);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 438 */     paramInt--;
/* 439 */     long l1 = paramInt / 30;
/* 440 */     long l2 = -42521587200000L + l1 * 918518400000L;
/* 441 */     int i = paramInt % 30 + 1;
/*     */     
/* 443 */     for (int j = 1; j < i; j++) {
/* 444 */       l2 += (isLeapYear(j) ? 30672000000L : 30585600000L);
/*     */     }
/*     */     
/* 447 */     return l2;
/*     */   }
/*     */   
/*     */   int getMinYear()
/*     */   {
/* 452 */     return 1;
/*     */   }
/*     */   
/*     */   int getMaxYear()
/*     */   {
/* 457 */     return 292271022;
/*     */   }
/*     */   
/*     */ 
/*     */   long getApproxMillisAtEpochDividedByTwo()
/*     */   {
/* 463 */     return 21260793600000L;
/*     */   }
/*     */   
/*     */   protected void assemble(AssembledChronology.Fields paramFields)
/*     */   {
/* 468 */     if (getBase() == null) {
/* 469 */       super.assemble(paramFields);
/*     */       
/* 471 */       paramFields.era = ERA_FIELD;
/* 472 */       paramFields.monthOfYear = new BasicMonthOfYearDateTimeField(this, 12);
/* 473 */       paramFields.months = paramFields.monthOfYear.getDurationField();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static class LeapYearPatternType
/*     */     implements Serializable
/*     */   {
/*     */     private static final long serialVersionUID = 26581275372698L;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     final byte index;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     final int pattern;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     LeapYearPatternType(int paramInt1, int paramInt2)
/*     */     {
/* 510 */       this.index = ((byte)paramInt1);
/* 511 */       this.pattern = paramInt2;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     boolean isLeapYear(int paramInt)
/*     */     {
/* 520 */       int i = 1 << paramInt % 30;
/* 521 */       return (this.pattern & i) > 0;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     private Object readResolve()
/*     */     {
/* 529 */       switch (this.index) {
/*     */       case 0: 
/* 531 */         return IslamicChronology.LEAP_YEAR_15_BASED;
/*     */       case 1: 
/* 533 */         return IslamicChronology.LEAP_YEAR_16_BASED;
/*     */       case 2: 
/* 535 */         return IslamicChronology.LEAP_YEAR_INDIAN;
/*     */       case 3: 
/* 537 */         return IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB;
/*     */       }
/* 539 */       return this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\IslamicChronology.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */