/*
 * @lc app=leetcode id=236 lang=java
 *
 * [236] Lowest Common Ancestor of a Binary Tree
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/4641968.html
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 题目确定p,q一定存在二叉树中

        if (root == null || root == p || root == q)
            return root;

        // 有三种情况
        // 一是p,q分别在当前节点的左右子树中，则当前节点为LCA;
        // 二是p,q在当前节点左子树中，此时LCA为p，q中在左子树中较高的节点，
        // 或者是p,q在左子树中最下的父节点
        // 三是p,q在当前节点右子树中，与二类似

        // 若当前节点不是p或者q;
        // 则先在左子树寻找
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 若找到的节点不空，且既不是p也不是q
        // 说明情况二中的后一种,即p, q在左子树中有公共的祖先节点
        if (left != null && left != p && left != q)
            return left;

        // 不满足情况二的后一种，说明left可能为空，则p,q均在右子树中
        // left不空, p或q其中一个在左子树中, 再在右子树中寻找
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 如果left, right均不空，说明是情况一,p,q分别在左右子树中
        // 则当前节点为LCA
        if (left != null && right != null)
            return root;

        // 以上均不满足，说明p,q都在相同的子树当中, 且p,q当中一个节点是另一个节点的祖先节点
        return left != null ? left : right;
    }
}
