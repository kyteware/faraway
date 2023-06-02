package com.dworv.faraway;

/**
 * CameraSettings is used to represent the settings of a camera.
 * <p>
 * It is defined by a gamma, a ratio, a field of view, a number of samples, and a pixel height.
 * The gamma is a number that represents the gamma of the image produced by the camera.
 * The ratio is the aspect ratio the camera uses.
 * The field of view is is the number of degrees wide the camera is.
 * The number of samples is the number of samples the camera uses in each pixel. Set to 1 to disable anti-aliasing.
 * The pixel height is the height of the image produced by the camera, in pixels.
 * @author github.com/dworv
 */
public class CameraSettings {
    /**
     * The gamma of the camera.
     * <p>
     * This is a number that represents the gamma of the image produced by the camera.
     */
    private double gamma;
    /**
     * The ratio of the camera.
     * <p>
     * This is a number that represents the aspect ratio the camera uses.
     */
    private double ratio;
    /**
     * The field of view of the camera.
     * <p>
     * This is a number that represents the number of degrees wide the camera is.
     */
    private double fov;
    /**
     * The number of samples per pixel.
     * <p>
     * This is a number that represents the number of samples the camera uses in each pixel.
     * Set to 1 to disable anti-aliasing.
     */
    private double samples;
    /**
     * The pixel height of the camera.
     * <p>
     * This is a number that represents the height of the image produced by the camera, in pixels.
     */
    private int pixelHeight;

    /**
     * Create a new CameraSettings.
     * <p>
     * Example:
     * <pre>
     * CameraSettings settings = new CameraSettings(); // default settings
     * </pre>
     */
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

    /**
     * Get the ratio of the camera.
     * <p>
     * Example:
     * <pre>
     * CameraSettings settings = new CameraSettings();
     * double ratio = settings.getRatio(); // ratio is 16/9
     * </pre>
     * @return the ratio of the camera
     */
    public double getRatio() {
        return ratio;
    }

    /**
     * Set the ratio of the camera.
     * <p>
     * Example:
     * <pre>
     * CameraSettings settings = new CameraSettings();
     * settings.setRatio(4./3.); // ratio is now 4/3
     * </pre>
     * @param ratio the new ratio of the camera
     */
    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    /**
     * Get the gamma of the camera.
     * <p>
     * Example:
     * <pre>
     * CameraSettings settings = new CameraSettings();
     * double gamma = settings.getGamma(); // gamma is 2
     * </pre>
     * @return the gamma of the camera
     */
    public double getGamma() {
        return gamma;
    }

    /**
     * Set the gamma of the camera.
     * <p>
     * Example:
     * <pre>
     * CameraSettings settings = new CameraSettings();
     * settings.setGamma(1.8); // gamma is now 1.8
     * </pre>
     * @param gamma the new gamma of the camera
     */
    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

    /**
     * Get the field of view of the camera.
     * <p>
     * Example:
     * <pre>
     * CameraSettings settings = new CameraSettings();
     * double fov = settings.getFOV(); // fov is 90
     * </pre>
     * @return the field of view of the camera
     */
    public double getFOV() {
        return fov;
    }

    /**
     * Set the field of view of the camera.
     * <p>
     * Example:
     * <pre>
     * CameraSettings settings = new CameraSettings();
     * settings.setFOV(120); // fov is now 120
     * </pre>
     * @param fov the new field of view of the camera
     */
    public void setFOV(double fov) {
        this.fov = fov;
    }

    /**
     * Get the height of the camera.
     * <p>
     * Example:
     * <pre>
     * CameraSettings settings = new CameraSettings();
     * double height = settings.getHeight(); // height is 2
     * </pre>
     * @return the height of the camera
     */
    public double getHeight() {
        return 2;
    }

    /**
     * Get the width of the camera.
     * <p>
     * Example:
     * <pre>
     * CameraSettings settings = new CameraSettings();
     * double width = settings.getWidth(); // width is 2 * 16/9
     * </pre>
     * @return the width of the camera
     */
    public double getWidth() {
        return getHeight() * getRatio();
    }

    /**
     * Get the pixel height of the camera.
     * <p>
     * Example:
     * <pre>
     * CameraSettings settings = new CameraSettings();
     * int pixelHeight = settings.getPixelHeight(); // pixelHeight is 1080
     * </pre>
     * @return the pixel height of the camera
     */
    public int getPixelHeight() {
        return pixelHeight;
    }

    /**
     * Set the pixel height of the camera.
     * <p>
     * Example:
     * <pre>
     * CameraSettings settings = new CameraSettings();
     * settings.setPixelHeight(720); // pixelHeight is now 720
     * </pre>
     * @param pixelHeight the new pixel height of the camera
     */
    public void setPixelHeight(int pixelHeight) {
        this.pixelHeight = pixelHeight;
    }

    /**
     * Get the pixel width of the camera.
     * <p>
     * Example:
     * <pre>
     * CameraSettings settings = new CameraSettings();
     * int pixelWidth = settings.getPixelWidth(); // pixelWidth is 1920
     * </pre>
     * @return the pixel width of the camera
     */
    public int getPixelWidth() {
        return (int) (getPixelHeight() * getRatio());
    }

    /**
     * Get the number of samples per pixel.
     * <p>
     * Example:
     * <pre>
     * CameraSettings settings = new CameraSettings();
     * double samples = settings.getSamples(); // samples is 50
     * </pre>
     * @return the number of samples per pixel
     */
    public double getSamples() {
        return samples;
    }

    /**
     * Set the number of samples per pixel.
     * <p>
     * Example:
     * <pre>
     * CameraSettings settings = new CameraSettings();
     * settings.setSamples(100); // samples is now 100
     * </pre>
     * @param samples the new number of samples per pixel
     */
    public void setSamples(int samples) {
        this.samples = samples;
    }
}
