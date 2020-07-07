package com.vaani.dsa.ds.algos.tree.binary;


import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

public class BinaryTreeHeight {
	public static int getTreeHeight(BinaryTreeNode root) {
		if (root == null) {
			return -1;
		}
		return 1 + Math.max(getTreeHeight(root.left), getTreeHeight(root.right));
	}
}
