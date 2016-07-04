/*     */ package org.joda.time.chrono;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.joda.time.Chronology;
/*     */ import org.joda.time.DateTimeField;
/*     */ import org.joda.time.DateTimeFieldType;
/*     */ import org.joda.time.DateTimeZone;
/*     */ import org.joda.time.field.DividedDateTimeField;
/*     */ import org.joda.time.field.RemainderDateTimeField;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ISOChronology
/*     */   extends AssembledChronology
/*     */ {
/*     */   private static final long serialVersionUID = -6212696554273812441L;
/*     */   private static final ISOChronology INSTANCE_UTC;
/*     */   private static final int FAST_CACHE_SIZE = 64;
/*     */   private static final ISOChronology[] cFastCache;
/*  63 */   private static final Map cCache = new HashMap();
/*     */   
/*  65 */   static { cFastCache = new ISOChronology[64];
/*  66 */     INSTANCE_UTC = new ISOChronology(GregorianChronology.getInstanceUTC());
/*  67 */     cCache.put(DateTimeZone.UTC, INSTANCE_UTC);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ISOChronology getInstanceUTC()
/*     */   {
/*  77 */     return INSTANCE_UTC;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ISOChronology getInstance()
/*     */   {
/*  86 */     return getInstance(DateTimeZone.getDefault());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ISOChronology getInstance(DateTimeZone paramDateTimeZone)
/*     */   {
/*  96 */     if (paramDateTimeZone == null) {
/*  97 */       paramDateTimeZone = DateTimeZone.getDefault();
/*     */     }
/*  99 */     int i = System.identityHashCode(paramDateTimeZone) & 0x3F;
/* 100 */     ISOChronology localISOChronology = cFastCache[i];
/* 101 */     if ((localISOChronology != null) && (localISOChronology.getZone() == paramDateTimeZone)) {
/* 102 */       return localISOChronology;
/*     */     }
/* 104 */     synchronized (cCache) {
/* 105 */       localISOChronology = (ISOChronology)cCache.get(paramDateTimeZone);
/* 106 */       if (localISOChronology == null) {
/* 107 */         localISOChronology = new ISOChronology(ZonedChronology.getInstance(INSTANCE_UTC, paramDateTimeZone));
/* 108 */         cCache.put(paramDateTimeZone, localISOChronology);
/*     */       }
/*     */     }
/* 111 */     cFastCache[i] = localISOChronology;
/* 112 */     return localISOChronology;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private ISOChronology(Chronology paramChronology)
/*     */   {
/* 122 */     super(paramChronology, null);
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
/* 133 */     return INSTANCE_UTC;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Chronology withZone(DateTimeZone paramDateTimeZone)
/*     */   {
/* 143 */     if (paramDateTimeZone == null) {
/* 144 */       paramDateTimeZone = DateTimeZone.getDefault();
/*     */     }
/* 146 */     if (paramDateTimeZone == getZone()) {
/* 147 */       return this;
/*     */     }
/* 149 */     return getInstance(paramDateTimeZone);
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
/* 160 */     String str = "ISOChronology";
/* 161 */     DateTimeZone localDateTimeZone = getZone();
/* 162 */     if (localDateTimeZone != null) {
/* 163 */       str = str + '[' + localDateTimeZone.getID() + ']';
/*     */     }
/* 165 */     return str;
/*     */   }
/*     */   
/*     */   protected void assemble(AssembledChronology.Fields paramFields) {
/* 169 */     if (getBase().getZone() == DateTimeZone.UTC)
/*     */     {
/* 171 */       paramFields.centuryOfEra = new DividedDateTimeField(ISOYearOfEraDateTimeField.INSTANCE, DateTimeFieldType.centuryOfEra(), 100);
/*     */       
/* 173 */       paramFields.yearOfCentury = new RemainderDateTimeField((DividedDateTimeField)paramFields.centuryOfEra, DateTimeFieldType.yearOfCentury());
/*     */       
/* 175 */       paramFields.weekyearOfCentury = new RemainderDateTimeField((DividedDateTimeField)paramFields.centuryOfEra, DateTimeFieldType.weekyearOfCentury());
/*     */       
/*     */ 
/* 178 */       paramFields.centuries = paramFields.centuryOfEra.getDurationField();
/*     */     }
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
/* 190 */     return super.equals(paramObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 200 */     return "ISO".hashCode() * 11 + getZone().hashCode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private Object writeReplace()
/*     */   {
/* 208 */     return new Stub(getZone());
/*     */   }
/*     */   
/*     */   private static final class Stub implements Serializable
/*     */   {
/*     */     private static final long serialVersionUID = -6212696554273812441L;
/*     */     private transient DateTimeZone iZone;
/*     */     
/*     */     Stub(DateTimeZone paramDateTimeZone) {
/* 217 */       this.iZone = paramDateTimeZone;
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 221 */       return ISOChronology.getInstance(this.iZone);
/*     */     }
/*     */     
/*     */     private void writeObject(ObjectOutputStream paramObjectOutputStream) throws IOException {
/* 225 */       paramObjectOutputStream.writeObject(this.iZone);
/*     */     }
/*     */     
/*     */     private void readObject(ObjectInputStream paramObjectInputStream)
/*     */       throws IOException, ClassNotFoundException
/*     */     {
/* 231 */       this.iZone = ((DateTimeZone)paramObjectInputStream.readObject());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\ISOChronology.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */