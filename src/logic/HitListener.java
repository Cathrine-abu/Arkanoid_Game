// 325516193 Cathrine Abu-Elazam
package arkanoid.logic;

import arkanoid.sprites.Ball;
import arkanoid.sprites.Block;

/**
 * The {@code arkanoid.logic.HitListener} interface represents an object that listens to hit events.
 * Implementations of this interface can react to collision events between a {@link Ball}
 * and a {@link Block}. The {@code hitEvent} method is called whenever the {@code beingHit}
 * block is hit by the {@code hitter} ball.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit is the block that the ball hit.
     * @param hitter   the hitter parameter is the arkanoid.sprites.Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
