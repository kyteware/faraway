public abstract class Light {
    public abstract Color contribution(Ray oldRay, Triangle currentTriangle, Scene scene);
}
