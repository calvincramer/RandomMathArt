package randommathart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

//TODO DOCUMENTATION
public class MathExpressions {
    
    private MathExpression[] exprs;       //all expressions
    private Map<String, Integer> exprNameMap;   //name of tree to integer 
    
    
    /**
     * Creates a new math math expression for each expression name given
     * @param exprNames 
     */
    public MathExpressions(String... exprNames) {        
        //make map and name map
        this.exprs = new MathExpression[exprNames.length];
        this.exprNameMap = new HashMap<>();
        for (int i = 0; i < exprNames.length; i++) {
            this.exprNameMap.put(exprNames[i], i);
        }
        
        this.generateExprs();
    }
    
    
    /**
     * Generates the entire array of trees
     */
    private void generateExprs() {
        for (int i = 0; i < exprs.length; i++)
            exprs[i] = new MathExpression();
    }
    

    
    /**
     * Gets a expression from an index value
     * @param exprIndex expression index
     * @return the math expression at the index
     */
    public MathExpression getrExpr(int exprIndex) {
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
     * gets the double r,g,b and converts it into a int color for the picture?
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
     * gets the double r,g,b and converts it into a int color for the picture?
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
}
