package com.vaani.dsa.ds.core.tree.binarytree.simple;


public class TreeLinkNode {
    public int val;
    public TreeLinkNode left;
    public TreeLinkNode right;
    public TreeLinkNode next;

    public TreeLinkNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(val);
        sb.append(", left=");
        if (this.left != null) {
            sb.append(left.val);
        } else {
            sb.append("null");
        }
        sb.append(", right=");
        if (this.right != null) {
            sb.append(right.val);
        } else {
            sb.append("null");
        }
        sb.append(", next=");
        if (this.next != null) {
            sb.append(next.val);
        } else {
            sb.append("null");
        }
        sb.append("]");
        return sb.toString();
    }
}
