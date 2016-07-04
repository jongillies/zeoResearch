/*     */ package org.joda.time.chrono;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTime;
/*     */ import org.joda.time.DateTimeField;
/*     */ import org.joda.time.DateTimeZone;
/*     */ import org.joda.time.field.SkipDateTimeField;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class EthiopicChronology
/*     */   extends BasicFixedMonthChronology
/*     */ {
/*     */   private static final long serialVersionUID = -5972804258688333942L;
/*     */   public static final int EE = 1;
/*  63 */   private static final DateTimeField ERA_FIELD = new BasicSingleEraDateTimeField("EE");
/*     */   
/*     */ 
/*     */   private static final int MIN_YEAR = -292269337;
/*     */   
/*     */ 
/*     */   private static final int MAX_YEAR = 292272984;
/*     */   
/*     */ 
/*  72 */   private static final Map cCache = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  78 */   private static final EthiopicChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static EthiopicChronology getInstanceUTC()
/*     */   {
/*  89 */     return INSTANCE_UTC;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static EthiopicChronology getInstance()
/*     */   {
/*  98 */     return getInstance(DateTimeZone.getDefault(), 4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static EthiopicChronology getInstance(DateTimeZone paramDateTimeZone)
/*     */   {
/* 108 */     return getInstance(paramDateTimeZone, 4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static EthiopicChronology getInstance(DateTimeZone paramDateTimeZone, int paramInt)
/*     */   {
/* 119 */     if (paramDateTimeZone == null) {
/* 120 */       paramDateTimeZone = DateTimeZone.getDefault();
/*     */     }
/*     */     EthiopicChronology localEthiopicChronology;
/* 123 */     synchronized (cCache) {
/* 124 */       EthiopicChronology[] arrayOfEthiopicChronology = (EthiopicChronology[])cCache.get(paramDateTimeZone);
/* 125 */       if (arrayOfEthiopicChronology == null) {
/* 126 */         arrayOfEthiopicChronology = new EthiopicChronology[7];
/* 127 */         cCache.put(paramDateTimeZone, arrayOfEthiopicChronology);
/*     */       }
/*     */       try {
/* 130 */         localEthiopicChronology = arrayOfEthiopicChronology[(paramInt - 1)];
/*     */       } catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {
/* 132 */         throw new IllegalArgumentException("Invalid min days in first week: " + paramInt);
/*     */       }
/*     */       
/* 135 */       if (localEthiopicChronology == null) {
/* 136 */         if (paramDateTimeZone == DateTimeZone.UTC)
/*     */         {
/* 138 */           localEthiopicChronology = new EthiopicChronology(null, null, paramInt);
/*     */           
/* 140 */           DateTime localDateTime = new DateTime(1, 1, 1, 0, 0, 0, 0, localEthiopicChronology);
/* 141 */           localEthiopicChronology = new EthiopicChronology(LimitChronology.getInstance(localEthiopicChronology, localDateTime, null), null, paramInt);
/*     */         }
/*     */         else
/*     */         {
/* 145 */           localEthiopicChronology = getInstance(DateTimeZone.UTC, paramInt);
/* 146 */           localEthiopicChronology = new EthiopicChronology(ZonedChronology.getInstance(localEthiopicChronology, paramDateTimeZone), null, paramInt);
/*     */         }
/*     */         
/* 149 */         arrayOfEthiopicChronology[(paramInt - 1)] = localEthiopicChronology;
/*     */       }
/*     */     }
/* 152 */     return localEthiopicChronology;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   EthiopicChronology(Chronology paramChronology, Object paramObject, int paramInt)
/*     */   {
/* 161 */     super(paramChronology, paramObject, paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 168 */     Chronology localChronology = getBase();
/* 169 */     return localChronology == null ? getInstance(DateTimeZone.UTC, getMinimumDaysInFirstWeek()) : getInstance(localChronology.getZone(), getMinimumDaysInFirstWeek());
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
/*     */   public Chronology withUTC()
/*     */   {
/* 182 */     return INSTANCE_UTC;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Chronology withZone(DateTimeZone paramDateTimeZone)
/*     */   {
/* 192 */     if (paramDateTimeZone == null) {
/* 193 */       paramDateTimeZone = DateTimeZone.getDefault();
/*     */     }
/* 195 */     if (paramDateTimeZone == getZone()) {
/* 196 */       return this;
/*     */     }
/* 198 */     return getInstance(paramDateTimeZone);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   long calculateFirstDayOfYearMillis(int paramInt)
/*     */   {
/* 207 */     int i = paramInt - 1963;
/*     */     int j;
/* 209 */     if (i <= 0)
/*     */     {
/*     */ 
/* 212 */       j = i + 3 >> 2;
/*     */     } else {
/* 214 */       j = i >> 2;
/*     */       
/* 216 */       if (!isLeapYear(paramInt)) {
/* 217 */         j++;
/*     */       }
/*     */     }
/*     */     
/* 221 */     long l = (i * 365L + j) * 86400000L;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 226 */     return l + 21859200000L;
/*     */   }
/*     */   
/*     */   int getMinYear()
/*     */   {
/* 231 */     return -292269337;
/*     */   }
/*     */   
/*     */   int getMaxYear()
/*     */   {
/* 236 */     return 292272984;
/*     */   }
/*     */   
/*     */   long getApproxMillisAtEpochDividedByTwo()
/*     */   {
/* 241 */     return 30962844000000L;
/*     */   }
/*     */   
/*     */   protected void assemble(AssembledChronology.Fields paramFields)
/*     */   {
/* 246 */     if (getBase() == null) {
/* 247 */       super.assemble(paramFields);
/*     */       
/*     */ 
/* 250 */       paramFields.year = new SkipDateTimeField(this, paramFields.year);
/* 251 */       paramFields.weekyear = new SkipDateTimeField(this, paramFields.weekyear);
/*     */       
/* 253 */       paramFields.era = ERA_FIELD;
/* 254 */       paramFields.monthOfYear = new BasicMonthOfYearDateTimeField(this, 13);
/* 255 */       paramFields.months = paramFields.monthOfYear.getDurationField();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\EthiopicChronology.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */