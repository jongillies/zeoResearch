/*     */ package org.joda.time.chrono;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTimeFieldType;
/*     */ import org.joda.time.DateTimeZone;
/*     */ import org.joda.time.IllegalFieldValueException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class JulianChronology
/*     */   extends BasicGJChronology
/*     */ {
/*  71 */   private static final Map cCache = new HashMap();
/*     */   
/*     */ 
/*  74 */   private static final JulianChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);
/*     */   private static final int MAX_YEAR = 292272992;
/*     */   private static final int MIN_YEAR = -292269054;
/*     */   
/*  78 */   static int adjustYearForSet(int paramInt) { if (paramInt <= 0) {
/*  79 */       if (paramInt == 0) {
/*  80 */         throw new IllegalFieldValueException(DateTimeFieldType.year(), new Integer(paramInt), null, null);
/*     */       }
/*     */       
/*  83 */       paramInt++;
/*     */     }
/*  85 */     return paramInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static JulianChronology getInstanceUTC()
/*     */   {
/*  95 */     return INSTANCE_UTC;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static JulianChronology getInstance()
/*     */   {
/* 104 */     return getInstance(DateTimeZone.getDefault(), 4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static JulianChronology getInstance(DateTimeZone paramDateTimeZone)
/*     */   {
/* 114 */     return getInstance(paramDateTimeZone, 4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static JulianChronology getInstance(DateTimeZone paramDateTimeZone, int paramInt)
/*     */   {
/* 125 */     if (paramDateTimeZone == null) {
/* 126 */       paramDateTimeZone = DateTimeZone.getDefault();
/*     */     }
/*     */     JulianChronology localJulianChronology;
/* 129 */     synchronized (cCache) {
/* 130 */       JulianChronology[] arrayOfJulianChronology = (JulianChronology[])cCache.get(paramDateTimeZone);
/* 131 */       if (arrayOfJulianChronology == null) {
/* 132 */         arrayOfJulianChronology = new JulianChronology[7];
/* 133 */         cCache.put(paramDateTimeZone, arrayOfJulianChronology);
/*     */       }
/*     */       try {
/* 136 */         localJulianChronology = arrayOfJulianChronology[(paramInt - 1)];
/*     */       } catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {
/* 138 */         throw new IllegalArgumentException("Invalid min days in first week: " + paramInt);
/*     */       }
/*     */       
/* 141 */       if (localJulianChronology == null) {
/* 142 */         if (paramDateTimeZone == DateTimeZone.UTC) {
/* 143 */           localJulianChronology = new JulianChronology(null, null, paramInt);
/*     */         } else {
/* 145 */           localJulianChronology = getInstance(DateTimeZone.UTC, paramInt);
/* 146 */           localJulianChronology = new JulianChronology(ZonedChronology.getInstance(localJulianChronology, paramDateTimeZone), null, paramInt);
/*     */         }
/*     */         
/* 149 */         arrayOfJulianChronology[(paramInt - 1)] = localJulianChronology;
/*     */       }
/*     */     }
/* 152 */     return localJulianChronology;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   JulianChronology(Chronology paramChronology, Object paramObject, int paramInt)
/*     */   {
/* 162 */     super(paramChronology, paramObject, paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 169 */     Chronology localChronology = getBase();
/* 170 */     int i = getMinimumDaysInFirstWeek();
/* 171 */     i = i == 0 ? 4 : i;
/* 172 */     return localChronology == null ? getInstance(DateTimeZone.UTC, i) : getInstance(localChronology.getZone(), i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static final long MILLIS_PER_MONTH = 2629800000L;
/*     */   
/*     */   private static final long MILLIS_PER_YEAR = 31557600000L;
/*     */   
/*     */   private static final long serialVersionUID = -8731039522547897247L;
/*     */   
/*     */   public Chronology withUTC()
/*     */   {
/* 185 */     return INSTANCE_UTC;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Chronology withZone(DateTimeZone paramDateTimeZone)
/*     */   {
/* 195 */     if (paramDateTimeZone == null) {
/* 196 */       paramDateTimeZone = DateTimeZone.getDefault();
/*     */     }
/* 198 */     if (paramDateTimeZone == getZone()) {
/* 199 */       return this;
/*     */     }
/* 201 */     return getInstance(paramDateTimeZone);
/*     */   }
/*     */   
/*     */   long getDateMidnightMillis(int paramInt1, int paramInt2, int paramInt3)
/*     */     throws IllegalArgumentException
/*     */   {
/* 207 */     return super.getDateMidnightMillis(adjustYearForSet(paramInt1), paramInt2, paramInt3);
/*     */   }
/*     */   
/*     */   boolean isLeapYear(int paramInt) {
/* 211 */     return (paramInt & 0x3) == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   long calculateFirstDayOfYearMillis(int paramInt)
/*     */   {
/* 219 */     int i = paramInt - 1968;
/*     */     int j;
/* 221 */     if (i <= 0)
/*     */     {
/*     */ 
/* 224 */       j = i + 3 >> 2;
/*     */     } else {
/* 226 */       j = i >> 2;
/*     */       
/* 228 */       if (!isLeapYear(paramInt)) {
/* 229 */         j++;
/*     */       }
/*     */     }
/*     */     
/* 233 */     long l = (i * 365L + j) * 86400000L;
/*     */     
/*     */ 
/*     */ 
/* 237 */     return l - 62035200000L;
/*     */   }
/*     */   
/*     */   int getMinYear() {
/* 241 */     return -292269054;
/*     */   }
/*     */   
/*     */   int getMaxYear() {
/* 245 */     return 292272992;
/*     */   }
/*     */   
/*     */   long getAverageMillisPerYear() {
/* 249 */     return 31557600000L;
/*     */   }
/*     */   
/*     */   long getAverageMillisPerYearDividedByTwo() {
/* 253 */     return 15778800000L;
/*     */   }
/*     */   
/*     */   long getAverageMillisPerMonth() {
/* 257 */     return 2629800000L;
/*     */   }
/*     */   
/*     */   long getApproxMillisAtEpochDividedByTwo() {
/* 261 */     return 31083663600000L;
/*     */   }
/*     */   
/*     */   protected void assemble(AssembledChronology.Fields paramFields) {
/* 265 */     if (getBase() == null) {
/* 266 */       super.assemble(paramFields);
/*     */       
/* 268 */       paramFields.year = new SkipDateTimeField(this, paramFields.year);
/* 269 */       paramFields.weekyear = new SkipDateTimeField(this, paramFields.weekyear);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\JulianChronology.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */