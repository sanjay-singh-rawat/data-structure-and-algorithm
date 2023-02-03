package com.learndatastructure.stack;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Use Stack
 * Traverse from array's last index to 0th index
 *      if stack is not empty
 *          check if stack top element is lesser than current element from array
 *              remove top element till the condition is true
 *          else
 *              add top element in the output array at current index
 *     at each step add current element at the top of stack.
 *
 * @author Sanjay Singh Rawat
 */
public class NearestGreaterToRight {

    /**
     * arr: 1, 3, 2, 4
     * out: 3, 4, 4, -1
     */
    public static void main(String[] args) {
        int[] input = {1, 3, 2, 4};
        int[] output = nextGreater(input);
        Assertions.assertArrayEquals(new int[]{3, 4, 4, -1}, output);
    }

    private static int[] nextGreater(int[] arr) {
        int size = arr.length;
        int[] output = new int[size];
        Arrays.fill(output, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int index = size - 1; index >= 0; index--) {
            while (!stack.isEmpty()) {
                if (stack.peek() <= arr[index]) {
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
