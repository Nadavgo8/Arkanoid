// 209702745 Nadav Gonen
/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-09-03
 */

import java.awt.Color;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * Classname: GameLevel.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    public static final double WIDTH = 800;
    public static final int HEIGHT = 600;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 25;
    public static final double PADDLE_WIDTH = 80;
    public static final double PADDLE_HEIGHT = 20;
    public static final int WALL_SIZE = 20;
    private Counter numOfBlocks;
    private BlockRemover blockRemover;
    private Counter numOfBalls;
    private BallRemover ballsRemover;
    private Counter score;
    private ScoreTrackingListener scoreTrackingListener;
    private ScoreIndicator scoreIndicator;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    /**
     * The function adds a collidable to the list.
     *
     * @param c - thecollidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * The function removes a collidable to the list.
     *
     * @param c - thecollidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * The constructor.
     *
     * @param lI          - the level.
     * @param runner      - the animation runner.
     * @param gui         - the gui.
     * @param score       - the score.
     * @param numOfBlocks - teh number of blocks.
     * @param numOfBalls  - the number of balls.
     */
    public GameLevel(LevelInformation lI, AnimationRunner runner, GUI gui, Counter score,
                     Counter numOfBlocks, Counter numOfBalls) {
        this.gui = gui;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.numOfBlocks = numOfBlocks;
        this.numOfBalls = numOfBalls;
        this.blockRemover = new BlockRemover(this, this.numOfBlocks);
        this.ballsRemover = new BallRemover(this, this.numOfBalls);
        this.score = score;
        this.scoreTrackingListener = new ScoreTrackingListener(score);
        this.runner = runner;
        this.keyboard = gui.getKeyboardSensor();
        this.levelInformation = lI;
    }

    /**
     * The function adds a sprite to the list.
     *
     * @param s - the sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * The function removes a sprite to the list.
     *
     * @param s - the sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * The function adds the walls.
     */
    private void addWalls() {
        Point upperLeft = new Point(0, 0);
        double x = upperLeft.getX();
        double y = upperLeft.getY();
        int width = 800;
        int height = 600;
        Block left = new Block(new Point(x, y + WALL_SIZE), WALL_SIZE, height - WALL_SIZE);
        Block right = new Block(new Point(x + width - WALL_SIZE, y + WALL_SIZE), WALL_SIZE, height - WALL_SIZE);
        Block top = new Block(upperLeft, width, WALL_SIZE);
        Block bottom = new Block(new Point(x + WALL_SIZE, y + height), width - (2 * WALL_SIZE), WALL_SIZE);
        left.setColor(Color.LIGHT_GRAY);
        right.setColor(Color.LIGHT_GRAY);
        top.setColor(Color.LIGHT_GRAY);
        bottom.setColor(Color.LIGHT_GRAY);
        left.addToGame(this);
        right.addToGame(this);
        top.addToGame(this);
        bottom.addToGame(this);
        bottom.addHitListener(this.ballsRemover);
    }

    /**
     * The function adds a ball.
     *
     * @param numOfBalls - the number of balls
     */
    private void addBall(int numOfBalls) {
        Ball ball = new Ball(400, 550, 8, Color.WHITE);
        ball.setVelocity(levelInformation.initialBallVelocities().get(numOfBalls));
        ball.setGameEnvironment(this.environment);
        ball.addToGame(this);
        this.numOfBalls.increase(1);
    }

    /**
     * The function adds the blocks.
     *
     * @param blocks - the list of blocks we want to add.
     */
    private void addBlocks(List<Block> blocks) {
        for (Block b : blocks) {
            b.addToGame(this);
            b.addHitListener(this.blockRemover);
            b.addHitListener(this.scoreTrackingListener);
            this.numOfBlocks.increase(1);
        }
    }

    /**
     * The function adds the paddle.
     */
    private void addPaddle() {
        // We want it to start in the middle and on the bottom wall.
        Point upperLeft = new Point(
                WIDTH / 2 - (float) levelInformation.paddleWidth() / 2, HEIGHT - WALL_SIZE - PADDLE_HEIGHT);
        Rectangle rectangle = new Rectangle(upperLeft, levelInformation.paddleWidth(), PADDLE_HEIGHT);
        KeyboardSensor kb = this.gui.getKeyboardSensor();
        Paddle paddle = new Paddle(rectangle, Color.ORANGE, kb);
        paddle.addToGame(this);
    }

    /**
     * The function initializes a new game: creates the Blocks and Ball (and Paddle)
     * and adds them to the game.
     */
    public void initialize() {
        this.numOfBalls.decrease(this.numOfBalls.getValue());
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            addBall(i);
        }
        addBlocks(this.levelInformation.blocks());
        addSprite(this.levelInformation.getBackground());
        addWalls();
        addPaddle();
        this.scoreIndicator = new ScoreIndicator(score, this);

    }

    /**
     * the function Runs the game -- starts the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.numOfBlocks.getValue() == 0) {
            this.running = false;
        }
        if (this.numOfBalls.getValue() == 0) {
            this.running = false;
        }
        d.setColor(this.levelInformation.getBackgroundColor());
        d.fillRectangle(0, 0, (int) WIDTH, HEIGHT);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * the function returns the level information.
     *
     * @return level information.
     */
    public LevelInformation getLevelInformation() {
        return this.levelInformation;
    }
}