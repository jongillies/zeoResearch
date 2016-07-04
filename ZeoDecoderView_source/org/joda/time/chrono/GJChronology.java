/*      */ package org.joda.time.chrono;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import org.joda.time.Chronology;
/*      */ import org.joda.time.DateTimeField;
/*      */ import org.joda.time.DateTimeFieldType;
/*      */ import org.joda.time.DateTimeUtils;
/*      */ import org.joda.time.DateTimeZone;
/*      */ import org.joda.time.DurationField;
/*      */ import org.joda.time.IllegalFieldValueException;
/*      */ import org.joda.time.Instant;
/*      */ import org.joda.time.ReadableInstant;
/*      */ import org.joda.time.ReadablePartial;
/*      */ import org.joda.time.field.BaseDateTimeField;
/*      */ import org.joda.time.field.DecoratedDurationField;
/*      */ import org.joda.time.format.DateTimeFormatter;
/*      */ import org.joda.time.format.ISODateTimeFormat;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class GJChronology
/*      */   extends AssembledChronology
/*      */ {
/*      */   private static final long serialVersionUID = -2545574827706931671L;
/*      */   
/*      */   private static long convertByYear(long paramLong, Chronology paramChronology1, Chronology paramChronology2)
/*      */   {
/*   83 */     return paramChronology2.getDateTimeMillis(paramChronology1.year().get(paramLong), paramChronology1.monthOfYear().get(paramLong), paramChronology1.dayOfMonth().get(paramLong), paramChronology1.millisOfDay().get(paramLong));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static long convertByWeekyear(long paramLong, Chronology paramChronology1, Chronology paramChronology2)
/*      */   {
/*   95 */     long l = paramChronology2.weekyear().set(0L, paramChronology1.weekyear().get(paramLong));
/*   96 */     l = paramChronology2.weekOfWeekyear().set(l, paramChronology1.weekOfWeekyear().get(paramLong));
/*   97 */     l = paramChronology2.dayOfWeek().set(l, paramChronology1.dayOfWeek().get(paramLong));
/*   98 */     l = paramChronology2.millisOfDay().set(l, paramChronology1.millisOfDay().get(paramLong));
/*   99 */     return l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  105 */   static final Instant DEFAULT_CUTOVER = new Instant(-12219292800000L);
/*      */   
/*      */ 
/*  108 */   private static final Map cCache = new HashMap();
/*      */   
/*      */   private JulianChronology iJulianChronology;
/*      */   
/*      */   private GregorianChronology iGregorianChronology;
/*      */   
/*      */   private Instant iCutoverInstant;
/*      */   
/*      */   private long iCutoverMillis;
/*      */   
/*      */   private long iGapDuration;
/*      */   
/*      */ 
/*      */   public static GJChronology getInstanceUTC()
/*      */   {
/*  123 */     return getInstance(DateTimeZone.UTC, DEFAULT_CUTOVER, 4);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static GJChronology getInstance()
/*      */   {
/*  139 */     return getInstance(DateTimeZone.getDefault(), DEFAULT_CUTOVER, 4);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static GJChronology getInstance(DateTimeZone paramDateTimeZone)
/*      */   {
/*  155 */     return getInstance(paramDateTimeZone, DEFAULT_CUTOVER, 4);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static GJChronology getInstance(DateTimeZone paramDateTimeZone, ReadableInstant paramReadableInstant)
/*      */   {
/*  173 */     return getInstance(paramDateTimeZone, paramReadableInstant, 4);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static synchronized GJChronology getInstance(DateTimeZone paramDateTimeZone, ReadableInstant paramReadableInstant, int paramInt)
/*      */   {
/*  189 */     paramDateTimeZone = DateTimeUtils.getZone(paramDateTimeZone);
/*      */     Instant localInstant;
/*  191 */     if (paramReadableInstant == null) {
/*  192 */       localInstant = DEFAULT_CUTOVER;
/*      */     } else {
/*  194 */       localInstant = paramReadableInstant.toInstant();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  199 */     ArrayList localArrayList = (ArrayList)cCache.get(paramDateTimeZone);
/*  200 */     GJChronology localGJChronology; if (localArrayList == null) {
/*  201 */       localArrayList = new ArrayList(2);
/*  202 */       cCache.put(paramDateTimeZone, localArrayList);
/*      */     } else {
/*  204 */       int i = localArrayList.size(); do { i--; if (i < 0) break;
/*  205 */         localGJChronology = (GJChronology)localArrayList.get(i);
/*  206 */       } while ((paramInt != localGJChronology.getMinimumDaysInFirstWeek()) || (!localInstant.equals(localGJChronology.getGregorianCutover())));
/*      */       
/*      */ 
/*  209 */       return localGJChronology;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  214 */     if (paramDateTimeZone == DateTimeZone.UTC) {
/*  215 */       localGJChronology = new GJChronology(JulianChronology.getInstance(paramDateTimeZone, paramInt), GregorianChronology.getInstance(paramDateTimeZone, paramInt), localInstant);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  220 */       localGJChronology = getInstance(DateTimeZone.UTC, localInstant, paramInt);
/*  221 */       localGJChronology = new GJChronology(ZonedChronology.getInstance(localGJChronology, paramDateTimeZone), localGJChronology.iJulianChronology, localGJChronology.iGregorianChronology, localGJChronology.iCutoverInstant);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  228 */     localArrayList.add(localGJChronology);
/*      */     
/*  230 */     return localGJChronology;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static GJChronology getInstance(DateTimeZone paramDateTimeZone, long paramLong, int paramInt)
/*      */   {
/*      */     Instant localInstant;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  247 */     if (paramLong == DEFAULT_CUTOVER.getMillis()) {
/*  248 */       localInstant = null;
/*      */     } else {
/*  250 */       localInstant = new Instant(paramLong);
/*      */     }
/*  252 */     return getInstance(paramDateTimeZone, localInstant, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private GJChronology(JulianChronology paramJulianChronology, GregorianChronology paramGregorianChronology, Instant paramInstant)
/*      */   {
/*  271 */     super(null, new Object[] { paramJulianChronology, paramGregorianChronology, paramInstant });
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private GJChronology(Chronology paramChronology, JulianChronology paramJulianChronology, GregorianChronology paramGregorianChronology, Instant paramInstant)
/*      */   {
/*  281 */     super(paramChronology, new Object[] { paramJulianChronology, paramGregorianChronology, paramInstant });
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private Object readResolve()
/*      */   {
/*  288 */     return getInstance(getZone(), this.iCutoverInstant, getMinimumDaysInFirstWeek());
/*      */   }
/*      */   
/*      */   public DateTimeZone getZone() {
/*      */     Chronology localChronology;
/*  293 */     if ((localChronology = getBase()) != null) {
/*  294 */       return localChronology.getZone();
/*      */     }
/*  296 */     return DateTimeZone.UTC;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Chronology withUTC()
/*      */   {
/*  307 */     return withZone(DateTimeZone.UTC);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Chronology withZone(DateTimeZone paramDateTimeZone)
/*      */   {
/*  317 */     if (paramDateTimeZone == null) {
/*  318 */       paramDateTimeZone = DateTimeZone.getDefault();
/*      */     }
/*  320 */     if (paramDateTimeZone == getZone()) {
/*  321 */       return this;
/*      */     }
/*  323 */     return getInstance(paramDateTimeZone, this.iCutoverInstant, getMinimumDaysInFirstWeek());
/*      */   }
/*      */   
/*      */ 
/*      */   public long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     throws IllegalArgumentException
/*      */   {
/*      */     Chronology localChronology;
/*  331 */     if ((localChronology = getBase()) != null) {
/*  332 */       return localChronology.getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4);
/*      */     }
/*      */     
/*      */ 
/*  336 */     long l = this.iGregorianChronology.getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4);
/*      */     
/*  338 */     if (l < this.iCutoverMillis)
/*      */     {
/*  340 */       l = this.iJulianChronology.getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4);
/*      */       
/*  342 */       if (l >= this.iCutoverMillis)
/*      */       {
/*  344 */         throw new IllegalArgumentException("Specified date does not exist");
/*      */       }
/*      */     }
/*  347 */     return l;
/*      */   }
/*      */   
/*      */ 
/*      */   public long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
/*      */     throws IllegalArgumentException
/*      */   {
/*      */     Chronology localChronology;
/*      */     
/*  356 */     if ((localChronology = getBase()) != null) {
/*  357 */       return localChronology.getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  363 */     long l = this.iGregorianChronology.getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
/*      */     
/*      */ 
/*  366 */     if (l < this.iCutoverMillis)
/*      */     {
/*  368 */       l = this.iJulianChronology.getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
/*      */       
/*      */ 
/*  371 */       if (l >= this.iCutoverMillis)
/*      */       {
/*  373 */         throw new IllegalArgumentException("Specified date does not exist");
/*      */       }
/*      */     }
/*  376 */     return l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Instant getGregorianCutover()
/*      */   {
/*  384 */     return this.iCutoverInstant;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMinimumDaysInFirstWeek()
/*      */   {
/*  393 */     return this.iGregorianChronology.getMinimumDaysInFirstWeek();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object paramObject)
/*      */   {
/*  404 */     return super.equals(paramObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/*  414 */     return "GJ".hashCode() * 11 + this.iJulianChronology.hashCode() + this.iGregorianChronology.hashCode() + this.iCutoverInstant.hashCode();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/*  426 */     StringBuffer localStringBuffer = new StringBuffer(60);
/*  427 */     localStringBuffer.append("GJChronology");
/*  428 */     localStringBuffer.append('[');
/*  429 */     localStringBuffer.append(getZone().getID());
/*      */     
/*  431 */     if (this.iCutoverMillis != DEFAULT_CUTOVER.getMillis()) {
/*  432 */       localStringBuffer.append(",cutover=");
/*      */       DateTimeFormatter localDateTimeFormatter;
/*  434 */       if (withUTC().dayOfYear().remainder(this.iCutoverMillis) == 0L) {
/*  435 */         localDateTimeFormatter = ISODateTimeFormat.date();
/*      */       } else {
/*  437 */         localDateTimeFormatter = ISODateTimeFormat.dateTime();
/*      */       }
/*  439 */       localDateTimeFormatter.withChronology(withUTC()).printTo(localStringBuffer, this.iCutoverMillis);
/*      */     }
/*      */     
/*  442 */     if (getMinimumDaysInFirstWeek() != 4) {
/*  443 */       localStringBuffer.append(",mdfw=");
/*  444 */       localStringBuffer.append(getMinimumDaysInFirstWeek());
/*      */     }
/*  446 */     localStringBuffer.append(']');
/*      */     
/*  448 */     return localStringBuffer.toString();
/*      */   }
/*      */   
/*      */   protected void assemble(AssembledChronology.Fields paramFields) {
/*  452 */     Object[] arrayOfObject = (Object[])getParam();
/*      */     
/*  454 */     JulianChronology localJulianChronology = (JulianChronology)arrayOfObject[0];
/*  455 */     GregorianChronology localGregorianChronology = (GregorianChronology)arrayOfObject[1];
/*  456 */     Instant localInstant = (Instant)arrayOfObject[2];
/*  457 */     this.iCutoverMillis = localInstant.getMillis();
/*      */     
/*  459 */     this.iJulianChronology = localJulianChronology;
/*  460 */     this.iGregorianChronology = localGregorianChronology;
/*  461 */     this.iCutoverInstant = localInstant;
/*      */     
/*  463 */     if (getBase() != null) {
/*  464 */       return;
/*      */     }
/*      */     
/*  467 */     if (localJulianChronology.getMinimumDaysInFirstWeek() != localGregorianChronology.getMinimumDaysInFirstWeek()) {
/*  468 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*      */ 
/*  472 */     this.iGapDuration = (this.iCutoverMillis - julianToGregorianByYear(this.iCutoverMillis));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  478 */     paramFields.copyFieldsFrom(localGregorianChronology);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  484 */     if (localGregorianChronology.millisOfDay().get(this.iCutoverMillis) == 0)
/*      */     {
/*      */ 
/*      */ 
/*  488 */       paramFields.millisOfSecond = new CutoverField(localJulianChronology.millisOfSecond(), paramFields.millisOfSecond, this.iCutoverMillis);
/*  489 */       paramFields.millisOfDay = new CutoverField(localJulianChronology.millisOfDay(), paramFields.millisOfDay, this.iCutoverMillis);
/*  490 */       paramFields.secondOfMinute = new CutoverField(localJulianChronology.secondOfMinute(), paramFields.secondOfMinute, this.iCutoverMillis);
/*  491 */       paramFields.secondOfDay = new CutoverField(localJulianChronology.secondOfDay(), paramFields.secondOfDay, this.iCutoverMillis);
/*  492 */       paramFields.minuteOfHour = new CutoverField(localJulianChronology.minuteOfHour(), paramFields.minuteOfHour, this.iCutoverMillis);
/*  493 */       paramFields.minuteOfDay = new CutoverField(localJulianChronology.minuteOfDay(), paramFields.minuteOfDay, this.iCutoverMillis);
/*  494 */       paramFields.hourOfDay = new CutoverField(localJulianChronology.hourOfDay(), paramFields.hourOfDay, this.iCutoverMillis);
/*  495 */       paramFields.hourOfHalfday = new CutoverField(localJulianChronology.hourOfHalfday(), paramFields.hourOfHalfday, this.iCutoverMillis);
/*  496 */       paramFields.clockhourOfDay = new CutoverField(localJulianChronology.clockhourOfDay(), paramFields.clockhourOfDay, this.iCutoverMillis);
/*  497 */       paramFields.clockhourOfHalfday = new CutoverField(localJulianChronology.clockhourOfHalfday(), paramFields.clockhourOfHalfday, this.iCutoverMillis);
/*      */       
/*  499 */       paramFields.halfdayOfDay = new CutoverField(localJulianChronology.halfdayOfDay(), paramFields.halfdayOfDay, this.iCutoverMillis);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  504 */     paramFields.era = new CutoverField(localJulianChronology.era(), paramFields.era, this.iCutoverMillis);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  513 */     long l = localGregorianChronology.year().roundCeiling(this.iCutoverMillis);
/*  514 */     paramFields.dayOfYear = new CutoverField(localJulianChronology.dayOfYear(), paramFields.dayOfYear, l);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  519 */     l = localGregorianChronology.weekyear().roundCeiling(this.iCutoverMillis);
/*  520 */     paramFields.weekOfWeekyear = new CutoverField(localJulianChronology.weekOfWeekyear(), paramFields.weekOfWeekyear, l, true);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  528 */     paramFields.year = new ImpreciseCutoverField(localJulianChronology.year(), paramFields.year, this.iCutoverMillis);
/*      */     
/*  530 */     paramFields.years = paramFields.year.getDurationField();
/*  531 */     paramFields.yearOfEra = new ImpreciseCutoverField(localJulianChronology.yearOfEra(), paramFields.yearOfEra, paramFields.years, this.iCutoverMillis);
/*      */     
/*  533 */     paramFields.yearOfCentury = new ImpreciseCutoverField(localJulianChronology.yearOfCentury(), paramFields.yearOfCentury, paramFields.years, this.iCutoverMillis);
/*      */     
/*      */ 
/*  536 */     paramFields.centuryOfEra = new ImpreciseCutoverField(localJulianChronology.centuryOfEra(), paramFields.centuryOfEra, this.iCutoverMillis);
/*      */     
/*  538 */     paramFields.centuries = paramFields.centuryOfEra.getDurationField();
/*      */     
/*  540 */     paramFields.monthOfYear = new ImpreciseCutoverField(localJulianChronology.monthOfYear(), paramFields.monthOfYear, this.iCutoverMillis);
/*      */     
/*  542 */     paramFields.months = paramFields.monthOfYear.getDurationField();
/*      */     
/*  544 */     paramFields.weekyear = new ImpreciseCutoverField(localJulianChronology.weekyear(), paramFields.weekyear, null, this.iCutoverMillis, true);
/*      */     
/*  546 */     paramFields.weekyearOfCentury = new ImpreciseCutoverField(localJulianChronology.weekyearOfCentury(), paramFields.weekyearOfCentury, paramFields.weekyears, this.iCutoverMillis);
/*      */     
/*  548 */     paramFields.weekyears = paramFields.weekyear.getDurationField();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  554 */     CutoverField localCutoverField = new CutoverField(localJulianChronology.dayOfMonth(), paramFields.dayOfMonth, this.iCutoverMillis);
/*      */     
/*  556 */     localCutoverField.iRangeDurationField = paramFields.months;
/*  557 */     paramFields.dayOfMonth = localCutoverField;
/*      */   }
/*      */   
/*      */   long julianToGregorianByYear(long paramLong)
/*      */   {
/*  562 */     return convertByYear(paramLong, this.iJulianChronology, this.iGregorianChronology);
/*      */   }
/*      */   
/*      */   long gregorianToJulianByYear(long paramLong) {
/*  566 */     return convertByYear(paramLong, this.iGregorianChronology, this.iJulianChronology);
/*      */   }
/*      */   
/*      */   long julianToGregorianByWeekyear(long paramLong) {
/*  570 */     return convertByWeekyear(paramLong, this.iJulianChronology, this.iGregorianChronology);
/*      */   }
/*      */   
/*      */   long gregorianToJulianByWeekyear(long paramLong) {
/*  574 */     return convertByWeekyear(paramLong, this.iGregorianChronology, this.iJulianChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private class CutoverField
/*      */     extends BaseDateTimeField
/*      */   {
/*      */     private static final long serialVersionUID = 3528501219481026402L;
/*      */     
/*      */     final DateTimeField iJulianField;
/*      */     
/*      */     final DateTimeField iGregorianField;
/*      */     
/*      */     final long iCutover;
/*      */     
/*      */     final boolean iConvertByWeekyear;
/*      */     
/*      */     protected DurationField iDurationField;
/*      */     
/*      */     protected DurationField iRangeDurationField;
/*      */     
/*      */ 
/*      */     CutoverField(DateTimeField paramDateTimeField1, DateTimeField paramDateTimeField2, long paramLong)
/*      */     {
/*  599 */       this(paramDateTimeField1, paramDateTimeField2, paramLong, false);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     CutoverField(DateTimeField paramDateTimeField1, DateTimeField paramDateTimeField2, long paramLong, boolean paramBoolean)
/*      */     {
/*  610 */       super();
/*  611 */       this.iJulianField = paramDateTimeField1;
/*  612 */       this.iGregorianField = paramDateTimeField2;
/*  613 */       this.iCutover = paramLong;
/*  614 */       this.iConvertByWeekyear = paramBoolean;
/*      */       
/*      */ 
/*  617 */       this.iDurationField = paramDateTimeField2.getDurationField();
/*      */       
/*  619 */       DurationField localDurationField = paramDateTimeField2.getRangeDurationField();
/*  620 */       if (localDurationField == null) {
/*  621 */         localDurationField = paramDateTimeField1.getRangeDurationField();
/*      */       }
/*  623 */       this.iRangeDurationField = localDurationField;
/*      */     }
/*      */     
/*      */     public boolean isLenient() {
/*  627 */       return false;
/*      */     }
/*      */     
/*      */     public int get(long paramLong) {
/*  631 */       if (paramLong >= this.iCutover) {
/*  632 */         return this.iGregorianField.get(paramLong);
/*      */       }
/*  634 */       return this.iJulianField.get(paramLong);
/*      */     }
/*      */     
/*      */     public String getAsText(long paramLong, Locale paramLocale)
/*      */     {
/*  639 */       if (paramLong >= this.iCutover) {
/*  640 */         return this.iGregorianField.getAsText(paramLong, paramLocale);
/*      */       }
/*  642 */       return this.iJulianField.getAsText(paramLong, paramLocale);
/*      */     }
/*      */     
/*      */     public String getAsText(int paramInt, Locale paramLocale)
/*      */     {
/*  647 */       return this.iGregorianField.getAsText(paramInt, paramLocale);
/*      */     }
/*      */     
/*      */     public String getAsShortText(long paramLong, Locale paramLocale) {
/*  651 */       if (paramLong >= this.iCutover) {
/*  652 */         return this.iGregorianField.getAsShortText(paramLong, paramLocale);
/*      */       }
/*  654 */       return this.iJulianField.getAsShortText(paramLong, paramLocale);
/*      */     }
/*      */     
/*      */     public String getAsShortText(int paramInt, Locale paramLocale)
/*      */     {
/*  659 */       return this.iGregorianField.getAsShortText(paramInt, paramLocale);
/*      */     }
/*      */     
/*      */     public long add(long paramLong, int paramInt) {
/*  663 */       return this.iGregorianField.add(paramLong, paramInt);
/*      */     }
/*      */     
/*      */     public long add(long paramLong1, long paramLong2) {
/*  667 */       return this.iGregorianField.add(paramLong1, paramLong2);
/*      */     }
/*      */     
/*      */ 
/*      */     public int[] add(ReadablePartial paramReadablePartial, int paramInt1, int[] paramArrayOfInt, int paramInt2)
/*      */     {
/*  673 */       if (paramInt2 == 0) {
/*  674 */         return paramArrayOfInt;
/*      */       }
/*  676 */       if (DateTimeUtils.isContiguous(paramReadablePartial)) {
/*  677 */         long l = 0L;
/*  678 */         int i = 0; for (int j = paramReadablePartial.size(); i < j; i++) {
/*  679 */           l = paramReadablePartial.getFieldType(i).getField(GJChronology.this).set(l, paramArrayOfInt[i]);
/*      */         }
/*  681 */         l = add(l, paramInt2);
/*  682 */         return GJChronology.this.get(paramReadablePartial, l);
/*      */       }
/*  684 */       return super.add(paramReadablePartial, paramInt1, paramArrayOfInt, paramInt2);
/*      */     }
/*      */     
/*      */     public int getDifference(long paramLong1, long paramLong2)
/*      */     {
/*  689 */       return this.iGregorianField.getDifference(paramLong1, paramLong2);
/*      */     }
/*      */     
/*      */     public long getDifferenceAsLong(long paramLong1, long paramLong2) {
/*  693 */       return this.iGregorianField.getDifferenceAsLong(paramLong1, paramLong2);
/*      */     }
/*      */     
/*      */     public long set(long paramLong, int paramInt) {
/*  697 */       if (paramLong >= this.iCutover) {
/*  698 */         paramLong = this.iGregorianField.set(paramLong, paramInt);
/*  699 */         if (paramLong < this.iCutover)
/*      */         {
/*  701 */           if (paramLong + GJChronology.this.iGapDuration < this.iCutover) {
/*  702 */             paramLong = gregorianToJulian(paramLong);
/*      */           }
/*      */           
/*  705 */           if (get(paramLong) != paramInt) {
/*  706 */             throw new IllegalFieldValueException(this.iGregorianField.getType(), new Integer(paramInt), null, null);
/*      */           }
/*      */         }
/*      */       }
/*      */       else {
/*  711 */         paramLong = this.iJulianField.set(paramLong, paramInt);
/*  712 */         if (paramLong >= this.iCutover)
/*      */         {
/*  714 */           if (paramLong - GJChronology.this.iGapDuration >= this.iCutover) {
/*  715 */             paramLong = julianToGregorian(paramLong);
/*      */           }
/*      */           
/*  718 */           if (get(paramLong) != paramInt) {
/*  719 */             throw new IllegalFieldValueException(this.iJulianField.getType(), new Integer(paramInt), null, null);
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*  724 */       return paramLong;
/*      */     }
/*      */     
/*      */     public long set(long paramLong, String paramString, Locale paramLocale) {
/*  728 */       if (paramLong >= this.iCutover) {
/*  729 */         paramLong = this.iGregorianField.set(paramLong, paramString, paramLocale);
/*  730 */         if (paramLong < this.iCutover)
/*      */         {
/*  732 */           if (paramLong + GJChronology.this.iGapDuration < this.iCutover) {
/*  733 */             paramLong = gregorianToJulian(paramLong);
/*      */           }
/*      */         }
/*      */       }
/*      */       else {
/*  738 */         paramLong = this.iJulianField.set(paramLong, paramString, paramLocale);
/*  739 */         if (paramLong >= this.iCutover)
/*      */         {
/*  741 */           if (paramLong - GJChronology.this.iGapDuration >= this.iCutover) {
/*  742 */             paramLong = julianToGregorian(paramLong);
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*  747 */       return paramLong;
/*      */     }
/*      */     
/*      */     public DurationField getDurationField() {
/*  751 */       return this.iDurationField;
/*      */     }
/*      */     
/*      */     public DurationField getRangeDurationField() {
/*  755 */       return this.iRangeDurationField;
/*      */     }
/*      */     
/*      */     public boolean isLeap(long paramLong) {
/*  759 */       if (paramLong >= this.iCutover) {
/*  760 */         return this.iGregorianField.isLeap(paramLong);
/*      */       }
/*  762 */       return this.iJulianField.isLeap(paramLong);
/*      */     }
/*      */     
/*      */     public int getLeapAmount(long paramLong)
/*      */     {
/*  767 */       if (paramLong >= this.iCutover) {
/*  768 */         return this.iGregorianField.getLeapAmount(paramLong);
/*      */       }
/*  770 */       return this.iJulianField.getLeapAmount(paramLong);
/*      */     }
/*      */     
/*      */     public DurationField getLeapDurationField()
/*      */     {
/*  775 */       return this.iGregorianField.getLeapDurationField();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     public int getMinimumValue()
/*      */     {
/*  782 */       return this.iJulianField.getMinimumValue();
/*      */     }
/*      */     
/*      */     public int getMinimumValue(ReadablePartial paramReadablePartial) {
/*  786 */       return this.iJulianField.getMinimumValue(paramReadablePartial);
/*      */     }
/*      */     
/*      */     public int getMinimumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt) {
/*  790 */       return this.iJulianField.getMinimumValue(paramReadablePartial, paramArrayOfInt);
/*      */     }
/*      */     
/*      */     public int getMinimumValue(long paramLong) {
/*  794 */       if (paramLong < this.iCutover) {
/*  795 */         return this.iJulianField.getMinimumValue(paramLong);
/*      */       }
/*      */       
/*  798 */       int i = this.iGregorianField.getMinimumValue(paramLong);
/*      */       
/*      */ 
/*      */ 
/*  802 */       paramLong = this.iGregorianField.set(paramLong, i);
/*  803 */       if (paramLong < this.iCutover) {
/*  804 */         i = this.iGregorianField.get(this.iCutover);
/*      */       }
/*      */       
/*  807 */       return i;
/*      */     }
/*      */     
/*      */ 
/*      */     public int getMaximumValue()
/*      */     {
/*  813 */       return this.iGregorianField.getMaximumValue();
/*      */     }
/*      */     
/*      */     public int getMaximumValue(long paramLong) {
/*  817 */       if (paramLong >= this.iCutover) {
/*  818 */         return this.iGregorianField.getMaximumValue(paramLong);
/*      */       }
/*      */       
/*  821 */       int i = this.iJulianField.getMaximumValue(paramLong);
/*      */       
/*      */ 
/*      */ 
/*  825 */       paramLong = this.iJulianField.set(paramLong, i);
/*  826 */       if (paramLong >= this.iCutover) {
/*  827 */         i = this.iJulianField.get(this.iJulianField.add(this.iCutover, -1));
/*      */       }
/*      */       
/*  830 */       return i;
/*      */     }
/*      */     
/*      */     public int getMaximumValue(ReadablePartial paramReadablePartial) {
/*  834 */       long l = GJChronology.getInstanceUTC().set(paramReadablePartial, 0L);
/*  835 */       return getMaximumValue(l);
/*      */     }
/*      */     
/*      */     public int getMaximumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt) {
/*  839 */       GJChronology localGJChronology = GJChronology.getInstanceUTC();
/*  840 */       long l = 0L;
/*  841 */       int i = 0; for (int j = paramReadablePartial.size(); i < j; i++) {
/*  842 */         DateTimeField localDateTimeField = paramReadablePartial.getFieldType(i).getField(localGJChronology);
/*  843 */         if (paramArrayOfInt[i] <= localDateTimeField.getMaximumValue(l)) {
/*  844 */           l = localDateTimeField.set(l, paramArrayOfInt[i]);
/*      */         }
/*      */       }
/*  847 */       return getMaximumValue(l);
/*      */     }
/*      */     
/*      */     public long roundFloor(long paramLong) {
/*  851 */       if (paramLong >= this.iCutover) {
/*  852 */         paramLong = this.iGregorianField.roundFloor(paramLong);
/*  853 */         if (paramLong < this.iCutover)
/*      */         {
/*  855 */           if (paramLong + GJChronology.this.iGapDuration < this.iCutover) {
/*  856 */             paramLong = gregorianToJulian(paramLong);
/*      */           }
/*      */         }
/*      */       } else {
/*  860 */         paramLong = this.iJulianField.roundFloor(paramLong);
/*      */       }
/*  862 */       return paramLong;
/*      */     }
/*      */     
/*      */     public long roundCeiling(long paramLong) {
/*  866 */       if (paramLong >= this.iCutover) {
/*  867 */         paramLong = this.iGregorianField.roundCeiling(paramLong);
/*      */       } else {
/*  869 */         paramLong = this.iJulianField.roundCeiling(paramLong);
/*  870 */         if (paramLong >= this.iCutover)
/*      */         {
/*  872 */           if (paramLong - GJChronology.this.iGapDuration >= this.iCutover) {
/*  873 */             paramLong = julianToGregorian(paramLong);
/*      */           }
/*      */         }
/*      */       }
/*  877 */       return paramLong;
/*      */     }
/*      */     
/*      */     public int getMaximumTextLength(Locale paramLocale) {
/*  881 */       return Math.max(this.iJulianField.getMaximumTextLength(paramLocale), this.iGregorianField.getMaximumTextLength(paramLocale));
/*      */     }
/*      */     
/*      */     public int getMaximumShortTextLength(Locale paramLocale)
/*      */     {
/*  886 */       return Math.max(this.iJulianField.getMaximumShortTextLength(paramLocale), this.iGregorianField.getMaximumShortTextLength(paramLocale));
/*      */     }
/*      */     
/*      */     protected long julianToGregorian(long paramLong)
/*      */     {
/*  891 */       if (this.iConvertByWeekyear) {
/*  892 */         return GJChronology.this.julianToGregorianByWeekyear(paramLong);
/*      */       }
/*  894 */       return GJChronology.this.julianToGregorianByYear(paramLong);
/*      */     }
/*      */     
/*      */     protected long gregorianToJulian(long paramLong)
/*      */     {
/*  899 */       if (this.iConvertByWeekyear) {
/*  900 */         return GJChronology.this.gregorianToJulianByWeekyear(paramLong);
/*      */       }
/*  902 */       return GJChronology.this.gregorianToJulianByYear(paramLong);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final class ImpreciseCutoverField
/*      */     extends GJChronology.CutoverField
/*      */   {
/*      */     private static final long serialVersionUID = 3410248757173576441L;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     ImpreciseCutoverField(DateTimeField paramDateTimeField1, DateTimeField paramDateTimeField2, long paramLong)
/*      */     {
/*  921 */       this(paramDateTimeField1, paramDateTimeField2, null, paramLong, false);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     ImpreciseCutoverField(DateTimeField paramDateTimeField1, DateTimeField paramDateTimeField2, DurationField paramDurationField, long paramLong)
/*      */     {
/*  932 */       this(paramDateTimeField1, paramDateTimeField2, paramDurationField, paramLong, false);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     ImpreciseCutoverField(DateTimeField paramDateTimeField1, DateTimeField paramDateTimeField2, DurationField paramDurationField, long paramLong, boolean paramBoolean)
/*      */     {
/*  944 */       super(paramDateTimeField1, paramDateTimeField2, paramLong, paramBoolean);
/*  945 */       if (paramDurationField == null) {
/*  946 */         paramDurationField = new GJChronology.LinkedDurationField(this.iDurationField, this);
/*      */       }
/*  948 */       this.iDurationField = paramDurationField;
/*      */     }
/*      */     
/*      */     public long add(long paramLong, int paramInt) {
/*  952 */       if (paramLong >= this.iCutover) {
/*  953 */         paramLong = this.iGregorianField.add(paramLong, paramInt);
/*  954 */         if (paramLong < this.iCutover)
/*      */         {
/*  956 */           if (paramLong + GJChronology.this.iGapDuration < this.iCutover) {
/*  957 */             paramLong = gregorianToJulian(paramLong);
/*      */           }
/*      */         }
/*      */       } else {
/*  961 */         paramLong = this.iJulianField.add(paramLong, paramInt);
/*  962 */         if (paramLong >= this.iCutover)
/*      */         {
/*  964 */           if (paramLong - GJChronology.this.iGapDuration >= this.iCutover) {
/*  965 */             paramLong = julianToGregorian(paramLong);
/*      */           }
/*      */         }
/*      */       }
/*  969 */       return paramLong;
/*      */     }
/*      */     
/*      */     public long add(long paramLong1, long paramLong2) {
/*  973 */       if (paramLong1 >= this.iCutover) {
/*  974 */         paramLong1 = this.iGregorianField.add(paramLong1, paramLong2);
/*  975 */         if (paramLong1 < this.iCutover)
/*      */         {
/*  977 */           if (paramLong1 + GJChronology.this.iGapDuration < this.iCutover) {
/*  978 */             paramLong1 = gregorianToJulian(paramLong1);
/*      */           }
/*      */         }
/*      */       } else {
/*  982 */         paramLong1 = this.iJulianField.add(paramLong1, paramLong2);
/*  983 */         if (paramLong1 >= this.iCutover)
/*      */         {
/*  985 */           if (paramLong1 - GJChronology.this.iGapDuration >= this.iCutover) {
/*  986 */             paramLong1 = julianToGregorian(paramLong1);
/*      */           }
/*      */         }
/*      */       }
/*  990 */       return paramLong1;
/*      */     }
/*      */     
/*      */     public int getDifference(long paramLong1, long paramLong2) {
/*  994 */       if (paramLong1 >= this.iCutover) {
/*  995 */         if (paramLong2 >= this.iCutover) {
/*  996 */           return this.iGregorianField.getDifference(paramLong1, paramLong2);
/*      */         }
/*      */         
/*      */ 
/* 1000 */         paramLong1 = gregorianToJulian(paramLong1);
/* 1001 */         return this.iJulianField.getDifference(paramLong1, paramLong2);
/*      */       }
/* 1003 */       if (paramLong2 < this.iCutover) {
/* 1004 */         return this.iJulianField.getDifference(paramLong1, paramLong2);
/*      */       }
/*      */       
/*      */ 
/* 1008 */       paramLong1 = julianToGregorian(paramLong1);
/* 1009 */       return this.iGregorianField.getDifference(paramLong1, paramLong2);
/*      */     }
/*      */     
/*      */     public long getDifferenceAsLong(long paramLong1, long paramLong2)
/*      */     {
/* 1014 */       if (paramLong1 >= this.iCutover) {
/* 1015 */         if (paramLong2 >= this.iCutover) {
/* 1016 */           return this.iGregorianField.getDifferenceAsLong(paramLong1, paramLong2);
/*      */         }
/*      */         
/*      */ 
/* 1020 */         paramLong1 = gregorianToJulian(paramLong1);
/* 1021 */         return this.iJulianField.getDifferenceAsLong(paramLong1, paramLong2);
/*      */       }
/* 1023 */       if (paramLong2 < this.iCutover) {
/* 1024 */         return this.iJulianField.getDifferenceAsLong(paramLong1, paramLong2);
/*      */       }
/*      */       
/*      */ 
/* 1028 */       paramLong1 = julianToGregorian(paramLong1);
/* 1029 */       return this.iGregorianField.getDifferenceAsLong(paramLong1, paramLong2);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public int getMinimumValue(long paramLong)
/*      */     {
/* 1044 */       if (paramLong >= this.iCutover) {
/* 1045 */         return this.iGregorianField.getMinimumValue(paramLong);
/*      */       }
/* 1047 */       return this.iJulianField.getMinimumValue(paramLong);
/*      */     }
/*      */     
/*      */     public int getMaximumValue(long paramLong)
/*      */     {
/* 1052 */       if (paramLong >= this.iCutover) {
/* 1053 */         return this.iGregorianField.getMaximumValue(paramLong);
/*      */       }
/* 1055 */       return this.iJulianField.getMaximumValue(paramLong);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private static class LinkedDurationField
/*      */     extends DecoratedDurationField
/*      */   {
/*      */     private static final long serialVersionUID = 4097975388007713084L;
/*      */     
/*      */     private final GJChronology.ImpreciseCutoverField iField;
/*      */     
/*      */ 
/*      */     LinkedDurationField(DurationField paramDurationField, GJChronology.ImpreciseCutoverField paramImpreciseCutoverField)
/*      */     {
/* 1070 */       super(paramDurationField.getType());
/* 1071 */       this.iField = paramImpreciseCutoverField;
/*      */     }
/*      */     
/*      */     public long add(long paramLong, int paramInt) {
/* 1075 */       return this.iField.add(paramLong, paramInt);
/*      */     }
/*      */     
/*      */     public long add(long paramLong1, long paramLong2) {
/* 1079 */       return this.iField.add(paramLong1, paramLong2);
/*      */     }
/*      */     
/*      */     public int getDifference(long paramLong1, long paramLong2) {
/* 1083 */       return this.iField.getDifference(paramLong1, paramLong2);
/*      */     }
/*      */     
/*      */     public long getDifferenceAsLong(long paramLong1, long paramLong2) {
/* 1087 */       return this.iField.getDifferenceAsLong(paramLong1, paramLong2);
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\chrono\GJChronology.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */