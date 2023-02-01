package com.rinseo.encapsulation;

public class Main {
    public static void main(String[] args) {

        // Instantiate a new Player object without constructor
//        Player player = new Player();
//        player.name = "Sparrow";
//        player.health = 10;
//        player.weapon = "Sword";
//
//        int damage = 100;
//        player.loseHealth(damage); // Player knocked out
//
//        // But with public fields we could make mistake by doing this;
//        // This will cause unexpected behavior in our program
//        player.health = 100;
//
//        System.out.println("Remaining health = " + player.healthRemaining());
//
//        // Instantiate a new Player object with constructor
//        Player player2 = new Player("Shelby", 100, "Halberd");


        // Enhanced Player class
        EnhancedPlayer player = new EnhancedPlayer("Sparrow", 101, "Sword");
        System.out.println("Health remaining " + player.getHealth());
    }
}
