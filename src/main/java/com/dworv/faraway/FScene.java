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
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

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

abstract class Shape {
    Vec3 position;
    Triangle[] getTriangles() {
        Triangle[] triangles = toTriangles();
        Triangle[] transformed = new Triangle[triangles.length];
        for (int i = 0; i < triangles.length; i++) {
            Triangle moved = new Triangle(
                triangles[i].getA().add(position), 
                triangles[i].getB().add(position), 
                triangles[i].getC().add(position), 
                triangles[i].getTexture()
            );
            transformed[i] = moved;
        }
        return transformed;
    }
    abstract Triangle[] toTriangles();
}

class ShapeDeserializer implements JsonDeserializer<Shape> {
    @Override
    public Shape deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        switch (type) {
            case "cube":
                return context.deserialize(jsonObject, Cube.class);
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

/*        +++
 *     g
 *  f  |  h
 *  | e|  |
 *  | |c  |
 *  b |   d
 *    a
 * ---
 */
class Cube extends Shape {
    private Vec3 center;
    private double size;
    private Texture texture;

    Cube(Vec3 center, double size, Texture texture) {
        this.center = center;
        this.size = size;
        this.texture = texture;
    }

    @Override
    Triangle[] toTriangles() {
        Vec3 a = new Vec3(center.getX() - size / 2, center.getY() - size / 2, center.getZ() - size / 2);
        Vec3 b = new Vec3(center.getX() + size / 2, center.getY() - size / 2, center.getZ() - size / 2);
        Vec3 c = new Vec3(center.getX() + size / 2, center.getY() + size / 2, center.getZ() - size / 2);
        Vec3 d = new Vec3(center.getX() - size / 2, center.getY() + size / 2, center.getZ() - size / 2);
        Vec3 e = new Vec3(center.getX() - size / 2, center.getY() - size / 2, center.getZ() + size / 2);
        Vec3 f = new Vec3(center.getX() + size / 2, center.getY() - size / 2, center.getZ() + size / 2);
        Vec3 g = new Vec3(center.getX() + size / 2, center.getY() + size / 2, center.getZ() + size / 2);
        Vec3 h = new Vec3(center.getX() - size / 2, center.getY() + size / 2, center.getZ() + size / 2);
        Triangle[] triangles = {
            new Triangle(a, b, c, texture),
            new Triangle(a, c, d, texture),
            new Triangle(a, e, f, texture),
            new Triangle(a, f, b, texture),
            new Triangle(b, f, g, texture),
            new Triangle(b, g, c, texture),
            new Triangle(c, g, h, texture),
            new Triangle(c, h, d, texture),
            new Triangle(d, h, e, texture),
            new Triangle(d, e, a, texture),
            new Triangle(e, h, g, texture),
            new Triangle(e, g, f, texture),
        };
        return triangles;
    }
    
}
