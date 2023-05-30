package com.dworv.faraway;

public class Camera {
    private Vec3 position;
    private CameraSettings settings;
    private double yaw;
    private double pitch;

    public Camera(Vec3 position, double yaw, double pitch) {
        this.position = position;
        this.settings = new CameraSettings();
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public String toString() {
        return "Camera(" + position + ", " + yaw + ", " + pitch + ")";
    }

    public CameraSettings getSettings() {
        return settings;
    }

    public Color[][] render(Scene scene) {
        int pixelHeight = settings.getPixelHeight();
        int pixelWidth = settings.getPixelWidth();

        double startYaw = yaw + -1 * settings.getFOV() / 2;
        double yawIncrement = settings.getFOV() / pixelWidth;
        double startPitch = pitch + startYaw / settings.getRatio() * -1;
        double pitchDecrement = (settings.getFOV() / settings.getRatio()) / pixelHeight;
        System.out.println("Rendering " + pixelHeight + "x" + pixelWidth + " image with start yaw" + startYaw + " and yaw increment  " + yawIncrement + " and start pitch " + startPitch+ " and pitch decrement " + pitchDecrement);

        Color[][] pixels = new Color[pixelHeight][pixelWidth];

        for (int i=0; i<pixelHeight; i++) {
            for (int j=0; j<pixelWidth; j++) {
                double localPitch = startPitch - i * pitchDecrement;
                double localYaw = startYaw + j * yawIncrement;
                // System.out.println("Rendering pixel (" + i + ", " + j + ") with yaw " + yaw + " and pitch " + pitch);
                Color color = new Color(0.);
                // System.out.println("Ray: " + generateRay(yaw, pitch));
                for (int k=0; k<settings.getSamples(); k++) {
                    Ray ray = Ray.fromYawPitch(localYaw + Math.random() * yawIncrement, localPitch - Math.random() * pitchDecrement, position);
                    color = color.add(ray.getColor(scene, 25));
                }
                color = color.div(settings.getSamples());
                color = color.gamma(settings.getGamma());
                pixels[i][j] = color;
            }
        }

        return pixels;
    }
}
