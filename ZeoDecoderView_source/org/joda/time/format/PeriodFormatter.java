/*     */ package org.joda.time.format;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.Locale;
/*     */ import org.joda.time.MutablePeriod;
/*     */ import org.joda.time.Period;
/*     */ import org.joda.time.PeriodType;
/*     */ import org.joda.time.ReadWritablePeriod;
/*     */ import org.joda.time.ReadablePeriod;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PeriodFormatter
/*     */ {
/*     */   private final PeriodPrinter iPrinter;
/*     */   private final PeriodParser iParser;
/*     */   private final Locale iLocale;
/*     */   private final PeriodType iParseType;
/*     */   
/*     */   public PeriodFormatter(PeriodPrinter paramPeriodPrinter, PeriodParser paramPeriodParser)
/*     */   {
/*  88 */     this.iPrinter = paramPeriodPrinter;
/*  89 */     this.iParser = paramPeriodParser;
/*  90 */     this.iLocale = null;
/*  91 */     this.iParseType = null;
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
/*     */   private PeriodFormatter(PeriodPrinter paramPeriodPrinter, PeriodParser paramPeriodParser, Locale paramLocale, PeriodType paramPeriodType)
/*     */   {
/* 106 */     this.iPrinter = paramPeriodPrinter;
/* 107 */     this.iParser = paramPeriodParser;
/* 108 */     this.iLocale = paramLocale;
/* 109 */     this.iParseType = paramPeriodType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isPrinter()
/*     */   {
/* 119 */     return this.iPrinter != null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PeriodPrinter getPrinter()
/*     */   {
/* 128 */     return this.iPrinter;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isParser()
/*     */   {
/* 137 */     return this.iParser != null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PeriodParser getParser()
/*     */   {
/* 146 */     return this.iParser;
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
/*     */   public PeriodFormatter withLocale(Locale paramLocale)
/*     */   {
/* 161 */     if ((paramLocale == getLocale()) || ((paramLocale != null) && (paramLocale.equals(getLocale())))) {
/* 162 */       return this;
/*     */     }
/* 164 */     return new PeriodFormatter(this.iPrinter, this.iParser, paramLocale, this.iParseType);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Locale getLocale()
/*     */   {
/* 173 */     return this.iLocale;
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
/*     */   public PeriodFormatter withParseType(PeriodType paramPeriodType)
/*     */   {
/* 187 */     if (paramPeriodType == this.iParseType) {
/* 188 */       return this;
/*     */     }
/* 190 */     return new PeriodFormatter(this.iPrinter, this.iParser, this.iLocale, paramPeriodType);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PeriodType getParseType()
/*     */   {
/* 199 */     return this.iParseType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void printTo(StringBuffer paramStringBuffer, ReadablePeriod paramReadablePeriod)
/*     */   {
/* 210 */     checkPrinter();
/* 211 */     checkPeriod(paramReadablePeriod);
/*     */     
/* 213 */     getPrinter().printTo(paramStringBuffer, paramReadablePeriod, this.iLocale);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void printTo(Writer paramWriter, ReadablePeriod paramReadablePeriod)
/*     */     throws IOException
/*     */   {
/* 223 */     checkPrinter();
/* 224 */     checkPeriod(paramReadablePeriod);
/*     */     
/* 226 */     getPrinter().printTo(paramWriter, paramReadablePeriod, this.iLocale);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String print(ReadablePeriod paramReadablePeriod)
/*     */   {
/* 236 */     checkPrinter();
/* 237 */     checkPeriod(paramReadablePeriod);
/*     */     
/* 239 */     PeriodPrinter localPeriodPrinter = getPrinter();
/* 240 */     StringBuffer localStringBuffer = new StringBuffer(localPeriodPrinter.calculatePrintedLength(paramReadablePeriod, this.iLocale));
/* 241 */     localPeriodPrinter.printTo(localStringBuffer, paramReadablePeriod, this.iLocale);
/* 242 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void checkPrinter()
/*     */   {
/* 251 */     if (this.iPrinter == null) {
/* 252 */       throw new UnsupportedOperationException("Printing not supported");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void checkPeriod(ReadablePeriod paramReadablePeriod)
/*     */   {
/* 262 */     if (paramReadablePeriod == null) {
/* 263 */       throw new IllegalArgumentException("Period must not be null");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int parseInto(ReadWritablePeriod paramReadWritablePeriod, String paramString, int paramInt)
/*     */   {
/* 288 */     checkParser();
/* 289 */     checkPeriod(paramReadWritablePeriod);
/*     */     
/* 291 */     return getParser().parseInto(paramReadWritablePeriod, paramString, paramInt, this.iLocale);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Period parsePeriod(String paramString)
/*     */   {
/* 302 */     checkParser();
/*     */     
/* 304 */     return parseMutablePeriod(paramString).toPeriod();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutablePeriod parseMutablePeriod(String paramString)
/*     */   {
/* 315 */     checkParser();
/*     */     
/* 317 */     MutablePeriod localMutablePeriod = new MutablePeriod(0L, this.iParseType);
/* 318 */     int i = getParser().parseInto(localMutablePeriod, paramString, 0, this.iLocale);
/* 319 */     if (i >= 0) {
/* 320 */       if (i >= paramString.length()) {
/* 321 */         return localMutablePeriod;
/*     */       }
/*     */     } else {
/* 324 */       i ^= 0xFFFFFFFF;
/*     */     }
/* 326 */     throw new IllegalArgumentException(FormatUtils.createErrorMessage(paramString, i));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void checkParser()
/*     */   {
/* 335 */     if (this.iParser == null) {
/* 336 */       throw new UnsupportedOperationException("Parsing not supported");
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\format\PeriodFormatter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */