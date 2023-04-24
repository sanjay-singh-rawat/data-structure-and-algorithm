package com.learndatastructure.heap;

import org.junit.jupiter.api.Assertions;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given an array arr[], sort the array in decreasing order based on frequency of the values.
 * If 2 numbers have the same frequency then print the one which came first.
 *
 * @author Sanjay Singh Rawat
 */
public class FrequencySort {

    public static void main(String[] args) {
        int[] arr = {2, 1, 1, 1, 3, 3, 3, 3, 2, 2, 4};
        sort(arr);
        int[] expected = {3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 4};
        Assertions.assertArrayEquals(expected, arr);
    }

    private record Pair(int number, int frequency, int firstIndex) {}

    private static void sort(int[] arr) {
        if (arr == null) {
            return;
        }

        Map<Integer, Pair> numberFrequencyIndex = new HashMap<>();
        int index = 0;
        for (int number : arr) {
            Pair elementIndexPair = numberFrequencyIndex.get(number);
            if (elementIndexPair == null) {
                numberFrequencyIndex.put(number, new Pair(number, 1, index));
            } else {
                int firstIndex = elementIndexPair.firstIndex;
                if (elementIndexPair.frequency() == 0) {
                    firstIndex = index;
                }
                numberFrequencyIndex.put(number, new Pair(number, elementIndexPair.frequency() + 1, firstIndex));
            }
            index++;
        }

        Queue<Pair> maxHeap = new PriorityQueue<>(Comparator.comparing(Pair::frequency).reversed().thenComparing(Pair::firstIndex));

        numberFrequencyIndex.forEach((number, pair) -> maxHeap.offer(new Pair(number, pair.frequency, pair.firstIndex)));

        index = 0;
        while (maxHeap.size() > 0) {
            Pair pair = maxHeap.poll();
            for (int i = 0; i < pair.frequency(); i++) {
                arr[index++] = pair.number();
            }
        }
    }
}
