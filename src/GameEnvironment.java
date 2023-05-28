
import java.util.List;
import java.util.ArrayList;

/**
 * Classname: GameEnvironment.
 */
public class GameEnvironment {
    private List<Collidable> collidablesList;

    /**
     * A constructor.
     */
    public GameEnvironment() {
        this.collidablesList = new ArrayList<>();
    }

    /**
     * The function adds the given collidable to the environment.
     *
     * @param c - the collidable.
     */
    public void addCollidable(Collidable c) {
        this.collidablesList.add(c);
    }
    /**
     * The function removes the given collidable to the environment.
     *
     * @param c - the collidable.
     */
    public void removeCollidable(Collidable c) {
        this.collidablesList.remove(c);
    }
    /**
     * The function returns the collidables list.
     * @return colldables list.
     */
    public List<Collidable> getCollidables() {
        return this.collidablesList;
    }

    /**
     * The function assumes an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory - the line.
     * @return CollisionInfo.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closest = trajectory.closestIntersectionToStartOfLine(
                this.collidablesList.get(0).getCollisionRectangle());
        Point temp = null;
        Point start = trajectory.start();
        int index = 0;
        if (closest == null) {
            index = -1;
        }
        for (int i = 1; i < this.collidablesList.size(); i++) {
            temp = trajectory.closestIntersectionToStartOfLine(
                    this.collidablesList.get(i).getCollisionRectangle());
            if (closest == null && temp != null) {
                closest = temp;
                index = i;
            }
            if (temp != null && start.distance(closest) > start.distance(temp)) {
                closest = temp;
                index = i;
            }
        }
        if (index == -1) {
            return null;
        }
        return new CollisionInfo(this.collidablesList.get(index), closest);
    }
}

