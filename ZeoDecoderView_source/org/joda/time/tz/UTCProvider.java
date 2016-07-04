/*    */ package org.joda.time.tz;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.Set;
/*    */ import org.joda.time.DateTimeZone;
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
/*    */ public final class UTCProvider
/*    */   implements Provider
/*    */ {
/*    */   public DateTimeZone getZone(String paramString)
/*    */   {
/* 44 */     if ("UTC".equalsIgnoreCase(paramString)) {
/* 45 */       return DateTimeZone.UTC;
/*    */     }
/* 47 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public Set getAvailableIDs()
/*    */   {
/* 54 */     return Collections.singleton("UTC");
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\tz\UTCProvider.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */