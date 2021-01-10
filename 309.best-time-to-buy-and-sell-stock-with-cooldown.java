/*
 * @lc app=leetcode id=309 lang=java
 *
 * [309] Best Time to Buy and Sell Stock with Cooldown
 */
class Solution {

    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/most-consistent-ways-of-dealing-with-the-series-of-stock-problems
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] f1 = new int[n + 1], f2 = new int[n + 1], f3 = new int[n + 1];
        f1[0] = f2[0] = 0;
        f3[0] = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            f3[i] = Math.max(f3[i - 1], f2[i - 1] - prices[i - 1]);
            f1[i] = f3[i - 1] + prices[i - 1];
            f2[i] = Math.max(f2[i - 1], f1[i - 1]);
        }

        return Math.max(f1[n], f2[n]);
    }

    public int maxProfit(int[] prices) {
        // T_i1表示当前是手中没有股票的第一天
        // T_i2表示当前是手中没有股票的第二天
        // T_i3表示当前手中有股票
        int T_i1 = 0, T_i2 = 0, T_i3 = Integer.MIN_VALUE;

        for (int price : prices) {
            int preT_i3 = T_i3, preT_i1 = T_i1;
            // 手中有股票只能从原状态维持下来，或者由没有股票的第二天这种状态转化而来
            T_i3 = Math.max(T_i3, T_i2 - price);
            // 没有股票的第1天这种状态只能由手中有股票这种状态卖出后得到
            T_i1 = preT_i3 + price;
            // 没有股票的第2天只能从原状态维持，或者没有股票的第1天转化而来
            T_i2 = Math.max(T_i2, preT_i1);
        }
        // 最后比较两种当前手中没有股票的状态
        return Math.max(T_i1, T_i2);
    }
}
