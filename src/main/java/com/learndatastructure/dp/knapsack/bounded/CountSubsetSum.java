package com.learndatastructure.dp.knapsack.bounded;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/**
 * Given an array of non-negative number arr[], and a value sum. Count subset in given arr[] with sum equals to given sum.
 * <p>
 * To count the subset sum we need to add the result instead of returning true or false.
 *
 * @author Sanjay Singh Rawat
 */
public class CountSubsetSum {

    public static void main(String[] args) {
        int[] input = {2, 3, 5, 6, 8, 10};
        // recursive solution tests
        Assertions.assertEquals(3, countSubsetSumRecursive(input, 10, input.length));
        Assertions.assertEquals(3, countSubsetSumRecursive(input, 11, input.length));
        Assertions.assertEquals(1, countSubsetSumRecursive(input, 12, input.length));

        // memoization solution tests
        int sum = 10;
        int[][] table = new int[input.length + 1][sum + 1];
        Arrays.stream(table).forEach(row -> Arrays.fill(row, -1));
        Assertions.assertEquals(3, countSubsetSumMemoization(input, sum, input.length, table));
        sum = 11;
        table = new int[input.length + 1][sum + 1];
        Arrays.stream(table).forEach(row -> Arrays.fill(row, -1));
        Assertions.assertEquals(3, countSubsetSumMemoization(input, sum, input.length, table));
        sum = 12;
        table = new int[input.length + 1][sum + 1];
        Arrays.stream(table).forEach(row -> Arrays.fill(row, -1));
        Assertions.assertEquals(1, countSubsetSumMemoization(input, sum, input.length, table));

        // tabulation solution tests
        Assertions.assertEquals(3, countSubsetSumTabulation(input, 10));
        Assertions.assertEquals(3, countSubsetSumTabulation(input, 11));
        Assertions.assertEquals(1, countSubsetSumTabulation(input, 12));
    }

    public static int countSubsetSumRecursive(int[] arr, int sum, int size) {
        // if sum is zero we can create an empty subset.
        if (sum == 0) {
            return 1;
        }
        if (size == 0) {
            return 0;
        }

        if (arr[size - 1] <= sum) {
            int include = countSubsetSumRecursive(arr, sum - arr[size - 1], size - 1);
            int exclude = countSubsetSumRecursive(arr, sum, size - 1);
            return include + exclude;
        }
        return countSubsetSumRecursive(arr, sum, size - 1);
    }

    public static int countSubsetSumMemoization(int[] arr, int sum, int size, int[][] table) {
        if (sum == 0) {
            return 1;
        }
        if (size == 0) {
            return 0;
        }
        if (table[size][sum] != -1) {
            return table[size][sum];
        }

        if (arr[size - 1] <= sum) {
            int include = countSubsetSumMemoization(arr, sum - arr[size - 1], size - 1, table);
            int exclude = countSubsetSumMemoization(arr, sum, size - 1, table);
            table[size][sum] = include + exclude;
            return table[size][sum];
        }
        table[size][sum] = countSubsetSumMemoization(arr, sum, size - 1, table);
        return table[size][sum];
    }

    public static int countSubsetSumTabulation(int[] arr, int sum) {
        if (arr == null) {
            return 0;
        }
        int size = arr.length;
        int[][] table = new int[size + 1][sum + 1];
        for (int i = 0; i <= size; i++) {
            table[i][0] = 1;
        }
        for (int j = 1; j <= sum; j++) {
            table[0][j] = 0;
        }

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] <= j) {
                    table[i][j] = table[i - 1][j - arr[i - 1]] + table[i - 1][j];
                } else {
                    table[i][j] = table[i - 1][j];
                }
            }
        }
        return table[size][sum];
    }
}
