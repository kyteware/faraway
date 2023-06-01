package com.dworv.faraway;

/*        +++
 *     g
 *  f  |  h
 *  | e|  |
 *  | |c  |
 *  b |   d
 *    a
 * ---
 */
class Cube extends Shape {
    private Vec3 center;
    private double size;
    private Texture texture;

    Cube(Vec3 center, double size, Texture texture) {
        this.center = center;
        this.size = size;
        this.texture = texture;
    }

    @Override
    Triangle[] toTriangles() {
        Vec3 a = new Vec3(center.getX() - size / 2, center.getY() - size / 2, center.getZ() - size / 2);
        Vec3 b = new Vec3(center.getX() + size / 2, center.getY() - size / 2, center.getZ() - size / 2);
        Vec3 c = new Vec3(center.getX() + size / 2, center.getY() + size / 2, center.getZ() - size / 2);
        Vec3 d = new Vec3(center.getX() - size / 2, center.getY() + size / 2, center.getZ() - size / 2);
        Vec3 e = new Vec3(center.getX() - size / 2, center.getY() - size / 2, center.getZ() + size / 2);
        Vec3 f = new Vec3(center.getX() + size / 2, center.getY() - size / 2, center.getZ() + size / 2);
        Vec3 g = new Vec3(center.getX() + size / 2, center.getY() + size / 2, center.getZ() + size / 2);
        Vec3 h = new Vec3(center.getX() - size / 2, center.getY() + size / 2, center.getZ() + size / 2);
        Triangle[] triangles = {
            new Triangle(a, b, c, texture),
            new Triangle(a, c, d, texture),
            new Triangle(a, e, f, texture),
            new Triangle(a, f, b, texture),
            new Triangle(b, f, g, texture),
            new Triangle(b, g, c, texture),
            new Triangle(c, g, h, texture),
            new Triangle(c, h, d, texture),
            new Triangle(d, h, e, texture),
            new Triangle(d, e, a, texture),
            new Triangle(e, h, g, texture),
            new Triangle(e, g, f, texture),
        };
        return triangles;
    }
    
}
