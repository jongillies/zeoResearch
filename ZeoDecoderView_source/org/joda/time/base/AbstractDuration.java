/*     */ package org.joda.time.base;
/*     */ 
/*     */ import org.joda.time.Duration;
/*     */ import org.joda.time.Period;
/*     */ import org.joda.time.ReadableDuration;
/*     */ import org.joda.time.format.FormatUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractDuration
/*     */   implements ReadableDuration
/*     */ {
/*     */   public Duration toDuration()
/*     */   {
/*  52 */     return new Duration(getMillis());
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
/*     */ 
/*     */ 
/*     */   public Period toPeriod()
/*     */   {
/*  78 */     return new Period(getMillis());
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
/*     */   public int compareTo(Object paramObject)
/*     */   {
/*  92 */     AbstractDuration localAbstractDuration = this;
/*  93 */     ReadableDuration localReadableDuration = (ReadableDuration)paramObject;
/*     */     
/*  95 */     long l1 = localAbstractDuration.getMillis();
/*  96 */     long l2 = localReadableDuration.getMillis();
/*     */     
/*     */ 
/*  99 */     if (l1 < l2) {
/* 100 */       return -1;
/*     */     }
/* 102 */     if (l1 > l2) {
/* 103 */       return 1;
/*     */     }
/* 105 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEqual(ReadableDuration paramReadableDuration)
/*     */   {
/* 115 */     if (paramReadableDuration == null) {
/* 116 */       paramReadableDuration = Duration.ZERO;
/*     */     }
/* 118 */     return compareTo(paramReadableDuration) == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLongerThan(ReadableDuration paramReadableDuration)
/*     */   {
/* 128 */     if (paramReadableDuration == null) {
/* 129 */       paramReadableDuration = Duration.ZERO;
/*     */     }
/* 131 */     return compareTo(paramReadableDuration) > 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isShorterThan(ReadableDuration paramReadableDuration)
/*     */   {
/* 141 */     if (paramReadableDuration == null) {
/* 142 */       paramReadableDuration = Duration.ZERO;
/*     */     }
/* 144 */     return compareTo(paramReadableDuration) < 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 156 */     if (this == paramObject) {
/* 157 */       return true;
/*     */     }
/* 159 */     if (!(paramObject instanceof ReadableDuration)) {
/* 160 */       return false;
/*     */     }
/* 162 */     ReadableDuration localReadableDuration = (ReadableDuration)paramObject;
/* 163 */     return getMillis() == localReadableDuration.getMillis();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 173 */     long l = getMillis();
/* 174 */     return (int)(l ^ l >>> 32);
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
/*     */   public String toString()
/*     */   {
/* 190 */     long l1 = getMillis();
/* 191 */     StringBuffer localStringBuffer = new StringBuffer();
/* 192 */     localStringBuffer.append("PT");
/* 193 */     FormatUtils.appendUnpaddedInteger(localStringBuffer, l1 / 1000L);
/* 194 */     long l2 = Math.abs(l1 % 1000L);
/* 195 */     if (l2 > 0L) {
/* 196 */       localStringBuffer.append('.');
/* 197 */       FormatUtils.appendPaddedInteger(localStringBuffer, l2, 3);
/*     */     }
/* 199 */     localStringBuffer.append('S');
/* 200 */     return localStringBuffer.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\base\AbstractDuration.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */