package randommathart;

//TODO difference between PanelIcon and ImagePanel?
import java.awt.image.BufferedImage;

public class PanelIcon {
    
    private final BufferedImage image;
    private final MathExpressions mathExpressions;
    private boolean selected;

    
    public PanelIcon(MathExpressions mathTree, BufferedImage image) {
        this.mathExpressions = mathTree;
        this.image = image;
        this.selected = false;
    }
    
    
    public BufferedImage getImage() {
        return image;
    }

    
    public MathExpressions getMathExpressions() {
        return mathExpressions;
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
