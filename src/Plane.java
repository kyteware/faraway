public class Plane { //TODO: rename to Plane
    private double a;
    private double b;
    private double c;
    private double k;

    public Plane(double a, double b, double c, double k) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.k = k;
    }

    public Plane(double all) {
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

    public Plane add(Plane other) { // TODO: scaler ops for all vec4 math
        return new Plane(
            this.a + other.a,
            this.b + other.b,
            this.c + other.c,
            this.k + other.k
        );
    }

    public Plane add(double other) {
        return new Plane(
            this.a + other,
            this.b + other,
            this.c + other,
            this.k + other
        );
    }

    public Plane sub(Plane other) {
        return new Plane(
            this.a - other.a,
            this.b - other.b,
            this.c - other.c,
            this.k - other.k
        );
    }

    public Plane sub(double other) {
        return new Plane(
            this.a - other,
            this.b - other,
            this.c - other,
            this.k - other
        );
    }

    public Plane dot(Plane other) {
        return new Plane(
            this.a * other.a,
            this.b * other.b,
            this.c * other.c,
            this.k * other.k
        );
    }

    public Plane dot(double other) {
        return new Plane(
            this.a * other,
            this.b * other,
            this.c * other,
            this.k * other
        );
    }

    public Plane div(Plane other) {
        return new Plane(
            this.a / other.a,
            this.b / other.b,
            this.c / other.c,
            this.k + other.k
        );
    }

    public Plane div(double other) {
        return new Plane(
            this.a / other,
            this.b / other,
            this.c / other,
            this.k / other
        );
    }
}
