package com.learndatastructure.array.problem2;

import java.util.Scanner;

/**
 * Time Complexity : O(n)
 * Space Complexity : O(n)
 *
 * @author Sanjay Singh Rawat
 * @since 2019.08.26
 */
public class Solution2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] ar = new int[n];
        String[] arItems = scanner.nextLine().split(" ");

        int[] frequency = new int[101];

        for (int i = 0; i < n; i++) {
            ar[i] = Integer.parseInt(arItems[i]);
            frequency[ar[i]]++;
        }
        scanner.close();

        int result = sockMerchant(n, ar, frequency);
        System.out.println(result);
    }

    private static int sockMerchant(int n, int[] ar, int[] frequency) {
        int pair = 0;
        for (int i = 0; i < n; i++) {
            pair += frequency[ar[i]]/2;
            frequency[ar[i]] = 0;
        }
        return pair;
    }
}
