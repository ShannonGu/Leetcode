/*
 * @lc app=leetcode id=529 lang=java
 *
 * [529] Minesweeper
 */
class Solution {
    private int m, n;

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board.length == 0 || board[0].length == 0)
            return board;
        m = board.length;
        n = board[0].length;

        if(board[click[0]][click[1]] == 'M')
            board[click[0]][click[1]] = 'X';
        else
            helper(board, click[0], click[1]);
        return board;
    }
    

    private void helper(char[][] board, int i, int j) {
        //若当前点为'B'
        if(i < 0 || i == m || j <0 || j == n || board[i][j] == 'B')
            return;
        //计算周边'M'的个数
        int cnt = countMines(board, i, j);
        if (cnt == 0) {
            board[i][j] = 'B';
            for (int x = -1; x < 2; ++x) {
                for (int y = -1; y < 2; ++y) {
                    int r = i + x, c = j + y;
                    helper(board, r, c);
                }
            }
        } else {
            board[i][j] = (char) (cnt + '0');
        }
    }
    

    private int countMines(char[][] board, int x, int y) {
        int cnt = 0;
        for (int i = -1; i < 2; ++i) {
            for (int j = -1; j < 2; ++j) {
                int r = x + i, c = y + j;
                if (r < 0 || r == m || c < 0 || c == n)
                    continue;
                if (board[r][c] == 'M')
                    ++cnt;
            }
        }
        return cnt;
    }
}

