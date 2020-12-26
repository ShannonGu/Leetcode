import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode id=450 lang=java
 *
 * [450] Delete Node in a BST
 */

// @lc code=start
class Solution {
    // solution1 使用所有树
    // public TreeNode deleteNode(TreeNode root, int key) {
    // if (root == null)
    // return root;

    // if (root.val == key) {
    // if (root.right == null)
    // return root.left;
    // else {
    // TreeNode p = root.right;
    // while (p.left != null)
    // p = p.left;
    // // 与根节点交换值
    // // 再在右子树中删除这个节点
    // int tmp = p.val;
    // p.val = root.val;
    // root.val = tmp;
    // }
    // }
    // root.left = deleteNode(root.left, key);
    // // 在右子树中删除与根节点进行交换值后的节点
    // root.right = deleteNode(root.right, key);
    // return root;
    // }

    // 适用BST
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return root;
        if (root.val > key)
            root.left = deleteNode(root.left, key);
        else if (root.val < key)
            root.right = deleteNode(root.right, key);
        else {
            if (root.left == null || root.right == null)
                root = root.left == null ? root.right : root.left;
            else {
                TreeNode p = root.right;
                while (p.left != null)
                    p = p.left;
                root.val = p.val;
                root.right = deleteNode(root.right, p.val);
            }
        }
        return root;
    }

}
// @lc code=end
