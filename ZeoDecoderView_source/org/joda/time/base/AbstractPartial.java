/*     */ package org.joda.time.base;
/*     */ 
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTime;
/*     */ import org.joda.time.DateTimeField;
/*     */ import org.joda.time.DateTimeFieldType;
/*     */ import org.joda.time.DateTimeUtils;
/*     */ import org.joda.time.DurationFieldType;
/*     */ import org.joda.time.ReadableInstant;
/*     */ import org.joda.time.ReadablePartial;
/*     */ import org.joda.time.field.FieldUtils;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractPartial
/*     */   implements ReadablePartial, Comparable
/*     */ {
/*     */   protected abstract DateTimeField getField(int paramInt, Chronology paramChronology);
/*     */   
/*     */   public DateTimeFieldType getFieldType(int paramInt)
/*     */   {
/*  79 */     return getField(paramInt, getChronology()).getType();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeFieldType[] getFieldTypes()
/*     */   {
/*  90 */     DateTimeFieldType[] arrayOfDateTimeFieldType = new DateTimeFieldType[size()];
/*  91 */     for (int i = 0; i < arrayOfDateTimeFieldType.length; i++) {
/*  92 */       arrayOfDateTimeFieldType[i] = getFieldType(i);
/*     */     }
/*  94 */     return arrayOfDateTimeFieldType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField getField(int paramInt)
/*     */   {
/* 105 */     return getField(paramInt, getChronology());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeField[] getFields()
/*     */   {
/* 116 */     DateTimeField[] arrayOfDateTimeField = new DateTimeField[size()];
/* 117 */     for (int i = 0; i < arrayOfDateTimeField.length; i++) {
/* 118 */       arrayOfDateTimeField[i] = getField(i);
/*     */     }
/* 120 */     return arrayOfDateTimeField;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int[] getValues()
/*     */   {
/* 132 */     int[] arrayOfInt = new int[size()];
/* 133 */     for (int i = 0; i < arrayOfInt.length; i++) {
/* 134 */       arrayOfInt[i] = getValue(i);
/*     */     }
/* 136 */     return arrayOfInt;
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
/*     */   public int get(DateTimeFieldType paramDateTimeFieldType)
/*     */   {
/* 150 */     return getValue(indexOfSupported(paramDateTimeFieldType));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isSupported(DateTimeFieldType paramDateTimeFieldType)
/*     */   {
/* 160 */     return indexOf(paramDateTimeFieldType) != -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int indexOf(DateTimeFieldType paramDateTimeFieldType)
/*     */   {
/* 170 */     int i = 0; for (int j = size(); i < j; i++) {
/* 171 */       if (getFieldType(i) == paramDateTimeFieldType) {
/* 172 */         return i;
/*     */       }
/*     */     }
/* 175 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int indexOfSupported(DateTimeFieldType paramDateTimeFieldType)
/*     */   {
/* 187 */     int i = indexOf(paramDateTimeFieldType);
/* 188 */     if (i == -1) {
/* 189 */       throw new IllegalArgumentException("Field '" + paramDateTimeFieldType + "' is not supported");
/*     */     }
/* 191 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int indexOf(DurationFieldType paramDurationFieldType)
/*     */   {
/* 202 */     int i = 0; for (int j = size(); i < j; i++) {
/* 203 */       if (getFieldType(i).getDurationType() == paramDurationFieldType) {
/* 204 */         return i;
/*     */       }
/*     */     }
/* 207 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int indexOfSupported(DurationFieldType paramDurationFieldType)
/*     */   {
/* 219 */     int i = indexOf(paramDurationFieldType);
/* 220 */     if (i == -1) {
/* 221 */       throw new IllegalArgumentException("Field '" + paramDurationFieldType + "' is not supported");
/*     */     }
/* 223 */     return i;
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
/*     */   public DateTime toDateTime(ReadableInstant paramReadableInstant)
/*     */   {
/* 240 */     Chronology localChronology = DateTimeUtils.getInstantChronology(paramReadableInstant);
/* 241 */     long l1 = DateTimeUtils.getInstantMillis(paramReadableInstant);
/* 242 */     long l2 = localChronology.set(this, l1);
/* 243 */     return new DateTime(l2, localChronology);
/*     */   }
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
/* 255 */     if (this == paramObject) {
/* 256 */       return true;
/*     */     }
/* 258 */     if (!(paramObject instanceof ReadablePartial)) {
/* 259 */       return false;
/*     */     }
/* 261 */     ReadablePartial localReadablePartial = (ReadablePartial)paramObject;
/* 262 */     if (size() != localReadablePartial.size()) {
/* 263 */       return false;
/*     */     }
/* 265 */     int i = 0; for (int j = size(); i < j; i++) {
/* 266 */       if ((getValue(i) != localReadablePartial.getValue(i)) || (getFieldType(i) != localReadablePartial.getFieldType(i))) {
/* 267 */         return false;
/*     */       }
/*     */     }
/* 270 */     return FieldUtils.equals(getChronology(), localReadablePartial.getChronology());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 280 */     int i = 157;
/* 281 */     int j = 0; for (int k = size(); j < k; j++) {
/* 282 */       i = 23 * i + getValue(j);
/* 283 */       i = 23 * i + getFieldType(j).hashCode();
/*     */     }
/* 285 */     i += getChronology().hashCode();
/* 286 */     return i;
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
/*     */   public int compareTo(Object paramObject)
/*     */   {
/* 316 */     if (this == paramObject) {
/* 317 */       return 0;
/*     */     }
/* 319 */     ReadablePartial localReadablePartial = (ReadablePartial)paramObject;
/* 320 */     if (size() != localReadablePartial.size()) {
/* 321 */       throw new ClassCastException("ReadablePartial objects must have matching field types");
/*     */     }
/* 323 */     int i = 0; for (int j = size(); i < j; i++) {
/* 324 */       if (getFieldType(i) != localReadablePartial.getFieldType(i)) {
/* 325 */         throw new ClassCastException("ReadablePartial objects must have matching field types");
/*     */       }
/*     */     }
/*     */     
/* 329 */     i = 0; for (j = size(); i < j; i++) {
/* 330 */       if (getValue(i) > localReadablePartial.getValue(i)) {
/* 331 */         return 1;
/*     */       }
/* 333 */       if (getValue(i) < localReadablePartial.getValue(i)) {
/* 334 */         return -1;
/*     */       }
/*     */     }
/* 337 */     return 0;
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
/*     */   public boolean isAfter(ReadablePartial paramReadablePartial)
/*     */   {
/* 356 */     if (paramReadablePartial == null) {
/* 357 */       throw new IllegalArgumentException("Partial cannot be null");
/*     */     }
/* 359 */     return compareTo(paramReadablePartial) > 0;
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
/*     */   public boolean isBefore(ReadablePartial paramReadablePartial)
/*     */   {
/* 378 */     if (paramReadablePartial == null) {
/* 379 */       throw new IllegalArgumentException("Partial cannot be null");
/*     */     }
/* 381 */     return compareTo(paramReadablePartial) < 0;
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
/*     */   public boolean isEqual(ReadablePartial paramReadablePartial)
/*     */   {
/* 400 */     if (paramReadablePartial == null) {
/* 401 */       throw new IllegalArgumentException("Partial cannot be null");
/*     */     }
/* 403 */     return compareTo(paramReadablePartial) == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString(DateTimeFormatter paramDateTimeFormatter)
/*     */   {
/* 415 */     if (paramDateTimeFormatter == null) {
/* 416 */       return toString();
/*     */     }
/* 418 */     return paramDateTimeFormatter.print(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\base\AbstractPartial.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */