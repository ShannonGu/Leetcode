
import java.util.Arrays;

/*
 * @lc app=leetcode id=51 lang=java
 *
 * [51] N-Queens
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4377782.html
    int[] pos;
    List<List<String>> res;
    public List<List<String>> solveNQueens(int n) {
        pos = new int[n];
        res = new ArrayList<>();
        helper(0);
        return res;
    }
    
    private void helper(int row) {
        int n = pos.length;
        if (row == n) {
            List<String> temp = new ArrayList<>();
            char[][] str = new char[n][n];
            for (char[] s : str)
                Arrays.fill(s, '.');
            for (int i = 0; i < n; ++i) {
                str[i][pos[i]] = 'Q';
                temp.add(String.valueOf(str[i]));
            }
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int col = 0; col < n; ++col) {
            if (isValid(row, col)) {
                pos[row] = col;
                helper(row + 1);
                pos[row] = -1;
            }
        }
    }

    private boolean isValid(int row, int col) {
        for (int i = 0; i < row; ++i) {
            if (col == pos[i] || Math.abs(i - row) == Math.abs(col - pos[i]))
                return false;
        }
        return true;
    }
}

