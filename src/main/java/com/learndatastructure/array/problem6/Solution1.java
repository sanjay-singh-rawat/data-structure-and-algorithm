package com.learndatastructure.array.problem6;

import java.util.Arrays;

/**
 * @author Sanjay Singh Rawat
 * @since 2019.10.15
 */
public class Solution1 {

    public static void main(String[] args) {
        int[] arr1 = {0, 3, 4, 31};
        int[] arr2 = {2, 4, 6, 30, 43, 44};

        int[] mergedArray = mergeSortedArray(arr1, arr2);
        if (mergedArray != null) {
            Arrays.stream(mergedArray).forEach(System.out::println);
        }
    }

    private static int[] mergeSortedArray(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length == 0) {
            return arr2;
        }
        if (arr2 == null || arr2.length == 0) {
            return arr1;
        }
        int arr1Length = arr1.length;
        int arr2Length = arr2.length;
        int[] mergedArray = new int[arr1Length + arr2Length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (arr1Length > i && arr2Length > j) {
            if (arr1[i] < arr2[j]) {
                mergedArray[k++] = arr1[i++];
            } else {
                mergedArray[k++] = arr2[j++];
            }
        }

        while (arr1Length > i) {
            mergedArray[k++] = arr1[i++];
        }

        while (arr2Length > j) {
            mergedArray[k++] = arr2[j++];
        }

        return mergedArray;
    }
}
