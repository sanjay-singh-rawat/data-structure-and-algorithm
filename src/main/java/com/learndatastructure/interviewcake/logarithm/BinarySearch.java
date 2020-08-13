package com.learndatastructure.interviewcake.logarithm;

/**
 * @author Sanjay Singh Rawat
 * @since 2020.08.13
 */
public class BinarySearch {

    public static void main(String[] args) {

        int nums[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        boolean isTargetFound = binarySearch(3, nums);
        System.out.println("Is Target Found : " + isTargetFound);

    }

    public static boolean binarySearch(int target, int[] nums) {
        // see if target appears in nums

        // we think of floorIndex and ceilingIndex as "walls" around
        // the possible positions of our target so by -1 below we mean
        // to start our wall "to the left" of the 0th index
        // (we *don't* mean "the last index")
        int floorIndex = -1;
        int ceilingIndex = nums.length;

        // if there isn't at least 1 index between floor and ceiling,
        // we've run out of guesses and the number must not be present
        while (floorIndex + 1 < ceilingIndex) {
            // find the index ~halfway between the floor and ceiling
            // we use integer division, so we'll never get a "half index"
            int distance = ceilingIndex - floorIndex;
            int halfDistance = distance / 2;
            int guessIndex = floorIndex + halfDistance;

            int guessValue = nums[guessIndex];

            if (target == guessValue) {
                return true;
            }

            if (guessValue > target) {
                // target is to the left, so move ceiling to the left
                ceilingIndex = guessIndex;
            } else {
                // target is to the right, so move floor to the right
                floorIndex = guessIndex;
            }
        }
        return false;
    }
}
