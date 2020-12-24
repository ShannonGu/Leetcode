/*
 * @lc app=leetcode id=104 lang=java
 *
 * [104] Maximum Depth of Binary Tree
 */

// @lc code=start
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int lH = maxDepth(root.left);
        int rH = maxDepth(root.right);
        return 1 + (lH > rH ? lH : rH);
    }
}
// @lc code=end
