package randommathart;

//TODO difference between PanelIcon and ImagePanel?
import java.awt.image.BufferedImage;

public class PanelIcon {
    
    private final BufferedImage image;
    private final MathTrees mathTree;
    private boolean selected;

    
    public PanelIcon(MathTrees mathTree, BufferedImage image) {
        this.mathTree = mathTree;
        this.image = image;
        this.selected = false;
    }
    
    
    public BufferedImage getImage() {
        return image;
    }

    
    public MathTrees getMathTree() {
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
    
    
}
