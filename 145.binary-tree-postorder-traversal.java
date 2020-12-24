/*
 * @lc app=leetcode id=145 lang=java
 *
 * [145] Binary Tree Postorder Traversal
 */

// @lc code=start
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null)
            return new ArrayList<Integer>();
        Stack<TreeNode> st = new Stack<>();
        List<Integer> res = new ArrayList<>();
        st.push(root);
        // cur指向当前栈顶节点，pre指向上一个遍历过的节点
        TreeNode cur = null, pre = null;
        while (!st.isEmpty()) {
            cur = st.peek();
            // 如果cur左孩子和右孩子均为空,则可以直接遍历
            // 或者cur存在左孩子和右孩子，且均已访问过(即pre不为空，且为cur的左孩子或者右孩子)
            // 则也可以直接遍历cur,然后出栈
            if ((cur.left == null && cur.right == null) || (pre != null && (pre == cur.left || pre == cur.right))) {
                res.add(cur.val);
                pre = cur;
                st.pop();
            } else {// 否则，
                // 若cur右孩子不空，先右孩子进栈，
                if (cur.right != null)
                    st.push(cur.right);
                // 左孩子不空，左孩子进栈
                // 这样保证出栈时，左孩子在右孩子前面被访问,左孩子和右孩子在根节点前面被访问
                if (cur.left != null)
                    st.push(cur.left);
            }
        }
        return res;
    }
}
// @lc code=end
