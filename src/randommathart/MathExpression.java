package randommathart;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds a list of math terms and expressions, and can evaluate them numerically
 * @author CalvinLaptop
 */
public class MathExpression {
    // Reverse polish notation list of terms
    private List<MathTerm> terms;
    // Used for evalaution
    private List<Double> stack;
    protected static final double PI = Math.PI;
    public static final int MAX_NON_TERMS = 200;


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

        // Repetedly check for patterns of NUM {sin|cos}
        // and NUM NUM {+|*}
        boolean foundOpt = false;
        do {
            for (int i = 0; i < terms.size() - 1; i++) {
                // Set flag false at start
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
                Double result = Double.NaN;
                if (type == MathTerm.ADD || type == MathTerm.MULT || type == MathTerm.POW) {
                    // Pull top two numbers from stack
                    Double term2 = stack.remove(stack.size() - 1);
                    Double term1 = stack.remove(stack.size() - 1);
                    // Apply operation
                    switch (type) {
                        case MathTerm.ADD:  result = term1 + term2; break;
                        case MathTerm.MULT: result = term1 * term2; break;
                        case MathTerm.POW:  result = term1 * term2; break;
                    }
                }
                else if (type == MathTerm.SIN || type == MathTerm.COS || type == MathTerm.TAN) {
                    // Pull top number from stack
                    Double term = stack.remove(stack.size() - 1);
                    // Apply operation
                    switch (type) {
                        case MathTerm.SIN:  result = Math.sin(term); break;
                        case MathTerm.COS:  result = Math.cos(term); break;
                        case MathTerm.TAN:  result = Math.sin(term); break;
                    }
                }
                // Add result to stack
                stack.add(result);
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
            // TODO: Proper error handling
            System.err.println("BAD RESULT FROM EXPR EVALUATION");
            return Double.NaN;
        }
    }


    /**
     * Clears the term list, and generates a new random one
     * Overview of design:
     * Generate all the non-terminating terms first, keep placeholders for
     * terminating terms at each step when adding non-terminating terms,
     * choose a placeholder for where to add then place all terminating terms in
     */
    private void generateRandomExpression() {
        // Match _ _ +
        // then _ _ * _ +
        // then _ _ * _ sin +

        terms.clear();
        terms.add(new MathTerm(MathTerm.PLACEHOLDER));

        //add non-terms
        int num_non_terms_to_add = 2 + RandomMathArt.rng.nextInt(MAX_NON_TERMS);
        for (int n = 1; n <= num_non_terms_to_add; n++) {
            List<MathTerm> toAdd = getRandomSmallExpression();

            // Pick random placeholder index to add toAdd
            List<Integer> spotsToAdd = new ArrayList<>();
            for (int i = 0; i < terms.size(); i++)
                if (terms.get(i).getType() == MathTerm.PLACEHOLDER)
                    spotsToAdd.add(i);

            int indexToAdd = spotsToAdd.get(RandomMathArt.rng.nextInt(spotsToAdd.size()));
            terms.remove(indexToAdd);
            terms.addAll(indexToAdd, toAdd);
        }

        // Add terminals
        for (int i = 0; i < terms.size(); i++) {
            if (terms.get(i).getType() == MathTerm.PLACEHOLDER) {
                terms.set(i, MathTerm.getRandomTerminatingTerm());
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

        // Add placeholders
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
