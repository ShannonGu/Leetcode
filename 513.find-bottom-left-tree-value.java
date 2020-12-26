/*
 * @lc app=leetcode id=513 lang=java
 *
 * [513] Find Bottom Left Tree Value
 */

// @lc code=start
class Solution {
    // public int findBottomLeftValue(TreeNode root) {
    // if (root == null)
    // return -1;
    // // 利用堆从右向左遍历
    // Queue<TreeNode> q = new LinkedList<>();
    // q.offer(root);
    // TreeNode node = null;
    // while (!q.isEmpty()) {
    // node = q.poll();
    // if (node.right != null)
    // q.offer(node.right);
    // if (node.left != null)
    // q.offer(node.left);
    // }
    // return node.val;
    // }

    public int findBottomLeftValue(TreeNode root) {
        if (root == null)
            return -1;
        res = root.val;
        maxDepth = 1;
        helper(root, 1);
        return res;
    }

    private int res, maxDepth;

    private void helper(TreeNode root, int depth) {
        if (root == null)
            return;
        // 先序遍历
        // 左子树总比右子树先遍历到，于是先更新maxDepth
        if (depth > maxDepth) {
            maxDepth = depth;
            res = root.val;
        }
        helper(root.left, depth + 1);
        helper(root.right, depth + 1);
    }
}
// @lc code=end
