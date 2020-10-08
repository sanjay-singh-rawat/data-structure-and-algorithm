package com.learndatastructure.interviewcake.treesandgraphs.secondlargestiteminbst;

import com.learndatastructure.interviewcake.treesandgraphs.BinaryTreeNode;
import com.learndatastructure.utils.JUnitUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Recursive approach:
 *      Time Complexity - O(h) (where h is the height of the tree)
 *      Space Complexity - O(h)
 * We're doing one walk from top to bottom of our BST. This means O(h) time (again, that's O(lg n) if the tree is balanced, O(n) otherwise).
 * A clean recursive implementation will take O(h) space in the call stack.
 *
 * Iterative approach:
 *      Time Complexity - O(h) (where h is the height of the tree)
 *      Space Complexity - O(1)
 * We're doing one walk down our BST, which means O(h) time,
 * where h is the height of the tree (again, that's O(lg n) if the tree is balanced, O(n) otherwise). O(1) space.
 *
 * @author Sanjay Singh Rawat
 * @since 2020.10.08
 */
public class Solution {

    public static void main(String[] args) {
        JUnitUtils.launch(Solution.class);
    }

    private static int findLargestRecursive(BinaryTreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException("Tree must have at least 1 node");
        }
        if (root.right != null) {
            return findLargestRecursive(root.right);
        }
        return root.value;
    }

    public static int findSecondLargestRecursive(BinaryTreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            throw new IllegalArgumentException("Tree must have at least 2 nodes");
        }

        // case: we're currently at largest, and largest has a left subtree,
        // so 2nd largest is largest in said subtree
        if (root.left != null && root.right == null) {
            return findLargestRecursive(root.left);
        }

        // case: we're at parent of largest, and largest has no left subtree,
        // so 2nd largest must be current node
        // by the way root.right will always be not null at this point.
        // putting it in if condition for more clarity.
        if (root.right != null && root.right.left == null && root.right.right == null) {
            return root.value;
        }

        // otherwise: step right
        return findSecondLargestRecursive(root.right);
    }

    private int findLargestIterative(BinaryTreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException("Tree must have at least 1 node");
        }
        BinaryTreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

    public int findSecondLargestIterative(BinaryTreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            throw new IllegalArgumentException("Tree must have at least 2 nodes");
        }

        BinaryTreeNode current = root;
        while (true) {

            // case: current is largest and has a left subtree
            // 2nd largest is the largest in that subtree
            if (current.left != null && current.right == null) {
                return findLargestIterative(current.left);
            }

            // case: current is parent of largest, and largest has no children,
            // so current is 2nd largest
            if (current.right != null && current.right.left == null && current.right.right == null) {
                return current.value;
            }

            current = current.right;
        }
    }

    // tests

    @Test
    public void findSecondLargestTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(10);
        a.insertRight(40);
        final BinaryTreeNode b = root.insertRight(70);
        b.insertLeft(60);
        b.insertRight(80);
        final int expected = 70;
        assertEquals(expected, findSecondLargestRecursive(root));
        assertEquals(expected, findSecondLargestIterative(root));
    }

    @Test
    public void largestHasLeftChildTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(10);
        a.insertRight(40);
        root.insertRight(70).insertLeft(60);
        final int expected = 60;
        assertEquals(expected, findSecondLargestRecursive(root));
        assertEquals(expected, findSecondLargestIterative(root));
    }

    @Test
    public void largestHasLeftSubtreeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(10);
        a.insertRight(40);
        final BinaryTreeNode b = root.insertRight(70).insertLeft(60);
        b.insertLeft(55).insertRight(58);
        b.insertRight(65);
        final int expected = 65;
        assertEquals(expected, findSecondLargestRecursive(root));
        assertEquals(expected, findSecondLargestIterative(root));
    }

    @Test
    public void secondLargestIsRootNodeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(10);
        a.insertRight(40);
        root.insertRight(70);
        final int expected = 50;
        assertEquals(expected, findSecondLargestRecursive(root));
        assertEquals(expected, findSecondLargestIterative(root));
    }

    @Test
    public void descendingLinkedListTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        root.insertLeft(40).insertLeft(30).insertLeft(20);
        final int expected = 40;
        assertEquals(expected, findSecondLargestRecursive(root));
        assertEquals(expected, findSecondLargestIterative(root));
    }

    @Test
    public void ascendingLinkedListTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        root.insertRight(60).insertRight(70).insertRight(80);
        final int expected = 70;
        assertEquals(expected, findSecondLargestRecursive(root));
        assertEquals(expected, findSecondLargestIterative(root));
    }

    @Test
    public void exceptionWithTreeThatHasOneNodeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        assertThrows(IllegalArgumentException.class, () -> {
            findSecondLargestRecursive(root);
            findSecondLargestIterative(root);
        });
    }

    @Test
    public void exceptionWithEmptyTreeTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            findSecondLargestRecursive(null);
            findSecondLargestIterative(null);
        });
    }
}
