package com.learndatastructure.dp.tree;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Given a tree root node, find out the diameter of the tree.
 * Take two leaf node, find number of nodes in between including leafs.
 * Number of nodes along the longest path between two leafs will be the diameter of a tree.
 * It is not necessary that diameter will pass from the root.
 * <p>
 * When we need to traverse a tree and then at each node if we need to do some operation that requires O(n) time then the total complexity would be O(n^2) in
 * such case use DP.
 * <p>
 * To solve the tree based recursive problems we need to follow three steps:
 * 1. Base condition
 * 2. Hypothesis
 * 3. Induction
 * <p>
 * Hypothesis: It will bring the answer from left subtree and right subtree. We do not worry how it will work, we know it will bring the answer.
 * Induction: Induction step is to use left answer and right answer and get parent node answer.
 *
 * @author Sanjay Singh Rawat
 */
public class DiameterOfTree {

    public int getDiameter(TreeNode node) {
        AtomicInteger diameter = new AtomicInteger(0);
        return getDiameter(node, diameter);
    }

    private int getDiameter(TreeNode node, AtomicInteger diameter) {
        // 1. base condition
        if (node == null) {
            return 0;
        }

        // 2. hypothesis
        int left = getDiameter(node.left, diameter);
        int right = getDiameter(node.right, diameter);

        // 3. induction

        // i. current node do not want to be the answer.
        // create the temp answer, it will pass this answer to the parent node.
        // while creating the temp answer add one to the maximum of left and right answer.
        int temp = 1 + Math.max(left, right);

        // ii. current node wants to be the answer.
        // so, add left and right answer and add 1 to it to be the answer.
        // answer will be the max of temp answer and current answer.
        int ans = Math.max(temp, 1 + left + right);

        // set the diameter by comparing previously set answer with current answer.
        // maximum of both will be the diameter.
        diameter.set(Math.max(diameter.get(), ans));

        // return temp answer to parent.
        return temp;
    }
}
