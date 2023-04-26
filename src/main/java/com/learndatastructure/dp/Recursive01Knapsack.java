package com.learndatastructure.dp;

import org.junit.jupiter.api.Assertions;

/**
 * We are given a weight array wt[], a value array val[] and a weight.
 * Find the maximum profit.
 * <p>
 * Identification:
 * 1. We have given choices.
 * 2. We have to return optimal result.
 * <p>
 * wt[]  = {1, 3, 4, 5}
 * val[] = {1, 4, 5, 7}
 * W = 7
 * We have choices that we can put any item to the knapsack to maximize profit.
 *               w1
 *             /   \
 *           /      \
 *       w1 <= W     w1 > W
 *       /    \         |
 * Include  Exclude  Exclude
 * <p>
 * If we have a weight, then:
 * 1. Weight can be less than or equal to the max weight of knapsack.
 *      In this case we can include or exclude it from the knapsack.
 * 2. Weight is greater than knapsack weight
 *      In this case we have only one choice - to exclude that weight.
 * <p>
 * How to write a recursive function for 0/1 knapsack?
 * In a recursive function we should have:
 * 1. Base condition
 * 2. choice diagram
 * <p>
 * How to think of a base condition?
 * Do not run recursion to get base condition.
 * <p>
 * Base condition: Think of the smallest valid input.
 * Because size can not be -1 also knapsack capacity cannot be less than zero.
 * if (n == 0 || w == 0)
 *      return 0;
 * 1. If we do not have any item in array then profit is 0.
 * 2. If array has item but bag capacity is zero in that cae profit is 0.
 *
 * @author Sanjay Singh Rawat
 */
public class Recursive01Knapsack {

    public static void main(String[] args) {
        int[] weight = {1, 2, 4, 5};
        int[] value = {1, 4, 5, 7};
        int capacity = 7;
        Assertions.assertEquals(11, knapsack(weight, value, capacity, weight.length));
        weight = new int[] {1, 3, 4, 5};
        Assertions.assertEquals(9, knapsack(weight, value, capacity, weight.length));
    }

    public static int knapsack(int[] weight, int[] value, int capacity, int size) {
        if (capacity == 0 || size == 0) {
            return 0;
        }

        // If current weight is less than or equal to the capacity, we have two choices
        // 1. Include the weight
        // 2. Exclude the weight
        if (weight[size - 1] <= capacity) {
            int profitOnWeightInclusion = value[size - 1] + knapsack(weight, value, capacity - weight[size - 1], size - 1);
            int profitOnWeightExclusion = knapsack(weight, value, capacity, size - 1);
            return Math.max(profitOnWeightInclusion, profitOnWeightExclusion);
        }
        // if current weight is greater than the capacity, exclude the weight.
        else {
            return knapsack(weight, value, capacity, size - 1);
        }
    }
}
