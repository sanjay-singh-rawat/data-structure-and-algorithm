package com.learndatastructure.interviewcake.arrayandstringmanipulation.reverselettersinplace;

/**
 * @author Sanjay Singh Rawat
 * @since 2020.08.16
 */
public class Solutions {

    public static void main(String[] args) {
        emptyStringTest();
        singleCharacterStringTest();
        longerStringTest();
    }

    public static void reverse(char[] arrayOfChars) {
        int leftIndex = 0;
        int rightIndex = arrayOfChars.length - 1;

        while (leftIndex < rightIndex) {
            char temp = arrayOfChars[leftIndex];
            arrayOfChars[leftIndex] = arrayOfChars[rightIndex];
            arrayOfChars[rightIndex] = temp;

            leftIndex++;
            rightIndex--;
        }
    }

    public static void emptyStringTest() {
        final char[] actual = "".toCharArray();
        reverse(actual);
        final char[] expected = "".toCharArray();
        assert expected.equals(actual);
    }

    public static void singleCharacterStringTest() {
        final char[] actual = "A".toCharArray();
        reverse(actual);
        final char[] expected = "A".toCharArray();
        assert expected.equals(actual);
    }

    public static void longerStringTest() {
        final char[] actual = "ABCDE".toCharArray();
        reverse(actual);
        final char[] expected = "EDCBA".toCharArray();
        assert expected.equals(actual);
    }
}
