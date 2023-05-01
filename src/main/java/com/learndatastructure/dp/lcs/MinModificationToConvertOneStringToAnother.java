package com.learndatastructure.dp.lcs;

import org.junit.jupiter.api.Assertions;

import java.util.Map;

/**
 * Given two strings str1 and str2, find minimum number of deletion and insertion to convert string str1 to str2.
 * str1 = "heap"
 * str2 = "pea"
 * <p>
 * heap     pea
 *    \    /
 *      ea
 * Delete two character from "heap" 'h' and 'p'
 * Then insert 'p' in front of the "ea" to make it "pea"
 * <p>
 * If you have noticed that the LCS of str1 and str2 i.e., "heap" and "pea" is "ea".
 * So to convert str1 into str2, we need to do following:
 * 1. No. of deletion = str1.length() - LCS(str1, str2)
 * 2. No. of insertion = srt2.length() - LCS(str1, str2)
 *
 * @author Sanjay Singh Rawat
 */
public class MinModificationToConvertOneStringToAnother {

    private static final String DELETION_KEY = "Deletion";
    private static final String INSERTION_KEY = "Insertion";

    public static void main(String[] args) {

        Map<String, Integer> output = noOfModification("heap", "pea");
        Assertions.assertEquals(2, output.get(DELETION_KEY));
        Assertions.assertEquals(1, output.get(INSERTION_KEY));

        output = noOfModification("sanjay", "jay");
        Assertions.assertEquals(3, output.get(DELETION_KEY));
        Assertions.assertEquals(0, output.get(INSERTION_KEY));
    }

    public static Map<String, Integer> noOfModification(String str1, String str2) {
        int lcs = lcs(str1, str2);
        int noOfDeletion = str1.length() - lcs;
        int noOfInsertion = str2.length() - lcs;
        return Map.of(DELETION_KEY, noOfDeletion, INSERTION_KEY, noOfInsertion);
    }

    private static int lcs(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] table = new int[m + 1][n + 1];
        // initialization
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
