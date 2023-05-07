package com.learndatastructure.recursion;

import org.junit.jupiter.api.Assertions;

/**
 * @author Sanjay Singh Rawat
 */
public class Factorial {

    public static void main(String[] args) {
        Assertions.assertEquals(1, factorial(0));
        Assertions.assertEquals(1, factorial(1));
        Assertions.assertEquals(2, factorial(2));
        Assertions.assertEquals(6, factorial(3));
        Assertions.assertEquals(24, factorial(4));
    }

    public static int factorial(int number) {
        if (number == 0 || number == 1) {
            return 1;
        }
        if (number < 0) {
            return -1;
        }

        int factorial = factorial(number - 1);
        factorial = number * factorial;
        return factorial;
    }
}
