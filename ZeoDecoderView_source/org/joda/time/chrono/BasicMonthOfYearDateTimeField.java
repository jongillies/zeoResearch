/*     */ package org.joda.time.chrono;
/*     */ 
/*     */ import org.joda.time.DateTimeField;
/*     */ import org.joda.time.DateTimeFieldType;
/*     */ import org.joda.time.DateTimeUtils;
/*     */ import org.joda.time.DurationField;
/*     */ import org.joda.time.ReadablePartial;
/*     */ import org.joda.time.field.FieldUtils;
/*     */ import org.joda.time.field.ImpreciseDateTimeField;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class BasicMonthOfYearDateTimeField
/*     */   extends ImpreciseDateTimeField
/*     */ {
/*     */   private static final long serialVersionUID = -8258715387168736L;
/*     */   private static final int MIN = 1;
/*     */   private final BasicChronology iChronology;
/*     */   private final int iMax;
/*     */   private final int iLeapMonth;
/*     */   
/*     */   BasicMonthOfYearDateTimeField(BasicChronology paramBasicChronology, int paramInt)
/*     */   {
/*  51 */     super(DateTimeFieldType.monthOfYear(), paramBasicChronology.getAverageMillisPerMonth());
/*  52 */     this.iChronology = paramBasicChronology;
/*  53 */     this.iMax = this.iChronology.getMaxMonth();
/*  54 */     this.iLeapMonth = paramInt;
/*     */   }
/*     */   
/*     */   public boolean isLenient()
/*     */   {
/*  59 */     return false;
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
/*     */   public int get(long paramLong)
/*     */   {
/*  72 */     return this.iChronology.getMonthOfYear(paramLong);
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
/*     */   public long add(long paramLong, int paramInt)
/*     */   {
/*  92 */     if (paramInt == 0) {
/*  93 */       return paramLong;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  98 */     long l1 = this.iChronology.getMillisOfDay(paramLong);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 103 */     int i = this.iChronology.getYear(paramLong);
/* 104 */     int j = this.iChronology.getMonthOfYear(paramLong, i);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 112 */     int m = j - 1 + paramInt;
/* 113 */     int k; if (m >= 0) {
/* 114 */       k = i + m / this.iMax;
/* 115 */       m = m % this.iMax + 1;
/*     */     } else {
/* 117 */       k = i + m / this.iMax - 1;
/* 118 */       m = Math.abs(m);
/* 119 */       n = m % this.iMax;
/*     */       
/* 121 */       if (n == 0) {
/* 122 */         n = this.iMax;
/*     */       }
/* 124 */       m = this.iMax - n + 1;
/*     */       
/* 126 */       if (m == 1) {
/* 127 */         k++;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 136 */     int n = this.iChronology.getDayOfMonth(paramLong, i, j);
/* 137 */     int i1 = this.iChronology.getDaysInYearMonth(k, m);
/* 138 */     if (n > i1) {
/* 139 */       n = i1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 144 */     long l2 = this.iChronology.getYearMonthDayMillis(k, m, n);
/*     */     
/* 146 */     return l2 + l1;
/*     */   }
/*     */   
/*     */   public long add(long paramLong1, long paramLong2)
/*     */   {
/* 151 */     int i = (int)paramLong2;
/* 152 */     if (i == paramLong2) {
/* 153 */       return add(paramLong1, i);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 158 */     long l1 = this.iChronology.getMillisOfDay(paramLong1);
/*     */     
/* 160 */     int j = this.iChronology.getYear(paramLong1);
/* 161 */     int k = this.iChronology.getMonthOfYear(paramLong1, j);
/*     */     
/*     */ 
/* 164 */     long l3 = k - 1 + paramLong2;
/* 165 */     long l2; if (l3 >= 0L) {
/* 166 */       l2 = j + l3 / this.iMax;
/* 167 */       l3 = l3 % this.iMax + 1L;
/*     */     } else {
/* 169 */       l2 = j + l3 / this.iMax - 1L;
/* 170 */       l3 = Math.abs(l3);
/* 171 */       m = (int)(l3 % this.iMax);
/* 172 */       if (m == 0) {
/* 173 */         m = this.iMax;
/*     */       }
/* 175 */       l3 = this.iMax - m + 1;
/* 176 */       if (l3 == 1L) {
/* 177 */         l2 += 1L;
/*     */       }
/*     */     }
/*     */     
/* 181 */     if ((l2 < this.iChronology.getMinYear()) || (l2 > this.iChronology.getMaxYear()))
/*     */     {
/*     */ 
/* 184 */       throw new IllegalArgumentException("Magnitude of add amount is too large: " + paramLong2);
/*     */     }
/*     */     
/*     */ 
/* 188 */     int m = (int)l2;
/* 189 */     int n = (int)l3;
/*     */     
/* 191 */     int i1 = this.iChronology.getDayOfMonth(paramLong1, j, k);
/* 192 */     int i2 = this.iChronology.getDaysInYearMonth(m, n);
/* 193 */     if (i1 > i2) {
/* 194 */       i1 = i2;
/*     */     }
/*     */     
/* 197 */     long l4 = this.iChronology.getYearMonthDayMillis(m, n, i1);
/*     */     
/* 199 */     return l4 + l1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int[] add(ReadablePartial paramReadablePartial, int paramInt1, int[] paramArrayOfInt, int paramInt2)
/*     */   {
/* 206 */     if (paramInt2 == 0) {
/* 207 */       return paramArrayOfInt;
/*     */     }
/* 209 */     if (DateTimeUtils.isContiguous(paramReadablePartial)) {
/* 210 */       long l = 0L;
/* 211 */       int i = 0; for (int j = paramReadablePartial.size(); i < j; i++) {
/* 212 */         l = paramReadablePartial.getFieldType(i).getField(this.iChronology).set(l, paramArrayOfInt[i]);
/*     */       }
/* 214 */       l = add(l, paramInt2);
/* 215 */       return this.iChronology.get(paramReadablePartial, l);
/*     */     }
/* 217 */     return super.add(paramReadablePartial, paramInt1, paramArrayOfInt, paramInt2);
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
/*     */   public long addWrapField(long paramLong, int paramInt)
/*     */   {
/* 232 */     return set(paramLong, FieldUtils.getWrappedValue(get(paramLong), paramInt, 1, this.iMax));
/*     */   }
/*     */   
/*     */   public long getDifferenceAsLong(long paramLong1, long paramLong2)
/*     */   {
/* 237 */     if (paramLong1 < paramLong2) {
/* 238 */       return -getDifference(paramLong2, paramLong1);
/*     */     }
/*     */     
/* 241 */     int i = this.iChronology.getYear(paramLong1);
/* 242 */     int j = this.iChronology.getMonthOfYear(paramLong1, i);
/* 243 */     int k = this.iChronology.getYear(paramLong2);
/* 244 */     int m = this.iChronology.getMonthOfYear(paramLong2, k);
/*     */     
/* 246 */     long l1 = (i - k) * this.iMax + j - m;
/*     */     
/*     */ 
/*     */ 
/* 250 */     int n = this.iChronology.getDayOfMonth(paramLong1, i, j);
/*     */     
/* 252 */     if (n == this.iChronology.getDaysInYearMonth(i, j))
/*     */     {
/* 254 */       int i1 = this.iChronology.getDayOfMonth(paramLong2, k, m);
/*     */       
/* 256 */       if (i1 > n)
/*     */       {
/*     */ 
/*     */ 
/* 260 */         paramLong2 = this.iChronology.dayOfMonth().set(paramLong2, n);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 265 */     long l2 = paramLong1 - this.iChronology.getYearMonthMillis(i, j);
/*     */     
/* 267 */     long l3 = paramLong2 - this.iChronology.getYearMonthMillis(k, m);
/*     */     
/*     */ 
/* 270 */     if (l2 < l3) {
/* 271 */       l1 -= 1L;
/*     */     }
/*     */     
/* 274 */     return l1;
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
/*     */   public long set(long paramLong, int paramInt)
/*     */   {
/* 292 */     FieldUtils.verifyValueBounds(this, paramInt, 1, this.iMax);
/*     */     
/* 294 */     int i = this.iChronology.getYear(paramLong);
/*     */     
/* 296 */     int j = this.iChronology.getDayOfMonth(paramLong, i);
/* 297 */     int k = this.iChronology.getDaysInYearMonth(i, paramInt);
/* 298 */     if (j > k)
/*     */     {
/* 300 */       j = k;
/*     */     }
/*     */     
/* 303 */     return this.iChronology.getYearMonthDayMillis(i, paramInt, j) + this.iChronology.getMillisOfDay(paramLong);
/*     */   }
/*     */   
/*     */ 
/*     */   public DurationField getRangeDurationField()
/*     */   {
/* 309 */     return this.iChronology.years();
/*     */   }
/*     */   
/*     */   public boolean isLeap(long paramLong)
/*     */   {
/* 314 */     int i = this.iChronology.getYear(paramLong);
/* 315 */     if (this.iChronology.isLeapYear(i)) {
/* 316 */       return this.iChronology.getMonthOfYear(paramLong, i) == this.iLeapMonth;
/*     */     }
/* 318 */     return false;
/*     */   }
/*     */   
/*     */   public int getLeapAmount(long paramLong)
/*     */   {
/* 323 */     return isLeap(paramLong) ? 1 : 0;
/*     */   }
/*     */   
/*     */   public DurationField getLeapDurationField()
/*     */   {
/* 328 */     return this.iChronology.days();
/*     */   }
/*     */   
/*     */   public int getMinimumValue()
/*     */   {
/* 333 */     return 1;
/*     */   }
/*     */   
/*     */   public int getMaximumValue()
/*     */   {
/* 338 */     return this.iMax;
/*     */   }
/*     */   
/*     */   public long roundFloor(long paramLong)
/*     */   {
/* 343 */     int i = this.iChronology.getYear(paramLong);
/* 344 */     int j = this.iChronology.getMonthOfYear(paramLong, i);
/* 345 */     return this.iChronology.getYearMonthMillis(i, j);
/*     */   }
/*     */   
/*     */   public long remainder(long paramLong)
/*     */   {
/* 350 */     return paramLong - roundFloor(paramLong);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 358 */     return this.iChronology.monthOfYear();
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\BasicMonthOfYearDateTimeField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */