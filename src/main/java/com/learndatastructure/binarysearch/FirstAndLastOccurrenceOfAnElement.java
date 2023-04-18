package com.learndatastructure.binarysearch;

import org.junit.jupiter.api.Assertions;

/**
 * @author Sanjay Singh Rawat
 */
public class FirstAndLastOccurrenceOfAnElement {

    public static void main(String[] args) {
        int[] input = {2, 4, 10, 10, 10, 18, 20};
        Assertions.assertArrayEquals(new int[]{2, 4}, searchFirstAndLastOccurrence(input, 10));
        Assertions.assertArrayEquals(new int[]{1, 1}, searchFirstAndLastOccurrence(input, 4));
    }

    private static int[] searchFirstAndLastOccurrence(int[] arr, int target) {
        int firstOccurrence = firstOccurrence(arr, target);
        int lastOccurrence = lastOccurrence(arr, target);
        return new int[] {firstOccurrence, lastOccurrence};
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
