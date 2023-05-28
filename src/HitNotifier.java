// 209702745 Nadav Gonen

/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-09-03
 */

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
