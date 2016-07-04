/*     */ package org.joda.time.chrono;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTimeField;
/*     */ import org.joda.time.DateTimeFieldType;
/*     */ import org.joda.time.DateTimeZone;
/*     */ import org.joda.time.DurationField;
/*     */ import org.joda.time.DurationFieldType;
/*     */ import org.joda.time.IllegalFieldValueException;
/*     */ import org.joda.time.ReadablePartial;
/*     */ import org.joda.time.ReadablePeriod;
/*     */ import org.joda.time.field.FieldUtils;
/*     */ import org.joda.time.field.UnsupportedDateTimeField;
/*     */ import org.joda.time.field.UnsupportedDurationField;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BaseChronology
/*     */   extends Chronology
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7310865996721419676L;
/*     */   
/*     */   public abstract DateTimeZone getZone();
/*     */   
/*     */   public abstract Chronology withUTC();
/*     */   
/*     */   public abstract Chronology withZone(DateTimeZone paramDateTimeZone);
/*     */   
/*     */   public long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */     throws IllegalArgumentException
/*     */   {
/* 102 */     long l = year().set(0L, paramInt1);
/* 103 */     l = monthOfYear().set(l, paramInt2);
/* 104 */     l = dayOfMonth().set(l, paramInt3);
/* 105 */     return millisOfDay().set(l, paramInt4);
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
/*     */   public long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
/*     */     throws IllegalArgumentException
/*     */   {
/* 132 */     long l = year().set(0L, paramInt1);
/* 133 */     l = monthOfYear().set(l, paramInt2);
/* 134 */     l = dayOfMonth().set(l, paramInt3);
/* 135 */     l = hourOfDay().set(l, paramInt4);
/* 136 */     l = minuteOfHour().set(l, paramInt5);
/* 137 */     l = secondOfMinute().set(l, paramInt6);
/* 138 */     return millisOfSecond().set(l, paramInt7);
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
/*     */   public long getDateTimeMillis(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */     throws IllegalArgumentException
/*     */   {
/* 163 */     paramLong = hourOfDay().set(paramLong, paramInt1);
/* 164 */     paramLong = minuteOfHour().set(paramLong, paramInt2);
/* 165 */     paramLong = secondOfMinute().set(paramLong, paramInt3);
/* 166 */     return millisOfSecond().set(paramLong, paramInt4);
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
/*     */   public void validate(ReadablePartial paramReadablePartial, int[] paramArrayOfInt)
/*     */   {
/* 183 */     int i = paramReadablePartial.size();
/* 184 */     int k; DateTimeField localDateTimeField; for (int j = 0; j < i; j++) {
/* 185 */       k = paramArrayOfInt[j];
/* 186 */       localDateTimeField = paramReadablePartial.getField(j);
/* 187 */       if (k < localDateTimeField.getMinimumValue()) {
/* 188 */         throw new IllegalFieldValueException(localDateTimeField.getType(), new Integer(k), new Integer(localDateTimeField.getMinimumValue()), null);
/*     */       }
/*     */       
/*     */ 
/* 192 */       if (k > localDateTimeField.getMaximumValue()) {
/* 193 */         throw new IllegalFieldValueException(localDateTimeField.getType(), new Integer(k), null, new Integer(localDateTimeField.getMaximumValue()));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 199 */     for (j = 0; j < i; j++) {
/* 200 */       k = paramArrayOfInt[j];
/* 201 */       localDateTimeField = paramReadablePartial.getField(j);
/* 202 */       if (k < localDateTimeField.getMinimumValue(paramReadablePartial, paramArrayOfInt)) {
/* 203 */         throw new IllegalFieldValueException(localDateTimeField.getType(), new Integer(k), new Integer(localDateTimeField.getMinimumValue(paramReadablePartial, paramArrayOfInt)), null);
/*     */       }
/*     */       
/*     */ 
/* 207 */       if (k > localDateTimeField.getMaximumValue(paramReadablePartial, paramArrayOfInt)) {
/* 208 */         throw new IllegalFieldValueException(localDateTimeField.getType(), new Integer(k), null, new Integer(localDateTimeField.getMaximumValue(paramReadablePartial, paramArrayOfInt)));
/*     */       }
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
/*     */   public int[] get(ReadablePartial paramReadablePartial, long paramLong)
/*     */   {
/* 223 */     int i = paramReadablePartial.size();
/* 224 */     int[] arrayOfInt = new int[i];
/* 225 */     for (int j = 0; j < i; j++) {
/* 226 */       arrayOfInt[j] = paramReadablePartial.getFieldType(j).getField(this).get(paramLong);
/*     */     }
/* 228 */     return arrayOfInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long set(ReadablePartial paramReadablePartial, long paramLong)
/*     */   {
/* 239 */     int i = 0; for (int j = paramReadablePartial.size(); i < j; i++) {
/* 240 */       paramLong = paramReadablePartial.getFieldType(i).getField(this).set(paramLong, paramReadablePartial.getValue(i));
/*     */     }
/* 242 */     return paramLong;
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
/*     */   public int[] get(ReadablePeriod paramReadablePeriod, long paramLong1, long paramLong2)
/*     */   {
/* 255 */     int i = paramReadablePeriod.size();
/* 256 */     int[] arrayOfInt = new int[i];
/* 257 */     if (paramLong1 != paramLong2) {
/* 258 */       for (int j = 0; j < i; j++) {
/* 259 */         DurationField localDurationField = paramReadablePeriod.getFieldType(j).getField(this);
/* 260 */         int k = localDurationField.getDifference(paramLong2, paramLong1);
/* 261 */         paramLong1 = localDurationField.add(paramLong1, k);
/* 262 */         arrayOfInt[j] = k;
/*     */       }
/*     */     }
/* 265 */     return arrayOfInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int[] get(ReadablePeriod paramReadablePeriod, long paramLong)
/*     */   {
/* 276 */     int i = paramReadablePeriod.size();
/* 277 */     int[] arrayOfInt = new int[i];
/* 278 */     if (paramLong != 0L) {
/* 279 */       long l = 0L;
/* 280 */       for (int j = 0; j < i; j++) {
/* 281 */         DurationField localDurationField = paramReadablePeriod.getFieldType(j).getField(this);
/* 282 */         if (localDurationField.isPrecise()) {
/* 283 */           int k = localDurationField.getDifference(paramLong, l);
/* 284 */           l = localDurationField.add(l, k);
/* 285 */           arrayOfInt[j] = k;
/*     */         }
/*     */       }
/*     */     }
/* 289 */     return arrayOfInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long add(ReadablePeriod paramReadablePeriod, long paramLong, int paramInt)
/*     */   {
/* 301 */     if ((paramInt != 0) && (paramReadablePeriod != null)) {
/* 302 */       int i = 0; for (int j = paramReadablePeriod.size(); i < j; i++) {
/* 303 */         long l = paramReadablePeriod.getValue(i);
/* 304 */         if (l != 0L) {
/* 305 */           paramLong = paramReadablePeriod.getFieldType(i).getField(this).add(paramLong, l * paramInt);
/*     */         }
/*     */       }
/*     */     }
/* 309 */     return paramLong;
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
/*     */   public long add(long paramLong1, long paramLong2, int paramInt)
/*     */   {
/* 322 */     if ((paramLong2 == 0L) || (paramInt == 0)) {
/* 323 */       return paramLong1;
/*     */     }
/* 325 */     long l = FieldUtils.safeMultiply(paramLong2, paramInt);
/* 326 */     return FieldUtils.safeAdd(paramLong1, l);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DurationField millis()
/*     */   {
/* 337 */     return UnsupportedDurationField.getInstance(DurationFieldType.millis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField millisOfSecond()
/*     */   {
/* 346 */     return UnsupportedDateTimeField.getInstance(DateTimeFieldType.millisOfSecond(), millis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField millisOfDay()
/*     */   {
/* 355 */     return UnsupportedDateTimeField.getInstance(DateTimeFieldType.millisOfDay(), millis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DurationField seconds()
/*     */   {
/* 366 */     return UnsupportedDurationField.getInstance(DurationFieldType.seconds());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField secondOfMinute()
/*     */   {
/* 375 */     return UnsupportedDateTimeField.getInstance(DateTimeFieldType.secondOfMinute(), seconds());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField secondOfDay()
/*     */   {
/* 384 */     return UnsupportedDateTimeField.getInstance(DateTimeFieldType.secondOfDay(), seconds());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DurationField minutes()
/*     */   {
/* 395 */     return UnsupportedDurationField.getInstance(DurationFieldType.minutes());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField minuteOfHour()
/*     */   {
/* 404 */     return UnsupportedDateTimeField.getInstance(DateTimeFieldType.minuteOfHour(), minutes());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField minuteOfDay()
/*     */   {
/* 413 */     return UnsupportedDateTimeField.getInstance(DateTimeFieldType.minuteOfDay(), minutes());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DurationField hours()
/*     */   {
/* 424 */     return UnsupportedDurationField.getInstance(DurationFieldType.hours());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField hourOfDay()
/*     */   {
/* 433 */     return UnsupportedDateTimeField.getInstance(DateTimeFieldType.hourOfDay(), hours());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField clockhourOfDay()
/*     */   {
/* 442 */     return UnsupportedDateTimeField.getInstance(DateTimeFieldType.clockhourOfDay(), hours());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DurationField halfdays()
/*     */   {
/* 453 */     return UnsupportedDurationField.getInstance(DurationFieldType.halfdays());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField hourOfHalfday()
/*     */   {
/* 462 */     return UnsupportedDateTimeField.getInstance(DateTimeFieldType.hourOfHalfday(), hours());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField clockhourOfHalfday()
/*     */   {
/* 471 */     return UnsupportedDateTimeField.getInstance(DateTimeFieldType.clockhourOfHalfday(), hours());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField halfdayOfDay()
/*     */   {
/* 480 */     return UnsupportedDateTimeField.getInstance(DateTimeFieldType.halfdayOfDay(), halfdays());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DurationField days()
/*     */   {
/* 491 */     return UnsupportedDurationField.getInstance(DurationFieldType.days());
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
/*     */   public DateTimeField dayOfWeek()
/*     */   {
/* 504 */     return UnsupportedDateTimeField.getInstance(DateTimeFieldType.dayOfWeek(), days());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField dayOfMonth()
/*     */   {
/* 513 */     return UnsupportedDateTimeField.getInstance(DateTimeFieldType.dayOfMonth(), days());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField dayOfYear()
/*     */   {
/* 522 */     return UnsupportedDateTimeField.getInstance(DateTimeFieldType.dayOfYear(), days());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DurationField weeks()
/*     */   {
/* 533 */     return UnsupportedDurationField.getInstance(DurationFieldType.weeks());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField weekOfWeekyear()
/*     */   {
/* 542 */     return UnsupportedDateTimeField.getInstance(DateTimeFieldType.weekOfWeekyear(), weeks());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DurationField weekyears()
/*     */   {
/* 553 */     return UnsupportedDurationField.getInstance(DurationFieldType.weekyears());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField weekyear()
/*     */   {
/* 562 */     return UnsupportedDateTimeField.getInstance(DateTimeFieldType.weekyear(), weekyears());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField weekyearOfCentury()
/*     */   {
/* 571 */     return UnsupportedDateTimeField.getInstance(DateTimeFieldType.weekyearOfCentury(), weekyears());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DurationField months()
/*     */   {
/* 582 */     return UnsupportedDurationField.getInstance(DurationFieldType.months());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField monthOfYear()
/*     */   {
/* 591 */     return UnsupportedDateTimeField.getInstance(DateTimeFieldType.monthOfYear(), months());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DurationField years()
/*     */   {
/* 602 */     return UnsupportedDurationField.getInstance(DurationFieldType.years());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField year()
/*     */   {
/* 611 */     return UnsupportedDateTimeField.getInstance(DateTimeFieldType.year(), years());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField yearOfEra()
/*     */   {
/* 620 */     return UnsupportedDateTimeField.getInstance(DateTimeFieldType.yearOfEra(), years());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField yearOfCentury()
/*     */   {
/* 629 */     return UnsupportedDateTimeField.getInstance(DateTimeFieldType.yearOfCentury(), years());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DurationField centuries()
/*     */   {
/* 640 */     return UnsupportedDurationField.getInstance(DurationFieldType.centuries());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField centuryOfEra()
/*     */   {
/* 649 */     return UnsupportedDateTimeField.getInstance(DateTimeFieldType.centuryOfEra(), centuries());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DurationField eras()
/*     */   {
/* 660 */     return UnsupportedDurationField.getInstance(DurationFieldType.eras());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField era()
/*     */   {
/* 669 */     return UnsupportedDateTimeField.getInstance(DateTimeFieldType.era(), eras());
/*     */   }
/*     */   
/*     */   public abstract String toString();
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\BaseChronology.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */