package com.learndatastructure.interviewcake.greedyalgorith.inplaceshuffle;

import java.util.Arrays;
import java.util.Random;

/**
 * Time Complexity - O(n)
 * Space Complexity - O(1)
 *
 * @author Sanjay Singh Rawat
 * @since 2020.08.25
 */
public class Solution {

    private static Random random = new Random();

    private static int getRandom(int floor, int ceiling) {
        return random.ints(floor, ceiling + 1).findFirst().getAsInt();
    }

    public static void main(String[] args) {
        final int[] initial = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        final int[] shuffled = Arrays.copyOf(initial, initial.length);
        shuffle(shuffled);
        System.out.printf("initial array: %s\n", Arrays.toString(initial));
        System.out.printf("shuffled array: %s\n", Arrays.toString(shuffled));
    }

    /**
     * Fisher-Yates shuffle (sometimes called the Knuth shuffle).
     *
     * @param array the shuffled array
     */
    public static void shuffle(int[] array) {

        // if it's 1 or 0 items, just return
        if (array.length <= 1) {
            return;
        }

        // walk through from beginning to end
        for (int indexWeAreChoosingFor = 0; indexWeAreChoosingFor < array.length - 1; indexWeAreChoosingFor++) {

            // choose a random not-yet-placed item to place there
            // (could also be the item currently in that spot)
            // must be an item AFTER the current item, because the stuff
            // before has all already been placed
            int randomChoiceIndex = getRandom(indexWeAreChoosingFor, array.length - 1);

            // place our random choice in the spot by swapping
            if (randomChoiceIndex != indexWeAreChoosingFor) {
                int valueAtIndexWeChoseFor = array[indexWeAreChoosingFor];
                array[indexWeAreChoosingFor] = array[randomChoiceIndex];
                array[randomChoiceIndex] = valueAtIndexWeChoseFor;
            }
        }
    }
}
