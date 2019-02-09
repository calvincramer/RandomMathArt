package randommathart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

//TODO DOCUMENTATION
public class MathExpressions {
    // All expressions
    private MathExpression[] exprs;
    // Map of the name of tree to an integer
    private Map<String, Integer> exprNameMap;


    /**
     * Creates a new math math expression for each expression name given
     * @param exprNames
     */
    public MathExpressions(boolean optimize, String... exprNames) {
        // Make map and name map
        this.exprs = new MathExpression[exprNames.length];
        this.exprNameMap = new HashMap<>();
        for (int i = 0; i < exprNames.length; i++) {
            this.exprNameMap.put(exprNames[i], i);
        }

        this.generateExprs(optimize);
    }


    /**
     * Generates the entire array of trees
     */
    private void generateExprs(boolean optimize) {
        for (int i = 0; i < exprs.length; i++)
            exprs[i] = new MathExpression(optimize);
    }



    /**
     * Gets a expression from an index value
     * @param exprIndex expression index
     * @return the math expression at the index
     */
    public MathExpression getExpr(int exprIndex) {
        if (exprIndex < 0 || exprIndex >= this.exprs.length) {
            System.out.println("Expression index is out of bounds");
            return null;
        }
        return this.exprs[exprIndex];
    }


    /**
     * Gets a expression from the expression name
     * Will return null if the exprName is not found in the exprNameMap
     * @param exprName expression name
     * @return the math expression
     */
    public MathExpression getExpr(String exprName) {
        Integer index = this.exprNameMap.get(exprName);
        if (index == null)
            return null;
        return this.exprs[index];
    }


    /**
     * Prints all of the expressions associated with this collection
     */
    public void printExpressions(){
        for (String exprName : this.exprNameMap.keySet()) {
            System.out.println(exprName + " expr: ");
            System.out.println(this.exprs[this.exprNameMap.get(exprName)]);
        }
        System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
    }


    /**
     * Gets the double r,g,b and converts it into a int color for the picture?
     * @return
     */
    public static int getRGB(MathExpression redExpr, MathExpression greenExpr, MathExpression blueExpr, double x, double y) {
        if (redExpr == null || greenExpr == null || blueExpr == null) {
            System.out.println("ONE OR MORE EXPRESSIONS ARE NULL!");
            if (redExpr == null) System.out.println("redExpr is null");
            if (greenExpr == null) System.out.println("greenExpr is null");
            if (blueExpr == null) System.out.println("blueExpr is null");
            System.exit(1);
        }

        double red = redExpr.evaluateExpression(x, y);
        double green = greenExpr.evaluateExpression(x, y);
        double blue = blueExpr.evaluateExpression(x, y);

        int r = (int)((red + 1) * (255.0/2));
        int g = (int)((green + 1) * (255.0/2));
        int b = (int)((blue + 1) * (255.0/2));
        int rgb = r;
        rgb = (rgb << 8) + g;
        rgb = (rgb << 8) + b;
        return rgb;
    }


    /**
     * Gets the double r,g,b and converts it into a int color for the picture?
     * @return
     */
    public static int[] getRGBArr(MathExpression redExpr, MathExpression greenExpr, MathExpression blueExpr, double x, double y) {
        if (redExpr == null || greenExpr == null || blueExpr == null) {
            System.out.println("ONE OR MORE EXPRESSIONS ARE NULL!");
            if (redExpr == null) System.out.println("redExpr is null");
            if (greenExpr == null) System.out.println("greenExpr is null");
            if (blueExpr == null) System.out.println("blueExpr is null");
            System.exit(1);
        }
        double red = redExpr.evaluateExpression(x, y);
        double green = greenExpr.evaluateExpression(x, y);
        double blue = blueExpr.evaluateExpression(x, y);

        int r = (int)((red + 1) * (255.0/2));
        int g = (int)((green + 1) * (255.0/2));
        int b = (int)((blue + 1) * (255.0/2));
        return new int[]{r, g, b};
    }


    public static void main(String[] args) {
        RandomMathArt.setRandomSeed(1234L);
        MathExpressions unop = new MathExpressions(false, "red", "green", "blue");
        System.out.println("unop size: " + unop.getExpr(0).getTerms().size());

        RandomMathArt.setRandomSeed(1234L);
        MathExpressions opti = new MathExpressions(true, "red", "green", "blue");
        System.out.println("opti size: " + opti.getExpr(0).getTerms().size());

        // Test how long it takes for optimized / unoptomized evaluation
        final int MAX = 200;
        final int REPEAT = 10;
        final int TOTAL_CALLS = MAX * MAX * REPEAT;

        System.out.println("Dimensions of `image`: " + MAX + " x " + MAX);
        System.out.println("");


        long startUnop = System.nanoTime();
        for (int r = 1; r <= REPEAT; r++) {
        for (int x = 0; x < MAX; x++) {
        for (int y = 0; y < MAX; y++) {
            double eval = MathExpressions.getRGB(unop.getExpr("red"), unop.getExpr("green"), unop.getExpr("blue"), x, y);
        }}}

        double total = (System.nanoTime() - startUnop) * 1.0 / 1000000;
        double avgTotal = total / REPEAT;
        double avgCall = total / TOTAL_CALLS;
        System.out.println("Unop: ");
        System.out.println(" total: " + total + " ms");
        System.out.println(" avg total: " + avgTotal + " ms");
        System.out.println(" method call avg: " + avgCall + " ms");
        System.out.println("");

        long startOpti = System.currentTimeMillis();
        for (int r = 1; r <= REPEAT; r++) {
        for (int x = 0; x <= MAX; x++) {
        for (int y = 0; y <= MAX; y++) {
            double eval = MathExpressions.getRGB(opti.getExpr("red"), opti.getExpr("green"), opti.getExpr("blue"), x, y);
        }}}

        total = System.currentTimeMillis() - startOpti;
        avgTotal = total / REPEAT;
        avgCall = total / TOTAL_CALLS;
        System.out.println("Opti: ");
        System.out.println(" total: " + total + " ms");
        System.out.println(" avg total: " + avgTotal + " ms");
        System.out.println(" method call avg: " + avgCall + " ms");
        System.out.println("");

    }
}
