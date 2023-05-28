// 209702745 Nadav Gonen
/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-09-03
 */

import biuoop.DrawSurface;

/**
 * Interface name: Sprite.
 */
public interface Sprite {
    /**
     * The function draws the sprite to the screen.
     *
     * @param d - the surface.
     */
    void drawOn(DrawSurface d);
    /**
     * The function notifies the sprite that time has passed.
     *
     */
    void timePassed();
}
