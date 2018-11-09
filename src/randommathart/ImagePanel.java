package randommathart;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

//TODO difference between PanelIcon and ImagePanel?
public class ImagePanel extends JPanel{

    private BufferedImage image;
    private int index;

    public ImagePanel(BufferedImage image, int index) {
       this.image = image;
       this.index = index;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getIndex() {
        return index;
    }

    //TODO  can do antialiasing here?
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) { 
            g.drawImage(image, 0, 0, null);
        }    
    }

}