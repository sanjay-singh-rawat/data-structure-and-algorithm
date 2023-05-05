package com.learndatastructure.dp.tree;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Find maximum path sum from one leaf to another leaf node.
 *
 * @author Sanjay Singh Rawat
 */
public class MaxPathSumLeafNode {

    public int maxSum(TreeNode node) {
        AtomicInteger maxSum = new AtomicInteger(0);
        return maxSum(node, maxSum);
    }

    private int maxSum(TreeNode node, AtomicInteger maxSum) {
        if (node == null) {
            return 0;
        }

        int left = maxSum(node.left, maxSum);
        int right = maxSum(node.right, maxSum);

        int temp = node.val + Math.max(left, right);
        int ans = Math.max(temp, node.val + left + right);
        maxSum.set(Math.max(maxSum.get(), ans));
        return temp;
    }
}
