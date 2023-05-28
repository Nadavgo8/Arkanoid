// 209702745 Nadav Gonen
/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-08-03
 */

/**
 * Class name: CollisionInfo.
 */
public class CollisionInfo {
    private Point collPoint;
    private Collidable collisionObject;
    /**
     * A constructor.
     *
     * @param collidable  - the collidable object.
     * @param point - the hitting point.
     */
    public CollisionInfo(Collidable collidable, Point point) {
        this.collisionObject = collidable;
        this.collPoint = point;
    }
    /**
     * The function returns the point at which the collision occurs.
     *
     * @return point of collision.
     */
    public Point collisionPoint() {
        return this.collPoint;
    }

    /**
     * The function returns the collidable object involved in the collision.
     *
     * @return collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
