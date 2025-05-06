// 325516193 Cathrine Abu-Elazam
package arkanoid.logic;

import arkanoid.geometry.Point;

/**
 * arkanoid.logic.CollisionInfo class the point at which the collision occurs.
 * and the collidable object that involved in the collision.
 */
public class CollisionInfo {
    private final Collidable collisionObject;
    private final Point collisionPoint;

    /**
     * Constructs arkanoid.logic.CollisionInfo object with the specified collision object and collision point.
     *
     * @param collisionObject the rectangle representing the collision object
     * @param collisionPoint  the point at which the collision occurred
     */
    public CollisionInfo(Collidable collisionObject, Point collisionPoint) {
        this.collisionObject = collisionObject;
        this.collisionPoint = collisionPoint;
    }

    /**
     * the point at which the collision occurs.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }


    /**
     * the collidable object involved in the collision.
     *
     * @return the collision object that involved in the collision
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
