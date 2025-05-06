// 325516193 Cathrine Abu-Elazam
package arkanoid.collections;

import biuoop.DrawSurface;
import arkanoid.sprites.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of sprites in the game.
 */
public class SpriteCollection {
    private final ArrayList<Sprite> spriteCollections;

    /**
     * Get the spriteCollection arrayList.
     *
     * @return the spriteCollection arrayList
     */
    public ArrayList<Sprite> getSpriteCollections() {
        return spriteCollections;
    }

    /**
     * Constructs a new arkanoid.collections.SpriteCollection with an empty list of sprites.
     */
    public SpriteCollection() {
        this.spriteCollections = new ArrayList<>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.spriteCollections.add(s);
    }

    /**
     * Calls the timePassed() method on all sprites in the collection.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(spriteCollections);
        for (Sprite sprite : spritesCopy) {
            sprite.timePassed();
        }
    }

    /**
     * Draws all sprites in the collection on the given draw surface.
     *
     * @param surface the draw surface on which to draw the sprites
     */
    public void drawAllOn(DrawSurface surface) {
        for (Sprite sprite : spriteCollections) {
            sprite.drawOn(surface);
        }
    }

    /**
     * Removes the given sprite from the list.
     *
     * @param s a sprite.
     */
    public void removeSprite(Sprite s) {
        spriteCollections.remove(s);
    }
}
