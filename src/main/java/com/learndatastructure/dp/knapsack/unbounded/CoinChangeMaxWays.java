package com.learndatastructure.dp.knapsack.unbounded;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/**
 * Given an integer array of coins[] of size N representing different types of currency and an integer sum,
 * The task is to find the number of ways to make sum by using different combinations from coins[].
 * <p>
 * Note: Assume that you have an infinite supply of each type of coin.
 *
 * @author Sanjay Singh Rawat
 */
public class CoinChangeMaxWays {

    public static void main(String[] args) {
        int[] coins = {1, 2, 3};
        int sum = 5;
        Assertions.assertEquals(5, maxWaysRecursive(coins, sum, coins.length));

        int[][] table = new int[coins.length + 1][sum + 1];
        Arrays.stream(table).forEach(row -> Arrays.fill(row, - 1));
        Assertions.assertEquals(5, maxWaysMemoization(coins, sum, coins.length, table));

        Assertions.assertEquals(5, maxWaysTabulation(coins, sum));
    }

    public static int maxWaysRecursive(int[] coins, int sum, int index) {
        if (sum == 0) {
            return 1;
        }
        if (index == 0) {
            return 0;
        }

        if (coins[index - 1] <= sum) {
            int include = maxWaysRecursive(coins, sum - coins[index - 1], index);
            int exclude = maxWaysRecursive(coins, sum, index - 1);
            return include + exclude;
        }
        return maxWaysRecursive(coins, sum, index - 1);
    }

    public static int maxWaysMemoization(int[] coins, int sum, int index, int[][] table) {
        if (sum == 0) {
            return 1;
        }
        if (index == 0) {
            return 0;
        }
        if (table[index][sum] != -1) {
            return table[index][sum];
        }

        if (coins[index - 1] <= sum) {
            int include = maxWaysMemoization(coins, sum - coins[index - 1], index, table);
            int exclude = maxWaysMemoization(coins, sum, index - 1, table);
            table[index][sum] = include + exclude;
            return table[index][sum];
        }
        table[index][sum] = maxWaysMemoization(coins, sum, index - 1, table);
        return table[index][sum];
    }

    public static int maxWaysTabulation(int[] coins, int sum) {
        int size = coins.length;
        int[][] table = new int[size + 1][sum + 1];
        for (int i = 0; i < size + 1; i++) {
            table[i][0] = 1;
        }
        for (int j = 0; j < sum + 1; j++) {
            table[0][j] = 0;
        }

        for (int i = 1; i < size + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                if (coins[i - 1] <= j) {
                    table[i][j] = table[i][j - coins[i - 1]] + table[i - 1][j];
                } else {
                    table[i][j] = table[i - 1][j];
                }
            }
        }
        return table[size][sum];
    }
}
