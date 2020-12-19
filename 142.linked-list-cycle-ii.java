/*
 * @lc app=leetcode id=142 lang=java
 *
 * [142] Linked List Cycle II
 */
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    //证明:https://www.acwing.com/solution/LeetCode/content/241/
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null)
            return null;
        ListNode fir = head, sec = head;
        while (sec != null && sec.next != null) {
            fir = fir.next;
            sec = sec.next.next;
            if (fir == sec)
                break;
        }
        
        if (sec == null || sec.next == null)
            return null;
            
        fir = head;
        while (fir != sec) {
            fir = fir.next;
            sec = sec.next;
        }
        return fir;
    }
}

