package com.learndatastructure.slidingwindow;

import org.junit.jupiter.api.Assertions;

/**
 * Given an array arr[] = {2, 5, 3, 9, 7, 1, 4, 6} and window size is 3.
 * Find max sum in the window size.
 * <p>
 * when window size hits we calculate the max sum and slides the window.
 * 1. calculate current sum
 * 2. if window size not hit
 *      increment end index
 *    else
 *      calculate maxSum in current window
 *      subtract window start index element from currentSum
 *      increment start and end index
 *
 * @author Sanjay Singh Rawat
 */
public class FindMaxSumInWindow {

    public static void main(String[] args) {
        Assertions.assertEquals(19, maxSum(new int[] {2, 5, 3, 9, 7, 1, 4, 6}, 3));
        Assertions.assertEquals(19, maxSumSlidingWindow(new int[] {2, 5, 3, 9, 7, 1, 4, 6}, 3));
    }

    public static int maxSum(int[] arr, int window) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length - window; i++) {
            int windowSum = 0;
            for (int j = i; j < i + window; j++) {
                windowSum += arr[j];
            }
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }

    public static int maxSumSlidingWindow(int[] arr, int window) {
        int size = arr.length;
        int start = 0;
        int end = 0;
        int currentSum = 0;
        int maxSum = 0;

        while (end < size) {
            currentSum += arr[end];
            if (end - start + 1 < window) {
                end++;
            } else if (end - start + 1 == window) {
                maxSum = Math.max(maxSum, currentSum);
                currentSum -= arr[start];
                start++;
                end++;
            }
        }
        return maxSum;
    }
}
