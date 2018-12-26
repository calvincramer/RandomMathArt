package randommathart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Represents an image panel
 * The image is created by the given math expressions
 * The panel can be selected and deselected
 */
public class ImagePanel 
        extends JPanel
        implements Runnable {

    private MathExpressions mathExprs;    //math expressions for the image
    private BufferedImage image;                //image
    private BufferedImage scaledImage;          //scaled down image
    private boolean selected;                   //selected or not

    private static final Color SELECTED_BORDER_COLOR = new Color(255, 255, 255);
    private static final LineBorder BORDER_SELECTED = new LineBorder(SELECTED_BORDER_COLOR, 5, false);
    private static final LineBorder BORDER_DESELECTED = new LineBorder(Color.YELLOW, 1, false);
    
    private State STATE = State.CLEAR;
            
    private enum State { CLEAR, LOADING, CROSS, HAVE_PICTURE }
    
    
    /**
     * Creates an empty image panel
     */
    public ImagePanel() {
        this(null, null, false);
    }
    
    
    /**
     * Constructs an Image panel by creating an image through the math expressions given
     * @param mathExprs math expressions
     */
    public ImagePanel(MathExpressions mathExprs) {
        this(mathExprs, null, false);
    }
    
    
    /**
     * Constructs an Image panel by creating an image through the math expressions given
     * and also starts out selected or not selected
     * @param mathExprs math expressions
     * @param selected selected (true), not selected (false)
     */
    public ImagePanel(MathExpressions mathExprs, boolean selected) {
        this(mathExprs, null, selected);
    }
    
    
    /**
     * Constructs an Image panel with an already created image.
     * @param mathExprs math tree associated with the image
     * @param image already created image
     */
    public ImagePanel(MathExpressions mathExprs, BufferedImage image) {
        this(mathExprs, image, false);
    }
    
    
    /**
     * Constructs an Image panel with an already created image, and selected or not
     * @param mathExprs math tree associated with the image
     * @param image already created image
     * @param selected selected (true), not selected (false)
     */
    public ImagePanel(MathExpressions mathExprs, BufferedImage image, boolean selected) {
        this.mathExprs = mathExprs;
        if (image == null && mathExprs != null) {
            //TODO
            //set image to loading image, or progress bar
            this.setState(State.LOADING);
            startImageCreationThread();
        }
        else {
            this.setState(State.HAVE_PICTURE);
            setImage(image);
        }
        //sets border to desired, also sets the border 
        this.selected = selected;
        this.setBorder(this.selected ? BORDER_SELECTED : BORDER_DESELECTED);
        //set mouse listener
        this.addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {    //hopefully left click
                    toggleSelected();
                } else {
                    toggleSelected();   //to be used for right clicks, if want different action in future
                }
            }
        });
        this.setOpaque(false);
    }
   
    
    /**
     * Starts construction of the image in another thread
     */
    private void startImageCreationThread() {
        Thread creationThread = new Thread(this, "image creation thread");
        creationThread.start();
        try {
            creationThread.join();      //don't want to use join, because it gets rid of all benefits of multithreading
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    
    /**
     * Sets the image of this panel, and also sets a scaled image of size GUI2.ACTUAL_ICON_RESOLUTION
     * @param image image to use
     */
    public synchronized void setImage(BufferedImage image) {
        this.setImage(image, null);
    }
    
    
    /**
     * Sets the image of this panel, and also sets a scaled image of size GUI2.ACTUAL_ICON_RESOLUTION
     * @param image image to use
     * @param scaledImage scaled image of the image
     */
    public synchronized void setImage(BufferedImage image, BufferedImage scaledImage) {
        this.image = image;
        this.scaledImage = scaledImage;
        if (image != null)
            this.scaledImage = RandomMathArt.getScaledImage(image, GUI2.ACTUAL_ICON_RESOLUTION, GUI2.ACTUAL_ICON_RESOLUTION);
        this.setState((image == null) ? State.CLEAR : State.HAVE_PICTURE);
    }

    
    /**
     * Returns the maths expressions associated with the image
     * @return the maths expressions associated with the image
     */
    public MathExpressions getMathExpressions() {
        return mathExprs;
    }

    
    /**
     * Returns whether the panel is selected
     * @return whether the panel is selected
     */
    public boolean isSelected() {
        return selected;
    }

    
    /**
     * Sets whether this panel is selected
     * @param selected selected (true), not selected (false)
     */
    public void setSelected(boolean selected) {
        if (this.selected == selected) 
            return;  //don't call setBorder if nothing will change
        
        this.selected = selected;
        this.setBorder(this.selected ? BORDER_SELECTED : BORDER_DESELECTED);
    }
    
    
    /**
     * Toggles the selection of the panel
     */
    public void toggleSelected() {
        if (this.image == null && this.scaledImage == null)
            this.setSelected( false );
        else
            this.setSelected( !this.selected );
    }

    
    //TODO: can do antialiasing here?
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        switch (this.STATE) {
            case CLEAR: return;
            case LOADING: 
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
                break;
            case CROSS: {
                g.setColor(Color.RED);
                g.drawLine(0, 0, this.getWidth(), this.getHeight());
                g.drawLine(0, this.getHeight(), this.getWidth(), 0);
                break;
            }
            case HAVE_PICTURE: {
                if (scaledImage != null)    g.drawImage(scaledImage, 0, 0, null);  
                else if (image != null)     g.drawImage(image, 0, 0, null);
                else {} //default no picture picture
                break;
            }
            default: {
                System.err.println("bad image panel state");
                break;
            }
        }
    }
    
    
    /**
     * Replaces all of the data of this with the data from other
     * @param other 
     */
    public void replaceWith(ImagePanel other) {
        this.setImage(other.image, other.scaledImage);
        this.mathExprs = other.mathExprs;
        this.setSelected(other.selected);
    }

    
    /**
     * Create image from math expressions
     */
    @Override
    public void run() {
        //System.out.println("running thread!");
        
        if (this.image != null) {
            //System.out.println("already have image, stopping thead");
            return;
        }
        
        this.setState(State.LOADING);
        
        if (this.mathExprs != null) {
            //System.out.println("creating image in thread");
            BufferedImage image = RandomMathArt.createPicture(mathExprs, GUI2.ICON_RESOLUTION);
            this.setImage(image);
            this.repaint();
        }
        
        this.setState(State.HAVE_PICTURE);
        
        //System.out.println("done running thread\n");
    }
    
    
    /**
     * Sets the state of this image panel
     * Thread safe
     * @param state new state to be in 
     */
    private synchronized void setState(ImagePanel.State state) {
        this.STATE = state;
    }
}