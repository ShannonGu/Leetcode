/*
 * @lc app=leetcode id=684 lang=java
 *
 * [684] Redundant Connection
 */
class Solution {
    private int[] root;
    public int[] findRedundantConnection(int[][] edges) {
        root = new int[1001];
        Arrays.fill(root, -1);
        for (int[] edge : edges) {
            int v1 = find(edge[0]), v2 = find(edge[1]);
            if (v1 == v2)
                return edge;
            root[v1] = v2;
        }
        return new int[] {};
    }

    
    private int find(int x) {
        while (root[x] != -1) {
            x = root[x];
        }
        return x;
    }
}

