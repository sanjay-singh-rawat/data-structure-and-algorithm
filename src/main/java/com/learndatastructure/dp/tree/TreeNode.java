package com.learndatastructure.dp.tree;

import lombok.Getter;

/**
 * @author Sanjay Singh Rawat
 */
@Getter
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
