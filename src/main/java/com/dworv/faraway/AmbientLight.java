package com.dworv.faraway;

public class AmbientLight extends Light {
    Color color;
    double intensity;

    public AmbientLight(Color color, double intensity) {
        this.color = color;
        this.intensity = intensity;
    }

    public String toString() {
        return "AmbientLight(" + color + ", " + intensity + ")";
    }

    public Color contribution(Ray oldRay, Triangle currentTriangle, Scene scene) {
        return color.dot(intensity);
    }
}
