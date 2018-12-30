package randommathart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Holds a list of math terms and expressions, and can evaluate them numerically
 * @author CalvinLaptop
 */
public class MathExpression {
    
    private List<MathTerm> terms;   //reverse polish notation list of terms?
    protected static final double PI = Math.PI;
    public static final int MAX_NON_TERMS = 200;
    private List<Double> stack;     //used for evalaution
    
    
    /**
     * Generates a new random math expression
     */
    public MathExpression(boolean optimize) {
        this.terms = new ArrayList<>();
        this.stack = new ArrayList<>();
        this.generateRandomExpression();
        //TODO: preprocess expression to make actual expression faster
        if (optimize)
            this.optimizeExpression();
    }
    
    
    /**
     * Optimizes the expression to make evaluation the same, but faster
     */
    private void optimizeExpression() {
        if (terms == null || terms.size() <= 1)
            return;
        
        //repetedly check for patterns of NUM {sin|cos}
        //and NUM NUM {+|*}
        
        boolean foundOpt = false;
        do {
            //System.out.println(this);
            
            for (int i = 0; i < terms.size() - 1; i++) {
                //set flag false at start
                foundOpt = false;
                //NUM {sin|cos}
                if (terms.get(i).isNumber() && 
                           (terms.get(i+1).getType() == MathTerm.SIN
                         || terms.get(i+1).getType() == MathTerm.COS
                         || terms.get(i+1).getType() == MathTerm.TAN)) {
                    foundOpt = true;
                    Double num = terms.remove(i).getNumber();
                    MathTerm op = terms.remove(i);
                    Double res = Double.NaN;
                    switch (op.getType()) {
                        case MathTerm.SIN: res = Math.sin(num); break;
                        case MathTerm.COS: res = Math.cos(num); break;
                        case MathTerm.TAN: res = Math.tan(num); break;
                    }
                    terms.add(i, new MathTerm(res));
                    break;
                } 
            }
            for (int i = 0; i < terms.size() - 2; i++) {
                //NUM NUM {+|*}
                if (terms.get(i).isNumber() && terms.get(i + 1).isNumber() && 
                           (terms.get(i+2).getType() == MathTerm.ADD
                         || terms.get(i+2).getType() == MathTerm.MULT)) {
                    foundOpt = true;
                    Double num1 = terms.remove(i).getNumber();
                    Double num2 = terms.remove(i).getNumber();
                    MathTerm op = terms.remove(i);
                    Double res = Double.NaN;
                    switch (op.getType()) {
                        case MathTerm.ADD: res = num1 + num2; break;
                        case MathTerm.MULT: res = num1 * num2; break;
                    }
                    terms.add(i, new MathTerm(res));
                    break;
                }   
            }
        } while (foundOpt);
    }
    
    
    /**
     * Evaluates the expression at the given x and y
     * @return the value of the expression at (x,y)
     */
    public double evaluateExpression(double x, double y) {
        stack.clear(); //should only hold numbers, no operations
        for (MathTerm mt : terms) {
            int type = mt.getType();
            if (mt.isOperation()) {
                Double res = Double.NaN;    //the result of the operation
                if (type == MathTerm.ADD || type == MathTerm.MULT || type == MathTerm.POW) {
                    //pull top two numbers from stack
                    Double term2 = stack.remove(stack.size() - 1);
                    Double term1 = stack.remove(stack.size() - 1);
                    //apply operation
                    switch (type) {
                        case MathTerm.ADD:  res = term1 + term2; break;
                        case MathTerm.MULT: res = term1 * term2; break;
                        case MathTerm.POW:  res = term1 * term2; break;
                    }
                }
                else if (type == MathTerm.SIN || type == MathTerm.COS || type == MathTerm.TAN) {
                    //pull top number from stack
                    Double term = stack.remove(stack.size() - 1);
                    //apply operation
                    switch (type) {
                        case MathTerm.SIN:  res = Math.sin(term); break;
                        case MathTerm.COS:  res = Math.cos(term); break;
                        case MathTerm.TAN:  res = Math.sin(term); break;
                    }
                }
                //add result to stack
                stack.add(res);
            }
            else {
                if (mt.getType() == MathTerm.NUMBER)    stack.add(mt.getNumber());
                else if (mt.getType() == MathTerm.X)    stack.add(x);
                else if (mt.getType() == MathTerm.Y)    stack.add(y);
                else {
                    System.err.println("Got " + mt + " but expected something that is a number");
                }
            }
        }
        
        if (stack.size() == 1) return stack.get(0);
        else {
            System.out.println("BAD RESULT FROM EXPR EVALUATION");
            return Double.NaN;
        }
    }
    
    
    /**
     * Clears the term list, and generates a new random one
     */
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
        terms.clear();
        terms.add(new MathTerm(MathTerm.PLACEHOLDER));
        
        //System.out.println(this);

        //add non-terms
        int num_non_terms_to_add = 2 + RandomMathArt.rng.nextInt(MAX_NON_TERMS);
        for (int n = 1; n <= num_non_terms_to_add; n++) {
            
            List<MathTerm> toAdd = getRandomSmallExpression();
            
            //pick random placeholder index to add toAdd
            List<Integer> spotsToAdd = new ArrayList<>();
            for (int i = 0; i < terms.size(); i++)
                if (terms.get(i).getType() == MathTerm.PLACEHOLDER)
                    spotsToAdd.add(i);
            
            int indexToAdd = spotsToAdd.get(RandomMathArt.rng.nextInt(spotsToAdd.size()));
            terms.remove(indexToAdd);
            terms.addAll(indexToAdd, toAdd);
            
            //System.out.println(this);
        }
        //add terminals
        for (int i = 0; i < terms.size(); i++) {
            if (terms.get(i).getType() == MathTerm.PLACEHOLDER) {
                terms.set(i, MathTerm.getRandomTerminatingTerm());
                //System.out.println(this);
            }
        }
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
    
    
    /**
     * Returns a copy of the terms of this expression
     * @return a copy of the terms of this expression
     */
    public List<MathTerm> getTerms() {
        return new ArrayList<>(this.terms);
    }
    
    
    /**
     * Math expression to string
     * @return 
     */
    @Override 
    public String toString() {
        String s = "[";
        for (MathTerm mt : this.terms)
            s += mt.toString() + " ";
        s = s.substring(0, s.length() - 1) + "]";
        return s;
    }
    
    
    /**
     * Debugging purposes
     * @param args unused
     */
    public static void main(String[] args) {
        MathExpression expr = new MathExpression(false);
        System.out.println("expression at (1,1): " + expr.evaluateExpression(1,1));
    }
}
