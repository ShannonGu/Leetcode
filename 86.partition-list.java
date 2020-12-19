/*
 * @lc app=leetcode id=86 lang=java
 *
 * [86] Partition List
 *
 * https://leetcode.com/problems/partition-list/description/
 *
 * algorithms
 * Medium (37.24%)
 * Total Accepted:    163.9K
 * Total Submissions: 439.8K
 * Testcase Example:  '[1,4,3,2,5,2]\n3'
 *
 * Given a linked list and a value x, partition it such that all nodes less
 * than x come before nodes greater than or equal to x.
 * 
 * You should preserve the original relative order of the nodes in each of the
 * two partitions.
 * 
 * Example:
 * 
 * 
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 * 
 * 
 */
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/4321292.html#commentform
    public ListNode partition(ListNode head, int x) {
        if (head == null)
            return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;

        // 首先找到第一个>=x的结点;
        // pre指向它的前驱;
        while (pre.next != null && pre.next.val < x)
            pre = pre.next;
        ListNode cur = pre;
        // 从该结点开始，向后依次寻找<x的结点;
        while (cur.next != null) {
            if (cur.next.val < x) {
                // 找到一个则插入到第一个>=x的结点的前面;
                ListNode tmp = cur.next;
                cur.next = tmp.next;
                tmp.next = pre.next;
                pre.next = tmp;
                pre = pre.next;
            } else {
                // 未找到则继续向后遍历;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
