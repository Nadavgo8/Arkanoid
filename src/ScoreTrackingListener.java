// 209702745 Nadav Gonen
/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-08-03
 */

public class ScoreTrackingListener implements HitListener {
        private Counter currentScore;

        /**
         * A constructor.
         *
         * @param scoreCounter - the points counter.
         */
        public ScoreTrackingListener(Counter scoreCounter) {
            this.currentScore = scoreCounter;
        }

        @Override
        public void hitEvent(Block beingHit, Ball hitter) {
            this.currentScore.increase(5);
        }
    }
