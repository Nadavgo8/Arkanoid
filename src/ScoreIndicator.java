
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * Classname: ScoreIndicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private GameLevel game;
    /**
     * A constructor.
     * @param score the score.
     * @param game the game.
     */
    public ScoreIndicator(Counter score, GameLevel game) {
        this.score = score;
        this.game = game;
        game.addSprite(this);
    }
    /**
     * The function draws the score indicator on the given DrawSurface.
     *
     * @param d - the surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, (int) GameLevel.WIDTH, GameLevel.WALL_SIZE);
        d.setColor(Color.BLACK);
        d.drawText((int) GameLevel.WIDTH / 2, GameLevel.WALL_SIZE - 3, "Score:" + " " + score.getValue(),
                GameLevel.WALL_SIZE - 5);
    }

    @Override
    public void timePassed() {

    }
}
