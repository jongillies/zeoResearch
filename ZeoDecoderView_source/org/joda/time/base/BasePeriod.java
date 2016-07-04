/*     */ package org.joda.time.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTimeUtils;
/*     */ import org.joda.time.Duration;
/*     */ import org.joda.time.DurationFieldType;
/*     */ import org.joda.time.MutablePeriod;
/*     */ import org.joda.time.PeriodType;
/*     */ import org.joda.time.ReadWritablePeriod;
/*     */ import org.joda.time.ReadableDuration;
/*     */ import org.joda.time.ReadableInstant;
/*     */ import org.joda.time.ReadablePartial;
/*     */ import org.joda.time.ReadablePeriod;
/*     */ import org.joda.time.convert.ConverterManager;
/*     */ import org.joda.time.convert.PeriodConverter;
/*     */ import org.joda.time.field.FieldUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BasePeriod
/*     */   extends AbstractPeriod
/*     */   implements ReadablePeriod, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2110953284060001145L;
/*     */   private PeriodType iType;
/*     */   private int[] iValues;
/*     */   
/*     */   protected BasePeriod(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, PeriodType paramPeriodType)
/*     */   {
/*  81 */     paramPeriodType = checkPeriodType(paramPeriodType);
/*  82 */     this.iType = paramPeriodType;
/*  83 */     setPeriodInternal(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8);
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
/*     */   protected BasePeriod(long paramLong1, long paramLong2, PeriodType paramPeriodType, Chronology paramChronology)
/*     */   {
/*  97 */     paramPeriodType = checkPeriodType(paramPeriodType);
/*  98 */     paramChronology = DateTimeUtils.getChronology(paramChronology);
/*  99 */     this.iType = paramPeriodType;
/* 100 */     this.iValues = paramChronology.get(this, paramLong1, paramLong2);
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
/*     */   protected BasePeriod(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2, PeriodType paramPeriodType)
/*     */   {
/* 113 */     paramPeriodType = checkPeriodType(paramPeriodType);
/* 114 */     if ((paramReadableInstant1 == null) && (paramReadableInstant2 == null)) {
/* 115 */       this.iType = paramPeriodType;
/* 116 */       this.iValues = new int[size()];
/*     */     } else {
/* 118 */       long l1 = DateTimeUtils.getInstantMillis(paramReadableInstant1);
/* 119 */       long l2 = DateTimeUtils.getInstantMillis(paramReadableInstant2);
/* 120 */       Chronology localChronology = DateTimeUtils.getIntervalChronology(paramReadableInstant1, paramReadableInstant2);
/* 121 */       this.iType = paramPeriodType;
/* 122 */       this.iValues = localChronology.get(this, l1, l2);
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
/*     */   protected BasePeriod(ReadablePartial paramReadablePartial1, ReadablePartial paramReadablePartial2, PeriodType paramPeriodType)
/*     */   {
/* 146 */     if ((paramReadablePartial1 == null) || (paramReadablePartial2 == null)) {
/* 147 */       throw new IllegalArgumentException("ReadablePartial objects must not be null");
/*     */     }
/* 149 */     if (((paramReadablePartial1 instanceof BaseLocal)) && ((paramReadablePartial2 instanceof BaseLocal)) && (paramReadablePartial1.getClass() == paramReadablePartial2.getClass()))
/*     */     {
/* 151 */       paramPeriodType = checkPeriodType(paramPeriodType);
/* 152 */       long l1 = ((BaseLocal)paramReadablePartial1).getLocalMillis();
/* 153 */       long l2 = ((BaseLocal)paramReadablePartial2).getLocalMillis();
/* 154 */       Chronology localChronology2 = paramReadablePartial1.getChronology();
/* 155 */       localChronology2 = DateTimeUtils.getChronology(localChronology2);
/* 156 */       this.iType = paramPeriodType;
/* 157 */       this.iValues = localChronology2.get(this, l1, l2);
/*     */     } else {
/* 159 */       if (paramReadablePartial1.size() != paramReadablePartial2.size()) {
/* 160 */         throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
/*     */       }
/* 162 */       int i = 0; for (int j = paramReadablePartial1.size(); i < j; i++) {
/* 163 */         if (paramReadablePartial1.getFieldType(i) != paramReadablePartial2.getFieldType(i)) {
/* 164 */           throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
/*     */         }
/*     */       }
/* 167 */       if (!DateTimeUtils.isContiguous(paramReadablePartial1)) {
/* 168 */         throw new IllegalArgumentException("ReadablePartial objects must be contiguous");
/*     */       }
/* 170 */       this.iType = checkPeriodType(paramPeriodType);
/* 171 */       Chronology localChronology1 = DateTimeUtils.getChronology(paramReadablePartial1.getChronology()).withUTC();
/* 172 */       this.iValues = localChronology1.get(this, localChronology1.set(paramReadablePartial1, 0L), localChronology1.set(paramReadablePartial2, 0L));
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
/*     */   protected BasePeriod(ReadableInstant paramReadableInstant, ReadableDuration paramReadableDuration, PeriodType paramPeriodType)
/*     */   {
/* 185 */     paramPeriodType = checkPeriodType(paramPeriodType);
/* 186 */     long l1 = DateTimeUtils.getInstantMillis(paramReadableInstant);
/* 187 */     long l2 = DateTimeUtils.getDurationMillis(paramReadableDuration);
/* 188 */     long l3 = FieldUtils.safeAdd(l1, l2);
/* 189 */     Chronology localChronology = DateTimeUtils.getInstantChronology(paramReadableInstant);
/* 190 */     this.iType = paramPeriodType;
/* 191 */     this.iValues = localChronology.get(this, l1, l3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected BasePeriod(ReadableDuration paramReadableDuration, ReadableInstant paramReadableInstant, PeriodType paramPeriodType)
/*     */   {
/* 203 */     paramPeriodType = checkPeriodType(paramPeriodType);
/* 204 */     long l1 = DateTimeUtils.getDurationMillis(paramReadableDuration);
/* 205 */     long l2 = DateTimeUtils.getInstantMillis(paramReadableInstant);
/* 206 */     long l3 = FieldUtils.safeSubtract(l2, l1);
/* 207 */     Chronology localChronology = DateTimeUtils.getInstantChronology(paramReadableInstant);
/* 208 */     this.iType = paramPeriodType;
/* 209 */     this.iValues = localChronology.get(this, l3, l2);
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
/*     */   protected BasePeriod(long paramLong, PeriodType paramPeriodType, Chronology paramChronology)
/*     */   {
/* 226 */     paramPeriodType = checkPeriodType(paramPeriodType);
/* 227 */     paramChronology = DateTimeUtils.getChronology(paramChronology);
/* 228 */     this.iType = paramPeriodType;
/* 229 */     this.iValues = paramChronology.get(this, paramLong);
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
/*     */   protected BasePeriod(Object paramObject, PeriodType paramPeriodType, Chronology paramChronology)
/*     */   {
/* 243 */     PeriodConverter localPeriodConverter = ConverterManager.getInstance().getPeriodConverter(paramObject);
/* 244 */     paramPeriodType = paramPeriodType == null ? localPeriodConverter.getPeriodType(paramObject) : paramPeriodType;
/* 245 */     paramPeriodType = checkPeriodType(paramPeriodType);
/* 246 */     this.iType = paramPeriodType;
/* 247 */     if ((this instanceof ReadWritablePeriod)) {
/* 248 */       this.iValues = new int[size()];
/* 249 */       paramChronology = DateTimeUtils.getChronology(paramChronology);
/* 250 */       localPeriodConverter.setInto((ReadWritablePeriod)this, paramObject, paramChronology);
/*     */     } else {
/* 252 */       this.iValues = new MutablePeriod(paramObject, paramPeriodType, paramChronology).getValues();
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
/*     */   protected BasePeriod(int[] paramArrayOfInt, PeriodType paramPeriodType)
/*     */   {
/* 265 */     this.iType = paramPeriodType;
/* 266 */     this.iValues = paramArrayOfInt;
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
/*     */   protected PeriodType checkPeriodType(PeriodType paramPeriodType)
/*     */   {
/* 279 */     return DateTimeUtils.getPeriodType(paramPeriodType);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PeriodType getPeriodType()
/*     */   {
/* 289 */     return this.iType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 299 */     return this.iType.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DurationFieldType getFieldType(int paramInt)
/*     */   {
/* 310 */     return this.iType.getFieldType(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getValue(int paramInt)
/*     */   {
/* 321 */     return this.iValues[paramInt];
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
/*     */   public Duration toDurationFrom(ReadableInstant paramReadableInstant)
/*     */   {
/* 342 */     long l1 = DateTimeUtils.getInstantMillis(paramReadableInstant);
/* 343 */     Chronology localChronology = DateTimeUtils.getInstantChronology(paramReadableInstant);
/* 344 */     long l2 = localChronology.add(this, l1, 1);
/* 345 */     return new Duration(l1, l2);
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
/*     */   public Duration toDurationTo(ReadableInstant paramReadableInstant)
/*     */   {
/* 366 */     long l1 = DateTimeUtils.getInstantMillis(paramReadableInstant);
/* 367 */     Chronology localChronology = DateTimeUtils.getInstantChronology(paramReadableInstant);
/* 368 */     long l2 = localChronology.add(this, l1, -1);
/* 369 */     return new Duration(l2, l1);
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
/*     */   private void checkAndUpdate(DurationFieldType paramDurationFieldType, int[] paramArrayOfInt, int paramInt)
/*     */   {
/* 382 */     int i = indexOf(paramDurationFieldType);
/* 383 */     if (i == -1) {
/* 384 */       if (paramInt != 0) {
/* 385 */         throw new IllegalArgumentException("Period does not support field '" + paramDurationFieldType.getName() + "'");
/*     */       }
/*     */     }
/*     */     else {
/* 389 */       paramArrayOfInt[i] = paramInt;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setPeriod(ReadablePeriod paramReadablePeriod)
/*     */   {
/* 401 */     if (paramReadablePeriod == null) {
/* 402 */       setValues(new int[size()]);
/*     */     } else {
/* 404 */       setPeriodInternal(paramReadablePeriod);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void setPeriodInternal(ReadablePeriod paramReadablePeriod)
/*     */   {
/* 412 */     int[] arrayOfInt = new int[size()];
/* 413 */     int i = 0; for (int j = paramReadablePeriod.size(); i < j; i++) {
/* 414 */       DurationFieldType localDurationFieldType = paramReadablePeriod.getFieldType(i);
/* 415 */       int k = paramReadablePeriod.getValue(i);
/* 416 */       checkAndUpdate(localDurationFieldType, arrayOfInt, k);
/*     */     }
/* 418 */     this.iValues = arrayOfInt;
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
/*     */   protected void setPeriod(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
/*     */   {
/* 436 */     setPeriodInternal(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void setPeriodInternal(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
/*     */   {
/* 444 */     int[] arrayOfInt = new int[size()];
/* 445 */     checkAndUpdate(DurationFieldType.years(), arrayOfInt, paramInt1);
/* 446 */     checkAndUpdate(DurationFieldType.months(), arrayOfInt, paramInt2);
/* 447 */     checkAndUpdate(DurationFieldType.weeks(), arrayOfInt, paramInt3);
/* 448 */     checkAndUpdate(DurationFieldType.days(), arrayOfInt, paramInt4);
/* 449 */     checkAndUpdate(DurationFieldType.hours(), arrayOfInt, paramInt5);
/* 450 */     checkAndUpdate(DurationFieldType.minutes(), arrayOfInt, paramInt6);
/* 451 */     checkAndUpdate(DurationFieldType.seconds(), arrayOfInt, paramInt7);
/* 452 */     checkAndUpdate(DurationFieldType.millis(), arrayOfInt, paramInt8);
/* 453 */     this.iValues = arrayOfInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setField(DurationFieldType paramDurationFieldType, int paramInt)
/*     */   {
/* 465 */     setFieldInto(this.iValues, paramDurationFieldType, paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setFieldInto(int[] paramArrayOfInt, DurationFieldType paramDurationFieldType, int paramInt)
/*     */   {
/* 477 */     int i = indexOf(paramDurationFieldType);
/* 478 */     if (i == -1) {
/* 479 */       if ((paramInt != 0) || (paramDurationFieldType == null)) {
/* 480 */         throw new IllegalArgumentException("Period does not support field '" + paramDurationFieldType + "'");
/*     */       }
/*     */     }
/*     */     else {
/* 484 */       paramArrayOfInt[i] = paramInt;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void addField(DurationFieldType paramDurationFieldType, int paramInt)
/*     */   {
/* 496 */     addFieldInto(this.iValues, paramDurationFieldType, paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void addFieldInto(int[] paramArrayOfInt, DurationFieldType paramDurationFieldType, int paramInt)
/*     */   {
/* 508 */     int i = indexOf(paramDurationFieldType);
/* 509 */     if (i == -1) {
/* 510 */       if ((paramInt != 0) || (paramDurationFieldType == null)) {
/* 511 */         throw new IllegalArgumentException("Period does not support field '" + paramDurationFieldType + "'");
/*     */       }
/*     */     }
/*     */     else {
/* 515 */       paramArrayOfInt[i] = FieldUtils.safeAdd(paramArrayOfInt[i], paramInt);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void mergePeriod(ReadablePeriod paramReadablePeriod)
/*     */   {
/* 526 */     if (paramReadablePeriod != null) {
/* 527 */       this.iValues = mergePeriodInto(getValues(), paramReadablePeriod);
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
/*     */   protected int[] mergePeriodInto(int[] paramArrayOfInt, ReadablePeriod paramReadablePeriod)
/*     */   {
/* 540 */     int i = 0; for (int j = paramReadablePeriod.size(); i < j; i++) {
/* 541 */       DurationFieldType localDurationFieldType = paramReadablePeriod.getFieldType(i);
/* 542 */       int k = paramReadablePeriod.getValue(i);
/* 543 */       checkAndUpdate(localDurationFieldType, paramArrayOfInt, k);
/*     */     }
/* 545 */     return paramArrayOfInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void addPeriod(ReadablePeriod paramReadablePeriod)
/*     */   {
/* 555 */     if (paramReadablePeriod != null) {
/* 556 */       this.iValues = addPeriodInto(getValues(), paramReadablePeriod);
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
/*     */   protected int[] addPeriodInto(int[] paramArrayOfInt, ReadablePeriod paramReadablePeriod)
/*     */   {
/* 569 */     int i = 0; for (int j = paramReadablePeriod.size(); i < j; i++) {
/* 570 */       DurationFieldType localDurationFieldType = paramReadablePeriod.getFieldType(i);
/* 571 */       int k = paramReadablePeriod.getValue(i);
/* 572 */       if (k != 0) {
/* 573 */         int m = indexOf(localDurationFieldType);
/* 574 */         if (m == -1) {
/* 575 */           throw new IllegalArgumentException("Period does not support field '" + localDurationFieldType.getName() + "'");
/*     */         }
/*     */         
/* 578 */         paramArrayOfInt[m] = FieldUtils.safeAdd(getValue(m), k);
/*     */       }
/*     */     }
/*     */     
/* 582 */     return paramArrayOfInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setValue(int paramInt1, int paramInt2)
/*     */   {
/* 594 */     this.iValues[paramInt1] = paramInt2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setValues(int[] paramArrayOfInt)
/*     */   {
/* 603 */     this.iValues = paramArrayOfInt;
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\base\BasePeriod.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */