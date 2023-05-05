package com.learndatastructure.dp.lcs;

import org.junit.jupiter.api.Assertions;

/**
 * Given two strings, print the shortest commons super sequence.
 * <p>
 * 1. Create LCS table.
 * 2. Print common string one time along with different string.
 *
 * @author Sanjay Singh Rawat
 */
public class PrintShortestCommonSuperSequence {

    public static void main(String[] args) {
        Assertions.assertEquals("acbcdaf", printShortestCommonSuperSequence("acbcf", "abcdaf"));
    }

    public static String printShortestCommonSuperSequence(String str1, String str2) {
        int[][] table = lcsTable(str1, str2);

        int i = str1.length();
        int j = str2.length();

        StringBuilder superString = new StringBuilder();

        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                superString.append(str1.charAt(i - 1));
                i--;
                j--;
            } else {
                if (table[i][j - 1] > table[i - 1][j]) {
                    superString.append(str2.charAt(j - 1));
                    j--;
                } else {
                    superString.append(str1.charAt(i - 1));
                    i--;
                }
            }
        }

        while (i > 0) {
            superString.append(str1.charAt(i - 1));
            i--;
        }

        while (j > 0) {
            superString.append(str2.charAt(j - 1));
            j--;
        }

        return superString.reverse().toString();
    }

    private static int[][] lcsTable(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] table = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            table[i][0] = 0;
        }
        for (int j = 1; j < n + 1; j++) {
            table[0][j] = 0;
        }

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    table[i][j] = 1 + table[i - 1][j - 1];
                } else {
                    table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
                }
            }
        }
        return table;
    }
}
