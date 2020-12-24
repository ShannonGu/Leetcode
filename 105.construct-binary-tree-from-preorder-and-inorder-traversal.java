import java.util.Map;

/*
 * @lc app=leetcode id=105 lang=java
 *
 * [105] Construct Binary Tree from Preorder and Inorder Traversal
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || preorder.length != inorder.length)
            return null;
        // 使用哈希表预处理中序序列中每个节点值与位置的关系
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        // 传入的后三个参数分别为
        // 当前子树在中序序列中的起、始位置
        // 以及根节点在先序序列中的位置
        return build(preorder, 0, inorder.length - 1, 0);
    }

    private Map<Integer, Integer> map;

    private TreeNode build(int[] preorder, int stIn, int edIn, int idxPre) {
        if (stIn > edIn)
            return null;
        // 取根节点的值
        int rootVal = preorder[idxPre];
        // 取根节点在中序中的位置以及左子树的长度
        int idxIn = map.get(rootVal), leftLen = idxIn - stIn;
        TreeNode root = new TreeNode(rootVal);
        // 递归构建左子树
        root.left = build(preorder, stIn, idxIn - 1, idxPre + 1);
        // 递归构建右子树
        root.right = build(preorder, idxIn + 1, edIn, idxPre + leftLen + 1);

        return root;
    }
}
