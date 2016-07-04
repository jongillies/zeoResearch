/*     */ package zeoDecoder;
/*     */ 
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.util.Calendar;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TimeChange
/*     */ {
/*     */   private Calendar change_time;
/*     */   private long change_timestamp;
/*     */   private Calendar value;
/*     */   private long value_timestamp;
/*     */   
/*     */   public TimeChange(long change_parm, long value_parm)
/*     */   {
/*  63 */     this.change_timestamp = change_parm;
/*  64 */     this.value_timestamp = value_parm;
/*     */     
/*  66 */     this.change_time = ZeoData.timestamp_to_calendar(this.change_timestamp);
/*  67 */     this.value = ZeoData.timestamp_to_calendar(this.value_timestamp);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getTime()
/*     */   {
/*  77 */     return this.change_timestamp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Calendar getTimeAsCalendar()
/*     */   {
/*  89 */     if (this.change_time == null) {
/*  90 */       return null;
/*     */     }
/*  92 */     return (Calendar)this.change_time.clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getValue()
/*     */   {
/* 103 */     return this.value_timestamp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Calendar getValueAsCalendar()
/*     */   {
/* 113 */     if (this.value == null) {
/* 114 */       return null;
/*     */     }
/* 116 */     return (Calendar)this.value.clone();
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
/* 127 */     return 
/* 128 */       "value: " + ZeoData.calendar_to_human_string(this.value) + " changed: " + ZeoData.calendar_to_human_string(this.change_time);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toXML()
/*     */   {
/* 138 */     StringWriter txt = new StringWriter();
/* 139 */     PrintWriter out = new PrintWriter(txt);
/*     */     
/* 141 */     out.println("<change_time>");
/* 142 */     out.println("<new_value>");
/* 143 */     out.write(ZeoData.calendar_to_xml(this.value));
/* 144 */     out.println("</new_value>");
/* 145 */     out.println("<time_changed>");
/* 146 */     out.write(ZeoData.calendar_to_xml(this.change_time));
/* 147 */     out.println("</time_changed>");
/* 148 */     out.println("</change_time>");
/* 149 */     return txt.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\zeoDecoder\TimeChange.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */