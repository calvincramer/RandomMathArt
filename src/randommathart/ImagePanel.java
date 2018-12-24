package randommathart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Represents an image panel
 * The image is created by the given math expressions
 * The panel can be selected and deselected
 */
public class ImagePanel extends JPanel{

    private final MathExpressions mathExprs;    //math expressions for the image
    private BufferedImage image;                //image
    private BufferedImage scaledImage;          //scaled down image
    private boolean selected;                   //selected or not

    private static final Color SELECTED_BORDER_COLOR = new Color(255, 255, 255);
    private static final LineBorder BORDER_SELECTED = new LineBorder(SELECTED_BORDER_COLOR, 5, false);
    private static final LineBorder BORDER_DESELECTED = new LineBorder(GUI.GRAY, 1, false);
    
    
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
        if (image == null) {
            //TODO
            //set image to loading image, or progress bar
            
            startImageCreationThread();
        }
        else {
            setImage(image);
        }
        //sets border to desired, also sets the border 
        this.setSelected(selected);
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
        //TODO
    }

    
    /**
     * Sets the image of this panel, and scaled image size
     * @param image image to use
     */
    public void setImage(BufferedImage image) {
        this.image = image;
        if (image != null)
            this.scaledImage = RandomMathArt.getScaledImage(image, GUI.ACTUAL_ICON_RESOLUTION, GUI.ACTUAL_ICON_RESOLUTION);
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
        this.setSelected( !this.selected );
    }

    
    //TODO: can do antialiasing here?
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (scaledImage != null)    g.drawImage(scaledImage, 0, 0, null);  
        else if (image != null)     g.drawImage(image, 0, 0, null);
    }
}