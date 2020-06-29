package com.vaani.dsa.ds.algos.list.linked;

import com.vaani.dsa.ds.core.list.sll.generic.ListNode;

public class RemoveKFromList {
    static ListNode<Integer> removeKFromList(ListNode<Integer> l, int k) {
        ListNode<Integer> curr = l;
        ListNode<Integer> prev = null;
        while (curr != null) {
            if (curr.val == k && prev == null) {
                l = l.next;
                curr = l;
                continue;
            }

            if (curr.val == k && prev != null) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }

            curr = curr.next;

        }

        return l;
    }
}
