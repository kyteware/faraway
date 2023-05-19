public class CameraSettings {
    private double gamma;
    private double ratio;
    private double length;
    private double samples;

    public CameraSettings() {
        gamma = 2;
        ratio = 16./9.;
        length = 1;
        samples = 50.;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public double getGamma() {
        return gamma;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getHeight() {
        return 2;
    }

    public double getWidth() {
        return getHeight() * getRatio();
    }

    public double getSamples() {
        return samples;
    }

    public void setSamples(int samples) {
        this.samples = samples;
    }
}
