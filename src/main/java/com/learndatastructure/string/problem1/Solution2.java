package com.learndatastructure.string.problem1;

import java.util.Scanner;

/**
 * @author Sanjay Singh Rawat
 * @since 2019.08.28
 */
public class Solution2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String s = scanner.next();
        int result = countingValleys(s);
        scanner.close();
        System.out.println(result);
    }

    private static int countingValleys(String s) {
        int level = 0;
        int valleys = 0;
        for (char c : s.toCharArray()) {
            if (c == 'U') {
                if (level == -1) {
                    ++valleys;
                }
                ++level;
            } else {
                --level;
            }
        }
        return valleys;
    }
}
