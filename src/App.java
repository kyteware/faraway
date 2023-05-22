public class App {
    public static void main(String[] args) throws Exception {
        Triangle t1 = new Triangle(
            new Vec3(-1., 0., 2.), 
            new Vec3(0., -1., 3.), 
            new Vec3(1., 0., 2.),
            new Vec3(0.5, 0.5, 0.2)
        );

        Triangle t2 = new Triangle(
            new Vec3(-2.5, -1., 2.), 
            new Vec3(1., -1., 2.5), 
            new Vec3(1., 1., 2.5),
            new Vec3(0.2, 0.5, 0.2)
        );

        Triangle t3 = new Triangle(
            new Vec3(-1., 0., 2.), 
            new Vec3(0., 1., 3.), 
            new Vec3(1., 0., 2.),
            new Vec3(0.2, 0.2, 0.5)
        );

        Triangle[] triangles = { t1, t2, t3 };

        Camera c = new Camera(
            new Vec3(0., 0., 1.),
            new Vec3(0.)
        );

        c.getSettings().setSamples(5);

        Camera[] cameras = { c };

        Vec3 background = new Vec3(0.4, 0.6, 1.);

        Light l1 = new PointLight(
            new Vec3(-3.3, -1., 0.5),
            new Vec3(1, 1, 1),
            1.2
        );

        Light l2 = new AmbientLight(background, 0.1);

        Light[] lights = { l1, l2 };

        Scene scene = new Scene(cameras, triangles, lights, background);

        scene.render_cams();
    }
}
