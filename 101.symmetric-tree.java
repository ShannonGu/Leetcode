/*
 * @lc app=leetcode id=101 lang=java
 *
 * [101] Symmetric Tree
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return judge(root, root);
    }

    // 四步法
    // (1)如果两个子树都为空指针，则它们相等或对称
    // (2)如果两个子树只有一个为空指针，则它们不相等或不对称
    // (3)如果两个子树根节点的值不相等， 则它们不相等或不对称
    // (4)根据相等或对称要求，进行递归处理。
    public boolean judge(TreeNode p, TreeNode q) {
        // 同时为空
        if (p == null && q == null)
            return true;
        // 不同时为空
        if (p == null || q == null)
            return false;
        if (p.val != q.val)
            return false;
        return judge(p.left, q.right) && judge(p.right, q.left);
    }
}
