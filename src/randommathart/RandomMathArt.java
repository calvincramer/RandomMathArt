package randommathart;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;

//TODO DOCUMENTATION
public class RandomMathArt {

    private MathTrees mt;
    
    
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
}
