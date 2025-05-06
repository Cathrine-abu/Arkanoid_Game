// 325516193 Cathrine Abu-Elazam
package arkanoid.logic;

import arkanoid.sprites.Ball;
import arkanoid.sprites.Block;
import arkanoid.utils.Counter;
import arkanoid.game.Game;


/**
 * arkanoid.logic.BallRemover is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that remain.
 */
public class BallRemover implements HitListener {
    private final Game game;
    private final Counter remainingBalls;

    /**
     * constructor.
     *
     * @param game           the game
     * @param remainingBalls the count of the balls that remain in game
     */
     public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit is the block that the ball hit.
     * @param hitter   the hitter parameter is the arkanoid.sprites.Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        hitter.removeHitListener(this);
        this.remainingBalls.decrease(1);
    }
}
