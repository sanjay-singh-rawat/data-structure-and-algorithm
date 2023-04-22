package com.learndatastructure.heap;

import java.util.Arrays;

/**
 * @author Sanjay Singh Rawat
 */
@SuppressWarnings("unchecked")
public class MinHeap<E extends Comparable<E>> {

    private int capacity = 10;

    private int size;

    private Object[] elements = new Object[capacity];

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    private E leftChild(int index) {
        return (E) elements[getLeftChildIndex(index)];
    }

    private E rightChild(int index) {
        return (E) elements[getRightChildIndex(index)];
    }

    private E parent(int index) {
        return (E) elements[getParentIndex(index)];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return (E) elements[0];
    }

    public void add(E element) {
        ensuresCapacity();
        elements[size] = element;
        size++;
        heapifyUp();
    }

    private void heapifyUp() {
        int index = size - 1;
        while (hasParent(index) && parent(index).compareTo((E) elements[index]) > 0) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void swap(int indexOne, int indexTwo) {
        E temp = (E) elements[indexOne];
        elements[indexOne] = elements[indexTwo];
        elements[indexTwo] = temp;
    }

    public E poll() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        E element = (E) elements[0];
        elements[0] = elements[size - 1];
        size--;
        heapifyDown();
        return element;
    }

    public void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallChildIndex = getLeftChildIndex(index);

            if (hasRightChild(index) && rightChild(index).compareTo(leftChild(index)) < 0) {
                smallChildIndex = getRightChildIndex(index);
            }

            if (((E) elements[index]).compareTo((E) elements[smallChildIndex]) < 0) {
                break;
            } else {
                swap(index, smallChildIndex);
                index = smallChildIndex;
            }
        }
    }

    private void ensuresCapacity() {
        if (size == capacity) {
            int newCapacity = 2 * capacity;
            elements = Arrays.copyOf(elements, newCapacity);
            capacity = newCapacity;
        }
    }
}
