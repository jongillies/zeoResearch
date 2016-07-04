/*     */ package org.joda.time.chrono;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTime;
/*     */ import org.joda.time.DateTimeField;
/*     */ import org.joda.time.DateTimeZone;
/*     */ import org.joda.time.DurationField;
/*     */ import org.joda.time.MutableDateTime;
/*     */ import org.joda.time.ReadableDateTime;
/*     */ import org.joda.time.field.DecoratedDateTimeField;
/*     */ import org.joda.time.field.DecoratedDurationField;
/*     */ import org.joda.time.field.FieldUtils;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class LimitChronology
/*     */   extends AssembledChronology
/*     */ {
/*     */   private static final long serialVersionUID = 7670866536893052522L;
/*     */   final DateTime iLowerLimit;
/*     */   final DateTime iUpperLimit;
/*     */   private transient LimitChronology iWithUTC;
/*     */   
/*     */   public static LimitChronology getInstance(Chronology paramChronology, ReadableDateTime paramReadableDateTime1, ReadableDateTime paramReadableDateTime2)
/*     */   {
/*  67 */     if (paramChronology == null) {
/*  68 */       throw new IllegalArgumentException("Must supply a chronology");
/*     */     }
/*     */     
/*  71 */     paramReadableDateTime1 = paramReadableDateTime1 == null ? null : paramReadableDateTime1.toDateTime();
/*  72 */     paramReadableDateTime2 = paramReadableDateTime2 == null ? null : paramReadableDateTime2.toDateTime();
/*     */     
/*  74 */     if ((paramReadableDateTime1 != null) && (paramReadableDateTime2 != null) && 
/*  75 */       (!paramReadableDateTime1.isBefore(paramReadableDateTime2))) {
/*  76 */       throw new IllegalArgumentException("The lower limit must be come before than the upper limit");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  81 */     return new LimitChronology(paramChronology, (DateTime)paramReadableDateTime1, (DateTime)paramReadableDateTime2);
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
/*     */   private LimitChronology(Chronology paramChronology, DateTime paramDateTime1, DateTime paramDateTime2)
/*     */   {
/*  99 */     super(paramChronology, null);
/*     */     
/* 101 */     this.iLowerLimit = paramDateTime1;
/* 102 */     this.iUpperLimit = paramDateTime2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTime getLowerLimit()
/*     */   {
/* 111 */     return this.iLowerLimit;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTime getUpperLimit()
/*     */   {
/* 120 */     return this.iUpperLimit;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Chronology withUTC()
/*     */   {
/* 129 */     return withZone(DateTimeZone.UTC);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Chronology withZone(DateTimeZone paramDateTimeZone)
/*     */   {
/* 138 */     if (paramDateTimeZone == null) {
/* 139 */       paramDateTimeZone = DateTimeZone.getDefault();
/*     */     }
/* 141 */     if (paramDateTimeZone == getZone()) {
/* 142 */       return this;
/*     */     }
/*     */     
/* 145 */     if ((paramDateTimeZone == DateTimeZone.UTC) && (this.iWithUTC != null)) {
/* 146 */       return this.iWithUTC;
/*     */     }
/*     */     
/* 149 */     DateTime localDateTime = this.iLowerLimit;
/* 150 */     if (localDateTime != null) {
/* 151 */       localObject1 = localDateTime.toMutableDateTime();
/* 152 */       ((MutableDateTime)localObject1).setZoneRetainFields(paramDateTimeZone);
/* 153 */       localDateTime = ((MutableDateTime)localObject1).toDateTime();
/*     */     }
/*     */     
/* 156 */     Object localObject1 = this.iUpperLimit;
/* 157 */     if (localObject1 != null) {
/* 158 */       localObject2 = ((DateTime)localObject1).toMutableDateTime();
/* 159 */       ((MutableDateTime)localObject2).setZoneRetainFields(paramDateTimeZone);
/* 160 */       localObject1 = ((MutableDateTime)localObject2).toDateTime();
/*     */     }
/*     */     
/* 163 */     Object localObject2 = getInstance(getBase().withZone(paramDateTimeZone), localDateTime, (ReadableDateTime)localObject1);
/*     */     
/*     */ 
/* 166 */     if (paramDateTimeZone == DateTimeZone.UTC) {
/* 167 */       this.iWithUTC = ((LimitChronology)localObject2);
/*     */     }
/*     */     
/* 170 */     return (Chronology)localObject2;
/*     */   }
/*     */   
/*     */ 
/*     */   public long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */     throws IllegalArgumentException
/*     */   {
/* 177 */     long l = getBase().getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4);
/* 178 */     checkLimits(l, "resulting");
/* 179 */     return l;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
/*     */     throws IllegalArgumentException
/*     */   {
/* 187 */     long l = getBase().getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
/*     */     
/*     */ 
/* 190 */     checkLimits(l, "resulting");
/* 191 */     return l;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public long getDateTimeMillis(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */     throws IllegalArgumentException
/*     */   {
/* 199 */     checkLimits(paramLong, null);
/* 200 */     paramLong = getBase().getDateTimeMillis(paramLong, paramInt1, paramInt2, paramInt3, paramInt4);
/*     */     
/* 202 */     checkLimits(paramLong, "resulting");
/* 203 */     return paramLong;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void assemble(AssembledChronology.Fields paramFields)
/*     */   {
/* 209 */     HashMap localHashMap = new HashMap();
/*     */     
/*     */ 
/*     */ 
/* 213 */     paramFields.eras = convertField(paramFields.eras, localHashMap);
/* 214 */     paramFields.centuries = convertField(paramFields.centuries, localHashMap);
/* 215 */     paramFields.years = convertField(paramFields.years, localHashMap);
/* 216 */     paramFields.months = convertField(paramFields.months, localHashMap);
/* 217 */     paramFields.weekyears = convertField(paramFields.weekyears, localHashMap);
/* 218 */     paramFields.weeks = convertField(paramFields.weeks, localHashMap);
/* 219 */     paramFields.days = convertField(paramFields.days, localHashMap);
/*     */     
/* 221 */     paramFields.halfdays = convertField(paramFields.halfdays, localHashMap);
/* 222 */     paramFields.hours = convertField(paramFields.hours, localHashMap);
/* 223 */     paramFields.minutes = convertField(paramFields.minutes, localHashMap);
/* 224 */     paramFields.seconds = convertField(paramFields.seconds, localHashMap);
/* 225 */     paramFields.millis = convertField(paramFields.millis, localHashMap);
/*     */     
/*     */ 
/*     */ 
/* 229 */     paramFields.year = convertField(paramFields.year, localHashMap);
/* 230 */     paramFields.yearOfEra = convertField(paramFields.yearOfEra, localHashMap);
/* 231 */     paramFields.yearOfCentury = convertField(paramFields.yearOfCentury, localHashMap);
/* 232 */     paramFields.centuryOfEra = convertField(paramFields.centuryOfEra, localHashMap);
/* 233 */     paramFields.era = convertField(paramFields.era, localHashMap);
/* 234 */     paramFields.dayOfWeek = convertField(paramFields.dayOfWeek, localHashMap);
/* 235 */     paramFields.dayOfMonth = convertField(paramFields.dayOfMonth, localHashMap);
/* 236 */     paramFields.dayOfYear = convertField(paramFields.dayOfYear, localHashMap);
/* 237 */     paramFields.monthOfYear = convertField(paramFields.monthOfYear, localHashMap);
/* 238 */     paramFields.weekOfWeekyear = convertField(paramFields.weekOfWeekyear, localHashMap);
/* 239 */     paramFields.weekyear = convertField(paramFields.weekyear, localHashMap);
/* 240 */     paramFields.weekyearOfCentury = convertField(paramFields.weekyearOfCentury, localHashMap);
/*     */     
/* 242 */     paramFields.millisOfSecond = convertField(paramFields.millisOfSecond, localHashMap);
/* 243 */     paramFields.millisOfDay = convertField(paramFields.millisOfDay, localHashMap);
/* 244 */     paramFields.secondOfMinute = convertField(paramFields.secondOfMinute, localHashMap);
/* 245 */     paramFields.secondOfDay = convertField(paramFields.secondOfDay, localHashMap);
/* 246 */     paramFields.minuteOfHour = convertField(paramFields.minuteOfHour, localHashMap);
/* 247 */     paramFields.minuteOfDay = convertField(paramFields.minuteOfDay, localHashMap);
/* 248 */     paramFields.hourOfDay = convertField(paramFields.hourOfDay, localHashMap);
/* 249 */     paramFields.hourOfHalfday = convertField(paramFields.hourOfHalfday, localHashMap);
/* 250 */     paramFields.clockhourOfDay = convertField(paramFields.clockhourOfDay, localHashMap);
/* 251 */     paramFields.clockhourOfHalfday = convertField(paramFields.clockhourOfHalfday, localHashMap);
/* 252 */     paramFields.halfdayOfDay = convertField(paramFields.halfdayOfDay, localHashMap);
/*     */   }
/*     */   
/*     */   private DurationField convertField(DurationField paramDurationField, HashMap paramHashMap) {
/* 256 */     if ((paramDurationField == null) || (!paramDurationField.isSupported())) {
/* 257 */       return paramDurationField;
/*     */     }
/* 259 */     if (paramHashMap.containsKey(paramDurationField)) {
/* 260 */       return (DurationField)paramHashMap.get(paramDurationField);
/*     */     }
/* 262 */     LimitDurationField localLimitDurationField = new LimitDurationField(paramDurationField);
/* 263 */     paramHashMap.put(paramDurationField, localLimitDurationField);
/* 264 */     return localLimitDurationField;
/*     */   }
/*     */   
/*     */   private DateTimeField convertField(DateTimeField paramDateTimeField, HashMap paramHashMap) {
/* 268 */     if ((paramDateTimeField == null) || (!paramDateTimeField.isSupported())) {
/* 269 */       return paramDateTimeField;
/*     */     }
/* 271 */     if (paramHashMap.containsKey(paramDateTimeField)) {
/* 272 */       return (DateTimeField)paramHashMap.get(paramDateTimeField);
/*     */     }
/* 274 */     LimitDateTimeField localLimitDateTimeField = new LimitDateTimeField(paramDateTimeField, convertField(paramDateTimeField.getDurationField(), paramHashMap), convertField(paramDateTimeField.getRangeDurationField(), paramHashMap), convertField(paramDateTimeField.getLeapDurationField(), paramHashMap));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 279 */     paramHashMap.put(paramDateTimeField, localLimitDateTimeField);
/* 280 */     return localLimitDateTimeField;
/*     */   }
/*     */   
/*     */   void checkLimits(long paramLong, String paramString) {
/*     */     DateTime localDateTime;
/* 285 */     if (((localDateTime = this.iLowerLimit) != null) && (paramLong < localDateTime.getMillis())) {
/* 286 */       throw new LimitException(paramString, true);
/*     */     }
/* 288 */     if (((localDateTime = this.iUpperLimit) != null) && (paramLong >= localDateTime.getMillis())) {
/* 289 */       throw new LimitException(paramString, false);
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
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 303 */     if (this == paramObject) {
/* 304 */       return true;
/*     */     }
/* 306 */     if (!(paramObject instanceof LimitChronology)) {
/* 307 */       return false;
/*     */     }
/* 309 */     LimitChronology localLimitChronology = (LimitChronology)paramObject;
/* 310 */     return (getBase().equals(localLimitChronology.getBase())) && (FieldUtils.equals(getLowerLimit(), localLimitChronology.getLowerLimit())) && (FieldUtils.equals(getUpperLimit(), localLimitChronology.getUpperLimit()));
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
/*     */   public int hashCode()
/*     */   {
/* 323 */     int i = 317351877;
/* 324 */     i += (getLowerLimit() != null ? getLowerLimit().hashCode() : 0);
/* 325 */     i += (getUpperLimit() != null ? getUpperLimit().hashCode() : 0);
/* 326 */     i += getBase().hashCode() * 7;
/* 327 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 336 */     return "LimitChronology[" + getBase().toString() + ", " + (getLowerLimit() == null ? "NoLimit" : getLowerLimit().toString()) + ", " + (getUpperLimit() == null ? "NoLimit" : getUpperLimit().toString()) + ']';
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private class LimitException
/*     */     extends IllegalArgumentException
/*     */   {
/*     */     private static final long serialVersionUID = -5924689995607498581L;
/*     */     
/*     */ 
/*     */     private final boolean iIsLow;
/*     */     
/*     */ 
/*     */     LimitException(String paramString, boolean paramBoolean)
/*     */     {
/* 352 */       super();
/* 353 */       this.iIsLow = paramBoolean;
/*     */     }
/*     */     
/*     */     public String getMessage() {
/* 357 */       StringBuffer localStringBuffer = new StringBuffer(85);
/* 358 */       localStringBuffer.append("The");
/* 359 */       String str = super.getMessage();
/* 360 */       if (str != null) {
/* 361 */         localStringBuffer.append(' ');
/* 362 */         localStringBuffer.append(str);
/*     */       }
/* 364 */       localStringBuffer.append(" instant is ");
/*     */       
/* 366 */       DateTimeFormatter localDateTimeFormatter = ISODateTimeFormat.dateTime();
/* 367 */       localDateTimeFormatter = localDateTimeFormatter.withChronology(LimitChronology.this.getBase());
/* 368 */       if (this.iIsLow) {
/* 369 */         localStringBuffer.append("below the supported minimum of ");
/* 370 */         localDateTimeFormatter.printTo(localStringBuffer, LimitChronology.this.getLowerLimit().getMillis());
/*     */       } else {
/* 372 */         localStringBuffer.append("above the supported maximum of ");
/* 373 */         localDateTimeFormatter.printTo(localStringBuffer, LimitChronology.this.getUpperLimit().getMillis());
/*     */       }
/*     */       
/* 376 */       localStringBuffer.append(" (");
/* 377 */       localStringBuffer.append(LimitChronology.this.getBase());
/* 378 */       localStringBuffer.append(')');
/*     */       
/* 380 */       return localStringBuffer.toString();
/*     */     }
/*     */     
/*     */     public String toString() {
/* 384 */       return "IllegalArgumentException: " + getMessage();
/*     */     }
/*     */   }
/*     */   
/*     */   private class LimitDurationField extends DecoratedDurationField {
/*     */     private static final long serialVersionUID = 8049297699408782284L;
/*     */     
/*     */     LimitDurationField(DurationField paramDurationField) {
/* 392 */       super(paramDurationField.getType());
/*     */     }
/*     */     
/*     */     public int getValue(long paramLong1, long paramLong2) {
/* 396 */       LimitChronology.this.checkLimits(paramLong2, null);
/* 397 */       return getWrappedField().getValue(paramLong1, paramLong2);
/*     */     }
/*     */     
/*     */     public long getValueAsLong(long paramLong1, long paramLong2) {
/* 401 */       LimitChronology.this.checkLimits(paramLong2, null);
/* 402 */       return getWrappedField().getValueAsLong(paramLong1, paramLong2);
/*     */     }
/*     */     
/*     */     public long getMillis(int paramInt, long paramLong) {
/* 406 */       LimitChronology.this.checkLimits(paramLong, null);
/* 407 */       return getWrappedField().getMillis(paramInt, paramLong);
/*     */     }
/*     */     
/*     */     public long getMillis(long paramLong1, long paramLong2) {
/* 411 */       LimitChronology.this.checkLimits(paramLong2, null);
/* 412 */       return getWrappedField().getMillis(paramLong1, paramLong2);
/*     */     }
/*     */     
/*     */     public long add(long paramLong, int paramInt) {
/* 416 */       LimitChronology.this.checkLimits(paramLong, null);
/* 417 */       long l = getWrappedField().add(paramLong, paramInt);
/* 418 */       LimitChronology.this.checkLimits(l, "resulting");
/* 419 */       return l;
/*     */     }
/*     */     
/*     */     public long add(long paramLong1, long paramLong2) {
/* 423 */       LimitChronology.this.checkLimits(paramLong1, null);
/* 424 */       long l = getWrappedField().add(paramLong1, paramLong2);
/* 425 */       LimitChronology.this.checkLimits(l, "resulting");
/* 426 */       return l;
/*     */     }
/*     */     
/*     */     public int getDifference(long paramLong1, long paramLong2) {
/* 430 */       LimitChronology.this.checkLimits(paramLong1, "minuend");
/* 431 */       LimitChronology.this.checkLimits(paramLong2, "subtrahend");
/* 432 */       return getWrappedField().getDifference(paramLong1, paramLong2);
/*     */     }
/*     */     
/*     */     public long getDifferenceAsLong(long paramLong1, long paramLong2) {
/* 436 */       LimitChronology.this.checkLimits(paramLong1, "minuend");
/* 437 */       LimitChronology.this.checkLimits(paramLong2, "subtrahend");
/* 438 */       return getWrappedField().getDifferenceAsLong(paramLong1, paramLong2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private class LimitDateTimeField
/*     */     extends DecoratedDateTimeField
/*     */   {
/*     */     private static final long serialVersionUID = -2435306746995699312L;
/*     */     
/*     */     private final DurationField iDurationField;
/*     */     private final DurationField iRangeDurationField;
/*     */     private final DurationField iLeapDurationField;
/*     */     
/*     */     LimitDateTimeField(DateTimeField paramDateTimeField, DurationField paramDurationField1, DurationField paramDurationField2, DurationField paramDurationField3)
/*     */     {
/* 454 */       super(paramDateTimeField.getType());
/* 455 */       this.iDurationField = paramDurationField1;
/* 456 */       this.iRangeDurationField = paramDurationField2;
/* 457 */       this.iLeapDurationField = paramDurationField3;
/*     */     }
/*     */     
/*     */     public int get(long paramLong) {
/* 461 */       LimitChronology.this.checkLimits(paramLong, null);
/* 462 */       return getWrappedField().get(paramLong);
/*     */     }
/*     */     
/*     */     public String getAsText(long paramLong, Locale paramLocale) {
/* 466 */       LimitChronology.this.checkLimits(paramLong, null);
/* 467 */       return getWrappedField().getAsText(paramLong, paramLocale);
/*     */     }
/*     */     
/*     */     public String getAsShortText(long paramLong, Locale paramLocale) {
/* 471 */       LimitChronology.this.checkLimits(paramLong, null);
/* 472 */       return getWrappedField().getAsShortText(paramLong, paramLocale);
/*     */     }
/*     */     
/*     */     public long add(long paramLong, int paramInt) {
/* 476 */       LimitChronology.this.checkLimits(paramLong, null);
/* 477 */       long l = getWrappedField().add(paramLong, paramInt);
/* 478 */       LimitChronology.this.checkLimits(l, "resulting");
/* 479 */       return l;
/*     */     }
/*     */     
/*     */     public long add(long paramLong1, long paramLong2) {
/* 483 */       LimitChronology.this.checkLimits(paramLong1, null);
/* 484 */       long l = getWrappedField().add(paramLong1, paramLong2);
/* 485 */       LimitChronology.this.checkLimits(l, "resulting");
/* 486 */       return l;
/*     */     }
/*     */     
/*     */     public long addWrapField(long paramLong, int paramInt) {
/* 490 */       LimitChronology.this.checkLimits(paramLong, null);
/* 491 */       long l = getWrappedField().addWrapField(paramLong, paramInt);
/* 492 */       LimitChronology.this.checkLimits(l, "resulting");
/* 493 */       return l;
/*     */     }
/*     */     
/*     */     public int getDifference(long paramLong1, long paramLong2) {
/* 497 */       LimitChronology.this.checkLimits(paramLong1, "minuend");
/* 498 */       LimitChronology.this.checkLimits(paramLong2, "subtrahend");
/* 499 */       return getWrappedField().getDifference(paramLong1, paramLong2);
/*     */     }
/*     */     
/*     */     public long getDifferenceAsLong(long paramLong1, long paramLong2) {
/* 503 */       LimitChronology.this.checkLimits(paramLong1, "minuend");
/* 504 */       LimitChronology.this.checkLimits(paramLong2, "subtrahend");
/* 505 */       return getWrappedField().getDifferenceAsLong(paramLong1, paramLong2);
/*     */     }
/*     */     
/*     */     public long set(long paramLong, int paramInt) {
/* 509 */       LimitChronology.this.checkLimits(paramLong, null);
/* 510 */       long l = getWrappedField().set(paramLong, paramInt);
/* 511 */       LimitChronology.this.checkLimits(l, "resulting");
/* 512 */       return l;
/*     */     }
/*     */     
/*     */     public long set(long paramLong, String paramString, Locale paramLocale) {
/* 516 */       LimitChronology.this.checkLimits(paramLong, null);
/* 517 */       long l = getWrappedField().set(paramLong, paramString, paramLocale);
/* 518 */       LimitChronology.this.checkLimits(l, "resulting");
/* 519 */       return l;
/*     */     }
/*     */     
/*     */     public final DurationField getDurationField() {
/* 523 */       return this.iDurationField;
/*     */     }
/*     */     
/*     */     public final DurationField getRangeDurationField() {
/* 527 */       return this.iRangeDurationField;
/*     */     }
/*     */     
/*     */     public boolean isLeap(long paramLong) {
/* 531 */       LimitChronology.this.checkLimits(paramLong, null);
/* 532 */       return getWrappedField().isLeap(paramLong);
/*     */     }
/*     */     
/*     */     public int getLeapAmount(long paramLong) {
/* 536 */       LimitChronology.this.checkLimits(paramLong, null);
/* 537 */       return getWrappedField().getLeapAmount(paramLong);
/*     */     }
/*     */     
/*     */     public final DurationField getLeapDurationField() {
/* 541 */       return this.iLeapDurationField;
/*     */     }
/*     */     
/*     */     public long roundFloor(long paramLong) {
/* 545 */       LimitChronology.this.checkLimits(paramLong, null);
/* 546 */       long l = getWrappedField().roundFloor(paramLong);
/* 547 */       LimitChronology.this.checkLimits(l, "resulting");
/* 548 */       return l;
/*     */     }
/*     */     
/*     */     public long roundCeiling(long paramLong) {
/* 552 */       LimitChronology.this.checkLimits(paramLong, null);
/* 553 */       long l = getWrappedField().roundCeiling(paramLong);
/* 554 */       LimitChronology.this.checkLimits(l, "resulting");
/* 555 */       return l;
/*     */     }
/*     */     
/*     */     public long roundHalfFloor(long paramLong) {
/* 559 */       LimitChronology.this.checkLimits(paramLong, null);
/* 560 */       long l = getWrappedField().roundHalfFloor(paramLong);
/* 561 */       LimitChronology.this.checkLimits(l, "resulting");
/* 562 */       return l;
/*     */     }
/*     */     
/*     */     public long roundHalfCeiling(long paramLong) {
/* 566 */       LimitChronology.this.checkLimits(paramLong, null);
/* 567 */       long l = getWrappedField().roundHalfCeiling(paramLong);
/* 568 */       LimitChronology.this.checkLimits(l, "resulting");
/* 569 */       return l;
/*     */     }
/*     */     
/*     */     public long roundHalfEven(long paramLong) {
/* 573 */       LimitChronology.this.checkLimits(paramLong, null);
/* 574 */       long l = getWrappedField().roundHalfEven(paramLong);
/* 575 */       LimitChronology.this.checkLimits(l, "resulting");
/* 576 */       return l;
/*     */     }
/*     */     
/*     */     public long remainder(long paramLong) {
/* 580 */       LimitChronology.this.checkLimits(paramLong, null);
/* 581 */       long l = getWrappedField().remainder(paramLong);
/* 582 */       LimitChronology.this.checkLimits(l, "resulting");
/* 583 */       return l;
/*     */     }
/*     */     
/*     */     public int getMinimumValue(long paramLong) {
/* 587 */       LimitChronology.this.checkLimits(paramLong, null);
/* 588 */       return getWrappedField().getMinimumValue(paramLong);
/*     */     }
/*     */     
/*     */     public int getMaximumValue(long paramLong) {
/* 592 */       LimitChronology.this.checkLimits(paramLong, null);
/* 593 */       return getWrappedField().getMaximumValue(paramLong);
/*     */     }
/*     */     
/*     */     public int getMaximumTextLength(Locale paramLocale) {
/* 597 */       return getWrappedField().getMaximumTextLength(paramLocale);
/*     */     }
/*     */     
/*     */     public int getMaximumShortTextLength(Locale paramLocale) {
/* 601 */       return getWrappedField().getMaximumShortTextLength(paramLocale);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\LimitChronology.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */