package randommathart;

/**
 * Represents expr1 + expr2
 * @author CalvinLaptop
 */
public class MathTerm_Add extends MathTerm {
    private MathTerm term1;
    private MathTerm term2;
    
    public MathTerm_Add(MathTerm term1, MathTerm term2) {
        this.term1 = term1;
        this.term2 = term2;
    }
    
    @Override
    public double evaluate() {
        return term1.evaluate() + term2.evaluate();
    }
    
}
