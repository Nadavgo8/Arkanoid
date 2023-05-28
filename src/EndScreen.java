// 209702745 Nadav Gonen

import biuoop.DrawSurface;


/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-09-03
 */

public class EndScreen implements Animation {
    private Counter score;
    private boolean win;

    /**
     * The constructor.
     *
     * @param score - the score.
     * @param win - a boolean value indicating a win/loss.
     */
    public EndScreen(Counter score, boolean win) {
        this.score = score;
        this.win = win;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (win) {
            d.drawText(200, 300, "You Win! Your score is " + this.score.getValue(), 30);
        } else {
            d.drawText(200, 300, "Game Over. Your score is " + this.score.getValue(), 30);
        }
    }

    @Override
    public boolean shouldStop() {
        return true;
    }
}
