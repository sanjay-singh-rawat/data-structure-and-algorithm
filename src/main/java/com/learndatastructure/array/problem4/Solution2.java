package com.learndatastructure.array.problem4;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Sanjay Singh Rawat
 * @since 2019.08.29
 */
public class Solution2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[101];
        for (int i = 0; i < n; i++) {
            arr[scanner.nextInt()]++;
        }
        int result = equalizeArray(n, arr);
        scanner.close();
        System.out.println(result);
        System.out.println(minDeletions(new int[]{}));
    }

    private static int equalizeArray(int n, int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        return n - max;
    }

    private static int minDeletions(int[] arr) {
        return arr.length - Arrays.stream(arr).max().orElse(0);
    }
}
