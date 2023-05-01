package com.learndatastructure.dp.lcs;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/**
 * @author Sanjay Singh Rawat
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        String x = "abcdgh";
        String y = "abedfhr";

        // verify recursive approach
        Assertions.assertEquals(4, lcsRecursive(x, y, x.length(), y.length()));

        // verify memoization approach
        int[][] table = new int[x.length() + 1][ y.length() + 1];
        Arrays.stream(table).forEach(row -> Arrays.fill(row, - 1));
        Assertions.assertEquals(4, lcsMemoization(x, y, x.length(), y.length(), table));

        // verify tabulation approach
        Assertions.assertEquals(4, lcsTabulation(x, y));
    }

    public static int lcsRecursive(String x, String y, int xIndex, int yIndex) {
        if (xIndex == 0 || yIndex == 0) {
            return 0;
        }

        if (x.charAt(xIndex - 1) == y.charAt(yIndex - 1)) {
            return 1 + lcsRecursive(x, y, xIndex - 1, yIndex - 1);
        }
        return Math.max(lcsRecursive(x, y, xIndex - 1, yIndex), lcsRecursive(x, y, xIndex, yIndex - 1));
    }

    public static int lcsMemoization(String x, String y, int xIndex, int yIndex, int[][] table) {
        if (xIndex == 0 || yIndex == 0) {
            return 0;
        }
        if (table[xIndex][yIndex] != -1) {
            return table[xIndex][yIndex];
        }

        if (x.charAt(xIndex - 1) == y.charAt(yIndex - 1)) {
            table[xIndex][yIndex] = 1 + lcsMemoization(x, y, xIndex - 1, yIndex - 1, table);
            return table[xIndex][yIndex];
        }
        table[xIndex][yIndex] = Math.max(lcsMemoization(x, y, xIndex - 1, yIndex, table), lcsMemoization(x, y, xIndex, yIndex - 1, table));
        return table[xIndex][yIndex];
    }

    public static int lcsTabulation(String x, String y) {
        int xSize = x.length();
        int ySize = y.length();
        int[][] table = new int[xSize + 1][ySize + 1];
        for (int i = 0; i < xSize + 1; i++) {
            table[i][0] = 0;
        }
        for (int j = 1; j < ySize + 1; j++) {
            table[0][j] = 0;
        }

        for (int i = 1; i < xSize + 1; i++) {
            for (int j = 1; j < ySize + 1; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    table[i][j] = 1 + table[i - 1][j - 1];
                } else {
                    table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
                }
            }
        }
        return table[xSize][ySize];
    }
}
