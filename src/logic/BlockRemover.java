// 325516193 Cathrine Abu-Elazam
package arkanoid.logic;

import arkanoid.game.Game;
import arkanoid.sprites.Ball;
import arkanoid.sprites.Block;
import arkanoid.utils.Counter;

/**
 * arkanoid.logic.BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private final Game game;
    private Counter remainingBlocks;

    /**
     * constructor.
     * @param game the game
     * @param remainingBlocks the count of the blocks that remain in game
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit is the block that the ball hit.
     * @param hitter   the hitter parameter is the arkanoid.sprites.Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
        hitter.setColor(beingHit.getColor());
    }
}