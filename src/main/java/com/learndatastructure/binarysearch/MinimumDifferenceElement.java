package com.learndatastructure.binarysearch;

import org.junit.jupiter.api.Assertions;

/**
 * Given an arr[] : {1, 3, 8, 10, 15}, and a key : 12.
 * Find the element in the array whose difference is minimum with the key.
 * <p>
 * If key (12) is present in the array then minimum difference is zero, so the result is key (12). Why? because 12 - 12 = 0.
 * If key is not present then, element with minimum difference will be the neighbour of the element.
 * <p>
 * 1. If key found in the array, return the key because it has the minimum difference.
 * 2. Iterate till the loop ends. Now start and end pointer will be at neighbouring position i.e., 10, 15.
 * 3. Now subtract key from element at start index and end index, and compare the absolute value, whichever is less that is the result.
 * i.e., abs(key - arr[start]) < abs(key - arr[end]) ? arr[start] : arr[end]
 *
 * @author Sanjay Singh Rawat
 */
public class MinimumDifferenceElement {

    public static void main(String[] args) {
        int[] input = {1, 3, 8, 10, 15};
        Assertions.assertEquals(10, findMinimumDifferenceElement(input, 12));
        Assertions.assertEquals(15, findMinimumDifferenceElement(input, 13));
        Assertions.assertEquals(3, findMinimumDifferenceElement(input, 3));
        Assertions.assertEquals(1, findMinimumDifferenceElement(input, 2));
    }

    public static int findMinimumDifferenceElement(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                return arr[mid];
            }
            if (arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        int differenceWithStartIndexElement = Math.abs(target - arr[start]);
        int differenceWithEndIndexElement = Math.abs(target - arr[end]);
        return differenceWithStartIndexElement < differenceWithEndIndexElement ? arr[start] : arr[end];
    }
}
