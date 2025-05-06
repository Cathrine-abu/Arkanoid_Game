// 325516193 Cathrine Abu-Elazam
package arkanoid.collections;

import arkanoid.geometry.Line;
import arkanoid.geometry.Point;
import arkanoid.logic.Collidable;
import arkanoid.logic.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * arkanoid.collections.GameEnvironment class represents the environment in which collidable objects interact.
 * This class manages a collection of collidable objects and provides
 * methods such add and get information about the closest collision.
 */
public class GameEnvironment {
    private final ArrayList<Collidable> collidables;

    /**
     * Constructs a new game environment with an empty list of collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Constructs a new game environment with the given list of collidables.
     *
     * @param collidables the initial list of collidable objects in the environment
     */
    public GameEnvironment(ArrayList<Collidable> collidables) {
        this.collidables = collidables;
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c the given collidable that we add to the environment
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Returns the closest collision point between the given trajectory and the collidable object.
     *
     * @param trajectory    The trajectory of the moving object.
     * @param collidableObj The collidable object to check for collisions.
     * @return The point of collision, or null if no collision occurs.
     */
    public Point collisionPoint(Line trajectory, Collidable collidableObj) {
        if (trajectory == null || collidableObj == null) {
            return null;
        }
        return trajectory.closestIntersectionToStartOfLine(collidableObj.getCollisionRectangle());
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory The trajectory of the moving object.
     * @return The information about the closest collision, or null if no collision occurs.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double closestDistance = Double.MAX_VALUE;
        CollisionInfo closestCollision = null;

        // Iterate over each collidable
        for (Collidable collidable : collidables) {
            List<Point> pointList = collidable.getCollisionRectangle().intersectionPoints(trajectory);

            if (pointList.isEmpty()) {
                continue;
            }

            // Iterate over each intersection point
            for (Point p : pointList) {
                double distance = p.distance(trajectory.start());
                if (closestCollision == null || distance < closestDistance) {
                    closestDistance = distance;
                    closestCollision = new CollisionInfo(collidable, p);
                }
            }
        }

        return closestCollision;
    }

    /**
     * Removes the given collidable from the environment.
     *
     * @param c a collidable.
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }
}
