/*     */ package org.joda.time.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTimeUtils;
/*     */ import org.joda.time.DateTimeZone;
/*     */ import org.joda.time.ReadableDateTime;
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
/*     */ public abstract class BaseDateTime
/*     */   extends AbstractDateTime
/*     */   implements ReadableDateTime, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6728882245981L;
/*     */   private long iMillis;
/*     */   private Chronology iChronology;
/*     */   
/*     */   public BaseDateTime()
/*     */   {
/*  61 */     this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BaseDateTime(DateTimeZone paramDateTimeZone)
/*     */   {
/*  73 */     this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance(paramDateTimeZone));
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
/*     */   public BaseDateTime(Chronology paramChronology)
/*     */   {
/*  86 */     this(DateTimeUtils.currentTimeMillis(), paramChronology);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BaseDateTime(long paramLong)
/*     */   {
/*  97 */     this(paramLong, ISOChronology.getInstance());
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
/*     */   public BaseDateTime(long paramLong, DateTimeZone paramDateTimeZone)
/*     */   {
/* 110 */     this(paramLong, ISOChronology.getInstance(paramDateTimeZone));
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
/*     */   public BaseDateTime(long paramLong, Chronology paramChronology)
/*     */   {
/* 125 */     this.iChronology = checkChronology(paramChronology);
/* 126 */     this.iMillis = checkInstant(paramLong, this.iChronology);
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
/*     */   public BaseDateTime(Object paramObject, DateTimeZone paramDateTimeZone)
/*     */   {
/* 147 */     InstantConverter localInstantConverter = ConverterManager.getInstance().getInstantConverter(paramObject);
/* 148 */     Chronology localChronology = checkChronology(localInstantConverter.getChronology(paramObject, paramDateTimeZone));
/* 149 */     this.iChronology = localChronology;
/* 150 */     this.iMillis = checkInstant(localInstantConverter.getInstantMillis(paramObject, localChronology), localChronology);
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
/*     */   public BaseDateTime(Object paramObject, Chronology paramChronology)
/*     */   {
/* 169 */     InstantConverter localInstantConverter = ConverterManager.getInstance().getInstantConverter(paramObject);
/* 170 */     this.iChronology = checkChronology(localInstantConverter.getChronology(paramObject, paramChronology));
/* 171 */     this.iMillis = checkInstant(localInstantConverter.getInstantMillis(paramObject, paramChronology), this.iChronology);
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
/*     */   public BaseDateTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
/*     */   {
/* 195 */     this(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, ISOChronology.getInstance());
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
/*     */   public BaseDateTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, DateTimeZone paramDateTimeZone)
/*     */   {
/* 223 */     this(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, ISOChronology.getInstance(paramDateTimeZone));
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
/*     */ 
/*     */ 
/*     */   public BaseDateTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, Chronology paramChronology)
/*     */   {
/* 253 */     this.iChronology = checkChronology(paramChronology);
/* 254 */     long l = this.iChronology.getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
/*     */     
/* 256 */     this.iMillis = checkInstant(l, this.iChronology);
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
/*     */   protected Chronology checkChronology(Chronology paramChronology)
/*     */   {
/* 270 */     return DateTimeUtils.getChronology(paramChronology);
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
/*     */   protected long checkInstant(long paramLong, Chronology paramChronology)
/*     */   {
/* 284 */     return paramLong;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getMillis()
/*     */   {
/* 295 */     return this.iMillis;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Chronology getChronology()
/*     */   {
/* 304 */     return this.iChronology;
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
/*     */   protected void setMillis(long paramLong)
/*     */   {
/* 317 */     this.iMillis = checkInstant(paramLong, this.iChronology);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setChronology(Chronology paramChronology)
/*     */   {
/* 329 */     this.iChronology = checkChronology(paramChronology);
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\base\BaseDateTime.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */