package com.learndatastructure.binarysearch;

import org.junit.jupiter.api.Assertions;

/**
 * Given a sorted array, we do not know if it is sorted in ascending or descending order.
 * Find if an element is present or not in it.
 *
 * @author Sanjay Singh Rawat
 */
public class OrderAgnosticSearch {

    public static void main(String[] args) {
        int[] ascendingOrderInput = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Assertions.assertTrue(search(ascendingOrderInput, 4));
        Assertions.assertFalse(search(ascendingOrderInput, 42));

        int[] descendingOrderInput = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        Assertions.assertTrue(search(descendingOrderInput, 8));
        Assertions.assertFalse(search(descendingOrderInput, 21));
    }

    private static boolean search(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        boolean isAsc = arr[start] < arr[end];
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                return true;
            }

            if (isAsc) {
                if (arr[mid] > target) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (arr[mid] > target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return false;
    }
}
