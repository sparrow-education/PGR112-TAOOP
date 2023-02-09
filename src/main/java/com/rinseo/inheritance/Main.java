package com.rinseo.inheritance;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //https://www.geogebra.org/geometry
        ArrayList<Shape> shapeList = new ArrayList<>();

        Circle circle = new Circle("Red", false, 1.0, new MovablePoint(5.0, 5.0));

        Rectangle rectangle = new Rectangle("Green", true, 5.0, 10.0,  new MovablePoint(9.0, 10.0), new MovablePoint(10.0, 0.0));
        rectangle.setTopLeft(new MovablePoint(5.0, 4.0));
        Square square = new Square("Blue", true, 5.0, new MovablePoint(0.0, 2.0), new MovablePoint(2.0, 0.0));

        shapeList.add(circle);
        shapeList.add(rectangle);
        shapeList.add(square);

        for(Shape i : shapeList) {
            System.out.println(i);
        }

    }
}
