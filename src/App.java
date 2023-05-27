public class App {
    public static void main(String[] args) throws Exception {
        Texture floorTexture = new Texture(new Color(1), 0);
        Texture greenTexture = new Texture(new Color(0.5, 1, 0.5), 0);

        Triangle floor1 = new Triangle(
            new Vec3(-1, -1, 3),
            new Vec3(1, -1, 3),
            new Vec3(1, 1, 5),
            floorTexture
        );
        Triangle floor2 = new Triangle(
            new Vec3(-1, 1, 5),
            new Vec3(1, 1, 5),
            new Vec3(-1, -1, 3),
            floorTexture
        );
        Triangle greenTriangle = new Triangle(
            new Vec3(0.7, 0.7, 2.5),
            new Vec3(-0.7, 0.7, 2.5),
            new Vec3(0.7, 2, 2.5),
            greenTexture
        );

        Triangle[] triangles = { floor1, floor2, greenTriangle };

        Camera c = new Camera(
            new Vec3(0., 0., 1.),
            new Vec3(0.)
        );

        c.getSettings().setSamples(25);
        c.getSettings().setPixelHeight(720);

        Camera[] cameras = { c };

        Color background = new Color(0.2, 0.3, 0.5);

        Light l1 = new PointLight(
            new Vec3(-1.5, 0, 0),
            new Color(1, 1, 1),
            1.5
        );

        Light l2 = new AmbientLight(background, 0.1);

        Light[] lights = { l1, l2 };

        Scene scene = new Scene(cameras, triangles, lights, background);

        scene.render_cams();
    }
}
