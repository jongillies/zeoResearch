/*     */ package org.joda.time.field;
/*     */ 
/*     */ import org.joda.time.DateTimeFieldType;
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
/*     */ public abstract class ImpreciseDateTimeField
/*     */   extends BaseDateTimeField
/*     */ {
/*     */   private static final long serialVersionUID = 7190739608550251860L;
/*     */   final long iUnitMillis;
/*     */   private final DurationField iDurationField;
/*     */   
/*     */   public ImpreciseDateTimeField(DateTimeFieldType paramDateTimeFieldType, long paramLong)
/*     */   {
/*  55 */     super(paramDateTimeFieldType);
/*  56 */     this.iUnitMillis = paramLong;
/*  57 */     this.iDurationField = new LinkedDurationField(paramDateTimeFieldType.getDurationType());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract int get(long paramLong);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract long set(long paramLong, int paramInt);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract long add(long paramLong, int paramInt);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract long add(long paramLong1, long paramLong2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDifference(long paramLong1, long paramLong2)
/*     */   {
/*  91 */     return FieldUtils.safeToInt(getDifferenceAsLong(paramLong1, paramLong2));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getDifferenceAsLong(long paramLong1, long paramLong2)
/*     */   {
/* 118 */     if (paramLong1 < paramLong2) {
/* 119 */       return -getDifferenceAsLong(paramLong2, paramLong1);
/*     */     }
/*     */     
/* 122 */     long l = (paramLong1 - paramLong2) / this.iUnitMillis;
/* 123 */     if (add(paramLong2, l) < paramLong1) {
/*     */       do {
/* 125 */         l += 1L;
/* 126 */       } while (add(paramLong2, l) <= paramLong1);
/* 127 */       l -= 1L;
/* 128 */     } else if (add(paramLong2, l) > paramLong1) {
/*     */       do {
/* 130 */         l -= 1L;
/* 131 */       } while (add(paramLong2, l) > paramLong1);
/*     */     }
/* 133 */     return l;
/*     */   }
/*     */   
/*     */   public final DurationField getDurationField() {
/* 137 */     return this.iDurationField;
/*     */   }
/*     */   
/*     */   public abstract DurationField getRangeDurationField();
/*     */   
/*     */   public abstract long roundFloor(long paramLong);
/*     */   
/*     */   protected final long getDurationUnitMillis() {
/* 145 */     return this.iUnitMillis;
/*     */   }
/*     */   
/*     */   private final class LinkedDurationField extends BaseDurationField {
/*     */     private static final long serialVersionUID = -203813474600094134L;
/*     */     
/*     */     LinkedDurationField(DurationFieldType paramDurationFieldType) {
/* 152 */       super();
/*     */     }
/*     */     
/*     */     public boolean isPrecise() {
/* 156 */       return false;
/*     */     }
/*     */     
/*     */     public long getUnitMillis() {
/* 160 */       return ImpreciseDateTimeField.this.iUnitMillis;
/*     */     }
/*     */     
/*     */     public int getValue(long paramLong1, long paramLong2) {
/* 164 */       return ImpreciseDateTimeField.this.getDifference(paramLong2 + paramLong1, paramLong2);
/*     */     }
/*     */     
/*     */     public long getValueAsLong(long paramLong1, long paramLong2)
/*     */     {
/* 169 */       return ImpreciseDateTimeField.this.getDifferenceAsLong(paramLong2 + paramLong1, paramLong2);
/*     */     }
/*     */     
/*     */     public long getMillis(int paramInt, long paramLong)
/*     */     {
/* 174 */       return ImpreciseDateTimeField.this.add(paramLong, paramInt) - paramLong;
/*     */     }
/*     */     
/*     */     public long getMillis(long paramLong1, long paramLong2) {
/* 178 */       return ImpreciseDateTimeField.this.add(paramLong2, paramLong1) - paramLong2;
/*     */     }
/*     */     
/*     */     public long add(long paramLong, int paramInt) {
/* 182 */       return ImpreciseDateTimeField.this.add(paramLong, paramInt);
/*     */     }
/*     */     
/*     */     public long add(long paramLong1, long paramLong2) {
/* 186 */       return ImpreciseDateTimeField.this.add(paramLong1, paramLong2);
/*     */     }
/*     */     
/*     */     public int getDifference(long paramLong1, long paramLong2) {
/* 190 */       return ImpreciseDateTimeField.this.getDifference(paramLong1, paramLong2);
/*     */     }
/*     */     
/*     */     public long getDifferenceAsLong(long paramLong1, long paramLong2)
/*     */     {
/* 195 */       return ImpreciseDateTimeField.this.getDifferenceAsLong(paramLong1, paramLong2);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\field\ImpreciseDateTimeField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */