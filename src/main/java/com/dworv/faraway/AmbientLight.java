package com.dworv.faraway;

/**
 * AmbientLight is used to represent a light source that is present everywhere in the scene.
 * <p>
 * It is defined by a color and an intensity. The color is the color of the light, and the intensity is a number between 0 and 1 that represents how much of the color is present in the scene.
 * For example, if the color is (1, 1, 1) and the intensity is 0.5, then the light will be white, but only half as bright as a light with intensity 1.
*/
public class AmbientLight extends Light {
    /**
     * The color of the light.
     * <p>
     * This is a Color object, which is a 3D vector with red, green, and blue components.
     */
    Color color;
    /**
     * The intensity of the light.
     * <p>
     * This is a number between 0 and 1 that represents how much of the color is present in the scene.
     */
    double intensity;

    /**
     * Create a new AmbientLight.
     * <p>
     * Example:
     * <pre>
     * AmbientLight light = new AmbientLight(new Color(1, 1, 1), 0.5);
     * </pre>
     * @param color the color of the light
     * @param intensity the intensity of the light
     */
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
