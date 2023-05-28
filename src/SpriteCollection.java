// 209702745 Nadav Gonen
/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-08-03
 */

import biuoop.DrawSurface;
/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-08-03
 */
import java.util.ArrayList;
import java.util.List;
/**
 * Classname: SpriteCollection.
 */
public class SpriteCollection {
    private List<Sprite> spriteList;
    /**
     * A constructor.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }
    /**
     * this function adds a sprite to the list.
     *
     * @param s - sprite.
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }
    /**
     * this function removes a sprite to the list.
     *
     * @param s - sprite.
     */
    public void removeSprite(Sprite s) {
        spriteList.remove(s);
    }
    /**
     * The function calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sList = new ArrayList<>(this.spriteList);
        for (Sprite s : sList) {
            s.timePassed();
        }
    }

    /**
     * The function calls drawn(d) on all sprites.
     * @param d - the surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : spriteList) {
            s.drawOn(d);
        }
    }
}
