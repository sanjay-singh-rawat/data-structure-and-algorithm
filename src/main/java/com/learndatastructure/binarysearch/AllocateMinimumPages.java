package com.learndatastructure.binarysearch;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/**
 * Given an array pages[] : {10, 20, 30, 40} (each element in the array represents the pages of a book) and a key (number of student) : 2.
 * Assign books in such a way that the maximum number of pages assigned to a student is minimum.
 * Criteria:
 *      1. We have to provide books to each student.<br>
 *      2. One book will be assigned to only one person.<br>
 *      3. Books assignment must be in continuous manner.
 * <p></p>
 * 1. The maximum possible answer could be when there is only one student. So, all the book will be assigned to him and the result would be
 * the sum of pages of all the books. end = sum(pages[]).
 * 2. The minimum possible answer could be when number of student is equal to the number of book, In this case all the students will get at
 * most one book. So, the result would be the maximum number of pages among them (i.e, maximum(pages[])). start = max(pages[]).
 * 3. Hence, we can apply binary search in this given range and each time we can consider the mid-value as the maximum limit of pages one can
 * get. And check for the limit if answer is valid then update the limit accordingly.
 *
 * @author Sanjay Singh Rawat
 */
public class AllocateMinimumPages {

    public static void main(String[] args) {
        Assertions.assertEquals(60, minimizeMaximumPages(new int[] {10, 20, 30, 40}, 2));
        Assertions.assertEquals(113, minimizeMaximumPages(new int[] {12, 34, 67, 90}, 2));
    }

    public static int minimizeMaximumPages(int[] pages, int students) {
        if (students > pages.length) {
            return -1;
        }

        int start = pages[pages.length - 1];
        int end = Arrays.stream(pages).sum();
        int maximumPages = 0;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (isPageAssignmentPossible(pages, students, mid)) {
                maximumPages = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return maximumPages;
    }

    private static boolean isPageAssignmentPossible(int[] pages, int maxStudents, int maxPagesAssigned) {
        int studentsRequired = 1;
        int pageAssigned = 0;

        for (int page : pages) {
            pageAssigned += page;
            if (pageAssigned > maxPagesAssigned) {
                studentsRequired++;
                pageAssigned = page;
            }
            if (studentsRequired > maxStudents) {
                return false;
            }
        }
        return true;
    }
}
