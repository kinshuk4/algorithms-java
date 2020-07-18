package com.vaani.dsa.algo.ds.list.linked;

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
 * <p>
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->2->3->4->5->NULL
 * Output: 1->3->5->2->4->NULL
 * <p>
 * Example 2:
 * <p>
 * Input: 2->1->3->5->6->4->7->NULL
 * Output: 2->3->6->7->1->5->4->NULL
 */

import com.vaani.dsa.ds.core.list.sll.simple.ListNode;

import static com.vaani.dsa.ds.utils.simple.ListUtil.getListFromString;

public class OddEvenLinkedList {
    public static void main(String[] args) {
        ListNode l = getListFromString("1 2 3 4 5");
        ListNode l2 = new OddEvenLinkedList().oddEvenList(l);
        l2.display();
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode odd = head, even = head.next, evenHead = even;

        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
