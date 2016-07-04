/*     */ package org.joda.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import org.joda.time.base.AbstractPartial;
/*     */ import org.joda.time.field.AbstractPartialFieldProperty;
/*     */ import org.joda.time.field.FieldUtils;
/*     */ import org.joda.time.format.DateTimeFormat;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Partial
/*     */   extends AbstractPartial
/*     */   implements ReadablePartial, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 12324121189002L;
/*     */   private final Chronology iChronology;
/*     */   private final DateTimeFieldType[] iTypes;
/*     */   private final int[] iValues;
/*     */   private transient DateTimeFormatter[] iFormatter;
/*     */   
/*     */   public Partial()
/*     */   {
/* 103 */     this((Chronology)null);
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
/*     */   public Partial(Chronology paramChronology)
/*     */   {
/* 124 */     this.iChronology = DateTimeUtils.getChronology(paramChronology).withUTC();
/* 125 */     this.iTypes = new DateTimeFieldType[0];
/* 126 */     this.iValues = new int[0];
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
/*     */   public Partial(DateTimeFieldType paramDateTimeFieldType, int paramInt)
/*     */   {
/* 139 */     this(paramDateTimeFieldType, paramInt, null);
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
/*     */   public Partial(DateTimeFieldType paramDateTimeFieldType, int paramInt, Chronology paramChronology)
/*     */   {
/* 154 */     paramChronology = DateTimeUtils.getChronology(paramChronology).withUTC();
/* 155 */     this.iChronology = paramChronology;
/* 156 */     if (paramDateTimeFieldType == null) {
/* 157 */       throw new IllegalArgumentException("The field type must not be null");
/*     */     }
/* 159 */     this.iTypes = new DateTimeFieldType[] { paramDateTimeFieldType };
/* 160 */     this.iValues = new int[] { paramInt };
/* 161 */     paramChronology.validate(this, this.iValues);
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
/*     */   public Partial(DateTimeFieldType[] paramArrayOfDateTimeFieldType, int[] paramArrayOfInt)
/*     */   {
/* 175 */     this(paramArrayOfDateTimeFieldType, paramArrayOfInt, null);
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
/*     */   public Partial(DateTimeFieldType[] paramArrayOfDateTimeFieldType, int[] paramArrayOfInt, Chronology paramChronology)
/*     */   {
/* 191 */     paramChronology = DateTimeUtils.getChronology(paramChronology).withUTC();
/* 192 */     this.iChronology = paramChronology;
/* 193 */     if (paramArrayOfDateTimeFieldType == null) {
/* 194 */       throw new IllegalArgumentException("Types array must not be null");
/*     */     }
/* 196 */     if (paramArrayOfInt == null) {
/* 197 */       throw new IllegalArgumentException("Values array must not be null");
/*     */     }
/* 199 */     if (paramArrayOfInt.length != paramArrayOfDateTimeFieldType.length) {
/* 200 */       throw new IllegalArgumentException("Values array must be the same length as the types array");
/*     */     }
/* 202 */     if (paramArrayOfDateTimeFieldType.length == 0) {
/* 203 */       this.iTypes = paramArrayOfDateTimeFieldType;
/* 204 */       this.iValues = paramArrayOfInt;
/* 205 */       return;
/*     */     }
/* 207 */     for (int i = 0; i < paramArrayOfDateTimeFieldType.length; i++) {
/* 208 */       if (paramArrayOfDateTimeFieldType[i] == null) {
/* 209 */         throw new IllegalArgumentException("Types array must not contain null: index " + i);
/*     */       }
/*     */     }
/* 212 */     Object localObject = null;
/* 213 */     for (int j = 0; j < paramArrayOfDateTimeFieldType.length; j++) {
/* 214 */       DateTimeFieldType localDateTimeFieldType = paramArrayOfDateTimeFieldType[j];
/* 215 */       DurationField localDurationField1 = localDateTimeFieldType.getDurationType().getField(this.iChronology);
/* 216 */       if (j > 0) {
/* 217 */         int k = ((DurationField)localObject).compareTo(localDurationField1);
/* 218 */         if ((k < 0) || ((k != 0) && (!localDurationField1.isSupported()))) {
/* 219 */           throw new IllegalArgumentException("Types array must be in order largest-smallest: " + paramArrayOfDateTimeFieldType[(j - 1)].getName() + " < " + localDateTimeFieldType.getName());
/*     */         }
/* 221 */         if (k == 0) {
/* 222 */           if (paramArrayOfDateTimeFieldType[(j - 1)].getRangeDurationType() == null) {
/* 223 */             if (localDateTimeFieldType.getRangeDurationType() == null) {
/* 224 */               throw new IllegalArgumentException("Types array must not contain duplicate: " + localDateTimeFieldType.getName());
/*     */             }
/*     */           } else {
/* 227 */             if (localDateTimeFieldType.getRangeDurationType() == null) {
/* 228 */               throw new IllegalArgumentException("Types array must be in order largest-smallest: " + paramArrayOfDateTimeFieldType[(j - 1)].getName() + " < " + localDateTimeFieldType.getName());
/*     */             }
/*     */             
/* 231 */             DurationField localDurationField2 = paramArrayOfDateTimeFieldType[(j - 1)].getRangeDurationType().getField(this.iChronology);
/* 232 */             DurationField localDurationField3 = localDateTimeFieldType.getRangeDurationType().getField(this.iChronology);
/* 233 */             if (localDurationField2.compareTo(localDurationField3) < 0) {
/* 234 */               throw new IllegalArgumentException("Types array must be in order largest-smallest: " + paramArrayOfDateTimeFieldType[(j - 1)].getName() + " < " + localDateTimeFieldType.getName());
/*     */             }
/*     */             
/* 237 */             if (localDurationField2.compareTo(localDurationField3) == 0) {
/* 238 */               throw new IllegalArgumentException("Types array must not contain duplicate: " + localDateTimeFieldType.getName());
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 243 */       localObject = localDurationField1;
/*     */     }
/*     */     
/* 246 */     this.iTypes = ((DateTimeFieldType[])paramArrayOfDateTimeFieldType.clone());
/* 247 */     paramChronology.validate(this, paramArrayOfInt);
/* 248 */     this.iValues = ((int[])paramArrayOfInt.clone());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Partial(ReadablePartial paramReadablePartial)
/*     */   {
/* 259 */     if (paramReadablePartial == null) {
/* 260 */       throw new IllegalArgumentException("The partial must not be null");
/*     */     }
/* 262 */     this.iChronology = DateTimeUtils.getChronology(paramReadablePartial.getChronology()).withUTC();
/* 263 */     this.iTypes = new DateTimeFieldType[paramReadablePartial.size()];
/* 264 */     this.iValues = new int[paramReadablePartial.size()];
/* 265 */     for (int i = 0; i < paramReadablePartial.size(); i++) {
/* 266 */       this.iTypes[i] = paramReadablePartial.getFieldType(i);
/* 267 */       this.iValues[i] = paramReadablePartial.getValue(i);
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
/*     */   Partial(Partial paramPartial, int[] paramArrayOfInt)
/*     */   {
/* 281 */     this.iChronology = paramPartial.iChronology;
/* 282 */     this.iTypes = paramPartial.iTypes;
/* 283 */     this.iValues = paramArrayOfInt;
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
/*     */   Partial(Chronology paramChronology, DateTimeFieldType[] paramArrayOfDateTimeFieldType, int[] paramArrayOfInt)
/*     */   {
/* 297 */     this.iChronology = paramChronology;
/* 298 */     this.iTypes = paramArrayOfDateTimeFieldType;
/* 299 */     this.iValues = paramArrayOfInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 309 */     return this.iTypes.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Chronology getChronology()
/*     */   {
/* 321 */     return this.iChronology;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DateTimeField getField(int paramInt, Chronology paramChronology)
/*     */   {
/* 333 */     return this.iTypes[paramInt].getField(paramChronology);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeFieldType getFieldType(int paramInt)
/*     */   {
/* 344 */     return this.iTypes[paramInt];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeFieldType[] getFieldTypes()
/*     */   {
/* 356 */     return (DateTimeFieldType[])this.iTypes.clone();
/*     */   }
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
/* 368 */     return this.iValues[paramInt];
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
/*     */   public int[] getValues()
/*     */   {
/* 381 */     return (int[])this.iValues.clone();
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
/*     */   public Partial withChronologyRetainFields(Chronology paramChronology)
/*     */   {
/* 400 */     paramChronology = DateTimeUtils.getChronology(paramChronology);
/* 401 */     paramChronology = paramChronology.withUTC();
/* 402 */     if (paramChronology == getChronology()) {
/* 403 */       return this;
/*     */     }
/* 405 */     Partial localPartial = new Partial(paramChronology, this.iTypes, this.iValues);
/* 406 */     paramChronology.validate(localPartial, this.iValues);
/* 407 */     return localPartial;
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
/*     */   public Partial with(DateTimeFieldType paramDateTimeFieldType, int paramInt)
/*     */   {
/* 427 */     if (paramDateTimeFieldType == null) {
/* 428 */       throw new IllegalArgumentException("The field type must not be null");
/*     */     }
/* 430 */     int i = indexOf(paramDateTimeFieldType);
/* 431 */     if (i == -1) {
/* 432 */       localObject1 = new DateTimeFieldType[this.iTypes.length + 1];
/* 433 */       int[] arrayOfInt = new int[localObject1.length];
/*     */       
/*     */ 
/* 436 */       int j = 0;
/* 437 */       DurationField localDurationField1 = paramDateTimeFieldType.getDurationType().getField(this.iChronology);
/* 438 */       if (localDurationField1.isSupported()) {
/* 439 */         for (; j < this.iTypes.length; j++) {
/* 440 */           localObject2 = this.iTypes[j];
/* 441 */           DurationField localDurationField2 = ((DateTimeFieldType)localObject2).getDurationType().getField(this.iChronology);
/* 442 */           if (localDurationField2.isSupported()) {
/* 443 */             int k = localDurationField1.compareTo(localDurationField2);
/* 444 */             if (k > 0)
/*     */               break;
/* 446 */             if (k == 0) {
/* 447 */               DurationField localDurationField3 = paramDateTimeFieldType.getRangeDurationType().getField(this.iChronology);
/* 448 */               DurationField localDurationField4 = ((DateTimeFieldType)localObject2).getRangeDurationType().getField(this.iChronology);
/* 449 */               if (localDurationField3.compareTo(localDurationField4) > 0) {
/*     */                 break;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 456 */       System.arraycopy(this.iTypes, 0, localObject1, 0, j);
/* 457 */       System.arraycopy(this.iValues, 0, arrayOfInt, 0, j);
/* 458 */       localObject1[j] = paramDateTimeFieldType;
/* 459 */       arrayOfInt[j] = paramInt;
/* 460 */       System.arraycopy(this.iTypes, j, localObject1, j + 1, localObject1.length - j - 1);
/* 461 */       System.arraycopy(this.iValues, j, arrayOfInt, j + 1, arrayOfInt.length - j - 1);
/*     */       
/* 463 */       Object localObject2 = new Partial(this.iChronology, (DateTimeFieldType[])localObject1, arrayOfInt);
/* 464 */       this.iChronology.validate((ReadablePartial)localObject2, arrayOfInt);
/* 465 */       return (Partial)localObject2;
/*     */     }
/* 467 */     if (paramInt == getValue(i)) {
/* 468 */       return this;
/*     */     }
/* 470 */     Object localObject1 = getValues();
/* 471 */     localObject1 = getField(i).set(this, i, (int[])localObject1, paramInt);
/* 472 */     return new Partial(this, (int[])localObject1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Partial without(DateTimeFieldType paramDateTimeFieldType)
/*     */   {
/* 484 */     int i = indexOf(paramDateTimeFieldType);
/* 485 */     if (i != -1) {
/* 486 */       DateTimeFieldType[] arrayOfDateTimeFieldType = new DateTimeFieldType[size() - 1];
/* 487 */       int[] arrayOfInt = new int[size() - 1];
/* 488 */       System.arraycopy(this.iTypes, 0, arrayOfDateTimeFieldType, 0, i);
/* 489 */       System.arraycopy(this.iTypes, i + 1, arrayOfDateTimeFieldType, i, arrayOfDateTimeFieldType.length - i);
/* 490 */       System.arraycopy(this.iValues, 0, arrayOfInt, 0, i);
/* 491 */       System.arraycopy(this.iValues, i + 1, arrayOfInt, i, arrayOfInt.length - i);
/* 492 */       Partial localPartial = new Partial(this.iChronology, arrayOfDateTimeFieldType, arrayOfInt);
/* 493 */       this.iChronology.validate(localPartial, arrayOfInt);
/* 494 */       return localPartial;
/*     */     }
/* 496 */     return this;
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
/*     */   public Partial withField(DateTimeFieldType paramDateTimeFieldType, int paramInt)
/*     */   {
/* 515 */     int i = indexOfSupported(paramDateTimeFieldType);
/* 516 */     if (paramInt == getValue(i)) {
/* 517 */       return this;
/*     */     }
/* 519 */     int[] arrayOfInt = getValues();
/* 520 */     arrayOfInt = getField(i).set(this, i, arrayOfInt, paramInt);
/* 521 */     return new Partial(this, arrayOfInt);
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
/*     */   public Partial withFieldAdded(DurationFieldType paramDurationFieldType, int paramInt)
/*     */   {
/* 539 */     int i = indexOfSupported(paramDurationFieldType);
/* 540 */     if (paramInt == 0) {
/* 541 */       return this;
/*     */     }
/* 543 */     int[] arrayOfInt = getValues();
/* 544 */     arrayOfInt = getField(i).add(this, i, arrayOfInt, paramInt);
/* 545 */     return new Partial(this, arrayOfInt);
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
/*     */   public Partial withFieldAddWrapped(DurationFieldType paramDurationFieldType, int paramInt)
/*     */   {
/* 563 */     int i = indexOfSupported(paramDurationFieldType);
/* 564 */     if (paramInt == 0) {
/* 565 */       return this;
/*     */     }
/* 567 */     int[] arrayOfInt = getValues();
/* 568 */     arrayOfInt = getField(i).addWrapPartial(this, i, arrayOfInt, paramInt);
/* 569 */     return new Partial(this, arrayOfInt);
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
/*     */   public Partial withPeriodAdded(ReadablePeriod paramReadablePeriod, int paramInt)
/*     */   {
/* 588 */     if ((paramReadablePeriod == null) || (paramInt == 0)) {
/* 589 */       return this;
/*     */     }
/* 591 */     int[] arrayOfInt = getValues();
/* 592 */     for (int i = 0; i < paramReadablePeriod.size(); i++) {
/* 593 */       DurationFieldType localDurationFieldType = paramReadablePeriod.getFieldType(i);
/* 594 */       int j = indexOf(localDurationFieldType);
/* 595 */       if (j >= 0) {
/* 596 */         arrayOfInt = getField(j).add(this, j, arrayOfInt, FieldUtils.safeMultiply(paramReadablePeriod.getValue(i), paramInt));
/*     */       }
/*     */     }
/*     */     
/* 600 */     return new Partial(this, arrayOfInt);
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
/*     */   public Partial plus(ReadablePeriod paramReadablePeriod)
/*     */   {
/* 613 */     return withPeriodAdded(paramReadablePeriod, 1);
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
/*     */   public Partial minus(ReadablePeriod paramReadablePeriod)
/*     */   {
/* 626 */     return withPeriodAdded(paramReadablePeriod, -1);
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
/*     */   public Property property(DateTimeFieldType paramDateTimeFieldType)
/*     */   {
/* 641 */     return new Property(this, indexOfSupported(paramDateTimeFieldType));
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
/*     */   public boolean isMatch(ReadableInstant paramReadableInstant)
/*     */   {
/* 655 */     long l = DateTimeUtils.getInstantMillis(paramReadableInstant);
/* 656 */     Chronology localChronology = DateTimeUtils.getInstantChronology(paramReadableInstant);
/* 657 */     for (int i = 0; i < this.iTypes.length; i++) {
/* 658 */       int j = this.iTypes[i].getField(localChronology).get(l);
/* 659 */       if (j != this.iValues[i]) {
/* 660 */         return false;
/*     */       }
/*     */     }
/* 663 */     return true;
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
/*     */   public boolean isMatch(ReadablePartial paramReadablePartial)
/*     */   {
/* 679 */     if (paramReadablePartial == null) {
/* 680 */       throw new IllegalArgumentException("The partial must not be null");
/*     */     }
/* 682 */     for (int i = 0; i < this.iTypes.length; i++) {
/* 683 */       int j = paramReadablePartial.get(this.iTypes[i]);
/* 684 */       if (j != this.iValues[i]) {
/* 685 */         return false;
/*     */       }
/*     */     }
/* 688 */     return true;
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
/*     */   public DateTimeFormatter getFormatter()
/*     */   {
/* 704 */     DateTimeFormatter[] arrayOfDateTimeFormatter = this.iFormatter;
/* 705 */     if (arrayOfDateTimeFormatter == null) {
/* 706 */       if (size() == 0) {
/* 707 */         return null;
/*     */       }
/* 709 */       arrayOfDateTimeFormatter = new DateTimeFormatter[2];
/*     */       try {
/* 711 */         ArrayList localArrayList = new ArrayList(Arrays.asList(this.iTypes));
/* 712 */         arrayOfDateTimeFormatter[0] = ISODateTimeFormat.forFields(localArrayList, true, false);
/* 713 */         if (localArrayList.size() == 0) {
/* 714 */           arrayOfDateTimeFormatter[1] = arrayOfDateTimeFormatter[0];
/*     */         }
/*     */       }
/*     */       catch (IllegalArgumentException localIllegalArgumentException) {}
/*     */       
/* 719 */       this.iFormatter = arrayOfDateTimeFormatter;
/*     */     }
/* 721 */     return arrayOfDateTimeFormatter[0];
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
/*     */   public String toString()
/*     */   {
/* 737 */     DateTimeFormatter[] arrayOfDateTimeFormatter = this.iFormatter;
/* 738 */     if (arrayOfDateTimeFormatter == null) {
/* 739 */       getFormatter();
/* 740 */       arrayOfDateTimeFormatter = this.iFormatter;
/* 741 */       if (arrayOfDateTimeFormatter == null) {
/* 742 */         return toStringList();
/*     */       }
/*     */     }
/* 745 */     DateTimeFormatter localDateTimeFormatter = arrayOfDateTimeFormatter[1];
/* 746 */     if (localDateTimeFormatter == null) {
/* 747 */       return toStringList();
/*     */     }
/* 749 */     return localDateTimeFormatter.print(this);
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
/*     */   public String toStringList()
/*     */   {
/* 762 */     int i = size();
/* 763 */     StringBuffer localStringBuffer = new StringBuffer(20 * i);
/* 764 */     localStringBuffer.append('[');
/* 765 */     for (int j = 0; j < i; j++) {
/* 766 */       if (j > 0) {
/* 767 */         localStringBuffer.append(',').append(' ');
/*     */       }
/* 769 */       localStringBuffer.append(this.iTypes[j].getName());
/* 770 */       localStringBuffer.append('=');
/* 771 */       localStringBuffer.append(this.iValues[j]);
/*     */     }
/* 773 */     localStringBuffer.append(']');
/* 774 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString(String paramString)
/*     */   {
/* 785 */     if (paramString == null) {
/* 786 */       return toString();
/*     */     }
/* 788 */     return DateTimeFormat.forPattern(paramString).print(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString(String paramString, Locale paramLocale)
/*     */   {
/* 800 */     if (paramString == null) {
/* 801 */       return toString();
/*     */     }
/* 803 */     return DateTimeFormat.forPattern(paramString).withLocale(paramLocale).print(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static class Property
/*     */     extends AbstractPartialFieldProperty
/*     */     implements Serializable
/*     */   {
/*     */     private static final long serialVersionUID = 53278362873888L;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     private final Partial iPartial;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     private final int iFieldIndex;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     Property(Partial paramPartial, int paramInt)
/*     */     {
/* 833 */       this.iPartial = paramPartial;
/* 834 */       this.iFieldIndex = paramInt;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public DateTimeField getField()
/*     */     {
/* 843 */       return this.iPartial.getField(this.iFieldIndex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected ReadablePartial getReadablePartial()
/*     */     {
/* 852 */       return this.iPartial;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Partial getPartial()
/*     */     {
/* 861 */       return this.iPartial;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public int get()
/*     */     {
/* 870 */       return this.iPartial.getValue(this.iFieldIndex);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Partial addToCopy(int paramInt)
/*     */     {
/* 892 */       int[] arrayOfInt = this.iPartial.getValues();
/* 893 */       arrayOfInt = getField().add(this.iPartial, this.iFieldIndex, arrayOfInt, paramInt);
/* 894 */       return new Partial(this.iPartial, arrayOfInt);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Partial addWrapFieldToCopy(int paramInt)
/*     */     {
/* 916 */       int[] arrayOfInt = this.iPartial.getValues();
/* 917 */       arrayOfInt = getField().addWrapField(this.iPartial, this.iFieldIndex, arrayOfInt, paramInt);
/* 918 */       return new Partial(this.iPartial, arrayOfInt);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Partial setCopy(int paramInt)
/*     */     {
/* 933 */       int[] arrayOfInt = this.iPartial.getValues();
/* 934 */       arrayOfInt = getField().set(this.iPartial, this.iFieldIndex, arrayOfInt, paramInt);
/* 935 */       return new Partial(this.iPartial, arrayOfInt);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Partial setCopy(String paramString, Locale paramLocale)
/*     */     {
/* 950 */       int[] arrayOfInt = this.iPartial.getValues();
/* 951 */       arrayOfInt = getField().set(this.iPartial, this.iFieldIndex, arrayOfInt, paramString, paramLocale);
/* 952 */       return new Partial(this.iPartial, arrayOfInt);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Partial setCopy(String paramString)
/*     */     {
/* 966 */       return setCopy(paramString, null);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Partial withMaximumValue()
/*     */     {
/* 980 */       return setCopy(getMaximumValue());
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Partial withMinimumValue()
/*     */     {
/* 993 */       return setCopy(getMinimumValue());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\Partial.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */