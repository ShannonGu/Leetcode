/*
 * @lc app=leetcode id=221 lang=java
 *
 * [221] Maximal Square
 */
class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0)
            return 0;
        int m = matrix.length, n = matrix[0].length;

        // sizes[i][j]表示从[0, 0]到[i - 1, j - 1]所能构成的全是的1的方形的最大边长
        // 即sizes[i][j]为右下角的顶点的最大边长
        // 它与另外三个角的顶点的边长情况有关
        int[][] sizes = new int[m + 1][n + 1];

        int res = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                // 若i==0||j==0则不用考虑,右下角顶点在第一行或第一列不可能组成正方形
                // 因为组成最大的方形的边长最少为1，当该位置数字为'1'时;
                if (matrix[i - 1][j - 1] == '1')
                    sizes[i][j] = Math.min(Math.min(sizes[i - 1][j], sizes[i][j - 1]), sizes[i - 1][j - 1]) + 1;
                res = Math.max(res, sizes[i][j] * sizes[i][j]);
            }
        }
        return res;
    }
}
