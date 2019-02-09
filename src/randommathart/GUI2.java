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
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

public class GUI2 extends JFrame {

    // Colors
    protected static final Color GRAY = new Color(90, 90, 90);
    // Total number of pictures created
    private int numOfPictures = 0;
    // Generating new pictures or not
    private boolean running;
    // Repeating timer to create a new math picture
    private Timer timer;
    // The 15 panels that show the icons of the math pictures
    private ImagePanel[] panels;

    // The size of the icon ON THE SCREEN
    protected static final int ACTUAL_ICON_RESOLUTION = 200;
    // Ihe size of the generate picture
    protected static final int ICON_RESOLUTION = 20;
    // Clock speed in ms
    protected static final int TICK_TIME = 2000;
    protected static final int NUM_PANELS = 15;
    private   static final int INITIAL_EXPORT_SIZE = 1000;

    private boolean spacebarPressed = false;

    private JMenu exitButton;
    private JMenu exportButton;
    private JLabel exportSizeLabel;
    private JPanel mainPanel;
    private JMenuBar menuBar;
    private JTextField numOfPicturesLabel;
    private JMenu printTreeButton;
    private JTextField resolutionTextField;
    private JMenu startStopButton;

    /**
     * Construct a frame to generate random math art
     */
    public GUI2() {
        myInitComponents();
        centerFrame();
        // Start timer
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
     * Reinitialize the timer
     */
    private void restartTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override public void run() { tick(); }
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

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Random Math Art GUI2 ");
        this.setMinimumSize(new Dimension(1044, 600));
        this.setResizable(true);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        this.getContentPane().setBackground(GRAY);

        // Keyboard listener
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

        panels = new ImagePanel[NUM_PANELS];
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new ImagePanel(null);
            panels[i].setMinimumSize(new Dimension (200, 200));
            panels[i].setMaximumSize(new Dimension (200, 200));
            panels[i].setPreferredSize(new Dimension (200, 200));
            panels[i].setMinimumSize(new Dimension(200, 200));

            panels[i].setBackground(GRAY);

            mainPanel.add(panels[i]);
        }

        numOfPicturesLabel.setEditable(false);
        numOfPicturesLabel.setBackground(new java.awt.Color(255, 255, 255));
        numOfPicturesLabel.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        numOfPicturesLabel.setText("Pictures: ");
        numOfPicturesLabel.setBorder(BorderFactory.createEtchedBorder());
        numOfPicturesLabel.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        numOfPicturesLabel.setSelectionColor(new java.awt.Color(255, 255, 255));

        exportSizeLabel.setForeground(Color.WHITE);
        exportSizeLabel.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        exportSizeLabel.setText("Export Size:");

        resolutionTextField.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        resolutionTextField.setText("" + INITIAL_EXPORT_SIZE);
        resolutionTextField.setBorder(BorderFactory.createEtchedBorder());
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

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numOfPicturesLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportSizeLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resolutionTextField, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(numOfPicturesLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportSizeLabel)
                    .addComponent(resolutionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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

        ImagePanel lastPanel = panels[NUM_PANELS - 1];
        // Check if last panel is selected
        // TODO: make this check into a method
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

        // Shift all of the icons one forward, except for the 0th icon
        for(int i = panels.length - 1; i > 0; i--)
            panels[i].replaceWith(panels[i-1]);

        // Setting constant seed for testing
        RandomMathArt.setRandomSeed(1234L);
        // Create new random math picture
        MathExpressions newMathExprs = RandomMathArt.createNewMathExprs();
        //BufferedImage image = RandomMathArt.createPicture(newMathExprs, ICON_RESOLUTION);
        ImagePanel newFirstImagePanel = new ImagePanel(newMathExprs);
        // Place beginning image
        panels[0].replaceWith(newFirstImagePanel);

        // Update the number of pictures generated label
        numOfPictures++;
        numOfPicturesLabel.setText("Pictures: " + numOfPictures);

        // Update frame to reflect changes
        this.repaint();
    }


    //<editor-fold defaultstate="collapsed" desc="Boring stuff">
    /**
     * For when any button is pressed down
     * @param e the KeyEvent
     */
    private void keyPressedEvent(KeyEvent e) {
        // Check if spacebar pressed
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
        // Check if spacebar released
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            this.spacebarPressed = false;
    }


    /**
     * Exit button
     * @param evt the Mouse event passed
     */
    private void exitButtonMousePressed(java.awt.event.MouseEvent evt) {
        // TODO: check if any image is being saved, join with the threads that are
        System.exit(0);
    }


    /**
     * Starts / stops the generating of new math pictures
     * @param evt the Mouse event passed
     */
    private void startStopButtonMousePressed(java.awt.event.MouseEvent evt) {
        running = !running;
    }


    /**
     * TODO: implement the export button (Issue #7)
     * @param evt the Mouse event passed
     */
    private void exportButtonMousePressed(java.awt.event.MouseEvent evt) {
        System.out.println("EXPORT BUTTON DOES NOTHING RIGHT NOW");
    }


    /**
     * Prints the math trees for each of the icons/panels
     * @param evt the Mouse event passed
     */
    private void printTreeButtonMousePressed(java.awt.event.MouseEvent evt) {
        if (panels == null)
            return;

        for (int i = 0; i < panels.length; i++) {
            if (panels[i] != null) {
                ((ImagePanel) (panels[i].getComponents()[0])).getMathExpressions().printExpressions();
                return;
            }
        }
    }


    /**
     * Currently has no functionality
     * @param evt unused
     */
    private void formKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
    }


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
     * @param args not used
     */
    public static void main(String args[]) {
        new GUI2().setVisible(true);
    }
}
