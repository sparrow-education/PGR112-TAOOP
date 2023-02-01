package com.rinseo.composers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComposerTest {

    private Composer composer;
    private int age;

    @BeforeEach
    void setUp() {
        composer = new Composer("David", 1999, 2000);
        age = composer.getAge();
    }

    @Test
    public void ageIsNotNegative() {
        Composer composer2 = new Composer("Test", 2001, 2000);
        int age = composer2.getAge();
        assertTrue(!(age < 0));
    }



}
