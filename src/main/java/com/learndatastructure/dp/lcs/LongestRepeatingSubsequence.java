package com.learndatastructure.dp.lcs;

import org.junit.jupiter.api.Assertions;

/**
 * Given a string, find the length of the longest repeating subsequence, such that the two subsequences don't have same string character
 * at the same position, i.e. any ith character in the two subsequences shouldn't have the same index in the original string.
 * <p>
 * str = AABEBCDD
 * Get a subsequence that is repeating, we can not use same letter.
 * if i == j, then do not include
 * Now, when writing LCS code, we just need to add one check that i != j
 *
 * @author Sanjay Singh Rawat
 */
public class LongestRepeatingSubsequence {

    public static void main(String[] args) {
        Assertions.assertEquals(3, longestRepeatingSubsequence("AABEBCDD"));
        Assertions.assertEquals(4, longestRepeatingSubsequence("ABABEBCDD"));
        Assertions.assertEquals(0, longestRepeatingSubsequence("abc"));
        Assertions.assertEquals(1, longestRepeatingSubsequence("aab"));
        Assertions.assertEquals(2, longestRepeatingSubsequence("aabb"));
        Assertions.assertEquals(2, longestRepeatingSubsequence("axxxy"));
    }

    public static int longestRepeatingSubsequence(String str1) {
        String str2 = new String(str1);
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
                if (str1.charAt(i - 1) == str2.charAt(j - 1) && i != j) {
                    table[i][j] = 1 + table[i - 1][j - 1];
                } else {
                    table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
                }
            }
        }
        return table[m][n];
    }
}
