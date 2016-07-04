/*     */ package org.joda.time.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTimeUtils;
/*     */ import org.joda.time.MutableInterval;
/*     */ import org.joda.time.ReadWritableInterval;
/*     */ import org.joda.time.ReadableDuration;
/*     */ import org.joda.time.ReadableInstant;
/*     */ import org.joda.time.ReadableInterval;
/*     */ import org.joda.time.ReadablePeriod;
/*     */ import org.joda.time.chrono.ISOChronology;
/*     */ import org.joda.time.convert.ConverterManager;
/*     */ import org.joda.time.convert.IntervalConverter;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BaseInterval
/*     */   extends AbstractInterval
/*     */   implements ReadableInterval, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 576586928732749278L;
/*     */   private Chronology iChronology;
/*     */   private long iStartMillis;
/*     */   private long iEndMillis;
/*     */   
/*     */   protected BaseInterval(long paramLong1, long paramLong2, Chronology paramChronology)
/*     */   {
/*  72 */     this.iChronology = DateTimeUtils.getChronology(paramChronology);
/*  73 */     checkInterval(paramLong1, paramLong2);
/*  74 */     this.iStartMillis = paramLong1;
/*  75 */     this.iEndMillis = paramLong2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected BaseInterval(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
/*     */   {
/*  87 */     if ((paramReadableInstant1 == null) && (paramReadableInstant2 == null)) {
/*  88 */       this.iStartMillis = (this.iEndMillis = DateTimeUtils.currentTimeMillis());
/*  89 */       this.iChronology = ISOChronology.getInstance();
/*     */     } else {
/*  91 */       this.iChronology = DateTimeUtils.getInstantChronology(paramReadableInstant1);
/*  92 */       this.iStartMillis = DateTimeUtils.getInstantMillis(paramReadableInstant1);
/*  93 */       this.iEndMillis = DateTimeUtils.getInstantMillis(paramReadableInstant2);
/*  94 */       checkInterval(this.iStartMillis, this.iEndMillis);
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
/*     */   protected BaseInterval(ReadableInstant paramReadableInstant, ReadableDuration paramReadableDuration)
/*     */   {
/* 108 */     this.iChronology = DateTimeUtils.getInstantChronology(paramReadableInstant);
/* 109 */     this.iStartMillis = DateTimeUtils.getInstantMillis(paramReadableInstant);
/* 110 */     long l = DateTimeUtils.getDurationMillis(paramReadableDuration);
/* 111 */     this.iEndMillis = FieldUtils.safeAdd(this.iStartMillis, l);
/* 112 */     checkInterval(this.iStartMillis, this.iEndMillis);
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
/*     */   protected BaseInterval(ReadableDuration paramReadableDuration, ReadableInstant paramReadableInstant)
/*     */   {
/* 125 */     this.iChronology = DateTimeUtils.getInstantChronology(paramReadableInstant);
/* 126 */     this.iEndMillis = DateTimeUtils.getInstantMillis(paramReadableInstant);
/* 127 */     long l = DateTimeUtils.getDurationMillis(paramReadableDuration);
/* 128 */     this.iStartMillis = FieldUtils.safeAdd(this.iEndMillis, -l);
/* 129 */     checkInterval(this.iStartMillis, this.iEndMillis);
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
/*     */   protected BaseInterval(ReadableInstant paramReadableInstant, ReadablePeriod paramReadablePeriod)
/*     */   {
/* 145 */     Chronology localChronology = DateTimeUtils.getInstantChronology(paramReadableInstant);
/* 146 */     this.iChronology = localChronology;
/* 147 */     this.iStartMillis = DateTimeUtils.getInstantMillis(paramReadableInstant);
/* 148 */     if (paramReadablePeriod == null) {
/* 149 */       this.iEndMillis = this.iStartMillis;
/*     */     } else {
/* 151 */       this.iEndMillis = localChronology.add(paramReadablePeriod, this.iStartMillis, 1);
/*     */     }
/* 153 */     checkInterval(this.iStartMillis, this.iEndMillis);
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
/*     */   protected BaseInterval(ReadablePeriod paramReadablePeriod, ReadableInstant paramReadableInstant)
/*     */   {
/* 169 */     Chronology localChronology = DateTimeUtils.getInstantChronology(paramReadableInstant);
/* 170 */     this.iChronology = localChronology;
/* 171 */     this.iEndMillis = DateTimeUtils.getInstantMillis(paramReadableInstant);
/* 172 */     if (paramReadablePeriod == null) {
/* 173 */       this.iStartMillis = this.iEndMillis;
/*     */     } else {
/* 175 */       this.iStartMillis = localChronology.add(paramReadablePeriod, this.iEndMillis, -1);
/*     */     }
/* 177 */     checkInterval(this.iStartMillis, this.iEndMillis);
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
/*     */   protected BaseInterval(Object paramObject, Chronology paramChronology)
/*     */   {
/* 190 */     IntervalConverter localIntervalConverter = ConverterManager.getInstance().getIntervalConverter(paramObject);
/* 191 */     Object localObject; if (localIntervalConverter.isReadableInterval(paramObject, paramChronology)) {
/* 192 */       localObject = (ReadableInterval)paramObject;
/* 193 */       this.iChronology = (paramChronology != null ? paramChronology : ((ReadableInterval)localObject).getChronology());
/* 194 */       this.iStartMillis = ((ReadableInterval)localObject).getStartMillis();
/* 195 */       this.iEndMillis = ((ReadableInterval)localObject).getEndMillis();
/* 196 */     } else if ((this instanceof ReadWritableInterval)) {
/* 197 */       localIntervalConverter.setInto((ReadWritableInterval)this, paramObject, paramChronology);
/*     */     } else {
/* 199 */       localObject = new MutableInterval();
/* 200 */       localIntervalConverter.setInto((ReadWritableInterval)localObject, paramObject, paramChronology);
/* 201 */       this.iChronology = ((MutableInterval)localObject).getChronology();
/* 202 */       this.iStartMillis = ((MutableInterval)localObject).getStartMillis();
/* 203 */       this.iEndMillis = ((MutableInterval)localObject).getEndMillis();
/*     */     }
/* 205 */     checkInterval(this.iStartMillis, this.iEndMillis);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Chronology getChronology()
/*     */   {
/* 215 */     return this.iChronology;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getStartMillis()
/*     */   {
/* 225 */     return this.iStartMillis;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getEndMillis()
/*     */   {
/* 235 */     return this.iEndMillis;
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
/*     */   protected void setInterval(long paramLong1, long paramLong2, Chronology paramChronology)
/*     */   {
/* 248 */     checkInterval(paramLong1, paramLong2);
/* 249 */     this.iStartMillis = paramLong1;
/* 250 */     this.iEndMillis = paramLong2;
/* 251 */     this.iChronology = DateTimeUtils.getChronology(paramChronology);
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\base\BaseInterval.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */