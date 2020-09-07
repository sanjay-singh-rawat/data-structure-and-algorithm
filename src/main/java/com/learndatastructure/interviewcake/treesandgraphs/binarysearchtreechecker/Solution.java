package com.learndatastructure.interviewcake.treesandgraphs.binarysearchtreechecker;

import com.learndatastructure.interviewcake.treesandgraphs.BinaryTreeNode;
import com.learndatastructure.utils.JUnitUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Time Complexity - O(n)
 * Space Complexity - O(n)
 *
 * What makes a given node "correct" relative to its ancestors in a BST? Two things:
 *  1.  if a node is in the ancestor's left subtree, then it must be less than the ancestor, and
 *  2.  if a node is in the ancestor's right subtree, then it must be greater than the ancestor.
 *
 * We do a depth-"rst walk through the tree, testing each node for validity as we go.
 * If a node appears in the left subtree of an ancestor, it must be less than that ancestor.
 * If a node appears in the right subtree of an ancestor, it must be greater than that ancestor.
 *
 * Instead of keeping track of every ancestor to check these inequalities,
 * we just check the largest number it must be greater than (its lowerBound) and
 * the smallest number it must be less than (its upperBound).
 *
 * @author Sanjay Singh Rawat
 * @since 2020.09.07
 */
public class Solution {

    public static void main(String[] args) {
        JUnitUtils.launch(Solution.class);
    }

    private static class NodeBounds {
        BinaryTreeNode node;
        int lowerBound;
        int upperBound;

        public NodeBounds(BinaryTreeNode node, int lowerBound, int upperBound) {
            this.node = node;
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }
    }

    public static boolean isBinarySearchTreeIterative(BinaryTreeNode root) {

        // start at the root, with an arbitrarily low lower bound
        // and an arbitrarily high upper bound
        Deque<NodeBounds> nodeAndBoundsStack = new ArrayDeque<>();
        nodeAndBoundsStack.push(new NodeBounds(root, Integer.MIN_VALUE, Integer.MAX_VALUE));

        // depth-first traversal
        while (!nodeAndBoundsStack.isEmpty()) {
            NodeBounds nb = nodeAndBoundsStack.pop();
            BinaryTreeNode node = nb.node;
            int lowerBound = nb.lowerBound;
            int upperBound = nb.upperBound;

            // if this node is invalid, we return false right away
            if (node.value <= lowerBound || node.value >= upperBound) {
                return false;
            }

            if (node.left != null) {
                // this node must be less than the current node
                nodeAndBoundsStack.push(new NodeBounds(node.left, lowerBound, node.value));
            }

            if (node.right != null) {
                // this node must be greater than the current node
                nodeAndBoundsStack.push(new NodeBounds(node.right, node.value, upperBound));
            }
        }

        // if none of the nodes were invalid, return true
        // (at this point we have checked all nodes)
        return true;
    }

    public static boolean isBinarySearchTreeRecursive(BinaryTreeNode root) {
        return isBinarySearchTreeRecursive(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBinarySearchTreeRecursive(BinaryTreeNode root, int lowerBound, int upperBound) {
        if (root == null) {
            return true;
        }

        if (root.value <= lowerBound || root.value >= upperBound) {
            return false;
        }

        return isBinarySearchTreeRecursive(root.left, lowerBound, root.value)
                && isBinarySearchTreeRecursive(root.right, root.value, upperBound);
    }

    // tests

    @Test
    public void validFullTreeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(10);
        a.insertRight(40);
        final BinaryTreeNode b = root.insertRight(70);
        b.insertLeft(60);
        b.insertRight(80);
        final boolean iterativeResult = isBinarySearchTreeIterative(root);
        final boolean recursiveResult = isBinarySearchTreeRecursive(root);
        assertTrue(iterativeResult);
        assertTrue(recursiveResult);
    }

    @Test
    public void bothSubtreesValidTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(20);
        a.insertRight(60);
        final BinaryTreeNode b = root.insertRight(80);
        b.insertLeft(70);
        b.insertRight(90);
        final boolean iterativeResult = isBinarySearchTreeIterative(root);
        final boolean recursiveResult = isBinarySearchTreeRecursive(root);
        assertFalse(iterativeResult);
        assertFalse(recursiveResult);
    }

    @Test
    public void descendingLinkedListTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        root.insertLeft(40).insertLeft(30).insertLeft(20).insertLeft(10);
        final boolean iterativeResult = isBinarySearchTreeIterative(root);
        final boolean recursiveResult = isBinarySearchTreeRecursive(root);
        assertTrue(iterativeResult);
        assertTrue(recursiveResult);
    }

    @Test
    public void outOfOrderLinkedListTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        root.insertRight(70).insertRight(60).insertRight(80);
        final boolean iterativeResult = isBinarySearchTreeIterative(root);
        final boolean recursiveResult = isBinarySearchTreeRecursive(root);
        assertFalse(iterativeResult);
        assertFalse(recursiveResult);
    }

    @Test
    public void oneNodeTreeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final boolean iterativeResult = isBinarySearchTreeIterative(root);
        final boolean recursiveResult = isBinarySearchTreeRecursive(root);
        assertTrue(iterativeResult);
        assertTrue(recursiveResult);
    }
}
