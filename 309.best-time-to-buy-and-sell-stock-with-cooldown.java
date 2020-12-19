/*
 * @lc app=leetcode id=309 lang=java
 *
 * [309] Best Time to Buy and Sell Stock with Cooldown
 */
class Solution {

    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/most-consistent-ways-of-dealing-with-the-series-of-stock-problems
    public int maxProfit(int[] prices) {
        if(prices.length == 0)
            return 0;
        //两天前的不持有状态
        int preT_ik0 = 0, T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;

        for (int price : prices) {
            //如果前一天卖出的话，不能在当前时间买入
            int oldT_ik0 = T_ik0;

            T_ik0 = Math.max(T_ik0, T_ik1 + price);
            T_ik1 = Math.max(T_ik1, preT_ik0 - price);
            
            //将前一天的不持有状态作为作为下一轮的两天前的不持有状态
            preT_ik0 = oldT_ik0;
        }
        return T_ik0;
    }
}

