/*
 * @lc app=leetcode id=543 lang=java
 *
 * [543] Diameter of Binary Tree
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
    public int diameterOfBinaryTree(TreeNode root) {
        res = 0;
        map = new HashMap<>();
        height(root);
        return res;
    }

    private int height(TreeNode root) {
        if (root == null)
            return 0;
        if (map.containsKey(root))
            return map.get(root);
        int lH = height(root.left);
        int rH = height(root.right);
        res = Math.max(res, lH + rH);
        return (lH > rH ? lH : rH) + 1;
    }

    private int res;
    private Map<TreeNode, Integer> map;
}

