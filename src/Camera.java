public class Camera {
    private Vec3 position;
    private Vec3 dir;

    public Camera(Vec3 dir, Vec3 position) {
        this.dir = dir;
        this.position = position;
    }

    public Vec3[][] render(Triangle[] triangles, int width, int height) {
        Vec3[][] pixels = new Vec3[height][width];

        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
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

                if (closestTriangle != null) {
                    pixels[i][j] = closestTriangle.getColor();
                }
                else {
                    pixels[i][j] = new Vec3(0.);
                }
            }
        }

        return pixels;
    }
}
