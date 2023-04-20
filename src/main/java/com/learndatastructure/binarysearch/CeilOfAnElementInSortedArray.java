package com.learndatastructure.binarysearch;

import org.junit.jupiter.api.Assertions;

/**
 * Given an array : {1, 2, 3, 4, 8, 10, 10, 12, 19}
 * Find ceil of an element, let's say floor of 5.
 * Possible candidate for floor of 5 can be 6, 7, 8.
 * Ceil of an element would be the smallest element greater than that element.
 * <p>
 * While moving towards the left side, store the possible candidate element.
 *
 * @author Sanjay Singh Rawat
 */
public class CeilOfAnElementInSortedArray {

    public static void main(String[] args) {
        Assertions.assertEquals(8, findCeil(new int[]{1, 2, 3, 4, 8, 10, 10, 12, 19}, 5));
        Assertions.assertEquals(10, findCeil(new int[]{1, 2, 3, 4, 8, 10, 10, 12, 19}, 10));
        Assertions.assertEquals(19, findCeil(new int[]{1, 2, 3, 4, 8, 10, 10, 12, 19}, 19));
        Assertions.assertEquals(-1, findCeil(new int[]{1, 2, 3, 4, 8, 10, 10, 12, 19}, 20));
    }

    public static int findCeil(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int ceil = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                return arr[mid];
            }
            if (arr[mid] > target) {
                end = mid - 1;
                ceil = arr[mid];
            } else {
                start = mid + 1;
            }
        }

        return ceil;
    }
}
