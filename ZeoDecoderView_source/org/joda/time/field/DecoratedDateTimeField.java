/*     */ package org.joda.time.field;
/*     */ 
/*     */ import org.joda.time.DateTimeField;
/*     */ import org.joda.time.DateTimeFieldType;
/*     */ import org.joda.time.DurationField;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class DecoratedDateTimeField
/*     */   extends BaseDateTimeField
/*     */ {
/*     */   private static final long serialVersionUID = 203115783733757597L;
/*     */   private final DateTimeField iField;
/*     */   
/*     */   protected DecoratedDateTimeField(DateTimeField paramDateTimeField, DateTimeFieldType paramDateTimeFieldType)
/*     */   {
/*  54 */     super(paramDateTimeFieldType);
/*  55 */     if (paramDateTimeField == null) {
/*  56 */       throw new IllegalArgumentException("The field must not be null");
/*     */     }
/*  58 */     if (!paramDateTimeField.isSupported()) {
/*  59 */       throw new IllegalArgumentException("The field must be supported");
/*     */     }
/*  61 */     this.iField = paramDateTimeField;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final DateTimeField getWrappedField()
/*     */   {
/*  70 */     return this.iField;
/*     */   }
/*     */   
/*     */   public boolean isLenient() {
/*  74 */     return this.iField.isLenient();
/*     */   }
/*     */   
/*     */   public int get(long paramLong) {
/*  78 */     return this.iField.get(paramLong);
/*     */   }
/*     */   
/*     */   public long set(long paramLong, int paramInt) {
/*  82 */     return this.iField.set(paramLong, paramInt);
/*     */   }
/*     */   
/*     */   public DurationField getDurationField() {
/*  86 */     return this.iField.getDurationField();
/*     */   }
/*     */   
/*     */   public DurationField getRangeDurationField() {
/*  90 */     return this.iField.getRangeDurationField();
/*     */   }
/*     */   
/*     */   public int getMinimumValue() {
/*  94 */     return this.iField.getMinimumValue();
/*     */   }
/*     */   
/*     */   public int getMaximumValue() {
/*  98 */     return this.iField.getMaximumValue();
/*     */   }
/*     */   
/*     */   public long roundFloor(long paramLong) {
/* 102 */     return this.iField.roundFloor(paramLong);
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\field\DecoratedDateTimeField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */