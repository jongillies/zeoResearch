/*     */ package org.joda.time.chrono;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTime;
/*     */ import org.joda.time.DateTimeField;
/*     */ import org.joda.time.DateTimeFieldType;
/*     */ import org.joda.time.DateTimeZone;
/*     */ import org.joda.time.field.DelegatedDateTimeField;
/*     */ import org.joda.time.field.DividedDateTimeField;
/*     */ import org.joda.time.field.OffsetDateTimeField;
/*     */ import org.joda.time.field.RemainderDateTimeField;
/*     */ import org.joda.time.field.SkipUndoDateTimeField;
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
/*     */ 
/*     */ 
/*     */ public final class BuddhistChronology
/*     */   extends AssembledChronology
/*     */ {
/*     */   private static final long serialVersionUID = -3474595157769370126L;
/*     */   public static final int BE = 1;
/*  65 */   private static final DateTimeField ERA_FIELD = new BasicSingleEraDateTimeField("BE");
/*     */   
/*     */ 
/*     */   private static final int BUDDHIST_OFFSET = 543;
/*     */   
/*     */ 
/*  71 */   private static final Map cCache = new HashMap();
/*     */   
/*     */ 
/*  74 */   private static final BuddhistChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static BuddhistChronology getInstanceUTC()
/*     */   {
/*  84 */     return INSTANCE_UTC;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static BuddhistChronology getInstance()
/*     */   {
/*  93 */     return getInstance(DateTimeZone.getDefault());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static synchronized BuddhistChronology getInstance(DateTimeZone paramDateTimeZone)
/*     */   {
/* 104 */     if (paramDateTimeZone == null) {
/* 105 */       paramDateTimeZone = DateTimeZone.getDefault();
/*     */     }
/* 107 */     BuddhistChronology localBuddhistChronology = (BuddhistChronology)cCache.get(paramDateTimeZone);
/* 108 */     if (localBuddhistChronology == null)
/*     */     {
/* 110 */       localBuddhistChronology = new BuddhistChronology(GJChronology.getInstance(paramDateTimeZone, null), null);
/*     */       
/* 112 */       DateTime localDateTime = new DateTime(1, 1, 1, 0, 0, 0, 0, localBuddhistChronology);
/* 113 */       localBuddhistChronology = new BuddhistChronology(LimitChronology.getInstance(localBuddhistChronology, localDateTime, null), "");
/* 114 */       cCache.put(paramDateTimeZone, localBuddhistChronology);
/*     */     }
/* 116 */     return localBuddhistChronology;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private BuddhistChronology(Chronology paramChronology, Object paramObject)
/*     */   {
/* 128 */     super(paramChronology, paramObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 135 */     Chronology localChronology = getBase();
/* 136 */     return localChronology == null ? getInstanceUTC() : getInstance(localChronology.getZone());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Chronology withUTC()
/*     */   {
/* 147 */     return INSTANCE_UTC;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Chronology withZone(DateTimeZone paramDateTimeZone)
/*     */   {
/* 157 */     if (paramDateTimeZone == null) {
/* 158 */       paramDateTimeZone = DateTimeZone.getDefault();
/*     */     }
/* 160 */     if (paramDateTimeZone == getZone()) {
/* 161 */       return this;
/*     */     }
/* 163 */     return getInstance(paramDateTimeZone);
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
/* 174 */     return super.equals(paramObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 184 */     return "Buddhist".hashCode() * 11 + getZone().hashCode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 195 */     String str = "BuddhistChronology";
/* 196 */     DateTimeZone localDateTimeZone = getZone();
/* 197 */     if (localDateTimeZone != null) {
/* 198 */       str = str + '[' + localDateTimeZone.getID() + ']';
/*     */     }
/* 200 */     return str;
/*     */   }
/*     */   
/*     */   protected void assemble(AssembledChronology.Fields paramFields) {
/* 204 */     if (getParam() == null)
/*     */     {
/* 206 */       Object localObject = paramFields.year;
/* 207 */       paramFields.year = new OffsetDateTimeField(new SkipUndoDateTimeField(this, (DateTimeField)localObject), 543);
/*     */       
/*     */ 
/*     */ 
/* 211 */       localObject = paramFields.yearOfEra;
/* 212 */       paramFields.yearOfEra = new DelegatedDateTimeField(paramFields.year, DateTimeFieldType.yearOfEra());
/*     */       
/*     */ 
/*     */ 
/* 216 */       localObject = paramFields.weekyear;
/* 217 */       paramFields.weekyear = new OffsetDateTimeField(new SkipUndoDateTimeField(this, (DateTimeField)localObject), 543);
/*     */       
/*     */ 
/* 220 */       localObject = new OffsetDateTimeField(paramFields.yearOfEra, 99);
/* 221 */       paramFields.centuryOfEra = new DividedDateTimeField((DateTimeField)localObject, DateTimeFieldType.centuryOfEra(), 100);
/*     */       
/*     */ 
/* 224 */       localObject = new RemainderDateTimeField((DividedDateTimeField)paramFields.centuryOfEra);
/*     */       
/* 226 */       paramFields.yearOfCentury = new OffsetDateTimeField((DateTimeField)localObject, DateTimeFieldType.yearOfCentury(), 1);
/*     */       
/*     */ 
/* 229 */       localObject = new RemainderDateTimeField(paramFields.weekyear, DateTimeFieldType.weekyearOfCentury(), 100);
/*     */       
/* 231 */       paramFields.weekyearOfCentury = new OffsetDateTimeField((DateTimeField)localObject, DateTimeFieldType.weekyearOfCentury(), 1);
/*     */       
/*     */ 
/* 234 */       paramFields.era = ERA_FIELD;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\BuddhistChronology.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */