package com.learndatastructure.slidingwindow;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/**
 * Given an arr[], find the max in all sub-array for given window size.
 *
 * @author Sanjay Singh Rawat
 */
public class MaxElementInWindow {

    public static void main(String[] args) {
        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] expectedOutput = {3, 3, 5, 5, 6, 7};
        Assertions.assertArrayEquals(expectedOutput, maxInSubArray(arr, 3));
    }

    public static int[] maxInSubArray(int[] arr, int window) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        int start = 0;
        int end = 0;

        while (end < arr.length) {
            maxHeap.offer(arr[end]);
            if (end - start + 1 < window) {
                end++;
            } else if (end - start + 1 == window) {
                result.add(maxHeap.peek());
                if (maxHeap.peek() == arr[start]) {
                    maxHeap.poll();
                }
                start++;
                end++;
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
