package com.rinseo.polymorphism;

public interface IProtection {
    default void shieldWall() {
        System.out.println("Reduced all damage you take by 40% for 8 sec.");
    }
}
