// 325516193 Cathrine Abu-Elazam
package arkanoid.geometry;

/**
 * arkanoid.geometry.Velocity class represent changing in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor for creating arkanoid.geometry.Velocity object with specified change
     * in position on the x and y axes.
     *
     * @param dx The change in position on the x-axis.
     * @param dy The change in position on the y-axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets the change in position on the x-axis.
     *
     * @return The change in position on the x-axis.
     */
    public double getDx() {
        return dx;
    }

    /**
     * Sets the change in position on the x-axis.
     *
     * @param dx The new value for the change in position on the x-axis.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Gets the change in position on the y-axis.
     *
     * @return The change in position on the y-axis.
     */
    public double getDy() {
        return dy;
    }

    /**
     * Sets the change in position on the y-axis.
     *
     * @param dy The new value for the change in position on the y-axis.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }


    /**
     * Take a point with position (x,y) and return a new point
     * // with position (x+dx, y+dy).
     *
     * @param p point with position (x,y)
     * @return a new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(final Point p) {
        double x = ((p.getX()) + this.dx);
        double y = ((p.getY()) + this.dy);

        return new Point(x, y);
    }

    /**
     * Creates arkanoid.geometry.Velocity object from a given angle and speed.
     *
     * <p>* This method converts the given angle and speed into the respective
     * x (dx) and y (dy) components of velocity.
     * The angle is assumed to be in degrees, where 0 degrees points upwards,
     * and positive angles are measured clockwise.
     *
     * @param angle the direction of the velocity in degrees, where 0 degrees is upwards.
     * @param speed the magnitude of the velocity.
     * @return a new arkanoid.geometry.Velocity object with the calculated dx and dy values.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double adjustAngle = angle - 90;
        double nextX = speed * Math.cos(Math.toRadians(adjustAngle));
        double nextY = speed * Math.sin(Math.toRadians(adjustAngle));
        return new Velocity(nextX, nextY);
    }

    /**
     * Method to calculate speed from dx and dy.
     *
     * @return the speed
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }
}
