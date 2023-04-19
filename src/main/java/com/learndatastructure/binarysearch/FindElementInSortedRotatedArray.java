package com.learndatastructure.binarysearch;

import org.junit.jupiter.api.Assertions;

/**
 * 1. find min element index in the array. see {@link ArrayRotationCount}
 * 2. divide array in two parts, and apply binary search in each part
 *      a. start (0) to minIndex - 1
 *      b. minIndex to end (size - 1)
 * 3. find the element in each part.
 *
 * @author Sanjay Singh Rawat
 */
public class FindElementInSortedRotatedArray {

    public static void main(String[] args) {
        Assertions.assertTrue(findElement(new int[] {11, 12, 15, 18, 2, 5, 6, 8}, 12));
        Assertions.assertFalse(findElement(new int[] {11, 12, 15, 18, 2, 5, 6, 8}, 1));
    }

    public static boolean findElement(int[] arr, int target) {
        int midIndex = findMinElementIndex(arr);
        boolean foundInSecondHalf = search(arr, target, 0, midIndex - 1);
        if (foundInSecondHalf) {
            return true;
        }
        return search(arr, target, midIndex, arr.length - 1);
    }

    private static int findMinElementIndex(int[] arr) {
        int size = arr.length;
        int start = 0;
        int end = size - 1;
        if (arr[start] < arr[end]) {
            return 0;
        }

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int prev = (mid - 1 + size) % size;
            int next = (mid + 1) % size;

            if (arr[mid] <= arr[prev] && arr[mid] <= arr[next]) {
                return mid;
            }
            if (arr[mid] <= arr[end]) {
                end = mid - 1;
            } else if (arr[mid] >= arr[start]) {
                start = mid + 1;
            }
        }

        return -1;
    }

    private static boolean search(int[] arr, int target, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                return true;
            }
            if (arr[mid] < target) {
                start = mid + 1;
            }
            else if (arr[mid] > target) {
                end = mid - 1;
            }
        }
        return false;
    }
}
