public class Vec3 {
    private double x;
    private double y;
    private double z;

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3(double all) {
        x = all;
        y = all;
        z = all;
    }

    public String toString() {
        return "Vec3(" + x + ", " + y + ", " + z + ")";
    }

    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Vec3 add(Vec3 other) {
        return new Vec3(
            this.x + other.x,
            this.y + other.y,
            this.z + other.z
        );
    }

    public Vec3 add(double other) {
        return new Vec3(
            this.x + other,
            this.y + other,
            this.z + other
        );
    }

    public Vec3 sub(Vec3 other) {
        return new Vec3(
            this.x - other.x,
            this.y - other.y,
            this.z - other.z
        );
    }

    public Vec3 sub(double other) {
        return new Vec3(
            this.x - other,
            this.y - other,
            this.z - other
        );
    }

    public Vec3 mul(Vec3 other) {
        return new Vec3(
            this.x * other.x,
            this.y * other.y,
            this.z * other.z
        );
    }

    public Vec3 mul(double other) {
        return new Vec3(
            this.x * other,
            this.y * other,
            this.z * other
        );
    }

    public Vec3 cross(Vec3 other) {
        return new Vec3(
            this.y * other.z - this.z * other.y,
            this.z * other.x - this.x * other.z,
            this.x * other.y - this.y * other.x
        );
    }

    public Vec3 div(Vec3 other) {
        return new Vec3(
            this.x / other.x,
            this.y / other.y,
            this.z / other.z
        );
    }

    public Vec3 div(double other) {
        return new Vec3(
            this.x / other,
            this.y / other,
            this.z / other
        );
    }

    public Vec3 pow(Vec3 other) {
        return new Vec3(
            Math.pow(this.x, other.x),
            Math.pow(this.y, other.y),
            Math.pow(this.z, other.z)
        );
    }

    public Vec3 pow(double other) {
        return new Vec3(
            Math.pow(this.x, other),
            Math.pow(this.y, other),
            Math.pow(this.z, other)
        );
    }

    public Vec3 min(Vec3 other) {
        return new Vec3(
            Math.min(this.x, other.x),
            Math.min(this.y, other.y),
            Math.min(this.z, other.z)
        );
    }

    public Vec3 min(double other) {
        return new Vec3(
            Math.min(this.x, other),
            Math.min(this.y, other),
            Math.min(this.z, other)
        );
    }

    public Vec3 max(Vec3 other) {
        return new Vec3(
            Math.max(this.x, other.x),
            Math.max(this.y, other.y),
            Math.max(this.z, other.z)
        );
    }

    public Vec3 max(double other) {
        return new Vec3(
            Math.max(this.x, other),
            Math.max(this.y, other),
            Math.max(this.z, other)
        );
    }

    public double sum() {
        return x + y + z;
    }

    public double distance(Vec3 other) {
        double sum = Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2) + Math.pow(this.z - other.z, 2);
        return Math.sqrt(sum);
    }

    public Vec3 normalize() {
        double length = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
        return new Vec3(x / length, y / length, z / length);
    }
}
