/*
 * @lc app=leetcode id=24 lang=java
 *
 * [24] Swap Nodes in Pairs
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
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode slow = head, fast = head.next;
        while (slow != null && fast != null) {
            slow.next = fast.next;
            fast.next = pre.next;
            pre.next = fast;
            pre = slow;
            if(slow.next != null)
                slow = slow.next;
            fast = slow.next;
        }
        return dummy.next;
    }
}

