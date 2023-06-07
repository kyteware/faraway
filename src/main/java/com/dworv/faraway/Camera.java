package com.dworv.faraway;

/**
 * Camera is used to represent a camera in the scene.
 * <p>
 * It is defined by a position, a yaw, and a pitch. The position is a 3D vector that represents the position of the camera in the scene.
 * The yaw and pitch are numbers that represent the direction the camera is facing. The yaw is the angle between the camera's forward direction and the x-axis, and the pitch is the angle between the camera's forward direction and the y-axis.
 * The camera also has settings, which are used to determine how the camera renders the scene.
 * Different cameras in the scene can have different settings.
 * @author github.com/dworv
 */
public class Camera {
    /**
     * The position of the camera.
     * <p>
     * This is a Vec3 object, which is a 3D vector with x, y, and z components.
     */
    private Vec3 position;
    /**
     * The settings of the camera.
     * <p>
     * This is a CameraSettings object, which contains the settings of the camera.
     */
    private CameraSettings settings;
    /**
     * The yaw of the camera.
     * <p>
     * This is a number that represents the direction the camera is facing.
     * The yaw is the angle between the camera's forward direction and the x-axis.
     */
    private double yaw;
    /**
     * The pitch of the camera.
     * <p>
     * This is a number that represents the direction the camera is facing.
     * The pitch is the angle between the camera's forward direction and the y-axis.
     */
    private double pitch;

    /**
     * Create a new Camera.
     * <p>
     * Example:
     * <pre>
     * Camera camera = new Camera(new Vec3(0, 0, 0), 0, 0); // camera at (0, 0, 0) facing forward
     * </pre>
     * @param position the position of the camera
     * @param yaw the yaw of the camera
     * @param pitch the pitch of the camera
     */
    public Camera(Vec3 position, double yaw, double pitch) {
        this.position = position;
        this.settings = new CameraSettings();
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public String toString() {
        return "Camera(" + position + ", " + yaw + ", " + pitch + ")";
    }

    /**
     * Get the settings of the camera.
     * <p>
     * Example:
     * <pre>
     * Settings camSettings = camera.getSettings();
     * </pre>
     * @return the settings for the camera
     */
    public CameraSettings getSettings() {
        return settings;
    }

    /**
     * Renders the scene from the camera's perspective.
     * <p>
     * This method uses the contents of the scene to produce the colors of the pixels in the image.
     * It does this by generating a ray for each pixel in the image, and then using the ray to determine the color of the pixel.
     * <p>
     * Pseudocode:
     * <ol>
     * <li>For each pixel in the image:</li>
     * <li>Generate a ray from the camera to the pixel</li>
     * <li>For each triangle in the scene:</li>
     * <li>Find the intersection of the ray and the triangle</li>
     * <li>If the intersection is closer than the previous closest intersection, set the color of the pixel to the color of the triangle</li>
     * </ol>
     * <p>
     * Example:
     * <pre>
     * Color[][] pixels = camera.render(scene);
     * </pre>
     * @param scene the scene to render
     * @return the colors of the pixels in the image
     */
    public Color[][] render(Scene scene) {
        // Get the height and width of the image
        int pixelHeight = settings.getPixelHeight();
        int pixelWidth = settings.getPixelWidth();

        // Get the pitch and yaw of the camera for pixel (0,0)
        double startYaw = yaw + -1 * settings.getFOV() / 2;
        double yawIncrement = settings.getFOV() / pixelWidth;
        // Get the increment for the pitch and the yaw for each pixel change
        double startPitch = pitch + startYaw / settings.getRatio() * -1;
        double pitchDecrement = (settings.getFOV() / settings.getRatio()) / pixelHeight;
        // Generate 2d array of pixels
        Color[][] pixels = new Color[pixelHeight][pixelWidth];
        for (int i=0; i<pixelHeight; i++) {
            for (int j=0; j<pixelWidth; j++) {
                // Use starting pitch and yaw with the increments to deduce the current angle
                double localPitch = startPitch - i * pitchDecrement;
                double localYaw = startYaw + j * yawIncrement;
                // Create base color
                Color color = new Color(0.);
                // Get all samples for the pixel (multisampling/antiaaliasing)
                for (int k=0; k<settings.getSamples(); k++) {
                    // Get the ray of the sample
                    Ray ray = Ray.fromYawPitch(localYaw + Math.random() * yawIncrement, localPitch - Math.random() * pitchDecrement, position);
                    // Add to the base color
                    color = color.add(ray.getColor(scene, 25));
                }
                // Average the color and apply gamma correction
                color = color.div(settings.getSamples()).gamma(settings.getGamma());
                pixels[i][j] = color;
            }
        }

        return pixels;
    }
}
