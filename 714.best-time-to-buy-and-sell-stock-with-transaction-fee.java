/*
 * @lc app=leetcode id=714 lang=java
 *
 * [714] Best Time to Buy and Sell Stock with Transaction Fee
 */
class Solution {
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/most-consistent-ways-of-dealing-with-the-series-of-stock-problems
    // 设T[i][k]表示到第i天经过至多k次交易后能得到的最大收益
    // 于是T[-1][k] = T[i][0] = 0,因为(没有股票或者经过0次交易不会产生收益)
    // 而经过buy，rest或者sell操作后，每天都会有两种状态即有股票在手和无股票在手
    // 于是T[i][k]可以被拆分成两种状态T[i][k][0], T[i][k][1];
    // 前者表示到第i天经过至多k次交易后没有股票在手的最大收益
    // 后者表示到第i天经过至多k次交易后有股票在手的最大收益

    // 初始状态T[-1][k][0] = 0(没有股票可供交易), T[-1][k][1]=-INF(在没有股票可供交易的情况下，不可能有股票在手)
    // T[i][0][0] = 0(交易0次), T[i][0][1] = -INF(在交易0次的情况下，不可能有股票在手)

    // 状态转移方程
    // T[i][k][0] = max(T[i-1][k][0], T[i-1][k][1] + prices[i])
    // 对于第i天T[i][k][0]，只能rest或者sell,因为在第i天没有股票在手，
    // T[i-1][k][0]表示第i天rest, T[i-1][k][1]+prices[i]表示第i天卖出

    // T[i][k][1] = max(T[i-1][k][1], T[i-1][k][0] - prices[i])
    // 对于第i天T[i][k][1]，只能rest或者buy,因为第i天有股票在手;
    // T[i-1][k][1]表示第i天rest,T[i-1][k][0]-prices[i]表示第i天买入

    //最终答案是T[i][k][0]即到i天结束经过至多k次交易后没有股票在手的最大利润
    // public int maxProfit(int[] prices, int fee) {
    //     if (prices.length == 0)
    //         return 0;
    //     // 具体到这一题
    //     // 有本题交易次数k是不限制的，所以可以去掉这一维
    //     int len = prices.length;
    //     //dp[i][0]表示第i天没有股票在手的最大收益，dp[i][1]表示第i天有股票在手的最大收益
    //     int[][] dp = new int[len][2];
    //     dp[0][0] = 0;
    //     // dp[0][1] = -prices[0];
    //     dp[0][1] = -prices[0] - fee;
    //     //扣除fee是有两种选择，一种是在买入时扣除fee,一种是在卖出时扣除fee,相对应的初始状态也不同
    //     for (int i = 1; i < len; ++i) {
    //         // dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
    //         // dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
    //         dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
    //         dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);

    //     }
    //     return dp[len - 1][0];
    // }

    //继续优化空间复杂度
    //由于dp[i][0],dp[i][0]这和dp[i-1][0], dp[i-1][1]有关
    //进而可以去掉i这一维
    // public int maxProfit(int[] prices, int fee) {
    //     if(prices.length == 0)
    //         return 0;
    //     int len = prices.length;
    //     int T_ik0 = 0, T_ik1 = -prices[0] - fee;
    //     for (int i = 1; i < len; ++i) {
    //         int preT_ik0 = T_ik0;
    //         T_ik0 = Math.max(T_ik0, T_ik1 + prices[i]);
    //         T_ik1 = Math.max(T_ik1, preT_ik0 - prices[i] - fee);
    //     }
    //     return T_ik0;
    // }

    public int maxProfit(int[] prices, int fee) {
        if(prices.length < 2)
            return 0;
        int T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;
        for (int price : prices) {
            int preT_ik0 = T_ik0;
            T_ik0 = Math.max(T_ik0, T_ik1 + price);
            T_ik1 = Math.max(T_ik1, preT_ik0 - price - fee);
        }
        return T_ik0;
    }
}
