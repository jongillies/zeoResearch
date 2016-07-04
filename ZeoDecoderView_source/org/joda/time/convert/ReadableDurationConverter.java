/*    */ package org.joda.time.convert;
/*    */ 
/*    */ import org.joda.time.Chronology;
/*    */ import org.joda.time.DateTimeUtils;
/*    */ import org.joda.time.ReadWritablePeriod;
/*    */ import org.joda.time.ReadableDuration;
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
/*    */ class ReadableDurationConverter
/*    */   extends AbstractConverter
/*    */   implements DurationConverter, PeriodConverter
/*    */ {
/* 36 */   static final ReadableDurationConverter INSTANCE = new ReadableDurationConverter();
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
/*    */   public long getDurationMillis(Object paramObject)
/*    */   {
/* 56 */     return ((ReadableDuration)paramObject).getMillis();
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
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setInto(ReadWritablePeriod paramReadWritablePeriod, Object paramObject, Chronology paramChronology)
/*    */   {
/* 72 */     ReadableDuration localReadableDuration = (ReadableDuration)paramObject;
/* 73 */     paramChronology = DateTimeUtils.getChronology(paramChronology);
/* 74 */     long l = localReadableDuration.getMillis();
/* 75 */     int[] arrayOfInt = paramChronology.get(paramReadWritablePeriod, l);
/* 76 */     for (int i = 0; i < arrayOfInt.length; i++) {
/* 77 */       paramReadWritablePeriod.setValue(i, arrayOfInt[i]);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Class getSupportedType()
/*    */   {
/* 88 */     return ReadableDuration.class;
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\convert\ReadableDurationConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */