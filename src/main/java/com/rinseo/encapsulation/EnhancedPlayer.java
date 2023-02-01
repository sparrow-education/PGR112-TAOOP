package com.rinseo.encapsulation;

public class EnhancedPlayer {
    private final String name;
    private int health = 100;
    private final String weapon;

    public EnhancedPlayer(String name, int health, String weapon) {
        this.name = name;
        // This restricts the player health to be in range of 0 - 100 respectively when instantiated
        if (health > 0 && health <= 100) {
            this.health = health;
        }
        this.weapon = weapon;
    }

    public void loseHealth(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            System.out.println("Player knocked out");
        }
    }

    public int getHealth() {
        return health;
    }
}
