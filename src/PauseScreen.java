// 209702745 Nadav Gonen
/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-09-03
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Classname: PauseScreen.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * The constructor.
     *
     * @param k - the keyboard sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(180, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
