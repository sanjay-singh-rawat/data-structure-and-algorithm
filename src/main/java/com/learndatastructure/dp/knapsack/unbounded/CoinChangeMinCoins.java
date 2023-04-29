package com.learndatastructure.dp.knapsack.unbounded;

import org.junit.jupiter.api.Assertions;

/**
 * Given an integer array of coins[] of size N representing different types of currency and an integer sum,
 * The task is to find the minimum number of coins to make sum by using different combinations from coins[].
 * <p>
 * In this problem we have to initialize the 2nd row as well in tabulation approach.
 * In the second row we will put a value that represents the infinity, lets say max integer - 1, when it's not possible to
 * create the sum by first coins in the array.
 * Here j represents the sum.
 * if j % coins[0] == 0 then
 *      table[1][j] = j / coins[0];
 * else
 *      table[i][j] = Integer.MAX_VALUE - 1;
 * <p>
 * Why Integer.MAX_VALUE - 1?
 * To prevent the integer overflow.
 *
 * @author Sanjay Singh Rawat
 */
public class CoinChangeMinCoins {

    public static void main(String[] args) {
        Assertions.assertEquals(2, minCoinsRecursive(new int[] {1, 2, 3}, 5));
        Assertions.assertEquals(3, minCoinsRecursive(new int[] {9, 6, 5, 1}, 13));
        Assertions.assertEquals(4, minCoinsRecursive(new int[] {1, 3, 5, 7}, 18));
        Assertions.assertEquals(2, minCoinsRecursive(new int[] {25, 10, 5}, 30));

        Assertions.assertEquals(2, minCoinsTabulation(new int[] {1, 2, 3}, 5));
        Assertions.assertEquals(3, minCoinsTabulation(new int[] {9, 6, 5, 1}, 13));
        Assertions.assertEquals(4, minCoinsTabulation(new int[] {1, 3, 5, 7}, 18));
        Assertions.assertEquals(2, minCoinsTabulation(new int[] {25, 10, 5}, 30));
    }

    public static int minCoinsRecursive(int[] coins, int sum) {
        if (sum == 0) {
            return 0;
        }
        if (sum < 0) {
            return Integer.MAX_VALUE - 1;
        }
        int min = Integer.MAX_VALUE - 1;
        for (int coin : coins) {
            if (coin <= sum) {
                int subAns = minCoinsRecursive(coins, sum - coin);
                if (subAns != Integer.MAX_VALUE - 1) {
                    min = Math.min(min, 1 + subAns);
                }
            }
        }
        return min;
    }

    public static int minCoinsTabulation(int[] coins, int sum) {
        int size = coins.length;
        int[][] table = new int[size + 1][sum + 1];
        for (int i = 0; i < size + 1; i++) {
            table[i][0] = 0;
        }
        for (int j = 0; j < sum + 1; j++) {
            table[0][j] = Integer.MAX_VALUE - 1;
        }

        for (int j = 1; j < sum + 1; j++) {
            if (j % coins[0] == 0) {
                table[1][j] = j/coins[0];
            } else {
                table[1][j] = Integer.MAX_VALUE - 1;
            }
        }

        for (int i = 2; i < size + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                if (coins[i - 1] <= j) {
                    table[i][j] = Math.min(1 + table[i][j - coins[i - 1]], table[i - 1][j]);
                } else {
                    table[i][j] = table[i - 1][j];
                }
            }
        }

        if (table[size][sum] == Integer.MAX_VALUE - 1) {
            return -1;
        }
        return table[size][sum];
    }
}
