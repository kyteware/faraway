public class AmbientLight extends Light {
    Color color;
    double intensity;

    public AmbientLight(Color color, double intensity) {
        this.color = color;
        this.intensity = intensity;
    }

    public Color contribution(Vec3 intercept, Vec3 normal, Triangle[] triangles, Triangle currentTriangle) {
        return color.mul(intensity);
    }
}
