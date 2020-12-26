/*
 * @lc app=leetcode id=889 lang=java
 *
 * [889] Construct Binary Tree from Preorder and Postorder Traversal
 */

// @lc code=start
class Solution {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre.length != post.length)
            return null;
        map = new HashMap<>();
        for (int i = 0; i < post.length; i++) {
            map.put(post[i], i);
        }
        return helper(pre, 0, pre.length - 1, post, 0, post.length - 1);
    }

    private Map<Integer, Integer> map;

    // 参数含义:
    // preL、preR分别表示当前子树在先序序列中的起始位置
    // postL、postR分别表示当前子树在后序序列中的起始位置
    private TreeNode helper(int[] pre, int preL, int preR, int[] post, int postL, int postR) {
        if (preL > preR || postL > postR)
            return null;
        // 根节点
        int rootVal = pre[preL];
        TreeNode root = new TreeNode(rootVal);
        // 当前子树只有一个节点
        if (preL == preR)
            return root;
        // 左子树的第一个节点也即左子树的根节点
        int idx = map.get(pre[preL + 1]);
        // 左子树的长度
        int leftLen = idx - postL + 1;
        root.left = helper(pre, preL + 1, preL + leftLen, post, postL, idx);
        root.right = helper(pre, preL + 1 + leftLen, preR, post, idx + 1, postR - 1);
        return root;
    }
}
// @lc code=end
