/*
 * @lc app=leetcode id=82 lang=java
 *
 * [82] Remove Duplicates from Sorted List II
 *
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/
 *
 * algorithms
 * Medium (33.04%)
 * Total Accepted:    182.1K
 * Total Submissions: 551.2K
 * Testcase Example:  '[1,2,3,3,4,4,5]'
 *
 * Given a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list.
 * 
 * Example 1:
 * 
 * 
 * Input: 1->2->3->3->4->4->5
 * Output: 1->2->5
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 1->1->1->2->3
 * Output: 2->3
 * 
 * 
 */
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return head;

        ListNode slow = head, fast = head.next;
        // pre指向重复结点前面的最后一个不重复结点;
        ListNode pre = new ListNode(-1);
        pre.next = slow;
        while (fast != null) {
            if (slow.val != fast.val) {
                pre = slow;
                slow = fast;
                fast = fast.next;
            } else {
                while (fast != null && slow.val == fast.val)
                    fast = fast.next;
                // 如果头结点也是重复元素，则需要寻找后面第一个不重复的结点作为头结点
                if (slow == head) {
                    slow = fast;
                    head = slow;
                } else
                    slow = fast;
                pre.next = slow;

                // 注意fast是否为空;
                if (fast != null)
                    fast = fast.next;
            }
        }
        return head;
    }
}
