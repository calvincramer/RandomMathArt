package randommathart;

/**
 * Represents expr ^ num
 * @author CalvinLaptop
 */
public class MathTerm_Pow extends MathTerm {
    private MathExpression expr;
    private double num;
    
    public MathTerm_Pow(MathExpression expr, double num) {
        this.expr = expr;
        this.num = num;
    }

    @Override
    public double evaluate() {
        return Math.pow(expr.evaluateExpression(), num);
    }
    
}
