package com.learndatastructure.interviewcake.hashingandhashtables.topscores;

import java.util.Arrays;

/**
 * Time Complexity - O(n)
 * Space Complexity - O(n)
 *
 * Wait, aren't we nesting two loops towards the bottom? So shouldn't it be O(n^2) time?
 * Notice what those loops iterate over. The outer loop runs once for each unique number in the array.
 * The inner loop runs once for each time that number occurred.
 *
 * So in essence we're just looping through the n numbers from our input array, except we're splitting it into two steps:
 * (1) each unique number, and (2) each time that number appeared.
 *
 * Here's another way to think about it: in each iteration of our two nested loops, we append one item to sortedScores.
 * How many numbers end up in sortedScores in the end? Exactly how many were in our input array! n.
 *
 * If we didn't treat highestPossibleScore as a constant, we could call it k and say we have O(n+k) time and O(n+k) space.
 *
 * @author Sanjay Singh Rawat
 * @since 2020.08.21
 */
public class Solution {

    public static void main(String[] args) {
        noScoresTest();
        oneScoreTest();
        twoScoresTest();
        manyScoresTest();
        repeatedScoresTest();
    }

    public static int[] sortScores(int[] unorderedScores, int highestPossibleScore) {
        // array of 0s at indices 0..highestPossibleScore
        int[] scoreCounts = new int[highestPossibleScore + 1];

        // populate scoreCounts
        for (int score : unorderedScores) {
            scoreCounts[score]++;
        }

        // populate the final sorted array
        int[] sortedScores = new int[unorderedScores.length];
        int currentSortedIndex = 0;

        // for each item in scoreCounts
        for (int score = highestPossibleScore; score >= 0; score--) {
            int count = scoreCounts[score];
            // for the number of times the item occurs
            for (int occurrence = 0; occurrence < count; occurrence++) {
                // add it to the sorted array
                sortedScores[currentSortedIndex] = score;
                currentSortedIndex++;
            }
        }

        return sortedScores;
    }

    // tests

    public static void noScoresTest() {
        final int[] scores = {};
        final int[] expected = {};
        final int[] actual = sortScores(scores, 100);
        assert Arrays.equals(expected, actual);
    }

    public static void oneScoreTest() {
        final int[] scores = {55};
        final int[] expected = {55};
        final int[] actual = sortScores(scores, 100);
        assert Arrays.equals(expected, actual);
    }

    public static void twoScoresTest() {
        final int[] scores = {30, 60};
        final int[] expected = {60, 30};
        final int[] actual = sortScores(scores, 100);
        assert Arrays.equals(expected, actual);
    }

    public static void manyScoresTest() {
        final int[] scores = {37, 89, 41, 65, 91, 53};
        final int[] expected = {91, 89, 65, 53, 41, 37};
        final int[] actual = sortScores(scores, 100);
        assert Arrays.equals(expected, actual);
    }

    public static void repeatedScoresTest() {
        final int[] scores = {20, 10, 30, 30, 10, 20};
        final int[] expected = {30, 30, 20, 20, 10, 10};
        final int[] actual = sortScores(scores, 100);
        assert Arrays.equals(expected, actual);
    }
}
