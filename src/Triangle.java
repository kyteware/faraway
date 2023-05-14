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

    public Vec4 toPlane() {
        Vec3 line1 = b.sub(a);
        Vec3 line2 = c.sub(a);
        Vec3 normal = line2.cross(line1);
        double k = new Vec3(-1.).mul(normal).mul(a).sum();
        return new Vec4(
            normal.getX(),
            normal.getY(),
            normal.getZ(),
            k
        );
    }

    public boolean containsPoint(Vec3 point) {
        Vec3[][] configs = { { a, b, c }, { b, c, a }, { c, a, b } };
        for (Vec3[] config : configs) {
            Vec3 p1 = config[0];
            Vec3 p2 = config[1];
            Vec3 p3 = config[2];

            Vec3 line = p2.sub(p3);
            Vec3 check = line.cross(point.sub(p3));
            Vec3 known = line.cross(p1.sub(p3));
            if (check.mul(known).sum() <= 0) {
                return false;
            }
        }
        return true;
    }
}
