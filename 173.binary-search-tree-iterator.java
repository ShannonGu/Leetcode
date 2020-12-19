/*
 * @lc app=leetcode id=173 lang=java
 *
 * [173] Binary Search Tree Iterator
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// class BSTIterator {
//     List<TreeNode> tree;
//     int idx;
//     public BSTIterator(TreeNode root) {
//         tree = new ArrayList<>();
//         inorder(root);
//         idx = 0;
//     }

//     public void inorder(TreeNode root) {
//         if(root == null)
//             return;
//         inorder(root.left);
//         tree.add(root);
//         inorder(root.right);
//     }

//     /** @return the next smallest number */
//     public int next() {
//         if(idx < tree.size())
//             return tree.get(idx++).val;
//         return Integer.MIN_VALUE;
//     }

//     /** @return whether we have a next smallest number */
//     public boolean hasNext() {
//         if(idx < tree.size())
//             return true;
//         return false;
//     }
// }


class BSTIterator {
    Stack<TreeNode> st;

    public BSTIterator(TreeNode root) {
        st = new Stack<>();
        TreeNode p = root;
        while (p != null) {
            st.push(p);
            p = p.left;
        }
    }

    
    /** @return the next smallest number */
    public int next() {
        TreeNode cur = st.pop();
        int val = cur.val;
        cur = cur.right;
        while (cur != null) {
            st.push(cur);
            cur = cur.left;
        }
        return val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !st.isEmpty();
    }
}/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
