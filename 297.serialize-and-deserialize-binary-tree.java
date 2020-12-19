import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * @lc app=leetcode id=297 lang=java
 *
 * [297] Serialize and Deserialize Binary Tree
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
public class Codec {
    // https://leetcode.com/problems/serialize-and-deserialize-binary-tree/discuss/74253/Easy-to-understand-Java-Solution
    private final String spliter = ",";
    private final String NN = "#";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder str = new StringBuilder();
        buildString(root, str);
        return str.toString();
    }

    //先序遍历树，将遇到的节点存入StringBuilder中
    private void buildString(TreeNode node, StringBuilder str) {
        if (node == null) {
            str.append(NN).append(spliter);
        } else {
            str.append(node.val).append(spliter);
            buildString(node.left, str);
            buildString(node.right, str);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        LinkedList<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(nodes);
    }

    
    private TreeNode buildTree(LinkedList<String> nodes) {
        String tmp = nodes.remove();
        if (tmp.equals(NN))
            return null;
        else {
            TreeNode root = new TreeNode(Integer.valueOf(tmp));
            root.left = buildTree(nodes);
            root.right = buildTree(nodes);
            return root;
        }
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

