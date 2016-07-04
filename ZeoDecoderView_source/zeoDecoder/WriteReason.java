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
/*    */ public enum WriteReason
/*    */ {
/* 39 */   FS_REASON_TENTATIVE_NIGHT_END, 
/*    */   
/*    */ 
/*    */ 
/*    */ 
/* 44 */   FS_REASON_NIGHT_END, 
/*    */   
/*    */ 
/*    */ 
/*    */ 
/* 49 */   FS_REASON_ALARM_OFF, 
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 55 */   FS_REASON_CARD_INSERT, 
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 61 */   FS_REASON_24_HOUR_UPDATE, 
/*    */   
/*    */ 
/*    */ 
/*    */ 
/* 66 */   FS_REASON_SLEEP_RATED;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static WriteReason convert(int i)
/*    */   {
/* 74 */     return values()[i];
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\zeoDecoder\WriteReason.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */