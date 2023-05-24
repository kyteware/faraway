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

    public Color contribution(Vec3 intercept, Vec3 normal, Triangle[] triangles, Triangle currentTriangle) {
        double distanceToLight = intercept.distance(position);

        for (Triangle t : triangles) {
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
        double strength = normal.mul(dirToLight).sum();
        if (strength < 0) {
            normal = normal.mul(new Vec3(-1.));
            strength = normal.mul(dirToLight).sum();
        }
        return color.mul(strength * lightIntensity);
    }
}
