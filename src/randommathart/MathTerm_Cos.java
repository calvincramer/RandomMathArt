package randommathart;

/**
 * Represents Cos(expr)
 * @author CalvinLaptop
 */
public class MathTerm_Cos extends MathTerm{
    private MathExpression expr;
    
    public MathTerm_Cos(MathExpression expr) {
        this.expr = expr;
    }

    @Override
    public double evaluate() {
        return Math.cos(expr.evaluateExpression());
    }
}
