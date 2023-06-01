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

public class FScene {
    Camera[] cameras;
    Shape[] shapes;
    Light[] lights;
    Color background;

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

    public static Scene readFScene(String filename) throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Shape.class, new ShapeDeserializer());
        gsonBuilder.registerTypeAdapter(Light.class, new LightDeserializer());
        Gson gson = gsonBuilder.create();
        String json = new String(Files.readAllBytes(Paths.get(filename)));
        FScene scene = gson.fromJson(json, FScene.class);
        ArrayList<Triangle> triangles = new ArrayList<Triangle>();
        for (Shape shape : scene.shapes) {
            for (Triangle triangle : shape.getTriangles()) {
                triangles.add(triangle);
            }
        }
        return new Scene(scene.cameras, triangles.toArray(new Triangle[0]), scene.lights, scene.background);
    }
}

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

