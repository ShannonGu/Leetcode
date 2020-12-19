/*
 * @lc app=leetcode id=199 lang=java
 *
 * [199] Binary Tree Right Side View
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
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null)
            return new ArrayList<>();
        //List模拟队列
        List<TreeNode> q = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        int head = 0, tail = -1;
        q.add(root);
        ++tail;
        while (head <= tail) {
            TreeNode tmp = q.get(head);
            res.add(tmp.val);
            int size = tail - head + 1;
            for (int i = 0; i < size; ++i) {
                tmp = q.get(head++);
                if (tmp.right != null) {
                    q.add(tmp.right);
                    ++tail;
                }
                if (tmp.left != null) {
                    q.add(tmp.left);
                    ++tail;
                }
            }
        }
        return res;
    }
}

