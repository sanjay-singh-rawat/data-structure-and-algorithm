package com.learndatastructure.dp.knapsack.bounded;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/**
 * Recursion + Memoization
 * In the memoization approach we will be using a 2-D array to store the computed results.
 * <p>
 * What will be the dimension of the matrix?
 * It will depend on the recursive function's input. The input that changes at each invocation will be used as the matrix dimension.
 * <p>
 * In knapsack function, weight of bag & size of an array is changing, so we will use them as matrix dimension.
 * <p>
 * knapsack(weight[], value[], W, n) => here at each invocation W and n changes
 * because we need to store till W & n, so we will create a matrix of size n + 1 & W + 1.
 * int[][] matrix = new int[n + 1][W + 1]
 * And initialize the matrix with -1.
 * Now we will store the result in the matrix, if value is -1 replace value else return the value.
 *
 * @author Sanjay Singh Rawat
 */
public class Memoization01Knapsack {

    private static final int capacity = 7;
    private static final int size = 4;
    private static final int[][] matrix = new int[size + 1][capacity + 1];

    public static void main(String[] args) {
        int[] weight = {1, 2, 4, 5};
        int[] value = {1, 4, 5, 7};
        Arrays.stream(matrix).forEach(arr -> Arrays.fill(arr, -1));
        Assertions.assertEquals(11, knapsack(weight, value, capacity, weight.length));

        Arrays.stream(matrix).forEach(arr -> Arrays.fill(arr, -1));
        weight = new int[] {1, 3, 4, 5};
        Assertions.assertEquals(9, knapsack(weight, value, capacity, weight.length));
    }

    public static int knapsack(int[] weight, int[] value, int capacity, int size) {
        if (capacity == 0 || size == 0) {
            return 0;
        }
        if (matrix[size][capacity] != -1) {
            return matrix[size][capacity];
        }

        if (weight[size - 1] <= capacity) {
            int profitOnWeightInclusion = value[size - 1] + knapsack(weight, value, capacity - weight[size - 1], size - 1);
            int profitOnWeightExclusion = knapsack(weight, value, capacity, size - 1);
            int maxProfit = Math.max(profitOnWeightInclusion, profitOnWeightExclusion);
            matrix[size][capacity] = maxProfit;
            return maxProfit;
        } else {
            int profitOnWeightExclusion = knapsack(weight, value, capacity, size - 1);
            matrix[size][capacity] = profitOnWeightExclusion;
            return profitOnWeightExclusion;
        }
    }
}
