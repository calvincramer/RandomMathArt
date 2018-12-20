package randommathart;

/**
 * Represents sin(expr)
 * @author CalvinLaptop
 */
public class MathTerm_Sin extends MathTerm{
    private MathExpression expr;
    
    public MathTerm_Sin(MathExpression expr) {
        this.expr = expr;
    }

    @Override
    public double evaluate() {
        return Math.sin(expr.evaluateExpression());
    }
}
