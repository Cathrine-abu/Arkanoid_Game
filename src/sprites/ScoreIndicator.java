// 325516193 Cathrine Abu-Elazam
package arkanoid.sprites;

import arkanoid.game.Game;
import arkanoid.geometry.Rectangle;
import arkanoid.utils.Counter;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Class that is in charge of displaying the current score.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCount;
    private final Rectangle rectangle;

    /**
     * Constructor.
     * @param scoreCount the score of the player
     * @param rectangle to write in it the score
     */
    public ScoreIndicator(Counter scoreCount, Rectangle rectangle) {
        this.scoreCount = scoreCount;
        this.rectangle = rectangle;
    }

    /**
     * Draws the sprite onto the given DrawSurface.
     *
     * @param d the DrawSurface on which the sprite should be drawn
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(((int) rectangle.getUpperLeft().getX()), ((int) rectangle.getUpperLeft().getY()),
                ((int) rectangle.getWidth()), ((int) rectangle.getHeight()));
        d.setColor(Color.BLACK);
        d.drawText((int) (rectangle.getUpperLeft().getX() + rectangle.getWidth() / 2),
                (int) (rectangle.getUpperLeft().getY() + rectangle.getHeight() * 9 / 10),
                String.format("Score: %d", scoreCount.getValue()), 17);
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
     * Adding the indicator to the sprites list.
     *
     * @param game a game.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
