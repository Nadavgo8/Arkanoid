// 209702745 Nadav Gonen
/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-09-03
 */

import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * Classname: GameFlow.
 */
public class GameFlow {
    private GUI gui;
    private AnimationRunner runner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private Counter numOfBlocks;
    private Counter numOfBalls;
    private boolean win;

    /**
     * The constructor.
     *
     * @param ar  - the animation runner.
     * @param ks  - the keyboard sensor.
     * @param gui - the gui.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.runner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.score = new Counter();
        this.numOfBlocks = new Counter();
        this.numOfBalls = new Counter();
        this.win = true;
    }

    /**
     * The function runs the levels from the list.
     *
     * @param levels - the levels list.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.runner, this.gui,
                    this.score, this.numOfBlocks, this.numOfBalls);
            level.initialize();
            while (this.numOfBlocks.getValue() > 0 && this.numOfBalls.getValue() > 0) {
                level.run();
            }
            if (this.numOfBlocks.getValue() == 0) {
                this.score.increase(100);
            }
            if (this.numOfBalls.getValue() == 0) {
                this.win = false;
                break;
            }
        }
        this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                new EndScreen(this.score, win)));
//        new EndScreen(this.score, win);
        gui.close();
    }
}

