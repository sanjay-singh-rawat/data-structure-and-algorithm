package com.learndatastructure.dp.lcs;

import org.junit.jupiter.api.Assertions;

/**
 * Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.
 * If there are multiple valid strings, return any of them.
 * str1 = "AGGTAB"
 * str2 = "GXTXAYB"
 * <p>
 * Some of the common super sequence of str1, and str2 are:
 * AGGTABGXTXAYB
 * AGGXTXAYB
 * <p>
 * Longest common subsequence of the str1, str2 is:
 * GTAB
 * <p>
 * The shortest common super sequence of two string is:
 * str1.length() + str2.length() - LCS(str1, str2)
 *
 * @author Sanjay Singh Rawat
 */
public class ShortestCommonSuperSequence {

    public static void main(String[] args) {
        Assertions.assertEquals(5, superSequence("geek", "eke"));
        Assertions.assertEquals(9, superSequence("AGGTAB", "GXTXAYB"));
    }

    public static int superSequence(String str1, String str2) {
        return str1.length() + str2.length() - lcs(str1, str2);
    }

    private static int lcs(String str1, String str2) {
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
        return table[m][n];
    }
}
