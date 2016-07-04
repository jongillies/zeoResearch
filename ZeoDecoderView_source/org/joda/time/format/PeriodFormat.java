/*    */ package org.joda.time.format;
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
/*    */ public class PeriodFormat
/*    */ {
/*    */   private static PeriodFormatter cEnglishWords;
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
/*    */   public static PeriodFormatter getDefault()
/*    */   {
/* 57 */     if (cEnglishWords == null) {
/* 58 */       String[] arrayOfString = { " ", ",", ",and ", ", and " };
/* 59 */       cEnglishWords = new PeriodFormatterBuilder().appendYears().appendSuffix(" year", " years").appendSeparator(", ", " and ", arrayOfString).appendMonths().appendSuffix(" month", " months").appendSeparator(", ", " and ", arrayOfString).appendWeeks().appendSuffix(" week", " weeks").appendSeparator(", ", " and ", arrayOfString).appendDays().appendSuffix(" day", " days").appendSeparator(", ", " and ", arrayOfString).appendHours().appendSuffix(" hour", " hours").appendSeparator(", ", " and ", arrayOfString).appendMinutes().appendSuffix(" minute", " minutes").appendSeparator(", ", " and ", arrayOfString).appendSeconds().appendSuffix(" second", " seconds").appendSeparator(", ", " and ", arrayOfString).appendMillis().appendSuffix(" millisecond", " milliseconds").toFormatter();
/*    */     }
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
/* 85 */     return cEnglishWords;
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\format\PeriodFormat.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */