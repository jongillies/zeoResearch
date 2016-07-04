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
/*     */ 
/*     */ public class DelegatedDurationField
/*     */   extends DurationField
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -5576443481242007829L;
/*     */   private final DurationField iField;
/*     */   private final DurationFieldType iType;
/*     */   
/*     */   protected DelegatedDurationField(DurationField paramDurationField)
/*     */   {
/*  49 */     this(paramDurationField, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DelegatedDurationField(DurationField paramDurationField, DurationFieldType paramDurationFieldType)
/*     */   {
/*  60 */     if (paramDurationField == null) {
/*  61 */       throw new IllegalArgumentException("The field must not be null");
/*     */     }
/*  63 */     this.iField = paramDurationField;
/*  64 */     this.iType = (paramDurationFieldType == null ? paramDurationField.getType() : paramDurationFieldType);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final DurationField getWrappedField()
/*     */   {
/*  74 */     return this.iField;
/*     */   }
/*     */   
/*     */   public DurationFieldType getType() {
/*  78 */     return this.iType;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  82 */     return this.iType.getName();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isSupported()
/*     */   {
/*  89 */     return this.iField.isSupported();
/*     */   }
/*     */   
/*     */   public boolean isPrecise() {
/*  93 */     return this.iField.isPrecise();
/*     */   }
/*     */   
/*     */   public int getValue(long paramLong) {
/*  97 */     return this.iField.getValue(paramLong);
/*     */   }
/*     */   
/*     */   public long getValueAsLong(long paramLong) {
/* 101 */     return this.iField.getValueAsLong(paramLong);
/*     */   }
/*     */   
/*     */   public int getValue(long paramLong1, long paramLong2) {
/* 105 */     return this.iField.getValue(paramLong1, paramLong2);
/*     */   }
/*     */   
/*     */   public long getValueAsLong(long paramLong1, long paramLong2) {
/* 109 */     return this.iField.getValueAsLong(paramLong1, paramLong2);
/*     */   }
/*     */   
/*     */   public long getMillis(int paramInt) {
/* 113 */     return this.iField.getMillis(paramInt);
/*     */   }
/*     */   
/*     */   public long getMillis(long paramLong) {
/* 117 */     return this.iField.getMillis(paramLong);
/*     */   }
/*     */   
/*     */   public long getMillis(int paramInt, long paramLong) {
/* 121 */     return this.iField.getMillis(paramInt, paramLong);
/*     */   }
/*     */   
/*     */   public long getMillis(long paramLong1, long paramLong2) {
/* 125 */     return this.iField.getMillis(paramLong1, paramLong2);
/*     */   }
/*     */   
/*     */   public long add(long paramLong, int paramInt) {
/* 129 */     return this.iField.add(paramLong, paramInt);
/*     */   }
/*     */   
/*     */   public long add(long paramLong1, long paramLong2) {
/* 133 */     return this.iField.add(paramLong1, paramLong2);
/*     */   }
/*     */   
/*     */   public int getDifference(long paramLong1, long paramLong2) {
/* 137 */     return this.iField.getDifference(paramLong1, paramLong2);
/*     */   }
/*     */   
/*     */   public long getDifferenceAsLong(long paramLong1, long paramLong2) {
/* 141 */     return this.iField.getDifferenceAsLong(paramLong1, paramLong2);
/*     */   }
/*     */   
/*     */   public long getUnitMillis() {
/* 145 */     return this.iField.getUnitMillis();
/*     */   }
/*     */   
/*     */   public int compareTo(Object paramObject) {
/* 149 */     return this.iField.compareTo(paramObject);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 153 */     return "DurationField[" + this.iType + ']';
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\field\DelegatedDurationField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */