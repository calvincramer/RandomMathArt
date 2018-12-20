package randommathart;

import java.util.Random;

//TODO generalize to any number of trees
//TODO DOCUMENTATION
public class MathTree {
    
    private double x;   //what do even x and y do?
    private double y;
    
    private Tree redTree;
    private Tree greenTree;
    private Tree blueTree;
    private Tree[] trees;   //TODO IMPLEMENT ANY NUMBER OF TREES, user of class can say how many trees they want and what they map to (CMY, RGBA, etc)
    
    private Random rng;
    
    private static final int MAX_NODES = 5;
    private static final int NUM_NODES_ADD_PER_LOOP = 3;
        
    public MathTree() {
        this.generateTrees();
        rng = new Random(System.currentTimeMillis());
    }
    
    private void generateTrees() {
        redTree = new Tree(Node.getStandardBaseNode(), this);
        greenTree = new Tree(Node.getStandardBaseNode(), this);
        blueTree = new Tree(Node.getStandardBaseNode(), this);
        generateRandomTree(redTree);
        generateRandomTree(greenTree);
        generateRandomTree(blueTree);
    }
    
    private void generateRandomTree(Tree tree) {
        populateTreeAt(tree.getMotherNode(), tree);
    }
    
    private void populateTreeAt(Node node, Tree tree) {
        rng = new Random(); 
        //while (tree.numberOfNodes() < this.maxNodes) {
        boolean done = false;
        //MAGIC NUMBER: 8
        for (int loop = 0; loop < 8 && !done; loop++) {    
            
            
            if (tree.numberOfNodes() <= MathTree.MAX_NODES) {
                Node[] leaves = tree.getLeaves();
                
                int numberOfNodesToAdd = rng.nextInt(NUM_NODES_ADD_PER_LOOP) + 1;
                
                int whichLeafToAddTo = rng.nextInt(leaves.length);
                //if (rng.nextBoolean()) {
                //    leaves[whichLeafToAddTo].addNode(new Node(MathOp.Random));
                //}
                for (int i = 1; i <= numberOfNodesToAdd; i++) {
                    leaves[whichLeafToAddTo].addNode(new Node(getRandomMathOp()));
                }
            }
            
            if (tree.numberOfNodes() > MathTree.MAX_NODES) {
                done = true;
                Node[] leaves = tree.getLeaves();
                for (Node n : leaves) {
                    
                    int numberOfNodesToAdd = rng.nextInt(NUM_NODES_ADD_PER_LOOP) + 1;
                    
                    for (int i = 1; i <= numberOfNodesToAdd; i++) {
                        n.addNode(new Node(getEndMathOp()));
                    }
                }
            }
        }

        while (!lookForFullLeaves(tree.getMotherNode())) {
            Node[] leaves = tree.getLeaves();
            for (Node n : leaves) {

                int numberOfNodesToAdd = rng.nextInt(NUM_NODES_ADD_PER_LOOP) + 1;

                for (int i = 1; i <= numberOfNodesToAdd; i++) {
                    n.addNode(new Node(getEndMathOp()));
                }
            }
        }

    }
    
    /**
     * Works for all I know
     */
    public boolean lookForFullLeaves (Node node){
        MathOp op = node.getMathOp();
        if (op == MathOp.Base && (node.getChildNodes() == null || node.getChildNodes().length == 0)) return false;
        
        if(op == MathOp.Avg || op == MathOp.Sin || op == MathOp.Cos) {
            if (node.getChildNodes() == null || node.getChildNodes().length == 0) {
                return false;
            }
        }
        if (node.getChildNodes() != null && node.getChildNodes().length > 0) {
            boolean result = true;
            for (Node n : node.getChildNodes()) {
                if (n != null) {
                    if( lookForFullLeaves(n) && result) result = true;
                    else result = false;
                }
            }
            return result;
        }
        return true;
    }
    
    public MathOp getEndMathOp() {
        int n = rng.nextInt(4);
        MathOp op = MathOp.X;
        switch (n) {
            case 0: op = MathOp.Y; break;
            case 1: op = MathOp.X; break;
            case 2: op = MathOp.Pi; break;
            case 3: op = MathOp.Random; break;
            default: System.out.println("n = " + n + "   (which isn't good!)"); break;
        }
        return op;
    }
    
    
    //TODO : replace with percent weights for each category
    public MathOp getRandomMathOp() {
        int n = rng.nextInt(5);
        MathOp op = MathOp.X;
        switch (n) {
            case 0: op = MathOp.Sin; break;
            case 1: op = MathOp.Cos; break;
            case 2: op = MathOp.Avg; break;
            case 3: op = MathOp.Pi; break;
            case 4: op = MathOp.Random; break;
            default: System.out.println("n = " + n + "   (which isn't good!)"); break;
        }
        return op;
    }
    
    public Tree getRedTree() {
        if (redTree == null) {
            System.out.println("RED TREE IS NULL YOU DUMMY");
            System.exit(0);
        }
        return redTree;
    }

    public void setRedTree(Tree redTree) {
        this.redTree = redTree;
    }

    public Tree getGreenTree() {
        if (greenTree == null) {
            System.out.println("GREEN TREE IS NULL YOU DUMMY");
            System.exit(0);
        }
        return greenTree;
    }

    public void setGreenTree(Tree greenTree) {
        this.greenTree = greenTree;
    }

    public Tree getBlueTree() {
        if (blueTree == null) {
            System.out.println("BLUE TREE IS NULL YOU DUMMY");
            System.exit(0);
        }
        return blueTree;
    }

    public void setBlueTree(Tree blueTree) {
        this.blueTree = blueTree;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public void setCoord(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * gets the double r,g,b and converts it into a int color for the picture?
     * @return 
     */
    public int getRGB() {
        if (redTree == null || greenTree == null || blueTree == null) {
            System.out.println("ONE OR MORE TREES ARE NULL!");
            if (redTree == null) System.out.println("redTree is null");
            if (greenTree == null) System.out.println("greenTree is null");
            if (blueTree == null) System.out.println("blueTree is null");
            System.exit(1);
        }
        double red = redTree.evaluateTree();
        double green = greenTree.evaluateTree();
        double blue = blueTree.evaluateTree();
        
        int r = (int)((red + 1) * (255.0/2));
        int g = (int)((green + 1) * (255.0/2));
        int b = (int)((blue + 1) * (255.0/2));
        int rgb = r;
        rgb = (rgb << 8) + g;
        rgb = (rgb << 8) + b;
        return rgb;
    }
    
    public void printTrees(){
        System.out.println("Red tree: ");
        redTree.printTree();
        System.out.println("Green tree: ");
        greenTree.printTree();
        System.out.println("Blue tree: ");
        blueTree.printTree();
        System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
    }
}
