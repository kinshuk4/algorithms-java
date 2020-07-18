package com.vaani.dsa.algo.ds.list.linked;

import com.vaani.dsa.ds.core.list.sll.generic.ListNode;

public class NthNodeFromEndOfList {
    public static ListNode<Integer> removeNthFromEnd(ListNode<Integer> head, int n) {
        if (head == null) {
            return null;
        }

        ListNode<Integer> first = head, second = head;

        for (int i = 0; i < n; i++) {
            second = second.next;
        }

        if (second == null){
            return first.next;
        }

        while (second.next != null) {
            first = first.next;
            second = second.next;
        }

        return first;
    }

}
