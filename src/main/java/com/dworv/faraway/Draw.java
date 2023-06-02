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
        int width = pixels[0].length;
        int height = pixels.length;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                Color color = pixels[i][j];
                img.setRGB(j, i, color.encode());
            }
        }
        try {
            File f = new File(path);
            ImageIO.write(img, "png", f);
        } catch(IOException e){
            System.out.println(e);
        }
    }
}
