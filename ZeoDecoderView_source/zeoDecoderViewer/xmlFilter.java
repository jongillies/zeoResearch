/*    */ package zeoDecoderViewer;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileFilter;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class xmlFilter
/*    */   implements FileFilter
/*    */ {
/*    */   private String getExtension(File f)
/*    */   {
/* 13 */     String ext = null;
/* 14 */     String s = f.getName();
/* 15 */     int i = s.lastIndexOf('.');
/*    */     
/* 17 */     if ((i > 0) && (i < s.length() - 1)) {
/* 18 */       ext = s.substring(i + 1).toLowerCase();
/*    */     }
/* 20 */     return ext;
/*    */   }
/*    */   
/*    */   public boolean accept(File f)
/*    */   {
/* 25 */     return (!f.isDirectory()) && (getExtension(f).equals("xml"));
/*    */   }
/*    */   
/*    */   public String getDescription()
/*    */   {
/* 30 */     return "*.xml";
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\zeoDecoderViewer\xmlFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */