package randommathart;

//TODO DOCUMENTATION

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Node {
    
    private static Random rng = new Random(System.currentTimeMillis());
    //private Tree tree;
    private Node parentNode;
    private List<Node> childNodes;
    private MathOp op; 
    
    
    /**
     * Constructs a node
     * @param parentNode the parent to this node
     * @param op a math operation associated with this node
     */
    public Node(Node parentNode, MathOp op) {
        this.parentNode = parentNode;
        this.op = op;
        this.childNodes = new ArrayList<>();
    }
    
    
    /**
     * Constructs a node
     * @param op 
     */
    public Node(MathOp op) {
        this(null, op);
    }
    
    
    /**
     * Gets the value of the nodes at a specific point (x,y)
     * @param x (x,y)
     * @param y (x,y)
     * @return the value of the nodes at a specific point (x,y)
     */
    public double evaluate(double x, double y) {
        
        if (op == MathOp.X) return x;
        if (op == MathOp.Y) return y;  
        if (op == MathOp.Pi_Factor)return RandomMathArt.PI;
        if (op == MathOp.Random)    return Math.PI / (rng.nextInt(5) + 1);
        if (op == MathOp.Avg) {
            double sumTotal = 0;
            for (Node node : this.getChildNodes()) {
                sumTotal += node.evaluate(x, y);
            }
            return (sumTotal / childNodes.size());
        }
        
        double total = 1;
        for (Node node : this.getChildNodes()) {
            total *= node.evaluate(x, y);
        }
        
        if(op == MathOp.Sin) return Math.sin(total);
        if(op == MathOp.Cos) return Math.cos(total);
        
        return total;
    }
    
    
    /**
     * Finds the base node by traveling up the parent path
     * @return the most parent parent
     */
    public Node getBaseNode() {
        if (this.parentNode == null) return this;
        Node currentNode = this;
        while (currentNode != null) {
            if (currentNode.getParentNode() == null) return currentNode;
            else currentNode = currentNode.getParentNode();
        }
        System.out.println("Error in getBaseNode() didn't find base node");
        return null;
    }
   
    
    /**
     * Calculates the level of this node
     * @return the level of this node (0 is the base level)
     */
    public int getDepth() {
        if (this.getParentNode() == null) {
            return 0;
        }
        
        int depth = 0;
        Node currentNode = this;
        while (currentNode.getParentNode() != null) {
            currentNode = currentNode.getParentNode();
            depth++;
        }
        return depth;
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="Print functions">
    /**
     * Returns a string representation of this node
     * @return String representation of the node
     */
    @Override
    public String toString() {
        String s = "[" + op + ", PARENT: ";
        s += parentNode == null ? "null" : parentNode.getMathOp();
        if (childNodes != null && childNodes.size() > 0 && this.hasChildren()) {
            s += ", CHILDREN: ";
            for (Node n : childNodes) {
                if (n != null) s += n.getMathOp() + ", ";
            }
            s = s.substring(0, s.length() - 2);
        } else {
            s += ", LEAF";
        }
        s += "]";
        return s;
    }
    
    
    /**
     * Prints the nodes recursively
     */
    public void printNodes() {
        int depth = this.getDepth();
        String spacing = "";
        for (int i = 0; i < depth; i++) {
            spacing += "   ";
        }
        System.out.println(spacing + this.toString());
        for (Node n : childNodes) {
            if (n != null) {
                n.printNodes();
            }
        }
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Boring getters, setters">
    /**
     * Adds a child node
     * @param n the node to add
     */
    public void addChildNode(Node n) {
        childNodes.add(n);
        n.setParentNode(this);
        //n.setTree(this.tree); 
    }
    
    
    /**
     * Determines whether this node has chidren or not
     * @return true if there are more than 0 children
     */
    public boolean hasChildren() {
        return childNodes.size() != 0;
    }
    
    
    /**
     * Gives number of children
     * @return number of children
     */
    public int getNumberOfChildren() {
        return childNodes.size();
    }
    
    
    /**
     * Returns the math operation associated with this node
     * @return the math op
     */
    public MathOp getMathOp() {
        return op;
    }

    
    /**
     * Determines whether this node is a leaf node 
     * @return true iff a leaf node
     */
    public boolean isLeaf() {
        return getNumberOfChildren() == 0;
    }
    
    
    /**
     * Returns the parent node associated with this node
     * @return the parent node
     */
    public Node getParentNode() {
        return parentNode;
    }
    
    
    /**
     * Sets the parent node
     * @param parentNode the parent node
     */
    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }
    
    
    /**
     * Returns the child nodes
     * @return the child nodes
     */
    public List<Node> getChildNodes() {
        return childNodes;
    }
    
    
    /**
     * Returns the node at a specified index
     * @param index the index
     * @return the node at a specified index
     */
    public Node getNode(int index) {
        if (index < 0 || index >= childNodes.size()) {
            System.err.println("INDEX OUT OF BOUNDS");
            return null;
        }
        return childNodes.get(index);
    }
    
    
    /**
     * A standard node without a parent
     * @return 
     */
    public static Node getStandardBaseNode() {
        return new Node(null, MathOp.Base);
    }
    //</editor-fold>
}
