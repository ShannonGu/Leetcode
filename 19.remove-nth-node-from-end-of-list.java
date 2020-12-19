/*
 * @lc app=leetcode id=19 lang=java
 *
 * [19] Remove Nth Node From End of List
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || n == 0)
            return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = head, slow = head;
        ListNode pre = dummy;
        int cnt = 0;
        //先遍历到第n个节点
        while (cnt < n - 1 && fast != null) {
            fast = fast.next;
            ++cnt;
        }
        //此时fast指向的是第n个节点或者此时fast节点已经为空

        //若此时fast为空，说明n是大于链表节点个数的
        //直接返回整个链表即可
        if (fast == null)
            return dummy.next;
        
        //接着遍历到倒数第n个节点
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
            pre = pre.next;
        }
        //此时slow指向倒数第n个节点
        //pre指向前一个节点
        //将slow节点remove
        pre.next = slow.next;
        return dummy.next;
    }
}

