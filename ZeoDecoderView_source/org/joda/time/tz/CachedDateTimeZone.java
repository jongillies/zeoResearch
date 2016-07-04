/*     */ package org.joda.time.tz;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import org.joda.time.DateTimeZone;
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
/*     */ public class CachedDateTimeZone
/*     */   extends DateTimeZone
/*     */ {
/*     */   private static final long serialVersionUID = 5472298452022250685L;
/*     */   private static final int cInfoCacheMask;
/*     */   private final DateTimeZone iZone;
/*     */   private transient Info[] iInfoCache;
/*     */   
/*     */   static
/*     */   {
/*     */     Integer localInteger;
/*     */     try
/*     */     {
/*  39 */       localInteger = Integer.getInteger("org.joda.time.tz.CachedDateTimeZone.size");
/*     */     } catch (SecurityException localSecurityException) {
/*  41 */       localInteger = null;
/*     */     }
/*     */     
/*     */     int i;
/*  45 */     if (localInteger == null)
/*     */     {
/*     */ 
/*  48 */       i = 512;
/*     */     } else {
/*  50 */       i = localInteger.intValue();
/*     */       
/*  52 */       i--;
/*  53 */       int j = 0;
/*  54 */       while (i > 0) {
/*  55 */         j++;
/*  56 */         i >>= 1;
/*     */       }
/*  58 */       i = 1 << j;
/*     */     }
/*     */     
/*  61 */     cInfoCacheMask = i - 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static CachedDateTimeZone forZone(DateTimeZone paramDateTimeZone)
/*     */   {
/*  68 */     if ((paramDateTimeZone instanceof CachedDateTimeZone)) {
/*  69 */       return (CachedDateTimeZone)paramDateTimeZone;
/*     */     }
/*  71 */     return new CachedDateTimeZone(paramDateTimeZone);
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
/*     */   private CachedDateTimeZone(DateTimeZone paramDateTimeZone)
/*     */   {
/*  87 */     super(paramDateTimeZone.getID());
/*  88 */     this.iZone = paramDateTimeZone;
/*  89 */     this.iInfoCache = new Info[cInfoCacheMask + 1];
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream paramObjectInputStream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/*  95 */     paramObjectInputStream.defaultReadObject();
/*  96 */     this.iInfoCache = new Info[cInfoCacheMask + 1];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DateTimeZone getUncachedZone()
/*     */   {
/* 103 */     return this.iZone;
/*     */   }
/*     */   
/*     */   public String getNameKey(long paramLong) {
/* 107 */     return getInfo(paramLong).getNameKey(paramLong);
/*     */   }
/*     */   
/*     */   public int getOffset(long paramLong) {
/* 111 */     return getInfo(paramLong).getOffset(paramLong);
/*     */   }
/*     */   
/*     */   public int getStandardOffset(long paramLong) {
/* 115 */     return getInfo(paramLong).getStandardOffset(paramLong);
/*     */   }
/*     */   
/*     */   public boolean isFixed() {
/* 119 */     return this.iZone.isFixed();
/*     */   }
/*     */   
/*     */   public long nextTransition(long paramLong) {
/* 123 */     return this.iZone.nextTransition(paramLong);
/*     */   }
/*     */   
/*     */   public long previousTransition(long paramLong) {
/* 127 */     return this.iZone.previousTransition(paramLong);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 131 */     return this.iZone.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 135 */     if (this == paramObject) {
/* 136 */       return true;
/*     */     }
/* 138 */     if ((paramObject instanceof CachedDateTimeZone)) {
/* 139 */       return this.iZone.equals(((CachedDateTimeZone)paramObject).iZone);
/*     */     }
/* 141 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Info getInfo(long paramLong)
/*     */   {
/* 148 */     int i = (int)(paramLong >> 32);
/* 149 */     Info[] arrayOfInfo = this.iInfoCache;
/* 150 */     int j = i & cInfoCacheMask;
/* 151 */     Info localInfo = arrayOfInfo[j];
/* 152 */     if ((localInfo == null) || ((int)(localInfo.iPeriodStart >> 32) != i)) {
/* 153 */       localInfo = createInfo(paramLong);
/* 154 */       arrayOfInfo[j] = localInfo;
/*     */     }
/* 156 */     return localInfo;
/*     */   }
/*     */   
/*     */   private Info createInfo(long paramLong) {
/* 160 */     long l1 = paramLong & 0xFFFFFFFF00000000;
/* 161 */     Info localInfo1 = new Info(this.iZone, l1);
/*     */     
/* 163 */     long l2 = l1 | 0xFFFFFFFF;
/* 164 */     Info localInfo2 = localInfo1;
/*     */     for (;;) {
/* 166 */       long l3 = this.iZone.nextTransition(l1);
/* 167 */       if ((l3 == l1) || (l3 > l2)) {
/*     */         break;
/*     */       }
/* 170 */       l1 = l3;
/* 171 */       localInfo2 = localInfo2.iNextInfo = new Info(this.iZone, l1);
/*     */     }
/*     */     
/* 174 */     return localInfo1;
/*     */   }
/*     */   
/*     */ 
/*     */   private static final class Info
/*     */   {
/*     */     public final long iPeriodStart;
/*     */     
/*     */     public final DateTimeZone iZoneRef;
/*     */     Info iNextInfo;
/*     */     private String iNameKey;
/* 185 */     private int iOffset = Integer.MIN_VALUE;
/* 186 */     private int iStandardOffset = Integer.MIN_VALUE;
/*     */     
/*     */     Info(DateTimeZone paramDateTimeZone, long paramLong) {
/* 189 */       this.iPeriodStart = paramLong;
/* 190 */       this.iZoneRef = paramDateTimeZone;
/*     */     }
/*     */     
/*     */     public String getNameKey(long paramLong) {
/* 194 */       if ((this.iNextInfo == null) || (paramLong < this.iNextInfo.iPeriodStart)) {
/* 195 */         if (this.iNameKey == null) {
/* 196 */           this.iNameKey = this.iZoneRef.getNameKey(this.iPeriodStart);
/*     */         }
/* 198 */         return this.iNameKey;
/*     */       }
/* 200 */       return this.iNextInfo.getNameKey(paramLong);
/*     */     }
/*     */     
/*     */     public int getOffset(long paramLong) {
/* 204 */       if ((this.iNextInfo == null) || (paramLong < this.iNextInfo.iPeriodStart)) {
/* 205 */         if (this.iOffset == Integer.MIN_VALUE) {
/* 206 */           this.iOffset = this.iZoneRef.getOffset(this.iPeriodStart);
/*     */         }
/* 208 */         return this.iOffset;
/*     */       }
/* 210 */       return this.iNextInfo.getOffset(paramLong);
/*     */     }
/*     */     
/*     */     public int getStandardOffset(long paramLong) {
/* 214 */       if ((this.iNextInfo == null) || (paramLong < this.iNextInfo.iPeriodStart)) {
/* 215 */         if (this.iStandardOffset == Integer.MIN_VALUE) {
/* 216 */           this.iStandardOffset = this.iZoneRef.getStandardOffset(this.iPeriodStart);
/*     */         }
/* 218 */         return this.iStandardOffset;
/*     */       }
/* 220 */       return this.iNextInfo.getStandardOffset(paramLong);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\tz\CachedDateTimeZone.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */