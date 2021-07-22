package com.learndatastructure.list;

import java.util.NoSuchElementException;

/**
 * @author Sanjay Singh Rawat
 * @since 2019.08.23
 */
public class LinkedList<E> implements List<E> {

    private int size;

    private Node<E> first;

    private Node<E> last;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E item, Node<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private void linkFirst(E e) {
       Node<E> f = first;
       Node<E> newNode = new Node<>(null, e, f);
       first = newNode;
       if (f == null) {
           last = newNode;
       } else {
           f.prev = newNode;
       }
       size++;
    }

    private void linkLast(E e) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(last, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    private E unlinkFirst(Node<E> f) {
        E element = f.item;
        Node<E> next = f.next;
        f.item = null;
        f.next = null;
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        return element;
    }

    private E unlinkLast(Node<E> l) {
        E element = l.item;
        Node<E> prev = l.prev;
        l.item = null;
        l.prev = null;
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }
        size--;
        return element;
    }

    private E unlink(Node<E> x) {
        E element = x.item;
        Node<E> next = x.next;
        Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        x.item = null;
        size--;
        return element;
    }

    @Override
    public void add(E e) {
        linkLast(e);
    }

    @Override
    public void addFirst(E e) {
        linkFirst(e);
    }

    @Override
    public void addLast(E e) {
        linkLast(e);
    }

    @Override
    public E remove() {
        return removeFirst();
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    private Node<E> node(int index) {
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isElementIndex(int index) {
        return index >=0 && index < size;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public E removeFirst() {
        Node<E> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return unlinkFirst(f);
    }

    @Override
    public E removeLast() {
        Node<E> l = last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        return unlinkLast(l);
    }

    @Override
    public int size() {
        return size;
    }
}
