package randommathart;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Frame extends javax.swing.JFrame {

    public Frame() {
        myInitComponents();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        int x = (width / 2) - (this.getWidth() / 2);
        int y = (height / 2) - (this.getHeight() / 2);
        this.setBounds(x, y, this.getWidth(), this.getHeight());
        
        rma = new RandomMathArt();
        numOfPictures = 0;
        icons = new PanelIcon[15];
        
        running = false;
        
        timer = new Timer();
        timer.scheduleAtFixedRate(new Notifier(this), TIMER_LENGTH, TIMER_LENGTH);
    }
    
    private void myInitComponents() {
        mainPanel = new JPanel();
        numOfPicturesLabel = new JTextField();
        exportSizeLabel = new JLabel();
        resolutionTextField = new JTextField();
        jMenuBar1 = new JMenuBar();
        startStopButton = new JMenu();
        exportButton = new JMenu();
        printTreeButton = new JMenu();
        exitButton = new JMenu();

        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Mathematical Pictures");
        this.setMinimumSize(new Dimension(1044, 600));
        this.setResizable(false);
        this.getContentPane().setBackground(GREY);

        FlowLayout mainPanelLayout = new FlowLayout(FlowLayout.CENTER);
        mainPanel.setLayout(mainPanelLayout);
        mainPanel.setBorder(new LineBorder(GREY, 1, false));
        mainPanel.setMinimumSize(new Dimension (1044, 634));
        mainPanel.setMaximumSize(new Dimension (1044, 634));
        mainPanel.setPreferredSize(new Dimension (1044, 634));
        
        mainPanel.setBackground(GREY);
        
        panels = new ImagePanel[15];
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new ImagePanel(null, i);
            panels[i].setMinimumSize(new Dimension (200, 200));
            panels[i].setMaximumSize(new Dimension (200, 200));
            panels[i].setPreferredSize(new Dimension (200, 200));
            panels[i].setMinimumSize(new Dimension(200, 200));
            panels[i].setBorder(DESELECTED_PANEL_BORDER);
            
            panels[i].setBackground(GREY);

            mainPanel.add(panels[i]);
        }
        
        panels[0].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(0);
                } else {
                    panelRightClicked(0);
                }
            }
        });
        panels[1].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(1);
                } else {
                    panelRightClicked(1);
                }
            }
        });
        panels[2].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(2);
                } else {
                    panelRightClicked(2);
                }
            }
        });
        panels[3].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(3);
                } else {
                    panelRightClicked(3);
                }
            }
        });
        panels[4].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(4);
                } else {
                    panelRightClicked(4);
                }
            }
        });
        panels[5].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(5);
                } else {
                    panelRightClicked(5);
                }
            }
        });
        panels[6].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(6);
                } else {
                    panelRightClicked(6);
                }
            }
        });
        panels[7].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(7);
                } else {
                    panelRightClicked(7);
                }
            }
        });
        panels[8].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(8);
                } else {
                    panelRightClicked(8);
                }
            }
        });
        panels[9].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(9);
                } else {
                    panelRightClicked(9);
                }
            }
        });
        panels[10].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(10);
                } else {
                    panelRightClicked(10);
                }
            }
        });
        panels[11].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(11);
                } else {
                    panelRightClicked(11);
                }
            }
        });
        panels[12].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(12);
                } else {
                    panelRightClicked(12);
                }
            }
        });
        panels[13].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(13);
                } else {
                    panelRightClicked(13);
                }
            }
        });
        panels[14].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(14);
                } else {
                    panelRightClicked(14);
                }
            }
        });

        numOfPicturesLabel.setEditable(false);
        numOfPicturesLabel.setBackground(new java.awt.Color(255, 255, 255));
        numOfPicturesLabel.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        numOfPicturesLabel.setText("Pictures: ");
        numOfPicturesLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        numOfPicturesLabel.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        numOfPicturesLabel.setSelectionColor(new java.awt.Color(255, 255, 255));

        exportSizeLabel.setForeground(Color.WHITE);
        exportSizeLabel.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        exportSizeLabel.setText("Export Size:");

        resolutionTextField.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        resolutionTextField.setText("1920");
        resolutionTextField.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        resolutionTextField.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        resolutionTextField.setSelectionColor(new java.awt.Color(255, 255, 255));

        startStopButton.setText("Start/Stop");
        startStopButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                startStopButtonMousePressed(evt);
            }
        });
        jMenuBar1.add(startStopButton);

        exportButton.setText("Export Photos");
        exportButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exportButtonMousePressed(evt);
            }
        });
        jMenuBar1.add(exportButton);

        printTreeButton.setText("Print Tree");
        printTreeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                printTreeButtonMousePressed(evt);
            }
        });
        jMenuBar1.add(printTreeButton);
        
        exitButton.setText("Exit");
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitButtonMousePressed(evt);
            }
        });
        jMenuBar1.add(exitButton);

        setJMenuBar(jMenuBar1);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numOfPicturesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportSizeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resolutionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numOfPicturesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportSizeLabel)
                    .addComponent(resolutionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        
        pack();
    }    
    
    public void tick() {
        if (!running) return;
        
        //System.out.println("tick...");
        
        numOfPictures++;
        numOfPicturesLabel.setText("Pictures: " + numOfPictures);
        
        rma.createNewMathTree();
        BufferedImage image = rma.createPicture(ICON_RESOLUTION);
        PanelIcon icon = new PanelIcon(rma.getMathTree(), image);
        
        PanelIcon pan = icons[icons.length - 1];
        if (pan != null && pan.isSelected()) {
            timer.cancel();
            
            rma.setMathTree(pan.getMathTree());
            BufferedImage i = rma.createPicture(1920);
            
            try {
                RandomMathArt.exportImage(RandomMathArt.getScaledImage(i, 1920, 1080), "picture" + numOfPictures);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            timer = new Timer();
            timer.scheduleAtFixedRate(new Notifier(this), TIMER_LENGTH, TIMER_LENGTH);
        }
        
        for(int i = icons.length - 1; i > 0; i--) {
            icons[i] = icons[i-1];
            icons[i-1] = null;
        }
        icons[0] = icon;
        
        for (int i = 0; i < panels.length; i++) {
            if (icons[i] == null) panels[i].setImage(null);
            else panels[i].setImage(icons[i].getImage());
        }
        
        for (int i = 0; i < panels.length; i++) {
            if (icons[i] != null) {
                if (icons[i].isSelected()) {
                    panels[i].setBorder(SELECTED_PANEL_BORDER);
                } else {
                    panels[i].setBorder(DESELECTED_PANEL_BORDER);
                }
            } else {
                panels[i].setBorder(DESELECTED_PANEL_BORDER);
                
            }
        }
        this.repaint();
        
    }
    
    private void panelLeftClicked(int index) {
        //I dont want ability to delete them, if I acidentally left click a good one
        panelRightClicked(index);
        
        /*
        //delete on left-click code
        if (index < 0 || index > 14) return;
        
        if (icons[index] != null) {
            icons[index] = null;
            panels[index].setImage(null);
            panels[index].setBorder(DESELECTED_PANEL_BORDER);
            panels[index].repaint();
        }
                */
        
    }
    
    private void panelRightClicked(int index) {
        //SELECT IT
        if (index < 0 || index > 14) return;
        
        if (icons[index] != null) {
            if (icons[index].isSelected()) {
                panels[index].setBorder(DESELECTED_PANEL_BORDER);
                icons[index].setSelected(false);
            } else {
                panels[index].setBorder(SELECTED_PANEL_BORDER);
                icons[index].setSelected(true);
            }
            
        }
    }
    
    private int getPanelAt(MouseEvent evt) {
        ImagePanel p = (ImagePanel) mainPanel.getComponentAt(evt.getPoint());
        System.out.println(p.toString());

        return p.getIndex();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        numOfPicturesLabel = new javax.swing.JTextField();
        exportSizeLabel = new javax.swing.JLabel();
        resolutionTextField = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        startStopButton = new javax.swing.JMenu();
        exportButton = new javax.swing.JMenu();
        printTreeButton = new javax.swing.JMenu();
        exitButton = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mathematical Pictures");
        setMinimumSize(new java.awt.Dimension(1044, 600));
        setResizable(false);

        mainPanel.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1046, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 636, Short.MAX_VALUE)
        );

        numOfPicturesLabel.setEditable(false);
        numOfPicturesLabel.setBackground(new java.awt.Color(255, 255, 255));
        numOfPicturesLabel.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        numOfPicturesLabel.setText("Pictures: ");
        numOfPicturesLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        numOfPicturesLabel.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        numOfPicturesLabel.setSelectionColor(new java.awt.Color(255, 255, 255));

        exportSizeLabel.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        exportSizeLabel.setForeground(new java.awt.Color(255, 255, 255));
        exportSizeLabel.setText("Export Size:");

        resolutionTextField.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        resolutionTextField.setText("1920");
        resolutionTextField.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        resolutionTextField.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        resolutionTextField.setSelectionColor(new java.awt.Color(255, 255, 255));

        startStopButton.setText("Start/Stop");
        startStopButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                startStopButtonMousePressed(evt);
            }
        });
        jMenuBar1.add(startStopButton);

        exportButton.setText("Export Photos");
        exportButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exportButtonMousePressed(evt);
            }
        });
        jMenuBar1.add(exportButton);

        printTreeButton.setText("Print Tree");
        printTreeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                printTreeButtonMousePressed(evt);
            }
        });
        jMenuBar1.add(printTreeButton);

        exitButton.setText("Exit");
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitButtonMousePressed(evt);
            }
        });
        jMenuBar1.add(exitButton);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numOfPicturesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportSizeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resolutionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(642, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numOfPicturesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportSizeLabel)
                    .addComponent(resolutionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMousePressed
        System.exit(0);
    }//GEN-LAST:event_exitButtonMousePressed

    private void startStopButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startStopButtonMousePressed
        if (running) {
            running = false;
        } else {
            running = true;
        }
    }//GEN-LAST:event_startStopButtonMousePressed

    private void exportButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportButtonMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_exportButtonMousePressed

    private void printTreeButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printTreeButtonMousePressed
        if (icons != null) {
            for (int i = 0; i < icons.length; i++) {
                if (icons[i] != null) {
                    icons[i].getMathTree().printTrees();
                    return;
                }
            }
        }
    }//GEN-LAST:event_printTreeButtonMousePressed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }
    
    private int numOfPictures;
    private boolean running;
    private ImagePanel[] panels;
    private PanelIcon[] icons;
    private Timer timer;
    private RandomMathArt rma;
    
    private static final Color SELECTED_BORDER_COLOR = new Color(255, 255, 255);
    private static final Color GREY = new Color(90, 90, 90);
    private static final int ICON_RESOLUTION = 200;
    
    private static final int TIMER_LENGTH = 400;
    
    private static final LineBorder SELECTED_PANEL_BORDER = new LineBorder(SELECTED_BORDER_COLOR, 5, false);
    private static final LineBorder DESELECTED_PANEL_BORDER = new LineBorder(GREY, 1, false);
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu exitButton;
    private javax.swing.JMenu exportButton;
    private javax.swing.JLabel exportSizeLabel;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField numOfPicturesLabel;
    private javax.swing.JMenu printTreeButton;
    private javax.swing.JTextField resolutionTextField;
    private javax.swing.JMenu startStopButton;
    // End of variables declaration//GEN-END:variables
}
