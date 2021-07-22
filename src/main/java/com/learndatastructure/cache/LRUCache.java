package com.learndatastructure.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Look up the item in our hash map.
 * 2. If the item is in the hash table, then it's already in our cache - this is called a "cache hit".
 *      1. Use the hash table to quickly find the corresponding linked list node.
 *      2. Move the item's linked list node to the head of the linked list,
 *          since it's now the most recently used (so it shouldn't get evicted any time soon).
 * 3. If the item isn't in the hash table, we have a cache miss. We need to load the item into the cache:
 *      1. Is our cache full? If so, we need to evict something to make room:
 *          1. Grab the least-recently used cache item - it'll be at the tail of the linked list.
 *          2. Evict that item from the cache by removing it from the linked list and the hash map.
 *      2. Create a new linked list node for the item. Insert it at the head of the linked ist.
 *      3. Add the item to our hash map, storing the newly created linked list node as the value.
 *
 * @param <K> the type of key maintained by this cache.
 * @param <V> the type of mapped value.
 *
 * @author Sanjay Singh Rawat
 * @since 2021.07.22
 */
public final class LRUCache<K, V> implements Cache<K, V> {

    private final int maxCapacity;

    private Map<K, Node<K, V>> cache;

    private Node<K, V> first;

    private Node<K, V> last;

    public LRUCache() {
        this(16);
    }

    public LRUCache(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.cache = new HashMap<>(maxCapacity);
    }

    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        public Node(Node<K, V> prev, K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    @Override
    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            Node<K, V> cachedNode = cache.get(key);
            cachedNode.value = value;
            remove(cachedNode);
            add(cachedNode);
        } else {
            if (cache.size() == maxCapacity) {
                cache.remove(last.key);
                remove(last);
            }
            Node<K, V> newNode = new Node<>(null, key, value, null);
            add(newNode);
            cache.put(key, newNode);
        }
    }

    @Override
    public V get(K key) {
        Node<K, V> cachedNode = cache.get(key);
        remove(cachedNode);
        add(cachedNode);
        return cachedNode == null ? null : cachedNode.value;
    }

    private void add(Node<K, V> node) {
        if (node == null) return;

        if (first == null) {
            first = last = node;
        } else {
            node.next = first;
            first.prev = node;
            first = node;
        }
    }

    private void remove(Node<K, V> node) {
        if (node == null) return;

        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            first = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            last = node.prev;
        }
    }

    public void print() {
        Node<K, V> current = first;
        while (current != null) {
            System.out.print(current.value + " -> ");
            current = current.next;
        }
        System.out.print("NULL");
        System.out.println();
    }

    public static void main(String[] args) {
        // 1. initialize the cache with capacity 10.
        Cache<String, String> cache = new LRUCache<>(10);

        // 2. insert 10 objects to cache
        for (int i = 1; i <= 10; i++) {
            cache.put(String.format("key-%d", i), String.format("value-%d", i));
        }

        // 3. print the cache objects
        System.out.println("Printing cache : ");
        cache.print();

        // 4. access the first object and print the cache
        cache.get("key-1");
        System.out.println("Printing cache after accessing key-1 : ");
        cache.print();

        // 5. insert new objects to cache
        for (int i = 11; i <= 15; i++) {
            cache.put(String.format("key-%d", i), String.format("value-%d", i));
        }

        // 6. print the cache and observe that the least recently used objects are evicted.
        System.out.println("Printing cache after adding new objects : ");
        cache.print();
    }
}
