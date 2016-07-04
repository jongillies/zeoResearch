/*     */ package org.joda.time.field;
/*     */ 
/*     */ import org.joda.time.DateTimeField;
/*     */ import org.joda.time.DateTimeFieldType;
/*     */ import org.joda.time.IllegalFieldValueException;
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
/*     */ public class FieldUtils
/*     */ {
/*     */   public static int safeNegate(int paramInt)
/*     */   {
/*  49 */     if (paramInt == Integer.MIN_VALUE) {
/*  50 */       throw new ArithmeticException("Integer.MIN_VALUE cannot be negated");
/*     */     }
/*  52 */     return -paramInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int safeAdd(int paramInt1, int paramInt2)
/*     */   {
/*  64 */     int i = paramInt1 + paramInt2;
/*     */     
/*  66 */     if (((paramInt1 ^ i) < 0) && ((paramInt1 ^ paramInt2) >= 0)) {
/*  67 */       throw new ArithmeticException("The calculation caused an overflow: " + paramInt1 + " + " + paramInt2);
/*     */     }
/*     */     
/*  70 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long safeAdd(long paramLong1, long paramLong2)
/*     */   {
/*  82 */     long l = paramLong1 + paramLong2;
/*     */     
/*  84 */     if (((paramLong1 ^ l) < 0L) && ((paramLong1 ^ paramLong2) >= 0L)) {
/*  85 */       throw new ArithmeticException("The calculation caused an overflow: " + paramLong1 + " + " + paramLong2);
/*     */     }
/*     */     
/*  88 */     return l;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long safeSubtract(long paramLong1, long paramLong2)
/*     */   {
/* 100 */     long l = paramLong1 - paramLong2;
/*     */     
/* 102 */     if (((paramLong1 ^ l) < 0L) && ((paramLong1 ^ paramLong2) < 0L)) {
/* 103 */       throw new ArithmeticException("The calculation caused an overflow: " + paramLong1 + " - " + paramLong2);
/*     */     }
/*     */     
/* 106 */     return l;
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
/*     */   public static int safeMultiply(int paramInt1, int paramInt2)
/*     */   {
/* 119 */     long l = paramInt1 * paramInt2;
/* 120 */     if ((l < -2147483648L) || (l > 2147483647L)) {
/* 121 */       throw new ArithmeticException("The calculation caused an overflow: " + paramInt1 + " * " + paramInt2);
/*     */     }
/*     */     
/* 124 */     return (int)l;
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
/*     */   public static long safeMultiply(long paramLong, int paramInt)
/*     */   {
/* 137 */     switch (paramInt) {
/*     */     case -1: 
/* 139 */       return -paramLong;
/*     */     case 0: 
/* 141 */       return 0L;
/*     */     case 1: 
/* 143 */       return paramLong;
/*     */     }
/* 145 */     long l = paramLong * paramInt;
/* 146 */     if (l / paramInt != paramLong) {
/* 147 */       throw new ArithmeticException("The calculation caused an overflow: " + paramLong + " * " + paramInt);
/*     */     }
/*     */     
/* 150 */     return l;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long safeMultiply(long paramLong1, long paramLong2)
/*     */   {
/* 162 */     if (paramLong2 == 1L) {
/* 163 */       return paramLong1;
/*     */     }
/* 165 */     if (paramLong2 == 0L) {
/* 166 */       return 0L;
/*     */     }
/* 168 */     long l = paramLong1 * paramLong2;
/* 169 */     if (l / paramLong2 != paramLong1) {
/* 170 */       throw new ArithmeticException("The calculation caused an overflow: " + paramLong1 + " * " + paramLong2);
/*     */     }
/*     */     
/* 173 */     return l;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int safeToInt(long paramLong)
/*     */   {
/* 184 */     if ((-2147483648L <= paramLong) && (paramLong <= 2147483647L)) {
/* 185 */       return (int)paramLong;
/*     */     }
/* 187 */     throw new ArithmeticException("Value cannot fit in an int: " + paramLong);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int safeMultiplyToInt(long paramLong1, long paramLong2)
/*     */   {
/* 199 */     long l = safeMultiply(paramLong1, paramLong2);
/* 200 */     return safeToInt(l);
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
/*     */   public static void verifyValueBounds(DateTimeField paramDateTimeField, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 214 */     if ((paramInt1 < paramInt2) || (paramInt1 > paramInt3)) {
/* 215 */       throw new IllegalFieldValueException(paramDateTimeField.getType(), new Integer(paramInt1), new Integer(paramInt2), new Integer(paramInt3));
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
/*     */ 
/*     */   public static void verifyValueBounds(DateTimeFieldType paramDateTimeFieldType, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 232 */     if ((paramInt1 < paramInt2) || (paramInt1 > paramInt3)) {
/* 233 */       throw new IllegalFieldValueException(paramDateTimeFieldType, new Integer(paramInt1), new Integer(paramInt2), new Integer(paramInt3));
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
/*     */   public static void verifyValueBounds(String paramString, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 249 */     if ((paramInt1 < paramInt2) || (paramInt1 > paramInt3)) {
/* 250 */       throw new IllegalFieldValueException(paramString, new Integer(paramInt1), new Integer(paramInt2), new Integer(paramInt3));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int getWrappedValue(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 273 */     return getWrappedValue(paramInt1 + paramInt2, paramInt3, paramInt4);
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
/*     */ 
/*     */   public static int getWrappedValue(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 289 */     if (paramInt2 >= paramInt3) {
/* 290 */       throw new IllegalArgumentException("MIN > MAX");
/*     */     }
/*     */     
/* 293 */     int i = paramInt3 - paramInt2 + 1;
/* 294 */     paramInt1 -= paramInt2;
/*     */     
/* 296 */     if (paramInt1 >= 0) {
/* 297 */       return paramInt1 % i + paramInt2;
/*     */     }
/*     */     
/* 300 */     int j = -paramInt1 % i;
/*     */     
/* 302 */     if (j == 0) {
/* 303 */       return 0 + paramInt2;
/*     */     }
/* 305 */     return i - j + paramInt2;
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
/*     */   public static boolean equals(Object paramObject1, Object paramObject2)
/*     */   {
/* 318 */     if (paramObject1 == paramObject2) {
/* 319 */       return true;
/*     */     }
/* 321 */     if ((paramObject1 == null) || (paramObject2 == null)) {
/* 322 */       return false;
/*     */     }
/* 324 */     return paramObject1.equals(paramObject2);
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\field\FieldUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */