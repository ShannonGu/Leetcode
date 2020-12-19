/*
 * @lc app=leetcode id=101 lang=java
 *
 * [101] Symmetric Tree
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
    public boolean isSymmetric(TreeNode root) {
        return judge(root, root);
    }

    public boolean judge(TreeNode p, TreeNode q) {
        if(p == null && q ==null)
            return true;
        if(p == null || q == null)
            return false;
        if(p.val != q.val)
            return false;
        return judge(p.left, q.right) && judge(p.right, q.left);
    }
}

