public abstract class Light {
    public abstract Color contribution(Vec3 intercept, Vec3 normal, Triangle[] triangles, Triangle currentTriangle);
}
