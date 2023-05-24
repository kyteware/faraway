public class CameraSettings {
    private double gamma;
    private double ratio;
    private double length;
    private double samples;
    private int pixelHeight;

    public CameraSettings() {
        gamma = 2;
        ratio = 16./9.;
        length = 1;
        samples = 50.;
        pixelHeight = 1080;
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

    public double getLensLength() {
        return length;
    }

    public void setLensLength(double length) {
        this.length = length;
    }

    public double getHeight() {
        return 2;
    }

    public double getWidth() {
        return getHeight() * getRatio();
    }

    public int getPixelHeight() {
        return pixelHeight;
    }

    public void setPixelHeight(int pixelHeight) {
        this.pixelHeight = pixelHeight;
    }

    public int getPixelWidth() {
        return (int) (getPixelHeight() * getRatio());
    }

    public double getSamples() {
        return samples;
    }

    public void setSamples(int samples) {
        this.samples = samples;
    }
}
