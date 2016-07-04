/*      */ package org.joda.time.format;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.Writer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.TreeSet;
/*      */ import org.joda.time.DurationFieldType;
/*      */ import org.joda.time.PeriodType;
/*      */ import org.joda.time.ReadWritablePeriod;
/*      */ import org.joda.time.ReadablePeriod;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PeriodFormatterBuilder
/*      */ {
/*      */   private static final int PRINT_ZERO_RARELY_FIRST = 1;
/*      */   private static final int PRINT_ZERO_RARELY_LAST = 2;
/*      */   private static final int PRINT_ZERO_IF_SUPPORTED = 3;
/*      */   private static final int PRINT_ZERO_ALWAYS = 4;
/*      */   private static final int PRINT_ZERO_NEVER = 5;
/*      */   private static final int YEARS = 0;
/*      */   private static final int MONTHS = 1;
/*      */   private static final int WEEKS = 2;
/*      */   private static final int DAYS = 3;
/*      */   private static final int HOURS = 4;
/*      */   private static final int MINUTES = 5;
/*      */   private static final int SECONDS = 6;
/*      */   private static final int MILLIS = 7;
/*      */   private static final int SECONDS_MILLIS = 8;
/*      */   private static final int SECONDS_OPTIONAL_MILLIS = 9;
/*      */   private static final int MAX_FIELD = 9;
/*      */   private int iMinPrintedDigits;
/*      */   private int iPrintZeroSetting;
/*      */   private int iMaxParsedDigits;
/*      */   private boolean iRejectSignedValues;
/*      */   private PeriodFieldAffix iPrefix;
/*      */   private List iElementPairs;
/*      */   private boolean iNotPrinter;
/*      */   private boolean iNotParser;
/*      */   private FieldFormatter[] iFieldFormatters;
/*      */   
/*      */   public PeriodFormatterBuilder()
/*      */   {
/*  102 */     clear();
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
/*      */   public PeriodFormatter toFormatter()
/*      */   {
/*  123 */     PeriodFormatter localPeriodFormatter = toFormatter(this.iElementPairs, this.iNotPrinter, this.iNotParser);
/*  124 */     this.iFieldFormatters = ((FieldFormatter[])this.iFieldFormatters.clone());
/*  125 */     return localPeriodFormatter;
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
/*      */   public PeriodPrinter toPrinter()
/*      */   {
/*  141 */     if (this.iNotPrinter) {
/*  142 */       return null;
/*      */     }
/*  144 */     return toFormatter().getPrinter();
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
/*      */   public PeriodParser toParser()
/*      */   {
/*  160 */     if (this.iNotParser) {
/*  161 */       return null;
/*      */     }
/*  163 */     return toFormatter().getParser();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clear()
/*      */   {
/*  171 */     this.iMinPrintedDigits = 1;
/*  172 */     this.iPrintZeroSetting = 2;
/*  173 */     this.iMaxParsedDigits = 10;
/*  174 */     this.iRejectSignedValues = false;
/*  175 */     this.iPrefix = null;
/*  176 */     if (this.iElementPairs == null) {
/*  177 */       this.iElementPairs = new ArrayList();
/*      */     } else {
/*  179 */       this.iElementPairs.clear();
/*      */     }
/*  181 */     this.iNotPrinter = false;
/*  182 */     this.iNotParser = false;
/*  183 */     this.iFieldFormatters = new FieldFormatter[10];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PeriodFormatterBuilder append(PeriodFormatter paramPeriodFormatter)
/*      */   {
/*  192 */     if (paramPeriodFormatter == null) {
/*  193 */       throw new IllegalArgumentException("No formatter supplied");
/*      */     }
/*  195 */     clearPrefix();
/*  196 */     append0(paramPeriodFormatter.getPrinter(), paramPeriodFormatter.getParser());
/*  197 */     return this;
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
/*      */   public PeriodFormatterBuilder append(PeriodPrinter paramPeriodPrinter, PeriodParser paramPeriodParser)
/*      */   {
/*  212 */     if ((paramPeriodPrinter == null) && (paramPeriodParser == null)) {
/*  213 */       throw new IllegalArgumentException("No printer or parser supplied");
/*      */     }
/*  215 */     clearPrefix();
/*  216 */     append0(paramPeriodPrinter, paramPeriodParser);
/*  217 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PeriodFormatterBuilder appendLiteral(String paramString)
/*      */   {
/*  228 */     if (paramString == null) {
/*  229 */       throw new IllegalArgumentException("Literal must not be null");
/*      */     }
/*  231 */     clearPrefix();
/*  232 */     Literal localLiteral = new Literal(paramString);
/*  233 */     append0(localLiteral, localLiteral);
/*  234 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PeriodFormatterBuilder minimumPrintedDigits(int paramInt)
/*      */   {
/*  245 */     this.iMinPrintedDigits = paramInt;
/*  246 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PeriodFormatterBuilder maximumParsedDigits(int paramInt)
/*      */   {
/*  256 */     this.iMaxParsedDigits = paramInt;
/*  257 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PeriodFormatterBuilder rejectSignedValues(boolean paramBoolean)
/*      */   {
/*  266 */     this.iRejectSignedValues = paramBoolean;
/*  267 */     return this;
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
/*      */   public PeriodFormatterBuilder printZeroRarelyLast()
/*      */   {
/*  280 */     this.iPrintZeroSetting = 2;
/*  281 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PeriodFormatterBuilder printZeroRarelyFirst()
/*      */   {
/*  292 */     this.iPrintZeroSetting = 1;
/*  293 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PeriodFormatterBuilder printZeroIfSupported()
/*      */   {
/*  303 */     this.iPrintZeroSetting = 3;
/*  304 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PeriodFormatterBuilder printZeroAlways()
/*      */   {
/*  315 */     this.iPrintZeroSetting = 4;
/*  316 */     return this;
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
/*      */   public PeriodFormatterBuilder printZeroNever()
/*      */   {
/*  329 */     this.iPrintZeroSetting = 5;
/*  330 */     return this;
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
/*      */   public PeriodFormatterBuilder appendPrefix(String paramString)
/*      */   {
/*  343 */     if (paramString == null) {
/*  344 */       throw new IllegalArgumentException();
/*      */     }
/*  346 */     return appendPrefix(new SimpleAffix(paramString));
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
/*      */   public PeriodFormatterBuilder appendPrefix(String paramString1, String paramString2)
/*      */   {
/*  363 */     if ((paramString1 == null) || (paramString2 == null)) {
/*  364 */       throw new IllegalArgumentException();
/*      */     }
/*  366 */     return appendPrefix(new PluralAffix(paramString1, paramString2));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private PeriodFormatterBuilder appendPrefix(PeriodFieldAffix paramPeriodFieldAffix)
/*      */   {
/*  378 */     if (paramPeriodFieldAffix == null) {
/*  379 */       throw new IllegalArgumentException();
/*      */     }
/*  381 */     if (this.iPrefix != null) {
/*  382 */       paramPeriodFieldAffix = new CompositeAffix(this.iPrefix, paramPeriodFieldAffix);
/*      */     }
/*  384 */     this.iPrefix = paramPeriodFieldAffix;
/*  385 */     return this;
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
/*      */   public PeriodFormatterBuilder appendYears()
/*      */   {
/*  398 */     appendField(0);
/*  399 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PeriodFormatterBuilder appendMonths()
/*      */   {
/*  411 */     appendField(1);
/*  412 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PeriodFormatterBuilder appendWeeks()
/*      */   {
/*  424 */     appendField(2);
/*  425 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PeriodFormatterBuilder appendDays()
/*      */   {
/*  437 */     appendField(3);
/*  438 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PeriodFormatterBuilder appendHours()
/*      */   {
/*  450 */     appendField(4);
/*  451 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PeriodFormatterBuilder appendMinutes()
/*      */   {
/*  463 */     appendField(5);
/*  464 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PeriodFormatterBuilder appendSeconds()
/*      */   {
/*  476 */     appendField(6);
/*  477 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PeriodFormatterBuilder appendSecondsWithMillis()
/*      */   {
/*  488 */     appendField(8);
/*  489 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PeriodFormatterBuilder appendSecondsWithOptionalMillis()
/*      */   {
/*  500 */     appendField(9);
/*  501 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PeriodFormatterBuilder appendMillis()
/*      */   {
/*  513 */     appendField(7);
/*  514 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PeriodFormatterBuilder appendMillis3Digit()
/*      */   {
/*  525 */     appendField(7, 3);
/*  526 */     return this;
/*      */   }
/*      */   
/*      */   private void appendField(int paramInt) {
/*  530 */     appendField(paramInt, this.iMinPrintedDigits);
/*      */   }
/*      */   
/*      */   private void appendField(int paramInt1, int paramInt2) {
/*  534 */     FieldFormatter localFieldFormatter = new FieldFormatter(paramInt2, this.iPrintZeroSetting, this.iMaxParsedDigits, this.iRejectSignedValues, paramInt1, this.iFieldFormatters, this.iPrefix, null);
/*      */     
/*  536 */     append0(localFieldFormatter, localFieldFormatter);
/*  537 */     this.iFieldFormatters[paramInt1] = localFieldFormatter;
/*  538 */     this.iPrefix = null;
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
/*      */   public PeriodFormatterBuilder appendSuffix(String paramString)
/*      */   {
/*  552 */     if (paramString == null) {
/*  553 */       throw new IllegalArgumentException();
/*      */     }
/*  555 */     return appendSuffix(new SimpleAffix(paramString));
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
/*      */   public PeriodFormatterBuilder appendSuffix(String paramString1, String paramString2)
/*      */   {
/*  573 */     if ((paramString1 == null) || (paramString2 == null)) {
/*  574 */       throw new IllegalArgumentException();
/*      */     }
/*  576 */     return appendSuffix(new PluralAffix(paramString1, paramString2));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private PeriodFormatterBuilder appendSuffix(PeriodFieldAffix paramPeriodFieldAffix)
/*      */   {
/*      */     Object localObject1;
/*      */     
/*      */ 
/*      */ 
/*      */     Object localObject2;
/*      */     
/*      */ 
/*  591 */     if (this.iElementPairs.size() > 0) {
/*  592 */       localObject1 = this.iElementPairs.get(this.iElementPairs.size() - 2);
/*  593 */       localObject2 = this.iElementPairs.get(this.iElementPairs.size() - 1);
/*      */     } else {
/*  595 */       localObject1 = null;
/*  596 */       localObject2 = null;
/*      */     }
/*      */     
/*  599 */     if ((localObject1 == null) || (localObject2 == null) || (localObject1 != localObject2) || (!(localObject1 instanceof FieldFormatter)))
/*      */     {
/*      */ 
/*  602 */       throw new IllegalStateException("No field to apply suffix to");
/*      */     }
/*      */     
/*  605 */     clearPrefix();
/*  606 */     FieldFormatter localFieldFormatter = new FieldFormatter((FieldFormatter)localObject1, paramPeriodFieldAffix);
/*  607 */     this.iElementPairs.set(this.iElementPairs.size() - 2, localFieldFormatter);
/*  608 */     this.iElementPairs.set(this.iElementPairs.size() - 1, localFieldFormatter);
/*  609 */     this.iFieldFormatters[localFieldFormatter.getFieldType()] = localFieldFormatter;
/*      */     
/*  611 */     return this;
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
/*      */   public PeriodFormatterBuilder appendSeparator(String paramString)
/*      */   {
/*  632 */     return appendSeparator(paramString, paramString, null, true, true);
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
/*      */   public PeriodFormatterBuilder appendSeparatorIfFieldsAfter(String paramString)
/*      */   {
/*  652 */     return appendSeparator(paramString, paramString, null, false, true);
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
/*      */   public PeriodFormatterBuilder appendSeparatorIfFieldsBefore(String paramString)
/*      */   {
/*  672 */     return appendSeparator(paramString, paramString, null, true, false);
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
/*      */ 
/*      */ 
/*      */ 
/*      */   public PeriodFormatterBuilder appendSeparator(String paramString1, String paramString2)
/*      */   {
/*  697 */     return appendSeparator(paramString1, paramString2, null, true, true);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PeriodFormatterBuilder appendSeparator(String paramString1, String paramString2, String[] paramArrayOfString)
/*      */   {
/*  724 */     return appendSeparator(paramString1, paramString2, paramArrayOfString, true, true);
/*      */   }
/*      */   
/*      */ 
/*      */   private PeriodFormatterBuilder appendSeparator(String paramString1, String paramString2, String[] paramArrayOfString, boolean paramBoolean1, boolean paramBoolean2)
/*      */   {
/*  730 */     if ((paramString1 == null) || (paramString2 == null)) {
/*  731 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  734 */     clearPrefix();
/*      */     
/*      */ 
/*  737 */     List localList = this.iElementPairs;
/*  738 */     if (localList.size() == 0) {
/*  739 */       if ((paramBoolean2) && (!paramBoolean1)) {
/*  740 */         Separator localSeparator1 = new Separator(paramString1, paramString2, paramArrayOfString, Literal.EMPTY, Literal.EMPTY, paramBoolean1, paramBoolean2);
/*      */         
/*      */ 
/*  743 */         append0(localSeparator1, localSeparator1);
/*      */       }
/*  745 */       return this;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  750 */     Separator localSeparator2 = null;
/*  751 */     int i = localList.size(); for (;;) { i--; if (i < 0) break;
/*  752 */       if ((localList.get(i) instanceof Separator)) {
/*  753 */         localSeparator2 = (Separator)localList.get(i);
/*  754 */         localList = localList.subList(i + 1, localList.size());
/*  755 */         break;
/*      */       }
/*  757 */       i--;
/*      */     }
/*      */     
/*      */ 
/*  761 */     if ((localSeparator2 != null) && (localList.size() == 0)) {
/*  762 */       throw new IllegalStateException("Cannot have two adjacent separators");
/*      */     }
/*  764 */     Object[] arrayOfObject = createComposite(localList);
/*  765 */     localList.clear();
/*  766 */     Separator localSeparator3 = new Separator(paramString1, paramString2, paramArrayOfString, (PeriodPrinter)arrayOfObject[0], (PeriodParser)arrayOfObject[1], paramBoolean1, paramBoolean2);
/*      */     
/*      */ 
/*      */ 
/*  770 */     localList.add(localSeparator3);
/*  771 */     localList.add(localSeparator3);
/*      */     
/*      */ 
/*  774 */     return this;
/*      */   }
/*      */   
/*      */   private void clearPrefix() throws IllegalStateException
/*      */   {
/*  779 */     if (this.iPrefix != null) {
/*  780 */       throw new IllegalStateException("Prefix not followed by field");
/*      */     }
/*  782 */     this.iPrefix = null;
/*      */   }
/*      */   
/*      */   private PeriodFormatterBuilder append0(PeriodPrinter paramPeriodPrinter, PeriodParser paramPeriodParser) {
/*  786 */     this.iElementPairs.add(paramPeriodPrinter);
/*  787 */     this.iElementPairs.add(paramPeriodParser);
/*  788 */     this.iNotPrinter |= paramPeriodPrinter == null;
/*  789 */     this.iNotParser |= paramPeriodParser == null;
/*  790 */     return this;
/*      */   }
/*      */   
/*      */   private static PeriodFormatter toFormatter(List paramList, boolean paramBoolean1, boolean paramBoolean2)
/*      */   {
/*  795 */     if ((paramBoolean1) && (paramBoolean2)) {
/*  796 */       throw new IllegalStateException("Builder has created neither a printer nor a parser");
/*      */     }
/*  798 */     int i = paramList.size();
/*  799 */     if ((i >= 2) && ((paramList.get(0) instanceof Separator))) {
/*  800 */       localObject = (Separator)paramList.get(0);
/*  801 */       PeriodFormatter localPeriodFormatter = toFormatter(paramList.subList(2, i), paramBoolean1, paramBoolean2);
/*  802 */       localObject = ((Separator)localObject).finish(localPeriodFormatter.getPrinter(), localPeriodFormatter.getParser());
/*  803 */       return new PeriodFormatter((PeriodPrinter)localObject, (PeriodParser)localObject);
/*      */     }
/*  805 */     Object localObject = createComposite(paramList);
/*  806 */     if (paramBoolean1)
/*  807 */       return new PeriodFormatter(null, (PeriodParser)localObject[1]);
/*  808 */     if (paramBoolean2) {
/*  809 */       return new PeriodFormatter((PeriodPrinter)localObject[0], null);
/*      */     }
/*  811 */     return new PeriodFormatter((PeriodPrinter)localObject[0], (PeriodParser)localObject[1]);
/*      */   }
/*      */   
/*      */   private static Object[] createComposite(List paramList)
/*      */   {
/*  816 */     switch (paramList.size()) {
/*      */     case 0: 
/*  818 */       return new Object[] { Literal.EMPTY, Literal.EMPTY };
/*      */     case 1: 
/*  820 */       return new Object[] { paramList.get(0), paramList.get(1) };
/*      */     }
/*  822 */     Composite localComposite = new Composite(paramList);
/*  823 */     return new Object[] { localComposite, localComposite };
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   static abstract interface PeriodFieldAffix
/*      */   {
/*      */     public abstract int calculatePrintedLength(int paramInt);
/*      */     
/*      */ 
/*      */ 
/*      */     public abstract void printTo(StringBuffer paramStringBuffer, int paramInt);
/*      */     
/*      */ 
/*      */ 
/*      */     public abstract void printTo(Writer paramWriter, int paramInt)
/*      */       throws IOException;
/*      */     
/*      */ 
/*      */ 
/*      */     public abstract int parse(String paramString, int paramInt);
/*      */     
/*      */ 
/*      */     public abstract int scan(String paramString, int paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */   static class SimpleAffix
/*      */     implements PeriodFormatterBuilder.PeriodFieldAffix
/*      */   {
/*      */     private final String iText;
/*      */     
/*      */ 
/*      */     SimpleAffix(String paramString)
/*      */     {
/*  858 */       this.iText = paramString;
/*      */     }
/*      */     
/*      */     public int calculatePrintedLength(int paramInt) {
/*  862 */       return this.iText.length();
/*      */     }
/*      */     
/*      */     public void printTo(StringBuffer paramStringBuffer, int paramInt) {
/*  866 */       paramStringBuffer.append(this.iText);
/*      */     }
/*      */     
/*      */     public void printTo(Writer paramWriter, int paramInt) throws IOException {
/*  870 */       paramWriter.write(this.iText);
/*      */     }
/*      */     
/*      */     public int parse(String paramString, int paramInt) {
/*  874 */       String str = this.iText;
/*  875 */       int i = str.length();
/*  876 */       if (paramString.regionMatches(true, paramInt, str, 0, i)) {
/*  877 */         return paramInt + i;
/*      */       }
/*  879 */       return paramInt ^ 0xFFFFFFFF;
/*      */     }
/*      */     
/*      */     public int scan(String paramString, int paramInt) {
/*  883 */       String str = this.iText;
/*  884 */       int i = str.length();
/*  885 */       int j = paramString.length();
/*      */       
/*  887 */       for (int k = paramInt; k < j; k++) {
/*  888 */         if (paramString.regionMatches(true, k, str, 0, i)) {
/*  889 */           return k;
/*      */         }
/*      */         
/*  892 */         switch (paramString.charAt(k)) {
/*      */         case '+': case ',': case '-': case '.': case '0': case '1': case '2': 
/*      */         case '3': case '4': case '5': case '6': case '7': case '8': case '9': 
/*      */           break;
/*      */         case '/': default: 
/*      */           break label136;
/*      */         }
/*      */       }
/*      */       label136:
/*  901 */       return paramInt ^ 0xFFFFFFFF;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   static class PluralAffix
/*      */     implements PeriodFormatterBuilder.PeriodFieldAffix
/*      */   {
/*      */     private final String iSingularText;
/*      */     
/*      */     private final String iPluralText;
/*      */     
/*      */     PluralAffix(String paramString1, String paramString2)
/*      */     {
/*  915 */       this.iSingularText = paramString1;
/*  916 */       this.iPluralText = paramString2;
/*      */     }
/*      */     
/*      */     public int calculatePrintedLength(int paramInt) {
/*  920 */       return (paramInt == 1 ? this.iSingularText : this.iPluralText).length();
/*      */     }
/*      */     
/*      */     public void printTo(StringBuffer paramStringBuffer, int paramInt) {
/*  924 */       paramStringBuffer.append(paramInt == 1 ? this.iSingularText : this.iPluralText);
/*      */     }
/*      */     
/*      */     public void printTo(Writer paramWriter, int paramInt) throws IOException {
/*  928 */       paramWriter.write(paramInt == 1 ? this.iSingularText : this.iPluralText);
/*      */     }
/*      */     
/*      */     public int parse(String paramString, int paramInt) {
/*  932 */       Object localObject1 = this.iPluralText;
/*  933 */       Object localObject2 = this.iSingularText;
/*      */       
/*  935 */       if (((String)localObject1).length() < ((String)localObject2).length())
/*      */       {
/*  937 */         Object localObject3 = localObject1;
/*  938 */         localObject1 = localObject2;
/*  939 */         localObject2 = localObject3;
/*      */       }
/*      */       
/*  942 */       if (paramString.regionMatches(true, paramInt, (String)localObject1, 0, ((String)localObject1).length()))
/*      */       {
/*  944 */         return paramInt + ((String)localObject1).length();
/*      */       }
/*  946 */       if (paramString.regionMatches(true, paramInt, (String)localObject2, 0, ((String)localObject2).length()))
/*      */       {
/*  948 */         return paramInt + ((String)localObject2).length();
/*      */       }
/*      */       
/*  951 */       return paramInt ^ 0xFFFFFFFF;
/*      */     }
/*      */     
/*      */     public int scan(String paramString, int paramInt) {
/*  955 */       Object localObject1 = this.iPluralText;
/*  956 */       Object localObject2 = this.iSingularText;
/*      */       
/*  958 */       if (((String)localObject1).length() < ((String)localObject2).length())
/*      */       {
/*  960 */         Object localObject3 = localObject1;
/*  961 */         localObject1 = localObject2;
/*  962 */         localObject2 = localObject3;
/*      */       }
/*      */       
/*  965 */       int i = ((String)localObject1).length();
/*  966 */       int j = ((String)localObject2).length();
/*      */       
/*  968 */       int k = paramString.length();
/*  969 */       for (int m = paramInt; m < k; m++) {
/*  970 */         if (paramString.regionMatches(true, m, (String)localObject1, 0, i)) {
/*  971 */           return m;
/*      */         }
/*  973 */         if (paramString.regionMatches(true, m, (String)localObject2, 0, j)) {
/*  974 */           return m;
/*      */         }
/*      */       }
/*  977 */       return paramInt ^ 0xFFFFFFFF;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   static class CompositeAffix
/*      */     implements PeriodFormatterBuilder.PeriodFieldAffix
/*      */   {
/*      */     private final PeriodFormatterBuilder.PeriodFieldAffix iLeft;
/*      */     private final PeriodFormatterBuilder.PeriodFieldAffix iRight;
/*      */     
/*      */     CompositeAffix(PeriodFormatterBuilder.PeriodFieldAffix paramPeriodFieldAffix1, PeriodFormatterBuilder.PeriodFieldAffix paramPeriodFieldAffix2)
/*      */     {
/*  990 */       this.iLeft = paramPeriodFieldAffix1;
/*  991 */       this.iRight = paramPeriodFieldAffix2;
/*      */     }
/*      */     
/*      */     public int calculatePrintedLength(int paramInt) {
/*  995 */       return this.iLeft.calculatePrintedLength(paramInt) + this.iRight.calculatePrintedLength(paramInt);
/*      */     }
/*      */     
/*      */     public void printTo(StringBuffer paramStringBuffer, int paramInt)
/*      */     {
/* 1000 */       this.iLeft.printTo(paramStringBuffer, paramInt);
/* 1001 */       this.iRight.printTo(paramStringBuffer, paramInt);
/*      */     }
/*      */     
/*      */     public void printTo(Writer paramWriter, int paramInt) throws IOException {
/* 1005 */       this.iLeft.printTo(paramWriter, paramInt);
/* 1006 */       this.iRight.printTo(paramWriter, paramInt);
/*      */     }
/*      */     
/*      */     public int parse(String paramString, int paramInt) {
/* 1010 */       paramInt = this.iLeft.parse(paramString, paramInt);
/* 1011 */       if (paramInt >= 0) {
/* 1012 */         paramInt = this.iRight.parse(paramString, paramInt);
/*      */       }
/* 1014 */       return paramInt;
/*      */     }
/*      */     
/*      */     public int scan(String paramString, int paramInt) {
/* 1018 */       int i = this.iLeft.scan(paramString, paramInt);
/* 1019 */       if (i >= 0) {
/* 1020 */         return this.iRight.scan(paramString, i);
/*      */       }
/* 1022 */       return paramInt ^ 0xFFFFFFFF;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   static class FieldFormatter
/*      */     implements PeriodPrinter, PeriodParser
/*      */   {
/*      */     private final int iMinPrintedDigits;
/*      */     
/*      */ 
/*      */     private final int iPrintZeroSetting;
/*      */     
/*      */ 
/*      */     private final int iMaxParsedDigits;
/*      */     
/*      */     private final boolean iRejectSignedValues;
/*      */     
/*      */     private final int iFieldType;
/*      */     
/*      */     private final FieldFormatter[] iFieldFormatters;
/*      */     
/*      */     private final PeriodFormatterBuilder.PeriodFieldAffix iPrefix;
/*      */     
/*      */     private final PeriodFormatterBuilder.PeriodFieldAffix iSuffix;
/*      */     
/*      */ 
/*      */     FieldFormatter(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, FieldFormatter[] paramArrayOfFieldFormatter, PeriodFormatterBuilder.PeriodFieldAffix paramPeriodFieldAffix1, PeriodFormatterBuilder.PeriodFieldAffix paramPeriodFieldAffix2)
/*      */     {
/* 1052 */       this.iMinPrintedDigits = paramInt1;
/* 1053 */       this.iPrintZeroSetting = paramInt2;
/* 1054 */       this.iMaxParsedDigits = paramInt3;
/* 1055 */       this.iRejectSignedValues = paramBoolean;
/* 1056 */       this.iFieldType = paramInt4;
/* 1057 */       this.iFieldFormatters = paramArrayOfFieldFormatter;
/* 1058 */       this.iPrefix = paramPeriodFieldAffix1;
/* 1059 */       this.iSuffix = paramPeriodFieldAffix2;
/*      */     }
/*      */     
/*      */     FieldFormatter(FieldFormatter paramFieldFormatter, PeriodFormatterBuilder.PeriodFieldAffix paramPeriodFieldAffix) {
/* 1063 */       this.iMinPrintedDigits = paramFieldFormatter.iMinPrintedDigits;
/* 1064 */       this.iPrintZeroSetting = paramFieldFormatter.iPrintZeroSetting;
/* 1065 */       this.iMaxParsedDigits = paramFieldFormatter.iMaxParsedDigits;
/* 1066 */       this.iRejectSignedValues = paramFieldFormatter.iRejectSignedValues;
/* 1067 */       this.iFieldType = paramFieldFormatter.iFieldType;
/* 1068 */       this.iFieldFormatters = paramFieldFormatter.iFieldFormatters;
/* 1069 */       this.iPrefix = paramFieldFormatter.iPrefix;
/* 1070 */       if (paramFieldFormatter.iSuffix != null) {
/* 1071 */         paramPeriodFieldAffix = new PeriodFormatterBuilder.CompositeAffix(paramFieldFormatter.iSuffix, paramPeriodFieldAffix);
/*      */       }
/* 1073 */       this.iSuffix = paramPeriodFieldAffix;
/*      */     }
/*      */     
/*      */     public int countFieldsToPrint(ReadablePeriod paramReadablePeriod, int paramInt, Locale paramLocale) {
/* 1077 */       if (paramInt <= 0) {
/* 1078 */         return 0;
/*      */       }
/* 1080 */       if ((this.iPrintZeroSetting == 4) || (getFieldValue(paramReadablePeriod) != Long.MAX_VALUE)) {
/* 1081 */         return 1;
/*      */       }
/* 1083 */       return 0;
/*      */     }
/*      */     
/*      */     public int calculatePrintedLength(ReadablePeriod paramReadablePeriod, Locale paramLocale) {
/* 1087 */       long l = getFieldValue(paramReadablePeriod);
/* 1088 */       if (l == Long.MAX_VALUE) {
/* 1089 */         return 0;
/*      */       }
/*      */       
/* 1092 */       int i = Math.max(FormatUtils.calculateDigitCount(l), this.iMinPrintedDigits);
/* 1093 */       if (this.iFieldType >= 8)
/*      */       {
/*      */ 
/* 1096 */         i = Math.max(i, 4);
/*      */         
/* 1098 */         i++;
/* 1099 */         if ((this.iFieldType == 9) && (Math.abs(l) % 1000L == 0L))
/*      */         {
/* 1101 */           i -= 4;
/*      */         }
/*      */         
/* 1104 */         l /= 1000L;
/*      */       }
/* 1106 */       int j = (int)l;
/*      */       
/* 1108 */       if (this.iPrefix != null) {
/* 1109 */         i += this.iPrefix.calculatePrintedLength(j);
/*      */       }
/* 1111 */       if (this.iSuffix != null) {
/* 1112 */         i += this.iSuffix.calculatePrintedLength(j);
/*      */       }
/*      */       
/* 1115 */       return i;
/*      */     }
/*      */     
/*      */     public void printTo(StringBuffer paramStringBuffer, ReadablePeriod paramReadablePeriod, Locale paramLocale) {
/* 1119 */       long l = getFieldValue(paramReadablePeriod);
/* 1120 */       if (l == Long.MAX_VALUE) {
/* 1121 */         return;
/*      */       }
/* 1123 */       int i = (int)l;
/* 1124 */       if (this.iFieldType >= 8) {
/* 1125 */         i = (int)(l / 1000L);
/*      */       }
/*      */       
/* 1128 */       if (this.iPrefix != null) {
/* 1129 */         this.iPrefix.printTo(paramStringBuffer, i);
/*      */       }
/* 1131 */       int j = this.iMinPrintedDigits;
/* 1132 */       if (j <= 1) {
/* 1133 */         FormatUtils.appendUnpaddedInteger(paramStringBuffer, i);
/*      */       } else {
/* 1135 */         FormatUtils.appendPaddedInteger(paramStringBuffer, i, j);
/*      */       }
/* 1137 */       if (this.iFieldType >= 8) {
/* 1138 */         int k = (int)(Math.abs(l) % 1000L);
/* 1139 */         if ((this.iFieldType == 8) || (k > 0)) {
/* 1140 */           paramStringBuffer.append('.');
/* 1141 */           FormatUtils.appendPaddedInteger(paramStringBuffer, k, 3);
/*      */         }
/*      */       }
/* 1144 */       if (this.iSuffix != null) {
/* 1145 */         this.iSuffix.printTo(paramStringBuffer, i);
/*      */       }
/*      */     }
/*      */     
/*      */     public void printTo(Writer paramWriter, ReadablePeriod paramReadablePeriod, Locale paramLocale) throws IOException {
/* 1150 */       long l = getFieldValue(paramReadablePeriod);
/* 1151 */       if (l == Long.MAX_VALUE) {
/* 1152 */         return;
/*      */       }
/* 1154 */       int i = (int)l;
/* 1155 */       if (this.iFieldType >= 8) {
/* 1156 */         i = (int)(l / 1000L);
/*      */       }
/*      */       
/* 1159 */       if (this.iPrefix != null) {
/* 1160 */         this.iPrefix.printTo(paramWriter, i);
/*      */       }
/* 1162 */       int j = this.iMinPrintedDigits;
/* 1163 */       if (j <= 1) {
/* 1164 */         FormatUtils.writeUnpaddedInteger(paramWriter, i);
/*      */       } else {
/* 1166 */         FormatUtils.writePaddedInteger(paramWriter, i, j);
/*      */       }
/* 1168 */       if (this.iFieldType >= 8) {
/* 1169 */         int k = (int)(Math.abs(l) % 1000L);
/* 1170 */         if ((this.iFieldType == 8) || (k > 0)) {
/* 1171 */           paramWriter.write(46);
/* 1172 */           FormatUtils.writePaddedInteger(paramWriter, k, 3);
/*      */         }
/*      */       }
/* 1175 */       if (this.iSuffix != null) {
/* 1176 */         this.iSuffix.printTo(paramWriter, i);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     public int parseInto(ReadWritablePeriod paramReadWritablePeriod, String paramString, int paramInt, Locale paramLocale)
/*      */     {
/* 1184 */       int i = this.iPrintZeroSetting == 4 ? 1 : 0;
/*      */       
/*      */ 
/* 1187 */       if (paramInt >= paramString.length()) {
/* 1188 */         return i != 0 ? paramInt ^ 0xFFFFFFFF : paramInt;
/*      */       }
/*      */       
/* 1191 */       if (this.iPrefix != null) {
/* 1192 */         paramInt = this.iPrefix.parse(paramString, paramInt);
/* 1193 */         if (paramInt >= 0)
/*      */         {
/* 1195 */           i = 1;
/*      */         }
/*      */         else {
/* 1198 */           if (i == 0)
/*      */           {
/*      */ 
/*      */ 
/* 1202 */             return paramInt ^ 0xFFFFFFFF;
/*      */           }
/* 1204 */           return paramInt;
/*      */         }
/*      */       }
/*      */       
/* 1208 */       int j = -1;
/* 1209 */       if ((this.iSuffix != null) && (i == 0))
/*      */       {
/*      */ 
/* 1212 */         j = this.iSuffix.scan(paramString, paramInt);
/* 1213 */         if (j >= 0)
/*      */         {
/* 1215 */           i = 1;
/*      */         }
/*      */         else {
/* 1218 */           if (i == 0)
/*      */           {
/*      */ 
/*      */ 
/* 1222 */             return j ^ 0xFFFFFFFF;
/*      */           }
/* 1224 */           return j;
/*      */         }
/*      */       }
/*      */       
/* 1228 */       if ((i == 0) && (!isSupported(paramReadWritablePeriod.getPeriodType(), this.iFieldType)))
/*      */       {
/*      */ 
/* 1231 */         return paramInt;
/*      */       }
/*      */       
/*      */       int k;
/* 1235 */       if (j > 0) {
/* 1236 */         k = Math.min(this.iMaxParsedDigits, j - paramInt);
/*      */       } else {
/* 1238 */         k = Math.min(this.iMaxParsedDigits, paramString.length() - paramInt);
/*      */       }
/*      */       
/*      */ 
/* 1242 */       int m = 0;
/* 1243 */       int n = -1;
/* 1244 */       int i1 = 0;
/* 1245 */       int i2; int i3; while (m < k) {
/* 1246 */         i2 = paramString.charAt(paramInt + m);
/*      */         
/* 1248 */         if ((m == 0) && ((i2 == 45) || (i2 == 43)) && (!this.iRejectSignedValues)) {
/* 1249 */           i3 = i2 == 45 ? 1 : 0;
/*      */           
/*      */ 
/* 1252 */           if ((m + 1 >= k) || ((i2 = paramString.charAt(paramInt + m + 1)) < '0') || (i2 > 57)) {
/*      */             break;
/*      */           }
/*      */           
/*      */ 
/*      */ 
/* 1258 */           if (i3 != 0) {
/* 1259 */             m++;
/*      */           }
/*      */           else {
/* 1262 */             paramInt++;
/*      */           }
/*      */           
/* 1265 */           k = Math.min(k + 1, paramString.length() - paramInt);
/*      */         }
/*      */         else
/*      */         {
/* 1269 */           if ((i2 >= 48) && (i2 <= 57)) {
/* 1270 */             i1 = 1;
/*      */           } else {
/* 1272 */             if (((i2 != 46) && (i2 != 44)) || ((this.iFieldType != 8) && (this.iFieldType != 9)))
/*      */               break;
/* 1274 */             if (n >= 0) {
/*      */               break;
/*      */             }
/*      */             
/* 1278 */             n = paramInt + m + 1;
/*      */             
/* 1280 */             k = Math.min(k + 1, paramString.length() - paramInt);
/*      */           }
/*      */           
/*      */ 
/*      */ 
/* 1285 */           m++;
/*      */         }
/*      */       }
/* 1288 */       if (i1 == 0) {
/* 1289 */         return paramInt ^ 0xFFFFFFFF;
/*      */       }
/*      */       
/* 1292 */       if ((j >= 0) && (paramInt + m != j))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 1297 */         return paramInt;
/*      */       }
/*      */       
/* 1300 */       if ((this.iFieldType != 8) && (this.iFieldType != 9))
/*      */       {
/* 1302 */         setFieldValue(paramReadWritablePeriod, this.iFieldType, parseInt(paramString, paramInt, m));
/* 1303 */       } else if (n < 0) {
/* 1304 */         setFieldValue(paramReadWritablePeriod, 6, parseInt(paramString, paramInt, m));
/* 1305 */         setFieldValue(paramReadWritablePeriod, 7, 0);
/*      */       } else {
/* 1307 */         i2 = parseInt(paramString, paramInt, n - paramInt - 1);
/* 1308 */         setFieldValue(paramReadWritablePeriod, 6, i2);
/*      */         
/* 1310 */         i3 = paramInt + m - n;
/*      */         int i4;
/* 1312 */         if (i3 <= 0) {
/* 1313 */           i4 = 0;
/*      */         } else {
/* 1315 */           if (i3 >= 3) {
/* 1316 */             i4 = parseInt(paramString, n, 3);
/*      */           } else {
/* 1318 */             i4 = parseInt(paramString, n, i3);
/* 1319 */             if (i3 == 1) {
/* 1320 */               i4 *= 100;
/*      */             } else {
/* 1322 */               i4 *= 10;
/*      */             }
/*      */           }
/* 1325 */           if (i2 < 0) {
/* 1326 */             i4 = -i4;
/*      */           }
/*      */         }
/*      */         
/* 1330 */         setFieldValue(paramReadWritablePeriod, 7, i4);
/*      */       }
/*      */       
/* 1333 */       paramInt += m;
/*      */       
/* 1335 */       if ((paramInt >= 0) && (this.iSuffix != null)) {
/* 1336 */         paramInt = this.iSuffix.parse(paramString, paramInt);
/*      */       }
/*      */       
/* 1339 */       return paramInt;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private int parseInt(String paramString, int paramInt1, int paramInt2)
/*      */     {
/* 1349 */       if (paramInt2 >= 10)
/*      */       {
/* 1351 */         return Integer.parseInt(paramString.substring(paramInt1, paramInt1 + paramInt2));
/*      */       }
/* 1353 */       if (paramInt2 <= 0) {
/* 1354 */         return 0;
/*      */       }
/* 1356 */       int i = paramString.charAt(paramInt1++);
/* 1357 */       paramInt2--;
/*      */       int j;
/* 1359 */       if (i == 45) {
/* 1360 */         paramInt2--; if (paramInt2 < 0) {
/* 1361 */           return 0;
/*      */         }
/* 1363 */         j = 1;
/* 1364 */         i = paramString.charAt(paramInt1++);
/*      */       } else {
/* 1366 */         j = 0;
/*      */       }
/* 1368 */       i -= 48;
/* 1369 */       while (paramInt2-- > 0) {
/* 1370 */         i = (i << 3) + (i << 1) + paramString.charAt(paramInt1++) - 48;
/*      */       }
/* 1372 */       return j != 0 ? -i : i;
/*      */     }
/*      */     
/*      */ 
/*      */     long getFieldValue(ReadablePeriod paramReadablePeriod)
/*      */     {
/*      */       PeriodType localPeriodType;
/*      */       
/* 1380 */       if (this.iPrintZeroSetting == 4) {
/* 1381 */         localPeriodType = null;
/*      */       } else {
/* 1383 */         localPeriodType = paramReadablePeriod.getPeriodType();
/*      */       }
/* 1385 */       if ((localPeriodType != null) && (!isSupported(localPeriodType, this.iFieldType))) {
/* 1386 */         return Long.MAX_VALUE;
/*      */       }
/*      */       
/*      */       long l;
/*      */       int i;
/* 1391 */       switch (this.iFieldType) {
/*      */       default: 
/* 1393 */         return Long.MAX_VALUE;
/*      */       case 0: 
/* 1395 */         l = paramReadablePeriod.get(DurationFieldType.years());
/* 1396 */         break;
/*      */       case 1: 
/* 1398 */         l = paramReadablePeriod.get(DurationFieldType.months());
/* 1399 */         break;
/*      */       case 2: 
/* 1401 */         l = paramReadablePeriod.get(DurationFieldType.weeks());
/* 1402 */         break;
/*      */       case 3: 
/* 1404 */         l = paramReadablePeriod.get(DurationFieldType.days());
/* 1405 */         break;
/*      */       case 4: 
/* 1407 */         l = paramReadablePeriod.get(DurationFieldType.hours());
/* 1408 */         break;
/*      */       case 5: 
/* 1410 */         l = paramReadablePeriod.get(DurationFieldType.minutes());
/* 1411 */         break;
/*      */       case 6: 
/* 1413 */         l = paramReadablePeriod.get(DurationFieldType.seconds());
/* 1414 */         break;
/*      */       case 7: 
/* 1416 */         l = paramReadablePeriod.get(DurationFieldType.millis());
/* 1417 */         break;
/*      */       case 8: 
/*      */       case 9: 
/* 1420 */         i = paramReadablePeriod.get(DurationFieldType.seconds());
/* 1421 */         int j = paramReadablePeriod.get(DurationFieldType.millis());
/* 1422 */         l = i * 1000L + j;
/*      */       }
/*      */       
/*      */       
/*      */ 
/* 1427 */       if (l == 0L) {
/* 1428 */         switch (this.iPrintZeroSetting) {
/*      */         case 5: 
/* 1430 */           return Long.MAX_VALUE;
/*      */         case 2: 
/* 1432 */           if ((isZero(paramReadablePeriod)) && (this.iFieldFormatters[this.iFieldType] == this))
/* 1433 */             for (i = this.iFieldType + 1; i <= 9;) {
/* 1434 */               if ((isSupported(localPeriodType, i)) && (this.iFieldFormatters[i] != null)) {
/* 1435 */                 return Long.MAX_VALUE;
/*      */               }
/* 1433 */               i++; continue;
/*      */               
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1439 */               return Long.MAX_VALUE;
/*      */               
/*      */ 
/*      */ 
/* 1443 */               if ((isZero(paramReadablePeriod)) && (this.iFieldFormatters[this.iFieldType] == this)) {
/* 1444 */                 i = Math.min(this.iFieldType, 8);
/* 1445 */                 i--; }
/* 1446 */               while ((i >= 0) && (i <= 9)) {
/* 1447 */                 if ((isSupported(localPeriodType, i)) && (this.iFieldFormatters[i] != null)) {
/* 1448 */                   return Long.MAX_VALUE;
/*      */                 }
/* 1446 */                 i--; continue;
/*      */                 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1452 */                 return Long.MAX_VALUE;
/*      */               }
/*      */             }
/*      */           break;
/*      */         }
/*      */       }
/* 1458 */       return l;
/*      */     }
/*      */     
/*      */     boolean isZero(ReadablePeriod paramReadablePeriod) {
/* 1462 */       int i = 0; for (int j = paramReadablePeriod.size(); i < j; i++) {
/* 1463 */         if (paramReadablePeriod.getValue(i) != 0) {
/* 1464 */           return false;
/*      */         }
/*      */       }
/* 1467 */       return true;
/*      */     }
/*      */     
/*      */     boolean isSupported(PeriodType paramPeriodType, int paramInt) {
/* 1471 */       switch (paramInt) {
/*      */       default: 
/* 1473 */         return false;
/*      */       case 0: 
/* 1475 */         return paramPeriodType.isSupported(DurationFieldType.years());
/*      */       case 1: 
/* 1477 */         return paramPeriodType.isSupported(DurationFieldType.months());
/*      */       case 2: 
/* 1479 */         return paramPeriodType.isSupported(DurationFieldType.weeks());
/*      */       case 3: 
/* 1481 */         return paramPeriodType.isSupported(DurationFieldType.days());
/*      */       case 4: 
/* 1483 */         return paramPeriodType.isSupported(DurationFieldType.hours());
/*      */       case 5: 
/* 1485 */         return paramPeriodType.isSupported(DurationFieldType.minutes());
/*      */       case 6: 
/* 1487 */         return paramPeriodType.isSupported(DurationFieldType.seconds());
/*      */       case 7: 
/* 1489 */         return paramPeriodType.isSupported(DurationFieldType.millis());
/*      */       }
/*      */       
/* 1492 */       return (paramPeriodType.isSupported(DurationFieldType.seconds())) || (paramPeriodType.isSupported(DurationFieldType.millis()));
/*      */     }
/*      */     
/*      */ 
/*      */     void setFieldValue(ReadWritablePeriod paramReadWritablePeriod, int paramInt1, int paramInt2)
/*      */     {
/* 1498 */       switch (paramInt1) {
/*      */       default: 
/*      */         break;
/*      */       case 0: 
/* 1502 */         paramReadWritablePeriod.setYears(paramInt2);
/* 1503 */         break;
/*      */       case 1: 
/* 1505 */         paramReadWritablePeriod.setMonths(paramInt2);
/* 1506 */         break;
/*      */       case 2: 
/* 1508 */         paramReadWritablePeriod.setWeeks(paramInt2);
/* 1509 */         break;
/*      */       case 3: 
/* 1511 */         paramReadWritablePeriod.setDays(paramInt2);
/* 1512 */         break;
/*      */       case 4: 
/* 1514 */         paramReadWritablePeriod.setHours(paramInt2);
/* 1515 */         break;
/*      */       case 5: 
/* 1517 */         paramReadWritablePeriod.setMinutes(paramInt2);
/* 1518 */         break;
/*      */       case 6: 
/* 1520 */         paramReadWritablePeriod.setSeconds(paramInt2);
/* 1521 */         break;
/*      */       case 7: 
/* 1523 */         paramReadWritablePeriod.setMillis(paramInt2);
/*      */       }
/*      */     }
/*      */     
/*      */     int getFieldType()
/*      */     {
/* 1529 */       return this.iFieldType;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   static class Literal
/*      */     implements PeriodPrinter, PeriodParser
/*      */   {
/* 1539 */     static final Literal EMPTY = new Literal("");
/*      */     private final String iText;
/*      */     
/*      */     Literal(String paramString) {
/* 1543 */       this.iText = paramString;
/*      */     }
/*      */     
/*      */     public int countFieldsToPrint(ReadablePeriod paramReadablePeriod, int paramInt, Locale paramLocale) {
/* 1547 */       return 0;
/*      */     }
/*      */     
/*      */     public int calculatePrintedLength(ReadablePeriod paramReadablePeriod, Locale paramLocale) {
/* 1551 */       return this.iText.length();
/*      */     }
/*      */     
/*      */     public void printTo(StringBuffer paramStringBuffer, ReadablePeriod paramReadablePeriod, Locale paramLocale) {
/* 1555 */       paramStringBuffer.append(this.iText);
/*      */     }
/*      */     
/*      */     public void printTo(Writer paramWriter, ReadablePeriod paramReadablePeriod, Locale paramLocale) throws IOException {
/* 1559 */       paramWriter.write(this.iText);
/*      */     }
/*      */     
/*      */ 
/*      */     public int parseInto(ReadWritablePeriod paramReadWritablePeriod, String paramString, int paramInt, Locale paramLocale)
/*      */     {
/* 1565 */       if (paramString.regionMatches(true, paramInt, this.iText, 0, this.iText.length())) {
/* 1566 */         return paramInt + this.iText.length();
/*      */       }
/* 1568 */       return paramInt ^ 0xFFFFFFFF;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   static class Separator
/*      */     implements PeriodPrinter, PeriodParser
/*      */   {
/*      */     private final String iText;
/*      */     
/*      */     private final String iFinalText;
/*      */     
/*      */     private final String[] iParsedForms;
/*      */     
/*      */     private final boolean iUseBefore;
/*      */     
/*      */     private final boolean iUseAfter;
/*      */     
/*      */     private final PeriodPrinter iBeforePrinter;
/*      */     
/*      */     private volatile PeriodPrinter iAfterPrinter;
/*      */     private final PeriodParser iBeforeParser;
/*      */     private volatile PeriodParser iAfterParser;
/*      */     
/*      */     Separator(String paramString1, String paramString2, String[] paramArrayOfString, PeriodPrinter paramPeriodPrinter, PeriodParser paramPeriodParser, boolean paramBoolean1, boolean paramBoolean2)
/*      */     {
/* 1594 */       this.iText = paramString1;
/* 1595 */       this.iFinalText = paramString2;
/*      */       
/* 1597 */       if (((paramString2 == null) || (paramString1.equals(paramString2))) && ((paramArrayOfString == null) || (paramArrayOfString.length == 0)))
/*      */       {
/*      */ 
/* 1600 */         this.iParsedForms = new String[] { paramString1 };
/*      */       }
/*      */       else {
/* 1603 */         TreeSet localTreeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
/* 1604 */         localTreeSet.add(paramString1);
/* 1605 */         localTreeSet.add(paramString2);
/* 1606 */         if (paramArrayOfString != null) {
/* 1607 */           int i = paramArrayOfString.length; for (;;) { i--; if (i < 0) break;
/* 1608 */             localTreeSet.add(paramArrayOfString[i]);
/*      */           }
/*      */         }
/* 1611 */         ArrayList localArrayList = new ArrayList(localTreeSet);
/* 1612 */         Collections.reverse(localArrayList);
/* 1613 */         this.iParsedForms = ((String[])localArrayList.toArray(new String[localArrayList.size()]));
/*      */       }
/*      */       
/* 1616 */       this.iBeforePrinter = paramPeriodPrinter;
/* 1617 */       this.iBeforeParser = paramPeriodParser;
/* 1618 */       this.iUseBefore = paramBoolean1;
/* 1619 */       this.iUseAfter = paramBoolean2;
/*      */     }
/*      */     
/*      */     public int countFieldsToPrint(ReadablePeriod paramReadablePeriod, int paramInt, Locale paramLocale) {
/* 1623 */       int i = this.iBeforePrinter.countFieldsToPrint(paramReadablePeriod, paramInt, paramLocale);
/* 1624 */       if (i < paramInt) {
/* 1625 */         i += this.iAfterPrinter.countFieldsToPrint(paramReadablePeriod, paramInt, paramLocale);
/*      */       }
/* 1627 */       return i;
/*      */     }
/*      */     
/*      */     public int calculatePrintedLength(ReadablePeriod paramReadablePeriod, Locale paramLocale) {
/* 1631 */       PeriodPrinter localPeriodPrinter1 = this.iBeforePrinter;
/* 1632 */       PeriodPrinter localPeriodPrinter2 = this.iAfterPrinter;
/*      */       
/* 1634 */       int i = localPeriodPrinter1.calculatePrintedLength(paramReadablePeriod, paramLocale) + localPeriodPrinter2.calculatePrintedLength(paramReadablePeriod, paramLocale);
/*      */       
/*      */ 
/* 1637 */       if (this.iUseBefore) {
/* 1638 */         if (localPeriodPrinter1.countFieldsToPrint(paramReadablePeriod, 1, paramLocale) > 0) {
/* 1639 */           if (this.iUseAfter) {
/* 1640 */             int j = localPeriodPrinter2.countFieldsToPrint(paramReadablePeriod, 2, paramLocale);
/* 1641 */             if (j > 0) {
/* 1642 */               i += (j > 1 ? this.iText : this.iFinalText).length();
/*      */             }
/*      */           } else {
/* 1645 */             i += this.iText.length();
/*      */           }
/*      */         }
/* 1648 */       } else if ((this.iUseAfter) && (localPeriodPrinter2.countFieldsToPrint(paramReadablePeriod, 1, paramLocale) > 0)) {
/* 1649 */         i += this.iText.length();
/*      */       }
/*      */       
/* 1652 */       return i;
/*      */     }
/*      */     
/*      */     public void printTo(StringBuffer paramStringBuffer, ReadablePeriod paramReadablePeriod, Locale paramLocale) {
/* 1656 */       PeriodPrinter localPeriodPrinter1 = this.iBeforePrinter;
/* 1657 */       PeriodPrinter localPeriodPrinter2 = this.iAfterPrinter;
/*      */       
/* 1659 */       localPeriodPrinter1.printTo(paramStringBuffer, paramReadablePeriod, paramLocale);
/* 1660 */       if (this.iUseBefore) {
/* 1661 */         if (localPeriodPrinter1.countFieldsToPrint(paramReadablePeriod, 1, paramLocale) > 0) {
/* 1662 */           if (this.iUseAfter) {
/* 1663 */             int i = localPeriodPrinter2.countFieldsToPrint(paramReadablePeriod, 2, paramLocale);
/* 1664 */             if (i > 0) {
/* 1665 */               paramStringBuffer.append(i > 1 ? this.iText : this.iFinalText);
/*      */             }
/*      */           } else {
/* 1668 */             paramStringBuffer.append(this.iText);
/*      */           }
/*      */         }
/* 1671 */       } else if ((this.iUseAfter) && (localPeriodPrinter2.countFieldsToPrint(paramReadablePeriod, 1, paramLocale) > 0)) {
/* 1672 */         paramStringBuffer.append(this.iText);
/*      */       }
/* 1674 */       localPeriodPrinter2.printTo(paramStringBuffer, paramReadablePeriod, paramLocale);
/*      */     }
/*      */     
/*      */     public void printTo(Writer paramWriter, ReadablePeriod paramReadablePeriod, Locale paramLocale) throws IOException {
/* 1678 */       PeriodPrinter localPeriodPrinter1 = this.iBeforePrinter;
/* 1679 */       PeriodPrinter localPeriodPrinter2 = this.iAfterPrinter;
/*      */       
/* 1681 */       localPeriodPrinter1.printTo(paramWriter, paramReadablePeriod, paramLocale);
/* 1682 */       if (this.iUseBefore) {
/* 1683 */         if (localPeriodPrinter1.countFieldsToPrint(paramReadablePeriod, 1, paramLocale) > 0) {
/* 1684 */           if (this.iUseAfter) {
/* 1685 */             int i = localPeriodPrinter2.countFieldsToPrint(paramReadablePeriod, 2, paramLocale);
/* 1686 */             if (i > 0) {
/* 1687 */               paramWriter.write(i > 1 ? this.iText : this.iFinalText);
/*      */             }
/*      */           } else {
/* 1690 */             paramWriter.write(this.iText);
/*      */           }
/*      */         }
/* 1693 */       } else if ((this.iUseAfter) && (localPeriodPrinter2.countFieldsToPrint(paramReadablePeriod, 1, paramLocale) > 0)) {
/* 1694 */         paramWriter.write(this.iText);
/*      */       }
/* 1696 */       localPeriodPrinter2.printTo(paramWriter, paramReadablePeriod, paramLocale);
/*      */     }
/*      */     
/*      */ 
/*      */     public int parseInto(ReadWritablePeriod paramReadWritablePeriod, String paramString, int paramInt, Locale paramLocale)
/*      */     {
/* 1702 */       int i = paramInt;
/* 1703 */       paramInt = this.iBeforeParser.parseInto(paramReadWritablePeriod, paramString, paramInt, paramLocale);
/*      */       
/* 1705 */       if (paramInt < 0) {
/* 1706 */         return paramInt;
/*      */       }
/*      */       
/* 1709 */       int j = 0;
/* 1710 */       if (paramInt > i)
/*      */       {
/* 1712 */         String[] arrayOfString = this.iParsedForms;
/* 1713 */         int k = arrayOfString.length;
/* 1714 */         for (int m = 0; m < k; m++) {
/* 1715 */           String str = arrayOfString[m];
/* 1716 */           if ((str == null) || (str.length() == 0) || (paramString.regionMatches(true, paramInt, str, 0, str.length())))
/*      */           {
/*      */ 
/*      */ 
/* 1720 */             paramInt += str.length();
/* 1721 */             j = 1;
/* 1722 */             break;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/* 1727 */       i = paramInt;
/* 1728 */       paramInt = this.iAfterParser.parseInto(paramReadWritablePeriod, paramString, paramInt, paramLocale);
/*      */       
/* 1730 */       if (paramInt < 0) {
/* 1731 */         return paramInt;
/*      */       }
/*      */       
/* 1734 */       if ((j != 0) && (paramInt == i))
/*      */       {
/* 1736 */         return i ^ 0xFFFFFFFF;
/*      */       }
/*      */       
/* 1739 */       if ((paramInt > i) && (j == 0) && (!this.iUseBefore))
/*      */       {
/* 1741 */         return i ^ 0xFFFFFFFF;
/*      */       }
/*      */       
/* 1744 */       return paramInt;
/*      */     }
/*      */     
/*      */     Separator finish(PeriodPrinter paramPeriodPrinter, PeriodParser paramPeriodParser) {
/* 1748 */       this.iAfterPrinter = paramPeriodPrinter;
/* 1749 */       this.iAfterParser = paramPeriodParser;
/* 1750 */       return this;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   static class Composite
/*      */     implements PeriodPrinter, PeriodParser
/*      */   {
/*      */     private final PeriodPrinter[] iPrinters;
/*      */     
/*      */     private final PeriodParser[] iParsers;
/*      */     
/*      */ 
/*      */     Composite(List paramList)
/*      */     {
/* 1765 */       ArrayList localArrayList1 = new ArrayList();
/* 1766 */       ArrayList localArrayList2 = new ArrayList();
/*      */       
/* 1768 */       decompose(paramList, localArrayList1, localArrayList2);
/*      */       
/* 1770 */       if (localArrayList1.size() <= 0) {
/* 1771 */         this.iPrinters = null;
/*      */       } else {
/* 1773 */         this.iPrinters = ((PeriodPrinter[])localArrayList1.toArray(new PeriodPrinter[localArrayList1.size()]));
/*      */       }
/*      */       
/*      */ 
/* 1777 */       if (localArrayList2.size() <= 0) {
/* 1778 */         this.iParsers = null;
/*      */       } else {
/* 1780 */         this.iParsers = ((PeriodParser[])localArrayList2.toArray(new PeriodParser[localArrayList2.size()]));
/*      */       }
/*      */     }
/*      */     
/*      */     public int countFieldsToPrint(ReadablePeriod paramReadablePeriod, int paramInt, Locale paramLocale)
/*      */     {
/* 1786 */       int i = 0;
/* 1787 */       PeriodPrinter[] arrayOfPeriodPrinter = this.iPrinters;
/* 1788 */       for (int j = arrayOfPeriodPrinter.length; i < paramInt;) { j--; if (j < 0) break;
/* 1789 */         i += arrayOfPeriodPrinter[j].countFieldsToPrint(paramReadablePeriod, Integer.MAX_VALUE, paramLocale);
/*      */       }
/* 1791 */       return i;
/*      */     }
/*      */     
/*      */     public int calculatePrintedLength(ReadablePeriod paramReadablePeriod, Locale paramLocale) {
/* 1795 */       int i = 0;
/* 1796 */       PeriodPrinter[] arrayOfPeriodPrinter = this.iPrinters;
/* 1797 */       int j = arrayOfPeriodPrinter.length; for (;;) { j--; if (j < 0) break;
/* 1798 */         i += arrayOfPeriodPrinter[j].calculatePrintedLength(paramReadablePeriod, paramLocale);
/*      */       }
/* 1800 */       return i;
/*      */     }
/*      */     
/*      */     public void printTo(StringBuffer paramStringBuffer, ReadablePeriod paramReadablePeriod, Locale paramLocale) {
/* 1804 */       PeriodPrinter[] arrayOfPeriodPrinter = this.iPrinters;
/* 1805 */       int i = arrayOfPeriodPrinter.length;
/* 1806 */       for (int j = 0; j < i; j++) {
/* 1807 */         arrayOfPeriodPrinter[j].printTo(paramStringBuffer, paramReadablePeriod, paramLocale);
/*      */       }
/*      */     }
/*      */     
/*      */     public void printTo(Writer paramWriter, ReadablePeriod paramReadablePeriod, Locale paramLocale) throws IOException {
/* 1812 */       PeriodPrinter[] arrayOfPeriodPrinter = this.iPrinters;
/* 1813 */       int i = arrayOfPeriodPrinter.length;
/* 1814 */       for (int j = 0; j < i; j++) {
/* 1815 */         arrayOfPeriodPrinter[j].printTo(paramWriter, paramReadablePeriod, paramLocale);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */     public int parseInto(ReadWritablePeriod paramReadWritablePeriod, String paramString, int paramInt, Locale paramLocale)
/*      */     {
/* 1822 */       PeriodParser[] arrayOfPeriodParser = this.iParsers;
/* 1823 */       if (arrayOfPeriodParser == null) {
/* 1824 */         throw new UnsupportedOperationException();
/*      */       }
/*      */       
/* 1827 */       int i = arrayOfPeriodParser.length;
/* 1828 */       for (int j = 0; (j < i) && (paramInt >= 0); j++) {
/* 1829 */         paramInt = arrayOfPeriodParser[j].parseInto(paramReadWritablePeriod, paramString, paramInt, paramLocale);
/*      */       }
/* 1831 */       return paramInt;
/*      */     }
/*      */     
/*      */     private void decompose(List paramList1, List paramList2, List paramList3) {
/* 1835 */       int i = paramList1.size();
/* 1836 */       for (int j = 0; j < i; j += 2) {
/* 1837 */         Object localObject = paramList1.get(j);
/* 1838 */         if ((localObject instanceof PeriodPrinter)) {
/* 1839 */           if ((localObject instanceof Composite)) {
/* 1840 */             addArrayToList(paramList2, ((Composite)localObject).iPrinters);
/*      */           } else {
/* 1842 */             paramList2.add(localObject);
/*      */           }
/*      */         }
/*      */         
/* 1846 */         localObject = paramList1.get(j + 1);
/* 1847 */         if ((localObject instanceof PeriodParser)) {
/* 1848 */           if ((localObject instanceof Composite)) {
/* 1849 */             addArrayToList(paramList3, ((Composite)localObject).iParsers);
/*      */           } else {
/* 1851 */             paramList3.add(localObject);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     private void addArrayToList(List paramList, Object[] paramArrayOfObject) {
/* 1858 */       if (paramArrayOfObject != null) {
/* 1859 */         for (int i = 0; i < paramArrayOfObject.length; i++) {
/* 1860 */           paramList.add(paramArrayOfObject[i]);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\format\PeriodFormatterBuilder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */