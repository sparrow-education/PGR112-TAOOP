package com.rinseo.composers;

import com.rinseo.composers.program.Program;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        Program program = new Program();
        program.runProgram();

        //InvokePrivateMethods();
    }

    /**
     * Syntactic sugar for invoking private methods
     * method.invoke(object, args) is a bit verbose
     * First parameter is the object, second parameter takes null if object has 0 args
     * if object has parameters, pass in the object new Object[]{}
     */
    private static void InvokePrivateMethods() {
        // Syntactic sugar for invoking private methods in Java
        try {
            Method method = Program.class.getDeclaredMethod("runProgram");
            method.setAccessible(true);
            method.invoke(new Program(), null);
        } catch (NoSuchMethodException | IllegalAccessException | java.lang.reflect.InvocationTargetException e) {
            System.out.println("Method not found ".concat(e.getMessage()));
        }
    }
}
