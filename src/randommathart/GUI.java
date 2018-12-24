package randommathart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class GUI extends JFrame {
            
    //colors
    protected static final Color GRAY = new Color(90, 90, 90);
    
    private int numOfPictures = 0;  //counter variable for the number of picutes generated
    private boolean running;        //is generating new pictures or not
    private Timer timer;            //for the tick to get the next mathpicture every TICK_TIME ms

    private JPanel[] panels;    //the 15 panels that show the icons of the math pictures

    //important options
    protected static final int ACTUAL_ICON_RESOLUTION = 200;  //the size of the icon ON THE SCREEN
    protected static final int ICON_RESOLUTION = 10;         //the size of the generate picture
    protected static final int TICK_TIME = 2000;                //clock speed in ms
    protected static final int NUM_PANELS = 15;       
    
    private boolean spacebarPressed = false;
    
    
    /**
     * Construct a frame to generate random math art
     */
    public GUI() {
        myInitComponents();
        
        //center the frame
        centerFrame();
 
        //start timer
        running = false;
        restartTimer();
    }
    
    
    /**
     * Centers the frame in the window
     */
    private void centerFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        int x = (width / 2) - (this.getWidth() / 2);
        int y = (height / 2) - (this.getHeight() / 2);
        this.setBounds(x, y, this.getWidth(), this.getHeight());
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
        this.setResizable(true);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        this.getContentPane().setBackground(GRAY);
        
        //keyboard listener
        this.addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                keyPressedEvent(e);
            }
            @Override public void keyReleased(KeyEvent e) {
                keyReleasedEvent(e);
            }
        });

        FlowLayout mainPanelLayout = new FlowLayout(FlowLayout.CENTER);
        mainPanel.setLayout(mainPanelLayout);
        mainPanel.setBorder(new LineBorder(GRAY, 1, false));
        mainPanel.setMinimumSize(new Dimension (1044, 634));
        mainPanel.setMaximumSize(new Dimension (1044, 634));
        mainPanel.setPreferredSize(new Dimension (1044, 634));
        
        mainPanel.setBackground(GRAY);
        
        panels = new JPanel[NUM_PANELS];
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new ImagePanel(null);
            panels[i].setLayout(new BorderLayout());
            panels[i].setMinimumSize(new Dimension (200, 200));
            panels[i].setMaximumSize(new Dimension (200, 200));
            panels[i].setPreferredSize(new Dimension (200, 200));
            panels[i].setMinimumSize(new Dimension(200, 200));
            
            panels[i].setBackground(GRAY);

            mainPanel.add(panels[i]);
            
            panels[i].add(new ImagePanel(), BorderLayout.CENTER);
        }

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

        startStopButton.setText("Start");
        startStopButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mousePressed(java.awt.event.MouseEvent evt) {
                startStopButtonMousePressed(evt);
            }
        });
        menuBar.add(startStopButton);

        /*
        exportButton.setText("Export Photos");
        exportButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mousePressed(java.awt.event.MouseEvent evt) {
                exportButtonMousePressed(evt);
            }
        });
        menuBar.add(exportButton);
        */
        
        /*
        printTreeButton.setText("Print Tree");
        printTreeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mousePressed(java.awt.event.MouseEvent evt) {
                printTreeButtonMousePressed(evt);
            }
        });
        menuBar.add(printTreeButton);
        */
        
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
     * Main tick to create a new math picture and to shift everything
     * Automatically exports images that run "off" the screen and are selected
     */
    private void tick() {
        if (!running) 
            return;
        
        //check if last panel is selected
        //start export if selected
        ImagePanel lastPanel = (ImagePanel) panels[panels.length - 1].getComponent(0);
        if (lastPanel != null && lastPanel.isSelected() && lastPanel.getMathExpressions() != null) {
            //stop the time when we are exporting the image, then resume after
            timer.cancel();     
            //generate desired resolution image
            BufferedImage img = RandomMathArt.createPicture(lastPanel.getMathExpressions(), getExportResolution());
            //export image
            RandomMathArt.exportImage(img, "picture" + numOfPictures);
            //start timer again
            restartTimer();
        }
        
        //shift all of the icons one forward, except for the 0th icon
        for(int i = panels.length - 1; i > 0; i--) {
            panels[i].removeAll();
            if (panels[i-1].getComponents().length > 0)
                panels[i].add(panels[i-1].getComponents()[0]);
        }
        
        //create new random math picture
        MathExpressions newMathExprs = RandomMathArt.createNewMathExprs();
        BufferedImage image = RandomMathArt.createPicture(newMathExprs, ICON_RESOLUTION);
        ImagePanel firstImagePanel = new ImagePanel(newMathExprs, image);
        //place beginning image
        panels[0].removeAll();
        panels[0].add(firstImagePanel);     //set 0th icon to the new picture
        
        //Update number of pictures generated label
        numOfPictures++;
        numOfPicturesLabel.setText("Pictures: " + numOfPictures);
        
        //update frame to reflect changes
        //TODO VISUAL BUG NOT SHOWING NEWEST PICTURES UNLESS WE PRESS MOUSE
        this.repaint();
        this.getContentPane().repaint();
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="Boring stuff">
    /**
     * For when any button is pressed down
     * @param e the KeyEvent
     */
    private void keyPressedEvent(KeyEvent e) {
        //check if spacebar pressed
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (this.spacebarPressed == false) {
                running = !running;
                startStopButton.setText(running ? "Stop" : "Start");
            }
            this.spacebarPressed = true;
        }
    }

    
    /**
     * For when any button is released
     * @param e the KeyEvent
     */
    private void keyReleasedEvent(KeyEvent e) {
        //check if spacebar released
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            this.spacebarPressed = false;
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
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

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
        if (panels == null) 
            return;
        
        for (int i = 0; i < panels.length; i++) {
            if (panels[i] != null) {
                ((ImagePanel) (panels[i].getComponents()[0])).getMathExpressions().printExpressions();
                return;
            }
        }
    }//GEN-LAST:event_printTreeButtonMousePressed

    
    /**
     * Currently has no functionality
     * @param evt unused
     */
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    
    /**
     * Gets the export resolution from the text field
     * @return the number in the text field, or 1920 if the text is not a number
     */
    private int getExportResolution() {
        String s = this.resolutionTextField.getText();
        try {
            this.resolutionTextField.setBorder(new LineBorder(Color.BLACK, 1));
            return Integer.parseInt(s);
        }
        catch (NumberFormatException e) {
            this.resolutionTextField.setBorder(new LineBorder(Color.RED, 2));
            return 1920;
        }
    }
    //</editor-fold>
    
    
    /**
     * Starting point
     * @param args no use
     */
    public static void main(String args[]) {
        //TODO why invoke a different thread?
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
                new GUI().setVisible(true);
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
