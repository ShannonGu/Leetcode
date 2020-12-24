/*
 * @lc app=leetcode id=24 lang=java
 *
 * [24] Swap Nodes in Pairs
 */
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        dummy.next = head;
        ListNode slow = head, fast = head.next;
        while (slow != null && fast != null) {
            slow.next = fast.next;
            fast.next = pre.next;
            pre.next = fast;
            pre = slow;
            slow = slow.next;
            if (slow != null)
                fast = slow.next;
        }
        return dummy.next;
    }
}
