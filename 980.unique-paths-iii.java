/*
 * @lc app=leetcode id=980 lang=java
 *
 * [980] Unique Paths III
 */
class Solution {
    private int[][] dires = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    private int res = 0;

    public int uniquePathsIII(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return 0;
        int m = grid.length, n = grid[0].length;
        
        //注意这里cnt初始为1，因为这里表示的是要经过non-obstacle的点,也包括1
        int stx = -1, sty = -1, cnt = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0)
                    ++cnt;
                else if (grid[i][j] == 1) {
                    stx = i;
                    sty = j;
                }
            }
        }

        helper(grid, stx, sty, cnt);
        return res;
    }

    private void helper(int[][] grid, int x, int y, int cnt) {

        if (x < 0 || x == grid.length || y < 0 || y == grid[0].length || grid[x][y] == -1)
            return;
        if (grid[x][y] == 2) {
            if (cnt == 0)
                ++res;
            return;
        }

        //将当前访问过的位置标记为-1，可以避免创建访问数组
        grid[x][y] = -1;
        for (int[] dire : dires) {
            int nx = x + dire[0], ny = y + dire[1];
            helper(grid, nx, ny, cnt - 1);
        }
        grid[x][y] = 0;
    }
}
