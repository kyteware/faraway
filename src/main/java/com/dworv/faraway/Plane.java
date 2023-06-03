package com.dworv.faraway;

/**
 * Plane is used to represent a plane in the scene.
 * <p>
 * It is defined by a normal vector and a distance from the origin.
 * @author github.com/dworv
 */
public class Plane {
    /**
     * The a component of the plane.
     * <p>
     * This is the x component of the normal vector.
     */
    private double a;
    /**
     * The b component of the plane.
     * <p>
     * This is the y component of the normal vector.
     */
    private double b;
    /**
     * The c component of the plane.
     * <p>
     * This is the z component of the normal vector.
     */
    private double c;
    /**
     * The k component of the plane.
     * <p>
     * This is the distance from the origin.
     */
    private double k;

    /**
     * Create a new Plane.
     * <p>
     * Example:
     * <pre>
     * Plane plane = new Plane(1, 0, 0, 0);
     * </pre>
     * @param a the a component of the plane
     * @param b the b component of the plane
     * @param c the c component of the plane
     * @param k the k component of the plane
     */
    public Plane(double a, double b, double c, double k) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.k = k;
    }

    public String toString() {
        return "Vec4(" + a + ", " + b + ", " + c + ", " + k + ")";
    }

    /**
     * Get the a component of the plane.
     * <p>
     * This is the x component of the normal vector.
     * @return the a component of the plane
     */
    public double getA() {
        return a;
    }
    
    /**
     * Get the b component of the plane.
     * <p>
     * This is the y component of the normal vector.
     * @return the b component of the plane
     */
    public double getB() {
        return b;
    }

    /**
     * Get the c component of the plane.
     * <p>
     * This is the z component of the normal vector.
     * @return the c component of the plane
     */
    public double getC() {
        return c;
    }

    /**
     * Get the k component of the plane.
     * <p>
     * This is the distance from the origin.
     * @return the k component of the plane
     */
    public double getK() {
        return k;
    }

    /**
     * Get the normal vector of the plane.
     * @return the normal vector of the plane
     */
    public Vec3 toABC() {
        return new Vec3(a, b, c);
    }

    /**
     * Add two planes together.
     * <p>
     * Example:
     * <pre>
     * Plane plane = new Plane(1, 0, 0, 0);
     * Plane other = new Plane(0, 1, 0, 0);
     * Plane sum = plane.add(other);
     * </pre>
     * @param other the other plane to add
     * @return the sum of the two planes
     */
    public Plane add(Plane other) {
        return new Plane(
            this.a + other.a,
            this.b + other.b,
            this.c + other.c,
            this.k + other.k
        );
    }

    /**
     * Add a scalar to a plane.
     * <p>
     * Example:
     * <pre>
     * Plane plane = new Plane(1, 0, 0, 0);
     * Plane sum = plane.add(1);
     * </pre>
     * @param other the scalar to add
     * @return the sum of the plane and the scalar
     */
    public Plane add(double other) {
        return new Plane(
            this.a + other,
            this.b + other,
            this.c + other,
            this.k + other
        );
    }

    /**
     * Subtract two planes.
     * <p>
     * Example:
     * <pre>
     * Plane plane = new Plane(1, 0, 0, 0);
     * Plane other = new Plane(0, 1, 0, 0);
     * Plane difference = plane.sub(other);
     * </pre>
     * @param other the other plane to subtract
     * @return the difference of the two planes
     */
    public Plane sub(Plane other) {
        return new Plane(
            this.a - other.a,
            this.b - other.b,
            this.c - other.c,
            this.k - other.k
        );
    }

    /**
     * Subtract a scalar from a plane.
     * <p>
     * Example:
     * <pre>
     * Plane plane = new Plane(1, 0, 0, 0);
     * Plane difference = plane.sub(1);
     * </pre>
     * @param other the scalar to subtract
     * @return the difference of the plane and the scalar
     */
    public Plane sub(double other) {
        return new Plane(
            this.a - other,
            this.b - other,
            this.c - other,
            this.k - other
        );
    }

    /**
     * Multiply two planes together.
     * <p>
     * Example:
     * <pre>
     * Plane plane = new Plane(1, 0, 0, 0);
     * Plane other = new Plane(0, 1, 0, 0);
     * Plane product = plane.mul(other);
     * </pre>
     * @param other the other plane to multiply
     * @return the product of the two planes
     */
    public Plane dot(Plane other) {
        return new Plane(
            this.a * other.a,
            this.b * other.b,
            this.c * other.c,
            this.k * other.k
        );
    }

    /**
     * Multiply a plane by a scalar.
     * <p>
     * Example:
     * <pre>
     * Plane plane = new Plane(1, 0, 0, 0);
     * Plane product = plane.mul(1);
     * </pre>
     * @param other the scalar to multiply
     * @return the product of the plane and the scalar
     */
    public Plane dot(double other) {
        return new Plane(
            this.a * other,
            this.b * other,
            this.c * other,
            this.k * other
        );
    }

    /**
     * Divide two planes.
     * <p>
     * Example:
     * <pre>
     * Plane plane = new Plane(1, 0, 0, 0);
     * Plane other = new Plane(0, 1, 0, 0);
     * Plane quotient = plane.div(other);
     * </pre>
     * @param other the other plane to divide
     * @return the quotient of the two planes
     */
    public Plane div(Plane other) {
        return new Plane(
            this.a / other.a,
            this.b / other.b,
            this.c / other.c,
            this.k + other.k
        );
    }

    /**
     * Divide a plane by a scalar.
     * <p>
     * Example:
     * <pre>
     * Plane plane = new Plane(1, 0, 0, 0);
     * Plane quotient = plane.div(1);
     * </pre>
     * @param other the scalar to divide
     * @return the quotient of the plane and the scalar
     */
    public Plane div(double other) {
        return new Plane(
            this.a / other,
            this.b / other,
            this.c / other,
            this.k / other
        );
    }
}
