package com.learndatastructure.array.problem5;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Sanjay Singh Rawat
 * @since 2019.10.10
 */
public class Solution1 {

    public static void main(String[] args) {
        char[] arr1 = {'a', 'b', 'c', 'x'};
        char[] arr2 = {'z', 'y', 'x'};

        System.out.println(containsCommonItem(arr1, arr2));
    }

    private static boolean containsCommonItem(char[] arr1, char[] arr2) {
        // loop through first array and create object where properties == items int the array
        Set<Character> arr1Set = new HashSet<>();
        for (int i = 0; i < arr1.length; i++) {
            arr1Set.add(arr1[i]);
        }
        // loop through second array and check if item in second array exists on created object
        for (int j = 0; j < arr2.length; j++) {
            if (arr1Set.contains(arr2[j])) {
                return true;
            }
        }
        return false;
    }
}
