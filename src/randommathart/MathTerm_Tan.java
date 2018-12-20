package randommathart;

/**
 * Represents Tan(expr)
 * @author CalvinLaptop
 */
public class MathTerm_Tan extends MathTerm{
    private MathExpression expr;
    
    public MathTerm_Tan(MathExpression expr) {
        this.expr = expr;
    }

    @Override
    public double evaluate() {
        return Math.tan(expr.evaluateExpression());
    }
}
