/*
 * @lc app=leetcode id=404 lang=java
 *
 * [404] Sum of Left Leaves
 */

// @lc code=start
class Solution {
    private int res;

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null)
            return 0;
        res = 0;
        helper(root.left, true);
        helper(root.right, false);
        return res;
    }

    private void helper(TreeNode node, boolean isLeft) {
        if (node == null)
            return;
        if (isLeft && node.left == null && node.right == null) {
            res += node.val;
        }
        helper(node.left, true);
        helper(node.right, false);
    }
}
// @lc code=end
