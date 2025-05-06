// 325516193 Cathrine Abu-Elazam
package arkanoid.geometry;

/**
 * The arkanoid.geometry.Point class represents a point in a two-dimensional space.
 */
public class Point {
    private double x; // The x-coordinate of the point
    private double y; // The y-coordinate of the point

    /**
     * Constructs arkanoid.geometry.Point with the specified coordinates.
     *
     * @param x represents the x-coordinate of the point.
     * @param y represents the y-coordinate of the point.
     */
    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x value of this point.
     *
     * @return the x value of this point.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y value of this point.
     *
     * @return the y value of this point.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the y value of this point.
     *
     * @param y the new y value of this point.
     */
    public void setY(final double y) {
        this.y = y;
    }

    /**
     * Sets the x value of this point.
     *
     * @param x the new x value of this point.
     */
    public void setX(final double x) {
        this.x = x;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other the other point to which the distance is calculated.
     * @return the distance of this point to the other point.
     */
    public double distance(final Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Check if this point equals to another point.
     *
     * @param other the other point to witch is equal to this point
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(final Point other) {
        return (this.x == other.x) && (this.y == other.y);
    }
}
