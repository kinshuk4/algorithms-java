package com.vaani.dsa.ds.algos.list.linked;

import com.vaani.dsa.ds.core.list.ListNode;
import com.vaani.dsa.ds.core.list.SinglyLinkedListNode;

//https://www.hackerrank.com/challenges/compare-two-linked-lists/problem
public class CompareLinkedList {
    static boolean compareLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        SinglyLinkedListNode curr1 = head1;
        SinglyLinkedListNode curr2 = head2;

        while(curr1 != null && curr2!=null){
            if(curr1.val != curr2.val){
                return false;
            }
            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        if(curr1 != null || curr2 != null){
            return false;
        }

        return true;

    }
}
