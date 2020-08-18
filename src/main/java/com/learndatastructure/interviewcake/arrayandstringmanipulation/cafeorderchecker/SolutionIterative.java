package com.learndatastructure.interviewcake.arrayandstringmanipulation.cafeorderchecker;

/**
 * Time Complexity - O(n)
 * Space Complexity - O(1)
 *
 * Solution:
 * We walk through servedOrders, seeing if each customer order so far matches a customer order from one of the two registers. To check this, we:
 *
 * 1. Keep pointers to the current index in takeOutOrders, dineInOrders, and servedOrders.
 * 2. Walk through servedOrders from beginning to end.
 * 3. If the current order in servedOrders is the same as the current customer order in takeOutOrders, increment takeOutOrdersIndex and keep going.
 *    This can be thought of as "checking off" the current customer order in takeOutOrders and servedOrders,
 *    reducing the problem to the remaining customer orders in the arrays.
 * 4. Same as above with dineInOrders.
 * 5. If the current order isn't the same as the customer order at the front of takeOutOrders or dineInOrders,
 *    we know something's gone wrong and we're not serving food first-come, first-served.
 * 6. If we make it all the way to the end of servedOrders, we'll check that we've reached the end of takeOutOrders and dineInOrders.
 *    If every customer order checks out, that means we're serving food first-come, first-served.
 *
 * In this writeup, we'll enforce that every order that goes into the kitchen (appearing in takeOutOrders or dineInOrders)
 * should come out of the kitchen (appearing in servedOrders).
 *
 * @author Sanjay Singh Rawat
 * @since 2020.08.18
 */
public class SolutionIterative {

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
        int takeOutOrdersIndex = 0;
        int dineInOrdersIndex = 0;

        for (int order : servedOrders) {
            // if we still have orders in takeOutOrders
            // and the current order in takeOutOrders is the same
            // as the current order in servedOrders
            if (takeOutOrdersIndex < takeOutOrders.length && order == takeOutOrders[takeOutOrdersIndex]) {
                takeOutOrdersIndex++;
            }
            // if we still have orders in dineInOrders
            // and the current order in dineInOrders is the same
            // as the current order in servedOrders
            else if (dineInOrdersIndex < dineInOrders.length && order == dineInOrders[dineInOrdersIndex]) {
                dineInOrdersIndex++;
            }
            // if the current order in servedOrders doesn't match the current
            // order in takeOutOrders or dineInOrders, then we're not serving first-come,
            // first-served
            else {
                return false;
            }
        }

        // check for any extra orders at the end of takeOutOrders or dineInOrders
        if (takeOutOrdersIndex != takeOutOrders.length || dineInOrdersIndex != dineInOrders.length) {
            return false;
        }

        // all orders in servedOrders have been "accounted for"
        // so we're serving first-come, first-served!
        return true;
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
