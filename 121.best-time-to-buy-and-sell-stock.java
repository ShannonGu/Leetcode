/*
 * @lc app=leetcode id=121 lang=java
 *
 * [121] Best Time to Buy and Sell Stock
 */
class Solution {
    // public int maxProfit(int[] prices) {
    //     if (prices.length == 0)
    //         return 0;
    //     int res = 0, minPrice = prices[0];
    //     for (int i = 0; i < prices.length; ++i) {
    //         if (prices[i] > minPrice) {
    //             res = Math.max(res, prices[i] - minPrice);
    //         } else {
    //             minPrice = prices[i];
    //         }
    //     }
    //     return res;
    // }

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/most-consistent-ways-of-dealing-with-the-series-of-stock-problems
    public int maxProfit(int[] prices) {
        if(prices.length == 0)
            return 0;
        int T_i10 = 0, T_i11 = Integer.MIN_VALUE;
        //T_i00 = 0;
        for (int price : prices) {
            T_i10 = Math.max(T_i10, T_i11 + price);
            // T_i11 = Math.max(T_i11, T_i00 - price);
            T_i11 = Math.max(T_i11, -price);

        }
        return T_i10;
    }
}
