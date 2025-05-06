//325516193 Cathrine Abu-Elazam
package arkanoid.sprites;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import arkanoid.geometry.Rectangle;
import arkanoid.logic.Collidable;
import arkanoid.logic.HitListener;
import arkanoid.logic.HitNotifier;
import biuoop.DrawSurface;
import arkanoid.collections.GameEnvironment;
import arkanoid.game.Game;
import arkanoid.geometry.Line;
import arkanoid.geometry.Point;
import arkanoid.geometry.Velocity;
import arkanoid.logic.CollisionInfo;

/**
 * The arkanoid.sprites.Ball class represents a ball with a center point, radius, color, and velocity.
 * It can move within a defined frame and handle collisions with frame boundaries and rectangles/blocks.
 */
public class Ball implements Sprite, HitNotifier {
    private final GameEnvironment gameEnvironment;
    private Point center;
    private final int radius;
    private Color color;
    private Velocity velocity;
    private final List<HitListener> hitListeners;


    private static final double THRESHOLD = 0.01;


    /**
     * @param center          the point that represent the center of the arkanoid.sprites.Ball.
     * @param r               the radius of the arkanoid.sprites.Ball.
     * @param color           the color of the arkanoid.sprites.Ball.
     * @param gameEnvironment the environment that the ball interacts with.
     */
    public Ball(Point center, int r, Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0); // default velocity
        this.gameEnvironment = gameEnvironment;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Getter for the x coordinate of the center.
     *
     * @return the x of the center point.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Getter for the y coordinate of the center.
     *
     * @return the y of the center point.
     */
    public int getY() {
        return (int) this.center.getY();

    }

    /**
     * Get the size (radius) of the arkanoid.sprites.Ball.
     *
     * @return the size of the arkanoid.sprites.Ball.
     */
    public int getSize() {
        return this.radius;
    }


    /**
     * Draws the sprite onto the given DrawSurface.
     *
     * @param d the DrawSurface on which the sprite(ball) should be drawn
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * Notifies the sprite that time has passed. This method is used to update
     * the sprite's state, such as moving its position, changing its appearance,
     * or performing any other time-based updates.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Sets the velocity of the ball to the given velocity object.
     *
     * @param v The velocity to set.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Returns the color of the object.
     *
     * @return the color of the object.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * sets the ball's color.
     *
     * @param color the new color of the ball
     */
    public void setColor(Color color) {
        this.color = color;
    }


    /**
     * Returns the center point of the object.
     *
     * @return the center point of the object.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Returns the radius of the object.
     *
     * @return the radius of the object.
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * Moves the object by one step based on its current velocity. If the object encounters a collision
     * during its movement, it handles the collision appropriately by adjusting its position and velocity.
     */
    public void moveOneStep() {

        Point newP = this.velocity.applyToPoint(this.center);
        int horizontalMove, verticalMove;
        if (velocity.getDx() > 0) {
            horizontalMove = radius;
        } else {
            horizontalMove = -radius;
        }
        if (velocity.getDy() > 0) {
            verticalMove = radius;
        } else {
            verticalMove = -radius;
        }
        newP.setX(newP.getX() + horizontalMove);
        newP.setY(newP.getY() + verticalMove);

        Line trajectory = new Line(this.center.getX(), this.center.getY(), newP.getX(), newP.getY());
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo != null) {

            double collisionPointX = collisionInfo.collisionPoint().getX();
            double collisionPointY = collisionInfo.collisionPoint().getY();
            double objectCenterX = center.getX();
            double objectCenterY = center.getY();

            // Calculate the adjusted X coordinate based on the collision point and movement direction
            double adjustedX;
            if (objectCenterX < collisionPointX) {
                adjustedX = collisionPointX - horizontalMove - THRESHOLD;
            } else {
                adjustedX = collisionPointX - horizontalMove + THRESHOLD;
            }

            // Calculate the adjusted Y coordinate based on the collision point and movement direction
            double adjustedY;
            if (objectCenterY < collisionPointY) {
                adjustedY = collisionPointY - verticalMove - THRESHOLD;
            } else {
                adjustedY = collisionPointY - verticalMove + THRESHOLD;
            }

            // Update the center point of the object to the new adjusted coordinates
            this.center = new Point(adjustedX, adjustedY);

            // Update velocity
            this.velocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), velocity);
        }

        this.center = this.velocity.applyToPoint(this.center);

        //Check ball collision with paddle
        if (collisionInfo != null) {
            Collidable collisionObject = collisionInfo.collisionObject();

            if (collisionObject.isPaddle() && ballInsidePaddle(collisionInfo.collisionObject()
                    .getCollisionRectangle())) {
                this.center.setX(getCenter().getX());
                this.center.setY(getCenter().getY() - 10);
            }
        }
    }

    /**
     * Checks if the ball is inside the given rectangle, typically representing the paddle.
     *
     * @param rectangle the object representing the paddle.
     * @return true if the ball's coordinates are within the bounds of the rectangle;
     * false otherwise.
     */
    private boolean ballInsidePaddle(Rectangle rectangle) {
        double rectX = rectangle.getUpperLeft().getX();
        double rectY = rectangle.getUpperLeft().getY();
        double rectWidth = rectangle.getWidth();
        double rectHeight = rectangle.getHeight();

        return this.getX() >= rectX && this.getX() <= rectX + rectWidth
                && this.getY() >= rectY && this.getY() <= rectY + rectHeight;
    }

    /**
     * Add this ball to the game.
     *
     * @param g the game we add ti it the ball.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the arkanoid.logic.HitListener we add.
     */
    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the arkanoid.logic.HitListener we remove.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Remove ball from game.
     *
     * @param game the game we remove ball from it.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }
}


