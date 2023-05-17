package com.learndatastructure.slidingwindow;

import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Given a word and a text return the count of occurrence of the anagrams of the word in the text.
 * <p>
 * text = "forxxorfxdofr"
 * word = "for"
 * output = 3 (anagram of for -> for, fro, ofr, orf, rof, rfo)
 *
 * @author Sanjay Singh Rawat
 */
public class CountAnagramOccurrence {

    public static void main(String[] args) {
        Assertions.assertEquals(3, countOccurrence("forxxorfxdofr", "for"));
    }

    public static int countOccurrence(String text, String word) {
        Map<Character, Integer> wordCharCount = new HashMap<>();
        for (char character : word.toCharArray()) {
            wordCharCount.put(character, wordCharCount.getOrDefault(character, 0) + 1);
        }

        int count = 0;
        int start = 0;
        int end = 0;

        Map<Character, Integer> substringCharCount = new HashMap<>();

        while (end < text.length()) {
            char character = text.charAt(end);
            substringCharCount.put(character, substringCharCount.getOrDefault(character, 0) + 1);
            if (end - start + 1 < word.length()) {
                end++;
            } else if (end - start + 1 == word.length()) {
                if (isAnagram(wordCharCount, substringCharCount)) {
                    count++;
                }
                substringCharCount.put(text.charAt(start), substringCharCount.get(text.charAt(start)) - 1);
                start++;
                end++;
            }
        }

        return count;
    }

    private static boolean isAnagram(Map<Character, Integer> first, Map<Character, Integer> second) {
        for (Character character : first.keySet()) {
            if (!Objects.equals(first.get(character), second.get(character))) {
                return false;
            }
        }
        return true;
    }
}
