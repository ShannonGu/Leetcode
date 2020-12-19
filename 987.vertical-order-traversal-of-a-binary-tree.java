import java.util.Iterator;

/*
 * @lc app=leetcode id=987 lang=java
 *
 * [987] Vertical Order Traversal of a Binary Tree
 */
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */

 //https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/solution/
class Solution {
    List<Location> locations;

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if (root == null)
            return new ArrayList<List<Integer>>();
        locations = new ArrayList<>();
        dfs(root, 0, 0);
        //遍历完成后对locations排序
        Collections.sort(locations);
        List<List<Integer>> res = new ArrayList<>();
        int prevLoc = Integer.MIN_VALUE;
        for (Location loc : locations) {
            if (loc.x != prevLoc) {
                prevLoc = loc.x;
                res.add(new ArrayList<Integer>());
            }

            res.get(res.size() - 1).add(loc.val);
        }
        return res;
    }

    //dfs先序遍历每一个节点
    private void dfs(TreeNode node, int x, int y) {
        if (node != null) {
            locations.add(new Location(x, y, node.val));
            dfs(node.left, x - 1, y + 1);
            dfs(node.right, x + 1, y + 1);
        }
    }


    //首先构造一个Location类
    //并重写排序规则
    class Location implements Comparable<Location> {
        int x, y, val;

        Location(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
        
        @Override
        public int compareTo(Location that) {
            if(this.x != that.x)
                return Integer.compare(this.x, that.x);
            else if(this.y != that.y)
                return Integer.compare(this.y, that.y);
            else
                return Integer.compare(this.val, that.val);
        }
    }
}
