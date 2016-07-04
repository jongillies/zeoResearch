/*     */ package org.joda.time;
/*     */ 
/*     */ import org.joda.time.chrono.ISOChronology;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DateTimeUtils
/*     */ {
/*  31 */   private static final SystemMillisProvider SYSTEM_MILLIS_PROVIDER = new SystemMillisProvider();
/*     */   
/*  33 */   private static volatile MillisProvider cMillisProvider = SYSTEM_MILLIS_PROVIDER;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final long currentTimeMillis()
/*     */   {
/*  52 */     return cMillisProvider.getMillis();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final void setCurrentMillisSystem()
/*     */     throws SecurityException
/*     */   {
/*  64 */     checkPermission();
/*  65 */     cMillisProvider = SYSTEM_MILLIS_PROVIDER;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final void setCurrentMillisFixed(long paramLong)
/*     */     throws SecurityException
/*     */   {
/*  78 */     checkPermission();
/*  79 */     cMillisProvider = new FixedMillisProvider(paramLong);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final void setCurrentMillisOffset(long paramLong)
/*     */     throws SecurityException
/*     */   {
/*     */     
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  94 */     if (paramLong == 0L) {
/*  95 */       cMillisProvider = SYSTEM_MILLIS_PROVIDER;
/*     */     } else {
/*  97 */       cMillisProvider = new OffsetMillisProvider(paramLong);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void checkPermission()
/*     */     throws SecurityException
/*     */   {
/* 107 */     SecurityManager localSecurityManager = System.getSecurityManager();
/* 108 */     if (localSecurityManager != null) {
/* 109 */       localSecurityManager.checkPermission(new JodaTimePermission("CurrentTime.setProvider"));
/*     */     }
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
/*     */   public static final long getInstantMillis(ReadableInstant paramReadableInstant)
/*     */   {
/* 124 */     if (paramReadableInstant == null) {
/* 125 */       return currentTimeMillis();
/*     */     }
/* 127 */     return paramReadableInstant.getMillis();
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
/*     */   public static final Chronology getInstantChronology(ReadableInstant paramReadableInstant)
/*     */   {
/* 142 */     if (paramReadableInstant == null) {
/* 143 */       return ISOChronology.getInstance();
/*     */     }
/* 145 */     Chronology localChronology = paramReadableInstant.getChronology();
/* 146 */     if (localChronology == null) {
/* 147 */       return ISOChronology.getInstance();
/*     */     }
/* 149 */     return localChronology;
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
/*     */   public static final Chronology getIntervalChronology(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
/*     */   {
/* 165 */     Object localObject = null;
/* 166 */     if (paramReadableInstant1 != null) {
/* 167 */       localObject = paramReadableInstant1.getChronology();
/* 168 */     } else if (paramReadableInstant2 != null) {
/* 169 */       localObject = paramReadableInstant2.getChronology();
/*     */     }
/* 171 */     if (localObject == null) {
/* 172 */       localObject = ISOChronology.getInstance();
/*     */     }
/* 174 */     return (Chronology)localObject;
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
/*     */   public static final Chronology getIntervalChronology(ReadableInterval paramReadableInterval)
/*     */   {
/* 189 */     if (paramReadableInterval == null) {
/* 190 */       return ISOChronology.getInstance();
/*     */     }
/* 192 */     Chronology localChronology = paramReadableInterval.getChronology();
/* 193 */     if (localChronology == null) {
/* 194 */       return ISOChronology.getInstance();
/*     */     }
/* 196 */     return localChronology;
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
/*     */   public static final ReadableInterval getReadableInterval(ReadableInterval paramReadableInterval)
/*     */   {
/* 212 */     if (paramReadableInterval == null) {
/* 213 */       long l = currentTimeMillis();
/* 214 */       paramReadableInterval = new Interval(l, l);
/*     */     }
/* 216 */     return paramReadableInterval;
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
/*     */   public static final Chronology getChronology(Chronology paramChronology)
/*     */   {
/* 230 */     if (paramChronology == null) {
/* 231 */       return ISOChronology.getInstance();
/*     */     }
/* 233 */     return paramChronology;
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
/*     */   public static final DateTimeZone getZone(DateTimeZone paramDateTimeZone)
/*     */   {
/* 247 */     if (paramDateTimeZone == null) {
/* 248 */       return DateTimeZone.getDefault();
/*     */     }
/* 250 */     return paramDateTimeZone;
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
/*     */   public static final PeriodType getPeriodType(PeriodType paramPeriodType)
/*     */   {
/* 264 */     if (paramPeriodType == null) {
/* 265 */       return PeriodType.standard();
/*     */     }
/* 267 */     return paramPeriodType;
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
/*     */   public static final long getDurationMillis(ReadableDuration paramReadableDuration)
/*     */   {
/* 281 */     if (paramReadableDuration == null) {
/* 282 */       return 0L;
/*     */     }
/* 284 */     return paramReadableDuration.getMillis();
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
/*     */ 
/*     */ 
/*     */   public static final boolean isContiguous(ReadablePartial paramReadablePartial)
/*     */   {
/* 312 */     if (paramReadablePartial == null) {
/* 313 */       throw new IllegalArgumentException("Partial must not be null");
/*     */     }
/* 315 */     DurationFieldType localDurationFieldType = null;
/* 316 */     for (int i = 0; i < paramReadablePartial.size(); i++) {
/* 317 */       DateTimeField localDateTimeField = paramReadablePartial.getField(i);
/* 318 */       if ((i > 0) && 
/* 319 */         (localDateTimeField.getRangeDurationField().getType() != localDurationFieldType)) {
/* 320 */         return false;
/*     */       }
/*     */       
/* 323 */       localDurationFieldType = localDateTimeField.getDurationField().getType();
/*     */     }
/* 325 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static abstract class MillisProvider
/*     */   {
/*     */     abstract long getMillis();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static class SystemMillisProvider
/*     */     extends DateTimeUtils.MillisProvider
/*     */   {
/*     */     long getMillis()
/*     */     {
/* 349 */       return System.currentTimeMillis();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   static class FixedMillisProvider
/*     */     extends DateTimeUtils.MillisProvider
/*     */   {
/*     */     private final long iMillis;
/*     */     
/*     */ 
/*     */ 
/*     */     FixedMillisProvider(long paramLong)
/*     */     {
/* 365 */       this.iMillis = paramLong;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     long getMillis()
/*     */     {
/* 373 */       return this.iMillis;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   static class OffsetMillisProvider
/*     */     extends DateTimeUtils.MillisProvider
/*     */   {
/*     */     private final long iMillis;
/*     */     
/*     */ 
/*     */ 
/*     */     OffsetMillisProvider(long paramLong)
/*     */     {
/* 389 */       this.iMillis = paramLong;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     long getMillis()
/*     */     {
/* 397 */       return System.currentTimeMillis() + this.iMillis;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\DateTimeUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */