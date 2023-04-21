package com.learndatastructure.binarysearch;

import org.junit.jupiter.api.Assertions;

/**
 * Given a matrix[][], that is sorted row wise and column wise.
 * Find an element in it and return the position of the element.
 * {
 *  {10, 20, 30, 40},
 *  {15, 25, 35, 45},
 *  {27, 29, 37, 48},
 *  {32, 33, 39, 50}
 * }
 * <p></p>
 * Start iteration from top right i.e, row = 0 and column = matrix[0].length - 1.
 * If target is greater than current element then increment the row.
 * If target is less than current element then decrement the column.
 *
 * @author Sanjay Singh Rawat
 */
public class SearchInSortedMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                    {10, 20, 30, 40},
                    {15, 25, 35, 45},
                    {27, 29, 37, 48},
                    {32, 33, 39, 50}
                };
        Assertions.assertTrue(searchInMatrix(matrix, 29));
        Assertions.assertTrue(searchInMatrix(matrix, 32));
        Assertions.assertTrue(searchInMatrix(matrix, 10));
        Assertions.assertTrue(searchInMatrix(matrix, 50));
        Assertions.assertTrue(searchInMatrix(matrix, 15));
        Assertions.assertFalse(searchInMatrix(matrix, 51));
    }

    public static boolean searchInMatrix(int[][] matrix, int target) {
        int row = 0;
        int column = matrix.length - 1;

        while (row >= 0 && row < matrix.length && column >= 0) {
            if (matrix[row][column] == target) {
                return true;
            }
            if (matrix[row][column] < target) {
                row++;
            } else {
                column--;
            }
        }

        return false;
    }
}
