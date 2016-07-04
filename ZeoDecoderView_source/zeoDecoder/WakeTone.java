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
/*    */ public enum WakeTone
/*    */ {
/* 38 */   DUO, 
/* 39 */   FOREST, 
/* 40 */   SUNRISE, 
/* 41 */   MEADOW, 
/* 42 */   DAYBREAK;
/*    */   
/*    */   public static WakeTone convert(int i)
/*    */   {
/* 46 */     return values()[i];
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\zeoDecoder\WakeTone.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */