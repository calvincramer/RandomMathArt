package randommathart;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//TODO DOCUMENTATION
public class MathTrees {
    
    private Tree[] trees;       //all trees
    private Map<String, Integer> treeNameMap;   //name of tree to integer 
    
    private Random rng;
    
    private static final int MAX_NODES = 5;
    private static final int NUM_NODES_ADD_PER_LOOP = 3;
        
    
    public MathTrees(String... treeNames) {
        rng = new Random(System.currentTimeMillis());
        
        //make map and name map
        this.trees = new Tree[treeNames.length];
        this.treeNameMap = new HashMap<>();
        for (int i = 0; i < treeNames.length; i++) {
            this.treeNameMap.put(treeNames[i], i);
        }
        
        this.generateTrees();
    }
    
    
    /**
     * Generates the entire array of trees
     */
    private void generateTrees() {
        for (int i = 0; i < trees.length; i++) {
            trees[i] = new Tree(Node.getStandardBaseNode(), this);
            generateRandomTree(trees[i]);
        }
    }
    
    
    /**
     * Generates a single tree
     * @param tree the tree to be populated
     */
    private void generateRandomTree(Tree tree) {
        populateTreeAt(tree.getMotherNode(), tree);
    }
    
    
    private void populateTreeAt(Node node, Tree tree) {
        //while (tree.numberOfNodes() < this.maxNodes) {
        boolean done = false;
        //MAGIC NUMBER: 8
        for (int loop = 0; loop < 8 && !done; loop++) {    
            
            
            if (tree.numberOfNodes() <= MathTrees.MAX_NODES) {
                Node[] leaves = tree.getLeaves();
                
                int numberOfNodesToAdd = rng.nextInt(NUM_NODES_ADD_PER_LOOP) + 1;
                
                int whichLeafToAddTo = rng.nextInt(leaves.length);
                //if (rng.nextBoolean()) {
                //    leaves[whichLeafToAddTo].addNode(new Node(MathOp.Random));
                //}
                for (int i = 1; i <= numberOfNodesToAdd; i++) {
                    leaves[whichLeafToAddTo].addChildNode(new Node(getRandomMathOp()));
                }
            }
            
            if (tree.numberOfNodes() > MathTrees.MAX_NODES) {
                done = true;
                Node[] leaves = tree.getLeaves();
                for (Node n : leaves) {
                    
                    int numberOfNodesToAdd = rng.nextInt(NUM_NODES_ADD_PER_LOOP) + 1;
                    
                    for (int i = 1; i <= numberOfNodesToAdd; i++) {
                        n.addChildNode(new Node(getEndMathOp()));
                    }
                }
            }
        }

        while (!lookForFullLeaves(tree.getMotherNode())) {
            Node[] leaves = tree.getLeaves();
            for (Node n : leaves) {

                int numberOfNodesToAdd = rng.nextInt(NUM_NODES_ADD_PER_LOOP) + 1;

                for (int i = 1; i <= numberOfNodesToAdd; i++) {
                    n.addChildNode(new Node(getEndMathOp()));
                }
            }
        }

    }
    
    
    /**
     * Works for all I know
     */
    public boolean lookForFullLeaves (Node node){
        MathOp op = node.getMathOp();
        if (op == MathOp.Base && (node.getChildNodes() == null || node.getChildNodes().size() == 0)) return false;
        
        if(op == MathOp.Avg || op == MathOp.Sin || op == MathOp.Cos) {
            if (node.getChildNodes() == null || node.getChildNodes().size() == 0) {
                return false;
            }
        }
        if (node.getChildNodes() != null && node.getChildNodes().size() > 0) {
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
            case 2: op = MathOp.Pi_Factor; break;
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
            case 3: op = MathOp.Pi_Factor; break;
            case 4: op = MathOp.Random; break;
            default: System.out.println("n = " + n + "   (which isn't good!)"); break;
        }
        return op;
    }
    
    
    /**
     * Gets a tree from an index value
     * @param treeIndex
     * @return 
     */
    public Tree getTree(int treeIndex) {
        if (treeIndex < 0 || treeIndex >= this.trees.length) {
            System.out.println("Tree index is out of bounds");
            return null;
        }
        return this.trees[treeIndex];
    }
    
    
    /**
     * Gets a tree from the tree name
     * Will return null if the treeName is not found in the treeName map
     * @param treeName
     * @return 
     */
    public Tree getTree(String treeName) {
        Integer index = this.treeNameMap.get(treeName);
        if (index == null)
            return null;
        return this.trees[index];
    }
    
    
    /**
     * Sets a tree at the specified index
     * @param treeIndex
     * @param tree
     * @return 
     */
    public boolean setTree(int treeIndex, Tree tree) {
        if (treeIndex < 0 || treeIndex >= this.trees.length) {
            System.out.println("Tree index is out of bounds");
            return false;
        }
        this.trees[treeIndex] = tree;
        return true;
    }
    
    
    /**
     * Sets a tree given the tree name
     * @param treeName
     * @param tree
     * @return 
     */
    public boolean setTree(String treeName, Tree tree) {
        Integer index = this.treeNameMap.get(treeName);
        if (index == null)
            return false;
        return setTree(index, tree);
    }

    
    /**
     * Prints all of the trees associated with this math tree 
     */
    public void printTrees(){
        for (String treeName : this.treeNameMap.keySet()) {
            System.out.println(treeName + " tree: ");
            this.trees[this.treeNameMap.get(treeName)].printTree();
        }
        System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
    }
    
    
    /**
     * gets the double r,g,b and converts it into a int color for the picture?
     * @return 
     */
    public static int getRGB(Tree redTree, Tree greenTree, Tree blueTree, double x, double y) {
        if (redTree == null || greenTree == null || blueTree == null) {
            System.out.println("ONE OR MORE TREES ARE NULL!");
            if (redTree == null) System.out.println("redTree is null");
            if (greenTree == null) System.out.println("greenTree is null");
            if (blueTree == null) System.out.println("blueTree is null");
            System.exit(1);
        }
        double red = redTree.evaluateTree(x, y);
        double green = greenTree.evaluateTree(x, y);
        double blue = blueTree.evaluateTree(x, y);
        
        int r = (int)((red + 1) * (255.0/2));
        int g = (int)((green + 1) * (255.0/2));
        int b = (int)((blue + 1) * (255.0/2));
        int rgb = r;
        rgb = (rgb << 8) + g;
        rgb = (rgb << 8) + b;
        return rgb;
    }
}
