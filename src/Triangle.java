public class Triangle {
    private Vec3 a;
    private Vec3 b;
    private Vec3 c;
    private Vec3 color;

    public Triangle(Vec3 a, Vec3 b, Vec3 c, Vec3 color) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.color = color;
    }

    public Triangle(Vec3 a, Vec3 b, Vec3 c) {
        this(a, b, c, new Vec3(1.));
    }

    public String toString() {
        return "Triangle(" + a + ", " + b + ", " + c + ", " + color + ")";
    }

    public Vec3 getColor() {
        return color;
    }

    public Vec4 getPlane() {
        Vec3 line1 = b.sub(a);
        Vec3 line2 = c.sub(a);
        Vec3 normal = line1.cross(line2);
        double k = new Vec3(-1.).mul(normal).mul(a).sum();
        return new Vec4(
            normal.getX(),
            normal.getY(),
            normal.getZ(),
            k
        );
    }
}
