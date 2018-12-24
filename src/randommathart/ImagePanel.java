package randommathart;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

//TODO difference between PanelIcon and ImagePanel?
public class ImagePanel extends JPanel{

    private BufferedImage image;    //image
    private BufferedImage scaledImage;
    
    
    public ImagePanel(BufferedImage image) {
        setImage(image);
    }

    
    /**
     * Sets the image of this panel
     * @param image image to use
     */
    public void setImage(BufferedImage image) {
        this.image = image;
        if (image != null)
            this.scaledImage = RandomMathArt.getScaledImage(image, GUI.ACTUAL_ICON_RESOLUTION, GUI.ACTUAL_ICON_RESOLUTION);
    }

    
    //TODO: can do antialiasing here?
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (scaledImage != null)    g.drawImage(scaledImage, 0, 0, null);  
        else if (image != null)     g.drawImage(image, 0, 0, null);
    }

}