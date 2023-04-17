package com.learndatastructure.stack;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Use Stack
 *   Traverse from array's last index to 0th index
 *      if stack is not empty
 *          if stack's top element is greater than or equal to current element from array
 *              remove top element till the condition is true
 *          else
 *              add top element in the output array at current index
 *      at each step add current element at the top of stack
 *
 * @author Sanjay Singh Rawat
 */
public class NearestSmallerToRight {

    public static void main(String[] args) {
        int[] input = {4, 5, 2, 10, 8};
        int[] output = nextSmaller(input);
        Assertions.assertArrayEquals(new int[] {2, 2, -1, 8, -1}, output);
    }

    private static int[] nextSmaller(int[] arr) {
        int size = arr.length;
        int[] output = new int[size];
        Arrays.fill(output, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int index = size - 1; index >= 0; index--) {
            while (!stack.isEmpty()) {
                if (stack.peek() >= arr[index]) {
                    stack.pop();
                } else {
                    output[index] = stack.peek();
                    break;
                }
            }
            stack.push(arr[index]);
        }
        return output;
    }
}
