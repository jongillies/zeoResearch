/*    */ package org.joda.time.field;
/*    */ 
/*    */ import org.joda.time.Chronology;
/*    */ import org.joda.time.DateTimeField;
/*    */ import org.joda.time.DateTimeFieldType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class SkipUndoDateTimeField
/*    */   extends DelegatedDateTimeField
/*    */ {
/*    */   private static final long serialVersionUID = -5875876968979L;
/*    */   private final Chronology iChronology;
/*    */   private final int iSkip;
/*    */   private transient int iMinValue;
/*    */   
/*    */   public SkipUndoDateTimeField(Chronology paramChronology, DateTimeField paramDateTimeField)
/*    */   {
/* 53 */     this(paramChronology, paramDateTimeField, 0);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public SkipUndoDateTimeField(Chronology paramChronology, DateTimeField paramDateTimeField, int paramInt)
/*    */   {
/* 64 */     super(paramDateTimeField);
/* 65 */     this.iChronology = paramChronology;
/* 66 */     int i = super.getMinimumValue();
/* 67 */     if (i < paramInt) {
/* 68 */       this.iMinValue = (i + 1);
/* 69 */     } else if (i == paramInt + 1) {
/* 70 */       this.iMinValue = paramInt;
/*    */     } else {
/* 72 */       this.iMinValue = i;
/*    */     }
/* 74 */     this.iSkip = paramInt;
/*    */   }
/*    */   
/*    */   public int get(long paramLong)
/*    */   {
/* 79 */     int i = super.get(paramLong);
/* 80 */     if (i < this.iSkip) {
/* 81 */       i++;
/*    */     }
/* 83 */     return i;
/*    */   }
/*    */   
/*    */   public long set(long paramLong, int paramInt) {
/* 87 */     FieldUtils.verifyValueBounds(this, paramInt, this.iMinValue, getMaximumValue());
/* 88 */     if (paramInt <= this.iSkip) {
/* 89 */       paramInt--;
/*    */     }
/* 91 */     return super.set(paramLong, paramInt);
/*    */   }
/*    */   
/*    */   public int getMinimumValue() {
/* 95 */     return this.iMinValue;
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 99 */     return getType().getField(this.iChronology);
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\field\SkipUndoDateTimeField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */