

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
