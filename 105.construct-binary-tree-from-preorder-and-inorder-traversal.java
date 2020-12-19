/*
 * @lc app=leetcode id=105 lang=java
 *
 * [105] Construct Binary Tree from Preorder and Inorder Traversal
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
    
    public TreeNode build(int[] preorder, int stPre, int edPre, int[] inorder, int stIn, int edIn) {
        if(preorder.length != inorder.length)
            return null;
        if(preorder.length == 1 && inorder.length == 1 && preorder[0] != inorder[0])
            return null;
        
        TreeNode root = null;
        int i = stIn;
        for (; i <= edIn; ++i) {
            if (inorder[i] == preorder[stPre]) {
                root = new TreeNode(inorder[i]);
                break;
            }
        }
        if(root == null)
            return null;
        int leftLen = i - stIn, rightLen = edIn - i;
        if(leftLen > 0)
            root.left = build(preorder, stPre + 1, stPre + leftLen, inorder, stIn, i - 1);
        if(rightLen > 0)
            root.right = build(preorder, stPre + leftLen + 1, edPre, inorder, i + 1, edIn);
        return root;
    }
}

