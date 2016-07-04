/*     */ package zeoDecoderViewer;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.List;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import org.joda.time.LocalDateTime;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ public class xmlZeoData implements Comparable
/*     */ {
/*     */   private File src;
/*  16 */   private Boolean loaded = Boolean.valueOf(false);
/*     */   private Integer ZQ;
/*  18 */   private List<Integer> hypnogram = new java.util.ArrayList();
/*     */   
/*     */   private LocalDateTime startHyp;
/*     */   
/*     */   private LocalDateTime endHyp;
/*     */   
/*     */   private LocalDateTime bedtime;
/*     */   
/*     */   private LocalDateTime risetime;
/*     */   private Integer zTime;
/*     */   private Integer wokenTimes;
/*     */   private Integer totalSleep;
/*     */   private Integer remSleep;
/*     */   private Integer lightSleep;
/*     */   private Integer deepSleep;
/*     */   private Integer wakeTime;
/*     */   
/*     */   public xmlZeoData(File f)
/*     */   {
/*  37 */     this.src = f;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Integer getZQ()
/*     */   {
/*  46 */     if (!this.loaded.booleanValue()) loadFile();
/*  47 */     return this.ZQ;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<Integer> getHypnogram()
/*     */   {
/*  55 */     if (!this.loaded.booleanValue()) {
/*  56 */       loadFile();
/*     */     }
/*  58 */     return this.hypnogram;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public LocalDateTime getHypStart()
/*     */   {
/*  66 */     if (!this.loaded.booleanValue()) loadFile();
/*  67 */     return this.startHyp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public LocalDateTime getHypEnd()
/*     */   {
/*  75 */     if (!this.loaded.booleanValue()) loadFile();
/*  76 */     return this.endHyp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public LocalDateTime getBedtime()
/*     */   {
/*  84 */     if (!this.loaded.booleanValue()) loadFile();
/*  85 */     return this.bedtime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public LocalDateTime getRiseTime()
/*     */   {
/*  93 */     if (!this.loaded.booleanValue()) loadFile();
/*  94 */     return this.risetime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Integer getZTime()
/*     */   {
/* 102 */     if (!this.loaded.booleanValue()) loadFile();
/* 103 */     return this.zTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Integer getWokenTimes()
/*     */   {
/* 111 */     if (!this.loaded.booleanValue()) loadFile();
/* 112 */     return this.wokenTimes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Integer getTotalSleep()
/*     */   {
/* 120 */     if (!this.loaded.booleanValue()) loadFile();
/* 121 */     return this.totalSleep;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Integer getREMSleep()
/*     */   {
/* 129 */     if (!this.loaded.booleanValue()) loadFile();
/* 130 */     return this.remSleep;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Integer getLightSleep()
/*     */   {
/* 138 */     if (!this.loaded.booleanValue()) loadFile();
/* 139 */     return this.lightSleep;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Integer getDeepSleep()
/*     */   {
/* 147 */     if (!this.loaded.booleanValue()) loadFile();
/* 148 */     return this.deepSleep;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Integer getWakeTime()
/*     */   {
/* 156 */     if (!this.loaded.booleanValue()) loadFile();
/* 157 */     return this.wakeTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void loadFile()
/*     */   {
/*     */     try
/*     */     {
/* 166 */       DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
/* 167 */       DocumentBuilder db = dbf.newDocumentBuilder();
/* 168 */       Document doc = db.parse(this.src);
/*     */       
/*     */ 
/* 171 */       this.ZQ = Integer.valueOf(Integer.parseInt(((Element)doc.getElementsByTagName("zq_score").item(0)).getChildNodes().item(0).getNodeValue()));
/* 172 */       Integer hypCount = Integer.valueOf(Integer.parseInt(((Element)doc.getElementsByTagName("display_hypnogram_count").item(0)).getChildNodes().item(0).getNodeValue()));
/* 173 */       if (hypCount.intValue() > 0) {
/* 174 */         String[] hyp = ((Element)doc.getElementsByTagName("display_hypnogram").item(0)).getChildNodes().item(0).getNodeValue().trim().split(" ");
/*     */         String[] arrayOfString1;
/* 176 */         int j = (arrayOfString1 = hyp).length; for (int i = 0; i < j; i++) { String s = arrayOfString1[i];
/*     */           try {
/* 178 */             this.hypnogram.add(Integer.valueOf(Integer.parseInt(s)));
/*     */           }
/*     */           catch (NumberFormatException localNumberFormatException) {}
/*     */         }
/*     */       }
/*     */       else {
/* 184 */         this.hypnogram.add(Integer.valueOf(0));
/*     */       }
/*     */       
/* 187 */       Element hypST = (Element)doc.getElementsByTagName("hypnogram_start_time").item(0);
/* 188 */       if (hypST.getElementsByTagName("year") != null) {
/* 189 */         Integer hypSTYear = Integer.valueOf(Integer.parseInt(((Element)hypST.getElementsByTagName("year").item(0)).getChildNodes().item(0).getNodeValue()));
/* 190 */         Integer hypSTMonth = Integer.valueOf(Integer.parseInt(((Element)hypST.getElementsByTagName("month").item(0)).getChildNodes().item(0).getNodeValue()));
/* 191 */         Integer hypSTDay = Integer.valueOf(Integer.parseInt(((Element)hypST.getElementsByTagName("day").item(0)).getChildNodes().item(0).getNodeValue()));
/* 192 */         Integer hypSTHour = Integer.valueOf(Integer.parseInt(((Element)hypST.getElementsByTagName("hour").item(0)).getChildNodes().item(0).getNodeValue()));
/* 193 */         Integer hypSTMinute = Integer.valueOf(Integer.parseInt(((Element)hypST.getElementsByTagName("minute").item(0)).getChildNodes().item(0).getNodeValue()));
/* 194 */         Integer hypSTSecond = Integer.valueOf(Integer.parseInt(((Element)hypST.getElementsByTagName("second").item(0)).getChildNodes().item(0).getNodeValue()));
/* 195 */         this.startHyp = new LocalDateTime(hypSTYear.intValue(), hypSTMonth.intValue(), hypSTDay.intValue(), hypSTHour.intValue(), hypSTMinute.intValue(), hypSTSecond.intValue());
/*     */       }
/*     */       
/*     */ 
/* 199 */       Element bed = (Element)doc.getElementsByTagName("start_of_night").item(0);
/* 200 */       Integer bedYear = Integer.valueOf(Integer.parseInt(((Element)bed.getElementsByTagName("year").item(0)).getChildNodes().item(0).getNodeValue()));
/* 201 */       Integer bedMonth = Integer.valueOf(Integer.parseInt(((Element)bed.getElementsByTagName("month").item(0)).getChildNodes().item(0).getNodeValue()));
/* 202 */       Integer bedDay = Integer.valueOf(Integer.parseInt(((Element)bed.getElementsByTagName("day").item(0)).getChildNodes().item(0).getNodeValue()));
/* 203 */       Integer bedHour = Integer.valueOf(Integer.parseInt(((Element)bed.getElementsByTagName("hour").item(0)).getChildNodes().item(0).getNodeValue()));
/* 204 */       Integer bedMinute = Integer.valueOf(Integer.parseInt(((Element)bed.getElementsByTagName("minute").item(0)).getChildNodes().item(0).getNodeValue()));
/* 205 */       Integer bedSecond = Integer.valueOf(Integer.parseInt(((Element)bed.getElementsByTagName("second").item(0)).getChildNodes().item(0).getNodeValue()));
/* 206 */       this.bedtime = new LocalDateTime(bedYear.intValue(), bedMonth.intValue(), bedDay.intValue(), bedHour.intValue(), bedMinute.intValue(), bedSecond.intValue());
/*     */       
/* 208 */       Element rise = (Element)doc.getElementsByTagName("rise_time").item(0);
/* 209 */       if (rise.getElementsByTagName("year").item(0) != null) {
/* 210 */         Integer riseYear = Integer.valueOf(Integer.parseInt(((Element)rise.getElementsByTagName("year").item(0)).getChildNodes().item(0).getNodeValue()));
/* 211 */         Integer riseMonth = Integer.valueOf(Integer.parseInt(((Element)rise.getElementsByTagName("month").item(0)).getChildNodes().item(0).getNodeValue()));
/* 212 */         Integer riseDay = Integer.valueOf(Integer.parseInt(((Element)rise.getElementsByTagName("day").item(0)).getChildNodes().item(0).getNodeValue()));
/* 213 */         Integer riseHour = Integer.valueOf(Integer.parseInt(((Element)rise.getElementsByTagName("hour").item(0)).getChildNodes().item(0).getNodeValue()));
/* 214 */         Integer riseMinute = Integer.valueOf(Integer.parseInt(((Element)rise.getElementsByTagName("minute").item(0)).getChildNodes().item(0).getNodeValue()));
/* 215 */         Integer riseSecond = Integer.valueOf(Integer.parseInt(((Element)rise.getElementsByTagName("second").item(0)).getChildNodes().item(0).getNodeValue()));
/* 216 */         this.risetime = new LocalDateTime(riseYear.intValue(), riseMonth.intValue(), riseDay.intValue(), riseHour.intValue(), riseMinute.intValue(), riseSecond.intValue());
/* 217 */         this.endHyp = this.risetime;
/*     */       }
/*     */       
/* 220 */       this.zTime = Integer.valueOf(Integer.parseInt(((Element)doc.getElementsByTagName("time_to_z").item(0)).getChildNodes().item(0).getNodeValue()));
/* 221 */       this.wokenTimes = Integer.valueOf(Integer.parseInt(((Element)doc.getElementsByTagName("awakenings").item(0)).getChildNodes().item(0).getNodeValue()));
/* 222 */       this.totalSleep = Integer.valueOf(Integer.parseInt(((Element)doc.getElementsByTagName("total_z").item(0)).getChildNodes().item(0).getNodeValue()));
/* 223 */       this.remSleep = Integer.valueOf(Integer.parseInt(((Element)doc.getElementsByTagName("time_in_rem").item(0)).getChildNodes().item(0).getNodeValue()));
/* 224 */       this.lightSleep = Integer.valueOf(Integer.parseInt(((Element)doc.getElementsByTagName("time_in_light").item(0)).getChildNodes().item(0).getNodeValue()));
/* 225 */       this.deepSleep = Integer.valueOf(Integer.parseInt(((Element)doc.getElementsByTagName("time_in_deep").item(0)).getChildNodes().item(0).getNodeValue()));
/* 226 */       this.wakeTime = Integer.valueOf(Integer.parseInt(((Element)doc.getElementsByTagName("time_in_wake").item(0)).getChildNodes().item(0).getNodeValue()));
/* 227 */       this.loaded = Boolean.valueOf(true);
/*     */     } catch (Exception e) {
/* 229 */       e.printStackTrace();
/* 230 */       this.loaded = Boolean.valueOf(false);
/*     */     }
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 236 */     return this.src.getName().replace(".xml", "").replace("_", "@");
/*     */   }
/*     */   
/*     */   public int compareTo(Object o)
/*     */   {
/* 241 */     return toString().compareTo(o.toString());
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\zeoDecoderViewer\xmlZeoData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */