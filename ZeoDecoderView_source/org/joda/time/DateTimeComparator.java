/*     */ package org.joda.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ import org.joda.time.convert.ConverterManager;
/*     */ import org.joda.time.convert.InstantConverter;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DateTimeComparator
/*     */   implements Comparator, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6097339773320178364L;
/*  54 */   private static final DateTimeComparator ALL_INSTANCE = new DateTimeComparator(null, null);
/*     */   
/*  56 */   private static final DateTimeComparator DATE_INSTANCE = new DateTimeComparator(DateTimeFieldType.dayOfYear(), null);
/*     */   
/*  58 */   private static final DateTimeComparator TIME_INSTANCE = new DateTimeComparator(null, DateTimeFieldType.dayOfYear());
/*     */   
/*     */ 
/*     */ 
/*     */   private final DateTimeFieldType iLowerLimit;
/*     */   
/*     */ 
/*     */ 
/*     */   private final DateTimeFieldType iUpperLimit;
/*     */   
/*     */ 
/*     */ 
/*     */   public static DateTimeComparator getInstance()
/*     */   {
/*  72 */     return ALL_INSTANCE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeComparator getInstance(DateTimeFieldType paramDateTimeFieldType)
/*     */   {
/*  83 */     return getInstance(paramDateTimeFieldType, null);
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
/*     */   public static DateTimeComparator getInstance(DateTimeFieldType paramDateTimeFieldType1, DateTimeFieldType paramDateTimeFieldType2)
/*     */   {
/*  98 */     if ((paramDateTimeFieldType1 == null) && (paramDateTimeFieldType2 == null)) {
/*  99 */       return ALL_INSTANCE;
/*     */     }
/* 101 */     if ((paramDateTimeFieldType1 == DateTimeFieldType.dayOfYear()) && (paramDateTimeFieldType2 == null)) {
/* 102 */       return DATE_INSTANCE;
/*     */     }
/* 104 */     if ((paramDateTimeFieldType1 == null) && (paramDateTimeFieldType2 == DateTimeFieldType.dayOfYear())) {
/* 105 */       return TIME_INSTANCE;
/*     */     }
/* 107 */     return new DateTimeComparator(paramDateTimeFieldType1, paramDateTimeFieldType2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeComparator getDateOnlyInstance()
/*     */   {
/* 117 */     return DATE_INSTANCE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateTimeComparator getTimeOnlyInstance()
/*     */   {
/* 127 */     return TIME_INSTANCE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DateTimeComparator(DateTimeFieldType paramDateTimeFieldType1, DateTimeFieldType paramDateTimeFieldType2)
/*     */   {
/* 138 */     this.iLowerLimit = paramDateTimeFieldType1;
/* 139 */     this.iUpperLimit = paramDateTimeFieldType2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeFieldType getLowerLimit()
/*     */   {
/* 149 */     return this.iLowerLimit;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTimeFieldType getUpperLimit()
/*     */   {
/* 158 */     return this.iUpperLimit;
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
/*     */   public int compare(Object paramObject1, Object paramObject2)
/*     */   {
/* 174 */     InstantConverter localInstantConverter = ConverterManager.getInstance().getInstantConverter(paramObject1);
/* 175 */     Chronology localChronology1 = localInstantConverter.getChronology(paramObject1, (Chronology)null);
/* 176 */     long l1 = localInstantConverter.getInstantMillis(paramObject1, localChronology1);
/*     */     
/* 178 */     localInstantConverter = ConverterManager.getInstance().getInstantConverter(paramObject2);
/* 179 */     Chronology localChronology2 = localInstantConverter.getChronology(paramObject2, (Chronology)null);
/* 180 */     long l2 = localInstantConverter.getInstantMillis(paramObject2, localChronology2);
/*     */     
/* 182 */     if (this.iLowerLimit != null) {
/* 183 */       l1 = this.iLowerLimit.getField(localChronology1).roundFloor(l1);
/* 184 */       l2 = this.iLowerLimit.getField(localChronology2).roundFloor(l2);
/*     */     }
/*     */     
/* 187 */     if (this.iUpperLimit != null) {
/* 188 */       l1 = this.iUpperLimit.getField(localChronology1).remainder(l1);
/* 189 */       l2 = this.iUpperLimit.getField(localChronology2).remainder(l2);
/*     */     }
/*     */     
/* 192 */     if (l1 < l2)
/* 193 */       return -1;
/* 194 */     if (l1 > l2) {
/* 195 */       return 1;
/*     */     }
/* 197 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 208 */     return getInstance(this.iLowerLimit, this.iUpperLimit);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 218 */     if ((paramObject instanceof DateTimeComparator)) {
/* 219 */       DateTimeComparator localDateTimeComparator = (DateTimeComparator)paramObject;
/* 220 */       return ((this.iLowerLimit == localDateTimeComparator.getLowerLimit()) || ((this.iLowerLimit != null) && (this.iLowerLimit.equals(localDateTimeComparator.getLowerLimit())))) && ((this.iUpperLimit == localDateTimeComparator.getUpperLimit()) || ((this.iUpperLimit != null) && (this.iUpperLimit.equals(localDateTimeComparator.getUpperLimit()))));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 225 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 234 */     return (this.iLowerLimit == null ? 0 : this.iLowerLimit.hashCode()) + 123 * (this.iUpperLimit == null ? 0 : this.iUpperLimit.hashCode());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 244 */     if (this.iLowerLimit == this.iUpperLimit) {
/* 245 */       return "DateTimeComparator[" + (this.iLowerLimit == null ? "" : this.iLowerLimit.getName()) + "]";
/*     */     }
/*     */     
/*     */ 
/* 249 */     return "DateTimeComparator[" + (this.iLowerLimit == null ? "" : this.iLowerLimit.getName()) + "-" + (this.iUpperLimit == null ? "" : this.iUpperLimit.getName()) + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\DateTimeComparator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */