package com.learndatastructure.interviewcake.hashingandhashtables.permutationpalindrome;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Sanjay Singh Rawat
 * @since 2020.08.19
 */
public class Solution {

    public static void main(String[] args) throws Exception {
        permutationWithOddNumberOfCharsTest();
        permutationWithEvenNumberOfCharsTest();
        noPermutationWithOddNumberOfChars();
        noPermutationWithEvenNumberOfCharsTest();
        emptyStringTest();
        oneCharacterStringTest();
    }

    public static boolean hasPalindromePermutation(String theString) {
        // track characters we've seen an odd number of times
        Set<Character> unpairedCharacters = new HashSet<>();

        for (char c : theString.toCharArray()) {
            if (unpairedCharacters.contains(c)) {
                unpairedCharacters.remove(c);
            } else {
                unpairedCharacters.add(c);
            }
        }

        // the string has a palindrome permutation if it
        // has one or zero characters without a pair
        return unpairedCharacters.size() <= 1;
    }

    // tests

    public static void permutationWithOddNumberOfCharsTest() {
        final boolean result = hasPalindromePermutation("aabcbcd");
        assert result;
    }

    public static void permutationWithEvenNumberOfCharsTest() {
        final boolean result = hasPalindromePermutation("aabccbdd");
        assert result;
    }

    public static void noPermutationWithOddNumberOfChars() {
        final boolean result = hasPalindromePermutation("aabcd");
        assert !result;
    }

    public static void noPermutationWithEvenNumberOfCharsTest() {
        final boolean result = hasPalindromePermutation("aabbcd");
        assert !result;
    }

    public static void emptyStringTest() {
        final boolean result = hasPalindromePermutation("");
        assert result;
    }

    public static void oneCharacterStringTest() {
        final boolean result = hasPalindromePermutation("a");
        assert result;
    }
}
