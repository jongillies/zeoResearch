/*     */ package org.joda.time.convert;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTimeZone;
/*     */ import org.joda.time.chrono.BuddhistChronology;
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
/*     */ final class CalendarConverter
/*     */   extends AbstractConverter
/*     */   implements InstantConverter, PartialConverter
/*     */ {
/*  43 */   static final CalendarConverter INSTANCE = new CalendarConverter();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Chronology getChronology(Object paramObject, Chronology paramChronology)
/*     */   {
/*  68 */     if (paramChronology != null) {
/*  69 */       return paramChronology;
/*     */     }
/*  71 */     Calendar localCalendar = (Calendar)paramObject;
/*  72 */     DateTimeZone localDateTimeZone = null;
/*     */     try {
/*  74 */       localDateTimeZone = DateTimeZone.forTimeZone(localCalendar.getTimeZone());
/*     */     }
/*     */     catch (IllegalArgumentException localIllegalArgumentException) {
/*  77 */       localDateTimeZone = DateTimeZone.getDefault();
/*     */     }
/*  79 */     return getChronology(localCalendar, localDateTimeZone);
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
/*     */   public Chronology getChronology(Object paramObject, DateTimeZone paramDateTimeZone)
/*     */   {
/*  94 */     if (paramObject.getClass().getName().endsWith(".BuddhistCalendar"))
/*  95 */       return BuddhistChronology.getInstance(paramDateTimeZone);
/*  96 */     if ((paramObject instanceof GregorianCalendar)) {
/*  97 */       GregorianCalendar localGregorianCalendar = (GregorianCalendar)paramObject;
/*  98 */       long l = localGregorianCalendar.getGregorianChange().getTime();
/*  99 */       if (l == Long.MIN_VALUE)
/* 100 */         return GregorianChronology.getInstance(paramDateTimeZone);
/* 101 */       if (l == Long.MAX_VALUE) {
/* 102 */         return JulianChronology.getInstance(paramDateTimeZone);
/*     */       }
/* 104 */       return GJChronology.getInstance(paramDateTimeZone, l, 4);
/*     */     }
/*     */     
/* 107 */     return ISOChronology.getInstance(paramDateTimeZone);
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
/*     */   public long getInstantMillis(Object paramObject, Chronology paramChronology)
/*     */   {
/* 121 */     Calendar localCalendar = (Calendar)paramObject;
/* 122 */     return localCalendar.getTime().getTime();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Class getSupportedType()
/*     */   {
/* 132 */     return Calendar.class;
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\convert\CalendarConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */