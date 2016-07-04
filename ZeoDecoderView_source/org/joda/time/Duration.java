/*     */ package org.joda.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.joda.time.base.BaseDuration;
/*     */ import org.joda.time.field.FieldUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Duration
/*     */   extends BaseDuration
/*     */   implements ReadableDuration, Serializable
/*     */ {
/*  42 */   public static final Duration ZERO = new Duration(0L);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final long serialVersionUID = 2471658376918L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Duration standardDays(long paramLong)
/*     */   {
/*  66 */     if (paramLong == 0L) {
/*  67 */       return ZERO;
/*     */     }
/*  69 */     return new Duration(FieldUtils.safeMultiply(paramLong, 86400000));
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
/*     */   public static Duration standardHours(long paramLong)
/*     */   {
/*  89 */     if (paramLong == 0L) {
/*  90 */       return ZERO;
/*     */     }
/*  92 */     return new Duration(FieldUtils.safeMultiply(paramLong, 3600000));
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
/*     */   public static Duration standardMinutes(long paramLong)
/*     */   {
/* 112 */     if (paramLong == 0L) {
/* 113 */       return ZERO;
/*     */     }
/* 115 */     return new Duration(FieldUtils.safeMultiply(paramLong, 60000));
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
/*     */   public static Duration standardSeconds(long paramLong)
/*     */   {
/* 134 */     if (paramLong == 0L) {
/* 135 */       return ZERO;
/*     */     }
/* 137 */     return new Duration(FieldUtils.safeMultiply(paramLong, 1000));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Duration(long paramLong)
/*     */   {
/* 147 */     super(paramLong);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Duration(long paramLong1, long paramLong2)
/*     */   {
/* 158 */     super(paramLong1, paramLong2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Duration(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
/*     */   {
/* 169 */     super(paramReadableInstant1, paramReadableInstant2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Duration(Object paramObject)
/*     */   {
/* 180 */     super(paramObject);
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
/*     */   public long getStandardSeconds()
/*     */   {
/* 195 */     return getMillis() / 1000L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Duration toDuration()
/*     */   {
/* 206 */     return this;
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
/*     */   public Seconds toStandardSeconds()
/*     */   {
/* 220 */     long l = getStandardSeconds();
/* 221 */     return Seconds.seconds(FieldUtils.safeToInt(l));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Duration withMillis(long paramLong)
/*     */   {
/* 232 */     if (paramLong == getMillis()) {
/* 233 */       return this;
/*     */     }
/* 235 */     return new Duration(paramLong);
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
/*     */   public Duration withDurationAdded(long paramLong, int paramInt)
/*     */   {
/* 249 */     if ((paramLong == 0L) || (paramInt == 0)) {
/* 250 */       return this;
/*     */     }
/* 252 */     long l1 = FieldUtils.safeMultiply(paramLong, paramInt);
/* 253 */     long l2 = FieldUtils.safeAdd(getMillis(), l1);
/* 254 */     return new Duration(l2);
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
/*     */   public Duration withDurationAdded(ReadableDuration paramReadableDuration, int paramInt)
/*     */   {
/* 268 */     if ((paramReadableDuration == null) || (paramInt == 0)) {
/* 269 */       return this;
/*     */     }
/* 271 */     return withDurationAdded(paramReadableDuration.getMillis(), paramInt);
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
/*     */   public Duration plus(long paramLong)
/*     */   {
/* 285 */     return withDurationAdded(paramLong, 1);
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
/*     */   public Duration plus(ReadableDuration paramReadableDuration)
/*     */   {
/* 298 */     if (paramReadableDuration == null) {
/* 299 */       return this;
/*     */     }
/* 301 */     return withDurationAdded(paramReadableDuration.getMillis(), 1);
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
/*     */   public Duration minus(long paramLong)
/*     */   {
/* 314 */     return withDurationAdded(paramLong, -1);
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
/*     */   public Duration minus(ReadableDuration paramReadableDuration)
/*     */   {
/* 327 */     if (paramReadableDuration == null) {
/* 328 */       return this;
/*     */     }
/* 330 */     return withDurationAdded(paramReadableDuration.getMillis(), -1);
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\Duration.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */