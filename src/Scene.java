import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Scene {
    private Camera[] cameras;
    private Triangle[] triangles;
    private Light[] lights;
    private Color background;

    public Scene(Camera[] cameras, Triangle[] triangles, Light[] lights, Color background) {
        this.cameras = cameras;
        this.triangles = triangles;
        this.background = background;
        this.lights = lights;
    }

    public void render_cams() {
        try {
            Utils.deleteDirectory(new File("outputs"));
            Files.createDirectory(Paths.get("outputs"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i=0; i<cameras.length; i++) {
            String path = "outputs/cam" + i + ".png";
            Color[][] pixels = cameras[i].render(triangles, lights, background);
            Draw.drawImage(pixels, path);
        }
    }
}
