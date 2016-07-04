/*     */ package org.joda.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.joda.time.base.AbstractInstant;
/*     */ import org.joda.time.chrono.ISOChronology;
/*     */ import org.joda.time.convert.ConverterManager;
/*     */ import org.joda.time.convert.InstantConverter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Instant
/*     */   extends AbstractInstant
/*     */   implements ReadableInstant, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3299096530934209741L;
/*     */   private final long iMillis;
/*     */   
/*     */   public Instant()
/*     */   {
/*  68 */     this.iMillis = DateTimeUtils.currentTimeMillis();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Instant(long paramLong)
/*     */   {
/*  78 */     this.iMillis = paramLong;
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
/*     */   public Instant(Object paramObject)
/*     */   {
/*  92 */     InstantConverter localInstantConverter = ConverterManager.getInstance().getInstantConverter(paramObject);
/*  93 */     this.iMillis = localInstantConverter.getInstantMillis(paramObject, ISOChronology.getInstanceUTC());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Instant toInstant()
/*     */   {
/* 103 */     return this;
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
/*     */   public Instant withMillis(long paramLong)
/*     */   {
/* 116 */     return paramLong == this.iMillis ? this : new Instant(paramLong);
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
/*     */   public Instant withDurationAdded(long paramLong, int paramInt)
/*     */   {
/* 130 */     if ((paramLong == 0L) || (paramInt == 0)) {
/* 131 */       return this;
/*     */     }
/* 133 */     long l = getChronology().add(getMillis(), paramLong, paramInt);
/* 134 */     return withMillis(l);
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
/*     */   public Instant withDurationAdded(ReadableDuration paramReadableDuration, int paramInt)
/*     */   {
/* 148 */     if ((paramReadableDuration == null) || (paramInt == 0)) {
/* 149 */       return this;
/*     */     }
/* 151 */     return withDurationAdded(paramReadableDuration.getMillis(), paramInt);
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
/*     */   public Instant plus(long paramLong)
/*     */   {
/* 165 */     return withDurationAdded(paramLong, 1);
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
/*     */   public Instant plus(ReadableDuration paramReadableDuration)
/*     */   {
/* 178 */     return withDurationAdded(paramReadableDuration, 1);
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
/*     */   public Instant minus(long paramLong)
/*     */   {
/* 192 */     return withDurationAdded(paramLong, -1);
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
/*     */   public Instant minus(ReadableDuration paramReadableDuration)
/*     */   {
/* 205 */     return withDurationAdded(paramReadableDuration, -1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getMillis()
/*     */   {
/* 215 */     return this.iMillis;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Chronology getChronology()
/*     */   {
/* 227 */     return ISOChronology.getInstanceUTC();
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
/*     */   public DateTime toDateTime()
/*     */   {
/* 247 */     return new DateTime(getMillis(), ISOChronology.getInstance());
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
/*     */   public DateTime toDateTimeISO()
/*     */   {
/* 268 */     return toDateTime();
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
/*     */   public MutableDateTime toMutableDateTime()
/*     */   {
/* 287 */     return new MutableDateTime(getMillis(), ISOChronology.getInstance());
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
/*     */   public MutableDateTime toMutableDateTimeISO()
/*     */   {
/* 308 */     return toMutableDateTime();
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\Instant.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */