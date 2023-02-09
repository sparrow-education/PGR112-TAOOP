package com.rinseo.inheritance;

/**
 * Square is a subclass of Rectangle
 * All four sides are equal in length
 * */
public class Square extends Rectangle {
    private MovablePoint topLeft;
    private MovablePoint bottomRight;
    public Square(String color, boolean isFilled, double side, MovablePoint topLeft, MovablePoint bottomRight) {
        super(color, isFilled, side, side, topLeft, bottomRight);
    }

    public double getSide() {
        return super.getLength();
    }

    public void setSide(double side) {
        super.setWidth(side);
        super.setLength(side);
    }

    @Override
    public double getArea() {
        // A = side * side
        return super.getArea();
    }

    @Override
    public double getPerimeter() {
        // perimeter = side * side ^ 2
        return super.getPerimeter();
    }

    @Override
    public String toString() {
        return String.format("A Square with id: %s, sides: %s, which is a Shape with color %s and %s",
                getIdCounter(),
                getSide(),
                getColor(),
                isFilled() ? "filled." : "not filled.");
    }
}
