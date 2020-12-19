/*
 * @lc app=leetcode id=64 lang=java
 *
 * [64] Minimum Path Sum
 */
class Solution {
    // public int minPathSum(int[][] grid) {
    //     if(grid.length == 0 || grid[0].length == 0)
    //         return 0;
    //     int m = grid.length, n = grid[0].length;
    //     int[][] dp= new int[m][n];
    //     dp[0][0] = grid[0][0];
    //     for (int i = 1; i < n; ++i)
    //         dp[0][i] = dp[0][i - 1] + grid[0][i];
    //     for (int i = 1; i < m; ++i)
    //         dp[i][0] = dp[i - 1][0] + grid[i][0];
    //     for (int i = 1; i < m; ++i) {
    //         for (int j = 1; j < n; ++j) {
    //             dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
    //         }
    //     }
    //     return dp[m - 1][n - 1];
    // }

    public int minPathSum(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0)
            return 0;
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n + 1];
        dp[0] = grid[0][0];
        for(int i = 1; i < n; ++i)
            dp[i] = dp[i-1] + grid[0][i];
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (j == 0)
                    dp[j] = dp[j] + grid[i][j];
                else {
                    dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
                }
            }
        }
        return dp[n - 1];
    }
}
