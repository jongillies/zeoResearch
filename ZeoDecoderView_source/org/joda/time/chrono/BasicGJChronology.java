/*     */ package org.joda.time.chrono;
/*     */ 
/*     */ import org.joda.time.Chronology;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class BasicGJChronology
/*     */   extends BasicChronology
/*     */ {
/*     */   private static final long serialVersionUID = 538276888268L;
/*  42 */   private static final int[] MIN_DAYS_PER_MONTH_ARRAY = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
/*     */   
/*     */ 
/*  45 */   private static final int[] MAX_DAYS_PER_MONTH_ARRAY = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  53 */   private static final long[] MIN_TOTAL_MILLIS_BY_MONTH_ARRAY = new long[12];
/*  54 */   private static final long[] MAX_TOTAL_MILLIS_BY_MONTH_ARRAY = new long[12];
/*     */   
/*  56 */   static { long l1 = 0L;
/*  57 */     long l2 = 0L;
/*  58 */     for (int i = 0; i < 11; i++) {
/*  59 */       long l3 = MIN_DAYS_PER_MONTH_ARRAY[i] * 86400000L;
/*     */       
/*  61 */       l1 += l3;
/*  62 */       MIN_TOTAL_MILLIS_BY_MONTH_ARRAY[(i + 1)] = l1;
/*     */       
/*  64 */       l3 = MAX_DAYS_PER_MONTH_ARRAY[i] * 86400000L;
/*     */       
/*  66 */       l2 += l3;
/*  67 */       MAX_TOTAL_MILLIS_BY_MONTH_ARRAY[(i + 1)] = l2;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   BasicGJChronology(Chronology paramChronology, Object paramObject, int paramInt)
/*     */   {
/*  75 */     super(paramChronology, paramObject, paramInt);
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
/*     */   int getMonthOfYear(long paramLong, int paramInt)
/*     */   {
/*  88 */     int i = (int)(paramLong - getYearMillis(paramInt) >> 10);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  93 */     return i < 28181250 ? 11 : i < 25650000 ? 10 : i < 23034375 ? 9 : i < 20503125 ? 8 : i < 17887500 ? 7 : i < 15271875 ? 6 : i < 12740625 ? 5 : i < 10125000 ? 4 : i < 7593750 ? 3 : i < 4978125 ? 2 : i < 2615625 ? 1 : isLeapYear(paramInt) ? 12 : i < 28265625 ? 11 : i < 25734375 ? 10 : i < 23118750 ? 9 : i < 20587500 ? 8 : i < 17971875 ? 7 : i < 15356250 ? 6 : i < 12825000 ? 5 : i < 10209375 ? 4 : i < 7678125 ? 3 : i < 5062500 ? 2 : i < 2615625 ? 1 : 12;
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
/*     */   private static final long FEB_29 = 5097600000L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   int getDaysInYearMonth(int paramInt1, int paramInt2)
/*     */   {
/* 120 */     if (isLeapYear(paramInt1)) {
/* 121 */       return MAX_DAYS_PER_MONTH_ARRAY[(paramInt2 - 1)];
/*     */     }
/* 123 */     return MIN_DAYS_PER_MONTH_ARRAY[(paramInt2 - 1)];
/*     */   }
/*     */   
/*     */ 
/*     */   int getDaysInMonthMax(int paramInt)
/*     */   {
/* 129 */     return MAX_DAYS_PER_MONTH_ARRAY[(paramInt - 1)];
/*     */   }
/*     */   
/*     */   int getDaysInMonthMaxForSet(long paramLong, int paramInt)
/*     */   {
/* 134 */     return paramInt > 28 ? getDaysInMonthMax(paramLong) : 28;
/*     */   }
/*     */   
/*     */   long getTotalMillisByYearMonth(int paramInt1, int paramInt2)
/*     */   {
/* 139 */     if (isLeapYear(paramInt1)) {
/* 140 */       return MAX_TOTAL_MILLIS_BY_MONTH_ARRAY[(paramInt2 - 1)];
/*     */     }
/* 142 */     return MIN_TOTAL_MILLIS_BY_MONTH_ARRAY[(paramInt2 - 1)];
/*     */   }
/*     */   
/*     */ 
/*     */   long getYearDifference(long paramLong1, long paramLong2)
/*     */   {
/* 148 */     int i = getYear(paramLong1);
/* 149 */     int j = getYear(paramLong2);
/*     */     
/*     */ 
/* 152 */     long l1 = paramLong1 - getYearMillis(i);
/* 153 */     long l2 = paramLong2 - getYearMillis(j);
/*     */     
/*     */ 
/* 156 */     if (l2 >= 5097600000L) {
/* 157 */       if (isLeapYear(j)) {
/* 158 */         if (!isLeapYear(i)) {
/* 159 */           l2 -= 86400000L;
/*     */         }
/* 161 */       } else if ((l1 >= 5097600000L) && (isLeapYear(i))) {
/* 162 */         l1 -= 86400000L;
/*     */       }
/*     */     }
/*     */     
/* 166 */     int k = i - j;
/* 167 */     if (l1 < l2) {
/* 168 */       k--;
/*     */     }
/* 170 */     return k;
/*     */   }
/*     */   
/*     */   long setYear(long paramLong, int paramInt)
/*     */   {
/* 175 */     int i = getYear(paramLong);
/* 176 */     int j = getDayOfYear(paramLong, i);
/* 177 */     int k = getMillisOfDay(paramLong);
/*     */     
/* 179 */     if (j > 59) {
/* 180 */       if (isLeapYear(i))
/*     */       {
/* 182 */         if (!isLeapYear(paramInt))
/*     */         {
/* 184 */           j--;
/*     */         }
/*     */         
/*     */       }
/* 188 */       else if (isLeapYear(paramInt))
/*     */       {
/* 190 */         j++;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 195 */     paramLong = getYearMonthDayMillis(paramInt, 1, j);
/* 196 */     paramLong += k;
/*     */     
/* 198 */     return paramLong;
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\BasicGJChronology.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */