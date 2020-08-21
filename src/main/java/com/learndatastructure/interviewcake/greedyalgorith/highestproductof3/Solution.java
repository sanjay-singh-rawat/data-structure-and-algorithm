package com.learndatastructure.interviewcake.greedyalgorith.highestproductof3;

import com.learndatastructure.utils.JUnitUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Sanjay Singh Rawat
 * @since 2020.08.21
 */
public class Solution {

    public static void main(String[] args) {
        JUnitUtils.launch(Solution.class);
    }

    public static int highestProductOf3InNCube(int[] arrayOfInts) {
        if (arrayOfInts.length < 3) {
            throw new IllegalArgumentException("Less than 3 items!");
        }
        int highestProductOf3 = Integer.MIN_VALUE;

        int arrayLength = arrayOfInts.length;

        for (int i = 0; i < arrayLength; i++) {
            for (int j = i + 1; j < arrayLength; j++) {
                for (int k = j + 1; k < arrayLength; k++) {
                    int product = arrayOfInts[i] * arrayOfInts[j] * arrayOfInts[k];
                    if (product > highestProductOf3) {
                        highestProductOf3 = product;
                    }
                }
            }
        }


        return highestProductOf3;
    }

    public static int highestProductOf3InNLogN(int[] arrayOfInts) {
        if (arrayOfInts.length < 3) {
            throw new IllegalArgumentException("Less than 3 items!");
        }

        Arrays.sort(arrayOfInts);

        return Math.max(arrayOfInts[0] * arrayOfInts[1] * arrayOfInts[arrayOfInts.length - 1],
                arrayOfInts[arrayOfInts.length - 1] * arrayOfInts[arrayOfInts.length - 2] * arrayOfInts[arrayOfInts.length - 3]);

    }

    public static int highestProductOf3InN(int[] arrayOfInts) {

        if (arrayOfInts.length < 3) {
            throw new IllegalArgumentException("Less than 3 items!");
        }

        // we're going to start at the 3rd item (at index 2)
        // so pre-populate highests and lowests based on the first 2 items.
        // we could also start these as null and check below if they're set
        // but this is arguably cleaner
        int highest = Math.max(arrayOfInts[0], arrayOfInts[1]);
        int lowest  = Math.min(arrayOfInts[0], arrayOfInts[1]);

        int highestProductOf2 = arrayOfInts[0] * arrayOfInts[1];
        int lowestProductOf2  = arrayOfInts[0] * arrayOfInts[1];

        // except this one--we pre-populate it for the first *3* items.
        // this means in our first pass it'll check against itself, which is fine.
        int highestProductOf3 = arrayOfInts[0] * arrayOfInts[1] * arrayOfInts[2];

        // walk through items, starting at index 2
        for (int i = 2; i < arrayOfInts.length; i++) {
            int current = arrayOfInts[i];

            // do we have a new highest product of 3?
            // it's either the current highest,
            // or the current times the highest product of two
            // or the current times the lowest product of two
            highestProductOf3 = Math.max(Math.max(highestProductOf3, current * highestProductOf2), current * lowestProductOf2);

            // do we have a new highest product of two?
            highestProductOf2 = Math.max(Math.max(highestProductOf2, current * highest), current * lowest);

            // do we have a new lowest product of two?
            lowestProductOf2 = Math.min(Math.min(lowestProductOf2, current * highest), current * lowest);

            // do we have a new highest?
            highest = Math.max(highest, current);

            // do we have a new lowest?
            lowest = Math.min(lowest, current);
        }

        return highestProductOf3;
    }

    public static int highestProductOf3InNUsing3Max2Min(int[] arrayOfInts) {

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int n : arrayOfInts) {
            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2) {
                max3 = max2;
                max2 = n;
            } else if (n > max3) {
                max3 = n;
            }

            if (n < min1) {
                min2 = min1;
                min1 = n;
            } else if (n < min2) {
                min2 = n;
            }
        }

        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }

    // tests

    @Test
    public void shortArrayTest() {
        assertHighestProductOf3(new int[] {1, 2, 3, 4}, 24);
    }

    @Test
    public void longerArrayTest() {
        assertHighestProductOf3(new int[] {6, 1, 3, 5, 7, 8, 2}, 336);
    }

    @Test
    public void arrayHasOneNegativeTest() {
        assertHighestProductOf3(new int[] {-5, 4, 8, 2, 3}, 96);
    }

    @Test
    public void arrayHasTwoNegativesTest() {
        assertHighestProductOf3(new int[] {-10, 1, 3, 2, -10}, 300);
    }

    @Test
    public void arrayIsAllNegativesTest() {
        assertHighestProductOf3(new int[] {-5, -1, -3, -2}, -6);
    }

    @Test
    public void exceptionWithEmptyArrayTest() {
        assertThrows(Exception.class, () -> highestProductOf3InN(new int[] {}));
    }

    @Test
    public void exceptionWithOneNumberTest() {
        assertThrows(Exception.class, () -> highestProductOf3InN(new int[] {1}));
    }

    @Test
    public void exceptionWithTwoNumbersTest() {
        assertThrows(Exception.class, () -> highestProductOf3InN(new int[] {1, 1}));
    }

    private void assertHighestProductOf3(int[] arrayOfInts, int expected) {
        final int actualHighestProductOf3InNCube = highestProductOf3InNCube(arrayOfInts);
        final int actualHighestProductOf3InNLogN = highestProductOf3InNLogN(arrayOfInts);
        final int actualHighestProductOf3InN = highestProductOf3InN(arrayOfInts);
        final int actualHighestProductOf3InNUsing3Max2Min = highestProductOf3InNUsing3Max2Min(arrayOfInts);
        assertEquals(expected, actualHighestProductOf3InNCube);
        assertEquals(expected, actualHighestProductOf3InNLogN);
        assertEquals(expected, actualHighestProductOf3InN);
        assertEquals(expected, actualHighestProductOf3InNUsing3Max2Min);
    }
}
