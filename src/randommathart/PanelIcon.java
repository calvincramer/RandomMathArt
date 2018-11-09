package randommathart;

import java.awt.image.BufferedImage;

public class PanelIcon {
    
    public PanelIcon(MathTree mathTree, BufferedImage image) {
        this.mathTree = mathTree;
        this.image = image;
        this.selected = false;
    }
    
    public BufferedImage getImage() {
        return image;
    }

    public MathTree getMathTree() {
        return mathTree;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    @Override
    public String toString() {
        return "I";
    }
    
    private final BufferedImage image;
    private final MathTree mathTree;
    private boolean selected;

}
