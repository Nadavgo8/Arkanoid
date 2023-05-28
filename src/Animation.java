// 209702745 Nadav Gonen
/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-09-03
 */

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
