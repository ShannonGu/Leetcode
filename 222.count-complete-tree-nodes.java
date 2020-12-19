/*
 * @lc app=leetcode id=222 lang=java
 *
 * [222] Count Complete Tree Nodes
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
    public int countNodes(TreeNode root) {
        int hLeft = 0, hRight = 0;
        TreeNode pLeft = root, pRight = root;
        //先分别计算最左边和最右边路径的长度
        while (pLeft != null) {
            ++hLeft;
            pLeft = pLeft.left;
        }
        while (pRight != null) {
            ++hRight;
            pRight = pRight.right;
        }

        //若两者相等，说明是满二叉树
        if (hLeft == hRight)
            //则直接根据公式返回结果
            return (int) Math.pow(2, hLeft) - 1;
        //否则分别计算左子树和右子树节点个数,再加上根节点
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}

