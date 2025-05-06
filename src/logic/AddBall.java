// 325516193 Cathrine Abu-Elazam
package arkanoid.logic;

import arkanoid.collections.GameEnvironment;
import arkanoid.game.Game;
import arkanoid.geometry.Point;
import arkanoid.geometry.Velocity;
import arkanoid.sprites.Ball;
import arkanoid.sprites.Block;
import arkanoid.utils.Counter;

import java.awt.Color;

/**
 * arkanoid.logic.AddBall is in charge of adding balls for the game, as well as keeping count
 * of the number of balls that remain.
 */
public class AddBall implements HitListener {
    private final Game game;
    private final Counter remainingBalls;
    private final GameEnvironment environment;

    /**
     * constructor.
     *
     * @param game           the game
     * @param remainingBalls the count of the balls in game
     * @param environment    the game environment
     */
    public AddBall(Game game, Counter remainingBalls, GameEnvironment environment) {
        this.game = game;
        this.remainingBalls = remainingBalls;
        this.environment = environment;
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
        Ball b = new Ball(new Point(beingHit.getCollisionRectangle().getXStart(),
                beingHit.getCollisionRectangle().getYStart()), 5, Color.WHITE, environment);
        b.addToGame(game);
        b.setVelocity(new Velocity(4, 4));
        b.addHitListener(this);
        this.remainingBalls.increase(1);
    }
}
