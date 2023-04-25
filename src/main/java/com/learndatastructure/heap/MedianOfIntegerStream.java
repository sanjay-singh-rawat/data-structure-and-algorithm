package com.learndatastructure.heap;

import org.junit.jupiter.api.Assertions;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a stream of integers, compute the median.
 * Median is the middle value of an ordered data set. For a set of integers, there are just as many elements less than the median as greater.
 * <p>
 * {1, 2, 3, 4, 5, 6, 7, 8, 9}
 * 1. Create a min-heap that contains the larger half of the elements, with the minimum element at the root. {6, 7, 8, 9}
 * 2. Create a max-heap that contains the smaller half of the elements, with the maximum element at the root. {5, 4, 3, 2, 1}
 * <p>
 * Add a new integer into the appropriate half such that the size of the lists differs by 1, at most:
 *  if element is smaller than min. element of larger half:
 *     insert into smaller half at appropriate index
 *     if smaller half is much bigger than larger half:
 *         remove max. element of smaller half and insert at the beginning of larger half (re-balance)
 *  else
 *     insert into larger half at appropriate index:
 *     if larger half is much bigger than smaller half:
 *         remove min. element of larger half and insert at the beginning of smaller half (re-balance)
 * <p>
 * Now, we can compute the median:
 *  if lists contain equal number of elements:
 *     median = (max. element of smaller half + min. element of larger half) / 2
 *  else if smaller half contains more elements:
 *     median = max. element of smaller half
 *  else if larger half contains more elements:
 *     median = min. element of larger half
 * <p>
 * So, the getMedian operation can be performed in O(1) time as it requires the find-min and find-max functions only.
 * The time complexity of the add operation is O(log n) – three insert/delete calls each requiring O(log n) time.
 * @author Sanjay Singh Rawat
 */
public class MedianOfIntegerStream {

    private static final Queue<Integer> minHeap = new PriorityQueue<>();
    private static final Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

    public static void main(String[] args) {
        Assertions.assertThrows(IllegalStateException.class, MedianOfIntegerStream::getMedian);
        add(10);
        Assertions.assertEquals(1, minHeap.size());
        Assertions.assertEquals(0, maxHeap.size());
        add(9);
        add(8);
        Assertions.assertEquals(1, minHeap.size());
        Assertions.assertEquals(2, maxHeap.size());

        Assertions.assertEquals(10, minHeap.peek());
        Assertions.assertEquals(9, maxHeap.peek());

        Assertions.assertEquals(9, getMedian());
        add(11);

        Assertions.assertEquals(9.5, getMedian());
    }

    public static void add(Integer number) {
        if (!minHeap.isEmpty() && number < minHeap.peek()) {
            maxHeap.offer(number);

            // re-balancing
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            }
        } else {
            minHeap.offer(number);

            // re-balancing
            if (minHeap.size() > maxHeap.size() + 1) {
                maxHeap.offer(minHeap.poll());
            }
        }
    }

    public static double getMedian() {
        if (minHeap.isEmpty() && maxHeap.isEmpty()) {
            throw new IllegalStateException("The stream is empty, please try again later!");
        }
        if (minHeap.size() == maxHeap.size()) {
            return (minHeap.peek() + maxHeap.peek())/2.0;
        } else if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else {
            return maxHeap.peek();
        }
    }
}
