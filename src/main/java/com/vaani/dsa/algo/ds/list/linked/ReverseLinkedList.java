package com.vaani.dsa.algo.ds.list.linked;

import com.vaani.dsa.ds.core.list.sll.generic.ListNode;

import static com.vaani.dsa.ds.utils.generic.ListUtil.reverseIterative;

public class ReverseLinkedList {



    public static void main(String[] args) {
        ListNode l = new ListNode(1);
        l.next = new ListNode(4);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(2);
        l.next.next.next.next = new ListNode(5);
        l.next.next.next.next.next = new ListNode(2);
        l.display();

        ListNode n = reverseIterative(l);
        n.display();
    }
}
