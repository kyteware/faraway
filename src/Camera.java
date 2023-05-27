public class Camera {
    private Vec3 position;
    private Vec3 dir;
    private CameraSettings settings;

    public Camera(Vec3 dir, Vec3 position) {
        this.dir = dir;
        this.position = position;
        this.settings = new CameraSettings();
    }

    public CameraSettings getSettings() {
        return settings;
    }

    public Color[][] render(Scene scene) {
        int pixelHeight = settings.getPixelHeight();
        int pixelWidth = settings.getPixelWidth();
        Color[][] pixels = new Color[pixelHeight][pixelWidth];

        for (int i=0; i<pixelHeight; i++) {
            for (int j=0; j<pixelWidth; j++) {
                Ray baseRay = generateBaseRay(i, j, pixelHeight, pixelWidth);
                Color color = new Color(0.);
                for (int k=0; k<settings.getSamples(); k++) {
                    Ray aaRay = generateAARay(baseRay, settings.getWidth() / pixelWidth, settings.getHeight() / pixelHeight);
                    color = color.add(aaRay.getColor(scene, 25));
                }
                color = color.div(settings.getSamples());
                // gamma
                color = color.pow(1./settings.getGamma());
                pixels[i][j] = color;
            }
        }

        return pixels;
    }

    private Ray generateBaseRay(int i, int j, int ph, int pw) {
        return new Ray(
            new Vec3(
                dir.getX() + (double)(j-pw/2) / (pw/settings.getWidth()),
                dir.getY() - (double)(i-ph/2) / (ph/settings.getHeight()),
                1.
            ),
            position
        );
    }

    private Ray generateAARay(Ray base, double ah, double av) {
        return new Ray(
            base.getDir().add(
                new Vec3(
                    Math.random() * ah,
                    Math.random() * av,
                    0.
                )
            ).normalize()
        );
    }
}
