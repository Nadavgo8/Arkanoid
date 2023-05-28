
/**
 * Interface name: HitNotifier.
 */

public interface HitNotifier {
    /**
     * The function adds hl as a listener to hit events.
     * @param hl the listener.
     */
    void addHitListener(HitListener hl);
    /**
     * The function removes hl as a listener to hit events.
     * @param hl the listener.
     */
    void removeHitListener(HitListener hl);
}
