// 325516193 Cathrine Abu-Elazam
package arkanoid.sprites;

import biuoop.DrawSurface;

/**
 * The arkanoid.sprites.Sprite interface represents a game object that can be drawn to the screen
 * and can be notified of the passage of time to update its state. Implementing
 * this interface allows a class to be treated as a drawable and time-aware game
 * object within the game's animation loop.
 */
public interface Sprite {

    /**
     * Draws the sprite onto the given DrawSurface.
     *
     * @param d the DrawSurface on which the sprite should be drawn
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed. This method is used to update
     * the sprite's state, such as moving its position, changing its appearance,
     * or performing any other time-based updates.
     */
    void timePassed();
}
