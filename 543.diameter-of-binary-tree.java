import java.util.Map;

/*
 * @lc app=leetcode id=543 lang=java
 *
 * [543] Diameter of Binary Tree
 */
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        res = 0;
        height(root);
        return res;
    }

    private int res;

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int lH = height(root.left);
        int rH = height(root.right);
        res = Math.max(lH + rH, res);

        return 1 + (lH > rH ? lH : rH);
    }
}
