// 209702745 Nadav Gonen
/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-09-03
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * Classname: Level1.
 */
public class Level1 implements LevelInformation {

    /**
     * The constructor.
     */
    public Level1() {
    }

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(0, -4));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Background(Color.green, this.levelName());
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int blockWidth = 30;
        int blockHeight = 30;
        Point upperLeft = new Point(385, 200);
        Block block = new Block(upperLeft, blockWidth, blockHeight);
        block.setColor(Color.red);
        blocks.add(block);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public Color getBackgroundColor() {
        return Color.green;
    }
}
