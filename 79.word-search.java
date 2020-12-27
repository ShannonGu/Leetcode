/*
 * @lc app=leetcode id=79 lang=java
 *
 * [79] Word Search
 */
class Solution {
    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        if (board.length == 0)
            return false;
        int m = board.length, n = board[0].length;
        visited = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (helper(board, i, j, 0, word))
                    return true;
            }
        }
        return false;
    }

    private boolean helper(char[][] board, int i, int j, int pos, String word) {
        if (pos == word.length())
            return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j])
            return false;
        if (board[i][j] != word.charAt(pos))
            return false;
        visited[i][j] = true;
        boolean find = helper(board, i - 1, j, pos + 1, word) || helper(board, i + 1, j, pos + 1, word)
                || helper(board, i, j - 1, pos + 1, word) || helper(board, i, j + 1, pos + 1, word);
        visited[i][j] = false;
        return find;
    }

}
