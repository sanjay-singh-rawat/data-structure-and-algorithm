package com.learndatastructure.dp.tree;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Find maximum path sum from any node to any node in a tree.
 * Here we need to find max path sum from one node to another, not necessarily from leaf to other leaf.
 * In the induction step, we need to check that:
 * If left and right result is negative, in that case we will return UP the current value.
 * int tempAnswer = max(max(left, right) + node.val, node.val).
 *
 * @author Sanjay Singh Rawat
 */
public class MaxPathSumAnyNode {

    public int maxPathSum(TreeNode node) {
        AtomicInteger maxSum = new AtomicInteger(0);
        return maxPathSum(node, maxSum);
    }

    private int maxPathSum(TreeNode node, AtomicInteger maxSum) {
        if (node == null) {
            return 0;
        }

        int left = maxPathSum(node.left, maxSum);
        int right = maxPathSum(node.right, maxSum);

        // if left or right result are negative, we need to return current value up to the caller.
        // we need to check the maximum of left and right plus current value with current value.
        int temp = Math.max(node.val + Math.max(left, right), node.val);
        int ans = Math.max(temp, node.val + left + right);
        maxSum.set(Math.max(maxSum.get(), ans));
        return temp;
    }
}
