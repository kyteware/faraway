import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Scene {
    private Camera[] cameras;
    private Triangle[] triangles;

    public Scene(Camera[] cameras, Triangle[] triangles) {
        this.cameras = cameras;
        this.triangles = triangles;
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
            Vec3[][] pixels = cameras[i].render(triangles);
            Draw.drawImage(pixels, path);
        }
    }
}
