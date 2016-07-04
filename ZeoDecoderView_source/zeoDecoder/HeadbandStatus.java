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
/*    */ public enum HeadbandStatus
/*    */ {
/* 39 */   CHARGED, 
/* 40 */   CHARGING, 
/* 41 */   OFF_BASE, 
/* 42 */   NONE;
/*    */   
/*    */   public static HeadbandStatus convert(int i)
/*    */   {
/* 46 */     return values()[i];
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\zeoDecoder\HeadbandStatus.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */