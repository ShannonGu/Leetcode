/*
 * @lc app=leetcode id=148 lang=java
 *
 * [148] Sort List
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/4249905.html
    // 递归
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

    // merge迭代写法
    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return dummy.next;
    }

    // merge递归写法
    // public ListNode merge(ListNode l1, ListNode l2) {
    // if (l1 == null)
    // return l2;
    // if (l2 == null)
    // return l1;
    // if (l1.val < l2.val) {
    // l1.next = merge(l1.next, l2);
    // return l1;
    // } else {
    // l2.next = merge(l1, l2.next);
    // return l2;
    // }
    // }
}
