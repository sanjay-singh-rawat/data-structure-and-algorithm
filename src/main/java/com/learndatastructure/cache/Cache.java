package com.learndatastructure.cache;

/**
 * @author Sanjay Singh Rawat
 * @since 2021.07.22
 */
public interface Cache<K, V> {

    void put(K key, V value);

    V get(K key);

    void print();
}
