/*    */ package zeoDecoderViewer;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileFilter;
/*    */ 
/*    */ 
/*    */ public class yearFilter
/*    */   implements FileFilter
/*    */ {
/*    */   public boolean accept(File f)
/*    */   {
/* 12 */     if (f.isDirectory()) {
/*    */       try {
/* 14 */         Integer value = Integer.valueOf(Integer.parseInt(f.getName()));
/* 15 */         return (value.intValue() > 2000) && (value.intValue() < 3000);
/*    */       } catch (NumberFormatException e) {
/* 17 */         return false;
/*    */       }
/*    */     }
/* 20 */     return false;
/*    */   }
/*    */   
/*    */   public String getDescription()
/*    */   {
/* 25 */     return "years";
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\zeoDecoderViewer\yearFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */