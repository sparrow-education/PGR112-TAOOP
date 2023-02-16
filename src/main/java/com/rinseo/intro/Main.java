package com.rinseo.intro;

import java.util.ArrayList;

/**
 * @author Rinseo
 * @version 1.0
 *
 * Build the project first before from the command line.
 * java -cp out\production\TAOOP com.rinseo.intro.Main
 */
public class Main {
    private ArrayList<String> myList = new ArrayList<>();
    public Main() {
        myList.add("Hello World!");
    }

    public static void main(String[] args) {
        Main main = new Main();

        ArrayList<String> anotherList = new ArrayList<>(main.myList);
        anotherList.add("Hello World Again!");
        System.out.println(anotherList);
        System.out.println(main.myList);
    }
}
