package com.learndatastructure.heap;

import org.junit.jupiter.api.Assertions;

import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Given an array arr[] = {6, 5, 3, 2, 8, 10, 9}, which is nearly sorted or where each element is at most K places away from
 * its target position. Sort the array in O(N logK) time.
 * The element at index i, can be at index i + k or i - k side in a sorted array.
 * So, the element must be in the range of [i - k, i + k].
 *
 * @author Sanjay Singh Rawat
 */
public class SortKSortedArray {

    public static void main(String[] args) {
        int[] input = {6, 5, 3, 2, 8, 10, 9};
        sort(input, 3);
        int[] sortedInput = {2, 3, 5, 6, 8, 9, 10};
        Assertions.assertArrayEquals(sortedInput, input);
    }

    public static void sort(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            return;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int index = 0;
        for (; index < arr.length; index++) {
            minHeap.offer(arr[index]);
            if (minHeap.size() > k) {
                arr[index - k] = minHeap.poll();
            }
        }

        Iterator<Integer> iterator = minHeap.iterator();
        while (iterator.hasNext()) {
            arr[index - k] = minHeap.poll();
            index++;
        }
    }
}
