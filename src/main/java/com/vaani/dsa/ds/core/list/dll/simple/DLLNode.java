package com.vaani.dsa.ds.core.list.dll.simple;

public class DLLNode {
    public int val;
    public DLLNode next;
    public DLLNode prev;

    public DLLNode(int x) {
        this.val = x;
    }

    @Override
    public String toString() {
        return "" + this.val;
    }

    public void display() {
        DLLNode head = this;
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
