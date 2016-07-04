/*      */ package org.joda.time.tz;
/*      */ 
/*      */ import java.io.DataInput;
/*      */ import java.io.DataInputStream;
/*      */ import java.io.DataOutput;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.io.PrintStream;
/*      */ import java.text.DateFormatSymbols;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.Locale;
/*      */ import java.util.Set;
/*      */ import org.joda.time.Chronology;
/*      */ import org.joda.time.DateTime;
/*      */ import org.joda.time.DateTimeField;
/*      */ import org.joda.time.DateTimeUtils;
/*      */ import org.joda.time.DateTimeZone;
/*      */ import org.joda.time.Period;
/*      */ import org.joda.time.PeriodType;
/*      */ import org.joda.time.chrono.ISOChronology;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class DateTimeZoneBuilder
/*      */ {
/*      */   private final ArrayList iRuleSets;
/*      */   
/*      */   public static DateTimeZone readFrom(InputStream paramInputStream, String paramString)
/*      */     throws IOException
/*      */   {
/*   95 */     if ((paramInputStream instanceof DataInput)) {
/*   96 */       return readFrom((DataInput)paramInputStream, paramString);
/*      */     }
/*   98 */     return readFrom(new DataInputStream(paramInputStream), paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeZone readFrom(DataInput paramDataInput, String paramString)
/*      */     throws IOException
/*      */   {
/*  110 */     switch (paramDataInput.readUnsignedByte()) {
/*      */     case 70: 
/*  112 */       Object localObject = new FixedDateTimeZone(paramString, paramDataInput.readUTF(), (int)readMillis(paramDataInput), (int)readMillis(paramDataInput));
/*      */       
/*  114 */       if (((DateTimeZone)localObject).equals(DateTimeZone.UTC)) {
/*  115 */         localObject = DateTimeZone.UTC;
/*      */       }
/*  117 */       return (DateTimeZone)localObject;
/*      */     case 67: 
/*  119 */       return CachedDateTimeZone.forZone(PrecalculatedZone.readFrom(paramDataInput, paramString));
/*      */     case 80: 
/*  121 */       return PrecalculatedZone.readFrom(paramDataInput, paramString);
/*      */     }
/*  123 */     throw new IOException("Invalid encoding");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static void writeMillis(DataOutput paramDataOutput, long paramLong)
/*      */     throws IOException
/*      */   {
/*      */     long l;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  140 */     if (paramLong % 1800000L == 0L)
/*      */     {
/*  142 */       l = paramLong / 1800000L;
/*  143 */       if (l << 58 >> 58 == l)
/*      */       {
/*  145 */         paramDataOutput.writeByte((int)(l & 0x3F));
/*  146 */         return;
/*      */       }
/*      */     }
/*      */     
/*  150 */     if (paramLong % 60000L == 0L)
/*      */     {
/*  152 */       l = paramLong / 60000L;
/*  153 */       if (l << 34 >> 34 == l)
/*      */       {
/*  155 */         paramDataOutput.writeInt(0x40000000 | (int)(l & 0x3FFFFFFF));
/*  156 */         return;
/*      */       }
/*      */     }
/*      */     
/*  160 */     if (paramLong % 1000L == 0L)
/*      */     {
/*  162 */       l = paramLong / 1000L;
/*  163 */       if (l << 26 >> 26 == l)
/*      */       {
/*  165 */         paramDataOutput.writeByte(0x80 | (int)(l >> 32 & 0x3F));
/*  166 */         paramDataOutput.writeInt((int)(l & 0xFFFFFFFFFFFFFFFF));
/*  167 */         return;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  175 */     paramDataOutput.writeByte(paramLong < 0L ? 255 : 192);
/*  176 */     paramDataOutput.writeLong(paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */   static long readMillis(DataInput paramDataInput)
/*      */     throws IOException
/*      */   {
/*  183 */     int i = paramDataInput.readUnsignedByte();
/*  184 */     switch (i >> 6) {
/*      */     case 0: 
/*      */     default: 
/*  187 */       i = i << 26 >> 26;
/*  188 */       return i * 1800000L;
/*      */     
/*      */ 
/*      */     case 1: 
/*  192 */       i = i << 26 >> 2;
/*  193 */       i |= paramDataInput.readUnsignedByte() << 16;
/*  194 */       i |= paramDataInput.readUnsignedByte() << 8;
/*  195 */       i |= paramDataInput.readUnsignedByte();
/*  196 */       return i * 60000L;
/*      */     
/*      */ 
/*      */     case 2: 
/*  200 */       long l = i << 58 >> 26;
/*  201 */       l |= paramDataInput.readUnsignedByte() << 24;
/*  202 */       l |= paramDataInput.readUnsignedByte() << 16;
/*  203 */       l |= paramDataInput.readUnsignedByte() << 8;
/*  204 */       l |= paramDataInput.readUnsignedByte();
/*  205 */       return l * 1000L;
/*      */     }
/*      */     
/*      */     
/*  209 */     return paramDataInput.readLong();
/*      */   }
/*      */   
/*      */ 
/*      */   private static DateTimeZone buildFixedZone(String paramString1, String paramString2, int paramInt1, int paramInt2)
/*      */   {
/*  215 */     if (("UTC".equals(paramString1)) && (paramString1.equals(paramString2)) && (paramInt1 == 0) && (paramInt2 == 0))
/*      */     {
/*  217 */       return DateTimeZone.UTC;
/*      */     }
/*  219 */     return new FixedDateTimeZone(paramString1, paramString2, paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public DateTimeZoneBuilder()
/*      */   {
/*  226 */     this.iRuleSets = new ArrayList(10);
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
/*      */   public DateTimeZoneBuilder addCutover(int paramInt1, char paramChar, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean, int paramInt5)
/*      */   {
/*  252 */     OfYear localOfYear = new OfYear(paramChar, paramInt2, paramInt3, paramInt4, paramBoolean, paramInt5);
/*      */     
/*  254 */     if (this.iRuleSets.size() > 0) {
/*  255 */       RuleSet localRuleSet = (RuleSet)this.iRuleSets.get(this.iRuleSets.size() - 1);
/*  256 */       localRuleSet.setUpperLimit(paramInt1, localOfYear);
/*      */     }
/*  258 */     this.iRuleSets.add(new RuleSet());
/*  259 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeZoneBuilder setStandardOffset(int paramInt)
/*      */   {
/*  268 */     getLastRuleSet().setStandardOffset(paramInt);
/*  269 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public DateTimeZoneBuilder setFixedSavings(String paramString, int paramInt)
/*      */   {
/*  276 */     getLastRuleSet().setFixedSavings(paramString, paramInt);
/*  277 */     return this;
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
/*      */   public DateTimeZoneBuilder addRecurringSavings(String paramString, int paramInt1, int paramInt2, int paramInt3, char paramChar, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean, int paramInt7)
/*      */   {
/*  309 */     if (paramInt2 <= paramInt3) {
/*  310 */       OfYear localOfYear = new OfYear(paramChar, paramInt4, paramInt5, paramInt6, paramBoolean, paramInt7);
/*      */       
/*  312 */       Recurrence localRecurrence = new Recurrence(localOfYear, paramString, paramInt1);
/*  313 */       Rule localRule = new Rule(localRecurrence, paramInt2, paramInt3);
/*  314 */       getLastRuleSet().addRule(localRule);
/*      */     }
/*  316 */     return this;
/*      */   }
/*      */   
/*      */   private RuleSet getLastRuleSet() {
/*  320 */     if (this.iRuleSets.size() == 0) {
/*  321 */       addCutover(Integer.MIN_VALUE, 'w', 1, 1, 0, false, 0);
/*      */     }
/*  323 */     return (RuleSet)this.iRuleSets.get(this.iRuleSets.size() - 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DateTimeZone toDateTimeZone(String paramString, boolean paramBoolean)
/*      */   {
/*  333 */     if (paramString == null) {
/*  334 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  339 */     ArrayList localArrayList = new ArrayList();
/*      */     
/*      */ 
/*      */ 
/*  343 */     DSTZone localDSTZone = null;
/*      */     
/*  345 */     long l = Long.MIN_VALUE;
/*  346 */     int i = 0;
/*      */     
/*  348 */     int j = this.iRuleSets.size();
/*  349 */     for (int k = 0; k < j; k++) {
/*  350 */       RuleSet localRuleSet = (RuleSet)this.iRuleSets.get(k);
/*  351 */       Transition localTransition = localRuleSet.firstTransition(l);
/*  352 */       if (localTransition != null)
/*      */       {
/*      */ 
/*  355 */         addTransition(localArrayList, localTransition);
/*  356 */         l = localTransition.getMillis();
/*  357 */         i = localTransition.getSaveMillis();
/*      */         
/*      */ 
/*  360 */         localRuleSet = new RuleSet(localRuleSet);
/*      */         
/*  362 */         while (((localTransition = localRuleSet.nextTransition(l, i)) != null) && (
/*  363 */           (!addTransition(localArrayList, localTransition)) || 
/*  364 */           (localDSTZone == null)))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*  369 */           l = localTransition.getMillis();
/*  370 */           i = localTransition.getSaveMillis();
/*  371 */           if ((localDSTZone == null) && (k == j - 1)) {
/*  372 */             localDSTZone = localRuleSet.buildTailZone(paramString);
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  379 */         l = localRuleSet.getUpperLimit(i);
/*      */       }
/*      */     }
/*      */     
/*  383 */     if (localArrayList.size() == 0) {
/*  384 */       if (localDSTZone != null)
/*      */       {
/*  386 */         return localDSTZone;
/*      */       }
/*  388 */       return buildFixedZone(paramString, "UTC", 0, 0);
/*      */     }
/*  390 */     if ((localArrayList.size() == 1) && (localDSTZone == null)) {
/*  391 */       localObject = (Transition)localArrayList.get(0);
/*  392 */       return buildFixedZone(paramString, ((Transition)localObject).getNameKey(), ((Transition)localObject).getWallOffset(), ((Transition)localObject).getStandardOffset());
/*      */     }
/*      */     
/*      */ 
/*  396 */     Object localObject = PrecalculatedZone.create(paramString, paramBoolean, localArrayList, localDSTZone);
/*  397 */     if (((PrecalculatedZone)localObject).isCachable()) {
/*  398 */       return CachedDateTimeZone.forZone((DateTimeZone)localObject);
/*      */     }
/*  400 */     return (DateTimeZone)localObject;
/*      */   }
/*      */   
/*      */   private boolean addTransition(ArrayList paramArrayList, Transition paramTransition) {
/*  404 */     int i = paramArrayList.size();
/*  405 */     if (i == 0) {
/*  406 */       paramArrayList.add(paramTransition);
/*  407 */       return true;
/*      */     }
/*      */     
/*  410 */     Transition localTransition = (Transition)paramArrayList.get(i - 1);
/*  411 */     if (!paramTransition.isTransitionFrom(localTransition)) {
/*  412 */       return false;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  417 */     int j = 0;
/*  418 */     if (i >= 2) {
/*  419 */       j = ((Transition)paramArrayList.get(i - 2)).getWallOffset();
/*      */     }
/*  421 */     int k = localTransition.getWallOffset();
/*      */     
/*  423 */     long l1 = localTransition.getMillis() + j;
/*  424 */     long l2 = paramTransition.getMillis() + k;
/*      */     
/*  426 */     if (l2 != l1) {
/*  427 */       paramArrayList.add(paramTransition);
/*  428 */       return true;
/*      */     }
/*      */     
/*  431 */     paramArrayList.remove(i - 1);
/*  432 */     return addTransition(paramArrayList, paramTransition);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void writeTo(String paramString, OutputStream paramOutputStream)
/*      */     throws IOException
/*      */   {
/*  443 */     if ((paramOutputStream instanceof DataOutput)) {
/*  444 */       writeTo(paramString, (DataOutput)paramOutputStream);
/*      */     } else {
/*  446 */       writeTo(paramString, new DataOutputStream(paramOutputStream));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void writeTo(String paramString, DataOutput paramDataOutput)
/*      */     throws IOException
/*      */   {
/*  459 */     DateTimeZone localDateTimeZone = toDateTimeZone(paramString, false);
/*      */     
/*  461 */     if ((localDateTimeZone instanceof FixedDateTimeZone)) {
/*  462 */       paramDataOutput.writeByte(70);
/*  463 */       paramDataOutput.writeUTF(localDateTimeZone.getNameKey(0L));
/*  464 */       writeMillis(paramDataOutput, localDateTimeZone.getOffset(0L));
/*  465 */       writeMillis(paramDataOutput, localDateTimeZone.getStandardOffset(0L));
/*      */     } else {
/*  467 */       if ((localDateTimeZone instanceof CachedDateTimeZone)) {
/*  468 */         paramDataOutput.writeByte(67);
/*  469 */         localDateTimeZone = ((CachedDateTimeZone)localDateTimeZone).getUncachedZone();
/*      */       } else {
/*  471 */         paramDataOutput.writeByte(80);
/*      */       }
/*  473 */       ((PrecalculatedZone)localDateTimeZone).writeTo(paramDataOutput); } }
/*      */   
/*      */   private static final class OfYear { final char iMode;
/*      */     final int iMonthOfYear;
/*      */     final int iDayOfMonth;
/*      */     final int iDayOfWeek;
/*      */     final boolean iAdvance;
/*      */     final int iMillisOfDay;
/*      */     
/*  482 */     static OfYear readFrom(DataInput paramDataInput) throws IOException { return new OfYear((char)paramDataInput.readUnsignedByte(), paramDataInput.readUnsignedByte(), paramDataInput.readByte(), paramDataInput.readUnsignedByte(), paramDataInput.readBoolean(), (int)DateTimeZoneBuilder.readMillis(paramDataInput)); }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     OfYear(char paramChar, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4)
/*      */     {
/*  505 */       if ((paramChar != 'u') && (paramChar != 'w') && (paramChar != 's')) {
/*  506 */         throw new IllegalArgumentException("Unknown mode: " + paramChar);
/*      */       }
/*      */       
/*  509 */       this.iMode = paramChar;
/*  510 */       this.iMonthOfYear = paramInt1;
/*  511 */       this.iDayOfMonth = paramInt2;
/*  512 */       this.iDayOfWeek = paramInt3;
/*  513 */       this.iAdvance = paramBoolean;
/*  514 */       this.iMillisOfDay = paramInt4;
/*      */     }
/*      */     
/*      */ 
/*      */     public long setInstant(int paramInt1, int paramInt2, int paramInt3)
/*      */     {
/*      */       int i;
/*      */       
/*  522 */       if (this.iMode == 'w') {
/*  523 */         i = paramInt2 + paramInt3;
/*  524 */       } else if (this.iMode == 's') {
/*  525 */         i = paramInt2;
/*      */       } else {
/*  527 */         i = 0;
/*      */       }
/*      */       
/*  530 */       ISOChronology localISOChronology = ISOChronology.getInstanceUTC();
/*  531 */       long l = localISOChronology.year().set(0L, paramInt1);
/*  532 */       l = localISOChronology.monthOfYear().set(l, this.iMonthOfYear);
/*  533 */       l = localISOChronology.millisOfDay().set(l, this.iMillisOfDay);
/*  534 */       l = setDayOfMonth(localISOChronology, l);
/*      */       
/*  536 */       if (this.iDayOfWeek != 0) {
/*  537 */         l = setDayOfWeek(localISOChronology, l);
/*      */       }
/*      */       
/*      */ 
/*  541 */       return l - i;
/*      */     }
/*      */     
/*      */ 
/*      */     public long next(long paramLong, int paramInt1, int paramInt2)
/*      */     {
/*      */       int i;
/*      */       
/*  549 */       if (this.iMode == 'w') {
/*  550 */         i = paramInt1 + paramInt2;
/*  551 */       } else if (this.iMode == 's') {
/*  552 */         i = paramInt1;
/*      */       } else {
/*  554 */         i = 0;
/*      */       }
/*      */       
/*      */ 
/*  558 */       paramLong += i;
/*      */       
/*  560 */       ISOChronology localISOChronology = ISOChronology.getInstanceUTC();
/*  561 */       long l = localISOChronology.monthOfYear().set(paramLong, this.iMonthOfYear);
/*      */       
/*  563 */       l = localISOChronology.millisOfDay().set(l, 0);
/*  564 */       l = localISOChronology.millisOfDay().add(l, this.iMillisOfDay);
/*  565 */       l = setDayOfMonthNext(localISOChronology, l);
/*      */       
/*  567 */       if (this.iDayOfWeek == 0) {
/*  568 */         if (l <= paramLong) {
/*  569 */           l = localISOChronology.year().add(l, 1);
/*  570 */           l = setDayOfMonthNext(localISOChronology, l);
/*      */         }
/*      */       } else {
/*  573 */         l = setDayOfWeek(localISOChronology, l);
/*  574 */         if (l <= paramLong) {
/*  575 */           l = localISOChronology.year().add(l, 1);
/*  576 */           l = localISOChronology.monthOfYear().set(l, this.iMonthOfYear);
/*  577 */           l = setDayOfMonthNext(localISOChronology, l);
/*  578 */           l = setDayOfWeek(localISOChronology, l);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  583 */       return l - i;
/*      */     }
/*      */     
/*      */ 
/*      */     public long previous(long paramLong, int paramInt1, int paramInt2)
/*      */     {
/*      */       int i;
/*      */       
/*  591 */       if (this.iMode == 'w') {
/*  592 */         i = paramInt1 + paramInt2;
/*  593 */       } else if (this.iMode == 's') {
/*  594 */         i = paramInt1;
/*      */       } else {
/*  596 */         i = 0;
/*      */       }
/*      */       
/*      */ 
/*  600 */       paramLong += i;
/*      */       
/*  602 */       ISOChronology localISOChronology = ISOChronology.getInstanceUTC();
/*  603 */       long l = localISOChronology.monthOfYear().set(paramLong, this.iMonthOfYear);
/*      */       
/*  605 */       l = localISOChronology.millisOfDay().set(l, 0);
/*  606 */       l = localISOChronology.millisOfDay().add(l, this.iMillisOfDay);
/*  607 */       l = setDayOfMonthPrevious(localISOChronology, l);
/*      */       
/*  609 */       if (this.iDayOfWeek == 0) {
/*  610 */         if (l >= paramLong) {
/*  611 */           l = localISOChronology.year().add(l, -1);
/*  612 */           l = setDayOfMonthPrevious(localISOChronology, l);
/*      */         }
/*      */       } else {
/*  615 */         l = setDayOfWeek(localISOChronology, l);
/*  616 */         if (l >= paramLong) {
/*  617 */           l = localISOChronology.year().add(l, -1);
/*  618 */           l = localISOChronology.monthOfYear().set(l, this.iMonthOfYear);
/*  619 */           l = setDayOfMonthPrevious(localISOChronology, l);
/*  620 */           l = setDayOfWeek(localISOChronology, l);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  625 */       return l - i;
/*      */     }
/*      */     
/*      */     public boolean equals(Object paramObject) {
/*  629 */       if (this == paramObject) {
/*  630 */         return true;
/*      */       }
/*  632 */       if ((paramObject instanceof OfYear)) {
/*  633 */         OfYear localOfYear = (OfYear)paramObject;
/*  634 */         return (this.iMode == localOfYear.iMode) && (this.iMonthOfYear == localOfYear.iMonthOfYear) && (this.iDayOfMonth == localOfYear.iDayOfMonth) && (this.iDayOfWeek == localOfYear.iDayOfWeek) && (this.iAdvance == localOfYear.iAdvance) && (this.iMillisOfDay == localOfYear.iMillisOfDay);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  642 */       return false;
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
/*      */ 
/*      */     public void writeTo(DataOutput paramDataOutput)
/*      */       throws IOException
/*      */     {
/*  659 */       paramDataOutput.writeByte(this.iMode);
/*  660 */       paramDataOutput.writeByte(this.iMonthOfYear);
/*  661 */       paramDataOutput.writeByte(this.iDayOfMonth);
/*  662 */       paramDataOutput.writeByte(this.iDayOfWeek);
/*  663 */       paramDataOutput.writeBoolean(this.iAdvance);
/*  664 */       DateTimeZoneBuilder.writeMillis(paramDataOutput, this.iMillisOfDay);
/*      */     }
/*      */     
/*      */ 
/*      */     private long setDayOfMonthNext(Chronology paramChronology, long paramLong)
/*      */     {
/*      */       try
/*      */       {
/*  672 */         paramLong = setDayOfMonth(paramChronology, paramLong);
/*      */       } catch (IllegalArgumentException localIllegalArgumentException) {
/*  674 */         if ((this.iMonthOfYear == 2) && (this.iDayOfMonth == 29)) {
/*  675 */           while (!paramChronology.year().isLeap(paramLong)) {
/*  676 */             paramLong = paramChronology.year().add(paramLong, 1);
/*      */           }
/*  678 */           paramLong = setDayOfMonth(paramChronology, paramLong);
/*      */         } else {
/*  680 */           throw localIllegalArgumentException;
/*      */         }
/*      */       }
/*  683 */       return paramLong;
/*      */     }
/*      */     
/*      */ 
/*      */     private long setDayOfMonthPrevious(Chronology paramChronology, long paramLong)
/*      */     {
/*      */       try
/*      */       {
/*  691 */         paramLong = setDayOfMonth(paramChronology, paramLong);
/*      */       } catch (IllegalArgumentException localIllegalArgumentException) {
/*  693 */         if ((this.iMonthOfYear == 2) && (this.iDayOfMonth == 29)) {
/*  694 */           while (!paramChronology.year().isLeap(paramLong)) {
/*  695 */             paramLong = paramChronology.year().add(paramLong, -1);
/*      */           }
/*  697 */           paramLong = setDayOfMonth(paramChronology, paramLong);
/*      */         } else {
/*  699 */           throw localIllegalArgumentException;
/*      */         }
/*      */       }
/*  702 */       return paramLong;
/*      */     }
/*      */     
/*      */     private long setDayOfMonth(Chronology paramChronology, long paramLong) {
/*  706 */       if (this.iDayOfMonth >= 0) {
/*  707 */         paramLong = paramChronology.dayOfMonth().set(paramLong, this.iDayOfMonth);
/*      */       } else {
/*  709 */         paramLong = paramChronology.dayOfMonth().set(paramLong, 1);
/*  710 */         paramLong = paramChronology.monthOfYear().add(paramLong, 1);
/*  711 */         paramLong = paramChronology.dayOfMonth().add(paramLong, this.iDayOfMonth);
/*      */       }
/*  713 */       return paramLong;
/*      */     }
/*      */     
/*      */     private long setDayOfWeek(Chronology paramChronology, long paramLong) {
/*  717 */       int i = paramChronology.dayOfWeek().get(paramLong);
/*  718 */       int j = this.iDayOfWeek - i;
/*  719 */       if (j != 0) {
/*  720 */         if (this.iAdvance) {
/*  721 */           if (j < 0) {
/*  722 */             j += 7;
/*      */           }
/*      */         }
/*  725 */         else if (j > 0) {
/*  726 */           j -= 7;
/*      */         }
/*      */         
/*  729 */         paramLong = paramChronology.dayOfWeek().add(paramLong, j);
/*      */       }
/*  731 */       return paramLong;
/*      */     }
/*      */   }
/*      */   
/*      */   private static final class Recurrence {
/*      */     final DateTimeZoneBuilder.OfYear iOfYear;
/*      */     final String iNameKey;
/*      */     final int iSaveMillis;
/*      */     
/*  740 */     static Recurrence readFrom(DataInput paramDataInput) throws IOException { return new Recurrence(DateTimeZoneBuilder.OfYear.readFrom(paramDataInput), paramDataInput.readUTF(), (int)DateTimeZoneBuilder.readMillis(paramDataInput)); }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     Recurrence(DateTimeZoneBuilder.OfYear paramOfYear, String paramString, int paramInt)
/*      */     {
/*  748 */       this.iOfYear = paramOfYear;
/*  749 */       this.iNameKey = paramString;
/*  750 */       this.iSaveMillis = paramInt;
/*      */     }
/*      */     
/*      */     public DateTimeZoneBuilder.OfYear getOfYear() {
/*  754 */       return this.iOfYear;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     public long next(long paramLong, int paramInt1, int paramInt2)
/*      */     {
/*  761 */       return this.iOfYear.next(paramLong, paramInt1, paramInt2);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     public long previous(long paramLong, int paramInt1, int paramInt2)
/*      */     {
/*  768 */       return this.iOfYear.previous(paramLong, paramInt1, paramInt2);
/*      */     }
/*      */     
/*      */     public String getNameKey() {
/*  772 */       return this.iNameKey;
/*      */     }
/*      */     
/*      */     public int getSaveMillis() {
/*  776 */       return this.iSaveMillis;
/*      */     }
/*      */     
/*      */     public boolean equals(Object paramObject) {
/*  780 */       if (this == paramObject) {
/*  781 */         return true;
/*      */       }
/*  783 */       if ((paramObject instanceof Recurrence)) {
/*  784 */         Recurrence localRecurrence = (Recurrence)paramObject;
/*  785 */         return (this.iSaveMillis == localRecurrence.iSaveMillis) && (this.iNameKey.equals(localRecurrence.iNameKey)) && (this.iOfYear.equals(localRecurrence.iOfYear));
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  790 */       return false;
/*      */     }
/*      */     
/*      */     public void writeTo(DataOutput paramDataOutput) throws IOException {
/*  794 */       this.iOfYear.writeTo(paramDataOutput);
/*  795 */       paramDataOutput.writeUTF(this.iNameKey);
/*  796 */       DateTimeZoneBuilder.writeMillis(paramDataOutput, this.iSaveMillis);
/*      */     }
/*      */     
/*      */     Recurrence rename(String paramString) {
/*  800 */       return new Recurrence(this.iOfYear, paramString, this.iSaveMillis);
/*      */     }
/*      */     
/*      */     Recurrence renameAppend(String paramString) {
/*  804 */       return rename((this.iNameKey + paramString).intern());
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private static final class Rule
/*      */   {
/*      */     final DateTimeZoneBuilder.Recurrence iRecurrence;
/*      */     final int iFromYear;
/*      */     final int iToYear;
/*      */     
/*      */     Rule(DateTimeZoneBuilder.Recurrence paramRecurrence, int paramInt1, int paramInt2)
/*      */     {
/*  817 */       this.iRecurrence = paramRecurrence;
/*  818 */       this.iFromYear = paramInt1;
/*  819 */       this.iToYear = paramInt2;
/*      */     }
/*      */     
/*      */     public int getFromYear() {
/*  823 */       return this.iFromYear;
/*      */     }
/*      */     
/*      */     public int getToYear() {
/*  827 */       return this.iToYear;
/*      */     }
/*      */     
/*      */     public DateTimeZoneBuilder.OfYear getOfYear() {
/*  831 */       return this.iRecurrence.getOfYear();
/*      */     }
/*      */     
/*      */     public String getNameKey() {
/*  835 */       return this.iRecurrence.getNameKey();
/*      */     }
/*      */     
/*      */     public int getSaveMillis() {
/*  839 */       return this.iRecurrence.getSaveMillis();
/*      */     }
/*      */     
/*      */     public long next(long paramLong, int paramInt1, int paramInt2) {
/*  843 */       ISOChronology localISOChronology = ISOChronology.getInstanceUTC();
/*      */       
/*  845 */       int i = paramInt1 + paramInt2;
/*  846 */       long l1 = paramLong;
/*      */       
/*      */       int j;
/*  849 */       if (paramLong == Long.MIN_VALUE) {
/*  850 */         j = Integer.MIN_VALUE;
/*      */       } else {
/*  852 */         j = localISOChronology.year().get(paramLong + i);
/*      */       }
/*      */       
/*  855 */       if (j < this.iFromYear)
/*      */       {
/*  857 */         l1 = localISOChronology.year().set(0L, this.iFromYear) - i;
/*      */         
/*      */ 
/*  860 */         l1 -= 1L;
/*      */       }
/*      */       
/*  863 */       long l2 = this.iRecurrence.next(l1, paramInt1, paramInt2);
/*      */       
/*  865 */       if (l2 > paramLong) {
/*  866 */         j = localISOChronology.year().get(l2 + i);
/*  867 */         if (j > this.iToYear)
/*      */         {
/*  869 */           l2 = paramLong;
/*      */         }
/*      */       }
/*      */       
/*  873 */       return l2;
/*      */     }
/*      */   }
/*      */   
/*      */   private static final class Transition {
/*      */     private final long iMillis;
/*      */     private final String iNameKey;
/*      */     private final int iWallOffset;
/*      */     private final int iStandardOffset;
/*      */     
/*      */     Transition(long paramLong, Transition paramTransition) {
/*  884 */       this.iMillis = paramLong;
/*  885 */       this.iNameKey = paramTransition.iNameKey;
/*  886 */       this.iWallOffset = paramTransition.iWallOffset;
/*  887 */       this.iStandardOffset = paramTransition.iStandardOffset;
/*      */     }
/*      */     
/*      */     Transition(long paramLong, DateTimeZoneBuilder.Rule paramRule, int paramInt) {
/*  891 */       this.iMillis = paramLong;
/*  892 */       this.iNameKey = paramRule.getNameKey();
/*  893 */       this.iWallOffset = (paramInt + paramRule.getSaveMillis());
/*  894 */       this.iStandardOffset = paramInt;
/*      */     }
/*      */     
/*      */     Transition(long paramLong, String paramString, int paramInt1, int paramInt2)
/*      */     {
/*  899 */       this.iMillis = paramLong;
/*  900 */       this.iNameKey = paramString;
/*  901 */       this.iWallOffset = paramInt1;
/*  902 */       this.iStandardOffset = paramInt2;
/*      */     }
/*      */     
/*      */     public long getMillis() {
/*  906 */       return this.iMillis;
/*      */     }
/*      */     
/*      */     public String getNameKey() {
/*  910 */       return this.iNameKey;
/*      */     }
/*      */     
/*      */     public int getWallOffset() {
/*  914 */       return this.iWallOffset;
/*      */     }
/*      */     
/*      */     public int getStandardOffset() {
/*  918 */       return this.iStandardOffset;
/*      */     }
/*      */     
/*      */     public int getSaveMillis() {
/*  922 */       return this.iWallOffset - this.iStandardOffset;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     public boolean isTransitionFrom(Transition paramTransition)
/*      */     {
/*  929 */       if (paramTransition == null) {
/*  930 */         return true;
/*      */       }
/*  932 */       return (this.iMillis > paramTransition.iMillis) && ((this.iWallOffset != paramTransition.iWallOffset) || (!this.iNameKey.equals(paramTransition.iNameKey)));
/*      */     }
/*      */   }
/*      */   
/*      */   private static final class RuleSet
/*      */   {
/*      */     private static final int YEAR_LIMIT;
/*      */     private int iStandardOffset;
/*      */     private ArrayList iRules;
/*      */     private String iInitialNameKey;
/*      */     private int iInitialSaveMillis;
/*      */     private int iUpperYear;
/*      */     private DateTimeZoneBuilder.OfYear iUpperOfYear;
/*      */     
/*      */     static
/*      */     {
/*  948 */       long l = DateTimeUtils.currentTimeMillis();
/*  949 */       YEAR_LIMIT = ISOChronology.getInstanceUTC().year().get(l) + 100;
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
/*      */     RuleSet()
/*      */     {
/*  964 */       this.iRules = new ArrayList(10);
/*  965 */       this.iUpperYear = Integer.MAX_VALUE;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     RuleSet(RuleSet paramRuleSet)
/*      */     {
/*  972 */       this.iStandardOffset = paramRuleSet.iStandardOffset;
/*  973 */       this.iRules = new ArrayList(paramRuleSet.iRules);
/*  974 */       this.iInitialNameKey = paramRuleSet.iInitialNameKey;
/*  975 */       this.iInitialSaveMillis = paramRuleSet.iInitialSaveMillis;
/*  976 */       this.iUpperYear = paramRuleSet.iUpperYear;
/*  977 */       this.iUpperOfYear = paramRuleSet.iUpperOfYear;
/*      */     }
/*      */     
/*      */     public int getStandardOffset() {
/*  981 */       return this.iStandardOffset;
/*      */     }
/*      */     
/*      */     public void setStandardOffset(int paramInt) {
/*  985 */       this.iStandardOffset = paramInt;
/*      */     }
/*      */     
/*      */     public void setFixedSavings(String paramString, int paramInt) {
/*  989 */       this.iInitialNameKey = paramString;
/*  990 */       this.iInitialSaveMillis = paramInt;
/*      */     }
/*      */     
/*      */     public void addRule(DateTimeZoneBuilder.Rule paramRule) {
/*  994 */       if (!this.iRules.contains(paramRule)) {
/*  995 */         this.iRules.add(paramRule);
/*      */       }
/*      */     }
/*      */     
/*      */     public void setUpperLimit(int paramInt, DateTimeZoneBuilder.OfYear paramOfYear) {
/* 1000 */       this.iUpperYear = paramInt;
/* 1001 */       this.iUpperOfYear = paramOfYear;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public DateTimeZoneBuilder.Transition firstTransition(long paramLong)
/*      */     {
/* 1011 */       if (this.iInitialNameKey != null)
/*      */       {
/* 1013 */         return new DateTimeZoneBuilder.Transition(paramLong, this.iInitialNameKey, this.iStandardOffset + this.iInitialSaveMillis, this.iStandardOffset);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1018 */       ArrayList localArrayList = new ArrayList(this.iRules);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1024 */       long l = Long.MIN_VALUE;
/* 1025 */       int i = 0;
/* 1026 */       DateTimeZoneBuilder.Transition localTransition1 = null;
/*      */       
/*      */       DateTimeZoneBuilder.Transition localTransition2;
/* 1029 */       while ((localTransition2 = nextTransition(l, i)) != null) {
/* 1030 */         l = localTransition2.getMillis();
/*      */         
/* 1032 */         if (l == paramLong) {
/* 1033 */           localTransition1 = new DateTimeZoneBuilder.Transition(paramLong, localTransition2);
/* 1034 */           break;
/*      */         }
/*      */         
/* 1037 */         if (l > paramLong) {
/* 1038 */           if (localTransition1 == null)
/*      */           {
/*      */ 
/*      */ 
/* 1042 */             Iterator localIterator = localArrayList.iterator();
/* 1043 */             while (localIterator.hasNext()) {
/* 1044 */               DateTimeZoneBuilder.Rule localRule = (DateTimeZoneBuilder.Rule)localIterator.next();
/* 1045 */               if (localRule.getSaveMillis() == 0) {
/* 1046 */                 localTransition1 = new DateTimeZoneBuilder.Transition(paramLong, localRule, this.iStandardOffset);
/*      */               }
/*      */             }
/*      */           }
/*      */           
/* 1051 */           if (localTransition1 != null) {
/*      */             break;
/*      */           }
/*      */           
/* 1055 */           localTransition1 = new DateTimeZoneBuilder.Transition(paramLong, localTransition2.getNameKey(), this.iStandardOffset, this.iStandardOffset); break;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1063 */         localTransition1 = new DateTimeZoneBuilder.Transition(paramLong, localTransition2);
/*      */         
/* 1065 */         i = localTransition2.getSaveMillis();
/*      */       }
/*      */       
/* 1068 */       this.iRules = localArrayList;
/* 1069 */       return localTransition1;
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
/*      */     public DateTimeZoneBuilder.Transition nextTransition(long paramLong, int paramInt)
/*      */     {
/* 1084 */       ISOChronology localISOChronology = ISOChronology.getInstanceUTC();
/*      */       
/*      */ 
/* 1087 */       Object localObject = null;
/* 1088 */       long l1 = Long.MAX_VALUE;
/*      */       
/* 1090 */       Iterator localIterator = this.iRules.iterator();
/* 1091 */       while (localIterator.hasNext()) {
/* 1092 */         DateTimeZoneBuilder.Rule localRule = (DateTimeZoneBuilder.Rule)localIterator.next();
/* 1093 */         long l3 = localRule.next(paramLong, this.iStandardOffset, paramInt);
/* 1094 */         if (l3 <= paramLong) {
/* 1095 */           localIterator.remove();
/*      */ 
/*      */ 
/*      */ 
/*      */         }
/* 1100 */         else if (l3 <= l1)
/*      */         {
/* 1102 */           localObject = localRule;
/* 1103 */           l1 = l3;
/*      */         }
/*      */       }
/*      */       
/* 1107 */       if (localObject == null) {
/* 1108 */         return null;
/*      */       }
/*      */       
/*      */ 
/* 1112 */       if (localISOChronology.year().get(l1) >= YEAR_LIMIT) {
/* 1113 */         return null;
/*      */       }
/*      */       
/*      */ 
/* 1117 */       if (this.iUpperYear < Integer.MAX_VALUE) {
/* 1118 */         long l2 = this.iUpperOfYear.setInstant(this.iUpperYear, this.iStandardOffset, paramInt);
/*      */         
/* 1120 */         if (l1 >= l2)
/*      */         {
/* 1122 */           return null;
/*      */         }
/*      */       }
/*      */       
/* 1126 */       return new DateTimeZoneBuilder.Transition(l1, (DateTimeZoneBuilder.Rule)localObject, this.iStandardOffset);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     public long getUpperLimit(int paramInt)
/*      */     {
/* 1133 */       if (this.iUpperYear == Integer.MAX_VALUE) {
/* 1134 */         return Long.MAX_VALUE;
/*      */       }
/* 1136 */       return this.iUpperOfYear.setInstant(this.iUpperYear, this.iStandardOffset, paramInt);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     public DateTimeZoneBuilder.DSTZone buildTailZone(String paramString)
/*      */     {
/* 1143 */       if (this.iRules.size() == 2) {
/* 1144 */         DateTimeZoneBuilder.Rule localRule1 = (DateTimeZoneBuilder.Rule)this.iRules.get(0);
/* 1145 */         DateTimeZoneBuilder.Rule localRule2 = (DateTimeZoneBuilder.Rule)this.iRules.get(1);
/* 1146 */         if ((localRule1.getToYear() == Integer.MAX_VALUE) && (localRule2.getToYear() == Integer.MAX_VALUE))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1156 */           return new DateTimeZoneBuilder.DSTZone(paramString, this.iStandardOffset, localRule1.iRecurrence, localRule2.iRecurrence);
/*      */         }
/*      */       }
/*      */       
/* 1160 */       return null;
/*      */     } }
/*      */   
/*      */   private static final class DSTZone extends DateTimeZone { private static final long serialVersionUID = 6941492635554961361L;
/*      */     final int iStandardOffset;
/*      */     final DateTimeZoneBuilder.Recurrence iStartRecurrence;
/*      */     final DateTimeZoneBuilder.Recurrence iEndRecurrence;
/*      */     
/* 1168 */     static DSTZone readFrom(DataInput paramDataInput, String paramString) throws IOException { return new DSTZone(paramString, (int)DateTimeZoneBuilder.readMillis(paramDataInput), DateTimeZoneBuilder.Recurrence.readFrom(paramDataInput), DateTimeZoneBuilder.Recurrence.readFrom(paramDataInput)); }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     DSTZone(String paramString, int paramInt, DateTimeZoneBuilder.Recurrence paramRecurrence1, DateTimeZoneBuilder.Recurrence paramRecurrence2)
/*      */     {
/* 1178 */       super();
/* 1179 */       this.iStandardOffset = paramInt;
/* 1180 */       this.iStartRecurrence = paramRecurrence1;
/* 1181 */       this.iEndRecurrence = paramRecurrence2;
/*      */     }
/*      */     
/*      */     public String getNameKey(long paramLong) {
/* 1185 */       return findMatchingRecurrence(paramLong).getNameKey();
/*      */     }
/*      */     
/*      */     public int getOffset(long paramLong) {
/* 1189 */       return this.iStandardOffset + findMatchingRecurrence(paramLong).getSaveMillis();
/*      */     }
/*      */     
/*      */     public int getStandardOffset(long paramLong) {
/* 1193 */       return this.iStandardOffset;
/*      */     }
/*      */     
/*      */     public boolean isFixed() {
/* 1197 */       return false;
/*      */     }
/*      */     
/*      */     public long nextTransition(long paramLong) {
/* 1201 */       int i = this.iStandardOffset;
/* 1202 */       DateTimeZoneBuilder.Recurrence localRecurrence1 = this.iStartRecurrence;
/* 1203 */       DateTimeZoneBuilder.Recurrence localRecurrence2 = this.iEndRecurrence;
/*      */       
/*      */       long l1;
/*      */       try
/*      */       {
/* 1208 */         l1 = localRecurrence1.next(paramLong, i, localRecurrence2.getSaveMillis());
/*      */         
/* 1210 */         if ((paramLong > 0L) && (l1 < 0L))
/*      */         {
/* 1212 */           l1 = paramLong;
/*      */         }
/*      */       }
/*      */       catch (IllegalArgumentException localIllegalArgumentException1) {
/* 1216 */         l1 = paramLong;
/*      */       }
/*      */       catch (ArithmeticException localArithmeticException1) {
/* 1219 */         l1 = paramLong;
/*      */       }
/*      */       long l2;
/*      */       try {
/* 1223 */         l2 = localRecurrence2.next(paramLong, i, localRecurrence1.getSaveMillis());
/*      */         
/* 1225 */         if ((paramLong > 0L) && (l2 < 0L))
/*      */         {
/* 1227 */           l2 = paramLong;
/*      */         }
/*      */       }
/*      */       catch (IllegalArgumentException localIllegalArgumentException2) {
/* 1231 */         l2 = paramLong;
/*      */       }
/*      */       catch (ArithmeticException localArithmeticException2) {
/* 1234 */         l2 = paramLong;
/*      */       }
/*      */       
/* 1237 */       return l1 > l2 ? l2 : l1;
/*      */     }
/*      */     
/*      */ 
/*      */     public long previousTransition(long paramLong)
/*      */     {
/* 1243 */       paramLong += 1L;
/*      */       
/* 1245 */       int i = this.iStandardOffset;
/* 1246 */       DateTimeZoneBuilder.Recurrence localRecurrence1 = this.iStartRecurrence;
/* 1247 */       DateTimeZoneBuilder.Recurrence localRecurrence2 = this.iEndRecurrence;
/*      */       
/*      */       long l1;
/*      */       try
/*      */       {
/* 1252 */         l1 = localRecurrence1.previous(paramLong, i, localRecurrence2.getSaveMillis());
/*      */         
/* 1254 */         if ((paramLong < 0L) && (l1 > 0L))
/*      */         {
/* 1256 */           l1 = paramLong;
/*      */         }
/*      */       }
/*      */       catch (IllegalArgumentException localIllegalArgumentException1) {
/* 1260 */         l1 = paramLong;
/*      */       }
/*      */       catch (ArithmeticException localArithmeticException1) {
/* 1263 */         l1 = paramLong;
/*      */       }
/*      */       long l2;
/*      */       try {
/* 1267 */         l2 = localRecurrence2.previous(paramLong, i, localRecurrence1.getSaveMillis());
/*      */         
/* 1269 */         if ((paramLong < 0L) && (l2 > 0L))
/*      */         {
/* 1271 */           l2 = paramLong;
/*      */         }
/*      */       }
/*      */       catch (IllegalArgumentException localIllegalArgumentException2) {
/* 1275 */         l2 = paramLong;
/*      */       }
/*      */       catch (ArithmeticException localArithmeticException2) {
/* 1278 */         l2 = paramLong;
/*      */       }
/*      */       
/* 1281 */       return (l1 > l2 ? l1 : l2) - 1L;
/*      */     }
/*      */     
/*      */     public boolean equals(Object paramObject) {
/* 1285 */       if (this == paramObject) {
/* 1286 */         return true;
/*      */       }
/* 1288 */       if ((paramObject instanceof DSTZone)) {
/* 1289 */         DSTZone localDSTZone = (DSTZone)paramObject;
/* 1290 */         return (getID().equals(localDSTZone.getID())) && (this.iStandardOffset == localDSTZone.iStandardOffset) && (this.iStartRecurrence.equals(localDSTZone.iStartRecurrence)) && (this.iEndRecurrence.equals(localDSTZone.iEndRecurrence));
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1296 */       return false;
/*      */     }
/*      */     
/*      */     public void writeTo(DataOutput paramDataOutput) throws IOException {
/* 1300 */       DateTimeZoneBuilder.writeMillis(paramDataOutput, this.iStandardOffset);
/* 1301 */       this.iStartRecurrence.writeTo(paramDataOutput);
/* 1302 */       this.iEndRecurrence.writeTo(paramDataOutput);
/*      */     }
/*      */     
/*      */     private DateTimeZoneBuilder.Recurrence findMatchingRecurrence(long paramLong) {
/* 1306 */       int i = this.iStandardOffset;
/* 1307 */       DateTimeZoneBuilder.Recurrence localRecurrence1 = this.iStartRecurrence;
/* 1308 */       DateTimeZoneBuilder.Recurrence localRecurrence2 = this.iEndRecurrence;
/*      */       
/*      */       long l1;
/*      */       try
/*      */       {
/* 1313 */         l1 = localRecurrence1.next(paramLong, i, localRecurrence2.getSaveMillis());
/*      */       }
/*      */       catch (IllegalArgumentException localIllegalArgumentException1)
/*      */       {
/* 1317 */         l1 = paramLong;
/*      */       }
/*      */       catch (ArithmeticException localArithmeticException1) {
/* 1320 */         l1 = paramLong;
/*      */       }
/*      */       long l2;
/*      */       try {
/* 1324 */         l2 = localRecurrence2.next(paramLong, i, localRecurrence1.getSaveMillis());
/*      */       }
/*      */       catch (IllegalArgumentException localIllegalArgumentException2)
/*      */       {
/* 1328 */         l2 = paramLong;
/*      */       }
/*      */       catch (ArithmeticException localArithmeticException2) {
/* 1331 */         l2 = paramLong;
/*      */       }
/*      */       
/* 1334 */       return l1 > l2 ? localRecurrence1 : localRecurrence2; } }
/*      */   
/*      */   private static final class PrecalculatedZone extends DateTimeZone { private static final long serialVersionUID = 7811976468055766265L;
/*      */     private final long[] iTransitions;
/*      */     private final int[] iWallOffsets;
/*      */     private final int[] iStandardOffsets;
/*      */     private final String[] iNameKeys;
/*      */     private final DateTimeZoneBuilder.DSTZone iTailZone;
/*      */     
/* 1343 */     static PrecalculatedZone readFrom(DataInput paramDataInput, String paramString) throws IOException { int i = paramDataInput.readUnsignedShort();
/* 1344 */       String[] arrayOfString1 = new String[i];
/* 1345 */       for (int j = 0; j < i; j++) {
/* 1346 */         arrayOfString1[j] = paramDataInput.readUTF();
/*      */       }
/*      */       
/* 1349 */       j = paramDataInput.readInt();
/* 1350 */       long[] arrayOfLong = new long[j];
/* 1351 */       int[] arrayOfInt1 = new int[j];
/* 1352 */       int[] arrayOfInt2 = new int[j];
/* 1353 */       String[] arrayOfString2 = new String[j];
/*      */       
/* 1355 */       for (int k = 0; k < j; k++) {
/* 1356 */         arrayOfLong[k] = DateTimeZoneBuilder.readMillis(paramDataInput);
/* 1357 */         arrayOfInt1[k] = ((int)DateTimeZoneBuilder.readMillis(paramDataInput));
/* 1358 */         arrayOfInt2[k] = ((int)DateTimeZoneBuilder.readMillis(paramDataInput));
/*      */         try {
/*      */           int m;
/* 1361 */           if (i < 256) {
/* 1362 */             m = paramDataInput.readUnsignedByte();
/*      */           } else {
/* 1364 */             m = paramDataInput.readUnsignedShort();
/*      */           }
/* 1366 */           arrayOfString2[k] = arrayOfString1[m];
/*      */         } catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {
/* 1368 */           throw new IOException("Invalid encoding");
/*      */         }
/*      */       }
/*      */       
/* 1372 */       DateTimeZoneBuilder.DSTZone localDSTZone = null;
/* 1373 */       if (paramDataInput.readBoolean()) {
/* 1374 */         localDSTZone = DateTimeZoneBuilder.DSTZone.readFrom(paramDataInput, paramString);
/*      */       }
/*      */       
/* 1377 */       return new PrecalculatedZone(paramString, arrayOfLong, arrayOfInt1, arrayOfInt2, arrayOfString2, localDSTZone);
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
/*      */     static PrecalculatedZone create(String paramString, boolean paramBoolean, ArrayList paramArrayList, DateTimeZoneBuilder.DSTZone paramDSTZone)
/*      */     {
/* 1391 */       int i = paramArrayList.size();
/* 1392 */       if (i == 0) {
/* 1393 */         throw new IllegalArgumentException();
/*      */       }
/*      */       
/* 1396 */       long[] arrayOfLong = new long[i];
/* 1397 */       int[] arrayOfInt1 = new int[i];
/* 1398 */       int[] arrayOfInt2 = new int[i];
/* 1399 */       String[] arrayOfString = new String[i];
/*      */       
/* 1401 */       Object localObject1 = null;
/* 1402 */       for (int j = 0; j < i; j++) {
/* 1403 */         localObject3 = (DateTimeZoneBuilder.Transition)paramArrayList.get(j);
/*      */         
/* 1405 */         if (!((DateTimeZoneBuilder.Transition)localObject3).isTransitionFrom((DateTimeZoneBuilder.Transition)localObject1)) {
/* 1406 */           throw new IllegalArgumentException(paramString);
/*      */         }
/*      */         
/* 1409 */         arrayOfLong[j] = ((DateTimeZoneBuilder.Transition)localObject3).getMillis();
/* 1410 */         arrayOfInt1[j] = ((DateTimeZoneBuilder.Transition)localObject3).getWallOffset();
/* 1411 */         arrayOfInt2[j] = ((DateTimeZoneBuilder.Transition)localObject3).getStandardOffset();
/* 1412 */         arrayOfString[j] = ((DateTimeZoneBuilder.Transition)localObject3).getNameKey();
/*      */         
/* 1414 */         localObject1 = localObject3;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1419 */       Object localObject2 = new String[5];
/* 1420 */       Object localObject3 = new DateFormatSymbols(Locale.ENGLISH).getZoneStrings();
/* 1421 */       for (int k = 0; k < localObject3.length; k++) {
/* 1422 */         Object localObject4 = localObject3[k];
/* 1423 */         if ((localObject4 != null) && (localObject4.length == 5) && (paramString.equals(localObject4[0]))) {
/* 1424 */           localObject2 = localObject4;
/*      */         }
/*      */       }
/*      */       
/* 1428 */       ISOChronology localISOChronology = ISOChronology.getInstanceUTC();
/*      */       
/* 1430 */       for (int m = 0; m < arrayOfString.length - 1; m++) {
/* 1431 */         String str1 = arrayOfString[m];
/* 1432 */         String str2 = arrayOfString[(m + 1)];
/* 1433 */         long l1 = arrayOfInt1[m];
/* 1434 */         long l2 = arrayOfInt1[(m + 1)];
/* 1435 */         long l3 = arrayOfInt2[m];
/* 1436 */         long l4 = arrayOfInt2[(m + 1)];
/* 1437 */         Period localPeriod = new Period(arrayOfLong[m], arrayOfLong[(m + 1)], PeriodType.yearMonthDay(), localISOChronology);
/* 1438 */         if ((l1 != l2) && (l3 == l4) && (str1.equals(str2)) && (localPeriod.getYears() == 0) && (localPeriod.getMonths() > 4) && (localPeriod.getMonths() < 8) && (str1.equals(localObject2[2])) && (str1.equals(localObject2[4])))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1445 */           System.out.println("Fixing duplicate name key - " + str2);
/* 1446 */           System.out.println("     - " + new DateTime(arrayOfLong[m], localISOChronology) + " - " + new DateTime(arrayOfLong[(m + 1)], localISOChronology));
/*      */           
/* 1448 */           if (l1 > l2) {
/* 1449 */             arrayOfString[m] = (str1 + "-Summer").intern();
/* 1450 */           } else if (l1 < l2) {
/* 1451 */             arrayOfString[(m + 1)] = (str2 + "-Summer").intern();
/* 1452 */             m++;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/* 1457 */       if ((paramDSTZone != null) && 
/* 1458 */         (paramDSTZone.iStartRecurrence.getNameKey().equals(paramDSTZone.iEndRecurrence.getNameKey())))
/*      */       {
/* 1460 */         System.out.println("Fixing duplicate recurrent name key - " + paramDSTZone.iStartRecurrence.getNameKey());
/*      */         
/* 1462 */         if (paramDSTZone.iStartRecurrence.getSaveMillis() > 0) {
/* 1463 */           paramDSTZone = new DateTimeZoneBuilder.DSTZone(paramDSTZone.getID(), paramDSTZone.iStandardOffset, paramDSTZone.iStartRecurrence.renameAppend("-Summer"), paramDSTZone.iEndRecurrence);
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/* 1469 */           paramDSTZone = new DateTimeZoneBuilder.DSTZone(paramDSTZone.getID(), paramDSTZone.iStandardOffset, paramDSTZone.iStartRecurrence, paramDSTZone.iEndRecurrence.renameAppend("-Summer"));
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1478 */       return new PrecalculatedZone(paramBoolean ? paramString : "", arrayOfLong, arrayOfInt1, arrayOfInt2, arrayOfString, paramDSTZone);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private PrecalculatedZone(String paramString, long[] paramArrayOfLong, int[] paramArrayOfInt1, int[] paramArrayOfInt2, String[] paramArrayOfString, DateTimeZoneBuilder.DSTZone paramDSTZone)
/*      */     {
/* 1498 */       super();
/* 1499 */       this.iTransitions = paramArrayOfLong;
/* 1500 */       this.iWallOffsets = paramArrayOfInt1;
/* 1501 */       this.iStandardOffsets = paramArrayOfInt2;
/* 1502 */       this.iNameKeys = paramArrayOfString;
/* 1503 */       this.iTailZone = paramDSTZone;
/*      */     }
/*      */     
/*      */     public String getNameKey(long paramLong) {
/* 1507 */       long[] arrayOfLong = this.iTransitions;
/* 1508 */       int i = Arrays.binarySearch(arrayOfLong, paramLong);
/* 1509 */       if (i >= 0) {
/* 1510 */         return this.iNameKeys[i];
/*      */       }
/* 1512 */       i ^= 0xFFFFFFFF;
/* 1513 */       if (i < arrayOfLong.length) {
/* 1514 */         if (i > 0) {
/* 1515 */           return this.iNameKeys[(i - 1)];
/*      */         }
/* 1517 */         return "UTC";
/*      */       }
/* 1519 */       if (this.iTailZone == null) {
/* 1520 */         return this.iNameKeys[(i - 1)];
/*      */       }
/* 1522 */       return this.iTailZone.getNameKey(paramLong);
/*      */     }
/*      */     
/*      */     public int getOffset(long paramLong) {
/* 1526 */       long[] arrayOfLong = this.iTransitions;
/* 1527 */       int i = Arrays.binarySearch(arrayOfLong, paramLong);
/* 1528 */       if (i >= 0) {
/* 1529 */         return this.iWallOffsets[i];
/*      */       }
/* 1531 */       i ^= 0xFFFFFFFF;
/* 1532 */       if (i < arrayOfLong.length) {
/* 1533 */         if (i > 0) {
/* 1534 */           return this.iWallOffsets[(i - 1)];
/*      */         }
/* 1536 */         return 0;
/*      */       }
/* 1538 */       if (this.iTailZone == null) {
/* 1539 */         return this.iWallOffsets[(i - 1)];
/*      */       }
/* 1541 */       return this.iTailZone.getOffset(paramLong);
/*      */     }
/*      */     
/*      */     public int getStandardOffset(long paramLong) {
/* 1545 */       long[] arrayOfLong = this.iTransitions;
/* 1546 */       int i = Arrays.binarySearch(arrayOfLong, paramLong);
/* 1547 */       if (i >= 0) {
/* 1548 */         return this.iStandardOffsets[i];
/*      */       }
/* 1550 */       i ^= 0xFFFFFFFF;
/* 1551 */       if (i < arrayOfLong.length) {
/* 1552 */         if (i > 0) {
/* 1553 */           return this.iStandardOffsets[(i - 1)];
/*      */         }
/* 1555 */         return 0;
/*      */       }
/* 1557 */       if (this.iTailZone == null) {
/* 1558 */         return this.iStandardOffsets[(i - 1)];
/*      */       }
/* 1560 */       return this.iTailZone.getStandardOffset(paramLong);
/*      */     }
/*      */     
/*      */     public boolean isFixed() {
/* 1564 */       return false;
/*      */     }
/*      */     
/*      */     public long nextTransition(long paramLong) {
/* 1568 */       long[] arrayOfLong = this.iTransitions;
/* 1569 */       int i = Arrays.binarySearch(arrayOfLong, paramLong);
/* 1570 */       i = i >= 0 ? i + 1 : i ^ 0xFFFFFFFF;
/* 1571 */       if (i < arrayOfLong.length) {
/* 1572 */         return arrayOfLong[i];
/*      */       }
/* 1574 */       if (this.iTailZone == null) {
/* 1575 */         return paramLong;
/*      */       }
/* 1577 */       long l = arrayOfLong[(arrayOfLong.length - 1)];
/* 1578 */       if (paramLong < l) {
/* 1579 */         paramLong = l;
/*      */       }
/* 1581 */       return this.iTailZone.nextTransition(paramLong);
/*      */     }
/*      */     
/*      */     public long previousTransition(long paramLong) {
/* 1585 */       long[] arrayOfLong = this.iTransitions;
/* 1586 */       int i = Arrays.binarySearch(arrayOfLong, paramLong);
/* 1587 */       if (i >= 0) {
/* 1588 */         if (paramLong > Long.MIN_VALUE) {
/* 1589 */           return paramLong - 1L;
/*      */         }
/* 1591 */         return paramLong;
/*      */       }
/* 1593 */       i ^= 0xFFFFFFFF;
/* 1594 */       if (i < arrayOfLong.length) {
/* 1595 */         if (i > 0) {
/* 1596 */           l = arrayOfLong[(i - 1)];
/* 1597 */           if (l > Long.MIN_VALUE) {
/* 1598 */             return l - 1L;
/*      */           }
/*      */         }
/* 1601 */         return paramLong;
/*      */       }
/* 1603 */       if (this.iTailZone != null) {
/* 1604 */         l = this.iTailZone.previousTransition(paramLong);
/* 1605 */         if (l < paramLong) {
/* 1606 */           return l;
/*      */         }
/*      */       }
/* 1609 */       long l = arrayOfLong[(i - 1)];
/* 1610 */       if (l > Long.MIN_VALUE) {
/* 1611 */         return l - 1L;
/*      */       }
/* 1613 */       return paramLong;
/*      */     }
/*      */     
/*      */     public boolean equals(Object paramObject) {
/* 1617 */       if (this == paramObject) {
/* 1618 */         return true;
/*      */       }
/* 1620 */       if ((paramObject instanceof PrecalculatedZone)) {
/* 1621 */         PrecalculatedZone localPrecalculatedZone = (PrecalculatedZone)paramObject;
/* 1622 */         return (getID().equals(localPrecalculatedZone.getID())) && (Arrays.equals(this.iTransitions, localPrecalculatedZone.iTransitions)) && (Arrays.equals(this.iNameKeys, localPrecalculatedZone.iNameKeys)) && (Arrays.equals(this.iWallOffsets, localPrecalculatedZone.iWallOffsets)) && (Arrays.equals(this.iStandardOffsets, localPrecalculatedZone.iStandardOffsets)) && (this.iTailZone == null ? null == localPrecalculatedZone.iTailZone : this.iTailZone.equals(localPrecalculatedZone.iTailZone));
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1632 */       return false;
/*      */     }
/*      */     
/*      */     public void writeTo(DataOutput paramDataOutput) throws IOException {
/* 1636 */       int i = this.iTransitions.length;
/*      */       
/*      */ 
/* 1639 */       HashSet localHashSet = new HashSet();
/* 1640 */       for (int j = 0; j < i; j++) {
/* 1641 */         localHashSet.add(this.iNameKeys[j]);
/*      */       }
/*      */       
/* 1644 */       j = localHashSet.size();
/* 1645 */       if (j > 65535) {
/* 1646 */         throw new UnsupportedOperationException("String pool is too large");
/*      */       }
/* 1648 */       String[] arrayOfString = new String[j];
/* 1649 */       Iterator localIterator = localHashSet.iterator();
/* 1650 */       for (int k = 0; localIterator.hasNext(); k++) {
/* 1651 */         arrayOfString[k] = ((String)localIterator.next());
/*      */       }
/*      */       
/*      */ 
/* 1655 */       paramDataOutput.writeShort(j);
/* 1656 */       for (k = 0; k < j; k++) {
/* 1657 */         paramDataOutput.writeUTF(arrayOfString[k]);
/*      */       }
/*      */       
/* 1660 */       paramDataOutput.writeInt(i);
/*      */       
/* 1662 */       for (k = 0; k < i; k++) {
/* 1663 */         DateTimeZoneBuilder.writeMillis(paramDataOutput, this.iTransitions[k]);
/* 1664 */         DateTimeZoneBuilder.writeMillis(paramDataOutput, this.iWallOffsets[k]);
/* 1665 */         DateTimeZoneBuilder.writeMillis(paramDataOutput, this.iStandardOffsets[k]);
/*      */         
/*      */ 
/* 1668 */         String str = this.iNameKeys[k];
/* 1669 */         for (int m = 0; m < j; m++) {
/* 1670 */           if (arrayOfString[m].equals(str)) {
/* 1671 */             if (j < 256) {
/* 1672 */               paramDataOutput.writeByte(m); break;
/*      */             }
/* 1674 */             paramDataOutput.writeShort(m);
/*      */             
/* 1676 */             break;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/* 1681 */       paramDataOutput.writeBoolean(this.iTailZone != null);
/* 1682 */       if (this.iTailZone != null) {
/* 1683 */         this.iTailZone.writeTo(paramDataOutput);
/*      */       }
/*      */     }
/*      */     
/*      */     public boolean isCachable() {
/* 1688 */       if (this.iTailZone != null) {
/* 1689 */         return true;
/*      */       }
/* 1691 */       long[] arrayOfLong = this.iTransitions;
/* 1692 */       if (arrayOfLong.length <= 1) {
/* 1693 */         return false;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1698 */       double d1 = 0.0D;
/* 1699 */       int i = 0;
/*      */       
/* 1701 */       for (int j = 1; j < arrayOfLong.length; j++) {
/* 1702 */         long l = arrayOfLong[j] - arrayOfLong[(j - 1)];
/* 1703 */         if (l < 63158400000L) {
/* 1704 */           d1 += l;
/* 1705 */           i++;
/*      */         }
/*      */       }
/*      */       
/* 1709 */       if (i > 0) {
/* 1710 */         double d2 = d1 / i;
/* 1711 */         d2 /= 8.64E7D;
/* 1712 */         if (d2 >= 25.0D)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1719 */           return true;
/*      */         }
/*      */       }
/*      */       
/* 1723 */       return false;
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\tz\DateTimeZoneBuilder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */