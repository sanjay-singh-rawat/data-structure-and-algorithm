package com.learndatastructure.heap;

import org.junit.jupiter.api.Assertions;

/**
 * @author Sanjay Singh Rawat
 */
public class HeapValidationTests {

    public static void main(String[] args) {
        validateMinHeap();
        validateMaxHeap();
    }

    private static void validateMinHeap() {
        System.out.println("Starting Min Heap operation");
        MinHeap<Integer> integerMinHeap = new MinHeap<>();
        Assertions.assertTrue(integerMinHeap.isEmpty());
        Assertions.assertThrows(IllegalStateException.class, integerMinHeap::peek);
        Assertions.assertEquals(0, Integer.valueOf(integerMinHeap.size()));

        integerMinHeap.add(7);
        integerMinHeap.add(10);
        integerMinHeap.add(4);
        integerMinHeap.add(3);
        integerMinHeap.add(20);
        integerMinHeap.add(15);

        Assertions.assertFalse(integerMinHeap.isEmpty());
        Assertions.assertEquals(6, Integer.valueOf(integerMinHeap.size()));

        Assertions.assertEquals(3, integerMinHeap.peek());
        Integer deletedElement = integerMinHeap.poll();
        Assertions.assertEquals(3, deletedElement);
        Assertions.assertEquals(4, integerMinHeap.peek());

        integerMinHeap.add(1);
        Assertions.assertEquals(1, integerMinHeap.peek());
        System.out.println("Ending Min Heap operation");
    }

    private static void validateMaxHeap() {
        System.out.println("Starting Max Heap operation");
        MaxHeap<Integer> integerMaxHeap = new MaxHeap<>();
        Assertions.assertTrue(integerMaxHeap.isEmpty());
        Assertions.assertThrows(IllegalStateException.class, integerMaxHeap::peek);
        Assertions.assertEquals(0, Integer.valueOf(integerMaxHeap.size()));

        integerMaxHeap.add(7);
        integerMaxHeap.add(10);
        integerMaxHeap.add(4);
        integerMaxHeap.add(3);
        integerMaxHeap.add(20);
        integerMaxHeap.add(15);

        Assertions.assertFalse(integerMaxHeap.isEmpty());
        Assertions.assertEquals(6, Integer.valueOf(integerMaxHeap.size()));

        Assertions.assertEquals(20, integerMaxHeap.peek());
        Integer deletedElement = integerMaxHeap.poll();
        Assertions.assertEquals(20, deletedElement);
        Assertions.assertEquals(15, integerMaxHeap.peek());

        integerMaxHeap.add(100);
        Assertions.assertEquals(100, integerMaxHeap.peek());
        System.out.println("Ending Max Heap operation");
    }
}
