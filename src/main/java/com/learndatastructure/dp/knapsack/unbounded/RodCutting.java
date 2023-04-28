package com.learndatastructure.dp.knapsack.unbounded;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/**
 * In unbounded knapsack, if we have decided to use an element then we can again use the same element.
 * But if an element is discarded then we can not use that element.
 * <p>
 * Given a rod of length n inches and an array of prices that includes prices of all pieces of size smaller than n.
 * Determine the maximum value obtainable by cutting up the rod and selling the pieces.
 *
 * @author Sanjay Singh Rawat
 */
public class RodCutting {

    public static void main(String[] args) {
        int[] lengths = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20};

        int rodLength = 8;
        int index = lengths.length;
        Assertions.assertEquals(22, maxPriceRecursive(lengths, prices, rodLength, index));

        int[][] table = new int[index + 1][rodLength + 1];
        Arrays.stream(table).forEach(row -> Arrays.fill(row, -1));
        Assertions.assertEquals(22, maxPriceMemoization(lengths, prices, rodLength, index, table));

        Assertions.assertEquals(22, maxPriceTabulation(lengths, prices, rodLength));
    }

    public static int maxPriceRecursive(int[] lengths, int[] prices, int rodLength, int index) {
        if (rodLength == 0) {
            return 0;
        }
        if (index == 0) {
            return 0;
        }

        if (lengths[index - 1] <= rodLength) {
            int include = prices[index - 1] + maxPriceRecursive(lengths, prices, rodLength - lengths[index - 1], index);
            int exclude = maxPriceRecursive(lengths, prices, rodLength, index - 1);
            return Math.max(include, exclude);
        }
        return maxPriceRecursive(lengths, prices, rodLength, index - 1);
    }

    public static int maxPriceMemoization(int[] lengths, int[] prices, int rodLength, int index, int[][] table) {
        if (rodLength == 0) {
            return 0;
        }
        if (index == 0) {
            return 0;
        }
        if (table[index][rodLength] != -1) {
            return table[index][rodLength];
        }

        if (lengths[index - 1] <= rodLength) {
            int include = prices[index - 1] + maxPriceRecursive(lengths, prices, rodLength - lengths[index - 1], index);
            int exclude = maxPriceRecursive(lengths, prices, rodLength, index - 1);
            table[index][rodLength] = Math.max(include, exclude);
            return table[index][rodLength];
        }
        table[index][rodLength] = maxPriceRecursive(lengths, prices, rodLength, index - 1);
        return table[index][rodLength];
    }

    public static int maxPriceTabulation(int[] lengths, int[] prices, int rodLength) {
        int size = lengths.length;
        int[][] table = new int[size + 1][rodLength + 1];
        for (int i = 0; i < size + 1; i++) {
            table[i][0] = 0;
        }
        for (int j = 0; j < rodLength + 1; j++) {
            table[0][j] = 0;
        }

        for (int i = 1; i < size + 1; i++) {
            for (int j = 1; j < rodLength + 1; j++) {
                if (lengths[i - 1] <= j) {
                    table[i][j] = Math.max(prices[i - 1] + table[i][j - lengths[i - 1]], table[i - 1][j]);
                } else {
                    table[i][j] = table[i - 1][j];
                }
            }
        }
        return table[size][rodLength];
    }
}
