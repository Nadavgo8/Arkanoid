// 209702745 Nadav Gonen
/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-08-03
 */
import java.util.List;
import java.util.ArrayList;
/**
 * Classname: Rectangle.
 */
public class Rectangle {
    private Point upperleft;
    private double width;
    private double height;

    /**
     * The function creates a new rectangle with location and width/height.
     *
     * @param upperLeft the upper left point of the rectangle
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperleft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * The function returns a (possibly empty) list of intersection points with the specified line.
     *
     * @param line the line we check if intersects with the rectangle.
     * @return  A list of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> pointList = new ArrayList<>();
        Point point1 = line.intersectionWith(this.getLeft());
        Point point2 = line.intersectionWith(this.getRight());
        Point point3 = line.intersectionWith(this.getBottom());
        Point point4 = line.intersectionWith(this.getTop());
        if (point1 != null) {
            pointList.add(point1);
        }
        if (point2 != null) {
            pointList.add(point2);
        }
        if (point3 != null && !point3.equals(point1) && !point3.equals(point2)) {
            //Making sure that there aren't duplicated intersecting points.
            pointList.add(point3);
        }
        if (point4 != null && !point4.equals(point1) && !point4.equals(point2)) {
            //Making sure that there aren't duplicated intersecting points.
            pointList.add(point4);
        }
        return pointList;
    }


    /**
     * The function returns the width of the rectangle.
     *
     * @return double the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * The function returns the height of the rectangle.
     *
     * @return double the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * The function returns the left side of the rectangle.
     *
     * @return line.
     */
    public Line getLeft() {
        return new Line(this.upperleft, new Point(this.getUpperLeft().getX(),
                this.getUpperLeft().getY() + this.getHeight()));
    }

    /**
     * The function returns the right side of the rectangle.
     *
     * @return line.
     */
    public Line getRight() {
        Point up = new Point(this.getUpperLeft().getX() + this.getWidth(), this.upperleft.getY());
        Point down = new Point(this.getUpperLeft().getX() + this.getWidth(), this.upperleft.getY() + this.getHeight());
        return new Line(up, down);
    }

    /**
     * The function returns the top side of the rectangle.
     *
     * @return line.
     */
    public Line getTop() {
        Point topRight = new Point(this.getUpperLeft().getX(), this.upperleft.getY() + this.getHeight());
        Point topLeft = new Point(this.getUpperLeft().getX() + this.width, this.upperleft.getY() + this.getHeight());
        return new Line(topLeft, topRight);
    }
    /**
     * The function returns the bottom side of the rectangle.
     *
     * @return line.
     */

    public Line getBottom() {
        Point bottomRight = new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY());
        Point bottomLeft = new Point(this.getUpperLeft().getX(),
                this.getUpperLeft().getY());
        return new Line(bottomLeft, bottomRight);
    }


    /**
     * The function returns the upper-left point of the rectangle.
     *
     * @return point the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperleft;
    }
}