/*     */ package zeoDecoderViewer;
/*     */ 
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.PrintWriter;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.swing.ActionMap;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.GroupLayout.Alignment;
/*     */ import javax.swing.GroupLayout.ParallelGroup;
/*     */ import javax.swing.GroupLayout.SequentialGroup;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JSplitPane;
/*     */ import javax.swing.JTree;
/*     */ import javax.swing.event.TreeSelectionEvent;
/*     */ import javax.swing.tree.DefaultMutableTreeNode;
/*     */ import org.jdesktop.application.ResourceMap;
/*     */ import org.joda.time.format.DateTimeFormatter;
/*     */ import zeoDecoder.ZeoData;
/*     */ import zeoDecoder.ZeoDataDecoder;
/*     */ 
/*     */ public class ZeoDecodeView extends org.jdesktop.application.FrameView
/*     */ {
/*  38 */   private File workingDir = new File(".");
/*  39 */   private final String rootFolder = "/ZeoData/";
/*     */   private javax.swing.JFrame mainFrame;
/*     */   private JTree dataTree;
/*     */   private JPanel dirPanel;
/*     */   private JMenuItem directoryMenuItem;
/*     */   private JScrollPane jScrollPane1;
/*     */   private JSplitPane jSplitPane1;
/*     */   
/*  47 */   public ZeoDecodeView(org.jdesktop.application.SingleFrameApplication app) { super(app);
/*  48 */     initComponents();
/*  49 */     this.mainFrame = getFrame();
/*  50 */     getFrame().setDefaultCloseOperation(3);
/*  51 */     loadNights();
/*     */   }
/*     */   
/*     */   @org.jdesktop.application.Action
/*     */   public void showAboutBox()
/*     */   {
/*  57 */     if (this.aboutBox == null) {
/*  58 */       this.aboutBox = new ZeoDecoderAboutBox(this.mainFrame);
/*  59 */       this.aboutBox.setLocationRelativeTo(this.mainFrame);
/*     */     }
/*  61 */     ZeoDecoderViewer.getApplication().show(this.aboutBox);
/*     */   }
/*     */   
/*     */ 
/*     */   private JMenuItem loadMenuItem;
/*     */   private JPanel mainPanel;
/*     */   private JMenuBar menuBar;
/*     */   private nightPanel nightPanel1;
/*     */   private JButton refreshBtn;
/*     */   private javax.swing.JDialog aboutBox;
/*     */   private void initComponents()
/*     */   {
/*  73 */     this.mainPanel = new JPanel();
/*  74 */     this.jSplitPane1 = new JSplitPane();
/*  75 */     this.dirPanel = new JPanel();
/*  76 */     this.refreshBtn = new JButton();
/*  77 */     this.jScrollPane1 = new JScrollPane();
/*  78 */     this.dataTree = new JTree();
/*  79 */     this.nightPanel1 = new nightPanel();
/*  80 */     this.menuBar = new JMenuBar();
/*  81 */     JMenu fileMenu = new JMenu();
/*  82 */     this.loadMenuItem = new JMenuItem();
/*  83 */     this.directoryMenuItem = new JMenuItem();
/*  84 */     JMenuItem exitMenuItem = new JMenuItem();
/*  85 */     JMenu helpMenu = new JMenu();
/*  86 */     JMenuItem aboutMenuItem = new JMenuItem();
/*     */     
/*  88 */     this.mainPanel.setMaximumSize(new Dimension(580, 380));
/*  89 */     this.mainPanel.setName("mainPanel");
/*  90 */     this.mainPanel.setPreferredSize(new Dimension(750, 400));
/*  91 */     this.mainPanel.setRequestFocusEnabled(false);
/*     */     
/*  93 */     this.jSplitPane1.setDividerLocation(200);
/*  94 */     this.jSplitPane1.setMinimumSize(new Dimension(588, 380));
/*  95 */     this.jSplitPane1.setName("jSplitPane1");
/*  96 */     this.jSplitPane1.setPreferredSize(new Dimension(750, 380));
/*     */     
/*  98 */     this.dirPanel.setMinimumSize(new Dimension(150, 50));
/*  99 */     this.dirPanel.setName("dirPanel");
/* 100 */     this.dirPanel.setPreferredSize(new Dimension(150, 400));
/*     */     
/* 102 */     ResourceMap resourceMap = ((ZeoDecoderViewer)org.jdesktop.application.Application.getInstance(ZeoDecoderViewer.class)).getContext().getResourceMap(ZeoDecodeView.class);
/* 103 */     this.refreshBtn.setText(resourceMap.getString("refreshBtn.text", new Object[0]));
/* 104 */     this.refreshBtn.setName("refreshBtn");
/* 105 */     this.refreshBtn.addMouseListener(new java.awt.event.MouseAdapter() {
/*     */       public void mouseReleased(MouseEvent evt) {
/* 107 */         ZeoDecodeView.this.refreshBtnMouseReleased(evt);
/*     */       }
/*     */       
/* 110 */     });
/* 111 */     this.jScrollPane1.setVerticalScrollBarPolicy(22);
/* 112 */     this.jScrollPane1.setName("jScrollPane1");
/*     */     
/* 114 */     this.dataTree.setExpandsSelectedPaths(false);
/* 115 */     this.dataTree.setMaximumSize(new Dimension(150, 1000));
/* 116 */     this.dataTree.setName("dataTree");
/* 117 */     this.dataTree.setVisibleRowCount(10);
/* 118 */     this.dataTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
/*     */       public void valueChanged(TreeSelectionEvent evt) {
/* 120 */         ZeoDecodeView.this.dataTreeValueChanged(evt);
/*     */       }
/* 122 */     });
/* 123 */     this.jScrollPane1.setViewportView(this.dataTree);
/*     */     
/* 125 */     GroupLayout dirPanelLayout = new GroupLayout(this.dirPanel);
/* 126 */     this.dirPanel.setLayout(dirPanelLayout);
/* 127 */     dirPanelLayout.setHorizontalGroup(
/* 128 */       dirPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 129 */       .addGroup(dirPanelLayout.createSequentialGroup()
/* 130 */       .addGap(12, 12, 12)
/* 131 */       .addComponent(this.refreshBtn, -1, 126, 32767)
/* 132 */       .addContainerGap())
/* 133 */       .addComponent(this.jScrollPane1, -1, 150, 32767));
/*     */     
/* 135 */     dirPanelLayout.setVerticalGroup(
/* 136 */       dirPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 137 */       .addGroup(dirPanelLayout.createSequentialGroup()
/* 138 */       .addComponent(this.refreshBtn)
/* 139 */       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
/* 140 */       .addComponent(this.jScrollPane1, -1, 333, 32767)
/* 141 */       .addContainerGap()));
/*     */     
/*     */ 
/* 144 */     this.jSplitPane1.setLeftComponent(this.dirPanel);
/*     */     
/* 146 */     this.nightPanel1.setName("nightPanel1");
/* 147 */     this.jSplitPane1.setRightComponent(this.nightPanel1);
/*     */     
/* 149 */     GroupLayout mainPanelLayout = new GroupLayout(this.mainPanel);
/* 150 */     this.mainPanel.setLayout(mainPanelLayout);
/* 151 */     mainPanelLayout.setHorizontalGroup(
/* 152 */       mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 153 */       .addComponent(this.jSplitPane1, -1, 713, 32767));
/*     */     
/* 155 */     mainPanelLayout.setVerticalGroup(
/* 156 */       mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 157 */       .addGroup(mainPanelLayout.createSequentialGroup()
/* 158 */       .addComponent(this.jSplitPane1, -1, -1, 32767)
/* 159 */       .addGap(6, 6, 6)));
/*     */     
/*     */ 
/* 162 */     this.menuBar.setName("menuBar");
/*     */     
/* 164 */     fileMenu.setText(resourceMap.getString("fileMenu.text", new Object[0]));
/* 165 */     fileMenu.setName("fileMenu");
/*     */     
/* 167 */     this.loadMenuItem.setText(resourceMap.getString("loadMenuItem.text", new Object[0]));
/* 168 */     this.loadMenuItem.setName("loadMenuItem");
/* 169 */     this.loadMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
/*     */       public void mouseReleased(MouseEvent evt) {
/* 171 */         ZeoDecodeView.this.loadMenuItemMouseReleased(evt);
/*     */       }
/* 173 */     });
/* 174 */     fileMenu.add(this.loadMenuItem);
/*     */     
/* 176 */     this.directoryMenuItem.setText(resourceMap.getString("directoryMenuItem.text", new Object[0]));
/* 177 */     this.directoryMenuItem.setName("directoryMenuItem");
/* 178 */     this.directoryMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
/*     */       public void mouseReleased(MouseEvent evt) {
/* 180 */         ZeoDecodeView.this.directoryMenuItemMouseReleased(evt);
/*     */       }
/* 182 */     });
/* 183 */     fileMenu.add(this.directoryMenuItem);
/*     */     
/* 185 */     ActionMap actionMap = ((ZeoDecoderViewer)org.jdesktop.application.Application.getInstance(ZeoDecoderViewer.class)).getContext().getActionMap(ZeoDecodeView.class, this);
/* 186 */     exitMenuItem.setAction(actionMap.get("quit"));
/* 187 */     exitMenuItem.setName("exitMenuItem");
/* 188 */     fileMenu.add(exitMenuItem);
/*     */     
/* 190 */     this.menuBar.add(fileMenu);
/*     */     
/* 192 */     helpMenu.setText(resourceMap.getString("helpMenu.text", new Object[0]));
/* 193 */     helpMenu.setName("helpMenu");
/*     */     
/* 195 */     aboutMenuItem.setAction(actionMap.get("showAboutBox"));
/* 196 */     aboutMenuItem.setName("aboutMenuItem");
/* 197 */     helpMenu.add(aboutMenuItem);
/*     */     
/* 199 */     this.menuBar.add(helpMenu);
/*     */     
/* 201 */     setComponent(this.mainPanel);
/* 202 */     setMenuBar(this.menuBar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void loadMenuItemMouseReleased(MouseEvent evt)
/*     */   {
/* 211 */     JFileChooser fc = new JFileChooser();
/* 212 */     fc.setAcceptAllFileFilterUsed(false);
/* 213 */     fc.addChoosableFileFilter(new datFilter());
/* 214 */     fc.setCurrentDirectory(this.workingDir);
/* 215 */     int returnVal = fc.showOpenDialog(this.mainFrame);
/*     */     
/* 217 */     if (returnVal == 0) {
/* 218 */       File file = fc.getSelectedFile();
/* 219 */       loadData(file);
/* 220 */       loadNights();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void directoryMenuItemMouseReleased(MouseEvent evt)
/*     */   {
/* 230 */     JFileChooser fc = new JFileChooser();
/* 231 */     fc.setFileSelectionMode(1);
/* 232 */     fc.setCurrentDirectory(this.workingDir);
/* 233 */     int returnVal = fc.showOpenDialog(this.mainFrame);
/*     */     
/* 235 */     if (returnVal == 0) {
/* 236 */       this.workingDir = fc.getSelectedFile();
/* 237 */       loadNights();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void refreshBtnMouseReleased(MouseEvent evt)
/*     */   {
/* 246 */     loadNights();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void dataTreeValueChanged(TreeSelectionEvent evt)
/*     */   {
/* 255 */     Cursor waitCursor = new Cursor(3);
/* 256 */     this.mainPanel.setCursor(waitCursor);
/*     */     
/* 258 */     DefaultMutableTreeNode node = (DefaultMutableTreeNode)
/* 259 */       this.dataTree.getLastSelectedPathComponent();
/*     */     
/* 261 */     if (node != null) {
/* 262 */       Object nodeDat = node.getUserObject();
/* 263 */       if ((nodeDat != null) && ((nodeDat instanceof xmlZeoData))) {
/* 264 */         this.nightPanel1.update((xmlZeoData)nodeDat);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 269 */     Cursor normalCursor = new Cursor(0);
/* 270 */     this.mainPanel.setCursor(normalCursor);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void loadData(File f)
/*     */   {
/* 278 */     FileChannel ichannel = null;
/* 279 */     boolean reduce = true;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 284 */     Cursor waitCursor = new Cursor(3);
/* 285 */     this.mainPanel.setCursor(waitCursor);
/*     */     
/*     */     try
/*     */     {
/* 289 */       ichannel = new java.io.FileInputStream(f).getChannel();
/*     */     } catch (FileNotFoundException e) {
/* 291 */       JOptionPane.showMessageDialog(ZeoDecoderViewer.getApplication().getMainFrame(), 
/* 292 */         "ERROR: Zeosleep file " + f.getName() + " not found.", 
/* 293 */         "File Error", 2);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/* 301 */       byte[] data = new byte[(int)ichannel.size()];
/* 302 */       java.nio.ByteBuffer in = java.nio.ByteBuffer.wrap(data);
/*     */       
/*     */ 
/* 305 */       ichannel.read(in);
/* 306 */       in.rewind();
/*     */       
/*     */ 
/* 309 */       ZeoDataDecoder decoder = new ZeoDataDecoder(in);
/*     */       
/*     */ 
/* 312 */       int size = decoder.size();
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 318 */       if (size != 0)
/*     */       {
/*     */ 
/*     */ 
/* 322 */         if (reduce)
/*     */         {
/*     */ 
/*     */ 
/* 326 */           decoder.reduce_records();
/*     */           
/*     */ 
/* 329 */           size = decoder.size();
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 335 */           if (size == 0) {
/* 336 */             JOptionPane.showMessageDialog(ZeoDecoderViewer.getApplication().getMainFrame(), 
/* 337 */               "WARNING: recorded reduced down to 0 records. There will be no output.\nPlease try executing the command with the --expand option.\n", 
/*     */               
/*     */ 
/*     */ 
/*     */ 
/* 342 */               "File Error", 2);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 347 */       decoder.label_naps();
/* 348 */       List<ZeoData> records = decoder.get_records();
/*     */       
/*     */ 
/*     */ 
/* 352 */       for (ZeoData zd : records)
/*     */       {
/* 354 */         org.joda.time.LocalDateTime startTime = org.joda.time.LocalDateTime.fromCalendarFields(zd.get_start_of_night());
/* 355 */         DateTimeFormatter dirFormatter = org.joda.time.format.DateTimeFormat.forPattern("yyyy/MMMMM/");
/* 356 */         String directories = this.workingDir.getPath() + "/ZeoData/" + dirFormatter.print(startTime);
/* 357 */         DateTimeFormatter fileFormatter = org.joda.time.format.DateTimeFormat.forPattern("yyyy/MMMMM/dd_hh:mmaaa");
/* 358 */         String filename = this.workingDir.getPath() + "/ZeoData/" + fileFormatter.print(startTime) + ".xml";
/* 359 */         java.io.FileOutputStream ostream = null;
/*     */         
/*     */         try
/*     */         {
/* 363 */           new File(directories).mkdirs();
/*     */           
/* 365 */           ostream = new java.io.FileOutputStream(filename);
/*     */         } catch (FileNotFoundException e) {
/* 367 */           JOptionPane.showMessageDialog(ZeoDecoderViewer.getApplication().getMainFrame(), 
/* 368 */             "ERROR: Could not open output file " + filename, 
/* 369 */             "File Error", 2);
/*     */         }
/* 371 */         PrintWriter out = new PrintWriter(ostream);
/*     */         
/*     */ 
/* 374 */         out.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
/* 375 */         out.println("<sleep_records>");
/* 376 */         out.println(zd.toXML());
/* 377 */         out.println("</sleep_records>");
/*     */         
/* 379 */         out.flush();
/* 380 */         out.close();
/*     */       }
/*     */     } catch (Exception e) {
/* 383 */       JOptionPane.showMessageDialog(ZeoDecoderViewer.getApplication().getMainFrame(), 
/* 384 */         "ERROR: Error processing dat file.", 
/* 385 */         "File Error", 2);
/*     */     }
/*     */     
/*     */ 
/* 389 */     Cursor normalCursor = new Cursor(0);
/* 390 */     this.mainPanel.setCursor(normalCursor);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void loadNights()
/*     */   {
/* 400 */     Cursor waitCursor = new Cursor(3);
/* 401 */     this.mainPanel.setCursor(waitCursor);
/*     */     
/* 403 */     DefaultMutableTreeNode zeoNode = new DefaultMutableTreeNode("Zeo Data");
/* 404 */     javax.swing.tree.DefaultTreeModel tree = new javax.swing.tree.DefaultTreeModel(zeoNode);
/*     */     
/*     */ 
/* 407 */     new File(this.workingDir.getPath() + "/ZeoData/").mkdirs();
/* 408 */     File dir = new File(this.workingDir.getPath() + "/ZeoData/");
/*     */     
/*     */ 
/* 411 */     List<File> years = Arrays.asList(dir.listFiles(new yearFilter()));
/* 412 */     Collections.sort(years);
/* 413 */     Iterator localIterator2; for (Iterator localIterator1 = years.iterator(); localIterator1.hasNext(); 
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 420 */         localIterator2.hasNext())
/*     */     {
/* 413 */       File f = (File)localIterator1.next();
/* 414 */       DefaultMutableTreeNode fNode = new DefaultMutableTreeNode(Integer.valueOf(Integer.parseInt(f.getName())));
/* 415 */       zeoNode.add(fNode);
/*     */       
/*     */ 
/* 418 */       List<File> months = Arrays.asList(f.listFiles(new monthFilter()));
/* 419 */       Collections.sort(months);
/* 420 */       localIterator2 = months.iterator(); continue;File m = (File)localIterator2.next();
/* 421 */       DefaultMutableTreeNode mNode = new DefaultMutableTreeNode(m.getName());
/* 422 */       fNode.add(mNode);
/*     */       
/* 424 */       List<File> data = Arrays.asList(m.listFiles(new xmlFilter()));
/* 425 */       Collections.sort(data);
/* 426 */       for (File d : data)
/*     */       {
/* 428 */         DefaultMutableTreeNode dNode = new DefaultMutableTreeNode(new xmlZeoData(d));
/* 429 */         mNode.add(dNode);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 435 */     this.dataTree.setModel(tree);
/*     */     
/* 437 */     Cursor normalCursor = new Cursor(0);
/* 438 */     this.mainPanel.setCursor(normalCursor);
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\zeoDecoderViewer\ZeoDecodeView.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */