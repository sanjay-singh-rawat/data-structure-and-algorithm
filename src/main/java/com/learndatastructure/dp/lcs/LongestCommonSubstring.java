package com.learndatastructure.dp.lcs;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/**
 * @author Sanjay Singh Rawat
 */
public class LongestCommonSubstring {

    public static void main(String[] args) {
        String str1 = "abcde";
        String str2 = "abfce";

        Assertions.assertEquals(2, lcsRecursive(str1, str2, str1.length(), str2.length(), 0));

        int[][] table = new int[str1.length() + 1][str2.length() + 1];
        Arrays.stream(table).forEach(row -> Arrays.fill(row, - 1));
        Assertions.assertEquals(2, lcsMemoization(str1, str2, str1.length(), str2.length(), 0, table));

        Assertions.assertEquals(2, lcsTabulation(str1, str2));

        str1 = "fishes";
        str2 = "fisherman";

        Assertions.assertEquals(5, lcsRecursive(str1, str2, str1.length(), str2.length(), 0));

        table = new int[str1.length() + 1][str2.length() + 1];
        Arrays.stream(table).forEach(row -> Arrays.fill(row, - 1));
        Assertions.assertEquals(5, lcsMemoization(str1, str2, str1.length(), str2.length(), 0, table));

        Assertions.assertEquals(5, lcsTabulation(str1, str2));

        str1 = "acevd";
        str2 = "acenbvd";

        Assertions.assertEquals(3, lcsRecursive(str1, str2, str1.length(), str2.length(), 0));

        table = new int[str1.length() + 1][str2.length() + 1];
        Arrays.stream(table).forEach(row -> Arrays.fill(row, - 1));
        Assertions.assertEquals(3, lcsMemoization(str1, str2, str1.length(), str2.length(), 0, table));

        Assertions.assertEquals(3, lcsTabulation(str1, str2));
    }

    public static int lcsRecursive(String str1, String str2, int index1, int index2, int count) {
        if (index1 == 0 || index2 == 0) {
            return count;
        }

        if (str1.charAt(index1 - 1) == str2.charAt(index2 - 1)) {
            count = lcsRecursive(str1, str2, index1 - 1, index2 - 1, count + 1);
        }
        int countOnExcludingStr1Character = lcsRecursive(str1, str2, index1 - 1, index2, 0);
        int countOnExcludingStr2Character = lcsRecursive(str1, str2, index1, index2 - 1, 0);
        return Math.max(count, Math.max(countOnExcludingStr1Character, countOnExcludingStr2Character));
    }

    public static int lcsMemoization(String str1, String str2, int index1, int index2, int count, int[][] table) {
        if (index1 == 0 || index2 == 0) {
            return count;
        }
        if (table[index1][index2] != -1) {
            return table[index1][index2];
        }

        if (str1.charAt(index1 - 1) == str2.charAt(index2 - 1)) {
            count = lcsMemoization(str1, str2, index1 - 1, index2 - 1, count + 1, table);
        }
        int countOnExcludingStr1Character = lcsRecursive(str1, str2, index1 - 1, index2, 0);
        int countOnExcludingStr2Character = lcsRecursive(str1, str2, index1, index2 - 1, 0);
        table[index1][index2] = Math.max(count, Math.max(countOnExcludingStr1Character, countOnExcludingStr2Character));
        return table[index1][index2];
    }

    public static int lcsTabulation(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] table = new int[m + 1][n + 1];

        for (int i = 0; i < m + 1; i++) {
            table[i][0] = 0;
        }
        for (int j = 0; j < n + 1; j++) {
            table[0][j] = 0;
        }

        int longestSubstringLength = 0;
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    table[i][j] = 1 + table[i - 1][j - 1];
                    longestSubstringLength = Math.max(longestSubstringLength, table[i][j]);
                } else {
                    table[i][j] = 0;
                }
            }
        }
        return longestSubstringLength;
    }
}
