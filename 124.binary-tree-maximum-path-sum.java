/*
 * @lc app=leetcode id=124 lang=java
 *
 * [124] Binary Tree Maximum Path Sum
 */
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

    //https://www.cnblogs.com/grandyang/p/4280120.html
    public int maxPathSum(TreeNode root) {
        res = Integer.MIN_VALUE;
        helper(root);
        return res;
    }

    //返回以当前节点为父节点的path之和
    //用res记录全局最大值
    private int helper(TreeNode root) {
        if(root == null)
            return 0;
        int lPath = Math.max(helper(root.left), 0);
        int rPath = Math.max(helper(root.right), 0);
        res = Math.max(res, lPath + rPath + root.val);
        return Math.max(lPath, rPath) + root.val;
    }
    private int res;
}

