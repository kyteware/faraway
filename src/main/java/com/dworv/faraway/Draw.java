package com.dworv.faraway;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Draw {
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
