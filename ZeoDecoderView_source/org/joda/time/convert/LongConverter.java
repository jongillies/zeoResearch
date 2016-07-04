/*    */ package org.joda.time.convert;
/*    */ 
/*    */ import org.joda.time.Chronology;
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
/*    */ class LongConverter
/*    */   extends AbstractConverter
/*    */   implements InstantConverter, PartialConverter, DurationConverter
/*    */ {
/* 34 */   static final LongConverter INSTANCE = new LongConverter();
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
/*    */   public long getInstantMillis(Object paramObject, Chronology paramChronology)
/*    */   {
/* 54 */     return ((Long)paramObject).longValue();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public long getDurationMillis(Object paramObject)
/*    */   {
/* 67 */     return ((Long)paramObject).longValue();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Class getSupportedType()
/*    */   {
/* 77 */     return Long.class;
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\convert\LongConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */