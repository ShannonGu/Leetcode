/*
 * @lc app=leetcode id=52 lang=java
 *
 * [52] N-Queens II
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4380706.html
    // pos[i]表示第i行摆放皇后所在的列
    int[] pos;
    int res;
    public int totalNQueens(int n) {
        pos = new int[n];
        Arrays.fill(pos, -1);
        res = 0;
        helper(0);
        return res;
    }

    private void helper(int row) {
        int n = pos.length;
        //每一行的皇后都摆放完毕
        if (row == n) {
            ++res;
            return;
        }
        //对当前行的每一列检查是否能摆放皇后
        for (int col = 0; col < n; ++col) {
            //判断当前行row,能否在col处摆放皇后
            if (isValid(row, col)) {
                pos[row] = col;
                helper(row + 1);
                pos[row] = -1;
            }
        }
    }

    private boolean isValid(int row, int col) {
        for (int i = 0; i < row; ++i) {
            //当前摆放皇后的列已经有皇后了，
            //或者对角线上已经有皇后了
            if (col == pos[i] || Math.abs(row - i) == Math.abs(col - pos[i]))
                return false;
        }
        return true;
    }





    // boolean[] cols, diag, antiDiag;
    // int res;
    // public int totalNQueens(int n) {
    //     cols = new boolean[n];
    //     diag = new boolean[2 * n];
    //     antiDiag = new boolean[2 * n];
    //     res = 0;
    //     helper(n, 0);
    //     return res;
    // }
    
    // void helper(int n, int row) {
    //     if (row == n) {
    //         ++res;
    //         return;
    //     }
    //     for (int col = 0; col < n; ++col) {
    //         int idx1 = col - row + n, idx2 = col + row;
    //         if(cols[col] || diag[idx1] || antiDiag[idx2])
    //             continue;
    //         cols[col] = diag[idx1] = antiDiag[idx2] = true;
    //         helper(n, row + 1);
    //         cols[col] = diag[idx1] = antiDiag[idx2] = false;
    //     }
    // }
}

