package com.learndatastructure.interviewcake.hashingandhashtables.wordclouddata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Time Complexity - O(n)
 * Space Complexity - O(n)
 *
 * This challenge has several parts. Let's break them down.
 *
 * 1. Splitting the words from the input string
 * 2. Populating the hash map with each word
 * 3. Handling words that are both uppercase and lowercase in the input string
 *
 * Solution
 * In our solution, we make three decisions:
 *
 * 1. We use a class. This allows us to tie our methods together, calling them on instances of our class instead of passing references.
 * 2. To handle duplicate words with different cases, we choose to make a word uppercase in our hash map only if it is always uppercase in the original string.
 *      While this is a reasonable approach, it is imperfect (consider proper nouns that are also lowercase words, like "Bill" and "bill").
 * 3. We build our own splitWords() method instead of using a built-in one. This allows us to pass each word to our addWordToHashMap() method as it was split,
 *      and to split words and eliminate punctuation in one iteration.
 *
 * To split the words in the input string and populate a hash map of the unique words to the number of times they occurred, we:
 *
 * 1. Split words by spaces, em dashes, and ellipses—making sure to include hyphens surrounded by characters.
 *      We also include all apostrophes (which will handle contractions nicely but will break possessives into separate words).
 * 2. Populate the words in our hash map as they are identified, checking if the word is already in our hash map in its current case or another case.
 *
 * If the input word is uppercase and there's a lowercase version in the hash map, we increment the lowercase version's count.
 * If the input word is lowercase and there's an uppercase version in the hash map, we "demote" the uppercase version by adding the lowercase version
 * and giving it the uppercase version's count.
 *
 * @author Sanjay Singh Rawat
 * @since 2020.08.20
 */
public class WordCloudData {

    private Map<String, Integer> wordsToCounts = new HashMap<>();

    public WordCloudData(String inputString) {
        populateWordsToCounts(inputString);
    }

    public Map<String, Integer> getWordsToCounts() {
        return wordsToCounts;
    }

    private void populateWordsToCounts(String inputString) {
        // iterates over each character in the input string, splitting
        // words and passing them to addWordToHashMap()

        int currentWordStartIndex = 0;
        int currentWordLength = 0;

        for (int i = 0; i < inputString.length(); i++) {
            char character = inputString.charAt(i);

            // if we reached the end of the string we check if the last
            // character is a letter and add the last word to our hash map
            if (i == inputString.length() - 1) {
                if (Character.isLetter(character)) {
                    currentWordLength++;
                }
                if (currentWordLength > 0) {
                    String currentWord = inputString.substring(currentWordStartIndex, currentWordStartIndex + currentWordLength);
                    addWordToHashMap(currentWord);
                }
            }
            // if we reach a space or emdash we know we're at the end of a word
            // so we add it to our hash map and reset our current word
            else if (character == ' ' || character == '\u2014') {
                if (currentWordLength > 0) {
                    String currentWord = inputString.substring(currentWordStartIndex, currentWordStartIndex + currentWordLength);
                    addWordToHashMap(currentWord);
                    currentWordLength = 0;
                }
            }
            // we want to make sure we split on ellipses so if we get two periods in
            // a row we add the current word to our hash map and reset our current word
            else if (character == '.') {
                if (i < inputString.length() - 1 && inputString.charAt(i + 1) == '.') {
                    if (currentWordLength > 0) {
                        String currentWord = inputString.substring(currentWordStartIndex, currentWordStartIndex + currentWordLength);
                        addWordToHashMap(currentWord);
                        currentWordLength = 0;
                    }
                }
            }
            // if the character is a letter or an apostrophe, we add it to our current word
            else if (Character.isLetter(character) || character == '\'') {
                if (currentWordLength == 0) {
                    currentWordStartIndex = i;
                }
                currentWordLength++;
            }
            // if the character is a hyphen, we want to check if it's surrounded by letters
            // if it is, we add it to our current word
            else if (character == '-') {
                if (i > 0 && Character.isLetter(inputString.charAt(i - 1)) && Character.isLetter(inputString.charAt(i + 1))) {
                    if (currentWordLength == 0) {
                        currentWordStartIndex = i;
                    }
                    currentWordLength++;
                } else {
                    if (currentWordLength > 0) {
                        String currentWord = inputString.substring(currentWordStartIndex, currentWordStartIndex + currentWordLength);
                        addWordToHashMap(currentWord);
                        currentWordLength = 0;
                    }
                }
            }
        }

    }

    private void addWordToHashMap(String word) {
        // if the word is already in the hash map we increment its count
        if (wordsToCounts.containsKey(word)) {
            wordsToCounts.put(word, wordsToCounts.get(word) + 1);
        }
        // if a lowercase version is in the hash map, we know our input word must be uppercase
        // but we only include uppercase words if they're always uppercase
        // so we just increment the lowercase version's count
        else if (wordsToCounts.containsKey(word.toLowerCase())) {
            int newCount = wordsToCounts.get(word.toLowerCase()) + 1;
            wordsToCounts.put(word.toLowerCase(), newCount);
        }
        // if an uppercase version is in the hash map, we know our input word must be lowercase.
        // since we only include uppercase words if they're always uppercase, we add the
        // lowercase version and give it the uppercase version's count
        else if (wordsToCounts.containsKey(capitalize(word))) {
            int newCount = wordsToCounts.get(capitalize(word)) + 1;
            wordsToCounts.put(word, newCount);
            wordsToCounts.remove(capitalize(word));
        }
        // otherwise, the word is not in the hash map at all, lowercase or uppercase
        // so we add it to the hash map
        else {
            wordsToCounts.put(word, 1);
        }
    }

    private String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    /**
     * We could use the built-in String.split() method to separate our words,
     * but if we just split on spaces we'd have to iterate over all the words
     * before or after splitting to clean up the punctuation.
     * And consider em dashes or ellipses, which aren't surrounded by spaces but nonetheless separate words.
     * Instead, we'll make our own splitWords() method,
     * which will let us iterate over the input string only once.
     *
     * We'll check if each character is a letter with {@link Character#isLetter(char)}.
     *
     * @param inputString
     */
    private List<String> splitWords(String inputString) {
        List<String> words = new ArrayList<>();
        int currentWordStartIndex = 0;
        int currentWordLength = 0;

        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            if (Character.isLetter(c)) {
                if (currentWordLength == 0) {
                    currentWordStartIndex = i;
                }
                currentWordLength++;
            } else {
                String currentWord = inputString.substring(currentWordStartIndex, currentWordStartIndex + currentWordLength);
                words.add(currentWord);
                currentWordLength = 0;
            }
        }

        return words;
    }
}
