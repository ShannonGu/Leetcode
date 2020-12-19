/*
 * @lc app=leetcode id=188 lang=java
 *
 * [188] Best Time to Buy and Sell Stock IV
 */
class Solution {
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/most-consistent-ways-of-dealing-with-the-series-of-stock-problems
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/discuss/54113/a-concise-dp-solution-in-java
    public int maxProfit(int k, int[] prices) {
        if (k == 0 || prices.length < 2)
            return 0;
        // 相当于不限交易次数
        if (k >= prices.length / 2) {
            int T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;
            for (int price : prices) {
                int preT_ik0 = T_ik0;
                T_ik0 = Math.max(T_ik0, T_ik1 + price);
                T_ik1 = Math.max(T_ik1, T_ik0 - price);
            }
            return T_ik0;
        }

        // 到第i天经过至多k次交易时没有股票在手的最大收益
        int[] T_i0 = new int[k + 1];
        // 到第i天经过至多k次交易时有股票在手的最大收益
        int[] T_i1 = new int[k + 1];
        Arrays.fill(T_i1, Integer.MIN_VALUE);

        for (int price : prices) {
            for (int j = 1; j <= k; ++j) {
                T_i0[j] = Math.max(T_i0[j], T_i1[j] + price);
                T_i1[j] = Math.max(T_i1[j], T_i0[j - 1] - price);
            }
        }

        return T_i0[k];
    }
}
