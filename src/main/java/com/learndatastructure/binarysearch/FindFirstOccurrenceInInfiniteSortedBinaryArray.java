package com.learndatastructure.binarysearch;

import org.junit.jupiter.api.Assertions;

/**
 * @author Sanjay Singh Rawat
 */
public class FindFirstOccurrenceInInfiniteSortedBinaryArray {

    public static void main(String[] args) {
        int[] input = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        Assertions.assertEquals(7, findFirstOccurrence(input, 1));
    }

    public static int findFirstOccurrence(int[] arr, int target) {
        int start = 0;
        int end = 1;

        while (arr[end] < target) {
            start = end + 1;
            end = 2 * end;
        }
        return findFirstOccurrence(arr, target, start, end);
    }

    private static int findFirstOccurrence(int[] arr, int target, int start, int end) {
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
}
