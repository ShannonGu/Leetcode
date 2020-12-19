/*
 * @lc app=leetcode id=110 lang=java
 *
 * [110] Balanced Binary Tree
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

    //https://www.cnblogs.com/grandyang/p/4045660.html
    public boolean isBalanced(TreeNode root) {
        if(helper(root) == -1)
            return false;
        return true;
    }
    

    //对于每一个节点，计算其左右子树的深度,如果子树是平衡的，则返回其真实的深度,
    //若不平衡，直接返回-1;
    private int helper(TreeNode root) {
        if(root == null)
            return 0;
            
        int lHeight = helper(root.left);
        if(lHeight == -1)
            return -1;
        int rHeight = helper(root.right);
        if(rHeight == -1)
            return -1;

        int diff = Math.abs(lHeight - rHeight);
        if(diff > 1)
            return -1;
        return 1 + Math.max(lHeight, rHeight);
    }
}
// @lc code=end

