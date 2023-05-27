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
        Vec3 normal = plane.toABC().normalize();
        // System.out.println("old dir: " + dir);
        // System.out.println("normal: " + normal);
        Vec3 newDir = dir.sub(normal.mul(dir.mul(normal).sum()).mul(2));
        // System.out.println("new dir: " + newDir);
        return new Ray(newDir, intercept);
    }

    public Color getColor(Scene scene, int depth) {
        double closestDist = Double.POSITIVE_INFINITY;
        Triangle closestTriangle = null;

        for (Triangle t : scene.getTriangles()) {
            Vec4 plane = t.toPlane();
            Vec3 localIntercept = this.intercept(plane);
            if (localIntercept != null) {
                double distance = origin.distance(localIntercept);
                if (distance < closestDist && t.containsPoint(localIntercept)) {
                    closestDist = distance;
                    closestTriangle = t;
                }
            }
        }

        Color color = new Color(0.);;
        if (closestTriangle != null) {
            for (Light light : scene.getLights()) {
                Color contribution = light.contribution(this, closestTriangle, scene);
                color = color.add(contribution);
            }
            color = color.mul(closestTriangle.getTexture().getColor()).cap();

            if (depth > 0 && closestTriangle.getTexture().getReflectivity() > 0.) {
                // System.out.println("depth: " + depth);
                Ray reflectedRay = this.reflect(closestTriangle.toPlaneFacing(origin));
                reflectedRay.origin = reflectedRay.origin.add(reflectedRay.dir.mul(0.0001));
                Color reflectedColor = reflectedRay.getColor(scene, depth-1);
                double reflectivity = closestTriangle.getTexture().getReflectivity();
                color = color.mul(1.-reflectivity).add(reflectedColor.mul(reflectivity));
            }
        }
        else {
            color = scene.getBackground();
        }

        return color;
    }
}
