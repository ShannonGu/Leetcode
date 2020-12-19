import java.util.LinkedList;
import java.util.Queue;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode id=107 lang=java
 *
 * [107] Binary Tree Level Order Traversal II
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        List<List<Integer>> res = new ArrayList<>();
        while (!q.isEmpty()) {
            int len = q.size();
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < len; ++i) {
                TreeNode t = q.poll();
                tmp.add(t.val);
                if (t.left != null)
                    q.offer(t.left);
                if (t.right != null)
                    q.offer(t.right);
            }
            res.add(0, tmp);
        }
        return res;
    }
}
// @lc code=end
