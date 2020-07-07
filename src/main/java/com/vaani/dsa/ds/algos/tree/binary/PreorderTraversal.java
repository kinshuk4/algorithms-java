package com.vaani.dsa.ds.algos.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.generic.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * <p>
 * For example:
 * Given binary tree {1,#,2,3},
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * return [1,2,3].
 * <p>
 *  on 8/7/2014.
 */
public class PreorderTraversal {
    List<Integer> result = new ArrayList<Integer>();

    public static void main(String[] args) {
        PreorderTraversal test = new PreorderTraversal();
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);

        System.out.println(test.preorderTraversal(root));
    }

    public List<Integer> preorderTraversal(BinaryTreeNode root) {
        if (root == null) return result;
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode<Integer> node = stack.pop();
            result.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return result;
    }

//    static void Recursive(Node root) {
//        if(root == null){
//            return;
//        }
//
//        System.out.print(""+ root.data+ " ");
//        preOrder(root.left);
//        preOrder(root.right);
//    }
}
