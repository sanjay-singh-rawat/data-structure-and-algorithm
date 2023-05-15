package com.learndatastructure.stack;

import org.junit.jupiter.api.Assertions;

/**
 * 1. find maximum element at left for each element. maxLeft[]
 * 2. find maximum element at right for each element. maxRight[].
 * 3. water at each index is minimum of max left and max right minus current element. water[i] = min(maxLeft[i], maxRight[i]) - arr[i]
 * 4. maximum water can be trapped is sum of all the elements of water array. maxWater += water[i].
 *
 * @author Sanjay Singh Rawat
 */
public class RainWaterTrapping {

    public static void main(String[] args) {
        int[] input = {3, 0, 0, 2, 0, 4};
        int expectedOutput = 10;
        int actualOutput = maxWater(input);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    private static int maxWater(int[] arr) {
        int size = arr.length;
        int[] maxRight = new int[size];
        int[] maxLeft = new int[size];

        maxLeft[0] = arr[0];
        for (int index = 1; index < size; index++) {
            maxLeft[index] = Math.max(maxLeft[index - 1], arr[index]);
        }

        maxRight[size - 1] = arr[size - 1];
        for (int index = size - 2; index >= 0; index--) {
            maxRight[index] = Math.max(maxRight[index + 1], arr[index]);
        }

        int[] water = new int[size];
        for (int index = 0; index < size; index++) {
            water[index] = Math.min(maxLeft[index], maxRight[index]) - arr[index];
        }

        int maxWater = 0;
        for (int index = 0; index < size; index++) {
            maxWater += water[index];
        }
        return maxWater;
    }
}
