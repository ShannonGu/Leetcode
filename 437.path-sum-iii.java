import java.util.Map;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode id=437 lang=java
 *
 * [437] Path Sum III
 */

// @lc code=start
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
    // https://www.jiuzhang.com/solution/path-sum-iii
    public int pathSum(TreeNode root, int sum) {
        if(root == null)
            return 0;
        return helper(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    // 以当前点为起点，和为sum的路径个数
    private int helper(TreeNode node, int sum) {
        if(node == null)
            return 0;
        int res = 0;
        if (sum == node.val) {
            res += 1;
        }
        res += helper(node.left, sum - node.val);
        res += helper(node.right, sum - node.val);
        return res;
    }
}
// @lc code=end

