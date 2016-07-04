/*     */ package org.joda.time.chrono;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTimeField;
/*     */ import org.joda.time.DateTimeZone;
/*     */ import org.joda.time.DurationField;
/*     */ import org.joda.time.IllegalFieldValueException;
/*     */ import org.joda.time.Instant;
/*     */ import org.joda.time.ReadablePartial;
/*     */ import org.joda.time.field.BaseDateTimeField;
/*     */ import org.joda.time.field.BaseDurationField;
/*     */ import org.joda.time.format.DateTimeFormat;
/*     */ import org.joda.time.format.DateTimeFormatter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ZonedChronology
/*     */   extends AssembledChronology
/*     */ {
/*     */   private static final long serialVersionUID = -1079258847191166848L;
/*     */   
/*     */   public static ZonedChronology getInstance(Chronology paramChronology, DateTimeZone paramDateTimeZone)
/*     */   {
/*  56 */     if (paramChronology == null) {
/*  57 */       throw new IllegalArgumentException("Must supply a chronology");
/*     */     }
/*  59 */     paramChronology = paramChronology.withUTC();
/*  60 */     if (paramChronology == null) {
/*  61 */       throw new IllegalArgumentException("UTC chronology must not be null");
/*     */     }
/*  63 */     if (paramDateTimeZone == null) {
/*  64 */       throw new IllegalArgumentException("DateTimeZone must not be null");
/*     */     }
/*  66 */     return new ZonedChronology(paramChronology, paramDateTimeZone);
/*     */   }
/*     */   
/*     */ 
/*     */   static boolean useTimeArithmetic(DurationField paramDurationField)
/*     */   {
/*  72 */     return (paramDurationField != null) && (paramDurationField.getUnitMillis() < 43200000L);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private ZonedChronology(Chronology paramChronology, DateTimeZone paramDateTimeZone)
/*     */   {
/*  82 */     super(paramChronology, paramDateTimeZone);
/*     */   }
/*     */   
/*     */   public DateTimeZone getZone() {
/*  86 */     return (DateTimeZone)getParam();
/*     */   }
/*     */   
/*     */   public Chronology withUTC() {
/*  90 */     return getBase();
/*     */   }
/*     */   
/*     */   public Chronology withZone(DateTimeZone paramDateTimeZone) {
/*  94 */     if (paramDateTimeZone == null) {
/*  95 */       paramDateTimeZone = DateTimeZone.getDefault();
/*     */     }
/*  97 */     if (paramDateTimeZone == getParam()) {
/*  98 */       return this;
/*     */     }
/* 100 */     if (paramDateTimeZone == DateTimeZone.UTC) {
/* 101 */       return getBase();
/*     */     }
/* 103 */     return new ZonedChronology(getBase(), paramDateTimeZone);
/*     */   }
/*     */   
/*     */ 
/*     */   public long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */     throws IllegalArgumentException
/*     */   {
/* 110 */     return localToUTC(getBase().getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
/*     */     throws IllegalArgumentException
/*     */   {
/* 119 */     return localToUTC(getBase().getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getDateTimeMillis(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */     throws IllegalArgumentException
/*     */   {
/* 129 */     return localToUTC(getBase().getDateTimeMillis(paramLong + getZone().getOffset(paramLong), paramInt1, paramInt2, paramInt3, paramInt4));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private long localToUTC(long paramLong)
/*     */   {
/* 139 */     DateTimeZone localDateTimeZone = getZone();
/* 140 */     int i = localDateTimeZone.getOffsetFromLocal(paramLong);
/* 141 */     paramLong -= i;
/* 142 */     if (i != localDateTimeZone.getOffset(paramLong)) {
/* 143 */       throw new IllegalArgumentException("Illegal instant due to time zone offset transition: " + DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS").print(new Instant(paramLong)));
/*     */     }
/*     */     
/*     */ 
/* 147 */     return paramLong;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void assemble(AssembledChronology.Fields paramFields)
/*     */   {
/* 153 */     HashMap localHashMap = new HashMap();
/*     */     
/*     */ 
/*     */ 
/* 157 */     paramFields.eras = convertField(paramFields.eras, localHashMap);
/* 158 */     paramFields.centuries = convertField(paramFields.centuries, localHashMap);
/* 159 */     paramFields.years = convertField(paramFields.years, localHashMap);
/* 160 */     paramFields.months = convertField(paramFields.months, localHashMap);
/* 161 */     paramFields.weekyears = convertField(paramFields.weekyears, localHashMap);
/* 162 */     paramFields.weeks = convertField(paramFields.weeks, localHashMap);
/* 163 */     paramFields.days = convertField(paramFields.days, localHashMap);
/*     */     
/* 165 */     paramFields.halfdays = convertField(paramFields.halfdays, localHashMap);
/* 166 */     paramFields.hours = convertField(paramFields.hours, localHashMap);
/* 167 */     paramFields.minutes = convertField(paramFields.minutes, localHashMap);
/* 168 */     paramFields.seconds = convertField(paramFields.seconds, localHashMap);
/* 169 */     paramFields.millis = convertField(paramFields.millis, localHashMap);
/*     */     
/*     */ 
/*     */ 
/* 173 */     paramFields.year = convertField(paramFields.year, localHashMap);
/* 174 */     paramFields.yearOfEra = convertField(paramFields.yearOfEra, localHashMap);
/* 175 */     paramFields.yearOfCentury = convertField(paramFields.yearOfCentury, localHashMap);
/* 176 */     paramFields.centuryOfEra = convertField(paramFields.centuryOfEra, localHashMap);
/* 177 */     paramFields.era = convertField(paramFields.era, localHashMap);
/* 178 */     paramFields.dayOfWeek = convertField(paramFields.dayOfWeek, localHashMap);
/* 179 */     paramFields.dayOfMonth = convertField(paramFields.dayOfMonth, localHashMap);
/* 180 */     paramFields.dayOfYear = convertField(paramFields.dayOfYear, localHashMap);
/* 181 */     paramFields.monthOfYear = convertField(paramFields.monthOfYear, localHashMap);
/* 182 */     paramFields.weekOfWeekyear = convertField(paramFields.weekOfWeekyear, localHashMap);
/* 183 */     paramFields.weekyear = convertField(paramFields.weekyear, localHashMap);
/* 184 */     paramFields.weekyearOfCentury = convertField(paramFields.weekyearOfCentury, localHashMap);
/*     */     
/* 186 */     paramFields.millisOfSecond = convertField(paramFields.millisOfSecond, localHashMap);
/* 187 */     paramFields.millisOfDay = convertField(paramFields.millisOfDay, localHashMap);
/* 188 */     paramFields.secondOfMinute = convertField(paramFields.secondOfMinute, localHashMap);
/* 189 */     paramFields.secondOfDay = convertField(paramFields.secondOfDay, localHashMap);
/* 190 */     paramFields.minuteOfHour = convertField(paramFields.minuteOfHour, localHashMap);
/* 191 */     paramFields.minuteOfDay = convertField(paramFields.minuteOfDay, localHashMap);
/* 192 */     paramFields.hourOfDay = convertField(paramFields.hourOfDay, localHashMap);
/* 193 */     paramFields.hourOfHalfday = convertField(paramFields.hourOfHalfday, localHashMap);
/* 194 */     paramFields.clockhourOfDay = convertField(paramFields.clockhourOfDay, localHashMap);
/* 195 */     paramFields.clockhourOfHalfday = convertField(paramFields.clockhourOfHalfday, localHashMap);
/* 196 */     paramFields.halfdayOfDay = convertField(paramFields.halfdayOfDay, localHashMap);
/*     */   }
/*     */   
/*     */   private DurationField convertField(DurationField paramDurationField, HashMap paramHashMap) {
/* 200 */     if ((paramDurationField == null) || (!paramDurationField.isSupported())) {
/* 201 */       return paramDurationField;
/*     */     }
/* 203 */     if (paramHashMap.containsKey(paramDurationField)) {
/* 204 */       return (DurationField)paramHashMap.get(paramDurationField);
/*     */     }
/* 206 */     ZonedDurationField localZonedDurationField = new ZonedDurationField(paramDurationField, getZone());
/* 207 */     paramHashMap.put(paramDurationField, localZonedDurationField);
/* 208 */     return localZonedDurationField;
/*     */   }
/*     */   
/*     */   private DateTimeField convertField(DateTimeField paramDateTimeField, HashMap paramHashMap) {
/* 212 */     if ((paramDateTimeField == null) || (!paramDateTimeField.isSupported())) {
/* 213 */       return paramDateTimeField;
/*     */     }
/* 215 */     if (paramHashMap.containsKey(paramDateTimeField)) {
/* 216 */       return (DateTimeField)paramHashMap.get(paramDateTimeField);
/*     */     }
/* 218 */     ZonedDateTimeField localZonedDateTimeField = new ZonedDateTimeField(paramDateTimeField, getZone(), convertField(paramDateTimeField.getDurationField(), paramHashMap), convertField(paramDateTimeField.getRangeDurationField(), paramHashMap), convertField(paramDateTimeField.getLeapDurationField(), paramHashMap));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 223 */     paramHashMap.put(paramDateTimeField, localZonedDateTimeField);
/* 224 */     return localZonedDateTimeField;
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
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 237 */     if (this == paramObject) {
/* 238 */       return true;
/*     */     }
/* 240 */     if (!(paramObject instanceof ZonedChronology)) {
/* 241 */       return false;
/*     */     }
/* 243 */     ZonedChronology localZonedChronology = (ZonedChronology)paramObject;
/* 244 */     return (getBase().equals(localZonedChronology.getBase())) && (getZone().equals(localZonedChronology.getZone()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 256 */     return 326565 + getZone().hashCode() * 11 + getBase().hashCode() * 7;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 265 */     return "ZonedChronology[" + getBase() + ", " + getZone().getID() + ']';
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   static class ZonedDurationField
/*     */     extends BaseDurationField
/*     */   {
/*     */     private static final long serialVersionUID = -485345310999208286L;
/*     */     
/*     */     final DurationField iField;
/*     */     
/*     */     final boolean iTimeField;
/*     */     
/*     */     final DateTimeZone iZone;
/*     */     
/*     */ 
/*     */     ZonedDurationField(DurationField paramDurationField, DateTimeZone paramDateTimeZone)
/*     */     {
/* 284 */       super();
/* 285 */       if (!paramDurationField.isSupported()) {
/* 286 */         throw new IllegalArgumentException();
/*     */       }
/* 288 */       this.iField = paramDurationField;
/* 289 */       this.iTimeField = ZonedChronology.useTimeArithmetic(paramDurationField);
/* 290 */       this.iZone = paramDateTimeZone;
/*     */     }
/*     */     
/*     */     public boolean isPrecise() {
/* 294 */       return (this.iField.isPrecise()) && (this.iZone.isFixed()) ? true : this.iTimeField ? this.iField.isPrecise() : false;
/*     */     }
/*     */     
/*     */     public long getUnitMillis() {
/* 298 */       return this.iField.getUnitMillis();
/*     */     }
/*     */     
/*     */     public int getValue(long paramLong1, long paramLong2) {
/* 302 */       return this.iField.getValue(paramLong1, addOffset(paramLong2));
/*     */     }
/*     */     
/*     */     public long getValueAsLong(long paramLong1, long paramLong2) {
/* 306 */       return this.iField.getValueAsLong(paramLong1, addOffset(paramLong2));
/*     */     }
/*     */     
/*     */     public long getMillis(int paramInt, long paramLong) {
/* 310 */       return this.iField.getMillis(paramInt, addOffset(paramLong));
/*     */     }
/*     */     
/*     */     public long getMillis(long paramLong1, long paramLong2) {
/* 314 */       return this.iField.getMillis(paramLong1, addOffset(paramLong2));
/*     */     }
/*     */     
/*     */     public long add(long paramLong, int paramInt) {
/* 318 */       int i = getOffsetToAdd(paramLong);
/* 319 */       paramLong = this.iField.add(paramLong + i, paramInt);
/* 320 */       return paramLong - (this.iTimeField ? i : getOffsetFromLocalToSubtract(paramLong));
/*     */     }
/*     */     
/*     */     public long add(long paramLong1, long paramLong2) {
/* 324 */       int i = getOffsetToAdd(paramLong1);
/* 325 */       paramLong1 = this.iField.add(paramLong1 + i, paramLong2);
/* 326 */       return paramLong1 - (this.iTimeField ? i : getOffsetFromLocalToSubtract(paramLong1));
/*     */     }
/*     */     
/*     */     public int getDifference(long paramLong1, long paramLong2) {
/* 330 */       int i = getOffsetToAdd(paramLong2);
/* 331 */       return this.iField.getDifference(paramLong1 + (this.iTimeField ? i : getOffsetToAdd(paramLong1)), paramLong2 + i);
/*     */     }
/*     */     
/*     */ 
/*     */     public long getDifferenceAsLong(long paramLong1, long paramLong2)
/*     */     {
/* 337 */       int i = getOffsetToAdd(paramLong2);
/* 338 */       return this.iField.getDifferenceAsLong(paramLong1 + (this.iTimeField ? i : getOffsetToAdd(paramLong1)), paramLong2 + i);
/*     */     }
/*     */     
/*     */ 
/*     */     private int getOffsetToAdd(long paramLong)
/*     */     {
/* 344 */       int i = this.iZone.getOffset(paramLong);
/* 345 */       long l = paramLong + i;
/*     */       
/* 347 */       if (((paramLong ^ l) < 0L) && ((paramLong ^ i) >= 0L)) {
/* 348 */         throw new ArithmeticException("Adding time zone offset caused overflow");
/*     */       }
/* 350 */       return i;
/*     */     }
/*     */     
/*     */     private int getOffsetFromLocalToSubtract(long paramLong) {
/* 354 */       int i = this.iZone.getOffsetFromLocal(paramLong);
/* 355 */       long l = paramLong - i;
/*     */       
/* 357 */       if (((paramLong ^ l) < 0L) && ((paramLong ^ i) < 0L)) {
/* 358 */         throw new ArithmeticException("Subtracting time zone offset caused overflow");
/*     */       }
/* 360 */       return i;
/*     */     }
/*     */     
/*     */     private long addOffset(long paramLong) {
/* 364 */       return this.iZone.convertUTCToLocal(paramLong);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   static final class ZonedDateTimeField
/*     */     extends BaseDateTimeField
/*     */   {
/*     */     private static final long serialVersionUID = -3968986277775529794L;
/*     */     
/*     */     final DateTimeField iField;
/*     */     
/*     */     final DateTimeZone iZone;
/*     */     
/*     */     final DurationField iDurationField;
/*     */     
/*     */     final boolean iTimeField;
/*     */     
/*     */     final DurationField iRangeDurationField;
/*     */     
/*     */     final DurationField iLeapDurationField;
/*     */     
/*     */ 
/*     */     ZonedDateTimeField(DateTimeField paramDateTimeField, DateTimeZone paramDateTimeZone, DurationField paramDurationField1, DurationField paramDurationField2, DurationField paramDurationField3)
/*     */     {
/* 389 */       super();
/* 390 */       if (!paramDateTimeField.isSupported()) {
/* 391 */         throw new IllegalArgumentException();
/*     */       }
/* 393 */       this.iField = paramDateTimeField;
/* 394 */       this.iZone = paramDateTimeZone;
/* 395 */       this.iDurationField = paramDurationField1;
/* 396 */       this.iTimeField = ZonedChronology.useTimeArithmetic(paramDurationField1);
/* 397 */       this.iRangeDurationField = paramDurationField2;
/* 398 */       this.iLeapDurationField = paramDurationField3;
/*     */     }
/*     */     
/*     */     public boolean isLenient() {
/* 402 */       return this.iField.isLenient();
/*     */     }
/*     */     
/*     */     public int get(long paramLong) {
/* 406 */       long l = this.iZone.convertUTCToLocal(paramLong);
/* 407 */       return this.iField.get(l);
/*     */     }
/*     */     
/*     */     public String getAsText(long paramLong, Locale paramLocale) {
/* 411 */       long l = this.iZone.convertUTCToLocal(paramLong);
/* 412 */       return this.iField.getAsText(l, paramLocale);
/*     */     }
/*     */     
/*     */     public String getAsShortText(long paramLong, Locale paramLocale) {
/* 416 */       long l = this.iZone.convertUTCToLocal(paramLong);
/* 417 */       return this.iField.getAsShortText(l, paramLocale);
/*     */     }
/*     */     
/*     */     public String getAsText(int paramInt, Locale paramLocale) {
/* 421 */       return this.iField.getAsText(paramInt, paramLocale);
/*     */     }
/*     */     
/*     */     public String getAsShortText(int paramInt, Locale paramLocale) {
/* 425 */       return this.iField.getAsShortText(paramInt, paramLocale);
/*     */     }
/*     */     
/*     */     public long add(long paramLong, int paramInt) {
/* 429 */       if (this.iTimeField) {
/* 430 */         int i = getOffsetToAdd(paramLong);
/* 431 */         long l2 = this.iField.add(paramLong + i, paramInt);
/* 432 */         return l2 - i;
/*     */       }
/* 434 */       long l1 = this.iZone.convertUTCToLocal(paramLong);
/* 435 */       l1 = this.iField.add(l1, paramInt);
/* 436 */       return this.iZone.convertLocalToUTC(l1, false);
/*     */     }
/*     */     
/*     */     public long add(long paramLong1, long paramLong2)
/*     */     {
/* 441 */       if (this.iTimeField) {
/* 442 */         int i = getOffsetToAdd(paramLong1);
/* 443 */         long l2 = this.iField.add(paramLong1 + i, paramLong2);
/* 444 */         return l2 - i;
/*     */       }
/* 446 */       long l1 = this.iZone.convertUTCToLocal(paramLong1);
/* 447 */       l1 = this.iField.add(l1, paramLong2);
/* 448 */       return this.iZone.convertLocalToUTC(l1, false);
/*     */     }
/*     */     
/*     */     public long addWrapField(long paramLong, int paramInt)
/*     */     {
/* 453 */       if (this.iTimeField) {
/* 454 */         int i = getOffsetToAdd(paramLong);
/* 455 */         long l2 = this.iField.addWrapField(paramLong + i, paramInt);
/* 456 */         return l2 - i;
/*     */       }
/* 458 */       long l1 = this.iZone.convertUTCToLocal(paramLong);
/* 459 */       l1 = this.iField.addWrapField(l1, paramInt);
/* 460 */       return this.iZone.convertLocalToUTC(l1, false);
/*     */     }
/*     */     
/*     */     public long set(long paramLong, int paramInt)
/*     */     {
/* 465 */       long l1 = this.iZone.convertUTCToLocal(paramLong);
/* 466 */       l1 = this.iField.set(l1, paramInt);
/* 467 */       long l2 = this.iZone.convertLocalToUTC(l1, false);
/* 468 */       if (get(l2) != paramInt) {
/* 469 */         throw new IllegalFieldValueException(this.iField.getType(), new Integer(paramInt), "Illegal instant due to time zone offset transition: " + DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS").print(new Instant(l1)) + " (" + this.iZone.getID() + ")");
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 474 */       return l2;
/*     */     }
/*     */     
/*     */     public long set(long paramLong, String paramString, Locale paramLocale)
/*     */     {
/* 479 */       long l = this.iZone.convertUTCToLocal(paramLong);
/* 480 */       l = this.iField.set(l, paramString, paramLocale);
/* 481 */       return this.iZone.convertLocalToUTC(l, false);
/*     */     }
/*     */     
/*     */     public int getDifference(long paramLong1, long paramLong2) {
/* 485 */       int i = getOffsetToAdd(paramLong2);
/* 486 */       return this.iField.getDifference(paramLong1 + (this.iTimeField ? i : getOffsetToAdd(paramLong1)), paramLong2 + i);
/*     */     }
/*     */     
/*     */ 
/*     */     public long getDifferenceAsLong(long paramLong1, long paramLong2)
/*     */     {
/* 492 */       int i = getOffsetToAdd(paramLong2);
/* 493 */       return this.iField.getDifferenceAsLong(paramLong1 + (this.iTimeField ? i : getOffsetToAdd(paramLong1)), paramLong2 + i);
/*     */     }
/*     */     
/*     */ 
/*     */     public final DurationField getDurationField()
/*     */     {
/* 499 */       return this.iDurationField;
/*     */     }
/*     */     
/*     */     public final DurationField getRangeDurationField() {
/* 503 */       return this.iRangeDurationField;
/*     */     }
/*     */     
/*     */     public boolean isLeap(long paramLong) {
/* 507 */       long l = this.iZone.convertUTCToLocal(paramLong);
/* 508 */       return this.iField.isLeap(l);
/*     */     }
/*     */     
/*     */     public int getLeapAmount(long paramLong) {
/* 512 */       long l = this.iZone.convertUTCToLocal(paramLong);
/* 513 */       return this.iField.getLeapAmount(l);
/*     */     }
/*     */     
/*     */     public final DurationField getLeapDurationField() {
/* 517 */       return this.iLeapDurationField;
/*     */     }
/*     */     
/*     */     public long roundFloor(long paramLong) {
/* 521 */       if (this.iTimeField) {
/* 522 */         int i = getOffsetToAdd(paramLong);
/* 523 */         paramLong = this.iField.roundFloor(paramLong + i);
/* 524 */         return paramLong - i;
/*     */       }
/* 526 */       long l = this.iZone.convertUTCToLocal(paramLong);
/* 527 */       l = this.iField.roundFloor(l);
/* 528 */       return this.iZone.convertLocalToUTC(l, false);
/*     */     }
/*     */     
/*     */     public long roundCeiling(long paramLong)
/*     */     {
/* 533 */       if (this.iTimeField) {
/* 534 */         int i = getOffsetToAdd(paramLong);
/* 535 */         paramLong = this.iField.roundCeiling(paramLong + i);
/* 536 */         return paramLong - i;
/*     */       }
/* 538 */       long l = this.iZone.convertUTCToLocal(paramLong);
/* 539 */       l = this.iField.roundCeiling(l);
/* 540 */       return this.iZone.convertLocalToUTC(l, false);
/*     */     }
/*     */     
/*     */     public long remainder(long paramLong)
/*     */     {
/* 545 */       long l = this.iZone.convertUTCToLocal(paramLong);
/* 546 */       return this.iField.remainder(l);
/*     */     }
/*     */     
/*     */     public int getMinimumValue() {
/* 550 */       return this.iField.getMinimumValue();
/*     */     }
/*     */     
/*     */     public int getMinimumValue(long paramLong) {
/* 554 */       long l = this.iZone.convertUTCToLocal(paramLong);
/* 555 */       return this.iField.getMinimumValue(l);
/*     */     }
/*     */     
/*     */     public int getMinimumValue(ReadablePartial paramReadablePartial) {
/* 559 */       return this.iField.getMinimumValue(paramReadablePartial);
/*     */     }
/*     */     
/*     */     public int getMinimumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt) {
/* 563 */       return this.iField.getMinimumValue(paramReadablePartial, paramArrayOfInt);
/*     */     }
/*     */     
/*     */     public int getMaximumValue() {
/* 567 */       return this.iField.getMaximumValue();
/*     */     }
/*     */     
/*     */     public int getMaximumValue(long paramLong) {
/* 571 */       long l = this.iZone.convertUTCToLocal(paramLong);
/* 572 */       return this.iField.getMaximumValue(l);
/*     */     }
/*     */     
/*     */     public int getMaximumValue(ReadablePartial paramReadablePartial) {
/* 576 */       return this.iField.getMaximumValue(paramReadablePartial);
/*     */     }
/*     */     
/*     */     public int getMaximumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt) {
/* 580 */       return this.iField.getMaximumValue(paramReadablePartial, paramArrayOfInt);
/*     */     }
/*     */     
/*     */     public int getMaximumTextLength(Locale paramLocale) {
/* 584 */       return this.iField.getMaximumTextLength(paramLocale);
/*     */     }
/*     */     
/*     */     public int getMaximumShortTextLength(Locale paramLocale) {
/* 588 */       return this.iField.getMaximumShortTextLength(paramLocale);
/*     */     }
/*     */     
/*     */     private int getOffsetToAdd(long paramLong) {
/* 592 */       int i = this.iZone.getOffset(paramLong);
/* 593 */       long l = paramLong + i;
/*     */       
/* 595 */       if (((paramLong ^ l) < 0L) && ((paramLong ^ i) >= 0L)) {
/* 596 */         throw new ArithmeticException("Adding time zone offset caused overflow");
/*     */       }
/* 598 */       return i;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\ZonedChronology.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */