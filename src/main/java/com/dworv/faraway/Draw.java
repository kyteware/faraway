package com.dworv.faraway;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Draw {
    /**
     * Draw an image to a file.
     * <p>
     * Pseudocode:
     * <pre>
     * <ol>
     * <li>create a new BufferedImage</li>
     * <li>for each pixel in the image:
     * <li>set the pixel to the corresponding pixel in the pixels array</li>
     * </ol>
     * Example:
     * <pre>
     * Color[][] pixels = new Color[100][100];
     * for (int i=0; i<100; i++) {
     *     for (int j=0; j<100; j++) {
     *         pixels[i][j] = new Color(1, 0, 0);
     *     }
     * }
     * drawImage(pixels, "red.png");
     * </pre>
     * @param pixels the pixels to draw
     * @param path the path to draw to
     */
    public static void drawImage(Color[][] pixels, String path) {
        // Get the image width and height
        int width = pixels[0].length;
        int height = pixels.length;
        // Create a new awt BufferedImage
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Use the provided colors to write to the BufferedImage
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                Color color = pixels[i][j];
                img.setRGB(j, i, color.encode());
            }
        }
        // Write the BufferedImage to the file
        try {
            File f = new File(path);
            ImageIO.write(img, "png", f);
        } catch(IOException e){
            // Fails to write to disk (probably a permissions issue if it ever happens))
            System.out.println(e);
        }
    }
}
