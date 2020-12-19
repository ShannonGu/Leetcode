/*
 * @lc app=leetcode id=200 lang=java
 *
 * [200] Number of Islands
 */
class Solution {
    //Union-Find
    private int[] father;
    private int[][] dires = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    public int numIslands(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0)
            return 0;
        int m = grid.length, n = grid[0].length;
        father = new int[m * n];
        for (int i = 0; i < m * n; ++i)
            father[i] = i;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    for (int[] dire : dires) {
                        int x = i + dire[0], y = j + dire[1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                            unite(i * n + j, x * n + y);
                        }
                    }
                }
            }
        }


        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    if (father[i * n + j] == i * n + j)
                        ++res;
                }
            }
        }
        
        return res;
    }

    private int find(int x) {
        if (x == father[x])
            return x;
        father[x] = find(father[x]);
        return father[x];
    }
    
    private void unite(int x, int y) {
        x = find(x);
        y = find(y);
        if(x != y)
            father[x] = y;
    }
}

