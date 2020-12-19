/*
 * @lc app=leetcode id=123 lang=java
 *
 * [123] Best Time to Buy and Sell Stock III
 */
class Solution {
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/most-consistent-ways-of-dealing-with-the-series-of-stock-problems

    //https://www.jianshu.com/p/612bc5f1f131
    // public int maxProfit(int[] prices) {
    //     int len = prices.length;
    //     if(len == 0 || len < 2)
    //         return 0;
    //     int k = 2;

    //     //dp[i][j]表示到第j天最多i次交易所能获得的最大收益
    //     int[][] dp = new int[k + 1][len];

    //     //最多2次交易;
    //     for (int i = 1; i <= k; ++i) {
    //         //当天之前最后1次买入时的收益;
    //         int preBuy = dp[i - 1][0] - prices[0];

    //         for (int j = 1; j < len; ++j) {
    //             //若第j天没有卖出行为，则与第j-1天的最大收益相同;
    //             dp[i][j] = dp[i][j - 1];

    //             //若第j天卖出,则加上此前最后一次买入之前所获得的收益
    //             //与第j天没有卖出所获得收益比较，取大者;
    //             dp[i][j] = Math.max(dp[i][j], prices[j] + tmpMax);

    //             //更新最后一次买入时的收益;
    //             preBuy = Math.max(preBuy, dp[i - 1][j] - prices[j]);
    //         }
    //     }
    //     return dp[2][len - 1];
    // }

    // public int maxProfit(int[] prices) {
    //     if (prices.length == 0)
    //         return 0;
    //     int[][] dp = new int[3][2];
    //     dp[1][0] = 0;
    //     dp[1][1] = -prices[0];
    //     dp[2][0] = 0;
    //     dp[2][1] = -prices[0];
    //     for (int i = 1; i < prices.length; ++i) {
    //         dp[1][0] = Math.max(dp[1][0], dp[1][1] + prices[i]);
    //         dp[1][1] = Math.max(dp[1][1], dp[0][0] - prices[i]);
    //         dp[2][0] = Math.max(dp[2][0], dp[2][1] + prices[i]);
    //         dp[2][1] = Math.max(dp[2][1], dp[1][0] - prices[i]);
    //     }
    //     return dp[2][0];
    // }
    
    
    public int maxProfit(int[] prices) {
        if(prices.length < 2)
            return 0;
        //T_i00 = 0;
        int T_i10 = 0, T_i11 = Integer.MIN_VALUE;
        int T_i20 = 0, T_i21 = Integer.MIN_VALUE;
        for (int price : prices) {
            T_i10 = Math.max(T_i10, T_i11 + price);
            T_i11 = Math.max(T_i11, 0 - price);
            T_i20 = Math.max(T_i20, T_i21 + price);
            T_i21 = Math.max(T_i21, T_i10 - price);
        }
        return T_i20;
    }
}

