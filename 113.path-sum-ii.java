/*
 * @lc app=leetcode id=113 lang=java
 *
 * [113] Path Sum II
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
    // https://www.cnblogs.com/grandyang/p/4042156.html
    private List<List<Integer>> res;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null)
            return new ArrayList<>();
        res = new ArrayList<>();
        helper(root, sum, new ArrayList<>());
        return res;
    }
    
    private void helper(TreeNode root, int sum, List<Integer> out) {
        if(root == null)
            return;
        out.add(root.val);
        if (sum == root.val && root.left == null && root.right == null) {
            res.add(new ArrayList<>(out));
        }
        helper(root.left, sum - root.val, out);
        helper(root.right, sum - root.val, out);
        out.remove(out.size() - 1);
    }
}

