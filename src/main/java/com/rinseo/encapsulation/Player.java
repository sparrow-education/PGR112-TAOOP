package com.rinseo.encapsulation;

public class Player {

    // Fields
    public String name;
    public int health;
    public String weapon;

    // Empty constructor
    public Player() {

    }
    // Constructor with parameters
    public Player(String name, int health, String weapon) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
    }

    // Methods
    public void loseHealth(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            System.out.println("Player knocked out");
        }
    }

    public int healthRemaining() {
        return this.health;
    }
}
