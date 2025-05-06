// 325516193 Cathrine Abu-Elazam
package arkanoid.geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a rectangle with a specific position and size.
 */
public class Rectangle {
    private final double xStart;
    private final double yStart;
    private final double width;
    private final double height;

    /**
     * Constructs a new rectangle with the specified parameters.
     *
     * @param xStart The x-coordinate of the top-left corner of the rectangle.
     * @param yStart The y-coordinate of the top-left corner of the rectangle.
     * @param width  The width of the rectangle.
     * @param height The height of the rectangle.
     */
    public Rectangle(double xStart, double yStart, double width, double height) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.width = width;
        this.height = height;
    }

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft The point that represent the top-left corner of the rectangle.
     * @param width     The width of the rectangle.
     * @param height    The height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.xStart = upperLeft.getX();
        this.yStart = upperLeft.getY();
        this.width = width;
        this.height = height;
    }


    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line the line to check for intersections with this rectangle.
     * @return list of the intersection points with the specified line.
     */
    public List<Point> intersectionPoints(Line line) {
        Line topLine = new Line(this.xStart, this.yStart, this.xStart + this.width, this.yStart);
        Line bottomLine = new Line(this.xStart, this.yStart + this.height, this.xStart + this.width,
                this.yStart + this.height);
        Line rightLine = new Line(this.xStart + this.width, this.yStart, this.xStart + this.width,
                this.yStart + height);
        Line leftLine = new Line(this.xStart, this.yStart, this.xStart, this.yStart + this.height);

        ArrayList<Point> pointIntersectionArrayList = new ArrayList<>();

        // Check if the line intersects with any of the rectangle's edges
        Point intersection;
        if (topLine.isIntersecting(line)) {
            intersection = topLine.intersectionWith(line);
            if (intersection != null) {
                pointIntersectionArrayList.add(intersection);

            }
        }
        if (bottomLine.isIntersecting(line)) {
            intersection = bottomLine.intersectionWith(line);
            if (intersection != null) {
                pointIntersectionArrayList.add(intersection);

            }
        }
        if (rightLine.isIntersecting(line)) {
            intersection = rightLine.intersectionWith(line);
            if (intersection != null) {
                pointIntersectionArrayList.add(intersection);

            }
        }
        if (leftLine.isIntersecting(line)) {
            intersection = leftLine.intersectionWith(line);
            if (intersection != null) {
                pointIntersectionArrayList.add(intersection);
            }
        }
        return pointIntersectionArrayList;
    }

    /**
     * Returns the x-coordinate of the top-left corner of the rectangle.
     *
     * @return The x-coordinate of the top-left corner.
     */
    public double getXStart() {
        return xStart;
    }

    /**
     * Returns the y-coordinate of the top-left corner of the rectangle.
     *
     * @return The y-coordinate of the top-left corner.
     */
    public double getYStart() {
        return yStart;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return The width of the rectangle.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return The height of the rectangle.
     */
    public double getHeight() {
        return height;
    }


    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left(start) point of the rectangle.
     */
    public Point getUpperLeft() {
        return new Point(this.xStart, this.yStart);
    }
}

