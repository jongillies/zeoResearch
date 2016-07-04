/*     */ package org.joda.time;
/*     */ 
/*     */ import org.joda.time.chrono.BuddhistChronology;
/*     */ import org.joda.time.chrono.CopticChronology;
/*     */ import org.joda.time.chrono.GJChronology;
/*     */ import org.joda.time.chrono.GregorianChronology;
/*     */ import org.joda.time.chrono.ISOChronology;
/*     */ import org.joda.time.chrono.JulianChronology;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Chronology
/*     */ {
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static Chronology getISO()
/*     */   {
/*  83 */     return ISOChronology.getInstance();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static Chronology getISOUTC()
/*     */   {
/*  98 */     return ISOChronology.getInstanceUTC();
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
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static Chronology getISO(DateTimeZone paramDateTimeZone)
/*     */   {
/* 114 */     return ISOChronology.getInstance(paramDateTimeZone);
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
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static Chronology getGJ()
/*     */   {
/* 140 */     return GJChronology.getInstance();
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
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static Chronology getGJUTC()
/*     */   {
/* 165 */     return GJChronology.getInstanceUTC();
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
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static Chronology getGJ(DateTimeZone paramDateTimeZone)
/*     */   {
/* 191 */     return GJChronology.getInstance(paramDateTimeZone);
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
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static Chronology getGregorian()
/*     */   {
/* 213 */     return GregorianChronology.getInstance();
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
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static Chronology getGregorianUTC()
/*     */   {
/* 234 */     return GregorianChronology.getInstanceUTC();
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
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static Chronology getGregorian(DateTimeZone paramDateTimeZone)
/*     */   {
/* 256 */     return GregorianChronology.getInstance(paramDateTimeZone);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static Chronology getJulian()
/*     */   {
/* 271 */     return JulianChronology.getInstance();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static Chronology getJulianUTC()
/*     */   {
/* 285 */     return JulianChronology.getInstanceUTC();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static Chronology getJulian(DateTimeZone paramDateTimeZone)
/*     */   {
/* 300 */     return JulianChronology.getInstance(paramDateTimeZone);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static Chronology getBuddhist()
/*     */   {
/* 315 */     return BuddhistChronology.getInstance();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static Chronology getBuddhistUTC()
/*     */   {
/* 329 */     return BuddhistChronology.getInstanceUTC();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static Chronology getBuddhist(DateTimeZone paramDateTimeZone)
/*     */   {
/* 344 */     return BuddhistChronology.getInstance(paramDateTimeZone);
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
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static Chronology getCoptic()
/*     */   {
/* 363 */     return CopticChronology.getInstance();
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
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static Chronology getCopticUTC()
/*     */   {
/* 381 */     return CopticChronology.getInstanceUTC();
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
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static Chronology getCoptic(DateTimeZone paramDateTimeZone)
/*     */   {
/* 400 */     return CopticChronology.getInstance(paramDateTimeZone);
/*     */   }
/*     */   
/*     */   public abstract DateTimeZone getZone();
/*     */   
/*     */   public abstract Chronology withUTC();
/*     */   
/*     */   public abstract Chronology withZone(DateTimeZone paramDateTimeZone);
/*     */   
/*     */   public abstract long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*     */   
/*     */   public abstract long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7);
/*     */   
/*     */   public abstract long getDateTimeMillis(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*     */   
/*     */   public abstract void validate(ReadablePartial paramReadablePartial, int[] paramArrayOfInt);
/*     */   
/*     */   public abstract int[] get(ReadablePartial paramReadablePartial, long paramLong);
/*     */   
/*     */   public abstract long set(ReadablePartial paramReadablePartial, long paramLong);
/*     */   
/*     */   public abstract int[] get(ReadablePeriod paramReadablePeriod, long paramLong1, long paramLong2);
/*     */   
/*     */   public abstract int[] get(ReadablePeriod paramReadablePeriod, long paramLong);
/*     */   
/*     */   public abstract long add(ReadablePeriod paramReadablePeriod, long paramLong, int paramInt);
/*     */   
/*     */   public abstract long add(long paramLong1, long paramLong2, int paramInt);
/*     */   
/*     */   public abstract DurationField millis();
/*     */   
/*     */   public abstract DateTimeField millisOfSecond();
/*     */   
/*     */   public abstract DateTimeField millisOfDay();
/*     */   
/*     */   public abstract DurationField seconds();
/*     */   
/*     */   public abstract DateTimeField secondOfMinute();
/*     */   
/*     */   public abstract DateTimeField secondOfDay();
/*     */   
/*     */   public abstract DurationField minutes();
/*     */   
/*     */   public abstract DateTimeField minuteOfHour();
/*     */   
/*     */   public abstract DateTimeField minuteOfDay();
/*     */   
/*     */   public abstract DurationField hours();
/*     */   
/*     */   public abstract DateTimeField hourOfDay();
/*     */   
/*     */   public abstract DateTimeField clockhourOfDay();
/*     */   
/*     */   public abstract DurationField halfdays();
/*     */   
/*     */   public abstract DateTimeField hourOfHalfday();
/*     */   
/*     */   public abstract DateTimeField clockhourOfHalfday();
/*     */   
/*     */   public abstract DateTimeField halfdayOfDay();
/*     */   
/*     */   public abstract DurationField days();
/*     */   
/*     */   public abstract DateTimeField dayOfWeek();
/*     */   
/*     */   public abstract DateTimeField dayOfMonth();
/*     */   
/*     */   public abstract DateTimeField dayOfYear();
/*     */   
/*     */   public abstract DurationField weeks();
/*     */   
/*     */   public abstract DateTimeField weekOfWeekyear();
/*     */   
/*     */   public abstract DurationField weekyears();
/*     */   
/*     */   public abstract DateTimeField weekyear();
/*     */   
/*     */   public abstract DateTimeField weekyearOfCentury();
/*     */   
/*     */   public abstract DurationField months();
/*     */   
/*     */   public abstract DateTimeField monthOfYear();
/*     */   
/*     */   public abstract DurationField years();
/*     */   
/*     */   public abstract DateTimeField year();
/*     */   
/*     */   public abstract DateTimeField yearOfEra();
/*     */   
/*     */   public abstract DateTimeField yearOfCentury();
/*     */   
/*     */   public abstract DurationField centuries();
/*     */   
/*     */   public abstract DateTimeField centuryOfEra();
/*     */   
/*     */   public abstract DurationField eras();
/*     */   
/*     */   public abstract DateTimeField era();
/*     */   
/*     */   public abstract String toString();
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\Chronology.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */