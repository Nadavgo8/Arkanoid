

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * Classname: Level4.
 */
public class Level4 implements LevelInformation {
    private int NumOfblocks;
    /**
     * The function returns the number of balls.
     *
     * @return number of balls.
     */
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(0, -6));
        velocities.add(new Velocity(4, -7));
        velocities.add(new Velocity(-4, -7));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 90;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Background(Color.green, this.levelName());
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colors = {Color.red, Color.white};
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                Point upperLeft = new Point(GameLevel.WIDTH - GameLevel.WALL_SIZE - 51 - j * 51, 300 - 20 * i);
                Block block = new Block(upperLeft, 51, 20);
                block.setColor(colors[i % 2]);
                blocks.add(block);
            }
        }
        this.NumOfblocks = blocks.size();
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.NumOfblocks;
    }

    @Override
    public Color getBackgroundColor() {
        return Color.green;
    }
}
