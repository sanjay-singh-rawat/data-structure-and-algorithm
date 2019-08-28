package com.learndatastructure.string.problem1;

import java.util.Scanner;

/**
 * Figure out the number of times you came back up to see level.
 *
 * @author Sanjay Singh Rawat
 * @since 2019.08.28
 */
public class Solution1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String s = scanner.nextLine();
        int result = countingValleys(n, s);
        scanner.close();
        System.out.println(result);
    }

    private static int countingValleys(int n, String s) {
        int level = 0;
        int valleys = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'U')
                ++level;
            if (c == 'D')
                --level;

            // if we just came UP to sea level
            if (level == 0 && c == 'U')
                ++valleys;
        }
        return valleys;
    }
}
