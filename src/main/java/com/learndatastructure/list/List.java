package com.learndatastructure.list;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * @author Sanjay Singh Rawat
 * @since 2021.07.22
 */
public interface List<E> {

    /**
     * Appends the specified element to the end of this list.
     *
     * <p>This method is equivalent to {@link #addLast}.
     *
     * @param e element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     */
    void add(E e);

    /**
     * Inserts the specified element at the beginning of this list.
     *
     * @param e the element to add
     */
    void addFirst(E e);

    /**
     * Appends the specified element to the end of this list.
     *
     * <p>This method is equivalent to {@link #add}.
     *
     * @param e the element to add
     */
    void addLast(E e);

    /**
     * Retrieves and removes the head (first element) of this list.
     *
     *  @return the head of this list
     *  @throws NoSuchElementException if this list is empty
     */
    E remove();

    /**
     * Removes the element at the specified position in this list.  Shifts any
     * subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    E remove(int index);

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.  If this list does not contain the element, it is unchanged.
     * More formally, removes the element with the lowest index {@code i} such that {@code Objects.equals(o, get(i))} (if such an element exists).
     * Returns {@code true} if this list contained the specified element (or equivalently, if this list changed as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     */
    boolean remove(Object o);

    /**
     * Removes and returns the first element from this list.
     *
     * @return the first element from this list
     * @throws NoSuchElementException if this list is empty
     */
     E removeFirst();

    /**
     * Removes and returns the last element from this list.
     *
     * @return the last element from this list
     * @throws NoSuchElementException if this list is empty
     */
     E removeLast();

     int size();
}
