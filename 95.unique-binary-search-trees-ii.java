import java.util.ArrayList;
import java.util.Arrays;

/*
 * @lc app=leetcode id=95 lang=java
 *
 * [95] Unique Binary Search Trees II
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
    //https://www.cnblogs.com/grandyang/p/4301096.html
    // public List<TreeNode> generateTrees(int n) {
    //     if(n == 0)
    //         return new ArrayList<>();
    //     return helper(1, n);
    // }

    // List<TreeNode> helper(int st, int ed) {
    //     //空节点的情况
    //     if (st > ed) {
    //         TreeNode node = null;
    //         return new ArrayList<>(Arrays.asList(node));
    //     }
    //     List<TreeNode> res = new ArrayList<>();
    //     for (int i = st; i <= ed; ++i) {
    //         //依次将1~n中的每个数作为根，再分别递归求出左右子树的所有组合情况
    //         List<TreeNode> l = helper(st, i - 1), r = helper(i + 1, ed);
    //         //将左右子树中的每种情况与根节点进行搭配
    //         for (TreeNode a : l) {
    //             for (TreeNode b : r) {
    //                 TreeNode root = new TreeNode(i);
    //                 root.left = a;
    //                 root.right = b;
    //                 res.add(root);
    //             }
    //         }
    //     }
    //     return res;
    // }


    //记忆化递归
    //memo[i][j]表示[i,j]范围内可以生成的所有BST的根节点
    private List<TreeNode>[][] memo;

    public List<TreeNode> generateTrees(int n) {
        if (n == 0)
            return new ArrayList<>();
        memo = new ArrayList[n + 1][n + 1];
        //初始化
        for (List[] m : memo) {
            Arrays.fill(m, new ArrayList<TreeNode>());
        }
        return helper(1, n);
    }

    private List<TreeNode> helper(int st, int ed) {
        if (st > ed) {
            TreeNode node = null;
            return new ArrayList<>(Arrays.asList(node));
        }

        //[st,ed]范围已经计算过了，直接返回结果
        if(!memo[st][ed].isEmpty())
            return memo[st][ed];
        List<TreeNode> res = new ArrayList<>();
        //依次将1~n中的每个数作为根节点
        for (int i = st; i <= ed; ++i) {
            //再分别递归求出左右子树的所有组合情况
            List<TreeNode> l = helper(st, i - 1), r = helper(i + 1, ed);
            //再分别从左右子树列表取出左右子节点与根节点进行搭配
            for (TreeNode a : l) {
                for (TreeNode b : r) {
                    TreeNode root = new TreeNode(i);
                    root.left = a;
                    root.right = b;
                    res.add(root);
                }
            }
        }
        memo[st][ed] = res;
        return res;
    }
}

