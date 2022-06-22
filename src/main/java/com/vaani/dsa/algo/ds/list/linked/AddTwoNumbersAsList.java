package com.vaani.dsa.algo.ds.list.linked;

import com.vaani.dsa.ds.core.list.sll.simple.ListNode;

/**
 * You are given two linked lists representing two non-negative numbers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * <p>
 */
public class AddTwoNumbersAsList {

    static class UsingOrInLoop {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            int carry = 0;

            ListNode newHead = new ListNode(0);
            ListNode l3 = newHead;

            while (l1 != null || l2 != null) {
                if (l1 != null) {
                    carry += l1.val;
                    l1 = l1.next;
                }

                if (l2 != null) {
                    carry += l2.val;
                    l2 = l2.next;
                }

                l3.next = new ListNode(carry % 10);
                l3 = l3.next;
                carry /= 10;
            }

            if (carry > 0) {
                l3.next = new ListNode(carry);
            }

            return newHead.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode dummyHead = dummy;
        int extra = 0;
        while (l1 != null && l2 != null) {
            dummy.next = new ListNode((l1.val + l2.val + extra) % 10);
            extra = l1.val + l2.val + extra > 9 ? 1 : 0;
            dummy = dummy.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        ListNode n = l1 != null ? l1 : l2;
        while (n != null) {
            dummy.next = new ListNode((n.val + extra) % 10);
            extra = n.val + extra > 9 ? 1 : 0;
            n = n.next;
            dummy = dummy.next;
        }
        if (extra == 1) dummy.next = new ListNode(1);
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        addTwoNumbers(l1, l2).display();
    }
}
