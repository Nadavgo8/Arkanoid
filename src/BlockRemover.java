// 209702745 Nadav Gonen

/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-09-03
 */

/**
 * A constructor.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * A constructor.
     *
     * @param game            the game.
     * @param remainingBlocks the number of remaining blocks.
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * This function removes blocks that are hit.
     *
     * @param beingHit the block.
     * @param hitter   the ball that hit.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }
}
