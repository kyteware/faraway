public class Vec4 {
    private double a;
    private double b;
    private double c;
    private double k;

    public Vec4(double a, double b, double c, double k) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.k = k;
    }

    public Vec4(double all) {
        a = all;
        b = all;
        c = all;
        k = all;
    }

    public String toString() {
        return "Vec4(" + a + ", " + b + ", " + c + ", " + k + ")";
    }

    public double getA() {
        return a;
    }
    
    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getK() {
        return k;
    }

    public Vec3 toABC() {
        return new Vec3(a, b, c);
    }

    public Vec4 add(Vec4 other) {
        return new Vec4(
            this.a + other.a,
            this.b + other.b,
            this.c + other.c,
            this.k + other.k
        );
    }

    public Vec4 sub(Vec4 other) {
        return new Vec4(
            this.a - other.a,
            this.b - other.b,
            this.c - other.c,
            this.k - other.k
        );
    }

    public Vec4 mul(Vec4 other) {
        return new Vec4(
            this.a * other.a,
            this.b * other.b,
            this.c * other.c,
            this.k * other.k
        );
    }

    public Vec4 div(Vec4 other) {
        return new Vec4(
            this.a / other.a,
            this.b / other.b,
            this.c / other.c,
            this.k + other.k
        );
    }
}
