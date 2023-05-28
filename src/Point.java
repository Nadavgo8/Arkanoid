// 209702745 Nadav Gonen

/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-08-03
 */

/**
 * Classname: Point
 * Description:  Each Point has x and y values.
 * We can measure the distance between points and  check if it is equal to other points.
 */

public class Point {
    public static final double NEGLIGIBLE_ERROR = Math.pow(10, -10);
    private double x = 0;
    private double y = 0;

    /**
     * Constructor.
     *
     * @param x value.
     * @param y value.
     */
    public Point(double x, double y) {
        setX(x);
        setY(y);
    }

    /**
     * This function returns the distance between this point and the other point.
     *
     * @param other point variable.
     * @return The distance.
     */
    public double distance(Point other) {
        double xDis = (this.getX() - other.getX()) * (this.getX() - other.getX());
        double yDis = (this.getY() - other.getY()) * (this.getY() - other.getY());
        return Math.sqrt(yDis + xDis);
    }

    /**
     * This function checks if two doubles are equal (including the negligible error).
     *
     * @param a is a double.
     * @param b is a double.
     * @return true/false.
     */
    public static boolean doubleEqual(double a, double b) {
        return Math.abs(a - b) < NEGLIGIBLE_ERROR;

    }

    /**
     * This function returns if the two points are equal.
     *
     * @param other point variable.
     * @return True/False.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return doubleEqual(other.getX(), this.getX()) && doubleEqual(other.getY(), this.getY());
    }

    /**
     * This function returns the point's x value.
     *
     * @return x value.
     */
    public double getX() {
        return x;
    }

    /**
     * This function returns the point's y value.
     *
     * @return y value.
     */
    public double getY() {
        return y;
    }

    /**
     * This function changes the point's x value.
     *
     * @param x value.
     */

    public void setX(double x) {
        this.x = x;
    }

    /**
     * This function changes the point's y value.
     *
     * @param y value.
     */
    public void setY(double y) {
        this.y = y;
    }

}
