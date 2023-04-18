package com.learndatastructure.stack;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author Sanjay Singh Rawat
 */
public class MaximumAreaOfRectangleInBinaryMatrix {

    private record Pair(int index, int value) {}

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 1, 0},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 0}
        };

        int expectedArea = 8;
        int actualArea = maximumArea(matrix);
        Assertions.assertEquals(expectedArea, actualArea);
    }

    public static int maximumArea(int[][] matrix) {
        int size = matrix.length;
        int[] arr = new int[size];

        for (int column = 0; column < size; column++) {
            arr[column] = matrix[0][column];
        }

        int maxArea = maximumArea(arr);

        for (int row = 1; row < size; row++) {
            for (int column = 0; column < size; column++) {
                if (matrix[row][column] == 0) {
                    arr[column] = 0;
                } else {
                    arr[column] = arr[column] + matrix[row][column];
                }
            }
            maxArea = Math.max(maxArea, maximumArea(arr));
        }
        return maxArea;
    }

    private static int maximumArea(int[] arr) {
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
