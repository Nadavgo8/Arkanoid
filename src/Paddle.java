// 209702745 Nadav Gonen
/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-08-03
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * Classname: Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private Velocity velocity;
    private Rectangle paddle;
    private Color color;
    private KeyboardSensor keyboard;
    public static final int WALL_SIZE = 20;
    public static final int WIDTH = 800;


    /**
     * A constructor.
     *
     * @param keyboard - the sensor.
     * @param rec      -     the shape.
     * @param color    - the color.
     */
    public Paddle(Rectangle rec, Color color, KeyboardSensor keyboard) {
        this.velocity = new Velocity(6, 0);
        this.paddle = rec;
        this.color = color;
        this.keyboard = keyboard;
    }

    /**
     * The function moves the paddle left.
     */
    public void moveLeft() {
        double newY = this.paddle.getUpperLeft().getY();
        double newX = this.paddle.getUpperLeft().getX() - this.velocity.getDx();
        if (newX < WALL_SIZE) { //We have reached the left wall.
            newX = WALL_SIZE;
        }
        this.paddle = new Rectangle(new Point(newX, newY), this.paddle.getWidth(), this.paddle.getHeight());
    }

    /**
     * The function moves the paddle right.
     */

    public void moveRight() {
        double newY = this.paddle.getUpperLeft().getY();
        double newX = this.paddle.getUpperLeft().getX() + this.velocity.getDx();
        if (newX > WIDTH - WALL_SIZE - this.paddle.getWidth()) { //We have reached the right all.
            newX = WIDTH - WALL_SIZE - this.paddle.getWidth();
        }
        this.paddle = new Rectangle(new Point(newX, newY), this.paddle.getWidth(), this.paddle.getHeight());
    }

    /**
     * The function moves the paddle left/right, according to the keyboard sensor.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        } else if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
    }

    /**
     * The function draws the paddle.
     *
     * @param d - the surface drawn on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
        d.setColor(this.color);
        d.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
    }

    /**
     * the function returns the paddle's shape - a rectangle.
     *
     * @return the shape of the paddle.
     */

    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * the function calculates new velocity expected after a hit.
     *
     * @param collisionPoint  - the point of collision.
     * @param currentVelocity - the velocity before the collision.
     * @return the new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double speed = Math.sqrt(dy * dy + dx * dx);
        double width = this.getCollisionRectangle().getWidth();
        double x = this.getCollisionRectangle().getUpperLeft().getX();
        double y = this.getCollisionRectangle().getUpperLeft().getY();
        Line collisionLine = new Line(collisionPoint, collisionPoint);
        Point[] points = new Point[6];
        points[0] = this.getCollisionRectangle().getUpperLeft();
        for (double i = 1; i < points.length; i++) {
            points[(int) i] = new Point(x + (width) * (i / 5), y);
        }
        Line region1 = new Line(points[0], points[1]);
        Line region2 = new Line(points[1], points[2]);
        Line region3 = new Line(points[2], points[3]);
        Line region4 = new Line(points[3], points[4]);
        Line region5 = new Line(points[4], points[5]);
        if (collisionLine.isIntersecting(region1)) {
            return Velocity.fromAngleAndSpeed(300, speed);
        }
        if (collisionLine.isIntersecting(region2)) {
            return Velocity.fromAngleAndSpeed(330, speed);
        }
        if (collisionLine.isIntersecting(region3)) {
            return new Velocity(dx, -dy);
        }
        if (collisionLine.isIntersecting(region4)) {
            return Velocity.fromAngleAndSpeed(30, speed);
        }
        if (collisionLine.isIntersecting(region5)) {
            return Velocity.fromAngleAndSpeed(60, speed);
        }
        if (collisionLine.isIntersecting(this.getCollisionRectangle().getLeft())) {
            return new Velocity(-dx, dy);
        }
        return new Velocity(dx, -dy);
    }

    /**
     * the function adds this paddle to the game.
     *
     * @param game - the game
     */
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
        game.addSprite(this);
    }
}
