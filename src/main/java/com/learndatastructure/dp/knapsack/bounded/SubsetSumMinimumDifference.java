package com.learndatastructure.dp.knapsack.bounded;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/**
 * Given an array arr[] of non-negative integer, divide it into two sets S1 and S2 such that the absolute difference between
 * their sums is minimum.
 *
 * @author Sanjay Singh Rawat
 */
public class SubsetSumMinimumDifference {

    public static void main(String[] args) {
        int[] input = {1, 2, 7};
        Assertions.assertEquals(4, subsetSumMinDifference(input));

        input = new int[]{1, 6, 11, 5};
        Assertions.assertEquals(1, subsetSumMinDifference(input));
    }

    public static int subsetSumMinDifference(int[] arr) {
        int sum = Arrays.stream(arr).sum();
        boolean[][] table = subsetSum(arr);
        int min = Integer.MAX_VALUE;
        for (int j = sum/2; j >= 0; j--) {
            if (table[arr.length][j]) {
                min = Math.min(min, sum - 2 * j);
            }
        }
        return min;
    }

    private static boolean[][] subsetSum(int[] arr) {
        int size = arr.length;
        int sum = Arrays.stream(arr).sum();
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
        return table;
    }
}
