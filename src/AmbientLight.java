public class AmbientLight extends Light {
    Color color;
    double intensity;

    public AmbientLight(Color color, double intensity) {
        this.color = color;
        this.intensity = intensity;
    }

    public Color contribution(Vec3 intercept, Triangle currentTriangle, Scene scene) {
        return color.mul(intensity);
    }
}
