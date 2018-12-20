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
        //overview of design:
        //generate all the non-terminating terms first, keep placeholders for terminating terms
        //at each step when adding non-terminating terms, choose a placeholder for where to add
        //then place all terminating terms in
        
        //start with _ _ +
        //then _ _ * _ +
        //then _ _ * _ sin +
        
        //placeholder = MathTerm.PLACEHOLDER
    }
    
    
    /**
     * Returns a list of either _ _ +, _ _ *, _ sin, _ cos, _ tan, _ _ pow
     * where _ is MathTerm.PLACEHOLDER
     * @return a list of either _ _ +, _ _ *, _ sin, _ cos, _ tan, _ _ pow
     */
    private List<MathTerm> getRandomSmallExpression() {
        List<MathTerm> temp = new ArrayList<>();
        MathTerm rand_non_term = MathTerm.getRandomNonTerminatingTerm();
        int type = rand_non_term.getType();
        
        //add placeholders
        if (type == MathTerm.SIN || type == MathTerm.COS || type == MathTerm.TAN) {
            temp.add(new MathTerm(MathTerm.PLACEHOLDER));
        }
        else {
            temp.add(new MathTerm(MathTerm.PLACEHOLDER));
            temp.add(new MathTerm(MathTerm.PLACEHOLDER));
        }
        
        temp.add(rand_non_term);
        return temp;
    }
    
    
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
    
    
    public void setCoord(double x, double y) {
        this.x = x;
        this.y = y;
    }
    //</editor-fold>
}
