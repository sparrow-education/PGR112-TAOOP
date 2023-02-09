package com.rinseo.inheritance;

public class Circle extends Shape {
    private double radius;
    private MovablePoint center;

    public Circle(String color, boolean isFilled, double radius, MovablePoint center) {
        super(color, isFilled);
        this.radius = radius;
        if (center.getY() == center.getX()) {
            this.center = center;
        } else {
            System.out.printf("This ID `%s` needs to be on x=y line\n", this.getId());
            this.center = new MovablePoint(-1.0, -1.0);
        }
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getCenter() {
        return String.valueOf(this.center.getY());
    }

    public void setCenter(MovablePoint center) {
        this.center = center;
    }

    @Override
    public double getArea() {
        // PI * r ^ 2
        return Math.PI * Math.pow(this.radius, 2);
    }

    @Override
    public double getCircumference() {
        return Math.PI * this.radius * 2;
    }

    @Override
    public double getPerimeter() {
        System.out.println("Circle has no perimeter");
        return -1;
    }

    @Override
    public void moveUp(double distance) {
        center.moveUp(distance);
    }

    @Override
    public void moveDown(double distance) {
        center.moveDown(distance);
    }

    @Override
    public void moveLeft(double distance) {
        center.moveLeft(distance);
    }

    @Override
    public void moveRight(double distance) {
        center.moveRight(distance);
    }

    @Override
    public String toString() {
        return String.format("A Circle with id: %s, radius: %s, center: %s, which is %s",
                getIdCounter(),
                radius,
                getCenter(),
                super.toString());
    }
}
