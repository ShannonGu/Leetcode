/*
 * @lc app=leetcode id=234 lang=java
 *
 * [234] Palindrome Linked List
 */
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
    // solution1 递归
    // private ListNode cur;

    // public boolean isPalindrome(ListNode head) {
    //     cur = head;
    //     return helper(head);
    // }

    // private boolean helper(ListNode node) {
    //     if (node == null)
    //         return true;
    //     boolean res = helper(node.next) && (node.val == cur.val);
    //     cur = cur.next;
    //     return res;
    // }

    // solution2 使用栈，空间复杂度不为O(1)
    // public boolean isPalindrome(ListNode head) {
    //     if (head == null || head.next == null)
    //         return true;
    //     ListNode slow = head, fast = head;
    //     Stack<Integer> st = new Stack<>();
    //     st.push(head.val);
    //     while (fast.next != null && fast.next.next != null) {
    //         slow = slow.next;
    //         fast = fast.next.next;
    //         st.push(slow.val);
    //     }
    //     if (fast.next == null)
    //         st.pop();
    //     while (slow.next != null) {
    //         slow = slow.next;
    //         int tmp = st.pop();
    //         if (tmp != slow.val)
    //             return false;
    //     }
    //     return true;
    // }

    // solution3
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 将后半部分翻转
        ListNode last = slow.next, pre = head;
        while (last.next != null) {
            // 要防止tmp.next为空的情况
            // 条件是last.next判空
            ListNode tmp = last.next;
            last.next = tmp.next;
            tmp.next = slow.next;
            slow.next = tmp;
        }

        while (slow.next != null) {
            slow = slow.next;
            if (pre.val != slow.val)
                return false;
            pre = pre.next;
        }
        return true;
    }
}
