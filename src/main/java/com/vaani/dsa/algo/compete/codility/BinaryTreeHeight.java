package com.vaani.dsa.algo.compete.codility;


import com.vaani.dsa.ds.core.tree.binarytree.generic.BinaryTreeNode;

public class BinaryTreeHeight {
	public static int getTreeHeight(BinaryTreeNode<Integer> T) {
		if (T == null) {
			return -1;
		}
		return 1 + Math.max(getTreeHeight(T.left), getTreeHeight(T.right));
	}
}
