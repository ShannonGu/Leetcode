/*
 * @lc app=leetcode id=688 lang=java
 *
 * [688] Knight Probability in Chessboard
 */
class Solution {
    public double knightProbability(int N, int K, int r, int c) {
        if (K == 0)
            return 1.0;
        // dp[k][i][j]表示经过k步能够移动到[i, j]的还留在棋盘内的走法总和
        // 则dp[k][i][j]= sum(dp[k-1][x][y] for all (x,y) that can move to (i,j))
        // 所以可以省去一维
        double[][] dp0 = new double[N][N];
        // 走0步移动到[i,j]仍在棋盘内的走法为1
        for (double[] e : dp0)
            Arrays.fill(e, 1);
        int[][] dirs = { { -2, -1 }, { -2, 1 }, { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 }, { 2, -1 }, { 2, 1 } };
        for (int k = 0; k < K; ++k) {
            double[][] dp1 = new double[N][N];
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    for (int[] dir : dirs) {
                        int x = i + dir[0], y = j + dir[1];
                        if (x < 0 || y < 0 || x >= N || y >= N)
                            continue;
                        // [x,y]是可以到达[i,j]的
                        dp1[i][j] += dp0[x][y];
                    }
                }
            }
            dp0 = dp1;
        }
        return dp0[r][c] / Math.pow(8, K);
    }
}
