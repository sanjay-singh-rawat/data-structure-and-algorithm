package com.learndatastructure.slidingwindow;

import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sanjay Singh Rawat
 */
public class LargestSubArrayOfSumK {

    public static void main(String[] args) {
        Assertions.assertEquals(4, maxWindowForSumNestedLoop(new int[] {5, 6, -5, 5, 3, 5, 3, -2, 0}, 8));
//        Assertions.assertEquals(4, maxWindow(new int[] {5, 6, -5, 5, 3, 5, 3, -2, 0}, 8));
        Assertions.assertEquals(4, maxWindowPositiveNumbers(new int[] {4, 1, 1, 1, 2, 3, 5}, 5));
        Assertions.assertEquals(4, maxWindow(new int[] {4, 1, 1, 1, 2, 3, 5}, 5));
    }

    public static int maxWindowForSumNestedLoop(int[] arr, int sum) {
        int maxWindow = 0;

        for (int i = 0; i < arr.length; i++) {
            int currentSum = 0;
            for (int j = i; j < arr.length; j++) {
                currentSum += arr[j];
                if (currentSum == sum) {
                    maxWindow = Math.max(maxWindow, j - i + 1);
                }
            }
        }
        return maxWindow;
    }

    public static int maxWindowPositiveNumbers(int[] arr, int sum) {
        int start = 0;
        int end = 0;
        int maxWindow = 0;
        int currentSum = 0;

        while (end < arr.length) {
            currentSum += arr[end];
            if (currentSum < sum) {
                end++;
            } else if (currentSum == sum) {
                maxWindow = Math.max(maxWindow, end - start + 1);
                end++;
            } else {
                while (currentSum > sum) {
                    currentSum -= arr[start];
                    start++;
                }
                if (currentSum == sum) {
                    maxWindow = Math.max(maxWindow, end - start + 1);
                }
                end++;
            }
        }
        return maxWindow;
    }

    private static int maxWindow(int[] arr, int sum) {
        int currentSum = 0;
        int maxWindow = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];
            if (map.containsKey(currentSum - sum)) {
                maxWindow += map.get(currentSum - sum);
            }
            map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
        }
        return maxWindow;
    }
}
