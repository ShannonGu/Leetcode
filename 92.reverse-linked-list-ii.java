/*
 * @lc app=leetcode id=92 lang=java
 *
 * [92] Reverse Linked List II
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy, p = head, q = p;
        int cnt = 1;
        while (cnt < m && p != null) {
            pre = p;
            p = p.next;
            q = p;
            ++cnt;
        }
        
        while (cnt < n && p != null) {
            p = p.next;
            ++cnt;
        }

        if (p != null) {
            pre.next = p.next;
            p.next = null;
            p = q;
        }
        while (p != null) {
            q = p.next;
            p.next = pre.next;
            pre.next = p;
            p = q;
        }
        return dummy.next;
    }
}

