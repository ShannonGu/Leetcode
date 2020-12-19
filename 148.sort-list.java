/*
 * @lc app=leetcode id=148 lang=java
 *
 * [148] Sort List
 *
 * https://leetcode.com/problems/sort-list/description/
 *
 * algorithms
 * Medium (35.40%)
 * Total Accepted:    185.4K
 * Total Submissions: 523.7K
 * Testcase Example:  '[4,2,1,3]'
 *
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 
 * Example 1:
 * 
 * 
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 * 
 */
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4249905.html
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode slow = head, fast = head, pre = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        return merge(sortList(head), sortList(slow));
    }

    
    // 迭代写法;
    // public ListNode merge(ListNode l1, ListNode l2) {
    //     ListNode dummy = new ListNode(-1);
    //     ListNode cur = dummy;
    //     while (l1 != null && l2 != null) {
    //         if (l1.val < l2.val) {
    //             cur.next = l1;
    //             l1 = l1.next;
    //         } else {
    //             cur.next = l2;
    //             l2 = l2.next;
    //         }
    //         cur = cur.next;
    //     }
    //     if (l1 != null)
    //         cur.next = l1;
    //     if (l2 != null)
    //         cur.next = l2;
    //     return dummy.next;
    // }

    // 递归写法
    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }
}
