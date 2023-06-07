package com.dworv.faraway;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * A file representation of a shene.
 * <p>
 * This is used to read a scene from a file for rendering.
 * @author github.com/dworv
 */
public class FScene {
    /**
     * The cameras in the scene.
     * <p>
     * This is an array of Camera objects.
     */
    Camera[] cameras;
    /**
     * The shapes in the scene.
     * <p>
     * This is an array of Shape objects.
     */
    Shape[] shapes;
    /**
     * The lights in the scene.
     * <p>
     * This is an array of Light objects.
     */
    Light[] lights;
    /**
     * The background color of the scene.
     * <p>
     * This is a Color object.
     */
    Color background;

    /**
     * Create a new FScene.
     * <p>
     * This is used to read a scene from a file for rendering.
     * <p>
     * Example:
     * <pre>
     * FScene scene = new FScene();
     * </pre>
     */
    public FScene() {
        Camera[] cameras = {};
        Shape[] shapes = {};
        Light[] lights = {};
        Color background = new Color(0, 0, 0);

        this.cameras = cameras;
        this.shapes = shapes;
        this.lights = lights;
        this.background = background;
    }

    /**
     * Read a scene from a file.
     * <p>
     * This is used to read a scene from a file for rendering.
     * <p>
     * Pseudocode:
     * <ol>
     * <li>Read the file as a string</li>
     * <li>Parse the string as JSON</li>
     * <li>Deserialize the JSON into an FScene object</li>
     * <li>Convert the FScene object into a Scene object</li>
     * </ol>
     * Example:
     * <pre>
     * Scene scene = FScene.readFScene("scene.json");
     * </pre>
     * @param filename the name of the file to read the scene from
     * @return the scene read from the file
     * @throws Exception if there is an error reading the file
     */
    public static Scene readFScene(String filename) throws Exception {
        // Make a new json reader and add adapters for my special classes
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Shape.class, new ShapeDeserializer());
        gsonBuilder.registerTypeAdapter(Light.class, new LightDeserializer());
        Gson gson = gsonBuilder.create();
        // Read json from file
        String json = new String(Files.readAllBytes(Paths.get(filename)));
        // Deserialize json into FScene object
        FScene scene = gson.fromJson(json, FScene.class);
        // Convert FScene object into Scene object
        ArrayList<Triangle> triangles = new ArrayList<Triangle>();
        for (Shape shape : scene.shapes) {
            for (Triangle triangle : shape.getTriangles()) {
                triangles.add(triangle);
            }
        }
        return new Scene(scene.cameras, triangles.toArray(new Triangle[0]), scene.lights, scene.background);
    }
}

/**
 * A deserializer for Shape objects.
 * <p>
 * This is used to deserialize Shape objects from JSON.
 * @author github.com/dworv
 */
class ShapeDeserializer implements JsonDeserializer<Shape> {
    @Override
    public Shape deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        switch (type) {
            case "cube":
                return context.deserialize(jsonObject, Cube.class);
            case "triangle":
                return context.deserialize(jsonObject, Triangle.class);
            default:
                throw new JsonParseException("Unknown shape type: " + type);
        }
    }
}

/**
 * A deserializer for Light objects.
 * <p>
 * This is used to deserialize Light objects from JSON.
 * @author github.com/dworv
 */
class LightDeserializer implements JsonDeserializer<Light> {
    @Override
    public Light deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        switch (type) {
            case "point":
                return context.deserialize(jsonObject, PointLight.class);
            case "ambient":
                return context.deserialize(jsonObject, AmbientLight.class);
            default:
                throw new JsonParseException("Unknown shape type: " + type);
        }
    }
}

