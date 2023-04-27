package com.learndatastructure.dp;

import org.junit.jupiter.api.Assertions;

/**
 * Given an array of non-negative number arr[], and a value sum. Find if there is a subset of given arr[] with sum equals to given sum.
 * arr[] = {2, 3, 7, 8, 10}
 * sum = 11
 * output = true -> {8, 3}
 * <p>
 * When we have an array, and sum needs to be zero, then we can create empty subset.
 * arr[] = {1, 2}
 * sum = 0
 * output = true -> {}
 * <p>
 * When we have an empty array, and sum > 0, then we can not create sum with empty array.
 * arr[] = {}
 * sum = 1
 * output = false
 *
 * @author Sanjay Singh Rawat
 */
public class SubsetSum {

    public static void main(String[] args) {
        int[] input = {2, 3, 7, 8, 10};
        Assertions.assertTrue(subsetSumRecursive(input, 11, input.length));
        Assertions.assertTrue(subsetSumRecursive(input, 10, input.length));
        Assertions.assertFalse(subsetSumRecursive(input, 1, input.length));

        Assertions.assertTrue(subsetSumMemoization(input, 11, input.length, new boolean[input.length + 1][12]));
        Assertions.assertTrue(subsetSumMemoization(input, 10, input.length, new boolean[input.length + 1][11]));
        Assertions.assertFalse(subsetSumMemoization(input, 1, input.length, new boolean[input.length + 1][2]));

        Assertions.assertTrue(subsetSumTabulation(input, 11));
        Assertions.assertTrue(subsetSumTabulation(input, 10));
        Assertions.assertFalse(subsetSumTabulation(input, 1));
    }

    public static boolean subsetSumRecursive(int[] arr, int sum, int size) {
        if (sum == 0) {
            return true;
        }
        if (size == 0) {
            return false;
        }

        if (arr[size - 1] <= sum) {
            boolean isSubsetExistOnElementInclusion = subsetSumRecursive(arr, sum - arr[size - 1], size - 1);
            boolean isSubsetExistOnElementExclusion = subsetSumRecursive(arr, sum, size - 1);
            return isSubsetExistOnElementInclusion || isSubsetExistOnElementExclusion;
        }
        return subsetSumRecursive(arr, sum, size - 1);
    }

    public static boolean subsetSumMemoization(int[] arr, int sum, int size, boolean[][] table) {
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
            boolean isSubsetExistOnElementInclusion = subsetSumMemoization(arr, sum - arr[size - 1], size - 1, table);
            boolean isSubsetExistOnElementExclusion = subsetSumMemoization(arr, sum, size - 1, table);
            table[size][sum] = isSubsetExistOnElementInclusion || isSubsetExistOnElementExclusion;
            return table[size][sum];
        }
        table[size][sum] = subsetSumMemoization(arr, sum, size - 1, table);
        return table[size][sum];
    }

    public static boolean subsetSumTabulation(int[] arr, int sum) {
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
