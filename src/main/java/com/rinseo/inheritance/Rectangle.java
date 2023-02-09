package com.rinseo.inheritance;

public class Rectangle extends Shape{
    private double width;
    private double length;
    private MovablePoint topLeft;
    private MovablePoint bottomRight;
    /**
     *  y
     *  ^    topLeft
     *  |    (x,y)      (x,y)
     *  |
     *  |               bottomRight
     *  |   (x,y)       (x,y)
     *  |
     *  +-------------------> x
     *  Assuming that topY is always greater than bottomY to be a valid rectangle
     *  Assuming that bottomX is always greater than topX to be a valid rectangle
     */

    public Rectangle(String color, boolean isFilled, double width, double length) {
        super(color, isFilled);
        this.width = width;
        this.length = length;
        this.topLeft = new MovablePoint(0.0, length);
        this.bottomRight = new MovablePoint(width, 0.0);
    }
    public Rectangle(String color, boolean isFilled, double width, double length, MovablePoint topLeft, MovablePoint bottomRight) {
        super(color, isFilled);
        this.width = width;
        this.length = length;

        boolean sideY = topLeft.getY() > bottomRight.getY();
        boolean sideX = bottomRight.getX() > topLeft.getX();

        if (!sideY && sideX) {
            this.topLeft = new MovablePoint(0.0, length);
            this.bottomRight = new MovablePoint(width, 0.0);
        } else {
            this.topLeft = topLeft;
            this.bottomRight = bottomRight;
        }
    }


    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if (width < 0) {
            throw new IllegalArgumentException("Width cannot be negative.");
        } else {
            this.width = width;
        }
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        if (length < 0) {
            throw new IllegalArgumentException("Length cannot be negative.");
        } else {
            this.length = length;
        }
    }

    public MovablePoint getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(MovablePoint topLeft) {
        if (topLeft.getY() < bottomRight.getY() ) {
            throw new IllegalArgumentException("Top left point cannot be below bottom right point.");
        } else {
            this.topLeft = topLeft;
        }
    }

    public MovablePoint getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(MovablePoint bottomRight) {
        if (bottomRight.getX() < topLeft.getX()) {
            throw new IllegalArgumentException("Bottom right point cannot be to the left of top left point.");
        } else {
            this.bottomRight = bottomRight;
        }
    }

    @Override
    public double getArea() {
        // A = side * side
        return this.length * this.width;
    }

    @Override
    public double getCircumference() {
        System.out.println("Rectangle has no circumference");
        return -1;
    }

    @Override
    public double getPerimeter() {
        // perimeter = side * side ^ 2
        return (this.length + this.width) * 2;
    }

    @Override
    public void moveUp(double distance) {
        this.topLeft.moveUp(distance);
        this.bottomRight.moveUp(distance);
    }

    @Override
    public void moveDown(double distance) {
        this.topLeft.moveUp(distance);
        this.bottomRight.moveUp(distance);
    }

    @Override
    public void moveLeft(double distance) {
        this.topLeft.moveUp(distance);
        this.bottomRight.moveUp(distance);
    }

    @Override
    public void moveRight(double distance) {
        this.topLeft.moveUp(distance);
        this.bottomRight.moveUp(distance);
    }

    @Override
    public String toString() {
        return String.format("A Rectangle with id: %s, length: %s, width: %s, topLeft: %s, bottomRight %s, that has an area: %s, perimeter of: %s, which is %s",
                getIdCounter(),
                length,
                width,
                topLeft,
                bottomRight,
                getArea(),
                getPerimeter(),
                super.toString());
    }
}
