/*
 * @lc app=leetcode id=230 lang=java
 *
 * [230] Kth Smallest Element in a BST
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
    //https://leetcode.com/problems/kth-smallest-element-in-a-bst/solution/
    // TreeNode target = null;
    // private int cnt = 0;
    // public int kthSmallest(TreeNode root, int k) {
    //     if(root == null)
    //         return -1;
    //     inOrder(root, k);
    //     return target.val;
    // }

    // private void inOrder(TreeNode root, int k){
    //     if(root == null)
    //         return;
    //     inOrder(root.left, k);
    //     ++cnt;
    //     if(cnt == k){
    //         target = root;
    //         return;
    //     }
    //     inOrder(root.right, k);
    // }


    public int kthSmallest(TreeNode root, int k) {
        //先计算左子树的节点个数
        int cnt = count(root.left);
        if (k <= cnt) {
            return kthSmallest(root.left, k);
        }else if(k > cnt + 1)
            return kthSmallest(root.right, k - (cnt + 1));

        //k == cnt + 1;
        return root.val;
    }

    private int count(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + count(root.left) + count(root.right);
    }
    

    //https://www.cnblogs.com/grandyang/p/4620012.html
    //follow up 维护一个双向链表
}

