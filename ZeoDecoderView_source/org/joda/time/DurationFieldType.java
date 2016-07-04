/*     */ package org.joda.time;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public abstract class DurationFieldType
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8765135187319L;
/*     */   static final byte ERAS = 1;
/*     */   static final byte CENTURIES = 2;
/*     */   static final byte WEEKYEARS = 3;
/*     */   static final byte YEARS = 4;
/*     */   static final byte MONTHS = 5;
/*     */   static final byte WEEKS = 6;
/*     */   static final byte DAYS = 7;
/*     */   static final byte HALFDAYS = 8;
/*     */   static final byte HOURS = 9;
/*     */   static final byte MINUTES = 10;
/*     */   static final byte SECONDS = 11;
/*     */   static final byte MILLIS = 12;
/*  60 */   static final DurationFieldType ERAS_TYPE = new StandardDurationFieldType("eras", (byte)1);
/*     */   
/*  62 */   static final DurationFieldType CENTURIES_TYPE = new StandardDurationFieldType("centuries", (byte)2);
/*     */   
/*  64 */   static final DurationFieldType WEEKYEARS_TYPE = new StandardDurationFieldType("weekyears", (byte)3);
/*     */   
/*  66 */   static final DurationFieldType YEARS_TYPE = new StandardDurationFieldType("years", (byte)4);
/*     */   
/*  68 */   static final DurationFieldType MONTHS_TYPE = new StandardDurationFieldType("months", (byte)5);
/*     */   
/*  70 */   static final DurationFieldType WEEKS_TYPE = new StandardDurationFieldType("weeks", (byte)6);
/*     */   
/*  72 */   static final DurationFieldType DAYS_TYPE = new StandardDurationFieldType("days", (byte)7);
/*     */   
/*  74 */   static final DurationFieldType HALFDAYS_TYPE = new StandardDurationFieldType("halfdays", (byte)8);
/*     */   
/*  76 */   static final DurationFieldType HOURS_TYPE = new StandardDurationFieldType("hours", (byte)9);
/*     */   
/*  78 */   static final DurationFieldType MINUTES_TYPE = new StandardDurationFieldType("minutes", (byte)10);
/*     */   
/*  80 */   static final DurationFieldType SECONDS_TYPE = new StandardDurationFieldType("seconds", (byte)11);
/*     */   
/*  82 */   static final DurationFieldType MILLIS_TYPE = new StandardDurationFieldType("millis", (byte)12);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private final String iName;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DurationFieldType(String paramString)
/*     */   {
/*  95 */     this.iName = paramString;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DurationFieldType millis()
/*     */   {
/* 105 */     return MILLIS_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DurationFieldType seconds()
/*     */   {
/* 114 */     return SECONDS_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DurationFieldType minutes()
/*     */   {
/* 123 */     return MINUTES_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DurationFieldType hours()
/*     */   {
/* 132 */     return HOURS_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DurationFieldType halfdays()
/*     */   {
/* 141 */     return HALFDAYS_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DurationFieldType days()
/*     */   {
/* 151 */     return DAYS_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DurationFieldType weeks()
/*     */   {
/* 160 */     return WEEKS_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DurationFieldType weekyears()
/*     */   {
/* 169 */     return WEEKYEARS_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DurationFieldType months()
/*     */   {
/* 178 */     return MONTHS_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DurationFieldType years()
/*     */   {
/* 187 */     return YEARS_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DurationFieldType centuries()
/*     */   {
/* 196 */     return CENTURIES_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DurationFieldType eras()
/*     */   {
/* 205 */     return ERAS_TYPE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 216 */     return this.iName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract DurationField getField(Chronology paramChronology);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isSupported(Chronology paramChronology)
/*     */   {
/* 234 */     return getField(paramChronology).isSupported();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 243 */     return getName();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static class StandardDurationFieldType
/*     */     extends DurationFieldType
/*     */   {
/*     */     private static final long serialVersionUID = 31156755687123L;
/*     */     
/*     */ 
/*     */     private final byte iOrdinal;
/*     */     
/*     */ 
/*     */     StandardDurationFieldType(String paramString, byte paramByte)
/*     */     {
/* 259 */       super();
/* 260 */       this.iOrdinal = paramByte;
/*     */     }
/*     */     
/*     */     public DurationField getField(Chronology paramChronology) {
/* 264 */       paramChronology = DateTimeUtils.getChronology(paramChronology);
/*     */       
/* 266 */       switch (this.iOrdinal) {
/*     */       case 1: 
/* 268 */         return paramChronology.eras();
/*     */       case 2: 
/* 270 */         return paramChronology.centuries();
/*     */       case 3: 
/* 272 */         return paramChronology.weekyears();
/*     */       case 4: 
/* 274 */         return paramChronology.years();
/*     */       case 5: 
/* 276 */         return paramChronology.months();
/*     */       case 6: 
/* 278 */         return paramChronology.weeks();
/*     */       case 7: 
/* 280 */         return paramChronology.days();
/*     */       case 8: 
/* 282 */         return paramChronology.halfdays();
/*     */       case 9: 
/* 284 */         return paramChronology.hours();
/*     */       case 10: 
/* 286 */         return paramChronology.minutes();
/*     */       case 11: 
/* 288 */         return paramChronology.seconds();
/*     */       case 12: 
/* 290 */         return paramChronology.millis();
/*     */       }
/*     */       
/* 293 */       throw new InternalError();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private Object readResolve()
/*     */     {
/* 303 */       switch (this.iOrdinal) {
/*     */       case 1: 
/* 305 */         return ERAS_TYPE;
/*     */       case 2: 
/* 307 */         return CENTURIES_TYPE;
/*     */       case 3: 
/* 309 */         return WEEKYEARS_TYPE;
/*     */       case 4: 
/* 311 */         return YEARS_TYPE;
/*     */       case 5: 
/* 313 */         return MONTHS_TYPE;
/*     */       case 6: 
/* 315 */         return WEEKS_TYPE;
/*     */       case 7: 
/* 317 */         return DAYS_TYPE;
/*     */       case 8: 
/* 319 */         return HALFDAYS_TYPE;
/*     */       case 9: 
/* 321 */         return HOURS_TYPE;
/*     */       case 10: 
/* 323 */         return MINUTES_TYPE;
/*     */       case 11: 
/* 325 */         return SECONDS_TYPE;
/*     */       case 12: 
/* 327 */         return MILLIS_TYPE;
/*     */       }
/*     */       
/* 330 */       return this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\DurationFieldType.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */