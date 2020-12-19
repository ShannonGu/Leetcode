/*
 * @lc app=leetcode id=99 lang=java
 *
 * [99] Recover Binary Search Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/4298069.html
    // Morris遍历
    public void recoverTree(TreeNode root) {
        TreeNode first = null, second = null, last = null, pre = null;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                if (last != null && last.val > cur.val) {
                    if (first == null)
                        first = last;
                    second = cur;
                }
                last = cur;
                cur = cur.right;
            } else {
                pre = cur.left;
                while (pre.right != null && pre.right != cur)
                    pre = pre.right;
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right = null;
                    if (last != null && last.val > cur.val) {
                        if (first == null)
                            first = last;
                        second = cur;
                    }
                    last = cur;
                    cur = cur.right;
                }
            }
        }
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }
}
// @lc code=end
