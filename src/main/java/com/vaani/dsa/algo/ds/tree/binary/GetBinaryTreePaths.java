package com.vaani.dsa.algo.ds.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.generic.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Get all the root-to-leaf paths of a binary tree.
 */
public class GetBinaryTreePaths {

    public List<List<Integer>> getAllPaths(BinaryTreeNode node) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        find(node, cur, res);
        return res;
    }

    private void find(BinaryTreeNode<Integer> node, List<Integer> cur, List<List<Integer>> res) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            List<Integer> copy = new ArrayList<>(cur);
            copy.add(node.val);
            res.add(copy);
        } else {
            cur.add(node.val);
            if (node.left != null) {
                find(node.left, cur, res);
            }
            if (node.right != null) {
                find(node.right, cur, res);
            }
            cur.remove(cur.size() - 1);
        }
    }



}
