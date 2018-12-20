package randommathart;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds a list of math terms and expressions, and can evaluate them numerically
 * @author CalvinLaptop
 */
public class MathExpression {
    
    private double x;
    private double y;
    protected static final double PI = Math.PI;
    
    private List<MathTerm> terms;   //reverse polish notation list of terms?
    
    /**
     * Generates a new random math expression
     */
    public MathExpression() {
        this.terms = new ArrayList<>();
        this.generateRandomExpression();
    }
    
    public double evaluateExpression() {
        //DONT USE MATH TERM, JUST A NUMBER TO REPRESENT EACH TERM, except for random num
        return Double.NaN;
    }
    
    private void generateRandomExpression() {
        //TODO
    }
    
    private
    
    //<editor-fold defaultstate="collapsed" desc="Getters, setters">
    public double getX() {
        return x;
    }
    
    
    public void setX(double x) {
        this.x = x;
    }
    
    
    public double getY() {
        return y;
    }
    
    
    public void setY(double y) {
        this.y = y;
    }
    //</editor-fold>
}
