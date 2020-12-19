/*
 * @lc app=leetcode id=322 lang=java
 *
 * [322] Coin Change
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/5138186.html
    //完全背包优化
    //f(i, j)      =min(f(i-1, j - coin*k)+k) = min(f(i-1, j), f(i-1, j-coin)+1 , f(i-1, j-2*coin)+2, f(i-1, j-3*coin)+3, ...)
    //f(i, j-coin) =                          = min(           f(i-1, j-coin),    f(i-1, j-2*coin)+1, f(i-1, j-3*coin)+2, ...)
    //f(i,j) = min(f(i-1,j), f(i,j-coin)+1);
    public int coinChange(int[] coins, int amount) {
        // dp[i]表示钱数为i时的最少的硬币找零数
        int[] dp = new int[amount + 1];
        //最小的硬币是1，最多需要amount个硬币可以组成amount
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int coin : coins) {
            for (int j = coin; j <= amount; ++j) {
                dp[j] = Math.min(dp[j], dp[j - coin] + 1);
            }
        }

        //dp[amount] > amount说明不存在可以分解的情况
        return dp[amount] > amount ? -1 : dp[amount];
    }



    // private int[] mem;

    // public int coinChange(int[] coins, int amount) {
    //     mem = new int[amount + 1];
    //     Arrays.fill(mem, Integer.MAX_VALUE);
    //     mem[0] = 0;
    //     return helper(coins, amount);
    // }

    // private int helper(int[] coins, int target) {
    //     if (target < 0)
    //         return -1;

    //     if (mem[target] != Integer.MAX_VALUE)
    //         return mem[target];

    //     int ans = Integer.MAX_VALUE;
    //     for (int i = 0; i < coins.length; ++i) {
    //         int tmp = helper(coins, target - coins[i]);
    //         if (tmp >= 0)
    //             ans = Math.min(ans, tmp + 1);
    //     }

    //     mem[target] = (ans == Integer.MAX_VALUE ? -1 : ans);
    //     return mem[target];
    // }
}
