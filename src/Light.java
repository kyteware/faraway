public abstract class Light {
    public abstract Color contribution(Vec3 intercept, Triangle currentTriangle, Scene scene);
}
