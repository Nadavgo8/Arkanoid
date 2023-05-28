// 209702745 Nadav Gonen
/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-09-03
 */

import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * Classname: CountdownAnimation.
 */

public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean running;
    private Sleeper sleeper;

    /**
     * The constructor.
     *
     * @param numOfSeconds - the number of seconds to count for.
     * @param countFrom    - the number of seconds to count from.
     * @param gameScreen   - the game screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.running = true;
        this.sleeper = new Sleeper();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.blue);
        d.fillRectangle(0, 0, (int) GameLevel.WIDTH, GameLevel.HEIGHT);
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.magenta);
        d.drawText(380, 350, Integer.toString(this.countFrom), 70);
        if (this.countFrom != 3) {
            this.sleeper.sleepFor((long) ((this.numOfSeconds * 1000) / 3));
        }
        if (this.countFrom == 0) {
            this.running = false;
        }
        this.countFrom--;
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
