/*    */ package zeoDecoderViewer;
/*    */ 
/*    */ import java.io.File;
/*    */ import javax.swing.filechooser.FileFilter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class datFilter
/*    */   extends FileFilter
/*    */ {
/*    */   public static String getExtension(File f)
/*    */   {
/* 16 */     String ext = null;
/* 17 */     String s = f.getName();
/* 18 */     int i = s.lastIndexOf('.');
/*    */     
/* 20 */     if ((i > 0) && (i < s.length() - 1)) {
/* 21 */       ext = s.substring(i + 1).toLowerCase();
/*    */     }
/* 23 */     return ext;
/*    */   }
/*    */   
/*    */   public boolean accept(File f)
/*    */   {
/* 28 */     if (f.isDirectory()) {
/* 29 */       return true;
/*    */     }
/*    */     
/* 32 */     String extension = getExtension(f);
/* 33 */     if ((extension != null) && (extension.equals("dat"))) {
/* 34 */       return true;
/*    */     }
/*    */     
/* 37 */     return false;
/*    */   }
/*    */   
/*    */   public String getDescription()
/*    */   {
/* 42 */     return "*.dat";
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\zeoDecoderViewer\datFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */