// 209702745 Nadav Gonen
/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-09-03
 */

public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * A constructor.
     *
     * @param game           - the game.
     * @param remainingBalls - the number of remaining balls.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * The function removes the ball from the game and decreases the number of balls by one.
     *
     * @param beingHit - the block that was hit.
     * @param hitter   - the ball that hit.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        hitter.removeHitListener(this);
        this.remainingBalls.decrease(1);
    }
}
