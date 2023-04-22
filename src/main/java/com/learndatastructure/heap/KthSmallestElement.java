package com.learndatastructure.heap;

import org.junit.jupiter.api.Assertions;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Given an array arr[] : {7, 10, 4, 3, 20, 15}, and k = 3. Find the kth smallest element in the given array.
 * One way is to sort the array & return element at index k-1. The time complexity for this approach will be O(N logN).
 * Another approach is to use the max heap to reduce the time complexity from O(N logN) to O(N logK)
 *
 * @author Sanjay Singh Rawat
 */
public class KthSmallestElement {

    public static void main(String[] args) {
        int[] input = {7, 10, 4, 3, 20, 15};
        Assertions.assertEquals(7, find(input, 3));
        Assertions.assertEquals(10, find(input, 4));
        Assertions.assertEquals(15, find(input, 5));
        Assertions.assertEquals(-1, find(input, 7));
    }

    public static int find(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            return -1;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());

        for (int element : arr) {
            maxHeap.offer(element);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.peek();
    }
}
