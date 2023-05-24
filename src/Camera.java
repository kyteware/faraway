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

    public Color[][] render(Triangle[] triangles, Light[] lights, Color background) {
        int pixelHeight = settings.getPixelHeight();
        int pixelWidth = settings.getPixelWidth();
        Color[][] pixels = new Color[pixelHeight][pixelWidth];

        for (int i=0; i<pixelHeight; i++) {
            for (int j=0; j<pixelWidth; j++) {
                Ray baseRay = generateBaseRay(i, j, pixelHeight, pixelWidth);
                Color color = new Color(0.);
                for (int k=0; k<settings.getSamples(); k++) {
                    Ray aaRay = generateAARay(baseRay, settings.getWidth() / pixelWidth, settings.getHeight() / pixelHeight);
                    Color rayColor = getRayColor(aaRay, triangles, lights, background);
                    color = color.add(rayColor);
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
            )
        );
    }

    private Color getRayColor(Ray ray, Triangle[] triangles, Light[] lights, Color background) {
        double closestDist = Double.POSITIVE_INFINITY;
        Triangle closestTriangle = null;
        Vec3 intercept = null;

        for (Triangle t : triangles) {
            Vec4 plane = t.toPlane();
            Vec3 localIntercept = ray.intercept(plane);
            if (localIntercept != null) {
                double distance = position.distance(localIntercept);
                if (distance < closestDist && t.containsPoint(localIntercept)) {
                    closestDist = distance;
                    closestTriangle = t;
                    intercept = localIntercept;
                }
            }
        }

        Color color = new Color(0.);;
        if (closestTriangle != null) {
            for (Light light : lights) {
                Color contribution = light.contribution(intercept, closestTriangle.getNormal(), triangles, closestTriangle);
                color = color.add(contribution);
            }
            color = color.mul(closestTriangle.getColor()).cap();
        }
        else {
            color = background;
        }

        return color;
    }
}
