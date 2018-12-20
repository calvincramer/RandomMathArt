package randommathart;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;

//TODO DOCUMENTATION
public class RandomMathArt {

    private MathTrees mt;
    protected static final double PI = 3.141592653589793238;
      
    /**
     * For debugging, creased a random image and outputs it to desktop
     * @param args unused
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
    
    public void createNewMathTree() {
        mt = new MathTrees("red", "green", "blue");
    }
    
    public BufferedImage createPicture(int resolution) {
        if (mt == null) {
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
                
                image.setRGB(x, y, 
                        MathTrees.getRGB(mt.getTree("red"), mt.getTree("green"), mt.getTree("blue"), xCoord, yCoord)
                );
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
    
    public MathTrees getMathTree() {
        return mt;
    }

    public void setMathTree(MathTrees mathTree) {
        this.mt = mathTree;
    }
    
    private void debugStuff() {
        MathTrees mathTree = new MathTrees("r");
        Tree redTestTree = new Tree(Node.getStandardBaseNode(), mathTree);
        redTestTree.getMotherNode().addChildNode(new Node(MathOp.Cos));
        redTestTree.getMotherNode().addChildNode(new Node(MathOp.Sin));
        redTestTree.getMotherNode().addChildNode(new Node(MathOp.Avg));
        redTestTree.getMotherNode().getNode(0).addChildNode(new Node(MathOp.X));
        redTestTree.getMotherNode().getNode(0).addChildNode(new Node(MathOp.Y));
        redTestTree.getMotherNode().getNode(1).addChildNode(new Node(MathOp.Pi_Factor));
        redTestTree.getMotherNode().getNode(2).addChildNode(new Node(MathOp.X));
        redTestTree.getMotherNode().getNode(2).addChildNode(new Node(MathOp.Y));
        redTestTree.getMotherNode().getNode(2).addChildNode(new Node(MathOp.Pi_Factor));
        redTestTree.getMotherNode().getNode(2).addChildNode(new Node(MathOp.Pi_Factor));
        redTestTree.getMotherNode().getNode(0).getNode(0).addChildNode(new Node(MathOp.Sin));
        redTestTree.getMotherNode().getNode(0).getNode(0).addChildNode(new Node(MathOp.Y));
        redTestTree.getMotherNode().getNode(0).getNode(0).getNode(0).addChildNode(new Node(MathOp.X));
        Node n = new Node(MathOp.X);
        redTestTree.getMotherNode().getNode(0).getNode(0).getNode(0).addChildNode(n);
                
        
        mathTree.setTree("r", redTestTree);
        System.out.println("RED TEST TREE: ");
        mathTree.getTree("r").printTree();
        
        System.out.println();
        
        List<Node> firstChildren = mathTree.getTree("r").getMotherNode().getChildNodes();
        System.out.println("Children of motherNode:");
        for (Node node : firstChildren) {
            System.out.println("   " + node.toString());
        }
        System.out.println("Leaves are full? : " + mathTree.lookForFullLeaves(redTestTree.getMotherNode()));
        System.out.println("Mothernode a leaf? " + mathTree.getTree("r").getMotherNode().isLeaf());
        System.out.println("Number of nodes: "  + mathTree.getTree("r").numberOfNodes());
        Node[] leaves = mathTree.getTree("r").getLeaves();
        System.out.println("Number of leaves: " + leaves.length);
        /*
        System.out.println("Leaves: ");
        for (Node node : leaves) {
            System.out.println("   " + node.toString());
        }
        */
    }
}
