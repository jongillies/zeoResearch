/*      */ package org.joda.time.format;
/*      */ 
/*      */ import java.util.Collection;
/*      */ import java.util.HashSet;
/*      */ import java.util.Set;
/*      */ import org.joda.time.DateTimeFieldType;
/*      */ import org.joda.time.DateTimeZone;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ISODateTimeFormat
/*      */ {
/*      */   private static DateTimeFormatter ye;
/*      */   private static DateTimeFormatter mye;
/*      */   private static DateTimeFormatter dme;
/*      */   private static DateTimeFormatter we;
/*      */   private static DateTimeFormatter wwe;
/*      */   private static DateTimeFormatter dwe;
/*      */   private static DateTimeFormatter dye;
/*      */   private static DateTimeFormatter hde;
/*      */   private static DateTimeFormatter mhe;
/*      */   private static DateTimeFormatter sme;
/*      */   private static DateTimeFormatter fse;
/*      */   private static DateTimeFormatter ze;
/*      */   private static DateTimeFormatter lte;
/*      */   private static DateTimeFormatter ym;
/*      */   private static DateTimeFormatter ymd;
/*      */   private static DateTimeFormatter ww;
/*      */   private static DateTimeFormatter wwd;
/*      */   private static DateTimeFormatter hm;
/*      */   private static DateTimeFormatter hms;
/*      */   private static DateTimeFormatter hmsl;
/*      */   private static DateTimeFormatter hmsf;
/*      */   private static DateTimeFormatter dh;
/*      */   private static DateTimeFormatter dhm;
/*      */   private static DateTimeFormatter dhms;
/*      */   private static DateTimeFormatter dhmsl;
/*      */   private static DateTimeFormatter dhmsf;
/*      */   private static DateTimeFormatter t;
/*      */   private static DateTimeFormatter tx;
/*      */   private static DateTimeFormatter tt;
/*      */   private static DateTimeFormatter ttx;
/*      */   private static DateTimeFormatter dt;
/*      */   private static DateTimeFormatter dtx;
/*      */   private static DateTimeFormatter wdt;
/*      */   private static DateTimeFormatter wdtx;
/*      */   private static DateTimeFormatter od;
/*      */   private static DateTimeFormatter odt;
/*      */   private static DateTimeFormatter odtx;
/*      */   private static DateTimeFormatter bd;
/*      */   private static DateTimeFormatter bt;
/*      */   private static DateTimeFormatter btx;
/*      */   private static DateTimeFormatter btt;
/*      */   private static DateTimeFormatter bttx;
/*      */   private static DateTimeFormatter bdt;
/*      */   private static DateTimeFormatter bdtx;
/*      */   private static DateTimeFormatter bod;
/*      */   private static DateTimeFormatter bodt;
/*      */   private static DateTimeFormatter bodtx;
/*      */   private static DateTimeFormatter bwd;
/*      */   private static DateTimeFormatter bwdt;
/*      */   private static DateTimeFormatter bwdtx;
/*      */   private static DateTimeFormatter dpe;
/*      */   private static DateTimeFormatter tpe;
/*      */   private static DateTimeFormatter dp;
/*      */   private static DateTimeFormatter ldp;
/*      */   private static DateTimeFormatter tp;
/*      */   private static DateTimeFormatter ltp;
/*      */   private static DateTimeFormatter dtp;
/*      */   private static DateTimeFormatter dotp;
/*      */   private static DateTimeFormatter ldotp;
/*      */   
/*      */   public static DateTimeFormatter forFields(Collection paramCollection, boolean paramBoolean1, boolean paramBoolean2)
/*      */   {
/*  231 */     if ((paramCollection == null) || (paramCollection.size() == 0)) {
/*  232 */       throw new IllegalArgumentException("The fields must not be null or empty");
/*      */     }
/*  234 */     HashSet localHashSet = new HashSet(paramCollection);
/*  235 */     int i = localHashSet.size();
/*  236 */     boolean bool1 = false;
/*  237 */     DateTimeFormatterBuilder localDateTimeFormatterBuilder = new DateTimeFormatterBuilder();
/*      */     
/*  239 */     if (localHashSet.contains(DateTimeFieldType.monthOfYear())) {
/*  240 */       bool1 = dateByMonth(localDateTimeFormatterBuilder, localHashSet, paramBoolean1, paramBoolean2);
/*  241 */     } else if (localHashSet.contains(DateTimeFieldType.dayOfYear())) {
/*  242 */       bool1 = dateByOrdinal(localDateTimeFormatterBuilder, localHashSet, paramBoolean1, paramBoolean2);
/*  243 */     } else if (localHashSet.contains(DateTimeFieldType.weekOfWeekyear())) {
/*  244 */       bool1 = dateByWeek(localDateTimeFormatterBuilder, localHashSet, paramBoolean1, paramBoolean2);
/*  245 */     } else if (localHashSet.contains(DateTimeFieldType.dayOfMonth())) {
/*  246 */       bool1 = dateByMonth(localDateTimeFormatterBuilder, localHashSet, paramBoolean1, paramBoolean2);
/*  247 */     } else if (localHashSet.contains(DateTimeFieldType.dayOfWeek())) {
/*  248 */       bool1 = dateByWeek(localDateTimeFormatterBuilder, localHashSet, paramBoolean1, paramBoolean2);
/*  249 */     } else if (localHashSet.remove(DateTimeFieldType.year())) {
/*  250 */       localDateTimeFormatterBuilder.append(yearElement());
/*  251 */       bool1 = true;
/*  252 */     } else if (localHashSet.remove(DateTimeFieldType.weekyear())) {
/*  253 */       localDateTimeFormatterBuilder.append(weekyearElement());
/*  254 */       bool1 = true;
/*      */     }
/*  256 */     boolean bool2 = localHashSet.size() < i;
/*      */     
/*      */ 
/*  259 */     time(localDateTimeFormatterBuilder, localHashSet, paramBoolean1, paramBoolean2, bool1, bool2);
/*      */     
/*      */ 
/*  262 */     if (!localDateTimeFormatterBuilder.canBuildFormatter()) {
/*  263 */       throw new IllegalArgumentException("No valid format for fields: " + paramCollection);
/*      */     }
/*      */     
/*      */ 
/*      */     try
/*      */     {
/*  269 */       paramCollection.retainAll(localHashSet);
/*      */     }
/*      */     catch (UnsupportedOperationException localUnsupportedOperationException) {}
/*      */     
/*  273 */     return localDateTimeFormatterBuilder.toFormatter();
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
/*      */   private static boolean dateByMonth(DateTimeFormatterBuilder paramDateTimeFormatterBuilder, Collection paramCollection, boolean paramBoolean1, boolean paramBoolean2)
/*      */   {
/*  294 */     boolean bool = false;
/*  295 */     if (paramCollection.remove(DateTimeFieldType.year())) {
/*  296 */       paramDateTimeFormatterBuilder.append(yearElement());
/*  297 */       if (paramCollection.remove(DateTimeFieldType.monthOfYear())) {
/*  298 */         if (paramCollection.remove(DateTimeFieldType.dayOfMonth()))
/*      */         {
/*  300 */           appendSeparator(paramDateTimeFormatterBuilder, paramBoolean1);
/*  301 */           paramDateTimeFormatterBuilder.appendMonthOfYear(2);
/*  302 */           appendSeparator(paramDateTimeFormatterBuilder, paramBoolean1);
/*  303 */           paramDateTimeFormatterBuilder.appendDayOfMonth(2);
/*      */         }
/*      */         else {
/*  306 */           paramDateTimeFormatterBuilder.appendLiteral('-');
/*  307 */           paramDateTimeFormatterBuilder.appendMonthOfYear(2);
/*  308 */           bool = true;
/*      */         }
/*      */       }
/*  311 */       else if (paramCollection.remove(DateTimeFieldType.dayOfMonth()))
/*      */       {
/*  313 */         checkNotStrictISO(paramCollection, paramBoolean2);
/*  314 */         paramDateTimeFormatterBuilder.appendLiteral('-');
/*  315 */         paramDateTimeFormatterBuilder.appendLiteral('-');
/*  316 */         paramDateTimeFormatterBuilder.appendDayOfMonth(2);
/*      */       }
/*      */       else {
/*  319 */         bool = true;
/*      */       }
/*      */       
/*      */     }
/*  323 */     else if (paramCollection.remove(DateTimeFieldType.monthOfYear())) {
/*  324 */       paramDateTimeFormatterBuilder.appendLiteral('-');
/*  325 */       paramDateTimeFormatterBuilder.appendLiteral('-');
/*  326 */       paramDateTimeFormatterBuilder.appendMonthOfYear(2);
/*  327 */       if (paramCollection.remove(DateTimeFieldType.dayOfMonth()))
/*      */       {
/*  329 */         appendSeparator(paramDateTimeFormatterBuilder, paramBoolean1);
/*  330 */         paramDateTimeFormatterBuilder.appendDayOfMonth(2);
/*      */       }
/*      */       else {
/*  333 */         bool = true;
/*      */       }
/*  335 */     } else if (paramCollection.remove(DateTimeFieldType.dayOfMonth()))
/*      */     {
/*  337 */       paramDateTimeFormatterBuilder.appendLiteral('-');
/*  338 */       paramDateTimeFormatterBuilder.appendLiteral('-');
/*  339 */       paramDateTimeFormatterBuilder.appendLiteral('-');
/*  340 */       paramDateTimeFormatterBuilder.appendDayOfMonth(2);
/*      */     }
/*  342 */     return bool;
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
/*      */   private static boolean dateByOrdinal(DateTimeFormatterBuilder paramDateTimeFormatterBuilder, Collection paramCollection, boolean paramBoolean1, boolean paramBoolean2)
/*      */   {
/*  362 */     boolean bool = false;
/*  363 */     if (paramCollection.remove(DateTimeFieldType.year())) {
/*  364 */       paramDateTimeFormatterBuilder.append(yearElement());
/*  365 */       if (paramCollection.remove(DateTimeFieldType.dayOfYear()))
/*      */       {
/*  367 */         appendSeparator(paramDateTimeFormatterBuilder, paramBoolean1);
/*  368 */         paramDateTimeFormatterBuilder.appendDayOfYear(3);
/*      */       }
/*      */       else {
/*  371 */         bool = true;
/*      */       }
/*      */     }
/*  374 */     else if (paramCollection.remove(DateTimeFieldType.dayOfYear()))
/*      */     {
/*  376 */       paramDateTimeFormatterBuilder.appendLiteral('-');
/*  377 */       paramDateTimeFormatterBuilder.appendDayOfYear(3);
/*      */     }
/*  379 */     return bool;
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
/*      */   private static boolean dateByWeek(DateTimeFormatterBuilder paramDateTimeFormatterBuilder, Collection paramCollection, boolean paramBoolean1, boolean paramBoolean2)
/*      */   {
/*  399 */     boolean bool = false;
/*  400 */     if (paramCollection.remove(DateTimeFieldType.weekyear())) {
/*  401 */       paramDateTimeFormatterBuilder.append(weekyearElement());
/*  402 */       if (paramCollection.remove(DateTimeFieldType.weekOfWeekyear())) {
/*  403 */         appendSeparator(paramDateTimeFormatterBuilder, paramBoolean1);
/*  404 */         paramDateTimeFormatterBuilder.appendLiteral('W');
/*  405 */         paramDateTimeFormatterBuilder.appendWeekOfWeekyear(2);
/*  406 */         if (paramCollection.remove(DateTimeFieldType.dayOfWeek()))
/*      */         {
/*  408 */           appendSeparator(paramDateTimeFormatterBuilder, paramBoolean1);
/*  409 */           paramDateTimeFormatterBuilder.appendDayOfWeek(1);
/*      */         }
/*      */         else {
/*  412 */           bool = true;
/*      */         }
/*      */       }
/*  415 */       else if (paramCollection.remove(DateTimeFieldType.dayOfWeek()))
/*      */       {
/*  417 */         checkNotStrictISO(paramCollection, paramBoolean2);
/*  418 */         appendSeparator(paramDateTimeFormatterBuilder, paramBoolean1);
/*  419 */         paramDateTimeFormatterBuilder.appendLiteral('W');
/*  420 */         paramDateTimeFormatterBuilder.appendLiteral('-');
/*  421 */         paramDateTimeFormatterBuilder.appendDayOfWeek(1);
/*      */       }
/*      */       else {
/*  424 */         bool = true;
/*      */       }
/*      */       
/*      */     }
/*  428 */     else if (paramCollection.remove(DateTimeFieldType.weekOfWeekyear())) {
/*  429 */       paramDateTimeFormatterBuilder.appendLiteral('-');
/*  430 */       paramDateTimeFormatterBuilder.appendLiteral('W');
/*  431 */       paramDateTimeFormatterBuilder.appendWeekOfWeekyear(2);
/*  432 */       if (paramCollection.remove(DateTimeFieldType.dayOfWeek()))
/*      */       {
/*  434 */         appendSeparator(paramDateTimeFormatterBuilder, paramBoolean1);
/*  435 */         paramDateTimeFormatterBuilder.appendDayOfWeek(1);
/*      */       }
/*      */       else {
/*  438 */         bool = true;
/*      */       }
/*  440 */     } else if (paramCollection.remove(DateTimeFieldType.dayOfWeek()))
/*      */     {
/*  442 */       paramDateTimeFormatterBuilder.appendLiteral('-');
/*  443 */       paramDateTimeFormatterBuilder.appendLiteral('W');
/*  444 */       paramDateTimeFormatterBuilder.appendLiteral('-');
/*  445 */       paramDateTimeFormatterBuilder.appendDayOfWeek(1);
/*      */     }
/*  447 */     return bool;
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
/*      */   private static void time(DateTimeFormatterBuilder paramDateTimeFormatterBuilder, Collection paramCollection, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
/*      */   {
/*  471 */     boolean bool1 = paramCollection.remove(DateTimeFieldType.hourOfDay());
/*  472 */     boolean bool2 = paramCollection.remove(DateTimeFieldType.minuteOfHour());
/*  473 */     boolean bool3 = paramCollection.remove(DateTimeFieldType.secondOfMinute());
/*  474 */     boolean bool4 = paramCollection.remove(DateTimeFieldType.millisOfSecond());
/*  475 */     if ((!bool1) && (!bool2) && (!bool3) && (!bool4)) {
/*  476 */       return;
/*      */     }
/*  478 */     if ((bool1) || (bool2) || (bool3) || (bool4)) {
/*  479 */       if ((paramBoolean2) && (paramBoolean3)) {
/*  480 */         throw new IllegalArgumentException("No valid ISO8601 format for fields because Date was reduced precision: " + paramCollection);
/*      */       }
/*  482 */       if (paramBoolean4) {
/*  483 */         paramDateTimeFormatterBuilder.appendLiteral('T');
/*      */       }
/*      */     }
/*  486 */     if (((!bool1) || (!bool2) || (!bool3)) && ((!bool1) || (bool3) || (bool4)))
/*      */     {
/*      */ 
/*  489 */       if ((paramBoolean2) && (paramBoolean4)) {
/*  490 */         throw new IllegalArgumentException("No valid ISO8601 format for fields because Time was truncated: " + paramCollection);
/*      */       }
/*  492 */       if ((bool1) || (((!bool2) || (!bool3)) && ((!bool2) || (bool4)) && (!bool3)))
/*      */       {
/*      */ 
/*  495 */         if (paramBoolean2) {
/*  496 */           throw new IllegalArgumentException("No valid ISO8601 format for fields: " + paramCollection);
/*      */         }
/*      */       }
/*      */     }
/*  500 */     if (bool1) {
/*  501 */       paramDateTimeFormatterBuilder.appendHourOfDay(2);
/*  502 */     } else if ((bool2) || (bool3) || (bool4)) {
/*  503 */       paramDateTimeFormatterBuilder.appendLiteral('-');
/*      */     }
/*  505 */     if ((paramBoolean1) && (bool1) && (bool2)) {
/*  506 */       paramDateTimeFormatterBuilder.appendLiteral(':');
/*      */     }
/*  508 */     if (bool2) {
/*  509 */       paramDateTimeFormatterBuilder.appendMinuteOfHour(2);
/*  510 */     } else if ((bool3) || (bool4)) {
/*  511 */       paramDateTimeFormatterBuilder.appendLiteral('-');
/*      */     }
/*  513 */     if ((paramBoolean1) && (bool2) && (bool3)) {
/*  514 */       paramDateTimeFormatterBuilder.appendLiteral(':');
/*      */     }
/*  516 */     if (bool3) {
/*  517 */       paramDateTimeFormatterBuilder.appendSecondOfMinute(2);
/*  518 */     } else if (bool4) {
/*  519 */       paramDateTimeFormatterBuilder.appendLiteral('-');
/*      */     }
/*  521 */     if (bool4) {
/*  522 */       paramDateTimeFormatterBuilder.appendLiteral('.');
/*  523 */       paramDateTimeFormatterBuilder.appendMillisOfSecond(3);
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
/*      */   private static void checkNotStrictISO(Collection paramCollection, boolean paramBoolean)
/*      */   {
/*  536 */     if (paramBoolean) {
/*  537 */       throw new IllegalArgumentException("No valid ISO8601 format for fields: " + paramCollection);
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
/*      */   private static void appendSeparator(DateTimeFormatterBuilder paramDateTimeFormatterBuilder, boolean paramBoolean)
/*      */   {
/*  550 */     if (paramBoolean) {
/*  551 */       paramDateTimeFormatterBuilder.appendLiteral('-');
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
/*      */   public static DateTimeFormatter dateParser()
/*      */   {
/*  569 */     if (dp == null) {
/*  570 */       DateTimeParser localDateTimeParser = new DateTimeFormatterBuilder().appendLiteral('T').append(offsetElement()).toParser();
/*      */       
/*      */ 
/*  573 */       dp = new DateTimeFormatterBuilder().append(dateElementParser()).appendOptional(localDateTimeParser).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  578 */     return dp;
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
/*      */   public static DateTimeFormatter localDateParser()
/*      */   {
/*  595 */     if (ldp == null) {
/*  596 */       ldp = dateElementParser().withZone(DateTimeZone.UTC);
/*      */     }
/*  598 */     return ldp;
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
/*      */   public static DateTimeFormatter dateElementParser()
/*      */   {
/*  612 */     if (dpe == null) {
/*  613 */       dpe = new DateTimeFormatterBuilder().append(null, new DateTimeParser[] { new DateTimeFormatterBuilder().append(yearElement()).appendOptional(new DateTimeFormatterBuilder().append(monthElement()).appendOptional(dayOfMonthElement().getParser()).toParser()).toParser(), new DateTimeFormatterBuilder().append(weekyearElement()).append(weekElement()).appendOptional(dayOfWeekElement().getParser()).toParser(), new DateTimeFormatterBuilder().append(yearElement()).append(dayOfYearElement()).toParser() }).toFormatter();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  635 */     return dpe;
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
/*      */   public static DateTimeFormatter timeParser()
/*      */   {
/*  651 */     if (tp == null) {
/*  652 */       tp = new DateTimeFormatterBuilder().appendOptional(literalTElement().getParser()).append(timeElementParser()).appendOptional(offsetElement().getParser()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  658 */     return tp;
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
/*      */   public static DateTimeFormatter localTimeParser()
/*      */   {
/*  676 */     if (ltp == null) {
/*  677 */       ltp = new DateTimeFormatterBuilder().appendOptional(literalTElement().getParser()).append(timeElementParser()).toFormatter().withZone(DateTimeZone.UTC);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  682 */     return ltp;
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
/*      */   public static DateTimeFormatter timeElementParser()
/*      */   {
/*  696 */     if (tpe == null)
/*      */     {
/*  698 */       DateTimeParser localDateTimeParser = new DateTimeFormatterBuilder().append(null, new DateTimeParser[] { new DateTimeFormatterBuilder().appendLiteral('.').toParser(), new DateTimeFormatterBuilder().appendLiteral(',').toParser() }).toParser();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  709 */       tpe = new DateTimeFormatterBuilder().append(hourElement()).append(null, new DateTimeParser[] { new DateTimeFormatterBuilder().append(minuteElement()).append(null, new DateTimeParser[] { new DateTimeFormatterBuilder().append(secondElement()).appendOptional(new DateTimeFormatterBuilder().append(localDateTimeParser).appendFractionOfSecond(1, 9).toParser()).toParser(), new DateTimeFormatterBuilder().append(localDateTimeParser).appendFractionOfMinute(1, 9).toParser(), null }).toParser(), new DateTimeFormatterBuilder().append(localDateTimeParser).appendFractionOfHour(1, 9).toParser(), null }).toFormatter();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  745 */     return tpe;
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
/*      */   public static DateTimeFormatter dateTimeParser()
/*      */   {
/*  767 */     if (dtp == null)
/*      */     {
/*      */ 
/*  770 */       DateTimeParser localDateTimeParser = new DateTimeFormatterBuilder().appendLiteral('T').append(timeElementParser()).appendOptional(offsetElement().getParser()).toParser();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  775 */       dtp = new DateTimeFormatterBuilder().append(null, new DateTimeParser[] { localDateTimeParser, dateOptionalTimeParser().getParser() }).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*  779 */     return dtp;
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
/*      */   public static DateTimeFormatter dateOptionalTimeParser()
/*      */   {
/*  800 */     if (dotp == null) {
/*  801 */       DateTimeParser localDateTimeParser = new DateTimeFormatterBuilder().appendLiteral('T').appendOptional(timeElementParser().getParser()).appendOptional(offsetElement().getParser()).toParser();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  806 */       dotp = new DateTimeFormatterBuilder().append(dateElementParser()).appendOptional(localDateTimeParser).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  811 */     return dotp;
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
/*      */   public static DateTimeFormatter localDateOptionalTimeParser()
/*      */   {
/*  834 */     if (ldotp == null) {
/*  835 */       DateTimeParser localDateTimeParser = new DateTimeFormatterBuilder().appendLiteral('T').append(timeElementParser()).toParser();
/*      */       
/*      */ 
/*      */ 
/*  839 */       ldotp = new DateTimeFormatterBuilder().append(dateElementParser()).appendOptional(localDateTimeParser).toFormatter().withZone(DateTimeZone.UTC);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  844 */     return ldotp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter date()
/*      */   {
/*  855 */     return yearMonthDay();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter time()
/*      */   {
/*  867 */     if (t == null) {
/*  868 */       t = new DateTimeFormatterBuilder().append(hourMinuteSecondFraction()).append(offsetElement()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  873 */     return t;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter timeNoMillis()
/*      */   {
/*  884 */     if (tx == null) {
/*  885 */       tx = new DateTimeFormatterBuilder().append(hourMinuteSecond()).append(offsetElement()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  890 */     return tx;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter tTime()
/*      */   {
/*  902 */     if (tt == null) {
/*  903 */       tt = new DateTimeFormatterBuilder().append(literalTElement()).append(time()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  908 */     return tt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter tTimeNoMillis()
/*      */   {
/*  920 */     if (ttx == null) {
/*  921 */       ttx = new DateTimeFormatterBuilder().append(literalTElement()).append(timeNoMillis()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  926 */     return ttx;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter dateTime()
/*      */   {
/*  937 */     if (dt == null) {
/*  938 */       dt = new DateTimeFormatterBuilder().append(date()).append(tTime()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  943 */     return dt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter dateTimeNoMillis()
/*      */   {
/*  954 */     if (dtx == null) {
/*  955 */       dtx = new DateTimeFormatterBuilder().append(date()).append(tTimeNoMillis()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  960 */     return dtx;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter ordinalDate()
/*      */   {
/*  971 */     if (od == null) {
/*  972 */       od = new DateTimeFormatterBuilder().append(yearElement()).append(dayOfYearElement()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  977 */     return od;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter ordinalDateTime()
/*      */   {
/*  989 */     if (odt == null) {
/*  990 */       odt = new DateTimeFormatterBuilder().append(ordinalDate()).append(tTime()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  995 */     return odt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter ordinalDateTimeNoMillis()
/*      */   {
/* 1007 */     if (odtx == null) {
/* 1008 */       odtx = new DateTimeFormatterBuilder().append(ordinalDate()).append(tTimeNoMillis()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1013 */     return odtx;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter weekDate()
/*      */   {
/* 1023 */     return weekyearWeekDay();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter weekDateTime()
/*      */   {
/* 1034 */     if (wdt == null) {
/* 1035 */       wdt = new DateTimeFormatterBuilder().append(weekDate()).append(tTime()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1040 */     return wdt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter weekDateTimeNoMillis()
/*      */   {
/* 1051 */     if (wdtx == null) {
/* 1052 */       wdtx = new DateTimeFormatterBuilder().append(weekDate()).append(tTimeNoMillis()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1057 */     return wdtx;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter basicDate()
/*      */   {
/* 1068 */     if (bd == null) {
/* 1069 */       bd = new DateTimeFormatterBuilder().appendYear(4, 4).appendFixedDecimal(DateTimeFieldType.monthOfYear(), 2).appendFixedDecimal(DateTimeFieldType.dayOfMonth(), 2).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1075 */     return bd;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter basicTime()
/*      */   {
/* 1087 */     if (bt == null) {
/* 1088 */       bt = new DateTimeFormatterBuilder().appendFixedDecimal(DateTimeFieldType.hourOfDay(), 2).appendFixedDecimal(DateTimeFieldType.minuteOfHour(), 2).appendFixedDecimal(DateTimeFieldType.secondOfMinute(), 2).appendLiteral('.').appendFractionOfSecond(3, 9).appendTimeZoneOffset("Z", false, 2, 2).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1097 */     return bt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter basicTimeNoMillis()
/*      */   {
/* 1108 */     if (btx == null) {
/* 1109 */       btx = new DateTimeFormatterBuilder().appendFixedDecimal(DateTimeFieldType.hourOfDay(), 2).appendFixedDecimal(DateTimeFieldType.minuteOfHour(), 2).appendFixedDecimal(DateTimeFieldType.secondOfMinute(), 2).appendTimeZoneOffset("Z", false, 2, 2).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1116 */     return btx;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter basicTTime()
/*      */   {
/* 1128 */     if (btt == null) {
/* 1129 */       btt = new DateTimeFormatterBuilder().append(literalTElement()).append(basicTime()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1134 */     return btt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter basicTTimeNoMillis()
/*      */   {
/* 1146 */     if (bttx == null) {
/* 1147 */       bttx = new DateTimeFormatterBuilder().append(literalTElement()).append(basicTimeNoMillis()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1152 */     return bttx;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter basicDateTime()
/*      */   {
/* 1163 */     if (bdt == null) {
/* 1164 */       bdt = new DateTimeFormatterBuilder().append(basicDate()).append(basicTTime()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1169 */     return bdt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter basicDateTimeNoMillis()
/*      */   {
/* 1180 */     if (bdtx == null) {
/* 1181 */       bdtx = new DateTimeFormatterBuilder().append(basicDate()).append(basicTTimeNoMillis()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1186 */     return bdtx;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter basicOrdinalDate()
/*      */   {
/* 1197 */     if (bod == null) {
/* 1198 */       bod = new DateTimeFormatterBuilder().appendYear(4, 4).appendFixedDecimal(DateTimeFieldType.dayOfYear(), 3).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1203 */     return bod;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter basicOrdinalDateTime()
/*      */   {
/* 1215 */     if (bodt == null) {
/* 1216 */       bodt = new DateTimeFormatterBuilder().append(basicOrdinalDate()).append(basicTTime()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1221 */     return bodt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter basicOrdinalDateTimeNoMillis()
/*      */   {
/* 1233 */     if (bodtx == null) {
/* 1234 */       bodtx = new DateTimeFormatterBuilder().append(basicOrdinalDate()).append(basicTTimeNoMillis()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1239 */     return bodtx;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter basicWeekDate()
/*      */   {
/* 1249 */     if (bwd == null) {
/* 1250 */       bwd = new DateTimeFormatterBuilder().appendWeekyear(4, 4).appendLiteral('W').appendFixedDecimal(DateTimeFieldType.weekOfWeekyear(), 2).appendFixedDecimal(DateTimeFieldType.dayOfWeek(), 1).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1257 */     return bwd;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter basicWeekDateTime()
/*      */   {
/* 1268 */     if (bwdt == null) {
/* 1269 */       bwdt = new DateTimeFormatterBuilder().append(basicWeekDate()).append(basicTTime()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1274 */     return bwdt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter basicWeekDateTimeNoMillis()
/*      */   {
/* 1285 */     if (bwdtx == null) {
/* 1286 */       bwdtx = new DateTimeFormatterBuilder().append(basicWeekDate()).append(basicTTimeNoMillis()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1291 */     return bwdtx;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter year()
/*      */   {
/* 1301 */     return yearElement();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter yearMonth()
/*      */   {
/* 1311 */     if (ym == null) {
/* 1312 */       ym = new DateTimeFormatterBuilder().append(yearElement()).append(monthElement()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1317 */     return ym;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter yearMonthDay()
/*      */   {
/* 1327 */     if (ymd == null) {
/* 1328 */       ymd = new DateTimeFormatterBuilder().append(yearElement()).append(monthElement()).append(dayOfMonthElement()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1334 */     return ymd;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter weekyear()
/*      */   {
/* 1343 */     return weekyearElement();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter weekyearWeek()
/*      */   {
/* 1353 */     if (ww == null) {
/* 1354 */       ww = new DateTimeFormatterBuilder().append(weekyearElement()).append(weekElement()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1359 */     return ww;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter weekyearWeekDay()
/*      */   {
/* 1369 */     if (wwd == null) {
/* 1370 */       wwd = new DateTimeFormatterBuilder().append(weekyearElement()).append(weekElement()).append(dayOfWeekElement()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1376 */     return wwd;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter hour()
/*      */   {
/* 1385 */     return hourElement();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter hourMinute()
/*      */   {
/* 1395 */     if (hm == null) {
/* 1396 */       hm = new DateTimeFormatterBuilder().append(hourElement()).append(minuteElement()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1401 */     return hm;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter hourMinuteSecond()
/*      */   {
/* 1411 */     if (hms == null) {
/* 1412 */       hms = new DateTimeFormatterBuilder().append(hourElement()).append(minuteElement()).append(secondElement()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1418 */     return hms;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter hourMinuteSecondMillis()
/*      */   {
/* 1430 */     if (hmsl == null) {
/* 1431 */       hmsl = new DateTimeFormatterBuilder().append(hourElement()).append(minuteElement()).append(secondElement()).appendLiteral('.').appendFractionOfSecond(3, 3).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1439 */     return hmsl;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter hourMinuteSecondFraction()
/*      */   {
/* 1451 */     if (hmsf == null) {
/* 1452 */       hmsf = new DateTimeFormatterBuilder().append(hourElement()).append(minuteElement()).append(secondElement()).append(fractionElement()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1459 */     return hmsf;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter dateHour()
/*      */   {
/* 1469 */     if (dh == null) {
/* 1470 */       dh = new DateTimeFormatterBuilder().append(date()).append(literalTElement()).append(hour()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1476 */     return dh;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter dateHourMinute()
/*      */   {
/* 1486 */     if (dhm == null) {
/* 1487 */       dhm = new DateTimeFormatterBuilder().append(date()).append(literalTElement()).append(hourMinute()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1493 */     return dhm;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter dateHourMinuteSecond()
/*      */   {
/* 1504 */     if (dhms == null) {
/* 1505 */       dhms = new DateTimeFormatterBuilder().append(date()).append(literalTElement()).append(hourMinuteSecond()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1511 */     return dhms;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter dateHourMinuteSecondMillis()
/*      */   {
/* 1523 */     if (dhmsl == null) {
/* 1524 */       dhmsl = new DateTimeFormatterBuilder().append(date()).append(literalTElement()).append(hourMinuteSecondMillis()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1530 */     return dhmsl;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeFormatter dateHourMinuteSecondFraction()
/*      */   {
/* 1542 */     if (dhmsf == null) {
/* 1543 */       dhmsf = new DateTimeFormatterBuilder().append(date()).append(literalTElement()).append(hourMinuteSecondFraction()).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1549 */     return dhmsf;
/*      */   }
/*      */   
/*      */   private static DateTimeFormatter yearElement()
/*      */   {
/* 1554 */     if (ye == null) {
/* 1555 */       ye = new DateTimeFormatterBuilder().appendYear(4, 9).toFormatter();
/*      */     }
/*      */     
/*      */ 
/* 1559 */     return ye;
/*      */   }
/*      */   
/*      */   private static DateTimeFormatter monthElement() {
/* 1563 */     if (mye == null) {
/* 1564 */       mye = new DateTimeFormatterBuilder().appendLiteral('-').appendMonthOfYear(2).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1569 */     return mye;
/*      */   }
/*      */   
/*      */   private static DateTimeFormatter dayOfMonthElement() {
/* 1573 */     if (dme == null) {
/* 1574 */       dme = new DateTimeFormatterBuilder().appendLiteral('-').appendDayOfMonth(2).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1579 */     return dme;
/*      */   }
/*      */   
/*      */   private static DateTimeFormatter weekyearElement() {
/* 1583 */     if (we == null) {
/* 1584 */       we = new DateTimeFormatterBuilder().appendWeekyear(4, 9).toFormatter();
/*      */     }
/*      */     
/*      */ 
/* 1588 */     return we;
/*      */   }
/*      */   
/*      */   private static DateTimeFormatter weekElement() {
/* 1592 */     if (wwe == null) {
/* 1593 */       wwe = new DateTimeFormatterBuilder().appendLiteral("-W").appendWeekOfWeekyear(2).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1598 */     return wwe;
/*      */   }
/*      */   
/*      */   private static DateTimeFormatter dayOfWeekElement() {
/* 1602 */     if (dwe == null) {
/* 1603 */       dwe = new DateTimeFormatterBuilder().appendLiteral('-').appendDayOfWeek(1).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1608 */     return dwe;
/*      */   }
/*      */   
/*      */   private static DateTimeFormatter dayOfYearElement() {
/* 1612 */     if (dye == null) {
/* 1613 */       dye = new DateTimeFormatterBuilder().appendLiteral('-').appendDayOfYear(3).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1618 */     return dye;
/*      */   }
/*      */   
/*      */   private static DateTimeFormatter literalTElement() {
/* 1622 */     if (lte == null) {
/* 1623 */       lte = new DateTimeFormatterBuilder().appendLiteral('T').toFormatter();
/*      */     }
/*      */     
/*      */ 
/* 1627 */     return lte;
/*      */   }
/*      */   
/*      */   private static DateTimeFormatter hourElement() {
/* 1631 */     if (hde == null) {
/* 1632 */       hde = new DateTimeFormatterBuilder().appendHourOfDay(2).toFormatter();
/*      */     }
/*      */     
/*      */ 
/* 1636 */     return hde;
/*      */   }
/*      */   
/*      */   private static DateTimeFormatter minuteElement() {
/* 1640 */     if (mhe == null) {
/* 1641 */       mhe = new DateTimeFormatterBuilder().appendLiteral(':').appendMinuteOfHour(2).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1646 */     return mhe;
/*      */   }
/*      */   
/*      */   private static DateTimeFormatter secondElement() {
/* 1650 */     if (sme == null) {
/* 1651 */       sme = new DateTimeFormatterBuilder().appendLiteral(':').appendSecondOfMinute(2).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1656 */     return sme;
/*      */   }
/*      */   
/*      */   private static DateTimeFormatter fractionElement() {
/* 1660 */     if (fse == null) {
/* 1661 */       fse = new DateTimeFormatterBuilder().appendLiteral('.').appendFractionOfSecond(3, 9).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1668 */     return fse;
/*      */   }
/*      */   
/*      */   private static DateTimeFormatter offsetElement() {
/* 1672 */     if (ze == null) {
/* 1673 */       ze = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 2, 4).toFormatter();
/*      */     }
/*      */     
/*      */ 
/* 1677 */     return ze;
/*      */   }
/*      */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\format\ISODateTimeFormat.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */