/*     */ package org.joda.time.field;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import org.joda.time.DurationField;
/*     */ import org.joda.time.DurationFieldType;
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
/*     */ public final class UnsupportedDurationField
/*     */   extends DurationField
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6390301302770925357L;
/*     */   private static HashMap cCache;
/*     */   private final DurationFieldType iType;
/*     */   
/*     */   public static synchronized UnsupportedDurationField getInstance(DurationFieldType paramDurationFieldType)
/*     */   {
/*     */     UnsupportedDurationField localUnsupportedDurationField;
/*  48 */     if (cCache == null) {
/*  49 */       cCache = new HashMap(7);
/*  50 */       localUnsupportedDurationField = null;
/*     */     } else {
/*  52 */       localUnsupportedDurationField = (UnsupportedDurationField)cCache.get(paramDurationFieldType);
/*     */     }
/*  54 */     if (localUnsupportedDurationField == null) {
/*  55 */       localUnsupportedDurationField = new UnsupportedDurationField(paramDurationFieldType);
/*  56 */       cCache.put(paramDurationFieldType, localUnsupportedDurationField);
/*     */     }
/*  58 */     return localUnsupportedDurationField;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private UnsupportedDurationField(DurationFieldType paramDurationFieldType)
/*     */   {
/*  70 */     this.iType = paramDurationFieldType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final DurationFieldType getType()
/*     */   {
/*  78 */     return this.iType;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  82 */     return this.iType.getName();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isSupported()
/*     */   {
/*  91 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isPrecise()
/*     */   {
/* 100 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getValue(long paramLong)
/*     */   {
/* 109 */     throw unsupported();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getValueAsLong(long paramLong)
/*     */   {
/* 118 */     throw unsupported();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getValue(long paramLong1, long paramLong2)
/*     */   {
/* 127 */     throw unsupported();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getValueAsLong(long paramLong1, long paramLong2)
/*     */   {
/* 136 */     throw unsupported();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getMillis(int paramInt)
/*     */   {
/* 145 */     throw unsupported();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getMillis(long paramLong)
/*     */   {
/* 154 */     throw unsupported();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getMillis(int paramInt, long paramLong)
/*     */   {
/* 163 */     throw unsupported();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getMillis(long paramLong1, long paramLong2)
/*     */   {
/* 172 */     throw unsupported();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long add(long paramLong, int paramInt)
/*     */   {
/* 181 */     throw unsupported();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long add(long paramLong1, long paramLong2)
/*     */   {
/* 190 */     throw unsupported();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDifference(long paramLong1, long paramLong2)
/*     */   {
/* 199 */     throw unsupported();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getDifferenceAsLong(long paramLong1, long paramLong2)
/*     */   {
/* 208 */     throw unsupported();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getUnitMillis()
/*     */   {
/* 217 */     return 0L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int compareTo(Object paramObject)
/*     */   {
/* 226 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 237 */     if (this == paramObject)
/* 238 */       return true;
/* 239 */     if ((paramObject instanceof UnsupportedDurationField)) {
/* 240 */       UnsupportedDurationField localUnsupportedDurationField = (UnsupportedDurationField)paramObject;
/* 241 */       if (localUnsupportedDurationField.getName() == null) {
/* 242 */         return getName() == null;
/*     */       }
/* 244 */       return localUnsupportedDurationField.getName().equals(getName());
/*     */     }
/* 246 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 255 */     return getName().hashCode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 264 */     return "UnsupportedDurationField[" + getName() + ']';
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 271 */     return getInstance(this.iType);
/*     */   }
/*     */   
/*     */   private UnsupportedOperationException unsupported() {
/* 275 */     return new UnsupportedOperationException(this.iType + " field is unsupported");
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\field\UnsupportedDurationField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */