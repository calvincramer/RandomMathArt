package randommathart;

import java.util.Random;

/**
 * Represents a random number
 * @author CalvinLaptop
 */
public class MathTerm_Number extends MathTerm{

    double number;
    
    public MathTerm_Number() {
        //generate this random number once
        Random rng = new Random(System.currentTimeMillis());
        this.number = Math.PI / (rng.nextInt(10) + 1);
    }
    
    @Override
    public double evaluate() {
        return number;
    }
    
}
