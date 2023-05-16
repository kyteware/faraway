import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Draw {
    public static void drawImage(Vec3[][] pixels, String path) {
        int width = pixels[0].length;
        int height = pixels.length;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                Vec3 color = pixels[i][j];
                int r = (int) (color.getX() * 255.);
                int g = (int) (color.getY() * 255.);
                int b = (int) (color.getZ() * 255.);
                int p = (256<<24) | (r<<16) | (g<<8) | b;
                img.setRGB(j, i, p);
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
