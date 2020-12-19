/*
 * @lc app=leetcode id=61 lang=java
 *
 * [61] Rotate List
 *
 * https://leetcode.com/problems/rotate-list/description/
 *
 * algorithms
 * Medium (27.14%)
 * Total Accepted:    192.4K
 * Total Submissions: 709K
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * Given a linked list, rotate the list to the right by k places, where k is
 * non-negative.
 * 
 * Example 1:
 * 
 * 
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 0->1->2->NULL, k = 4
 * Output: 2->0->1->NULL
 * Explanation:
 * rotate 1 steps to the right: 2->0->1->NULL
 * rotate 2 steps to the right: 1->2->0->NULL
 * rotate 3 steps to the right: 0->1->2->NULL
 * rotate 4 steps to the right: 2->0->1->NULL
 * 
 */
/**
 * Definition for singly-linked list.
 * public class ListNode { 
 *      int val; 
 *      ListNode next; 
 *      ListNode(int x) { val = x; } 
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0)
            return head;
        ListNode node = head;
        int cnt = 1;

        //先统计链表长度;
        while (node.next != null) {
            ++cnt;
            node = node.next;
        }

        //将最后一个结点接上头结点;
        node.next = head;
        //k%cnt表示从右开始数第几个是最终的头结点
        //前面cnt-k%cnt个结点会被移动到后面;
        k = cnt - k % cnt;

        //寻找最终的最后一个结点;
        while (k > 0) {
            --k;
            node = node.next;
        }

        //tmp为最终的头结点;
        ListNode newHead = node.next;
        node.next = null;
        return newHead;
    }
}
