/*
 * @lc app=leetcode id=114 lang=java
 *
 * [114] Flatten Binary Tree to Linked List
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
    //https://www.cnblogs.com/grandyang/p/4293853.html
    // public void flatten(TreeNode root) {
    //     if (root == null)
    //         return;
    //     if (root.left != null)
    //         flatten(root.left);
    //     if (root.right != null)
    //         flatten(root.right);
    //     TreeNode tmp = root.right;
    //     root.right = root.left;
    //     root.left = null;
    //     while (root.right != null)
    //         root = root.right;
    //     root.right = tmp;
    // }
    
    public void flatten(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode p = cur.left;
                while (p.right != null)
                    p = p.right;
                p.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }
}
// @lc code=end

