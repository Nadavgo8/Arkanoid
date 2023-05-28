
public class Velocity {
    private double dx = 0;
    private double dy = 0;

    /**
     * A constructor.
     *
     * @param dx double.
     * @param dy color.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * this function changes the dx.
     *
     * @param dx double.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }
    /**
     * this function changes the dy.
     *
     * @param dy double.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }
    /**
     * this function returns the dx.
     *
     * @return  dx double.
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * this function returns the dy.
     *
     * @return  dy double.
     */
    public double getDy() {
        return this.dy;
    }
    /**
     * this function sets the velocity using radians.
     * @param angle double.
     * @param speed double.
     * @return  Velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * -Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * this function sets moves the point according to the velocity.
     * @param p point.
     * @return  point.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}

