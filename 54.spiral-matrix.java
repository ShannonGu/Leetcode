/*
 * @lc app=leetcode id=54 lang=java
 *
 * [54] Spiral Matrix
 */

// @lc code=start
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return new ArrayList<>();
        int r = matrix.length, c = matrix[0].length;
        int up = 0, down = r - 1, left = 0, right = c - 1;
        List<Integer> res = new ArrayList<>();
        int cnt = 0;
        while (cnt < r * c) {
            for (int j = left; j <= right; ++j) {
                res.add(matrix[up][j]);
                ++cnt;
            }
            ++up;
            if (cnt >= r * c)
                break;
            for (int i = up; i <= down; ++i) {
                res.add(matrix[i][right]);
                ++cnt;
            }
            --right;
            if (cnt >= r * c)
                break;
            for (int j = right; j >= left; --j) {
                res.add(matrix[down][j]);
                ++cnt;
            }
            --down;
            if (cnt >= r * c)
                break;
            for (int i = down; i >= up; --i) {
                res.add(matrix[i][left]);
                ++cnt;
            }
            ++left;
        }
        return res;
    }
}
// @lc code=end
