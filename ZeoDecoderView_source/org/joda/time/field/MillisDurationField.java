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
/*     */ public final class MillisDurationField
/*     */   extends DurationField
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2656707858124633367L;
/*  38 */   public static final DurationField INSTANCE = new MillisDurationField();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DurationFieldType getType()
/*     */   {
/*  49 */     return DurationFieldType.millis();
/*     */   }
/*     */   
/*     */   public String getName() {
/*  53 */     return "millis";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isSupported()
/*     */   {
/*  62 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final boolean isPrecise()
/*     */   {
/*  71 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final long getUnitMillis()
/*     */   {
/*  80 */     return 1L;
/*     */   }
/*     */   
/*     */   public int getValue(long paramLong)
/*     */   {
/*  85 */     return FieldUtils.safeToInt(paramLong);
/*     */   }
/*     */   
/*     */   public long getValueAsLong(long paramLong) {
/*  89 */     return paramLong;
/*     */   }
/*     */   
/*     */   public int getValue(long paramLong1, long paramLong2) {
/*  93 */     return FieldUtils.safeToInt(paramLong1);
/*     */   }
/*     */   
/*     */   public long getValueAsLong(long paramLong1, long paramLong2) {
/*  97 */     return paramLong1;
/*     */   }
/*     */   
/*     */   public long getMillis(int paramInt) {
/* 101 */     return paramInt;
/*     */   }
/*     */   
/*     */   public long getMillis(long paramLong) {
/* 105 */     return paramLong;
/*     */   }
/*     */   
/*     */   public long getMillis(int paramInt, long paramLong) {
/* 109 */     return paramInt;
/*     */   }
/*     */   
/*     */   public long getMillis(long paramLong1, long paramLong2) {
/* 113 */     return paramLong1;
/*     */   }
/*     */   
/*     */   public long add(long paramLong, int paramInt) {
/* 117 */     return FieldUtils.safeAdd(paramLong, paramInt);
/*     */   }
/*     */   
/*     */   public long add(long paramLong1, long paramLong2) {
/* 121 */     return FieldUtils.safeAdd(paramLong1, paramLong2);
/*     */   }
/*     */   
/*     */   public int getDifference(long paramLong1, long paramLong2) {
/* 125 */     return FieldUtils.safeToInt(FieldUtils.safeSubtract(paramLong1, paramLong2));
/*     */   }
/*     */   
/*     */   public long getDifferenceAsLong(long paramLong1, long paramLong2) {
/* 129 */     return FieldUtils.safeSubtract(paramLong1, paramLong2);
/*     */   }
/*     */   
/*     */   public int compareTo(Object paramObject)
/*     */   {
/* 134 */     DurationField localDurationField = (DurationField)paramObject;
/* 135 */     long l1 = localDurationField.getUnitMillis();
/* 136 */     long l2 = getUnitMillis();
/*     */     
/* 138 */     if (l2 == l1) {
/* 139 */       return 0;
/*     */     }
/* 141 */     if (l2 < l1) {
/* 142 */       return -1;
/*     */     }
/* 144 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 154 */     return "DurationField[millis]";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 161 */     return INSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\field\MillisDurationField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */