package com.dworv.faraway;

public abstract class Shape {
    Vec3 position;
    Triangle[] getTriangles() {
        Triangle[] triangles = toTriangles();
        Triangle[] transformed = new Triangle[triangles.length];
        for (int i = 0; i < triangles.length; i++) {
            Triangle moved = new Triangle(
                triangles[i].getA().add(position), 
                triangles[i].getB().add(position), 
                triangles[i].getC().add(position), 
                triangles[i].getTexture()
            );
            transformed[i] = moved;
        }
        return transformed;
    }
    abstract Triangle[] toTriangles();
}
