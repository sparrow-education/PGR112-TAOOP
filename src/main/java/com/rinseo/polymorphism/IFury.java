package com.rinseo.polymorphism;

public interface IFury {
    default void rampage() {
        System.out.println("Enrages you and unleashes a series of attacks.");
    }
    default void berserk() {
        System.out.println("Go berserk, increasing all Rage generation by 100%.");
    }
}
