/*
 * @lc app=leetcode id=687 lang=java
 *
 * [687] Longest Univalue Path
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/7636259.html
    private int res = 0;

    public int longestUnivaluePath(TreeNode root) {
        helper(root);
        return res;
    }
    
    private int helper(TreeNode node) {
        if(node == null)
            return 0;
        int l = helper(node.left);
        int r = helper(node.right);
        l = (node.left != null && node.left.val == node.val) ? l + 1 : 0;
        r = (node.right != null && node.right.val == node.val) ? r + 1 : 0;
        res = Math.max(res, l + r);
        return Math.max(l, r);
    }
}
// @lc code=end

