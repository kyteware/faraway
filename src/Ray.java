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
    public Vec3 intercept(Plane plane) {
        double rawDist = - (
            (plane.toABC().dot(origin).sum() + plane.getK()) /
            plane.toABC().dot(dir).sum()
        );
        if (rawDist >= 0) {
            return dir.dot(rawDist).add(origin);
        }
        else {
            return null;
        }
    }

    public Triangle closestTriangle(Triangle[] triangles) {
        double closestDist = Double.POSITIVE_INFINITY;
        Triangle closestTriangle = null;

        for (Triangle t : triangles) {
            Plane plane = t.toPlane();
            Vec3 localIntercept = this.intercept(plane);
            if (localIntercept != null) {
                double distance = origin.distance(localIntercept);
                if (distance < closestDist && t.containsPoint(localIntercept)) {
                    closestDist = distance;
                    closestTriangle = t;
                }
            }
        }

        return closestTriangle;
    }

    public Ray reflect(Plane plane) {
        Vec3 intercept = intercept(plane);
        Vec3 normal = plane.toABC().normalize();
        Vec3 newDir = dir.sub(normal.dot(dir.dot(normal).sum()).dot(2));
        return new Ray(newDir, intercept);
    }

    public Color getColor(Scene scene, int depth) {
        Triangle closestTriangle = closestTriangle(scene.getTriangles());

        Color color = new Color(0.);;
        if (closestTriangle != null) {
            for (Light light : scene.getLights()) {
                Color contribution = light.contribution(this, closestTriangle, scene);
                color = color.add(contribution);
            }
            color = color.dot(closestTriangle.getTexture().getColor()).cap();

            if (depth > 0 && closestTriangle.getTexture().getReflectivity() > 0.) {
                Ray reflectedRay = this.reflect(closestTriangle.toPlaneFacing(origin));
                reflectedRay.origin = reflectedRay.origin.add(reflectedRay.dir.dot(0.0001));
                Color reflectedColor = reflectedRay.getColor(scene, depth-1);
                double reflectivity = closestTriangle.getTexture().getReflectivity();
                color = color.dot(1.-reflectivity).add(reflectedColor.dot(reflectivity));
            }
        }
        else {
            color = scene.getBackground();
        }

        return color;
    }
}
