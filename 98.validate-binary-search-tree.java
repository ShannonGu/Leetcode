/*
 * @lc app=leetcode id=98 lang=java
 *
 * [98] Validate Binary Search Tree
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
    //https://www.cnblogs.com/grandyang/p/4298435.html
    // public boolean isValidBST(TreeNode root) {
    //     pre = null;
    //     return inorder(root);
    // }
    
    // public boolean inorder(TreeNode root) {
    //     if (root == null)
    //         return true;
    //     boolean left = inorder(root.left);
    //     if (left == false)
    //         return false;
    //     if (pre != null) {
    //         if (pre.val >= root.val)
    //             return false;
    //     }
    //     pre = root;
    //     return inorder(root.right);
    // }

    // private TreeNode pre;


    public boolean isValidBST(TreeNode root) {
        if(root == null)
            return true;
        TreeNode cur = root, pre = null, last = null;
        boolean res = true;
        while (cur != null) {
            if (cur.left == null) {
                if (last != null && last.val >= cur.val) {
                    res = false;
                    break;
                }
                last = cur;
                cur = cur.right;
            } else {
                pre = cur.left;
                while (pre.right != null && pre.right != cur)
                    pre = pre.right;
                    
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right = null;
                    if (last.val >= cur.val) {
                        res = false;
                        break;
                    }
                    last = cur;
                    cur = cur.right;
                }
            }
        }
        return res;
    }
}

