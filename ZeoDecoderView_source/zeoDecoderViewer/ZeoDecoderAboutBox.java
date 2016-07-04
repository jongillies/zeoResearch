/*     */ package zeoDecoderViewer;
/*     */ 
/*     */ import javax.swing.GroupLayout.ParallelGroup;
/*     */ import javax.swing.GroupLayout.SequentialGroup;
/*     */ import javax.swing.JLabel;
/*     */ 
/*     */ public class ZeoDecoderAboutBox extends javax.swing.JDialog
/*     */ {
/*     */   private static final long serialVersionUID = 7454330155919763047L;
/*     */   private javax.swing.JButton closeButton;
/*     */   
/*     */   public ZeoDecoderAboutBox(java.awt.Frame parent)
/*     */   {
/*  14 */     super(parent);
/*  15 */     initComponents();
/*  16 */     getRootPane().setDefaultButton(this.closeButton);
/*     */   }
/*     */   
/*     */   @org.jdesktop.application.Action
/*  20 */   public void closeAboutBox() { dispose(); }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  31 */     this.closeButton = new javax.swing.JButton();
/*  32 */     JLabel appTitleLabel = new JLabel();
/*  33 */     JLabel versionLabel = new JLabel();
/*  34 */     JLabel appVersionLabel = new JLabel();
/*  35 */     JLabel vendorLabel = new JLabel();
/*  36 */     JLabel appVendorLabel = new JLabel();
/*  37 */     JLabel homepageLabel = new JLabel();
/*  38 */     JLabel appHomepageLabel = new JLabel();
/*  39 */     JLabel appDescLabel = new JLabel();
/*     */     
/*  41 */     setDefaultCloseOperation(2);
/*  42 */     org.jdesktop.application.ResourceMap resourceMap = ((ZeoDecoderViewer)org.jdesktop.application.Application.getInstance(ZeoDecoderViewer.class)).getContext().getResourceMap(ZeoDecoderAboutBox.class);
/*  43 */     setTitle(resourceMap.getString("title", new Object[0]));
/*  44 */     setModal(true);
/*  45 */     setName("aboutBox");
/*  46 */     setResizable(false);
/*     */     
/*  48 */     javax.swing.ActionMap actionMap = ((ZeoDecoderViewer)org.jdesktop.application.Application.getInstance(ZeoDecoderViewer.class)).getContext().getActionMap(ZeoDecoderAboutBox.class, this);
/*  49 */     this.closeButton.setAction(actionMap.get("closeAboutBox"));
/*  50 */     this.closeButton.setName("closeButton");
/*     */     
/*  52 */     appTitleLabel.setFont(appTitleLabel.getFont().deriveFont(appTitleLabel.getFont().getStyle() | 0x1, appTitleLabel.getFont().getSize() + 4));
/*  53 */     appTitleLabel.setText(resourceMap.getString("Application.title", new Object[0]));
/*  54 */     appTitleLabel.setName("appTitleLabel");
/*     */     
/*  56 */     versionLabel.setFont(versionLabel.getFont().deriveFont(versionLabel.getFont().getStyle() | 0x1));
/*  57 */     versionLabel.setText(resourceMap.getString("versionLabel.text", new Object[0]));
/*  58 */     versionLabel.setName("versionLabel");
/*     */     
/*  60 */     appVersionLabel.setText(resourceMap.getString("Application.version", new Object[0]));
/*  61 */     appVersionLabel.setName("appVersionLabel");
/*     */     
/*  63 */     vendorLabel.setFont(vendorLabel.getFont().deriveFont(vendorLabel.getFont().getStyle() | 0x1));
/*  64 */     vendorLabel.setText(resourceMap.getString("vendorLabel.text", new Object[0]));
/*  65 */     vendorLabel.setName("vendorLabel");
/*     */     
/*  67 */     appVendorLabel.setText(resourceMap.getString("Application.vendor", new Object[0]));
/*  68 */     appVendorLabel.setName("appVendorLabel");
/*     */     
/*  70 */     homepageLabel.setFont(homepageLabel.getFont().deriveFont(homepageLabel.getFont().getStyle() | 0x1));
/*  71 */     homepageLabel.setText(resourceMap.getString("homepageLabel.text", new Object[0]));
/*  72 */     homepageLabel.setName("homepageLabel");
/*     */     
/*  74 */     appHomepageLabel.setText(resourceMap.getString("Application.homepage", new Object[0]));
/*  75 */     appHomepageLabel.setName("appHomepageLabel");
/*     */     
/*  77 */     appDescLabel.setText(resourceMap.getString("appDescLabel.text", new Object[0]));
/*  78 */     appDescLabel.setName("appDescLabel");
/*     */     
/*  80 */     javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
/*  81 */     getContentPane().setLayout(layout);
/*  82 */     layout.setHorizontalGroup(
/*  83 */       layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
/*  84 */       .addGroup(layout.createSequentialGroup()
/*  85 */       .addContainerGap()
/*  86 */       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
/*  87 */       .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
/*  88 */       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
/*  89 */       .addComponent(versionLabel)
/*  90 */       .addComponent(vendorLabel)
/*  91 */       .addComponent(homepageLabel))
/*  92 */       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
/*  93 */       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
/*  94 */       .addComponent(appVendorLabel)
/*  95 */       .addComponent(appVersionLabel)
/*  96 */       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
/*  97 */       .addComponent(this.closeButton)
/*  98 */       .addComponent(appHomepageLabel))))
/*  99 */       .addComponent(appTitleLabel, javax.swing.GroupLayout.Alignment.LEADING)
/* 100 */       .addComponent(appDescLabel, javax.swing.GroupLayout.Alignment.LEADING, -1, 277, 32767))
/* 101 */       .addContainerGap()));
/*     */     
/* 103 */     layout.setVerticalGroup(
/* 104 */       layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
/* 105 */       .addGroup(layout.createSequentialGroup()
/* 106 */       .addContainerGap()
/* 107 */       .addComponent(appTitleLabel)
/* 108 */       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
/* 109 */       .addComponent(appDescLabel, -2, -1, -2)
/* 110 */       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
/* 111 */       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
/* 112 */       .addComponent(versionLabel)
/* 113 */       .addComponent(appVersionLabel))
/* 114 */       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
/* 115 */       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
/* 116 */       .addComponent(vendorLabel)
/* 117 */       .addComponent(appVendorLabel))
/* 118 */       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
/* 119 */       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
/* 120 */       .addComponent(homepageLabel)
/* 121 */       .addComponent(appHomepageLabel))
/* 122 */       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
/* 123 */       .addComponent(this.closeButton)
/* 124 */       .addContainerGap()));
/*     */     
/*     */ 
/* 127 */     pack();
/*     */   }
/*     */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\zeoDecoderViewer\ZeoDecoderAboutBox.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */