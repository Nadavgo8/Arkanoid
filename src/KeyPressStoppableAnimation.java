// 209702745 Nadav Gonen
/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-09-03
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Classname: KeyPressStoppableAnimation.
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean running;
    private boolean isAlreadyPressed;

    /**
     * The constructor.
     *
     * @param sensor    - the keyboard sensor.
     * @param key       - the pressed key.
     * @param animation - the screen shown after the key is pressed.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.running = true;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.sensor.isPressed(this.key) && !isAlreadyPressed) {
            this.running = false;
        } else {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
