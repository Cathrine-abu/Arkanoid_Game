// 325516193 Cathrine Abu-Elazam
package arkanoid.logic;

/**
 * The {@code arkanoid.logic.HitNotifier} interface represents an object that can notify listeners
 * about hit events.
 * Classes implementing this interface can manage a list of {@link HitListener}s
 * and allow others to add or remove these listeners. When a hit event occurs,
 * registered listeners are notified.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the arkanoid.logic.HitListener we add.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the arkanoid.logic.HitListener we remove.
     */
    void removeHitListener(HitListener hl);
}
