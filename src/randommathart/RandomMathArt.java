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

    private MathExpressions mt;
    
    
    /**
     * For debugging, creased a random image and outputs it to desktop
     * @param args unused
     */
    public static void main(String[] args) {
        String desktopDir = System.getProperty("user.home") + "/Desktop/";
        RandomMathArt rma = new RandomMathArt();
        System.out.print("Enter a number (1 quits): ");
        
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        
        while (num != 1) {
            rma.createNewMathTree();
            rma.getMathTree().printExpressions();
            BufferedImage image = rma.createPicture(1000);
            try {
                File outputfile = new File(desktopDir + "testingPicture" + num + ".png");
                ImageIO.write(image, "png", outputfile);
                System.out.println("OUTPUTED IMAGE SUCCESSFULLY!\nInput another number: (1 quits): ");

            } catch (IOException e) {
                e.printStackTrace();
            }
            num = in.nextInt();
        }
        
    }
    
    
    public void createNewMathTree() {
        mt = new MathExpressions("red", "green", "blue");
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
                
                int rgb = MathExpressions.getRGB(mt.getExpr("red"), mt.getExpr("green"), mt.getExpr("blue"), xCoord, yCoord);                
                image.setRGB(x, y, rgb);

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
    
    
    public MathExpressions getMathTree() {
        return mt;
    }

    
    public void setMathTree(MathExpressions mathTree) {
        this.mt = mathTree;
    }
}
