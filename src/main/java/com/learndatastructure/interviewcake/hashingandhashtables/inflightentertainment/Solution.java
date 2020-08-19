package com.learndatastructure.interviewcake.hashingandhashtables.inflightentertainment;

import java.util.HashSet;
import java.util.Set;

/**
 * Time Complexity - O(n)
 * Space Complexity - O(n)
 *
 * @author Sanjay Singh Rawat
 * @since 2020.08.18
 */
public class Solution {

    public static void main(String[] args) {
        shortFlightTest();
        longFlightTest();
        onlyOneMovieHalfFlightLenghtTest();
        twoMoviesHalfFlightLengthTest();
        lotsOfPossiblePairsTest();
        notUsingFirstMovieTest();
        oneMovieTest();
        noMoviesTest();
    }

    public static boolean canTwoMoviesFillFlight(int[] movieLengths, int flightLength) {

        // movie lengths we've seen so far
        Set<Integer> movieLengthsSeen = new HashSet<>();

        for (int firstMovieLength : movieLengths) {
            int matchingSecondMovieLength = flightLength - firstMovieLength;
            if (movieLengthsSeen.contains(matchingSecondMovieLength)) {
                return true;
            }
            movieLengthsSeen.add(firstMovieLength);
        }

        // we never found a match, so return false
        return false;
    }

    // tests

    public static void shortFlightTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {2, 4}, 1);
        assert !result;
    }

    public static void longFlightTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {2, 4}, 6);
        assert result;
    }

    public static void onlyOneMovieHalfFlightLenghtTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {3, 8}, 6);
        assert !result;
    }

    public static void twoMoviesHalfFlightLengthTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {3, 8, 3}, 6);
        assert result;
    }

    public static void lotsOfPossiblePairsTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {1, 2, 3, 4, 5, 6}, 7);
        assert result;
    }

    public static void notUsingFirstMovieTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {4, 3, 2}, 5);
        assert result;
    }

    public static void oneMovieTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {6}, 6);
        assert !result;
    }

    public static void noMoviesTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {}, 6);
        assert !result;
    }
}
