package randommathart;

import java.util.ArrayList;

public class Tree {
    
    
    public Tree(Node motherNode, MathTree mathTree) {
        this.motherNode = motherNode;
        this.motherNode.setTree(this);
        this.mathTree = mathTree;
    }

    public Node getMotherNode() {
        return motherNode;
    }

    public void setMotherNode(Node motherNode) {
        this.motherNode = motherNode;
    }
    
    public double getX() {
        return mathTree.getX();
    }
    
    public double getY() {
        return mathTree.getY();
    }
    
    public double evaluateTree() {
        return motherNode.evaluate();
    }
    
    public void printTree() {
        motherNode.printNodes();
    }
    
    public int numberOfNodes() {
        //subtract the motherNode, as it doesnt effect anything
        return numberOfNodes(motherNode) - 1;
    }
    
    private int numberOfNodes(Node n) {
        int num = 0;
        for (Node node : n.getChildNodes()) {
            num += numberOfNodes(node);
        }
        return num + 1;
    }
    
    public Node[] getLeaves() {
        ArrayList<Node> leaves = getLeaves(motherNode, new ArrayList<Node>());
        return leaves.toArray(new Node[leaves.size()]);
    }
    
    private ArrayList<Node> getLeaves(Node n, ArrayList<Node> leavesSoFar) {
        if (n.isLeaf()) {
            leavesSoFar.add(n);
            return leavesSoFar;
        }
        for (Node node : n.getChildNodes()) {
            getLeaves(node, leavesSoFar);
        }
        return leavesSoFar;
    }
    
    private Node motherNode;
    private MathTree mathTree;
}
