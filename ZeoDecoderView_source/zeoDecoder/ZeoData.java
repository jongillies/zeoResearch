/*      */ package zeoDecoder;
/*      */ 
/*      */ import java.io.PrintWriter;
/*      */ import java.io.StringWriter;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.ByteOrder;
/*      */ import java.util.Calendar;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.TimeZone;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ZeoData
/*      */   implements Comparable<ZeoData>
/*      */ {
/*   55 */   private static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("UTC");
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final long FIRST_RECORD_TIMEOUT = 10L;
/*      */   
/*      */ 
/*      */ 
/*   64 */   public static final byte[] IDENTIFIER = { 83, 
/*   65 */     76, 
/*   66 */     69, 
/*   67 */     69, 
/*   68 */     80 };
/*      */   
/*      */ 
/*      */   public static final int IDENTIFIER_SIZE = 6;
/*      */   
/*      */ 
/*      */   public static final int VERSION_SIZE = 2;
/*      */   
/*      */ 
/*      */   public static final int HEADER_SIZE = 8;
/*      */   
/*      */ 
/*   80 */   public static final byte[] V22 = { 22 };
/*      */   
/*      */ 
/*      */ 
/*      */   public static final int V22_SIZE = 1680;
/*      */   
/*      */ 
/*      */ 
/*      */   private static final int ALARM_EVENTS_SAVED = 2;
/*      */   
/*      */ 
/*      */ 
/*      */   private static final int ASSERT_NAME_MAX = 20;
/*      */   
/*      */ 
/*      */ 
/*      */   private static final int EVENTS_SAVED = 4;
/*      */   
/*      */ 
/*      */ 
/*      */   private static final int HEADBAND_IMPEDANCE_SIZE = 144;
/*      */   
/*      */ 
/*      */ 
/*      */   private static final int HEADBAND_PACKETS_SIZE = 144;
/*      */   
/*      */ 
/*      */ 
/*      */   private static final int HEADBAND_RSSI_SIZE = 144;
/*      */   
/*      */ 
/*      */ 
/*      */   private static final int HEADBAND_STATUS_SIZE = 36;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final int MAX_RECORD_BYTES = 2000;
/*      */   
/*      */ 
/*      */ 
/*      */   private static final int SNOOZE_EVENTS_SAVED = 9;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final int DAYS_PER_MONTH = 30;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final int DAYS_PER_MONTH_MAX = 31;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final int DAYS_PER_WEEK = 7;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final int HOURS_PER_DAY = 24;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final int MINUTES_PER_HOUR = 60;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final int MONTHS_PER_YEAR = 12;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final int MSEC_PER_SECOND = 1000;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final int SECONDS_PER_EPOCH = 30;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final int SECONDS_PER_MINUTE = 60;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final int SECONDS_PER_HOUR = 3600;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final int SECONDS_PER_DAY = 86400;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final int SECONDS_PER_MONTH = 2592000;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final int SECONDS_PER_WEEK = 604800;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final int EPOCHS_PER_MINUTE = 2;
/*      */   
/*      */ 
/*      */   public static final int EPOCHS_PER_HOUR = 120;
/*      */   
/*      */ 
/*      */   public static final int HYP_BASE_STEP = 30;
/*      */   
/*      */ 
/*      */   public static final int HYP_DISPLAY_STEP = 300;
/*      */   
/*      */ 
/*      */   public static final int HYP_SECONDS_MAX = 57600;
/*      */   
/*      */ 
/*      */   public static final int HYP_BASE_LENGTH = 1920;
/*      */   
/*      */ 
/*      */   public static final int HYP_BASE_PER_DISPLAY = 10;
/*      */   
/*      */ 
/*      */   public static final int HYP_DISPLAY_LENGTH = 192;
/*      */   
/*      */ 
/*  200 */   public boolean is_nap = false;
/*      */   
/*      */   private int base_hypnogram_count;
/*      */   
/*      */   private long crc;
/*      */   
/*      */   private Calendar current_time;
/*      */   
/*      */   private Calendar end_of_night;
/*      */   
/*      */   private boolean reset_record;
/*      */   
/*      */   private Calendar sleep_date;
/*      */   
/*      */   private Calendar start_of_night;
/*      */   
/*      */   private boolean airplane_mode;
/*      */   private AlarmReason alarm_reason;
/*      */   private byte backlight;
/*      */   private ClockMode clock_mode;
/*      */   private boolean sleep_valid;
/*      */   private int snooze_time;
/*      */   private WakeTone wake_tone;
/*      */   private int wake_window;
/*      */   private boolean wdt_reset;
/*      */   private WriteReason write_reason;
/*      */   private boolean zeo_wake_on;
/*      */   private Calendar airplane_off;
/*      */   private Calendar airplane_on;
/*  229 */   private TimeChange[] alarm_change = new TimeChange[4];
/*      */   
/*  231 */   private char[] assert_function_name = new char[20];
/*      */   
/*      */   private int assert_line_number;
/*      */   
/*      */   private Calendar factory_reset;
/*      */   private long headband_id;
/*  237 */   private short[] headband_impedance = new short[''];
/*  238 */   private short[] headband_packets = new short[''];
/*  239 */   private byte[] headband_rssi = new byte[''];
/*  240 */   private short[] headband_status = new short[36];
/*      */   
/*      */   private int id_hw;
/*      */   
/*      */   private int id_sw;
/*      */   private int format_version;
/*  246 */   private TimeChange[] rtc_change = new TimeChange[4];
/*      */   
/*      */   private Calendar sensor_life_reset;
/*      */   
/*      */   private Calendar sleep_stat_reset;
/*      */   
/*  252 */   private Calendar[] alarm_ring = new Calendar[2];
/*  253 */   private Calendar[] alarm_snooze = new Calendar[9];
/*      */   
/*      */   private Calendar alarm_off;
/*      */   
/*      */   private int awakenings;
/*      */   
/*      */   private int awakenings_average;
/*      */   
/*      */   private int sleep_rating;
/*      */   
/*      */   private int time_in_deep;
/*      */   
/*      */   private int time_in_deep_average;
/*      */   
/*      */   private int time_in_deep_best;
/*      */   
/*      */   private int time_in_light;
/*      */   
/*      */   private int time_in_light_average;
/*      */   private int time_in_rem;
/*      */   private int time_in_rem_average;
/*      */   private int time_in_rem_best;
/*      */   private int time_in_wake;
/*      */   private int time_in_wake_average;
/*      */   private int time_to_z;
/*      */   private int time_to_z_average;
/*      */   private int total_z;
/*      */   private int total_z_average;
/*      */   private int total_z_best;
/*      */   private int zq_score;
/*      */   private int zq_score_average;
/*      */   private int zq_score_best;
/*      */   private int display_hypnogram_forced_index;
/*      */   private int display_hypnogram_forced_stage;
/*      */   private Calendar hypnogram_start_time;
/*  288 */   private byte[] base_hypnogram = new byte['ހ'];
/*      */   
/*      */ 
/*  291 */   private byte[] display_hypnogram = new byte['À'];
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private int display_hypnogram_count;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private Calendar alarm_set_time;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private Calendar rise_time;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ZeoData(ByteBuffer in, int version)
/*      */   {
/*  314 */     long[] change_time = new long[4];
/*  315 */     long[] change_value = new long[4];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  320 */     this.format_version = version;
/*      */     
/*      */ 
/*  323 */     in.order(ByteOrder.LITTLE_ENDIAN);
/*      */     
/*      */ 
/*  326 */     long current_timestamp = read_uint32(in);
/*  327 */     this.current_time = timestamp_to_calendar(current_timestamp);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  333 */     int crc_index = in.position();
/*  334 */     this.crc = read_uint32(in);
/*  335 */     in.putInt(crc_index, 0);
/*      */     
/*      */ 
/*  338 */     int tmp = (int)read_uint32(in);
/*      */     
/*  340 */     this.airplane_mode = ((tmp & 0x1) == 1);
/*  341 */     this.alarm_reason = AlarmReason.convert(tmp >> 1 & 0x7);
/*  342 */     this.backlight = ((byte)(tmp >> 4 & 0xF));
/*  343 */     this.clock_mode = ((tmp >> 8 & 0x1) == 1 ? ClockMode.HOUR_12 : 
/*  344 */       ClockMode.MILITARY);
/*  345 */     this.sleep_valid = ((tmp >> 9 & 0x1) == 1);
/*  346 */     this.snooze_time = (tmp >> 10 & 0x1F);
/*  347 */     this.wake_tone = WakeTone.convert(tmp >> 15 & 0x7);
/*  348 */     this.wake_window = (tmp >> 18 & 0x3F);
/*  349 */     this.write_reason = WriteReason.convert(tmp >> 24 & 0x7);
/*  350 */     this.zeo_wake_on = ((tmp >> 27 & 0x1) == 1);
/*  351 */     this.wdt_reset = ((tmp >> 28 & 0x1) == 1);
/*      */     
/*      */ 
/*  354 */     this.airplane_off = timestamp_to_calendar(read_uint32(in));
/*  355 */     this.airplane_on = timestamp_to_calendar(read_uint32(in));
/*      */     
/*  357 */     for (int i = 0; i < 4; i++) {
/*  358 */       change_time[i] = read_uint32(in);
/*      */     }
/*  360 */     for (int i = 0; i < 4; i++) {
/*  361 */       change_value[i] = read_uint32(in);
/*      */     }
/*  363 */     for (int i = 0; i < 4; i++) {
/*  364 */       this.alarm_change[i] = new TimeChange(change_time[i], change_value[i]);
/*      */     }
/*      */     
/*  367 */     for (int i = 0; i < 20; i++) {
/*  368 */       this.assert_function_name[i] = ((char)read_uint8(in));
/*      */     }
/*  370 */     this.assert_line_number = read_int32(in);
/*      */     
/*  372 */     this.factory_reset = timestamp_to_calendar(read_uint32(in));
/*  373 */     this.headband_id = read_uint32(in);
/*  374 */     for (int i = 0; i < 144; i++) {
/*  375 */       this.headband_impedance[i] = read_uint8(in);
/*      */     }
/*  377 */     for (int i = 0; i < 144; i++) {
/*  378 */       this.headband_packets[i] = read_uint8(in);
/*      */     }
/*  380 */     for (int i = 0; i < 144; i++) {
/*  381 */       this.headband_rssi[i] = read_int8(in);
/*      */     }
/*  383 */     for (int i = 0; i < 36; i++) {
/*  384 */       this.headband_status[i] = read_uint8(in);
/*      */     }
/*  386 */     this.id_hw = read_uint16(in);
/*  387 */     this.id_sw = read_uint16(in);
/*      */     
/*  389 */     for (int i = 0; i < 4; i++) {
/*  390 */       change_time[i] = read_uint32(in);
/*      */     }
/*  392 */     for (int i = 0; i < 4; i++) {
/*  393 */       change_value[i] = read_uint32(in);
/*      */     }
/*  395 */     for (int i = 0; i < 4; i++) {
/*  396 */       this.rtc_change[i] = new TimeChange(change_time[i], change_value[i]);
/*      */     }
/*  398 */     this.sensor_life_reset = timestamp_to_calendar(read_uint32(in));
/*  399 */     this.sleep_stat_reset = timestamp_to_calendar(read_uint32(in));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  406 */     int alarm_ring_count = 2;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  411 */     this.alarm_ring[1] = timestamp_to_calendar(0L);
/*      */     
/*  413 */     for (int i = 0; i < alarm_ring_count; i++) {
/*  414 */       long alarm_ring_value = read_uint32(in);
/*  415 */       Calendar alarm_ring_time = timestamp_to_calendar(alarm_ring_value);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  420 */       if (i == 0) {
/*  421 */         this.alarm_ring[0] = alarm_ring_time;
/*  422 */       } else if (alarm_ring_value != 0L) {
/*  423 */         this.alarm_ring[1] = alarm_ring_time;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  428 */     for (int i = 0; i < 9; i++) {
/*  429 */       this.alarm_snooze[i] = timestamp_to_calendar(read_uint32(in));
/*      */     }
/*  431 */     this.alarm_off = timestamp_to_calendar(read_uint32(in));
/*      */     
/*  433 */     this.awakenings = read_uint16(in);
/*  434 */     this.awakenings_average = read_uint16(in);
/*  435 */     this.end_of_night = timestamp_to_calendar(read_uint32(in));
/*  436 */     this.start_of_night = timestamp_to_calendar(read_uint32(in));
/*  437 */     this.time_in_deep = read_uint16(in);
/*  438 */     this.time_in_deep_average = read_uint16(in);
/*  439 */     this.time_in_deep_best = read_uint16(in);
/*  440 */     this.time_in_light = read_uint16(in);
/*  441 */     this.time_in_light_average = read_uint16(in);
/*  442 */     this.time_in_rem = read_uint16(in);
/*  443 */     this.time_in_rem_average = read_uint16(in);
/*  444 */     this.time_in_rem_best = read_uint16(in);
/*  445 */     this.time_in_wake = read_uint16(in);
/*  446 */     this.time_in_wake_average = read_uint16(in);
/*  447 */     this.time_to_z = read_uint16(in);
/*  448 */     this.time_to_z_average = read_uint16(in);
/*  449 */     this.total_z = read_uint16(in);
/*  450 */     this.total_z_average = read_uint16(in);
/*  451 */     this.total_z_best = read_uint16(in);
/*  452 */     this.zq_score = read_uint16(in);
/*  453 */     this.zq_score_average = read_uint16(in);
/*  454 */     this.zq_score_best = read_uint16(in);
/*      */     
/*      */ 
/*  457 */     this.display_hypnogram_forced_index = read_uint16(in);
/*      */     
/*      */ 
/*  460 */     this.display_hypnogram_forced_stage = read_uint16(in);
/*      */     
/*      */ 
/*  463 */     this.hypnogram_start_time = timestamp_to_calendar(read_uint32(in));
/*      */     
/*      */ 
/*  466 */     this.sleep_rating = 0;
/*  467 */     this.sleep_rating = read_uint8(in);
/*  468 */     read_uint8(in);
/*  469 */     read_uint8(in);
/*  470 */     read_uint8(in);
/*  471 */     read_uint32(in);
/*      */     
/*      */ 
/*  474 */     this.base_hypnogram_count = ((int)read_uint32(in));
/*      */     
/*  476 */     for (int i = 0; i < 1920; i += 2)
/*      */     {
/*      */ 
/*      */ 
/*  480 */       int hypnogram_data = read_uint8(in);
/*  481 */       this.base_hypnogram[i] = ((byte)(hypnogram_data & 0xF));
/*  482 */       this.base_hypnogram[(i + 1)] = ((byte)(hypnogram_data >> 4));
/*      */     }
/*      */     
/*      */ 
/*  486 */     assert (in.remaining() == 0);
/*      */     
/*      */ 
/*  489 */     make_display_hypnogram_from_base();
/*      */     
/*      */ 
/*  492 */     this.rise_time = compute_rise_time();
/*      */     
/*      */ 
/*  495 */     this.alarm_set_time = compute_alarm_set_time();
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
/*      */   static String calendar_to_human_string(Calendar calendar)
/*      */   {
/*  508 */     if (calendar == null) {
/*  509 */       return null;
/*      */     }
/*  511 */     StringWriter txt = new StringWriter();
/*  512 */     PrintWriter out = new PrintWriter(txt);
/*      */     
/*  514 */     int day = calendar.get(5);
/*  515 */     int month = calendar.get(2) + 1;
/*  516 */     int year = calendar.get(1);
/*      */     
/*      */ 
/*      */ 
/*  520 */     if ((year != 1970) || (month != 1) || (day != 1)) {
/*  521 */       out.printf("%04d-%02d-%02d T ", new Object[] { Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day) });
/*      */     }
/*  523 */     out.printf("%02d:%02d:%02d", new Object[] {
/*  524 */       Integer.valueOf(calendar.get(11)), 
/*  525 */       Integer.valueOf(calendar.get(12)), 
/*  526 */       Integer.valueOf(calendar.get(13)) });
/*      */     
/*      */ 
/*  529 */     out.printf(" (%d)", new Object[] { Long.valueOf(calendar.getTimeInMillis() / 1000L) });
/*      */     
/*  531 */     return txt.toString();
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
/*      */   static String calendar_to_xml(Calendar calendar)
/*      */   {
/*  544 */     if (calendar == null) {
/*  545 */       return "";
/*      */     }
/*  547 */     StringWriter txt = new StringWriter();
/*  548 */     PrintWriter out = new PrintWriter(txt);
/*      */     
/*  550 */     out.printf("<year>%d</year>", new Object[] { Integer.valueOf(calendar.get(1)) });
/*  551 */     out.println();
/*  552 */     out.printf("<month>%d</month>", new Object[] { Integer.valueOf(calendar.get(2) + 1) });
/*  553 */     out.println();
/*  554 */     out.printf("<day>%d</day>", new Object[] { Integer.valueOf(calendar.get(5)) });
/*  555 */     out.println();
/*  556 */     out.printf("<hour>%d</hour>", new Object[] { Integer.valueOf(calendar.get(11)) });
/*  557 */     out.println();
/*  558 */     out.printf("<minute>%d</minute>", new Object[] { Integer.valueOf(calendar.get(12)) });
/*  559 */     out.println();
/*  560 */     out.printf("<second>%d</second>", new Object[] { Integer.valueOf(calendar.get(13)) });
/*  561 */     out.println();
/*  562 */     return txt.toString();
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
/*      */   public int compareTo(ZeoData other)
/*      */   {
/*  587 */     Calendar other_start_of_night = other.get_start_of_night();
/*      */     
/*      */ 
/*  590 */     if ((this.start_of_night == null) && (other_start_of_night == null))
/*  591 */       return 0;
/*  592 */     if (this.start_of_night == null)
/*  593 */       return -1;
/*  594 */     if (other_start_of_night == null) {
/*  595 */       return 1;
/*      */     }
/*      */     
/*      */ 
/*  599 */     if (this.start_of_night.before(other_start_of_night))
/*  600 */       return -1;
/*  601 */     if (this.start_of_night.after(other_start_of_night)) {
/*  602 */       return 1;
/*      */     }
/*  604 */     return 0;
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
/*      */   public int compareLength(ZeoData other)
/*      */   {
/*  645 */     Calendar other_start_of_night = other.get_start_of_night();
/*  646 */     Calendar other_end_of_night = other.get_end_of_night();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  652 */     if ((this.end_of_night == null) && (other_end_of_night == null))
/*  653 */       return 0;
/*  654 */     if (this.end_of_night == null)
/*  655 */       return -1;
/*  656 */     if (other_end_of_night == null) {
/*  657 */       return 1;
/*      */     }
/*      */     
/*  660 */     assert ((this.start_of_night != null) && (other_start_of_night != null));
/*      */     
/*      */ 
/*  663 */     long other_length = other_end_of_night.getTimeInMillis() - 
/*  664 */       other_start_of_night.getTimeInMillis();
/*  665 */     long this_length = this.end_of_night.getTimeInMillis() - 
/*  666 */       this.start_of_night.getTimeInMillis();
/*      */     
/*      */ 
/*  669 */     if (this_length < other_length)
/*  670 */       return -1;
/*  671 */     if (this_length > other_length) {
/*  672 */       return 1;
/*      */     }
/*      */     
/*  675 */     int other_hypnogram_count = other.get_base_hypnogram_count();
/*  676 */     int this_hypnogram_count = this.base_hypnogram_count;
/*      */     
/*  678 */     if (this_hypnogram_count < other_hypnogram_count)
/*  679 */       return -1;
/*  680 */     if (this_hypnogram_count > other_hypnogram_count) {
/*  681 */       return 1;
/*      */     }
/*  683 */     return 0;
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
/*      */   public Calendar compute_alarm_set_time()
/*      */   {
/*  711 */     if ((this.start_of_night == null) || (this.end_of_night == null)) {
/*  712 */       return null;
/*      */     }
/*      */     
/*      */     Calendar cutoff;
/*      */     
/*      */     Calendar cutoff;
/*      */     
/*  719 */     if (this.alarm_ring[0] == null)
/*      */     {
/*      */ 
/*      */ 
/*  723 */       cutoff = this.end_of_night;
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  728 */       cutoff = this.alarm_ring[0];
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  734 */     TimeChange latest_change = null;
/*  735 */     for (int i = 0; i < 4; i++)
/*      */     {
/*  737 */       Calendar change_time = this.alarm_change[i].getTimeAsCalendar();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  743 */       if ((change_time == null) && (i > 0)) {
/*      */         break;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  752 */       if ((change_time == null) || (change_time.before(cutoff)))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  759 */         if ((latest_change == null) || 
/*  760 */           (latest_change.getTimeAsCalendar() == null) || 
/*  761 */           (change_time.after(latest_change.getTimeAsCalendar())))
/*      */         {
/*  763 */           latest_change = this.alarm_change[i];
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  768 */     if (latest_change == null)
/*      */     {
/*      */ 
/*      */ 
/*  772 */       return null; }
/*  773 */     if (latest_change.getValueAsCalendar() == null)
/*      */     {
/*  775 */       return null;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  782 */     Calendar calendar = latest_change.getValueAsCalendar();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  787 */     calendar.set(5, 
/*  788 */       this.start_of_night.get(5));
/*  789 */     calendar.set(2, 
/*  790 */       this.start_of_night.get(2));
/*  791 */     calendar.set(1, 
/*  792 */       this.start_of_night.get(1));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  799 */     if (calendar.before(this.start_of_night)) {
/*  800 */       calendar.add(5, 1);
/*      */     }
/*      */     
/*  803 */     return calendar;
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
/*      */   public Calendar compute_rise_time()
/*      */   {
/*  817 */     boolean found_sleep = false;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  823 */     if (this.hypnogram_start_time == null) {
/*  824 */       return null;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  830 */     for (int index = this.display_hypnogram_count - 1; index >= 0; index--) {
/*  831 */       if (SleepStage.convert(this.display_hypnogram[index]).is_sleep()) {
/*  832 */         found_sleep = true;
/*  833 */         break;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  838 */     if (!found_sleep) {
/*  839 */       return null;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  849 */     Calendar calendar = (Calendar)this.hypnogram_start_time.clone();
/*  850 */     calendar.add(13, 300 * (index + 1));
/*  851 */     return calendar;
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
/*      */   private SleepStage determine_bin_value(SleepStage[] values, int num_values)
/*      */   {
/*  874 */     int[] counts = new int[SleepStage.values().length];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  880 */     for (int i = 0; i < num_values; i++) {
/*  881 */       counts[values[i].ordinal()] += 1;
/*      */     }
/*      */     
/*      */ 
/*  885 */     if (counts[SleepStage.UNDEFINED.ordinal()] == num_values) {
/*  886 */       return SleepStage.UNDEFINED;
/*      */     }
/*      */     
/*      */ 
/*  890 */     if (counts[SleepStage.WAKE.ordinal()] != 0) {
/*  891 */       return SleepStage.WAKE;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  897 */     int max_count = counts[SleepStage.REM.ordinal()];
/*  898 */     SleepStage result = SleepStage.REM;
/*      */     
/*  900 */     for (i = SleepStage.LIGHT.ordinal(); 
/*  901 */         i < SleepStage.values().length; 
/*  902 */         i++) {
/*  903 */       if (max_count < counts[i]) {
/*  904 */         max_count = counts[i];
/*  905 */         result = SleepStage.convert(i);
/*      */       }
/*      */     }
/*  908 */     return result;
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
/*      */   private String epochs_to_human_string(int epochs)
/*      */   {
/*  923 */     StringWriter txt = new StringWriter();
/*  924 */     PrintWriter out = new PrintWriter(txt);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  932 */     int minutes = (epochs + 1) / 2;
/*      */     
/*  934 */     int hours = minutes / 60;
/*  935 */     minutes %= 60;
/*      */     
/*  937 */     out.printf("%2d:%02d (%4d epochs)", new Object[] { Integer.valueOf(hours), Integer.valueOf(minutes), Integer.valueOf(epochs) });
/*  938 */     return txt.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean get_airplane_mode()
/*      */   {
/*  948 */     return this.airplane_mode;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Calendar get_airplane_off()
/*      */   {
/*  959 */     return this.airplane_off;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Calendar get_airplane_on()
/*      */   {
/*  970 */     return this.airplane_on;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TimeChange[] get_alarm_change()
/*      */   {
/*  980 */     return this.alarm_change;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Calendar get_alarm_off()
/*      */   {
/*  991 */     return this.alarm_off;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public AlarmReason get_alarm_reason()
/*      */   {
/* 1001 */     return this.alarm_reason;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Calendar[] get_alarm_ring()
/*      */   {
/* 1012 */     return this.alarm_ring;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Calendar get_alarm_set_time()
/*      */   {
/* 1022 */     return this.alarm_set_time;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Calendar[] get_alarm_snooze()
/*      */   {
/* 1033 */     return this.alarm_snooze;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public char[] get_assert_function_name()
/*      */   {
/* 1044 */     return this.assert_function_name;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_assert_line_number()
/*      */   {
/* 1055 */     return this.assert_line_number;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_awakenings()
/*      */   {
/* 1065 */     return this.awakenings;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_awakenings_average()
/*      */   {
/* 1075 */     return this.awakenings_average;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte get_backlight()
/*      */   {
/* 1085 */     return this.backlight;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] get_base_hypnogram()
/*      */   {
/* 1095 */     return this.base_hypnogram;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_base_hypnogram_count()
/*      */   {
/* 1105 */     return this.base_hypnogram_count;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ClockMode get_clock_mode()
/*      */   {
/* 1115 */     return this.clock_mode;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long get_crc()
/*      */   {
/* 1125 */     return this.crc;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Calendar get_current_time()
/*      */   {
/* 1135 */     return this.current_time;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] get_display_hypnogram()
/*      */   {
/* 1145 */     return this.display_hypnogram;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_display_hypnogram_count()
/*      */   {
/* 1155 */     return this.display_hypnogram_count;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_display_hypnogram_forced_index()
/*      */   {
/* 1166 */     return this.display_hypnogram_forced_index;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_display_hypnogram_forced_stage()
/*      */   {
/* 1177 */     return this.display_hypnogram_forced_stage;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Calendar get_end_of_night()
/*      */   {
/* 1188 */     return this.end_of_night;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Calendar get_factory_reset()
/*      */   {
/* 1199 */     return this.factory_reset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_format_version()
/*      */   {
/* 1209 */     return this.format_version;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long get_headband_id()
/*      */   {
/* 1219 */     return this.headband_id;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public short[] get_headband_impedance()
/*      */   {
/* 1230 */     return this.headband_impedance;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public short[] get_headband_packets()
/*      */   {
/* 1241 */     return this.headband_packets;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] get_headband_rssi()
/*      */   {
/* 1252 */     return this.headband_rssi;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public short[] get_headband_status()
/*      */   {
/* 1263 */     return this.headband_status;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Calendar get_hypnogram_start_time()
/*      */   {
/* 1273 */     return this.hypnogram_start_time;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_id_hw()
/*      */   {
/* 1283 */     return this.id_hw;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_id_sw()
/*      */   {
/* 1293 */     return this.id_sw;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Calendar get_rise_time()
/*      */   {
/* 1303 */     return this.rise_time;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TimeChange[] get_rtc_change()
/*      */   {
/* 1314 */     return this.rtc_change;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Calendar get_sensor_life_reset()
/*      */   {
/* 1325 */     return this.sensor_life_reset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Calendar get_sleep_date()
/*      */   {
/* 1335 */     return this.sleep_date;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_sleep_rating()
/*      */   {
/* 1345 */     return this.sleep_rating;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Calendar get_sleep_stat_reset()
/*      */   {
/* 1356 */     return this.sleep_stat_reset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean get_sleep_valid()
/*      */   {
/* 1366 */     return this.sleep_valid;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_snooze_time()
/*      */   {
/* 1376 */     return this.snooze_time;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Calendar get_start_of_night()
/*      */   {
/* 1387 */     return this.start_of_night;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_time_in_deep()
/*      */   {
/* 1397 */     return this.time_in_deep;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_time_in_deep_average()
/*      */   {
/* 1407 */     return this.time_in_deep_average;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_time_in_deep_best()
/*      */   {
/* 1417 */     return this.time_in_deep_best;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_time_in_light()
/*      */   {
/* 1427 */     return this.time_in_light;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_time_in_light_average()
/*      */   {
/* 1437 */     return this.time_in_light_average;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_time_in_rem()
/*      */   {
/* 1447 */     return this.time_in_rem;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_time_in_rem_average()
/*      */   {
/* 1457 */     return this.time_in_rem_average;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_time_in_rem_best()
/*      */   {
/* 1467 */     return this.time_in_rem_best;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_time_in_wake()
/*      */   {
/* 1477 */     return this.time_in_wake;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_time_in_wake_average()
/*      */   {
/* 1487 */     return this.time_in_wake_average;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_time_to_z()
/*      */   {
/* 1497 */     return this.time_to_z;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_time_to_z_average()
/*      */   {
/* 1507 */     return this.time_to_z_average;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_total_z()
/*      */   {
/* 1517 */     return this.total_z;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_total_z_average()
/*      */   {
/* 1527 */     return this.total_z_average;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_total_z_best()
/*      */   {
/* 1537 */     return this.total_z_best;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public WakeTone get_wake_tone()
/*      */   {
/* 1547 */     return this.wake_tone;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_wake_window()
/*      */   {
/* 1557 */     return this.wake_window;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean get_wdt_reset()
/*      */   {
/* 1567 */     return this.wdt_reset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public WriteReason get_write_reason()
/*      */   {
/* 1577 */     return this.write_reason;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean get_zeo_wake_on()
/*      */   {
/* 1587 */     return this.zeo_wake_on;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_zq_score()
/*      */   {
/* 1597 */     return this.zq_score;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_zq_score_average()
/*      */   {
/* 1607 */     return this.zq_score_average;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int get_zq_score_best()
/*      */   {
/* 1617 */     return this.zq_score_best;
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
/*      */   public boolean is_reset_record()
/*      */   {
/* 1635 */     long change_time = this.rtc_change[0].getTime();
/* 1636 */     long change_value = this.rtc_change[0].getValue();
/* 1637 */     long current_timestamp = this.current_time.getTimeInMillis() / 1000L;
/*      */     
/* 1639 */     if ((this.wdt_reset) && 
/* 1640 */       (this.write_reason == WriteReason.FS_REASON_CARD_INSERT) && 
/* 1641 */       (change_time == 0L) && 
/* 1642 */       (current_timestamp > change_value) && 
/* 1643 */       (current_timestamp - change_value < 10L))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1648 */       return true;
/*      */     }
/* 1650 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean is_sleep_rating_record()
/*      */   {
/* 1661 */     return this.write_reason == WriteReason.FS_REASON_SLEEP_RATED;
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
/*      */   private void make_display_hypnogram_from_base()
/*      */   {
/* 1676 */     SleepStage display_value = SleepStage.UNDEFINED;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1682 */     SleepStage[] stages = new SleepStage[10];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1688 */     int n_display = this.base_hypnogram_count / 10;
/*      */     
/*      */ 
/* 1691 */     for (int i_display = 0; i_display < n_display; i_display++)
/*      */     {
/* 1693 */       int i_base = i_display * 10;
/* 1694 */       for (int i = 0; i < 10; i_base++) {
/* 1695 */         SleepStage stage = SleepStage.convert(this.base_hypnogram[i_base]);
/*      */         
/*      */ 
/* 1698 */         if (stage == SleepStage.DEEP_2) {
/* 1699 */           stage = SleepStage.DEEP;
/*      */         }
/*      */         
/* 1702 */         stages[i] = stage;i++;
/*      */       }
/*      */       
/*      */ 
/* 1706 */       display_value = determine_bin_value(stages, 10);
/* 1707 */       this.display_hypnogram[i_display] = ((byte)display_value.ordinal());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1713 */     if (this.display_hypnogram_forced_index > 0) {
/* 1714 */       this.display_hypnogram[this.display_hypnogram_forced_index] = 
/* 1715 */         ((byte)this.display_hypnogram_forced_stage);
/*      */     }
/*      */     
/*      */ 
/* 1719 */     this.display_hypnogram_count = i_display;
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
/*      */   private byte read_int8(ByteBuffer in)
/*      */   {
/* 1732 */     return in.get();
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
/*      */   private short read_uint8(ByteBuffer in)
/*      */   {
/* 1747 */     short value = (short)(in.get() & 0xFF);
/* 1748 */     return value;
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
/*      */   private int read_uint16(ByteBuffer in)
/*      */   {
/* 1763 */     int value = in.get() & 0xFF;
/* 1764 */     value |= (in.get() & 0xFF) << 8;
/* 1765 */     return value;
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
/*      */   private int read_int32(ByteBuffer in)
/*      */   {
/* 1780 */     int value = in.get() & 0xFF;
/* 1781 */     value |= (in.get() & 0xFF) << 8;
/* 1782 */     value |= (in.get() & 0xFF) << 16;
/* 1783 */     value |= (in.get() & 0xFF) << 24;
/* 1784 */     return value;
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
/*      */   private long read_uint32(ByteBuffer in)
/*      */   {
/* 1799 */     long value = in.get() & 0xFF;
/* 1800 */     value |= (in.get() & 0xFF) << 8;
/* 1801 */     value |= (in.get() & 0xFF) << 16;
/* 1802 */     value |= (in.get() & 0xFF) << 24;
/* 1803 */     return value;
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
/*      */   public boolean same_night(ZeoData other_record)
/*      */   {
/* 1817 */     if ((this.sleep_date != null) && 
/* 1818 */       (this.sleep_date.equals(other_record.get_sleep_date()))) {
/* 1819 */       return true;
/*      */     }
/* 1821 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean same_start_time(ZeoData other_record)
/*      */   {
/* 1831 */     if ((this.start_of_night != null) && 
/* 1832 */       (this.start_of_night.equals(other_record.get_start_of_night()))) {
/* 1833 */       return true;
/*      */     }
/* 1835 */     return false;
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
/*      */   public void set_sleep_date()
/*      */   {
/* 1848 */     if (this.start_of_night == null) {
/* 1849 */       this.sleep_date = null;
/*      */     } else {
/* 1851 */       this.sleep_date = ((Calendar)this.start_of_night.clone());
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1856 */       this.sleep_date.add(11, -6);
/*      */       
/*      */ 
/* 1859 */       this.sleep_date.set(11, 6);
/* 1860 */       this.sleep_date.set(12, 0);
/* 1861 */       this.sleep_date.set(13, 0);
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
/*      */   static Calendar timestamp_to_calendar(long unix_timestamp)
/*      */   {
/* 1876 */     if (unix_timestamp == 0L) {
/* 1877 */       return null;
/*      */     }
/*      */     
/* 1880 */     Calendar calendar = new GregorianCalendar(DEFAULT_TIMEZONE);
/* 1881 */     calendar.setTimeInMillis(unix_timestamp * 1000L);
/* 1882 */     return calendar;
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
/*      */   public String toHuman()
/*      */   {
/* 1896 */     StringWriter txt = new StringWriter();
/* 1897 */     PrintWriter out = new PrintWriter(txt);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1903 */     char[] hypnogram_chars = { '.', 'w', 'r', 'l', 'd', '?', 'L' };
/*      */     
/*      */ 
/* 1906 */     SleepRating sleep_rating_enum = SleepRating.convert(this.sleep_rating);
/*      */     
/* 1908 */     out.printf("record version= %d", new Object[] { Integer.valueOf(this.format_version) });
/* 1909 */     out.println();
/* 1910 */     out.printf("current_time  = %s", new Object[] {
/* 1911 */       calendar_to_human_string(this.current_time) });
/* 1912 */     out.println();
/* 1913 */     out.printf("crc           = %s", new Object[] { Long.valueOf(this.crc) });
/* 1914 */     out.println();
/* 1915 */     out.printf("is_nap        = %s", new Object[] { Boolean.valueOf(this.is_nap) });
/* 1916 */     out.println();
/* 1917 */     out.printf("sleep_date    = %s", new Object[] { calendar_to_human_string(this.sleep_date) });
/* 1918 */     out.println();
/* 1919 */     out.printf("airplane_mode = %s", new Object[] { Boolean.valueOf(this.airplane_mode) });
/* 1920 */     out.println();
/* 1921 */     out.printf("alarm_reason  = %s", new Object[] { this.alarm_reason });
/* 1922 */     out.println();
/* 1923 */     out.printf("backlight     = %s", new Object[] { Byte.valueOf(this.backlight) });
/* 1924 */     out.println();
/* 1925 */     out.printf("clock_mode    = %s", new Object[] { this.clock_mode });
/* 1926 */     out.println();
/* 1927 */     out.printf("sleep_valid   = %s", new Object[] { Boolean.valueOf(this.sleep_valid) });
/* 1928 */     out.println();
/* 1929 */     out.printf("snooze_time   = %s", new Object[] { Integer.valueOf(this.snooze_time) });
/* 1930 */     out.println();
/* 1931 */     out.printf("wake_tone     = %s", new Object[] { Integer.valueOf(this.wake_tone.ordinal()) });
/* 1932 */     out.println();
/* 1933 */     out.printf("wake_window   = %s", new Object[] { Integer.valueOf(this.wake_window) });
/* 1934 */     out.println();
/* 1935 */     out.printf("write_reason  = %s", new Object[] { this.write_reason });
/* 1936 */     out.println();
/* 1937 */     out.printf("zeo_wake_on   = %s", new Object[] { Boolean.valueOf(this.zeo_wake_on) });
/* 1938 */     out.println();
/* 1939 */     out.printf("wdt_reset     = %s", new Object[] { Boolean.valueOf(this.wdt_reset) });
/* 1940 */     out.println();
/*      */     
/* 1942 */     out.printf("airplane_off  = %s", new Object[] {
/* 1943 */       calendar_to_human_string(this.airplane_off) });
/* 1944 */     out.println();
/* 1945 */     out.printf("airplane_on   = %s", new Object[] {
/* 1946 */       calendar_to_human_string(this.airplane_on) });
/* 1947 */     out.println();
/* 1948 */     for (int i = 0; i < 4; i++) {
/* 1949 */       out.printf("alarm_change  = %s", new Object[] { this.alarm_change[i] });
/* 1950 */       out.println();
/*      */     }
/* 1952 */     out.printf("assert_function_name = ", new Object[0]);
/* 1953 */     for (int i = 0; i < 20; i++) {
/* 1954 */       if (this.assert_function_name[i] == 0) {
/*      */         break;
/*      */       }
/* 1957 */       out.printf("%c", new Object[] { Character.valueOf(this.assert_function_name[i]) });
/*      */     }
/* 1959 */     out.println();
/* 1960 */     out.printf("assert_line_number   = %d", new Object[] { Integer.valueOf(this.assert_line_number) });
/* 1961 */     out.println();
/* 1962 */     out.printf("factory_reset = %s", new Object[] {
/* 1963 */       calendar_to_human_string(this.factory_reset) });
/* 1964 */     out.println();
/* 1965 */     out.printf("headband_id   = %s", new Object[] { Long.valueOf(this.headband_id) });
/* 1966 */     out.println();
/* 1967 */     out.printf("headband_impedance = ", new Object[0]);
/* 1968 */     for (int i = 0; i < 144; i++) {
/* 1969 */       out.printf("%3d ", new Object[] { Short.valueOf(this.headband_impedance[i]) });
/*      */     }
/* 1971 */     out.println();
/* 1972 */     out.printf("headband_packets   = ", new Object[0]);
/* 1973 */     for (int i = 0; i < 144; i++) {
/* 1974 */       out.printf("%3d ", new Object[] { Short.valueOf(this.headband_packets[i]) });
/*      */     }
/* 1976 */     out.println();
/* 1977 */     out.printf("headband_rssi      = ", new Object[0]);
/* 1978 */     for (int i = 0; i < 144; i++) {
/* 1979 */       out.printf("%3d ", new Object[] { Byte.valueOf(this.headband_rssi[i]) });
/*      */     }
/* 1981 */     out.println();
/* 1982 */     out.printf("headband_status    = ", new Object[0]);
/* 1983 */     for (int i = 0; i < 36; i++)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1988 */       out.printf("%3d ", new Object[] { Integer.valueOf(this.headband_status[i] >> 0 & 0x3) });
/* 1989 */       out.printf("%3d ", new Object[] { Integer.valueOf(this.headband_status[i] >> 2 & 0x3) });
/* 1990 */       out.printf("%3d ", new Object[] { Integer.valueOf(this.headband_status[i] >> 4 & 0x3) });
/* 1991 */       out.printf("%3d ", new Object[] { Integer.valueOf(this.headband_status[i] >> 6 & 0x3) });
/*      */     }
/* 1993 */     out.println();
/*      */     
/* 1995 */     out.printf("id_hw                 = %s", new Object[] { Integer.valueOf(this.id_hw) });
/* 1996 */     out.println();
/* 1997 */     out.printf("id_sw                 = %s", new Object[] { Integer.valueOf(this.id_sw) });
/* 1998 */     out.println();
/* 1999 */     for (int i = 0; i < 4; i++) {
/* 2000 */       out.printf("rtc_change            = %s", new Object[] { this.rtc_change[i] });
/* 2001 */       out.println();
/*      */     }
/* 2003 */     out.printf("sensor_life_reset     = %s", new Object[] {
/* 2004 */       calendar_to_human_string(this.sensor_life_reset) });
/* 2005 */     out.println();
/* 2006 */     out.printf("sleep_stat_reset      = %s", new Object[] {
/* 2007 */       calendar_to_human_string(this.sleep_stat_reset) });
/* 2008 */     out.println();
/*      */     
/*      */ 
/* 2011 */     for (int i = 0; i < 2; i++) {
/* 2012 */       out.printf("alarm_ring            = %s", new Object[] {
/* 2013 */         calendar_to_human_string(this.alarm_ring[i]) });
/* 2014 */       out.println();
/*      */     }
/* 2016 */     for (int i = 0; i < 9; i++) {
/* 2017 */       out.printf("alarm_snooze          = %s", new Object[] {
/* 2018 */         calendar_to_human_string(this.alarm_snooze[i]) });
/* 2019 */       out.println();
/*      */     }
/* 2021 */     out.printf("alarm_off             = %s", new Object[] {
/* 2022 */       calendar_to_human_string(this.alarm_off) });
/* 2023 */     out.println();
/* 2024 */     out.printf("alarm_set_time        = %s", new Object[] {
/* 2025 */       calendar_to_human_string(this.alarm_set_time) });
/* 2026 */     out.println();
/* 2027 */     out.printf("awakenings            = %s", new Object[] { Integer.valueOf(this.awakenings) });
/* 2028 */     out.println();
/* 2029 */     out.printf("awakenings_average    = %s", new Object[] { Integer.valueOf(this.awakenings_average) });
/* 2030 */     out.println();
/* 2031 */     out.printf("start_of_night        = %s", new Object[] {
/* 2032 */       calendar_to_human_string(this.start_of_night) });
/* 2033 */     out.println();
/* 2034 */     out.printf("end_of_night          = %s", new Object[] {
/* 2035 */       calendar_to_human_string(this.end_of_night) });
/* 2036 */     out.println();
/* 2037 */     out.printf("rise_time             = %s", new Object[] {
/* 2038 */       calendar_to_human_string(this.rise_time) });
/* 2039 */     out.println();
/* 2040 */     out.printf("sleep_rating          = %s (%s)", new Object[] {
/* 2041 */       Integer.valueOf(this.sleep_rating), sleep_rating_enum });
/* 2042 */     out.println();
/* 2043 */     out.printf("time_in_deep          = %s", new Object[] {
/* 2044 */       epochs_to_human_string(this.time_in_deep) });
/* 2045 */     out.println();
/* 2046 */     out.printf("time_in_deep_average  = %s", new Object[] {
/* 2047 */       epochs_to_human_string(this.time_in_deep_average) });
/* 2048 */     out.println();
/* 2049 */     out.printf("time_in_deep_best     = %s", new Object[] {
/* 2050 */       epochs_to_human_string(this.time_in_deep_best) });
/* 2051 */     out.println();
/* 2052 */     out.printf("time_in_light         = %s", new Object[] {
/* 2053 */       epochs_to_human_string(this.time_in_light) });
/* 2054 */     out.println();
/* 2055 */     out.printf("time_in_light_average = %s", new Object[] {
/* 2056 */       epochs_to_human_string(this.time_in_light_average) });
/* 2057 */     out.println();
/* 2058 */     out.printf("time_in_rem           = %s", new Object[] {
/* 2059 */       epochs_to_human_string(this.time_in_rem) });
/* 2060 */     out.println();
/* 2061 */     out.printf("time_in_rem_average   = %s", new Object[] {
/* 2062 */       epochs_to_human_string(this.time_in_rem_average) });
/* 2063 */     out.println();
/* 2064 */     out.printf("time_in_rem_best      = %s", new Object[] {
/* 2065 */       epochs_to_human_string(this.time_in_rem_best) });
/* 2066 */     out.println();
/* 2067 */     out.printf("time_in_wake          = %s", new Object[] {
/* 2068 */       epochs_to_human_string(this.time_in_wake) });
/* 2069 */     out.println();
/* 2070 */     out.printf("time_in_wake_average  = %s", new Object[] {
/* 2071 */       epochs_to_human_string(this.time_in_wake_average) });
/* 2072 */     out.println();
/* 2073 */     out.printf("time_to_z             = %s", new Object[] {
/* 2074 */       epochs_to_human_string(this.time_to_z) });
/* 2075 */     out.println();
/* 2076 */     out.printf("time_to_z_average     = %s", new Object[] {
/* 2077 */       epochs_to_human_string(this.time_to_z_average) });
/* 2078 */     out.println();
/* 2079 */     out.printf("total_z               = %s", new Object[] {
/* 2080 */       epochs_to_human_string(this.total_z) });
/* 2081 */     out.println();
/* 2082 */     out.printf("total_z_average       = %s", new Object[] {
/* 2083 */       epochs_to_human_string(this.total_z_average) });
/* 2084 */     out.println();
/* 2085 */     out.printf("total_z_best          = %s", new Object[] {
/* 2086 */       epochs_to_human_string(this.total_z_best) });
/* 2087 */     out.println();
/* 2088 */     out.printf("zq_score              = %s", new Object[] { Integer.valueOf(this.zq_score) });
/* 2089 */     out.println();
/* 2090 */     out.printf("zq_score_average      = %s", new Object[] { Integer.valueOf(this.zq_score_average) });
/* 2091 */     out.println();
/* 2092 */     out.printf("zq_score_best         = %s", new Object[] { Integer.valueOf(this.zq_score_best) });
/* 2093 */     out.println();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2098 */     out.printf("display_hypnogram_forced_index = %s", new Object[] {
/* 2099 */       Integer.valueOf(this.display_hypnogram_forced_index) });
/* 2100 */     out.println();
/* 2101 */     out.printf("display_hypnogram_forced_stage = %s", new Object[] {
/* 2102 */       Integer.valueOf(this.display_hypnogram_forced_stage) });
/* 2103 */     out.println();
/*      */     
/*      */ 
/* 2106 */     out.printf("hypnogram_start_time  = %s", new Object[] {
/* 2107 */       calendar_to_human_string(this.hypnogram_start_time) });
/* 2108 */     out.println();
/*      */     
/* 2110 */     out.printf("base_hypnogram_count  = %s", new Object[] { Integer.valueOf(this.base_hypnogram_count) });
/* 2111 */     out.println();
/* 2112 */     out.write("base_hypnogram = ");
/* 2113 */     for (int i = 0; i < this.base_hypnogram_count; i++) {
/* 2114 */       out.printf("%c", new Object[] { Character.valueOf(hypnogram_chars[this.base_hypnogram[i]]) });
/*      */     }
/* 2116 */     out.println();
/*      */     
/* 2118 */     out.printf("display_hypnogram_count = %s", new Object[] { Integer.valueOf(this.display_hypnogram_count) });
/* 2119 */     out.println();
/* 2120 */     out.write("display hypnogram = ");
/* 2121 */     for (int i = 0; i < this.display_hypnogram_count; i++) {
/* 2122 */       out.printf("%c", new Object[] { Character.valueOf(hypnogram_chars[this.display_hypnogram[i]]) });
/*      */     }
/* 2124 */     out.println();
/*      */     
/*      */ 
/* 2127 */     out.println();
/* 2128 */     out.flush();
/*      */     
/* 2130 */     return txt.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toXML()
/*      */   {
/* 2140 */     StringWriter txt = new StringWriter();
/* 2141 */     PrintWriter out = new PrintWriter(txt);
/*      */     
/* 2143 */     out.printf("<sleep_record version=\"%d\">", new Object[] { Integer.valueOf(this.format_version) });
/* 2144 */     out.println();
/* 2145 */     out.println("<current_time>");
/* 2146 */     out.write(calendar_to_xml(this.current_time));
/* 2147 */     out.println("</current_time>");
/* 2148 */     out.printf("<crc>%s</crc>", new Object[] { Long.valueOf(this.crc) });
/* 2149 */     out.println();
/* 2150 */     out.printf("<is_nap>%s</is_nap>", new Object[] { Integer.valueOf(this.is_nap ? 1 : 0) });
/* 2151 */     out.println();
/* 2152 */     out.println("<sleep_date>");
/* 2153 */     out.write(calendar_to_xml(this.sleep_date));
/* 2154 */     out.println("</sleep_date>");
/*      */     
/*      */ 
/* 2157 */     out.println("<device_history>");
/*      */     
/* 2159 */     out.printf("<airplane_mode>%s</airplane_mode>", new Object[] {
/* 2160 */       Integer.valueOf(this.airplane_mode ? 1 : 0) });
/* 2161 */     out.println();
/* 2162 */     out.printf("<alarm_reason>%s</alarm_reason>", new Object[] { Integer.valueOf(this.alarm_reason.ordinal()) });
/* 2163 */     out.println();
/* 2164 */     out.printf("<backlight>%s</backlight>", new Object[] { Byte.valueOf(this.backlight) });
/* 2165 */     out.println();
/* 2166 */     out.printf("<clock_mode>%s</clock_mode>", new Object[] { Integer.valueOf(this.clock_mode.ordinal()) });
/* 2167 */     out.println();
/* 2168 */     out.printf("<sleep_valid>%s</sleep_valid>", new Object[] { Boolean.valueOf(this.sleep_valid) });
/* 2169 */     out.println();
/* 2170 */     out.printf("<snooze_time>%s</snooze_time>", new Object[] { Integer.valueOf(this.snooze_time) });
/* 2171 */     out.println();
/* 2172 */     out.printf("<wake_tone>%s</wake_tone>", new Object[] { Integer.valueOf(this.wake_tone.ordinal()) });
/* 2173 */     out.println();
/* 2174 */     out.printf("<wake_window>%s</wake_window>", new Object[] { Integer.valueOf(this.wake_window) });
/* 2175 */     out.println();
/* 2176 */     out.printf("<write_reason>%s</write_reason>", new Object[] { Integer.valueOf(this.write_reason.ordinal()) });
/* 2177 */     out.println();
/* 2178 */     out.printf("<zeo_wake_on>%s</zeo_wake_on>", new Object[] { Integer.valueOf(this.zeo_wake_on ? 1 : 0) });
/* 2179 */     out.println();
/* 2180 */     out.printf("<wdt_reset>%s</wdt_reset>", new Object[] { Integer.valueOf(this.wdt_reset ? 1 : 0) });
/* 2181 */     out.println();
/*      */     
/* 2183 */     out.println("<airplane_off>");
/* 2184 */     out.write(calendar_to_xml(this.airplane_off));
/* 2185 */     out.println("</airplane_off>");
/* 2186 */     out.println("<airplane_on>");
/* 2187 */     out.write(calendar_to_xml(this.airplane_on));
/* 2188 */     out.println("</airplane_on>");
/* 2189 */     out.println("<alarm_change>");
/* 2190 */     for (int i = 0; i < 4; i++) {
/* 2191 */       out.write(this.alarm_change[i].toXML());
/*      */     }
/* 2193 */     out.println("</alarm_change>");
/* 2194 */     out.println("<assert_function_name>");
/* 2195 */     for (int i = 0; i < 20; i++) {
/* 2196 */       if (this.assert_function_name[i] == 0) {
/*      */         break;
/*      */       }
/* 2199 */       out.printf("%c", new Object[] { Character.valueOf(this.assert_function_name[i]) });
/*      */     }
/* 2201 */     out.println("</assert_function_name>");
/* 2202 */     out.printf("<assert_line_number>%d</assert_line_number>", new Object[] {
/* 2203 */       Integer.valueOf(this.assert_line_number) });
/* 2204 */     out.println();
/* 2205 */     out.println("<factory_reset>");
/* 2206 */     out.write(calendar_to_xml(this.factory_reset));
/* 2207 */     out.println("</factory_reset>");
/* 2208 */     out.printf("<headband_id>%d</headband_id>", new Object[] { Long.valueOf(this.headband_id) });
/* 2209 */     out.println();
/* 2210 */     out.println("<headband_impedance>");
/* 2211 */     for (int i = 0; i < 144; i++) {
/* 2212 */       out.printf("%d ", new Object[] { Short.valueOf(this.headband_impedance[i]) });
/*      */     }
/* 2214 */     out.println();
/* 2215 */     out.println("</headband_impedance>");
/* 2216 */     out.println("<headband_packets>");
/* 2217 */     for (int i = 0; i < 144; i++) {
/* 2218 */       out.printf("%d ", new Object[] { Short.valueOf(this.headband_packets[i]) });
/*      */     }
/* 2220 */     out.println();
/* 2221 */     out.println("</headband_packets>");
/* 2222 */     out.println("<headband_rssi>");
/* 2223 */     for (int i = 0; i < 144; i++) {
/* 2224 */       out.printf("%d ", new Object[] { Byte.valueOf(this.headband_rssi[i]) });
/*      */     }
/* 2226 */     out.println();
/* 2227 */     out.println("</headband_rssi>");
/* 2228 */     out.println("<headband_status>");
/* 2229 */     for (int i = 0; i < 36; i++)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2234 */       out.printf("%d ", new Object[] { Integer.valueOf(this.headband_status[i] >> 0 & 0x3) });
/* 2235 */       out.printf("%d ", new Object[] { Integer.valueOf(this.headband_status[i] >> 2 & 0x3) });
/* 2236 */       out.printf("%d ", new Object[] { Integer.valueOf(this.headband_status[i] >> 4 & 0x3) });
/* 2237 */       out.printf("%d ", new Object[] { Integer.valueOf(this.headband_status[i] >> 6 & 0x3) });
/*      */     }
/* 2239 */     out.println();
/* 2240 */     out.println("</headband_status>");
/* 2241 */     out.printf("<id_hw>%s</id_hw>", new Object[] { Integer.valueOf(this.id_hw) });
/* 2242 */     out.println();
/* 2243 */     out.printf("<id_sw>%s</id_sw>", new Object[] { Integer.valueOf(this.id_sw) });
/* 2244 */     out.println();
/* 2245 */     out.println("<rtc_change>");
/* 2246 */     for (int i = 0; i < 4; i++) {
/* 2247 */       out.write(this.rtc_change[i].toXML());
/*      */     }
/* 2249 */     out.println("</rtc_change>");
/* 2250 */     out.println("<sensor_life_reset>");
/* 2251 */     out.write(calendar_to_xml(this.sensor_life_reset));
/* 2252 */     out.println("</sensor_life_reset>");
/* 2253 */     out.println("<sleep_stat_reset>");
/* 2254 */     out.write(calendar_to_xml(this.sleep_stat_reset));
/* 2255 */     out.println("</sleep_stat_reset>");
/*      */     
/* 2257 */     out.println("</device_history>");
/*      */     
/*      */ 
/* 2260 */     out.println("<sleep_information>");
/* 2261 */     out.println("<alarm_ring>");
/* 2262 */     for (int i = 0; i < 2; i++) {
/* 2263 */       out.println("<ring>");
/* 2264 */       out.write(calendar_to_xml(this.alarm_ring[i]));
/* 2265 */       out.println("</ring>");
/*      */     }
/* 2267 */     out.println("</alarm_ring>");
/* 2268 */     out.println("<alarm_snooze>");
/* 2269 */     for (int i = 0; i < 9; i++) {
/* 2270 */       out.println("<snooze>");
/* 2271 */       out.write(calendar_to_xml(this.alarm_snooze[i]));
/* 2272 */       out.println("</snooze>");
/*      */     }
/* 2274 */     out.println("</alarm_snooze>");
/* 2275 */     out.println("<alarm_off>");
/* 2276 */     out.write(calendar_to_xml(this.alarm_off));
/* 2277 */     out.println("</alarm_off>");
/* 2278 */     out.println("<alarm_set_time>");
/* 2279 */     out.write(calendar_to_xml(this.alarm_set_time));
/* 2280 */     out.println("</alarm_set_time>");
/* 2281 */     out.printf("<awakenings>%s</awakenings>", new Object[] { Integer.valueOf(this.awakenings) });
/* 2282 */     out.println();
/* 2283 */     out.printf("<awakenings_average>%s</awakenings_average>", new Object[] {
/* 2284 */       Integer.valueOf(this.awakenings_average) });
/* 2285 */     out.println();
/* 2286 */     out.println("<end_of_night>");
/* 2287 */     out.write(calendar_to_xml(this.end_of_night));
/* 2288 */     out.println("</end_of_night>");
/* 2289 */     out.println("<rise_time>");
/* 2290 */     out.write(calendar_to_xml(this.rise_time));
/* 2291 */     out.println("</rise_time>");
/* 2292 */     out.println("<start_of_night>");
/* 2293 */     out.write(calendar_to_xml(this.start_of_night));
/* 2294 */     out.println("</start_of_night>");
/* 2295 */     out.printf("<sleep_rating>%s</sleep_rating>", new Object[] { Integer.valueOf(this.sleep_rating) });
/* 2296 */     out.println();
/* 2297 */     out.printf("<time_in_deep>%s</time_in_deep>", new Object[] { Integer.valueOf(this.time_in_deep) });
/* 2298 */     out.println();
/* 2299 */     out.printf("<time_in_deep_average>%s</time_in_deep_average>", new Object[] {
/* 2300 */       Integer.valueOf(this.time_in_deep_average) });
/* 2301 */     out.println();
/* 2302 */     out.printf("<time_in_deep_best>%s</time_in_deep_best>", new Object[] {
/* 2303 */       Integer.valueOf(this.time_in_deep_best) });
/* 2304 */     out.println();
/* 2305 */     out.printf("<time_in_light>%s</time_in_light>", new Object[] { Integer.valueOf(this.time_in_light) });
/* 2306 */     out.println();
/* 2307 */     out.printf("<time_in_light_average>%s</time_in_light_average>", new Object[] {
/* 2308 */       Integer.valueOf(this.time_in_light_average) });
/* 2309 */     out.println();
/* 2310 */     out.printf("<time_in_rem>%s</time_in_rem>", new Object[] { Integer.valueOf(this.time_in_rem) });
/* 2311 */     out.println();
/* 2312 */     out.printf("<time_in_rem_average>%s</time_in_rem_average>", new Object[] {
/* 2313 */       Integer.valueOf(this.time_in_rem_average) });
/* 2314 */     out.println();
/* 2315 */     out.printf("<time_in_rem_best>%s</time_in_rem_best>", new Object[] {
/* 2316 */       Integer.valueOf(this.time_in_rem_best) });
/* 2317 */     out.println();
/* 2318 */     out.printf("<time_in_wake>%s</time_in_wake>", new Object[] { Integer.valueOf(this.time_in_wake) });
/* 2319 */     out.println();
/* 2320 */     out.printf("<time_in_wake_average>%s</time_in_wake_average>", new Object[] {
/* 2321 */       Integer.valueOf(this.time_in_wake_average) });
/* 2322 */     out.println();
/* 2323 */     out.printf("<time_to_z>%s</time_to_z>", new Object[] { Integer.valueOf(this.time_to_z) });
/* 2324 */     out.println();
/* 2325 */     out.printf("<time_to_z_average>%s</time_to_z_average>", new Object[] {
/* 2326 */       Integer.valueOf(this.time_to_z_average) });
/* 2327 */     out.println();
/* 2328 */     out.printf("<total_z>%s</total_z>", new Object[] { Integer.valueOf(this.total_z) });
/* 2329 */     out.println();
/* 2330 */     out.printf("<total_z_average>%s</total_z_average>", new Object[] { Integer.valueOf(this.total_z_average) });
/* 2331 */     out.println();
/* 2332 */     out.printf("<total_z_best>%s</total_z_best>", new Object[] { Integer.valueOf(this.total_z_best) });
/* 2333 */     out.println();
/* 2334 */     out.printf("<zq_score>%s</zq_score>", new Object[] { Integer.valueOf(this.zq_score) });
/* 2335 */     out.println();
/* 2336 */     out.printf("<zq_score_average>%s</zq_score_average>", new Object[] {
/* 2337 */       Integer.valueOf(this.zq_score_average) });
/* 2338 */     out.println();
/* 2339 */     out.printf("<zq_score_best>%s</zq_score_best>", new Object[] { Integer.valueOf(this.zq_score_best) });
/* 2340 */     out.println();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2345 */     out.printf("<display_hypnogram_forced_index>%s</display_hypnogram_forced_index>", new Object[] {
/*      */     
/*      */ 
/* 2348 */       Integer.valueOf(this.display_hypnogram_forced_index) });
/* 2349 */     out.println();
/* 2350 */     out.printf("<display_hypnogram_forced_stage>%s</display_hypnogram_forced_stage>", new Object[] {
/*      */     
/*      */ 
/* 2353 */       Integer.valueOf(this.display_hypnogram_forced_stage) });
/* 2354 */     out.println();
/*      */     
/*      */ 
/* 2357 */     out.println("<hypnogram_start_time>");
/* 2358 */     out.write(calendar_to_xml(this.hypnogram_start_time));
/* 2359 */     out.println("</hypnogram_start_time>");
/*      */     
/* 2361 */     out.printf("<display_hypnogram_count>%s</display_hypnogram_count>", new Object[] {
/* 2362 */       Integer.valueOf(this.display_hypnogram_count) });
/* 2363 */     out.println();
/* 2364 */     out.println("<display_hypnogram>");
/* 2365 */     for (int i = 0; i < this.display_hypnogram_count; i++) {
/* 2366 */       out.printf("%d ", new Object[] { Byte.valueOf(this.display_hypnogram[i]) });
/*      */     }
/* 2368 */     out.println();
/* 2369 */     out.println("</display_hypnogram>");
/* 2370 */     out.printf("<base_hypnogram_count>%s</base_hypnogram_count>", new Object[] {
/* 2371 */       Integer.valueOf(this.base_hypnogram_count) });
/* 2372 */     out.println();
/* 2373 */     out.println("<base_hypnogram>");
/* 2374 */     for (int i = 0; i < this.base_hypnogram_count; i++) {
/* 2375 */       out.printf("%d ", new Object[] { Byte.valueOf(this.base_hypnogram[i]) });
/*      */     }
/* 2377 */     out.println();
/* 2378 */     out.println("</base_hypnogram>");
/*      */     
/* 2380 */     out.println("</sleep_information>");
/* 2381 */     out.println("</sleep_record>");
/* 2382 */     out.flush();
/*      */     
/* 2384 */     return txt.toString();
/*      */   }
/*      */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\zeoDecoder\ZeoData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */