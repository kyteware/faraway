public class Scene {
    private Camera[] cameras;
    private Triangle[] triangles;

    public Scene(Camera[] cameras, Triangle[] triangles) {
        this.cameras = cameras;
        this.triangles = triangles;
    }

    public void render_cams() {
        for (int i=0; i<cameras.length; i++) {
            String path = "outputs/" + i + ".png";
            Draw.drawImage(null, path);
        }
    }
}
