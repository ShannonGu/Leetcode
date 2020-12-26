import java.util.Map;

/*
 * @lc app=leetcode id=106 lang=java
 *
 * [106] Construct Binary Tree from Inorder and Postorder Traversal
 */

// @lc code=start
class Solution {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length)
            return null;
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return helper(postorder, 0, inorder.length - 1, postorder.length - 1);
    }

    private Map<Integer, Integer> map;

    // 后三个参数分别指当前子树在中序序列中的起、始位置以及根节点在后序序列中的位置
    private TreeNode helper(int[] postorder, int stIn, int edIn, int idxPost) {
        if (stIn > edIn)
            return null;
        int rootVal = postorder[idxPost];
        int idxIn = map.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        // 右子树长度
        int rightLen = edIn - idxIn;
        root.right = helper(postorder, idxIn + 1, edIn, idxPost - 1);
        root.left = helper(postorder, stIn, idxIn - 1, idxPost - 1 - rightLen);
        return root;
    }
}
// @lc code=end
