public class AmbientLight extends Light {
    Vec3 color;
    double intensity;

    public AmbientLight(Vec3 color, double intensity) {
        this.color = color;
        this.intensity = intensity;
    }

    public Vec3 contribution(Vec3 intercept, Vec3 normal, Triangle[] triangles) {
        return color.mul(new Vec3(intensity));
    }
}
