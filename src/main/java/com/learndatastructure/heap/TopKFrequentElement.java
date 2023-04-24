package com.learndatastructure.heap;

import org.junit.jupiter.api.Assertions;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given an array arr[], find top K elements with most occurrence. If two numbers have same frequency then number with small value
 * will be appeared first in the output.
 * 1. Create a map that stores number with their frequency.
 * 2. Create a heap that store elements by their frequency in ascending order and then number in reversed order (descending order).
 * 3. Insert number and frequency Pair in the heap while traversing the numberFrequency map, and remove the top element when heap size
 * is greater than the K.
 * 4. Traverse the heap and put the heap element in the output array from index k-1 to 0, so that most frequent element is present at
 * the beginning and also the smallest element when multiple elements have same frequency.
 *
 * @author Sanjay Singh Rawat
 */
public class TopKFrequentElement {

    public static void main(String[] args) {
        int[] input = {1, 1, 1, 3, 3, 3, 3, 2, 2, 2, 2, 4};
        Assertions.assertArrayEquals(new int[]{2, 3, 1}, topKFrequentElement(input, 3));
        Assertions.assertArrayEquals(new int[]{2, 3}, topKFrequentElement(input, 2));
    }

    private static int[] topKFrequentElement(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            return arr;
        }

        Map<Integer, Integer> numberFrequency = new HashMap<>();
        for (int number : arr) {
            numberFrequency.put(number, numberFrequency.getOrDefault(number, 0) + 1);
        }

        Queue<Pair> minFrequencyAndMaxNumberHeap = new PriorityQueue<>(
                Comparator
                        .comparing(Pair::frequency)
                        .thenComparing(Comparator.comparing(Pair::number).reversed())
        );

        numberFrequency.forEach((value, frequency) -> {
            minFrequencyAndMaxNumberHeap.offer(new Pair(value, frequency));
            if (minFrequencyAndMaxNumberHeap.size() > k) {
                minFrequencyAndMaxNumberHeap.poll();
            }
        });

        int[] output = new int[k];
        for (int index = k - 1; index >= 0; index--){
            output[index] = Objects.requireNonNull(minFrequencyAndMaxNumberHeap.poll()).number();
        }

        return output;
    }

    private record Pair(int number, int frequency) {}
}
