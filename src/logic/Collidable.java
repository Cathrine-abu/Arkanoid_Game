// 325516193 Cathrine Abu-Elazam
package arkanoid.logic;

import arkanoid.geometry.Point;
import arkanoid.geometry.Rectangle;
import arkanoid.geometry.Velocity;
import arkanoid.sprites.Ball;

/**
 * The {@code arkanoid.logic.Collidable} interface should be implemented by any class whose instances
 * need to respond to collisions. Classes implementing this interface will define
 * specific behavior that occurs when a collision happens.
 */
public interface Collidable {
    /**
     * Returns the "collision shape" of the object, represented as a rectangle.
     *
     * @return the rectangle representing the collision shape of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision has occurred at the specified point
     * with the given velocity. The method calculates the new velocity expected
     * after the hit, based on the force the object inflicts.
     *
     * @param collisionPoint  the point at which the collision occurred
     * @param currentVelocity the velocity of the object at the time of collision
     * @param hitter          the ball hitter
     * @return the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Determines if the current object is recognized as a paddle.
     *
     * @return true if the object is a paddle; false otherwise.
     */
    Boolean isPaddle();
}
