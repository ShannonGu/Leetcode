/*
 * @lc app=leetcode id=695 lang=java
 *
 * [695] Max Area of Island
 */
class Solution {
    private int[][] dires = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    private int row, col;
    private int res = 0, tmp = 0;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return 0;

        row = grid.length;
        col = grid[0].length;
        // 不使用visited数组,用改变grid数组的值替代,将遍历过的位置的值改为-1;
        // vector<vector<int>> visited(row, vector<int>(col, 0));
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (grid[i][j] != 1)
                    continue;
                tmp = 0;
                helper(grid, i, j);
            }
        }
        return res;
    }

    void helper(int[][] grid, int x, int y) {
        if (x < 0 || x == row || y < 0 || y == col || grid[x][y] <= 0) {
            res = Math.max(res, tmp);
            return;
        }
        tmp++;
        
        // 标记grid[x][y]已经遍历过;
        grid[x][y] = -1;
        for (int[] dire : dires) {
            helper(grid, x + dire[0], y + dire[1]);
        }
    }
}
