

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * Classname: Level2.
 */
public class Level2 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 1; i <= numberOfBalls() / 2; i++) {
            Velocity velocity = new Velocity(i - 6, i * -3);
            velocities.add(velocity);
        }
        for (int i = 1; i <= numberOfBalls() / 2; i++) {
            Velocity velocity = new Velocity(6 - i, i * -3);
            velocities.add(velocity);
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 2;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Background(Color.green, this.levelName());
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Point upperLeft = new Point(GameLevel.WALL_SIZE + i * 50, (double) GameLevel.HEIGHT / 2);
            Block block = new Block(upperLeft, 50, 20);
            block.setColor(Color.red);
            blocks.add(block);
        }
        return blocks;
    }


    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }

    @Override
    public Color getBackgroundColor() {
        return Color.green;
    }
}
