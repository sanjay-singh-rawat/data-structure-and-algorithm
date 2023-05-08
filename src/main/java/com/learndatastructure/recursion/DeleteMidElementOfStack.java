package com.learndatastructure.recursion;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Sanjay Singh Rawat
 */
public class DeleteMidElementOfStack {

    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        delete(stack, stack.size()/2);

        Assertions.assertFalse(stack.contains(3));
    }


    public static void delete(Deque<Integer> stack, int k) {
        if (k == 0) {
            stack.pop();
            return;
        }

        int top = stack.pop();
        delete(stack, k - 1);
        stack.push(top);
    }
}
