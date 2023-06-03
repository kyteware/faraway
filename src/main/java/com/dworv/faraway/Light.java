package com.dworv.faraway;

/**
 * Light is used to represent a light source in the scene.
 * <p>
 * This is an abstract class that is extended by other classes that represent different types of lights.
 * They all implement the `contribution` method, which is used to calculate the contribution of the light to the scene.
 * @author github.com/dworv
 */
public abstract class Light {
    /**
     * Calculate the contribution of the light to the scene.
     * <p>
     * This is used to calculate the contribution of the light to the scene.
     * <p>
     * Example:
     * <pre>
     * Color contribution = light.contribution(oldRay, currentTriangle, scene);
     * </pre>
     * @param oldRay the ray that hit the triangle
     * @param currentTriangle the triangle that was hit
     * @param scene the scene that the triangle is in
     * @return the contribution of the light to the scene
     */
    public abstract Color contribution(Ray oldRay, Triangle currentTriangle, Scene scene);
}
