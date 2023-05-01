package com.learndatastructure.dp.lcs;

import org.junit.jupiter.api.Assertions;

/**
 * 1. Create the LCS table using tabulation approach.
 * 2. Once we have the table, using the table we can print the longest common subsequence.
 * 3. while string (str1, str2) is not empty do:
 *      if last character of two strings are equal,
 *          store the character and decrement index of both string.
 *      else
 *          if last char is not equal, use LCS table to check in which direction we need to move to find matching character.
 *              i. if table[i][j - 1] > table[i - 1][j] decrement j
 *              ii. table[i][j - 1] < table[i - 1][j] decrement i
 * <p>
 * LCS table[][]:
 *        a b c d a f
 *      0 1 2 3 4 5 6
 *   0 |0|0|0|0|0|0|0|
 * a 1 |0|1|1|1|1|1|1|
 * c 2 |0|1|1|2|2|2|2|
 * b 3 |0|1|2|2|2|2|2|
 * c 4 |0|1|2|3|3|3|3|
 * f 5 |0|1|2|3|3|3|4|
 * <p>
 * path to find result in LCS table -> (5,6) -> (4,5) -> (4, 4) -> (4, 3) -> (3, 2) -> (2, 1) -> (1, 1) -> (0, 0)
 *
 * @author Sanjay Singh Rawat
 */
public class PrintLongestCommonSubsequence {

    public static void main(String[] args) {
        Assertions.assertEquals("abce", printLcs("abcde", "abfce"));
        Assertions.assertEquals("fishe", printLcs("fishes", "fisherman"));
        Assertions.assertEquals("acevd", printLcs("acevd", "acenbvd"));
        Assertions.assertEquals("abcf", printLcs("acbcf", "abcdaf"));
    }

    public static String printLcs(String str1, String str2) {
        int[][] table = lcsTable(str1, str2);
        int i = str1.length();
        int j = str2.length();

        StringBuilder subsequence = new StringBuilder();
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                subsequence.append(str1.charAt(i - 1));
                i--;
                j--;
            } else {
                if (table[i][j - 1] > table[i - 1][j]) {
                    j--;
                } else {
                    i--;
                }
            }
        }
        subsequence.reverse();
        return subsequence.toString();
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
