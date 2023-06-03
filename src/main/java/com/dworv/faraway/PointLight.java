package com.dworv.faraway;

/**
 * PointLight is used to represent a point light source in the scene.
 * <p>
 * It is defined by a position, a color, and an intensity.
 * @author github.com/dworv
 */
public class PointLight extends Light {
    /**
     * The position of the light.
     * <p>
     * This is a Vec3 object, which is a 3D vector with x, y, and z components.
     */
    Vec3 position;
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
     * Create a new PointLight.
     * <p>
     * Example:
     * <pre>
     * PointLight light = new PointLight(new Vec3(0, 0, 0), new Color(1, 1, 1), 0.5);
     * </pre>
     * @param position the position of the light
     * @param color the color of the light
     * @param intensity the intensity of the light
     */
    public PointLight(Vec3 position, Color color, double intensity) {
        this.position = position;
        this.color = color;
        this.intensity = intensity;
    }

    public String toString() {
        return "Light(" + position + ", " + color + ")";
    }

    public Color contribution(Ray oldRay, Triangle currentTriangle, Scene scene) {
        Vec3 intercept = oldRay.intercept(currentTriangle.toPlane());
        double distanceToLight = intercept.distance(position);
        
        for (Triangle t : scene.getTriangles()) {
            if (currentTriangle == t) {
                continue;
            }
            Vec3 dirToLight = position.sub(intercept).normalize();
            Ray ray = new Ray(dirToLight, intercept);
            Vec3 otherIntercept = ray.intercept(t.toPlane());
            if (otherIntercept != null) {
                if (intercept.distance(otherIntercept) < distanceToLight && t.containsPoint(otherIntercept)) {
                    return new Color(0.);
                }
            }
        }
        double lightIntensity = intensity / Math.pow(distanceToLight, 2);
        Vec3 dirToLight = intercept.sub(position).normalize();

        Vec3 normal = currentTriangle.getNormal();
        if (normal.dot(oldRay.getDir().normalize()).sum() < 0) {
            normal = normal.dot(-1.);
        }

        double strength = normal.dot(dirToLight).sum();
        if (strength <= 0) {
            return new Color(0.);
        }
        return color.dot(strength * lightIntensity);
    }
}
