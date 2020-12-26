/*
 * @lc app=leetcode id=109 lang=java
 *
 * [109] Convert Sorted List to Binary Search Tree
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/4295618.html
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return new TreeNode(head.val);
        ListNode slow = head, fast = head, pre = slow;
        while (fast.next != null && fast.next.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // slow为中间节点,为根节点
        // fast此时指向右子树的第一个节点
        fast = slow.next;
        pre.next = null;
        TreeNode root = new TreeNode(slow.val);

        // 防止只有两个节点的情况
        // 此时head == slow
        if (head != slow)
            root.left = sortedListToBST(head);
        root.right = sortedListToBST(fast);
        return root;
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        return helper(head, null);
    }

    private TreeNode helper(ListNode head, ListNode tail) {
        if (head == tail)
            return null;
        ListNode slow = head, fast = head;
        while (fast.next != tail && fast.next.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = helper(head, slow);
        root.right = helper(slow.next, tail);
        return root;
    }
}
