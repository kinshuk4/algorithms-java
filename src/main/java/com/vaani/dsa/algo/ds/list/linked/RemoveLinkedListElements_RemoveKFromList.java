package com.vaani.dsa.algo.ds.list.linked;

import com.vaani.dsa.ds.core.list.sll.simple.ListNode;

public class RemoveLinkedListElements_RemoveKFromList {
    static ListNode removeKFromList(ListNode head, int k) {
        if(head == null){
            return null;
        }


        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            if (curr.val == k && prev == null) {
                head = head.next;
                curr = head;
                continue;
            }

            // Not checking head any more
            if (curr.val == k) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }

            curr = curr.next;

        }

        return head;
    }

}
