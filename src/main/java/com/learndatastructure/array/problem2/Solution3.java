package com.learndatastructure.array.problem2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Time Complexity : O(n log n)
 * Space Complexity : O(n)
 *
 * @author Sanjay Singh Rawat
 * @since 2019.08.26
 */
public class Solution3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] ar = new int[n];
        String[] arItems = scanner.nextLine().split(" ");

        for (int i = 0; i < n; i++) {
            ar[i] = Integer.parseInt(arItems[i]);
        }
        scanner.close();

        int result = sockMerchant(n, ar);
        System.out.println(result);
    }

    private static int sockMerchant(int n, int[] ar) {
        Arrays.sort(ar);
        int pair = 0;
        for (int i = 0; i < n; i++) {
            if (ar[i] == ar[i+1]) {
                pair++;
                i++;
            }
        }
        return pair;
    }
}
