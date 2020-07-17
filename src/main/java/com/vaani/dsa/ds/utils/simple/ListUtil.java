package com.vaani.dsa.ds.utils.simple;

import com.vaani.dsa.ds.core.list.sll.simple.ListNode;

public class ListUtil {
    public static ListNode getListFromArray(int[] arr){
        ListNode head = new ListNode(arr[0]);
        ListNode curr = head;
        for (int i = 1; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            curr = curr.next;
        }
        return head;
    }

    public static ListNode getListFromString(String s){
        String[] arr = s.split(" ");
        ListNode head = new ListNode(Integer.parseInt(arr[0]));
        ListNode curr = head;
        for (int i = 1; i < arr.length; i++) {
            curr.next = new ListNode(Integer.parseInt(arr[i]));
            curr = curr.next;
        }
        return head;
    }
    public static ListNode getMidNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            slow = slow.next;
        }
        return slow;
    }
    public static ListNode getPointerToMidNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static int getLength(ListNode head) {
        ListNode curr = head;
        int length = 0;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        return length;
    }
}
