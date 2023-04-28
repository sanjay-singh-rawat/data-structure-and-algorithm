package com.learndatastructure.dp.knapsack.bounded;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/**
 * Given an integer array arr[], return true if you can partition the array into two subsets such that the sum of the elements
 * in both subsets is equal or false otherwise.
 * arr[] = {1, 5, 11, 5};
 * output = true
 * Can we have two subset whose sum is equal? Yes we can have with given array:
 * {1, 5, 5} {11}
 * <p>
 * We know that we can only divide an Even number into two equal part.
 * An Odd number can not be divided into two equal parts.
 * First check if sum of all elements is even or odd. If odd return false else check for subset sum by reducing the sum by 2.
 *
 * @author Sanjay Singh Rawat
 */
public class EqualSumPartition {

    public static void main(String[] args) {
        int[] input = {1, 5, 11, 5};
        Assertions.assertTrue(equalSumPartition(input));
        Assertions.assertFalse(equalSumPartition(new int[] {1, 5, 10, 5}));
    }

    public static boolean equalSumPartition(int[] arr) {
        if (arr == null) {
            return false;
        }
        int sum = Arrays.stream(arr).sum();
        // sum is odd, we can not partition equally.
        if (sum % 2 != 0) {
            return false;
        }
        // testing all the approaches at once.
        // we can use any one of the below approach to test the result.
        return subsetSumRecursive(arr, sum/2, arr.length)
            && subsetSumMemoization(arr, sum/2, arr.length, new boolean[arr.length + 1][sum/2 + 1])
            && subsetSumTabulation(arr, sum/2);
    }

    private static boolean subsetSumRecursive(int[] arr, int sum, int size) {
        if (sum == 0) {
            return true;
        }
        if (size == 0) {
            return false;
        }

        if (arr[size - 1] <= sum) {
            boolean inclusion = subsetSumRecursive(arr, sum - arr[size - 1], size - 1);
            boolean exclusion = subsetSumRecursive(arr, sum , size - 1);
            return inclusion || exclusion;
        }
        return subsetSumRecursive(arr, sum, size - 1);
    }

    private static boolean subsetSumMemoization(int[] arr, int sum, int size, boolean[][] table) {
        if (sum == 0) {
            return true;
        }
        if (size == 0) {
            return false;
        }
        if (table[size][sum]) {
            return true;
        }

        if (arr[size - 1] <= sum) {
            boolean include = subsetSumMemoization(arr, sum - arr[size - 1], size - 1, table);
            boolean exclude = subsetSumMemoization(arr, sum, size - 1, table);
            table[size][sum] = include || exclude;
            return table[size][sum];
        }
        table[size][sum] = subsetSumMemoization(arr, sum, size - 1, table);
        return table[size][sum];
    }

    private static boolean subsetSumTabulation(int[] arr, int sum) {
        if (arr == null) {
            return false;
        }
        int size = arr.length;
        boolean[][] table = new boolean[size + 1][sum + 1];
        for (int i = 0; i <= size; i++) {
            table[i][0] = true;
        }
        for (int j = 1; j <= sum; j++) {
            table[0][j] = false;
        }

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] <= j) {
                    table[i][j] = table[i - 1][j - arr[i - 1]] || table[i - 1][j];
                } else {
                    table[i][j] = table[i - 1][j];
                }
            }
        }
        return table[size][sum];
    }
}
