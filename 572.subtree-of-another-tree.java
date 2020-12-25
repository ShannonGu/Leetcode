/*
 * @lc app=leetcode id=572 lang=java
 *
 * [572] Subtree of Another Tree
 */

// @lc code=start
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null)
            return false;
        // 若s和t相同
        if (isSame(s, t))
            return true;
        // 递归判断t是否是s的左子树或者右子树的子树
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    // 判断两棵树是否相同
    private boolean isSame(TreeNode s, TreeNode t) {
        if (s == null && t == null)
            return true;
        if (s == null || t == null)
            return false;
        if (s.val != t.val)
            return false;
        return isSame(s.left, t.left) && isSame(s.right, t.right);
    }
}
// @lc code=end
