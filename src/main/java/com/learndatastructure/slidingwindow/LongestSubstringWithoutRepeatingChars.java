package com.learndatastructure.slidingwindow;

import org.junit.jupiter.api.Assertions;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string str = "pwwkew", find the longest substring without repeating character.
 * output = wke or kew -> length = 3
 *
 * @author Sanjay Singh Rawat
 */
public class LongestSubstringWithoutRepeatingChars {

    public static void main(String[] args) {
        Assertions.assertEquals(3, longestSubstring("pwwkew"));
    }

    public static int longestSubstring(String str) {
        int start = 0;
        int end = 0;
        int longestSubstringLength = 0;

        Set<Character> set = new HashSet<>();

        while (end < str.length()) {
            char currentChar = str.charAt(end);
            set.add(currentChar);

            if (set.size() == end - start + 1) {
                longestSubstringLength = Math.max(longestSubstringLength, set.size());
                end++;
            } else if (set.size() < end - start + 1) {
                while (set.size() < end - start + 1) {
                    char charAtStart = str.charAt(start);
                    set.remove(charAtStart);
                    start++;
                }
                if (set.size() == end - start + 1) {
                    longestSubstringLength = Math.max(longestSubstringLength, end - start + 1);
                }
                end++;
            }
        }
        return longestSubstringLength;
    }
}
