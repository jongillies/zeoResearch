/*    */ package org.joda.time.tz;
/*    */ 
/*    */ import java.text.DateFormatSymbols;
/*    */ import java.util.HashMap;
/*    */ import java.util.Locale;
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
/*    */ public class DefaultNameProvider
/*    */   implements NameProvider
/*    */ {
/* 33 */   private HashMap iByLocaleCache = createCache();
/*    */   
/*    */ 
/*    */ 
/*    */   public String getShortName(Locale paramLocale, String paramString1, String paramString2)
/*    */   {
/* 39 */     String[] arrayOfString = getNameSet(paramLocale, paramString1, paramString2);
/* 40 */     return arrayOfString == null ? null : arrayOfString[0];
/*    */   }
/*    */   
/*    */   public String getName(Locale paramLocale, String paramString1, String paramString2) {
/* 44 */     String[] arrayOfString = getNameSet(paramLocale, paramString1, paramString2);
/* 45 */     return arrayOfString == null ? null : arrayOfString[1];
/*    */   }
/*    */   
/*    */   private synchronized String[] getNameSet(Locale paramLocale, String paramString1, String paramString2) {
/* 49 */     if ((paramLocale == null) || (paramString1 == null) || (paramString2 == null)) {
/* 50 */       return null;
/*    */     }
/*    */     
/* 53 */     HashMap localHashMap1 = (HashMap)this.iByLocaleCache.get(paramLocale);
/* 54 */     if (localHashMap1 == null) {
/* 55 */       this.iByLocaleCache.put(paramLocale, localHashMap1 = createCache());
/*    */     }
/*    */     
/* 58 */     HashMap localHashMap2 = (HashMap)localHashMap1.get(paramString1);
/* 59 */     if (localHashMap2 == null) {
/* 60 */       localHashMap1.put(paramString1, localHashMap2 = createCache());
/* 61 */       String[][] arrayOfString = new DateFormatSymbols(paramLocale).getZoneStrings();
/* 62 */       for (int i = 0; i < arrayOfString.length; i++) {
/* 63 */         String[] arrayOfString1 = arrayOfString[i];
/* 64 */         if ((arrayOfString1 != null) && (arrayOfString1.length == 5) && (paramString1.equals(arrayOfString1[0]))) {
/* 65 */           localHashMap2.put(arrayOfString1[2], new String[] { arrayOfString1[2], arrayOfString1[1] });
/*    */           
/*    */ 
/*    */ 
/* 69 */           if (arrayOfString1[2].equals(arrayOfString1[4])) {
/* 70 */             localHashMap2.put(arrayOfString1[4] + "-Summer", new String[] { arrayOfString1[4], arrayOfString1[3] }); break;
/*    */           }
/* 72 */           localHashMap2.put(arrayOfString1[4], new String[] { arrayOfString1[4], arrayOfString1[3] });
/*    */           
/* 74 */           break;
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 79 */     return (String[])localHashMap2.get(paramString2);
/*    */   }
/*    */   
/*    */   private HashMap createCache() {
/* 83 */     return new HashMap(7);
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\tz\DefaultNameProvider.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */