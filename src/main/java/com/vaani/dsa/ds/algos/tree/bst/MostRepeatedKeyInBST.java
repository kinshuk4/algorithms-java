package com.vaani.dsa.ds.algos.tree.bst;

import java.util.HashMap;
import java.util.Map;

import com.vaani.dsa.ds.core.tree.binarytree.generic.BinaryTreeNode;

/* class BinaryTreeNode { */
/*   int val; */
/*   BinaryTreeNode left, right; */
/*   public BinaryTreeNode(int val) { */
/*     this.val = val; */
/*   } */
/* } */

class BST {
    private BinaryTreeNode root;

    public void insert(int val) {
        if (root == null) {
            root = new BinaryTreeNode(val);
        } else {
            insert(root, val);
        }
    }

    private BinaryTreeNode insert(BinaryTreeNode<Integer> node, int val) {
        if (node == null) {
            return new BinaryTreeNode(val);
        } else if (node.value <= val) {
            node.right = insert(node.right, val);
        } else {
            node.left = insert(node.left, val);
        }
        return node;
    }

    public BinaryTreeNode root() {
        return root;
    }

}

public class MostRepeatedKeyInBST {

    public int mostRepeatedBST(BST bst) {
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        mostRepeatedBST(bst.root(), counts);
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            max = Math.max(max, entry.getValue());
        }
        return max;
    }

    private void mostRepeatedBST(BinaryTreeNode<Integer> node, Map<Integer, Integer> counts) {
        if (node == null) {
            return;
        } else {
            Integer count = counts.get(node.value);
            if (count == null) {
                counts.put(node.value, 1);
            } else {
                counts.put(node.value, count + 1);
            }
            mostRepeatedBST(node.left, counts);
            mostRepeatedBST(node.right, counts);
        }
    }

}

