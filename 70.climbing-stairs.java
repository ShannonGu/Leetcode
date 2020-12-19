/*
 * @lc app=leetcode id=70 lang=java
 *
 * [70] Climbing Stairs
 */
class Solution {
    public int climbStairs(int n) {
        // if(n < 1)
        //     return 0;
        // int[] dp = new int[n + 1];
        // dp[0] = 1;
        // dp[1] = 1;
        // for (int i = 2; i <= n; ++i) {
        //     dp[i] = dp[i - 2] + dp[i - 1];
        // }
        // return dp[n];

        if(n < 1)
            return 0;
        int dp0 = 1, dp1 = 1;
        int dp2 = 1;
        for (int i = 2; i <= n; ++i) {
            dp2 = dp0 + dp1;
            dp0 = dp1;
            dp1 = dp2;
        }
        return dp2;
    }
}

