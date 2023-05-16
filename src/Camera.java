public class Camera {
    private Vec3 position;
    private Vec3 dir;
    private CameraSettings settings;

    public Camera(Vec3 dir, Vec3 position) {
        this.dir = dir;
        this.position = position;
    }

    public CameraSettings getSettings() {
        return settings;
    }

    public Vec3[][] render(Triangle[] triangles) {
        int width = 1000;
        int height = 1000;
        Vec3[][] pixels = new Vec3[height][width];

        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                // get closest triangle
                double closestDist = Double.POSITIVE_INFINITY;
                Triangle closestTriangle = null;
                for (Triangle t : triangles) {
                    Ray ray = new Ray(
                        new Vec3(
                            dir.getX() - ((double)(j-width/2) * 0.001),
                            dir.getY() - ((double)(i-height/2) * 0.001),
                            1.
                        ),
                        position
                    );
                    Vec4 plane = t.toPlane();
                    Vec3 intercept = ray.intercept(plane);
                    double distance = position.distance(intercept);
                    if (intercept != null && distance < closestDist && t.containsPoint(intercept)) {
                        closestDist = distance;
                        closestTriangle = t;
                    }
                }

                // get raw color from rays
                Vec3 color;
                if (closestTriangle != null) {
                    color = closestTriangle.getColor();
                }
                else {
                    color = new Vec3(0.);
                }

                pixels[i][j] = color;
            }
        }

        return pixels;
    }
}
