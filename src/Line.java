//Nadav Gonen 209702745

import java.util.List;


/**
 * Classname: Line
 * Description: Each line has a start point and an end point.
 * We can checks the line's length, get it's middle, check if it intersects with another line and if so, where they do.
 * Furthermore, we can get it's slope and check if two lines are equal.
 */
public class Line {
    private Point start = null, end = null;
    public static final double NEGLIGIBLE_ERROR = Math.pow(10, -10);

    /**
     * Point constructor.
     *
     * @param end   is a point.
     * @param start is a point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Point constructor.
     *
     * @param x1 is a double.
     * @param y1 is a double.
     * @param x2 is a double.
     * @param y2 is a double.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * This function calculates the length of the line.
     *
     * @return the distance.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * This function returns the middle of the line.
     *
     * @return a point.
     */
    public Point middle() {
        Point mid = new Point(0, 0);
        mid.setX((this.start.getX() + this.end.getX()) / 2);
        mid.setY((this.start.getY() + this.end.getY()) / 2);
        return mid;
    }

    /**
     * This function returns the start point of the line.
     *
     * @return a point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * This function returns the end point of the line.
     *
     * @return a point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * This function checks if two doubles are equal (including the negligible error).
     *
     * @param a is a double.
     * @param b is a double.
     * @return true/false.
     */
    public Boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < NEGLIGIBLE_ERROR;
    }

    /**
     * This function returns if the other line intersects with the line.
     *
     * @param other line.
     * @return true/false.
     */
    public boolean isIntersecting(Line other) {
        double minX1, maxX1, minY1, maxY1, minX2, maxX2, minY2, maxY2;
        minX1 = Math.min(this.start.getX(), this.end.getX());
        maxX1 = Math.max(this.start.getX(), this.end.getX());
        minY1 = Math.min(this.start.getY(), this.end.getY());
        maxY1 = Math.max(this.start.getY(), this.end.getY());
        minX2 = Math.min(other.start.getX(), other.end.getX());
        maxX2 = Math.max(other.start.getX(), other.end.getX());
        minY2 = Math.min(other.start.getY(), other.end.getY());
        maxY2 = Math.max(other.start.getY(), other.end.getY());
        if (doubleEquals(minX1, maxX1)) { //This line is parallel to the y-axis.
            if (doubleEquals(minY2, maxY2)) { //The other line is parallel to the x-axis.
                return this.parYAndParX(other);
            }
            if (doubleEquals(minX2, maxX2)) { //The other line is parallel to the y-axis.
                return this.bothParY(other) > 0;
            }
            return this.oneIsParY(other); //The other line has a slope.
        }
        if (doubleEquals(minX2, maxX2)) { //The other line is parallel to the y-axis.
            if (doubleEquals(minY1, maxY1)) { //This line is parallel to the x-axis.
                return other.parYAndParX(this);
            }
            return other.oneIsParY(this); //This line has a slope.
        }
        if (doubleEquals(minY1, maxY1)) { //This line is parallel to the x-axis.
            if (doubleEquals(minY2, maxY2)) { //The other line is parallel to the x-axis.
                return this.bothParX(other) > 0;
            }
            return this.oneIsParX(other); //The other line has a slope.
        }
        //Now we know that this line has a slope.
        if (doubleEquals(minY2, maxY2)) { //The other line is parallel to the x-axis.
            return other.oneIsParX(this);
        }
        if (this.slope() == other.slope()) {
            return this.sameSlope(other) > 0;
        }
        return this.diffSlope(other);
    }

    /**
     * This function returns the intetsection point if it exists, and null if not.
     *
     * @param other line.
     * @return intersection point.
     */
    public Point intersectionWith(Line other) {
        if (this.equals(other)) {
            return null;
        }
        if (!this.isIntersecting(other)) {
            return null;
        }
        double minX1, maxX1, minY1, maxY1, minX2, maxX2, minY2, maxY2;
        minX1 = Math.min(this.start.getX(), this.end.getX());
        maxX1 = Math.max(this.start.getX(), this.end.getX());
        minY1 = Math.min(this.start.getY(), this.end.getY());
        maxY1 = Math.max(this.start.getY(), this.end.getY());
        minX2 = Math.min(other.start.getX(), other.end.getX());
        maxX2 = Math.max(other.start.getX(), other.end.getX());
        minY2 = Math.min(other.start.getY(), other.end.getY());
        maxY2 = Math.max(other.start.getY(), other.end.getY());
        if (doubleEquals(minX1, maxX1)) { //Parallel to y.
            if (doubleEquals(minX2, maxX2)) { //Other is parallel to y.
                if (this.bothParY(other) == 1) { //One intersection point.
                    if (doubleEquals(minX1, maxX2)) {
                        return new Point(minX1, minY1);
                    }
                    return new Point(maxX1, minY1);
                }
                return null; //More than one intersection point.
            }
            if (doubleEquals(minY2, maxY2)) { //Other is parallel to x.
                return new Point(minX1, minY2);
            }
            /// The other isn't parallel to any axis.
            double slope, b;
            slope = other.slope();
            b = getDepVariable(other, slope);
            return new Point(minX1, (slope * minX1) + b); ////Finding the y by placing the x1 in y = ax+b.
        }
        if (doubleEquals(minY1, maxY1)) { //Parallel to x.
            if (doubleEquals(minY2, maxY2)) { //Other is parallel to x.
                if (this.bothParX(other) == 1) {
                    if (doubleEquals(minX1, maxX2)) {
                        return new Point(minX1, minY1);
                    }
                    return new Point(maxX1, minY1);
                }
                return null; //More than one intersection point.
            }
            if (doubleEquals(minX2, maxX2)) { //Other is parallel to y.
                return new Point(minX2, minY1);
            }
            /// The other isn't parallel to any axis.
            double slope, b;
            slope = other.slope();
            b = getDepVariable(other, slope);
            double y1 = minY1;
            return new Point((y1 - b) / slope, y1); ////Finding the x by placing the y1 in y1 = ax+b -> x = (y1-b)/a
        }
        //This line has a slope different from 0.
        double slope1 = this.slope();
        double b = getDepVariable(this, slope1);
        if (doubleEquals(minX2, maxX2)) { //Other is parallel to y.
            return new Point(minX2, slope1 * minX1 + b); //Because y = ax+b.
        }
        double slope2 = other.slope();
        if (slope1 == slope2) {
            if (this.sameSlope(other) == 1) {
                if (this.start.equals(other.start) || this.start.equals(other.end)) {
                    return this.start;
                }
                return this.end;
            }
            return null; //More than one intersection point.
        }
        if (doubleEquals(minY2, maxY2)) { //Other is parallel to x.
            double y2 = minY2;
            return new Point((y2 - b) / slope1, y2); ////Finding the x by placing the y2 in y2 = ax+b -> x = (y2-b)/a
        }
        //They have different slopes.
        double intersectionX = (slope1 * this.start.getX() - slope2 * other.start.getX()
                + other.start.getY() - this.start.getY()) / (slope1 - slope2);
        double intersectionY = slope1 * intersectionX - slope1 * this.start.getX() + this.start.getY();
        return new Point(intersectionX, intersectionY);


    }

    /**
     * The function checks if the line intersects with the other line.
     * This line is parallel to the y-axis and the other to the x-axis.
     *
     * @param other represents the other line.
     * @return boolean - true if they intersect, false otherwise.
     */
    public boolean parYAndParX(Line other) {
        double x1 = this.start.getX();
        double y2 = other.start.getY();
        double minY1, maxY1, minX2, maxX2;
        minY1 = Math.min(this.start.getY(), this.end.getY());
        maxY1 = Math.max(this.start.getY(), this.end.getY());
        minX2 = Math.min(other.start.getX(), other.end.getX());
        maxX2 = Math.max(other.start.getX(), other.end.getX());
        if (x1 > maxX2 + NEGLIGIBLE_ERROR || x1 + NEGLIGIBLE_ERROR < minX2) {
            return false;
        }
        return !(y2 > maxY1 + NEGLIGIBLE_ERROR) && !(y2 + NEGLIGIBLE_ERROR < minY1);
    }
    /**
     * The function checks if the line intersects with the other line.
     * This line is parallel to the y-axis and the other to the y-axis.
     *
     * @param other represents the other line.
     * @return boolean - true if they intersect, false otherwise.
     */
    public int bothParY(Line other) {
        double minY1, maxY1, minY2, maxY2;
        minY1 = Math.min(this.start.getY(), this.end.getY());
        maxY1 = Math.max(this.start.getY(), this.end.getY());
        minY2 = Math.min(other.start.getY(), other.end.getY());
        maxY2 = Math.max(other.start.getY(), other.end.getY());
        if (!doubleEquals(this.start.getX(), other.start.getX())) {
            return 0;
        }
        if (doubleEquals(maxY1, minY2) || doubleEquals(minY1, maxY2)) {
            return 1; // Meaning that there is one intersection point.
        }
        return 2; // More than one intersection points.
    }
    /**
     * The function checks if the line intersects with the other line.
     * This line is parallel to the x-axis and the other to the x-axis.
     *
     * @param other represents the other line.
     * @return boolean - true if they intersect, false otherwise.
     */
    public int bothParX(Line other) {
        double minX1, maxX1, minX2, maxX2;
        minX1 = Math.min(this.start.getX(), this.end.getX());
        maxX1 = Math.max(this.start.getX(), this.end.getX());
        minX2 = Math.min(other.start.getX(), other.end.getX());
        maxX2 = Math.max(other.start.getX(), other.end.getX());
        if (!doubleEquals(this.start.getY(), other.start.getY())) {
            return 0;
        }
        if (doubleEquals(maxX1, minX2) || doubleEquals(minX1, maxX2)) {
            return 1; // Meaning that there is one intersection point.
        }
        return 2; // More than one intersection points.
    }
    /**
     * The function checks if the line intersects with the other line.
     * This line is parallel to the y-axis.
     *
     * @param other represents the other line.
     * @return boolean - true if they intersect, false otherwise.
     */
    public boolean oneIsParY(Line other) {
        double minY1, maxY1, minX2, maxX2, minY2, maxY2;
        double x1 = this.start.getX();
        minY1 = Math.min(this.start.getY(), this.end.getY());
        maxY1 = Math.max(this.start.getY(), this.end.getY());
        minX2 = Math.min(other.start.getX(), other.end.getX());
        maxX2 = Math.max(other.start.getX(), other.end.getX());
        return (other.slope() * this.start.getX() - other.slope() * other.start.getX() + other.start.getY()
                >= minY1)
                && (other.slope() * this.start.getX() - other.slope() * other.start.getX() + other.start.getY()
                <= maxY1)
                && this.start.getX() <= maxX2
                && this.start.getX() >= minX2;
    }
    /**
     * The function checks if the line intersects with the other line.
     * The lines have the same slope.
     *
     * @param other represents the other line.
     * @return boolean - true if they intersect, false otherwise.
     */
    public int sameSlope(Line other) {
        double minX1, maxX1, minY1, maxY1, minX2, maxX2, minY2, maxY2;
        minX1 = Math.min(this.start.getX(), this.end.getX());
        maxX1 = Math.max(this.start.getX(), this.end.getX());
        minY1 = Math.min(this.start.getY(), this.end.getY());
        maxY1 = Math.max(this.start.getY(), this.end.getY());
        minX2 = Math.min(other.start.getX(), other.end.getX());
        maxX2 = Math.max(other.start.getX(), other.end.getX());
        minY2 = Math.min(other.start.getY(), other.end.getY());
        maxY2 = Math.max(other.start.getY(), other.end.getY());
        if (maxX1 + NEGLIGIBLE_ERROR < minX2 || minX1 > maxX2 + NEGLIGIBLE_ERROR) {
            return 0;
        }
        if (maxY1 + NEGLIGIBLE_ERROR > minY2 || minY1 > maxY2 + NEGLIGIBLE_ERROR) {
            return 0;
        }
        if (doubleEquals(minX1, maxX2) || doubleEquals(maxX1, minX2)) { //One intersection point.
            return 1;
        }
        return 2; //More than one intersection point.
    }
    /**
     * The function checks if the line intersects with the other line.
     * This line is parallel to the x-axis.
     *
     * @param other represents the other line.
     * @return boolean - true if they intersect, false otherwise.
     */
    public boolean oneIsParX(Line other) {
        double minX1, maxX1, minX2, maxX2, minY2, maxY2;
        minX1 = Math.min(this.start.getX(), this.end.getX());
        maxX1 = Math.max(this.start.getX(), this.end.getX());
        minX2 = Math.min(other.start.getX(), other.end.getX());
        maxX2 = Math.max(other.start.getX(), other.end.getX());
        minY2 = Math.min(other.start.getY(), other.end.getY());
        maxY2 = Math.max(other.start.getY(), other.end.getY());
        return (this.start.getY() - other.start.getY()) / other.slope() + other.start.getX()
                >= minX1
                && (this.start.getY() - other.start.getY()) / other.slope() + other.start.getX()
                <= maxX1
                && this.start.getY() <= maxY2
                && this.start.getY() >= minY2;
    }
    /**
     * The function checks if the line intersects with the other line.
     * The lines have different slopes.
     *
     * @param other represents the other line.
     * @return boolean - true if they intersect, false otherwise.
     */
    public boolean diffSlope(Line other) {
        double slope1 = this.slope();
        double slope2 = other.slope();
        double intersectionX = (slope1 * this.start.getX() - slope2 * other.start.getX()
                + other.start.getY() - this.start.getY()) / (slope1 - slope2);
        double intersectionY = slope1 * intersectionX - slope1 * this.start.getX() + this.start.getY();
        return this.isPointOnLine(intersectionX, intersectionY, this);
    }
    /**
     * The function checks if the rectangle intersects this line and if so,
     * returns the closest one to the start of the line.
     *
     * @param rect represents the rectangle.
     * @return point - the closest intersection point to the start of the line, or null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> pointList = rect.intersectionPoints(this);
        if (pointList.isEmpty()) {
            return null;
        }
        if (pointList.size() == 1) {
            return pointList.get(0);
        }
        double d1 = this.start.distance(pointList.get(0));
        double d2 = this.start.distance(pointList.get(1));
        if (d1 > d2) {
            return pointList.get(1);
        }
        return pointList.get(0);
    }

    /**
     * This function returns if the given point (in x and y) is on the line.
     *
     * @param x    is a double.
     * @param y    is a double.
     * @param line line.
     * @return true/false.
     */
    public boolean isPointOnLine(double x, double y, Line line) {
        double minX, maxX, minY, maxY;
        minX = Math.min(line.start.getX(), line.end.getX());
        maxX = Math.max(line.start.getX(), line.end.getX());
        minY = Math.min(line.start.getY(), line.end.getY());
        maxY = Math.max(line.start.getY(), line.end.getY());
        return (x < maxX || Point.doubleEqual(x, maxX))
                && (x > minX || Point.doubleEqual(x, minX))
                && (y < maxY || Point.doubleEqual(y, maxY))
                && (y > minY || Point.doubleEqual(y, minY));
    }

    /**
     * This function returns true if the lines are equal, false otherwise.
     *
     * @param other line.
     * @return true/false.
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        return this.start.equals(other.end) && this.end.equals(other.start);
    }

    /**
     * This function returns the line's slope.
     *
     * @return slope, double.
     */
    private double slope() {
        if (start.getY() == end.getY()) {
            return 0;
        }
        return ((start.getY() - end.getY()) / (start.getX() - end.getX()));
    }

    /**
     * This function returns the line's dependant variable. ('b' in y = ax + b).
     *
     * @param line  Line
     * @param slope double.
     * @return the dependant variable, double.
     */
    private double getDepVariable(Line line, double slope) {
        return (line.start.getY() - (slope * line.start.getX()));
        //Because y-y1 = m(x-x1) -> y = mx - mx1 + y1 = mx + (y1 - mx1) and (y1 - mx1) is the 'b'.
    }
    //public static void main(String[] args) {

    // }


}

/*************************************************************************/





