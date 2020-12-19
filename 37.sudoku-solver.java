/*
 * @lc app=leetcode id=37 lang=java
 *
 * [37] Sudoku Solver
 *
 * https://leetcode.com/problems/sudoku-solver/description/
 *
 * algorithms
 * Hard (36.99%)
 * Total Accepted:    129.4K
 * Total Submissions: 349.7K
 * Testcase Example:  '[["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]'
 *
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * 
 * A sudoku solution must satisfy all of the following rules:
 * 
 * 
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3
 * sub-boxes of the grid.
 * 
 * 
 * Empty cells are indicated by the character '.'.
 * 
 * 
 * A sudoku puzzle...
 * 
 * 
 * ...and its solution numbers marked in red.
 * 
 * Note:
 * 
 * 
 * The given board contain only digits 1-9 and the character '.'.
 * You may assume that the given Sudoku puzzle will have a single unique
 * solution.
 * The given board size is always 9x9.
 * 
 * 
 */
class Solution {

    //http://zxi.mytechroad.com/blog/searching/leetcode-37-sudoku-solver/
    //表示第i行是否存在1~9之间的数
    boolean[][] rows = new boolean[9][10];
    //表示第i列是否存在1~9之间的数
    boolean[][] cols = new boolean[9][10];
    //表示第i个小9宫格是否存在1~9之间的数
    boolean[][] grids = new boolean[9][10];

    public void solveSudoku(char[][] board) {
        if (board.length != 9 || board[0].length != 9)
            return;
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                char c = board[i][j];
                if (c != '.') {
                    int num = c - '0';
                    int gx = i / 3, gy = j / 3;
                    //第i行存在num
                    rows[i][num] = true;
                    //第j列存在num
                    cols[j][num] = true;
                    //第gx*3+gy个小9宫格存在num 
                    grids[gx * 3 + gy][num] = true;
                }
            }
        }
        helper(board, 0, 0);
    }

    private boolean helper(char[][] board, int x, int y) {
        //说明每一行都已经遍历完毕
        if(x == 9)
            return true;
        //按列遍历
        //取第x行的下一列
        int nxtY = (y + 1) % 9;
        //nxtY==0说明到了下一行
        int nxtX = nxtY == 0 ? x + 1 : x;
        //如果[x,y]出不是'.',到下一个格继续填充
        if(board[x][y] != '.')
            return helper(board, nxtX, nxtY);
        //不为'.',则依次填充1~9，检查是否成立
        for (int i = 1; i <= 9; ++i) {
            int gx = x / 3, gy = y / 3;
            int gridKey = gx * 3 + gy;
            //第x行，第y列，所处的小9宫格，都不存在i，说明[x,y]处可以填充i
            if (!rows[x][i] && !cols[y][i] && !grids[gridKey][i]) {
                rows[x][i] = true;
                cols[y][i] = true;
                grids[gridKey][i] = true;
                board[x][y] = (char)(i + '0');
                if (helper(board, nxtX, nxtY))
                    return true;
                board[x][y] = '.';
                grids[gridKey][i] = false;
                cols[y][i] = false;
                rows[x][i] = false;
            }
        }
        return false;
    }
    
}

