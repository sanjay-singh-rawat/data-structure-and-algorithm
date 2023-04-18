package com.learndatastructure.stack;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 1. Get previous smaller element index at left for each element. left[]
 * 2. Get next smaller element index at right for each element. right[]
 * 3. Calculate the width for each index element. width[i] = right[i] - left[i] - 1
 * 4. Calculate the area for each index element. area[i] = width[i] * arr[i]
 * 5. Find max element in the area array. max <- area[]
 *
 * @author Sanjay Singh Rawat
 */
public class MaximumAreaOfHistogram {

    public static void main(String[] args) {
        int[] histogram = {6, 2, 5, 4, 5, 1, 6};
        int expectedArea = 12;
        int area = maxArea(histogram);
        Assertions.assertEquals(expectedArea, area);
    }

    private static int maxArea(int[] arr) {
        int size = arr.length;
        int[] leftSmaller = previousSmallerIndex(arr);

        int[] rightSmaller = nextSmallerIndex(arr);

        int[] width = new int[size];
        for (int index = 0; index < size; index++) {
            int currentWidth = rightSmaller[index] - leftSmaller[index] - 1;
            width[index] = currentWidth;
        }

        int[] area = new int[size];
        for (int index = 0; index < size; index++) {
            int currentArea = width[index] * arr[index];
            area[index] = currentArea;
        }

        return Arrays.stream(area).max().getAsInt();
    }

    private record Pair(int index, int value) {}

    private static int[] previousSmallerIndex(int[] arr) {
        int size = arr.length;
        int[] output = new int[size];
        Arrays.fill(output, -1);

        Deque<Pair> stack = new ArrayDeque<>();
        for (int index = 0; index < size; index++) {
            while (!stack.isEmpty()) {
                if (stack.peek().value() >= arr[index]) {
                    stack.pop();
                } else {
                    output[index] = stack.peek().index();
                    break;
                }
            }
            stack.push(new Pair(index, arr[index]));
        }
        return output;
    }

    private static int[] nextSmallerIndex(int[] arr) {
        int size = arr.length;
        int[] output = new int[size];
        Arrays.fill(output, size);

        Deque<Pair> stack = new ArrayDeque<>();
        for (int index = size - 1; index >= 0; index--) {
            while (!stack.isEmpty()) {
                if (stack.peek().value() >= arr[index]) {
                    stack.pop();
                } else {
                    output[index] = stack.peek().index();
                    break;
                }
            }
            stack.push(new Pair(index, arr[index]));
        }
        return output;
    }
}
