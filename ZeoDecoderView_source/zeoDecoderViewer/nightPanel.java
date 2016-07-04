/*     */ package zeoDecoderViewer;
/*     */ 
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.GroupLayout.Alignment;
/*     */ import javax.swing.GroupLayout.ParallelGroup;
/*     */ import javax.swing.GroupLayout.SequentialGroup;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JProgressBar;
/*     */ import org.jdesktop.application.ResourceMap;
/*     */ 
/*     */ public class nightPanel extends javax.swing.JPanel
/*     */ {
/*     */   private static final long serialVersionUID = 3634318786207186721L;
/*  14 */   private static org.joda.time.format.DateTimeFormatter TIME_FORMATTER = org.joda.time.format.DateTimeFormat.forPattern("hh:mma");
/*     */   private JLabel bedtimeLbl;
/*     */   
/*     */   public nightPanel() {
/*  18 */     initComponents();
/*     */   }
/*     */   
/*     */   private JLabel bedtimeValue;
/*     */   private JProgressBar deepPercentValue;
/*     */   private JLabel deepSleepLbl;
/*     */   
/*     */   public void update(xmlZeoData dat) {
/*  26 */     this.zqValue.setText(dat.getZQ().toString());
/*  27 */     this.bedtimeValue.setText(TIME_FORMATTER.print(dat.getBedtime()));
/*     */     
/*  29 */     this.hypnogramPanel1.clear();
/*  30 */     if ((dat.getRiseTime() != null) && (dat.getHypStart() != null)) {
/*  31 */       graphs.NightlyReportHypnogram hyp = new graphs.NightlyReportHypnogram(dat.getHypnogram(), dat.getHypStart(), dat.getHypEnd());
/*  32 */       this.hypnogramPanel1.setHypnogramImage(hyp.render());
/*  33 */       this.riseTimeValue.setText(TIME_FORMATTER.print(dat.getRiseTime()));
/*     */     }
/*  35 */     this.hypnogramPanel1.repaint();
/*  36 */     this.zTimeValue.setText(String.format("%02d:%02d", new Object[] { Integer.valueOf(dat.getZTime().intValue() / 2), Integer.valueOf(dat.getZTime().intValue() % 2 * 30) }));
/*  37 */     this.wokenTimeValue.setText(dat.getWokenTimes().toString());
/*  38 */     Integer total = dat.getTotalSleep();
/*  39 */     Integer rem = dat.getREMSleep();
/*  40 */     Integer light = dat.getLightSleep();
/*  41 */     Integer deep = dat.getDeepSleep();
/*  42 */     Integer wake = dat.getWakeTime();
/*     */     
/*  44 */     this.totalSleepValue.setText(String.format("%02d:%02d:%02d", new Object[] { Integer.valueOf(total.intValue() / 120), Integer.valueOf(total.intValue() % 120 / 2), Integer.valueOf(total.intValue() % 2 * 30) }));
/*     */     
/*  46 */     this.remSleepValue.setText(String.format("%02d:%02d:%02d", new Object[] { Integer.valueOf(rem.intValue() / 120), Integer.valueOf(rem.intValue() % 120 / 2), Integer.valueOf(rem.intValue() % 2 * 30) }));
/*  47 */     this.remPercentValue.setValue(rem.intValue() * 100 / total.intValue());
/*     */     
/*  49 */     this.lightSleepValue.setText(String.format("%02d:%02d:%02d", new Object[] { Integer.valueOf(light.intValue() / 120), Integer.valueOf(light.intValue() % 120 / 2), Integer.valueOf(light.intValue() % 2 * 30) }));
/*  50 */     this.lightPercentValue.setValue(light.intValue() * 100 / total.intValue());
/*     */     
/*  52 */     this.deepSleepValue.setText(String.format("%02d:%02d:%02d", new Object[] { Integer.valueOf(deep.intValue() / 120), Integer.valueOf(deep.intValue() % 120 / 2), Integer.valueOf(deep.intValue() % 2 * 30) }));
/*  53 */     this.deepPercentValue.setValue(deep.intValue() * 100 / total.intValue());
/*     */     
/*  55 */     this.wakeTimeValue.setText(String.format("%02d:%02d:%02d", new Object[] { Integer.valueOf(wake.intValue() / 120), Integer.valueOf(wake.intValue() % 120 / 2), Integer.valueOf(wake.intValue() % 2 * 30) }));
/*  56 */     this.wakePercentValue.setValue(wake.intValue() * 100 / total.intValue());
/*     */   }
/*     */   
/*     */   private JLabel deepSleepValue;
/*     */   private hypnogramPanel hypnogramPanel1;
/*     */   private JProgressBar lightPercentValue;
/*     */   private JLabel lightSleepLbl;
/*     */   private JLabel lightSleepValue;
/*     */   private JProgressBar remPercentValue;
/*     */   
/*     */   private void initComponents()
/*     */   {
/*  68 */     this.zqLbl = new JLabel();
/*  69 */     this.bedtimeLbl = new JLabel();
/*  70 */     this.riseTimeLbl = new JLabel();
/*  71 */     this.zTimeLbl = new JLabel();
/*  72 */     this.totalPercentValue = new JProgressBar();
/*  73 */     this.totalSleepLbl = new JLabel();
/*  74 */     this.remSleepLbl = new JLabel();
/*  75 */     this.lightSleepLbl = new JLabel();
/*  76 */     this.deepSleepLbl = new JLabel();
/*  77 */     this.wakeTimeLbl = new JLabel();
/*  78 */     this.remPercentValue = new JProgressBar();
/*  79 */     this.lightPercentValue = new JProgressBar();
/*  80 */     this.deepPercentValue = new JProgressBar();
/*  81 */     this.wakePercentValue = new JProgressBar();
/*  82 */     this.wokenTimeLbl = new JLabel();
/*  83 */     this.zqValue = new JLabel();
/*  84 */     this.bedtimeValue = new JLabel();
/*  85 */     this.zTimeValue = new JLabel();
/*  86 */     this.totalSleepValue = new JLabel();
/*  87 */     this.remSleepValue = new JLabel();
/*  88 */     this.lightSleepValue = new JLabel();
/*  89 */     this.deepSleepValue = new JLabel();
/*  90 */     this.wakeTimeValue = new JLabel();
/*  91 */     this.riseTimeValue = new JLabel();
/*  92 */     this.wokenTimeValue = new JLabel();
/*  93 */     this.hypnogramPanel1 = new hypnogramPanel();
/*     */     
/*  95 */     setMaximumSize(new java.awt.Dimension(32767, 600));
/*  96 */     setPreferredSize(new java.awt.Dimension(590, 380));
/*     */     
/*  98 */     this.zqLbl.setLabelFor(this.zqValue);
/*  99 */     ResourceMap resourceMap = ((ZeoDecoderViewer)org.jdesktop.application.Application.getInstance(ZeoDecoderViewer.class)).getContext().getResourceMap(nightPanel.class);
/* 100 */     this.zqLbl.setText(resourceMap.getString("zqLbl.text", new Object[0]));
/* 101 */     this.zqLbl.setName("zqLbl");
/*     */     
/* 103 */     this.bedtimeLbl.setText(resourceMap.getString("bedtimeLbl.text", new Object[0]));
/* 104 */     this.bedtimeLbl.setName("bedtimeLbl");
/*     */     
/* 106 */     this.riseTimeLbl.setText(resourceMap.getString("riseTimeLbl.text", new Object[0]));
/* 107 */     this.riseTimeLbl.setName("riseTimeLbl");
/*     */     
/* 109 */     this.zTimeLbl.setText(resourceMap.getString("zTimeLbl.text", new Object[0]));
/* 110 */     this.zTimeLbl.setName("zTimeLbl");
/*     */     
/* 112 */     this.totalPercentValue.setForeground(resourceMap.getColor("totalPercentValue.foreground"));
/* 113 */     this.totalPercentValue.setValue(100);
/* 114 */     this.totalPercentValue.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
/* 115 */     this.totalPercentValue.setName("totalPercentValue");
/* 116 */     this.totalPercentValue.setStringPainted(true);
/*     */     
/* 118 */     this.totalSleepLbl.setLabelFor(this.totalSleepValue);
/* 119 */     this.totalSleepLbl.setText(resourceMap.getString("totalSleepLbl.text", new Object[0]));
/* 120 */     this.totalSleepLbl.setName("totalSleepLbl");
/*     */     
/* 122 */     this.remSleepLbl.setLabelFor(this.remSleepValue);
/* 123 */     this.remSleepLbl.setText(resourceMap.getString("remSleepLbl.text", new Object[0]));
/* 124 */     this.remSleepLbl.setName("remSleepLbl");
/*     */     
/* 126 */     this.lightSleepLbl.setLabelFor(this.lightSleepValue);
/* 127 */     this.lightSleepLbl.setText(resourceMap.getString("lightSleepLbl.text", new Object[0]));
/* 128 */     this.lightSleepLbl.setName("lightSleepLbl");
/*     */     
/* 130 */     this.deepSleepLbl.setLabelFor(this.deepSleepValue);
/* 131 */     this.deepSleepLbl.setText(resourceMap.getString("deepSleepLbl.text", new Object[0]));
/* 132 */     this.deepSleepLbl.setName("deepSleepLbl");
/*     */     
/* 134 */     this.wakeTimeLbl.setLabelFor(this.wakeTimeValue);
/* 135 */     this.wakeTimeLbl.setText(resourceMap.getString("wakeTimeLbl.text", new Object[0]));
/* 136 */     this.wakeTimeLbl.setName("wakeTimeLbl");
/*     */     
/* 138 */     this.remPercentValue.setName("remPercentValue");
/* 139 */     this.remPercentValue.setStringPainted(true);
/*     */     
/* 141 */     this.lightPercentValue.setName("lightPercentValue");
/* 142 */     this.lightPercentValue.setStringPainted(true);
/*     */     
/* 144 */     this.deepPercentValue.setName("deepPercentValue");
/* 145 */     this.deepPercentValue.setStringPainted(true);
/*     */     
/* 147 */     this.wakePercentValue.setName("wakePercentValue");
/* 148 */     this.wakePercentValue.setStringPainted(true);
/*     */     
/* 150 */     this.wokenTimeLbl.setText(resourceMap.getString("wokenTimeLbl.text", new Object[0]));
/* 151 */     this.wokenTimeLbl.setName("wokenTimeLbl");
/*     */     
/* 153 */     this.zqValue.setText(resourceMap.getString("zqValue.text", new Object[0]));
/* 154 */     this.zqValue.setName("zqValue");
/*     */     
/* 156 */     this.bedtimeValue.setText(resourceMap.getString("bedtimeValue.text", new Object[0]));
/* 157 */     this.bedtimeValue.setName("bedtimeValue");
/*     */     
/* 159 */     this.zTimeValue.setText(resourceMap.getString("zTimeValue.text", new Object[0]));
/* 160 */     this.zTimeValue.setName("zTimeValue");
/*     */     
/* 162 */     this.totalSleepValue.setText(resourceMap.getString("totalSleepValue.text", new Object[0]));
/* 163 */     this.totalSleepValue.setName("totalSleepValue");
/*     */     
/* 165 */     this.remSleepValue.setText(resourceMap.getString("remSleepValue.text", new Object[0]));
/* 166 */     this.remSleepValue.setName("remSleepValue");
/*     */     
/* 168 */     this.lightSleepValue.setText(resourceMap.getString("lightSleepValue.text", new Object[0]));
/* 169 */     this.lightSleepValue.setName("lightSleepValue");
/*     */     
/* 171 */     this.deepSleepValue.setText(resourceMap.getString("deepSleepValue.text", new Object[0]));
/* 172 */     this.deepSleepValue.setName("deepSleepValue");
/*     */     
/* 174 */     this.wakeTimeValue.setText(resourceMap.getString("wakeTimeValue.text", new Object[0]));
/* 175 */     this.wakeTimeValue.setName("wakeTimeValue");
/*     */     
/* 177 */     this.riseTimeValue.setText(resourceMap.getString("riseTimeValue.text", new Object[0]));
/* 178 */     this.riseTimeValue.setName("riseTimeValue");
/*     */     
/* 180 */     this.wokenTimeValue.setText(resourceMap.getString("wokenTimeValue.text", new Object[0]));
/* 181 */     this.wokenTimeValue.setName("wokenTimeValue");
/*     */     
/* 183 */     this.hypnogramPanel1.setBackground(resourceMap.getColor("hypnogramPanel1.background"));
/* 184 */     this.hypnogramPanel1.setName("hypnogramPanel1");
/* 185 */     this.hypnogramPanel1.setPreferredSize(new java.awt.Dimension(576, 140));
/*     */     
/* 187 */     GroupLayout hypnogramPanel1Layout = new GroupLayout(this.hypnogramPanel1);
/* 188 */     this.hypnogramPanel1.setLayout(hypnogramPanel1Layout);
/* 189 */     hypnogramPanel1Layout.setHorizontalGroup(
/* 190 */       hypnogramPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 191 */       .addGap(0, 588, 32767));
/*     */     
/* 193 */     hypnogramPanel1Layout.setVerticalGroup(
/* 194 */       hypnogramPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 195 */       .addGap(0, 140, 32767));
/*     */     
/*     */ 
/* 198 */     GroupLayout layout = new GroupLayout(this);
/* 199 */     setLayout(layout);
/* 200 */     layout.setHorizontalGroup(
/* 201 */       layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 202 */       .addGroup(layout.createSequentialGroup()
/* 203 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 204 */       .addGroup(layout.createSequentialGroup()
/* 205 */       .addContainerGap()
/* 206 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 207 */       .addComponent(this.remSleepLbl, -1, -1, 32767)
/* 208 */       .addComponent(this.lightSleepLbl, -1, -1, 32767)
/* 209 */       .addComponent(this.deepSleepLbl, -1, -1, 32767)
/* 210 */       .addComponent(this.totalSleepLbl, -1, -1, 32767)
/* 211 */       .addComponent(this.wakeTimeLbl, -1, -1, 32767))
/* 212 */       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 510, 32767))
/* 213 */       .addGroup(layout.createSequentialGroup()
/* 214 */       .addGap(12, 12, 12)
/* 215 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 216 */       .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
/* 217 */       .addComponent(this.zTimeLbl)
/* 218 */       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
/* 219 */       .addComponent(this.zTimeValue)
/* 220 */       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
/* 221 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 222 */       .addComponent(this.remSleepValue)
/* 223 */       .addComponent(this.lightSleepValue)
/* 224 */       .addComponent(this.deepSleepValue)
/* 225 */       .addComponent(this.wakeTimeValue)
/* 226 */       .addComponent(this.totalSleepValue))
/* 227 */       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
/* 228 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 229 */       .addComponent(this.totalPercentValue, -1, 464, 32767)
/* 230 */       .addComponent(this.remPercentValue, -1, 464, 32767)
/* 231 */       .addComponent(this.lightPercentValue, -1, 464, 32767)
/* 232 */       .addComponent(this.deepPercentValue, -1, 464, 32767)
/* 233 */       .addComponent(this.wakePercentValue, -1, 464, 32767)))
/* 234 */       .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
/* 235 */       .addComponent(this.bedtimeLbl)
/* 236 */       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
/* 237 */       .addComponent(this.bedtimeValue)
/* 238 */       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 397, 32767)
/* 239 */       .addComponent(this.riseTimeLbl)
/* 240 */       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
/* 241 */       .addComponent(this.riseTimeValue))
/* 242 */       .addGroup(layout.createSequentialGroup()
/* 243 */       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 484, 32767)
/* 244 */       .addComponent(this.wokenTimeLbl)
/* 245 */       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
/* 246 */       .addComponent(this.wokenTimeValue))))
/* 247 */       .addGroup(layout.createSequentialGroup()
/* 248 */       .addContainerGap()
/* 249 */       .addComponent(this.hypnogramPanel1, -1, 588, 32767))
/* 250 */       .addGroup(layout.createSequentialGroup()
/* 251 */       .addContainerGap()
/* 252 */       .addComponent(this.zqLbl)
/* 253 */       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
/* 254 */       .addComponent(this.zqValue)))
/* 255 */       .addGap(0, 0, 0)));
/*     */     
/* 257 */     layout.setVerticalGroup(
/* 258 */       layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 259 */       .addGroup(layout.createSequentialGroup()
/* 260 */       .addContainerGap()
/* 261 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 262 */       .addComponent(this.zqLbl)
/* 263 */       .addComponent(this.zqValue))
/* 264 */       .addGap(12, 12, 12)
/* 265 */       .addComponent(this.hypnogramPanel1, -1, -1, 32767)
/* 266 */       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
/* 267 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 268 */       .addComponent(this.bedtimeLbl)
/* 269 */       .addComponent(this.bedtimeValue)
/* 270 */       .addComponent(this.riseTimeValue)
/* 271 */       .addComponent(this.riseTimeLbl))
/* 272 */       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
/* 273 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 274 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 275 */       .addComponent(this.zTimeLbl)
/* 276 */       .addComponent(this.zTimeValue))
/* 277 */       .addComponent(this.wokenTimeLbl)
/* 278 */       .addComponent(this.wokenTimeValue))
/* 279 */       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
/* 280 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
/* 281 */       .addComponent(this.totalSleepLbl)
/* 282 */       .addComponent(this.totalSleepValue)
/* 283 */       .addComponent(this.totalPercentValue, -2, -1, -2))
/* 284 */       .addGap(9, 9, 9)
/* 285 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
/* 286 */       .addComponent(this.remSleepLbl)
/* 287 */       .addComponent(this.remSleepValue)
/* 288 */       .addComponent(this.remPercentValue, -2, -1, -2))
/* 289 */       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
/* 290 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
/* 291 */       .addComponent(this.lightSleepLbl)
/* 292 */       .addComponent(this.lightSleepValue)
/* 293 */       .addComponent(this.lightPercentValue, -2, -1, -2))
/* 294 */       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
/* 295 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
/* 296 */       .addComponent(this.deepSleepLbl)
/* 297 */       .addComponent(this.deepSleepValue)
/* 298 */       .addComponent(this.deepPercentValue, -2, -1, -2))
/* 299 */       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
/* 300 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
/* 301 */       .addComponent(this.wakeTimeLbl)
/* 302 */       .addComponent(this.wakeTimeValue)
/* 303 */       .addComponent(this.wakePercentValue, -2, -1, -2))
/* 304 */       .addContainerGap()));
/*     */   }
/*     */   
/*     */   private JLabel remSleepLbl;
/*     */   private JLabel remSleepValue;
/*     */   private JLabel riseTimeLbl;
/*     */   private JLabel riseTimeValue;
/*     */   private JProgressBar totalPercentValue;
/*     */   private JLabel totalSleepLbl;
/*     */   private JLabel totalSleepValue;
/*     */   private JProgressBar wakePercentValue;
/*     */   private JLabel wakeTimeLbl;
/*     */   private JLabel wakeTimeValue;
/*     */   private JLabel wokenTimeLbl;
/*     */   private JLabel wokenTimeValue;
/*     */   private JLabel zTimeLbl;
/*     */   private JLabel zTimeValue;
/*     */   private JLabel zqLbl;
/*     */   private JLabel zqValue;
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\zeoDecoderViewer\nightPanel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */