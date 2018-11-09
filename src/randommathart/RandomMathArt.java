package randommathart;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

//TODO DOCUMENTATION
public class RandomMathArt {

    private MathTree mathTree;
    protected static final double PI = 3.141592653589793238;
      
    /**
     * Another start point, which start point should we use?
     * TODO: CHOOSE START POINT
     * @param args 
     */
    public static void main(String[] args) {
        RandomMathArt rma = new RandomMathArt();
        System.out.println("Enter a number (1 quits)");
        
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        
        while (num != 1) {
            rma.createNewMathTree();
            rma.getMathTree().printTrees();
            BufferedImage i = rma.createPicture(1000);
            try {
                File outputfile = new File("C:\\Users\\Calvin Cramer\\Desktop\\" + "testingPicture.png");
                ImageIO.write(i, "png", outputfile);

            } catch (IOException e) {
                e.printStackTrace();
            }
            num = in.nextInt();
        }
        
    }
    
    public RandomMathArt() {
        //nuthing? 
        
    }
    
    public void createNewMathTree() {
        mathTree = new MathTree();
    }
    
    public BufferedImage createPicture(int resolution) {
        if (mathTree == null) {
            throw new NullPointerException("MATHTREE IS NULL YOU DUMMY!");
        }
        if (resolution < 0) 
            resolution = 200;
        
        BufferedImage image = new BufferedImage(resolution, resolution, BufferedImage.OPAQUE);

        double xCoord;
        double yCoord;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                xCoord = ((x * 1.0/resolution) * 2) - 1;
                yCoord = ((y * 1.0/resolution) * 2) - 1;
                
                mathTree.setCoord(xCoord, yCoord);
                image.setRGB(x, y, mathTree.getRGB());
            }
        }
        
        return image;
    }
    
    public static void exportImage(BufferedImage image, String imageName) {
        try {
            File test = new File("");
            
            //File outputfile = new File("C:\\Users\\Calvin Cramer\\Desktop\\Math Pictures\\" + imageName + ".png");
            File outputfile = new File(test.getAbsolutePath() + imageName + ".png");
            System.out.println(outputfile.getAbsolutePath());
            ImageIO.write(image, "png", outputfile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
    
    public static BufferedImage getScaledImage(BufferedImage image, int width, int height) throws IOException {
        int imageWidth  = image.getWidth();
        int imageHeight = image.getHeight();

        double scaleX = (double)width/imageWidth;
        double scaleY = (double)height/imageHeight;
        AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
        AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);

        return bilinearScaleOp.filter(
            image,
            new BufferedImage(width, height, image.getType()));
    }
    
    public MathTree getMathTree() {
        return mathTree;
    }

    public void setMathTree(MathTree mathTree) {
        this.mathTree = mathTree;
    }
    
    private void debugStuff() {
        MathTree mathTree = new MathTree();
        Tree redTestTree = new Tree(Node.getStandardBaseNode(), mathTree);
        redTestTree.getMotherNode().addNode(new Node(MathOp.Cos));
        redTestTree.getMotherNode().addNode(new Node(MathOp.Sin));
        redTestTree.getMotherNode().addNode(new Node(MathOp.Avg));
        redTestTree.getMotherNode().getNode(0).addNode(new Node(MathOp.X));
        redTestTree.getMotherNode().getNode(0).addNode(new Node(MathOp.Y));
        redTestTree.getMotherNode().getNode(1).addNode(new Node(MathOp.Pi));
        redTestTree.getMotherNode().getNode(2).addNode(new Node(MathOp.X));
        redTestTree.getMotherNode().getNode(2).addNode(new Node(MathOp.Y));
        redTestTree.getMotherNode().getNode(2).addNode(new Node(MathOp.Pi));
        redTestTree.getMotherNode().getNode(2).addNode(new Node(MathOp.Pi));
        redTestTree.getMotherNode().getNode(0).getNode(0).addNode(new Node(MathOp.Sin));
        redTestTree.getMotherNode().getNode(0).getNode(0).addNode(new Node(MathOp.Y));
        redTestTree.getMotherNode().getNode(0).getNode(0).getNode(0).addNode(new Node(MathOp.X));
        Node n = new Node(MathOp.X);
        redTestTree.getMotherNode().getNode(0).getNode(0).getNode(0).addNode(n);
                
        
        mathTree.setRedTree(redTestTree);
        System.out.println("RED TEST TREE: ");
        mathTree.getRedTree().printTree();
        
        System.out.println();
        
        Node[] firstChildren = mathTree.getRedTree().getMotherNode().getChildNodes();
        System.out.println("Children of motherNode:");
        for (Node node : firstChildren) {
            System.out.println("   " + node.toString());
        }
        System.out.println("Leaves are full? : " + mathTree.lookForFullLeaves(redTestTree.getMotherNode()));
        System.out.println("Mothernode a leaf? " + mathTree.getRedTree().getMotherNode().isLeaf());
        System.out.println("Number of nodes: "  + mathTree.getRedTree().numberOfNodes());
        Node[] leaves = mathTree.getRedTree().getLeaves();
        System.out.println("Number of leaves: " + leaves.length);
        /*
        System.out.println("Leaves: ");
        for (Node node : leaves) {
            System.out.println("   " + node.toString());
        }
        */
    }
}
