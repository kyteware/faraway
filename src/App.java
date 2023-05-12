public class App {
    public static void main(String[] args) throws Exception {
        Vec3[][] pixels = new Vec3[100][100];
        for (int i=0; i<100; i++) {
            for (int j=0; j<100; j++) {
                pixels[i][j] = new Vec3(255., 0., 0.);
            }
        }
        Draw.drawImage(pixels);
    }
}
