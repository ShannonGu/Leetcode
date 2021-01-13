import java.util.Stack;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode id=144 lang=java
 *
 * [144] Binary Tree Preorder Traversal
 */

// @lc code=start
class Solution {
    // public List<Integer> preorderTraversal(TreeNode root) {
    // if (root == null)
    // return new ArrayList<>();
    // List<Integer> res = new ArrayList<>();
    // Stack<TreeNode> st = new Stack<>();
    // TreeNode p = root;
    // while (!st.isEmpty() || p != null) {
    // if (p != null) {
    // st.push(p);
    // res.add(p.val);
    // p = p.left;
    // } else {
    // p = st.pop();
    // p = p.right;
    // }
    // }
    // return res;
    // }

    // Morris traversal
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        TreeNode cur = root, pre = null;
        while (cur != null) {
            // 左子树为空
            if (cur.left == null) {
                // 先遍历当前节点cur
                res.add(cur.val);
                pre = cur;
                // 再转到右子树进行遍历
                cur = cur.right;
            } else {// 否则
                // 将左子树最右下节点的右指针指向当前节点cur
                pre = cur.left;
                // 寻找最右下节点
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    // 指向cur
                    pre.right = cur;
                    // 先遍历根节点cur
                    res.add(cur.val);
                    // 再对左子树进行遍历
                    cur = cur.left;
                } else {
                    // 如果最右下节点右指针不为空
                    // 说明是先序遍历返回到了根节点，左子树已经全部遍历完成
                    // 断开连接
                    pre.right = null;
                    pre = cur;
                    // 转到右子树进行遍历
                    cur = cur.right;
                }
            }
        }
        return res;
    }
}
// @lc code=end
