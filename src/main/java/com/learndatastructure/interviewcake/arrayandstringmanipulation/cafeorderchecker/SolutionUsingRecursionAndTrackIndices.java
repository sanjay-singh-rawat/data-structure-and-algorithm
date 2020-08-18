package com.learndatastructure.interviewcake.arrayandstringmanipulation.cafeorderchecker;

/**
 * Time complexity - O(n)
 * Space Complexity - O(n)
 *
 * @author Sanjay Singh Rawat
 * @since 2020.08.18
 */
public class SolutionUsingRecursionAndTrackIndices {

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

    public static boolean isFirstComeFirstServed(int[] takeOutOrders, int[] dineInOrder, int[] servedOrders) {
        return isFirstComeFirstServed(takeOutOrders, dineInOrder, servedOrders, 0, 0, 0);
    }

    private static boolean isFirstComeFirstServed(int[] takeOutOrders, int[] dineInOrder, int[] servedOrders, int servedOrdersIndex, int takeOutOrdersIndex, int dineInOrdersIndex) {
        // base case we've hit the end of servedOrders
        if (servedOrdersIndex == servedOrders.length) {
            return true;
        }

        // if we still have orders in takeOutOrders
        // and the current order in takeOutOrders is the same
        // as the current order in servedOrders
        if ((takeOutOrdersIndex < takeOutOrders.length) && (takeOutOrders[takeOutOrdersIndex] == servedOrders[servedOrdersIndex])) {
            takeOutOrdersIndex++;
        }
        // if we still have orders in dineInOrders
        // and the current order in dineInOrders is the same
        // as the current order in servedOrders
        else if ((dineInOrdersIndex < dineInOrder.length) && (dineInOrder[dineInOrdersIndex] == servedOrders[servedOrdersIndex])) {
            dineInOrdersIndex++;
        }
        // if the current order in servedOrders doesn't match
        // the current order in takeOutOrders or dineInOrders, then we're not
        // serving in first-come, first-served order.
        else {
            return false;
        }

        // the current order in servedOrders has now been "accounted for"
        // so move on to the next one
        servedOrdersIndex++;
        return isFirstComeFirstServed(takeOutOrders, dineInOrder, servedOrders, servedOrdersIndex, takeOutOrdersIndex, dineInOrdersIndex);
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
