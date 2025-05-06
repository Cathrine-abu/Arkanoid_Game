// 325516193 Cathrine Abu-Elazam
package arkanoid.sprites;

import biuoop.DrawSurface;
import arkanoid.game.Game;
import arkanoid.geometry.Point;
import arkanoid.geometry.Rectangle;
import arkanoid.geometry.Velocity;
import arkanoid.logic.Collidable;
import arkanoid.logic.HitListener;
import arkanoid.logic.HitNotifier;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * arkanoid.sprites.Block class represents a block in the game that can be collided with by other objects,
 * such as a ball. The block has a rectangular shape, a color, a velocity etc.
 * In addition, the block can be either a block in the middle of the game, or a block in the edge
 * of the screen
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private final Rectangle rectangle;
    private final Color color;
    private final Color colorFrame;
    private final List<HitListener> hitListeners;

    private static final double THRESHOLD = 0.0001;


    /**
     * Constructs arkanoid.sprites.Block with the specified properties.
     *
     * @param rectangle  the shape and of the block
     * @param color      the color of the block
     * @param colorFrame the frame color of the block
     */
    public Block(Rectangle rectangle, Color color, Color colorFrame) {
        this.rectangle = rectangle;
        this.color = color;
        this.colorFrame = colorFrame;
        this.hitListeners = new ArrayList<>();

    }

    /**
     * get the block color.
     *
     * @return block color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the "collision shape" of the object, represented as a rectangle.
     *
     * @return the rectangle representing the collision shape of the object
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Notifies the object that a collision has occurred at the specified point
     * with the given velocity. The method calculates the new velocity expected
     * after the hit, based on the force the object inflicts.
     *
     * @param collisionPoint  the point at which the collision occurred
     * @param currentVelocity the velocity of the object at the time of collision
     * @return the new velocity expected after the hit
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }

        // Collision with the top side of the rectangle
        if (Math.abs(collisionPoint.getY() - this.rectangle.getYStart()) < THRESHOLD) {
            dy = -dy;
            return new Velocity(dx, dy);
        } else if (Math.abs(collisionPoint.getY() - (this.rectangle.getYStart()
                + this.rectangle.getHeight())) < THRESHOLD) { // Collision with the bottom side of the rectangle
            dy = -dy;
            return new Velocity(dx, dy);
        } else if (Math.abs(collisionPoint.getX() - this.rectangle.getXStart()) < THRESHOLD) {
            // Collision with the left side of the rectangle
            dx = -dx;
            return new Velocity(dx, dy);
        } else if (Math.abs(collisionPoint.getX() - (this.rectangle.getXStart()
                + this.rectangle.getWidth())) < THRESHOLD) { // Collision with the right side of the rectangle
            dx = -dx;
            return new Velocity(dx, dy);
        }

        return new Velocity(dx, dy);
    }

    /**
     * @return false because this collidable is not paddle.
     */
    @Override
    public Boolean isPaddle() {
        return false;
    }

    /**
     * Draws the sprite onto the given DrawSurface.
     *
     * @param d the DrawSurface on which the sprite should be drawn
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getXStart(), (int) this.rectangle.getYStart(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(this.colorFrame);
        d.drawRectangle((int) this.rectangle.getXStart(), (int) this.rectangle.getYStart(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * add this block to the game given.
     *
     * @param g the game to add sprite to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Notifies the sprite that time has passed. This method is used to update
     * the sprite's state, such as moving its position, changing its appearance,
     * or performing any other time-based updates.
     */
    @Override
    public void timePassed() {

    }

    /**
     * If the color of the block matches the ball's color.
     *
     * @param ball we check if its color matched.
     * @return true if the color matched, otherwise false
     */
    public boolean ballColorMatch(Ball ball) {
        return ball.getColor().equals(this.color);
    }

    /**
     * Remove block from game.
     *
     * @param game the game we remove block from it.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
        game.removeCollidable(this);
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
     * Notifying all listeners that a hit accrued.
     *
     * @param hitter the ball that hit.
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        ArrayList<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
