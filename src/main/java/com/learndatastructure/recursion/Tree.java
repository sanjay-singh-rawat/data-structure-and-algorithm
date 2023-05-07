package com.learndatastructure.recursion;

import org.junit.jupiter.api.Assertions;

/**
 * @author Sanjay Singh Rawat
 */
public class Tree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        Assertions.assertEquals(0, getHeight(null));
        Assertions.assertEquals(1, getHeight(root));
        root.left = new TreeNode();
        root.right = new TreeNode();
        root.right.left = new TreeNode();

        Assertions.assertEquals(3, getHeight(root));
    }

    public static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);

        int height = 1 + Math.max(left, right);
        return height;
    }
}
