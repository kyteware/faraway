package com.dworv.faraway;

/**
 * Ray is used to represent a ray in the scene.
 * <p>
 * It is defined by a direction and an origin.
 * @author github.com/dworv
 */
public class Ray {
    /**
     * The direction of the ray.
     * <p>
     * This is a unit vector.
     */
    private Vec3 dir;
    /**
     * The origin of the ray.
     * <p>
     * This is a point.
     */
    private Vec3 origin;

    /**
     * Create a new Ray.
     * <p>
     * Example:
     * <pre>
     * Ray ray = new Ray(new Vec3(1, 0, 0), new Vec3(0, 0, 0));
     * </pre>
     * @param dir the direction of the ray
     * @param origin the origin of the ray
     */
    public Ray(Vec3 dir, Vec3 origin) {
        this.dir = dir;
        this.origin = origin;
    }

    /**
     * Create a new Ray with a (0, 0, 0) origin.
     * <p>
     * Example:
     * <pre>
     * Ray ray = new Ray(new Vec3(1, 0, 0));
     * </pre>
     * @param dir the direction of the ray
     */
    public Ray(Vec3 dir) {
        this.dir = dir;
        this.origin = new Vec3(0.);
    }

    /**
     * Create a new Ray from yaw and pitch.
     * <p>
     * Pseudocode:
     * <ol>
     *    <li> Use euclidean coordinates to convert yaw and pitch to a direction vector
     *    <li> Create a new Ray with the direction vector and position
     * </ol>
     * Example:
     * <pre>
     * Ray ray = Ray.fromYawPitch(0, 0, new Vec3(0, 0, 0));
     * </pre>
     * @param yaw the yaw of the ray
     * @param pitch the pitch of the ray
     * @param position the position of the ray
     */
    public static Ray fromYawPitch(double yaw, double pitch, Vec3 position) {
        return new Ray(
            new Vec3(
                Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)),
                Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)),
                Math.sin(Math.toRadians(pitch))
            ),
            position
        );
    }

    public String toString() {
        return "Ray(" + this.dir + ", " + this.origin + ")";
    }

    /**
     * Get the origin of the ray.
     * @return
     */
    public Vec3 getOrigin() {
        return origin;
    }

    /**
     * Get the direction of the ray.
     * @return
     */
    public Vec3 getDir() {
        return dir;
    }

    /**
     * Get the intercept of the ray and a plane.
     * <p>
     * WARNING: Returns null if the intercept is behind the origin.
     * <p>
     * Pseudocode:
     * <ol>
     *    <li> Get the distance from the origin to the plane
     *    <li> If the distance is positive, return the intercept
     *    <li> Otherwise, return null
     * </ol>
     * Example:
     * <pre>
     * Ray ray = new Ray(new Vec3(1, 0, 0), new Vec3(0, 0, 0));
     * Plane plane = new Plane(1, 0, 0, 0);
     * Vec3 intercept = ray.intercept(plane);
     * </pre>
     * @param plane the plane to intercept
     * @return the intercept of the ray and the plane
     */
    public Vec3 intercept(Plane plane) {
        double rawDist = - (
            (plane.toABC().dot(origin).sum() + plane.getK()) /
            plane.toABC().dot(dir).sum()
        );
        if (rawDist >= 0) {
            return dir.dot(rawDist).add(origin);
        }
        else {
            return null;
        }
    }

    /**
     * Get the closest triangle to the ray.
     * <p>
     * Pseudocode:
     * <ol>
     *    <li> For each triangle in the scene
     *    <li>    Get the plane of the triangle
     *    <li>    Get the intercept of the ray and the plane
     *    <li>    If the intercept is not null
     *    <li>        Get the distance from the origin to the intercept
     *    <li>        If the distance is less than the closest distance
     *    <li>            Set the closest triangle to the current triangle
     *    <li>            Set the closest distance to the current distance
     *    <li> Return the closest triangle
     * </ol>
     * Example:
     * <pre>
     * Ray ray = new Ray(new Vec3(1, 0, 0), new Vec3(0, 0, 0));
     * Triangle[] triangles = new Triangle[] {
     *     new Triangle(new Vec3(0, 0, 0), new Vec3(1, 0, 0), new Vec3(0, 1, 0)),
     *     new Triangle(new Vec3(0, 0, 0), new Vec3(0, 1, 0), new Vec3(0, 0, 1)),
     *     new Triangle(new Vec3(0, 0, 0), new Vec3(0, 0, 1), new Vec3(1, 0, 0))
     * };
     * Triangle closestTriangle = ray.closestTriangle(triangles);
     * </pre>
     * @param triangles the triangles to check
     * @return the closest triangle to the ray
     */
    public Triangle closestTriangle(Triangle[] triangles) {
        double closestDist = Double.POSITIVE_INFINITY;
        Triangle closestTriangle = null;

        for (Triangle t : triangles) {
            Plane plane = t.toPlane();
            Vec3 localIntercept = this.intercept(plane);
            if (localIntercept != null) {
                double distance = origin.distance(localIntercept);
                if (distance < closestDist && t.containsPoint(localIntercept)) {
                    closestDist = distance;
                    closestTriangle = t;
                }
            }
        }

        return closestTriangle;
    }

    /**
     * Get the reflected ray of the ray and a plane.
     * <p>
     * Pseudocode:
     * <ol>
     *   <li> Get the intercept of the ray and the plane
     *   <li> Get the normal of the plane
     *   <li> Get the new direction of the ray
     *   <li> Return a new ray with the new direction and the intercept
     * </ol>
     * Example:
     * <pre>
     * Ray ray = new Ray(new Vec3(1, 0, 0), new Vec3(0, 0, 0));
     * Plane plane = new Plane(1, 0, 0, 0);
     * Ray reflectedRay = ray.reflect(plane);
     * </pre>
     * @param plane the plane to reflect off of
     * @return the reflected ray
     */
    public Ray reflect(Plane plane) {
        Vec3 intercept = intercept(plane);
        Vec3 normal = plane.toABC().normalize();
        Vec3 newDir = dir.sub(normal.dot(dir.dot(normal).sum()).dot(2));
        return new Ray(newDir, intercept);
    }

    /**
     * Get the color of the ray.
     * <p>
     * Pseudocode:
     * <ol>
     *   <li> Get the closest triangle to the ray
     *   <li> If the closest triangle is not null
     *   <li>    For each light in the scene
     *   <li>        Get the contribution of the light
     *   <li>        Add the contribution to the color
     *   <li>    Multiply the color by the color of the triangle
     *   <li>    If the depth is greater than 0 and the triangle is reflective
     *   <li>        Get the reflected ray
     *   <li>        Get the color of the reflected ray
     *   <li>        Multiply the color by the reflectivity of the triangle
     *   <li>        Add the color to the color
     *   <li> Return the color
     * </ol>
     * Example:
     * <pre>
     * Ray ray = new Ray(new Vec3(1, 0, 0), new Vec3(0, 0, 0));
     * Scene scene = new Scene();
     * Color color = ray.getColor(scene, 5);
     * </pre>
     * @param scene the scene to get the color from
     * @param depth the depth of the ray
     * @return the color of the ray
     */
    public Color getColor(Scene scene, int depth) {
        Triangle closestTriangle = closestTriangle(scene.getTriangles());

        Color color = new Color(0.);;
        if (closestTriangle != null) {
            for (Light light : scene.getLights()) {
                Color contribution = light.contribution(this, closestTriangle, scene);
                color = color.add(contribution);
            }  
            color = color.dot(closestTriangle.getTexture().getColor()).cap();

            if (depth > 0 && closestTriangle.getTexture().getReflectivity() > 0.) {
                Ray reflectedRay = this.reflect(closestTriangle.toPlaneFacing(origin));
                reflectedRay.origin = reflectedRay.origin.add(reflectedRay.dir.dot(0.0001));
                Color reflectedColor = reflectedRay.getColor(scene, depth-1);
                double reflectivity = closestTriangle.getTexture().getReflectivity();
                color = color.dot(1.-reflectivity).add(reflectedColor.dot(reflectivity));
            }
        }
        else {
            color = scene.getBackground();
        }

        return color;
    }
}
