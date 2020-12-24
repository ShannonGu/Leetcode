/*
 * @lc app=leetcode id=110 lang=java
 *
 * [110] Balanced Binary Tree
 */

// @lc code=start
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (helper(root) == -1)
            return false;
        return true;
    }

    // 对于每一个节点，计算其左右子树的深度,如果子树是平衡的，则返回其真实的深度,
    // 若不平衡，直接返回-1;
    private int helper(TreeNode root) {
        if (root == null)
            return 0;
        int lH = helper(root.left);
        if (lH == -1)
            return -1;
        int rH = helper(root.right);
        if (rH == -1)
            return -1;
        int diff = Math.abs(lH - rH);
        if (diff > 1)
            return -1;
        return 1 + (lH > rH ? lH : rH);
    }
}
// @lc code=end
