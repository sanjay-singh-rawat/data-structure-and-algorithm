package com.learndatastructure.interviewcake.arrayandstringmanipulation.reversewords;

import java.util.Arrays;

/**
 * @author Sanjay Singh Rawat
 * @since 2020.08.17
 */
public class Solution {

    public static void main(String[] args) {
        oneWordTest();
        twoWordsTest();
        threeWordsTest();
        multipleWordsSameLengthTest();
        multipleWordsDifferentLengthsTest();
        emptyStringTest();
    }

    public static void reverseWords(char[] message) {
        // first we reverse all the characters in the entire message array
        // this gives us the right word order
        // but with each word backward
        reverseCharacters(message, 0, message.length - 1);

        // now we'll make the words forward again
        // by reversing each word's characters

        // we hold the index of the *start* of the current word
        // as we look for the *end* of the current word
        int currentWordStartIndex = 0;
        for (int i = 0; i <= message.length; i++) {
            // found the end of the current word!
            if (i == message.length || message[i] == ' ') {
                // if we haven't exhausted the array, our
                // next word's start is one character ahead
                reverseCharacters(message, currentWordStartIndex, i - 1);
                currentWordStartIndex = i + 1;
            }
        }
    }

    public static void reverseCharacters(char[] message, int leftIndex, int rightIndex) {
        // walk towards the middle, from both sides
        while (leftIndex < rightIndex) {
            // swap the left char and right char
            char temp = message[leftIndex];
            message[leftIndex] = message[rightIndex];
            message[rightIndex] = temp;
            leftIndex++;
            rightIndex--;
        }
    }

    // tests

    public static void oneWordTest() {
        final char[] expected = "vault".toCharArray();
        final char[] actual = "vault".toCharArray();
        reverseWords(actual);
        assert Arrays.equals(expected, actual);
    }

    public static void twoWordsTest() {
        final char[] expected = "cake thief".toCharArray();
        final char[] actual = "thief cake".toCharArray();
        reverseWords(actual);
        assert Arrays.equals(expected, actual);
    }

    public static void threeWordsTest() {
        final char[] expected = "get another one".toCharArray();
        final char[] actual = "one another get".toCharArray();
        reverseWords(actual);
        assert Arrays.equals(expected, actual);
    }

    public static void multipleWordsSameLengthTest() {
        final char[] expected = "the cat ate the rat".toCharArray();
        final char[] actual = "rat the ate cat the".toCharArray();
        reverseWords(actual);
        assert Arrays.equals(expected, actual);
    }

    public static void multipleWordsDifferentLengthsTest() {
        final char[] expected = "chocolate bundt cake is yummy".toCharArray();
        final char[] actual = "yummy is cake bundt chocolate".toCharArray();
        reverseWords(actual);
        assert Arrays.equals(expected, actual);
    }

    public static void emptyStringTest() {
        final char[] expected = "".toCharArray();
        final char[] actual = "".toCharArray();
        reverseWords(actual);
        assert Arrays.equals(expected, actual);
    }
}
