/*
 * @lc app=leetcode id=94 lang=java
 *
 * [94] Binary Tree Inorder Traversal
 */
class Solution {
    // Morris遍历
    // public List<Integer> inorderTraversal(TreeNode root) {
    // List<Integer> res = new ArrayList<>();
    // TreeNode cur = root, pre = null;
    // while (cur != null) {
    // if (cur.left == null) {
    // res.add(cur.val);
    // pre = cur;
    // cur = cur.right;
    // } else {
    // pre = cur.left;
    // while (pre.right != null && pre.right != cur)
    // pre = pre.right;
    // if (pre.right == null) {
    // pre.right = cur;
    // cur = cur.left;
    // } else {
    // pre.right = null;
    // res.add(cur.val);
    // pre = cur;
    // cur = cur.right;
    // }
    // }
    // }
    // return res;
    // }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        TreeNode p = root;

        while (!st.isEmpty() || p != null) {
            if (p != null) {
                st.push(p);
                p = p.left;
            } else {
                p = st.pop();
                res.add(p.val);
                p = p.right;
            }
        }
        return res;
    }
}
