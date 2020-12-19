/*
 * @lc app=leetcode id=129 lang=java
 *
 * [129] Sum Root to Leaf Numbers
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
    private int res = 0;

    public int sumNumbers(TreeNode root) {
        count(root, 0);
        return res;
    }

    private void count(TreeNode root, int tmp) {
        if(root == null)
            return;
        tmp = tmp * 10 + root.val;

        if (root.left == null && root.right == null) {
            res += tmp;
            return;
        }

        count(root.left, tmp);
        count(root.right, tmp);
    }
}

