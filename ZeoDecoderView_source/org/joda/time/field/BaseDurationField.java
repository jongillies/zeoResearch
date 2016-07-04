/*     */ package org.joda.time.field;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.joda.time.DurationField;
/*     */ import org.joda.time.DurationFieldType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BaseDurationField
/*     */   extends DurationField
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2554245107589433218L;
/*     */   private final DurationFieldType iType;
/*     */   
/*     */   protected BaseDurationField(DurationFieldType paramDurationFieldType)
/*     */   {
/*  47 */     if (paramDurationFieldType == null) {
/*  48 */       throw new IllegalArgumentException("The type must not be null");
/*     */     }
/*  50 */     this.iType = paramDurationFieldType;
/*     */   }
/*     */   
/*     */   public final DurationFieldType getType() {
/*  54 */     return this.iType;
/*     */   }
/*     */   
/*     */   public final String getName() {
/*  58 */     return this.iType.getName();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public final boolean isSupported()
/*     */   {
/*  65 */     return true;
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
/*     */   public int getValue(long paramLong)
/*     */   {
/*  78 */     return FieldUtils.safeToInt(getValueAsLong(paramLong));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getValueAsLong(long paramLong)
/*     */   {
/*  90 */     return paramLong / getUnitMillis();
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
/*     */   public int getValue(long paramLong1, long paramLong2)
/*     */   {
/* 110 */     return FieldUtils.safeToInt(getValueAsLong(paramLong1, paramLong2));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getMillis(int paramInt)
/*     */   {
/* 122 */     return paramInt * getUnitMillis();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getMillis(long paramLong)
/*     */   {
/* 134 */     return FieldUtils.safeMultiply(paramLong, getUnitMillis());
/*     */   }
/*     */   
/*     */ 
/*     */   public int getDifference(long paramLong1, long paramLong2)
/*     */   {
/* 140 */     return FieldUtils.safeToInt(getDifferenceAsLong(paramLong1, paramLong2));
/*     */   }
/*     */   
/*     */   public int compareTo(Object paramObject)
/*     */   {
/* 145 */     DurationField localDurationField = (DurationField)paramObject;
/* 146 */     long l1 = localDurationField.getUnitMillis();
/* 147 */     long l2 = getUnitMillis();
/*     */     
/* 149 */     if (l2 == l1) {
/* 150 */       return 0;
/*     */     }
/* 152 */     if (l2 < l1) {
/* 153 */       return -1;
/*     */     }
/* 155 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 165 */     return "DurationField[" + getName() + ']';
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\field\BaseDurationField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */