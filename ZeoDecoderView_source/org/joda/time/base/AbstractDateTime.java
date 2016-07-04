/*     */ package org.joda.time.base;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.Locale;
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTimeField;
/*     */ import org.joda.time.DateTimeFieldType;
/*     */ import org.joda.time.DateTimeZone;
/*     */ import org.joda.time.ReadableDateTime;
/*     */ import org.joda.time.format.DateTimeFormat;
/*     */ import org.joda.time.format.DateTimeFormatter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractDateTime
/*     */   extends AbstractInstant
/*     */   implements ReadableDateTime
/*     */ {
/*     */   public int get(DateTimeFieldType paramDateTimeFieldType)
/*     */   {
/*  66 */     if (paramDateTimeFieldType == null) {
/*  67 */       throw new IllegalArgumentException("The DateTimeFieldType must not be null");
/*     */     }
/*  69 */     return paramDateTimeFieldType.getField(getChronology()).get(getMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getEra()
/*     */   {
/*  79 */     return getChronology().era().get(getMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getCenturyOfEra()
/*     */   {
/*  88 */     return getChronology().centuryOfEra().get(getMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getYearOfEra()
/*     */   {
/*  97 */     return getChronology().yearOfEra().get(getMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getYearOfCentury()
/*     */   {
/* 106 */     return getChronology().yearOfCentury().get(getMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getYear()
/*     */   {
/* 115 */     return getChronology().year().get(getMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getWeekyear()
/*     */   {
/* 124 */     return getChronology().weekyear().get(getMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMonthOfYear()
/*     */   {
/* 133 */     return getChronology().monthOfYear().get(getMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getWeekOfWeekyear()
/*     */   {
/* 142 */     return getChronology().weekOfWeekyear().get(getMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDayOfYear()
/*     */   {
/* 151 */     return getChronology().dayOfYear().get(getMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDayOfMonth()
/*     */   {
/* 162 */     return getChronology().dayOfMonth().get(getMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDayOfWeek()
/*     */   {
/* 173 */     return getChronology().dayOfWeek().get(getMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getHourOfDay()
/*     */   {
/* 183 */     return getChronology().hourOfDay().get(getMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMinuteOfDay()
/*     */   {
/* 192 */     return getChronology().minuteOfDay().get(getMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMinuteOfHour()
/*     */   {
/* 201 */     return getChronology().minuteOfHour().get(getMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSecondOfDay()
/*     */   {
/* 210 */     return getChronology().secondOfDay().get(getMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSecondOfMinute()
/*     */   {
/* 219 */     return getChronology().secondOfMinute().get(getMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMillisOfDay()
/*     */   {
/* 228 */     return getChronology().millisOfDay().get(getMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMillisOfSecond()
/*     */   {
/* 237 */     return getChronology().millisOfSecond().get(getMillis());
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
/*     */   public Calendar toCalendar(Locale paramLocale)
/*     */   {
/* 259 */     if (paramLocale == null) {
/* 260 */       paramLocale = Locale.getDefault();
/*     */     }
/* 262 */     DateTimeZone localDateTimeZone = getZone();
/* 263 */     Calendar localCalendar = Calendar.getInstance(localDateTimeZone.toTimeZone(), paramLocale);
/* 264 */     localCalendar.setTime(toDate());
/* 265 */     return localCalendar;
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
/*     */   public GregorianCalendar toGregorianCalendar()
/*     */   {
/* 283 */     DateTimeZone localDateTimeZone = getZone();
/* 284 */     GregorianCalendar localGregorianCalendar = new GregorianCalendar(localDateTimeZone.toTimeZone());
/* 285 */     localGregorianCalendar.setTime(toDate());
/* 286 */     return localGregorianCalendar;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString(String paramString)
/*     */   {
/* 297 */     if (paramString == null) {
/* 298 */       return toString();
/*     */     }
/* 300 */     return DateTimeFormat.forPattern(paramString).print(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString(String paramString, Locale paramLocale)
/*     */     throws IllegalArgumentException
/*     */   {
/* 311 */     if (paramString == null) {
/* 312 */       return toString();
/*     */     }
/* 314 */     return DateTimeFormat.forPattern(paramString).withLocale(paramLocale).print(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\base\AbstractDateTime.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */