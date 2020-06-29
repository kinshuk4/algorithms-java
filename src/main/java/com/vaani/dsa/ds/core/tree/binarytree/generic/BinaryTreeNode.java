package com.vaani.dsa.ds.core.tree.binarytree.generic;

import java.util.*;


public class BinaryTreeNode<T> {
    public BinaryTreeNode<T> left;
    public BinaryTreeNode<T> right;
    public T value;
    public boolean isVisited;

    public BinaryTreeNode(T data) {
        this.value = data;
    }

    public BinaryTreeNode(BinaryTreeNode<T> left, T data, BinaryTreeNode<T> right) {
        this.left = left;
        this.value = data;
        this.right = right;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public String detailedToString() {
        String result = detailedToStringHelper(this);
        return result;
    }

    //Using the logic from here : http://stackoverflow.com/questions/20489834/binary-search-tree-recursive-tostring
    private String detailedToStringHelper(BinaryTreeNode root) {
        StringBuilder builder = new StringBuilder();
        if (root == null)
            return "";
        builder.append(detailedToStringHelper(root.left));
        builder.append(detailedToStringHelper(root.right));
        return builder.append(String.valueOf(root.value)).toString();
    }


    public void display() {
        System.out.println(this.detailedToString());
    }

    public int size() {
        int size = 0;
        if (left != null) {
            size += left.size();
        }
        if (right != null) {
            size += right.size();
        }
        return size + 1;
    }


    public String toString2() {
        return "Tree{" +
                "value=" + value +
                ", left=" + (left != null ? left.value + "..." : "null") +
                ", right=" + (right != null ? right.value + "..." : "null") +
                '}';
    }

     boolean equalTrees(BinaryTreeNode<T> t1, BinaryTreeNode<T> t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        return Objects.equals(t1.value, t2.value)
                && equalTrees(t1.left, t2.left)
                && equalTrees(t1.right, t2.right);
    }


}
