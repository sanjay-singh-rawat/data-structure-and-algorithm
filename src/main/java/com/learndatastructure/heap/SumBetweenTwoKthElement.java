package com.learndatastructure.heap;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given an array arr[] = {1, 3, 12, 5, 15, 11}, find sum between k1 smallest and k2 smallest element in the array.
 * <p>
 * Using sort -> {1, 3, 5, 11, 12, 15}, we can add the element from k1 index to k2-1 index.
 * <p>
 * Using Max Heap:
 * 1. Using max heap find k1Smallest and k2Smallest element
 * 2. Iterate the input array and sum elements that are greater than k2Smallest and less than k2Smallest.
 *
 * @author Sanjay Singh Rawat
 */
public class SumBetweenTwoKthElement {

    public static void main(String[] args) {
        int[] input = {1, 3, 12, 5, 15, 11};
        Assertions.assertEquals(23, sumBetweenKthElementUsingSort(input, 3, 6));
        Assertions.assertEquals(23, sumBetweenKthElementUsingHeap(input, 3, 6));
    }

    public static int sumBetweenKthElementUsingSort(int[] arr, int k1, int k2) {
        Arrays.sort(arr);
        int sum = 0;
        for (int index = k1; index < k2 - 1; index++) {
            sum += arr[index];
        }
        return sum;
    }

    public static int sumBetweenKthElementUsingHeap(int[] arr, int k1, int k2) {
        int k1Smallest = kthSmallest(arr, k1);
        int k2Smallest = kthSmallest(arr, k2);

        int sum = 0;
        for (int index = 0; index < arr.length; index++) {
            if (arr[index] > k1Smallest && arr[index] < k2Smallest) {
                sum += arr[index];
            }
        }
        return sum;
    }

    private static int kthSmallest(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        for (int index = 0; index < arr.length; index++) {
            maxHeap.offer(arr[index]);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        return maxHeap.peek();
    }
}
