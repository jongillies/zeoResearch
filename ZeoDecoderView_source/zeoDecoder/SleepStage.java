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
/*    */ public enum SleepStage
/*    */ {
/* 39 */   UNDEFINED, 
/* 40 */   WAKE, 
/* 41 */   REM, 
/* 42 */   LIGHT, 
/* 43 */   DEEP, 
/* 44 */   UNUSED, 
/* 45 */   DEEP_2;
/*    */   
/*    */   public static SleepStage convert(int i)
/*    */   {
/* 49 */     return values()[i];
/*    */   }
/*    */   
/*    */   public boolean is_sleep()
/*    */   {
/* 54 */     if ((this == REM) || 
/* 55 */       (this == LIGHT) || 
/* 56 */       (this == DEEP) || 
/* 57 */       (this == DEEP_2)) {
/* 58 */       return true;
/*    */     }
/* 60 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean is_wake()
/*    */   {
/* 66 */     if (this == WAKE) {
/* 67 */       return true;
/*    */     }
/* 69 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\zeoDecoder\SleepStage.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */