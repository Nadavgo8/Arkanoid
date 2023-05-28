// 209702745 Nadav Gonen
/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-09-03
 */

import java.awt.Color;
import java.util.List;

/**
 * Classname: GameLevel.
 */
public interface LevelInformation {
    /**
     * The function returns the number of balls.
     *
     * @return number of balls.
     */
    int numberOfBalls();
    /**
     * The function initializes the velocity of each ball.
     *
     * @return velocities list.
     */
    List<Velocity> initialBallVelocities();

    /**
     * The function returns the paddle's speed.
     *
     * @return the paddle's speed.
     */
    int paddleSpeed();

    /**
     * The function returns the paddle's width.
     *
     * @return the paddle's width.
     */
    int paddleWidth();

    /**
     * The function returns the level's name.
     *
     * @return the level's name.
     */
    String levelName();

    /**
     * The function returns level's background.
     *
     * @return the level's background.
     */
    Sprite getBackground();

    /**
     * The function returns this level's blocks.
     *
     * @return list of blocks.
     */
    List<Block> blocks();

    /**
     * The function returns the number of blocks that should be removed.
     *
     * @return number of blocks.
     */
    int numberOfBlocksToRemove();
    /**
     * The function returns the background's color.
     *
     * @return color.
     */
    Color getBackgroundColor();
}
