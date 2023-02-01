package com.rinseo.exceptions;

import java.nio.file.Files;
import java.nio.file.Path;

public class ExceptionTest {
    public static void main(String[] args) {
        try {
            Files.lines(Path.of("sfsdfsdf"));

        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            System.out.println("Finally will run no matter what");
        }
    }
}
