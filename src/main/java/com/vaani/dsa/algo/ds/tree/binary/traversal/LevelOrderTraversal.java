package com.vaani.dsa.algo.ds.tree.binary.traversal;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class LevelOrderTraversal {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        BinaryTreeNode l1 = new BinaryTreeNode(3);
        BinaryTreeNode l2 = new BinaryTreeNode(9);
        BinaryTreeNode l3 = new BinaryTreeNode(11);
        BinaryTreeNode r1 = new BinaryTreeNode(5);
        BinaryTreeNode r2 = new BinaryTreeNode(12);
        BinaryTreeNode r3 = new BinaryTreeNode(13);
        root.left = l1;
        root.right = r1;
        l1.left = l2;
        l2.left = l3;
        l3.right = r2;
        r2.right = r3;
        levelOrderTraversal(root);

        List traversal = levelOrderTraversal(root);
    }

    public static  List levelOrderTraversal(BinaryTreeNode root) {
        List result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<BinaryTreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BinaryTreeNode remove = queue.remove();
            result.add(remove.val);
            if (remove.left != null) {
                queue.add(remove.left);
            }
            if (remove.right != null) {
                queue.add(remove.right);
            }

        }
        return result;
    }


}