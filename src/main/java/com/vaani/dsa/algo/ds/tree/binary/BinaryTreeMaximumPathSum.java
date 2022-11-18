package com.vaani.dsa.algo.ds.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

import java.util.ArrayList;

/**
 * Given a binary tree, find the maximum path sum.
 * <p>
 * The path may start and end at any node in the tree.
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 * <p>
 * For example:
 * Given the below binary tree,
 */
/*
Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42

 */
public class BinaryTreeMaximumPathSum {
    int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(20);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(40);
        root.left.right = new TreeNode(50);

        BinaryTreeMaximumPathSum pathSum = new BinaryTreeMaximumPathSum();
        System.out.println(pathSum.maxPathSum1(root));
    }

    public int maxPathSum1(TreeNode root) {
        dfs1(root);
        return max;
    }

    public int dfs1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int m = root.val;

        int left = dfs1(root.left);
        int right = dfs1(root.right);

        if (left > 0) m += left;
        if (right > 0) m += right;
        max = Math.max(m, max);

        return Math.max(left, right) > 0 ? root.val + Math.max(left, right) : root.val;
    }

    /**
     * My getTreeHeight, very similar to the first getTreeHeight
     */
    // very similar to post order - process leaves and then root
    // submitted
    public int dfs2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = dfs2(root.left);
        left = Math.max(left, 0);

        int right = dfs2(root.right);
        right = Math.max(right, 0);

        int total = left + right + root.val;
        max = Math.max(max, total);

        return root.val + Math.max(left, right); // current node is in path of max sum i.e. one of the children is missed
    }

    public int maxPathSum3(TreeNode root) {
        if (root == null) return 0;
        dfs3(root);
        return max;
    }

    private int dfs3(TreeNode root) {
        if (root == null) return 0;
        int left = dfs3(root.left);
        int right = dfs3(root.right);
        max = Math.max(max, root.val);
        max = Math.max(max, root.val + left);
        max = Math.max(max, root.val + right);
        max = Math.max(max, root.val + left + right);
        int leftVal = Math.max(root.val, root.val + left);
        int rightVal = Math.max(root.val, root.val + right);
        return Math.max(leftVal, rightVal);
    }


    public int maxPathSum4(com.vaani.dsa.ds.core.tree.binarytree.generic.BinaryTreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<Integer> curMax = new ArrayList<>(1);
        curMax.add(Integer.MIN_VALUE);
        subPath4(root, curMax);
        //getMaxNonAdjacentSum(root, curMax);
        return curMax.get(0);
    }

    public int subPath4(com.vaani.dsa.ds.core.tree.binarytree.generic.BinaryTreeNode<Integer> root, ArrayList<Integer> curMax) {
        if (root == null) return 0;
        int left = Math.max(0, subPath4(root.left, curMax));
        int right = Math.max(0, subPath4(root.right, curMax));
        int max = root.val + left + right;
        //use set(0, value) can't pass large test
        curMax.add(0, Math.max(curMax.get(0), max));
        //can only return one path (left or right) for recursion use
        return root.val + Math.max(left, right);
    }

    //doesn't pass all cases
    public int dfs5(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = dfs5(root.left);
        int right = dfs5(root.right);
        int allSum = left + right + root.val; // case when path is left, right and current root is parent of path

        int maxStraight = Math.max(Math.max(left, right) + root.val, root.val); // if children are -ve, ignore them
        int maxCaseVal = Math.max(maxStraight, allSum);
        max = Math.max(maxCaseVal, maxStraight);


        return maxStraight; // returning this as it make sure that current node is included in max path
    }
}
