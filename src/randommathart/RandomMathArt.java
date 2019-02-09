package randommathart;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;

// TODO DOCUMENTATION
public class RandomMathArt {

    protected static Random rng = new Random(System.currentTimeMillis());

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
            MathExpressions exprs = rma.createNewMathExprs();
            exprs.printExpressions();
            BufferedImage image = RandomMathArt.createPicture(exprs, 1000);
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


    /**
     * Creates a random MathExpressions, with three expressions for red, green, and blue.
     * @return a random MathExpressions
     */
    public static MathExpressions createNewMathExprs() {
        long start = System.currentTimeMillis();
        MathExpressions toReturn = new MathExpressions(true, "red", "green", "blue");
        System.out.println("Time create new math exprs: " + (System.currentTimeMillis() - start) + " ms");
        return toReturn;
    }


    /**
     * Creates an image of a given resolution (square) centered around the
     * origin (0,0) with a window of (-1,-1) to (1,1) in the math world that the
     * given math expressions work in
     * @param exprs math expression input, must have red, green, and blue expressions
     * @param resolution desired resolution, set to 200 if given non-positive
     * @return image of exprs
     */
    public static BufferedImage createPicture(MathExpressions exprs, int resolution) {
        long start = System.nanoTime();
        long nanoGetRBG = 0;
        if (exprs == null) {
            throw new NullPointerException("MATHEXPRESSIONS IS NULL YOU DUMMY!");
        }
        if (resolution < 0)
            resolution = 200;

        BufferedImage image = new BufferedImage(resolution, resolution, BufferedImage.OPAQUE);
        WritableRaster raster = image.getRaster();

        MathExpression redExpr = exprs.getExpr("red");
        MathExpression grnExpr = exprs.getExpr("green");
        MathExpression bluExpr = exprs.getExpr("blue");
        if (redExpr == null || grnExpr == null || bluExpr == null)
            throw new NullPointerException("Need to have 'red', 'green', and 'blue' math expressions");

        //int[] pixels = new int[raster.getWidth() * raster.getHeight() * 3];
        //int grnBandOffset = raster.getWidth() * raster.getHeight();
        //int bluBandOffset = grnBandOffset * 2;

        double xCoord;
        double yCoord;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                xCoord = ((x * 1.0/resolution) * 2) - 1;
                yCoord = ((y * 1.0/resolution) * 2) - 1;

                long nanoRBGStart = System.nanoTime();
                int rgb = MathExpressions.getRGB(redExpr, grnExpr, bluExpr, xCoord, yCoord);
                image.setRGB(x, y, rgb);
                nanoGetRBG += System.nanoTime() - nanoRBGStart;

                //int[] rgb = MathExpressions.getRGBArr(redExpr, grnExpr, bluExpr, xCoord, yCoord);
                //raster.setPixel(x, y, rgb);

                //TODO: correctly buffer pixel writing faster than 250ms at 80x80 resolution (50% power)
                //int[] rgb = MathExpressions.getRGBArr(redExpr, grnExpr, bluExpr, xCoord, yCoord);
                //pixels[y * raster.getWidth() + x] = rgb[0];
                //pixels[y * raster.getWidth() + x + grnBandOffset] = rgb[1];
                //pixels[y * raster.getWidth() + x + bluBandOffset] = rgb[2];
            }
        }


        //raster.setPixels(0, 0, raster.getWidth(), raster.getHeight(), pixels);
        //image.setData(raster);
        double timeTotal = (System.nanoTime() - start) * 1.0 / 1000000;
        System.out.println("Time create image: " + timeTotal + " ms");
        double msRBG = nanoGetRBG * 1.0 / 1000000;
        System.out.println("Time getRBG: " + msRBG + " ms");
        System.out.println();
        return image;
    }


    /**
     * Exports the given image with the given name to the working directory
     * @param image input image
     * @param imageName input name
     */
    public static void exportImage(BufferedImage image, String imageName) {
        try {
            File test = new File("");
            File outputfile = new File(test.getAbsolutePath() + imageName + ".png");
            System.out.println(outputfile.getAbsolutePath());
            ImageIO.write(image, "png", outputfile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Scales an image to the desired width and height
     * @param image the input image
     * @param width desired output width
     * @param height desired output height
     * @return the scaled image
     * @throws IOException
     */
    public static BufferedImage getScaledImage(BufferedImage image, int width, int height) {
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


    /**
     * Sets the random number generators' seed
     * @param seed the seed
     */
    public static void setRandomSeed(long seed) {
        RandomMathArt.rng = new Random(seed);
    }
}
