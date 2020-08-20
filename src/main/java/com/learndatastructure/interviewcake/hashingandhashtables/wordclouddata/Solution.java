package com.learndatastructure.interviewcake.hashingandhashtables.wordclouddata;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sanjay Singh Rawat
 * @since 2020.08.20
 */
public class Solution {

    public static void main(String[] args) {
        simpleSentenceTest();
        longerSentenceTest();
        punctuationTest();
        hyphenatedWordsTest();
        ellipsesBetweenWordsTest();
        apostrophesTest();
    }

    // tests

    // There are lots of valid solutions for this one. You
    // might have to edit some of these tests if you made
    // different design decisions in your solution.

    public static void simpleSentenceTest() {
        final String text = "I like cake";
        final Map<String, Integer> expected = new HashMap<>() {
            {
                put("I", 1);
                put("like", 1);
                put("cake", 1);
            }
        };
        final WordCloudData actual = new WordCloudData(text);
        assert expected.equals(actual.getWordsToCounts());
    }

    public static void longerSentenceTest() {
        final String text = "Chocolate cake for dinner and pound cake for dessert";
        final Map<String, Integer> expected = new HashMap<>() {
            {
                put("and", 1);
                put("pound", 1);
                put("for", 2);
                put("dessert", 1);
                put("Chocolate", 1);
                put("dinner", 1);
                put("cake", 2);
            }
        };
        final WordCloudData actual = new WordCloudData(text);
        assert expected.equals(actual.getWordsToCounts());
    }

    public static void punctuationTest() {
        final String text = "Strawberry short cake? Yum!";
        final Map<String, Integer> expected = new HashMap<>() {
            {
                put("cake", 1);
                put("Strawberry", 1);
                put("short", 1);
                put("Yum", 1);
            }
        };
        final WordCloudData actual = new WordCloudData(text);
        assert expected.equals(actual.getWordsToCounts());
    }

    public static void hyphenatedWordsTest() {
        final String text = "Dessert - mille-feuille cake";
        final Map<String, Integer> expected = new HashMap<>() {
            {
                put("cake", 1);
                put("Dessert", 1);
                put("mille-feuille", 1);
            }
        };
        final WordCloudData actual = new WordCloudData(text);
        assert expected.equals(actual.getWordsToCounts());
    }

    public static void ellipsesBetweenWordsTest() {
        final String text = "Mmm...mmm...decisions...decisions";
        final Map<String, Integer> expected = new HashMap<>() {
            {
                put("mmm", 2);
                put("decisions", 2);
            }
        };
        final WordCloudData actual = new WordCloudData(text);
        assert expected.equals(actual.getWordsToCounts());
    }

    public static void apostrophesTest() {
        final String text = "Allie's Bakery: Sasha's Cakes";
        final Map<String, Integer> expected = new HashMap<>() {
            {
                put("Bakery", 1);
                put("Cakes", 1);
                put("Allie's", 1);
                put("Sasha's", 1);
            }
        };
        final WordCloudData actual = new WordCloudData(text);
        assert expected.equals(actual.getWordsToCounts());
    }
}
