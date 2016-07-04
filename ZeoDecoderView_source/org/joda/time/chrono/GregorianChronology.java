/*     */ package org.joda.time.chrono;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTimeZone;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class GregorianChronology
/*     */   extends BasicGJChronology
/*     */ {
/*  68 */   private static final Map cCache = new HashMap();
/*     */   
/*     */ 
/*  71 */   private static final GregorianChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);
/*     */   private static final int MAX_YEAR = 292278993;
/*     */   private static final int MIN_YEAR = -292275054;
/*     */   private static final int DAYS_0000_TO_1970 = 719527;
/*     */   private static final long MILLIS_PER_MONTH = 2629746000L;
/*     */   private static final long MILLIS_PER_YEAR = 31556952000L;
/*     */   private static final long serialVersionUID = -861407383323710522L;
/*     */   
/*     */   public static GregorianChronology getInstanceUTC()
/*     */   {
/*  81 */     return INSTANCE_UTC;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static GregorianChronology getInstance()
/*     */   {
/*  90 */     return getInstance(DateTimeZone.getDefault(), 4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static GregorianChronology getInstance(DateTimeZone paramDateTimeZone)
/*     */   {
/* 100 */     return getInstance(paramDateTimeZone, 4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static GregorianChronology getInstance(DateTimeZone paramDateTimeZone, int paramInt)
/*     */   {
/* 111 */     if (paramDateTimeZone == null) {
/* 112 */       paramDateTimeZone = DateTimeZone.getDefault();
/*     */     }
/*     */     GregorianChronology localGregorianChronology;
/* 115 */     synchronized (cCache) {
/* 116 */       GregorianChronology[] arrayOfGregorianChronology = (GregorianChronology[])cCache.get(paramDateTimeZone);
/* 117 */       if (arrayOfGregorianChronology == null) {
/* 118 */         arrayOfGregorianChronology = new GregorianChronology[7];
/* 119 */         cCache.put(paramDateTimeZone, arrayOfGregorianChronology);
/*     */       }
/*     */       try {
/* 122 */         localGregorianChronology = arrayOfGregorianChronology[(paramInt - 1)];
/*     */       } catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {
/* 124 */         throw new IllegalArgumentException("Invalid min days in first week: " + paramInt);
/*     */       }
/*     */       
/* 127 */       if (localGregorianChronology == null) {
/* 128 */         if (paramDateTimeZone == DateTimeZone.UTC) {
/* 129 */           localGregorianChronology = new GregorianChronology(null, null, paramInt);
/*     */         } else {
/* 131 */           localGregorianChronology = getInstance(DateTimeZone.UTC, paramInt);
/* 132 */           localGregorianChronology = new GregorianChronology(ZonedChronology.getInstance(localGregorianChronology, paramDateTimeZone), null, paramInt);
/*     */         }
/*     */         
/* 135 */         arrayOfGregorianChronology[(paramInt - 1)] = localGregorianChronology;
/*     */       }
/*     */     }
/* 138 */     return localGregorianChronology;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private GregorianChronology(Chronology paramChronology, Object paramObject, int paramInt)
/*     */   {
/* 148 */     super(paramChronology, paramObject, paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 155 */     Chronology localChronology = getBase();
/* 156 */     int i = getMinimumDaysInFirstWeek();
/* 157 */     i = i == 0 ? 4 : i;
/* 158 */     return localChronology == null ? getInstance(DateTimeZone.UTC, i) : getInstance(localChronology.getZone(), i);
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
/* 171 */     return INSTANCE_UTC;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Chronology withZone(DateTimeZone paramDateTimeZone)
/*     */   {
/* 181 */     if (paramDateTimeZone == null) {
/* 182 */       paramDateTimeZone = DateTimeZone.getDefault();
/*     */     }
/* 184 */     if (paramDateTimeZone == getZone()) {
/* 185 */       return this;
/*     */     }
/* 187 */     return getInstance(paramDateTimeZone);
/*     */   }
/*     */   
/*     */   protected void assemble(AssembledChronology.Fields paramFields) {
/* 191 */     if (getBase() == null) {
/* 192 */       super.assemble(paramFields);
/*     */     }
/*     */   }
/*     */   
/*     */   boolean isLeapYear(int paramInt) {
/* 197 */     return ((paramInt & 0x3) == 0) && ((paramInt % 100 != 0) || (paramInt % 400 == 0));
/*     */   }
/*     */   
/*     */   long calculateFirstDayOfYearMillis(int paramInt)
/*     */   {
/* 202 */     int i = paramInt / 100;
/* 203 */     if (paramInt < 0)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 209 */       i = (paramInt + 3 >> 2) - i + (i + 3 >> 2) - 1;
/*     */     } else {
/* 211 */       i = (paramInt >> 2) - i + (i >> 2);
/* 212 */       if (isLeapYear(paramInt)) {
/* 213 */         i--;
/*     */       }
/*     */     }
/*     */     
/* 217 */     return (paramInt * 365L + (i - 719527)) * 86400000L;
/*     */   }
/*     */   
/*     */   int getMinYear() {
/* 221 */     return -292275054;
/*     */   }
/*     */   
/*     */   int getMaxYear() {
/* 225 */     return 292278993;
/*     */   }
/*     */   
/*     */   long getAverageMillisPerYear() {
/* 229 */     return 31556952000L;
/*     */   }
/*     */   
/*     */   long getAverageMillisPerYearDividedByTwo() {
/* 233 */     return 15778476000L;
/*     */   }
/*     */   
/*     */   long getAverageMillisPerMonth() {
/* 237 */     return 2629746000L;
/*     */   }
/*     */   
/*     */   long getApproxMillisAtEpochDividedByTwo() {
/* 241 */     return 31083597720000L;
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\GregorianChronology.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */