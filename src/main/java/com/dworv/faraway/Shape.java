package com.dworv.faraway;

/**
 * Shape represents a shape in a scene file.
 * <p>
 * This is an abstract class that is extended by other classes that represent different types of shapes.
 * They all implement the `toTriangles` method, which is used to convert the shape to triangles.
 * @author github.com/dworv
 */
public abstract class Shape {
    /**
     * The position of the shape.
     * <p>
     * This is a Vec3 object.
     */
    Vec3 position;

    /**
     * Get the triangles that make up the shape.
     * <p>
     * This is used to get the triangles that make up the shape (for example 12 in a cube).
     * <p>
     * Example:
     * <pre>
     * Triangle[] triangles = shape.getTriangles();
     * </pre>
     * @return the triangles that make up the shape
     */
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
