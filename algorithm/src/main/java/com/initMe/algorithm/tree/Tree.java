package com.initMe.algorithm.tree;

/**
 * @Description:
 * @Author: jiqing
 * @Date: 2022/2/16 8:42 PM
 **/
public class Tree {
    public static void main(String[] args) {
        printTree(TreeUtils.buildTree(), Order.pre);
        System.out.println();
        printTree(TreeUtils.buildTree(), Order.in);
        System.out.println();
        printTree(TreeUtils.buildTree(), Order.after);
    }

    public static void printTree(TreeNode treeNode, Order order) {
        if (null == treeNode) {
            return;
        }
        if (Order.pre.equals(order)) {
            System.out.print(treeNode.val + " ");
        }
        printTree(treeNode.left, order);
        if (Order.in.equals(order)) {
            System.out.print(treeNode.val + " ");
        }
        printTree(treeNode.right, order);
        if (Order.after.equals(order)) {
            System.out.print(treeNode.val + " ");
        }
    }

    enum Order {
        pre, in, after;
    }
}
