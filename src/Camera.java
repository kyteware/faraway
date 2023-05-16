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

    public Vec3[][] render(Triangle[] triangles, Vec3 background) {
        int pixelHeight = (int) (settings.getHeight() * 1000);
        int pixelWidth = (int) (settings.getWidth() * 1000);
        Vec3[][] pixels = new Vec3[pixelHeight][pixelWidth];

        for (int i=0; i<pixelHeight; i++) {
            for (int j=0; j<pixelWidth; j++) {
                // get closest triangle
                double closestDist = Double.POSITIVE_INFINITY;
                Triangle closestTriangle = null;
                for (Triangle t : triangles) {
                    Ray ray = new Ray(
                        new Vec3(
                            dir.getX() + (double)(j-pixelWidth/2) / (pixelWidth/settings.getWidth()),
                            dir.getY() - (double)(i-pixelHeight/2) / (pixelHeight/settings.getHeight()),
                            1.
                        ),
                        position
                    );
                    if (ray.getDir().getX() < -2.) {
                        System.out.println(ray);
                    }
                    Vec4 plane = t.toPlane();
                    Vec3 intercept = ray.intercept(plane);
                    if (intercept != null) {
                        double distance = position.distance(intercept);
                        if (distance < closestDist && t.containsPoint(intercept)) {
                            closestDist = distance;
                            closestTriangle = t;
                        }
                    }
                }

                // get raw color from rays
                Vec3 color;
                if (closestTriangle != null) {
                    color = closestTriangle.getColor();
                }
                else {
                    color = background;
                }

                pixels[i][j] = color;
            }
        }

        return pixels;
    }
}
