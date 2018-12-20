package randommathart;

/**
 * Represents a single math term
 * @author CalvinLaptop
 */
public class MathTerm {
    //random num
    //pi / integer
    //sin(term), cos(term), tan(term)
    //pow(term, num)
    
    public static final int RANDOM = 0;
    public static final int PI_TERM = 1;
    public static final int SIN = 2;
    public static final int COS = 3;
    public static final int TAN = 4;
    public static final int POW = 5;
    public static final int X   = 6;
    public static final int Y   = 7;
    
    private int state = -1;
    
    public MathTerm(int mathTerm) {
        if (mathTerm < 0 || mathTerm > 7) {
            System.out.println("BAD MATH TERM");
        }
        this.state = mathTerm;
    }
    
    public double evaluate(double x, double y) {
        
    }
}
