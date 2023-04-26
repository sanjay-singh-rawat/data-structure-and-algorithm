package com.learndatastructure.heap;

import org.junit.jupiter.api.Assertions;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given an array arr[], each element in the array represent the rope length, the task is to connect the rope into one rope
 * with minimum cost, such that the cost to connect two ropes is equal to the sum of their lengths.
 *
 * arr[] = {1, 2, 3, 4, 5};
 * We can reduce the cost by adding min elements. Add 2 min element from array and store in a variable, check we can add sum
 * with any other element to get min sum.
 *
 * Add 1, 2 => 3, store 3 in array => {3, 3, 4, 5}
 * Add 3, 3 => 6, store 6 in array => {4, 5, 6}
 * Add 4, 5 => 9, store 9 in array => {6, 9}
 * Add 6, 9 => 15, store 15 in array => {15}
 * Finally add all the sum {3, 6, 9, 16} => 33
 *
 * 1. Create a min heap.
 * 2. Add all elements from array into the min heap.
 * 3. Create a variable cost, and initialize with zero.
 * 4. Iterate the min heap, poll two elements and add them. Store the sum in the min heap and the add sum to the cost variable.
 * 5. return cost.
 *
 * @author Sanjay Singh Rawat
 */
public class ConnectRopeWithMinimumCost {

    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4, 5};
        Assertions.assertEquals(33, minCost(input));
        Assertions.assertEquals(0, minCost(new int[]{1}));
        Assertions.assertEquals(3, minCost(new int[]{1, 2}));
    }

    public static int minCost(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        Queue<Integer> minHeap = new PriorityQueue<>();

        for (int index = 0; index < arr.length; index++) {
            minHeap.offer(arr[index]);
        }

        int cost = 0;
        while (minHeap.size() > 1) {
            int first = minHeap.poll();
            int second = minHeap.poll();
            int sum = first + second;
            minHeap.offer(sum);
            cost += sum;
        }
        return cost;
    }
}
