/*      */ package org.joda.time;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.ObjectStreamException;
/*      */ import java.io.Serializable;
/*      */ import java.lang.ref.Reference;
/*      */ import java.lang.ref.SoftReference;
/*      */ import java.util.HashMap;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.TimeZone;
/*      */ import org.joda.time.chrono.BaseChronology;
/*      */ import org.joda.time.field.FieldUtils;
/*      */ import org.joda.time.format.DateTimeFormat;
/*      */ import org.joda.time.format.DateTimeFormatter;
/*      */ import org.joda.time.format.DateTimeFormatterBuilder;
/*      */ import org.joda.time.format.FormatUtils;
/*      */ import org.joda.time.tz.DefaultNameProvider;
/*      */ import org.joda.time.tz.FixedDateTimeZone;
/*      */ import org.joda.time.tz.NameProvider;
/*      */ import org.joda.time.tz.Provider;
/*      */ import org.joda.time.tz.UTCProvider;
/*      */ import org.joda.time.tz.ZoneInfoProvider;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class DateTimeZone
/*      */   implements Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 5546345482340108586L;
/*   93 */   public static final DateTimeZone UTC = new FixedDateTimeZone("UTC", "UTC", 0, 0);
/*      */   
/*      */   private static Provider cProvider;
/*      */   
/*      */   private static NameProvider cNameProvider;
/*      */   
/*      */   private static Set cAvailableIDs;
/*      */   
/*      */   private static volatile DateTimeZone cDefault;
/*      */   
/*      */   private static DateTimeFormatter cOffsetFormatter;
/*      */   
/*      */   private static Map iFixedOffsetCache;
/*      */   
/*      */   private static Map cZoneIdConversion;
/*      */   
/*      */   private final String iID;
/*      */   
/*      */   static
/*      */   {
/*  113 */     setProvider0(null);
/*  114 */     setNameProvider0(null);
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
/*      */   public static DateTimeZone getDefault()
/*      */   {
/*  128 */     Object localObject1 = cDefault;
/*  129 */     if (localObject1 == null) {
/*  130 */       synchronized (DateTimeZone.class) {
/*  131 */         localObject1 = cDefault;
/*  132 */         if (localObject1 == null) {
/*  133 */           DateTimeZone localDateTimeZone = null;
/*      */           try {
/*      */             try {
/*  136 */               String str = System.getProperty("user.timezone");
/*  137 */               if (str != null) {
/*  138 */                 localDateTimeZone = forID(str);
/*      */               }
/*      */             }
/*      */             catch (RuntimeException localRuntimeException) {}
/*      */             
/*  143 */             if (localDateTimeZone == null) {
/*  144 */               localDateTimeZone = forTimeZone(TimeZone.getDefault());
/*      */             }
/*      */           }
/*      */           catch (IllegalArgumentException localIllegalArgumentException) {}
/*      */           
/*  149 */           if (localDateTimeZone == null) {
/*  150 */             localDateTimeZone = UTC;
/*      */           }
/*  152 */           cDefault = localObject1 = localDateTimeZone;
/*      */         }
/*      */       }
/*      */     }
/*  156 */     return (DateTimeZone)localObject1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void setDefault(DateTimeZone paramDateTimeZone)
/*      */     throws SecurityException
/*      */   {
/*  167 */     SecurityManager localSecurityManager = System.getSecurityManager();
/*  168 */     if (localSecurityManager != null) {
/*  169 */       localSecurityManager.checkPermission(new JodaTimePermission("DateTimeZone.setDefault"));
/*      */     }
/*  171 */     if (paramDateTimeZone == null) {
/*  172 */       throw new IllegalArgumentException("The datetime zone must not be null");
/*      */     }
/*  174 */     synchronized (DateTimeZone.class) {
/*  175 */       cDefault = paramDateTimeZone;
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
/*      */   public static DateTimeZone forID(String paramString)
/*      */   {
/*  196 */     if (paramString == null) {
/*  197 */       return getDefault();
/*      */     }
/*  199 */     if (paramString.equals("UTC")) {
/*  200 */       return UTC;
/*      */     }
/*  202 */     DateTimeZone localDateTimeZone = cProvider.getZone(paramString);
/*  203 */     if (localDateTimeZone != null) {
/*  204 */       return localDateTimeZone;
/*      */     }
/*  206 */     if ((paramString.startsWith("+")) || (paramString.startsWith("-"))) {
/*  207 */       int i = parseOffset(paramString);
/*  208 */       if (i == 0L) {
/*  209 */         return UTC;
/*      */       }
/*  211 */       paramString = printOffset(i);
/*  212 */       return fixedOffsetZone(paramString, i);
/*      */     }
/*      */     
/*  215 */     throw new IllegalArgumentException("The datetime zone id '" + paramString + "' is not recognised");
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
/*      */   public static DateTimeZone forOffsetHours(int paramInt)
/*      */     throws IllegalArgumentException
/*      */   {
/*  229 */     return forOffsetHoursMinutes(paramInt, 0);
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
/*      */   public static DateTimeZone forOffsetHoursMinutes(int paramInt1, int paramInt2)
/*      */     throws IllegalArgumentException
/*      */   {
/*  246 */     if ((paramInt1 == 0) && (paramInt2 == 0)) {
/*  247 */       return UTC;
/*      */     }
/*  249 */     if ((paramInt2 < 0) || (paramInt2 > 59)) {
/*  250 */       throw new IllegalArgumentException("Minutes out of range: " + paramInt2);
/*      */     }
/*  252 */     int i = 0;
/*      */     try {
/*  254 */       int j = FieldUtils.safeMultiply(paramInt1, 60);
/*  255 */       if (j < 0) {
/*  256 */         paramInt2 = FieldUtils.safeAdd(j, -paramInt2);
/*      */       } else {
/*  258 */         paramInt2 = FieldUtils.safeAdd(j, paramInt2);
/*      */       }
/*  260 */       i = FieldUtils.safeMultiply(paramInt2, 60000);
/*      */     } catch (ArithmeticException localArithmeticException) {
/*  262 */       throw new IllegalArgumentException("Offset is too large");
/*      */     }
/*  264 */     return forOffsetMillis(i);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DateTimeZone forOffsetMillis(int paramInt)
/*      */   {
/*  274 */     String str = printOffset(paramInt);
/*  275 */     return fixedOffsetZone(str, paramInt);
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
/*      */   public static DateTimeZone forTimeZone(TimeZone paramTimeZone)
/*      */   {
/*  294 */     if (paramTimeZone == null) {
/*  295 */       return getDefault();
/*      */     }
/*  297 */     String str1 = paramTimeZone.getID();
/*  298 */     if (str1.equals("UTC")) {
/*  299 */       return UTC;
/*      */     }
/*      */     
/*      */ 
/*  303 */     DateTimeZone localDateTimeZone = null;
/*  304 */     String str2 = getConvertedId(str1);
/*  305 */     if (str2 != null) {
/*  306 */       localDateTimeZone = cProvider.getZone(str2);
/*      */     }
/*  308 */     if (localDateTimeZone == null) {
/*  309 */       localDateTimeZone = cProvider.getZone(str1);
/*      */     }
/*  311 */     if (localDateTimeZone != null) {
/*  312 */       return localDateTimeZone;
/*      */     }
/*      */     
/*      */ 
/*  316 */     if (str2 == null) {
/*  317 */       str2 = paramTimeZone.getDisplayName();
/*  318 */       if ((str2.startsWith("GMT+")) || (str2.startsWith("GMT-"))) {
/*  319 */         str2 = str2.substring(3);
/*  320 */         int i = parseOffset(str2);
/*  321 */         if (i == 0L) {
/*  322 */           return UTC;
/*      */         }
/*  324 */         str2 = printOffset(i);
/*  325 */         return fixedOffsetZone(str2, i);
/*      */       }
/*      */     }
/*      */     
/*  329 */     throw new IllegalArgumentException("The datetime zone id '" + str1 + "' is not recognised");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static synchronized DateTimeZone fixedOffsetZone(String paramString, int paramInt)
/*      */   {
/*  341 */     if (paramInt == 0) {
/*  342 */       return UTC;
/*      */     }
/*  344 */     if (iFixedOffsetCache == null) {
/*  345 */       iFixedOffsetCache = new HashMap();
/*      */     }
/*      */     
/*  348 */     Reference localReference = (Reference)iFixedOffsetCache.get(paramString);
/*  349 */     if (localReference != null) {
/*  350 */       localObject = (DateTimeZone)localReference.get();
/*  351 */       if (localObject != null) {
/*  352 */         return (DateTimeZone)localObject;
/*      */       }
/*      */     }
/*  355 */     Object localObject = new FixedDateTimeZone(paramString, null, paramInt, paramInt);
/*  356 */     iFixedOffsetCache.put(paramString, new SoftReference(localObject));
/*  357 */     return (DateTimeZone)localObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Set getAvailableIDs()
/*      */   {
/*  366 */     return cAvailableIDs;
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
/*      */   public static Provider getProvider()
/*      */   {
/*  379 */     return cProvider;
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
/*      */   public static void setProvider(Provider paramProvider)
/*      */     throws SecurityException
/*      */   {
/*  393 */     SecurityManager localSecurityManager = System.getSecurityManager();
/*  394 */     if (localSecurityManager != null) {
/*  395 */       localSecurityManager.checkPermission(new JodaTimePermission("DateTimeZone.setProvider"));
/*      */     }
/*  397 */     setProvider0(paramProvider);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void setProvider0(Provider paramProvider)
/*      */   {
/*  407 */     if (paramProvider == null) {
/*  408 */       paramProvider = getDefaultProvider();
/*      */     }
/*  410 */     Set localSet = paramProvider.getAvailableIDs();
/*  411 */     if ((localSet == null) || (localSet.size() == 0)) {
/*  412 */       throw new IllegalArgumentException("The provider doesn't have any available ids");
/*      */     }
/*      */     
/*  415 */     if (!localSet.contains("UTC")) {
/*  416 */       throw new IllegalArgumentException("The provider doesn't support UTC");
/*      */     }
/*  418 */     if (!UTC.equals(paramProvider.getZone("UTC"))) {
/*  419 */       throw new IllegalArgumentException("Invalid UTC zone provided");
/*      */     }
/*  421 */     cProvider = paramProvider;
/*  422 */     cAvailableIDs = localSet;
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
/*      */   private static Provider getDefaultProvider()
/*      */   {
/*  435 */     Object localObject = null;
/*      */     try
/*      */     {
/*  438 */       String str = System.getProperty("org.joda.time.DateTimeZone.Provider");
/*      */       
/*  440 */       if (str != null) {
/*      */         try {
/*  442 */           localObject = (Provider)Class.forName(str).newInstance();
/*      */         } catch (Exception localException2) {
/*  444 */           Thread localThread2 = Thread.currentThread();
/*  445 */           localThread2.getThreadGroup().uncaughtException(localThread2, localException2);
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (SecurityException localSecurityException) {}
/*      */     
/*      */ 
/*  452 */     if (localObject == null) {
/*      */       try {
/*  454 */         localObject = new ZoneInfoProvider("org/joda/time/tz/data");
/*      */       } catch (Exception localException1) {
/*  456 */         Thread localThread1 = Thread.currentThread();
/*  457 */         localThread1.getThreadGroup().uncaughtException(localThread1, localException1);
/*      */       }
/*      */     }
/*      */     
/*  461 */     if (localObject == null) {
/*  462 */       localObject = new UTCProvider();
/*      */     }
/*      */     
/*  465 */     return (Provider)localObject;
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
/*      */   public static NameProvider getNameProvider()
/*      */   {
/*  478 */     return cNameProvider;
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
/*      */   public static void setNameProvider(NameProvider paramNameProvider)
/*      */     throws SecurityException
/*      */   {
/*  492 */     SecurityManager localSecurityManager = System.getSecurityManager();
/*  493 */     if (localSecurityManager != null) {
/*  494 */       localSecurityManager.checkPermission(new JodaTimePermission("DateTimeZone.setNameProvider"));
/*      */     }
/*  496 */     setNameProvider0(paramNameProvider);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void setNameProvider0(NameProvider paramNameProvider)
/*      */   {
/*  506 */     if (paramNameProvider == null) {
/*  507 */       paramNameProvider = getDefaultNameProvider();
/*      */     }
/*  509 */     cNameProvider = paramNameProvider;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static NameProvider getDefaultNameProvider()
/*      */   {
/*  521 */     Object localObject = null;
/*      */     try {
/*  523 */       String str = System.getProperty("org.joda.time.DateTimeZone.NameProvider");
/*  524 */       if (str != null) {
/*      */         try {
/*  526 */           localObject = (NameProvider)Class.forName(str).newInstance();
/*      */         } catch (Exception localException) {
/*  528 */           Thread localThread = Thread.currentThread();
/*  529 */           localThread.getThreadGroup().uncaughtException(localThread, localException);
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (SecurityException localSecurityException) {}
/*      */     
/*      */ 
/*  536 */     if (localObject == null) {
/*  537 */       localObject = new DefaultNameProvider();
/*      */     }
/*      */     
/*  540 */     return (NameProvider)localObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static synchronized String getConvertedId(String paramString)
/*      */   {
/*  551 */     Object localObject = cZoneIdConversion;
/*  552 */     if (localObject == null)
/*      */     {
/*  554 */       localObject = new HashMap();
/*  555 */       ((Map)localObject).put("GMT", "UTC");
/*  556 */       ((Map)localObject).put("MIT", "Pacific/Apia");
/*  557 */       ((Map)localObject).put("HST", "Pacific/Honolulu");
/*  558 */       ((Map)localObject).put("AST", "America/Anchorage");
/*  559 */       ((Map)localObject).put("PST", "America/Los_Angeles");
/*  560 */       ((Map)localObject).put("MST", "America/Denver");
/*  561 */       ((Map)localObject).put("PNT", "America/Phoenix");
/*  562 */       ((Map)localObject).put("CST", "America/Chicago");
/*  563 */       ((Map)localObject).put("EST", "America/New_York");
/*  564 */       ((Map)localObject).put("IET", "America/Indianapolis");
/*  565 */       ((Map)localObject).put("PRT", "America/Puerto_Rico");
/*  566 */       ((Map)localObject).put("CNT", "America/St_Johns");
/*  567 */       ((Map)localObject).put("AGT", "America/Buenos_Aires");
/*  568 */       ((Map)localObject).put("BET", "America/Sao_Paulo");
/*  569 */       ((Map)localObject).put("WET", "Europe/London");
/*  570 */       ((Map)localObject).put("ECT", "Europe/Paris");
/*  571 */       ((Map)localObject).put("ART", "Africa/Cairo");
/*  572 */       ((Map)localObject).put("CAT", "Africa/Harare");
/*  573 */       ((Map)localObject).put("EET", "Europe/Bucharest");
/*  574 */       ((Map)localObject).put("EAT", "Africa/Addis_Ababa");
/*  575 */       ((Map)localObject).put("MET", "Asia/Tehran");
/*  576 */       ((Map)localObject).put("NET", "Asia/Yerevan");
/*  577 */       ((Map)localObject).put("PLT", "Asia/Karachi");
/*  578 */       ((Map)localObject).put("IST", "Asia/Calcutta");
/*  579 */       ((Map)localObject).put("BST", "Asia/Dhaka");
/*  580 */       ((Map)localObject).put("VST", "Asia/Saigon");
/*  581 */       ((Map)localObject).put("CTT", "Asia/Shanghai");
/*  582 */       ((Map)localObject).put("JST", "Asia/Tokyo");
/*  583 */       ((Map)localObject).put("ACT", "Australia/Darwin");
/*  584 */       ((Map)localObject).put("AET", "Australia/Sydney");
/*  585 */       ((Map)localObject).put("SST", "Pacific/Guadalcanal");
/*  586 */       ((Map)localObject).put("NST", "Pacific/Auckland");
/*  587 */       cZoneIdConversion = (Map)localObject;
/*      */     }
/*  589 */     return (String)((Map)localObject).get(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */   private static int parseOffset(String paramString)
/*      */   {
/*  595 */     BaseChronology local1 = new BaseChronology() {
/*      */       public DateTimeZone getZone() {
/*  597 */         return null;
/*      */       }
/*      */       
/*  600 */       public Chronology withUTC() { return this; }
/*      */       
/*      */       public Chronology withZone(DateTimeZone paramAnonymousDateTimeZone) {
/*  603 */         return this;
/*      */       }
/*      */       
/*  606 */       public String toString() { return getClass().getName();
/*      */       }
/*  608 */     };
/*  609 */     return -(int)offsetFormatter().withChronology(local1).parseMillis(paramString);
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
/*      */   private static String printOffset(int paramInt)
/*      */   {
/*  622 */     StringBuffer localStringBuffer = new StringBuffer();
/*  623 */     if (paramInt >= 0) {
/*  624 */       localStringBuffer.append('+');
/*      */     } else {
/*  626 */       localStringBuffer.append('-');
/*  627 */       paramInt = -paramInt;
/*      */     }
/*      */     
/*  630 */     int i = paramInt / 3600000;
/*  631 */     FormatUtils.appendPaddedInteger(localStringBuffer, i, 2);
/*  632 */     paramInt -= i * 3600000;
/*      */     
/*  634 */     int j = paramInt / 60000;
/*  635 */     localStringBuffer.append(':');
/*  636 */     FormatUtils.appendPaddedInteger(localStringBuffer, j, 2);
/*  637 */     paramInt -= j * 60000;
/*  638 */     if (paramInt == 0) {
/*  639 */       return localStringBuffer.toString();
/*      */     }
/*      */     
/*  642 */     int k = paramInt / 1000;
/*  643 */     localStringBuffer.append(':');
/*  644 */     FormatUtils.appendPaddedInteger(localStringBuffer, k, 2);
/*  645 */     paramInt -= k * 1000;
/*  646 */     if (paramInt == 0) {
/*  647 */       return localStringBuffer.toString();
/*      */     }
/*      */     
/*  650 */     localStringBuffer.append('.');
/*  651 */     FormatUtils.appendPaddedInteger(localStringBuffer, paramInt, 3);
/*  652 */     return localStringBuffer.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static synchronized DateTimeFormatter offsetFormatter()
/*      */   {
/*  661 */     if (cOffsetFormatter == null) {
/*  662 */       cOffsetFormatter = new DateTimeFormatterBuilder().appendTimeZoneOffset(null, true, 2, 4).toFormatter();
/*      */     }
/*      */     
/*      */ 
/*  666 */     return cOffsetFormatter;
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
/*      */   protected DateTimeZone(String paramString)
/*      */   {
/*  681 */     if (paramString == null) {
/*  682 */       throw new IllegalArgumentException("Id must not be null");
/*      */     }
/*  684 */     this.iID = paramString;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final String getID()
/*      */   {
/*  696 */     return this.iID;
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
/*      */   public final String getShortName(long paramLong)
/*      */   {
/*  719 */     return getShortName(paramLong, null);
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
/*      */   public String getShortName(long paramLong, Locale paramLocale)
/*      */   {
/*  734 */     if (paramLocale == null) {
/*  735 */       paramLocale = Locale.getDefault();
/*      */     }
/*  737 */     String str1 = getNameKey(paramLong);
/*  738 */     if (str1 == null) {
/*  739 */       return this.iID;
/*      */     }
/*  741 */     String str2 = cNameProvider.getShortName(paramLocale, this.iID, str1);
/*  742 */     if (str2 != null) {
/*  743 */       return str2;
/*      */     }
/*  745 */     return printOffset(getOffset(paramLong));
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
/*      */   public final String getName(long paramLong)
/*      */   {
/*  759 */     return getName(paramLong, null);
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
/*      */   public String getName(long paramLong, Locale paramLocale)
/*      */   {
/*  774 */     if (paramLocale == null) {
/*  775 */       paramLocale = Locale.getDefault();
/*      */     }
/*  777 */     String str1 = getNameKey(paramLong);
/*  778 */     if (str1 == null) {
/*  779 */       return this.iID;
/*      */     }
/*  781 */     String str2 = cNameProvider.getName(paramLocale, this.iID, str1);
/*  782 */     if (str2 != null) {
/*  783 */       return str2;
/*      */     }
/*  785 */     return printOffset(getOffset(paramLong));
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
/*      */   public final int getOffset(ReadableInstant paramReadableInstant)
/*      */   {
/*  803 */     if (paramReadableInstant == null) {
/*  804 */       return getOffset(DateTimeUtils.currentTimeMillis());
/*      */     }
/*  806 */     return getOffset(paramReadableInstant.getMillis());
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
/*      */   public boolean isStandardOffset(long paramLong)
/*      */   {
/*  834 */     return getOffset(paramLong) == getStandardOffset(paramLong);
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
/*      */   public int getOffsetFromLocal(long paramLong)
/*      */   {
/*  869 */     int i = getOffset(paramLong);
/*      */     
/*  871 */     int j = getOffset(paramLong - i);
/*      */     
/*  873 */     if (i != j)
/*      */     {
/*      */ 
/*  876 */       if (i - j < 0)
/*      */       {
/*      */ 
/*      */ 
/*  880 */         long l1 = nextTransition(paramLong - i);
/*  881 */         long l2 = nextTransition(paramLong - j);
/*  882 */         if (l1 != l2) {
/*  883 */           return i;
/*      */         }
/*      */       }
/*      */     }
/*  887 */     return j;
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
/*      */   public long convertUTCToLocal(long paramLong)
/*      */   {
/*  901 */     int i = getOffset(paramLong);
/*  902 */     long l = paramLong + i;
/*      */     
/*  904 */     if (((paramLong ^ l) < 0L) && ((paramLong ^ i) >= 0L)) {
/*  905 */       throw new ArithmeticException("Adding time zone offset caused overflow");
/*      */     }
/*  907 */     return l;
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
/*      */   public long convertLocalToUTC(long paramLong, boolean paramBoolean)
/*      */   {
/*  924 */     int i = getOffset(paramLong);
/*      */     
/*  926 */     int j = getOffset(paramLong - i);
/*      */     
/*  928 */     if (i != j)
/*      */     {
/*      */ 
/*      */ 
/*  932 */       if ((paramBoolean) || (i < 0))
/*      */       {
/*  934 */         l1 = nextTransition(paramLong - i);
/*  935 */         if (l1 == paramLong - i) {
/*  936 */           l1 = Long.MAX_VALUE;
/*      */         }
/*  938 */         long l2 = nextTransition(paramLong - j);
/*  939 */         if (l2 == paramLong - j) {
/*  940 */           l2 = Long.MAX_VALUE;
/*      */         }
/*  942 */         if (l1 != l2)
/*      */         {
/*  944 */           if (paramBoolean)
/*      */           {
/*  946 */             throw new IllegalArgumentException("Illegal instant due to time zone offset transition: " + DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS").print(new Instant(paramLong)) + " (" + getID() + ")");
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  953 */           j = i;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  959 */     long l1 = paramLong - j;
/*      */     
/*  961 */     if (((paramLong ^ l1) < 0L) && ((paramLong ^ j) < 0L)) {
/*  962 */       throw new ArithmeticException("Subtracting time zone offset caused overflow");
/*      */     }
/*  964 */     return l1;
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
/*      */   public long getMillisKeepLocal(DateTimeZone paramDateTimeZone, long paramLong)
/*      */   {
/*  978 */     if (paramDateTimeZone == null) {
/*  979 */       paramDateTimeZone = getDefault();
/*      */     }
/*  981 */     if (paramDateTimeZone == this) {
/*  982 */       return paramLong;
/*      */     }
/*  984 */     long l = paramLong + getOffset(paramLong);
/*  985 */     return l - paramDateTimeZone.getOffsetFromLocal(l);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isLocalDateTimeGap(LocalDateTime paramLocalDateTime)
/*      */   {
/* 1095 */     if (isFixed()) {
/* 1096 */       return false;
/*      */     }
/*      */     try {
/* 1099 */       paramLocalDateTime.toDateTime(this);
/* 1100 */       return false;
/*      */     } catch (IllegalArgumentException localIllegalArgumentException) {}
/* 1102 */     return true;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TimeZone toTimeZone()
/*      */   {
/* 1143 */     return TimeZone.getTimeZone(this.iID);
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
/*      */   public int hashCode()
/*      */   {
/* 1160 */     return 57 + getID().hashCode();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/* 1168 */     return getID();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1178 */   protected Object writeReplace()
/* 1178 */     throws ObjectStreamException { return new Stub(this.iID); }
/*      */   
/*      */   public abstract String getNameKey(long paramLong);
/*      */   
/*      */   public abstract int getOffset(long paramLong);
/*      */   
/*      */   public abstract int getStandardOffset(long paramLong);
/*      */   
/*      */   public abstract boolean isFixed();
/*      */   
/*      */   public abstract long nextTransition(long paramLong);
/*      */   
/*      */   public abstract long previousTransition(long paramLong);
/*      */   
/*      */   public abstract boolean equals(Object paramObject);
/*      */   
/*      */   private static final class Stub implements Serializable {
/* 1195 */     Stub(String paramString) { this.iID = paramString; }
/*      */     
/*      */     private static final long serialVersionUID = -6471952376487863581L;
/*      */     private transient String iID;
/* 1199 */     private void writeObject(ObjectOutputStream paramObjectOutputStream) throws IOException { paramObjectOutputStream.writeUTF(this.iID); }
/*      */     
/*      */     private void readObject(ObjectInputStream paramObjectInputStream) throws IOException
/*      */     {
/* 1203 */       this.iID = paramObjectInputStream.readUTF();
/*      */     }
/*      */     
/*      */     private Object readResolve() throws ObjectStreamException {
/* 1207 */       return DateTimeZone.forID(this.iID);
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\DateTimeZone.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */