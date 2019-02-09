package randommathart;

/**
 * Represents a single math term
 * @author CalvinLaptop
 */
public class MathTerm {
    // Terminating types
    public static final int NUMBER = 0;
    public static final int X      = 1;
    public static final int Y      = 2;
    // Non-terminating types
    public static final int ADD    = 3;
    public static final int MULT   = 4;
    public static final int SIN    = 5;
    public static final int COS    = 6;
    public static final int TAN    = 7;
    public static final int POW    = 8;
    // Only used when generating a random expression
    public static final int PLACEHOLDER = 300; 
    
    public static final int MAX_TYPES = 9;
    
    private double num; //Used only if type is NUMBER
    private int type;   //Stores the type of the term
        
    
    /**
     * Constructs a term given the type
     * @param typeOfTerm the type
     */
    public MathTerm(int typeOfTerm) {
        this.type = typeOfTerm;
        this.num = Double.NaN;
    }
    
    
    /**
     * Constructs a term that is a number
     * @param num the number
     */
    public MathTerm(double num) {
        this.num = num;
        this.type = NUMBER;
    }
    
    
    /**
     * Returns the number for this term
     * @return number will be NaN if type != NUMBER
     */
    public double getNumber() {
        return this.num;
    }
    
    
    /**
     * Returns the type of this term
     * @return the type of this term
     */
    public int getType() {
        return this.type;
    }
    
    
    /**
     * Determines whether the type is an operator or not
     * @return 
     */
    public boolean isOperation() {
        return type == ADD || type == MULT || type == SIN 
                || type == COS || type == TAN || type == POW;
    }
    
    
    /**
     * Returns true iff type is NUMBER
     * @return true iff type is NUMBER
     */
    public boolean isNumber() {
        return type == NUMBER;
    }
    
    
    /**
     * Returns a random term
     * @return Returns a random term
     */
    public static MathTerm getRandomTerm() {
        int rand_type = RandomMathArt.rng.nextInt(MAX_TYPES);
        if (rand_type != 0)
            return new MathTerm(rand_type);
        else {
            double rand_num = RandomMathArt.rng.nextDouble() * Math.PI;
            return new MathTerm(rand_num);
        }
    }
    
    
    /**
     * Returns either NUMBER, X, or Y
     * @return Returns either NUMBER, X, or Y
     */
    public static MathTerm getRandomTerminatingTerm() {
        int rand_type = RandomMathArt.rng.nextInt(3); //can be num, X, or Y
        if (rand_type != 0)
            return new MathTerm(rand_type);
        else {
            double rand_num = RandomMathArt.rng.nextDouble() * Math.PI;
            return new MathTerm(rand_num);
        }
    }
    
    
    /**
     * Returns a random non-terminating math term
     * A non terminating math term is either +, *, sin, cos, tan, or pow
     * @return Returns a random non-terminating math term
     */
    public static MathTerm getRandomNonTerminatingTerm() {
        // Needs to be in range [3,8] inclusive
        int rand_type = RandomMathArt.rng.nextInt(8 - 3 + 1) + 3;     
        return new MathTerm(rand_type);
    }
    
    
    /**
     * String representation of a math term
     * @return 
     */
    @Override
    public String toString() {
        switch (this.type) {
            case NUMBER:    return String.format("%.5f", num);
            case X:         return "X";
            case Y:         return "Y";
            case ADD:       return "+";
            case MULT:      return "*";
            case SIN:       return "sin";
            case COS:       return "cos";
            case TAN:       return "tan";
            case POW:       return "^";
            case PLACEHOLDER: return "_";
            default:        return "UNRECOGNIZED TYPE";
        }
    }
}
