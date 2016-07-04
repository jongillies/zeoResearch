/*    */ package zeoDecoder;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum AlarmReason
/*    */ {
/* 47 */   REM_TO_NREM_TRANSITION, 
/* 48 */   NREM_TO_REM_TRANSITION, 
/* 49 */   WAKE_ON_WAKE, 
/* 50 */   DEEP_RISING, 
/* 51 */   END_OF_WAKE_WINDOW, 
/* 52 */   NO_ALARM;
/*    */   
/*    */   public static AlarmReason convert(int i)
/*    */   {
/* 56 */     return values()[i];
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\zeoDecoder\AlarmReason.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */