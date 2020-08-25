package com.learndatastructure.interviewcake.greedyalgorithm.productofallothernumbers;

import com.learndatastructure.utils.JUnitUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Time Complexity - O(n)
 * Space Complexity - O(n)
 *
 * @author Sanjay Singh Rawat
 * @since 2020.08.24
 */
public class Solution {

    public static void main(String[] args) {
        JUnitUtils.launch(Solution.class);
    }

    public static int[] getProductsOfAllIntsExceptAtIndexByDivision(int[] intArray) {

        if (intArray.length < 2) {
            throw new IllegalArgumentException("Getting the product of number at other indices requires at least 2 numbers");
        }

        int[] productsOfAllIntsExceptAtIndex = new int[intArray.length];

        boolean isZero = false;
        int zeroCount = 0;
        int productSoFar = 1;

        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] == 0) {
                isZero = true;
                zeroCount++;
            } else {
                productSoFar *= intArray[i];
            }
        }

        if (zeroCount > 1) {
            Arrays.fill(productsOfAllIntsExceptAtIndex, 0);
        } else {
            for (int i = 0; i < intArray.length; i++) {
                if (!isZero) {
                    productsOfAllIntsExceptAtIndex[i] = productSoFar/intArray[i];
                } else {
                    if (intArray[i] == 0) {
                        productsOfAllIntsExceptAtIndex[i] = productSoFar;
                    } else {
                        productsOfAllIntsExceptAtIndex[i] = 0;
                    }
                }
            }
        }

        return productsOfAllIntsExceptAtIndex;
    }

    public static int[] getProductsOfAllIntsExceptAtIndexWithoutDivision(int[] intArray) {

        if (intArray.length < 2) {
            throw new IllegalArgumentException("Getting the product of number at other indices requires at least 2 numbers");
        }

        // we make an array with the length of the input array to
        // hold our products
        int[] productsOfAllIntsExceptAtIndex = new int[intArray.length];

        // for each integer, we find the product of all the integers
        // before it, storing the total product so far each time
        int productSoFar = 1;
        for (int i = 0; i < intArray.length; i++) {
            productsOfAllIntsExceptAtIndex[i] = productSoFar;
            productSoFar *= intArray[i];
        }

        // for each integer, we find the product of all the integers
        // after it. since each index in products already has the
        // product of all the integers before it, now we're storing
        // the total product of all other integers
        productSoFar = 1;
        for (int i = intArray.length - 1; i >= 0; i--) {
            productsOfAllIntsExceptAtIndex[i] *= productSoFar;
            productSoFar *= intArray[i];
        }

        return productsOfAllIntsExceptAtIndex;
    }

    // tests

    @Test
    public void smallArrayTest() {
        assertProductOfAllIntsExceptAtIndex(new int[] {1, 2, 3}, new int[] {6, 3, 2});
    }

    @Test
    public void longArrayTest() {
        assertProductOfAllIntsExceptAtIndex(new int[] {8, 2, 4, 3, 1, 5}, new int[] {120, 480, 240, 320, 960, 192});
    }

    @Test
    public void arrayHasOneZeroTest() {
        assertProductOfAllIntsExceptAtIndex(new int[] {6, 2, 0, 3}, new int[] {0, 0, 36, 0});
    }

    @Test
    public void arrayHasTwoZerosTest() {
        assertProductOfAllIntsExceptAtIndex(new int[] {4, 0, 9, 1, 0}, new int[] {0, 0, 0, 0, 0});
    }

    @Test
    public void oneNegativeNumberTest() {
        assertProductOfAllIntsExceptAtIndex(new int[] {-3, 8, 4}, new int[] {32, -12, -24});
    }

    @Test
    public void allNegativeNumbersTest() {
        assertProductOfAllIntsExceptAtIndex(new int[] {-7, -1, -4, -2}, new int[] {-8, -56, -14, -28});
    }

    @Test
    public void exceptionWithEmptyArrayTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            getProductsOfAllIntsExceptAtIndexByDivision(new int[] {});
            getProductsOfAllIntsExceptAtIndexWithoutDivision(new int[] {});
        });
    }

    @Test
    public void exceptionWithOneNumberTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            getProductsOfAllIntsExceptAtIndexByDivision(new int[] {1});
            getProductsOfAllIntsExceptAtIndexWithoutDivision(new int[] {1});
        });
    }

    private void assertProductOfAllIntsExceptAtIndex(int[] intArray, int[] expected) {
        int[] productsOfAllIntsExceptAtIndexByDivision = getProductsOfAllIntsExceptAtIndexByDivision(intArray);
        int[] productsOfAllIntsExceptAtIndexWithoutDivision = getProductsOfAllIntsExceptAtIndexWithoutDivision(intArray);
        assertArrayEquals(expected, productsOfAllIntsExceptAtIndexWithoutDivision);
        assertArrayEquals(expected, productsOfAllIntsExceptAtIndexByDivision);
    }
}
