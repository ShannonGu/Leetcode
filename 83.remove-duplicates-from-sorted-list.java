/*
 * @lc app=leetcode id=83 lang=java
 *
 * [83] Remove Duplicates from Sorted List
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return head;
        ListNode fir = head, sec = head.next;
        while (sec != null) {
            while (sec != null && sec.val == fir.val)
                sec = sec.next;
            fir.next = sec;
            fir = sec;
            if (sec != null)
                sec = sec.next;
        }
        return head;
    }
}
