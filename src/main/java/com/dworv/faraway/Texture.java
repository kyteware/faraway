package com.dworv.faraway;

/**
 * Texture is used to represent a texture in the scene.
 * <p>
 * It is defined by a color and a reflectivity.
 * @author github.com/dworv
 */
public class Texture {
    /**
     * The color of the texture.
     */
    private Color color;
    /**
     * The reflectivity of the texture.
     * <p>
     * This is a value between 0 and 1.
     */
    private double reflectivity;

    /**
     * Create a new Texture.
     * <p>
     * Example:
     * <pre>
     * Texture texture = new Texture(new Color(1, 0, 0), 0.5);
     * </pre>
     * @param color the color of the texture
     * @param reflectivity the reflectivity of the texture
     */
    public Texture(Color color, double reflectivity) {
        this.color = color;
        this.reflectivity = reflectivity;
    }

    public String toString() {
        return "Texture(" + color + ", " + reflectivity + ")";
    }

    /**
     * Get the color of the texture.
     * @return the color of the texture
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Get the reflectivity of the texture.
     * @return the reflectivity of the texture
     */
    public double getReflectivity() {
        return this.reflectivity;
    }
}
