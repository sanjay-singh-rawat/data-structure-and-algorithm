package com.learndatastructure.dp.lcs;

import org.junit.jupiter.api.Assertions;

/**
 * Get the LCS(str, reverse(str)) the subtract from the length of string.
 * min deletion = str.length() - lcs(str, reverse(lcs))
 *
 * @author Sanjay Singh Rawat
 */
public class MinDeletionToMakePalindrome {

    public static void main(String[] args) {
        Assertions.assertEquals(1, minDeletion("agbcba"));
        Assertions.assertEquals(0, minDeletion("nitin"));
    }

    public static int minDeletion(String str) {
        StringBuilder str2 = new StringBuilder(str);
        int lcs = lcs(str, str2.reverse().toString());
        return str.length() - lcs;
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
