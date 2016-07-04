/*     */ package org.joda.time.convert;
/*     */ 
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTime;
/*     */ import org.joda.time.Period;
/*     */ import org.joda.time.ReadWritableInterval;
/*     */ import org.joda.time.ReadWritablePeriod;
/*     */ import org.joda.time.ReadablePartial;
/*     */ import org.joda.time.field.FieldUtils;
/*     */ import org.joda.time.format.DateTimeFormatter;
/*     */ import org.joda.time.format.ISODateTimeFormat;
/*     */ import org.joda.time.format.ISOPeriodFormat;
/*     */ import org.joda.time.format.PeriodFormatter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class StringConverter
/*     */   extends AbstractConverter
/*     */   implements InstantConverter, PartialConverter, DurationConverter, PeriodConverter, IntervalConverter
/*     */ {
/*  44 */   static final StringConverter INSTANCE = new StringConverter();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getInstantMillis(Object paramObject, Chronology paramChronology)
/*     */   {
/*  63 */     String str = (String)paramObject;
/*  64 */     DateTimeFormatter localDateTimeFormatter = ISODateTimeFormat.dateTimeParser();
/*  65 */     return localDateTimeFormatter.withChronology(paramChronology).parseMillis(str);
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
/*     */   public int[] getPartialValues(ReadablePartial paramReadablePartial, Object paramObject, Chronology paramChronology, DateTimeFormatter paramDateTimeFormatter)
/*     */   {
/*  84 */     if (paramDateTimeFormatter.getZone() != null) {
/*  85 */       paramChronology = paramChronology.withZone(paramDateTimeFormatter.getZone());
/*     */     }
/*  87 */     long l = paramDateTimeFormatter.withChronology(paramChronology).parseMillis((String)paramObject);
/*  88 */     return paramChronology.get(paramReadablePartial, l);
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
/*     */   public long getDurationMillis(Object paramObject)
/*     */   {
/* 102 */     String str1 = (String)paramObject;
/* 103 */     String str2 = str1;
/* 104 */     int i = str2.length();
/* 105 */     if ((i < 4) || ((str2.charAt(0) != 'P') && (str2.charAt(0) != 'p')) || ((str2.charAt(1) != 'T') && (str2.charAt(1) != 't')) || ((str2.charAt(i - 1) != 'S') && (str2.charAt(i - 1) != 's')))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 111 */       throw new IllegalArgumentException("Invalid format: \"" + str1 + '"');
/*     */     }
/* 113 */     str2 = str2.substring(2, i - 1);
/* 114 */     int j = -1;
/* 115 */     for (int k = 0; k < str2.length(); k++) {
/* 116 */       if (((str2.charAt(k) < '0') || (str2.charAt(k) > '9')) && ((k != 0) || (str2.charAt(0) != '-')))
/*     */       {
/*     */ 
/* 119 */         if ((k > 0) && (str2.charAt(k) == '.') && (j == -1))
/*     */         {
/* 121 */           j = k;
/*     */         } else
/* 123 */           throw new IllegalArgumentException("Invalid format: \"" + str1 + '"');
/*     */       }
/*     */     }
/* 126 */     long l1 = 0L;long l2 = 0L;
/* 127 */     if (j > 0) {
/* 128 */       l2 = Long.parseLong(str2.substring(0, j));
/* 129 */       str2 = str2.substring(j + 1);
/* 130 */       if (str2.length() != 3) {
/* 131 */         str2 = (str2 + "000").substring(0, 3);
/*     */       }
/* 133 */       l1 = Integer.parseInt(str2);
/*     */     } else {
/* 135 */       l2 = Long.parseLong(str2);
/*     */     }
/* 137 */     if (l2 < 0L) {
/* 138 */       return FieldUtils.safeAdd(FieldUtils.safeMultiply(l2, 1000), -l1);
/*     */     }
/* 140 */     return FieldUtils.safeAdd(FieldUtils.safeMultiply(l2, 1000), l1);
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
/*     */   public void setInto(ReadWritablePeriod paramReadWritablePeriod, Object paramObject, Chronology paramChronology)
/*     */   {
/* 156 */     String str = (String)paramObject;
/* 157 */     PeriodFormatter localPeriodFormatter = ISOPeriodFormat.standard();
/* 158 */     paramReadWritablePeriod.clear();
/* 159 */     int i = localPeriodFormatter.parseInto(paramReadWritablePeriod, str, 0);
/* 160 */     if (i < str.length()) {
/* 161 */       if (i < 0)
/*     */       {
/* 163 */         localPeriodFormatter.withParseType(paramReadWritablePeriod.getPeriodType()).parseMutablePeriod(str);
/*     */       }
/* 165 */       throw new IllegalArgumentException("Invalid format: \"" + str + '"');
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
/*     */   public void setInto(ReadWritableInterval paramReadWritableInterval, Object paramObject, Chronology paramChronology)
/*     */   {
/* 178 */     String str1 = (String)paramObject;
/*     */     
/* 180 */     int i = str1.indexOf('/');
/* 181 */     if (i < 0) {
/* 182 */       throw new IllegalArgumentException("Format requires a '/' separator: " + str1);
/*     */     }
/*     */     
/* 185 */     String str2 = str1.substring(0, i);
/* 186 */     if (str2.length() <= 0) {
/* 187 */       throw new IllegalArgumentException("Format invalid: " + str1);
/*     */     }
/* 189 */     String str3 = str1.substring(i + 1);
/* 190 */     if (str3.length() <= 0) {
/* 191 */       throw new IllegalArgumentException("Format invalid: " + str1);
/*     */     }
/*     */     
/* 194 */     DateTimeFormatter localDateTimeFormatter = ISODateTimeFormat.dateTimeParser();
/* 195 */     localDateTimeFormatter = localDateTimeFormatter.withChronology(paramChronology);
/* 196 */     PeriodFormatter localPeriodFormatter = ISOPeriodFormat.standard();
/* 197 */     long l1 = 0L;long l2 = 0L;
/* 198 */     Period localPeriod = null;
/* 199 */     Chronology localChronology = null;
/*     */     
/*     */ 
/* 202 */     int j = str2.charAt(0);
/* 203 */     DateTime localDateTime; if ((j == 80) || (j == 112)) {
/* 204 */       localPeriod = localPeriodFormatter.withParseType(getPeriodType(str2)).parsePeriod(str2);
/*     */     } else {
/* 206 */       localDateTime = localDateTimeFormatter.parseDateTime(str2);
/* 207 */       l1 = localDateTime.getMillis();
/* 208 */       localChronology = localDateTime.getChronology();
/*     */     }
/*     */     
/*     */ 
/* 212 */     j = str3.charAt(0);
/* 213 */     if ((j == 80) || (j == 112)) {
/* 214 */       if (localPeriod != null) {
/* 215 */         throw new IllegalArgumentException("Interval composed of two durations: " + str1);
/*     */       }
/* 217 */       localPeriod = localPeriodFormatter.withParseType(getPeriodType(str3)).parsePeriod(str3);
/* 218 */       paramChronology = paramChronology != null ? paramChronology : localChronology;
/* 219 */       l2 = paramChronology.add(localPeriod, l1, 1);
/*     */     } else {
/* 221 */       localDateTime = localDateTimeFormatter.parseDateTime(str3);
/* 222 */       l2 = localDateTime.getMillis();
/* 223 */       localChronology = localChronology != null ? localChronology : localDateTime.getChronology();
/* 224 */       paramChronology = paramChronology != null ? paramChronology : localChronology;
/* 225 */       if (localPeriod != null) {
/* 226 */         l1 = paramChronology.add(localPeriod, l2, -1);
/*     */       }
/*     */     }
/*     */     
/* 230 */     paramReadWritableInterval.setInterval(l1, l2);
/* 231 */     paramReadWritableInterval.setChronology(paramChronology);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Class getSupportedType()
/*     */   {
/* 241 */     return String.class;
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\convert\StringConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */