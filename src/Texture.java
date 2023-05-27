public class Texture {
    private Color color;
    private double reflectivity;

    public Texture(Color color, double reflectivity) {
        this.color = color;
        this.reflectivity = reflectivity;
    }

    public Color getColor() {
        return this.color;
    }

    public double getReflectivity() {
        return this.reflectivity;
    }
}
