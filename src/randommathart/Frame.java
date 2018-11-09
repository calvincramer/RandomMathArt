package randommathart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Frame extends JFrame {
    
    private final RandomMathArt rma;//to generate the math pictures
        
    //some colors
    private static final Color SELECTED_BORDER_COLOR = new Color(255, 255, 255);
    private static final Color GRAY = new Color(90, 90, 90);
    
    private int numOfPictures = 0;  //counter variable for the number of picutes generated
    private boolean running;        //is generating new pictures or not
    private Timer timer;            //for the tick to get the next mathpicture every TICK_TIME ms

    private ImagePanel[] panels;    //the 15 panels that show the icons of the math pictures
    private final PanelIcon[] icons;//TODO: what is panels use and icons use? Should they be combined?
    
    private static final LineBorder SELECTED_PANEL_BORDER = new LineBorder(SELECTED_BORDER_COLOR, 5, false);
    private static final LineBorder DESELECTED_PANEL_BORDER = new LineBorder(GRAY, 1, false);

    //important options
    private static final int ICON_RESOLUTION = 200; //the preview icon size in pixels
    private static final int TICK_TIME = 1000;       //clock speed in ms
    
    /**
     * Construct a frame to generate random math art
     */
    public Frame() {
        myInitComponents();
        
        //center frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        int x = (width / 2) - (this.getWidth() / 2);
        int y = (height / 2) - (this.getHeight() / 2);
        this.setBounds(x, y, this.getWidth(), this.getHeight());
        
        //initialize the random math art generator
        rma = new RandomMathArt();
        
        //TODO: purpose of icons?
        icons = new PanelIcon[15];
 
        //start timer
        running = false;
        restartTimer();
        
    }
    
    /**
     * Reinitialized the timer
     */
    private void restartTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override public void run() {
                tick();
            }
        }, 0, TICK_TIME);
    }
    
    /**
     * Initialize the frame components
     */
    private void myInitComponents() {
        mainPanel = new JPanel();
        numOfPicturesLabel = new JTextField();
        exportSizeLabel = new JLabel();
        resolutionTextField = new JTextField();
        menuBar = new JMenuBar();
        startStopButton = new JMenu();
        exportButton = new JMenu();
        printTreeButton = new JMenu();
        exitButton = new JMenu();

        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Random Math Art");
        this.setMinimumSize(new Dimension(1044, 600));
        this.setResizable(false);
        this.getContentPane().setBackground(GRAY);

        FlowLayout mainPanelLayout = new FlowLayout(FlowLayout.CENTER);
        mainPanel.setLayout(mainPanelLayout);
        mainPanel.setBorder(new LineBorder(GRAY, 1, false));
        mainPanel.setMinimumSize(new Dimension (1044, 634));
        mainPanel.setMaximumSize(new Dimension (1044, 634));
        mainPanel.setPreferredSize(new Dimension (1044, 634));
        
        mainPanel.setBackground(GRAY);
        
        panels = new ImagePanel[15];
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new ImagePanel(null, i);
            panels[i].setMinimumSize(new Dimension (200, 200));
            panels[i].setMaximumSize(new Dimension (200, 200));
            panels[i].setPreferredSize(new Dimension (200, 200));
            panels[i].setMinimumSize(new Dimension(200, 200));
            panels[i].setBorder(DESELECTED_PANEL_BORDER);
            
            panels[i].setBackground(GRAY);

            mainPanel.add(panels[i]);
        }
        
        // TODO replace with generic solution
        panels[0].addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(0);
                } else {
                    panelRightClicked(0);
                }
            }
        });
        panels[1].addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(1);
                } else {
                    panelRightClicked(1);
                }
            }
        });
        panels[2].addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(2);
                } else {
                    panelRightClicked(2);
                }
            }
        });
        panels[3].addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(3);
                } else {
                    panelRightClicked(3);
                }
            }
        });
        panels[4].addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(4);
                } else {
                    panelRightClicked(4);
                }
            }
        });
        panels[5].addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(5);
                } else {
                    panelRightClicked(5);
                }
            }
        });
        panels[6].addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(6);
                } else {
                    panelRightClicked(6);
                }
            }
        });
        panels[7].addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(7);
                } else {
                    panelRightClicked(7);
                }
            }
        });
        panels[8].addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(8);
                } else {
                    panelRightClicked(8);
                }
            }
        });
        panels[9].addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(9);
                } else {
                    panelRightClicked(9);
                }
            }
        });
        panels[10].addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(10);
                } else {
                    panelRightClicked(10);
                }
            }
        });
        panels[11].addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(11);
                } else {
                    panelRightClicked(11);
                }
            }
        });
        panels[12].addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(12);
                } else {
                    panelRightClicked(12);
                }
            }
        });
        panels[13].addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == 1) {
                    panelLeftClicked(13);
                } else {
                    panelRightClicked(13);
                }
            }
        });
        panels[14].addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent evt) {
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
            @Override public void mousePressed(java.awt.event.MouseEvent evt) {
                startStopButtonMousePressed(evt);
            }
        });
        menuBar.add(startStopButton);

        exportButton.setText("Export Photos");
        exportButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mousePressed(java.awt.event.MouseEvent evt) {
                exportButtonMousePressed(evt);
            }
        });
        menuBar.add(exportButton);

        printTreeButton.setText("Print Tree");
        printTreeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mousePressed(java.awt.event.MouseEvent evt) {
                printTreeButtonMousePressed(evt);
            }
        });
        menuBar.add(printTreeButton);
        
        exitButton.setText("Exit");
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mousePressed(java.awt.event.MouseEvent evt) {
                exitButtonMousePressed(evt);
            }
        });
        menuBar.add(exitButton);

        setJMenuBar(menuBar);
        
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
    
    /**
     * Main tick to create a new math picture and to shift everything?
     * Automatically exports images that run "off" the screen and are selected
     */
    public void tick() {
        if (!running) 
            return;
        
        //last panel icon, export if it is selected
        PanelIcon pan = icons[icons.length - 1];    
        if (pan != null && pan.isSelected()) {
            timer.cancel();     //stop the time when we are exporting the image, then resume after
            
            rma.setMathTree(pan.getMathTree());
            BufferedImage i = rma.createPicture(1920);      //TODO this should be set by resolutionTextField
            
            //export image
            try {
                RandomMathArt.exportImage(RandomMathArt.getScaledImage(i, 1920, 1080), "picture" + numOfPictures);  //TODO square resolution.
            } catch (IOException ex) {
                System.err.println("Error exporting image");
                ex.printStackTrace();
            }
            
            restartTimer();
        }
        
        //shift all of the icons one forward, except for the 0th icon
        for(int i = icons.length - 1; i > 0; i--) {
            icons[i] = icons[i-1];
            icons[i-1] = null;
        }
        
        //create new random math picture
        rma.createNewMathTree();
        BufferedImage image = rma.createPicture(ICON_RESOLUTION);
        PanelIcon icon = new PanelIcon(rma.getMathTree(), image);
        icons[0] = icon;    //set 0th icon to the new picture
        
        //TODO WHAT ABOUT THESE?
        for (int i = 0; i < panels.length; i++) {
            if (icons[i] == null) panels[i].setImage(null);
            else panels[i].setImage(icons[i].getImage());
        }
        
        //TODO AND THESE?
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
        
        //Update number of pictures generated label
        numOfPictures++;
        numOfPicturesLabel.setText("Pictures: " + numOfPictures);
        
        //update frame to reflect changes
        this.repaint();
    }
    
    /**
     * Left click of a panel event, toggles selection of the panel
     * @param index the index of the panel/icon that was right clicked
     */
    private void panelLeftClicked(int index) {
        togglePanelSelection(index);        
    }
    
    /**
     * Right click of a panel event
     * @param index the index of the panel/icon that was right clicked
     */
    private void panelRightClicked(int index) {
        //TODO: preview of math picture as higher resolution (Issue #6)
        
    }
    
    /**
     * Toggles a panel/icon selected or deselected
     * @param index the index of the panel/icon that was right clicked
     */
    private void togglePanelSelection(int index) {
        //bounds check
        if (index < 0 || index >= panels.length) {
            System.err.println("Cannot toggle selection of panel " + index + " because index is out of range");
            return;
        }  
        //toggle selection
        if (icons[index] != null) {
            if (icons[index].isSelected()) {    //icon/panel is selected, deselect it
                panels[index].setBorder(DESELECTED_PANEL_BORDER);
                icons[index].setSelected(false);
            } else {                            //icon/panel is not selected, select it
                panels[index].setBorder(SELECTED_PANEL_BORDER);
                icons[index].setSelected(true);
            }
        }
    }
    
    /**
     * TODO: INSERT COMMENTS
     * @param evt
     * @return 
     */
    private int getPanelAt(MouseEvent evt) {
        ImagePanel p = (ImagePanel) mainPanel.getComponentAt(evt.getPoint());
        System.out.println(p.toString());

        return p.getIndex();
    }
    
    /**
     * Apparently I used the GUI builder and then copied the code so I was able to change it, so this initComponents is not used.  
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        numOfPicturesLabel = new javax.swing.JTextField();
        exportSizeLabel = new javax.swing.JLabel();
        resolutionTextField = new javax.swing.JTextField();
        menuBar = new javax.swing.JMenuBar();
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
        menuBar.add(startStopButton);

        exportButton.setText("Export Photos");
        exportButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exportButtonMousePressed(evt);
            }
        });
        menuBar.add(exportButton);

        printTreeButton.setText("Print Tree");
        printTreeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                printTreeButtonMousePressed(evt);
            }
        });
        menuBar.add(printTreeButton);

        exitButton.setText("Exit");
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitButtonMousePressed(evt);
            }
        });
        menuBar.add(exitButton);

        setJMenuBar(menuBar);

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

    /**
     * Exit button
     * @param evt the Mouse event passed
     */
    private void exitButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMousePressed
        System.exit(0);
    }//GEN-LAST:event_exitButtonMousePressed

    /**
     * Starts / stops the generating of new math pictures
     * @param evt the Mouse event passed
     */
    private void startStopButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startStopButtonMousePressed
        running = !running;
    }//GEN-LAST:event_startStopButtonMousePressed

    /**
     * TODO: implement the export button (Issue #7)
     * @param evt the Mouse event passed
     */
    private void exportButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportButtonMousePressed
        System.out.println("EXPORT BUTTON DOES NOTHING RIGHT NOW");
    }//GEN-LAST:event_exportButtonMousePressed

    /**
     * Prints the math trees for each of the icons/panels
     * @param evt the Mouse event passed
     */
    private void printTreeButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printTreeButtonMousePressed
        if (icons == null) 
            return;
        
        for (int i = 0; i < icons.length; i++) {
            if (icons[i] != null) {
                icons[i].getMathTree().printTrees();
                return;
            }
        }
    }//GEN-LAST:event_printTreeButtonMousePressed

    /**
     * Starting point
     * @param args no use
     */
    public static void main(String args[]) {
        //TODO why invoke a different thread?
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
                new Frame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu exitButton;
    private javax.swing.JMenu exportButton;
    private javax.swing.JLabel exportSizeLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextField numOfPicturesLabel;
    private javax.swing.JMenu printTreeButton;
    private javax.swing.JTextField resolutionTextField;
    private javax.swing.JMenu startStopButton;
    // End of variables declaration//GEN-END:variables
}
