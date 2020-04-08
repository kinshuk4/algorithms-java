package com.vaani.ctci.chap4treegraph;

import com.vaani.dsa.ds.core.tree.BinaryTreeNode;

import java.util.ArrayList;

/**
 * You are given a binary tree in which each node contains a value. Design an
 * algorithm to print all paths which sum to a given value. Note that a path can
 * start or end anywhere in the tree.
 */
public class Question9 {

    public ArrayList<ArrayList<Integer>> pathSum(BinaryTreeNode root, int sum) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
        int curSum = 0;
        ArrayList<Integer> curPath = new ArrayList<Integer>();
        check(root, sum, curSum, curPath, paths);
        return paths;
    }

    private void check(BinaryTreeNode<Integer> node, int sum, int curSum,
                       ArrayList<Integer> curPath, ArrayList<ArrayList<Integer>> paths) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) { // leaf
            if (node.value + curSum == sum) { // equal to sum
                ArrayList<Integer> extPath = new ArrayList<Integer>(curPath);
                extPath.add(node.value);
                paths.add(extPath);
            }
        } else {
            int newSum = node.value + curSum;
            ArrayList<Integer> extPath = new ArrayList<Integer>(curPath);
            extPath.add(node.value);
            if (node.left != null) {
                check(node.left, sum, newSum, extPath, paths);
            }
            if (node.right != null) {
                check(node.right, sum, newSum, extPath, paths);
            }
        }
    }


}
