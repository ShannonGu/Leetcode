/*
 * @lc app=leetcode id=122 lang=java
 *
 * [122] Best Time to Buy and Sell Stock II
 */
class Solution {
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/most-consistent-ways-of-dealing-with-the-series-of-stock-problems
    public int maxProfit(int[] prices) {
        // Tik0表示到i天经过k次没有stock在手
        // Tik1表示到i天经过k次有stock在手
        int Tik0 = 0, Tik1 = Integer.MIN_VALUE;
        for (int price : prices) {
            int Tik0_old = Tik0;
            // 将i-1天k次交易没有stock在手，和i-1天k次交易有stock在手在第i天卖出收益相比
            Tik0 = Math.max(Tik0, Tik1 + price);
            // 将i-1天k次交易有stock在手, 和i-1天k-1交易没有stock在手并在第i天买入所获收益相比
            Tik1 = Math.max(Tik1, Tik0_old - price);
        }
        
        return Tik0;
    }
}
