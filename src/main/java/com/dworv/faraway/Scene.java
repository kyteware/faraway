package com.dworv.faraway;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Scene is used to represent a scene.
 * @author github.com/dworv
 */
public class Scene {
    /**
     * The cameras in the scene.
     * <p>
     * This is an array of Camera objects.
     */
    private Camera[] cameras;
    /**
     * The triangles in the scene.
     * <p>
     * This is an array of Triangle objects.
     */
    private Triangle[] triangles;
    /**
     * The lights in the scene.
     * <p>
     * This is an array of Light objects.
     */
    private Light[] lights;
    /**
     * The background color of the scene.
     * <p>
     * This is a Color object.
     */
    private Color background;

    /**
     * Create a new Scene.
     * <p>
     * This is used to represent a scene.
     * <p>
     * Example:
     * <pre>
     * Scene scene = new Scene(cameras, triangles, lights, background);
     * </pre>
     * @param cameras the cameras in the scene
     * @param triangles the triangles in the scene
     * @param lights the lights in the scene
     * @param background the background color of the scene
     */
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

    /**
     * Get the cameras in the scene.
     * @return the cameras in the scene
     */
    public Camera[] getCameras() {
        return cameras;
    }

    /**
     * Get the triangles in the scene.
     * @return the triangles in the scene
     */
    public Triangle[] getTriangles() {
        return triangles;
    }

    /**
     * Get the lights in the scene.
     * @return the lights in the scene
     */
    public Light[] getLights() {
        return lights;
    }

    /**
     * Get the background color of the scene.
     * @return the background color of the scene
     */
    public Color getBackground() {
        return background;
    }

    /**
     * Render the scene.
     * <p>
     * This renders the scene and saves the images to the outputs directory.
     */
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
            Draw.drawImage(pixels, path);
            System.err.println("Rendered and drawn in " + (System.currentTimeMillis() - start) + "ms");
        }
    }
}
