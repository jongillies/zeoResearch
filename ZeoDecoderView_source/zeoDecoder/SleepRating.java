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
/*    */ public enum SleepRating
/*    */ {
/* 40 */   UNKNOWN, 
/* 41 */   TERRIBLY, 
/* 42 */   POORLY, 
/* 43 */   OKAY, 
/* 44 */   WELL, 
/* 45 */   GREAT;
/*    */   
/*    */   public static SleepRating convert(int i)
/*    */   {
/* 49 */     return values()[i];
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\zeoDecoder\SleepRating.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */