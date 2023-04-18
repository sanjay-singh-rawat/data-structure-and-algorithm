package com.learndatastructure.binarysearch;

import org.junit.jupiter.api.Assertions;

/**
 * @author Sanjay Singh Rawat
 */
public class CountElementOccurrence {

    public static void main(String[] args) {
        int[] input = {2, 4, 10, 10, 10, 18, 20};
        Assertions.assertEquals(3, countOccurrence(input, 10));
        Assertions.assertEquals(1, countOccurrence(input, 4));
    }

    private static int countOccurrence(int[] arr, int target) {
        int firstOccurrence = firstOccurrence(arr, target);
        int lastOccurrence = lastOccurrence(arr, target);
        return lastOccurrence - firstOccurrence + 1;
    }

    private static int firstOccurrence(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int firstOccurrence = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                firstOccurrence = mid;
                end = mid - 1;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return firstOccurrence;
    }

    private static int lastOccurrence(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int lastOccurrence = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                lastOccurrence = mid;
                start = mid + 1;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return lastOccurrence;
    }
}
