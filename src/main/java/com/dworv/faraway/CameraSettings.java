package com.dworv.faraway;

public class CameraSettings {
    private double gamma;
    private double ratio;
    private double fov;
    private double samples;
    private int pixelHeight;

    public CameraSettings() {
        gamma = 2;
        ratio = 16./9.;
        fov = 90;
        samples = 50.;
        pixelHeight = 1080;
    }

    public String toString() {
        return "CameraSettings(" + gamma + ", " + ratio + ", " + fov + ", " + samples + ", " + pixelHeight + ")";
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

    public double getFOV() {
        return fov;
    }

    public void setFOV(double fov) {
        this.fov = fov;
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
