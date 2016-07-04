/*     */ package org.joda.time.format;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FormatUtils
/*     */ {
/*  31 */   private static final double LOG_10 = Math.log(10.0D);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void appendPaddedInteger(StringBuffer paramStringBuffer, int paramInt1, int paramInt2)
/*     */   {
/*  50 */     if (paramInt1 < 0) {
/*  51 */       paramStringBuffer.append('-');
/*  52 */       if (paramInt1 != Integer.MIN_VALUE) {
/*  53 */         paramInt1 = -paramInt1;
/*     */       } else {
/*  55 */         for (; paramInt2 > 10; paramInt2--) {
/*  56 */           paramStringBuffer.append('0');
/*     */         }
/*  58 */         paramStringBuffer.append("2147483648");
/*  59 */         return;
/*     */       }
/*     */     }
/*  62 */     if (paramInt1 < 10) {
/*  63 */       for (; paramInt2 > 1; paramInt2--) {
/*  64 */         paramStringBuffer.append('0');
/*     */       }
/*  66 */       paramStringBuffer.append((char)(paramInt1 + 48)); } else { int i;
/*  67 */       if (paramInt1 < 100) {
/*  68 */         for (; paramInt2 > 2; paramInt2--) {
/*  69 */           paramStringBuffer.append('0');
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*  74 */         i = (paramInt1 + 1) * 13421772 >> 27;
/*  75 */         paramStringBuffer.append((char)(i + 48));
/*     */         
/*  77 */         paramStringBuffer.append((char)(paramInt1 - (i << 3) - (i << 1) + 48));
/*     */       }
/*     */       else {
/*  80 */         if (paramInt1 < 1000) {
/*  81 */           i = 3;
/*  82 */         } else if (paramInt1 < 10000) {
/*  83 */           i = 4;
/*     */         } else {
/*  85 */           i = (int)(Math.log(paramInt1) / LOG_10) + 1;
/*     */         }
/*  87 */         for (; paramInt2 > i; paramInt2--) {
/*  88 */           paramStringBuffer.append('0');
/*     */         }
/*  90 */         paramStringBuffer.append(Integer.toString(paramInt1));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void appendPaddedInteger(StringBuffer paramStringBuffer, long paramLong, int paramInt)
/*     */   {
/* 105 */     int i = (int)paramLong;
/* 106 */     if (i == paramLong) {
/* 107 */       appendPaddedInteger(paramStringBuffer, i, paramInt);
/* 108 */     } else if (paramInt <= 19) {
/* 109 */       paramStringBuffer.append(Long.toString(paramLong));
/*     */     } else {
/* 111 */       if (paramLong < 0L) {
/* 112 */         paramStringBuffer.append('-');
/* 113 */         if (paramLong != Long.MIN_VALUE) {
/* 114 */           paramLong = -paramLong;
/*     */         } else {
/* 116 */           for (; paramInt > 19; paramInt--) {
/* 117 */             paramStringBuffer.append('0');
/*     */           }
/* 119 */           paramStringBuffer.append("9223372036854775808");
/* 120 */           return;
/*     */         }
/*     */       }
/* 123 */       int j = (int)(Math.log(paramLong) / LOG_10) + 1;
/* 124 */       for (; paramInt > j; paramInt--) {
/* 125 */         paramStringBuffer.append('0');
/*     */       }
/* 127 */       paramStringBuffer.append(Long.toString(paramLong));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void writePaddedInteger(Writer paramWriter, int paramInt1, int paramInt2)
/*     */     throws IOException
/*     */   {
/* 144 */     if (paramInt1 < 0) {
/* 145 */       paramWriter.write(45);
/* 146 */       if (paramInt1 != Integer.MIN_VALUE) {
/* 147 */         paramInt1 = -paramInt1;
/*     */       } else {
/* 149 */         for (; paramInt2 > 10; paramInt2--) {
/* 150 */           paramWriter.write(48);
/*     */         }
/* 152 */         paramWriter.write("2147483648");
/* 153 */         return;
/*     */       }
/*     */     }
/* 156 */     if (paramInt1 < 10) {
/* 157 */       for (; paramInt2 > 1; paramInt2--) {
/* 158 */         paramWriter.write(48);
/*     */       }
/* 160 */       paramWriter.write(paramInt1 + 48); } else { int i;
/* 161 */       if (paramInt1 < 100) {
/* 162 */         for (; paramInt2 > 2; paramInt2--) {
/* 163 */           paramWriter.write(48);
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 168 */         i = (paramInt1 + 1) * 13421772 >> 27;
/* 169 */         paramWriter.write(i + 48);
/*     */         
/* 171 */         paramWriter.write(paramInt1 - (i << 3) - (i << 1) + 48);
/*     */       }
/*     */       else {
/* 174 */         if (paramInt1 < 1000) {
/* 175 */           i = 3;
/* 176 */         } else if (paramInt1 < 10000) {
/* 177 */           i = 4;
/*     */         } else {
/* 179 */           i = (int)(Math.log(paramInt1) / LOG_10) + 1;
/*     */         }
/* 181 */         for (; paramInt2 > i; paramInt2--) {
/* 182 */           paramWriter.write(48);
/*     */         }
/* 184 */         paramWriter.write(Integer.toString(paramInt1));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void writePaddedInteger(Writer paramWriter, long paramLong, int paramInt)
/*     */     throws IOException
/*     */   {
/* 201 */     int i = (int)paramLong;
/* 202 */     if (i == paramLong) {
/* 203 */       writePaddedInteger(paramWriter, i, paramInt);
/* 204 */     } else if (paramInt <= 19) {
/* 205 */       paramWriter.write(Long.toString(paramLong));
/*     */     } else {
/* 207 */       if (paramLong < 0L) {
/* 208 */         paramWriter.write(45);
/* 209 */         if (paramLong != Long.MIN_VALUE) {
/* 210 */           paramLong = -paramLong;
/*     */         } else {
/* 212 */           for (; paramInt > 19; paramInt--) {
/* 213 */             paramWriter.write(48);
/*     */           }
/* 215 */           paramWriter.write("9223372036854775808");
/* 216 */           return;
/*     */         }
/*     */       }
/* 219 */       int j = (int)(Math.log(paramLong) / LOG_10) + 1;
/* 220 */       for (; paramInt > j; paramInt--) {
/* 221 */         paramWriter.write(48);
/*     */       }
/* 223 */       paramWriter.write(Long.toString(paramLong));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void appendUnpaddedInteger(StringBuffer paramStringBuffer, int paramInt)
/*     */   {
/* 236 */     if (paramInt < 0) {
/* 237 */       paramStringBuffer.append('-');
/* 238 */       if (paramInt != Integer.MIN_VALUE) {
/* 239 */         paramInt = -paramInt;
/*     */       } else {
/* 241 */         paramStringBuffer.append("2147483648");
/* 242 */         return;
/*     */       }
/*     */     }
/* 245 */     if (paramInt < 10) {
/* 246 */       paramStringBuffer.append((char)(paramInt + 48));
/* 247 */     } else if (paramInt < 100)
/*     */     {
/*     */ 
/*     */ 
/* 251 */       int i = (paramInt + 1) * 13421772 >> 27;
/* 252 */       paramStringBuffer.append((char)(i + 48));
/*     */       
/* 254 */       paramStringBuffer.append((char)(paramInt - (i << 3) - (i << 1) + 48));
/*     */     } else {
/* 256 */       paramStringBuffer.append(Integer.toString(paramInt));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void appendUnpaddedInteger(StringBuffer paramStringBuffer, long paramLong)
/*     */   {
/* 269 */     int i = (int)paramLong;
/* 270 */     if (i == paramLong) {
/* 271 */       appendUnpaddedInteger(paramStringBuffer, i);
/*     */     } else {
/* 273 */       paramStringBuffer.append(Long.toString(paramLong));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void writeUnpaddedInteger(Writer paramWriter, int paramInt)
/*     */     throws IOException
/*     */   {
/* 288 */     if (paramInt < 0) {
/* 289 */       paramWriter.write(45);
/* 290 */       if (paramInt != Integer.MIN_VALUE) {
/* 291 */         paramInt = -paramInt;
/*     */       } else {
/* 293 */         paramWriter.write("2147483648");
/* 294 */         return;
/*     */       }
/*     */     }
/* 297 */     if (paramInt < 10) {
/* 298 */       paramWriter.write(paramInt + 48);
/* 299 */     } else if (paramInt < 100)
/*     */     {
/*     */ 
/*     */ 
/* 303 */       int i = (paramInt + 1) * 13421772 >> 27;
/* 304 */       paramWriter.write(i + 48);
/*     */       
/* 306 */       paramWriter.write(paramInt - (i << 3) - (i << 1) + 48);
/*     */     } else {
/* 308 */       paramWriter.write(Integer.toString(paramInt));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void writeUnpaddedInteger(Writer paramWriter, long paramLong)
/*     */     throws IOException
/*     */   {
/* 323 */     int i = (int)paramLong;
/* 324 */     if (i == paramLong) {
/* 325 */       writeUnpaddedInteger(paramWriter, i);
/*     */     } else {
/* 327 */       paramWriter.write(Long.toString(paramLong));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int calculateDigitCount(long paramLong)
/*     */   {
/* 336 */     if (paramLong < 0L) {
/* 337 */       if (paramLong != Long.MIN_VALUE) {
/* 338 */         return calculateDigitCount(-paramLong) + 1;
/*     */       }
/* 340 */       return 20;
/*     */     }
/*     */     
/* 343 */     return paramLong < 10000L ? 4 : paramLong < 1000L ? 3 : paramLong < 100L ? 2 : paramLong < 10L ? 1 : (int)(Math.log(paramLong) / LOG_10) + 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static int parseTwoDigits(String paramString, int paramInt)
/*     */   {
/* 352 */     int i = paramString.charAt(paramInt) - '0';
/* 353 */     return (i << 3) + (i << 1) + paramString.charAt(paramInt + 1) - 48;
/*     */   }
/*     */   
/*     */   static String createErrorMessage(String paramString, int paramInt) {
/* 357 */     int i = paramInt + 32;
/*     */     String str;
/* 359 */     if (paramString.length() <= i + 3) {
/* 360 */       str = paramString;
/*     */     } else {
/* 362 */       str = paramString.substring(0, i).concat("...");
/*     */     }
/*     */     
/* 365 */     if (paramInt <= 0) {
/* 366 */       return "Invalid format: \"" + str + '"';
/*     */     }
/*     */     
/* 369 */     if (paramInt >= paramString.length()) {
/* 370 */       return "Invalid format: \"" + str + "\" is too short";
/*     */     }
/*     */     
/* 373 */     return "Invalid format: \"" + str + "\" is malformed at \"" + str.substring(paramInt) + '"';
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\format\FormatUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */