// 209702745 Nadav Gonen
/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-08-03
 */

/**
 * Interface name: Collidable.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return Rectangle - a rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param collisionPoint - point.
     * @param currentVelocity - velocity.
     * @param hitter - the ball that hit.
     * @return velocity - the new velocity after the collision.
     */

    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
