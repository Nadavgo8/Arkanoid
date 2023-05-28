// 209702745 Nadav Gonen
/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-08-03
 */

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Classname: Ball.
 */
public class Ball implements Sprite, HitNotifier {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private double maxY;
    private double minY;
    private double maxX;
    private double minX;
    private GameEnvironment gameEnvironment;
    private List<HitListener> hitListeners;

    /**
     * A constructor.
     *
     * @param center point.
     * @param color  color.
     * @param r      int.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        maxY = 200;
        minY = 0;
        maxX = 200;
        minX = 0;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * A constructor.
     *
     * @param x     double, the x value of the center.
     * @param y     double, the y value of the center.
     * @param color color.
     * @param r     int.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        maxY = 200;
        minY = 0;
        maxX = 200;
        minX = 0;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * This function sets the max y for the surface.
     *
     * @param y int.
     */
    public void setMaxY(int y) {
        this.maxY = y;
    }

    /**
     * This function sets the max x for the surface.
     *
     * @param x int.
     */
    public void setMaxX(int x) {
        this.maxX = x;
    }

    /**
     * This function sets the min y for the surface.
     *
     * @param y int.
     */
    public void setMinY(int y) {
        this.minY = y;
    }

    /**
     * This function sets the min x for the surface.
     *
     * @param x int.
     */
    public void setMinX(int x) {
        this.minX = x;
    }

    /**
     * This function sets the ball's center.
     *
     * @param point point.
     */
    public void setCenter(Point point) {
        this.center = point;
    }

    /**
     * This function sets the ball's velocity.
     *
     * @param velocity velocity.
     */
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    /**
     * This function sets the ball's velocity.
     *
     * @param dx double.
     * @param dy double.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * This function returns the ball's velocity.
     *
     * @return velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * The function initializes the game environment.
     *
     * @param gameEnvironment - the new game environment.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * This function sets the ball's color.
     *
     * @param color Color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * The function checks if moving on the trajectory will hit anything.
     * If there's no hit, the ball will to the end of the trajectory.
     * Otherwise, the ball will advance to the hitting point and the velocity will be updated.
     */
    public void moveOneStep() {
        if (this.center.getY() < 22) {
            this.setCenter(new Point(this.getX(), 26));
            this.velocity.setDy(-this.velocity.getDy());
        }
        if (this.center.getX() < 22) {
            this.setCenter(new Point(24, this.getY()));
            this.velocity.setDx(-this.velocity.getDx());
        }
        if (this.center.getX() > 782) {
            this.setCenter(new Point(784, this.getY()));
            this.velocity.setDx(-this.velocity.getDx());
        }
        Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));
        CollisionInfo obstacle = this.gameEnvironment.getClosestCollision(trajectory);
        if (obstacle != null) { //There's a hit.
            this.setVelocity(obstacle.collisionObject().hit(this, obstacle.collisionPoint(), this.getVelocity()));
            this.setCenter(new Point(trajectory.middle().getX() - 1, trajectory.middle().getY() - 1));
        }
        this.setCenter(this.getVelocity().applyToPoint(this.center));
    }


    /**
     * This function returns the x value.
     *
     * @return x int.
     */
    public int getX() {
        return (int) Math.round(this.center.getX());
    }

    /**
     * This function returns the y value.
     *
     * @return y int.
     */
    public int getY() {
        return (int) Math.round(this.center.getY());
    }

    /**
     * This function returns the radius.
     *
     * @return r int.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * This function returns the color.
     *
     * @return color color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * This function draws a circle on the given surface.
     *
     * @param d DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.getColor());
        d.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * The function updates the ball that it should move one step.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * The function adds the ball to the given game.
     *
     * @param game Game.
     */

    public void addToGame(GameLevel game) {
        game.addSprite(this);
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
    }

    /**
     * This function draws the animations.
     *
     * @param dx    double.
     * @param dy    double.
     * @param start point.
     */
    private static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", 200, 200);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(new Point(start.getX(), start.getY()), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}