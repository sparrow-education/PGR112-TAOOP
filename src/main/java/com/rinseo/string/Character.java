package com.rinseo.string;

import java.util.Arrays;

public class Character {
    public static void main(String[] args) {
        String firstWord = "A";
        String secondWord = "A";
        System.out.println(compareToIgnoreCase(firstWord, secondWord));

        System.out.println(contentEquals(firstWord, secondWord));
    }

    /**
     * @return return 0 if equals, other numbers based on ASCII value difference
     */
    public static int compareToIgnoreCase(String txt1, String txt2) {
        String lowerTxt1 = txt1.toLowerCase();
        String lowerTxt2 = txt2.toLowerCase();
        return lowerTxt1.compareTo(lowerTxt2);
    }

    public static boolean contentEquals(String txt1, String txt2) {
        return txt1.contentEquals(txt2);
    }
}
