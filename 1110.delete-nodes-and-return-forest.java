import jdk.internal.agent.resources.agent;
import jdk.internal.jshell.tool.resources.l10n;

/*
 * @lc app=leetcode id=1110 lang=java
 *
 * [1110] Delete Nodes And Return Forest
 */

// @lc code=start
class Solution {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        val = new HashSet<>();
        for (int a : to_delete) {
            val.add(a);
        }
        res = new ArrayList<>();
        root = helper(root);
        // 判断根节点是否在删除列表中
        if (root != null)
            res.add(root);
        return res;
    }

    private Set<Integer> val;
    private List<TreeNode> res;

    private TreeNode helper(TreeNode root) {
        if (root == null)
            return root;

        root.left = helper(root.left);
        root.right = helper(root.right);
        if (val.contains(root.val)) {
            if (root.left != null)
                res.add(root.left);
            if (root.right != null)
                res.add(root.right);
            // 根节点置空
            root = null;
        }
        return root;
    }
}
// @lc code=end
