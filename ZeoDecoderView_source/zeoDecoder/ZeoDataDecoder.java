/*     */ package zeoDecoder;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Vector;
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
/*     */ public class ZeoDataDecoder
/*     */ {
/*  63 */   static final int[] CRC16_TABLE = {
/*  64 */     0, 4129, 8258, 12387, 16516, 20645, 24774, 28903, 
/*  65 */     33032, 37161, 41290, 45419, 49548, 53677, 57806, 61935, 
/*  66 */     4657, 528, 12915, 8786, 21173, 17044, 29431, 25302, 
/*  67 */     37689, 33560, 45947, 41818, 54205, 50076, 62463, 58334, 
/*  68 */     9314, 13379, 1056, 5121, 25830, 29895, 17572, 21637, 
/*  69 */     42346, 46411, 34088, 38153, 58862, 62927, 50604, 54669, 
/*  70 */     13907, 9842, 5649, 1584, 30423, 26358, 22165, 18100, 
/*  71 */     46939, 42874, 38681, 34616, 63455, 59390, 55197, 51132, 
/*  72 */     18628, 22757, 26758, 30887, 2112, 6241, 10242, 14371, 
/*  73 */     51660, 55789, 59790, 63919, 35144, 39273, 43274, 47403, 
/*  74 */     23285, 19156, 31415, 27286, 6769, 2640, 14899, 10770, 
/*  75 */     56317, 52188, 64447, 60318, 39801, 35672, 47931, 43802, 
/*  76 */     27814, 31879, 19684, 23749, 11298, 15363, 3168, 7233, 
/*  77 */     60846, 64911, 52716, 56781, 44330, 48395, 36200, 40265, 
/*  78 */     32407, 28342, 24277, 20212, 15891, 11826, 7761, 3696, 
/*  79 */     65439, 61374, 57309, 53244, 48923, 44858, 40793, 36728, 
/*  80 */     37256, 33193, 45514, 41451, 53516, 49453, 61774, 57711, 
/*  81 */     4224, 161, 12482, 8419, 20484, 16421, 28742, 24679, 
/*  82 */     33721, 37784, 41979, 46042, 49981, 54044, 58239, 62302, 
/*  83 */     689, 4752, 8947, 13010, 16949, 21012, 25207, 29270, 
/*  84 */     46570, 42443, 38312, 34185, 62830, 58703, 54572, 50445, 
/*  85 */     13538, 9411, 5280, 1153, 29798, 25671, 21540, 17413, 
/*  86 */     42971, 47098, 34713, 38840, 59231, 63358, 50973, 55100, 
/*  87 */     9939, 14066, 1681, 5808, 26199, 30326, 17941, 22068, 
/*  88 */     55628, 51565, 63758, 59695, 39368, 35305, 47498, 43435, 
/*  89 */     22596, 18533, 30726, 26663, 6336, 2273, 14466, 10403, 
/*  90 */     52093, 56156, 60223, 64286, 35833, 39896, 43963, 48026, 
/*  91 */     19061, 23124, 27191, 31254, 2801, 6864, 10931, 14994, 
/*  92 */     64814, 60687, 56684, 52557, 48554, 44427, 40424, 36297, 
/*  93 */     31782, 27655, 23652, 19525, 15522, 11395, 7392, 3265, 
/*  94 */     61215, 65342, 53085, 57212, 44955, 49082, 36825, 40952, 
/*  95 */     28183, 32310, 20053, 24180, 11923, 16050, 3793, 7920 };
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
/*     */   static final String DOCUMENTATION = "Decode an Zeo save data file (zeosleep.dat).\n\nThis program decodes sleep records in a zeosleep.dat file into\neither a human readable format or XML depending upon how it is\ninvoked. If the data is written to standard output then it is\noutput as human readable text. If a file is specified and that\nfile's extension is .xml then the data is output as XML. Any other\nfile extension specified will cause the program to output the human\nreadable version to the specified file.\n\nTo output data to standard output invoke this command with just\nthe zeosleep.dat input file name. To output data to an XML file\ninvoke this command with two arguments; the zeosleep.dat file and\nthe intended XML file zeosleep.xml (please include .xml extension).\n\n";
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
/*     */   static final String OPTIONS = "Options:\n-e or --expand  : Don't remove related records for each night.\n-r or --resets  : Output just watchdog reset records.\n-V or --version : Display decoder version and exit";
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
/*     */   static final String USAGE = "Usage:\njava -jar OpenDecoder.jar [options] directory/zeosleep.dat\nor\njava -jar OpenDecoder.jar [options] directory/zeosleep.dat\n                                    directory/zeosleep.xml\nor\njava -jar OpenDecoder.jar [options] directory/zeosleep.dat\n                                    directory/zeosleep.txt\n";
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
/*     */   public static final int ZEO_DATA_DECODER_VERSION = 10;
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
/* 156 */   private Vector<ZeoData> records = new Vector(10, 10);
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
/*     */   public ZeoDataDecoder(ByteBuffer in)
/*     */     throws EOFException
/*     */   {
/* 172 */     byte[] blank = new byte[6];
/* 173 */     byte[] identifier = new byte[6];
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 178 */     byte[] version_str = new byte[2];
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 183 */     assert (in.arrayOffset() == 0);
/*     */     
/*     */ 
/* 186 */     in.order(ByteOrder.LITTLE_ENDIAN);
/* 187 */     in.rewind();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 193 */     if (find_next_record(in) == -1) {
/* 194 */       System.err.println("ERROR: File may be encrypted.Please update to firmware version 2.6.3O to disable SD card encryption.");
/*     */     }
/*     */     
/* 197 */     in.rewind();
/*     */     
/*     */ 
/* 200 */     int record_count = 0;
/* 201 */     while (in.hasRemaining())
/*     */     {
/* 203 */       in.mark();
/* 204 */       int record_offset = in.position();
/*     */       
/* 206 */       int record_size = 0;
/* 207 */       int version = 0;
/*     */       
/*     */ 
/* 210 */       if (in.remaining() < 8) {
/* 211 */         System.err.println(
/* 212 */           "WARNING: File ended with incomplete record.");
/* 213 */         break;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 219 */       in.get(identifier);
/* 220 */       in.get(version_str);
/* 221 */       if (!Arrays.equals(identifier, ZeoData.IDENTIFIER)) {
/* 222 */         System.err.println("WARNING: Invalid record identifier skipped after record " + 
/* 223 */           record_count + ".");
/*     */ 
/*     */       }
/* 226 */       else if (Arrays.equals(version_str, ZeoData.V22)) {
/* 227 */         record_size = 1680;
/* 228 */         version = 22;
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 233 */         System.err.println(
/* 234 */           "WARNING: Unable to handle Zeo record version " + (
/* 235 */           version_str[1] * 256 + version_str[0]) + 
/* 236 */           " after record " + record_count + ".");
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 241 */       if (in.remaining() < record_size - 8) {
/* 242 */         System.err.println(
/* 243 */           "WARNING: File ended with incomplete record.");
/* 244 */         break;
/*     */       }
/*     */       
/*     */ 
/* 248 */       if (version != 0)
/*     */       {
/*     */ 
/*     */ 
/*     */         try
/*     */         {
/*     */ 
/*     */ 
/* 256 */           ByteBuffer bstream = 
/* 257 */             ByteBuffer.wrap(in.array(), 
/* 258 */             record_offset + 8, 
/* 259 */             record_size - 8);
/*     */           
/*     */ 
/* 262 */           ZeoData record = new ZeoData(bstream, version);
/*     */           
/*     */ 
/* 265 */           if (version >= 20) {
/* 266 */             int crc = crc16(in.array(), record_offset, record_size);
/*     */             
/* 268 */             if (crc != record.get_crc()) {
/* 269 */               System.err.println(
/* 270 */                 "WARNING: Skipping the record after record " + 
/* 271 */                 record_count + " due to bad CRC.");
/* 272 */               version = 0;
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 277 */           if (version != 0) {
/* 278 */             this.records.addElement(record);
/*     */             
/*     */ 
/* 281 */             in.position(record_offset + record_size);
/*     */           }
/*     */           
/*     */ 
/*     */         }
/*     */         catch (Throwable e)
/*     */         {
/* 288 */           System.err.println(
/* 289 */             "WARNING: Exception parsing the record after record " + 
/* 290 */             record_count + ": " + e.toString());
/* 291 */           version = 0;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 297 */       if (version == 0)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 302 */         in.reset();
/* 303 */         in.get();
/*     */         
/* 305 */         if (find_next_record(in) == -1) {
/* 306 */           System.err.println(
/* 307 */             "WARNING: Valid records stopped before file ended.");
/* 308 */           break;
/*     */         }
/*     */       }
/*     */       else {
/* 312 */         record_count++;
/*     */       }
/*     */       
/*     */ 
/* 316 */       System.arraycopy(blank, 0, identifier, 0, 6);
/* 317 */       System.arraycopy(blank, 0, version_str, 0, 2);
/*     */     }
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
/*     */ 
/*     */ 
/*     */   public static int crc16(byte[] buffer, int offset, int length)
/*     */   {
/* 333 */     int crc = 0;
/*     */     
/*     */ 
/*     */ 
/* 337 */     for (int i = 0; i < length; offset++) {
/* 338 */       int value = buffer[offset] & 0xFF;
/* 339 */       int t = crc >> 8 ^ value;
/* 340 */       crc = crc << 8 & 0xFFFF ^ CRC16_TABLE[t];i++;
/*     */     }
/*     */     
/*     */ 
/* 342 */     return crc;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static List<ZeoData> decode(byte[] data)
/*     */     throws IOException
/*     */   {
/* 355 */     ByteBuffer in = ByteBuffer.wrap(data);
/*     */     
/* 357 */     ZeoDataDecoder decoder = new ZeoDataDecoder(in);
/*     */     
/* 359 */     decoder.reduce_records();
/*     */     
/* 361 */     decoder.label_naps();
/*     */     
/* 363 */     return decoder.get_records();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int find_next_record(ByteBuffer in)
/*     */   {
/* 382 */     byte[] identifier = new byte[6];
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 388 */     int bytes_to_search = in.remaining() - identifier.length;
/* 389 */     if (bytes_to_search > 2000) {
/* 390 */       bytes_to_search = 2000;
/*     */     }
/*     */     
/* 393 */     while (bytes_to_search > 0) {
/* 394 */       in.mark();
/* 395 */       in.get(identifier);
/* 396 */       if (Arrays.equals(identifier, ZeoData.IDENTIFIER))
/*     */       {
/*     */ 
/*     */ 
/* 400 */         in.reset();
/* 401 */         return in.position();
/*     */       }
/*     */       
/* 404 */       in.reset();
/*     */       
/* 406 */       in.get();
/* 407 */       bytes_to_search--;
/*     */     }
/*     */     
/* 410 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<ZeoData> get_records()
/*     */   {
/* 419 */     return Collections.unmodifiableList(this.records);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void keep_resets()
/*     */   {
/* 429 */     Iterator<ZeoData> iterator = this.records.iterator();
/*     */     
/*     */ 
/* 432 */     while (iterator.hasNext()) {
/* 433 */       ZeoData record = (ZeoData)iterator.next();
/*     */       
/*     */ 
/* 436 */       if (!record.is_reset_record()) {
/* 437 */         iterator.remove();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void label_naps()
/*     */   {
/* 451 */     Iterator<ZeoData> iterator = this.records.iterator();
/* 452 */     ZeoData largest_record = null;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 457 */     if (iterator.hasNext()) {
/* 458 */       largest_record = (ZeoData)iterator.next();
/* 459 */       largest_record.set_sleep_date();
/*     */     }
/*     */     
/*     */ 
/* 463 */     while (iterator.hasNext()) {
/* 464 */       ZeoData record = (ZeoData)iterator.next();
/*     */       
/*     */ 
/* 467 */       record.set_sleep_date();
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 472 */       if (largest_record.same_night(record))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 478 */         if (largest_record.compareLength(record) == 1)
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 484 */           record.is_nap = true;
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/* 489 */           largest_record.is_nap = true;
/* 490 */           largest_record = record;
/*     */ 
/*     */         }
/*     */         
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/* 499 */         largest_record.is_nap = false;
/* 500 */         largest_record = record;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 507 */     if (largest_record != null) {
/* 508 */       largest_record.is_nap = false;
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reduce_records()
/*     */   {
/* 530 */     ZeoData largest_record = null;
/*     */     
/* 532 */     Vector<ZeoData> reduced_records = new Vector(10, 10);
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
/* 544 */     Iterator<ZeoData> iterator = this.records.iterator();
/* 545 */     while (iterator.hasNext()) {
/* 546 */       ZeoData record = (ZeoData)iterator.next();
/*     */       
/* 548 */       if ((record.get_start_of_night() != null) && 
/* 549 */         (record.get_end_of_night() != null))
/*     */       {
/* 551 */         if (largest_record == null)
/*     */         {
/* 553 */           largest_record = record;
/* 554 */         } else if (!largest_record.same_start_time(record))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 560 */           reduced_records.addElement(largest_record);
/* 561 */           largest_record = record;
/* 562 */         } else if (largest_record.compareLength(record) == -1)
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 568 */           largest_record = record;
/* 569 */         } else if ((largest_record.compareLength(record) == 0) && 
/* 570 */           (record.is_sleep_rating_record()))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 578 */           largest_record = record;
/*     */         }
/*     */       }
/*     */     }
/* 582 */     if (largest_record != null) {
/* 583 */       reduced_records.addElement(largest_record);
/* 584 */       largest_record = null;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 595 */     Collections.sort(reduced_records);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 600 */     this.records.clear();
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
/* 613 */     Iterator<ZeoData> iterator2 = reduced_records.iterator();
/* 614 */     while (iterator2.hasNext()) {
/* 615 */       ZeoData record = (ZeoData)iterator2.next();
/*     */       
/* 617 */       if (largest_record == null)
/*     */       {
/* 619 */         largest_record = record;
/* 620 */       } else if (!largest_record.same_start_time(record))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 626 */         this.records.addElement(largest_record);
/* 627 */         largest_record = record;
/* 628 */       } else if (largest_record.compareLength(record) == -1)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 634 */         largest_record = record;
/* 635 */       } else if ((largest_record.compareLength(record) == 0) && 
/* 636 */         (record.is_sleep_rating_record()))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 644 */         largest_record = record;
/*     */       }
/*     */     }
/*     */     
/* 648 */     if (largest_record != null) {
/* 649 */       this.records.addElement(largest_record);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 661 */     return this.records.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toHuman()
/*     */   {
/* 671 */     StringWriter txt = new StringWriter();
/* 672 */     PrintWriter out = new PrintWriter(txt);
/* 673 */     Iterator<ZeoData> iterator = this.records.iterator();
/*     */     
/* 675 */     while (iterator.hasNext()) {
/* 676 */       ZeoData record = (ZeoData)iterator.next();
/* 677 */       out.println(record.toHuman());
/*     */     }
/* 679 */     out.flush();
/* 680 */     return txt.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toXML()
/*     */   {
/* 690 */     StringWriter txt = new StringWriter();
/* 691 */     PrintWriter out = new PrintWriter(txt);
/* 692 */     Iterator<ZeoData> iterator = this.records.iterator();
/*     */     
/* 694 */     out.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
/* 695 */     out.println("<sleep_records>");
/*     */     
/* 697 */     while (iterator.hasNext()) {
/* 698 */       ZeoData record = (ZeoData)iterator.next();
/* 699 */       out.println(record.toXML());
/*     */     }
/* 701 */     out.println("</sleep_records>");
/* 702 */     out.flush();
/* 703 */     return txt.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 711 */     ArrayList<String> arguments = new ArrayList();
/* 712 */     FileChannel ichannel = null;
/* 713 */     boolean reduce = true;
/*     */     
/*     */ 
/*     */ 
/* 717 */     String[] arrayOfString = args;int j = args.length; for (int i = 0; i < j; i++) { String arg = arrayOfString[i];
/*     */       
/*     */ 
/* 720 */       if ((arg.equals("-e")) || (arg.equals("--expand")))
/*     */       {
/*     */ 
/*     */ 
/* 724 */         reduce = false;
/* 725 */       } else if ((arg.equals("-V")) || (arg.equals("--version"))) {
/* 726 */         System.out.println("ZeoDataDecoder version 10.\nCopyright 2010 by Zeo Inc. All Rights Reserved.");
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 731 */         System.exit(0);
/*     */       }
/* 733 */       else if (!arguments.add(arg)) {
/* 734 */         System.err.println("WARNING: could not add argument " + arg + 
/* 735 */           "to arguments ArrayList");
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 740 */     if ((arguments.size() < 1) || (arguments.size() > 2)) {
/* 741 */       System.out.println("Decode an Zeo save data file (zeosleep.dat).\n\nThis program decodes sleep records in a zeosleep.dat file into\neither a human readable format or XML depending upon how it is\ninvoked. If the data is written to standard output then it is\noutput as human readable text. If a file is specified and that\nfile's extension is .xml then the data is output as XML. Any other\nfile extension specified will cause the program to output the human\nreadable version to the specified file.\n\nTo output data to standard output invoke this command with just\nthe zeosleep.dat input file name. To output data to an XML file\ninvoke this command with two arguments; the zeosleep.dat file and\nthe intended XML file zeosleep.xml (please include .xml extension).\n\n");
/* 742 */       System.out.println("Usage:\njava -jar OpenDecoder.jar [options] directory/zeosleep.dat\nor\njava -jar OpenDecoder.jar [options] directory/zeosleep.dat\n                                    directory/zeosleep.xml\nor\njava -jar OpenDecoder.jar [options] directory/zeosleep.dat\n                                    directory/zeosleep.txt\n");
/* 743 */       System.out.println("Options:\n-e or --expand  : Don't remove related records for each night.\n-r or --resets  : Output just watchdog reset records.\n-V or --version : Display decoder version and exit");
/* 744 */       System.err.println("Invalid program arguments. ");
/* 745 */       System.err.println("You must specify the location of a zeosleep.dat file.");
/*     */       
/* 747 */       System.exit(1);
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 752 */       ichannel = new FileInputStream((String)arguments.get(0)).getChannel();
/*     */     } catch (FileNotFoundException e) {
/* 754 */       System.err.println("ERROR: Zeosleep file " + (String)arguments.get(0) + 
/* 755 */         " not found.");
/* 756 */       System.exit(1);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/* 764 */       byte[] data = new byte[(int)ichannel.size()];
/* 765 */       ByteBuffer in = ByteBuffer.wrap(data);
/*     */       
/*     */ 
/* 768 */       ichannel.read(in);
/* 769 */       in.rewind();
/*     */       
/*     */ 
/* 772 */       ZeoDataDecoder decoder = new ZeoDataDecoder(in);
/*     */       
/*     */ 
/* 775 */       int size = decoder.size();
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 781 */       if (size != 0)
/*     */       {
/*     */ 
/*     */ 
/* 785 */         if (reduce)
/*     */         {
/*     */ 
/*     */ 
/* 789 */           decoder.reduce_records();
/*     */           
/*     */ 
/* 792 */           size = decoder.size();
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 798 */           if (size == 0) {
/* 799 */             System.err.println("WARNING: recorded reduced down to 0 records. There will be no output.\nPlease try executing the command with the --expand option.\n");
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 808 */       decoder.label_naps();
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 813 */       if (arguments.size() == 1)
/*     */       {
/*     */ 
/*     */ 
/* 817 */         System.out.println(decoder.toHuman());
/*     */       }
/*     */       else {
/* 820 */         String filename = (String)arguments.get(1);
/* 821 */         FileOutputStream ostream = null;
/*     */         try
/*     */         {
/* 824 */           ostream = new FileOutputStream(filename);
/*     */         } catch (FileNotFoundException e) {
/* 826 */           System.err.println("ERROR: Could not open output file " + 
/* 827 */             filename);
/* 828 */           System.exit(1);
/*     */         }
/* 830 */         PrintWriter out = new PrintWriter(ostream);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 837 */         String extension = filename.lastIndexOf(".") == -1 ? 
/* 838 */           "" : 
/* 839 */           filename.substring(filename.lastIndexOf(".") + 1, 
/* 840 */           filename.length());
/* 841 */         if (extension.toLowerCase().equals("xml")) {
/* 842 */           out.println(decoder.toXML());
/*     */         } else {
/* 844 */           out.println(decoder.toHuman());
/*     */         }
/* 846 */         out.flush();
/* 847 */         out.close();
/*     */       }
/*     */     } catch (IOException e) {
/* 850 */       System.err.println("ERROR: Received an IOException.");
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\zeoDecoder\ZeoDataDecoder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */