package com.learndatastructure.stack;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Use Stack
 *   Traverse from array's 0th index to last index
 *      if stack is not empty
 *          if stack's top element is greater than or equal to current element from array
 *              remove top element till the condition is true
 *          else
 *              add top element in the output array at current index
 *      at each step add current element at the top of stack
 *
 * @author Sanjay Singh Rawat
 */
public class NearestSmallerToLeft {

    public static void main(String[] args) {
        int[] input = {4, 5, 2, 10, 8};
        int[] output = previousSmaller(input);
        Assertions.assertArrayEquals(new int[]{-1, 4, -1, 2, 2}, output);
    }

    private static int[] previousSmaller(int[] arr) {
        int size = arr.length;
        int[] output = new int[size];
        Arrays.fill(output, -1);

        Deque<Integer> stack = new ArrayDeque<>();
        for (int index = 0; index < size; index++) {
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
