/*
 * @lc app=leetcode id=99 lang=java
 *
 * [99] Recover Binary Search Tree
 */

// @lc code=start
class Solution {
    // https://www.cnblogs.com/grandyang/p/4298069.html
    // Morris遍历
    public void recoverTree(TreeNode root) {
        // fir指向第一个错误节点，sec指向第二个错误节点
        // last指向中序遍历的上一个节点
        TreeNode cur = root, last = null, fir = null, sec = null;
        while (cur != null) {
            if (cur.left == null) {
                // 中序遍历的上一个节点大于当前节点
                if (last != null && last.val > cur.val) {
                    if (fir == null)
                        fir = last;
                    sec = cur;
                }
                last = cur;
                cur = cur.right;
            } else {
                TreeNode pre = cur.left;
                while (pre.right != null && pre.right != cur)
                    pre = pre.right;
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right = null;
                    if (last != null && last.val > cur.val) {
                        if (fir == null)
                            fir = last;
                        sec = cur;
                    }
                    last = cur;
                    cur = cur.right;
                }
            }
        }
        int tmp = fir.val;
        fir.val = sec.val;
        sec.val = tmp;
    }
}
// @lc code=end
