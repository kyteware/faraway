public class Light {
    Vec3 position;
    Vec3 color;
    double intensity;

    public Light(Vec3 position, Vec3 color, double intensity) {
        this.position = position;
        this.color = color;
        this.intensity = intensity;
    }

    public Vec3 getPosition() {
        return position;
    }

    public Vec3 getColor() {
        return color;
    }

    public double getIntensity() {
        return intensity;
    }

    public String toString() {
        return "Light(" + position + ", " + color + ")";
    }
}
