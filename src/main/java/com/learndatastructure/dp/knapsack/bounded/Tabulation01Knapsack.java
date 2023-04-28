package com.learndatastructure.dp.knapsack.bounded;

import org.junit.jupiter.api.Assertions;

/**
 * In tabulation approach we do not use recursion, we only use the matrix.
 * 1. Base condition is changed to initialization of matrix.
 * 2. Change choice diagram code to iterative.
 * 3. Solve the current block of matrix by already solved block.
 *
 * @author Sanjay Singh Rawat
 */
public class Tabulation01Knapsack {

    public static void main(String[] args) {
        int[] weight = {1, 2, 4, 5};
        int[] value = {1, 4, 5, 7};
        int capacity = 7;
        Assertions.assertEquals(11, knapsack(weight, value, capacity));
        weight = new int[] {1, 3, 4, 5};
        Assertions.assertEquals(9, knapsack(weight, value, capacity));
    }

    public static int knapsack(int[] weight, int[] value, int capacity) {
        int size = weight.length;
        // create a matrix of dimension size + 1 and capacity + 1, because the first row and column will contain the initial value.
        int[][] table = new int[size + 1][capacity + 1];
        // 1. Base condition of recursion changes to initialization of matrix.
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (i == 0 || j == 0) {
                    table[i][j] = 0;
                }
            }
        }

        // 2. Change choice diagram to iterative
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= capacity; j++) {
                // 3. solve current block by already solved block.
                if (weight[i - 1] <= j) {
                    int profitOnWeightInclusion = value[i - 1] + table[i - 1][j - weight[i - 1]];
                    int profitOnWeightExclusion = table[i - 1][j];
                    table[i][j] = Math.max(profitOnWeightInclusion, profitOnWeightExclusion);
                } else {
                    table[i][j] = table[i - 1][j];
                }
            }
        }
        return table[size][capacity];
    }
}
