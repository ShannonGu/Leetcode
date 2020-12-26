/*
 * @lc app=leetcode id=695 lang=java
 *
 * [695] Max Area of Island
 */
class Solution {
    // 方向数组
    private int[] dires = { -1, 0, 1, 0, -1 };

    // solution1 递归
    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return 0;

        row = grid.length;
        col = grid[0].length;
        // 不使用visited数组, 用改变grid数组的值替代,将遍历过的位置的值改为-1;
        // vector<vector<int>> visited(row, vector<int>(col, 0));
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (grid[i][j] == 1) {
                    tmp = 0;
                    helper(grid, i, j);
                }
            }
        }
        return res;
    }

    private int row, col;
    private int res = 0, tmp = 0;

    private void helper(int[][] grid, int x, int y) {
        if (x < 0 || x == row || y < 0 || y == col || grid[x][y] <= 0) {
            res = Math.max(res, tmp);
            return;
        }
        tmp++;
        // 标记grid[x][y]已经遍历过;
        grid[x][y] = -1;
        for (int k = 0; k < 4; k++) {
            helper(grid, x + dires[k], y + dires[k + 1]);
        }
    }

    // solution2 利用栈迭代
    // public int maxAreaOfIsland(int[][] grid) {
    // int m = grid.length, n = m > 0 ? grid[0].length : 0;
    // int curArea = 0, res = 0;
    // for (int i = 0; i < m; i++) {
    // for (int j = 0; j < n; j++) {
    // if (grid[i][j] == 1) {
    // curArea = 1;
    // grid[i][j] = -1;
    // Stack<int[]> isLand = new Stack<>();
    // isLand.push(new int[] { i, j });
    // while (!isLand.isEmpty()) {
    // int[] tmp = isLand.pop();
    // for (int k = 0; k < 4; k++) {
    // int x = tmp[0] + dires[k], y = tmp[1] + dires[k + 1];
    // if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
    // grid[x][y] = -1;
    // isLand.push(new int[] { x, y });
    // ++curArea;
    // }
    // }
    // }
    // res = Math.max(res, curArea);
    // }
    // }
    // }
    // return res;
    // }
}
