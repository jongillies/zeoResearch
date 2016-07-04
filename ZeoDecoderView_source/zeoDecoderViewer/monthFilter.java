/*    */ package zeoDecoderViewer;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileFilter;
/*    */ import java.text.DateFormatSymbols;
/*    */ 
/*    */ public class monthFilter
/*    */   implements FileFilter
/*    */ {
/*    */   public boolean accept(File f)
/*    */   {
/* 12 */     if (f.isDirectory()) {
/* 13 */       String[] months = new DateFormatSymbols().getMonths();
/* 14 */       String[] arrayOfString1; int j = (arrayOfString1 = months).length; for (int i = 0; i < j; i++) { String m = arrayOfString1[i];
/* 15 */         if (f.getName().equals(m)) {
/* 16 */           return true;
/*    */         }
/*    */       }
/* 19 */       return false;
/*    */     }
/* 21 */     return false;
/*    */   }
/*    */   
/*    */   public String getDescription()
/*    */   {
/* 26 */     return "months";
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\zeoDecoderViewer\monthFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */