/*    */ package org.joda.time.field;
/*    */ 
/*    */ import org.joda.time.DateTimeField;
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
/*    */ public class StrictDateTimeField
/*    */   extends DelegatedDateTimeField
/*    */ {
/*    */   private static final long serialVersionUID = 3154803964207950910L;
/*    */   
/*    */   public static DateTimeField getInstance(DateTimeField paramDateTimeField)
/*    */   {
/* 41 */     if (paramDateTimeField == null) {
/* 42 */       return null;
/*    */     }
/* 44 */     if ((paramDateTimeField instanceof LenientDateTimeField)) {
/* 45 */       paramDateTimeField = ((LenientDateTimeField)paramDateTimeField).getWrappedField();
/*    */     }
/* 47 */     if (!paramDateTimeField.isLenient()) {
/* 48 */       return paramDateTimeField;
/*    */     }
/* 50 */     return new StrictDateTimeField(paramDateTimeField);
/*    */   }
/*    */   
/*    */   protected StrictDateTimeField(DateTimeField paramDateTimeField) {
/* 54 */     super(paramDateTimeField);
/*    */   }
/*    */   
/*    */   public final boolean isLenient() {
/* 58 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public long set(long paramLong, int paramInt)
/*    */   {
/* 67 */     FieldUtils.verifyValueBounds(this, paramInt, getMinimumValue(paramLong), getMaximumValue(paramLong));
/*    */     
/* 69 */     return super.set(paramLong, paramInt);
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\field\StrictDateTimeField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */