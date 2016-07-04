/*     */ package org.joda.time.base;
/*     */ 
/*     */ import java.util.Date;
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTime;
/*     */ import org.joda.time.DateTimeField;
/*     */ import org.joda.time.DateTimeFieldType;
/*     */ import org.joda.time.DateTimeUtils;
/*     */ import org.joda.time.DateTimeZone;
/*     */ import org.joda.time.Instant;
/*     */ import org.joda.time.MutableDateTime;
/*     */ import org.joda.time.ReadableInstant;
/*     */ import org.joda.time.chrono.ISOChronology;
/*     */ import org.joda.time.field.FieldUtils;
/*     */ import org.joda.time.format.DateTimeFormatter;
/*     */ import org.joda.time.format.ISODateTimeFormat;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractInstant
/*     */   implements ReadableInstant
/*     */ {
/*     */   public DateTimeZone getZone()
/*     */   {
/*  70 */     return getChronology().getZone();
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
/*     */   public int get(DateTimeFieldType paramDateTimeFieldType)
/*     */   {
/*  88 */     if (paramDateTimeFieldType == null) {
/*  89 */       throw new IllegalArgumentException("The DateTimeFieldType must not be null");
/*     */     }
/*  91 */     return paramDateTimeFieldType.getField(getChronology()).get(getMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isSupported(DateTimeFieldType paramDateTimeFieldType)
/*     */   {
/* 102 */     if (paramDateTimeFieldType == null) {
/* 103 */       return false;
/*     */     }
/* 105 */     return paramDateTimeFieldType.getField(getChronology()).isSupported();
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
/*     */   public int get(DateTimeField paramDateTimeField)
/*     */   {
/* 123 */     if (paramDateTimeField == null) {
/* 124 */       throw new IllegalArgumentException("The DateTimeField must not be null");
/*     */     }
/* 126 */     return paramDateTimeField.get(getMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Instant toInstant()
/*     */   {
/* 136 */     return new Instant(getMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTime toDateTime()
/*     */   {
/* 145 */     return new DateTime(getMillis(), getZone());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTime toDateTimeISO()
/*     */   {
/* 154 */     return new DateTime(getMillis(), ISOChronology.getInstance(getZone()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTime toDateTime(DateTimeZone paramDateTimeZone)
/*     */   {
/* 164 */     Chronology localChronology = DateTimeUtils.getChronology(getChronology());
/* 165 */     localChronology = localChronology.withZone(paramDateTimeZone);
/* 166 */     return new DateTime(getMillis(), localChronology);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTime toDateTime(Chronology paramChronology)
/*     */   {
/* 176 */     return new DateTime(getMillis(), paramChronology);
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
/*     */   public MutableDateTime toMutableDateTime()
/*     */   {
/* 190 */     return new MutableDateTime(getMillis(), getZone());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutableDateTime toMutableDateTimeISO()
/*     */   {
/* 199 */     return new MutableDateTime(getMillis(), ISOChronology.getInstance(getZone()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutableDateTime toMutableDateTime(DateTimeZone paramDateTimeZone)
/*     */   {
/* 209 */     Chronology localChronology = DateTimeUtils.getChronology(getChronology());
/* 210 */     localChronology = localChronology.withZone(paramDateTimeZone);
/* 211 */     return new MutableDateTime(getMillis(), localChronology);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutableDateTime toMutableDateTime(Chronology paramChronology)
/*     */   {
/* 221 */     return new MutableDateTime(getMillis(), paramChronology);
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
/*     */   public Date toDate()
/*     */   {
/* 234 */     return new Date(getMillis());
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
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 258 */     if (this == paramObject) {
/* 259 */       return true;
/*     */     }
/* 261 */     if (!(paramObject instanceof ReadableInstant)) {
/* 262 */       return false;
/*     */     }
/* 264 */     ReadableInstant localReadableInstant = (ReadableInstant)paramObject;
/* 265 */     return (getMillis() == localReadableInstant.getMillis()) && (FieldUtils.equals(getChronology(), localReadableInstant.getChronology()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 277 */     return (int)(getMillis() ^ getMillis() >>> 32) + getChronology().hashCode();
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
/*     */   public int compareTo(Object paramObject)
/*     */   {
/* 295 */     if (this == paramObject) {
/* 296 */       return 0;
/*     */     }
/*     */     
/* 299 */     ReadableInstant localReadableInstant = (ReadableInstant)paramObject;
/*     */     
/* 301 */     long l1 = localReadableInstant.getMillis();
/* 302 */     long l2 = getMillis();
/*     */     
/*     */ 
/* 305 */     if (l2 == l1) {
/* 306 */       return 0;
/*     */     }
/* 308 */     if (l2 < l1) {
/* 309 */       return -1;
/*     */     }
/* 311 */     return 1;
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
/*     */   public boolean isAfter(long paramLong)
/*     */   {
/* 324 */     return getMillis() > paramLong;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAfterNow()
/*     */   {
/* 334 */     return isAfter(DateTimeUtils.currentTimeMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAfter(ReadableInstant paramReadableInstant)
/*     */   {
/* 345 */     long l = DateTimeUtils.getInstantMillis(paramReadableInstant);
/* 346 */     return isAfter(l);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isBefore(long paramLong)
/*     */   {
/* 358 */     return getMillis() < paramLong;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isBeforeNow()
/*     */   {
/* 368 */     return isBefore(DateTimeUtils.currentTimeMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isBefore(ReadableInstant paramReadableInstant)
/*     */   {
/* 379 */     long l = DateTimeUtils.getInstantMillis(paramReadableInstant);
/* 380 */     return isBefore(l);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEqual(long paramLong)
/*     */   {
/* 392 */     return getMillis() == paramLong;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEqualNow()
/*     */   {
/* 402 */     return isEqual(DateTimeUtils.currentTimeMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEqual(ReadableInstant paramReadableInstant)
/*     */   {
/* 413 */     long l = DateTimeUtils.getInstantMillis(paramReadableInstant);
/* 414 */     return isEqual(l);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 424 */     return ISODateTimeFormat.dateTime().print(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString(DateTimeFormatter paramDateTimeFormatter)
/*     */   {
/* 436 */     if (paramDateTimeFormatter == null) {
/* 437 */       return toString();
/*     */     }
/* 439 */     return paramDateTimeFormatter.print(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\base\AbstractInstant.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */