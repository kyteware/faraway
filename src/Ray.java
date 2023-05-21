public class Ray {
    private Vec3 dir;
    private Vec3 origin;

    public Ray(Vec3 dir, Vec3 origin) {
        this.dir = dir;
        this.origin = origin;
    }

    public Ray(Vec3 dir) {
        this.dir = dir;
        this.origin = new Vec3(0.);
    }

    public String toString() {
        return "Ray(" + this.dir + ", " + this.origin + ")";
    }

    public Vec3 getOrigin() {
        return origin;
    }

    public Vec3 getDir() {
        return dir;
    }

    /**
     * returns null if intercept behind
    */
    public Vec3 intercept(Vec4 plane) {
        double rawDist = - (
            (plane.toABC().mul(origin).sum() + plane.getK()) /
            plane.toABC().mul(dir).sum()
        );
        if (rawDist >= 0) {
            return dir.mul(new Vec3(rawDist)).add(origin);
        }
        else {
            return null;
        }
    }

    public Ray reflect(Vec4 plane) {
        Vec3 intercept = intercept(plane);
        Vec3 normal = plane.toABC();
        Vec3 newDir = dir.sub(normal.mul(dir.mul(normal).mul(new Vec3(2.))));
        return new Ray(newDir, intercept);
    }
}
