/*
 * @lc app=leetcode id=449 lang=java
 *
 * [449] Serialize and Deserialize BST
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
    private final String spliter = ",";
    private final String N = "#";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder res = new StringBuilder("");
        buildString(root, res);
        return res.toString();
    }

    private void buildString(TreeNode root, StringBuilder res) {
        if (root == null) {
            res.append(N).append(spliter);
        } else {
            res.append(root.val).append(spliter);
            buildString(root.left, res);
            buildString(root.right, res);
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
        if (tmp.equals(N)) {
            return null;
        } else {
            TreeNode root = new TreeNode(Integer.parseInt(tmp));
            root.left = buildTree(nodes);
            root.right = buildTree(nodes);
            return root;
        }
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

