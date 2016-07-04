/*      */ package org.joda.time.format;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.Writer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import org.joda.time.Chronology;
/*      */ import org.joda.time.DateTimeField;
/*      */ import org.joda.time.DateTimeFieldType;
/*      */ import org.joda.time.DateTimeZone;
/*      */ import org.joda.time.DurationField;
/*      */ import org.joda.time.MutableDateTime;
/*      */ import org.joda.time.MutableDateTime.Property;
/*      */ import org.joda.time.ReadablePartial;
/*      */ import org.joda.time.field.MillisDurationField;
/*      */ import org.joda.time.field.PreciseDateTimeField;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class DateTimeFormatterBuilder
/*      */ {
/*      */   private ArrayList iElementPairs;
/*      */   private Object iFormatter;
/*      */   
/*      */   public DateTimeFormatterBuilder()
/*      */   {
/*   84 */     this.iElementPairs = new ArrayList();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatter toFormatter()
/*      */   {
/*  104 */     Object localObject = getFormatter();
/*  105 */     DateTimePrinter localDateTimePrinter = null;
/*  106 */     if (isPrinter(localObject)) {
/*  107 */       localDateTimePrinter = (DateTimePrinter)localObject;
/*      */     }
/*  109 */     DateTimeParser localDateTimeParser = null;
/*  110 */     if (isParser(localObject)) {
/*  111 */       localDateTimeParser = (DateTimeParser)localObject;
/*      */     }
/*  113 */     if ((localDateTimePrinter != null) || (localDateTimeParser != null)) {
/*  114 */       return new DateTimeFormatter(localDateTimePrinter, localDateTimeParser);
/*      */     }
/*  116 */     throw new UnsupportedOperationException("Both printing and parsing not supported");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimePrinter toPrinter()
/*      */   {
/*  132 */     Object localObject = getFormatter();
/*  133 */     if (isPrinter(localObject)) {
/*  134 */       return (DateTimePrinter)localObject;
/*      */     }
/*  136 */     throw new UnsupportedOperationException("Printing is not supported");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeParser toParser()
/*      */   {
/*  152 */     Object localObject = getFormatter();
/*  153 */     if (isParser(localObject)) {
/*  154 */       return (DateTimeParser)localObject;
/*      */     }
/*  156 */     throw new UnsupportedOperationException("Parsing is not supported");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean canBuildFormatter()
/*      */   {
/*  167 */     return isFormatter(getFormatter());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean canBuildPrinter()
/*      */   {
/*  177 */     return isPrinter(getFormatter());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean canBuildParser()
/*      */   {
/*  187 */     return isParser(getFormatter());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clear()
/*      */   {
/*  196 */     this.iFormatter = null;
/*  197 */     this.iElementPairs.clear();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder append(DateTimeFormatter paramDateTimeFormatter)
/*      */   {
/*  209 */     if (paramDateTimeFormatter == null) {
/*  210 */       throw new IllegalArgumentException("No formatter supplied");
/*      */     }
/*  212 */     return append0(paramDateTimeFormatter.getPrinter(), paramDateTimeFormatter.getParser());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder append(DateTimePrinter paramDateTimePrinter)
/*      */   {
/*  224 */     checkPrinter(paramDateTimePrinter);
/*  225 */     return append0(paramDateTimePrinter, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder append(DateTimeParser paramDateTimeParser)
/*      */   {
/*  237 */     checkParser(paramDateTimeParser);
/*  238 */     return append0(null, paramDateTimeParser);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder append(DateTimePrinter paramDateTimePrinter, DateTimeParser paramDateTimeParser)
/*      */   {
/*  250 */     checkPrinter(paramDateTimePrinter);
/*  251 */     checkParser(paramDateTimeParser);
/*  252 */     return append0(paramDateTimePrinter, paramDateTimeParser);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder append(DateTimePrinter paramDateTimePrinter, DateTimeParser[] paramArrayOfDateTimeParser)
/*      */   {
/*  273 */     if (paramDateTimePrinter != null) {
/*  274 */       checkPrinter(paramDateTimePrinter);
/*      */     }
/*  276 */     if (paramArrayOfDateTimeParser == null) {
/*  277 */       throw new IllegalArgumentException("No parsers supplied");
/*      */     }
/*  279 */     int i = paramArrayOfDateTimeParser.length;
/*  280 */     if (i == 1) {
/*  281 */       if (paramArrayOfDateTimeParser[0] == null) {
/*  282 */         throw new IllegalArgumentException("No parser supplied");
/*      */       }
/*  284 */       return append0(paramDateTimePrinter, paramArrayOfDateTimeParser[0]);
/*      */     }
/*      */     
/*  287 */     DateTimeParser[] arrayOfDateTimeParser = new DateTimeParser[i];
/*      */     
/*  289 */     for (int j = 0; j < i - 1; j++) {
/*  290 */       if ((arrayOfDateTimeParser[j] = paramArrayOfDateTimeParser[j]) == null) {
/*  291 */         throw new IllegalArgumentException("Incomplete parser array");
/*      */       }
/*      */     }
/*  294 */     arrayOfDateTimeParser[j] = paramArrayOfDateTimeParser[j];
/*      */     
/*  296 */     return append0(paramDateTimePrinter, new MatchingParser(arrayOfDateTimeParser));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendOptional(DateTimeParser paramDateTimeParser)
/*      */   {
/*  307 */     checkParser(paramDateTimeParser);
/*  308 */     DateTimeParser[] arrayOfDateTimeParser = { paramDateTimeParser, null };
/*  309 */     return append0(null, new MatchingParser(arrayOfDateTimeParser));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void checkParser(DateTimeParser paramDateTimeParser)
/*      */   {
/*  319 */     if (paramDateTimeParser == null) {
/*  320 */       throw new IllegalArgumentException("No parser supplied");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void checkPrinter(DateTimePrinter paramDateTimePrinter)
/*      */   {
/*  330 */     if (paramDateTimePrinter == null) {
/*  331 */       throw new IllegalArgumentException("No printer supplied");
/*      */     }
/*      */   }
/*      */   
/*      */   private DateTimeFormatterBuilder append0(Object paramObject) {
/*  336 */     this.iFormatter = null;
/*      */     
/*  338 */     this.iElementPairs.add(paramObject);
/*  339 */     this.iElementPairs.add(paramObject);
/*  340 */     return this;
/*      */   }
/*      */   
/*      */   private DateTimeFormatterBuilder append0(DateTimePrinter paramDateTimePrinter, DateTimeParser paramDateTimeParser)
/*      */   {
/*  345 */     this.iFormatter = null;
/*  346 */     this.iElementPairs.add(paramDateTimePrinter);
/*  347 */     this.iElementPairs.add(paramDateTimeParser);
/*  348 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendLiteral(char paramChar)
/*      */   {
/*  359 */     return append0(new CharacterLiteral(paramChar));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendLiteral(String paramString)
/*      */   {
/*  370 */     if (paramString == null) {
/*  371 */       throw new IllegalArgumentException("Literal must not be null");
/*      */     }
/*  373 */     switch (paramString.length()) {
/*      */     case 0: 
/*  375 */       return this;
/*      */     case 1: 
/*  377 */       return append0(new CharacterLiteral(paramString.charAt(0)));
/*      */     }
/*  379 */     return append0(new StringLiteral(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendDecimal(DateTimeFieldType paramDateTimeFieldType, int paramInt1, int paramInt2)
/*      */   {
/*  396 */     if (paramDateTimeFieldType == null) {
/*  397 */       throw new IllegalArgumentException("Field type must not be null");
/*      */     }
/*  399 */     if (paramInt2 < paramInt1) {
/*  400 */       paramInt2 = paramInt1;
/*      */     }
/*  402 */     if ((paramInt1 < 0) || (paramInt2 <= 0)) {
/*  403 */       throw new IllegalArgumentException();
/*      */     }
/*  405 */     if (paramInt1 <= 1) {
/*  406 */       return append0(new UnpaddedNumber(paramDateTimeFieldType, paramInt2, false));
/*      */     }
/*  408 */     return append0(new PaddedNumber(paramDateTimeFieldType, paramInt2, false, paramInt1));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendFixedDecimal(DateTimeFieldType paramDateTimeFieldType, int paramInt)
/*      */   {
/*  426 */     if (paramDateTimeFieldType == null) {
/*  427 */       throw new IllegalArgumentException("Field type must not be null");
/*      */     }
/*  429 */     if (paramInt <= 0) {
/*  430 */       throw new IllegalArgumentException("Illegal number of digits: " + paramInt);
/*      */     }
/*  432 */     return append0(new FixedNumber(paramDateTimeFieldType, paramInt, false));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendSignedDecimal(DateTimeFieldType paramDateTimeFieldType, int paramInt1, int paramInt2)
/*      */   {
/*  448 */     if (paramDateTimeFieldType == null) {
/*  449 */       throw new IllegalArgumentException("Field type must not be null");
/*      */     }
/*  451 */     if (paramInt2 < paramInt1) {
/*  452 */       paramInt2 = paramInt1;
/*      */     }
/*  454 */     if ((paramInt1 < 0) || (paramInt2 <= 0)) {
/*  455 */       throw new IllegalArgumentException();
/*      */     }
/*  457 */     if (paramInt1 <= 1) {
/*  458 */       return append0(new UnpaddedNumber(paramDateTimeFieldType, paramInt2, true));
/*      */     }
/*  460 */     return append0(new PaddedNumber(paramDateTimeFieldType, paramInt2, true, paramInt1));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendFixedSignedDecimal(DateTimeFieldType paramDateTimeFieldType, int paramInt)
/*      */   {
/*  478 */     if (paramDateTimeFieldType == null) {
/*  479 */       throw new IllegalArgumentException("Field type must not be null");
/*      */     }
/*  481 */     if (paramInt <= 0) {
/*  482 */       throw new IllegalArgumentException("Illegal number of digits: " + paramInt);
/*      */     }
/*  484 */     return append0(new FixedNumber(paramDateTimeFieldType, paramInt, true));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendText(DateTimeFieldType paramDateTimeFieldType)
/*      */   {
/*  496 */     if (paramDateTimeFieldType == null) {
/*  497 */       throw new IllegalArgumentException("Field type must not be null");
/*      */     }
/*  499 */     return append0(new TextField(paramDateTimeFieldType, false));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendShortText(DateTimeFieldType paramDateTimeFieldType)
/*      */   {
/*  511 */     if (paramDateTimeFieldType == null) {
/*  512 */       throw new IllegalArgumentException("Field type must not be null");
/*      */     }
/*  514 */     return append0(new TextField(paramDateTimeFieldType, true));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendFraction(DateTimeFieldType paramDateTimeFieldType, int paramInt1, int paramInt2)
/*      */   {
/*  532 */     if (paramDateTimeFieldType == null) {
/*  533 */       throw new IllegalArgumentException("Field type must not be null");
/*      */     }
/*  535 */     if (paramInt2 < paramInt1) {
/*  536 */       paramInt2 = paramInt1;
/*      */     }
/*  538 */     if ((paramInt1 < 0) || (paramInt2 <= 0)) {
/*  539 */       throw new IllegalArgumentException();
/*      */     }
/*  541 */     return append0(new Fraction(paramDateTimeFieldType, paramInt1, paramInt2));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendFractionOfSecond(int paramInt1, int paramInt2)
/*      */   {
/*  550 */     return appendFraction(DateTimeFieldType.secondOfDay(), paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendFractionOfMinute(int paramInt1, int paramInt2)
/*      */   {
/*  559 */     return appendFraction(DateTimeFieldType.minuteOfDay(), paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendFractionOfHour(int paramInt1, int paramInt2)
/*      */   {
/*  568 */     return appendFraction(DateTimeFieldType.hourOfDay(), paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendFractionOfDay(int paramInt1, int paramInt2)
/*      */   {
/*  577 */     return appendFraction(DateTimeFieldType.dayOfYear(), paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendMillisOfSecond(int paramInt)
/*      */   {
/*  594 */     return appendDecimal(DateTimeFieldType.millisOfSecond(), paramInt, 3);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendMillisOfDay(int paramInt)
/*      */   {
/*  604 */     return appendDecimal(DateTimeFieldType.millisOfDay(), paramInt, 8);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendSecondOfMinute(int paramInt)
/*      */   {
/*  614 */     return appendDecimal(DateTimeFieldType.secondOfMinute(), paramInt, 2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendSecondOfDay(int paramInt)
/*      */   {
/*  624 */     return appendDecimal(DateTimeFieldType.secondOfDay(), paramInt, 5);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendMinuteOfHour(int paramInt)
/*      */   {
/*  634 */     return appendDecimal(DateTimeFieldType.minuteOfHour(), paramInt, 2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendMinuteOfDay(int paramInt)
/*      */   {
/*  644 */     return appendDecimal(DateTimeFieldType.minuteOfDay(), paramInt, 4);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendHourOfDay(int paramInt)
/*      */   {
/*  654 */     return appendDecimal(DateTimeFieldType.hourOfDay(), paramInt, 2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendClockhourOfDay(int paramInt)
/*      */   {
/*  664 */     return appendDecimal(DateTimeFieldType.clockhourOfDay(), paramInt, 2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendHourOfHalfday(int paramInt)
/*      */   {
/*  674 */     return appendDecimal(DateTimeFieldType.hourOfHalfday(), paramInt, 2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendClockhourOfHalfday(int paramInt)
/*      */   {
/*  684 */     return appendDecimal(DateTimeFieldType.clockhourOfHalfday(), paramInt, 2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendDayOfWeek(int paramInt)
/*      */   {
/*  694 */     return appendDecimal(DateTimeFieldType.dayOfWeek(), paramInt, 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendDayOfMonth(int paramInt)
/*      */   {
/*  704 */     return appendDecimal(DateTimeFieldType.dayOfMonth(), paramInt, 2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendDayOfYear(int paramInt)
/*      */   {
/*  714 */     return appendDecimal(DateTimeFieldType.dayOfYear(), paramInt, 3);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendWeekOfWeekyear(int paramInt)
/*      */   {
/*  724 */     return appendDecimal(DateTimeFieldType.weekOfWeekyear(), paramInt, 2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendWeekyear(int paramInt1, int paramInt2)
/*      */   {
/*  736 */     return appendSignedDecimal(DateTimeFieldType.weekyear(), paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendMonthOfYear(int paramInt)
/*      */   {
/*  746 */     return appendDecimal(DateTimeFieldType.monthOfYear(), paramInt, 2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendYear(int paramInt1, int paramInt2)
/*      */   {
/*  758 */     return appendSignedDecimal(DateTimeFieldType.year(), paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendTwoDigitYear(int paramInt)
/*      */   {
/*  780 */     return appendTwoDigitYear(paramInt, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendTwoDigitYear(int paramInt, boolean paramBoolean)
/*      */   {
/*  798 */     return append0(new TwoDigitYear(DateTimeFieldType.year(), paramInt, paramBoolean));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendTwoDigitWeekyear(int paramInt)
/*      */   {
/*  820 */     return appendTwoDigitWeekyear(paramInt, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendTwoDigitWeekyear(int paramInt, boolean paramBoolean)
/*      */   {
/*  838 */     return append0(new TwoDigitYear(DateTimeFieldType.weekyear(), paramInt, paramBoolean));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendYearOfEra(int paramInt1, int paramInt2)
/*      */   {
/*  850 */     return appendDecimal(DateTimeFieldType.yearOfEra(), paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendYearOfCentury(int paramInt1, int paramInt2)
/*      */   {
/*  862 */     return appendDecimal(DateTimeFieldType.yearOfCentury(), paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendCenturyOfEra(int paramInt1, int paramInt2)
/*      */   {
/*  874 */     return appendSignedDecimal(DateTimeFieldType.centuryOfEra(), paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendHalfdayOfDayText()
/*      */   {
/*  884 */     return appendText(DateTimeFieldType.halfdayOfDay());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendDayOfWeekText()
/*      */   {
/*  894 */     return appendText(DateTimeFieldType.dayOfWeek());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendDayOfWeekShortText()
/*      */   {
/*  905 */     return appendShortText(DateTimeFieldType.dayOfWeek());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendMonthOfYearText()
/*      */   {
/*  916 */     return appendText(DateTimeFieldType.monthOfYear());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendMonthOfYearShortText()
/*      */   {
/*  926 */     return appendShortText(DateTimeFieldType.monthOfYear());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendEraText()
/*      */   {
/*  936 */     return appendText(DateTimeFieldType.era());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendTimeZoneName()
/*      */   {
/*  947 */     return append0(new TimeZoneName(0), null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendTimeZoneShortName()
/*      */   {
/*  958 */     return append0(new TimeZoneName(1), null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendTimeZoneId()
/*      */   {
/*  968 */     return append0(new TimeZoneName(2), null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendTimeZoneOffset(String paramString, boolean paramBoolean, int paramInt1, int paramInt2)
/*      */   {
/*  988 */     return append0(new TimeZoneOffset(paramString, paramBoolean, paramInt1, paramInt2));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeFormatterBuilder appendPattern(String paramString)
/*      */   {
/* 1002 */     DateTimeFormat.appendPatternTo(this, paramString);
/* 1003 */     return this;
/*      */   }
/*      */   
/*      */   private Object getFormatter()
/*      */   {
/* 1008 */     Object localObject1 = this.iFormatter;
/*      */     
/* 1010 */     if (localObject1 == null) {
/* 1011 */       if (this.iElementPairs.size() == 2) {
/* 1012 */         Object localObject2 = this.iElementPairs.get(0);
/* 1013 */         Object localObject3 = this.iElementPairs.get(1);
/*      */         
/* 1015 */         if (localObject2 != null) {
/* 1016 */           if ((localObject2 == localObject3) || (localObject3 == null)) {
/* 1017 */             localObject1 = localObject2;
/*      */           }
/*      */         } else {
/* 1020 */           localObject1 = localObject3;
/*      */         }
/*      */       }
/*      */       
/* 1024 */       if (localObject1 == null) {
/* 1025 */         localObject1 = new Composite(this.iElementPairs);
/*      */       }
/*      */       
/* 1028 */       this.iFormatter = localObject1;
/*      */     }
/*      */     
/* 1031 */     return localObject1;
/*      */   }
/*      */   
/*      */   private boolean isPrinter(Object paramObject) {
/* 1035 */     if ((paramObject instanceof DateTimePrinter)) {
/* 1036 */       if ((paramObject instanceof Composite)) {
/* 1037 */         return ((Composite)paramObject).isPrinter();
/*      */       }
/* 1039 */       return true;
/*      */     }
/* 1041 */     return false;
/*      */   }
/*      */   
/*      */   private boolean isParser(Object paramObject) {
/* 1045 */     if ((paramObject instanceof DateTimeParser)) {
/* 1046 */       if ((paramObject instanceof Composite)) {
/* 1047 */         return ((Composite)paramObject).isParser();
/*      */       }
/* 1049 */       return true;
/*      */     }
/* 1051 */     return false;
/*      */   }
/*      */   
/*      */   private boolean isFormatter(Object paramObject) {
/* 1055 */     return (isPrinter(paramObject)) || (isParser(paramObject));
/*      */   }
/*      */   
/*      */   static void appendUnknownString(StringBuffer paramStringBuffer, int paramInt) {
/* 1059 */     int i = paramInt; for (;;) { i--; if (i < 0) break;
/* 1060 */       paramStringBuffer.append(65533);
/*      */     }
/*      */   }
/*      */   
/*      */   static void printUnknownString(Writer paramWriter, int paramInt) throws IOException {
/* 1065 */     int i = paramInt; for (;;) { i--; if (i < 0) break;
/* 1066 */       paramWriter.write(65533);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   static class CharacterLiteral
/*      */     implements DateTimePrinter, DateTimeParser
/*      */   {
/*      */     private final char iValue;
/*      */     
/*      */     CharacterLiteral(char paramChar)
/*      */     {
/* 1078 */       this.iValue = paramChar;
/*      */     }
/*      */     
/*      */     public int estimatePrintedLength() {
/* 1082 */       return 1;
/*      */     }
/*      */     
/*      */ 
/*      */     public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
/*      */     {
/* 1088 */       paramStringBuffer.append(this.iValue);
/*      */     }
/*      */     
/*      */     public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
/*      */       throws IOException
/*      */     {
/* 1094 */       paramWriter.write(this.iValue);
/*      */     }
/*      */     
/*      */     public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale) {
/* 1098 */       paramStringBuffer.append(this.iValue);
/*      */     }
/*      */     
/*      */     public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale) throws IOException {
/* 1102 */       paramWriter.write(this.iValue);
/*      */     }
/*      */     
/*      */     public int estimateParsedLength() {
/* 1106 */       return 1;
/*      */     }
/*      */     
/*      */     public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt) {
/* 1110 */       if (paramInt >= paramString.length()) {
/* 1111 */         return paramInt ^ 0xFFFFFFFF;
/*      */       }
/*      */       
/* 1114 */       char c1 = paramString.charAt(paramInt);
/* 1115 */       char c2 = this.iValue;
/*      */       
/* 1117 */       if (c1 != c2) {
/* 1118 */         c1 = Character.toUpperCase(c1);
/* 1119 */         c2 = Character.toUpperCase(c2);
/* 1120 */         if (c1 != c2) {
/* 1121 */           c1 = Character.toLowerCase(c1);
/* 1122 */           c2 = Character.toLowerCase(c2);
/* 1123 */           if (c1 != c2) {
/* 1124 */             return paramInt ^ 0xFFFFFFFF;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/* 1129 */       return paramInt + 1;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   static class StringLiteral
/*      */     implements DateTimePrinter, DateTimeParser
/*      */   {
/*      */     private final String iValue;
/*      */     
/*      */     StringLiteral(String paramString)
/*      */     {
/* 1141 */       this.iValue = paramString;
/*      */     }
/*      */     
/*      */     public int estimatePrintedLength() {
/* 1145 */       return this.iValue.length();
/*      */     }
/*      */     
/*      */ 
/*      */     public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
/*      */     {
/* 1151 */       paramStringBuffer.append(this.iValue);
/*      */     }
/*      */     
/*      */     public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
/*      */       throws IOException
/*      */     {
/* 1157 */       paramWriter.write(this.iValue);
/*      */     }
/*      */     
/*      */     public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale) {
/* 1161 */       paramStringBuffer.append(this.iValue);
/*      */     }
/*      */     
/*      */     public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale) throws IOException {
/* 1165 */       paramWriter.write(this.iValue);
/*      */     }
/*      */     
/*      */     public int estimateParsedLength() {
/* 1169 */       return this.iValue.length();
/*      */     }
/*      */     
/*      */     public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt) {
/* 1173 */       if (paramString.regionMatches(true, paramInt, this.iValue, 0, this.iValue.length())) {
/* 1174 */         return paramInt + this.iValue.length();
/*      */       }
/* 1176 */       return paramInt ^ 0xFFFFFFFF;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   static abstract class NumberFormatter
/*      */     implements DateTimePrinter, DateTimeParser
/*      */   {
/*      */     protected final DateTimeFieldType iFieldType;
/*      */     protected final int iMaxParsedDigits;
/*      */     protected final boolean iSigned;
/*      */     
/*      */     NumberFormatter(DateTimeFieldType paramDateTimeFieldType, int paramInt, boolean paramBoolean)
/*      */     {
/* 1190 */       this.iFieldType = paramDateTimeFieldType;
/* 1191 */       this.iMaxParsedDigits = paramInt;
/* 1192 */       this.iSigned = paramBoolean;
/*      */     }
/*      */     
/*      */     public int estimateParsedLength() {
/* 1196 */       return this.iMaxParsedDigits;
/*      */     }
/*      */     
/*      */     public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt) {
/* 1200 */       int i = Math.min(this.iMaxParsedDigits, paramString.length() - paramInt);
/*      */       
/* 1202 */       int j = 0;
/* 1203 */       int k = 0;
/* 1204 */       int m; while (k < i) {
/* 1205 */         m = paramString.charAt(paramInt + k);
/* 1206 */         if ((k == 0) && ((m == 45) || (m == 43)) && (this.iSigned)) {
/* 1207 */           j = m == 45 ? 1 : 0;
/*      */           
/*      */ 
/* 1210 */           if ((k + 1 >= i) || ((m = paramString.charAt(paramInt + k + 1)) < '0') || (m > 57)) {
/*      */             break;
/*      */           }
/*      */           
/*      */ 
/*      */ 
/* 1216 */           if (j != 0) {
/* 1217 */             k++;
/*      */           }
/*      */           else {
/* 1220 */             paramInt++;
/*      */           }
/*      */           
/* 1223 */           i = Math.min(i + 1, paramString.length() - paramInt);
/*      */         }
/*      */         else {
/* 1226 */           if ((m < 48) || (m > 57)) {
/*      */             break;
/*      */           }
/* 1229 */           k++;
/*      */         }
/*      */       }
/* 1232 */       if (k == 0) {
/* 1233 */         return paramInt ^ 0xFFFFFFFF;
/*      */       }
/*      */       
/*      */ 
/* 1237 */       if (k >= 9)
/*      */       {
/*      */ 
/* 1240 */         m = Integer.parseInt(paramString.substring(paramInt, paramInt += k));
/*      */       } else {
/* 1242 */         int n = paramInt;
/* 1243 */         if (j != 0) {
/* 1244 */           n++;
/*      */         }
/*      */         try {
/* 1247 */           m = paramString.charAt(n++) - '0';
/*      */         } catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
/* 1249 */           return paramInt ^ 0xFFFFFFFF;
/*      */         }
/* 1251 */         paramInt += k;
/* 1252 */         while (n < paramInt) {
/* 1253 */           m = (m << 3) + (m << 1) + paramString.charAt(n++) - 48;
/*      */         }
/* 1255 */         if (j != 0) {
/* 1256 */           m = -m;
/*      */         }
/*      */       }
/*      */       
/* 1260 */       paramDateTimeParserBucket.saveField(this.iFieldType, m);
/* 1261 */       return paramInt;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   static class UnpaddedNumber
/*      */     extends DateTimeFormatterBuilder.NumberFormatter
/*      */   {
/*      */     protected UnpaddedNumber(DateTimeFieldType paramDateTimeFieldType, int paramInt, boolean paramBoolean)
/*      */     {
/* 1271 */       super(paramInt, paramBoolean);
/*      */     }
/*      */     
/*      */     public int estimatePrintedLength() {
/* 1275 */       return this.iMaxParsedDigits;
/*      */     }
/*      */     
/*      */     public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
/*      */     {
/*      */       try
/*      */       {
/* 1282 */         DateTimeField localDateTimeField = this.iFieldType.getField(paramChronology);
/* 1283 */         FormatUtils.appendUnpaddedInteger(paramStringBuffer, localDateTimeField.get(paramLong));
/*      */       } catch (RuntimeException localRuntimeException) {
/* 1285 */         paramStringBuffer.append(65533);
/*      */       }
/*      */     }
/*      */     
/*      */     public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale) throws IOException
/*      */     {
/*      */       try
/*      */       {
/* 1293 */         DateTimeField localDateTimeField = this.iFieldType.getField(paramChronology);
/* 1294 */         FormatUtils.writeUnpaddedInteger(paramWriter, localDateTimeField.get(paramLong));
/*      */       } catch (RuntimeException localRuntimeException) {
/* 1296 */         paramWriter.write(65533);
/*      */       }
/*      */     }
/*      */     
/*      */     public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale) {
/* 1301 */       if (paramReadablePartial.isSupported(this.iFieldType)) {
/*      */         try {
/* 1303 */           FormatUtils.appendUnpaddedInteger(paramStringBuffer, paramReadablePartial.get(this.iFieldType));
/*      */         } catch (RuntimeException localRuntimeException) {
/* 1305 */           paramStringBuffer.append(65533);
/*      */         }
/*      */       } else {
/* 1308 */         paramStringBuffer.append(65533);
/*      */       }
/*      */     }
/*      */     
/*      */     public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale) throws IOException {
/* 1313 */       if (paramReadablePartial.isSupported(this.iFieldType)) {
/*      */         try {
/* 1315 */           FormatUtils.writeUnpaddedInteger(paramWriter, paramReadablePartial.get(this.iFieldType));
/*      */         } catch (RuntimeException localRuntimeException) {
/* 1317 */           paramWriter.write(65533);
/*      */         }
/*      */       } else {
/* 1320 */         paramWriter.write(65533);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   static class PaddedNumber
/*      */     extends DateTimeFormatterBuilder.NumberFormatter
/*      */   {
/*      */     protected final int iMinPrintedDigits;
/*      */     
/*      */     protected PaddedNumber(DateTimeFieldType paramDateTimeFieldType, int paramInt1, boolean paramBoolean, int paramInt2)
/*      */     {
/* 1333 */       super(paramInt1, paramBoolean);
/* 1334 */       this.iMinPrintedDigits = paramInt2;
/*      */     }
/*      */     
/*      */     public int estimatePrintedLength() {
/* 1338 */       return this.iMaxParsedDigits;
/*      */     }
/*      */     
/*      */     public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
/*      */     {
/*      */       try
/*      */       {
/* 1345 */         DateTimeField localDateTimeField = this.iFieldType.getField(paramChronology);
/* 1346 */         FormatUtils.appendPaddedInteger(paramStringBuffer, localDateTimeField.get(paramLong), this.iMinPrintedDigits);
/*      */       } catch (RuntimeException localRuntimeException) {
/* 1348 */         DateTimeFormatterBuilder.appendUnknownString(paramStringBuffer, this.iMinPrintedDigits);
/*      */       }
/*      */     }
/*      */     
/*      */     public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale) throws IOException
/*      */     {
/*      */       try
/*      */       {
/* 1356 */         DateTimeField localDateTimeField = this.iFieldType.getField(paramChronology);
/* 1357 */         FormatUtils.writePaddedInteger(paramWriter, localDateTimeField.get(paramLong), this.iMinPrintedDigits);
/*      */       } catch (RuntimeException localRuntimeException) {
/* 1359 */         DateTimeFormatterBuilder.printUnknownString(paramWriter, this.iMinPrintedDigits);
/*      */       }
/*      */     }
/*      */     
/*      */     public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale) {
/* 1364 */       if (paramReadablePartial.isSupported(this.iFieldType)) {
/*      */         try {
/* 1366 */           FormatUtils.appendPaddedInteger(paramStringBuffer, paramReadablePartial.get(this.iFieldType), this.iMinPrintedDigits);
/*      */         } catch (RuntimeException localRuntimeException) {
/* 1368 */           DateTimeFormatterBuilder.appendUnknownString(paramStringBuffer, this.iMinPrintedDigits);
/*      */         }
/*      */       } else {
/* 1371 */         DateTimeFormatterBuilder.appendUnknownString(paramStringBuffer, this.iMinPrintedDigits);
/*      */       }
/*      */     }
/*      */     
/*      */     public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale) throws IOException {
/* 1376 */       if (paramReadablePartial.isSupported(this.iFieldType)) {
/*      */         try {
/* 1378 */           FormatUtils.writePaddedInteger(paramWriter, paramReadablePartial.get(this.iFieldType), this.iMinPrintedDigits);
/*      */         } catch (RuntimeException localRuntimeException) {
/* 1380 */           DateTimeFormatterBuilder.printUnknownString(paramWriter, this.iMinPrintedDigits);
/*      */         }
/*      */       } else {
/* 1383 */         DateTimeFormatterBuilder.printUnknownString(paramWriter, this.iMinPrintedDigits);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   static class FixedNumber extends DateTimeFormatterBuilder.PaddedNumber
/*      */   {
/*      */     protected FixedNumber(DateTimeFieldType paramDateTimeFieldType, int paramInt, boolean paramBoolean)
/*      */     {
/* 1392 */       super(paramInt, paramBoolean, paramInt);
/*      */     }
/*      */     
/*      */     public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt) {
/* 1396 */       int i = super.parseInto(paramDateTimeParserBucket, paramString, paramInt);
/* 1397 */       if (i < 0) {
/* 1398 */         return i;
/*      */       }
/* 1400 */       int j = paramInt + this.iMaxParsedDigits;
/* 1401 */       if (i != j) {
/* 1402 */         if (this.iSigned) {
/* 1403 */           int k = paramString.charAt(paramInt);
/* 1404 */           if ((k == 45) || (k == 43)) {
/* 1405 */             j++;
/*      */           }
/*      */         }
/* 1408 */         if (i > j)
/*      */         {
/* 1410 */           return j + 1 ^ 0xFFFFFFFF; }
/* 1411 */         if (i < j)
/*      */         {
/* 1413 */           return i ^ 0xFFFFFFFF;
/*      */         }
/*      */       }
/* 1416 */       return i;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   static class TwoDigitYear
/*      */     implements DateTimePrinter, DateTimeParser
/*      */   {
/*      */     private final DateTimeFieldType iType;
/*      */     
/*      */     private final int iPivot;
/*      */     
/*      */     private final boolean iLenientParse;
/*      */     
/*      */     TwoDigitYear(DateTimeFieldType paramDateTimeFieldType, int paramInt, boolean paramBoolean)
/*      */     {
/* 1432 */       this.iType = paramDateTimeFieldType;
/* 1433 */       this.iPivot = paramInt;
/* 1434 */       this.iLenientParse = paramBoolean;
/*      */     }
/*      */     
/*      */     public int estimateParsedLength() {
/* 1438 */       return this.iLenientParse ? 4 : 2;
/*      */     }
/*      */     
/*      */     public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt) {
/* 1442 */       int i = paramString.length() - paramInt;
/*      */       int i1;
/* 1444 */       if (!this.iLenientParse) {
/* 1445 */         i = Math.min(2, i);
/* 1446 */         if (i < 2) {
/* 1447 */           return paramInt ^ 0xFFFFFFFF;
/*      */         }
/*      */       } else {
/* 1450 */         j = 0;
/* 1451 */         k = 0;
/* 1452 */         m = 0;
/* 1453 */         while (m < i) {
/* 1454 */           n = paramString.charAt(paramInt + m);
/* 1455 */           if ((m == 0) && ((n == 45) || (n == 43))) {
/* 1456 */             j = 1;
/* 1457 */             k = n == 45 ? 1 : 0;
/* 1458 */             if (k != 0) {
/* 1459 */               m++;
/*      */             }
/*      */             else {
/* 1462 */               paramInt++;
/* 1463 */               i--;
/*      */             }
/*      */           }
/*      */           else {
/* 1467 */             if ((n < 48) || (n > 57)) {
/*      */               break;
/*      */             }
/* 1470 */             m++;
/*      */           }
/*      */         }
/* 1473 */         if (m == 0) {
/* 1474 */           return paramInt ^ 0xFFFFFFFF;
/*      */         }
/*      */         
/* 1477 */         if ((j != 0) || (m != 2))
/*      */         {
/* 1479 */           if (m >= 9)
/*      */           {
/*      */ 
/* 1482 */             n = Integer.parseInt(paramString.substring(paramInt, paramInt += m));
/*      */           } else {
/* 1484 */             i1 = paramInt;
/* 1485 */             if (k != 0) {
/* 1486 */               i1++;
/*      */             }
/*      */             try {
/* 1489 */               n = paramString.charAt(i1++) - '0';
/*      */             } catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
/* 1491 */               return paramInt ^ 0xFFFFFFFF;
/*      */             }
/* 1493 */             paramInt += m;
/* 1494 */             while (i1 < paramInt) {
/* 1495 */               n = (n << 3) + (n << 1) + paramString.charAt(i1++) - 48;
/*      */             }
/* 1497 */             if (k != 0) {
/* 1498 */               n = -n;
/*      */             }
/*      */           }
/*      */           
/* 1502 */           paramDateTimeParserBucket.saveField(this.iType, n);
/* 1503 */           return paramInt;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1508 */       int k = paramString.charAt(paramInt);
/* 1509 */       if ((k < 48) || (k > 57)) {
/* 1510 */         return paramInt ^ 0xFFFFFFFF;
/*      */       }
/* 1512 */       int j = k - 48;
/* 1513 */       k = paramString.charAt(paramInt + 1);
/* 1514 */       if ((k < 48) || (k > 57)) {
/* 1515 */         return paramInt ^ 0xFFFFFFFF;
/*      */       }
/* 1517 */       j = (j << 3) + (j << 1) + k - 48;
/*      */       
/* 1519 */       int m = this.iPivot;
/*      */       
/* 1521 */       if (paramDateTimeParserBucket.getPivotYear() != null) {
/* 1522 */         m = paramDateTimeParserBucket.getPivotYear().intValue();
/*      */       }
/*      */       
/* 1525 */       int n = m - 50;
/*      */       
/*      */ 
/* 1528 */       if (n >= 0) {
/* 1529 */         i1 = n % 100;
/*      */       } else {
/* 1531 */         i1 = 99 + (n + 1) % 100;
/*      */       }
/*      */       
/* 1534 */       j += n + (j < i1 ? 100 : 0) - i1;
/*      */       
/* 1536 */       paramDateTimeParserBucket.saveField(this.iType, j);
/* 1537 */       return paramInt + 2;
/*      */     }
/*      */     
/*      */     public int estimatePrintedLength() {
/* 1541 */       return 2;
/*      */     }
/*      */     
/*      */ 
/*      */     public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
/*      */     {
/* 1547 */       int i = getTwoDigitYear(paramLong, paramChronology);
/* 1548 */       if (i < 0) {
/* 1549 */         paramStringBuffer.append(65533);
/* 1550 */         paramStringBuffer.append(65533);
/*      */       } else {
/* 1552 */         FormatUtils.appendPaddedInteger(paramStringBuffer, i, 2);
/*      */       }
/*      */     }
/*      */     
/*      */     public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
/*      */       throws IOException
/*      */     {
/* 1559 */       int i = getTwoDigitYear(paramLong, paramChronology);
/* 1560 */       if (i < 0) {
/* 1561 */         paramWriter.write(65533);
/* 1562 */         paramWriter.write(65533);
/*      */       } else {
/* 1564 */         FormatUtils.writePaddedInteger(paramWriter, i, 2);
/*      */       }
/*      */     }
/*      */     
/*      */     private int getTwoDigitYear(long paramLong, Chronology paramChronology) {
/*      */       try {
/* 1570 */         int i = this.iType.getField(paramChronology).get(paramLong);
/* 1571 */         if (i < 0) {
/* 1572 */           i = -i;
/*      */         }
/* 1574 */         return i % 100;
/*      */       } catch (RuntimeException localRuntimeException) {}
/* 1576 */       return -1;
/*      */     }
/*      */     
/*      */     public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale)
/*      */     {
/* 1581 */       int i = getTwoDigitYear(paramReadablePartial);
/* 1582 */       if (i < 0) {
/* 1583 */         paramStringBuffer.append(65533);
/* 1584 */         paramStringBuffer.append(65533);
/*      */       } else {
/* 1586 */         FormatUtils.appendPaddedInteger(paramStringBuffer, i, 2);
/*      */       }
/*      */     }
/*      */     
/*      */     public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale) throws IOException {
/* 1591 */       int i = getTwoDigitYear(paramReadablePartial);
/* 1592 */       if (i < 0) {
/* 1593 */         paramWriter.write(65533);
/* 1594 */         paramWriter.write(65533);
/*      */       } else {
/* 1596 */         FormatUtils.writePaddedInteger(paramWriter, i, 2);
/*      */       }
/*      */     }
/*      */     
/*      */     private int getTwoDigitYear(ReadablePartial paramReadablePartial) {
/* 1601 */       if (paramReadablePartial.isSupported(this.iType)) {
/*      */         try {
/* 1603 */           int i = paramReadablePartial.get(this.iType);
/* 1604 */           if (i < 0) {
/* 1605 */             i = -i;
/*      */           }
/* 1607 */           return i % 100;
/*      */         } catch (RuntimeException localRuntimeException) {}
/*      */       }
/* 1610 */       return -1;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   static class TextField
/*      */     implements DateTimePrinter, DateTimeParser
/*      */   {
/* 1618 */     private static Map cParseCache = new HashMap();
/*      */     private final DateTimeFieldType iFieldType;
/*      */     private final boolean iShort;
/*      */     
/*      */     TextField(DateTimeFieldType paramDateTimeFieldType, boolean paramBoolean)
/*      */     {
/* 1624 */       this.iFieldType = paramDateTimeFieldType;
/* 1625 */       this.iShort = paramBoolean;
/*      */     }
/*      */     
/*      */     public int estimatePrintedLength() {
/* 1629 */       return this.iShort ? 6 : 20;
/*      */     }
/*      */     
/*      */     public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
/*      */     {
/*      */       try
/*      */       {
/* 1636 */         paramStringBuffer.append(print(paramLong, paramChronology, paramLocale));
/*      */       } catch (RuntimeException localRuntimeException) {
/* 1638 */         paramStringBuffer.append(65533);
/*      */       }
/*      */     }
/*      */     
/*      */     public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale) throws IOException
/*      */     {
/*      */       try
/*      */       {
/* 1646 */         paramWriter.write(print(paramLong, paramChronology, paramLocale));
/*      */       } catch (RuntimeException localRuntimeException) {
/* 1648 */         paramWriter.write(65533);
/*      */       }
/*      */     }
/*      */     
/*      */     public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale) {
/*      */       try {
/* 1654 */         paramStringBuffer.append(print(paramReadablePartial, paramLocale));
/*      */       } catch (RuntimeException localRuntimeException) {
/* 1656 */         paramStringBuffer.append(65533);
/*      */       }
/*      */     }
/*      */     
/*      */     public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale) throws IOException {
/*      */       try {
/* 1662 */         paramWriter.write(print(paramReadablePartial, paramLocale));
/*      */       } catch (RuntimeException localRuntimeException) {
/* 1664 */         paramWriter.write(65533);
/*      */       }
/*      */     }
/*      */     
/*      */     private String print(long paramLong, Chronology paramChronology, Locale paramLocale) {
/* 1669 */       DateTimeField localDateTimeField = this.iFieldType.getField(paramChronology);
/* 1670 */       if (this.iShort) {
/* 1671 */         return localDateTimeField.getAsShortText(paramLong, paramLocale);
/*      */       }
/* 1673 */       return localDateTimeField.getAsText(paramLong, paramLocale);
/*      */     }
/*      */     
/*      */     private String print(ReadablePartial paramReadablePartial, Locale paramLocale)
/*      */     {
/* 1678 */       if (paramReadablePartial.isSupported(this.iFieldType)) {
/* 1679 */         DateTimeField localDateTimeField = this.iFieldType.getField(paramReadablePartial.getChronology());
/* 1680 */         if (this.iShort) {
/* 1681 */           return localDateTimeField.getAsShortText(paramReadablePartial, paramLocale);
/*      */         }
/* 1683 */         return localDateTimeField.getAsText(paramReadablePartial, paramLocale);
/*      */       }
/*      */       
/* 1686 */       return "�";
/*      */     }
/*      */     
/*      */     public int estimateParsedLength()
/*      */     {
/* 1691 */       return estimatePrintedLength();
/*      */     }
/*      */     
/*      */     public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt) {
/* 1695 */       Locale localLocale = paramDateTimeParserBucket.getLocale();
/*      */       
/*      */ 
/* 1698 */       Object localObject1 = null;
/* 1699 */       int i = 0;
/* 1700 */       Object localObject3; synchronized (cParseCache) {
/* 1701 */         localObject2 = (Map)cParseCache.get(localLocale);
/* 1702 */         if (localObject2 == null) {
/* 1703 */           localObject2 = new HashMap();
/* 1704 */           cParseCache.put(localLocale, localObject2);
/*      */         }
/* 1706 */         localObject3 = (Object[])((Map)localObject2).get(this.iFieldType);
/* 1707 */         if (localObject3 == null) {
/* 1708 */           localObject1 = new HashSet(32);
/* 1709 */           MutableDateTime localMutableDateTime = new MutableDateTime(0L, DateTimeZone.UTC);
/* 1710 */           MutableDateTime.Property localProperty = localMutableDateTime.property(this.iFieldType);
/* 1711 */           int k = localProperty.getMinimumValueOverall();
/* 1712 */           int m = localProperty.getMaximumValueOverall();
/* 1713 */           if (m - k > 32) {
/* 1714 */             return paramInt ^ 0xFFFFFFFF;
/*      */           }
/* 1716 */           i = localProperty.getMaximumTextLength(localLocale);
/* 1717 */           for (int n = k; n <= m; n++) {
/* 1718 */             localProperty.set(n);
/* 1719 */             ((Set)localObject1).add(localProperty.getAsShortText(localLocale));
/* 1720 */             ((Set)localObject1).add(localProperty.getAsShortText(localLocale).toLowerCase(localLocale));
/* 1721 */             ((Set)localObject1).add(localProperty.getAsShortText(localLocale).toUpperCase(localLocale));
/* 1722 */             ((Set)localObject1).add(localProperty.getAsText(localLocale));
/* 1723 */             ((Set)localObject1).add(localProperty.getAsText(localLocale).toLowerCase(localLocale));
/* 1724 */             ((Set)localObject1).add(localProperty.getAsText(localLocale).toUpperCase(localLocale));
/*      */           }
/* 1726 */           if (("en".equals(localLocale.getLanguage())) && (this.iFieldType == DateTimeFieldType.era()))
/*      */           {
/* 1728 */             ((Set)localObject1).add("BCE");
/* 1729 */             ((Set)localObject1).add("bce");
/* 1730 */             ((Set)localObject1).add("CE");
/* 1731 */             ((Set)localObject1).add("ce");
/* 1732 */             i = 3;
/*      */           }
/* 1734 */           localObject3 = new Object[] { localObject1, new Integer(i) };
/* 1735 */           ((Map)localObject2).put(this.iFieldType, localObject3);
/*      */         } else {
/* 1737 */           localObject1 = (Set)localObject3[0];
/* 1738 */           i = ((Integer)localObject3[1]).intValue();
/*      */         }
/*      */       }
/*      */       
/* 1742 */       int j = Math.min(paramString.length(), paramInt + i);
/* 1743 */       for (Object localObject2 = j; localObject2 > paramInt; localObject2--) {
/* 1744 */         localObject3 = paramString.substring(paramInt, localObject2);
/* 1745 */         if (((Set)localObject1).contains(localObject3)) {
/* 1746 */           paramDateTimeParserBucket.saveField(this.iFieldType, (String)localObject3, localLocale);
/* 1747 */           return (int)localObject2;
/*      */         }
/*      */       }
/* 1750 */       return paramInt ^ 0xFFFFFFFF;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   static class Fraction
/*      */     implements DateTimePrinter, DateTimeParser
/*      */   {
/*      */     private final DateTimeFieldType iFieldType;
/*      */     protected int iMinDigits;
/*      */     protected int iMaxDigits;
/*      */     
/*      */     protected Fraction(DateTimeFieldType paramDateTimeFieldType, int paramInt1, int paramInt2)
/*      */     {
/* 1764 */       this.iFieldType = paramDateTimeFieldType;
/*      */       
/* 1766 */       if (paramInt2 > 18) {
/* 1767 */         paramInt2 = 18;
/*      */       }
/* 1769 */       this.iMinDigits = paramInt1;
/* 1770 */       this.iMaxDigits = paramInt2;
/*      */     }
/*      */     
/*      */     public int estimatePrintedLength() {
/* 1774 */       return this.iMaxDigits;
/*      */     }
/*      */     
/*      */     public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
/*      */     {
/*      */       try
/*      */       {
/* 1781 */         printTo(paramStringBuffer, null, paramLong, paramChronology);
/*      */       }
/*      */       catch (IOException localIOException) {}
/*      */     }
/*      */     
/*      */ 
/*      */     public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
/*      */       throws IOException
/*      */     {
/* 1790 */       printTo(null, paramWriter, paramLong, paramChronology);
/*      */     }
/*      */     
/*      */ 
/*      */     public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale)
/*      */     {
/* 1796 */       long l = paramReadablePartial.getChronology().set(paramReadablePartial, 0L);
/*      */       try {
/* 1798 */         printTo(paramStringBuffer, null, l, paramReadablePartial.getChronology());
/*      */       }
/*      */       catch (IOException localIOException) {}
/*      */     }
/*      */     
/*      */ 
/*      */     public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
/*      */       throws IOException
/*      */     {
/* 1807 */       long l = paramReadablePartial.getChronology().set(paramReadablePartial, 0L);
/* 1808 */       printTo(null, paramWriter, l, paramReadablePartial.getChronology());
/*      */     }
/*      */     
/*      */     protected void printTo(StringBuffer paramStringBuffer, Writer paramWriter, long paramLong, Chronology paramChronology)
/*      */       throws IOException
/*      */     {
/* 1814 */       DateTimeField localDateTimeField = this.iFieldType.getField(paramChronology);
/* 1815 */       int i = this.iMinDigits;
/*      */       long l1;
/*      */       try
/*      */       {
/* 1819 */         l1 = localDateTimeField.remainder(paramLong);
/*      */       } catch (RuntimeException localRuntimeException) {
/* 1821 */         if (paramStringBuffer != null) {
/* 1822 */           DateTimeFormatterBuilder.appendUnknownString(paramStringBuffer, i);
/*      */         } else {
/* 1824 */           DateTimeFormatterBuilder.printUnknownString(paramWriter, i);
/*      */         }
/* 1826 */         return;
/*      */       }
/*      */       
/* 1829 */       if (l1 == 0L) {
/* 1830 */         if (paramStringBuffer != null)
/* 1831 */           for (;;) { i--; if (i < 0) break;
/* 1832 */             paramStringBuffer.append('0');
/*      */           }
/*      */         for (;;) {
/* 1835 */           i--; if (i < 0) break;
/* 1836 */           paramWriter.write(48);
/*      */         }
/*      */         
/* 1839 */         return;
/*      */       }
/*      */       
/*      */ 
/* 1843 */       long[] arrayOfLong = getFractionData(l1, localDateTimeField);
/* 1844 */       long l2 = arrayOfLong[0];
/* 1845 */       int j = (int)arrayOfLong[1];
/*      */       String str;
/* 1847 */       if ((l2 & 0x7FFFFFFF) == l2) {
/* 1848 */         str = Integer.toString((int)l2);
/*      */       } else {
/* 1850 */         str = Long.toString(l2);
/*      */       }
/*      */       
/* 1853 */       int k = str.length();
/* 1854 */       int m = j;
/* 1855 */       while (k < m) {
/* 1856 */         if (paramStringBuffer != null) {
/* 1857 */           paramStringBuffer.append('0');
/*      */         } else {
/* 1859 */           paramWriter.write(48);
/*      */         }
/* 1861 */         i--;
/* 1862 */         m--;
/*      */       }
/*      */       
/* 1865 */       if (i < m)
/*      */       {
/* 1867 */         while ((i < m) && 
/* 1868 */           (k > 1) && (str.charAt(k - 1) == '0'))
/*      */         {
/*      */ 
/* 1871 */           m--;
/* 1872 */           k--;
/*      */         }
/* 1874 */         if (k < str.length()) {
/* 1875 */           if (paramStringBuffer != null) {
/* 1876 */             for (n = 0; n < k; n++) {
/* 1877 */               paramStringBuffer.append(str.charAt(n));
/*      */             }
/*      */           }
/* 1880 */           for (int n = 0; n < k; n++) {
/* 1881 */             paramWriter.write(str.charAt(n));
/*      */           }
/*      */           
/* 1884 */           return;
/*      */         }
/*      */       }
/*      */       
/* 1888 */       if (paramStringBuffer != null) {
/* 1889 */         paramStringBuffer.append(str);
/*      */       } else {
/* 1891 */         paramWriter.write(str);
/*      */       }
/*      */     }
/*      */     
/*      */     private long[] getFractionData(long paramLong, DateTimeField paramDateTimeField) {
/* 1896 */       long l1 = paramDateTimeField.getDurationField().getUnitMillis();
/*      */       
/* 1898 */       int i = this.iMaxDigits;
/*      */       long l2;
/* 1900 */       for (;;) { switch (i) {
/* 1901 */         default:  l2 = 1L; break;
/* 1902 */         case 1:  l2 = 10L; break;
/* 1903 */         case 2:  l2 = 100L; break;
/* 1904 */         case 3:  l2 = 1000L; break;
/* 1905 */         case 4:  l2 = 10000L; break;
/* 1906 */         case 5:  l2 = 100000L; break;
/* 1907 */         case 6:  l2 = 1000000L; break;
/* 1908 */         case 7:  l2 = 10000000L; break;
/* 1909 */         case 8:  l2 = 100000000L; break;
/* 1910 */         case 9:  l2 = 1000000000L; break;
/* 1911 */         case 10:  l2 = 10000000000L; break;
/* 1912 */         case 11:  l2 = 100000000000L; break;
/* 1913 */         case 12:  l2 = 1000000000000L; break;
/* 1914 */         case 13:  l2 = 10000000000000L; break;
/* 1915 */         case 14:  l2 = 100000000000000L; break;
/* 1916 */         case 15:  l2 = 1000000000000000L; break;
/* 1917 */         case 16:  l2 = 10000000000000000L; break;
/* 1918 */         case 17:  l2 = 100000000000000000L; break;
/* 1919 */         case 18:  l2 = 1000000000000000000L;
/*      */         }
/* 1921 */         if (l1 * l2 / l2 == l1) {
/*      */           break;
/*      */         }
/*      */         
/* 1925 */         i--;
/*      */       }
/*      */       
/* 1928 */       return new long[] { paramLong * l2 / l1, i };
/*      */     }
/*      */     
/*      */     public int estimateParsedLength() {
/* 1932 */       return this.iMaxDigits;
/*      */     }
/*      */     
/*      */     public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt) {
/* 1936 */       DateTimeField localDateTimeField = this.iFieldType.getField(paramDateTimeParserBucket.getChronology());
/*      */       
/* 1938 */       int i = Math.min(this.iMaxDigits, paramString.length() - paramInt);
/*      */       
/* 1940 */       long l1 = 0L;
/* 1941 */       long l2 = localDateTimeField.getDurationField().getUnitMillis() * 10L;
/* 1942 */       int j = 0;
/* 1943 */       while (j < i) {
/* 1944 */         int k = paramString.charAt(paramInt + j);
/* 1945 */         if ((k < 48) || (k > 57)) {
/*      */           break;
/*      */         }
/* 1948 */         j++;
/* 1949 */         long l3 = l2 / 10L;
/* 1950 */         l1 += (k - 48) * l3;
/* 1951 */         l2 = l3;
/*      */       }
/*      */       
/* 1954 */       l1 /= 10L;
/*      */       
/* 1956 */       if (j == 0) {
/* 1957 */         return paramInt ^ 0xFFFFFFFF;
/*      */       }
/*      */       
/* 1960 */       if (l1 > 2147483647L) {
/* 1961 */         return paramInt ^ 0xFFFFFFFF;
/*      */       }
/*      */       
/* 1964 */       PreciseDateTimeField localPreciseDateTimeField = new PreciseDateTimeField(DateTimeFieldType.millisOfSecond(), MillisDurationField.INSTANCE, localDateTimeField.getDurationField());
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1969 */       paramDateTimeParserBucket.saveField(localPreciseDateTimeField, (int)l1);
/*      */       
/* 1971 */       return paramInt + j;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   static class TimeZoneOffset
/*      */     implements DateTimePrinter, DateTimeParser
/*      */   {
/*      */     private final String iZeroOffsetText;
/*      */     
/*      */     private final boolean iShowSeparators;
/*      */     
/*      */     private final int iMinFields;
/*      */     
/*      */     private final int iMaxFields;
/*      */     
/*      */     TimeZoneOffset(String paramString, boolean paramBoolean, int paramInt1, int paramInt2)
/*      */     {
/* 1989 */       this.iZeroOffsetText = paramString;
/* 1990 */       this.iShowSeparators = paramBoolean;
/* 1991 */       if ((paramInt1 <= 0) || (paramInt2 < paramInt1)) {
/* 1992 */         throw new IllegalArgumentException();
/*      */       }
/* 1994 */       if (paramInt1 > 4) {
/* 1995 */         paramInt1 = 4;
/* 1996 */         paramInt2 = 4;
/*      */       }
/* 1998 */       this.iMinFields = paramInt1;
/* 1999 */       this.iMaxFields = paramInt2;
/*      */     }
/*      */     
/*      */     public int estimatePrintedLength() {
/* 2003 */       int i = 1 + this.iMinFields << 1;
/* 2004 */       if (this.iShowSeparators) {
/* 2005 */         i += this.iMinFields - 1;
/*      */       }
/* 2007 */       if ((this.iZeroOffsetText != null) && (this.iZeroOffsetText.length() > i)) {
/* 2008 */         i = this.iZeroOffsetText.length();
/*      */       }
/* 2010 */       return i;
/*      */     }
/*      */     
/*      */ 
/*      */     public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
/*      */     {
/* 2016 */       if (paramDateTimeZone == null) {
/* 2017 */         return;
/*      */       }
/* 2019 */       if ((paramInt == 0) && (this.iZeroOffsetText != null)) {
/* 2020 */         paramStringBuffer.append(this.iZeroOffsetText);
/* 2021 */         return;
/*      */       }
/* 2023 */       if (paramInt >= 0) {
/* 2024 */         paramStringBuffer.append('+');
/*      */       } else {
/* 2026 */         paramStringBuffer.append('-');
/* 2027 */         paramInt = -paramInt;
/*      */       }
/*      */       
/* 2030 */       int i = paramInt / 3600000;
/* 2031 */       FormatUtils.appendPaddedInteger(paramStringBuffer, i, 2);
/* 2032 */       if (this.iMaxFields == 1) {
/* 2033 */         return;
/*      */       }
/* 2035 */       paramInt -= i * 3600000;
/* 2036 */       if ((paramInt == 0) && (this.iMinFields <= 1)) {
/* 2037 */         return;
/*      */       }
/*      */       
/* 2040 */       int j = paramInt / 60000;
/* 2041 */       if (this.iShowSeparators) {
/* 2042 */         paramStringBuffer.append(':');
/*      */       }
/* 2044 */       FormatUtils.appendPaddedInteger(paramStringBuffer, j, 2);
/* 2045 */       if (this.iMaxFields == 2) {
/* 2046 */         return;
/*      */       }
/* 2048 */       paramInt -= j * 60000;
/* 2049 */       if ((paramInt == 0) && (this.iMinFields <= 2)) {
/* 2050 */         return;
/*      */       }
/*      */       
/* 2053 */       int k = paramInt / 1000;
/* 2054 */       if (this.iShowSeparators) {
/* 2055 */         paramStringBuffer.append(':');
/*      */       }
/* 2057 */       FormatUtils.appendPaddedInteger(paramStringBuffer, k, 2);
/* 2058 */       if (this.iMaxFields == 3) {
/* 2059 */         return;
/*      */       }
/* 2061 */       paramInt -= k * 1000;
/* 2062 */       if ((paramInt == 0) && (this.iMinFields <= 3)) {
/* 2063 */         return;
/*      */       }
/*      */       
/* 2066 */       if (this.iShowSeparators) {
/* 2067 */         paramStringBuffer.append('.');
/*      */       }
/* 2069 */       FormatUtils.appendPaddedInteger(paramStringBuffer, paramInt, 3);
/*      */     }
/*      */     
/*      */     public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
/*      */       throws IOException
/*      */     {
/* 2075 */       if (paramDateTimeZone == null) {
/* 2076 */         return;
/*      */       }
/* 2078 */       if ((paramInt == 0) && (this.iZeroOffsetText != null)) {
/* 2079 */         paramWriter.write(this.iZeroOffsetText);
/* 2080 */         return;
/*      */       }
/* 2082 */       if (paramInt >= 0) {
/* 2083 */         paramWriter.write(43);
/*      */       } else {
/* 2085 */         paramWriter.write(45);
/* 2086 */         paramInt = -paramInt;
/*      */       }
/*      */       
/* 2089 */       int i = paramInt / 3600000;
/* 2090 */       FormatUtils.writePaddedInteger(paramWriter, i, 2);
/* 2091 */       if (this.iMaxFields == 1) {
/* 2092 */         return;
/*      */       }
/* 2094 */       paramInt -= i * 3600000;
/* 2095 */       if ((paramInt == 0) && (this.iMinFields == 1)) {
/* 2096 */         return;
/*      */       }
/*      */       
/* 2099 */       int j = paramInt / 60000;
/* 2100 */       if (this.iShowSeparators) {
/* 2101 */         paramWriter.write(58);
/*      */       }
/* 2103 */       FormatUtils.writePaddedInteger(paramWriter, j, 2);
/* 2104 */       if (this.iMaxFields == 2) {
/* 2105 */         return;
/*      */       }
/* 2107 */       paramInt -= j * 60000;
/* 2108 */       if ((paramInt == 0) && (this.iMinFields == 2)) {
/* 2109 */         return;
/*      */       }
/*      */       
/* 2112 */       int k = paramInt / 1000;
/* 2113 */       if (this.iShowSeparators) {
/* 2114 */         paramWriter.write(58);
/*      */       }
/* 2116 */       FormatUtils.writePaddedInteger(paramWriter, k, 2);
/* 2117 */       if (this.iMaxFields == 3) {
/* 2118 */         return;
/*      */       }
/* 2120 */       paramInt -= k * 1000;
/* 2121 */       if ((paramInt == 0) && (this.iMinFields == 3)) {
/* 2122 */         return;
/*      */       }
/*      */       
/* 2125 */       if (this.iShowSeparators) {
/* 2126 */         paramWriter.write(46);
/*      */       }
/* 2128 */       FormatUtils.writePaddedInteger(paramWriter, paramInt, 3);
/*      */     }
/*      */     
/*      */ 
/*      */     public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale) {}
/*      */     
/*      */     public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
/*      */       throws IOException
/*      */     {}
/*      */     
/*      */     public int estimateParsedLength()
/*      */     {
/* 2140 */       return estimatePrintedLength();
/*      */     }
/*      */     
/*      */     public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt) {
/* 2144 */       int i = paramString.length() - paramInt;
/*      */       
/*      */       int j;
/* 2147 */       if (this.iZeroOffsetText != null) {
/* 2148 */         if (this.iZeroOffsetText.length() == 0)
/*      */         {
/* 2150 */           if (i > 0) {
/* 2151 */             j = paramString.charAt(paramInt);
/* 2152 */             if ((j == 45) || (j == 43)) {}
/*      */           }
/*      */           else
/*      */           {
/* 2156 */             paramDateTimeParserBucket.setOffset(0);
/* 2157 */             return paramInt;
/*      */           }
/* 2159 */         } else if (paramString.regionMatches(true, paramInt, this.iZeroOffsetText, 0, this.iZeroOffsetText.length()))
/*      */         {
/* 2161 */           paramDateTimeParserBucket.setOffset(0);
/* 2162 */           return paramInt + this.iZeroOffsetText.length();
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 2168 */       if (i <= 1) {
/* 2169 */         return paramInt ^ 0xFFFFFFFF;
/*      */       }
/*      */       
/*      */ 
/* 2173 */       int k = paramString.charAt(paramInt);
/* 2174 */       if (k == 45) {
/* 2175 */         j = 1;
/* 2176 */       } else if (k == 43) {
/* 2177 */         j = 0;
/*      */       } else {
/* 2179 */         return paramInt ^ 0xFFFFFFFF;
/*      */       }
/*      */       
/* 2182 */       i--;
/* 2183 */       paramInt++;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2197 */       if (digitCount(paramString, paramInt, 2) < 2)
/*      */       {
/* 2199 */         return paramInt ^ 0xFFFFFFFF;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 2204 */       int n = FormatUtils.parseTwoDigits(paramString, paramInt);
/* 2205 */       if (n > 23) {
/* 2206 */         return paramInt ^ 0xFFFFFFFF;
/*      */       }
/* 2208 */       int m = n * 3600000;
/* 2209 */       i -= 2;
/* 2210 */       paramInt += 2;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2216 */       if (i > 0)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 2221 */         k = paramString.charAt(paramInt);
/* 2222 */         int i1; if (k == 58) {
/* 2223 */           i1 = 1;
/* 2224 */           i--;
/* 2225 */           paramInt++;
/* 2226 */         } else { if ((k < 48) || (k > 57)) break label552;
/* 2227 */           i1 = 0;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2234 */         int i2 = digitCount(paramString, paramInt, 2);
/* 2235 */         if ((i2 != 0) || (i1 != 0))
/*      */         {
/* 2237 */           if (i2 < 2)
/*      */           {
/* 2239 */             return paramInt ^ 0xFFFFFFFF;
/*      */           }
/*      */           
/* 2242 */           int i3 = FormatUtils.parseTwoDigits(paramString, paramInt);
/* 2243 */           if (i3 > 59) {
/* 2244 */             return paramInt ^ 0xFFFFFFFF;
/*      */           }
/* 2246 */           m += i3 * 60000;
/* 2247 */           i -= 2;
/* 2248 */           paramInt += 2;
/*      */           
/*      */ 
/*      */ 
/* 2252 */           if (i > 0)
/*      */           {
/*      */ 
/*      */ 
/* 2256 */             if (i1 != 0) {
/* 2257 */               if (paramString.charAt(paramInt) == ':')
/*      */               {
/*      */ 
/* 2260 */                 i--;
/* 2261 */                 paramInt++;
/*      */               }
/*      */             } else {
/* 2264 */               i2 = digitCount(paramString, paramInt, 2);
/* 2265 */               if ((i2 != 0) || (i1 != 0))
/*      */               {
/* 2267 */                 if (i2 < 2)
/*      */                 {
/* 2269 */                   return paramInt ^ 0xFFFFFFFF;
/*      */                 }
/*      */                 
/* 2272 */                 int i4 = FormatUtils.parseTwoDigits(paramString, paramInt);
/* 2273 */                 if (i4 > 59) {
/* 2274 */                   return paramInt ^ 0xFFFFFFFF;
/*      */                 }
/* 2276 */                 m += i4 * 1000;
/* 2277 */                 i -= 2;
/* 2278 */                 paramInt += 2;
/*      */                 
/*      */ 
/*      */ 
/* 2282 */                 if (i > 0)
/*      */                 {
/*      */ 
/*      */ 
/* 2286 */                   if (i1 != 0) {
/* 2287 */                     if ((paramString.charAt(paramInt) == '.') || (paramString.charAt(paramInt) == ','))
/*      */                     {
/*      */ 
/* 2290 */                       i--;
/* 2291 */                       paramInt++;
/*      */                     }
/*      */                   } else {
/* 2294 */                     i2 = digitCount(paramString, paramInt, 3);
/* 2295 */                     if ((i2 != 0) || (i1 != 0))
/*      */                     {
/* 2297 */                       if (i2 < 1)
/*      */                       {
/* 2299 */                         return paramInt ^ 0xFFFFFFFF;
/*      */                       }
/*      */                       
/* 2302 */                       m += (paramString.charAt(paramInt++) - '0') * 100;
/* 2303 */                       if (i2 > 1) {
/* 2304 */                         m += (paramString.charAt(paramInt++) - '0') * 10;
/* 2305 */                         if (i2 > 2)
/* 2306 */                           m += paramString.charAt(paramInt++) - '0';
/*      */                       }
/*      */                     }
/*      */                   } } } } } } }
/*      */       label552:
/* 2311 */       paramDateTimeParserBucket.setOffset(j != 0 ? -m : m);
/* 2312 */       return paramInt;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     private int digitCount(String paramString, int paramInt1, int paramInt2)
/*      */     {
/* 2320 */       int i = Math.min(paramString.length() - paramInt1, paramInt2);
/* 2321 */       paramInt2 = 0;
/* 2322 */       for (; i > 0; i--) {
/* 2323 */         int j = paramString.charAt(paramInt1 + paramInt2);
/* 2324 */         if ((j < 48) || (j > 57)) {
/*      */           break;
/*      */         }
/* 2327 */         paramInt2++;
/*      */       }
/* 2329 */       return paramInt2;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   static class TimeZoneName
/*      */     implements DateTimePrinter
/*      */   {
/*      */     static final int LONG_NAME = 0;
/*      */     
/*      */     static final int SHORT_NAME = 1;
/*      */     static final int ID = 2;
/*      */     private final int iType;
/*      */     
/*      */     TimeZoneName(int paramInt)
/*      */     {
/* 2345 */       this.iType = paramInt;
/*      */     }
/*      */     
/*      */     public int estimatePrintedLength() {
/* 2349 */       return this.iType == 1 ? 4 : 20;
/*      */     }
/*      */     
/*      */ 
/*      */     public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
/*      */     {
/* 2355 */       paramStringBuffer.append(print(paramLong - paramInt, paramDateTimeZone, paramLocale));
/*      */     }
/*      */     
/*      */     public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
/*      */       throws IOException
/*      */     {
/* 2361 */       paramWriter.write(print(paramLong - paramInt, paramDateTimeZone, paramLocale));
/*      */     }
/*      */     
/*      */     private String print(long paramLong, DateTimeZone paramDateTimeZone, Locale paramLocale) {
/* 2365 */       if (paramDateTimeZone == null) {
/* 2366 */         return "";
/*      */       }
/* 2368 */       switch (this.iType) {
/*      */       case 0: 
/* 2370 */         return paramDateTimeZone.getName(paramLong, paramLocale);
/*      */       case 1: 
/* 2372 */         return paramDateTimeZone.getShortName(paramLong, paramLocale);
/*      */       case 2: 
/* 2374 */         return paramDateTimeZone.getID();
/*      */       }
/* 2376 */       return "";
/*      */     }
/*      */     
/*      */ 
/*      */     public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale) {}
/*      */     
/*      */ 
/*      */     public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
/*      */       throws IOException
/*      */     {}
/*      */   }
/*      */   
/*      */ 
/*      */   static class Composite
/*      */     implements DateTimePrinter, DateTimeParser
/*      */   {
/*      */     private final DateTimePrinter[] iPrinters;
/*      */     
/*      */     private final DateTimeParser[] iParsers;
/*      */     
/*      */     private final int iPrintedLengthEstimate;
/*      */     private final int iParsedLengthEstimate;
/*      */     
/*      */     Composite(List paramList)
/*      */     {
/* 2401 */       ArrayList localArrayList1 = new ArrayList();
/* 2402 */       ArrayList localArrayList2 = new ArrayList();
/*      */       
/* 2404 */       decompose(paramList, localArrayList1, localArrayList2);
/*      */       int i;
/* 2406 */       int j; int k; Object localObject; if (localArrayList1.size() <= 0) {
/* 2407 */         this.iPrinters = null;
/* 2408 */         this.iPrintedLengthEstimate = 0;
/*      */       } else {
/* 2410 */         i = localArrayList1.size();
/* 2411 */         this.iPrinters = new DateTimePrinter[i];
/* 2412 */         j = 0;
/* 2413 */         for (k = 0; k < i; k++) {
/* 2414 */           localObject = (DateTimePrinter)localArrayList1.get(k);
/* 2415 */           j += ((DateTimePrinter)localObject).estimatePrintedLength();
/* 2416 */           this.iPrinters[k] = localObject;
/*      */         }
/* 2418 */         this.iPrintedLengthEstimate = j;
/*      */       }
/*      */       
/* 2421 */       if (localArrayList2.size() <= 0) {
/* 2422 */         this.iParsers = null;
/* 2423 */         this.iParsedLengthEstimate = 0;
/*      */       } else {
/* 2425 */         i = localArrayList2.size();
/* 2426 */         this.iParsers = new DateTimeParser[i];
/* 2427 */         j = 0;
/* 2428 */         for (k = 0; k < i; k++) {
/* 2429 */           localObject = (DateTimeParser)localArrayList2.get(k);
/* 2430 */           j += ((DateTimeParser)localObject).estimateParsedLength();
/* 2431 */           this.iParsers[k] = localObject;
/*      */         }
/* 2433 */         this.iParsedLengthEstimate = j;
/*      */       }
/*      */     }
/*      */     
/*      */     public int estimatePrintedLength() {
/* 2438 */       return this.iPrintedLengthEstimate;
/*      */     }
/*      */     
/*      */ 
/*      */     public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
/*      */     {
/* 2444 */       DateTimePrinter[] arrayOfDateTimePrinter = this.iPrinters;
/* 2445 */       if (arrayOfDateTimePrinter == null) {
/* 2446 */         throw new UnsupportedOperationException();
/*      */       }
/*      */       
/* 2449 */       if (paramLocale == null)
/*      */       {
/* 2451 */         paramLocale = Locale.getDefault();
/*      */       }
/*      */       
/* 2454 */       int i = arrayOfDateTimePrinter.length;
/* 2455 */       for (int j = 0; j < i; j++) {
/* 2456 */         arrayOfDateTimePrinter[j].printTo(paramStringBuffer, paramLong, paramChronology, paramInt, paramDateTimeZone, paramLocale);
/*      */       }
/*      */     }
/*      */     
/*      */     public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
/*      */       throws IOException
/*      */     {
/* 2463 */       DateTimePrinter[] arrayOfDateTimePrinter = this.iPrinters;
/* 2464 */       if (arrayOfDateTimePrinter == null) {
/* 2465 */         throw new UnsupportedOperationException();
/*      */       }
/*      */       
/* 2468 */       if (paramLocale == null)
/*      */       {
/* 2470 */         paramLocale = Locale.getDefault();
/*      */       }
/*      */       
/* 2473 */       int i = arrayOfDateTimePrinter.length;
/* 2474 */       for (int j = 0; j < i; j++) {
/* 2475 */         arrayOfDateTimePrinter[j].printTo(paramWriter, paramLong, paramChronology, paramInt, paramDateTimeZone, paramLocale);
/*      */       }
/*      */     }
/*      */     
/*      */     public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale) {
/* 2480 */       DateTimePrinter[] arrayOfDateTimePrinter = this.iPrinters;
/* 2481 */       if (arrayOfDateTimePrinter == null) {
/* 2482 */         throw new UnsupportedOperationException();
/*      */       }
/*      */       
/* 2485 */       if (paramLocale == null)
/*      */       {
/* 2487 */         paramLocale = Locale.getDefault();
/*      */       }
/*      */       
/* 2490 */       int i = arrayOfDateTimePrinter.length;
/* 2491 */       for (int j = 0; j < i; j++) {
/* 2492 */         arrayOfDateTimePrinter[j].printTo(paramStringBuffer, paramReadablePartial, paramLocale);
/*      */       }
/*      */     }
/*      */     
/*      */     public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale) throws IOException {
/* 2497 */       DateTimePrinter[] arrayOfDateTimePrinter = this.iPrinters;
/* 2498 */       if (arrayOfDateTimePrinter == null) {
/* 2499 */         throw new UnsupportedOperationException();
/*      */       }
/*      */       
/* 2502 */       if (paramLocale == null)
/*      */       {
/* 2504 */         paramLocale = Locale.getDefault();
/*      */       }
/*      */       
/* 2507 */       int i = arrayOfDateTimePrinter.length;
/* 2508 */       for (int j = 0; j < i; j++) {
/* 2509 */         arrayOfDateTimePrinter[j].printTo(paramWriter, paramReadablePartial, paramLocale);
/*      */       }
/*      */     }
/*      */     
/*      */     public int estimateParsedLength() {
/* 2514 */       return this.iParsedLengthEstimate;
/*      */     }
/*      */     
/*      */     public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt) {
/* 2518 */       DateTimeParser[] arrayOfDateTimeParser = this.iParsers;
/* 2519 */       if (arrayOfDateTimeParser == null) {
/* 2520 */         throw new UnsupportedOperationException();
/*      */       }
/*      */       
/* 2523 */       int i = arrayOfDateTimeParser.length;
/* 2524 */       for (int j = 0; (j < i) && (paramInt >= 0); j++) {
/* 2525 */         paramInt = arrayOfDateTimeParser[j].parseInto(paramDateTimeParserBucket, paramString, paramInt);
/*      */       }
/* 2527 */       return paramInt;
/*      */     }
/*      */     
/*      */     boolean isPrinter() {
/* 2531 */       return this.iPrinters != null;
/*      */     }
/*      */     
/*      */     boolean isParser() {
/* 2535 */       return this.iParsers != null;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     private void decompose(List paramList1, List paramList2, List paramList3)
/*      */     {
/* 2543 */       int i = paramList1.size();
/* 2544 */       for (int j = 0; j < i; j += 2) {
/* 2545 */         Object localObject = paramList1.get(j);
/* 2546 */         if ((localObject instanceof DateTimePrinter)) {
/* 2547 */           if ((localObject instanceof Composite)) {
/* 2548 */             addArrayToList(paramList2, ((Composite)localObject).iPrinters);
/*      */           } else {
/* 2550 */             paramList2.add(localObject);
/*      */           }
/*      */         }
/*      */         
/* 2554 */         localObject = paramList1.get(j + 1);
/* 2555 */         if ((localObject instanceof DateTimeParser)) {
/* 2556 */           if ((localObject instanceof Composite)) {
/* 2557 */             addArrayToList(paramList3, ((Composite)localObject).iParsers);
/*      */           } else {
/* 2559 */             paramList3.add(localObject);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     private void addArrayToList(List paramList, Object[] paramArrayOfObject) {
/* 2566 */       if (paramArrayOfObject != null) {
/* 2567 */         for (int i = 0; i < paramArrayOfObject.length; i++) {
/* 2568 */           paramList.add(paramArrayOfObject[i]);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   static class MatchingParser
/*      */     implements DateTimeParser
/*      */   {
/*      */     private final DateTimeParser[] iParsers;
/*      */     private final int iParsedLengthEstimate;
/*      */     
/*      */     MatchingParser(DateTimeParser[] paramArrayOfDateTimeParser)
/*      */     {
/* 2583 */       this.iParsers = paramArrayOfDateTimeParser;
/* 2584 */       int i = 0;
/* 2585 */       int j = paramArrayOfDateTimeParser.length; for (;;) { j--; if (j < 0) break;
/* 2586 */         DateTimeParser localDateTimeParser = paramArrayOfDateTimeParser[j];
/* 2587 */         if (localDateTimeParser != null) {
/* 2588 */           int k = localDateTimeParser.estimateParsedLength();
/* 2589 */           if (k > i) {
/* 2590 */             i = k;
/*      */           }
/*      */         }
/*      */       }
/* 2594 */       this.iParsedLengthEstimate = i;
/*      */     }
/*      */     
/*      */     public int estimateParsedLength() {
/* 2598 */       return this.iParsedLengthEstimate;
/*      */     }
/*      */     
/*      */     public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt) {
/* 2602 */       DateTimeParser[] arrayOfDateTimeParser = this.iParsers;
/* 2603 */       int i = arrayOfDateTimeParser.length;
/*      */       
/* 2605 */       Object localObject1 = paramDateTimeParserBucket.saveState();
/* 2606 */       int j = 0;
/*      */       
/* 2608 */       int k = paramInt;
/* 2609 */       Object localObject2 = null;
/*      */       
/* 2611 */       int m = paramInt;
/*      */       
/* 2613 */       for (int n = 0; n < i; n++) {
/* 2614 */         DateTimeParser localDateTimeParser = arrayOfDateTimeParser[n];
/* 2615 */         if (localDateTimeParser == null)
/*      */         {
/* 2617 */           if (k <= paramInt) {
/* 2618 */             return paramInt;
/*      */           }
/* 2620 */           j = 1;
/* 2621 */           break;
/*      */         }
/* 2623 */         int i1 = localDateTimeParser.parseInto(paramDateTimeParserBucket, paramString, paramInt);
/* 2624 */         if (i1 >= paramInt) {
/* 2625 */           if (i1 > k) {
/* 2626 */             if ((i1 >= paramString.length()) || (n + 1 >= i) || (arrayOfDateTimeParser[(n + 1)] == null))
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/* 2631 */               return i1;
/*      */             }
/* 2633 */             k = i1;
/* 2634 */             localObject2 = paramDateTimeParserBucket.saveState();
/*      */           }
/*      */         }
/* 2637 */         else if (i1 < 0) {
/* 2638 */           i1 ^= 0xFFFFFFFF;
/* 2639 */           if (i1 > m) {
/* 2640 */             m = i1;
/*      */           }
/*      */         }
/*      */         
/* 2644 */         paramDateTimeParserBucket.restoreState(localObject1);
/*      */       }
/*      */       
/* 2647 */       if ((k > paramInt) || ((k == paramInt) && (j != 0)))
/*      */       {
/* 2649 */         if (localObject2 != null) {
/* 2650 */           paramDateTimeParserBucket.restoreState(localObject2);
/*      */         }
/* 2652 */         return k;
/*      */       }
/*      */       
/* 2655 */       return m ^ 0xFFFFFFFF;
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\format\DateTimeFormatterBuilder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */