public class Camera {
    private Vec3 s;
    private Vec3 m;

    public Camera(Vec3 s, Vec3 m) {
        this.s = s;
        this.m = m;
    }

    public Vec3[][] render(Triangle[] triangles, int width, int height) {
        Vec3[][] pixels = new Vec3[height][width];

        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                Vec3 localM = new Vec3(
                    m.getX() - ((double)(j-width/2) * 0.001),
                    m.getY() - ((double)(i-height/2) * 0.001),
                    1.
                );

                double closestDist = Double.POSITIVE_INFINITY;
                Triangle closestTriangle = null;
                for (Triangle t : triangles) {
                    Vec4 plane = t.toPlane();
                    Vec3 intercept = plane.intercept(localM, s);
                    double distance = s.distance(intercept);
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
