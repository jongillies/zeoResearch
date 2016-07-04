/*     */ package org.joda.time.chrono;
/*     */ 
/*     */ import org.joda.time.DateTimeField;
/*     */ import org.joda.time.DateTimeFieldType;
/*     */ import org.joda.time.DurationField;
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
/*     */ final class BasicWeekyearDateTimeField
/*     */   extends ImpreciseDateTimeField
/*     */ {
/*     */   private static final long serialVersionUID = 6215066916806820644L;
/*     */   private static final long WEEK_53 = 31449600000L;
/*     */   private final BasicChronology iChronology;
/*     */   
/*     */   BasicWeekyearDateTimeField(BasicChronology paramBasicChronology)
/*     */   {
/*  44 */     super(DateTimeFieldType.weekyear(), paramBasicChronology.getAverageMillisPerYear());
/*  45 */     this.iChronology = paramBasicChronology;
/*     */   }
/*     */   
/*     */   public boolean isLenient() {
/*  49 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int get(long paramLong)
/*     */   {
/*  60 */     return this.iChronology.getWeekyear(paramLong);
/*     */   }
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
/*  72 */     if (paramInt == 0) {
/*  73 */       return paramLong;
/*     */     }
/*  75 */     return set(paramLong, get(paramLong) + paramInt);
/*     */   }
/*     */   
/*     */   public long add(long paramLong1, long paramLong2) {
/*  79 */     return add(paramLong1, FieldUtils.safeToInt(paramLong2));
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
/*     */   public long addWrapField(long paramLong, int paramInt)
/*     */   {
/*  92 */     return add(paramLong, paramInt);
/*     */   }
/*     */   
/*     */   public long getDifferenceAsLong(long paramLong1, long paramLong2) {
/*  96 */     if (paramLong1 < paramLong2) {
/*  97 */       return -getDifference(paramLong2, paramLong1);
/*     */     }
/*     */     
/* 100 */     int i = get(paramLong1);
/* 101 */     int j = get(paramLong2);
/*     */     
/* 103 */     long l1 = remainder(paramLong1);
/* 104 */     long l2 = remainder(paramLong2);
/*     */     
/*     */ 
/* 107 */     if ((l2 >= 31449600000L) && (this.iChronology.getWeeksInYear(i) <= 52)) {
/* 108 */       l2 -= 604800000L;
/*     */     }
/*     */     
/* 111 */     int k = i - j;
/* 112 */     if (l1 < l2) {
/* 113 */       k--;
/*     */     }
/* 115 */     return k;
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
/*     */   public long set(long paramLong, int paramInt)
/*     */   {
/* 128 */     FieldUtils.verifyValueBounds(this, Math.abs(paramInt), this.iChronology.getMinYear(), this.iChronology.getMaxYear());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 133 */     int i = get(paramLong);
/* 134 */     if (i == paramInt) {
/* 135 */       return paramLong;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 140 */     int j = this.iChronology.getDayOfWeek(paramLong);
/*     */     
/*     */ 
/*     */ 
/* 144 */     int k = this.iChronology.getWeeksInYear(i);
/* 145 */     int m = this.iChronology.getWeeksInYear(paramInt);
/* 146 */     int n = m < k ? m : k;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 154 */     int i1 = this.iChronology.getWeekOfWeekyear(paramLong);
/* 155 */     if (i1 > n) {
/* 156 */       i1 = n;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 162 */     long l = paramLong;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 168 */     l = this.iChronology.setYear(l, paramInt);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 173 */     int i2 = get(l);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 179 */     if (i2 < paramInt) {
/* 180 */       l += 604800000L;
/* 181 */     } else if (i2 > paramInt) {
/* 182 */       l -= 604800000L;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 189 */     int i3 = this.iChronology.getWeekOfWeekyear(l);
/*     */     
/* 191 */     l += (i1 - i3) * 604800000L;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 200 */     l = this.iChronology.dayOfWeek().set(l, j);
/*     */     
/*     */ 
/*     */ 
/* 204 */     return l;
/*     */   }
/*     */   
/*     */   public DurationField getRangeDurationField() {
/* 208 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isLeap(long paramLong) {
/* 212 */     return this.iChronology.getWeeksInYear(this.iChronology.getWeekyear(paramLong)) > 52;
/*     */   }
/*     */   
/*     */   public int getLeapAmount(long paramLong) {
/* 216 */     return this.iChronology.getWeeksInYear(this.iChronology.getWeekyear(paramLong)) - 52;
/*     */   }
/*     */   
/*     */   public DurationField getLeapDurationField() {
/* 220 */     return this.iChronology.weeks();
/*     */   }
/*     */   
/*     */   public int getMinimumValue() {
/* 224 */     return this.iChronology.getMinYear();
/*     */   }
/*     */   
/*     */   public int getMaximumValue() {
/* 228 */     return this.iChronology.getMaxYear();
/*     */   }
/*     */   
/*     */ 
/*     */   public long roundFloor(long paramLong)
/*     */   {
/* 234 */     paramLong = this.iChronology.weekOfWeekyear().roundFloor(paramLong);
/* 235 */     int i = this.iChronology.getWeekOfWeekyear(paramLong);
/* 236 */     if (i > 1) {
/* 237 */       paramLong -= 604800000L * (i - 1);
/*     */     }
/* 239 */     return paramLong;
/*     */   }
/*     */   
/*     */   public long remainder(long paramLong) {
/* 243 */     return paramLong - roundFloor(paramLong);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 250 */     return this.iChronology.weekyear();
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\BasicWeekyearDateTimeField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */