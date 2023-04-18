package com.learndatastructure.stack;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Use Stack
 *  Create a Pair of index and value
 *  Traverse from array's 0th index to last index
 *      while stack is not empty
 *          if stack top element is less than or equal to current element from array
 *              remove top element till the condition is true
 *          else
 *              subtract current index from top element's index and put the result in output array at current index
 *      at each step add current index along with current element at the top of stack as a Pair.
 *
 * @author Sanjay Singh Rawat
 */
public class StockSpanProblem {

    public static void main(String[] args) {
        int[] input = {100, 80, 60, 70, 60, 75, 85};
        int[] expectedOutput = {1, 1, 1, 2, 1, 4, 6};
        int[] actualOutput = stockSpan(input);
        Assertions.assertArrayEquals(expectedOutput, actualOutput);
    }

    private record Pair (int index, int value) {}

    private static int[] stockSpan(int[] arr) {
        int size = arr.length;
        int[] output = new int[size];
        Arrays.fill(output, 1);
        Deque<Pair> stack = new ArrayDeque<>();
        for (int index = 0; index < size; index++) {
            while (!stack.isEmpty()) {
                if (stack.peek().value() <= arr[index]) {
                    stack.pop();
                } else {
                    output[index] = index - stack.peek().index();
                    break;
                }
            }
            stack.push(new Pair(index, arr[index]));
        }
        return output;
    }
}
