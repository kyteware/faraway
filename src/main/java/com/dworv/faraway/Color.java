package com.dworv.faraway;

/**
 * Color is used to represent a color.
 * <p>
 * It is defined by a red, green, and blue component.
 * Each component is a number between 0 and 1 that represents how much of that color is present.
 * For example, if the red component is 1 and the green and blue components are 0, then the color will be red.
 * @author github.com/dworv
 */
public class Color {
    /**
     * The red component of the color.
     * <p>
     * This is a number between 0 and 1 that represents how much red is present in the color.
     */
    private double r;
    /**
     * The green component of the color.
     * <p>
     * This is a number between 0 and 1 that represents how much green is present in the color.
     */
    private double g;
    /**
     * The blue component of the color.
     * <p>
     * This is a number between 0 and 1 that represents how much blue is present in the color.
     */
    private double b;

    /**
     * Create a new Color.
     * <p>
     * Example:
     * <pre>
     * Color color = new Color(1, 0, 0);
     * </pre>
     * @param r the red component of the color
     * @param g the green component of the color
     * @param b the blue component of the color
     */
    public Color(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Create a new Color where all components are the same.
     * <p>
     * Example:
     * <pre>
     * Color color = new Color(1);
     * </pre>
     * @param all the red, green, and blue components of the color
     */
    public Color(double all) {
        r = all;
        b = all;
        g = all;
    }

    public String toString() {
        return "Color(" + r + ", " + g + ", " + b + ")";
    }

    /**
     * Add two colors together.
     * <p>
     * Example:
     * <pre>
     * Color color1 = new Color(1, 0, 0);
     * Color color2 = new Color(0, 1, 0);
     * 
     * Color color3 = color1.add(color2);
     * </pre>
     * @param other the other color to add
     * @return the sum of the two colors
     */
    public Color add(Color other) {
        return new Color(r + other.r, g + other.g, b + other.b);
    }

    /**
     * Add a number to all components of the color.
     * <p>
     * Example:
     * <pre>
     * Color color1 = new Color(1, 0, 0);
     * 
     * Color color2 = color1.add(0.5);
     * </pre>
     * @param other the number to add
     * @return the sum of the color and the number
     */
    public Color add(double other) {
        return new Color(r + other, g + other, b + other);
    }

    /**
     * Subtract two colors.
     * <p>
     * Example:
     * <pre>
     * Color color1 = new Color(1, 0, 0);
     * Color color2 = new Color(0, 1, 0);
     * 
     * Color color3 = color1.sub(color2);
     * </pre>
     * @param other the other color to subtract
     * @return the difference of the two colors
     */
    public Color sub(Color other) {
        return new Color(r - other.r, g - other.g, b - other.b);
    }

    /**
     * Subtract a number from all components of the color.
     * <p>
     * Example:
     * <pre>
     * Color color1 = new Color(1, 0, 0);
     * 
     * Color color2 = color1.sub(0.5);
     * </pre>
     * @param other the number to subtract
     * @return the difference of the color and the number
     */
    public Color sub(double other) {
        return new Color(r - other, g - other, b - other);
    }

    /**
     * Multiply two colors together.
     * <p>
     * Example:
     * <pre>
     * Color color1 = new Color(1, 0, 0);
     * Color color2 = new Color(0, 1, 0);
     * 
     * Color color3 = color1.dot(color2);
     * </pre>
     * @param other the other color to multiply
     * @return the product of the two colors
     */
    public Color dot(Color other) {
        return new Color(r * other.r, g * other.g, b * other.b);
    }

    /**
     * Multiply all components of the color by a number.
     * <p>
     * Example:
     * <pre>
     * Color color1 = new Color(1, 0, 0);
     * 
     * Color color2 = color1.dot(0.5);
     * </pre>
     * @param other the number to multiply
     * @return the product of the color and the number
     */
    public Color dot(double other) {
        return new Color(r * other, g * other, b * other);
    }

    /**
     * Divide two colors.
     * <p>
     * Example:
     * <pre>
     * Color color1 = new Color(1, 0, 0);
     * Color color2 = new Color(0, 1, 0);
     * 
     * Color color3 = color1.div(color2);
     * </pre>
     * @param other the other color to divide
     * @return the quotient of the two colors
     */
    public Color div(Color other) {
        return new Color(r / other.r, g / other.g, b / other.b);
    }

    /**
     * Divide all components of the color by a number.
     * <p>
     * Example:
     * <pre>
     * Color color1 = new Color(1, 0, 0);
     * 
     * Color color2 = color1.div(0.5);
     * </pre>
     * @param other the number to divide
     * @return the quotient of the color and the number
     */
    public Color div(double other) {
        return new Color(r / other, g / other, b / other);
    }

    /**
     * Raise the color to a power.
     * <p>
     * Example:
     * <pre>
     * Color color1 = new Color(1, 0, 0);
     * Color color2 = new Color(0, 1, 0);
     * 
     * Color color3 = color1.pow(color2);
     * </pre>
     * @param other the other color to raise to
     * @return the color raised to the power of the other color
     */
    public Color pow(Color other) {
        return new Color(Math.pow(r, other.r), Math.pow(g, other.g), Math.pow(b, other.b));
    }

    /**
     * Raise all components of the color to a power.
     * <p>
     * Example:
     * <pre>
     * Color color1 = new Color(1, 0, 0);
     * 
     * Color color2 = color1.pow(0.5);
     * </pre>
     * @param other the number to raise to
     * @return the color raised to the power of the number
     */
    public Color pow(double other) {
        return new Color(Math.pow(r, other), Math.pow(g, other), Math.pow(b, other));
    }

    /**
     * Limit the color to the range [0, 1].
     * <p>
     * Example:
     * <pre>
     * Color color1 = new Color(-1, 0.5, 2);
     * Color color2 = color1.cap(); // (-1, 0.5, 1)
     * </pre>
     * @return the color with all components capped to the range [0, 1]
     */
    public Color cap() {
        return new Color(
            Math.max(0, Math.min(1, r)),
            Math.max(0, Math.min(1, g)),
            Math.max(0, Math.min(1, b))
        );
    }

    /**
     * Convert a color in a linear range to a color in a gamma range.
     * <p>
     * Example:
     * <pre>
     * Color color1 = new Color(0.5, 0.5, 0.5);
     * Color color2 = color1.gamma(2.2); // (0.218, 0.218, 0.218)
     * </pre>
     * @param linear the linear value to convert to gamma
     * @return the color in a gamma range
     */
    public Color gamma(double linear) {
        return new Color(
            Math.pow(r, 1. / linear),
            Math.pow(g, 1. / linear),
            Math.pow(b, 1. / linear)
        );
    }

    /**
     * Convert a color in a gamma range to a color in a linear range.
     * <p>
     * Example:
     * <pre>
     * Color color1 = new Color(0.218, 0.218, 0.218);
     * Color color2 = color1.linear(2.2); // (0.5, 0.5, 0.5)
     * </pre>
     * @param gamma the gamma value to convert to linear
     * @return the color in a linear range
     */
    public Color linear(double gamma) {
        return new Color(
            Math.pow(r, gamma),
            Math.pow(g, gamma),
            Math.pow(b, gamma)
        );
    }

    /**
     * Convert a color to a packed integer.
     * <p>
     * Pseudocode:
     * <ol>
     * <li>Encode the red component as an integer in the range [0, 255]</li>
     * <li>Shift the red component 8 bits to the left</li>
     * <li>Encode the green component as an integer in the range [0, 255]</li>
     * <li>Shift the green component 8 bits to the left</li>
     * <li>Encode the blue component as an integer in the range [0, 255]</li>
     * <li>Shift the blue component 8 bits to the left</li>
     * <li>Encode the alpha component as an integer in the range [0, 255]</li>
     * </ol>
     * Example:
     * <pre>
     * Color color1 = new Color(1, 0, 0);
     * int packed = color1.encode(); // 0xFFFF0000
     * </pre>
     * @return the packed integer
     */
    public int encode() {
        int encoded = 0;
        encoded += 255;
        encoded <<= 8;
        encoded += (int) (r * 255.);
        encoded <<= 8;
        encoded += (int) (g * 255.);
        encoded <<= 8;
        encoded += (int) (b * 255.);
        return encoded;
    }
}
