package com.learndatastructure.slidingwindow;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sanjay Singh Rawat
 */
public class FirstNegativeNumberInWindow {

    public static void main(String[] args) {
        int[] arr = {12, -1, -7, 8, -15, 30, 16, 28};
        int[] expectedOutput = {-1, -1, -7, -15, -15, 0};
        Assertions.assertArrayEquals(expectedOutput, firstNegative(arr, 3).stream().mapToInt(Integer::intValue).toArray());
    }

    public static List<Integer> firstNegative(int[] arr, int window) {
        List<Integer> result = new ArrayList<>();
        Deque<Integer> queue = new LinkedList<>();
        int start = 0;
        int end = 0;

        while (end < arr.length) {
            if (arr[end] < 0) {
                queue.offer(arr[end]);
            }

            if (end - start + 1 < window) {
                end++;
            } else if (end - start + 1 == window) {
                if (queue.isEmpty()) {
                    result.add(0);
                } else {
                    result.add(queue.peek());
                    if (arr[start] == queue.peek()) {
                        queue.poll();
                    }
                }
                start++;
                end++;
            }
        }
        return result;
    }
}
