/*     */ package org.joda.time.base;
/*     */ 
/*     */ import org.joda.time.DurationFieldType;
/*     */ import org.joda.time.MutablePeriod;
/*     */ import org.joda.time.Period;
/*     */ import org.joda.time.PeriodType;
/*     */ import org.joda.time.ReadablePeriod;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractPeriod
/*     */   implements ReadablePeriod
/*     */ {
/*     */   public DurationFieldType[] getFieldTypes()
/*     */   {
/*  56 */     DurationFieldType[] arrayOfDurationFieldType = new DurationFieldType[size()];
/*  57 */     for (int i = 0; i < arrayOfDurationFieldType.length; i++) {
/*  58 */       arrayOfDurationFieldType[i] = getFieldType(i);
/*     */     }
/*  60 */     return arrayOfDurationFieldType;
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
/*  72 */     int[] arrayOfInt = new int[size()];
/*  73 */     for (int i = 0; i < arrayOfInt.length; i++) {
/*  74 */       arrayOfInt[i] = getValue(i);
/*     */     }
/*  76 */     return arrayOfInt;
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
/*     */   public int get(DurationFieldType paramDurationFieldType)
/*     */   {
/*  90 */     int i = indexOf(paramDurationFieldType);
/*  91 */     if (i == -1) {
/*  92 */       return 0;
/*     */     }
/*  94 */     return getValue(i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isSupported(DurationFieldType paramDurationFieldType)
/*     */   {
/* 104 */     return getPeriodType().isSupported(paramDurationFieldType);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int indexOf(DurationFieldType paramDurationFieldType)
/*     */   {
/* 114 */     return getPeriodType().indexOf(paramDurationFieldType);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Period toPeriod()
/*     */   {
/* 124 */     return new Period(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MutablePeriod toMutablePeriod()
/*     */   {
/* 135 */     return new MutablePeriod(this);
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
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 160 */     if (this == paramObject) {
/* 161 */       return true;
/*     */     }
/* 163 */     if (!(paramObject instanceof ReadablePeriod)) {
/* 164 */       return false;
/*     */     }
/* 166 */     ReadablePeriod localReadablePeriod = (ReadablePeriod)paramObject;
/* 167 */     if (size() != localReadablePeriod.size()) {
/* 168 */       return false;
/*     */     }
/* 170 */     int i = 0; for (int j = size(); i < j; i++) {
/* 171 */       if ((getValue(i) != localReadablePeriod.getValue(i)) || (getFieldType(i) != localReadablePeriod.getFieldType(i))) {
/* 172 */         return false;
/*     */       }
/*     */     }
/* 175 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 184 */     int i = 17;
/* 185 */     int j = 0; for (int k = size(); j < k; j++) {
/* 186 */       i = 27 * i + getValue(j);
/* 187 */       i = 27 * i + getFieldType(j).hashCode();
/*     */     }
/* 189 */     return i;
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
/*     */   public String toString()
/*     */   {
/* 204 */     return ISOPeriodFormat.standard().print(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString(PeriodFormatter paramPeriodFormatter)
/*     */   {
/* 216 */     if (paramPeriodFormatter == null) {
/* 217 */       return toString();
/*     */     }
/* 219 */     return paramPeriodFormatter.print(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\base\AbstractPeriod.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */