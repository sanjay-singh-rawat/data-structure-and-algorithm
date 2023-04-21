package com.learndatastructure.binarysearch;

import org.junit.jupiter.api.Assertions;

/**
 * Given an array arr[] : {5, 10, 20, 15}, Find peak element.
 * A peak element in an array  is the element that is greater than its left and right element.
 * In above array 20 is the peak element because 5 < 20 > 15.
 *
 * <p></p>
 * Element at 0th and n-1th index can be a peak element if the element at 1st index is less than 0th index element and
 * element at n-1th index is greater than n-2nd index element.
 * {1, 2, 3, 4, 5} -> 5 is peak element because 5 is the last element and is greater than its left element.
 * {15, 14, 13, 12, 11} -> 15 is is peak element because it is on the 0th index and is greater than its right element.
 *
 * <p></p>
 * If peak element is somewhere in the middle then, in which direction we will move to find the element?
 * We will move towards the greater element because if we have an element which is greater than its left element then the left
 * element can not be peak because it's less than its right element, and for a peak element it must be greater than its left
 * and right element.
 *
 * @author Sanjay Singh Rawat
 */
public class PeakElement {

    public static void main(String[] args) {
        Assertions.assertEquals(20, findPeakElement(new int[]{5, 10, 20, 15}));
        // either 20 or 90, both are peak element
        Assertions.assertEquals(20, findPeakElement(new int[]{10, 20, 15, 2, 23, 90, 67}));
        Assertions.assertEquals(90, findPeakElement(new int[]{10, 20, 15, 21, 23, 90, 67}));
    }

    public static int findPeakElement(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (mid > 0 && mid < arr.length - 1) {
                if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                    return arr[mid];
                }
                if (arr[mid] > arr[mid - 1]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else if (mid == 0) {
                return Math.max(arr[mid], arr[mid + 1]);
            } else if (mid == arr.length - 1) {
                return Math.max(arr[mid], arr[mid - 1]);
            }
        }
        return -1;
    }
}
