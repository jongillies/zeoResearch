/*     */ package org.joda.time.format;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Locale;
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTimeField;
/*     */ import org.joda.time.DateTimeFieldType;
/*     */ import org.joda.time.DateTimeUtils;
/*     */ import org.joda.time.DateTimeZone;
/*     */ import org.joda.time.DurationField;
/*     */ import org.joda.time.IllegalFieldValueException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DateTimeParserBucket
/*     */ {
/*     */   private final Chronology iChrono;
/*     */   private final long iMillis;
/*     */   private DateTimeZone iZone;
/*     */   private int iOffset;
/*     */   private Locale iLocale;
/*     */   private Integer iPivotYear;
/*  68 */   private SavedField[] iSavedFields = new SavedField[8];
/*     */   
/*     */ 
/*     */   private int iSavedFieldsCount;
/*     */   
/*     */ 
/*     */   private boolean iSavedFieldsShared;
/*     */   
/*     */ 
/*     */   private Object iSavedState;
/*     */   
/*     */ 
/*     */   public DateTimeParserBucket(long paramLong, Chronology paramChronology, Locale paramLocale)
/*     */   {
/*  82 */     this(paramLong, paramChronology, paramLocale, null);
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
/*     */   public DateTimeParserBucket(long paramLong, Chronology paramChronology, Locale paramLocale, Integer paramInteger)
/*     */   {
/*  97 */     paramChronology = DateTimeUtils.getChronology(paramChronology);
/*  98 */     this.iMillis = paramLong;
/*  99 */     this.iChrono = paramChronology.withUTC();
/* 100 */     this.iLocale = (paramLocale == null ? Locale.getDefault() : paramLocale);
/* 101 */     setZone(paramChronology.getZone());
/* 102 */     this.iPivotYear = paramInteger;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Chronology getChronology()
/*     */   {
/* 110 */     return this.iChrono;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Locale getLocale()
/*     */   {
/* 120 */     return this.iLocale;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeZone getZone()
/*     */   {
/* 129 */     return this.iZone;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setZone(DateTimeZone paramDateTimeZone)
/*     */   {
/* 139 */     this.iSavedState = null;
/* 140 */     this.iZone = (paramDateTimeZone == DateTimeZone.UTC ? null : paramDateTimeZone);
/* 141 */     this.iOffset = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getOffset()
/*     */   {
/* 150 */     return this.iOffset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOffset(int paramInt)
/*     */   {
/* 158 */     this.iSavedState = null;
/* 159 */     this.iOffset = paramInt;
/* 160 */     this.iZone = null;
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
/*     */   public Integer getPivotYear()
/*     */   {
/* 173 */     return this.iPivotYear;
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
/*     */   public void setPivotYear(Integer paramInteger)
/*     */   {
/* 186 */     this.iPivotYear = paramInteger;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void saveField(DateTimeField paramDateTimeField, int paramInt)
/*     */   {
/* 197 */     saveField(new SavedField(paramDateTimeField, paramInt));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void saveField(DateTimeFieldType paramDateTimeFieldType, int paramInt)
/*     */   {
/* 207 */     saveField(new SavedField(paramDateTimeFieldType.getField(this.iChrono), paramInt));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void saveField(DateTimeFieldType paramDateTimeFieldType, String paramString, Locale paramLocale)
/*     */   {
/* 218 */     saveField(new SavedField(paramDateTimeFieldType.getField(this.iChrono), paramString, paramLocale));
/*     */   }
/*     */   
/*     */   private void saveField(SavedField paramSavedField) {
/* 222 */     Object localObject = this.iSavedFields;
/* 223 */     int i = this.iSavedFieldsCount;
/*     */     
/* 225 */     if ((i == localObject.length) || (this.iSavedFieldsShared))
/*     */     {
/* 227 */       SavedField[] arrayOfSavedField = new SavedField[i == localObject.length ? i * 2 : localObject.length];
/*     */       
/* 229 */       System.arraycopy(localObject, 0, arrayOfSavedField, 0, i);
/* 230 */       this.iSavedFields = (localObject = arrayOfSavedField);
/* 231 */       this.iSavedFieldsShared = false;
/*     */     }
/*     */     
/* 234 */     this.iSavedState = null;
/* 235 */     localObject[i] = paramSavedField;
/* 236 */     this.iSavedFieldsCount = (i + 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object saveState()
/*     */   {
/* 247 */     if (this.iSavedState == null) {
/* 248 */       this.iSavedState = new SavedState();
/*     */     }
/* 250 */     return this.iSavedState;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean restoreState(Object paramObject)
/*     */   {
/* 262 */     if (((paramObject instanceof SavedState)) && 
/* 263 */       (((SavedState)paramObject).restoreState(this))) {
/* 264 */       this.iSavedState = paramObject;
/* 265 */       return true;
/*     */     }
/*     */     
/* 268 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long computeMillis()
/*     */   {
/* 279 */     return computeMillis(false, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long computeMillis(boolean paramBoolean)
/*     */   {
/* 291 */     return computeMillis(paramBoolean, null);
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
/*     */   public long computeMillis(boolean paramBoolean, String paramString)
/*     */   {
/* 305 */     SavedField[] arrayOfSavedField = this.iSavedFields;
/* 306 */     int i = this.iSavedFieldsCount;
/* 307 */     if (this.iSavedFieldsShared) {
/* 308 */       this.iSavedFields = (arrayOfSavedField = (SavedField[])this.iSavedFields.clone());
/* 309 */       this.iSavedFieldsShared = false;
/*     */     }
/* 311 */     sort(arrayOfSavedField, i);
/*     */     
/* 313 */     long l = this.iMillis;
/*     */     try {
/* 315 */       for (int j = 0; j < i; j++) {
/* 316 */         l = arrayOfSavedField[j].set(l, paramBoolean);
/*     */       }
/*     */     } catch (IllegalFieldValueException localIllegalFieldValueException) {
/* 319 */       if (paramString != null) {
/* 320 */         localIllegalFieldValueException.prependMessage("Cannot parse \"" + paramString + '"');
/*     */       }
/* 322 */       throw localIllegalFieldValueException;
/*     */     }
/*     */     
/* 325 */     if (this.iZone == null) {
/* 326 */       l -= this.iOffset;
/*     */     } else {
/* 328 */       int k = this.iZone.getOffsetFromLocal(l);
/* 329 */       l -= k;
/* 330 */       if (k != this.iZone.getOffset(l)) {
/* 331 */         String str = "Illegal instant due to time zone offset transition (" + this.iZone + ')';
/*     */         
/* 333 */         if (paramString != null) {
/* 334 */           str = "Cannot parse \"" + paramString + "\": " + str;
/*     */         }
/* 336 */         throw new IllegalArgumentException(str);
/*     */       }
/*     */     }
/*     */     
/* 340 */     return l;
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
/*     */   private static void sort(Comparable[] paramArrayOfComparable, int paramInt)
/*     */   {
/* 362 */     if (paramInt > 10) {
/* 363 */       Arrays.sort(paramArrayOfComparable, 0, paramInt);
/*     */     } else {
/* 365 */       for (int i = 0; i < paramInt; i++) {
/* 366 */         for (int j = i; (j > 0) && (paramArrayOfComparable[(j - 1)].compareTo(paramArrayOfComparable[j]) > 0); j--) {
/* 367 */           Comparable localComparable = paramArrayOfComparable[j];
/* 368 */           paramArrayOfComparable[j] = paramArrayOfComparable[(j - 1)];
/* 369 */           paramArrayOfComparable[(j - 1)] = localComparable;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   class SavedState {
/*     */     final DateTimeZone iZone;
/*     */     final int iOffset;
/*     */     final DateTimeParserBucket.SavedField[] iSavedFields;
/*     */     final int iSavedFieldsCount;
/*     */     
/*     */     SavedState() {
/* 382 */       this.iZone = DateTimeParserBucket.this.iZone;
/* 383 */       this.iOffset = DateTimeParserBucket.this.iOffset;
/* 384 */       this.iSavedFields = DateTimeParserBucket.this.iSavedFields;
/* 385 */       this.iSavedFieldsCount = DateTimeParserBucket.this.iSavedFieldsCount;
/*     */     }
/*     */     
/*     */     boolean restoreState(DateTimeParserBucket paramDateTimeParserBucket) {
/* 389 */       if (paramDateTimeParserBucket != DateTimeParserBucket.this) {
/* 390 */         return false;
/*     */       }
/* 392 */       paramDateTimeParserBucket.iZone = this.iZone;
/* 393 */       paramDateTimeParserBucket.iOffset = this.iOffset;
/* 394 */       paramDateTimeParserBucket.iSavedFields = this.iSavedFields;
/* 395 */       if (this.iSavedFieldsCount < paramDateTimeParserBucket.iSavedFieldsCount)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 400 */         paramDateTimeParserBucket.iSavedFieldsShared = true;
/*     */       }
/* 402 */       paramDateTimeParserBucket.iSavedFieldsCount = this.iSavedFieldsCount;
/* 403 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   static class SavedField implements Comparable {
/*     */     final DateTimeField iField;
/*     */     final int iValue;
/*     */     final String iText;
/*     */     final Locale iLocale;
/*     */     
/*     */     SavedField(DateTimeField paramDateTimeField, int paramInt) {
/* 414 */       this.iField = paramDateTimeField;
/* 415 */       this.iValue = paramInt;
/* 416 */       this.iText = null;
/* 417 */       this.iLocale = null;
/*     */     }
/*     */     
/*     */     SavedField(DateTimeField paramDateTimeField, String paramString, Locale paramLocale) {
/* 421 */       this.iField = paramDateTimeField;
/* 422 */       this.iValue = 0;
/* 423 */       this.iText = paramString;
/* 424 */       this.iLocale = paramLocale;
/*     */     }
/*     */     
/*     */     long set(long paramLong, boolean paramBoolean) {
/* 428 */       if (this.iText == null) {
/* 429 */         paramLong = this.iField.set(paramLong, this.iValue);
/*     */       } else {
/* 431 */         paramLong = this.iField.set(paramLong, this.iText, this.iLocale);
/*     */       }
/* 433 */       if (paramBoolean) {
/* 434 */         paramLong = this.iField.roundFloor(paramLong);
/*     */       }
/* 436 */       return paramLong;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public int compareTo(Object paramObject)
/*     */     {
/* 445 */       DateTimeField localDateTimeField = ((SavedField)paramObject).iField;
/* 446 */       int i = compareReverse(this.iField.getRangeDurationField(), localDateTimeField.getRangeDurationField());
/*     */       
/* 448 */       if (i != 0) {
/* 449 */         return i;
/*     */       }
/* 451 */       return compareReverse(this.iField.getDurationField(), localDateTimeField.getDurationField());
/*     */     }
/*     */     
/*     */     private int compareReverse(DurationField paramDurationField1, DurationField paramDurationField2)
/*     */     {
/* 456 */       if ((paramDurationField1 == null) || (!paramDurationField1.isSupported())) {
/* 457 */         if ((paramDurationField2 == null) || (!paramDurationField2.isSupported())) {
/* 458 */           return 0;
/*     */         }
/* 460 */         return -1;
/*     */       }
/* 462 */       if ((paramDurationField2 == null) || (!paramDurationField2.isSupported())) {
/* 463 */         return 1;
/*     */       }
/* 465 */       return -paramDurationField1.compareTo(paramDurationField2);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\format\DateTimeParserBucket.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */