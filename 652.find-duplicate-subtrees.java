import java.util.Map;

/*
 * @lc app=leetcode id=652 lang=java
 *
 * [652] Find Duplicate Subtrees
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
    //https://www.cnblogs.com/grandyang/p/7500082.html
    Map<String, Integer> m = new HashMap<>();
    List<TreeNode> res = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        helper(root);
        return res;
    }


    //首先序列化二叉树
    //并且建立序列化与其出现次数的映射
    //这样如果得到某个节点的序列化字符串，而且该字符串正好出现的次数为1,说明之前已经有一个重复的子树了,
    //可以直接将该节点存入res，保证了多个重复子树只存入一个节点
    private String helper(TreeNode root) {
        if (root == null) {
            return "#";
        }

        String str = String.valueOf(root.val) + "," + helper(root.left) + "," + helper(root.right);
        //检查哈希表中是否存在该节点为根的序列化的字符串，且只出现一次
        if(m.containsKey(str) && m.get(str) == 1)
            res.add(root);
        m.put(str, m.getOrDefault(str, 0) + 1);
        return str;
    }
}

