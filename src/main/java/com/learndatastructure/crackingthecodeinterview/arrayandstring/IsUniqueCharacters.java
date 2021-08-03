package com.learndatastructure.crackingthecodeinterview.arrayandstring;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Is Unique: Implement an algorithm to determine if a string has all unique characters.
 *            What if you cannot use additional data structure?
 *
 * @author Sanjay Singh Rawat
 */
public class IsUniqueCharacters {

    static boolean isUnique(String inputString) {
        Map<Character, Integer> characterCounts = new HashMap<>();

        for (char ch : inputString.toCharArray()) {
            Integer count = characterCounts.get(ch);
            characterCounts.put(ch, count == null ? 1 : count + 1);

            if (characterCounts.get(ch) > 1) {
                return false;
            }
        }
        return true;
    }

    static boolean isUniqueWithBooleanArray(String inputString) {
        boolean[] chars = new boolean[256];
        Arrays.fill(chars, false);

        for (int i = 0; i < inputString.length(); i++) {
            int index = inputString.charAt(i);
            if (chars[index]) {
                return false;
            }
            chars[index] = true;
        }
        return true;
    }

    static boolean isUniqueWithoutAdditionalDataStructure(String inputString) {
        int checker = 0;

        for (int i = 0; i < inputString.length(); i++) {
            int bitAtIndex = inputString.charAt(i) - 'a';
            if ((checker & (1 << bitAtIndex)) > 0) {
                return false;
            }
            checker = checker | (1 << bitAtIndex);
        }
        return true;
    }

    public static void main(String[] args) {
        String uniqueCharString = "abcdefghijklmnopqrstuvwxyz";
        String duplicateCharString = "abcaaabcbbc";

        System.out.println("Is " + uniqueCharString + " unique? " + isUnique(uniqueCharString));

        System.out.println("Is " + duplicateCharString + " unique? " + isUnique(duplicateCharString));

        System.out.println("Is " + uniqueCharString + " unique? " + isUniqueWithBooleanArray(uniqueCharString));

        System.out.println("Is " + duplicateCharString + " unique? " + isUniqueWithBooleanArray(duplicateCharString));

        System.out.println("Is " + uniqueCharString + " unique? " + isUniqueWithoutAdditionalDataStructure(uniqueCharString));

        System.out.println("Is " + duplicateCharString + " unique? " + isUniqueWithoutAdditionalDataStructure(duplicateCharString));
    }
}
