// 209702745 Nadav Gonen
/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-08-03
 */

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

import biuoop.DrawSurface;

/**
 * Classname: Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle collisionRectangle;
    private Color color;
    public static final double NEGLIGIBLE_ERROR = Math.pow(10, -10);
    private List<HitListener> hitListeners;


    /**
     * The function initializes the "collision shape" of the block - a rectangle.
     *
     * @param rectangle - the rectangle.
     */
    public Block(Rectangle rectangle) {
        this.collisionRectangle = rectangle;
        this.hitListeners = new ArrayList<>();

    }

    /**
     * This function sets the block's color.
     *
     * @param color Color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * The function initializes the "collision shape" of the block - a rectangle, this time with color (the block).
     *
     * @param color     - the block's color.
     * @param rectangle - the rectangle.
     */
    public Block(Rectangle rectangle, java.awt.Color color) {
        this.collisionRectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * The function initializes the "collision shape" of the block - a rectangle.
     *
     * @param upperLeft - the rectangles upper left point.
     * @param width     - the rectangle's width.
     * @param height    the recatangle's height.
     */
    public Block(Point upperLeft, double width, double height) {
        this.collisionRectangle = new Rectangle(upperLeft, width, height);
        this.hitListeners = new ArrayList<>();
    }

    /**
     * The function returns the block's color.
     *
     * @return color - the block's color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * The function draws the block on the given DrawSurface.
     *
     * @param d - the surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.collisionRectangle.getUpperLeft().getX(),
                (int) this.collisionRectangle.getUpperLeft().getY(),
                (int) this.collisionRectangle.getWidth(),
                (int) this.collisionRectangle.getHeight());

        d.setColor(Color.black);
        d.drawRectangle((int) this.collisionRectangle.getUpperLeft().getX(),
                (int) this.collisionRectangle.getUpperLeft().getY(),
                (int) this.collisionRectangle.getWidth(),
                (int) this.collisionRectangle.getHeight());
    }

    /**
     * The function changes the block's collisionRectangle.
     *
     * @param collisionRectangle - the rectangle.
     */
    public void setCollisionRectangle(Rectangle collisionRectangle) {
        this.collisionRectangle = collisionRectangle;
    }

    /**
     * The function returns the "collision shape" of the object - a rectangle.
     *
     * @return collisionRectangle - the rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }

    /**
     * The function checks which part of the rectangle he hit (if at all) and returns the new expected velocity.
     *
     * @param collisionPoint  - the point we collided with.
     * @param currentVelocity - the velocity.
     * @param hitter          - the ball that hit.
     * @return new velocity expected after the hit, based on the part of the block we hit.
     */

    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Boolean dxHigher = currentVelocity.getDx() >= currentVelocity.getDy(); // Both will be true if equal.
        Boolean dyHigher = currentVelocity.getDy() >= currentVelocity.getDx();
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Velocity newVelocity = new Velocity(dx, dy);
        Line left = this.collisionRectangle.getLeft();
        Line right = this.collisionRectangle.getRight();
        Line top = this.collisionRectangle.getTop();
        Line bottom = this.collisionRectangle.getBottom();
        Line collisionLine = new Line(collisionPoint, collisionPoint);
        if (left.isIntersecting(collisionLine)) {
            if (top.isIntersecting(collisionLine) || bottom.isIntersecting(collisionLine)) { // It hit a corner.
                if (dxHigher) {
                    newVelocity.setDy(-dy);
                }
                if (dyHigher) {
                    newVelocity.setDx(-dx);
                }
                this.notifyHit(hitter);
                return newVelocity;
            }
            newVelocity.setDx(-dx); // It didn't hit a corner.
        }
        if (right.isIntersecting(collisionLine)) {
            if (top.isIntersecting(collisionLine) || bottom.isIntersecting(collisionLine)) { // It hit a corner.
                if (dxHigher) {
                    newVelocity.setDy(-dy);
                }
                if (dyHigher) {
                    newVelocity.setDx(-dx);
                }
                this.notifyHit(hitter);
                return newVelocity;
            }
            newVelocity.setDx(-dx); // It didn't hit a corner.
        }
        if (top.isIntersecting(collisionLine)) {
            if (right.isIntersecting(collisionLine) || left.isIntersecting(collisionLine)) { // It hit a corner.
                if (dxHigher) {
                    newVelocity.setDy(-dy);
                }
                if (dyHigher) {
                    newVelocity.setDx(-dx);
                }
                this.notifyHit(hitter);
                return newVelocity;
            }
            newVelocity.setDy(-dy); // It didn't hit a corner.
        }
        if (bottom.isIntersecting(collisionLine)) {
            if (right.isIntersecting(collisionLine) || left.isIntersecting(collisionLine)) { // It hit a corner.
                if (dxHigher) {
                    newVelocity.setDy(-dy);
                }
                if (dyHigher) {
                    newVelocity.setDx(-dx);
                }
                this.notifyHit(hitter);
                return newVelocity;

            }
            newVelocity.setDy(-dy); // It didn't hit a corner.
        }
        this.notifyHit(hitter);
        return newVelocity;
    }


    /**
     * The function is relevant if want to make the blocks animated.
     */
    public void timePassed() {

    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * The function adds the block to the game and its relevant lists.
     *
     * @param game Game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * The function removes the block from the game and its relevant lists.
     *
     * @param game Game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }
}