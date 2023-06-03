package com.dworv.faraway;

/**
 * Triangle is used to represent a triangle in the scene.
 * <p>
 * It is defined by three points and a texture.
 * @author github.com/dworv
 */
public class Triangle extends Shape {
    /**
     * The first point of the triangle.
     */
    private Vec3 a;
    /**
     * The second point of the triangle.
     */
    private Vec3 b;
    /**
     * The third point of the triangle.
     */
    private Vec3 c;
    /**
     * The texture of the triangle.
     */
    private Texture texture;

    /**
     * Create a new Triangle.
     * <p>
     * Example:
     * <pre>
     * Triangle triangle = new Triangle(new Vec3(0, 0, 0), new Vec3(1, 0, 0), new Vec3(0, 1, 0), new Texture(new Color(1, 0, 0), 0.5));
     * </pre>
     * @param a the first point of the triangle
     * @param b the second point of the triangle
     * @param c the third point of the triangle
     * @param texture the texture of the triangle
     */
    public Triangle(Vec3 a, Vec3 b, Vec3 c, Texture texture) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.texture = texture;
    }

    /**
     * Create a new Triangle with a white texture.
     * <p>
     * Example:
     * <pre>
     * Triangle triangle = new Triangle(new Vec3(0, 0, 0), new Vec3(1, 0, 0), new Vec3(0, 1, 0));
     * </pre>
     * @param a the first point of the triangle
     * @param b the second point of the triangle
     * @param c the third point of the triangle
     */
    public Triangle(Vec3 a, Vec3 b, Vec3 c) {
        this(a, b, c, new Texture(new Color(1), 0));
    }

    public String toString() {
        return "Triangle(" + a + ", " + b + ", " + c + ", " + texture + ")";
    }

    /**
     * Get the first point of the triangle.
     * @return the first point of the triangle
     */
    public Vec3 getA() {
        return a;
    }

    /**
     * Get the second point of the triangle.
     * @return the second point of the triangle
     */
    public Vec3 getB() {
        return b;
    }

    /**
     * Get the third point of the triangle.
     * @return the third point of the triangle
     */
    public Vec3 getC() {
        return c;
    }

    /**
     * Get the texture of the triangle.
     * @return the texture of the triangle
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * Get the normal of the triangle.
     * <p>
     * The normal is the vector perpendicular to the triangle.
     * <p>
     * Pseudocode:
     * <ol>
     * <li>Compute the two vectors of the triangle</li>
     * <li>Compute the cross product of the two vectors</li>
     * <li>Normalize the cross product</li>
     * </ol>
     * Example:
     * <pre>
     * Triangle triangle = new Triangle(new Vec3(0, 0, 0), new Vec3(1, 0, 0), new Vec3(0, 1, 0));
     * Vec3 normal = triangle.getNormal();
     * </pre>
     * @return the normal of the triangle
     */
    public Vec3 getNormal() {
        Vec3 line1 = b.sub(a);
        Vec3 line2 = c.sub(a);
        return line2.cross(line1).normalize();
    }

    /**
     * Get the normal of the triangle facing a point.
     * <p>
     * The normal is the vector perpendicular to the triangle.
     * <p>
     * Pseudocode:
     * <ol>
     * <li>Compute the normal of the triangle</li>
     * <li>If the dot product of the normal and the vector from the point to the triangle is positive, return the normal, otherwise return the negative of the normal</li>
     * </ol>
     * Example:
     * <pre>
     * Triangle triangle = new Triangle(new Vec3(0, 0, 0), new Vec3(1, 0, 0), new Vec3(0, 1, 0));
     * Vec3 normal = triangle.getNormalFacing(new Vec3(0, 0, 1));
     * </pre>
     * @param point the point to face
     * @return the normal of the triangle
     */
    public Vec3 getNormalFacing(Vec3 point) {
        Vec3 normal = getNormal();
        if (normal.dot(point.sub(a)).sum() > 0) {
            return normal;
        } else {
            return normal.dot(-1.);
        }
    }

    /**
     * Get the plane of the triangle.
     * <p>
     * The plane is defined by the normal of the triangle and a point on the triangle.
     * @return the plane of the triangle
     */
    public Plane toPlane() {
        Vec3 normal = getNormal();
        double k = new Vec3(-1.).dot(normal).dot(a).sum();
        return new Plane(
            normal.getX(),
            normal.getY(),
            normal.getZ(),
            k
        );
    }

    /**
     * Get the plane of the triangle facing a point.
     * <p>
     * The plane is defined by the normal of the triangle and a point on the triangle.
     * @param point the point to face
     * @return the plane of the triangle
     */
    public Plane toPlaneFacing(Vec3 point) {
        Vec3 normal = getNormalFacing(point);
        double k = new Vec3(-1.).dot(normal).dot(a).sum();
        return new Plane(
            normal.getX(),
            normal.getY(),
            normal.getZ(),
            k
        );
    }

    /**
     * Checks if a point is inside the triangle.
     * <p>
     * Pseudocode:
     * <ol>
     * <li>For each side of the triangle</li>
     * <li>Compute the line of the side</li>
     * <li>Compute the cross product of the line and the vector from the point to the third point of the side</li>
     * <li>Compute the cross product of the line and the vector from the first point of the side to the third point of the side</li>
     * <li>If the dot product of the two cross products is less than or equal to zero, return false</li>
     * <li>Return true</li>
     * </ol>
     * Example:
     * <pre>
     * Triangle triangle = new Triangle(new Vec3(0, 0, 0), new Vec3(1, 0, 0), new Vec3(0, 1, 0));
     * boolean contains = triangle.containsPoint(new Vec3(0.5, 0.5, 0));
     * </pre>
     * @param point the point to check
     * @return true if the point is inside the triangle, false otherwise
     */
    public boolean containsPoint(Vec3 point) {
        Vec3[][] configs = { { a, b, c }, { b, c, a }, { c, a, b } };
        for (Vec3[] config : configs) {
            Vec3 p1 = config[0];
            Vec3 p2 = config[1];
            Vec3 p3 = config[2];

            Vec3 line = p2.sub(p3);
            Vec3 check = line.cross(point.sub(p3));
            Vec3 known = line.cross(p1.sub(p3));
            if (check.dot(known).sum() <= 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    Triangle[] toTriangles() {
        return new Triangle[] { this };
    }
}
