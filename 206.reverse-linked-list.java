/*
 * @lc app=leetcode id=206 lang=java
 *
 * [206] Reverse Linked List
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
    // public ListNode reverseList(ListNode head) {
    //     if(head == null || head.next == null)
    //         return head;
    //     ListNode dummy = new ListNode(-1);
    //     dummy.next = null;
    //     ListNode p = head;
    //     while (p != null) {
    //         ListNode q = p.next;
    //         p.next = dummy.next;
    //         dummy.next = p;
    //         p = q;
    //     }
    //     return dummy.next;
    // }

    public ListNode reverseList(ListNode head) {
        //空链表或者只有一个元素
        //直接返回
        if(head == null || head.next == null)
            return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}

