package com.learndatastructure.heap;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an array arr[], and a value x, find the k closest elements to x.
 *
 * @author Sanjay Singh Rawat
 */
public class KClosestNumberToX {

    public static void main(String[] args) {
        var input = new int[]{-10, -50, 20, 17, 80};
        Assertions.assertArrayEquals(new int[]{17, 20}, closestNumber(input, 2, 20));
        Assertions.assertArrayEquals(new int[]{-10, 17, 20}, closestNumber(input, 3, 20));
        Assertions.assertArrayEquals(new int[]{17, 20, 80}, closestNumber(input, 3, 50));

        var sortedInput = new int[]{-50, -10, 17, 20, 80};
        Assertions.assertArrayEquals(new int[]{17, 20}, closestNumberInSortedArray(sortedInput, 2, 20));
        Assertions.assertArrayEquals(new int[]{-10, 17, 20}, closestNumberInSortedArray(sortedInput, 3, 20));
        Assertions.assertArrayEquals(new int[]{17, 20, 80}, closestNumberInSortedArray(sortedInput, 3, 50));
    }

    private static int[] closestNumber(int[] arr, int k, int x) {
        if (arr == null || arr.length < k) {
            return arr;
        }

        var maxHeap = new PriorityQueue<>(Collections.reverseOrder(Comparator.comparingInt(Pair::difference)));

        for (Integer value : arr) {
            var difference = Math.abs(value - x);
            maxHeap.offer(new Pair(difference, value));
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.stream().mapToInt(Pair::value).sorted().toArray();
    }

    private record Pair(int difference, int value) {}

    public static int[] closestNumberInSortedArray(int[] arr, int k, int x) {
        int start = 0;
        int end = arr.length - 1;

        while (end - start >= k) {
            if (x - arr[start] > arr[end] - x) {
                start++;
            } else {
                end--;
            }
        }

        return Arrays.stream(arr, start, end + 1).toArray();
    }
}
