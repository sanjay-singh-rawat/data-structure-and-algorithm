package com.learndatastructure.interviewcake.arrayandstringmanipulation.mergesortedarray;

import java.util.Arrays;

/**
 * @author Sanjay Singh Rawat
 * @since 2020.08.17
 */
public class Solution {

    public static void main(String[] args) {
        bothArraysAreEmptyTest();
        firstArrayIsEmptyTest();
        secondArrayIsEmptyTest();
        bothArraysHaveSomeNumbersTest();
        arraysAreDifferentLengthsTest();
    }

    public static int[] mergeArraysWithoutDRY(int[] myArray, int[] aliceArray) {

        int[] mergedArray = new int[myArray.length + aliceArray.length];

        int currentIndexMine = 0;
        int currentIndexAlice = 0;
        int currentIndexMerged = 0;

        while (currentIndexMerged < mergedArray.length) {
            // case: my array is exhausted
            if (currentIndexMine >= myArray.length) {
                mergedArray[currentIndexMerged] = aliceArray[currentIndexAlice];
                currentIndexAlice++;
            }
            // case: Alice's array is exhausted
            else if (currentIndexAlice >= aliceArray.length) {
                mergedArray[currentIndexMerged] = myArray[currentIndexMine];
                currentIndexMine++;
            }
            // case: my item is next
            else if (myArray[currentIndexMine] < aliceArray[currentIndexAlice]) {
                mergedArray[currentIndexMerged] = myArray[currentIndexMine];
                currentIndexMine++;
            }
            // case: Alice's item is next
            else {
                mergedArray[currentIndexMerged] = aliceArray[currentIndexAlice];
                currentIndexAlice++;
            }
            currentIndexMerged++;
        }

        return mergedArray;
    }

    public static int[] mergeArrayWithDRY(int[] myArray, int[] aliceArray) {

        // set up our mergedArray
        int[] mergedArray = new int[myArray.length + aliceArray.length];

        int currentIndexAlices = 0;
        int currentIndexMine   = 0;
        int currentIndexMerged = 0;

        while (currentIndexMerged < mergedArray.length) {

            boolean isMyArrayExhausted = currentIndexMine >= myArray.length;
            boolean isAlicesArrayExhausted = currentIndexAlices >= aliceArray.length;

            // case: next comes from my array
            // my array must not be exhausted, and EITHER:
            // 1) Alice's array IS exhausted, or
            // 2) the current element in my array is less than the current element in Alice's array
            if (!isMyArrayExhausted && (isAlicesArrayExhausted || (myArray[currentIndexMine] < aliceArray[currentIndexAlices]))) {
                mergedArray[currentIndexMerged] = myArray[currentIndexMine];
                currentIndexMine++;
            }
            // case: next comes from Alice's array
            else {
                mergedArray[currentIndexMerged] = aliceArray[currentIndexAlices];
                currentIndexAlices++;
            }

            currentIndexMerged++;
        }

        return mergedArray;
    }

    // tests

    public static void bothArraysAreEmptyTest() {
        final int[] myArray = {};
        final int[] aliceArray = {};
        final int[] expected = {};
        final int[] actual = mergeArraysWithoutDRY(myArray, aliceArray);
        assert Arrays.equals(expected, actual);
    }

    public static void firstArrayIsEmptyTest() {
        final int[] myArray = {};
        final int[] aliceArray = {1, 2, 3};
        final int[] expected = {1, 2, 3};
        final int[] actual = mergeArraysWithoutDRY(myArray, aliceArray);
        assert Arrays.equals(expected, actual);
    }

    public static void secondArrayIsEmptyTest() {
        final int[] myArray = {5, 6, 7};
        final int[] aliceArray = {};
        final int[] expected = {5, 6, 7};
        final int[] actual = mergeArraysWithoutDRY(myArray, aliceArray);
        assert Arrays.equals(expected, actual);
    }

    public static void bothArraysHaveSomeNumbersTest() {
        final int[] myArray = {2, 4, 6};
        final int[] aliceArray = {1, 3, 7};
        final int[] expected = {1, 2, 3, 4, 6, 7};
        final int[] actual = mergeArraysWithoutDRY(myArray, aliceArray);
        assert Arrays.equals(expected, actual);
    }

    public static void arraysAreDifferentLengthsTest() {
        final int[] myArray = {2, 4, 6, 8};
        final int[] aliceArray = {1, 7};
        final int[] expected = {1, 2, 4, 6, 7, 8};
        final int[] actual = mergeArraysWithoutDRY(myArray, aliceArray);
        assert Arrays.equals(expected, actual);
    }
}
