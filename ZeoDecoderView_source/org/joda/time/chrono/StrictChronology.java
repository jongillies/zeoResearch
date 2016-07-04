/*     */ package org.joda.time.chrono;
/*     */ 
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTimeField;
/*     */ import org.joda.time.DateTimeZone;
/*     */ import org.joda.time.field.StrictDateTimeField;
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
/*     */ public final class StrictChronology
/*     */   extends AssembledChronology
/*     */ {
/*     */   private static final long serialVersionUID = 6633006628097111960L;
/*     */   private transient Chronology iWithUTC;
/*     */   
/*     */   public static StrictChronology getInstance(Chronology paramChronology)
/*     */   {
/*  45 */     if (paramChronology == null) {
/*  46 */       throw new IllegalArgumentException("Must supply a chronology");
/*     */     }
/*  48 */     return new StrictChronology(paramChronology);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private StrictChronology(Chronology paramChronology)
/*     */   {
/*  59 */     super(paramChronology, null);
/*     */   }
/*     */   
/*     */   public Chronology withUTC() {
/*  63 */     if (this.iWithUTC == null) {
/*  64 */       if (getZone() == DateTimeZone.UTC) {
/*  65 */         this.iWithUTC = this;
/*     */       } else {
/*  67 */         this.iWithUTC = getInstance(getBase().withUTC());
/*     */       }
/*     */     }
/*  70 */     return this.iWithUTC;
/*     */   }
/*     */   
/*     */   public Chronology withZone(DateTimeZone paramDateTimeZone) {
/*  74 */     if (paramDateTimeZone == null) {
/*  75 */       paramDateTimeZone = DateTimeZone.getDefault();
/*     */     }
/*  77 */     if (paramDateTimeZone == DateTimeZone.UTC) {
/*  78 */       return withUTC();
/*     */     }
/*  80 */     if (paramDateTimeZone == getZone()) {
/*  81 */       return this;
/*     */     }
/*  83 */     return getInstance(getBase().withZone(paramDateTimeZone));
/*     */   }
/*     */   
/*     */   protected void assemble(AssembledChronology.Fields paramFields) {
/*  87 */     paramFields.year = convertField(paramFields.year);
/*  88 */     paramFields.yearOfEra = convertField(paramFields.yearOfEra);
/*  89 */     paramFields.yearOfCentury = convertField(paramFields.yearOfCentury);
/*  90 */     paramFields.centuryOfEra = convertField(paramFields.centuryOfEra);
/*  91 */     paramFields.era = convertField(paramFields.era);
/*  92 */     paramFields.dayOfWeek = convertField(paramFields.dayOfWeek);
/*  93 */     paramFields.dayOfMonth = convertField(paramFields.dayOfMonth);
/*  94 */     paramFields.dayOfYear = convertField(paramFields.dayOfYear);
/*  95 */     paramFields.monthOfYear = convertField(paramFields.monthOfYear);
/*  96 */     paramFields.weekOfWeekyear = convertField(paramFields.weekOfWeekyear);
/*  97 */     paramFields.weekyear = convertField(paramFields.weekyear);
/*  98 */     paramFields.weekyearOfCentury = convertField(paramFields.weekyearOfCentury);
/*     */     
/* 100 */     paramFields.millisOfSecond = convertField(paramFields.millisOfSecond);
/* 101 */     paramFields.millisOfDay = convertField(paramFields.millisOfDay);
/* 102 */     paramFields.secondOfMinute = convertField(paramFields.secondOfMinute);
/* 103 */     paramFields.secondOfDay = convertField(paramFields.secondOfDay);
/* 104 */     paramFields.minuteOfHour = convertField(paramFields.minuteOfHour);
/* 105 */     paramFields.minuteOfDay = convertField(paramFields.minuteOfDay);
/* 106 */     paramFields.hourOfDay = convertField(paramFields.hourOfDay);
/* 107 */     paramFields.hourOfHalfday = convertField(paramFields.hourOfHalfday);
/* 108 */     paramFields.clockhourOfDay = convertField(paramFields.clockhourOfDay);
/* 109 */     paramFields.clockhourOfHalfday = convertField(paramFields.clockhourOfHalfday);
/* 110 */     paramFields.halfdayOfDay = convertField(paramFields.halfdayOfDay);
/*     */   }
/*     */   
/*     */   private static final DateTimeField convertField(DateTimeField paramDateTimeField) {
/* 114 */     return StrictDateTimeField.getInstance(paramDateTimeField);
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
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 127 */     if (this == paramObject) {
/* 128 */       return true;
/*     */     }
/* 130 */     if (!(paramObject instanceof StrictChronology)) {
/* 131 */       return false;
/*     */     }
/* 133 */     StrictChronology localStrictChronology = (StrictChronology)paramObject;
/* 134 */     return getBase().equals(localStrictChronology.getBase());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 144 */     return 352831696 + getBase().hashCode() * 7;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 153 */     return "StrictChronology[" + getBase().toString() + ']';
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\StrictChronology.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */