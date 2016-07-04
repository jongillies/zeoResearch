/*    */ package org.joda.time.convert;
/*    */ 
/*    */ import org.joda.time.Chronology;
/*    */ import org.joda.time.PeriodType;
/*    */ import org.joda.time.ReadWritablePeriod;
/*    */ import org.joda.time.ReadablePeriod;
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
/*    */ class ReadablePeriodConverter
/*    */   extends AbstractConverter
/*    */   implements PeriodConverter
/*    */ {
/* 36 */   static final ReadablePeriodConverter INSTANCE = new ReadablePeriodConverter();
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
/*    */   public void setInto(ReadWritablePeriod paramReadWritablePeriod, Object paramObject, Chronology paramChronology)
/*    */   {
/* 58 */     paramReadWritablePeriod.setPeriod((ReadablePeriod)paramObject);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public PeriodType getPeriodType(Object paramObject)
/*    */   {
/* 70 */     ReadablePeriod localReadablePeriod = (ReadablePeriod)paramObject;
/* 71 */     return localReadablePeriod.getPeriodType();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Class getSupportedType()
/*    */   {
/* 81 */     return ReadablePeriod.class;
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\convert\ReadablePeriodConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */