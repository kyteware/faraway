public abstract class Light {
    public abstract Vec3 contribution(Vec3 intercept, Vec3 normal, Triangle[] triangles);
}
