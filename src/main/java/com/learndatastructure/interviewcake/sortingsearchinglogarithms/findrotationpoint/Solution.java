package com.learndatastructure.interviewcake.sortingsearchinglogarithms.findrotationpoint;

import com.learndatastructure.utils.JUnitUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Time Complexity - O(n)
 * Space Complexity - O(1)
 *
 * @author Sanjay Singh Rawat
 * @since 2020.08.26
 */
public class Solution {

    public static void main(String[] args) {
        JUnitUtils.launch(Solution.class);
    }

    public static int findRotationPoint(String[] words) {
        final String firstWord = words[0];

        int floorIndex = -1;
        int ceilingIndex = words.length;

        while (floorIndex + 1 < ceilingIndex) {
            // guess a point halfway between floor and ceiling
            int distance = ceilingIndex - floorIndex;
            int halfDistance = distance / 2;
            int guessIndex = floorIndex + halfDistance;

            String guessWord = words[guessIndex];

            // if guess comes after first word or is the first word
            if (guessWord.compareTo(firstWord) >= 0) {
                // go right
                floorIndex = guessIndex;
            } else {
                // go left
                ceilingIndex = guessIndex;
            }

            // if floor and ceiling have converged
            if (floorIndex + 1 == ceilingIndex) {
                // between floor and ceiling is where we flipped to the beginning
                // so ceiling is alphabetically first
                break;
            }
        }

        return ceilingIndex == words.length ? 0 : ceilingIndex;
    }

    // tests

    @Test
    public void smallArrayTest() {
        final int actual = findRotationPoint(new String[] {"cape", "cake"});
        final int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void smallArrayNotRotatedTest() {
        final int actual = findRotationPoint(new String[] {"cake", "cape"});
        final int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void mediumArrayTest() {
        final int actual = findRotationPoint(new String[] {"grape", "orange", "plum",
                "radish", "apple"});
        final int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void largeArrayTest() {
        final int actual = findRotationPoint(
                new String[] {"ptolemaic", "retrograde", "supplant", "undulate", "xenoepist",
                        "asymptote", "babka", "banoffee", "engender", "karpatka", "othellolagkage"});
        final int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    public void largeArrayNotRotatedTest() {
        final int actual = findRotationPoint(
                new String[] {"asymptote", "babka", "banoffee", "engender", "karpatka", "othellolagkage",
                        "ptolemaic", "retrograde", "supplant", "undulate", "xenoepist"});
        final int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void possiblyMissingEdgeCaseTest() {
        // are we missing any edge cases?
    }
}
