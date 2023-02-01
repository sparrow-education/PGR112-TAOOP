package com.rinseo.polymorphism;

public class Tank extends Warrior implements IProtection, IFury, IArms{

    @Override
    public void taunt(){
        System.out.println("Taunts");
    }
}
