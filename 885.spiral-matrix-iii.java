/*
 * @lc app=leetcode id=885 lang=java
 *
 * [885] Spiral Matrix III
 */

// @lc code=start
class Solution {
    // https://www.cnblogs.com/grandyang/p/10887598.html
    private int[][] res;
    private int cnt = 0;

    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        res = new int[R * C][2];
        int step = 1;
        while (cnt < R * C) {
            for (int i = 0; i < step; ++i)
                add(R, C, r0, c0++);
            for (int i = 0; i < step; ++i)
                add(R, C, r0++, c0);
            ++step;
            for (int i = 0; i < step; ++i)
                add(R, C, r0, c0--);
            for (int i = 0; i < step; ++i)
                add(R, C, r0--, c0);
            ++step;
        }
        return res;
    }

    private void add(int R, int C, int x, int y) {
        if (x >= 0 && x < R && y >= 0 && y < C) {
            res[cnt][0] = x;
            res[cnt][1] = y;
            ++cnt;
        }
    }
}
// @lc code=end
