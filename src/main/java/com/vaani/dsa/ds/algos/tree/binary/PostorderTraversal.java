package com.vaani.dsa.ds.algos.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.generic.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * <p>
 * For example:
 * Given binary tree {1,#,2,3},
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * return [3,2,1].
 * <p>
 *  on 8/7/2014.
 */
public class PostorderTraversal {
    public static void main(String[] args) {
        PostorderTraversal test = new PostorderTraversal();
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);

        System.out.println(test.postorderTraversal(root));
    }

//    public static void postOrder(Node root) {
//        if(root == null){
//            return;
//        }
//
//
//        postOrder(root.left);
//        postOrder(root.right);
//        System.out.print(""+ root.data+ " ");
//    }

    public List<Integer> postorderTraversal(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) return result;

        Stack<BinaryTreeNode> tmpStack = new Stack<BinaryTreeNode>();
        Stack<BinaryTreeNode<Integer>> outStack = new Stack<BinaryTreeNode<Integer>>();
        tmpStack.push(root);

        while (!tmpStack.isEmpty()) {
            BinaryTreeNode<Integer> node = tmpStack.pop();
            outStack.push(node);
            if (node.left != null) tmpStack.add(node.left);
            if (node.right != null) tmpStack.add(node.right);
        }

        while (!outStack.isEmpty()) {
            result.add(outStack.pop().value);
        }
        return result;
    }
}
