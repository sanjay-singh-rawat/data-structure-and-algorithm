package com.learndatastructure.binarysearch;

import org.junit.jupiter.api.Assertions;

/**
 * arrBeforeRotation[] = {2, 5, 6, 8, 11, 12, 15, 18}
 * arrAfterRightRotation[] = {11, 12, 15, 18, 2, 5, 6, 8}
 * When array was not rotated the smallest element was at index 0.
 * After array is rotated to the right or clock-wise, the smallest element is present at index 4.
 * That means, number of times an array is rotated is equal to the index of min element.
 * <p>
 * arrBeforeRotation[] = {2, 5, 6, 8, 11, 12, 15, 18}
 * arrAfterLeftRotation[] = {6, 8, 11, 12, 15, 18, 2, 5}
 * After array is rotated to the left or anti-clock wise, the smallest element is present at size - smallest element index.
 * That means, number of times an array is rotated to left is equal to the size minus min element index. 8 - 6 => 2
 *
 * <P>
 * Right rotation or clock-wise rotation.
 *     Find middle element.
 *          if middle element is less than mid + 1 and also less than mid - 1
 *              return mid, smallest element is found.
 *          else
 *              move end to mid - 1 if right array is sorted i.e., end element is greater than middle element
 *          else
 *              move start to mid + 1 if left array is sorted i.e., start element is smaller than middle element.
 * <p>
 * Note: inside the while loop, always check for last element and middle element condition first, because we can be in a
 * situation where array is sorted, in that case we need to move to left side or bring end pointer towards start pointer.
 *
 * @author Sanjay Singh Rawat
 */
public class ArrayRotationCount {

    public static void main(String[] args) {
        Assertions.assertEquals(0, numberOfRotation(new int[]{2, 5, 6, 8, 11, 12, 15, 18}, Direction.RIGHT));
        Assertions.assertEquals(0, numberOfRotation(new int[]{2, 5, 6, 8, 11, 12, 15, 18}, Direction.LEFT));
        Assertions.assertEquals(4, numberOfRotation(new int[]{11, 12, 15, 18, 2, 5, 6, 8}, Direction.RIGHT));
        Assertions.assertEquals(2, numberOfRotation(new int[]{6, 8, 11, 12, 15, 18, 2, 5}, Direction.LEFT));
        Assertions.assertEquals(2, numberOfRotation(new int[]{15, 18, 2, 3, 6, 12}, Direction.RIGHT));
        Assertions.assertEquals(3, numberOfRotation(new int[]{12, 15, 18, 2, 3, 6}, Direction.LEFT));
    }

    private static int numberOfRotation(int[] arr, Direction rotationDirection) {
        int size = arr.length;
        int start = 0;
        int end = size - 1;

        // no rotation
        if (arr[start] < arr[end]) {
            return 0;
        }

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int next = (mid + 1) % size;
            int prev = (mid - 1 + size) % size;

            if (arr[mid] <= arr[prev] && arr[mid] <= arr[next]) {
                if (Direction.RIGHT.equals(rotationDirection)) {
                    return mid;
                }
                return size - mid;
            } else if(arr[mid] <= arr[end]) {
                end = mid - 1;
            } else if (arr[mid] >= arr[start]) {
                start = mid + 1;
            }
        }
        return 0;
    }

    private enum Direction {
        RIGHT,
        LEFT
    }
}
