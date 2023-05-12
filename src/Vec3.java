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

    public Vec3 div(Vec3 other) {
        return new Vec3(
            this.x / other.x,
            this.y / other.y,
            this.z / other.z
        );
    }
}
