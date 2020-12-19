import java.util.Map;

/*
 * @lc app=leetcode id=337 lang=java
 *
 * [337] House Robber III
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
    //https://www.cnblogs.com/grandyang/p/5275096.html
    // Map<TreeNode, Integer> m = new HashMap<>();

    // public int rob(TreeNode root) {
    //     return helper(root);
    // }

    // private int helper(TreeNode root) {
    //     if (root == null)
    //         return 0;
    //     if (m.containsKey(root))
    //         return m.get(root);

    //     int res = 0;
    //     if(root.left != null)
    //         res += helper(root.left.left) + helper(root.left.right);
    //     if(root.right != null)
    //         res += helper(root.right.left) + helper(root.right.right);
    //     res = Math.max(res + root.val, helper(root.left) + helper(root.right));
    //     m.put(root, res);
    //     return res;
    // }



    public int rob(TreeNode root) {
        int[]res = helper(root);
        return Math.max(res[0], res[1]);
    }
    
    //res[0]表示不包含当前节点值的最大值
    //res[1]表示包含当前值的最大值
    private int[] helper(TreeNode node) {
        if(node == null)
            return new int[] { 0, 0 };
        
        int[] l = helper(node.left);
        int[] r = helper(node.right);

        int[] res = new int[] { 0, 0 };
        res[1] = node.val;//res[1]包含当前节点的值
        
        res[0] = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        
        res[1] += l[0] + r[0];//再加上左右子树不含左右子节点的最大值
        return res;
    }
}

