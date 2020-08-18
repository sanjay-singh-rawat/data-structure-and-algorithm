package com.learndatastructure.interviewcake.arrayandstringmanipulation.cafeorderchecker;

import java.util.Arrays;

/**
 * Time complexity - O(n^2)
 * Space Complexity - O(n^2)
 *
 * @author Sanjay Singh Rawat
 * @since 2020.08.18
 */
public class SolutionUsingRecursionAndSlicing {

    public static void main(String[] args) {
        bothRegistersHaveSameNumberOfOrdersTest();
        registersHaveDifferentLengthsTest();
        oneRegisterIsEmptyTest();
        servedOrdersIsMissingOrdersTest();
        servedOrdersHasExtraOrders();
        oneRegisterHasExtraOrders();
        oneRegisterHasUnservedOrders();
        orderNumbersAreNotSequential();
    }

    public static boolean isFirstComeFirstServed(int[] takeOutOrders, int[] dineInOrders, int[] servedOrders) {

        // base case
        if (servedOrders.length == 0) {
            return true;
        }

        // if the first order in servedOrders is the same as the
        // first order in takeOutOrders
        // (making sure first that we have an order in takeOutOrders)
        if (takeOutOrders.length > 0 && takeOutOrders[0] == servedOrders[0]) {
            // take the first order off takeOutOrders and servedOrders and recurse
            return isFirstComeFirstServed(removeFirstOrder(takeOutOrders), dineInOrders, removeFirstOrder(servedOrders));
        }
        // if the first order in servedOrders is the same as the first
        // in dineInOrders
        else if (dineInOrders.length > 0 && dineInOrders[0] == servedOrders[0]) {
            // take the first order off dineInOrders and servedOrders and recurse
            return isFirstComeFirstServed(takeOutOrders, removeFirstOrder(dineInOrders), removeFirstOrder(servedOrders));
        }
        // first order in servedOrders doesn't match the first in
        // takeOutOrders or dineInOrders, so we know it's not first-come, first-served
        else {
            return false;
        }
    }

    private static int[] removeFirstOrder(int[] orders) {
        return Arrays.copyOfRange(orders, 1, orders.length);
    }

    // tests

    public static void bothRegistersHaveSameNumberOfOrdersTest() {
        final int[] takeOutOrders = {1, 4, 5};
        final int[] dineInOrders = {2, 3, 6};
        final int[] servedOrders = {1, 2, 3, 4, 5, 6};
        final boolean result = isFirstComeFirstServed(takeOutOrders, dineInOrders, servedOrders);
        assert result;
    }

    public static void registersHaveDifferentLengthsTest() {
        final int[] takeOutOrders = {1, 5};
        final int[] dineInOrders = {2, 3, 6};
        final int[] servedOrders = {1, 2, 6, 3, 5};
        final boolean result = isFirstComeFirstServed(takeOutOrders, dineInOrders, servedOrders);
        assert !result;
    }

    public static void oneRegisterIsEmptyTest() {
        final int[] takeOutOrders = {};
        final int[] dineInOrders = {2, 3, 6};
        final int[] servedOrders = {2, 3, 6};
        final boolean result = isFirstComeFirstServed(takeOutOrders, dineInOrders, servedOrders);
        assert result;
    }

    public static void servedOrdersIsMissingOrdersTest() {
        final int[] takeOutOrders = {1, 5};
        final int[] dineInOrders = {2, 3, 6};
        final int[] servedOrders = {1, 6, 3, 5};
        final boolean result = isFirstComeFirstServed(takeOutOrders, dineInOrders, servedOrders);
        assert !result;
    }

    public static void servedOrdersHasExtraOrders() {
        final int[] takeOutOrders = {1, 5};
        final int[] dineInOrders = {2, 3, 6};
        final int[] servedOrders = {1, 2, 3, 5, 6, 8};
        final boolean result = isFirstComeFirstServed(takeOutOrders, dineInOrders, servedOrders);
        assert !result;
    }

    public static void oneRegisterHasExtraOrders() {
        final int[] takeOutOrders = {1, 9};
        final int[] dineInOrders = {7, 8};
        final int[] servedOrders = {1, 7, 8};
        final boolean result = isFirstComeFirstServed(takeOutOrders, dineInOrders, servedOrders);
        assert !result;
    }

    public static void oneRegisterHasUnservedOrders() {
        final int[] takeOutOrders = {55, 9};
        final int[] dineInOrders = {7, 8};
        final int[] servedOrders = {1, 7, 8, 9};
        final boolean result = isFirstComeFirstServed(takeOutOrders, dineInOrders, servedOrders);
        assert !result;
    }

    public static void orderNumbersAreNotSequential() {
        final int[] takeOutOrders = {27, 12, 18};
        final int[] dineInOrders = {55, 31, 8};
        final int[] servedOrders = {55, 31, 8, 27, 12, 18};
        final boolean result = isFirstComeFirstServed(takeOutOrders, dineInOrders, servedOrders);
        assert result;
    }
}
