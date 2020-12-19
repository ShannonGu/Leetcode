/*
 * @lc app=leetcode id=530 lang=java
 *
 * [530] Minimum Absolute Difference in BST
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
    private int mn = Integer.MAX_VALUE;
    private TreeNode pre = null;

    public int getMinimumDifference(TreeNode root) {
        if(root == null)
            return 0;
        helper(root);
        return mn;
    }
    
    private void helper(TreeNode root) {
        if(root == null)
            return;
        helper(root.left);
        if (pre != null) {
            mn = Math.min(mn, Math.abs(pre.val - root.val));
        }
        pre = root;
        helper(root.right);
    }
}
// @lc code=end

