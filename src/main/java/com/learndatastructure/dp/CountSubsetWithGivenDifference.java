package com.learndatastructure.dp;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/**
 * Given an array arr[] of size N and a given difference diff, the task is to count the number of partitions that we can perform
 * such that the difference between the sum of the two subsets is equal to the given difference.
 * <p>
 * Suppose the array is partitioned in two subsets with sum S1 and S2, so we know that,
 * S1 + S2  is the total sum of array
 * S1 – S2 is the given diff
 * <p>
 * Adding the two equation, we would get:
 * sumToFind = (array sum + difference)/2
 *
 * @author Sanjay Singh Rawat
 */
public class CountSubsetWithGivenDifference {

    public static void main(String[] args) {
        Assertions.assertEquals(3, countSubsetWithDifference(new int[]{1, 1, 2, 3}, 1));
        Assertions.assertEquals(1, countSubsetWithDifference(new int[]{5, 2, 6, 4}, 3));
        Assertions.assertEquals(5, countSubsetWithDifference(new int[]{1, 2, 3, 1, 2}, 1));
    }

    public static int countSubsetWithDifference(int[] arr, int diff) {
        int arraySum = Arrays.stream(arr).sum();
        int sumToFind = (arraySum + diff) / 2;
        return countSubsetSumTabulation(arr, sumToFind);
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
