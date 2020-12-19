/*
 * @lc app=leetcode id=147 lang=java
 *
 * [147] Insertion Sort List
 *
 * https://leetcode.com/problems/insertion-sort-list/description/
 *
 * algorithms
 * Medium (37.29%)
 * Total Accepted:    149.6K
 * Total Submissions: 401.1K
 * Testcase Example:  '[4,2,1,3]'
 *
 * Sort a linked list using insertion sort.
 * 
 * 
 * 
 * 
 * 
 * A graphical example of insertion sort. The partial sorted list (black)
 * initially contains only the first element in the list.
 * With each iteration one element (red) is removed from the input data and
 * inserted in-place into the sorted list
 * 
 * 
 * 
 * 
 * 
 * Algorithm of Insertion Sort:
 * 
 * 
 * Insertion sort iterates, consuming one input element each repetition, and
 * growing a sorted output list.
 * At each iteration, insertion sort removes one element from the input data,
 * finds the location it belongs within the sorted list, and inserts it
 * there.
 * It repeats until no input elements remain.
 * 
 * 
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
 * 
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
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy, cur = head;
        //pre总是指向头结点的前一个节点,
        //因为需要借助pre找到比当前要插入节点小的最后一个节点的位置
        //这里将pre.next置为null，是为了将head也统一考虑
        pre.next = null;
        while (cur != null) {
            ListNode tmp = cur.next;
            //pre.next指向第一个>=当前要插入节点
            //即pre是最后一个比当前要插入节点的小的节点
            while (pre.next != null && pre.next.val < cur.val) {
                pre = pre.next;
            }

            cur.next = pre.next;
            pre.next = cur;
            //pre还原到最初的位置
            pre = dummy;
            cur = tmp;
        }
        return dummy.next;
    }
}

