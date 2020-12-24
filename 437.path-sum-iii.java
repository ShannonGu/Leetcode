import java.util.Map;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode id=437 lang=java
 *
 * [437] Path Sum III
 */

// @lc code=start
class Solution {
    // https://www.jiuzhang.com/solution/path-sum-iii
    public int pathSum(TreeNode root, int sum) {
        if (root == null)
            return 0;
        return helper(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    // 以当前点为起点，计算和为sum的路径个数
    private int helper(TreeNode root, int sum) {
        if (root == null)
            return 0;
        int cnt = 0;
        if (root.val == sum)
            cnt += 1;
        // 递归遍历左右子树
        cnt += helper(root.left, sum - root.val);
        cnt += helper(root.right, sum - root.val);
        return cnt;
    }
}
// @lc code=end
