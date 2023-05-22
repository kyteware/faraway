public class Light {
    Vec3 position;
    Vec3 color;
    double intensity;
    Type type;

    public enum Type {
        POINT,
        DIRECTIONAL,
        AMBIENT
    }

    public Light(Vec3 position, Vec3 color, double intensity, Type type) {
        this.position = position;
        this.color = color;
        this.intensity = intensity;
        this.type = type;
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

    public Type getType() {
        return type;
    }

    public String toString() {
        return "Light(" + position + ", " + color + ")";
    }
}
