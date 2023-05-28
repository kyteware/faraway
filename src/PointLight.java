public class PointLight extends Light {
    Vec3 position;
    Color color;
    double intensity;

    public PointLight(Vec3 position, Color color, double intensity) {
        this.position = position;
        this.color = color;
        this.intensity = intensity;
    }

    public String toString() {
        return "Light(" + position + ", " + color + ")";
    }

    public Color contribution(Ray oldRay, Triangle currentTriangle, Scene scene) {
        Vec3 intercept = oldRay.intercept(currentTriangle.toPlane());
        double distanceToLight = intercept.distance(position);

        for (Triangle t : scene.getTriangles()) {
            if (currentTriangle == t) {
                continue;
            }
            Vec3 dirToLight = position.sub(intercept).normalize();
            Ray ray = new Ray(dirToLight, intercept);
            Vec3 otherIntercept = ray.intercept(t.toPlane());
            if (otherIntercept != null) {
                if (intercept.distance(otherIntercept) < distanceToLight && t.containsPoint(otherIntercept)) {
                    return new Color(0.);
                }
            }
        }
        double lightIntensity = intensity / Math.pow(distanceToLight, 2);
        Vec3 dirToLight = intercept.sub(position).normalize();

        Vec3 normal = currentTriangle.getNormal().normalize();
        if (normal.dot(oldRay.getDir().normalize()).sum() < 0) {
            normal = normal.dot(new Vec3(-1.));
        }

        double strength = normal.dot(dirToLight).sum();
        if (strength <= 0) {
            return new Color(0.);
        }
        return color.dot(strength * lightIntensity);
    }
}
