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
/*     */ public final class CopticChronology
/*     */   extends BasicFixedMonthChronology
/*     */ {
/*     */   private static final long serialVersionUID = -5972804258688333942L;
/*     */   public static final int AM = 1;
/*  63 */   private static final DateTimeField ERA_FIELD = new BasicSingleEraDateTimeField("AM");
/*     */   
/*     */ 
/*     */   private static final int MIN_YEAR = -292269337;
/*     */   
/*     */ 
/*     */   private static final int MAX_YEAR = 292272708;
/*     */   
/*     */ 
/*  72 */   private static final Map cCache = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  78 */   private static final CopticChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static CopticChronology getInstanceUTC()
/*     */   {
/*  89 */     return INSTANCE_UTC;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static CopticChronology getInstance()
/*     */   {
/*  98 */     return getInstance(DateTimeZone.getDefault(), 4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static CopticChronology getInstance(DateTimeZone paramDateTimeZone)
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
/*     */   public static CopticChronology getInstance(DateTimeZone paramDateTimeZone, int paramInt)
/*     */   {
/* 119 */     if (paramDateTimeZone == null) {
/* 120 */       paramDateTimeZone = DateTimeZone.getDefault();
/*     */     }
/*     */     CopticChronology localCopticChronology;
/* 123 */     synchronized (cCache) {
/* 124 */       CopticChronology[] arrayOfCopticChronology = (CopticChronology[])cCache.get(paramDateTimeZone);
/* 125 */       if (arrayOfCopticChronology == null) {
/* 126 */         arrayOfCopticChronology = new CopticChronology[7];
/* 127 */         cCache.put(paramDateTimeZone, arrayOfCopticChronology);
/*     */       }
/*     */       try {
/* 130 */         localCopticChronology = arrayOfCopticChronology[(paramInt - 1)];
/*     */       } catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {
/* 132 */         throw new IllegalArgumentException("Invalid min days in first week: " + paramInt);
/*     */       }
/*     */       
/* 135 */       if (localCopticChronology == null) {
/* 136 */         if (paramDateTimeZone == DateTimeZone.UTC)
/*     */         {
/* 138 */           localCopticChronology = new CopticChronology(null, null, paramInt);
/*     */           
/* 140 */           DateTime localDateTime = new DateTime(1, 1, 1, 0, 0, 0, 0, localCopticChronology);
/* 141 */           localCopticChronology = new CopticChronology(LimitChronology.getInstance(localCopticChronology, localDateTime, null), null, paramInt);
/*     */         }
/*     */         else
/*     */         {
/* 145 */           localCopticChronology = getInstance(DateTimeZone.UTC, paramInt);
/* 146 */           localCopticChronology = new CopticChronology(ZonedChronology.getInstance(localCopticChronology, paramDateTimeZone), null, paramInt);
/*     */         }
/*     */         
/* 149 */         arrayOfCopticChronology[(paramInt - 1)] = localCopticChronology;
/*     */       }
/*     */     }
/* 152 */     return localCopticChronology;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   CopticChronology(Chronology paramChronology, Object paramObject, int paramInt)
/*     */   {
/* 161 */     super(paramChronology, paramObject, paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 168 */     Chronology localChronology = getBase();
/* 169 */     int i = getMinimumDaysInFirstWeek();
/* 170 */     i = i == 0 ? 4 : i;
/* 171 */     return localChronology == null ? getInstance(DateTimeZone.UTC, i) : getInstance(localChronology.getZone(), i);
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
/* 184 */     return INSTANCE_UTC;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Chronology withZone(DateTimeZone paramDateTimeZone)
/*     */   {
/* 194 */     if (paramDateTimeZone == null) {
/* 195 */       paramDateTimeZone = DateTimeZone.getDefault();
/*     */     }
/* 197 */     if (paramDateTimeZone == getZone()) {
/* 198 */       return this;
/*     */     }
/* 200 */     return getInstance(paramDateTimeZone);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   long calculateFirstDayOfYearMillis(int paramInt)
/*     */   {
/* 209 */     int i = paramInt - 1687;
/*     */     int j;
/* 211 */     if (i <= 0)
/*     */     {
/*     */ 
/* 214 */       j = i + 3 >> 2;
/*     */     } else {
/* 216 */       j = i >> 2;
/*     */       
/* 218 */       if (!isLeapYear(paramInt)) {
/* 219 */         j++;
/*     */       }
/*     */     }
/*     */     
/* 223 */     long l = (i * 365L + j) * 86400000L;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 228 */     return l + 21859200000L;
/*     */   }
/*     */   
/*     */   int getMinYear()
/*     */   {
/* 233 */     return -292269337;
/*     */   }
/*     */   
/*     */   int getMaxYear()
/*     */   {
/* 238 */     return 292272708;
/*     */   }
/*     */   
/*     */   long getApproxMillisAtEpochDividedByTwo()
/*     */   {
/* 243 */     return 26607895200000L;
/*     */   }
/*     */   
/*     */   protected void assemble(AssembledChronology.Fields paramFields)
/*     */   {
/* 248 */     if (getBase() == null) {
/* 249 */       super.assemble(paramFields);
/*     */       
/*     */ 
/* 252 */       paramFields.year = new SkipDateTimeField(this, paramFields.year);
/* 253 */       paramFields.weekyear = new SkipDateTimeField(this, paramFields.weekyear);
/*     */       
/* 255 */       paramFields.era = ERA_FIELD;
/* 256 */       paramFields.monthOfYear = new BasicMonthOfYearDateTimeField(this, 13);
/* 257 */       paramFields.months = paramFields.monthOfYear.getDurationField();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\CopticChronology.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */