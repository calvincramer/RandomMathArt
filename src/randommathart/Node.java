package randommathart;

import java.util.ArrayList;

public class Node {
    
    public Node(Tree tree, Node parentNode, MathOp op) {
        this.tree = tree;
        this.parentNode = parentNode;
        this.op = op;
        this.childNodes = new Node[10];
    }
    
    public Node(MathOp op) {
        this.op = op;
        this.tree = null;
        this.parentNode = null;
        this.childNodes = new Node[10];
    }
    
    public void addNode(Node n) {
        if (childNodes == null) {
            System.out.println("CHILD NODES IS NULL YOU DUMMY");
            System.exit(0);
        }
        //see if need to expand array
        if (this.getNumberOfChildren() >= childNodes.length) {
            Node[] expandedArray = new Node[childNodes.length * 2];
            for (int i = 0; i < childNodes.length; i++) {
                expandedArray[i] = childNodes[i];
            }
            childNodes = expandedArray;
        }
        
        int index = 0;
        while (index < childNodes.length && childNodes[index] != null) {
            index++;
        }
        childNodes[index] = n;
        n.setParentNode(this);
        n.setTree(this.tree); 
    }
    
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
    
    public void removeAllNodes() {
        if (childNodes != null) {
            childNodes = new Node[0];
        }
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public Node[] getChildNodes() {
        int numFull = 0;
        for (Node n : childNodes) {
            if (n != null) numFull++;
        }
        Node[] nodes = new Node[numFull];
        for (int i = 0; i < numFull; i++) {
            nodes[i] = childNodes[i];
        }
        
        return nodes;
    }

    public Node getNode(int index) {
        if (index < 0 || index > childNodes.length - 1) {
            System.out.println("INDEX OUT OF BOUNDS : index = " + index + "  length of childNodex = " + childNodes.length);
        }
        return childNodes[index];
    }
    
    public void setOtherNodes(Node[] otherNodes) {
        this.childNodes = otherNodes;
    }
    
    public double evaluate() {
        
        if (op == MathOp.X) return tree.getX();
        if (op == MathOp.Y) return tree.getY();  
        if (op == MathOp.Pi)return RandomMathArt.pi;
           
        if (op == MathOp.Avg) {
            double sumTotal = 0;
            for (Node node : this.getChildNodes()) {
                sumTotal += node.evaluate();
            }
            return (sumTotal / childNodes.length);
        }
        
        double total = 1;
        for (Node node : this.getChildNodes()) {
            total *= node.evaluate();
        }
        
        if(op == MathOp.Sin) return Math.sin(total);
        if(op == MathOp.Cos) return Math.cos(total);
        
        
        return total;
    }
    
    public MathOp getMathOp() {
        return op;
    }

    public Tree getTree() {
        return tree;
    }

    public boolean isLeaf() {
        return getNumberOfChildren() == 0;
    }
    
    public int getNumberOfChildren() {
        int children = 0;
        for (Node n : childNodes) {
            if (n != null) children++;
        }
        return children;
    }
    
    public void setTree(Tree tree) {
        this.tree = tree;
    }
    
    /**
     * works
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
    
    public boolean hasChildren() {
        for (int i = 0; i < childNodes.length; i++) {
            if (childNodes[i] != null) return true;
        }
        return false;
    }
    
    /**
     * Returns a string representation of this node
     */
    @Override
    public String toString() {
        String s = "[" + op + ", PARENT: ";
        s += parentNode == null ? "null" : parentNode.getMathOp();
        if (childNodes != null && childNodes.length > 0 && this.hasChildren()) {
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
    
    public String mathFunctionToString() {
        return "" + op;
    }
    
    public static Node getStandardBaseNode() {
        return new Node(null, null, MathOp.Base);
    }
    
    private Tree tree;
    private MathOp op; 
    private Node parentNode;
    private Node[] childNodes;
}
