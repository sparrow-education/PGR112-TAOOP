package com.rinseo.polymorphism;

public interface IArms {
    default void mortalStrike() {
        System.out.println("A vicious strike that deals 200% of attack power");
    }
}
