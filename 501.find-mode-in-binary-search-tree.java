import java.util.List;

/*
 * @lc app=leetcode id=501 lang=java
 *
 * [501] Find Mode in Binary Search Tree
 */

// @lc code=start
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
    private List<Integer> res;
    private TreeNode pre = null;
    private int mx = 0, cnt = 1;
    public int[] findMode(TreeNode root) {
        res = new ArrayList<>();
        inOrder(root);
        int[] ans = new int[res.size()];
        for(int i = 0; i < res.size(); ++i)
            ans[i] = res.get(i);
        return ans;
    }

    private void inOrder(TreeNode node) {
        if(node == null)
            return;
        inOrder(node.left);
        if (pre != null) {
            cnt = (pre.val == node.val) ? cnt + 1 : 1;
        }
        if (cnt >= mx) {
            if(cnt > mx)
                res.clear();
            mx = cnt;
            res.add(node.val);
        }
        pre = node;
        inOrder(node.right);
    }
}
// @lc code=end

