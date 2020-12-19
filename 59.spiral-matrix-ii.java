/*
 * @lc app=leetcode id=59 lang=java
 *
 * [59] Spiral Matrix II
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4362813.html
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int up = 0, down = n - 1, left = 0, right = n - 1;
        int cnt = 0;
        while (true) {
            for (int i = left; i <= right; ++i)
                res[up][i] = ++cnt;
            if (++up > down)
                break;

            for (int i = up; i <= down; ++i)
                res[i][right] = ++cnt;
            if (--right < left)
                break;

            for (int i = right; i >= left; --i)
                res[down][i] = ++cnt;
            if (--down < up)
                break;
                
            for (int i = down; i >= up; --i)
                res[i][left] = ++cnt;
            if (++left > right)
                break;
        }
        return res;
    }
}

