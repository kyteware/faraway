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

    public String toString() {
        String result = "Scene(\n";
        result += "  cameras: [\n";
        for (Camera camera : cameras) {
            result += "    " + camera + "\n";
        }
        result += "  ],\n";
        result += "  triangles: [\n";
        for (Triangle triangle : triangles) {
            result += "    " + triangle + "\n";
        }
        result += "  ],\n";
        result += "  lights: [\n";
        for (Light light : lights) {
            result += "    " + light + "\n";
        }
        result += "  ],\n";
        result += "  background: " + background + "\n";
        result += ")";
        return result;
    }

    public Camera[] getCameras() {
        return cameras;
    }

    public Triangle[] getTriangles() {
        return triangles;
    }

    public Light[] getLights() {
        return lights;
    }

    public Color getBackground() {
        return background;
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
            double start = System.currentTimeMillis();
            Color[][] pixels = cameras[i].render(this);
            System.out.println("Rendered in " + (System.currentTimeMillis() - start) + "ms");
            start = System.currentTimeMillis();
            Draw.drawImage(pixels, path);
            System.out.println("Drawn in " + (System.currentTimeMillis() - start) + "ms");
        }
    }
}
