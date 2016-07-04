/*     */ package org.joda.time.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTimeUtils;
/*     */ import org.joda.time.DurationField;
/*     */ import org.joda.time.DurationFieldType;
/*     */ import org.joda.time.MutablePeriod;
/*     */ import org.joda.time.Period;
/*     */ import org.joda.time.PeriodType;
/*     */ import org.joda.time.ReadableInstant;
/*     */ import org.joda.time.ReadablePartial;
/*     */ import org.joda.time.ReadablePeriod;
/*     */ import org.joda.time.chrono.ISOChronology;
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
/*     */ public abstract class BaseSingleFieldPeriod
/*     */   implements ReadablePeriod, Comparable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 9386874258972L;
/*     */   private int iPeriod;
/*     */   
/*     */   protected static int between(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2, DurationFieldType paramDurationFieldType)
/*     */   {
/*  66 */     if ((paramReadableInstant1 == null) || (paramReadableInstant2 == null)) {
/*  67 */       throw new IllegalArgumentException("ReadableInstant objects must not be null");
/*     */     }
/*  69 */     Chronology localChronology = DateTimeUtils.getInstantChronology(paramReadableInstant1);
/*  70 */     int i = paramDurationFieldType.getField(localChronology).getDifference(paramReadableInstant2.getMillis(), paramReadableInstant1.getMillis());
/*  71 */     return i;
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
/*     */   protected static int between(ReadablePartial paramReadablePartial1, ReadablePartial paramReadablePartial2, ReadablePeriod paramReadablePeriod)
/*     */   {
/*  88 */     if ((paramReadablePartial1 == null) || (paramReadablePartial2 == null)) {
/*  89 */       throw new IllegalArgumentException("ReadablePartial objects must not be null");
/*     */     }
/*  91 */     if (paramReadablePartial1.size() != paramReadablePartial2.size()) {
/*  92 */       throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
/*     */     }
/*  94 */     int i = 0; for (int j = paramReadablePartial1.size(); i < j; i++) {
/*  95 */       if (paramReadablePartial1.getFieldType(i) != paramReadablePartial2.getFieldType(i)) {
/*  96 */         throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
/*     */       }
/*     */     }
/*  99 */     if (!DateTimeUtils.isContiguous(paramReadablePartial1)) {
/* 100 */       throw new IllegalArgumentException("ReadablePartial objects must be contiguous");
/*     */     }
/* 102 */     Chronology localChronology = DateTimeUtils.getChronology(paramReadablePartial1.getChronology()).withUTC();
/* 103 */     int[] arrayOfInt = localChronology.get(paramReadablePeriod, localChronology.set(paramReadablePartial1, 0L), localChronology.set(paramReadablePartial2, 0L));
/* 104 */     return arrayOfInt[0];
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
/*     */   protected static int standardPeriodIn(ReadablePeriod paramReadablePeriod, long paramLong)
/*     */   {
/* 128 */     if (paramReadablePeriod == null) {
/* 129 */       return 0;
/*     */     }
/* 131 */     ISOChronology localISOChronology = ISOChronology.getInstanceUTC();
/* 132 */     long l = 0L;
/* 133 */     for (int i = 0; i < paramReadablePeriod.size(); i++) {
/* 134 */       int j = paramReadablePeriod.getValue(i);
/* 135 */       if (j != 0) {
/* 136 */         DurationField localDurationField = paramReadablePeriod.getFieldType(i).getField(localISOChronology);
/* 137 */         if (!localDurationField.isPrecise()) {
/* 138 */           throw new IllegalArgumentException("Cannot convert period to duration as " + localDurationField.getName() + " is not precise in the period " + paramReadablePeriod);
/*     */         }
/*     */         
/*     */ 
/* 142 */         l = FieldUtils.safeAdd(l, FieldUtils.safeMultiply(localDurationField.getUnitMillis(), j));
/*     */       }
/*     */     }
/* 145 */     return FieldUtils.safeToInt(l / paramLong);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected BaseSingleFieldPeriod(int paramInt)
/*     */   {
/* 156 */     this.iPeriod = paramInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int getValue()
/*     */   {
/* 166 */     return this.iPeriod;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setValue(int paramInt)
/*     */   {
/* 176 */     this.iPeriod = paramInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract DurationFieldType getFieldType();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract PeriodType getPeriodType();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 201 */     return 1;
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
/*     */   public DurationFieldType getFieldType(int paramInt)
/*     */   {
/* 215 */     if (paramInt != 0) {
/* 216 */       throw new IndexOutOfBoundsException(String.valueOf(paramInt));
/*     */     }
/* 218 */     return getFieldType();
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
/*     */   public int getValue(int paramInt)
/*     */   {
/* 231 */     if (paramInt != 0) {
/* 232 */       throw new IndexOutOfBoundsException(String.valueOf(paramInt));
/*     */     }
/* 234 */     return getValue();
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
/*     */   public int get(DurationFieldType paramDurationFieldType)
/*     */   {
/* 247 */     if (paramDurationFieldType == getFieldType()) {
/* 248 */       return getValue();
/*     */     }
/* 250 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isSupported(DurationFieldType paramDurationFieldType)
/*     */   {
/* 260 */     return paramDurationFieldType == getFieldType();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Period toPeriod()
/*     */   {
/* 271 */     return Period.ZERO.withFields(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutablePeriod toMutablePeriod()
/*     */   {
/* 283 */     MutablePeriod localMutablePeriod = new MutablePeriod();
/* 284 */     localMutablePeriod.add(this);
/* 285 */     return localMutablePeriod;
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
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 299 */     if (this == paramObject) {
/* 300 */       return true;
/*     */     }
/* 302 */     if (!(paramObject instanceof ReadablePeriod)) {
/* 303 */       return false;
/*     */     }
/* 305 */     ReadablePeriod localReadablePeriod = (ReadablePeriod)paramObject;
/* 306 */     return (localReadablePeriod.getPeriodType() == getPeriodType()) && (localReadablePeriod.getValue(0) == getValue());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 315 */     int i = 17;
/* 316 */     i = 27 * i + getValue();
/* 317 */     i = 27 * i + getFieldType().hashCode();
/* 318 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int compareTo(Object paramObject)
/*     */   {
/* 330 */     if (paramObject.getClass() != getClass()) {
/* 331 */       throw new ClassCastException(getClass() + " cannot be compared to " + paramObject.getClass());
/*     */     }
/* 333 */     int i = ((BaseSingleFieldPeriod)paramObject).getValue();
/* 334 */     int j = getValue();
/* 335 */     if (j > i) {
/* 336 */       return 1;
/*     */     }
/* 338 */     if (j < i) {
/* 339 */       return -1;
/*     */     }
/* 341 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\base\BaseSingleFieldPeriod.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */