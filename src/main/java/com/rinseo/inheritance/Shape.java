package com.rinseo.inheritance;

/**
 *  All geometric figures are shapes in one form or another...
 *  And shapes can be drawn on a canvas...
 */
public abstract class Shape implements Movable{
    // For Database auto-increment of ID
    private int id;
    private String color;
    private boolean isFilled;

    // Global counter as ID for all implementations of Shape
    private static long idCounter = 0;

    // Counter will increment for each new instance of Shape
    public Shape(String color, boolean isFilled) {
        idCounter += 1;
        this.color = color;
        this.isFilled = isFilled;
    }

    public abstract double getArea();
    public abstract double getCircumference();
    public abstract double getPerimeter();



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static synchronized String createId() {
        return String.valueOf(idCounter++);
    }
    public static long getIdCounter() {
        return idCounter;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }


    @Override
    public void moveUp(double distance) {

    }

    @Override
    public void moveDown(double distance) {

    }

    @Override
    public void moveLeft(double distance) {

    }

    @Override
    public void moveRight(double distance) {

    }
    @Override
    public String toString() {
        return String.format("a shape with color of %s and %s",
                color,
                isFilled ? "filled." : "not filled.");
    }
}
