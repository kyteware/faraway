package com.dworv.faraway;

/**
 * Vec3 is used to represent a 3D vector.
 * <p>
 * It is defined by three doubles: x, y, and z.
 * @author github.com/dworv
 */
public class Vec3 {
    /**
     * The x component of the vector.
     */
    private double x;
    /**
     * The y component of the vector.
     */
    private double y;
    /**
     * The z component of the vector.
     */
    private double z;

    /**
     * Create a new Vec3.
     * <p>
     * Example:
     * <pre>
     * Vec3 vec3 = new Vec3(1, 2, 3);
     * </pre>
     * @param x the x component of the vector
     * @param y the y component of the vector
     * @param z the z component of the vector
     */
    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Create a new Vec3.
     * <p>
     * Example:
     * <pre>
     * Vec3 vec3 = new Vec3(1);
     * </pre>
     * @param all the value of all components of the vector
     */
    public Vec3(double all) {
        x = all;
        y = all;
        z = all;
    }

    public String toString() {
        return "Vec3(" + x + ", " + y + ", " + z + ")";
    }

    /**
     * Get the x component of the vector.
     * <p>
     * Example:
     * <pre>
     * Vec3 vec3 = new Vec3(1, 2, 3);
     * double x = vec3.getX();
     * </pre>
     * @return the x component of the vector
     */
    public double getX() {
        return x;
    }
    
    /**
     * Get the y component of the vector.
     * <p>
     * Example:
     * <pre>
     * Vec3 vec3 = new Vec3(1, 2, 3);
     * double y = vec3.getY();
     * </pre>
     * @return the y component of the vector
     */
    public double getY() {
        return y;
    }

    /**
     * Get the z component of the vector.
     * <p>
     * Example:
     * <pre>
     * Vec3 vec3 = new Vec3(1, 2, 3);
     * double z = vec3.getZ();
     * </pre>
     * @return the z component of the vector
     */
    public double getZ() {
        return z;
    }

    /**
     * Get the length of the vector.
     * <p>
     * Example:
     * <pre>
     * Vec3 vec3 = new Vec3(1, 2, 3);
     * double length = vec3.length();
     * </pre>
     * @return the length of the vector
     */
    public Vec3 add(Vec3 other) {
        return new Vec3(
            this.x + other.x,
            this.y + other.y,
            this.z + other.z
        );
    }

    /**
     * Add a double to all components of the vector.
     * <p>
     * Example:
     * <pre>
     * Vec3 vec3 = new Vec3(1, 2, 3);
     * vec3 = vec3.add(1);
     * @param other
     * @return
     */
    public Vec3 add(double other) {
        return new Vec3(
            this.x + other,
            this.y + other,
            this.z + other
        );
    }

    /**
     * Subtract a vector from this vector.
     * <p>
     * Example:
     * <pre>
     * Vec3 vec3 = new Vec3(1, 2, 3);
     * Vec3 other = new Vec3(1, 2, 3);
     * vec3 = vec3.sub(other);
     * @param other the vector to subtract
     * @return the result of the subtraction
     */
    public Vec3 sub(Vec3 other) {
        return new Vec3(
            this.x - other.x,
            this.y - other.y,
            this.z - other.z
        );
    }

    /**
     * Subtract a double from all components of the vector.
     * <p>
     * Example:
     * <pre>
     * Vec3 vec3 = new Vec3(1, 2, 3);
     * vec3 = vec3.sub(1);
     * @param other the double to subtract
     * @return the result of the subtraction
     */
    public Vec3 sub(double other) {
        return new Vec3(
            this.x - other,
            this.y - other,
            this.z - other
        );
    }

    /**
     * Multiply this vector by another vector.
     * <p>
     * Example:
     * <pre>
     * Vec3 vec3 = new Vec3(1, 2, 3);
     * Vec3 other = new Vec3(1, 2, 3);
     * vec3 = vec3.mul(other);
     * @param other the vector to multiply by
     * @return the result of the multiplication
     */
    public Vec3 dot(Vec3 other) {
        return new Vec3(
            this.x * other.x,
            this.y * other.y,
            this.z * other.z
        );
    }

    /**
     * Multiply all components of the vector by a double.
     * <p>
     * Example:
     * <pre>
     * Vec3 vec3 = new Vec3(1, 2, 3);
     * vec3 = vec3.mul(1);
     * @param other the double to multiply by
     * @return the result of the multiplication
     */
    public Vec3 dot(double other) {
        return new Vec3(
            this.x * other,
            this.y * other,
            this.z * other
        );
    }

    /**
     * Get the cross product of this vector and another vector.
     * <p>
     * Example:
     * <pre>
     * Vec3 vec3 = new Vec3(1, 0, 0);
     * Vec3 other = new Vec3(0, 1, 0);
     * vec3 = vec3.cross(other);
     * @param other the vector to cross with
     * @return the result of the cross product
     */
    public Vec3 cross(Vec3 other) {
        return new Vec3(
            this.y * other.z - this.z * other.y,
            this.z * other.x - this.x * other.z,
            this.x * other.y - this.y * other.x
        );
    }

    /**
     * Divide this vector by another vector.
     * <p>
     * Example:
     * <pre>
     * Vec3 vec3 = new Vec3(2, 4, 6);
     * Vec3 other = new Vec3(1, 2, 3);
     * vec3 = vec3.div(other);
     * </pre>
     * @param other the vector to divide by
     * @return the result of the division
     */
    public Vec3 div(Vec3 other) {
        return new Vec3(
            this.x / other.x,
            this.y / other.y,
            this.z / other.z
        );
    }

    /**
     * Divide all components of the vector by a double.
     * <p>
     * Example:
     * <pre>
     * Vec3 vec3 = new Vec3(2, 4, 6);
     * vec3 = vec3.div(2);
     * </pre>
     * @param other the double to divide by
     * @return the result of the division
     */
    public Vec3 div(double other) {
        return new Vec3(
            this.x / other,
            this.y / other,
            this.z / other
        );
    }

    /**
     * Raise the vector to a power of another vector.
     * @param other the vector to raise by
     * @return the result of the power
     */
    public Vec3 pow(Vec3 other) {
        return new Vec3(
            Math.pow(this.x, other.x),
            Math.pow(this.y, other.y),
            Math.pow(this.z, other.z)
        );
    }

    /**
     * Raise all components of the vector to a power of a double.
     * @param other the double to raise by
     * @return the result of the power
     */
    public Vec3 pow(double other) {
        return new Vec3(
            Math.pow(this.x, other),
            Math.pow(this.y, other),
            Math.pow(this.z, other)
        );
    }

    /**
     * Get the minimum of this vector and another vector.
     * @param other the vector to compare with
     * @return the minimum of the two vectors
     */
    public Vec3 min(Vec3 other) {
        return new Vec3(
            Math.min(this.x, other.x),
            Math.min(this.y, other.y),
            Math.min(this.z, other.z)
        );
    }

    /**
     * Get the minimum of this vector and a double.
     * @param other the double to compare with
     * @return the minimum of the vector and the double
     */
    public Vec3 min(double other) {
        return new Vec3(
            Math.min(this.x, other),
            Math.min(this.y, other),
            Math.min(this.z, other)
        );
    }

    /**
     * Get the maximum of this vector and another vector.
     * @param other the vector to compare with
     * @return the maximum of the two vectors
     */
    public Vec3 max(Vec3 other) {
        return new Vec3(
            Math.max(this.x, other.x),
            Math.max(this.y, other.y),
            Math.max(this.z, other.z)
        );
    }

    /**
     * Get the maximum of this vector and a double.
     * @param other the double to compare with
     * @return the maximum of the vector and the double
     */
    public Vec3 max(double other) {
        return new Vec3(
            Math.max(this.x, other),
            Math.max(this.y, other),
            Math.max(this.z, other)
        );
    }

    /**
     * Get the absolute value of this vector.
     * @return the absolute value of the vector
     */
    public double sum() {
        return x + y + z;
    }

    /**
     * Get the distance between this vector and another vector.
     * @param other the vector to get the distance to
     * @return the distance between the two vectors
     */
    public double distance(Vec3 other) {
        double sum = Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2) + Math.pow(this.z - other.z, 2);
        return Math.sqrt(sum);
    }

    /**
     * Get the length of the vector.
     * @return the length of the vector
     */
    public Vec3 normalize() {
        double length = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
        return new Vec3(x / length, y / length, z / length);
    }
}
