package com.vaani.dsa.algo.ds.tree.binary;


import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

import static com.vaani.dsa.algo.ds.tree.binary.IsMirrorTree.isMirror;

/**
 * https://leetcode.com/articles/symmetric-tree/
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 */

/*
For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3


But the following [1,2,2,null,3,null,3] is not:

    1
   / \
  2   2
   \   \
   3    3
 */
public class IsSymmetricTree {

    public static boolean isSymmetricRecursive(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isSymmetricDFSHelper(root.left, root.right);
    }

    private static boolean isSymmetricDFSHelper(TreeNode left, TreeNode right) {
        if (right == null && left == null)
            return true;
        else if (right == null || left == null)
            return false;
        // can be uncomment to shorten the code:
        // if(left == null || right==null){
        // return left == right;
        // }

        // Note using equals method and not == as otherwise for generic objects it checks equality of reference.
        return left.val == right.val && isSymmetricDFSHelper(left.left, right.right) && isSymmetricDFSHelper(left.right, right.left);

    }

    public static boolean isSymmetricRecursive2 (TreeNode root){
        return isMirror(root, root);
    }


    public boolean isSymmetricHelper2(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        } else if (n1 == null || n2 == null || n1.val != n2.val) {
            return false;
        } else {
            return isSymmetricHelper2(n1.left, n2.right) && isSymmetricHelper2(n1.right, n2.left);

        }
    }

    public static boolean isSymmetricIterative(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();

            if(t1 == null && t2 == null){
                continue;
            }

            if (t1 == null || t2 == null){
                return false;
            }

            if (t1.val != t2.val){
                return false;
            }

            queue.add(t1.left);
            queue.add(t2.right);
            queue.add(t1.right);
            queue.add(t2.left);
        }
        return true;
    }
}