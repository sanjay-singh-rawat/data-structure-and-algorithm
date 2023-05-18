package com.learndatastructure.slidingwindow;

import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string str = "aabacbebebe", and k = 3.
 * Find the max window with K unique characters.
 * "aabacb" -> 6
 * "cbebebe" -> 7 -> this is the answer.
 *
 * @author Sanjay Singh Rawat
 */
public class LongestSubstringWithKUniqueChar {

    public static void main(String[] args) {
        Assertions.assertEquals(7, longestSubstringCount("aabacbebebe", 3));
    }

    public static int longestSubstringCount(String str, int k) {
        int start = 0;
        int end = 0;
        int maxSubstring = 0;
        Map<Character, Integer> characterFrequency = new HashMap<>();
        while (end < str.length()) {
            char currentChar = str.charAt(end);
            characterFrequency.put(currentChar, characterFrequency.getOrDefault(currentChar, 0) + 1);

            if (characterFrequency.size() < k) {
                end++;
            } else if (characterFrequency.size() == k) {
                maxSubstring = Math.max(maxSubstring, end - start + 1);
                end++;
            } else {
                while (characterFrequency.size() > k) {
                    char charAtStart = str.charAt(start);
                    Integer charCount = characterFrequency.get(charAtStart);
                    charCount--;
                    if (charCount == 0) {
                        characterFrequency.remove(charAtStart);
                    } else {
                        characterFrequency.put(charAtStart, charCount);
                    }
                    start++;
                }
                if (characterFrequency.size() == k) {
                    maxSubstring = Math.max(maxSubstring, end - start + 1);
                }
                end++;
            }
        }
        return maxSubstring;
    }
}
