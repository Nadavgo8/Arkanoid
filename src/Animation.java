
import biuoop.DrawSurface;
/**
 * Interface name: Animation.
 */

public interface Animation {
    /**
     * The function draws the game frame.
     *
     * @param d - the draw surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * The function returns if the level should be stopped.
     * @return a boolean value.
     */
    boolean shouldStop();
}
