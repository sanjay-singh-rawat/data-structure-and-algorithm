package com.learndatastructure.recursion;

/**
 * @author Sanjay Singh Rawat
 */
public class PrintNumbers {

    public static void main(String[] args) {
        System.out.println("Numbers from 1 to N");
        print1ToN(6);
        System.out.println("Numbers from N to 1");
        printNTo1(6);
    }

    public static void print1ToN(int n) {
        if (n == 0) {
            return;
        }
        print1ToN(n - 1);
        System.out.println(n);
    }

    public static void printNTo1(int n) {
        if (n == 0) {
            return;
        }
        System.out.println(n);
        printNTo1(n - 1);
    }
}
