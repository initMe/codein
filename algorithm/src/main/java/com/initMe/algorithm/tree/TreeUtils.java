package com.initMe.algorithm.tree;

/**
 * @Description:
 * @Author: jiqing
 * @Date: 2022/2/16 8:23 PM
 **/
public class TreeUtils {
    public static TreeNode buildTree() {
        TreeNode tree = new TreeNode();
        tree.val = 15;
        TreeNode t1 = new TreeNode();
        t1.val = 10;
        TreeNode t1_1 = new TreeNode();
        t1_1.val = 8;
        t1.left = t1_1;
        TreeNode t1_2 = new TreeNode();
        t1_2.val = 13;
        t1.right = t1_2;
        TreeNode t2 = new TreeNode();
        t2.val = 18;
        TreeNode t2_1 = new TreeNode();
        t2_1.val = 16;
        t2.left = t2_1;

        tree.left = t1;
        tree.right = t2;
        return tree;
    }
}
