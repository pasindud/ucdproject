
package FLOG_GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Dushan Galappaththi
 */
public class PanelGamePlay extends javax.swing.JPanel {

    /**
     * Creates new form PanelGamePlay
     */
    private Image bg;
    JPanel pnlOppList;
    JPanel pnlOppRow;
    JScrollPane jsp;
    private int mouseX;
    private int mouseY;
    public PanelGamePlay() {
        initComponents();
         bg = new ImageIcon(getClass().getResource("/images/bg_playscreen.png")).getImage();
         pnlOppList = new JPanel();
         pnlOppRow = new JPanel();
    }

      @Override
    protected void paintComponent(Graphics g) 
    {
         g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
    
     public void drawOpponents(PlayerData[] pdArr)
    {
       
        int count =0;
        int div3 = (int)pdArr.length/3;
        int loopTimes = div3 +1;
        
        pnlOppRow.setLayout(new BoxLayout(pnlOppRow, BoxLayout.PAGE_AXIS));
        pnlOppRow.setOpaque(false);
        
        for(int i=0;i<loopTimes;i++)
        {
            JPanel pnlA = new JPanel();
                pnlA.setLayout(new FlowLayout(FlowLayout.LEADING));
                pnlA.setOpaque(false);
                
            for(int j=0;j<3;j++)
            {
            
               if(count<pdArr.length) 
               {
                pnlA.add(new OpponentsUI(pdArr[count].getPosition(), pdArr[count].getName(), pdArr[count].getScore(), pdArr[count].getLetterOne(), pdArr[count].letterTwo));
                 count++;
               }
         
            }
            pnlOppRow.add(pnlA);
        }
        
        jsp = new JScrollPane(pnlOppRow);
        jsp.setOpaque(false);
        jsp.setBorder(null);
        jsp.getViewport().setOpaque(false);
        pnlOpponents.add(jsp).setBounds(90, 0, 900, 315);
        
    }
    public void drawPlayerName (String name)
    {
        lblPlayerName.setText(name);
    }
    public void drawPlayerScore (int score)
    {
        lblScore.setText("Score : " + String.valueOf(score));
    }
    public void drawPlayerPosition (int pos)
    {
        lblPos.setText("Rank : " + String.valueOf(pos));
    }
    public void drawRoundNumber (int num)
    {
        lblRound.setText("ROUND "+String.valueOf(num));
    }
     
    public void drawTweleveLetters(String[] letterArr)
    {
        lblL1.setText(letterArr[0]);
        lblL2.setText(letterArr[1]);
        lblL3.setText(letterArr[2]);
        lblL4.setText(letterArr[3]);
        lblL5.setText(letterArr[4]);
        lblL6.setText(letterArr[5]);
        lblL7.setText(letterArr[6]);
        lblL8.setText(letterArr[7]);
        lblL9.setText(letterArr[8]);
        lblL10.setText(letterArr[9]);
        lblL11.setText(letterArr[10]);
        lblL12.setText(letterArr[11]);
    
    }
    
     
    public Component getCompTop(int x)
    {
        Component[] cmpList = this.getComponents();
        
        for(int i =0;i<cmpList.length;i++)
        {
              System.out.println(i + " "+cmpList[i].getY()+" "+ cmpList[i].getName() );
        }
        return this.pnlTop.getComponent(x);
    }
    
     public Component getCompBottom(int x)
    {
        Component[] cmpList = this.pnlBottom.getComponents();
        
        for(int i =0;i<cmpList.length;i++)
        {
              System.out.println(i + " "+cmpList[i].getY()+" "+ cmpList[i].getName() );
        }
        return this.pnlBottom.getComponent(x);
    }
     public Component getCompTopBorder(int x)
    {
        Component[] cmpList = this.pnlTopBorder.getComponents();
        
        for(int i =0;i<cmpList.length;i++)
        {
              System.out.println(i + " "+cmpList[i].getY()+" "+ cmpList[i].getName() );
        }
        return this.pnlTopBorder.getComponent(x);
    }
     
    
     public void setIcon_Vowels(ImageIcon icon)
     {
         this.btnVowels.setIcon(icon);
     
     }
      public void setIcon_Consonents(ImageIcon icon)
     {
         this.btnConsonents.setIcon(icon);
     
     }
       public void setIcon_Gen(ImageIcon icon)
     {
         this.btnGenerate.setIcon(icon);
     
     }
        public void setIcon_Submit(ImageIcon icon)
     {
         this.btnSubmit.setIcon(icon);
     
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new javax.swing.JPanel();
        pnlTopBorder = new javax.swing.JPanel();
        btnDisconnect = new javax.swing.JLabel();
        btnSettings = new javax.swing.JLabel();
        lblPlayerName = new javax.swing.JLabel();
        lblScore = new javax.swing.JLabel();
        lblPos = new javax.swing.JLabel();
        lblRound = new javax.swing.JLabel();
        pnlOpponents = new javax.swing.JPanel();
        pnlBottom = new javax.swing.JPanel();
        lblL1 = new javax.swing.JLabel();
        lblL2 = new javax.swing.JLabel();
        lblL3 = new javax.swing.JLabel();
        lblL4 = new javax.swing.JLabel();
        lblL5 = new javax.swing.JLabel();
        lblL6 = new javax.swing.JLabel();
        lblL7 = new javax.swing.JLabel();
        lblL8 = new javax.swing.JLabel();
        lblL9 = new javax.swing.JLabel();
        lblL10 = new javax.swing.JLabel();
        lblL11 = new javax.swing.JLabel();
        lblL12 = new javax.swing.JLabel();
        btnVowels = new javax.swing.JLabel();
        btnConsonents = new javax.swing.JLabel();
        btnGenerate = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JLabel();
        txtAnswer = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTop.setBackground(new java.awt.Color(102, 255, 255));
        pnlTop.setMaximumSize(new java.awt.Dimension(900, 72));
        pnlTop.setMinimumSize(new java.awt.Dimension(900, 72));
        pnlTop.setOpaque(false);
        pnlTop.setPreferredSize(new java.awt.Dimension(900, 72));
        pnlTop.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTopBorder.setName("dragingLabel"); // NOI18N
        pnlTopBorder.setOpaque(false);
        pnlTopBorder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlTopBorderMousePressed(evt);
            }
        });
        pnlTopBorder.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlTopBorderMouseDragged(evt);
            }
        });

        btnDisconnect.setText("close");
        btnDisconnect.setName("close"); // NOI18N

        btnSettings.setText("settings");
        btnSettings.setName("settings"); // NOI18N

        javax.swing.GroupLayout pnlTopBorderLayout = new javax.swing.GroupLayout(pnlTopBorder);
        pnlTopBorder.setLayout(pnlTopBorderLayout);
        pnlTopBorderLayout.setHorizontalGroup(
            pnlTopBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTopBorderLayout.createSequentialGroup()
                .addGap(0, 832, Short.MAX_VALUE)
                .addComponent(btnSettings)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDisconnect))
        );
        pnlTopBorderLayout.setVerticalGroup(
            pnlTopBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTopBorderLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(pnlTopBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDisconnect)
                    .addComponent(btnSettings))
                .addContainerGap())
        );

        pnlTop.add(pnlTopBorder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 38));

        lblPlayerName.setText("jLabel1");
        pnlTop.add(lblPlayerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, -1, -1));

        lblScore.setText("jLabel1");
        pnlTop.add(lblScore, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, -1, -1));

        lblPos.setText("jLabel1");
        pnlTop.add(lblPos, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, -1, -1));

        lblRound.setText("jLabel1");
        pnlTop.add(lblRound, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 50, -1, -1));

        add(pnlTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 72));

        pnlOpponents.setBackground(new java.awt.Color(51, 153, 255));
        pnlOpponents.setMaximumSize(new java.awt.Dimension(900, 315));
        pnlOpponents.setMinimumSize(new java.awt.Dimension(900, 315));
        pnlOpponents.setOpaque(false);

        javax.swing.GroupLayout pnlOpponentsLayout = new javax.swing.GroupLayout(pnlOpponents);
        pnlOpponents.setLayout(pnlOpponentsLayout);
        pnlOpponentsLayout.setHorizontalGroup(
            pnlOpponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        pnlOpponentsLayout.setVerticalGroup(
            pnlOpponentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );

        add(pnlOpponents, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 75, 900, 315));

        pnlBottom.setBackground(new java.awt.Color(0, 255, 255));
        pnlBottom.setOpaque(false);
        pnlBottom.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblL1.setBackground(new java.awt.Color(0, 174, 239));
        lblL1.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL1.setForeground(new java.awt.Color(255, 255, 255));
        lblL1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_normal.png"))); // NOI18N
        lblL1.setText("A");
        lblL1.setToolTipText("");
        lblL1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL1.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL1.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL1.setName("L1"); // NOI18N
        lblL1.setPreferredSize(new java.awt.Dimension(40, 43));
        pnlBottom.add(lblL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 7, 69, 84));

        lblL2.setBackground(new java.awt.Color(0, 174, 239));
        lblL2.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL2.setForeground(new java.awt.Color(255, 255, 255));
        lblL2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_normal.png"))); // NOI18N
        lblL2.setText("A");
        lblL2.setToolTipText("");
        lblL2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL2.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL2.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL2.setName("L2"); // NOI18N
        lblL2.setPreferredSize(new java.awt.Dimension(40, 43));
        pnlBottom.add(lblL2, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 7, 69, 84));

        lblL3.setBackground(new java.awt.Color(0, 174, 239));
        lblL3.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL3.setForeground(new java.awt.Color(255, 255, 255));
        lblL3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_normal.png"))); // NOI18N
        lblL3.setText("A");
        lblL3.setToolTipText("");
        lblL3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL3.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL3.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL3.setName("L3"); // NOI18N
        lblL3.setPreferredSize(new java.awt.Dimension(40, 43));
        pnlBottom.add(lblL3, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 7, 69, 84));

        lblL4.setBackground(new java.awt.Color(0, 174, 239));
        lblL4.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL4.setForeground(new java.awt.Color(255, 255, 255));
        lblL4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_normal.png"))); // NOI18N
        lblL4.setText("A");
        lblL4.setToolTipText("");
        lblL4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL4.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL4.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL4.setName("L4"); // NOI18N
        lblL4.setPreferredSize(new java.awt.Dimension(40, 43));
        pnlBottom.add(lblL4, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 7, 69, 84));

        lblL5.setBackground(new java.awt.Color(0, 174, 239));
        lblL5.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL5.setForeground(new java.awt.Color(255, 255, 255));
        lblL5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_normal.png"))); // NOI18N
        lblL5.setText("A");
        lblL5.setToolTipText("");
        lblL5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL5.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL5.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL5.setName("L5"); // NOI18N
        lblL5.setPreferredSize(new java.awt.Dimension(40, 43));
        pnlBottom.add(lblL5, new org.netbeans.lib.awtextra.AbsoluteConstraints(308, 7, 69, 84));

        lblL6.setBackground(new java.awt.Color(0, 174, 239));
        lblL6.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL6.setForeground(new java.awt.Color(255, 255, 255));
        lblL6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_normal.png"))); // NOI18N
        lblL6.setText("A");
        lblL6.setToolTipText("");
        lblL6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL6.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL6.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL6.setName("L6"); // NOI18N
        lblL6.setPreferredSize(new java.awt.Dimension(40, 43));
        pnlBottom.add(lblL6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 7, 69, 84));

        lblL7.setBackground(new java.awt.Color(0, 174, 239));
        lblL7.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL7.setForeground(new java.awt.Color(255, 255, 255));
        lblL7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_normal.png"))); // NOI18N
        lblL7.setText("A");
        lblL7.setToolTipText("");
        lblL7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL7.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL7.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL7.setName("L7"); // NOI18N
        lblL7.setPreferredSize(new java.awt.Dimension(40, 43));
        pnlBottom.add(lblL7, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 7, 69, 84));

        lblL8.setBackground(new java.awt.Color(0, 174, 239));
        lblL8.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL8.setForeground(new java.awt.Color(255, 255, 255));
        lblL8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_normal.png"))); // NOI18N
        lblL8.setText("A");
        lblL8.setToolTipText("");
        lblL8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL8.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL8.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL8.setName("L8"); // NOI18N
        lblL8.setPreferredSize(new java.awt.Dimension(40, 43));
        pnlBottom.add(lblL8, new org.netbeans.lib.awtextra.AbsoluteConstraints(524, 7, 69, 84));

        lblL9.setBackground(new java.awt.Color(0, 174, 239));
        lblL9.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL9.setForeground(new java.awt.Color(255, 255, 255));
        lblL9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_normal.png"))); // NOI18N
        lblL9.setText("A");
        lblL9.setToolTipText("");
        lblL9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL9.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL9.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL9.setName("L9"); // NOI18N
        lblL9.setPreferredSize(new java.awt.Dimension(40, 43));
        pnlBottom.add(lblL9, new org.netbeans.lib.awtextra.AbsoluteConstraints(596, 7, 69, 84));

        lblL10.setBackground(new java.awt.Color(0, 174, 239));
        lblL10.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL10.setForeground(new java.awt.Color(255, 255, 255));
        lblL10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_normal.png"))); // NOI18N
        lblL10.setText("A");
        lblL10.setToolTipText("");
        lblL10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL10.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL10.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL10.setName("L10"); // NOI18N
        lblL10.setPreferredSize(new java.awt.Dimension(40, 43));
        pnlBottom.add(lblL10, new org.netbeans.lib.awtextra.AbsoluteConstraints(668, 7, 69, 84));

        lblL11.setBackground(new java.awt.Color(0, 174, 239));
        lblL11.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL11.setForeground(new java.awt.Color(255, 255, 255));
        lblL11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_normal.png"))); // NOI18N
        lblL11.setText("A");
        lblL11.setToolTipText("");
        lblL11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL11.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL11.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL11.setName("L11"); // NOI18N
        lblL11.setPreferredSize(new java.awt.Dimension(40, 43));
        pnlBottom.add(lblL11, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 7, 69, 84));

        lblL12.setBackground(new java.awt.Color(0, 174, 239));
        lblL12.setFont(new java.awt.Font("Trebuchet MS", 1, 56)); // NOI18N
        lblL12.setForeground(new java.awt.Color(255, 255, 255));
        lblL12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblL12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ftletter_normal.png"))); // NOI18N
        lblL12.setText("A");
        lblL12.setToolTipText("");
        lblL12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblL12.setMaximumSize(new java.awt.Dimension(40, 43));
        lblL12.setMinimumSize(new java.awt.Dimension(40, 43));
        lblL12.setName("L12"); // NOI18N
        lblL12.setPreferredSize(new java.awt.Dimension(40, 43));
        pnlBottom.add(lblL12, new org.netbeans.lib.awtextra.AbsoluteConstraints(812, 7, 69, 84));

        btnVowels.setBackground(new java.awt.Color(0, 153, 153));
        btnVowels.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        btnVowels.setForeground(new java.awt.Color(255, 255, 255));
        btnVowels.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnVowels.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_vowels_n.png"))); // NOI18N
        btnVowels.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVowels.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVowels.setName("Vowels"); // NOI18N
        btnVowels.setPreferredSize(new java.awt.Dimension(140, 32));
        pnlBottom.add(btnVowels, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 204, 39));

        btnConsonents.setBackground(new java.awt.Color(0, 153, 153));
        btnConsonents.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        btnConsonents.setForeground(new java.awt.Color(255, 255, 255));
        btnConsonents.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnConsonents.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_consonent_n.png"))); // NOI18N
        btnConsonents.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConsonents.setName("Consonents"); // NOI18N
        btnConsonents.setPreferredSize(new java.awt.Dimension(140, 32));
        pnlBottom.add(btnConsonents, new org.netbeans.lib.awtextra.AbsoluteConstraints(478, 111, 204, 39));

        btnGenerate.setBackground(new java.awt.Color(0, 153, 153));
        btnGenerate.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        btnGenerate.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGenerate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_autosearch_n.png"))); // NOI18N
        btnGenerate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGenerate.setName("Gen"); // NOI18N
        btnGenerate.setPreferredSize(new java.awt.Dimension(140, 32));
        pnlBottom.add(btnGenerate, new org.netbeans.lib.awtextra.AbsoluteConstraints(696, 138, 72, 72));

        btnSubmit.setBackground(new java.awt.Color(0, 153, 153));
        btnSubmit.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        btnSubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_submit_n.png"))); // NOI18N
        btnSubmit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSubmit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSubmit.setName("Submit"); // NOI18N
        btnSubmit.setPreferredSize(new java.awt.Dimension(140, 32));
        pnlBottom.add(btnSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(775, 108, 102, 102));

        txtAnswer.setBackground(new java.awt.Color(76, 76, 76));
        txtAnswer.setFont(new java.awt.Font("Trebuchet MS", 0, 36)); // NOI18N
        txtAnswer.setForeground(new java.awt.Color(255, 255, 255));
        txtAnswer.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAnswer.setText("Answer");
        txtAnswer.setBorder(null);
        txtAnswer.setName("Answer"); // NOI18N
        pnlBottom.add(txtAnswer, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 167, 400, 39));

        add(pnlBottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 387, 900, 232));
    }// </editor-fold>//GEN-END:initComponents

    private void pnlTopBorderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopBorderMouseDragged
       
        System.out.println("dragged");
        
    }//GEN-LAST:event_pnlTopBorderMouseDragged

    private void pnlTopBorderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopBorderMousePressed
        System.out.println("pressed");
    }//GEN-LAST:event_pnlTopBorderMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnConsonents;
    private javax.swing.JLabel btnDisconnect;
    private javax.swing.JLabel btnGenerate;
    private javax.swing.JLabel btnSettings;
    private javax.swing.JLabel btnSubmit;
    private javax.swing.JLabel btnVowels;
    private javax.swing.JLabel lblL1;
    private javax.swing.JLabel lblL10;
    private javax.swing.JLabel lblL11;
    private javax.swing.JLabel lblL12;
    private javax.swing.JLabel lblL2;
    private javax.swing.JLabel lblL3;
    private javax.swing.JLabel lblL4;
    private javax.swing.JLabel lblL5;
    private javax.swing.JLabel lblL6;
    private javax.swing.JLabel lblL7;
    private javax.swing.JLabel lblL8;
    private javax.swing.JLabel lblL9;
    private javax.swing.JLabel lblPlayerName;
    private javax.swing.JLabel lblPos;
    private javax.swing.JLabel lblRound;
    private javax.swing.JLabel lblScore;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlOpponents;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JPanel pnlTopBorder;
    private javax.swing.JTextField txtAnswer;
    // End of variables declaration//GEN-END:variables
}
