/*     */ package org.joda.time.chrono;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.text.DateFormatSymbols;
/*     */ import java.util.Locale;
/*     */ import java.util.TreeMap;
/*     */ import java.util.WeakHashMap;
/*     */ import org.joda.time.DateTimeFieldType;
/*     */ import org.joda.time.IllegalFieldValueException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class GJLocaleSymbols
/*     */ {
/*     */   private static final int FAST_CACHE_SIZE = 64;
/*  36 */   private static final GJLocaleSymbols[] cFastCache = new GJLocaleSymbols[64];
/*     */   
/*  38 */   private static WeakHashMap cCache = new WeakHashMap();
/*     */   private final WeakReference iLocale;
/*     */   
/*  41 */   public static GJLocaleSymbols forLocale(Locale paramLocale) { if (paramLocale == null) {
/*  42 */       paramLocale = Locale.getDefault();
/*     */     }
/*  44 */     int i = System.identityHashCode(paramLocale) & 0x3F;
/*  45 */     GJLocaleSymbols localGJLocaleSymbols = cFastCache[i];
/*  46 */     if ((localGJLocaleSymbols != null) && (localGJLocaleSymbols.iLocale.get() == paramLocale)) {
/*  47 */       return localGJLocaleSymbols;
/*     */     }
/*  49 */     synchronized (cCache) {
/*  50 */       localGJLocaleSymbols = (GJLocaleSymbols)cCache.get(paramLocale);
/*  51 */       if (localGJLocaleSymbols == null) {
/*  52 */         localGJLocaleSymbols = new GJLocaleSymbols(paramLocale);
/*  53 */         cCache.put(paramLocale, localGJLocaleSymbols);
/*     */       }
/*     */     }
/*  56 */     cFastCache[i] = localGJLocaleSymbols;
/*  57 */     return localGJLocaleSymbols;
/*     */   }
/*     */   
/*     */   private static String[] realignMonths(String[] paramArrayOfString) {
/*  61 */     String[] arrayOfString = new String[13];
/*  62 */     for (int i = 1; i < 13; i++) {
/*  63 */       arrayOfString[i] = paramArrayOfString[(i - 1)];
/*     */     }
/*  65 */     return arrayOfString;
/*     */   }
/*     */   
/*     */   private static String[] realignDaysOfWeek(String[] paramArrayOfString) {
/*  69 */     String[] arrayOfString = new String[8];
/*  70 */     for (int i = 1; i < 8; i++) {
/*  71 */       arrayOfString[i] = paramArrayOfString[1];
/*     */     }
/*  73 */     return arrayOfString;
/*     */   }
/*     */   
/*     */   private static void addSymbols(TreeMap paramTreeMap, String[] paramArrayOfString, Integer[] paramArrayOfInteger) {
/*  77 */     int i = paramArrayOfString.length; for (;;) { i--; if (i < 0) break;
/*  78 */       String str = paramArrayOfString[i];
/*  79 */       if (str != null) {
/*  80 */         paramTreeMap.put(str, paramArrayOfInteger[i]);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static void addNumerals(TreeMap paramTreeMap, int paramInt1, int paramInt2, Integer[] paramArrayOfInteger) {
/*  86 */     for (int i = paramInt1; i <= paramInt2; i++) {
/*  87 */       paramTreeMap.put(String.valueOf(i).intern(), paramArrayOfInteger[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   private static int maxLength(String[] paramArrayOfString) {
/*  92 */     int i = 0;
/*  93 */     int j = paramArrayOfString.length; for (;;) { j--; if (j < 0) break;
/*  94 */       String str = paramArrayOfString[j];
/*  95 */       if (str != null) {
/*  96 */         int k = str.length();
/*  97 */         if (k > i) {
/*  98 */           i = k;
/*     */         }
/*     */       }
/*     */     }
/* 102 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */   private final String[] iEras;
/*     */   
/*     */   private final String[] iDaysOfWeek;
/*     */   
/*     */   private final String[] iShortDaysOfWeek;
/*     */   
/*     */   private final String[] iMonths;
/*     */   
/*     */   private final String[] iShortMonths;
/*     */   
/*     */   private final String[] iHalfday;
/*     */   
/*     */   private final TreeMap iParseEras;
/*     */   
/*     */   private final TreeMap iParseDaysOfWeek;
/*     */   private final TreeMap iParseMonths;
/*     */   private final int iMaxEraLength;
/*     */   private final int iMaxDayOfWeekLength;
/*     */   private final int iMaxShortDayOfWeekLength;
/*     */   private final int iMaxMonthLength;
/*     */   private final int iMaxShortMonthLength;
/*     */   private final int iMaxHalfdayLength;
/*     */   private GJLocaleSymbols(Locale paramLocale)
/*     */   {
/* 130 */     this.iLocale = new WeakReference(paramLocale);
/*     */     
/* 132 */     DateFormatSymbols localDateFormatSymbols = new DateFormatSymbols(paramLocale);
/*     */     
/* 134 */     this.iEras = localDateFormatSymbols.getEras();
/* 135 */     this.iDaysOfWeek = realignDaysOfWeek(localDateFormatSymbols.getWeekdays());
/* 136 */     this.iShortDaysOfWeek = realignDaysOfWeek(localDateFormatSymbols.getShortWeekdays());
/* 137 */     this.iMonths = realignMonths(localDateFormatSymbols.getMonths());
/* 138 */     this.iShortMonths = realignMonths(localDateFormatSymbols.getShortMonths());
/* 139 */     this.iHalfday = localDateFormatSymbols.getAmPmStrings();
/*     */     
/* 141 */     Integer[] arrayOfInteger = new Integer[13];
/* 142 */     for (int i = 0; i < 13; i++) {
/* 143 */       arrayOfInteger[i] = new Integer(i);
/*     */     }
/*     */     
/* 146 */     this.iParseEras = new TreeMap(String.CASE_INSENSITIVE_ORDER);
/* 147 */     addSymbols(this.iParseEras, this.iEras, arrayOfInteger);
/* 148 */     if ("en".equals(paramLocale.getLanguage()))
/*     */     {
/*     */ 
/*     */ 
/* 152 */       this.iParseEras.put("BCE", arrayOfInteger[0]);
/* 153 */       this.iParseEras.put("CE", arrayOfInteger[1]);
/*     */     }
/*     */     
/* 156 */     this.iParseDaysOfWeek = new TreeMap(String.CASE_INSENSITIVE_ORDER);
/* 157 */     addSymbols(this.iParseDaysOfWeek, this.iDaysOfWeek, arrayOfInteger);
/* 158 */     addSymbols(this.iParseDaysOfWeek, this.iShortDaysOfWeek, arrayOfInteger);
/* 159 */     addNumerals(this.iParseDaysOfWeek, 1, 7, arrayOfInteger);
/*     */     
/* 161 */     this.iParseMonths = new TreeMap(String.CASE_INSENSITIVE_ORDER);
/* 162 */     addSymbols(this.iParseMonths, this.iMonths, arrayOfInteger);
/* 163 */     addSymbols(this.iParseMonths, this.iShortMonths, arrayOfInteger);
/* 164 */     addNumerals(this.iParseMonths, 1, 12, arrayOfInteger);
/*     */     
/* 166 */     this.iMaxEraLength = maxLength(this.iEras);
/* 167 */     this.iMaxDayOfWeekLength = maxLength(this.iDaysOfWeek);
/* 168 */     this.iMaxShortDayOfWeekLength = maxLength(this.iShortDaysOfWeek);
/* 169 */     this.iMaxMonthLength = maxLength(this.iMonths);
/* 170 */     this.iMaxShortMonthLength = maxLength(this.iShortMonths);
/* 171 */     this.iMaxHalfdayLength = maxLength(this.iHalfday);
/*     */   }
/*     */   
/*     */   public String eraValueToText(int paramInt) {
/* 175 */     return this.iEras[paramInt];
/*     */   }
/*     */   
/*     */   public int eraTextToValue(String paramString) {
/* 179 */     Integer localInteger = (Integer)this.iParseEras.get(paramString);
/* 180 */     if (localInteger != null) {
/* 181 */       return localInteger.intValue();
/*     */     }
/* 183 */     throw new IllegalFieldValueException(DateTimeFieldType.era(), paramString);
/*     */   }
/*     */   
/*     */   public int getEraMaxTextLength() {
/* 187 */     return this.iMaxEraLength;
/*     */   }
/*     */   
/*     */   public String monthOfYearValueToText(int paramInt) {
/* 191 */     return this.iMonths[paramInt];
/*     */   }
/*     */   
/*     */   public String monthOfYearValueToShortText(int paramInt) {
/* 195 */     return this.iShortMonths[paramInt];
/*     */   }
/*     */   
/*     */   public int monthOfYearTextToValue(String paramString) {
/* 199 */     Integer localInteger = (Integer)this.iParseMonths.get(paramString);
/* 200 */     if (localInteger != null) {
/* 201 */       return localInteger.intValue();
/*     */     }
/* 203 */     throw new IllegalFieldValueException(DateTimeFieldType.monthOfYear(), paramString);
/*     */   }
/*     */   
/*     */   public int getMonthMaxTextLength() {
/* 207 */     return this.iMaxMonthLength;
/*     */   }
/*     */   
/*     */   public int getMonthMaxShortTextLength() {
/* 211 */     return this.iMaxShortMonthLength;
/*     */   }
/*     */   
/*     */   public String dayOfWeekValueToText(int paramInt) {
/* 215 */     return this.iDaysOfWeek[paramInt];
/*     */   }
/*     */   
/*     */   public String dayOfWeekValueToShortText(int paramInt) {
/* 219 */     return this.iShortDaysOfWeek[paramInt];
/*     */   }
/*     */   
/*     */   public int dayOfWeekTextToValue(String paramString) {
/* 223 */     Integer localInteger = (Integer)this.iParseDaysOfWeek.get(paramString);
/* 224 */     if (localInteger != null) {
/* 225 */       return localInteger.intValue();
/*     */     }
/* 227 */     throw new IllegalFieldValueException(DateTimeFieldType.dayOfWeek(), paramString);
/*     */   }
/*     */   
/*     */   public int getDayOfWeekMaxTextLength() {
/* 231 */     return this.iMaxDayOfWeekLength;
/*     */   }
/*     */   
/*     */   public int getDayOfWeekMaxShortTextLength() {
/* 235 */     return this.iMaxShortDayOfWeekLength;
/*     */   }
/*     */   
/*     */   public String halfdayValueToText(int paramInt) {
/* 239 */     return this.iHalfday[paramInt];
/*     */   }
/*     */   
/*     */   public int halfdayTextToValue(String paramString) {
/* 243 */     String[] arrayOfString = this.iHalfday;
/* 244 */     int i = arrayOfString.length; do { i--; if (i < 0) break;
/* 245 */     } while (!arrayOfString[i].equalsIgnoreCase(paramString));
/* 246 */     return i;
/*     */     
/*     */ 
/* 249 */     throw new IllegalFieldValueException(DateTimeFieldType.halfdayOfDay(), paramString);
/*     */   }
/*     */   
/*     */   public int getHalfdayMaxTextLength() {
/* 253 */     return this.iMaxHalfdayLength;
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\GJLocaleSymbols.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */