/*
 * @lc app=leetcode id=63 lang=java
 *
 * [63] Unique Paths II
 */
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
            return 0;
        if(obstacleGrid[0][0] == 1)
            return 0;
        int m = obstacleGrid.length , n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; ++i) {
            if (obstacleGrid[0][i] == 1)
                dp[i] = 0;
            else
                dp[i] = dp[i - 1];
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (obstacleGrid[i][j] == 1)
                    dp[j] = 0;
                else {
                    if (j != 0)
                        dp[j] += dp[j - 1];
                }
            }
        }
        return dp[n - 1];
    }
}
