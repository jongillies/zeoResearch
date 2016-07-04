/*    */ package org.joda.time.convert;
/*    */ 
/*    */ import java.util.Date;
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
/*    */ final class DateConverter
/*    */   extends AbstractConverter
/*    */   implements InstantConverter, PartialConverter
/*    */ {
/* 35 */   static final DateConverter INSTANCE = new DateConverter();
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
/* 55 */     Date localDate = (Date)paramObject;
/* 56 */     return localDate.getTime();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Class getSupportedType()
/*    */   {
/* 66 */     return Date.class;
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\convert\DateConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */