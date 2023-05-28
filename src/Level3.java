
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Classname: Level3.
 */
public class Level3 implements LevelInformation {
    private int numOfBlocks;

    /**
     * The constructor.
     */
    public Level3() {
    }

    @Override
    public int numberOfBalls() {
        return 5;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(5, -4));
        velocities.add(new Velocity(4, -5));
        velocities.add(new Velocity(5, -4));
        velocities.add(new Velocity(3, -5));
        velocities.add(new Velocity(3, -4));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Background(Color.green, this.levelName());
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colors = {Color.white, Color.red};
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < i + 6; j++) {
                Point upperLeft = new Point(GameLevel.WIDTH - GameLevel.WALL_SIZE - 50 - j * 50, 300 - 20 * i);
                Block block = new Block(upperLeft, 50, 20);
                block.setColor(colors[i % 2]);
                blocks.add(block);
                if (i == 4) {
                    Block block2 = new Block(upperLeft, 50, 20);
                    block2.setColor(colors[i % 2]);
                    blocks.add(block2);
                }
            }
        }
        this.numOfBlocks = blocks.size();
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numOfBlocks;
    }

    @Override
    public Color getBackgroundColor() {
        return Color.green;
    }
}
