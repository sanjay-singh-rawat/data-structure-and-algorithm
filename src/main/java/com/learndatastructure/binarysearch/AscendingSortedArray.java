package com.learndatastructure.binarysearch;

import org.junit.jupiter.api.Assertions;

/**
 * @author Sanjay Singh Rawat
 */
public class AscendingSortedArray {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Assertions.assertTrue(search(arr, 5));
        Assertions.assertFalse(search(arr, 10));
    }

    private static boolean search(int[] arr, int target) {
        int size = arr.length;
        int start = 0;
        int end = size - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                return true;
            }
            if (arr[mid] > target) {
                end = mid - 1;
            } else if (arr[mid] < target) {
                start = mid + 1;
            }
        }
        return false;
    }
}
