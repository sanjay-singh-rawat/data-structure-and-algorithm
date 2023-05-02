package com.learndatastructure.dp.lcs;

import org.junit.jupiter.api.Assertions;

/**
 * Given a string str = "agbcba", find the longest subsequence that is palindrome.
 * output = 5 -> "abcba"
 * Here we are given only one string, to perform LCS we need two strings.
 * To generate 2nd string, just reverse the input string.
 * str1 = "agbcba"
 * str2 = "abcbga" -> reverse(str1)
 * Now apply LCS -> "abcba"
 * <p>
 * So, LPS(str1) = LCS(str1, reverse(str1))
 *
 * @author Sanjay Singh Rawat
 */
public class LongestPalindromicSubsequence {

    public static void main(String[] args) {
        Assertions.assertEquals(5, lps("agbcba"));
    }

    public static int lps(String str) {
        return lcs(str, new StringBuilder(str).reverse().toString());
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
