package com.dworv.faraway;

/**
 * Cube is used to represent a cube in a scene.
 * <p>
 * It is defined by a center point, a size, and a texture.
 * <p>
 * Diagram:
 * <pre>
 * top
 *     g
 *  f  |  h
 *  | e|  |
 *  | |c  |
 *  b |   d
 *    a
 * bottom
 * </pre>
 * @author github.com/dworv
 */
class Cube extends Shape {
    /**
     * The center point of the cube.
     * <p>
     * This is a Vec3 object, which is a 3D vector with x, y, and z components.
     */
    private Vec3 center;
    /**
     * The size of the cube.
     * <p>
     * This is a number that represents the size of the cube.
     */
    private double size;
    /**
     * The texture of the cube.
     * <p>
     * This is a Texture object, which contains the texture of the cube.
     */
    private Texture texture;

    /**
     * Create a new Cube.
     * <p>
     * Example:
     * <pre>
     * Cube cube = new Cube(new Vec3(0, 0, 0), 1, new Texture("textures/stone.png")); // cube at (0, 0, 0) with size 1 and texture "textures/stone.png"
     * </pre>
     * @param center the center point of the cube
     * @param size the size of the cube
     * @param texture the texture of the cube
     */
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

