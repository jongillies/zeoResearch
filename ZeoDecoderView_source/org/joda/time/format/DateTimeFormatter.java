/*     */ package org.joda.time.format;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.Locale;
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTime;
/*     */ import org.joda.time.DateTimeUtils;
/*     */ import org.joda.time.DateTimeZone;
/*     */ import org.joda.time.MutableDateTime;
/*     */ import org.joda.time.ReadWritableInstant;
/*     */ import org.joda.time.ReadableInstant;
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
/*     */ public class DateTimeFormatter
/*     */ {
/*     */   private final DateTimePrinter iPrinter;
/*     */   private final DateTimeParser iParser;
/*     */   private final Locale iLocale;
/*     */   private final boolean iOffsetParsed;
/*     */   private final Chronology iChrono;
/*     */   private final DateTimeZone iZone;
/*     */   private final Integer iPivotYear;
/*     */   
/*     */   public DateTimeFormatter(DateTimePrinter paramDateTimePrinter, DateTimeParser paramDateTimeParser)
/*     */   {
/* 103 */     this.iPrinter = paramDateTimePrinter;
/* 104 */     this.iParser = paramDateTimeParser;
/* 105 */     this.iLocale = null;
/* 106 */     this.iOffsetParsed = false;
/* 107 */     this.iChrono = null;
/* 108 */     this.iZone = null;
/* 109 */     this.iPivotYear = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private DateTimeFormatter(DateTimePrinter paramDateTimePrinter, DateTimeParser paramDateTimeParser, Locale paramLocale, boolean paramBoolean, Chronology paramChronology, DateTimeZone paramDateTimeZone, Integer paramInteger)
/*     */   {
/* 121 */     this.iPrinter = paramDateTimePrinter;
/* 122 */     this.iParser = paramDateTimeParser;
/* 123 */     this.iLocale = paramLocale;
/* 124 */     this.iOffsetParsed = paramBoolean;
/* 125 */     this.iChrono = paramChronology;
/* 126 */     this.iZone = paramDateTimeZone;
/* 127 */     this.iPivotYear = paramInteger;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isPrinter()
/*     */   {
/* 137 */     return this.iPrinter != null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimePrinter getPrinter()
/*     */   {
/* 146 */     return this.iPrinter;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isParser()
/*     */   {
/* 155 */     return this.iParser != null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeParser getParser()
/*     */   {
/* 164 */     return this.iParser;
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
/*     */   public DateTimeFormatter withLocale(Locale paramLocale)
/*     */   {
/* 180 */     if ((paramLocale == getLocale()) || ((paramLocale != null) && (paramLocale.equals(getLocale())))) {
/* 181 */       return this;
/*     */     }
/* 183 */     return new DateTimeFormatter(this.iPrinter, this.iParser, paramLocale, this.iOffsetParsed, this.iChrono, this.iZone, this.iPivotYear);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Locale getLocale()
/*     */   {
/* 194 */     return this.iLocale;
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
/*     */   public DateTimeFormatter withOffsetParsed()
/*     */   {
/* 213 */     if (this.iOffsetParsed == true) {
/* 214 */       return this;
/*     */     }
/* 216 */     return new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, true, this.iChrono, null, this.iPivotYear);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isOffsetParsed()
/*     */   {
/* 227 */     return this.iOffsetParsed;
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
/*     */   public DateTimeFormatter withChronology(Chronology paramChronology)
/*     */   {
/* 248 */     if (this.iChrono == paramChronology) {
/* 249 */       return this;
/*     */     }
/* 251 */     return new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, this.iOffsetParsed, paramChronology, this.iZone, this.iPivotYear);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Chronology getChronology()
/*     */   {
/* 261 */     return this.iChrono;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public Chronology getChronolgy()
/*     */   {
/* 271 */     return this.iChrono;
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
/*     */   public DateTimeFormatter withZone(DateTimeZone paramDateTimeZone)
/*     */   {
/* 292 */     if (this.iZone == paramDateTimeZone) {
/* 293 */       return this;
/*     */     }
/* 295 */     return new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, false, this.iChrono, paramDateTimeZone, this.iPivotYear);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeZone getZone()
/*     */   {
/* 305 */     return this.iZone;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeFormatter withPivotYear(Integer paramInteger)
/*     */   {
/* 339 */     if ((this.iPivotYear == paramInteger) || ((this.iPivotYear != null) && (this.iPivotYear.equals(paramInteger)))) {
/* 340 */       return this;
/*     */     }
/* 342 */     return new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, this.iOffsetParsed, this.iChrono, this.iZone, paramInteger);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeFormatter withPivotYear(int paramInt)
/*     */   {
/* 376 */     return withPivotYear(new Integer(paramInt));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Integer getPivotYear()
/*     */   {
/* 386 */     return this.iPivotYear;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void printTo(StringBuffer paramStringBuffer, ReadableInstant paramReadableInstant)
/*     */   {
/* 397 */     long l = DateTimeUtils.getInstantMillis(paramReadableInstant);
/* 398 */     Chronology localChronology = DateTimeUtils.getInstantChronology(paramReadableInstant);
/* 399 */     printTo(paramStringBuffer, l, localChronology);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void printTo(Writer paramWriter, ReadableInstant paramReadableInstant)
/*     */     throws IOException
/*     */   {
/* 409 */     long l = DateTimeUtils.getInstantMillis(paramReadableInstant);
/* 410 */     Chronology localChronology = DateTimeUtils.getInstantChronology(paramReadableInstant);
/* 411 */     printTo(paramWriter, l, localChronology);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void printTo(StringBuffer paramStringBuffer, long paramLong)
/*     */   {
/* 423 */     printTo(paramStringBuffer, paramLong, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void printTo(Writer paramWriter, long paramLong)
/*     */     throws IOException
/*     */   {
/* 434 */     printTo(paramWriter, paramLong, null);
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
/*     */   public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial)
/*     */   {
/* 448 */     DateTimePrinter localDateTimePrinter = requirePrinter();
/* 449 */     if (paramReadablePartial == null) {
/* 450 */       throw new IllegalArgumentException("The partial must not be null");
/*     */     }
/* 452 */     localDateTimePrinter.printTo(paramStringBuffer, paramReadablePartial, this.iLocale);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial)
/*     */     throws IOException
/*     */   {
/* 465 */     DateTimePrinter localDateTimePrinter = requirePrinter();
/* 466 */     if (paramReadablePartial == null) {
/* 467 */       throw new IllegalArgumentException("The partial must not be null");
/*     */     }
/* 469 */     localDateTimePrinter.printTo(paramWriter, paramReadablePartial, this.iLocale);
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
/*     */   public String print(ReadableInstant paramReadableInstant)
/*     */   {
/* 483 */     StringBuffer localStringBuffer = new StringBuffer(requirePrinter().estimatePrintedLength());
/* 484 */     printTo(localStringBuffer, paramReadableInstant);
/* 485 */     return localStringBuffer.toString();
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
/*     */   public String print(long paramLong)
/*     */   {
/* 498 */     StringBuffer localStringBuffer = new StringBuffer(requirePrinter().estimatePrintedLength());
/* 499 */     printTo(localStringBuffer, paramLong);
/* 500 */     return localStringBuffer.toString();
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
/*     */   public String print(ReadablePartial paramReadablePartial)
/*     */   {
/* 513 */     StringBuffer localStringBuffer = new StringBuffer(requirePrinter().estimatePrintedLength());
/* 514 */     printTo(localStringBuffer, paramReadablePartial);
/* 515 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */   private void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology) {
/* 519 */     DateTimePrinter localDateTimePrinter = requirePrinter();
/* 520 */     paramChronology = selectChronology(paramChronology);
/*     */     
/*     */ 
/* 523 */     DateTimeZone localDateTimeZone = paramChronology.getZone();
/* 524 */     int i = localDateTimeZone.getOffset(paramLong);
/* 525 */     long l = paramLong + i;
/* 526 */     if (((paramLong ^ l) < 0L) && ((paramLong ^ i) >= 0L))
/*     */     {
/* 528 */       localDateTimeZone = DateTimeZone.UTC;
/* 529 */       i = 0;
/* 530 */       l = paramLong;
/*     */     }
/* 532 */     localDateTimePrinter.printTo(paramStringBuffer, l, paramChronology.withUTC(), i, localDateTimeZone, this.iLocale);
/*     */   }
/*     */   
/*     */   private void printTo(Writer paramWriter, long paramLong, Chronology paramChronology) throws IOException {
/* 536 */     DateTimePrinter localDateTimePrinter = requirePrinter();
/* 537 */     paramChronology = selectChronology(paramChronology);
/*     */     
/*     */ 
/* 540 */     DateTimeZone localDateTimeZone = paramChronology.getZone();
/* 541 */     int i = localDateTimeZone.getOffset(paramLong);
/* 542 */     long l = paramLong + i;
/* 543 */     if (((paramLong ^ l) < 0L) && ((paramLong ^ i) >= 0L))
/*     */     {
/* 545 */       localDateTimeZone = DateTimeZone.UTC;
/* 546 */       i = 0;
/* 547 */       l = paramLong;
/*     */     }
/* 549 */     localDateTimePrinter.printTo(paramWriter, l, paramChronology.withUTC(), i, localDateTimeZone, this.iLocale);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private DateTimePrinter requirePrinter()
/*     */   {
/* 558 */     DateTimePrinter localDateTimePrinter = this.iPrinter;
/* 559 */     if (localDateTimePrinter == null) {
/* 560 */       throw new UnsupportedOperationException("Printing not supported");
/*     */     }
/* 562 */     return localDateTimePrinter;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int parseInto(ReadWritableInstant paramReadWritableInstant, String paramString, int paramInt)
/*     */   {
/* 596 */     DateTimeParser localDateTimeParser = requireParser();
/* 597 */     if (paramReadWritableInstant == null) {
/* 598 */       throw new IllegalArgumentException("Instant must not be null");
/*     */     }
/*     */     
/* 601 */     long l1 = paramReadWritableInstant.getMillis();
/* 602 */     Chronology localChronology = paramReadWritableInstant.getChronology();
/* 603 */     long l2 = l1 + localChronology.getZone().getOffset(l1);
/* 604 */     localChronology = selectChronology(localChronology);
/*     */     
/* 606 */     DateTimeParserBucket localDateTimeParserBucket = new DateTimeParserBucket(l2, localChronology, this.iLocale, this.iPivotYear);
/*     */     
/* 608 */     int i = localDateTimeParser.parseInto(localDateTimeParserBucket, paramString, paramInt);
/* 609 */     paramReadWritableInstant.setMillis(localDateTimeParserBucket.computeMillis(false, paramString));
/* 610 */     if ((this.iOffsetParsed) && (localDateTimeParserBucket.getZone() == null)) {
/* 611 */       int j = localDateTimeParserBucket.getOffset();
/* 612 */       DateTimeZone localDateTimeZone = DateTimeZone.forOffsetMillis(j);
/* 613 */       localChronology = localChronology.withZone(localDateTimeZone);
/*     */     }
/* 615 */     paramReadWritableInstant.setChronology(localChronology);
/* 616 */     return i;
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
/*     */   public long parseMillis(String paramString)
/*     */   {
/* 632 */     DateTimeParser localDateTimeParser = requireParser();
/*     */     
/* 634 */     Chronology localChronology = selectChronology(this.iChrono);
/* 635 */     DateTimeParserBucket localDateTimeParserBucket = new DateTimeParserBucket(0L, localChronology, this.iLocale, this.iPivotYear);
/* 636 */     int i = localDateTimeParser.parseInto(localDateTimeParserBucket, paramString, 0);
/* 637 */     if (i >= 0) {
/* 638 */       if (i >= paramString.length()) {
/* 639 */         return localDateTimeParserBucket.computeMillis(true, paramString);
/*     */       }
/*     */     } else {
/* 642 */       i ^= 0xFFFFFFFF;
/*     */     }
/* 644 */     throw new IllegalArgumentException(FormatUtils.createErrorMessage(paramString, i));
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
/*     */   public DateTime parseDateTime(String paramString)
/*     */   {
/* 665 */     DateTimeParser localDateTimeParser = requireParser();
/*     */     
/* 667 */     Chronology localChronology = selectChronology(null);
/* 668 */     DateTimeParserBucket localDateTimeParserBucket = new DateTimeParserBucket(0L, localChronology, this.iLocale, this.iPivotYear);
/* 669 */     int i = localDateTimeParser.parseInto(localDateTimeParserBucket, paramString, 0);
/* 670 */     if (i >= 0) {
/* 671 */       if (i >= paramString.length()) {
/* 672 */         long l = localDateTimeParserBucket.computeMillis(true, paramString);
/* 673 */         if ((this.iOffsetParsed) && (localDateTimeParserBucket.getZone() == null)) {
/* 674 */           int j = localDateTimeParserBucket.getOffset();
/* 675 */           DateTimeZone localDateTimeZone = DateTimeZone.forOffsetMillis(j);
/* 676 */           localChronology = localChronology.withZone(localDateTimeZone);
/*     */         }
/* 678 */         return new DateTime(l, localChronology);
/*     */       }
/*     */     } else {
/* 681 */       i ^= 0xFFFFFFFF;
/*     */     }
/* 683 */     throw new IllegalArgumentException(FormatUtils.createErrorMessage(paramString, i));
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
/*     */   public MutableDateTime parseMutableDateTime(String paramString)
/*     */   {
/* 704 */     DateTimeParser localDateTimeParser = requireParser();
/*     */     
/* 706 */     Chronology localChronology = selectChronology(null);
/* 707 */     DateTimeParserBucket localDateTimeParserBucket = new DateTimeParserBucket(0L, localChronology, this.iLocale, this.iPivotYear);
/* 708 */     int i = localDateTimeParser.parseInto(localDateTimeParserBucket, paramString, 0);
/* 709 */     if (i >= 0) {
/* 710 */       if (i >= paramString.length()) {
/* 711 */         long l = localDateTimeParserBucket.computeMillis(true, paramString);
/* 712 */         if ((this.iOffsetParsed) && (localDateTimeParserBucket.getZone() == null)) {
/* 713 */           int j = localDateTimeParserBucket.getOffset();
/* 714 */           DateTimeZone localDateTimeZone = DateTimeZone.forOffsetMillis(j);
/* 715 */           localChronology = localChronology.withZone(localDateTimeZone);
/*     */         }
/* 717 */         return new MutableDateTime(l, localChronology);
/*     */       }
/*     */     } else {
/* 720 */       i ^= 0xFFFFFFFF;
/*     */     }
/* 722 */     throw new IllegalArgumentException(FormatUtils.createErrorMessage(paramString, i));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private DateTimeParser requireParser()
/*     */   {
/* 731 */     DateTimeParser localDateTimeParser = this.iParser;
/* 732 */     if (localDateTimeParser == null) {
/* 733 */       throw new UnsupportedOperationException("Parsing not supported");
/*     */     }
/* 735 */     return localDateTimeParser;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Chronology selectChronology(Chronology paramChronology)
/*     */   {
/* 746 */     paramChronology = DateTimeUtils.getChronology(paramChronology);
/* 747 */     if (this.iChrono != null) {
/* 748 */       paramChronology = this.iChrono;
/*     */     }
/* 750 */     if (this.iZone != null) {
/* 751 */       paramChronology = paramChronology.withZone(this.iZone);
/*     */     }
/* 753 */     return paramChronology;
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\format\DateTimeFormatter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */