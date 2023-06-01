package com.dworv.faraway;

public class Triangle extends Shape {
    private Vec3 a;
    private Vec3 b;
    private Vec3 c;
    private Texture texture;

    public Triangle(Vec3 a, Vec3 b, Vec3 c, Texture texture) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.texture = texture;
    }

    public Triangle(Vec3 a, Vec3 b, Vec3 c) {
        this(a, b, c, new Texture(new Color(1), 0));
    }

    public String toString() {
        return "Triangle(" + a + ", " + b + ", " + c + ", " + texture + ")";
    }

    public Vec3 getA() {
        return a;
    }

    public Vec3 getB() {
        return b;
    }

    public Vec3 getC() {
        return c;
    }

    public Texture getTexture() {
        return texture;
    }

    public Vec3 getNormal() {
        Vec3 line1 = b.sub(a);
        Vec3 line2 = c.sub(a);
        return line2.cross(line1).normalize();
    }

    public Vec3 getNormalFacing(Vec3 point) {
        Vec3 normal = getNormal();
        if (normal.dot(point.sub(a)).sum() > 0) {
            return normal;
        } else {
            return normal.dot(-1.);
        }
    }

    public Plane toPlane() {
        Vec3 normal = getNormal();
        double k = new Vec3(-1.).dot(normal).dot(a).sum();
        return new Plane(
            normal.getX(),
            normal.getY(),
            normal.getZ(),
            k
        );
    }

    public Plane toPlaneFacing(Vec3 point) {
        Vec3 normal = getNormalFacing(point);
        double k = new Vec3(-1.).dot(normal).dot(a).sum();
        return new Plane(
            normal.getX(),
            normal.getY(),
            normal.getZ(),
            k
        );
    }

    public boolean containsPoint(Vec3 point) {
        Vec3[][] configs = { { a, b, c }, { b, c, a }, { c, a, b } };
        for (Vec3[] config : configs) {
            Vec3 p1 = config[0];
            Vec3 p2 = config[1];
            Vec3 p3 = config[2];

            Vec3 line = p2.sub(p3);
            Vec3 check = line.cross(point.sub(p3));
            Vec3 known = line.cross(p1.sub(p3));
            if (check.dot(known).sum() <= 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    Triangle[] toTriangles() {
        return new Triangle[] { this };
    }
}
