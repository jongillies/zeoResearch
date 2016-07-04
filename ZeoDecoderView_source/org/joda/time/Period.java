/*      */ package org.joda.time;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import org.joda.time.base.BasePeriod;
/*      */ import org.joda.time.chrono.ISOChronology;
/*      */ import org.joda.time.field.FieldUtils;
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
/*      */ public final class Period
/*      */   extends BasePeriod
/*      */   implements ReadablePeriod, Serializable
/*      */ {
/*   63 */   public static final Period ZERO = new Period();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final long serialVersionUID = 741052353876488155L;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Period years(int paramInt)
/*      */   {
/*   83 */     return new Period(new int[] { paramInt, 0, 0, 0, 0, 0, 0, 0, 0 }, PeriodType.standard());
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
/*      */   public static Period months(int paramInt)
/*      */   {
/*  100 */     return new Period(new int[] { 0, paramInt, 0, 0, 0, 0, 0, 0 }, PeriodType.standard());
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
/*      */   public static Period weeks(int paramInt)
/*      */   {
/*  117 */     return new Period(new int[] { 0, 0, paramInt, 0, 0, 0, 0, 0 }, PeriodType.standard());
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
/*      */   public static Period days(int paramInt)
/*      */   {
/*  134 */     return new Period(new int[] { 0, 0, 0, paramInt, 0, 0, 0, 0 }, PeriodType.standard());
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
/*      */   public static Period hours(int paramInt)
/*      */   {
/*  151 */     return new Period(new int[] { 0, 0, 0, 0, paramInt, 0, 0, 0 }, PeriodType.standard());
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
/*      */   public static Period minutes(int paramInt)
/*      */   {
/*  168 */     return new Period(new int[] { 0, 0, 0, 0, 0, paramInt, 0, 0 }, PeriodType.standard());
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
/*      */   public static Period seconds(int paramInt)
/*      */   {
/*  185 */     return new Period(new int[] { 0, 0, 0, 0, 0, 0, paramInt, 0 }, PeriodType.standard());
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
/*      */   public static Period millis(int paramInt)
/*      */   {
/*  199 */     return new Period(new int[] { 0, 0, 0, 0, 0, 0, 0, paramInt }, PeriodType.standard());
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
/*      */   public static Period fieldDifference(ReadablePartial paramReadablePartial1, ReadablePartial paramReadablePartial2)
/*      */   {
/*  230 */     if ((paramReadablePartial1 == null) || (paramReadablePartial2 == null)) {
/*  231 */       throw new IllegalArgumentException("ReadablePartial objects must not be null");
/*      */     }
/*  233 */     if (paramReadablePartial1.size() != paramReadablePartial2.size()) {
/*  234 */       throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
/*      */     }
/*  236 */     DurationFieldType[] arrayOfDurationFieldType = new DurationFieldType[paramReadablePartial1.size()];
/*  237 */     int[] arrayOfInt = new int[paramReadablePartial1.size()];
/*  238 */     int i = 0; for (int j = paramReadablePartial1.size(); i < j; i++) {
/*  239 */       if (paramReadablePartial1.getFieldType(i) != paramReadablePartial2.getFieldType(i)) {
/*  240 */         throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
/*      */       }
/*  242 */       arrayOfDurationFieldType[i] = paramReadablePartial1.getFieldType(i).getDurationType();
/*  243 */       if ((i > 0) && (arrayOfDurationFieldType[(i - 1)] == arrayOfDurationFieldType[i])) {
/*  244 */         throw new IllegalArgumentException("ReadablePartial objects must not have overlapping fields");
/*      */       }
/*  246 */       arrayOfInt[i] = (paramReadablePartial2.getValue(i) - paramReadablePartial1.getValue(i));
/*      */     }
/*  248 */     return new Period(arrayOfInt, PeriodType.forFields(arrayOfDurationFieldType));
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Period()
/*      */   {
/*  271 */     super(0L, null, null);
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
/*      */   public Period(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/*  285 */     super(0, 0, 0, 0, paramInt1, paramInt2, paramInt3, paramInt4, PeriodType.standard());
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
/*      */   public Period(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
/*      */   {
/*  302 */     super(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, PeriodType.standard());
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Period(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, PeriodType paramPeriodType)
/*      */   {
/*  325 */     super(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramPeriodType);
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
/*      */   public Period(long paramLong)
/*      */   {
/*  355 */     super(paramLong, null, null);
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
/*      */ 
/*      */   public Period(long paramLong, PeriodType paramPeriodType)
/*      */   {
/*  375 */     super(paramLong, paramPeriodType, null);
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
/*      */ 
/*      */ 
/*      */   public Period(long paramLong, Chronology paramChronology)
/*      */   {
/*  396 */     super(paramLong, null, paramChronology);
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
/*      */ 
/*      */ 
/*      */   public Period(long paramLong, PeriodType paramPeriodType, Chronology paramChronology)
/*      */   {
/*  417 */     super(paramLong, paramPeriodType, paramChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Period(long paramLong1, long paramLong2)
/*      */   {
/*  428 */     super(paramLong1, paramLong2, null, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Period(long paramLong1, long paramLong2, PeriodType paramPeriodType)
/*      */   {
/*  439 */     super(paramLong1, paramLong2, paramPeriodType, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Period(long paramLong1, long paramLong2, Chronology paramChronology)
/*      */   {
/*  451 */     super(paramLong1, paramLong2, null, paramChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Period(long paramLong1, long paramLong2, PeriodType paramPeriodType, Chronology paramChronology)
/*      */   {
/*  463 */     super(paramLong1, paramLong2, paramPeriodType, paramChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Period(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2)
/*      */   {
/*  474 */     super(paramReadableInstant1, paramReadableInstant2, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Period(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2, PeriodType paramPeriodType)
/*      */   {
/*  485 */     super(paramReadableInstant1, paramReadableInstant2, paramPeriodType);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Period(ReadablePartial paramReadablePartial1, ReadablePartial paramReadablePartial2)
/*      */   {
/*  510 */     super(paramReadablePartial1, paramReadablePartial2, null);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Period(ReadablePartial paramReadablePartial1, ReadablePartial paramReadablePartial2, PeriodType paramPeriodType)
/*      */   {
/*  536 */     super(paramReadablePartial1, paramReadablePartial2, paramPeriodType);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Period(ReadableInstant paramReadableInstant, ReadableDuration paramReadableDuration)
/*      */   {
/*  546 */     super(paramReadableInstant, paramReadableDuration, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Period(ReadableInstant paramReadableInstant, ReadableDuration paramReadableDuration, PeriodType paramPeriodType)
/*      */   {
/*  557 */     super(paramReadableInstant, paramReadableDuration, paramPeriodType);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Period(ReadableDuration paramReadableDuration, ReadableInstant paramReadableInstant)
/*      */   {
/*  567 */     super(paramReadableDuration, paramReadableInstant, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Period(ReadableDuration paramReadableDuration, ReadableInstant paramReadableInstant, PeriodType paramPeriodType)
/*      */   {
/*  578 */     super(paramReadableDuration, paramReadableInstant, paramPeriodType);
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
/*      */   public Period(Object paramObject)
/*      */   {
/*  594 */     super(paramObject, null, null);
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
/*      */   public Period(Object paramObject, PeriodType paramPeriodType)
/*      */   {
/*  611 */     super(paramObject, paramPeriodType, null);
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
/*      */   public Period(Object paramObject, Chronology paramChronology)
/*      */   {
/*  628 */     super(paramObject, null, paramChronology);
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
/*      */   public Period(Object paramObject, PeriodType paramPeriodType, Chronology paramChronology)
/*      */   {
/*  646 */     super(paramObject, paramPeriodType, paramChronology);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Period(int[] paramArrayOfInt, PeriodType paramPeriodType)
/*      */   {
/*  656 */     super(paramArrayOfInt, paramPeriodType);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Period toPeriod()
/*      */   {
/*  667 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getYears()
/*      */   {
/*  677 */     return getPeriodType().getIndexedField(this, PeriodType.YEAR_INDEX);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMonths()
/*      */   {
/*  686 */     return getPeriodType().getIndexedField(this, PeriodType.MONTH_INDEX);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getWeeks()
/*      */   {
/*  695 */     return getPeriodType().getIndexedField(this, PeriodType.WEEK_INDEX);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getDays()
/*      */   {
/*  704 */     return getPeriodType().getIndexedField(this, PeriodType.DAY_INDEX);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getHours()
/*      */   {
/*  714 */     return getPeriodType().getIndexedField(this, PeriodType.HOUR_INDEX);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMinutes()
/*      */   {
/*  723 */     return getPeriodType().getIndexedField(this, PeriodType.MINUTE_INDEX);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getSeconds()
/*      */   {
/*  732 */     return getPeriodType().getIndexedField(this, PeriodType.SECOND_INDEX);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMillis()
/*      */   {
/*  741 */     return getPeriodType().getIndexedField(this, PeriodType.MILLI_INDEX);
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
/*      */   public Period withPeriodType(PeriodType paramPeriodType)
/*      */   {
/*  756 */     paramPeriodType = DateTimeUtils.getPeriodType(paramPeriodType);
/*  757 */     if (paramPeriodType.equals(getPeriodType())) {
/*  758 */       return this;
/*      */     }
/*  760 */     return new Period(this, paramPeriodType);
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
/*      */   public Period withFields(ReadablePeriod paramReadablePeriod)
/*      */   {
/*  774 */     if (paramReadablePeriod == null) {
/*  775 */       return this;
/*      */     }
/*  777 */     int[] arrayOfInt = getValues();
/*  778 */     arrayOfInt = super.mergePeriodInto(arrayOfInt, paramReadablePeriod);
/*  779 */     return new Period(arrayOfInt, getPeriodType());
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
/*      */   public Period withField(DurationFieldType paramDurationFieldType, int paramInt)
/*      */   {
/*  794 */     if (paramDurationFieldType == null) {
/*  795 */       throw new IllegalArgumentException("Field must not be null");
/*      */     }
/*  797 */     int[] arrayOfInt = getValues();
/*  798 */     super.setFieldInto(arrayOfInt, paramDurationFieldType, paramInt);
/*  799 */     return new Period(arrayOfInt, getPeriodType());
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
/*      */   public Period withFieldAdded(DurationFieldType paramDurationFieldType, int paramInt)
/*      */   {
/*  813 */     if (paramDurationFieldType == null) {
/*  814 */       throw new IllegalArgumentException("Field must not be null");
/*      */     }
/*  816 */     if (paramInt == 0) {
/*  817 */       return this;
/*      */     }
/*  819 */     int[] arrayOfInt = getValues();
/*  820 */     super.addFieldInto(arrayOfInt, paramDurationFieldType, paramInt);
/*  821 */     return new Period(arrayOfInt, getPeriodType());
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
/*      */   public Period withYears(int paramInt)
/*      */   {
/*  835 */     int[] arrayOfInt = getValues();
/*  836 */     getPeriodType().setIndexedField(this, PeriodType.YEAR_INDEX, arrayOfInt, paramInt);
/*  837 */     return new Period(arrayOfInt, getPeriodType());
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
/*      */   public Period withMonths(int paramInt)
/*      */   {
/*  850 */     int[] arrayOfInt = getValues();
/*  851 */     getPeriodType().setIndexedField(this, PeriodType.MONTH_INDEX, arrayOfInt, paramInt);
/*  852 */     return new Period(arrayOfInt, getPeriodType());
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
/*      */   public Period withWeeks(int paramInt)
/*      */   {
/*  865 */     int[] arrayOfInt = getValues();
/*  866 */     getPeriodType().setIndexedField(this, PeriodType.WEEK_INDEX, arrayOfInt, paramInt);
/*  867 */     return new Period(arrayOfInt, getPeriodType());
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
/*      */   public Period withDays(int paramInt)
/*      */   {
/*  880 */     int[] arrayOfInt = getValues();
/*  881 */     getPeriodType().setIndexedField(this, PeriodType.DAY_INDEX, arrayOfInt, paramInt);
/*  882 */     return new Period(arrayOfInt, getPeriodType());
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
/*      */   public Period withHours(int paramInt)
/*      */   {
/*  895 */     int[] arrayOfInt = getValues();
/*  896 */     getPeriodType().setIndexedField(this, PeriodType.HOUR_INDEX, arrayOfInt, paramInt);
/*  897 */     return new Period(arrayOfInt, getPeriodType());
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
/*      */   public Period withMinutes(int paramInt)
/*      */   {
/*  910 */     int[] arrayOfInt = getValues();
/*  911 */     getPeriodType().setIndexedField(this, PeriodType.MINUTE_INDEX, arrayOfInt, paramInt);
/*  912 */     return new Period(arrayOfInt, getPeriodType());
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
/*      */   public Period withSeconds(int paramInt)
/*      */   {
/*  925 */     int[] arrayOfInt = getValues();
/*  926 */     getPeriodType().setIndexedField(this, PeriodType.SECOND_INDEX, arrayOfInt, paramInt);
/*  927 */     return new Period(arrayOfInt, getPeriodType());
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
/*      */   public Period withMillis(int paramInt)
/*      */   {
/*  940 */     int[] arrayOfInt = getValues();
/*  941 */     getPeriodType().setIndexedField(this, PeriodType.MILLI_INDEX, arrayOfInt, paramInt);
/*  942 */     return new Period(arrayOfInt, getPeriodType());
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
/*      */ 
/*      */ 
/*      */ 
/*      */   public Period plus(ReadablePeriod paramReadablePeriod)
/*      */   {
/*  964 */     if (paramReadablePeriod == null) {
/*  965 */       return this;
/*      */     }
/*  967 */     int[] arrayOfInt = getValues();
/*  968 */     getPeriodType().addIndexedField(this, PeriodType.YEAR_INDEX, arrayOfInt, paramReadablePeriod.get(DurationFieldType.YEARS_TYPE));
/*  969 */     getPeriodType().addIndexedField(this, PeriodType.MONTH_INDEX, arrayOfInt, paramReadablePeriod.get(DurationFieldType.MONTHS_TYPE));
/*  970 */     getPeriodType().addIndexedField(this, PeriodType.WEEK_INDEX, arrayOfInt, paramReadablePeriod.get(DurationFieldType.WEEKS_TYPE));
/*  971 */     getPeriodType().addIndexedField(this, PeriodType.DAY_INDEX, arrayOfInt, paramReadablePeriod.get(DurationFieldType.DAYS_TYPE));
/*  972 */     getPeriodType().addIndexedField(this, PeriodType.HOUR_INDEX, arrayOfInt, paramReadablePeriod.get(DurationFieldType.HOURS_TYPE));
/*  973 */     getPeriodType().addIndexedField(this, PeriodType.MINUTE_INDEX, arrayOfInt, paramReadablePeriod.get(DurationFieldType.MINUTES_TYPE));
/*  974 */     getPeriodType().addIndexedField(this, PeriodType.SECOND_INDEX, arrayOfInt, paramReadablePeriod.get(DurationFieldType.SECONDS_TYPE));
/*  975 */     getPeriodType().addIndexedField(this, PeriodType.MILLI_INDEX, arrayOfInt, paramReadablePeriod.get(DurationFieldType.MILLIS_TYPE));
/*  976 */     return new Period(arrayOfInt, getPeriodType());
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
/*      */   public Period plusYears(int paramInt)
/*      */   {
/*  990 */     if (paramInt == 0) {
/*  991 */       return this;
/*      */     }
/*  993 */     int[] arrayOfInt = getValues();
/*  994 */     getPeriodType().addIndexedField(this, PeriodType.YEAR_INDEX, arrayOfInt, paramInt);
/*  995 */     return new Period(arrayOfInt, getPeriodType());
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
/*      */   public Period plusMonths(int paramInt)
/*      */   {
/* 1008 */     if (paramInt == 0) {
/* 1009 */       return this;
/*      */     }
/* 1011 */     int[] arrayOfInt = getValues();
/* 1012 */     getPeriodType().addIndexedField(this, PeriodType.MONTH_INDEX, arrayOfInt, paramInt);
/* 1013 */     return new Period(arrayOfInt, getPeriodType());
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
/*      */   public Period plusWeeks(int paramInt)
/*      */   {
/* 1026 */     if (paramInt == 0) {
/* 1027 */       return this;
/*      */     }
/* 1029 */     int[] arrayOfInt = getValues();
/* 1030 */     getPeriodType().addIndexedField(this, PeriodType.WEEK_INDEX, arrayOfInt, paramInt);
/* 1031 */     return new Period(arrayOfInt, getPeriodType());
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
/*      */   public Period plusDays(int paramInt)
/*      */   {
/* 1044 */     if (paramInt == 0) {
/* 1045 */       return this;
/*      */     }
/* 1047 */     int[] arrayOfInt = getValues();
/* 1048 */     getPeriodType().addIndexedField(this, PeriodType.DAY_INDEX, arrayOfInt, paramInt);
/* 1049 */     return new Period(arrayOfInt, getPeriodType());
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
/*      */   public Period plusHours(int paramInt)
/*      */   {
/* 1062 */     if (paramInt == 0) {
/* 1063 */       return this;
/*      */     }
/* 1065 */     int[] arrayOfInt = getValues();
/* 1066 */     getPeriodType().addIndexedField(this, PeriodType.HOUR_INDEX, arrayOfInt, paramInt);
/* 1067 */     return new Period(arrayOfInt, getPeriodType());
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
/*      */   public Period plusMinutes(int paramInt)
/*      */   {
/* 1080 */     if (paramInt == 0) {
/* 1081 */       return this;
/*      */     }
/* 1083 */     int[] arrayOfInt = getValues();
/* 1084 */     getPeriodType().addIndexedField(this, PeriodType.MINUTE_INDEX, arrayOfInt, paramInt);
/* 1085 */     return new Period(arrayOfInt, getPeriodType());
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
/*      */   public Period plusSeconds(int paramInt)
/*      */   {
/* 1098 */     if (paramInt == 0) {
/* 1099 */       return this;
/*      */     }
/* 1101 */     int[] arrayOfInt = getValues();
/* 1102 */     getPeriodType().addIndexedField(this, PeriodType.SECOND_INDEX, arrayOfInt, paramInt);
/* 1103 */     return new Period(arrayOfInt, getPeriodType());
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
/*      */   public Period plusMillis(int paramInt)
/*      */   {
/* 1116 */     if (paramInt == 0) {
/* 1117 */       return this;
/*      */     }
/* 1119 */     int[] arrayOfInt = getValues();
/* 1120 */     getPeriodType().addIndexedField(this, PeriodType.MILLI_INDEX, arrayOfInt, paramInt);
/* 1121 */     return new Period(arrayOfInt, getPeriodType());
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
/*      */ 
/*      */ 
/*      */ 
/*      */   public Period minus(ReadablePeriod paramReadablePeriod)
/*      */   {
/* 1143 */     if (paramReadablePeriod == null) {
/* 1144 */       return this;
/*      */     }
/* 1146 */     int[] arrayOfInt = getValues();
/* 1147 */     getPeriodType().addIndexedField(this, PeriodType.YEAR_INDEX, arrayOfInt, -paramReadablePeriod.get(DurationFieldType.YEARS_TYPE));
/* 1148 */     getPeriodType().addIndexedField(this, PeriodType.MONTH_INDEX, arrayOfInt, -paramReadablePeriod.get(DurationFieldType.MONTHS_TYPE));
/* 1149 */     getPeriodType().addIndexedField(this, PeriodType.WEEK_INDEX, arrayOfInt, -paramReadablePeriod.get(DurationFieldType.WEEKS_TYPE));
/* 1150 */     getPeriodType().addIndexedField(this, PeriodType.DAY_INDEX, arrayOfInt, -paramReadablePeriod.get(DurationFieldType.DAYS_TYPE));
/* 1151 */     getPeriodType().addIndexedField(this, PeriodType.HOUR_INDEX, arrayOfInt, -paramReadablePeriod.get(DurationFieldType.HOURS_TYPE));
/* 1152 */     getPeriodType().addIndexedField(this, PeriodType.MINUTE_INDEX, arrayOfInt, -paramReadablePeriod.get(DurationFieldType.MINUTES_TYPE));
/* 1153 */     getPeriodType().addIndexedField(this, PeriodType.SECOND_INDEX, arrayOfInt, -paramReadablePeriod.get(DurationFieldType.SECONDS_TYPE));
/* 1154 */     getPeriodType().addIndexedField(this, PeriodType.MILLI_INDEX, arrayOfInt, -paramReadablePeriod.get(DurationFieldType.MILLIS_TYPE));
/* 1155 */     return new Period(arrayOfInt, getPeriodType());
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
/*      */   public Period minusYears(int paramInt)
/*      */   {
/* 1169 */     return plusYears(-paramInt);
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
/*      */   public Period minusMonths(int paramInt)
/*      */   {
/* 1182 */     return plusMonths(-paramInt);
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
/*      */   public Period minusWeeks(int paramInt)
/*      */   {
/* 1195 */     return plusWeeks(-paramInt);
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
/*      */   public Period minusDays(int paramInt)
/*      */   {
/* 1208 */     return plusDays(-paramInt);
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
/*      */   public Period minusHours(int paramInt)
/*      */   {
/* 1221 */     return plusHours(-paramInt);
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
/*      */   public Period minusMinutes(int paramInt)
/*      */   {
/* 1234 */     return plusMinutes(-paramInt);
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
/*      */   public Period minusSeconds(int paramInt)
/*      */   {
/* 1247 */     return plusSeconds(-paramInt);
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
/*      */   public Period minusMillis(int paramInt)
/*      */   {
/* 1260 */     return plusMillis(-paramInt);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Weeks toStandardWeeks()
/*      */   {
/* 1284 */     checkYearsAndMonths("Weeks");
/* 1285 */     long l1 = getMillis();
/* 1286 */     l1 += getSeconds() * 1000L;
/* 1287 */     l1 += getMinutes() * 60000L;
/* 1288 */     l1 += getHours() * 3600000L;
/* 1289 */     l1 += getDays() * 86400000L;
/* 1290 */     long l2 = getWeeks() + l1 / 604800000L;
/* 1291 */     return Weeks.weeks(FieldUtils.safeToInt(l2));
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Days toStandardDays()
/*      */   {
/* 1314 */     checkYearsAndMonths("Days");
/* 1315 */     long l1 = getMillis();
/* 1316 */     l1 += getSeconds() * 1000L;
/* 1317 */     l1 += getMinutes() * 60000L;
/* 1318 */     l1 += getHours() * 3600000L;
/* 1319 */     long l2 = l1 / 86400000L;
/* 1320 */     l2 = FieldUtils.safeAdd(l2, getDays());
/* 1321 */     l2 = FieldUtils.safeAdd(l2, getWeeks() * 7L);
/* 1322 */     return Days.days(FieldUtils.safeToInt(l2));
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Hours toStandardHours()
/*      */   {
/* 1345 */     checkYearsAndMonths("Hours");
/* 1346 */     long l1 = getMillis();
/* 1347 */     l1 += getSeconds() * 1000L;
/* 1348 */     l1 += getMinutes() * 60000L;
/* 1349 */     long l2 = l1 / 3600000L;
/* 1350 */     l2 = FieldUtils.safeAdd(l2, getHours());
/* 1351 */     l2 = FieldUtils.safeAdd(l2, getDays() * 24L);
/* 1352 */     l2 = FieldUtils.safeAdd(l2, getWeeks() * 168L);
/* 1353 */     return Hours.hours(FieldUtils.safeToInt(l2));
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Minutes toStandardMinutes()
/*      */   {
/* 1376 */     checkYearsAndMonths("Minutes");
/* 1377 */     long l1 = getMillis();
/* 1378 */     l1 += getSeconds() * 1000L;
/* 1379 */     long l2 = l1 / 60000L;
/* 1380 */     l2 = FieldUtils.safeAdd(l2, getMinutes());
/* 1381 */     l2 = FieldUtils.safeAdd(l2, getHours() * 60L);
/* 1382 */     l2 = FieldUtils.safeAdd(l2, getDays() * 1440L);
/* 1383 */     l2 = FieldUtils.safeAdd(l2, getWeeks() * 10080L);
/* 1384 */     return Minutes.minutes(FieldUtils.safeToInt(l2));
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Seconds toStandardSeconds()
/*      */   {
/* 1407 */     checkYearsAndMonths("Seconds");
/* 1408 */     long l = getMillis() / 1000;
/* 1409 */     l = FieldUtils.safeAdd(l, getSeconds());
/* 1410 */     l = FieldUtils.safeAdd(l, getMinutes() * 60L);
/* 1411 */     l = FieldUtils.safeAdd(l, getHours() * 3600L);
/* 1412 */     l = FieldUtils.safeAdd(l, getDays() * 86400L);
/* 1413 */     l = FieldUtils.safeAdd(l, getWeeks() * 604800L);
/* 1414 */     return Seconds.seconds(FieldUtils.safeToInt(l));
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Duration toStandardDuration()
/*      */   {
/* 1437 */     checkYearsAndMonths("Duration");
/* 1438 */     long l = getMillis();
/* 1439 */     l += getSeconds() * 1000L;
/* 1440 */     l += getMinutes() * 60000L;
/* 1441 */     l += getHours() * 3600000L;
/* 1442 */     l += getDays() * 86400000L;
/* 1443 */     l += getWeeks() * 604800000L;
/* 1444 */     return new Duration(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void checkYearsAndMonths(String paramString)
/*      */   {
/* 1454 */     if (getMonths() != 0) {
/* 1455 */       throw new UnsupportedOperationException("Cannot convert to " + paramString + " as this period contains months and months vary in length");
/*      */     }
/* 1457 */     if (getYears() != 0) {
/* 1458 */       throw new UnsupportedOperationException("Cannot convert to " + paramString + " as this period contains years and years vary in length");
/*      */     }
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
/*      */   public Period normalizedStandard()
/*      */   {
/* 1489 */     return normalizedStandard(PeriodType.standard());
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
/*      */   public Period normalizedStandard(PeriodType paramPeriodType)
/*      */   {
/* 1525 */     long l = getMillis();
/* 1526 */     l += getSeconds() * 1000L;
/* 1527 */     l += getMinutes() * 60000L;
/* 1528 */     l += getHours() * 3600000L;
/* 1529 */     l += getDays() * 86400000L;
/* 1530 */     l += getWeeks() * 604800000L;
/* 1531 */     Period localPeriod = new Period(l, DateTimeUtils.getPeriodType(paramPeriodType), ISOChronology.getInstanceUTC());
/* 1532 */     int i = getYears();
/* 1533 */     int j = getMonths();
/* 1534 */     if ((i != 0) || (j != 0)) {
/* 1535 */       i = FieldUtils.safeAdd(i, j / 12);
/* 1536 */       j %= 12;
/* 1537 */       if (i != 0) {
/* 1538 */         localPeriod = localPeriod.withYears(i);
/*      */       }
/* 1540 */       if (j != 0) {
/* 1541 */         localPeriod = localPeriod.withMonths(j);
/*      */       }
/*      */     }
/* 1544 */     return localPeriod;
/*      */   }
/*      */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\Period.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */