package com.learndatastructure.binarysearch;

import org.junit.jupiter.api.Assertions;

/**
 * Given an infinite sorted array, search if an element is present or not.
 * Start from first two element, if element is not present in the range move start to end + 1 and move end by 2 * end.
 * Apply binary search within the bound.
 *
 * @author Sanjay Singh Rawat
 */
public class FindInInfiniteSortedArray {

    public static void main(String[] args) {
        int[] input = {3, 5, 7, 9, 10, 90, 100, 130, 140, 160, 170 };
        Assertions.assertTrue(search(input, 140));
    }

    public static boolean search(int[] arr, int target) {
        int start = 0;
        int end = 1;

        while (arr[end] < target) {
            start = end + 1;
            end = 2 * end;
        }

        return search(arr, target, start, end);
    }

    private static boolean search(int[] arr, int target, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                return true;
            }
            if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }

}
