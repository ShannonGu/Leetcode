/*
 * @lc app=leetcode id=576 lang=java
 *
 * [576] Out of Boundary Paths
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/6927921.html
    //https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-576-out-of-boundary-paths/
    // private final int MOD = 1000000007;
    // private int[] dirs = { -1, 0, 1, 0, -1 };

    // public int findPaths(int m, int n, int N, int i, int j) {
    //     // 从[i,j]处走k步到边界外相当于从边界外走k步到[i,j]，二者等价
    //     // dp[k][i][j]表示从边界外走k步到[i,j]的路径数;
    //     int[][][] dp = new int[N + 1][m][n];
    //     for (int k = 1; k <= N; ++k) {
    //         for (int x = 0; x < m; ++x) {
    //             for (int y = 0; y < n; ++y) {
    //                 for (int d = 0; d < 4; ++d) {
    //                     int nx = x + dirs[d];
    //                     int ny = y + dirs[d + 1];
    //                     // 若[nx, ny]是边界外的点,说明从[x, y]走一步就可以出边界
    //                     if (nx < 0 || ny < 0 || nx == m || ny == n)
    //                         dp[k][x][y] += 1;
    //                     else
    //                         dp[k][x][y] = (dp[k][x][y] + dp[k - 1][nx][ny]) % MOD;
    //                 }
    //             }
    //         }
    //     }
    //     return dp[N][i][j];
    // }


    //记忆化递归
    private int m_, n_;
    private final int MOD = 1000000007;
    private int[] dirs = { -1, 0, 1, 0, -1 };
    private int[][][] dp;
    public int findPaths(int m, int n, int N, int i, int j){
        m_ = m;
        n_ = n;
        dp = new int[N + 1][m_][n_];
        for (int[][] mem : dp) {
            for (int[] e : mem) {
                Arrays.fill(e, Integer.MIN_VALUE);
            }
        }
        return helper(N, i, j);
    }

    private int helper(int N, int x, int y) {
        //当前点在边界外，有一条路径
        if(x < 0 || y < 0 || x == m_ || y == n_)
            return 1;
        //仍在grid中，但剩余步数为0,所以不能走到边界外
        if(N == 0)
            return 0;
        //当前位置已经计算过了
        if(dp[N][x][y] != Integer.MIN_VALUE)
            return dp[N][x][y];
        int res = 0;
        for (int i = 0; i < 4; ++i) {
            int nx = x + dirs[i], ny = y + dirs[i + 1];
            res = (res + helper(N - 1, nx, ny)) % MOD;
        }
        dp[N][x][y] = res;
        return res;
    }
}
