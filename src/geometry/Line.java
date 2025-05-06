// 325516193 Cathrine Abu-Elazam
package arkanoid.geometry;

import java.util.List;

/**
 * This package contains classes related to geometric shapes and operations.
 * It includes classes for points, lines, and other geometric entities.
 * arkanoid.geometry.Line class represents a line segment connecting two points: a start point and an end point.
 */
public class Line {
    private static final double THRESHOLD = 0.0001;

    private final Point startP;
    private final Point endP;

    /**
     * Constructs arkanoid.geometry.Line with the specified coordinates for the start and end points.
     *
     * @param x1 the x-coordinate of the start point
     * @param y1 the y-coordinate of the start point
     * @param x2 the x-coordinate of the end point
     * @param y2 the y-coordinate of the end point
     */
    public Line(final double x1, final double y1, final double x2, final double y2) {
        this.startP = new Point((int) x1, (int) y1);
        this.endP = new Point((int) x2, (int) y2);
    }

    /**
     * Constructs arkanoid.geometry.Line with the specified coordinates for the start and end points.
     *
     * @param upperLeft  point of the line
     * @param upperRight point of the line
     */
    public Line(Point upperLeft, Point upperRight) {
        this.startP = upperLeft;
        this.endP = upperRight;
    }

    /**
     * Calculate the middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        double midX = (this.startP.getX() + this.endP.getX()) / 2;
        double midY = (this.startP.getY() + this.endP.getY()) / 2;
        return new Point((int) midX, (int) midY);
    }

    /**
     * Returns the starting point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return startP;
    }

    /**
     * Returns the ending point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return endP;
    }

    /**
     * Is intersecting boolean.
     * the method return true if two points intersecting or false otherwise by using intersectingWith method.
     *
     * @param other the other line
     * @return the boolean
     */
    public boolean isIntersecting(Line other) {
        if (!this.equals(other) && !this.isOverlap(other)) {
            return this.intersectionWith(other) != null;
        } else {
            return true;
        }

    }

    /**
     * if there is intersection point calculate it.
     *
     * @param other the other line that could intersect with this line
     * @return the intersection point if the lines intersect,and null otherwise.
     */
    public Point intersectionWith(final Line other) {
        if (other == null) {
            return null;
        }
        if (equals(other) || isOverlap(other)) {
            return null;
        }
        // this line segment shares an endpoint with the other line segment
        if ((Math.abs(this.startP.getX() - other.startP.getX()) < THRESHOLD
                && Math.abs(this.startP.getY() - other.startP.getY()) < THRESHOLD)
                || (Math.abs(this.startP.getX() - other.endP.getX()) < THRESHOLD
                && Math.abs(this.startP.getY() - other.endP.getY()) < THRESHOLD)) {
            return new Point(this.startP.getX(), this.startP.getY());
        } else if ((Math.abs(this.endP.getX() - other.startP.getX()) < THRESHOLD
                && Math.abs(this.endP.getY() - other.startP.getY()) < THRESHOLD)
                || (Math.abs(this.endP.getX() - other.endP.getX()) < THRESHOLD
                && Math.abs(this.endP.getY() - other.endP.getY()) < THRESHOLD)) {
            return new Point(this.endP.getX(), this.endP.getY());
        }

        // Check if either line is vertical, because we do not want to divide by zero
        boolean thisVertical = Math.abs(this.startP.getX() - this.endP.getX()) < THRESHOLD;
        boolean otherVertical = Math.abs(other.startP.getX() - other.endP.getX()) < THRESHOLD;

        double m1 = 0, m2 = 0, b1 = 0, b2 = 0;

        if (!thisVertical) {
            m1 = (this.startP.getY() - this.endP.getY()) / (this.startP.getX() - this.endP.getX());
            b1 = this.startP.getY() - m1 * this.startP.getX();
        }

        if (!otherVertical) {
            m2 = (other.startP.getY() - other.endP.getY()) / (other.startP.getX() - other.endP.getX());
            b2 = other.startP.getY() - m2 * other.startP.getX();
        }
        double x, y;

        if (thisVertical) {
            x = this.startP.getX();
            y = otherVertical ? Double.NaN : m2 * x + b2; // if other is also vertical, lines are parallel
        } else if (otherVertical) {
            x = other.startP.getX();
            y = m1 * x + b1;
        } else {
            if (Math.abs(m1 - m2) < 1e-10) {
                return null; // Lines are parallel
            }
            x = (b2 - b1) / (m1 - m2);
            y = m1 * x + b1;
        }

        // Check if the intersection point lies within the range of both lines,
        // If it is, the method returns the intersection point. Otherwise, the method returns null.
        boolean xInRange = (x >= Math.min(this.startP.getX(), this.endP.getX())
                && x <= Math.max(this.startP.getX(), this.endP.getX()))
                && (x >= Math.min(other.startP.getX(), other.endP.getX())
                && x <= Math.max(other.startP.getX(), other.endP.getX()));

        boolean yInRange = (y >= Math.min(this.startP.getY(), this.endP.getY())
                && y <= Math.max(this.startP.getY(), this.endP.getY()))
                && (y >= Math.min(other.startP.getY(), other.endP.getY())
                && y <= Math.max(other.startP.getY(), other.endP.getY()));

        if (xInRange && yInRange) {
            return new Point((int) x, (int) y); // Intersection point lies within the range of both lines
        } else {
            return null; // Intersection point is outside the range of one or both lines
        }
    }

    /**
     * Check if this line and other line equal.
     *
     * @param other the other line thar could be equal to this line
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(final Line other) {

        // Calculate the differences between coordinates
        double startXDiff = Math.abs(this.startP.getX() - other.startP.getX());
        double startYDiff = Math.abs(this.startP.getY() - other.startP.getY());
        double endXDiff = Math.abs(this.endP.getX() - other.endP.getX());
        double endYDiff = Math.abs(this.endP.getY() - other.endP.getY());
        double startEndXDiff = Math.abs(this.startP.getX() - other.endP.getX());
        double startEndYDiff = Math.abs(this.startP.getY() - other.endP.getY());
        double endStartXDiff = Math.abs(this.endP.getX() - other.startP.getX());
        double endStartYDiff = Math.abs(this.endP.getY() - other.startP.getY());

        // Check if all differences are within the threshold
        boolean startPointsEqual = startXDiff < THRESHOLD && startYDiff < THRESHOLD;
        boolean endPointsEqual = endXDiff < THRESHOLD && endYDiff < THRESHOLD;
        boolean startEndPointsEqual = startEndXDiff < THRESHOLD && startEndYDiff < THRESHOLD;
        boolean endStartPointsEqual = endStartXDiff < THRESHOLD && endStartYDiff < THRESHOLD;

        // Lines are equal if both start and end points are equal
        return (startPointsEqual && endPointsEqual) || (startEndPointsEqual && endStartPointsEqual);
    }

    /**
     * Checks if this line overlaps with another line.
     *
     * @param other the other line
     * @return true if the lines overlap, false otherwise
     */
    public boolean isOverlap(Line other) {
        // Check if both lines are vertical and have the same x-coordinate
        if (Math.abs(this.startP.getX() - this.endP.getX()) < THRESHOLD
                && Math.abs(this.endP.getX() - other.startP.getX()) < THRESHOLD
                && Math.abs(other.startP.getX() - other.endP.getX()) < THRESHOLD) {
            // Check if they overlap on the y-axis
            if ((other.startP.getY() <= this.startP.getY() && this.startP.getY() <= other.endP.getY())
                    || (other.endP.getY() <= this.startP.getY() && this.startP.getY() <= other.startP.getY())
                    || (this.startP.getY() <= other.startP.getY() && other.startP.getY() <= this.endP.getY())
                    || (this.endP.getY() <= other.startP.getY() && other.startP.getY() <= this.startP.getY())) {
                return true;
            }
        }
        // Check if both lines are horizontal and have the same y-coordinate
        if (Math.abs(this.startP.getY() - this.endP.getY()) < THRESHOLD
                && Math.abs(this.endP.getY() - other.startP.getY()) < THRESHOLD
                && Math.abs(other.startP.getY() - other.endP.getY()) < THRESHOLD) {
            // Check if they overlap on the x-axis
            return (other.startP.getX() <= this.startP.getX() && this.startP.getX() <= other.endP.getX())
                    || (other.endP.getX() <= this.startP.getX() && this.startP.getX() <= other.startP.getX())
                    || (this.startP.getX() <= other.startP.getX() && other.startP.getX() <= this.endP.getX())
                    || (this.endP.getX() <= other.startP.getX() && other.startP.getX() <= this.startP.getX());
        }
        return false;
    }


    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect the rectangles that might have an intersection points with this line.
     * @return The closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line line = new Line(this.startP.getX(), this.startP.getY(), this.endP.getX(), this.endP.getY());
        if (rect == null) {
            return null;
        }
        List<Point> intersectionPoints = rect.intersectionPoints(line);

        // If there are no intersection points, return null
        if (intersectionPoints == null) {
            return null;
        }

        // Find the closest intersection point to the start of the line
        Point closestPoint = null;
        double closestDistance = Double.MAX_VALUE;

        for (Point point : intersectionPoints) {
            double distance = this.startP.distance(point);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestPoint = point;
            }
        }
        return closestPoint;
    }
}