// 325516193 Cathrine Abu-Elazam
package arkanoid.logic;

import arkanoid.sprites.Ball;
import arkanoid.sprites.Block;
import arkanoid.utils.Counter;

/**
 * The {@code arkanoid.logic.ScoreTrackingListener} class is responsible for tracking the score of a player.
 * It maintains a reference to a {@link Counter} object which keeps the current score.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor that initializes the score tracking listener with the given score counter.
     *
     * @param scoreCounter the score counter of the player
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }


    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit is the block that the ball hit.
     * @param hitter   the hitter parameter is the arkanoid.sprites.Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
