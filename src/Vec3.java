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

    public Vec3 sub(Vec3 other) {
        return new Vec3(
            this.x - other.x,
            this.y - other.y,
            this.z - other.z
        );
    }

    public Vec3 mul(Vec3 other) {
        return new Vec3(
            this.x * other.x,
            this.y * other.y,
            this.z * other.z
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

    public double sum() {
        return x + y + z;
    }

    public double distance(Vec3 other) {
        double sum = Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2) + Math.pow(this.z - other.z, 2);
        return Math.sqrt(sum);
    }
}
