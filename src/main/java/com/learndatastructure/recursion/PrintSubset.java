package com.learndatastructure.recursion;

import org.junit.jupiter.api.Assertions;

import java.util.Set;
import java.util.TreeSet;

/**
 * If we have choices and has to make a decision then use recursion.
 * <p>
 * In Recursion, we take decisions, on basis of decisions the input becomes smaller.
 * <p>
 * We need to represent the decision using the recursion tree, using the input/output method.
 * <p>
 * In recursive tree, the number of branches is equal to number of choices.
 * <p>
 * Input - Output Method:
 *                         OP             IP
 *                        /                \
 *                OP1  Smaller IP     OP2  Smaller IP
 * <p>
 * In the leaf node we will have the answers.
 * <p>
 * We need to either include the input or exclude the input.
 * If we include, we add that to output and make input smaller.
 * If we exclude, we do not add that to output and make input smaller.
 *
 * @author Sanjay Singh Rawat
 */
public class PrintSubset {

    public static void main(String[] args) {
        Set<String> subsets = new TreeSet<>();

        subsets("ab", "", subsets);
        Assertions.assertArrayEquals(new String[]{"", "a", "ab", "b"}, subsets.toArray());

        subsets.clear();

        subsets("abc", "", subsets);
        Assertions.assertArrayEquals(new String[]{"", "a", "ab", "abc", "ac", "b", "bc", "c"}, subsets.toArray());
    }

    public static void subsets(String ip, String op, Set<String> subsets) {
        if (ip.length() == 0) {
            subsets.add(op);
            return;
        }
        String op1 = op;
        String op2 = op;
        op2 += ip.charAt(0);
        ip = ip.substring(1);
        subsets(ip, op1, subsets);
        subsets(ip, op2, subsets);
    }
}
