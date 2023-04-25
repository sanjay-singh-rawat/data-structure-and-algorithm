package com.learndatastructure.heap;

import org.junit.jupiter.api.Assertions;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a list of points in a 2-D plane and an integer K. Find K closest points to the origin.
 * The distance between two points on a plane is Euclidean distance (x1, y1) (x2, y2) = √{(x2-x1)^2 + (y2-y1)^2}.
 * To reduce the calculation we can store x^2 + y^2.
 *
 * @author Sanjay Singh Rawat
 */
public class KClosestPointToOrigin {

    public static void main(String[] args) {
        int[][] input = {
                {1, 3},
                {-2, 2},
                {5, 8},
                {0, 1}
        };
        Point[] expectedOutput = {
                new Point(-2, 2),
                new Point(0, 1)
        };
        Assertions.assertArrayEquals(expectedOutput, closestToOrigin(input, 2));
    }

    private record Point (int x, int y) {}

    private record Pair(int distance, Point point) {}

    public static Point[] closestToOrigin(int[][] arr, int k) {
        Queue<Pair> maxHeap = new PriorityQueue<>(Comparator.comparing(Pair::distance).reversed());
        for (int row = 0; row < arr.length; row++) {
            int distance = arr[row][0] * arr[row][0] + arr[row][1] * arr[row][1];
            maxHeap.offer(new Pair(distance, new Point(arr[row][0], arr[row][1])));
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int index = 0;
        Point[] points = new Point[k];
        while (!maxHeap.isEmpty()) {
            Pair pair = maxHeap.poll();
            points[index++] = new Point(pair.point().x(), pair.point().y());
        }
        return points;
    }
}
