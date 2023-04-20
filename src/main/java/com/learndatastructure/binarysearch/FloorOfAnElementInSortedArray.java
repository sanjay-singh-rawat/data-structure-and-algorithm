package com.learndatastructure.binarysearch;

import org.junit.jupiter.api.Assertions;

/**
 * Given an array : {1, 2, 3, 4, 8, 10, 10, 12, 19}
 * Find floor of an element, let's say floor of 5.
 * Possible candidate for floor of 5 can be 1, 2, 3, 4.
 * Floor of an element would be the greatest element smaller than that element.
 * <p>
 * While moving towards the right side, store the possible candidate element.
 *
 * @author Sanjay Singh Rawat
 */
public class FloorOfAnElementInSortedArray {

    public static void main(String[] args) {
        Assertions.assertEquals(4, findFloor(new int[] {1, 2, 3, 4, 8, 10, 10, 19}, 5));
        Assertions.assertEquals(10, findFloor(new int[] {1, 2, 3, 4, 8, 10, 10, 19}, 11));
        Assertions.assertEquals(8, findFloor(new int[] {1, 2, 3, 4, 8, 10, 10, 19}, 9));
    }

    public static int findFloor(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int floor = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                return arr[mid];
            }
            if (arr[mid] > target) {
                end = mid - 1;
            } else {
                floor = arr[mid];
                start = start + 1;
            }
        }

        return floor;
    }
}
