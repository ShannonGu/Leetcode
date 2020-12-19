/*
 * @lc app=leetcode id=983 lang=java
 *
 * [983] Minimum Cost For Tickets
 */
class Solution {
    // https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-983-minimum-cost-for-tickets/
    public int mincostTickets(int[] days, int[] costs) {
        
        int n = days[days.length - 1];
        // dp[i]表示旅行到第i天需要的最少费用
        int[] dp = new int[n + 1];
        // 标记第i天是否需要旅行
        boolean[] need = new boolean[n + 1];
        for (int day : days) {
            need[day] = true;
        }
        dp[0] = 0;
        for (int i = 1; i <= n; ++i) {
            // 第i天不需要旅行
            // 则和第i-1天的费用相同
            if (!need[i]) {
                dp[i] = dp[i - 1];
                continue;
            }
            // 否则考虑三种买票方式
            dp[i] = dp[i - 1] + costs[0];
            // 注意下标越界的问题
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 7)] + costs[1]);
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 30)] + costs[2]);
        }
        return dp[n];
    }
}
