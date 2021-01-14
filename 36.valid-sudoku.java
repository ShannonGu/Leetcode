/*
 * @lc app=leetcode id=36 lang=java
 *
 * [36] Valid Sudoku
 */
class Solution {
    // rows[i][num]表示第i行是否已存在num
    boolean[][] rows = new boolean[9][10];
    // cols[i][num]表示第i列是否存在num
    boolean[][] cols = new boolean[9][10];
    // grids[i][num]表示第i个方块是否存在num
    boolean[][] grids = new boolean[9][10];

    public boolean isValidSudoku(char[][] board) {
        if (board.length != 9 || board[0].length != 9)
            return false;
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.')
                    continue;
                char c = board[i][j];
                int num = c - '0';
                int gx = i / 3, gy = j / 3;
                // 第i行或者第j列或者该方块已存在num
                // 则该九宫格不合法
                if (rows[i][num] || cols[j][num] || grids[gx * 3 + gy][num])
                    return false;
                rows[i][num] = true;
                cols[j][num] = true;
                grids[gx * 3 + gy][num] = true;
            }
        }
        return true;
    }
}
