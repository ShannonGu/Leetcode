/*
 * @lc app=leetcode id=236 lang=java
 *
 * [236] Lowest Common Ancestor of a Binary Tree
 */
class Solution {
    // solution 1
    // https://www.cnblogs.com/grandyang/p/4641968.html
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 题目确定p,q一定存在二叉树中

        if (root == null || root == p || root == q)
            return root;

        // 有三种情况
        // 一是p,q分别在当前节点的左右子树中，则当前节点为LCA;
        // 二是p,q在当前节点左子树中，此时LCA为p，q中在左子树中较高的节点，
        // 或者是p,q在左子树中最下的父节点
        // 三是p,q在当前节点右子树中，与二类似

        // 若当前节点不是p或者q;
        // 则先在左子树寻找
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 若找到的节点不空，且既不是p也不是q
        // 说明情况二中的后一种,即p, q在左子树中有公共的祖先节点
        if (left != null && left != p && left != q)
            return left;

        // 不满足情况二的后一种，说明left可能为空，则p,q均在右子树中
        // left不空, p或q其中一个在左子树中, 再在右子树中寻找
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (right != null) {
            // 如果left, right均不空，说明是情况一,p,q分别在左右子树中
            // 则当前节点为LCA
            if (left != null)
                return root;
            // p,q在右子树中有公共的祖先节点
            if (right != p && right != q)
                return right;
        }

        // 以上均不满足，说明p,q都在相同的子树当中, 且p,q当中一个节点是另一个节点的祖先节点
        return left != null ? left : right;
    }

    // solution 2
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> st = new Stack<>();
        // 将每个节点与父节点建立映射
        Map<TreeNode, TreeNode> parents = new HashMap<>();
        // root的父节点设为null
        parents.put(root, null);
        st.push(root);
        while (!parents.containsKey(p) || !parents.containsKey(q)) {
            TreeNode node = st.pop();
            // 为每个节点与其父节点建立映射
            if (node.left != null) {
                parents.put(node.left, node);
                st.push(node.left);
            }
            if (node.right != null) {
                parents.put(node.right, node);
                st.push(node.right);
            }
        }

        // 自底向上存储其中一个节点的所有祖先节点
        Set<TreeNode> ancestors = new HashSet<>();
        while (p != null) {
            ancestors.add(p);
            p = parents.get(p);
        }

        // 再次自底向上遍历另一个节点的祖先节点
        // 寻找p的祖先节点中是否q的祖先节点
        while (!ancestors.contains(q)) {
            q = parents.get(q);
        }
        return q;
    }
}
