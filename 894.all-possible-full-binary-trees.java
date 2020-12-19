/*
 * @lc app=leetcode id=894 lang=java
 *
 * [894] All Possible Full Binary Trees
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
class Solution {
    //https://www.cnblogs.com/grandyang/p/10952459.html
    //通过观察发现full binary trees的节点个数总为奇数
    //可以通过此性质进行剪枝
    //其次对于一个FBT，其根节点的左右子树也一定是FBT，所以左右子树的节点个数也是奇数
    //因此设N为FBT的节点个数，且N为奇数，N-1为左右子树的节点个数，N-1为偶数
    //设p为左子树的节点个数，q为右子树的节点个数,所以p+q=N-1;且p，q均为奇数
    //可以对左右子树进行递归，得到左右子树FBT情况,再和根节点进行搭配
    // public List<TreeNode> allPossibleFBT(int N) {
    //     if (N % 2 == 0)
    //         return new ArrayList<>();
    //     //仅有一个节点
    //     if (N == 1)
    //         return new ArrayList<>(Arrays.asList(new TreeNode(0)));
    //     List<TreeNode> res = new ArrayList<>();
    //     //i表示左子树的节点总数,N - 1 - i是右子树的节点总数
    //     //要确保均为奇数的情况，所以每次+2
    //     for (int i = 1; i < N; i += 2) {
    //         //递归得到左右子树的FBT情况
    //         List<TreeNode> l = allPossibleFBT(i), r = allPossibleFBT(N - i - 1);
    //         //从左右子树列表中取出左右子节点与根节点进行搭配
    //         for (TreeNode a : l) {
    //             for (TreeNode b : r) {
    //                 TreeNode root = new TreeNode(0);
    //                 root.left = a;
    //                 root.right = b;
    //                 res.add(root);
    //             }
    //         }
    //     }
    //     return res;
    // }


    //将节点总数与对应的可能的根节点映射
    Map<Integer, List<TreeNode>> m = new HashMap<>();

    public List<TreeNode> allPossibleFBT(int N) {
        if(N % 2 == 0)
            return new ArrayList<>();
        if(N == 1)
            return new ArrayList<>(Arrays.asList(new TreeNode(0)));
        if(m.containsKey(N))
            return m.get(N);
        List<TreeNode> res = new ArrayList();
        for (int i = 1; i < N; i += 2) {
            List<TreeNode> l = allPossibleFBT(i), r = allPossibleFBT(N - i - 1);
            for (TreeNode a : l) {
                for (TreeNode b : r) {
                    TreeNode root = new TreeNode(0);
                    root.left = a;
                    root.right = b;
                    res.add(root);
                }
            }
        }
        m.put(N, res);
        return res;
    }
}

