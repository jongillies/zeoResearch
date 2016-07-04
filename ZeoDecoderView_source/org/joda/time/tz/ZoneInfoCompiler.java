/*     */ package org.joda.time.tz;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.TreeMap;
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTime;
/*     */ import org.joda.time.DateTimeField;
/*     */ import org.joda.time.DateTimeZone;
/*     */ import org.joda.time.LocalDate;
/*     */ import org.joda.time.MutableDateTime;
/*     */ import org.joda.time.chrono.ISOChronology;
/*     */ import org.joda.time.chrono.LenientChronology;
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
/*     */ public class ZoneInfoCompiler
/*     */ {
/*     */   static DateTimeOfYear cStartOfYear;
/*     */   static Chronology cLenientISO;
/*     */   private Map iRuleSets;
/*     */   private List iZones;
/*     */   private List iLinks;
/*     */   
/*     */   public static void main(String[] paramArrayOfString)
/*     */     throws Exception
/*     */   {
/*  79 */     if (paramArrayOfString.length == 0) {
/*  80 */       printUsage();
/*  81 */       return;
/*     */     }
/*     */     
/*  84 */     File localFile1 = null;
/*  85 */     File localFile2 = null;
/*     */     
/*     */ 
/*  88 */     for (int i = 0; i < paramArrayOfString.length; i++) {
/*     */       try {
/*  90 */         if ("-src".equals(paramArrayOfString[i])) {
/*  91 */           localFile1 = new File(paramArrayOfString[(++i)]);
/*  92 */         } else if ("-dst".equals(paramArrayOfString[i])) {
/*  93 */           localFile2 = new File(paramArrayOfString[(++i)]);
/*  94 */         } else { if ("-?".equals(paramArrayOfString[i])) {
/*  95 */             printUsage();
/*  96 */             return;
/*     */           }
/*  98 */           break;
/*     */         }
/*     */       } catch (IndexOutOfBoundsException localIndexOutOfBoundsException) {
/* 101 */         printUsage();
/* 102 */         return;
/*     */       }
/*     */     }
/*     */     
/* 106 */     if (i >= paramArrayOfString.length) {
/* 107 */       printUsage();
/* 108 */       return;
/*     */     }
/*     */     
/* 111 */     File[] arrayOfFile = new File[paramArrayOfString.length - i];
/* 112 */     for (int j = 0; i < paramArrayOfString.length; j++) {
/* 113 */       arrayOfFile[j] = (localFile1 == null ? new File(paramArrayOfString[i]) : new File(localFile1, paramArrayOfString[i]));i++;
/*     */     }
/*     */     
/* 116 */     ZoneInfoCompiler localZoneInfoCompiler = new ZoneInfoCompiler();
/* 117 */     localZoneInfoCompiler.compile(localFile2, arrayOfFile);
/*     */   }
/*     */   
/*     */   private static void printUsage() {
/* 121 */     System.out.println("Usage: java org.joda.time.tz.ZoneInfoCompiler <options> <source files>");
/* 122 */     System.out.println("where possible options include:");
/* 123 */     System.out.println("  -src <directory>    Specify where to read source files");
/* 124 */     System.out.println("  -dst <directory>    Specify where to write generated files");
/*     */   }
/*     */   
/*     */   static DateTimeOfYear getStartOfYear() {
/* 128 */     if (cStartOfYear == null) {
/* 129 */       cStartOfYear = new DateTimeOfYear();
/*     */     }
/* 131 */     return cStartOfYear;
/*     */   }
/*     */   
/*     */   static Chronology getLenientISOChronology() {
/* 135 */     if (cLenientISO == null) {
/* 136 */       cLenientISO = LenientChronology.getInstance(ISOChronology.getInstanceUTC());
/*     */     }
/* 138 */     return cLenientISO;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   static void writeZoneInfoMap(DataOutputStream paramDataOutputStream, Map paramMap)
/*     */     throws IOException
/*     */   {
/* 146 */     HashMap localHashMap = new HashMap(paramMap.size());
/* 147 */     TreeMap localTreeMap = new TreeMap();
/*     */     
/* 149 */     Iterator localIterator = paramMap.entrySet().iterator();
/* 150 */     short s = 0;
/* 151 */     Map.Entry localEntry; String str; while (localIterator.hasNext()) {
/* 152 */       localEntry = (Map.Entry)localIterator.next();
/* 153 */       str = (String)localEntry.getKey();
/* 154 */       Short localShort; if (!localHashMap.containsKey(str)) {
/* 155 */         localShort = new Short(s);
/* 156 */         localHashMap.put(str, localShort);
/* 157 */         localTreeMap.put(localShort, str);
/* 158 */         s = (short)(s + 1); if (s == 0) {
/* 159 */           throw new InternalError("Too many time zone ids");
/*     */         }
/*     */       }
/* 162 */       str = ((DateTimeZone)localEntry.getValue()).getID();
/* 163 */       if (!localHashMap.containsKey(str)) {
/* 164 */         localShort = new Short(s);
/* 165 */         localHashMap.put(str, localShort);
/* 166 */         localTreeMap.put(localShort, str);
/* 167 */         s = (short)(s + 1); if (s == 0) {
/* 168 */           throw new InternalError("Too many time zone ids");
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 174 */     paramDataOutputStream.writeShort(localTreeMap.size());
/* 175 */     localIterator = localTreeMap.values().iterator();
/* 176 */     while (localIterator.hasNext()) {
/* 177 */       paramDataOutputStream.writeUTF((String)localIterator.next());
/*     */     }
/*     */     
/*     */ 
/* 181 */     paramDataOutputStream.writeShort(paramMap.size());
/* 182 */     localIterator = paramMap.entrySet().iterator();
/* 183 */     while (localIterator.hasNext()) {
/* 184 */       localEntry = (Map.Entry)localIterator.next();
/* 185 */       str = (String)localEntry.getKey();
/* 186 */       paramDataOutputStream.writeShort(((Short)localHashMap.get(str)).shortValue());
/* 187 */       str = ((DateTimeZone)localEntry.getValue()).getID();
/* 188 */       paramDataOutputStream.writeShort(((Short)localHashMap.get(str)).shortValue());
/*     */     }
/*     */   }
/*     */   
/*     */   static int parseYear(String paramString, int paramInt) {
/* 193 */     paramString = paramString.toLowerCase();
/* 194 */     if ((paramString.equals("minimum")) || (paramString.equals("min")))
/* 195 */       return Integer.MIN_VALUE;
/* 196 */     if ((paramString.equals("maximum")) || (paramString.equals("max")))
/* 197 */       return Integer.MAX_VALUE;
/* 198 */     if (paramString.equals("only")) {
/* 199 */       return paramInt;
/*     */     }
/* 201 */     return Integer.parseInt(paramString);
/*     */   }
/*     */   
/*     */   static int parseMonth(String paramString) {
/* 205 */     DateTimeField localDateTimeField = ISOChronology.getInstanceUTC().monthOfYear();
/* 206 */     return localDateTimeField.get(localDateTimeField.set(0L, paramString, Locale.ENGLISH));
/*     */   }
/*     */   
/*     */   static int parseDayOfWeek(String paramString) {
/* 210 */     DateTimeField localDateTimeField = ISOChronology.getInstanceUTC().dayOfWeek();
/* 211 */     return localDateTimeField.get(localDateTimeField.set(0L, paramString, Locale.ENGLISH));
/*     */   }
/*     */   
/*     */   static String parseOptional(String paramString) {
/* 215 */     return paramString.equals("-") ? null : paramString;
/*     */   }
/*     */   
/*     */   static int parseTime(String paramString) {
/* 219 */     DateTimeFormatter localDateTimeFormatter = ISODateTimeFormat.hourMinuteSecondFraction();
/* 220 */     MutableDateTime localMutableDateTime = new MutableDateTime(0L, getLenientISOChronology());
/* 221 */     int i = 0;
/* 222 */     if (paramString.startsWith("-")) {
/* 223 */       i = 1;
/*     */     }
/* 225 */     int j = localDateTimeFormatter.parseInto(localMutableDateTime, paramString, i);
/* 226 */     if (j == (i ^ 0xFFFFFFFF)) {
/* 227 */       throw new IllegalArgumentException(paramString);
/*     */     }
/* 229 */     int k = (int)localMutableDateTime.getMillis();
/* 230 */     if (i == 1) {
/* 231 */       k = -k;
/*     */     }
/* 233 */     return k;
/*     */   }
/*     */   
/*     */   static char parseZoneChar(char paramChar) {
/* 237 */     switch (paramChar) {
/*     */     case 'S': 
/*     */     case 's': 
/* 240 */       return 's';
/*     */     case 'G': case 'U': case 'Z': 
/*     */     case 'g': case 'u': case 'z': 
/* 243 */       return 'u';
/*     */     }
/*     */     
/* 246 */     return 'w';
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   static boolean test(String paramString, DateTimeZone paramDateTimeZone)
/*     */   {
/* 254 */     if (!paramString.equals(paramDateTimeZone.getID())) {
/* 255 */       return true;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 260 */     long l1 = ISOChronology.getInstanceUTC().year().set(0L, 1850);
/* 261 */     long l2 = ISOChronology.getInstanceUTC().year().set(0L, 2050);
/*     */     
/* 263 */     int i = paramDateTimeZone.getOffset(l1);
/* 264 */     Object localObject = paramDateTimeZone.getNameKey(l1);
/*     */     
/* 266 */     ArrayList localArrayList = new ArrayList();
/*     */     for (;;)
/*     */     {
/* 269 */       long l3 = paramDateTimeZone.nextTransition(l1);
/* 270 */       if ((l3 == l1) || (l3 > l2)) {
/*     */         break;
/*     */       }
/*     */       
/* 274 */       l1 = l3;
/*     */       
/* 276 */       int k = paramDateTimeZone.getOffset(l1);
/* 277 */       String str = paramDateTimeZone.getNameKey(l1);
/*     */       
/* 279 */       if ((i == k) && (((String)localObject).equals(str)))
/*     */       {
/* 281 */         System.out.println("*d* Error in " + paramDateTimeZone.getID() + " " + new DateTime(l1, ISOChronology.getInstanceUTC()));
/*     */         
/*     */ 
/* 284 */         return false;
/*     */       }
/*     */       
/* 287 */       if ((str == null) || ((str.length() < 3) && (!"??".equals(str)))) {
/* 288 */         System.out.println("*s* Error in " + paramDateTimeZone.getID() + " " + new DateTime(l1, ISOChronology.getInstanceUTC()) + ", nameKey=" + str);
/*     */         
/*     */ 
/*     */ 
/* 292 */         return false;
/*     */       }
/*     */       
/* 295 */       localArrayList.add(new Long(l1));
/*     */       
/* 297 */       i = k;
/* 298 */       localObject = str;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 303 */     l1 = ISOChronology.getInstanceUTC().year().set(0L, 2050);
/* 304 */     l2 = ISOChronology.getInstanceUTC().year().set(0L, 1850);
/*     */     
/* 306 */     int j = localArrayList.size(); long l5; do { j--; if (j < 0) break;
/* 307 */       long l4 = paramDateTimeZone.previousTransition(l1);
/* 308 */       if ((l4 == l1) || (l4 < l2)) {
/*     */         break;
/*     */       }
/*     */       
/* 312 */       l1 = l4;
/*     */       
/* 314 */       l5 = ((Long)localArrayList.get(j)).longValue();
/*     */     }
/* 316 */     while (l5 - 1L == l1);
/* 317 */     System.out.println("*r* Error in " + paramDateTimeZone.getID() + " " + new DateTime(l1, ISOChronology.getInstanceUTC()) + " != " + new DateTime(l5 - 1L, ISOChronology.getInstanceUTC()));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 323 */     return false;
/*     */     
/*     */ 
/*     */ 
/* 327 */     return true;
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
/*     */   public ZoneInfoCompiler()
/*     */   {
/* 340 */     this.iRuleSets = new HashMap();
/* 341 */     this.iZones = new ArrayList();
/* 342 */     this.iLinks = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map compile(File paramFile, File[] paramArrayOfFile)
/*     */     throws IOException
/*     */   {
/* 352 */     if (paramArrayOfFile != null) {
/* 353 */       for (int i = 0; i < paramArrayOfFile.length; i++) {
/* 354 */         BufferedReader localBufferedReader = new BufferedReader(new FileReader(paramArrayOfFile[i]));
/* 355 */         parseDataFile(localBufferedReader);
/* 356 */         localBufferedReader.close();
/*     */       }
/*     */     }
/*     */     
/* 360 */     if (paramFile != null) {
/* 361 */       if (!paramFile.exists()) {
/* 362 */         throw new IOException("Destination directory doesn't exist: " + paramFile);
/*     */       }
/* 364 */       if (!paramFile.isDirectory()) {
/* 365 */         throw new IOException("Destination is not a directory: " + paramFile);
/*     */       }
/*     */     }
/*     */     
/* 369 */     TreeMap localTreeMap = new TreeMap();
/*     */     Object localObject1;
/* 371 */     Object localObject2; Object localObject3; for (int j = 0; j < this.iZones.size(); j++) {
/* 372 */       Zone localZone = (Zone)this.iZones.get(j);
/* 373 */       localObject1 = new DateTimeZoneBuilder();
/* 374 */       localZone.addToBuilder((DateTimeZoneBuilder)localObject1, this.iRuleSets);
/* 375 */       localObject2 = ((DateTimeZoneBuilder)localObject1).toDateTimeZone(localZone.iName, true);
/* 376 */       localObject3 = localObject2;
/* 377 */       if (test(((DateTimeZone)localObject3).getID(), (DateTimeZone)localObject3)) {
/* 378 */         localTreeMap.put(((DateTimeZone)localObject3).getID(), localObject3);
/* 379 */         if (paramFile != null) {
/* 380 */           System.out.println("Writing " + ((DateTimeZone)localObject3).getID());
/* 381 */           File localFile2 = new File(paramFile, ((DateTimeZone)localObject3).getID());
/* 382 */           if (!localFile2.getParentFile().exists()) {
/* 383 */             localFile2.getParentFile().mkdirs();
/*     */           }
/* 385 */           FileOutputStream localFileOutputStream2 = new FileOutputStream(localFile2);
/* 386 */           ((DateTimeZoneBuilder)localObject1).writeTo(localZone.iName, localFileOutputStream2);
/* 387 */           localFileOutputStream2.close();
/*     */           
/*     */ 
/* 390 */           FileInputStream localFileInputStream = new FileInputStream(localFile2);
/* 391 */           DateTimeZone localDateTimeZone = DateTimeZoneBuilder.readFrom(localFileInputStream, ((DateTimeZone)localObject3).getID());
/* 392 */           localFileInputStream.close();
/*     */           
/* 394 */           if (!((DateTimeZone)localObject2).equals(localDateTimeZone)) {
/* 395 */             System.out.println("*e* Error in " + ((DateTimeZone)localObject3).getID() + ": Didn't read properly from file");
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 402 */     for (j = 0; j < 2; j++) {
/* 403 */       for (int k = 0; k < this.iLinks.size(); k += 2) {
/* 404 */         localObject1 = (String)this.iLinks.get(k);
/* 405 */         localObject2 = (String)this.iLinks.get(k + 1);
/* 406 */         localObject3 = (DateTimeZone)localTreeMap.get(localObject1);
/* 407 */         if (localObject3 == null) {
/* 408 */           if (j > 0) {
/* 409 */             System.out.println("Cannot find time zone '" + (String)localObject1 + "' to link alias '" + (String)localObject2 + "' to");
/*     */           }
/*     */         }
/*     */         else {
/* 413 */           localTreeMap.put(localObject2, localObject3);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 418 */     if (paramFile != null) {
/* 419 */       System.out.println("Writing ZoneInfoMap");
/* 420 */       File localFile1 = new File(paramFile, "ZoneInfoMap");
/* 421 */       if (!localFile1.getParentFile().exists()) {
/* 422 */         localFile1.getParentFile().mkdirs();
/*     */       }
/*     */       
/* 425 */       FileOutputStream localFileOutputStream1 = new FileOutputStream(localFile1);
/* 426 */       localObject1 = new DataOutputStream(localFileOutputStream1);
/*     */       
/* 428 */       localObject2 = new TreeMap(String.CASE_INSENSITIVE_ORDER);
/* 429 */       ((Map)localObject2).putAll(localTreeMap);
/* 430 */       writeZoneInfoMap((DataOutputStream)localObject1, (Map)localObject2);
/* 431 */       ((DataOutputStream)localObject1).close();
/*     */     }
/*     */     
/* 434 */     return localTreeMap;
/*     */   }
/*     */   
/*     */   public void parseDataFile(BufferedReader paramBufferedReader) throws IOException {
/* 438 */     Object localObject = null;
/*     */     String str1;
/* 440 */     while ((str1 = paramBufferedReader.readLine()) != null) {
/* 441 */       String str2 = str1.trim();
/* 442 */       if ((str2.length() != 0) && (str2.charAt(0) != '#'))
/*     */       {
/*     */ 
/*     */ 
/* 446 */         int i = str1.indexOf('#');
/* 447 */         if (i >= 0) {
/* 448 */           str1 = str1.substring(0, i);
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 453 */         StringTokenizer localStringTokenizer = new StringTokenizer(str1, " \t");
/*     */         
/* 455 */         if ((Character.isWhitespace(str1.charAt(0))) && (localStringTokenizer.hasMoreTokens())) {
/* 456 */           if (localObject != null)
/*     */           {
/* 458 */             ((Zone)localObject).chain(localStringTokenizer);
/*     */           }
/*     */         }
/*     */         else {
/* 462 */           if (localObject != null) {
/* 463 */             this.iZones.add(localObject);
/*     */           }
/* 465 */           localObject = null;
/*     */           
/*     */ 
/* 468 */           if (localStringTokenizer.hasMoreTokens()) {
/* 469 */             String str3 = localStringTokenizer.nextToken();
/* 470 */             if (str3.equalsIgnoreCase("Rule")) {
/* 471 */               Rule localRule = new Rule(localStringTokenizer);
/* 472 */               RuleSet localRuleSet = (RuleSet)this.iRuleSets.get(localRule.iName);
/* 473 */               if (localRuleSet == null) {
/* 474 */                 localRuleSet = new RuleSet(localRule);
/* 475 */                 this.iRuleSets.put(localRule.iName, localRuleSet);
/*     */               } else {
/* 477 */                 localRuleSet.addRule(localRule);
/*     */               }
/* 479 */             } else if (str3.equalsIgnoreCase("Zone")) {
/* 480 */               localObject = new Zone(localStringTokenizer);
/* 481 */             } else if (str3.equalsIgnoreCase("Link")) {
/* 482 */               this.iLinks.add(localStringTokenizer.nextToken());
/* 483 */               this.iLinks.add(localStringTokenizer.nextToken());
/*     */             } else {
/* 485 */               System.out.println("Unknown line: " + str1);
/*     */             }
/*     */           }
/*     */         }
/*     */       } }
/* 490 */     if (localObject != null) {
/* 491 */       this.iZones.add(localObject);
/*     */     }
/*     */   }
/*     */   
/*     */   static class DateTimeOfYear {
/*     */     public final int iMonthOfYear;
/*     */     public final int iDayOfMonth;
/*     */     public final int iDayOfWeek;
/*     */     public final boolean iAdvanceDayOfWeek;
/*     */     public final int iMillisOfDay;
/*     */     public final char iZoneChar;
/*     */     
/*     */     DateTimeOfYear() {
/* 504 */       this.iMonthOfYear = 1;
/* 505 */       this.iDayOfMonth = 1;
/* 506 */       this.iDayOfWeek = 0;
/* 507 */       this.iAdvanceDayOfWeek = false;
/* 508 */       this.iMillisOfDay = 0;
/* 509 */       this.iZoneChar = 'w';
/*     */     }
/*     */     
/*     */     DateTimeOfYear(StringTokenizer paramStringTokenizer) {
/* 513 */       int i = 1;
/* 514 */       int j = 1;
/* 515 */       int k = 0;
/* 516 */       int m = 0;
/* 517 */       boolean bool = false;
/* 518 */       char c = 'w';
/*     */       
/* 520 */       if (paramStringTokenizer.hasMoreTokens()) {
/* 521 */         i = ZoneInfoCompiler.parseMonth(paramStringTokenizer.nextToken());
/*     */         
/* 523 */         if (paramStringTokenizer.hasMoreTokens()) {
/* 524 */           String str = paramStringTokenizer.nextToken();
/* 525 */           if (str.startsWith("last")) {
/* 526 */             j = -1;
/* 527 */             k = ZoneInfoCompiler.parseDayOfWeek(str.substring(4));
/* 528 */             bool = false;
/*     */           } else {
/*     */             try {
/* 531 */               j = Integer.parseInt(str);
/* 532 */               k = 0;
/* 533 */               bool = false;
/*     */             } catch (NumberFormatException localNumberFormatException) {
/* 535 */               int n = str.indexOf(">=");
/* 536 */               if (n > 0) {
/* 537 */                 j = Integer.parseInt(str.substring(n + 2));
/* 538 */                 k = ZoneInfoCompiler.parseDayOfWeek(str.substring(0, n));
/* 539 */                 bool = true;
/*     */               } else {
/* 541 */                 n = str.indexOf("<=");
/* 542 */                 if (n > 0) {
/* 543 */                   j = Integer.parseInt(str.substring(n + 2));
/* 544 */                   k = ZoneInfoCompiler.parseDayOfWeek(str.substring(0, n));
/* 545 */                   bool = false;
/*     */                 } else {
/* 547 */                   throw new IllegalArgumentException(str);
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */           
/* 553 */           if (paramStringTokenizer.hasMoreTokens()) {
/* 554 */             str = paramStringTokenizer.nextToken();
/* 555 */             c = ZoneInfoCompiler.parseZoneChar(str.charAt(str.length() - 1));
/* 556 */             if (str.equals("24:00")) {
/* 557 */               LocalDate localLocalDate = j == -1 ? new LocalDate(2001, i, 1).plusMonths(1) : new LocalDate(2001, i, j).plusDays(1);
/*     */               
/*     */ 
/* 560 */               bool = j != -1;
/* 561 */               i = localLocalDate.getMonthOfYear();
/* 562 */               j = localLocalDate.getDayOfMonth();
/* 563 */               k = (k - 1 + 1) % 7 + 1;
/*     */             } else {
/* 565 */               m = ZoneInfoCompiler.parseTime(str);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 571 */       this.iMonthOfYear = i;
/* 572 */       this.iDayOfMonth = j;
/* 573 */       this.iDayOfWeek = k;
/* 574 */       this.iAdvanceDayOfWeek = bool;
/* 575 */       this.iMillisOfDay = m;
/* 576 */       this.iZoneChar = c;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void addRecurring(DateTimeZoneBuilder paramDateTimeZoneBuilder, String paramString, int paramInt1, int paramInt2, int paramInt3)
/*     */     {
/* 585 */       paramDateTimeZoneBuilder.addRecurringSavings(paramString, paramInt1, paramInt2, paramInt3, this.iZoneChar, this.iMonthOfYear, this.iDayOfMonth, this.iDayOfWeek, this.iAdvanceDayOfWeek, this.iMillisOfDay);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void addCutover(DateTimeZoneBuilder paramDateTimeZoneBuilder, int paramInt)
/*     */     {
/* 599 */       paramDateTimeZoneBuilder.addCutover(paramInt, this.iZoneChar, this.iMonthOfYear, this.iDayOfMonth, this.iDayOfWeek, this.iAdvanceDayOfWeek, this.iMillisOfDay);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public String toString()
/*     */     {
/* 609 */       return "MonthOfYear: " + this.iMonthOfYear + "\n" + "DayOfMonth: " + this.iDayOfMonth + "\n" + "DayOfWeek: " + this.iDayOfWeek + "\n" + "AdvanceDayOfWeek: " + this.iAdvanceDayOfWeek + "\n" + "MillisOfDay: " + this.iMillisOfDay + "\n" + "ZoneChar: " + this.iZoneChar + "\n";
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private static class Rule
/*     */   {
/*     */     public final String iName;
/*     */     
/*     */     public final int iFromYear;
/*     */     
/*     */     public final int iToYear;
/*     */     
/*     */     public final String iType;
/*     */     public final ZoneInfoCompiler.DateTimeOfYear iDateTimeOfYear;
/*     */     public final int iSaveMillis;
/*     */     public final String iLetterS;
/*     */     
/*     */     Rule(StringTokenizer paramStringTokenizer)
/*     */     {
/* 629 */       this.iName = paramStringTokenizer.nextToken().intern();
/* 630 */       this.iFromYear = ZoneInfoCompiler.parseYear(paramStringTokenizer.nextToken(), 0);
/* 631 */       this.iToYear = ZoneInfoCompiler.parseYear(paramStringTokenizer.nextToken(), this.iFromYear);
/* 632 */       if (this.iToYear < this.iFromYear) {
/* 633 */         throw new IllegalArgumentException();
/*     */       }
/* 635 */       this.iType = ZoneInfoCompiler.parseOptional(paramStringTokenizer.nextToken());
/* 636 */       this.iDateTimeOfYear = new ZoneInfoCompiler.DateTimeOfYear(paramStringTokenizer);
/* 637 */       this.iSaveMillis = ZoneInfoCompiler.parseTime(paramStringTokenizer.nextToken());
/* 638 */       this.iLetterS = ZoneInfoCompiler.parseOptional(paramStringTokenizer.nextToken());
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public void addRecurring(DateTimeZoneBuilder paramDateTimeZoneBuilder, String paramString)
/*     */     {
/* 645 */       String str = formatName(paramString);
/* 646 */       this.iDateTimeOfYear.addRecurring(paramDateTimeZoneBuilder, str, this.iSaveMillis, this.iFromYear, this.iToYear);
/*     */     }
/*     */     
/*     */     private String formatName(String paramString)
/*     */     {
/* 651 */       int i = paramString.indexOf('/');
/* 652 */       if (i > 0) {
/* 653 */         if (this.iSaveMillis == 0)
/*     */         {
/* 655 */           return paramString.substring(0, i).intern();
/*     */         }
/* 657 */         return paramString.substring(i + 1).intern();
/*     */       }
/*     */       
/* 660 */       i = paramString.indexOf("%s");
/* 661 */       if (i < 0) {
/* 662 */         return paramString;
/*     */       }
/* 664 */       String str1 = paramString.substring(0, i);
/* 665 */       String str2 = paramString.substring(i + 2);
/*     */       String str3;
/* 667 */       if (this.iLetterS == null) {
/* 668 */         str3 = str1.concat(str2);
/*     */       } else {
/* 670 */         str3 = str1 + this.iLetterS + str2;
/*     */       }
/* 672 */       return str3.intern();
/*     */     }
/*     */     
/*     */     public String toString() {
/* 676 */       return "[Rule]\nName: " + this.iName + "\n" + "FromYear: " + this.iFromYear + "\n" + "ToYear: " + this.iToYear + "\n" + "Type: " + this.iType + "\n" + this.iDateTimeOfYear + "SaveMillis: " + this.iSaveMillis + "\n" + "LetterS: " + this.iLetterS + "\n";
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static class RuleSet
/*     */   {
/*     */     private List iRules;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     RuleSet(ZoneInfoCompiler.Rule paramRule)
/*     */     {
/* 692 */       this.iRules = new ArrayList();
/* 693 */       this.iRules.add(paramRule);
/*     */     }
/*     */     
/*     */     void addRule(ZoneInfoCompiler.Rule paramRule) {
/* 697 */       if (!paramRule.iName.equals(((ZoneInfoCompiler.Rule)this.iRules.get(0)).iName)) {
/* 698 */         throw new IllegalArgumentException("Rule name mismatch");
/*     */       }
/* 700 */       this.iRules.add(paramRule);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public void addRecurring(DateTimeZoneBuilder paramDateTimeZoneBuilder, String paramString)
/*     */     {
/* 707 */       for (int i = 0; i < this.iRules.size(); i++) {
/* 708 */         ZoneInfoCompiler.Rule localRule = (ZoneInfoCompiler.Rule)this.iRules.get(i);
/* 709 */         localRule.addRecurring(paramDateTimeZoneBuilder, paramString);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static class Zone
/*     */   {
/*     */     public final String iName;
/*     */     public final int iOffsetMillis;
/*     */     public final String iRules;
/*     */     public final String iFormat;
/*     */     public final int iUntilYear;
/*     */     public final ZoneInfoCompiler.DateTimeOfYear iUntilDateTimeOfYear;
/*     */     private Zone iNext;
/*     */     
/*     */     Zone(StringTokenizer paramStringTokenizer) {
/* 725 */       this(paramStringTokenizer.nextToken(), paramStringTokenizer);
/*     */     }
/*     */     
/*     */     private Zone(String paramString, StringTokenizer paramStringTokenizer) {
/* 729 */       this.iName = paramString.intern();
/* 730 */       this.iOffsetMillis = ZoneInfoCompiler.parseTime(paramStringTokenizer.nextToken());
/* 731 */       this.iRules = ZoneInfoCompiler.parseOptional(paramStringTokenizer.nextToken());
/* 732 */       this.iFormat = paramStringTokenizer.nextToken().intern();
/*     */       
/* 734 */       int i = Integer.MAX_VALUE;
/* 735 */       ZoneInfoCompiler.DateTimeOfYear localDateTimeOfYear = ZoneInfoCompiler.getStartOfYear();
/*     */       
/* 737 */       if (paramStringTokenizer.hasMoreTokens()) {
/* 738 */         i = Integer.parseInt(paramStringTokenizer.nextToken());
/* 739 */         if (paramStringTokenizer.hasMoreTokens()) {
/* 740 */           localDateTimeOfYear = new ZoneInfoCompiler.DateTimeOfYear(paramStringTokenizer);
/*     */         }
/*     */       }
/*     */       
/* 744 */       this.iUntilYear = i;
/* 745 */       this.iUntilDateTimeOfYear = localDateTimeOfYear;
/*     */     }
/*     */     
/*     */     void chain(StringTokenizer paramStringTokenizer) {
/* 749 */       if (this.iNext != null) {
/* 750 */         this.iNext.chain(paramStringTokenizer);
/*     */       } else {
/* 752 */         this.iNext = new Zone(this.iName, paramStringTokenizer);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void addToBuilder(DateTimeZoneBuilder paramDateTimeZoneBuilder, Map paramMap)
/*     */     {
/* 768 */       addToBuilder(this, paramDateTimeZoneBuilder, paramMap);
/*     */     }
/*     */     
/*     */     private static void addToBuilder(Zone paramZone, DateTimeZoneBuilder paramDateTimeZoneBuilder, Map paramMap)
/*     */     {
/* 775 */       for (; 
/*     */           
/* 775 */           paramZone != null; paramZone = paramZone.iNext) {
/* 776 */         paramDateTimeZoneBuilder.setStandardOffset(paramZone.iOffsetMillis);
/*     */         
/* 778 */         if (paramZone.iRules == null) {
/* 779 */           paramDateTimeZoneBuilder.setFixedSavings(paramZone.iFormat, 0);
/*     */         } else {
/*     */           try
/*     */           {
/* 783 */             int i = ZoneInfoCompiler.parseTime(paramZone.iRules);
/* 784 */             paramDateTimeZoneBuilder.setFixedSavings(paramZone.iFormat, i);
/*     */           }
/*     */           catch (Exception localException) {
/* 787 */             ZoneInfoCompiler.RuleSet localRuleSet = (ZoneInfoCompiler.RuleSet)paramMap.get(paramZone.iRules);
/* 788 */             if (localRuleSet == null) {
/* 789 */               throw new IllegalArgumentException("Rules not found: " + paramZone.iRules);
/*     */             }
/*     */             
/* 792 */             localRuleSet.addRecurring(paramDateTimeZoneBuilder, paramZone.iFormat);
/*     */           }
/*     */         }
/*     */         
/* 796 */         if (paramZone.iUntilYear == Integer.MAX_VALUE) {
/*     */           break;
/*     */         }
/*     */         
/* 800 */         paramZone.iUntilDateTimeOfYear.addCutover(paramDateTimeZoneBuilder, paramZone.iUntilYear);
/*     */       }
/*     */     }
/*     */     
/*     */     public String toString() {
/* 805 */       String str = "[Zone]\nName: " + this.iName + "\n" + "OffsetMillis: " + this.iOffsetMillis + "\n" + "Rules: " + this.iRules + "\n" + "Format: " + this.iFormat + "\n" + "UntilYear: " + this.iUntilYear + "\n" + this.iUntilDateTimeOfYear;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 814 */       if (this.iNext == null) {
/* 815 */         return str;
/*     */       }
/*     */       
/* 818 */       return str + "...\n" + this.iNext.toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\tz\ZoneInfoCompiler.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */