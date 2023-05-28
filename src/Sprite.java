
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
