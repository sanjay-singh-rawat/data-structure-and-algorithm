package com.learndatastructure.heap;

import org.junit.jupiter.api.Assertions;

import java.util.PriorityQueue;

/**
 * Given an array arr[] = {7, 10, 4, 3, 20, 15}, find kth largest element.
 *
 * @author Sanjay Singh Rawat
 */
public class KthLargestElement {

    public static void main(String[] args) {
        int[] input = {7, 10, 4, 3, 20, 15};
        Assertions.assertEquals(15, find(input, 2));
        Assertions.assertEquals(20, find(input, 1));
        Assertions.assertEquals(10, find(input, 3));
        Assertions.assertEquals(-1, find(input, 7));
    }

    public static int find(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            return -1;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int element : arr) {
            minHeap.offer(element);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        return minHeap.peek();
    }
}
