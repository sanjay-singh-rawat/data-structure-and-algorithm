package com.learndatastructure.binarysearch;

import org.junit.jupiter.api.Assertions;

/**
 * Given and array, arr[] = {'a', 'b', 'f', 'h', 'j'}. Find next letter of a target element.
 * <p><
 * If target == arr[mid], move start to mid + 1 and store possible candidate in result while moving towards left.
 *
 * @author Sanjay Singh Rawat
 */
public class FindNextLetter {

    public static void main(String[] args) {
        char[] input = {'a', 'b', 'f', 'h', 'j'};
        Assertions.assertEquals('b', nextLetter(input, 'a'));
        Assertions.assertEquals('h', nextLetter(input, 'f'));
        Assertions.assertEquals('f', nextLetter(input, 'b'));
        Assertions.assertEquals('f', nextLetter(input, 'c'));
        Assertions.assertEquals('j', nextLetter(input, 'h'));
        Assertions.assertEquals('#', nextLetter(input, 'j'));
    }

    public static char nextLetter(char[] arr, char target) {
        int start = 0;
        int end = arr.length - 1;
        char nextLetter = '#';

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == target) {
                start = mid + 1;
            } else if (arr[mid] > target) {
                nextLetter = arr[mid];
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return nextLetter;
    }
}
