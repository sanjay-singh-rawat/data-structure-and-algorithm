package com.learndatastructure.binarysearch;

import org.junit.jupiter.api.Assertions;

/**
 * Given an array : {5, 10, 30, 20, 40},
 * An element which is supposed to be at the ith index, that can be at i-1, i or i+1 index.
 * Increment/decrement start/end index by 2, while doing this make sure to handle ArrayIndexOutOfBounds exception.
 *
 * @author Sanjay Singh Rawat
 */
public class SearchInNearlySortedArray {

    public static void main(String[] args) {
        Assertions.assertTrue(search(new int[]{5, 10, 30, 20, 40}, 5));
        Assertions.assertTrue(search(new int[]{5, 10, 30, 20, 40}, 10));
        Assertions.assertTrue(search(new int[]{5, 10, 30, 20, 40}, 20));
        Assertions.assertTrue(search(new int[]{5, 10, 30, 20, 40}, 40));
        Assertions.assertFalse(search(new int[]{5, 10, 30, 20, 40}, 50));
        Assertions.assertFalse(search(new int[]{5, 10, 30, 20, 40}, 4));
    }

    public static boolean search(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == target) {
                return true;
            }
            if (mid - 1 >= start && arr[mid - 1] == target) {
                return true;
            }
            if (mid + 1 <= end && arr[mid + 1] == target) {
                return true;
            }
            if (arr[mid] >= target) {
                end = mid - 2;
            } else {
                start = mid + 2;
            }
        }
        return false;
    }
}
