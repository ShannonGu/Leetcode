/*
 * @lc app=leetcode id=221 lang=java
 *
 * [221] Maximal Square
 */
class Solution {
    // public int maximalSquare(char[][] matrix) {
    //     int r = matrix.length;
    //     if(r == 0)
    //         return 0;
    //     int c = matrix[0].length;
    //     int[][] dp = new int[r][c];
    //     for (int i = 0; i < r; ++i) {
    //         for (int j = 0; j < c; ++j) {
    //             dp[i][j] = (matrix[i][j] == '1') ? (j == 0 ? 1 : dp[i][j - 1] + 1) : 0;
    //         }
    //     }

    //     int res = 0;
    //     for (int i = 0; i < r; ++i) {
    //         for (int j = 0; j < c; ++j) {
    //             int len = c;
    //             for (int k = i; k < r; ++k) {
    //                 len = Math.min(len, dp[k][j]);
    //                 if (len == 0)
    //                     break;
    //                 if (len != k - i + 1)
    //                     continue;
    //                 res = Math.max(res, len * (k - i + 1));
    //             }
    //         }
    //     }
    //     return res;
    // }

    //https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-221-maximal-square/
    // public int maximalSquare(char[][] matrix) {
    //     if(matrix.length == 0)
    //         return 0;
    //     int m = matrix.length, n = matrix[0].length;

    //     //sum[i][j] = sum(matrix[0][0] ~ matrix[i - 1][j - 1]);
    //     //sums[i][j]表示以matrix[i][j]为右下角顶点的矩形的含有 '1' 的个数
    //     int[][] sums = new int[m + 1][n + 1];

    //     for (int i = 1; i <= m; ++i) {
    //         for (int j = 1; j <= n; ++j) {
    //             sums[i][j] = sums[i - 1][j] + sums[i][j - 1] - sums[i - 1][j - 1] + (matrix[i - 1][j - 1] - '0');
    //         }
    //     }

    //     int res = 0;
    //     //[i, j]表示方形的左上角顶点
    //     for (int i = 1; i <= m; ++i) {
    //         for (int j = 1; j <= n; ++j) {
    //             //k表示方形边长
    //             //这里有一个技巧,边长从大递减，这样大的存在，就不用算小的了
    //             for (int k = Math.min(m - i + 1, n - j + 1); k > 0; --k) {

    //                 int sum = sums[i + k - 1][j + k - 1] - sums[i + k - 1][j - 1] - sums[i - 1][j + k - 1]
    //                         + sums[i - 1][j - 1];

    //                 //该方形是满足要求的
    //                 if (sum == k * k) {
    //                     res = Math.max(res, sum);
    //                     break;
    //                 }
    //             }
    //         }
    //     }
    //     return res;
    // }


    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0)
            return 0;
        int m = matrix.length, n = matrix[0].length;

        //sizes[i][j]表示从[0, 0]到[i, j]所能构成的全是的1的方形的最大边长
        //即sizes[i][j]为右下角的顶点的最大边长
        //它与另外三个角的顶点的边长情况有关
        int[][] sizes = new int[m][n];

        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                sizes[i][j] = matrix[i][j] - '0';
                //该点本就是'0'
                if (sizes[i][j] == 0)
                    continue;

                //若i==0||j==0则不用考虑,右下角顶点在第一行或第一列不可能组成正方形
                //因为组成最大的方形的边长最少为1，当该位置数字为'1'时;
                if(i != 0 && j != 0)
                    sizes[i][j] = Math.min(Math.min(sizes[i - 1][j], sizes[i][j - 1]), sizes[i - 1][j - 1]) + 1;

                res = Math.max(res, sizes[i][j] * sizes[i][j]);
            }
        }
        return res;
    }
}

