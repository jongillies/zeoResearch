/*     */ package org.joda.time.format;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTime;
/*     */ import org.joda.time.DateTimeZone;
/*     */ import org.joda.time.ReadablePartial;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DateTimeFormat
/*     */ {
/*     */   static final int FULL = 0;
/*     */   static final int LONG = 1;
/*     */   static final int MEDIUM = 2;
/*     */   static final int SHORT = 3;
/*     */   static final int NONE = 4;
/*     */   static final int DATE = 0;
/*     */   static final int TIME = 1;
/*     */   static final int DATETIME = 2;
/* 147 */   private static final Map cPatternedCache = new HashMap(7);
/*     */   
/* 149 */   private static final DateTimeFormatter[] cStyleCache = new DateTimeFormatter[25];
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFormatter forPattern(String paramString)
/*     */   {
/* 170 */     return createFormatterForPattern(paramString);
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
/*     */   public static DateTimeFormatter forStyle(String paramString)
/*     */   {
/* 194 */     return createFormatterForStyle(paramString);
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
/*     */   public static String patternForStyle(String paramString, Locale paramLocale)
/*     */   {
/* 212 */     DateTimeFormatter localDateTimeFormatter = createFormatterForStyle(paramString);
/* 213 */     if (paramLocale == null) {
/* 214 */       paramLocale = Locale.getDefault();
/*     */     }
/*     */     
/* 217 */     return ((StyleFormatter)localDateTimeFormatter.getPrinter()).getPattern(paramLocale);
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
/*     */   public static DateTimeFormatter shortDate()
/*     */   {
/* 230 */     return createFormatterForStyleIndex(3, 4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFormatter shortTime()
/*     */   {
/* 242 */     return createFormatterForStyleIndex(4, 3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFormatter shortDateTime()
/*     */   {
/* 254 */     return createFormatterForStyleIndex(3, 3);
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
/*     */   public static DateTimeFormatter mediumDate()
/*     */   {
/* 267 */     return createFormatterForStyleIndex(2, 4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFormatter mediumTime()
/*     */   {
/* 279 */     return createFormatterForStyleIndex(4, 2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFormatter mediumDateTime()
/*     */   {
/* 291 */     return createFormatterForStyleIndex(2, 2);
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
/*     */   public static DateTimeFormatter longDate()
/*     */   {
/* 304 */     return createFormatterForStyleIndex(1, 4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFormatter longTime()
/*     */   {
/* 316 */     return createFormatterForStyleIndex(4, 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFormatter longDateTime()
/*     */   {
/* 328 */     return createFormatterForStyleIndex(1, 1);
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
/*     */   public static DateTimeFormatter fullDate()
/*     */   {
/* 341 */     return createFormatterForStyleIndex(0, 4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFormatter fullTime()
/*     */   {
/* 353 */     return createFormatterForStyleIndex(4, 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeFormatter fullDateTime()
/*     */   {
/* 365 */     return createFormatterForStyleIndex(0, 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static void appendPatternTo(DateTimeFormatterBuilder paramDateTimeFormatterBuilder, String paramString)
/*     */   {
/* 377 */     parsePatternTo(paramDateTimeFormatterBuilder, paramString);
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
/*     */   private static void parsePatternTo(DateTimeFormatterBuilder paramDateTimeFormatterBuilder, String paramString)
/*     */   {
/* 400 */     int i = paramString.length();
/* 401 */     int[] arrayOfInt = new int[1];
/*     */     
/* 403 */     for (int j = 0; j < i; j++) {
/* 404 */       arrayOfInt[0] = j;
/* 405 */       String str1 = parseToken(paramString, arrayOfInt);
/* 406 */       j = arrayOfInt[0];
/*     */       
/* 408 */       int k = str1.length();
/* 409 */       if (k == 0) {
/*     */         break;
/*     */       }
/* 412 */       int m = str1.charAt(0);
/*     */       
/* 414 */       switch (m) {
/*     */       case 71: 
/* 416 */         paramDateTimeFormatterBuilder.appendEraText();
/* 417 */         break;
/*     */       case 67: 
/* 419 */         paramDateTimeFormatterBuilder.appendCenturyOfEra(k, k);
/* 420 */         break;
/*     */       case 89: 
/*     */       case 120: 
/*     */       case 121: 
/* 424 */         if (k == 2) {
/* 425 */           boolean bool = true;
/*     */           
/*     */ 
/* 428 */           if (j + 1 < i) {
/* 429 */             arrayOfInt[0] += 1;
/* 430 */             if (isNumericToken(parseToken(paramString, arrayOfInt)))
/*     */             {
/*     */ 
/*     */ 
/* 434 */               bool = false;
/*     */             }
/* 436 */             arrayOfInt[0] -= 1;
/*     */           }
/*     */           
/*     */ 
/* 440 */           switch (m) {
/*     */           case 120: 
/* 442 */             paramDateTimeFormatterBuilder.appendTwoDigitWeekyear(new DateTime().getWeekyear() - 30, bool);
/*     */             
/* 444 */             break;
/*     */           case 89: 
/*     */           case 121: 
/*     */           default: 
/* 448 */             paramDateTimeFormatterBuilder.appendTwoDigitYear(new DateTime().getYear() - 30, bool);
/* 449 */             break;
/*     */           }
/*     */         }
/*     */         else {
/* 453 */           int n = 9;
/*     */           
/*     */ 
/* 456 */           if (j + 1 < i) {
/* 457 */             arrayOfInt[0] += 1;
/* 458 */             if (isNumericToken(parseToken(paramString, arrayOfInt)))
/*     */             {
/* 460 */               n = k;
/*     */             }
/* 462 */             arrayOfInt[0] -= 1;
/*     */           }
/*     */           
/* 465 */           switch (m) {
/*     */           case 120: 
/* 467 */             paramDateTimeFormatterBuilder.appendWeekyear(k, n);
/* 468 */             break;
/*     */           case 121: 
/* 470 */             paramDateTimeFormatterBuilder.appendYear(k, n);
/* 471 */             break;
/*     */           case 89: 
/* 473 */             paramDateTimeFormatterBuilder.appendYearOfEra(k, n);
/*     */           }
/*     */           
/*     */         }
/* 477 */         break;
/*     */       case 77: 
/* 479 */         if (k >= 3) {
/* 480 */           if (k >= 4) {
/* 481 */             paramDateTimeFormatterBuilder.appendMonthOfYearText();
/*     */           } else {
/* 483 */             paramDateTimeFormatterBuilder.appendMonthOfYearShortText();
/*     */           }
/*     */         } else {
/* 486 */           paramDateTimeFormatterBuilder.appendMonthOfYear(k);
/*     */         }
/* 488 */         break;
/*     */       case 100: 
/* 490 */         paramDateTimeFormatterBuilder.appendDayOfMonth(k);
/* 491 */         break;
/*     */       case 97: 
/* 493 */         paramDateTimeFormatterBuilder.appendHalfdayOfDayText();
/* 494 */         break;
/*     */       case 104: 
/* 496 */         paramDateTimeFormatterBuilder.appendClockhourOfHalfday(k);
/* 497 */         break;
/*     */       case 72: 
/* 499 */         paramDateTimeFormatterBuilder.appendHourOfDay(k);
/* 500 */         break;
/*     */       case 107: 
/* 502 */         paramDateTimeFormatterBuilder.appendClockhourOfDay(k);
/* 503 */         break;
/*     */       case 75: 
/* 505 */         paramDateTimeFormatterBuilder.appendHourOfHalfday(k);
/* 506 */         break;
/*     */       case 109: 
/* 508 */         paramDateTimeFormatterBuilder.appendMinuteOfHour(k);
/* 509 */         break;
/*     */       case 115: 
/* 511 */         paramDateTimeFormatterBuilder.appendSecondOfMinute(k);
/* 512 */         break;
/*     */       case 83: 
/* 514 */         paramDateTimeFormatterBuilder.appendFractionOfSecond(k, k);
/* 515 */         break;
/*     */       case 101: 
/* 517 */         paramDateTimeFormatterBuilder.appendDayOfWeek(k);
/* 518 */         break;
/*     */       case 69: 
/* 520 */         if (k >= 4) {
/* 521 */           paramDateTimeFormatterBuilder.appendDayOfWeekText();
/*     */         } else {
/* 523 */           paramDateTimeFormatterBuilder.appendDayOfWeekShortText();
/*     */         }
/* 525 */         break;
/*     */       case 68: 
/* 527 */         paramDateTimeFormatterBuilder.appendDayOfYear(k);
/* 528 */         break;
/*     */       case 119: 
/* 530 */         paramDateTimeFormatterBuilder.appendWeekOfWeekyear(k);
/* 531 */         break;
/*     */       case 122: 
/* 533 */         if (k >= 4) {
/* 534 */           paramDateTimeFormatterBuilder.appendTimeZoneName();
/*     */         } else {
/* 536 */           paramDateTimeFormatterBuilder.appendTimeZoneShortName();
/*     */         }
/* 538 */         break;
/*     */       case 90: 
/* 540 */         if (k == 1) {
/* 541 */           paramDateTimeFormatterBuilder.appendTimeZoneOffset(null, false, 2, 2);
/* 542 */         } else if (k == 2) {
/* 543 */           paramDateTimeFormatterBuilder.appendTimeZoneOffset(null, true, 2, 2);
/*     */         } else {
/* 545 */           paramDateTimeFormatterBuilder.appendTimeZoneId();
/*     */         }
/* 547 */         break;
/*     */       case 39: 
/* 549 */         String str2 = str1.substring(1);
/* 550 */         if (str2.length() == 1) {
/* 551 */           paramDateTimeFormatterBuilder.appendLiteral(str2.charAt(0));
/*     */         }
/*     */         else
/*     */         {
/* 555 */           paramDateTimeFormatterBuilder.appendLiteral(new String(str2));
/*     */         }
/* 557 */         break;
/*     */       case 40: case 41: case 42: case 43: case 44: case 45: case 46: case 47: case 48: case 49: case 50: case 51: case 52: case 53: case 54: case 55: case 56: case 57: case 58: case 59: case 60: case 61: case 62: case 63: case 64: case 65: case 66: case 70: case 73: case 74: case 76: case 78: case 79: case 80: case 81: case 82: case 84: case 85: case 86: case 87: case 88: case 91: case 92: case 93: case 94: case 95: case 96: case 98: case 99: case 102: case 103: case 105: case 106: case 108: case 110: case 111: case 112: case 113: case 114: case 116: case 117: case 118: default: 
/* 559 */         throw new IllegalArgumentException("Illegal pattern component: " + str1);
/*     */       }
/*     */       
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
/*     */   private static String parseToken(String paramString, int[] paramArrayOfInt)
/*     */   {
/* 574 */     StringBuffer localStringBuffer = new StringBuffer();
/*     */     
/* 576 */     int i = paramArrayOfInt[0];
/* 577 */     int j = paramString.length();
/*     */     
/* 579 */     char c1 = paramString.charAt(i);
/* 580 */     if (((c1 >= 'A') && (c1 <= 'Z')) || ((c1 >= 'a') && (c1 <= 'z')))
/*     */     {
/*     */ 
/* 583 */       localStringBuffer.append(c1);
/*     */     }
/* 585 */     while (i + 1 < j) {
/* 586 */       char c2 = paramString.charAt(i + 1);
/* 587 */       if (c2 == c1) {
/* 588 */         localStringBuffer.append(c1);
/* 589 */         i++; continue;
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 596 */         localStringBuffer.append('\'');
/*     */         
/* 598 */         c2 = '\000';
/* 600 */         for (; 
/* 600 */             i < j; i++) {
/* 601 */           c1 = paramString.charAt(i);
/*     */           
/* 603 */           if (c1 == '\'') {
/* 604 */             if ((i + 1 < j) && (paramString.charAt(i + 1) == '\''))
/*     */             {
/* 606 */               i++;
/* 607 */               localStringBuffer.append(c1);
/*     */             } else {
/* 609 */               c2 = c2 == 0 ? '\001' : '\000';
/*     */             }
/* 611 */           } else { if ((c2 == 0) && (((c1 >= 'A') && (c1 <= 'Z')) || ((c1 >= 'a') && (c1 <= 'z'))))
/*     */             {
/* 613 */               i--;
/* 614 */               break;
/*     */             }
/* 616 */             localStringBuffer.append(c1);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 621 */     paramArrayOfInt[0] = i;
/* 622 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static boolean isNumericToken(String paramString)
/*     */   {
/* 632 */     int i = paramString.length();
/* 633 */     if (i > 0) {
/* 634 */       int j = paramString.charAt(0);
/* 635 */       switch (j) {
/*     */       case 67: 
/*     */       case 68: 
/*     */       case 70: 
/*     */       case 72: 
/*     */       case 75: 
/*     */       case 83: 
/*     */       case 87: 
/*     */       case 89: 
/*     */       case 99: 
/*     */       case 100: 
/*     */       case 101: 
/*     */       case 104: 
/*     */       case 107: 
/*     */       case 109: 
/*     */       case 115: 
/*     */       case 119: 
/*     */       case 120: 
/*     */       case 121: 
/* 654 */         return true;
/*     */       case 77: 
/* 656 */         if (i <= 2) {
/* 657 */           return true;
/*     */         }
/*     */         break;
/*     */       }
/*     */     }
/* 662 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static DateTimeFormatter createFormatterForPattern(String paramString)
/*     */   {
/* 674 */     if ((paramString == null) || (paramString.length() == 0)) {
/* 675 */       throw new IllegalArgumentException("Invalid pattern specification");
/*     */     }
/* 677 */     DateTimeFormatter localDateTimeFormatter = null;
/* 678 */     synchronized (cPatternedCache) {
/* 679 */       localDateTimeFormatter = (DateTimeFormatter)cPatternedCache.get(paramString);
/* 680 */       if (localDateTimeFormatter == null) {
/* 681 */         DateTimeFormatterBuilder localDateTimeFormatterBuilder = new DateTimeFormatterBuilder();
/* 682 */         parsePatternTo(localDateTimeFormatterBuilder, paramString);
/* 683 */         localDateTimeFormatter = localDateTimeFormatterBuilder.toFormatter();
/*     */         
/* 685 */         cPatternedCache.put(paramString, localDateTimeFormatter);
/*     */       }
/*     */     }
/* 688 */     return localDateTimeFormatter;
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
/*     */   private static DateTimeFormatter createFormatterForStyle(String paramString)
/*     */   {
/* 701 */     if ((paramString == null) || (paramString.length() != 2)) {
/* 702 */       throw new IllegalArgumentException("Invalid style specification: " + paramString);
/*     */     }
/* 704 */     int i = selectStyle(paramString.charAt(0));
/* 705 */     int j = selectStyle(paramString.charAt(1));
/* 706 */     if ((i == 4) && (j == 4)) {
/* 707 */       throw new IllegalArgumentException("Style '--' is invalid");
/*     */     }
/* 709 */     return createFormatterForStyleIndex(i, j);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static DateTimeFormatter createFormatterForStyleIndex(int paramInt1, int paramInt2)
/*     */   {
/* 720 */     int i = (paramInt1 << 2) + paramInt1 + paramInt2;
/* 721 */     DateTimeFormatter localDateTimeFormatter = null;
/* 722 */     synchronized (cStyleCache) {
/* 723 */       localDateTimeFormatter = cStyleCache[i];
/* 724 */       if (localDateTimeFormatter == null) {
/* 725 */         int j = 2;
/* 726 */         if (paramInt1 == 4) {
/* 727 */           j = 1;
/* 728 */         } else if (paramInt2 == 4) {
/* 729 */           j = 0;
/*     */         }
/* 731 */         StyleFormatter localStyleFormatter = new StyleFormatter(paramInt1, paramInt2, j);
/*     */         
/* 733 */         localDateTimeFormatter = new DateTimeFormatter(localStyleFormatter, localStyleFormatter);
/* 734 */         cStyleCache[i] = localDateTimeFormatter;
/*     */       }
/*     */     }
/* 737 */     return localDateTimeFormatter;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static int selectStyle(char paramChar)
/*     */   {
/* 747 */     switch (paramChar) {
/*     */     case 'S': 
/* 749 */       return 3;
/*     */     case 'M': 
/* 751 */       return 2;
/*     */     case 'L': 
/* 753 */       return 1;
/*     */     case 'F': 
/* 755 */       return 0;
/*     */     case '-': 
/* 757 */       return 4;
/*     */     }
/* 759 */     throw new IllegalArgumentException("Invalid style character: " + paramChar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   static class StyleFormatter
/*     */     implements DateTimePrinter, DateTimeParser
/*     */   {
/* 767 */     private static final Map cCache = new HashMap();
/*     */     
/*     */     private final int iDateStyle;
/*     */     private final int iTimeStyle;
/*     */     private final int iType;
/*     */     
/*     */     StyleFormatter(int paramInt1, int paramInt2, int paramInt3)
/*     */     {
/* 775 */       this.iDateStyle = paramInt1;
/* 776 */       this.iTimeStyle = paramInt2;
/* 777 */       this.iType = paramInt3;
/*     */     }
/*     */     
/*     */     public int estimatePrintedLength() {
/* 781 */       return 40;
/*     */     }
/*     */     
/*     */ 
/*     */     public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
/*     */     {
/* 787 */       DateTimePrinter localDateTimePrinter = getFormatter(paramLocale).getPrinter();
/* 788 */       localDateTimePrinter.printTo(paramStringBuffer, paramLong, paramChronology, paramInt, paramDateTimeZone, paramLocale);
/*     */     }
/*     */     
/*     */     public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
/*     */       throws IOException
/*     */     {
/* 794 */       DateTimePrinter localDateTimePrinter = getFormatter(paramLocale).getPrinter();
/* 795 */       localDateTimePrinter.printTo(paramWriter, paramLong, paramChronology, paramInt, paramDateTimeZone, paramLocale);
/*     */     }
/*     */     
/*     */     public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale) {
/* 799 */       DateTimePrinter localDateTimePrinter = getFormatter(paramLocale).getPrinter();
/* 800 */       localDateTimePrinter.printTo(paramStringBuffer, paramReadablePartial, paramLocale);
/*     */     }
/*     */     
/*     */     public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale) throws IOException {
/* 804 */       DateTimePrinter localDateTimePrinter = getFormatter(paramLocale).getPrinter();
/* 805 */       localDateTimePrinter.printTo(paramWriter, paramReadablePartial, paramLocale);
/*     */     }
/*     */     
/*     */     public int estimateParsedLength() {
/* 809 */       return 40;
/*     */     }
/*     */     
/*     */     public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt) {
/* 813 */       DateTimeParser localDateTimeParser = getFormatter(paramDateTimeParserBucket.getLocale()).getParser();
/* 814 */       return localDateTimeParser.parseInto(paramDateTimeParserBucket, paramString, paramInt);
/*     */     }
/*     */     
/*     */     private DateTimeFormatter getFormatter(Locale paramLocale) {
/* 818 */       paramLocale = paramLocale == null ? Locale.getDefault() : paramLocale;
/* 819 */       String str1 = Integer.toString(this.iType + (this.iDateStyle << 4) + (this.iTimeStyle << 8)) + paramLocale.toString();
/* 820 */       DateTimeFormatter localDateTimeFormatter = null;
/* 821 */       synchronized (cCache) {
/* 822 */         localDateTimeFormatter = (DateTimeFormatter)cCache.get(str1);
/* 823 */         if (localDateTimeFormatter == null) {
/* 824 */           String str2 = getPattern(paramLocale);
/* 825 */           localDateTimeFormatter = DateTimeFormat.forPattern(str2);
/* 826 */           cCache.put(str1, localDateTimeFormatter);
/*     */         }
/*     */       }
/* 829 */       return localDateTimeFormatter;
/*     */     }
/*     */     
/*     */     String getPattern(Locale paramLocale) {
/* 833 */       DateFormat localDateFormat = null;
/* 834 */       switch (this.iType) {
/*     */       case 0: 
/* 836 */         localDateFormat = DateFormat.getDateInstance(this.iDateStyle, paramLocale);
/* 837 */         break;
/*     */       case 1: 
/* 839 */         localDateFormat = DateFormat.getTimeInstance(this.iTimeStyle, paramLocale);
/* 840 */         break;
/*     */       case 2: 
/* 842 */         localDateFormat = DateFormat.getDateTimeInstance(this.iDateStyle, this.iTimeStyle, paramLocale);
/*     */       }
/*     */       
/* 845 */       if (!(localDateFormat instanceof SimpleDateFormat)) {
/* 846 */         throw new IllegalArgumentException("No datetime pattern for locale: " + paramLocale);
/*     */       }
/* 848 */       return ((SimpleDateFormat)localDateFormat).toPattern();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\format\DateTimeFormat.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */